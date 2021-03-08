package UDP;

import java.io.IOException;
import java.net.*;
public class UDPSendDemo1 {
    /**
     * 通信两端建立Socket对象，但只用于接收、发送数据，不区分客户端、服务器端
     * DatagramSocket类用于建立基于UDP协议的Socket
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        /*
        InetAddress addr = InetAddress.getByName("192.168.2.185"); //返回主机的ip地址，主机名可以为机器名或IP地址
        System.out.println(addr.getHostName()); //DESKTOP-376GFHM.lan，返回此ip地址的主机名
        System.out.println(addr.getHostAddress()); //192.168.2.185，返回文本显示中的字符串IP地址
        */

        //1、创建发送端的Socket对象（数据包套接字），绑定到指定ip的指定端口
        DatagramSocket ds = new DatagramSocket();

        //2、创建数据，并把数据打包（通过DatagramPacket类）
        byte[] bys = "Hello，我来了！".getBytes();
        DatagramPacket dp = new DatagramPacket(bys,bys.length,InetAddress.getByName("192.168.2.185"),1662);

        //3、调用DatagramSocket对象的方法，从套接字发送数据报包
        ds.send(dp);

        //4、关闭发送端（关闭数据报套接字）
        ds.close();

    }
}
