package day07.MultiThreadExample.developedMyVer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class MessageClientSendThreadEx extends Thread {
    Socket socket;

    public MessageClientSendThreadEx(Socket socket) {
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
                System.out.print("서버에게 보낼 내용을 입력해주세요 (종료 시 exit 입력) : ");
                String message = scanner.nextLine();

                bufferedWriter.write(message + "\n");
                bufferedWriter.flush();

                if (message.equals("exit")) {
                    System.exit(0);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
