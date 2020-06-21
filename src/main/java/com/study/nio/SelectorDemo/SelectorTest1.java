package com.study.nio.SelectorDemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * 判断注册的状态:判断当前是否向任何选择器进行了注册。可以看到新创建的通道总是未注册的
 */
public class SelectorTest1 {
    public static void main(String[] args) throws IOException {
        //打开serverSocket通道，同时设置为非阻塞，拿到serverSocket，进行ip和端口绑定
        //将选择器打开，将选择器key进行注册，关闭socket和socket通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false); //需要部分，通常需要将其设置为非阻塞
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress("localhost", 8888));

        System.out.println("A isRegistered=" + serverSocketChannel.isRegistered());

        Selector selector = Selector.open();
        SelectionKey key = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("B isRegistered=" + serverSocketChannel.isRegistered());

        serverSocket.close();
        serverSocketChannel.close();
    }
}
