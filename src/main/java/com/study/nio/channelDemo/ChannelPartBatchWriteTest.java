package com.study.nio.channelDemo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 进行部分批量写操作 long write(ByteBuffer[] srcs,int offset,int length)
 * 以指定缓冲数组的offset下标开始，向后使用length个字节缓冲区，
 * 再将每个缓冲区的remaining剩余字节子序列写入此通道的当前位置
 */
public class ChannelPartBatchWriteTest {
    public static void main(String[] args) throws Exception {
        fileChannelPartBatchWriteTest1();
        fileChannelPartBatchWriteTest2();
    }

    private static void fileChannelPartBatchWriteTest1() throws IOException {
        FileOutputStream fos = new FileOutputStream(new File("test.txt"));
        FileChannel fileChannel = fos.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.wrap("abcde".getBytes());
        ByteBuffer byteBuffer1 = ByteBuffer.wrap("12345".getBytes());

        ByteBuffer[] byteBuffers = new ByteBuffer[]{byteBuffer,byteBuffer1};

        fileChannel.write(ByteBuffer.wrap("QSDXXX".getBytes()));
        fileChannel.position(2);

        fileChannel.write(byteBuffers,0,2);

        fileChannel.close();
        fos.close();
    }

    private static void fileChannelPartBatchWriteTest2() throws IOException {
        FileOutputStream fos = new FileOutputStream(new File("test.txt"));
        FileChannel fileChannel = fos.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.wrap("abcde".getBytes());
        ByteBuffer byteBuffer1 = ByteBuffer.wrap("12345".getBytes());

        byteBuffer1.position(1);
        byteBuffer1.limit(3);
        ByteBuffer byteBuffer3 = ByteBuffer.wrap("dwsrdf".getBytes());
        byteBuffer3.position(2);
        byteBuffer3.limit(4);
        ByteBuffer[] byteBuffers = new ByteBuffer[]{byteBuffer,byteBuffer1,byteBuffer3};

        fileChannel.write(byteBuffers,1,2);
        fileChannel.position(2);

        fileChannel.write(byteBuffers,0,2);

        fileChannel.close();
        fos.close();
    }

}
