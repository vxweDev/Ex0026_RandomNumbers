package ex0026;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        final InetAddress IP = InetAddress.getByName(args[0]);
        final int PORT = Integer.parseInt(args[1]);

        Socket socket = new Socket(IP, PORT);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        Scanner scanner = new Scanner(System.in);

        String input;
        do {
            System.out.print("Input (count;min;max): ");
            input = scanner.nextLine();

            writer.write(input + System.lineSeparator());
            writer.flush();

            String[] result = reader.readLine().split(";");
            int[] numbers = new int[result.length];

            for (int i = 0; i < result.length; i++) {
                numbers[i] = Integer.parseInt(result[i]);
                System.out.println(String.format("%-3s|%-3s", i + 1, numbers[i]));
            }
        } while (!input.equals("exit"));
    }
}
