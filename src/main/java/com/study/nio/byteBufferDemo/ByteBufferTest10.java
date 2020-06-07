package com.study.nio.byteBufferDemo;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * 使用equals和CompareTo进行比较
 */
public class ByteBufferTest10 {
    public static void main(String[] args) {
        equalsMethod();
        equalsMethods();
        remainingEquals();
        equalsMethod2();
        compareToMethod();
        compareToMethod2();
    }

    private static void compareToMethod2() {
        byte[] byteBufferIn1 = {3,4,5};
        byte[] byteBufferIn2 = {1,2,3,4,5,6,7,8,9};
        ByteBuffer byteBuffer1 = ByteBuffer.wrap(byteBufferIn1);
        ByteBuffer byteBuffer2 = ByteBuffer.wrap(byteBufferIn2);
        byteBuffer1.position(0);
        byteBuffer2.position(2);
        System.out.println("A="+byteBuffer1.compareTo(byteBuffer2));
    }

    private static void compareToMethod() {
        byte[] byteBufferIn1 = {3,4,5};
        byte[] byteBufferIn2 = {1,2,3,104,5,6,7,8,9};
        ByteBuffer byteBuffer1 = ByteBuffer.wrap(byteBufferIn1);
        ByteBuffer byteBuffer2 = ByteBuffer.wrap(byteBufferIn2);
        byteBuffer1.position(0);
        byteBuffer2.position(2);
        System.out.println("A="+byteBuffer1.compareTo(byteBuffer2));
    }

    private static void equalsMethod2() {
        byte[] byteBufferIn1 = {3,4,5};
        byte[] byteBufferIn2 = {1,2,3,4,5,6,7,8};
        ByteBuffer byteBuffer1 = ByteBuffer.wrap(byteBufferIn1);
        ByteBuffer byteBuffer2 = ByteBuffer.wrap(byteBufferIn2);
        byteBuffer1.position(0);
        byteBuffer1.limit(3);
        byteBuffer2.position(2);
        byteBuffer2.limit(5);
        System.out.println("A="+byteBuffer1.equals(byteBuffer2));
        System.out.println("B byteBuffer1.remaining()="+byteBuffer1.remaining());
        System.out.println("C byteBuffer2.remaining()="+byteBuffer1.remaining());
        byteBuffer2.put(3,(byte)44);
        System.out.println("D="+byteBuffer1.equals(byteBuffer2));
        System.out.println("E byteBuffer1.remaining()="+byteBuffer1.remaining());
        System.out.println("F byteBuffer2.remaining()="+byteBuffer2.remaining());
    }

    private static void remainingEquals() {
        byte[] byteArrayIn1 = {3,4,5};
        byte[] byteArrayIn2 = {1,2,3,4,5,6,7,8};
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArrayIn1);
        ByteBuffer byteBuffer1 = ByteBuffer.wrap(byteArrayIn2);
        byteBuffer.position(0);
        byteBuffer1.position(3);
        System.out.println("A="+byteBuffer.equals(byteBuffer1));
        System.out.println("byteBuffer.remaining()="+byteBuffer.remaining());
        System.out.println("byteBuffer1.remaining()="+byteBuffer1.remaining());
    }

    private static void equalsMethods() {
        byte[] byteArrayIn1 = {1,2,3,4,5,6,7,8};
        int[] intArrayIn2 = {1,2,3,4,5,6,7,8};
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArrayIn1);
        IntBuffer intBuffer = IntBuffer.wrap(intArrayIn2);
        System.out.println("isEquals="+byteBuffer.equals(intBuffer));
    }

    private static void equalsMethod() {
        byte[] byteBufferIn1 = {1,2,3,4,5,6};
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteBufferIn1);
        System.out.println("isEquals="+byteBuffer.equals(byteBuffer));
    }


}
