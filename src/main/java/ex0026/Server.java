package ex0026;

import java.io.IOException;

public class Server {
  public static void main(String[] args) throws IOException {
    final int port = 5000;
    TCPServer server = new TCPServer(port, new CalculatorHandler());
    server.startServer();

    System.out.println("Server gestartet.");
    System.out.println("ENTER stoppt den Server.");
    System.in.read();

    server.stopServer();
    System.out.println("Server gestoppt.");
  }
}
