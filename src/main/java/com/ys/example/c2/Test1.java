package com.ys.example.c2;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

/**
 * @Description 第七章 共享模型之不可变
 * @Author 杨帅
 * @Date 2022/2/21 21:43
 * @Version 1.0
 **/
@Slf4j
public class Test1 {
    public static void main(String[] args) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                TemporalAccessor parse = dtf.parse("1951-04-21");
                log.debug("{}",parse);
            }).start();
        }
    }


    //有线程安全问题
    private static void test() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                synchronized (sdf){
                    try {
                        log.debug("{}",sdf.parse("1951-04-21"));
                    } catch (ParseException e) {
                        log.error("{}",e);
                    }
                }

            }).start();
        }
    }
}
