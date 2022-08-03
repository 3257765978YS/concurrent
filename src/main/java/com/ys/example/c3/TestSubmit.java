package com.ys.example.c3;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Description
 * @Author 杨帅
 * @Date 2022/5/16 18:59
 * @Version 1.0
 **/
@Slf4j
public class TestSubmit {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(1);


        String result = pool.invokeAny(Arrays.asList(
                () -> {
                    log.debug("begin -1");
                    Thread.sleep(1000);
                    log.debug("end -1");
                    return "1";
                },
                () -> {
                    log.debug("begin -2");
                    Thread.sleep(500);
                    log.debug("end -2");
                    return "2";
                },
                () -> {
                    log.debug("begin -3");
                    Thread.sleep(2000);
                    log.debug("end -3");
                    return "3";
                }
        ));

        log.debug("{}",result);
    }

    private static void method1(ExecutorService pool) throws InterruptedException, ExecutionException {
        Future<String> future = pool.submit(()->{
                log.debug("running...");
                Thread.sleep(1000);
                return "ok";

        });

        log.debug("{}",future.get());
    }

    private static void method2(ExecutorService pool) throws InterruptedException {
        List<Future<Object>> futures = pool.invokeAll(Arrays.asList(
                () -> {
                    log.debug("begin");
                    Thread.sleep(1000);
                    return "1";
                },
                () -> {
                    log.debug("begin");
                    Thread.sleep(500);
                    return "2";
                },
                () -> {
                    log.debug("begin");
                    Thread.sleep(2000);
                    return "3";
                }
        ));

        futures.forEach( f ->{
            try{
                log.debug("{}",f.get());
            }catch (InterruptedException | ExecutionException e){
                e.printStackTrace();
            }
        });
    }
}
