package com.study.nio.byteDemo;

import java.nio.CharBuffer;

/**
 * 使用flip解决NIOTest6的行为
 */
public class NIOTest7 {
    public static void main(String[] args) {
        //创建一个CharBuffer，容量为20
        CharBuffer charBuffer = CharBuffer.allocate(20);
        //查看当前位置和限制大小，你可以理解为上界
        System.out.println("A postion = " + charBuffer.position() + "limit=" + charBuffer.limit());

        //写入字符
        charBuffer.put("一个前行在路上的行路人");
        System.out.println("B postion =" + charBuffer.position() + "limit=" + charBuffer.limit());

        //还原位置到0
        charBuffer.position(0);
        System.out.println("C postion = " + charBuffer.position() + "limit=" + charBuffer.limit());

        //写入字符
        charBuffer.put("学无止境，你在前行");
        System.out.println("D postion = " + charBuffer.position() + "limit=" + charBuffer.limit());

        //还原缓冲区的状态
        charBuffer.clear();
        System.out.println("E postion = " + charBuffer.position() + "limit=" + charBuffer.limit());
        //继续写入
        charBuffer.put("我不前行的时候，你也在前行");
        charBuffer.append("我要更努力");

        //charBuffer.limit(charBuffer.position());
        //charBuffer.position(0);
        charBuffer.flip();
        for (int i = 0; i < charBuffer.limit(); i++) {
            System.out.print(charBuffer.get());
        }
        System.out.println(" ");
    }
}