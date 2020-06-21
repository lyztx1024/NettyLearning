package com.study.nio.SelectorDemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * 进行socket地址获取、设置阻塞模式
 */
public class SocketAddressTest {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("localhost",8888));
        InetSocketAddress address = (InetSocketAddress)serverSocketChannel.getLocalAddress();

        //获取ip和端口
        System.out.println(address.getHostString());
        System.out.println(address.getPort());
        //查看阻塞模式
        System.out.println(serverSocketChannel.isBlocking());
        serverSocketChannel.configureBlocking(false);
        System.out.println(serverSocketChannel.isBlocking());
        //获取选择器
        Selector selector = Selector.open();
        SelectionKey selectionKey = serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);
        System.out.println("A = "+selectionKey+" "+selectionKey.hashCode());
        SelectionKey selectionKey1 = serverSocketChannel.keyFor(selector);
        System.out.println("B = "+selectionKey1.hashCode());
        serverSocketChannel.close();
    }
}
