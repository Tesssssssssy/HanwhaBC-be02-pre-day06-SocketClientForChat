package day07.MultiThreadExample.Example;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
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
            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bow = new BufferedWriter(osw);
            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.print("서버에게 보낼 내용을 입력해주세요 : ");
                String message = sc.nextLine();

                bow.write(message + "\n");
                bow.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
