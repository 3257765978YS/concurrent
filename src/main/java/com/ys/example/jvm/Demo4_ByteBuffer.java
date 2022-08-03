package com.ys.example.jvm;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 演示DirectMemory 直接内存溢出
 * @Author 杨帅
 * @Date 2022/6/5 12:16
 * @Version 1.0
 **/
public class Demo4_ByteBuffer {
    static int _100Mb = 1024 * 1024 * 100;
    public static void main(String[] args) {
        List<ByteBuffer> list = new ArrayList<>();
        int i = 0;
        try {
            while(true){
                ByteBuffer byteBuffer = ByteBuffer.allocateDirect(_100Mb);
                list.add(byteBuffer);
                i++;
            }
        } finally {
            System.out.println(i);
        }
    }
}
