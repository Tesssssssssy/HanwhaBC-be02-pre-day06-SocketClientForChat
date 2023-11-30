package day07.MultiThreadExample.MyVer;

import day07.MultiThreadExample.Example.MessageReceiveThread;
import day07.MultiThreadExample.Example.MessageSendThread;

import java.io.IOException;
import java.net.Socket;

public class MultiThreadClientMainEx {
    public static void main(String[] args) {
        String serverIp = "192.168.0.115";
        int serverPort = 9991;

        try {
            Socket clientSocket = new Socket(serverIp, serverPort);

            Thread messageSendThread = new MessageClientSendThreadEx(clientSocket);
            messageSendThread.start();

            Thread messageReceiveThread = new MessageClientReceiveThreadEx(clientSocket);
            messageReceiveThread.start();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
