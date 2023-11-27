package day06;

import java.io.*;
import java.net.Socket;

public class WebBrowser {
    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket("192.168.0.115", 9999);


        OutputStream os = clientSocket.getOutputStream();
        PrintStream ps = new PrintStream(os);
        // 글자 단위로 처리할 수 있게 해줌.

        ps.println("GET / HTTP/1.1");
        // console 창에 출력하는 것이 아니라 outputstream을 통해 서버에 출력하겠다는 의미
        ps.println("Host: 192.169.0.115");
        ps.println();
        // 서버 측에 무언가를 보낸 것.


        InputStream is = clientSocket.getInputStream();
        // byte 단위로 읽기 때문에 볼 수 없다.

        // 문자 단위로 읽을 수 있게 (byte 단위 x)
        InputStreamReader isR = new InputStreamReader(is);

        // 한층 더 보완하기 위해 (성능 높이기 위해)
        BufferedReader br = new BufferedReader(isR);

        String res = null;
        while ( (res = br.readLine()) != null) {
            System.out.println(res);
        }

        clientSocket.close();
        br.close();
        ps.close();
        // 보조 stream을 닫으면 관련되어 있는 stream들도 종료됨.


    }
}
