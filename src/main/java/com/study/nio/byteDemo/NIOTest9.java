package com.study.nio.byteDemo;

import java.nio.ByteBuffer;

/**
 * 判断当前位置和限制之间是否有剩余元素
 * 进行迭代
 */
public class NIOTest9 {
    public static void main(String[] args) {
        hasRemain();
        hasElementGet();
    }

    //测试还剩下的元素
    private static void hasRemain() {
        byte[] byteArray = new byte[]{1, 2, 3, 4};
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);
        byteBuffer.limit(4);

        byteBuffer.position(2);
        System.out.println("byteBuffer.hasRemaining=" + byteBuffer.hasRemaining() + " " + "byteBuffer.remaining=" + byteBuffer.remaining());
    }

    //使用剩下的特性，进行迭代
    private static void hasElementGet() {
        byte[] byteArray = new byte[]{1, 2, 3, 4, 5};
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);
        int remaining = byteBuffer.remaining();
        for (int i = 0; i < remaining; i++) {
            System.out.print(byteBuffer.get() + "");
        }
        System.out.println("");
        byteBuffer.clear();
        while (byteBuffer.hasRemaining()) {
            System.out.print(byteBuffer.get() + "");
        }

        System.out.println("");
        byteBuffer.clear();
        for (; byteBuffer.hasRemaining() == true; ) {
            System.out.print(byteBuffer.get() + "");
        }
        System.out.println("");
    }

}
