package com.study.nio.SelectorDemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * 相同的通道可以注册不同的选择器，返回的SelectionKey不是同一个对象
 */
public class SelectorKeyDemo {
    public static void main(String[] args) throws IOException {
        //相同的通道可以注册不同的选择器，返回的SelectionKey不是同一个对象
        selectionKeyTest1();
        selectionKeyTest2();
    }

    private static void selectionKeyTest1() throws IOException {
        //打开ServerSocketChannel
        ServerSocketChannel serverSocketChannel =ServerSocketChannel.open();
        //进行ip和端口绑定
        serverSocketChannel.bind(new InetSocketAddress("localhost",8888));


        //配置非阻塞状态
        serverSocketChannel.configureBlocking(false);

        //打开选择器
        Selector selector1 = Selector.open();
        Selector selector2 = Selector.open();

        //将通道注册到选择器中，返回key
        SelectionKey selectionKey1 = serverSocketChannel.register(selector1,SelectionKey.OP_ACCEPT);
        System.out.println("SelectionKey1="+selectionKey1.hashCode());
        SelectionKey selectionKey2 = serverSocketChannel.register(selector2,SelectionKey.OP_ACCEPT);
        System.out.println("SelectionKey2="+selectionKey2.hashCode());
        serverSocketChannel.close();
    }

    //不同的通道注册到相同的选择器中，返回的SelectionKey不是同一个对象
    private static void selectionKeyTest2() throws IOException {
        ServerSocketChannel serverSocketChannel1 =ServerSocketChannel.open();
        serverSocketChannel1.bind(new InetSocketAddress("localhost",8888));
        serverSocketChannel1.configureBlocking(false);

        ServerSocketChannel serverSocketChannel2 =ServerSocketChannel.open();
        serverSocketChannel2.bind(new InetSocketAddress("localhost",8888));
        serverSocketChannel2.configureBlocking(false);

        Selector selector = Selector.open();

        SelectionKey selectionKey1 = serverSocketChannel1.register(selector,SelectionKey.OP_ACCEPT);
        System.out.println("SelectionKey1="+selectionKey1.hashCode());
        SelectionKey selectionKey2 = serverSocketChannel2.register(selector,SelectionKey.OP_ACCEPT);
        System.out.println("SelectionKey2="+selectionKey2.hashCode());
        serverSocketChannel1.close();
        serverSocketChannel2.close();
    }


}
