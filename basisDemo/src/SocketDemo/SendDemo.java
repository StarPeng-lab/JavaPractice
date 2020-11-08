package SocketDemo;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class SendDemo {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket(InetAddress.getByName("192.168.18.1"),10086);
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\t.txt")));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        String line;
        while((line=br.readLine())!=null){
            if("886".equals(line)){
                break;
            }
            bw.write(line);
            bw.newLine();
            bw.flush();

            InputStream i = s.getInputStream();
            byte[] b = new byte[1024];
            int len = i.read(b);
            System.out.println("客户端："+new String(b,0,len));
        }
        s.close();
    }
}
