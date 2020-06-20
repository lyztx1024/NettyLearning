package com.study.nio.channelDemo;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 进行批量写操作 long write(ByteBuffer[] src)
 * 将每个缓冲区的remaining字节序列写入此通道的当前位置
 */
@Slf4j
public class ChannelBatchWriteTest {
    public static void main(String[] args) throws Exception {
        //进行批量写操作
        fileChannelBatchWriteTest();
        fileChannelBatchWriteTest2();
        fileChannelBatchWriteTest3();
    }

    private static void fileChannelBatchWriteTest() throws IOException {
        FileOutputStream fos = new FileOutputStream(new File("a.txt"));
        FileChannel fileChannel = fos.getChannel();
        fileChannel.write(ByteBuffer.wrap("123456".getBytes()));
        fileChannel.position(3);

        ByteBuffer byteBuffer1 = ByteBuffer.wrap("000001".getBytes());
        ByteBuffer byteBuffer2 = ByteBuffer.wrap("000002".getBytes());
        //将多个单的bytebuffer放入byteBuffers中，再写入
        ByteBuffer[] byteBuffers = new ByteBuffer[]{byteBuffer1,byteBuffer2};
        fileChannel.write(byteBuffers);

        fileChannel.close();
        fos.close();
    }

    private static void fileChannelBatchWriteTest2() throws IOException {
        FileOutputStream fos = new FileOutputStream(new File("b.txt"));
        FileChannel fileChannel = fos.getChannel();
        fileChannel.write(ByteBuffer.wrap("123456".getBytes()));
        fileChannel.position(3);

        ByteBuffer byteBuffer1 = ByteBuffer.wrap("abcde1".getBytes());
        ByteBuffer byteBuffer2 = ByteBuffer.wrap("uxdax2".getBytes());
        //将多个单的bytebuffer放入byteBuffers中，再写入
        ByteBuffer[] byteBuffers = new ByteBuffer[]{byteBuffer1,byteBuffer2};

        byteBuffer1.position(1);
        byteBuffer1.limit(3);

        byteBuffer2.position(2);
        byteBuffer2.limit(4);

        fileChannel.write(byteBuffers);

        fileChannel.close();
        fos.close();
    }

    //write方法具有同步性
    private static void fileChannelBatchWriteTest3() throws InterruptedException, IOException {
        //fileChannel中的write方法的同步性
        FileOutputStream fos = new FileOutputStream(new File("c.txt"));
        FileChannel fileChannel = fos.getChannel();
        //启动两个线程
        for (int i = 0; i < 10; i++) {
            Thread thread1 = new Thread() {
                @Override
                public void run() {
                    ByteBuffer buffer1 = ByteBuffer.wrap("学无止境\r\n".getBytes());
                    ByteBuffer buffer2 = ByteBuffer.wrap("吾生有崖亦无涯\r\n".getBytes());
                    try {
                        ByteBuffer[] byteBuffers = new ByteBuffer[]{buffer1,buffer2};
                        fileChannel.write(byteBuffers);
                    } catch (IOException e) {
                        log.error("写入数据失败：{}" + e.getMessage());
                    }
                }
            };

            Thread thread2 = new Thread() {
                @Override
                public void run() {
                    ByteBuffer buffer1 = ByteBuffer.wrap("山高人为峰\r\n".getBytes());
                    ByteBuffer buffer2 = ByteBuffer.wrap("前行有路\r\n".getBytes());
                    ByteBuffer[] byteBuffers = new ByteBuffer[]{buffer1,buffer2};

                    try {
                        fileChannel.write(byteBuffers);
                    } catch (IOException e) {
                        log.error("写入数据失败：{}" + e.getMessage());
                    }
                }
            };

            thread1.start();
            thread2.start();
        }
        Thread.sleep(3000);
        fileChannel.close();
        fos.close();
    }



}
