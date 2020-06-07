package com.study.nio.byteDemo;

import java.nio.ByteBuffer;

/**
 * 对缓存区进行反转 flip()方法：原理是首先将闲置设置为当前位置，然后将
 * 位置设置为0.如果定义了标记，则丢弃该标记。
 * public Buffer flip(){
 * limit = postion;
 * postion = 0;
 * mark = -1;
 * return this;
 * }
 */
public class NIOTest5 {
    public static void main(String[] args) {
        byte[] byteArray = new byte[]{'a', 'b', 'c', 'd', 'd', 'e'};
        ByteBuffer buffer = ByteBuffer.wrap(byteArray);
        System.out.println("A capacity()=" + buffer.capacity() + " limit()=" + buffer.limit() + " position()=" + buffer.position());
        //buffer的下一个位置是2号角标的位置
        buffer.position(2);
        //在位置2设置mark
        buffer.mark();
        buffer.flip();
        System.out.println("remaining()=" + buffer.remaining());
        System.out.println("B capacity()=" + buffer.capacity() + " limit()=" + buffer.limit() + " position()=" + buffer.position());
        //位置重置，postion的位置会回到mark的位置
        try {
            buffer.reset();
        } catch (Exception e) {
            System.out.println("buffer的mark丢失了。。。");
        }

        //remaining()的作用：返回当前位置与limit之间的元素数,也即：remaining=limit-postion
        System.out.println("remaining()=" + buffer.remaining());
        System.out.println("B capacity()=" + buffer.capacity() + " limit()=" + buffer.limit() + " position()=" + buffer.position());


    }


}
