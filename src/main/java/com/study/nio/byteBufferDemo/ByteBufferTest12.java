package com.study.nio.byteBufferDemo;

import java.nio.ByteBuffer;

/**
 * 对缓冲区进行扩容
 */
public class ByteBufferTest12 {
    public static void main(String[] args) {
        byte[] byteBufferIn1 = {1,2,3,4,5};
        ByteBuffer byteBuffer1 = ByteBuffer.wrap(byteBufferIn1);
        ByteBuffer byteBuffer2 = extendsSize(byteBuffer1,2);

        byte[] newArray = byteBuffer2.array();
        byteBuffer1.position(0);
        for (int i = 0; i < newArray.length; i++) {
            System.out.print(newArray[i]+" ");
        }
    }

    public static ByteBuffer extendsSize(ByteBuffer buffer,int extendsSize){
        ByteBuffer newByteBuffer = ByteBuffer.allocate(buffer.capacity()+extendsSize);
        newByteBuffer.put(buffer);
        return newByteBuffer;
    }
}
