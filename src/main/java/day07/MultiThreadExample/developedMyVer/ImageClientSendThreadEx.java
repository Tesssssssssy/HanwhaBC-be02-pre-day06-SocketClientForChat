package day07.MultiThreadExample.developedMyVer;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ImageClientSendThreadEx extends Thread {
    Socket socket;

    public ImageClientSendThreadEx(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bow = new BufferedWriter(osw);
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("전송할 파일 이름을 입력하세요 (종료 시 exit 입력) : ");
                String filename = scanner.nextLine();

                FileInputStream fis = new FileInputStream("D:\\workspace\\spring_workspace\\Hanwha_javabasic2ForClientSocket\\hanwha_javabasic2ForClientSocket\\src\\main\\java\\day07\\MultiThreadExample\\developedMyVer\\" + filename);
                BufferedOutputStream bos = new BufferedOutputStream(os);

                int data = 0;

                while ((data = fis.read()) != -1) {
                    bos.write(data);
                }

                if (filename == "exit") {
                    System.exit(0);
                }
                bos.flush();
                fis.close();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
