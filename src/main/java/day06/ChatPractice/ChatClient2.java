package day06.ChatPractice;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 *  Scanner로 입력 받고
 *  client -> server    파일 이름을 입력하면
 *  server -> client    파일을 보내준다.
 *  그리고 client가 이를 저장한다

 *  파일은 byte로 주고 받아야 한다.
 */

public class ChatClient2 {
    public static void main(String[] args) throws Exception{

        Scanner scanner = new Scanner(System.in);
        Socket clientSocket = new Socket("192.168.0.115", 9998);

        DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());
        DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());

        while(true) {
            System.out.print("다운 받으실 파일명을 입력하세요 : ");
            String downFile = scanner.nextLine();

            dataOutputStream.writeUTF(downFile);

            byte[] fileContents = new byte[(int)dataInputStream.readLong()];
            dataInputStream.readFully(fileContents);

            File destination = new File("D:\\workspace\\spring_workspace\\Hanwha_javabasic2ForClientSocket\\hanwha_javabasic2ForClientSocket\\src\\main\\resources\\img/"+downFile);
            FileOutputStream fileOutputStream = new FileOutputStream(destination);
            DataOutputStream dataOutputStream2 = new DataOutputStream(fileOutputStream);
            dataOutputStream2.write(fileContents);
            dataOutputStream2.flush();
            dataOutputStream2.close();

            System.out.println(destination.getName()+" 수신 완료!");

            System.out.print("종료하시겠습니까? y | n : ");
            String menu = scanner.nextLine();
            if (menu.equals("y")) {
                System.exit(0);
            } else {
                continue;
            }
        }
    }

}
