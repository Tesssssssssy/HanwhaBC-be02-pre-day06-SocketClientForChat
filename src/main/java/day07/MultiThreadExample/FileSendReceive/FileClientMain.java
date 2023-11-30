package day07.MultiThreadExample.FileSendReceive;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class FileClientMain {
    public static void main(String[] args) {
        try {
            // 서버로 접속하는 코드, 서버가 실행되어 있어야 제대로 실행 됨
            Socket clientSocket = new Socket("192.168.0.100", 9999);

            // 여기서부터 보내는 코드
            OutputStream outputStream = clientSocket.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

            Scanner scanner = new Scanner(System.in);
            System.out.print("서버에게 요청할 파일이름을 입력해주세요 : ");
            String filename = scanner.nextLine();

            bufferedWriter.write(filename + "\n");
            bufferedWriter.flush();
            // 여기까지 보내는 코드

            // 여기서부터 받는 코드
            InputStream is = clientSocket.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);

            FileOutputStream fos = new FileOutputStream("c:\\test2\\" + filename);
            int data = 0;
            while( (data = bis.read()) != -1) {
                fos.write(data);
            }

            fos.close();
            bis.close();
            bufferedWriter.close();
            clientSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
