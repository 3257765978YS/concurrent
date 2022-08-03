package com.ys.example.c3;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.SynchronousQueue;

/**
 * @Description
 * @Author 杨帅
 * @Date 2022/5/16 17:48
 * @Version 1.0
 **/
@Slf4j
public class TestSynchronousQueue {
    public static void main(String[] args) {
        SynchronousQueue<Integer> integers = new SynchronousQueue<>();
        new Thread(()->{
            try {
                log.debug("putting {} ",1);
                integers.put(1);
                log.debug(" {} put... ",1);


                log.debug("putting {} ",2);
                integers.put(2);
                log.debug(" {} put... ",2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"t1").start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            try {
                log.debug("taking {} ",1);
                integers.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t2").start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            try {
                log.debug("taking {} ",2);
                integers.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t3").start();

    }
}
