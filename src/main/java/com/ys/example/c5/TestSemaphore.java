package com.ys.example.c5;

import com.ys.example.util.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;

/**
 * @Description
 * @Author 杨帅
 * @Date 2022/5/22 14:34
 * @Version 1.0
 **/
@Slf4j
public class TestSemaphore {
    public static void main(String[] args) {
        //创建semaphore对象
        Semaphore semaphore = new Semaphore(3);
        //10个线程同时运行
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    log.debug("running...");
                    ThreadUtil.sleep(1000);
                    log.debug("end");
                } finally {
                    semaphore.release();
                }
            }).start();
        }
    }
}
