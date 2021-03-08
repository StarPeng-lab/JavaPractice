package TCP_demo3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/*
    服务器：接收到的数据在控制台输出
 */
public class ServerDemo3 {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(1662);

        Socket s = ss.accept();
        InputStream is = s.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is)); //由于传入数据是字符，我们可封装成字符缓冲流，如果传入的是图片，就需要用字节流
        String line;
        while((line = br.readLine()) != null){
            System.out.println(line);
        }

        ss.close();
    }
}
