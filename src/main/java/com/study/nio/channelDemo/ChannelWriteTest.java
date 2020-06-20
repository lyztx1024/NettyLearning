package com.study.nio.channelDemo;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 进行channel的学习:
 * nio中buffer、channel、selector三个组件，其中buffer提供了读写操作的条件（缓冲区），而channel提供通道，
 * 而selector则是多路复用的技术
 * channel通道：用来传输数据的通道
 * 我们先来看FileChannel:主要是读取、写入、映射和操作文件的通道，该通道永远是阻塞的操作
 * 先看写操作 int write(ByteBuffer src);
 */
@Slf4j
public class ChannelWriteTest {
    public static void main(String[] args) throws Exception {
        //从通道的当前位置开始写入
        fileChannelTest1();
        System.out.println("================");
        //从remaining写入通道
        fileChannelTest2();
        System.out.println("================");
        //write方法具有同步性
        fileChennelTest3();
    }

    //write方法具有同步性
    private static void fileChennelTest3() throws InterruptedException, IOException {
        //fileChannel中的write方法的同步性
        FileOutputStream fos = new FileOutputStream(new File("test2.txt"));
        FileChannel fileChannel = fos.getChannel();
        //启动两个线程
        for (int i = 0; i < 10; i++) {
            Thread thread1 = new Thread() {
                @Override
                public void run() {
                    ByteBuffer buffer = ByteBuffer.wrap("学无止境\r\n".getBytes());
                    try {
                        fileChannel.write(buffer);
                    } catch (IOException e) {
                        log.error("写入数据失败：{}" + e.getMessage());
                    }
                }
            };

            Thread thread2 = new Thread() {
                @Override
                public void run() {
                    ByteBuffer buffer = ByteBuffer.wrap("山高人为峰\r\n".getBytes());
                    try {
                        fileChannel.write(buffer);
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

    //从remaining写入通道
    private static void fileChannelTest2() throws IOException {
        //创建流对象、获取通道，进行字节包装，使用通道进行写入
        FileOutputStream fos = new FileOutputStream(new File("test1.txt"));
        FileChannel fileChannel = fos.getChannel();
        try {
            //子节1：abcde
            ByteBuffer buffer1 = ByteBuffer.wrap("abcde".getBytes());
            //子节2:12345
            ByteBuffer buffer2 = ByteBuffer.wrap("12345".getBytes());
            //首先写入abcde,此时buffer2的当前位置是1，因此下一个位置是2，同时上界是3,因此2、3位置会放入，因此会将2、3放入到
            //abcde中，变成 ab23e,因此同时通道的位置设置当前位置是2，因此下一个位置是3，因此其位置为2+2=4
            fileChannel.write(buffer1);
            buffer2.position(1);
            buffer2.limit(3);
            fileChannel.position(2);
            fileChannel.write(buffer2);
            System.out.println(fileChannel.position());
        } catch (Exception e) {
            log.error("写入数据失败：{}" + e.getMessage());
        }
        //关闭流、通道
        fileChannel.close();
        fos.close();
    }

    //从通道的当前位置开始写入
    private static void fileChannelTest1() throws Exception {
        //创建流对象
        FileOutputStream fos = new FileOutputStream(new File("test.txt"));
        //获取通道
        FileChannel fileChannel = fos.getChannel();
        try {
            //将byte包装成byteBuffer，使用fileChannel的write方法将其写入到file中
            ByteBuffer buffer = ByteBuffer.wrap("abcde".getBytes());
            //写入之前的位置是0
            System.out.println("A fileChannel.postion()=" + fileChannel.position());
            //写入数据
            System.out.println("write() 1 返回值：" + fileChannel.write(buffer));
            //当前位置变成5
            System.out.println("B fileChannel.postion()=" + fileChannel.position());

            //此时的文件通道的位置是2
            fileChannel.position(2);
            //将其buffer的位置变成0
            buffer.rewind();
            //然后往文件里面写数据
            System.out.println("write() 2 返回值:" + fileChannel.write(buffer));
            //可以看到文件通道的位置变成了7
            System.out.println("C fileChannel.postion()=" + fileChannel.position());
        } catch (IOException e) {
            log.error("写入数据失败：{}" + e.getMessage());
        }
        //关通道、流
        fileChannel.close();
        fos.close();
    }
}
