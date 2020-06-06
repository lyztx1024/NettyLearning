package com.study.nio;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用List.toArray(T[])
 */
public class NIOTest12 {
    public static void main(String[] args) {
        ByteBuffer buffer1 = ByteBuffer.wrap(new byte[]{'1','2','3','4'});
        ByteBuffer buffer2 = ByteBuffer.wrap(new byte[]{'c','d','t','v'});
        ByteBuffer buffer3 = ByteBuffer.wrap(new byte[]{'x','m','a','n'});
        List<ByteBuffer> list = new ArrayList<>();
        list.add(buffer1);
        list.add(buffer2);
        list.add(buffer3);

        ByteBuffer[] byteBufferArray = new ByteBuffer[list.size()];
        list.toArray(byteBufferArray);

        System.out.println(byteBufferArray.length);

        for (int i = 0; i<byteBufferArray.length;i++){
            ByteBuffer eachByteBuffer = byteBufferArray[i];
            while (eachByteBuffer.hasRemaining()){
                System.out.print((char)eachByteBuffer.get());
            }
            System.out.println();
        }
    }
}
