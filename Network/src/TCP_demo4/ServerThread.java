package TCP_demo4;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.AbstractSequentialList;

public class ServerThread implements Runnable {
    private Socket s;

    public ServerThread(Socket s){
        this.s = s;
    }

    @Override
    public void run() {
        try {
            //接收数据写到文本文件
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            //解决名称冲突问题
            int count = 0;
            File file = new File("Network\\test["+count+"].txt");
            while(file.exists()){
                count++;
                file = new File("Network\\test["+count+"].txt");
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));

            String line;
            while((line = br.readLine()) != null){
                bw.write(line);
                bw.newLine();
                bw.flush();
            }

            //给出反馈
            BufferedWriter bwServer = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            bwServer.write("文件上传成功！！");
            bwServer.newLine();
            bwServer.flush();

            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
