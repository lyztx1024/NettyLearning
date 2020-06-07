package com.study.nio.byteDemo;

import java.nio.ByteBuffer;

/**
 * 获取偏移量 ArrayOffset
 * final int arrayOffset()：返回此缓冲区的底层实现数组中的第一个缓冲区元素的偏移量
 * public final int arrayOffset(){
 * if(hb==null)
 * throw new UnsupportedOperationException();
 * if(isReadOnly)
 * throw new ReadOnlyBufferException();
 * return offset;
 * }
 */
public class NIOTest11 {
    public static void main(String[] args) {
        getArrayOffsetZero();
        getArrayOffset();
    }

    //测试结果永远都为0的情况
    private static void getArrayOffsetZero() {
        byte[] byteArray = new byte[]{1,2,3,4,5,6};
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);
        System.out.println("byteBuffer.arrayOffset ="+byteBuffer.arrayOffset());
    }

    //测试不为0的情况，偏移是相对而言的
    private static void getArrayOffset() {
        byte[] byteArray = new byte[]{1,2,3,4,5,6};
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);
        byteBuffer.position(5);
        ByteBuffer byteBuffer1 = byteBuffer.slice();
        System.out.println("byteBuffer.arrayOffset ="+byteBuffer1.arrayOffset());
    }

}
