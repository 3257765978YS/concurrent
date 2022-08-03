package com.ys.example.c5;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Description
 * @Author 杨帅
 * @Date 2022/5/19 15:58
 * @Version 1.0
 **/
@Slf4j
public class TestAqs {
    public static void main(String[] args) {
        MyLock lock = new MyLock();
        new Thread(()->{
            lock.lock();
            log.debug("locking...");
            lock.lock();
            log.debug("locking...");
            //不可重入，导致阻塞
            try{
                log.debug("locking...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }finally {
                log.debug("unlocking...");
                lock.unlock();
            }

        },"t1").start();

        /*new Thread(()->{
            lock.lock();
            try{
                log.debug("locking...");
            }finally {
                log.debug("unlocking...");
                lock.unlock();
            }

        },"t2").start();*/

    }
}

//自定义锁（不可重入锁）
class MyLock implements Lock{

    //独占锁
    //同步器类
    class MySync extends AbstractQueuedSynchronizer{

        @Override
        protected boolean tryAcquire(int arg) {
            if(compareAndSetState(0,1)){
                //加上了锁，并设置了owner为当前线程
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }


        @Override
        protected boolean tryRelease(int arg) {
            setState(0);
            setExclusiveOwnerThread(null);
            return true;
        }


        //是否持有独占锁
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        public Condition newCondition(){
            return new ConditionObject();
        }
    }

    private MySync sync = new MySync();


    //加锁 （不成功则进入等待队列）
    @Override
    public void lock() {
        sync.acquire(1);
    }

    //加锁 （可打断）
    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    //尝试加锁 （一次）
    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    //尝试加锁，带超时时间
    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1,unit.toNanos(time));
    }

    //解锁
    @Override
    public void unlock() {
        sync.release(1);
    }

    //创建条件变量
    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
}
