package com.study.nio.nioReqRes;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * NIO客户端
 */
public class NIOClient {
    public static void main(String[] args) throws IOException {
       //开启网络通道
        SocketChannel channel = SocketChannel.open();//
        //设置非阻塞
        channel.configureBlocking(false);
        //绑定ip和端口
        InetSocketAddress address = new InetSocketAddress("localhost",8888);
        if(!channel.connect(address)){
            while (!channel.finishConnect()){
                System.out.println("连接服务器socket进行对话，做别的事情");

            }
            //获取缓冲区并存入数据
            String msg = "hello,l'm Client";
            ByteBuffer witerBuffer = ByteBuffer.wrap(msg.getBytes());
         //发送数据信息
            channel.write(witerBuffer);
            System.in.read();
        }

    }
}
