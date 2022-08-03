package com.ys.example.c1;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Description  AtomicReference 原子引用
 * @Author 杨帅
 * @Date 2022/2/20 15:59
 * @Version 1.0
 **/
public class Test2 {
    public static void main(String[] args) {
        DecimalAccountCas account = new DecimalAccountCas(new BigDecimal("10000"));
        DecimalAccount.demo(account);
    }
}

class DecimalAccountCas implements DecimalAccount{

    private AtomicReference<BigDecimal> balance;

    public DecimalAccountCas(BigDecimal balance) {
        this.balance = new AtomicReference<>(balance);
    }

    @Override
    public BigDecimal getBalance() {
        return balance.get();
    }

    @Override
    public void withdraw(BigDecimal amount) {
        while (true){
            BigDecimal prev = balance.get();
            BigDecimal next = prev.subtract(amount);
            if(balance.compareAndSet(prev,next)){
                break;
            }
        }
    }
}

interface DecimalAccount{

    BigDecimal getBalance();

    void withdraw(BigDecimal amount);

    static void demo(DecimalAccount account){
        List<Thread> ts = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            ts.add(new Thread(()->{
                account.withdraw(BigDecimal.TEN);
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