package UDP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

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
