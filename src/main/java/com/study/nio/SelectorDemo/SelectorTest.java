package com.study.nio.SelectorDemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * selector 选择器 多路复用，选择器结合selectable-channel实现非阻塞效果，提高效率
 * 可以将通道注册进选择器中，其主要注意是使用一个线程来对多个通道中的已就绪进行选择，然后就可以对选择
 * 的通道进行数据处理，属于一对多的关系
 */
public class SelectorTest {
    public static void main(String[] args) throws IOException {
        //创建serverSocketChannel对象
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //设置websocket通道为非阻塞方式
        serverSocketChannel.configureBlocking(false);
        //获取websocket
        ServerSocket serverSocket = serverSocketChannel.socket();
        //进行绑定操作
        serverSocket.bind(new InetSocketAddress("localhost", 8888));

        //核心代码开始
        Selector selector = Selector.open();
        SelectionKey key = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        //核心代码结束
        System.out.println("selector=" + selector);
        System.out.println("key=" + key);
        serverSocket.close();
        serverSocketChannel.close();
    }


}
