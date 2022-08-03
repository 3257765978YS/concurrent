package com.ys.example.jvm;

import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 演示软引用
 * -Xmx20m -XX:+PrintGCDetails -verbose:gc
 * @Author 杨帅
 * @Date 2022/6/5 20:37
 * @Version 1.0
 **/
public class Demo_SoftRef {
    private static final int _4MB = 1024 * 1024 * 4;


    public static void main(String[] args) throws IOException {
        //强引用
        /*List<byte[]> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new byte[_4MB]);
        }
        System.in.read();*/

        //软引用
        soft();
    }

    public static void soft(){
        List<SoftReference<byte[]>> list = new ArrayList<>();

        //引用队列
        ReferenceQueue<byte[]> queue = new ReferenceQueue<>();

        for (int i = 0; i < 5; i++) {
            //关联引用队列，当软引用关联的byte[]被回收时，软引用自己会加入到queue当中去
            SoftReference<byte[]> ref = new SoftReference<>(new byte[_4MB],queue);
            System.out.println(ref.get());
            list.add(ref);
            System.out.println(list.size());
        }

        //遍历list前 先从队列中获取无用的软引用对象并删除
        Reference<? extends byte[]> poll = queue.poll();
        while (poll!=null) {
          list.remove(poll);
        }
        System.out.println("======================");
        System.out.println("循环结束："+list.size());
        for (SoftReference<byte[]> ref : list) {
            System.out.println(ref.get());
        }
    }
}
