package com.ys.example.c1;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Description
 * @Author 杨帅
 * @Date 2022/2/20 16:12
 * @Version 1.0
 **/
@Slf4j
public class Test3 {

    static AtomicReference<String> ref = new AtomicReference<>("A");

    public static void main(String[] args) throws InterruptedException {
        log.debug("main start...");
        String prev = ref.get();
        other();
        Thread.sleep(1000);
        log.debug("change A->C {}",ref.compareAndSet(prev,"C"));
    }

    private static void other() {
    }


}
