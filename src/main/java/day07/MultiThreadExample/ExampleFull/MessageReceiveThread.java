package day07.MultiThreadExample.ExampleFull;

import java.io.*;
import java.net.Socket;

public class MessageReceiveThread extends Thread {
    Socket socket;

    public MessageReceiveThread(Socket socket) {
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

                if (message.startsWith("img:")) {
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                    FileOutputStream fileOutputStream = new FileOutputStream("c:\\test02\\" + message.split(":")[1]);

                    int data = 0;

                    while( (data = bufferedInputStream.read()) != -1) {
                        fileOutputStream.write(data);
                    }

                    fileOutputStream.close();
                } else {
                    System.out.println(message);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
