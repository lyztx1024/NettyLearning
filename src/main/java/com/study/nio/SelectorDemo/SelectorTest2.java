package com.study.nio.SelectorDemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketOption;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 获取支持的socketOption列表
 * Set<SocketOption<?> supportedOption()方法：返回通道支持的Socket Option
 */
public class SelectorTest2 {
    public static void main(String[] args) throws IOException {
        Thread t = new Thread() {
            public void run() {
                try {
                    Thread.sleep(2000);
                    Socket socket = new Socket("localhost", 8088);
                    socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("localhost", 8088));
        SocketChannel socketChannel = serverSocketChannel.accept();

        Set<SocketOption<?>> set1 = serverSocketChannel.supportedOptions();
        Set<SocketOption<?>> set2 = socketChannel.supportedOptions();

        Iterator iterator1 = set1.iterator();
        Iterator iterator2 = set2.iterator();

        System.out.println("ServerSocketChannel supportedOptions:");
        while (iterator1.hasNext()) {
            SocketOption each = (SocketOption) iterator1.next();
            System.out.println(each.name() + " " + each.getClass().getName());
        }
        System.out.println();
        System.out.println();
        System.out.println("SocketChannel supportedOptions:");
        while (iterator2.hasNext()) {
            SocketOption each1 = (SocketOption) iterator2.next();
            System.out.println(each1.name() + " " + each1.getClass().getName());
        }
        socketChannel.close();
        serverSocketChannel.close();
    }
}
