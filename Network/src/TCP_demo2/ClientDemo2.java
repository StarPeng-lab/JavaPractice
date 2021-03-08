package TCP_demo2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/*
    客户端：发送数据，并接收服务器反馈
 */
public class ClientDemo2 {
    public static void main(String[] args) throws IOException {
        //1、创建客户端的Socket对象
        Socket s = new Socket("192.168.2.185",1662);

        //2、获取字节输出流，写数据
        OutputStream os = s.getOutputStream();
        os.write("hello，tcp，我来了！！".getBytes());

        //3、接收服务器反馈
        InputStream is = s.getInputStream();
        byte[] bys = new byte[1024];
        int len = is.read(bys);
        System.out.println("客户端接收到反馈：" + new String(bys,0,len));

        //4、释放资源
        s.close();
    }
}
