package TCP_demo4;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
    服务器：接收到的数据写入文本文件，给出反馈，代码用线程进行封装，为每一个客户端开启一个线程，即多个ClientDemo.java并发使用时，有几个就开几个线程
 */
public class ServerDemo2 {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(1662);

        while(true){ //为了表示服务器一直在监听，一直在接收，因此采用死循环
            Socket s = ss.accept(); //监听客户端连接，返回对应的Socket对象
            new Thread(new ServerThread(s)).start(); //为每一个客户端开启一个线程
        }
    }
}
