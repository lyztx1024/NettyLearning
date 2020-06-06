package com.study.nio;

import java.nio.*;

/**
 * 进行nio测试:可以看到ByteBuffer、CharBuffer、DoubleBuffer、FloatBuffer、IntBuffer、LongBuffer、ShortBuffer
 * 是抽象类，而wrap()就相当于创建这些缓冲区的工厂方法，都会经过wrap(array, 0, array.length)方法，比如ByteBuffer会经过
 * //可以看到hb是一个字节数组
 * ByteBuffer(int mark, int pos, int lim, int cap,byte[] hb, int offset) {
 * //调用buffer的构造方法
 * super(mark, pos, lim, cap);
 * //仅堆缓冲区为非null
 * this.hb = hb;
 * //偏移量
 * this.offset = offset;
 * }
 * <p>
 * ByteBuffer类缓冲区的技术原理就是使用byte[]数组进行数据保存
 * 缓冲区存储的数据还是存储在byte[]字节数组中。使用缓冲区与使用byte[]字节数组相比：
 * 优点在于缓冲区将存储数据的byte[]字节数组内容与相关的信息整合在1个Buffer类中，将
 * 数据与缓冲区中的信息进行了整合，并进行了封装，这样便于得到相关的信息和处理数据
 */
public class NIOTest {
    public static void main(String[] args) {
        byte[] byteArray = new byte[]{1, 2, 3};
        short[] shortArray = new short[]{1, 2, 3, 4};

        int[] intArray = new int[]{1, 2, 3, 4, 5};
        long[] longArray = new long[]{1, 2, 3, 4, 5, 6, 7};
        float[] floatArray = new float[]{1, 2, 3, 4, 5, 6, 7};
        double[] doubleArray = new double[]{1, 2, 3, 4, 5, 6, 7, 8};
        char[] charArray = new char[]{'a', 'b', 'c', 'd'};

        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);
        ShortBuffer shortBuffer = ShortBuffer.wrap(shortArray);
        IntBuffer intBuffer = IntBuffer.wrap(intArray);
        LongBuffer longBuffer = LongBuffer.wrap(longArray);
        FloatBuffer floatBuffer = FloatBuffer.wrap(floatArray);
        DoubleBuffer doubleBuffer = DoubleBuffer.wrap(doubleArray);
        CharBuffer charBuffer = CharBuffer.wrap(charArray);

        /**
         * 返回结果：
         * byteBuffer=java.nio.HeapByteBuffer
         * shortBuffer=java.nio.HeapShortBuffer
         * intBuffer=java.nio.HeapIntBuffer
         * longBuffer=java.nio.HeapLongBuffer
         * floatBuffer=java.nio.HeapFloatBuffer
         * doubleBuffer=java.nio.HeapDoubleBuffer
         * charBuffer=java.nio.HeapCharBuffer
         */
        System.out.println("byteBuffer=" + byteBuffer.getClass().getName());
        System.out.println("shortBuffer=" + shortBuffer.getClass().getName());
        System.out.println("intBuffer=" + intBuffer.getClass().getName());
        System.out.println("longBuffer=" + longBuffer.getClass().getName());
        System.out.println("floatBuffer=" + floatBuffer.getClass().getName());
        System.out.println("doubleBuffer=" + doubleBuffer.getClass().getName());
        System.out.println("charBuffer=" + charBuffer.getClass().getName());

        System.out.println("======================================");

        System.out.println("byteBufffer.capacity=" + byteBuffer.capacity());
        System.out.println("shortBuffer.capacity=" + shortBuffer.capacity());
        System.out.println("intBuffer.capacity=" + intBuffer.capacity());
        System.out.println("longBuffer.capacity=" + longBuffer.capacity());
        System.out.println("floatBuffer.capacity=" + floatBuffer.capacity());
        System.out.println("doubleBuffer.capacity=" + doubleBuffer.capacity());
        System.out.println("charBuffer.capacity=" + charBuffer.capacity());

    }


}
