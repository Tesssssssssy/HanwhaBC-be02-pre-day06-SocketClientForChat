package day07.MultiThreadExample.developedMyVer;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class MultiThreadClientMainEx {
    public static void main(String[] args) {
        String serverIp = "192.168.0.115";
        int serverPort = 9991;

        Scanner scanner = new Scanner(System.in);

        try {
            Socket clientSocket = new Socket(serverIp, serverPort);

            System.out.print("1, 2 중 하나를 입력하세요. (이미지 전송 및 수신: 1 | 일반 채팅: 2) : ");
            int num = scanner.nextInt();

            if (num == 1) {
                Thread imageReceiveThread = new ImageClientReceiveThreadEx(clientSocket);
                imageReceiveThread.start();

                Thread imageSendThread = new ImageClientSendThreadEx(clientSocket);
                imageSendThread.start();
            } else if (num == 2) {
                Thread messageSendThread = new MessageClientSendThreadEx(clientSocket);
                messageSendThread.start();

                Thread messageReceiveThread = new MessageClientReceiveThreadEx(clientSocket);
                messageReceiveThread.start();
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
