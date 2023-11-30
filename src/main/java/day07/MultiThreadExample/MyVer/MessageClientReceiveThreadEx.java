package day07.MultiThreadExample.MyVer;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class MessageClientReceiveThreadEx extends Thread {
    Socket socket;

    public MessageClientReceiveThreadEx(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            while (true) {
                String message = bufferedReader.readLine();
                System.out.println("Client: " + message);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
