package com.ys.example.c5;

import com.ys.example.util.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description
 * @Author 杨帅
 * @Date 2022/5/22 16:46
 * @Version 1.0
 **/
@Slf4j
public class TestCountDownLacth2 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        String[] all = new String[10];
        CountDownLatch latch = new CountDownLatch(10);

        Random r = new Random();
        for (int j = 0; j < 10; j++) {
            int k = j;
            service.submit(()->{
                for (int i = 0; i <= 100; i++) {
                    ThreadUtil.sleep(r.nextInt(100));
                    all[k] = i + "%";
                    // "\r"可以使后一次打印覆盖掉前一次打印
                    System.out.print("\r"+Arrays.toString(all));
                }
                latch.countDown();
            });
        }
        latch.await();
        System.out.println("\n游戏开始");
        service.shutdown();
    }
}
