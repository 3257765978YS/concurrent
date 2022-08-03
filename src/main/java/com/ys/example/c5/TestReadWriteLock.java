package com.ys.example.c5;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description
 * @Author 杨帅
 * @Date 2022/5/21 15:36
 * @Version 1.0
 **/
@Slf4j
public class TestReadWriteLock {
    public static void main(String[] args) {

        DataContainer dataContainer = new DataContainer();
        new Thread(()->{
            dataContainer.read();
        },"t1").start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            dataContainer.write();
        },"t2").start();
    }
}

@Slf4j
class DataContainer{
    private Object data;

    private ReentrantReadWriteLock rw = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.ReadLock r = rw.readLock();
    private ReentrantReadWriteLock.WriteLock w = rw.writeLock();

    public Object read(){
        log.debug("获取读锁...");
        r.lock();
        try {
            log.debug("读取");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return data;
        } finally {
            log.debug("释放读锁...");
            r.unlock();
        }
    }

    public void write(){
        log.debug("获取写锁...");
        w.lock();
        try {
            log.debug("写入");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            log.debug("释放写锁...");
            w.unlock();
        }
    }


}
