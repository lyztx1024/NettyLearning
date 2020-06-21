package com.study.nio.bioReqRes;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * BIO客户端
 */
public class BIOClient {
    public static void main(String[] args) throws IOException {
        while (true){
            //创建客户端socket
            Socket socket = new Socket("localhost",8888);
            //从客户端socket中拿到输出流，进行消息发送
            OutputStream os = socket.getOutputStream();
            System.out.println("输入信息：");
            //你好，服务端
            Scanner sc = new Scanner(System.in);
            String msg = sc.nextLine();
            os.write(msg.getBytes());
            //从客户端socket中拿到输入流，进行消息回复
            InputStream is = socket.getInputStream();
            byte[] b= new byte[20];
            is.read(b);
            System.out.println("服务端说："+new String(b).trim());
        }
    }
}
