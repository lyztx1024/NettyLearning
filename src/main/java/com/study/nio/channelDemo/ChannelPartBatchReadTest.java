package com.study.nio.channelDemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 进行批量读操作 long read(ByteBuffer[] dsts,int offset,int length)
 */
public class ChannelPartBatchReadTest {
    public static void main(String[] args) throws Exception{
        fileChannelPartBatchReadTest1();
        System.out.println("===============");
        fileChannelPartBatchReadTest2();
    }

    private static void fileChannelPartBatchReadTest1() throws IOException {
        FileInputStream fis = new FileInputStream(new File("e.txt"));
        FileChannel fileChannel = fis.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(2);
        ByteBuffer byteBuffer1 = ByteBuffer.allocate(2);

        ByteBuffer[] byteBuffers = new ByteBuffer[]{byteBuffer, byteBuffer1};

        long readLength = fileChannel.read(byteBuffers,0,2);
        System.out.println("readLength=" + readLength);
        byteBuffer.clear();
        byteBuffer1.clear();

        readLength = fileChannel.read(byteBuffers,0,2);
        System.out.println("readLength=" + readLength);
        byteBuffer.clear();
        byteBuffer1.clear();

        readLength = fileChannel.read(byteBuffers,0,2);
        System.out.println("readLength=" + readLength);
        byteBuffer.clear();
        byteBuffer1.clear();

        fileChannel.close();
        fis.close();
    }

    private static void fileChannelPartBatchReadTest2() throws IOException {
        FileInputStream fis = new FileInputStream(new File("e.txt"));
        FileChannel fileChannel = fis.getChannel();
        fileChannel.position(2);

        ByteBuffer byteBuffer = ByteBuffer.allocate(2);
        ByteBuffer byteBuffer1 = ByteBuffer.allocate(2);

        ByteBuffer[] byteBuffers = new ByteBuffer[]{byteBuffer, byteBuffer1};

        fileChannel.read(byteBuffers,0,2);

        for (int i = 0; i < byteBuffers.length; i++) {
            byte[] getByte = byteBuffers[i].array();
            for (int k = 0; k < getByte.length; k++) {
                System.out.print((char) getByte[k]);
            }
            System.out.println();
        }
        fileChannel.close();
        fis.close();
    }
}
