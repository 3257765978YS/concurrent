package com.ys.example.c3;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description
 * @Author 杨帅
 * @Date 2022/5/16 16:31
 * @Version 1.0
 **/
@Slf4j(topic = "c.TestThreadPoolExecutors")
public class TestThreadPoolExecutors {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(2, new ThreadFactory() {
            private AtomicInteger t = new AtomicInteger(1);
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r,"myPool_t"+t.getAndIncrement());
            }
        });

        pool.execute(()-> log.debug("1"));
        pool.execute(()-> log.debug("2"));
        pool.execute(()-> log.debug("3"));
    }
}
