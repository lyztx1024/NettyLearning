package com.study.nio.byteBufferDemo;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * 设置与获得字节顺序：order()方法与字节数据排列顺序有关
 * ByteOrder order()：获取此缓冲区的字节顺序
 */
public class ByteBufferTest8 {
    public static void main(String[] args) {
        getOrder();
    }

    private static void getOrder(){
        int value = 123456789;
        ByteBuffer byteBuffer = ByteBuffer.allocate(4);
        System.out.print(byteBuffer.order()+" ");
        System.out.print(byteBuffer.order()+" ");
        byteBuffer.putInt(value);
        byte[] byteArray = byteBuffer.array();
        for (int i = 0; i <byteArray.length ; i++) {
            System.out.print(byteArray[i]+" ");
        }

        System.out.println();
        byteBuffer = ByteBuffer.allocate(4);
        System.out.print(byteBuffer.order()+" ");
        byteBuffer.order(ByteOrder.BIG_ENDIAN);
        System.out.print(byteBuffer.order()+" ");
        byteBuffer.putInt(value);
        byteArray = byteBuffer.array();

        for (int i = 0; i < byteArray.length; i++) {
            System.out.print(byteArray[i]+" ");
        }

        System.out.println();

        byteBuffer = ByteBuffer.allocate(4);
        System.out.print(byteBuffer.order()+" ");
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        System.out.print(byteBuffer.order()+" ");
        byteBuffer.putInt(value);
        byteArray = byteBuffer.array();

        for (int i = 0; i < byteArray.length; i++) {
            System.out.print(byteArray[i]+" ");
        }
    }
}
