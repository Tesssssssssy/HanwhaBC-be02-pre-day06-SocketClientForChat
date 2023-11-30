package day07.MultiThreadExample.developedMyVer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

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
                System.out.print("Client: " + message);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
