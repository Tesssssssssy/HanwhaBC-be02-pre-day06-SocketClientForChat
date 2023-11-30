package day07.MultiThreadExample.ExampleFull;

import java.io.*;
import java.net.Socket;

public class MessageProcessThread extends Thread {
    Socket socket;
    String id;

    public MessageProcessThread(String id, Socket socket) {
        this.id = id;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            while (true) {
                String message = bufferedReader.readLine();

                if(message.startsWith("to:")){
                    Socket client = MultiThreadServer.socketMap.get(message.split(" ")[0].substring(3));

                    OutputStream outputStream = client.getOutputStream();
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
                    BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

                    bufferedWriter.write("DM : " + message.split(" ")[1]+"\n");
                    bufferedWriter.flush();

                } else if(message.startsWith("img:")) {
                    for(String key : MultiThreadServer.socketMap.keySet()) {
                        if(id.equals(key)){
                            continue;
                        }

                        Socket client = MultiThreadServer.socketMap.get(key);
                        OutputStream outputStream = client.getOutputStream();
                        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
                        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

                        bufferedWriter.write(message+"\n");
                        bufferedWriter.flush();

                        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);

                        int data = 0;

                        while( (data = bufferedInputStream.read()) != -1) {
                            bufferedOutputStream.write(data);
                        }

                        bufferedOutputStream.flush();

                    }
                } else {
                    for(String key : MultiThreadServer.socketMap.keySet()) {
                        if(id.equals(key)){
                            continue;
                        }

                        Socket client = MultiThreadServer.socketMap.get(key);
                        OutputStream outputStream = client.getOutputStream();
                        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
                        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

                        bufferedWriter.write(message+"\n");
                        bufferedWriter.flush();
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
