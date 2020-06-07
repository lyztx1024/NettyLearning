package com.study.nio;

import java.nio.CharBuffer;

/**
 * 进行limit和position使用
 */
public class NIOTest2 {
    public static void main(String[] args) {
        char[] charArray = new char[]{'a', 'b', 'c', 'd', 'd', 'e', 'f', 'g'};
        CharBuffer buffer = CharBuffer.wrap(charArray);
        System.out.println("A capacity()=" + buffer.capacity() + " limit()=" + buffer.limit());
        buffer.limit(4);
        //buffer.position(2);
        System.out.println("B capacity()=" + buffer.capacity() + " limit()=" + buffer.limit());
        buffer.put(0, '0');
        buffer.put(1, 'h');
        buffer.put(2, '3');
        buffer.put(3, 'i');
        //会在这里抛异常，因为指定了现在4个数据
        buffer.put(4, 'j');
        buffer.put(5, 'k');
    }
}
