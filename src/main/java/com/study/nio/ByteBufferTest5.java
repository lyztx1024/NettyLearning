package com.study.nio;

import java.nio.ByteBuffer;

/**
 * put方法操作：put(int index,byte b) 绝对put方法，将给定字节写入此缓冲区的给定索引位置
 * get方法操作：get(int index) 绝对get方法，读取指定位置索引处的字节
 */
public class ByteBufferTest5 {
    public static void main(String[] args) {
        putMethodIndex();
        putMethod();

    }

    private static void putMethodIndex() {
        byte[] byteArrayIn1 = {1, 2, 3, 4, 5, 6, 7, 8};
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        byteBuffer.put(byteArrayIn1);
        byteBuffer.put(2, (byte) 127);
        System.out.println(byteBuffer.get(2));
        byteBuffer.position(0);
        byte[] byteArrayOut = new byte[byteBuffer.capacity()];
        byteBuffer.get(byteArrayOut, 0, byteArrayOut.length);
        for (int i = 0; i < byteArrayOut.length; i++) {
            System.out.print(byteArrayOut[i] + " ");
        }
    }

    private static void putMethod() {
        byte[] byteArrayIn1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ByteBuffer byteBuffer1 = ByteBuffer.wrap(byteArrayIn1);

        byte[] byteArrayIn2 = {55, 66, 77};
        ByteBuffer byteBuffer2 = ByteBuffer.wrap(byteArrayIn2);
        byteBuffer1.position(4);
        byteBuffer2.position(1);

        byteBuffer1.put(byteBuffer2);
        System.out.println("byteBuffer1被改变：" + byteBuffer1.position());
        System.out.println("byteBuffer2被改变：" + byteBuffer2.position());
        byte[] byteArrayOut = byteBuffer1.array();
        for (int i = 0; i < byteArrayOut.length; i++) {
            System.out.print(byteArrayOut[i] + " ");
        }
    }
}
