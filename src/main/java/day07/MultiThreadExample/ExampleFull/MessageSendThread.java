package day07.MultiThreadExample.ExampleFull;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class MessageSendThread extends Thread {
    Socket socket;

    public MessageSendThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            OutputStream outputStream = socket.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("서버에게 보낼 내용을 입력해주세요 : ");
                String message = scanner.nextLine();

                if (message.startsWith("img:")) {
                    bufferedWriter.write(message + "\n");
                    bufferedWriter.flush();

                    FileInputStream fis = new FileInputStream("c:\\test02\\"+message.split(":")[1]);
                    BufferedOutputStream bos = new BufferedOutputStream(outputStream);

                    int data = 0;

                    while( (data = fis.read()) != -1) {
                        bos.write(data);
                    }

                    bos.flush();
                    fis.close();
                } else {
                    bufferedWriter.write(message + "\n");
                    bufferedWriter.flush();
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
