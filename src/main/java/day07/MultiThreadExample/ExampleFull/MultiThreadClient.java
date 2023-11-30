package day07.MultiThreadExample.ExampleFull;


import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class MultiThreadClient {
    public static void main(String[] args) {
        String serverIp = "192.168.0.100";
        int serverPort = 9999;

        try {
            Socket socket = new Socket(serverIp, serverPort);
            OutputStream outputStream = socket.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

            Scanner scanner = new Scanner(System.in);

            System.out.print("사용할 ID를 입력해주세요 : ");
            String id = scanner.nextLine();

            bufferedWriter.write(id + "\n");
            bufferedWriter.flush();

            Thread messageSendThread = new MessageSendThread(socket);
            messageSendThread.start();

            Thread messageReceiveThread = new MessageReceiveThread(socket);
            messageReceiveThread.start();

            while (true) {

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
