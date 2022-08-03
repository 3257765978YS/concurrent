package com.ys.example.jvm;

/**
 * @Description 演示StringTable垃圾回收
 * -Xmx10m -XX:+PrintStringTableStatistics -XX:+PrintGCDetails -verbose:gc
 * @Author 杨帅
 * @Date 2022/6/4 17:33
 * @Version 1.0
 **/
public class Demo3 {
    public static void main(String[] args) {
        int i = 0;
        try {
            /*for (int j = 0; j < 10000; j++) {
                String.valueOf(j).intern();
                i++;
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(i);
        }
    }
}
