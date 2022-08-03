package com.ys.example.c1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description  保证取款操作的线程安全
 * @Author 杨帅
 * @Date 2022/2/19 16:07
 * @Version 1.0
 **/
public class TestAccount {
    public static void main(String[] args) {
        Account accountUnSafe = new AccountUnSafe(10000);
        Account.demo(accountUnSafe);

        Account accountCas = new AccountCas(10000);
        Account.demo(accountCas);
    }
}

class AccountUnSafe implements Account{

    private Integer balance;

    public AccountUnSafe(Integer balance) {
        this.balance = balance;
    }

    @Override
    public Integer getBalance() {
        synchronized (this){
            return this.balance;
        }
    }

    @Override
    public void withdraw(Integer amount) {
        synchronized (this){
            this.balance -= amount;
        }
    }
}

class AccountCas implements Account{

    //采用AtomicInteger 类 （保证原子性）
    private AtomicInteger balance;

    public AccountCas(int balance) {
        this.balance = new AtomicInteger(balance);
    }

    @Override
    public Integer getBalance() {
        return balance.get();
    }

    @Override
    public void withdraw(Integer amount) {
        /*while(true){
            //获取余额的最新值
            int prev = balance.get();
            //要修改的余额
            int next = prev - amount;
            //真正修改
            if(balance.compareAndSet(prev,next)){
                break;
            }
        }*/
        balance.getAndAdd(-1*amount);
    }
}

interface Account{
    Integer getBalance();

    void withdraw(Integer amount);

    static void demo(Account account){
        List<Thread> ts = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            ts.add(new Thread(()->{
                account.withdraw(10);
            }));
        }
        long start = System.nanoTime();
        ts.forEach(Thread::start);
        ts.forEach(t->{
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        long end = System.nanoTime();
        System.out.println("余额："+account.getBalance() + "，cost：" + (end-start)/1000_000 +"ms");

    }
    
}
