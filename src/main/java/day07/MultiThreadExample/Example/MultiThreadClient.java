package day07.MultiThreadExample.Example;

import java.io.IOException;
import java.net.Socket;

public class MultiThreadClient {
    public static void main(String[] args) {
        String serverIp = "192.168.0.115";
        int serverPort = 9991;

        try {
            Socket socket = new Socket(serverIp, serverPort);
            Thread messageSendThread = new MessageSendThread(socket);
            messageSendThread.start();

            Thread messageReceiveThread = new MessageReceiveThread(socket);
            messageReceiveThread.start();

            while (true) {

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
