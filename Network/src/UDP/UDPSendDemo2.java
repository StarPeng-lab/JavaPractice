package UDP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

//UDP发送数据：通过键盘录入数据，直到输入886，发送数据结束
//可以开启多个UDPSendDemo2.java并行进程，来发送数据在UDPReceiveDemo2.java的控制台显示，模拟简单版多人聊天
//如果开启不了，则配置【Edit Configurations】中勾选【Allow parallel run】（允许并行运行），即可
public class UDPSendDemo2 {
    public static void main(String[] args) throws IOException {
        //1、创建发送端的Socket对象
        DatagramSocket ds = new DatagramSocket();

        //2、封装键盘录入数据
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line = br.readLine()) != null){
            if("886".equals(line)){
                break;
            }

            //创建数据，并把数据打包
            byte[] bys = line.getBytes();
            DatagramPacket dp = new DatagramPacket(bys,bys.length,InetAddress.getByName("192.168.2.185"),1662);

            //3、发送数据包
            ds.send(dp);
        }

        //4、关闭发送端
        ds.close();
    }
}
