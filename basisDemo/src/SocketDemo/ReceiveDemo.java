package SocketDemo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ReceiveDemo {
    public static void main(String[] args) throws IOException {
        ServerSocket s = new ServerSocket(10086);
        Socket ss = s.accept();

        BufferedReader br = new BufferedReader(new InputStreamReader(ss.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\t1.txt"));
        String line;
        while((line=br.readLine())!=null){
            System.out.println(line);
            bw.write(line);
            bw.newLine();
            bw.flush();

            OutputStream o = ss.getOutputStream();
            o.write("服务器端已收到！".getBytes());
        }
        s.close();
    }

}
