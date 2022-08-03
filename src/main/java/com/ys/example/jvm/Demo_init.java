package com.ys.example.jvm;

/**
 * @Description
 * @Author 杨帅
 * @Date 2022/6/8 21:01
 * @Version 1.0
 **/
public class Demo_init {
    private String a = "s1";

    {
        b = 20;
    }

    private int b = 10;
    {
        a = "s2";
    }
    public Demo_init(String a,int b){
        this.a = a;
        this.b = b;
    }

    public static void main(String[] args) {
        Demo_init d = new Demo_init("s3", 30);
        System.out.println(d.a);
        System.out.println(d.b);
    }
}
