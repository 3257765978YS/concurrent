package com.ys.example.c1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @Description 原子累加器 -> Java8后专门拿出来做累加的类，虽然之前的AtomicInteger、AtomicLong也能做累加
 * @Author 杨帅
 * @Date 2022/2/20 20:09
 * @Version 1.0
 **/
public class Test6 {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            demo(
                    ()->new AtomicLong(0),
                    (adder)->adder.getAndIncrement()
            );


            demo(
                    ()->new LongAdder(),
                    (adder)->adder.increment()
            );
        }

    }

    /**
     * ()->结果 提供累加器对象
     * (参数)-> 执行累加操作
     * */
    private static <T> void demo(Supplier<T> adderSupplier, Consumer<T>action){
        T adder = adderSupplier.get();
        List<Thread> ts = new ArrayList<>();
        //4个线程，每人累加50万
        for (int i = 0; i < 4; i++) {
            ts.add(new Thread(()->{
                for (int j = 0; j < 500000; j++) {
                    action.accept(adder);
                }
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

        System.out.println(adder + "，cost:" + (end - start) / 1000_000 + "ms");
    }
}
