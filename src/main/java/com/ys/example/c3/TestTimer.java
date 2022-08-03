package com.ys.example.c3;

import lombok.extern.slf4j.Slf4j;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

/**
 * @Description
 * @Author 杨帅
 * @Date 2022/5/18 6:57
 * @Version 1.0
 **/
@Slf4j
public class TestTimer {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);
//
//        pool.schedule(() ->{
//            try {
//                log.debug("task1");
//                int i = 1 / 0;
//            } catch (Exception e) {
//                log.error("error:",e);
//            }
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        },1, TimeUnit.SECONDS);

        ExecutorService pool = Executors.newFixedThreadPool(1);
        Future<Boolean> f = pool.submit(() -> {
            log.debug("task1");
            int i = 1 / 0;
            return true;
        });
        log.debug("result:{}",f.get());

    }

    private static void method3(ScheduledExecutorService pool) {
        log.debug("start...");
        pool.scheduleAtFixedRate(()->{
            log.debug("running...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },1,1,TimeUnit.SECONDS);

        pool.scheduleWithFixedDelay(()->{
            log.debug("running...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },1,1,TimeUnit.SECONDS);
    }

    private static void method2(ScheduledExecutorService pool) {
        pool.schedule(() ->{
            log.debug("task1");
//            int i = 1 / 0;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },1, TimeUnit.SECONDS);

        pool.schedule(() ->{
            log.debug("task2");
        },1, TimeUnit.SECONDS);
    }

    private static void method1() {
        Timer timer = new Timer();
        TimerTask task1 = new TimerTask(){
            @Override
            public void run() {
                log.debug("task 1");
//                int i = 1 / 0;
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        TimerTask task2 = new TimerTask(){
            @Override
            public void run() {
                log.debug("task 2");
            }
        };

        log.debug("start...");

        timer.schedule(task1,1000);
        timer.schedule(task2,1000);
    }
}
