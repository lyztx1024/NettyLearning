package com.study.nio;

import java.nio.ByteBuffer;

/**
 * slice()方法的使用和arrayOffset为非0测试
 * slice()方法的作用：创建新的字节缓冲区，其内容是此缓冲区内容的共享子序列。新缓冲区的内容将从此缓冲区的当前位置开始。
 */
public class ByteBufferTest6 {
    public static void main(String[] args) {
      byte[] byteArrayIn1 = {1,2,3,4,5,6,7,8,9,10};
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArrayIn1);
        byteBuffer.position(5);
        ByteBuffer byteBuffer1 = byteBuffer.slice();
        System.out.println("byteBuffer.postion="+ byteBuffer.position()+" byteBuffer.capacity="+byteBuffer.capacity()+" byteBuffer.limit="+byteBuffer.limit());
        System.out.println("byteBuffer1.postion="+ byteBuffer1.position()+" byteBuffer1.capacity="+byteBuffer1.capacity()+" byteBuffer1.limit="+byteBuffer1.limit());
        byteBuffer1.put(0,(byte)111);
        byte[] byteArray1 = byteBuffer.array();
        byte[] byteArray2 = byteBuffer1.array();

        for (int i = 0; i < byteArray1.length; i++) {
            System.out.print(byteArray1[i]+" ");
        }
        System.out.println();
        for (int i = 0; i < byteArray2.length ; i++) {
            System.out.println(byteArray2[i]+" ");
        }
    }
}
