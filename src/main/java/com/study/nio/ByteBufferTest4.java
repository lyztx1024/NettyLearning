package com.study.nio;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * put操作：put(byte[] src)，相对批量put()方法，此方法是将给定的源byte数组的所有内容存储到此缓冲区的当前位置中
 * get操作：get(byte[] src)，相对批量get()方法，此方法是将此缓冲区remaining的字节传输到给定的目标数组
 *
 * put操作出现异常：
 * 缓冲区的remaining>=数组.length,不会出现异常
 * 缓冲区的remaing<数组.length，会出现异常
 *
 * get操作出现异常：
 * 缓冲区的remaining>=数组.length，不会出现异常
 * 缓冲区的remaining<数组.length，会出现异常
 */
public class ByteBufferTest4 {
    public static void main(String[] args) {
       // putMethod();
        putMethodExceptionSolve();
        System.out.println();
        getMethodExceptionSolve();
    }

    private static void putMethod() {
        byte[] byteArray = new byte[]{3, 4, 5, 6, 78, 8};
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        byteBuffer.put((byte) 1);
        byteBuffer.put((byte) 2);
        System.out.println("A=" + byteBuffer.position());
        byteBuffer.put(byteArray);
        System.out.println("B=" + byteBuffer.position());
        byteBuffer.flip();
        byteBuffer.position(3);
        System.out.println("C=" + byteBuffer.position());
        byte[] newArray = new byte[byteBuffer.remaining()];
        byteBuffer.get(newArray);
        //此时会有异常出现，由于此时的remaining<byteArray
        for (int i = 0; i < byteArray.length; i++) {
            System.out.println(newArray[i] + " ");
        }
    }

    private  static void putMethodExceptionSolve(){
        byte[] byteArray = new byte[]{3, 4, 5, 6, 78, 8,9,10};
        ByteBuffer byteBuffer = ByteBuffer.allocate(5);
        //设置两个标识，一个是数组的，一个是remaining的，byteArrayRemaining记录还剩下多少个元素,
        // 而remianing记录byteBuffer还有几个空间可以放,如果剩下的元素比剩下的空间大，则需要进行特殊处理，否者不需要
        int  byteArrayCurrentIndex = 0;
        int byteArrayRemaining = 0;
        while(byteArrayCurrentIndex<byteArray.length){
            byteArrayRemaining = byteArray.length - byteArrayCurrentIndex;
            int readLength = Math.min(byteArrayRemaining,byteBuffer.remaining());
            byte[] newByteArray = Arrays.copyOfRange(byteArray,byteArrayCurrentIndex,byteArrayCurrentIndex+readLength);
            byteBuffer.put(newByteArray);
            byteBuffer.flip();
            byte[] getByte = byteBuffer.array();
            for (int i = 0; i <byteBuffer.limit() ; i++) {
                System.out.print(getByte[i]+" ");
            }
            System.out.println();
            byteArrayCurrentIndex = byteArrayCurrentIndex + readLength;
            byteBuffer.clear();
        }
    }

    private static void getMethodExceptionSolve(){
       byte[] byteArray = new byte[]{1,2,3,4,5,6,7,8,9};
       ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);
       int copyDataCount =3;
       while (byteBuffer.hasRemaining()){
           byte[] copyByteArray = new byte[Math.min(byteBuffer.remaining(),copyDataCount)];
           byteBuffer.get(copyByteArray);
           for (int i = 0; i < copyByteArray.length; i++) {
               System.out.print(copyByteArray[i]);
           }
           System.out.println();
       }
    }

}
