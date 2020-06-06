package com.study.nio;

import java.nio.ByteBuffer;

/**
 * 进行测试,包装wrap数据的处理
 */
public class NIOTest13 {
    public static void main(String[] args) {
        byte[] byteArray = new byte[]{1, 2, 3, 4, 5, 56};
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);
        ByteBuffer byteBuffer1 = ByteBuffer.wrap(byteArray, 2, 4);
        System.out.println("byteBuffer capacity=" + byteBuffer.capacity() + " " + "postion=" + byteBuffer.position());
        System.out.println();
        System.out.println("byteBuffer1 capacity=" + byteBuffer1.capacity() + " " + "postion=" + byteBuffer1.position());
    }
}
