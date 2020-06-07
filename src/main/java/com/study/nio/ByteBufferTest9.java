package com.study.nio;

import java.nio.ByteBuffer;

/**
 * compact()方法的作用：压缩此缓冲区，将缓冲区的当前位置和限制之间的字节复制到缓冲区的开始处
 */
public class ByteBufferTest9 {
    public static void main(String[] args) {
        compactTest();
    }

    private static void compactTest(){
        ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[]{1,2,3,4,5,6});
        System.out.println("A capacity="+ byteBuffer.capacity()+" postion="+byteBuffer.limit());
        System.out.println(" 1 getValue="+ byteBuffer.get());

        System.out.println("B capacity="+byteBuffer.capacity()+" postion="+byteBuffer.limit());
        System.out.println(" 2 getValue="+ byteBuffer.get());

        System.out.println("C capacity="+byteBuffer.capacity()+" postion="+byteBuffer.limit());
        byteBuffer.compact();
        System.out.println("byteBuffer.compact"+byteBuffer.compact());
        System.out.println("D capacity="+byteBuffer.capacity()+" postion="+byteBuffer.limit());
        byte[] getByteArray = byteBuffer.array();
        for (int i = 0; i < getByteArray.length; i++) {
            System.out.print(getByteArray[i]+" ");
        }
    }
}
