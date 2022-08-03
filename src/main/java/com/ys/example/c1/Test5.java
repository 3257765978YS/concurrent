package com.ys.example.c1;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @Description  字段更新器
 * @Author 杨帅
 * @Date 2022/2/20 20:03
 * @Version 1.0
 **/

@Slf4j
public class Test5 {
    public static void main(String[] args) {
        Student stu = new Student();
        AtomicReferenceFieldUpdater updater =
                AtomicReferenceFieldUpdater.newUpdater(Student.class,String.class,"name");

        System.out.println(updater.compareAndSet(stu, null, "张三"));
        System.out.println(stu);
    }
}
class Student{
    volatile String name;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }
}
