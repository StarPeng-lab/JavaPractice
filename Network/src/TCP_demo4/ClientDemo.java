package TCP_demo4;

import java.io.*;
import java.net.Socket;

/*
    客户端：数据来自文本文件，接收服务器反馈
 */
public class ClientDemo {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("192.168.2.185",1662);

        BufferedReader br = new BufferedReader(new FileReader("Network\\test.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

        String line;
        while((line = br.readLine()) != null){
            bw.write(line);
            bw.newLine();
            bw.flush();
        }

        s.shutdownOutput(); //发出输出结束标记，表示输出结束（避免出现服务器程序一直等待而给不出反馈的问题，因为读数据的方法是阻塞式的）

        //接收服务器反馈
        BufferedReader brServer = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String data = brServer.readLine();
        System.out.println("服务器的反馈：" + data);

        br.close();
        s.close();

    }
}
