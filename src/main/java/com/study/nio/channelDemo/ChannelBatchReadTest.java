package com.study.nio.channelDemo;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 进行批量读操作 long read(ByteBuffer[] dsts)
 */
@Slf4j
public class ChannelBatchReadTest {
    public static void main(String[] args) throws Exception {
        //进行批量读操作
        fileChannelBatchReadTest1();
        System.out.println("==========");
        //从通道的当前位置开始读取
        fileChannelBatchReadTet2();
        System.out.println("==========");
        //进行批量读，将字节放入ByteBuffer当前位置
        fileChannelReadBatchTest3();
        System.out.println("==========");
        //批量读的同步性
        fileChannelReadBatchTest4();

        //从通道读取的数据大于缓冲区容量
        fileChannelBatchReadTest5();

        //从通道的字节放入缓冲去的remaining空间中
        fileChannelBatchReadTest6();
    }

    private static void fileChannelBatchReadTest6() throws IOException {
        FileInputStream fis = new FileInputStream(new File("test1.txt"));
        FileChannel fileChannel = fis.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(7);
        byteBuffer.position(1);
        byteBuffer.limit(3);
        ByteBuffer byteBuffer1 = ByteBuffer.allocate(7);
        byteBuffer.position(2);
        byteBuffer.limit(4);
        ByteBuffer[] byteBuffers = new ByteBuffer[]{byteBuffer, byteBuffer1};
        fileChannel.read(byteBuffers);
        fileChannel.close();
        fis.close();

        byteBuffer.rewind();
        byteBuffer1.rewind();

        for (int i = 0; i < byteBuffers.length; i++) {
            ByteBuffer byteBuffer2 = byteBuffers[i];
            byte[] getByte = byteBuffer2.array();
            for (int j = 0; j < getByte.length; j++) {
                if (getByte[j] == 0) {
                    System.out.println("空格");
                } else {
                    System.out.println((char) getByte[j]);
                }
                System.out.println();
            }
        }
    }

    private static void fileChannelBatchReadTest5() throws IOException {
        FileInputStream fis = new FileInputStream(new File("test1.txt"));
        FileChannel fileChannel = fis.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(2);
        ByteBuffer byteBuffer1 = ByteBuffer.allocate(2);
        ByteBuffer[] byteBuffers = new ByteBuffer[]{byteBuffer, byteBuffer1};
        System.out.println("A " + fileChannel.position());
        long readLength = fileChannel.read(byteBuffers);
        System.out.println("B " + fileChannel.position() + "readLength=" + readLength);
        fileChannel.close();
        fis.close();

        byteBuffer.rewind();
        byteBuffer1.rewind();
        byteBuffer.position(1);
        for (int i = 0; i < byteBuffers.length; i++) {
            byte[] getByte = byteBuffers[i].array();
            for (int k = 0; k < getByte.length; k++) {
                System.out.print((char) getByte[k]);
            }
            System.out.println();
        }
    }

    private static void fileChannelReadBatchTest4() throws Exception {
        FileInputStream fis = new FileInputStream(new File("d.txt"));
        FileChannel fileChannel = fis.getChannel();
        for (int i = 0; i < 10; i++) {
            Thread thread1 = new Thread() {
                @Override
                public void run() {
                    try {
                        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
                        ByteBuffer byteBuffer1 = ByteBuffer.allocate(8);
                        ByteBuffer[] byteBuffers = new ByteBuffer[]{byteBuffer, byteBuffer1};
                        long readLength = fileChannel.read(byteBuffers);
                        while (readLength != -1) {
                            synchronized (ChannelBatchReadTest.class) {
                                for (int j = 0; j < byteBuffers.length; j++) {
                                    byte[] getByte = byteBuffers[j].array();
                                    for (int k = 0; k < getByte.length; k++) {
                                        System.out.println((char) getByte[k]);
                                    }
                                }
                            }
                            byteBuffer.clear();
                            byteBuffer1.clear();
                            readLength = fileChannel.read(byteBuffers);
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
                        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
                        ByteBuffer byteBuffer1 = ByteBuffer.allocate(8);
                        ByteBuffer[] byteBuffers = new ByteBuffer[]{byteBuffer, byteBuffer1};
                        long readLength = fileChannel.read(byteBuffers);
                        while (readLength != -1) {
                            synchronized (ChannelBatchReadTest.class) {
                                for (int j = 0; j < byteBuffers.length; j++) {
                                    byte[] getByte = byteBuffers[j].array();
                                    for (int k = 0; k < getByte.length; k++) {
                                        System.out.println((char) getByte[k]);
                                    }
                                }
                            }
                            byteBuffer.clear();
                            byteBuffer1.clear();
                            readLength = fileChannel.read(byteBuffers);
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


    private static void fileChannelReadBatchTest3() throws IOException {
        FileInputStream fis = new FileInputStream(new File("test1.txt"));
        FileChannel fileChannel = fis.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(2);
        ByteBuffer byteBuffer1 = ByteBuffer.allocate(2);
        ByteBuffer[] byteBuffers = new ByteBuffer[]{byteBuffer, byteBuffer1};
        byteBuffer.position(1);
        fileChannel.read(byteBuffers);
        for (int i = 0; i < byteBuffers.length; i++) {
            byte[] getByte = byteBuffers[i].array();
            for (int j = 0; j < getByte.length; j++) {
                if (getByte[j] == 0) {
                    System.out.println("空格");
                } else {
                    System.out.println((char) getByte[j]);
                }
                System.out.println();
            }
            fileChannel.close();
            fis.close();
        }
    }

    private static void fileChannelBatchReadTet2() throws IOException {
        FileInputStream fis = new FileInputStream(new File("test1.txt"));
        FileChannel fileChannel = fis.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(2);
        ByteBuffer byteBuffer1 = ByteBuffer.allocate(2);
        ByteBuffer[] byteBuffers = new ByteBuffer[]{byteBuffer, byteBuffer1};
        fileChannel.read(byteBuffers);
        for (int i = 0; i < byteBuffers.length; i++) {
            byte[] getByte = byteBuffers[i].array();
            for (int j = 0; j < getByte.length; j++) {
                System.out.println((char) getByte[j]);
            }
            System.out.println();
        }
        fileChannel.close();
        fis.close();
    }

    private static void fileChannelBatchReadTest1() throws IOException {
        FileInputStream fis = new FileInputStream(new File("test1.txt"));
        FileChannel fileChannel = fis.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(2);
        ByteBuffer byteBuffer1 = ByteBuffer.allocate(2);
        ByteBuffer[] byteBuffers = new ByteBuffer[]{byteBuffer, byteBuffer1};
        long readLength = fileChannel.read(byteBuffers);
        System.out.println(readLength);
        byteBuffer.clear();
        byteBuffer1.clear();

        readLength = fileChannel.read(byteBuffers);
        System.out.println(readLength);
        byteBuffer.clear();
        byteBuffer1.clear();

        readLength = fileChannel.read(byteBuffers);
        System.out.println(readLength);
        byteBuffer.clear();
        byteBuffer1.clear();

        readLength = fileChannel.read(byteBuffers);
        System.out.println(readLength);
        byteBuffer.clear();
        byteBuffer1.clear();

        fileChannel.close();
        fis.close();
    }
}
