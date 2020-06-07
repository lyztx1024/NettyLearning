package com.study.nio;

import java.nio.ByteBuffer;

/**
 * 复制缓冲区：duplicate()
 */
public class ByteBufferTest11 {
    public static void main(String[] args) {
        duplicateAndSlice();
        duplicateMethod();
    }

    private static void duplicateMethod() {
        byte[] byteBufferIn1 = {1,2,3,4,5};
        ByteBuffer byteBuffer1 = ByteBuffer.wrap(byteBufferIn1);
        ByteBuffer byteBuffer2 = byteBuffer1.duplicate();
        System.out.println("A capacity="+ byteBuffer1.capacity()+" limit="+byteBuffer1.limit()+" postion="+byteBuffer1.position());
        System.out.println("B capacity="+ byteBuffer2.capacity()+" limit="+byteBuffer2.limit()+" postion="+byteBuffer2.position());
        byteBuffer2.put(1,(byte)22);
        byteBuffer2.position(3);
        System.out.println("C capacity="+ byteBuffer1.capacity()+" limit="+byteBuffer1.limit()+" postion="+byteBuffer1.position());
        System.out.println("D capacity="+ byteBuffer2.capacity()+" limit="+byteBuffer2.limit()+" postion="+byteBuffer2.position()+" byteBuffer2的位置是3，而byteBuffer1还是0，说明位置、限制和标记值是独立的 ");

        byteBuffer1.position(0);
        for (int i = byteBuffer1.position(); i < byteBuffer1.limit(); i++) {
            System.out.print(byteBuffer1.get(i)+" ");
        }
    }

    private static void duplicateAndSlice() {
        byte[] byteBufferIn1 = {1,2,3,4,5};
        ByteBuffer byteBuffer1 = ByteBuffer.wrap(byteBufferIn1);
        byteBuffer1.position(2);
        System.out.println("A capacity="+ byteBuffer1.capacity()+" limit="+byteBuffer1.limit()+" postion="+byteBuffer1.position());
        ByteBuffer byteBuffer2 = byteBuffer1.slice();
        ByteBuffer byteBuffer3 = byteBuffer1.duplicate();

        ByteBuffer byteBuffer4 = byteBuffer1;
        System.out.println("B capacity="+ byteBuffer2.capacity()+" limit="+byteBuffer2.limit()+" postion="+byteBuffer2.position());
        System.out.println("C capacity="+ byteBuffer3.capacity()+" limit="+byteBuffer3.limit()+" postion="+byteBuffer3.position());
        byteBuffer2.position(0);
        for (int i = byteBuffer2.position(); i < byteBuffer2.limit(); i++) {
            System.out.print(byteBuffer2.get(i)+" ");
        }
        System.out.println();
        byteBuffer3.position(0);
        for (int i = byteBuffer3.position(); i < byteBuffer3.limit(); i++) {
            System.out.print(byteBuffer3.get(i)+" ");
        }
    }
}
