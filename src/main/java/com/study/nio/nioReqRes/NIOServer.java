package com.study.nio.nioReqRes;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * NIO服务端
 */
public class NIOServer {
    public static void main(String[] args) throws IOException {
        //开启ServerScoketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //开启selector
        Selector selector = Selector.open();
        //绑定端口号
        serverSocketChannel.bind(new InetSocketAddress(8888));
        //设置非阻塞模式
        serverSocketChannel.configureBlocking(false);
        //将serverSocketChannel对象注册给Selector对象
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        //进行操作
        while(true){
            //如果在限定时间没有客户端的请求，则进行别的操作
            if(selector.select(2000)==0){
                System.out.println("server:没有客户端信息需要处理，做别的事情");
                continue;
            }
            //拿到所以的selectionkey，进行迭代，获取SelectorKey，判断通道里的时间
            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
            while (keyIterator.hasNext()){
                SelectionKey key = keyIterator.next();
                //可接收
                if(key.isAcceptable()){
                    System.out.println("OP_ACCEPT");
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }
                //可读
                if (key.isReadable()){
                    SocketChannel channel = (SocketChannel) key.channel();
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    channel.read(buffer);
                    System.out.println("客户端发来请求："+new String(buffer.array()));
                }
                //移除所有的key
                keyIterator.remove();
            }
        }
    }
}
