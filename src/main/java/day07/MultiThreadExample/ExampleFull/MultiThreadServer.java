package day07.MultiThreadExample.ExampleFull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultiThreadServer {
    static Map<String, Socket> socketMap = new HashMap<String, Socket>();


    public static void main(String[] args) {
        int port = 9999;

        try {
            ServerSocket serverSocket = new ServerSocket(port);

            while (true) {
                Socket socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String id = bufferedReader.readLine();
                System.out.println(id+" 님이 입장하셨습니다");

                socketMap.put(id, socket);

                Thread thread = new MessageProcessThread(id, socket);
                thread.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
