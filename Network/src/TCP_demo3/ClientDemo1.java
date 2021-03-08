package TCP_demo3;

import java.io.*;
import java.net.Socket;

/*
    客户端：数据来自键盘录入，直到输入的数据是886，发送数据结束
 */
public class ClientDemo1 {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("192.168.2.185",1662);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //封装键盘录入
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream())); //封装输出流
        String line;
        while((line = br.readLine()) != null){
            if("886".equals(line))
                break;
            bw.write(line);
            bw.newLine();
            bw.flush();
        }

        s.close();

    }
}
