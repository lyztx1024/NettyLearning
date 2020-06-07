package com.study.nio;

import java.nio.ByteBuffer;

/**
 * 测试put和get方法，这里put是放元素，get是获取元素
 * put(byte b) get()
 * 一次放入多个元素,进行批量操作
 * put(byte[] src,int offset,int length)
 * =>for(int i=offset;i<offset+length;i++){
 * dst.put(a[i]);
 * }
 * <p>
 * get(byte[] src, int offset,int length)
 * =>for(int i =offset;i<offset+length;i++){
 * dst[i] = src.get(i);
 * }
 * <p>
 * 在使用put(byte[] src,int offset,int length)方法时，需要注意：
 * 1)当offset+length>src.length,抛出IndexOutOfBoundsException异常
 * 2)当参数length的值大于buffer.remaining时，抛出BufferOverflowException异常
 * <p>
 * 在使用get(byte[] dst,int offset,int length)方法时，需要注意：
 * 1)当offset+length>dst.length,抛出IndexOutOfBoundsException异常
 * 2)当参数length的值大于buffer.remaining时，抛出BufferUnderflowException异常
 */
public class ByteBufferTest3 {
    public static void main(String[] args) {
        //仔细观察postion的变化，进行rewind后postion的变化，如果postion后面的位置的数据没有，则默认为0
        // singleElementPutAndGet();
        System.out.println();
        //一次性放入多个数据,进行批量操作
        batchElementPutAndGet();
        System.out.println();
        //测试异常处理,针对写多写少情况进行处理
        exPutByteBufferSolve();
        System.out.println();
        //测试get异常处理,针对写多写少进行处理
        exGetByteBufferSolve();
    }

    private static void singleElementPutAndGet() {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        System.out.println("buffer capacity=" + buffer.capacity() + " limit=" + buffer.limit() + " postion=" + buffer.position());
        buffer.put((byte) 125);
        System.out.println("buffer capacity=" + buffer.capacity() + " limit=" + buffer.limit() + " postion=" + buffer.position());
        buffer.put((byte) 126);
        buffer.rewind();
        System.out.println("buffer capacity=" + buffer.capacity() + " limit=" + buffer.limit() + " postion=" + buffer.position());
        System.out.println(buffer.get());
        System.out.println("buffer capacity=" + buffer.capacity() + " limit=" + buffer.limit() + " postion=" + buffer.position());
        System.out.println(buffer.get());
        System.out.println("buffer capacity=" + buffer.capacity() + " limit=" + buffer.limit() + " postion=" + buffer.position());
        System.out.println(buffer.get());
        byte[] getByteArray = buffer.array();
        for (int i = 0; i < getByteArray.length; i++) {
            System.out.print(getByteArray[i] + " ");
        }
    }


    private static void batchElementPutAndGet() {
        byte[] byteArrayIn1 = {1, 2, 3, 4, 5, 6, 7, 8};
        byte[] byteArrayIn2 = {11, 22, 33, 44};
        //分配空间10
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        //将byteArrayIn1放入前面8个位置
        byteBuffer.put(byteArrayIn1);
        //设置当前位置为2
        byteBuffer.position(2);
        //偏移量为1，同时长度为3，长度为<offset+length=4,也即3个元素，从角标1开始，也即第2-4个元素是会放入进去
        //由于当前位置是2，因此下一个位置是3，因此是下面的摆放方式
        //1 2 22 33 44 6 7 8
        byteBuffer.put(byteArrayIn2, 1, 3);
        System.out.print("A=");
        byte[] getByte = byteBuffer.array();
        for (int i = 0; i < getByte.length; i++) {
            System.out.print(getByte[i] + " ");
        }
        System.out.println();
        //设置当前位置为1
        byteBuffer.position(1);
        byte[] byteArrayOut = new byte[byteBuffer.capacity()];
        //设置当前位置为1，偏移量为3，长度为4，因此长度<offset+length=3+4，从角标为3的元素开始，第3-第7个元素会被填充，从而获取
        //由于当前位置为1，因此下一个位置是2，因此拿到的数据是2，也即有4个元素，那么应该是2,22,33,44，所以拿到的数据是
        //0 0 0 2 22 33 44 0 0 0
        byteBuffer.get(byteArrayOut, 3, 4);
        for (int i = 0; i < byteArrayOut.length; i++) {
            System.out.print(byteArrayOut[i] + " ");
        }
    }

    private static void exPutByteBufferSolve() {
        byte[] byteArrayIn1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 23, 24};
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        int getArrayIndex = 0;
        while (getArrayIndex < byteArrayIn1.length) {
            int readLength = Math.min(byteBuffer.remaining(), byteArrayIn1.length - getArrayIndex);
            byteBuffer.put(byteArrayIn1, getArrayIndex, readLength);
            byteBuffer.flip();
            byte[] getArray = byteBuffer.array();
            for (int i = 0; i < byteBuffer.limit(); i++) {
                System.out.print(getArray[i] + " ");
            }
            getArrayIndex = getArrayIndex + readLength;
            System.out.println();
            byteBuffer.clear();
        }
    }

    private static void exGetByteBufferSolve() {
        byte[] byteArrayIn = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 22, 33};
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArrayIn);
        byte[] byteArrayOut = new byte[5];
        while (byteBuffer.hasRemaining()) {
            int readLength = Math.min(byteBuffer.remaining(), byteArrayOut.length);
            byteBuffer.get(byteArrayOut, 0, readLength);
            for (int i = 0; i <readLength; i++) {
                System.out.print(byteArrayOut[i] + " ");
            }
            System.out.println();
        }
    }
}
