package com.ys.example.c5;

import com.ys.example.util.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.StampedLock;

/**
 * @Description
 * @Author 杨帅
 * @Date 2022/5/21 23:05
 * @Version 1.0
 **/
@Slf4j(topic = "c.TestStampedLock")
public class TestStampedLock {

    public static void main(String[] args) {
        DataContainerStamped dataContainer = new DataContainerStamped(1);
        new Thread(()->{
            dataContainer.read(1);
        },"t1").start();

        ThreadUtil.sleep(500);

        new Thread(()->{
            dataContainer.write(1000);
        },"t2").start();
    }
}

@Slf4j(topic = "c.DataContainerStamped")
class DataContainerStamped{
    private int data;
    private final StampedLock lock = new StampedLock();

    public DataContainerStamped(int data) {
        this.data = data;
    }

    public int read(int readTime){
        long stamp = lock.tryOptimisticRead();
        log.debug("optimistic read locking...{}",stamp);
        ThreadUtil.sleep(readTime*1000);
        if(lock.validate(stamp)){
            log.debug("read finish...{}",stamp);
            return data;
        }
        //锁升级 - 读锁
        log.debug("updating to read lock...{}",stamp);
        try {
            stamp = lock.readLock();
            log.debug("read lock {}",stamp);
            ThreadUtil.sleep(readTime*1000);
            log.debug("read finish... {}",stamp);
            return data;
        }finally {
            log.debug("read unlock{}",stamp);
            lock.unlockRead(stamp);
        }
    }

    public void write(int newData){
        long stamp = lock.writeLock();
        log.debug("write lock {}",stamp);
        try{
            ThreadUtil.sleep(2000);
            this.data = newData;
        }finally {
            log.debug("write unlock {}",stamp);
            lock.unlockWrite(stamp);
        }

    }
}
