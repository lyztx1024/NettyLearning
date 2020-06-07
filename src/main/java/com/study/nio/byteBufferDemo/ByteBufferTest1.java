package com.study.nio.byteBufferDemo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;

/**
 * 直接缓冲区释放内存
 */
public class ByteBufferTest1 {
    public static void main(String[] args) throws NoSuchMethodException, InterruptedException, InvocationTargetException, IllegalAccessException {
        System.out.println("使用直接缓冲区");
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(Integer.MAX_VALUE);
        System.out.println("放入信息");
        byte[] byteArray = new byte[]{1};
        System.out.println(Integer.MAX_VALUE);
        for(int i = 0;i<Integer.MAX_VALUE;i++){
            byteBuffer.put(byteArray);
        }
        System.out.println("放入结束");
        Thread.sleep(1000);
        //通过反射获取cleaner、clean进行释放
        Method cleanerMethod = byteBuffer.getClass().getMethod("cleaner");
        cleanerMethod.setAccessible(true);
        Object returnValue = cleanerMethod.invoke(byteBuffer);
        Method cleanMethod = returnValue.getClass().getMethod("clean");
        cleanerMethod.setAccessible(true);
        cleanerMethod.invoke(returnValue);
    }
}
