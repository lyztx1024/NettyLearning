package com.study.nio.byteBufferDemo;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

/**
 * 进行字符缓冲区转换,其中get()方法采用的编码方式UTF-16BE
 */
public class ByteBufferTest7 {
    public static void main(String[] args) throws UnsupportedEncodingException {
        //会出现乱码现象
        charBufferDemo();
        charBufferDemo2();
        charBufferDemo3();
    }

    private  static void charBufferDemo(){
        byte[] byteArrayIn1 = "学无止境".getBytes();
        System.out.println(Charset.defaultCharset().name());
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArrayIn1);
        System.out.println("byteBuffer="+byteBuffer.getClass().getName());

        CharBuffer buffer =byteBuffer.asCharBuffer();
        System.out.println("charBuffer="+buffer.getClass().getName());
        System.out.println("byteBuffer.postion="+ byteBuffer.position()+" byteBuffer.capacity="+byteBuffer.capacity()+" byteBuffer.limit="+byteBuffer.limit());

        System.out.println(buffer.capacity());
        buffer.position(0);
        for (int i = 0; i < buffer.capacity(); i++) {
            System.out.print(buffer.get()+" ");
        }
    }

    private  static void charBufferDemo2() throws UnsupportedEncodingException {
        byte[] byteArrayIn1 = "学无止境".getBytes("utf-16BE");
        System.out.println(Charset.defaultCharset().name());
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArrayIn1);
        System.out.println("byteBuffer="+byteBuffer.getClass().getName());

        CharBuffer buffer =byteBuffer.asCharBuffer();
        System.out.println("charBuffer="+buffer.getClass().getName());
        System.out.println("byteBuffer.postion="+ byteBuffer.position()+" byteBuffer.capacity="+byteBuffer.capacity()+" byteBuffer.limit="+byteBuffer.limit());

        System.out.println(buffer.capacity());
        buffer.position(0);
        for (int i = 0; i < buffer.capacity(); i++) {
            System.out.print(buffer.get()+" ");
        }
    }

    private  static void charBufferDemo3() throws UnsupportedEncodingException {
        byte[] byteArrayIn1 = "学无止境".getBytes("utf-8");
        System.out.println(Charset.defaultCharset().name());
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArrayIn1);
        System.out.println("byteBuffer="+byteBuffer.getClass().getName());

       // CharBuffer buffer =byteBuffer.asCharBuffer();
        CharBuffer buffer = Charset.forName("utf-8").decode(byteBuffer);
        System.out.println("charBuffer="+buffer.getClass().getName());
        System.out.println("byteBuffer.postion="+ byteBuffer.position()+" byteBuffer.capacity="+byteBuffer.capacity()+" byteBuffer.limit="+byteBuffer.limit());

        System.out.println(buffer.capacity());
        buffer.position(0);
        for (int i = 0; i < buffer.limit(); i++) {
            System.out.print(buffer.get()+" ");
        }
    }
}
