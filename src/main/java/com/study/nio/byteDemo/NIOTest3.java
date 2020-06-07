package com.study.nio.byteDemo;

import java.nio.CharBuffer;

/**
 * 进行limit和position、remaining的使用
 */
public class NIOTest3 {
    public static void main(String[] args) {
        char[] charArray = new char[]{'a', 'b', 'c', 'd', 'd', 'e', 'f', 'g'};
        CharBuffer buffer = CharBuffer.wrap(charArray);
        System.out.println("A capacity()=" + buffer.capacity() + " limit()=" + buffer.limit() + " position()=" + buffer.position());
        buffer.limit(5);
        //buffer的下一个位置是2号角标的位置
        buffer.position(2);

        //remaining()的作用：返回当前位置与limit之间的元素数,也即：remaining=limit-postion
        System.out.println("remaining()=" + buffer.remaining());
        System.out.println("B capacity()=" + buffer.capacity() + " limit()=" + buffer.limit() + " position()=" + buffer.position());
        buffer.put('i'); //第三个数据c会变成i
        for (int i = 0; i < charArray.length; i++) {
            System.out.println(charArray[i] + " ");

        }
    }


}
