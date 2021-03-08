package TCP_demo2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
    服务器：接收数据，输出反馈
 */
public class ServerDemo {
    public static void main(String[] args) throws IOException {
        //1、创建服务器端的Socket对象（ServerSocket），并绑定本机端口
        ServerSocket ss = new ServerSocket(1662);

        //2、监听客户端连接，返回一个Socket对象（侦听要连接到此套接字并接受它）
        Socket s = ss.accept();

        //获取输入流
        InputStream is = s.getInputStream();
        byte[] bys = new byte[1024];
        int len = is.read(bys);
        System.out.println("服务器接收到：" + new String(bys,0,len));

        //3、给出反馈
        OutputStream os = s.getOutputStream();
        os.write("数据已经收到".getBytes());

        //4、释放资源（is，os在s释放时就释放了）
        s.close();
        ss.close();
    }
}
