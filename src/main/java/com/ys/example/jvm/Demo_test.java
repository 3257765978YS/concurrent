package com.ys.example.jvm;

/**
 * @Description
 * @Author 杨帅
 * @Date 2022/6/8 21:24
 * @Version 1.0
 **/
public class Demo_test {
    public static void main(String[] args) {
        int a = 10;
        int b = a++ +  ++a + a--;
        System.out.println(a);
        System.out.println(b);
    }
}
