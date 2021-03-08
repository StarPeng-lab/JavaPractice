package TCP_demo1;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerDemo {
    public static void main(String[] args) throws IOException {
        //1、创建服务器端的ServerSocket对象，创建绑定到指定端口的服务器套接字
        ServerSocket ss = new ServerSocket(1662);

        //2、获取字节输入流，读取数据
        Socket s = ss.accept(); //侦听要连接到此套接字并接受它，返回Socket类型，之后可以调用Socket的getInputStream()获取字节输入流
        InputStream is = s.getInputStream();
        byte[] bys = new byte[1024];
        int len = is.read(bys);
        String data = new String(bys,0,len);

        System.out.println("接收：" + data);

        //3、释放资源
        s.close();
        ss.close();
    }
}
