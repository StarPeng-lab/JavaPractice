package TCP_demo3;

import java.io.*;
import java.net.Socket;

/*
    客户端：数据来自文本文件
 */
public class ClientDemo2 {
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

        br.close();
        s.close();
    }
}
