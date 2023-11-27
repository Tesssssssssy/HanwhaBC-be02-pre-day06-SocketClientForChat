package day06.ChatPractice;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ChatClientSample {
    public static void main(String[] args) {
        try {
            // 서버로 접속하는 코드, 서버가 실행되어 있어야 제대로 실행 됨
            Socket clientSocket = new Socket("192.168.0.115", 9876);

            // 받는거
            // 여기서부터 받는 코드
            InputStream inputStream = clientSocket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String data = bufferedReader.readLine();
            System.out.println(data);
            // 여기까지 받는 코드

            // 보내는 거
            // 여기서부터 보내는 코드
            OutputStream outputStream = clientSocket.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

            Scanner scanner = new Scanner(System.in);
            System.out.print("서버에게 보낼 내용을 입력해주세요 : ");
            String message = scanner.nextLine();

            bufferedWriter.write(message + "\n");
            bufferedWriter.flush();
            // 여기까지 보내는 코드

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
