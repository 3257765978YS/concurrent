package com.ys.example.c1;

/**
 * @Description  Balking模式练习
 * @Author 杨帅
 * @Date 2022/2/19 15:30
 * @Version 1.0
 **/

/*
 *希望doInit()方法仅执行一次，下面的代
 *码有何问题，为什么？如何修改？
 *
 * 问题：既有读操作、又有写操作
 * 可保证有序性、可见性，但不能保证原子性。多个线程访问会发生指令交错
 * 解决方法：添加synchronized 同步块
 */


public class TestVolatile {

    volatile boolean initialized = false;

    void init(){
        if(initialized){
            return;
        }
        doInit();
        initialized = true;
    }

    private void doInit() {
    }
}
