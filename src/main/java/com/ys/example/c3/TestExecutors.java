package com.ys.example.c3;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description
 * @Author 杨帅
 * @Date 2022/5/16 18:10
 * @Version 1.0
 **/
@Slf4j
public class TestExecutors {
    public static void main(String[] args) {
        test2();
    }

    private static void test2() {
        ExecutorService pool = Executors.newSingleThreadExecutor();
        pool.execute(()->{
            log.debug("1");
            int i = 1/0;
        });

        pool.execute(()-> log.debug("2"));

        pool.execute(()-> log.debug("3"));


    }

    private static void test1(){
        ExecutorService pool = Executors.newFixedThreadPool(2, new ThreadFactory() {
            private AtomicInteger t = new AtomicInteger(1);
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r,"myPool_t"+t.getAndIncrement());
            }
        });
    }
}
