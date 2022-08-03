package com.ys.example.jvm;

import com.ys.example.util.ThreadUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author 杨帅
 * @Date 2022/6/6 15:31
 * @Version 1.0
 **/
public class DemoGC {
    private static final int _512KB = 512 * 1024;
    private static final int _1MB = 1024 * 1024;
    private static final int _6MB = 6 * 1024 * 1024;
    private static final int _7MB= 7 * 1024 * 1024;
    private static final int _8MB = 8 * 1024 * 1024;

    //-Xms20M -Xmx20M -Xmn10M -XX:+UseSerialGC -XX:+PrintGCDetails -verbose:gc
    public static void main(String[] args) {
        new Thread(()->{
            List<byte[]> list = new ArrayList<>();
            list.add(new byte[_8MB]);
            list.add(new byte[_8MB]);
        }).start();

        System.out.println("sleep...");
        ThreadUtil.sleep(1000L);
    }
}
