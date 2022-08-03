package com.ys.example.jvm;

/**
 * @Description
 * @Author 杨帅
 * @Date 2022/6/10 14:22
 * @Version 1.0
 **/
public class Load01 {
    static {
        System.out.println("main init");
    }
    public static void main(String[] args) {
        System.out.println(new B[0]);
    }
}
class A{
    static int a = 0;
    static{
        System.out.println("a init");
    }
}
class B extends A{
    final static double b = 5.0;
    static boolean c = false;
    static{
        System.out.println("b init");
    }
}
