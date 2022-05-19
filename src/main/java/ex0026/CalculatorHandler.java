package ex0026;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.Random;

public class CalculatorHandler extends AbstractHandler {
  @Override
  protected void runTask(Socket socketClient) {
    try {
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
      BufferedReader br = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));

      String command = br.readLine();
      String tokens[] = command.split(";");
      if (tokens.length == 3) {
        int n = Integer.parseInt(tokens[0]);
        int min = Integer.parseInt(tokens[1]);
        int max = Integer.parseInt(tokens[2]);
        Random r = new Random();
        int numbers[] = new int[n];
        for (int i = 0; i < n; i++) {
          numbers[i] = min + r.nextInt(max - min + 1);
        }
        Arrays.sort(numbers);
        int k, i;
        print(numbers, n);
        i = 0;
        while (i < n - 1) {
          if (numbers[i] == numbers[i + 1]) {
            for (k = i; k < n - 1; k++) {
              numbers[k] = numbers[k + 1];
            }
            n--;
          } else {
            i++;
          }
        }
        print(numbers, n);
        StringBuilder sb = new StringBuilder();
        for (i = 0; i < n - 1; i++) {
          sb.append(numbers[i]);
          sb.append(";");
        }
        sb.append(numbers[n - 1]);
        bw.write(sb.toString());
        bw.flush();
      }

      br.close();
      bw.close();
    } catch (IOException e) {
      System.err.println(e);
    }
  }

  private void print(int numbers[], int n) {
    int x;
    for (x = 0; x < n; x++) {
      System.out.print(numbers[x] + " ");
    }
    System.out.println();
  }
}
