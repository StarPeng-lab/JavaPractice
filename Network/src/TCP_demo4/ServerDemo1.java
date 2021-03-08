package TCP_demo4;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/*
    服务器：接收到的数据写入文本文件，给出反馈
          因为读数据的方法是阻塞式的，会出现服务器程序一直等待而给不出反馈的问题，通过给出 输出结束标记 解决此问题
 */
public class ServerDemo1 {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(1662);

        Socket s = ss.accept();

        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new FileWriter("Network\\test.txt",true));

        String line;
        while((line = br.readLine()) != null){
            bw.write(line);
            bw.newLine();
            bw.flush();
        }

        //服务器接收完数据，开始给出反馈（如果没有输出结束标记，则会一直停留在上面的while循环中，等待输入流的数据继续读入）
        BufferedWriter bwServer = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        bwServer.write("文件上传成功！");
        bwServer.newLine();
        bwServer.flush();;

        bw.close();
        ss.close();
    }
}
