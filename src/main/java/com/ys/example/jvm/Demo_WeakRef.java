package com.ys.example.jvm;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 演示弱引用
 * -Xmx20m -XX:+PrintGCDetails -verbose:gc
 * @Author 杨帅
 * @Date 2022/6/5 21:14
 * @Version 1.0
 **/
public class Demo_WeakRef {
    private static final int _4MB = 1024 * 1024 * 4;

    public static void main(String[] args) {
        List<WeakReference<byte[]>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            WeakReference<byte[]> ref = new WeakReference<>(new byte[_4MB]);
            list.add(ref);
            for (WeakReference<byte[]> w : list) {
                System.out.print(w.get()+" ");
            }
            System.out.println();
        }
        System.out.println("循环结束："+list.size());
    }
}
