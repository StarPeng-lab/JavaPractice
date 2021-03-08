package UDP;

import java.io.IOException;
import java.net.*;

public class UDPReceiveDemo2 {
    public static void main(String[] args) throws IOException {
        //1、创建接收端的Socket对象，并绑定本机的指定端口
        DatagramSocket ds = new DatagramSocket(1662);

        while(true){
            //2、创建数据包
            byte[] bys = new byte[1024];
            DatagramPacket dp = new DatagramPacket(bys,bys.length);

            //3、接收数据
            ds.receive(dp);

            //4、解析数据并打印在控制台
            System.out.println();

        }

        //5、关闭接收端（通信时接收端一直保持开启状态，无需关闭）

    }
}
