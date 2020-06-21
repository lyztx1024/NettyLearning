package com.study.nio.bioReqRes;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * BIO服务端
 */
public class BIOServer {
    public static void main(String[] args) throws IOException {
        //创建一个ServerSocket对象，带端口
        ServerSocket serverSocket = new ServerSocket(8888);
        while(true){
            //监听客户端，阻塞
            Socket socket = serverSocket.accept();
            //从serverSocket中拿到输入流，进行消息的接收，阻塞
            InputStream is = socket.getInputStream();
            byte[] b =new byte[20];
            is.read(b);
            String clientIp = socket.getInetAddress().getHostAddress();
            System.out.println(clientIp + "说:" + new String(b).trim());
            //从serverScoket中拿到输出流，进行消息的响应
            OutputStream os = socket.getOutputStream();
            os.write("你好，客户端".getBytes());
            //关闭socket
            socket.close();
        }

    }
}
