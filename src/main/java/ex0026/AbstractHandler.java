package ex0026;

import java.net.Socket;
import java.net.SocketAddress;

public abstract class AbstractHandler implements Runnable {
    private Socket socketClient;

    public void handle(Socket socket) {
        this.socketClient = socket;
    }
    
    @Override
    public void run() {
        SocketAddress address = socketClient.getRemoteSocketAddress();
        System.out.println("Verbindung zu " + address + " hergestellt.");
        
        try {
            System.out.println(Thread.currentThread().getName() + " 1");
            runTask(socketClient);
            System.out.println(Thread.currentThread().getName() + " 2");
        } catch (Exception ex) {
            System.err.println("---> run / AbstractHandler");
            System.err.println(ex);
        }
    }

    protected abstract void runTask(Socket socketClient);
}
