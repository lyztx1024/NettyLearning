package com.study.nio.channelDemo;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * fileChannel读操作：int read(ByteBuffer dst);
 * 将字节序列从此通道的当前位置读入给定的缓冲区的当前位置
 * int中返回的值：
 * 正数表示从通道的当前位置向bytebuffer缓冲区中读的字节个数
 * 0表示从通道中没有读取任何数据
 * -1表示到达流的末端
 */
@Slf4j
public class ChannelReadTest {
    public static void main(String[] args) throws Exception {
        //使用read方法操作
        fileChannelTest1();
        System.out.println("=======================");
        //从通道的当前位置开始读取
        fileChannelTest2();
        System.out.println("=======================");
        //将字节放入ByteBuffer当前位置
        fileChannelTest3();
        System.out.println("=======================");
        fileChannelTest4();
        System.out.println("=======================");
        //从通道读取的数据大于缓冲区容量
        fileChannelTest5();
        System.out.println("=======================");
        //从通道读取的字节放入缓冲区的remaining空间中
        fileChannelTest6();

    }

    private static void fileChannelTest6() throws IOException {
        FileInputStream fis = new FileInputStream(new File("test.txt"));
        FileChannel fileChannel = fis.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
        byteBuffer.position(1);
        byteBuffer.limit(3);
        fileChannel.read(byteBuffer);
        fileChannel.close();
        fis.close();
        byteBuffer.rewind();
        for (int i = 0; i < byteBuffer.limit(); i++) {
            byte eachByte = byteBuffer.get();
            if (eachByte == 0) {
                System.out.println("空格");
            } else {
                System.out.println((char) eachByte);
            }
        }
    }

    private static void fileChannelTest5() throws IOException {
        FileInputStream fis = new FileInputStream(new File("test.txt"));
        FileChannel fileChannel = fis.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(3);
        System.out.println("A" + fileChannel.position());
        fileChannel.read(byteBuffer);
        System.out.println("B" + fileChannel.position());
        fileChannel.close();
        fis.close();
        byteBuffer.rewind();

        for (int i = 0; i < byteBuffer.limit(); i++) {
            System.out.println((char) byteBuffer.get());
        }
    }

    private static void fileChannelTest4() throws InterruptedException, IOException {
        FileInputStream fis = new FileInputStream(new File("test4.txt"));
        FileChannel fileChannel = fis.getChannel();
        for (int i = 0; i < 1; i++) {
            Thread thread1 = new Thread() {
                @Override
                public void run() {
                    try {
                        ByteBuffer byteBuffer = ByteBuffer.allocate(5);
                        int readLength = fileChannel.read(byteBuffer);
                        while (readLength != -1) {
                            byte[] getByte = byteBuffer.array();
                            System.out.println(new String(getByte, 0, readLength));
                            byteBuffer.clear();
                            readLength = fileChannel.read(byteBuffer);
                        }
                    } catch (Exception e) {
                        log.error("写入数据失败：{}" + e.getMessage());
                    }
                }
            };
            Thread thread2 = new Thread() {
                @Override
                public void run() {
                    try {
                        ByteBuffer byteBuffer = ByteBuffer.allocate(5);
                        int readLength = fileChannel.read(byteBuffer);
                        while (readLength != -1) {
                            byte[] getByte = byteBuffer.array();
                            System.out.println(new String(getByte, 0, readLength));
                            byteBuffer.clear();
                            readLength = fileChannel.read(byteBuffer);
                        }
                    } catch (Exception e) {
                        log.error("写入数据失败：{}" + e.getMessage());
                    }
                }
            };
            thread1.start();
            thread2.start();

        }
        Thread.sleep(3000);
        fileChannel.close();
        fis.close();
    }

    private static void fileChannelTest3() throws IOException {
        FileInputStream fos = new FileInputStream(new File("test3.txt"));
        FileChannel fileChannel = fos.getChannel();
        //当前位置为2，因此下一个位置为3
        fileChannel.position(2);
        //而长度为5，因此打印结果：3 4 5 空格位置  空格位置
        ByteBuffer byteBuffer = ByteBuffer.allocate(5);
        fileChannel.read(byteBuffer);
        byteBuffer.position(3);
        //向byteBuffer读入cd
        fileChannel.read(byteBuffer);
        byte[] getByteArray = byteBuffer.array();
        for (int i = 0; i < getByteArray.length; i++) {
            if (getByteArray[i] == 0) {
                System.out.println(" 空格 ");
            } else {
                System.out.println((char) getByteArray[i]);
            }
        }
        fileChannel.close();
        fos.close();
    }

    //从通道的当前位置开始读取
    private static void fileChannelTest2() throws IOException {
        FileInputStream fis = new FileInputStream(new File("test3.txt"));
        FileChannel fileChannel = fis.getChannel();
        //当前位置为2，因此下一个位置为3
        fileChannel.position(2);
        //而长度为5，因此打印结果：3 4 5 空格位置  空格位置
        ByteBuffer byteBuffer = ByteBuffer.allocate(5);
        fileChannel.read(byteBuffer);
        byte[] getByteArray = byteBuffer.array();
        for (int i = 0; i < getByteArray.length; i++) {
            System.out.println((char) getByteArray[i]);
        }
        fileChannel.close();
        fis.close();
    }

    //使用read方法操作
    private static void fileChannelTest1() throws Exception {
        FileInputStream fis = new FileInputStream(new File("test3.txt"));
        FileChannel fileChannel = fis.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(5);
        int readLength = fileChannel.read(byteBuffer);
        System.out.println(readLength);
        //由于byteBuffer没有remaing剩余空间，因此返回的就是0
        readLength = fileChannel.read(byteBuffer);
        System.out.println(readLength);
        byteBuffer.clear();
        readLength = fileChannel.read(byteBuffer);
        //到达流的末尾值为-1
        System.out.println(readLength);
        byteBuffer.clear();
        fileChannel.close();
        fis.close();
    }
}
