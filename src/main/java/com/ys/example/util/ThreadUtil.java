package com.ys.example.util;

/**
 * @Description
 * @Author 杨帅
 * @Date 2022/5/21 23:11
 * @Version 1.0
 **/
public class ThreadUtil {

    public static void sleep(long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
