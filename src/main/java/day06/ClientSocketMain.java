package day06;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientSocketMain {
    public static void main(String[] args) {
        try {
            Socket clientSocket = new Socket("192.168.0.115", 9999);

            OutputStream clientoutputStream = clientSocket.getOutputStream();
            clientoutputStream.write(100);

            InputStream clientInputStream = clientSocket.getInputStream();
            int num = clientInputStream.read();
            System.out.println(num);
            // 이번엔 서버가 보낸 값을 받아서 출력

            clientSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
