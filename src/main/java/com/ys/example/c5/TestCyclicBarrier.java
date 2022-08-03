package com.ys.example.c5;

import com.ys.example.util.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

import javax.sound.sampled.FloatControl;
import java.util.concurrent.*;

/**
 * @Description
 * @Author 杨帅
 * @Date 2022/5/22 17:50
 * @Version 1.0
 **/
@Slf4j
public class TestCyclicBarrier {
    public static void main(String[] args) {
        test2 ();

    }

    private static void test2() {
        ExecutorService service = Executors.newFixedThreadPool(2);
        CyclicBarrier barrier = new CyclicBarrier(2,()->{
            log.debug("task1 task2 finish...");
        });
        for (int i = 0; i < 3; i++) {
            service.submit(()->{
                log.debug("task1 begin...");
                ThreadUtil.sleep(1000);
                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });

            service.submit(()->{
                log.debug("task2 begin...");
                ThreadUtil.sleep(2000);
                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
        service.shutdown();
    }

    private static void test1() {
        ExecutorService service = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 3; i++) {
            CountDownLatch latch = new CountDownLatch(2);
            service.submit(()->{
                log.debug("task1 start...");
                ThreadUtil.sleep(1000);
                latch.countDown();
            });
            service.submit(()->{
                log.debug("task2 start...");
                ThreadUtil.sleep(2000);
                latch.countDown();
            });

            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("task1 task2 finish...");
        }
        service.shutdown();
    }
}
