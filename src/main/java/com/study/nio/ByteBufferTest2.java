package com.study.nio;

import javax.print.DocFlavor;
import java.nio.ByteBuffer;

/**
 * 包装wrap数据的处理：将byte数组包装到缓冲区中
 * wrap(byte[] array,int offset,int length)
 * array:缓冲区中的关联的字节数组
 * offset:偏移量
 * length:长度,limit = offset+length
 */
public class ByteBufferTest2 {
    public static void main(String[] args) {
      byte[] byteArray = new byte[] {1,2,3,4,5,6,7,8};
      ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);
      ByteBuffer byteBuffer1 = ByteBuffer.wrap(byteArray,2,4);
      System.out.println("bytebuffer capacity="+byteBuffer.capacity()+" "+"limit="+byteBuffer.limit()+" "+"postion="+byteBuffer.position());
      System.out.println();
      System.out.println("bytebuffer1 capacity="+byteBuffer1.capacity()+" "+"limit="+byteBuffer1.limit()+" "+"postion="+byteBuffer1.position());

    }
}
