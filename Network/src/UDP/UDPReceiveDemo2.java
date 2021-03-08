package UDP;

import java.io.IOException;
import java.net.*;

//UDP接收数据：因为接收端不知道发送端什么时候停止发送，因此采用死循环接收
public class UDPReceiveDemo2 {
    public static void main(String[] args) throws IOException {
        //1、创建接收端的Socket对象，并绑定本机的指定端口
        DatagramSocket ds = new DatagramSocket(1662);

        while(true){
            //2、创建数据包，用于接收数据
            byte[] bys = new byte[1024];
            DatagramPacket dp = new DatagramPacket(bys,bys.length);

            //3、接收数据
            ds.receive(dp);

            //4、解析数据包，并把数据打印在控制台显示
            System.out.println("biu：" + new String(dp.getData(),0,dp.getLength()));

        }

        //5、关闭接收端（通信时接收端一直保持开启状态，无需关闭）

    }
}
