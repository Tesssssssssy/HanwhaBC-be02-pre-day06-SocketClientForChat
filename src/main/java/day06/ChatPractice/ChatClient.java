package day06.ChatPractice;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 *  - 한 명은 서버
 *     - Scanner로 console에서 입력한 내용을
 *     - 서버 → client
 *     - client → 서버
 *     - 일단 한 번씩 주고 받는 프로그램 만들기
 * - 한 명은 클라이언트
 */

public class ChatClient {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = null;
        PrintWriter printWriter = null;

        Socket clientSocket = null;
        Scanner scanner = new Scanner(System.in);

        try {
            clientSocket = new Socket("192.168.0.115", 9999);

            bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            printWriter = new PrintWriter(clientSocket.getOutputStream());

            System.out.print("전송하기>>> ");
            String outputMessage = scanner.nextLine();
            printWriter.println(outputMessage);
            printWriter.flush();


            String inputMessage = bufferedReader.readLine();
            System.out.println("Server: " + inputMessage);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            scanner.close();
            if (clientSocket != null) clientSocket.close();
        }
    }
}
