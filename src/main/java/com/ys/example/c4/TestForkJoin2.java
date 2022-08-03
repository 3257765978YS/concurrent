package com.ys.example.c4;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @Description
 * @Author 杨帅
 * @Date 2022/5/18 8:28
 * @Version 1.0
 **/
@Slf4j
public class TestForkJoin2 {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool(4);
//        System.out.println(pool.invoke(new MyTask(5)));

        System.out.println(pool.invoke(new AddTask3(1, 10)));

        //new MyTask(5) -> 5+ myTask(4) -> 4 + myTask(3) -> 3 + myTask(2) ...
    }
}
//1~n之间整数和
@Slf4j
class MyTask extends RecursiveTask<Integer>{
    private int n;

    public MyTask(int n) {
        this.n = n;
    }

    @Override
    public String toString() {
        return "{" + n + "}";
    }

    @Override
    protected Integer compute() {
        //终止条件
        if(n ==1){
            log.debug("join {}",n);
            return n;
        }

        //将任务进行拆分(fork)
        MyTask t1 = new MyTask(n - 1);
        t1.fork();//让一个线程去执行此任务
        log.debug("fork() {} + {}",n,t1);

        //合并(join)结果
        int result = n + t1.join();//获取任务结果
        log.debug("join() {} + {} = {}",n,t1,result);
        return result;
    }
}

@Slf4j
/**
 * @Description:  改进后的 斐波那契数列求和 ->  任务拆分
 * @Author: 杨帅
 * @Date: 2022/5/18
 * @param null
 * @return: null
 */
class AddTask3 extends RecursiveTask<Integer>{
    int begin;  //1
    int end; //5

    public AddTask3(int begin,int end){
        this.begin = begin;
        this.end = end;
    }

    @Override
    public String toString() {
        return "{" + begin + "," + end + "}";
    }

    @Override
    protected Integer compute() {
        if(begin == end){
            log.debug("join() {}",begin);
            return begin;
        }

        if(end - begin ==1){
            log.debug("join() {} + {} = {}",begin,end,end + begin);
            return end + begin;
        }
        int mid = (end + begin) / 2;
        AddTask3 t1 = new AddTask3(begin,mid);
        t1.fork();
        AddTask3 t2 = new AddTask3(mid+1,end);
        t2.fork();
        log.debug("fork(){} + {} = ?",t1,t2);

        int result = t1.join() + t2.join();
        log.debug("join(){} + {} = {}",t1,t2,result);
        return result;
    }
}
