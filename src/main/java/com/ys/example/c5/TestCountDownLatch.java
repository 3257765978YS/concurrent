package com.ys.example.c5;

import com.ys.example.util.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description
 * @Author 杨帅
 * @Date 2022/5/22 16:10
 * @Version 1.0
 **/
@Slf4j
public class TestCountDownLatch {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);
        ExecutorService service = Executors.newFixedThreadPool(4);
        service.submit(()->{
            log.debug("begin...");
            ThreadUtil.sleep(1000);
            latch.countDown();
            log.debug("end...{}",latch.getCount() );
        });

        service.submit(()->{
            log.debug("begin...");
            ThreadUtil.sleep(1500);
            latch.countDown();
            log.debug("end...{}",latch.getCount() );
        });

        service.submit(()->{
            log.debug("begin...");
            ThreadUtil.sleep(2000);
            latch.countDown();
            log.debug("end...{}",latch.getCount() );
        });

        service.submit(()->{
            try {
                log.debug("waiting...");
                latch.await();
                log.debug("wait end...");
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        });

    }

    private static void method1() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);

        new Thread(()->{
            log.debug("begin...");
            ThreadUtil.sleep(1000);
            latch.countDown();
            log.debug("end...");
        }).start();

        new Thread(()->{
            log.debug("begin...");
            ThreadUtil.sleep(2000);
            latch.countDown();
            log.debug("end...");
        }).start();

        new Thread(()->{
            log.debug("begin...");
            ThreadUtil.sleep(1500);
            latch.countDown();
            log.debug("end...");
        }).start();

        log.debug("waiting...");
        latch.await();
        log.debug("wait end...");
    }
}
