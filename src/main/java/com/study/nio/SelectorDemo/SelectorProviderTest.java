package com.study.nio.SelectorDemo;

import java.io.IOException;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.spi.SelectorProvider;

/**
 * 获取selectorProvider
 */
public class SelectorProviderTest {
    public static void main(String[] args) throws IOException {
        SelectorProvider selectorProvider = SelectorProvider.provider();
        System.out.println(selectorProvider);

        ServerSocketChannel serverSocketChannel = null;
        serverSocketChannel =serverSocketChannel.open();
        SelectorProvider provider = SelectorProvider.provider();
        System.out.println(provider);
        serverSocketChannel.close();
    }
}
