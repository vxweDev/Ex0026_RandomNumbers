package ex0026;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TCPServer {
    private Thread thread;
    private final ServerSocket serverSocket;
    private final ExecutorService pool;
    private final AbstractHandler handler;

    public TCPServer(int port, AbstractHandler handler) throws IOException {
        this.handler = handler;

        this.serverSocket = new ServerSocket(port);
        this.pool = Executors.newCachedThreadPool();
    }

    private Runnable work() {
        return () -> {
            try {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + " 1");
                    Socket clientSocket = serverSocket.accept();
                    System.out.println(Thread.currentThread().getName() + " 2");

                    handler.handle(clientSocket);
                    pool.execute(handler);
                }
            } catch (IOException ex) {
                System.err.println("---> work / TCPServer");
                System.err.println(ex);
            }
        };
    }

    public void startServer() {
        thread = new Thread(work());
        thread.setName("TCPServer");
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
    }

    public void stopServer() throws IOException {
        pool.shutdown();
        serverSocket.close();
    }
}
