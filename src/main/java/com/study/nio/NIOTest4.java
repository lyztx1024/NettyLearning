package com.study.nio;

import java.nio.ByteBuffer;

/**
 * 使用buffer mark()方法处理标记
 * mark()的作用：在此缓冲区的位置设置标记，
 * 缓冲去的标记是一个索引，在调用reset()方法时，
 * 会将缓冲区的position位置重置为索引位置。
 * <p>
 * 如果定义了mark，则在将postion或limit调整为小于该mark的值时，该mark会被丢弃，丢弃后mark的值为-1
 * 如果未定义mark，调用reset会抛出InvalidMarkException异常
 * 同时可以看到默认是不开启直接缓冲区的，需要手动设置，此时在jvm和硬盘之间可以少了一个中间缓冲区，提高程序运行的效率
 */
public class NIOTest4 {
    public static void main(String[] args) {
        byte[] byteArray = new byte[]{'a', 'b', 'c', 'd', 'd', 'e', 'f'};
        ByteBuffer buffer = ByteBuffer.wrap(byteArray);
        System.out.println("A capacity()=" + buffer.capacity() + " limit()=" + buffer.limit() + " position()=" + buffer.position());
        buffer.limit(5);
        //buffer的下一个位置是2号角标的位置
        buffer.position(2);
        //在位置2设置mark
        buffer.mark();
        System.out.println("remaining()=" + buffer.remaining());
        System.out.println("B capacity()=" + buffer.capacity() + " limit()=" + buffer.limit() + " position()=" + buffer.position());
        buffer.position(3);
        //位置重置，postion的位置会回到mark的位置
        buffer.reset();
        //remaining()的作用：返回当前位置与limit之间的元素数,也即：remaining=limit-postion
        System.out.println("remaining()=" + buffer.remaining());
        System.out.println("B capacity()=" + buffer.capacity() + " limit()=" + buffer.limit() + " position()=" + buffer.position());

        //可以看到不是直接缓冲区
        System.out.println(buffer.isDirect());

        ByteBuffer byBuffer = ByteBuffer.allocate(100);
        System.out.println("默认关闭，此时调用的不是直接缓冲区:" + byBuffer.isDirect());
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(100);
        System.out.println("此时调用的是直接缓冲区:" + byteBuffer.isDirect());
    }


}
