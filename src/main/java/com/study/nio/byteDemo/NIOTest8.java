package com.study.nio.byteDemo;

import java.nio.ByteBuffer;

/**
 * 查看缓冲区底层实现是否是数组实现的
 */
public class NIOTest8 {
    public static void main(String[] args) {
        allocateDemo();
        allocateDirectDemo();
    }

    private static void allocateDemo() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
        byteBuffer.put((byte) 1);
        byteBuffer.put((byte) 2);
        //返回true，说明底层是数组实现的，说明数据存储在数组中
        System.out.println("缓冲区的底层基于数组实现:" + byteBuffer.hasArray());
    }

    private static void allocateDirectDemo() {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(100);
        byteBuffer.put((byte) 1);
        byteBuffer.put((byte) 2);
        //返回true，说明底层是数组实现的,而返回false，说明数据并没有直接存储在数组中，而是直接存储在内存中
        System.out.println("直接缓冲区的底层基于数组实现:" + byteBuffer.hasArray());
    }
}
