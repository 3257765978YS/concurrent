package com.ys.example.c1;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description  CAS 锁 （不要用于实践）
 * @Author 杨帅
 * @Date 2022/2/20 20:28
 * @Version 1.0
 **/
@Slf4j
public class LockCas {
    //0没加锁
    //1 加锁

    private AtomicInteger state = new AtomicInteger(0);

    public void lock(){
        while(true){
            if(state.compareAndSet(0,1)){
                break;
            }
        }

    }

    public void unlock(){
        log.debug("unlock...");
        state.set(0);
    }


    public static void main(String[] args) {
        LockCas lock = new LockCas();
        new Thread(()->{
            log.debug("begin...");
            lock.lock();
            try {
                log.debug("lock...");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        },"t1").start();

        new Thread(()->{
            log.debug("begin...");
            lock.lock();
            try {
                log.debug("lock...");
            } finally {
                lock.unlock();
            }
        },"t2").start();
    }
}
