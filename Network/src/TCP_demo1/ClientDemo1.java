package TCP_demo1;

/**
 * TCP通信协议是一种可靠的网络协议，它在通信的两端各建立一个Socket对象，从而在通信的两端形成网络虚拟链路；
 * 一旦建立了网络虚拟链路，两端的程序就可以通过虚拟链路进行通信
 * Java对于基于TCP协议的网络提供了良好的封装，使用Socket对象来代表两端的通信端口，并使用Socket产生IO流来进行网络通信
 * 客户端使用Socket类，服务器端使用ServerSocket类；作为传输控制协议，通信两端是通过IO字节流来通信的
 */

import java.io.IOException;
import java.io.OutputStream;
import java.net.*;

//由于TCP是面向连接的协议，因此直接运行ClientDemo.java会报错，需要开启ServerDemo.java，建立通信
public class ClientDemo1 {
    public static void main(String[] args) throws IOException {
        //1、创建客户端的Socket对象
        Socket socket = new Socket("192.168.2.185",1662); //创建流套接字，并将其连接到指定主机上的指定端口号
        //Socket socket = new Socket(InetAddress.getByName("192.168.2.185"),1662); //创建流套接字，并将其连接到指定ip地址上的指定端口号

        //2、获取输出字节流，往输出流写数据
        OutputStream os = socket.getOutputStream();
        os.write("hello，tcp，我来了！".getBytes());

        //3、释放资源
        socket.close();
    }
}
