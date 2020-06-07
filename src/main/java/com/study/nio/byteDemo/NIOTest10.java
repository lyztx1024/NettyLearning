package com.study.nio.byteDemo;

import java.nio.ByteBuffer;

/**
 * 对于flip、clear、rewind之间的区别：
 * remind:标记清除，位置postion值归0，limit不变
 * public final Buffer rewind(){
 * postion = 0;
 * mark = -1;
 * return this;
 * }
 * <p>
 * clear:清除缓冲区，将位置设置为0，将限制设置为容量，并丢弃标记
 * public final Buffer clear(){
 * postion = 0;
 * limit = capacity;
 * mark = -1;
 * return -1;
 * }
 * <p>
 * flip:反转缓冲区，首先将限制设置为当前位置，然后将位置设置为0.
 * public final Buffer flip(){
 * limit = postion;
 * postion = 0;
 * mark = -1;
 * return this;
 * }
 * <p>
 * 三者的侧重点：
 * rewind:侧重在重新，在重新读取、重新写入时使用
 * clear:侧重还原一切状态
 * flip:侧重在substring截取
 */
public class NIOTest10 {
    public static void main(String[] args) {
        byte[] byteArray = new byte[]{1, 2, 3, 4};
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);
        System.out.println("capacity=" + byteBuffer.capacity() + " " + "limit=" + byteBuffer.limit() + " " + "postion=" + byteBuffer.position());

        byteBuffer.position(1);
        byteBuffer.limit(3);
        byteBuffer.mark();

        System.out.println("capacity=" + byteBuffer.capacity() + " " + "limit=" + byteBuffer.limit() + " " + "postion=" + byteBuffer.position());

        byteBuffer.rewind();
        System.out.println("capacity=" + byteBuffer.capacity() + " " + "limit=" + byteBuffer.limit() + " " + "postion=" + byteBuffer.position());

    }

}
