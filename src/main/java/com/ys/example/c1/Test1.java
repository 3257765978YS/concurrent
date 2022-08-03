package com.ys.example.c1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntUnaryOperator;

/**
 * @Description
 * @Author 杨帅
 * @Date 2022/2/20 15:44
 * @Version 1.0
 **/
public class Test1 {
    public static void main(String[] args) {
        AtomicInteger i = new AtomicInteger(10);

        /*System.out.println(i.incrementAndGet());
        System.out.println(i.getAndIncrement());


        System.out.println(i.getAndAdd(5));
        System.out.println(i.addAndGet(5));*/

//        i.updateAndGet( x -> x*10);
        updateAndGet(i,x -> x / 2);

        System.out.println(i.get());
    }

    public static void updateAndGet(AtomicInteger i, IntUnaryOperator operator){
        while(true){
            int prev = i.get();
            int next = operator.applyAsInt(prev);
            if(i.compareAndSet(prev,next)){
                break;
            }
        }
    }
}


