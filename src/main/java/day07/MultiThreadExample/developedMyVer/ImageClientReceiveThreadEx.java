package day07.MultiThreadExample.developedMyVer;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ImageClientReceiveThreadEx extends Thread {
    Socket socket;

    public ImageClientReceiveThreadEx(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader bir = new BufferedReader(isr);

            while (true) {
                BufferedInputStream bis = new BufferedInputStream(is);
                FileOutputStream fos = new FileOutputStream("D:\\workspace\\spring_workspace\\Hanwha_javabasic2ForClientSocket\\hanwha_javabasic2ForClientSocket\\src\\main\\java\\day07\\MultiThreadExample\\developedMyVer\\new.jpg");

                int data = 0;

                while( (data = bis.read()) != -1) {
                    fos.write(data);
                }

                System.out.println("파일 다운로드가 완료되었습니다.");

                fos.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

