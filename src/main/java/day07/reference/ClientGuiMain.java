package day07.reference;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ClientGuiMain {
    public static void main(String[] args) {
        try {
            InetAddress ia = InetAddress.getLocalHost();
            String ip_str = ia.toString();
            String ip = ip_str.substring(ip_str.indexOf("/") + 1);
            new ClientGUI("192.168.0.115", 9992);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}