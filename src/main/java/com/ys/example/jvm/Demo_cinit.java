package com.ys.example.jvm;

/**
 * @Description
 * @Author 杨帅
 * @Date 2022/6/8 20:57
 * @Version 1.0
 **/
public class Demo_cinit {
    static int i = 10;
    static {
        i = 20;
    }
    static {
        i = 30;
    }

    public static void main(String[] args) {
        System.out.println(i);
    }
}
