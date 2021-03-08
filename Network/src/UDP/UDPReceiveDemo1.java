package UDP;

import java.io.IOException;
import java.net.*;

public class UDPReceiveDemo1 {
    public static void main(String[] args) throws IOException {
        //1、创建接收端的Socket对象（数据包套接字），并绑定到本地主机的指定端口
        DatagramSocket ds = new DatagramSocket(1662);

        //2、创建一个数据包，用于接收数据
        byte[] bytes = new byte[1024];
        DatagramPacket dp = new DatagramPacket(bytes,bytes.length);

        //3、调用DatagramSocket对象的方法接收数据
        ds.receive(dp);

        //4、解析数据包，并把数据打印在控制台
        byte[] datas = dp.getData(); //返回数据缓冲区

        System.out.println(new String(datas,0,dp.getLength())); //getLength(): 接收的实际数据长度

        //5、关闭接收端
        ds.close();
    }
}
