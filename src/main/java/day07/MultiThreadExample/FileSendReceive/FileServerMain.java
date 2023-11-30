package day07.MultiThreadExample.FileSendReceive;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServerMain {
    public static void main(String[] args) {
        try {
            // 포트를 LISTEN 상태로 변경
            ServerSocket serverSocket = new ServerSocket(9999);

            // serverSocket.accept()는 클라이언트 쪽에서 new Socket()이 실행되어야 끝남
            Socket cs = serverSocket.accept();

            // 여기서부터 받는 코드
            InputStream is = cs.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader bir = new BufferedReader(isr);
            String filename = bir.readLine();
            System.out.println(filename);
            // 여기까지 받는 코드

            OutputStream os = cs.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(os);

            FileInputStream fis = new FileInputStream("c:\\test\\"+filename);
            int data = 0;
            while( (data = fis.read()) != -1) {
                bos.write(data);
            }

            bos.close();
            bir.close();
            cs.close();
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
