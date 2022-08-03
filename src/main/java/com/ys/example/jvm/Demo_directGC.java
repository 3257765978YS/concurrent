package com.ys.example.jvm;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * @Description
 * @Author 杨帅
 * @Date 2022/6/5 12:22
 * @Version 1.0
 **/
@Slf4j
public class Demo_directGC {
    static int _1Gb = 1024 * 1024 * 1024;

    /**
     * -XX:+DisableExplicitGC 禁用显式的垃圾回收
     */
    public static void main(String[] args) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(_1Gb);
        log.debug("分配完毕...");
        System.in.read();
        log.debug("开始释放...");
        byteBuffer = null;
        System.gc(); //此为显示的垃圾回收  Full GC(也叫整堆收集)
        System.in.read();

    }
}
