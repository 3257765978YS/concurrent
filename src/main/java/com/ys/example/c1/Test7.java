package com.ys.example.c1;

import javafx.beans.NamedArg;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @Description  Unsafe类  ->Unsafe CAS操作
 * @Author 杨帅
 * @Date 2022/2/21 20:56
 * @Version 1.0
 **/

public class Test7 {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        //设置允许访问私有成员变量
        theUnsafe.setAccessible(true);
        Unsafe unsafe = (Unsafe) theUnsafe.get(null);

        System.out.println(unsafe);

        //1、获取域的偏移地址
        long idOffset = unsafe.objectFieldOffset(Teacher.class.getDeclaredField("id"));
        long nameOffset = unsafe.objectFieldOffset(Teacher.class.getDeclaredField("name"));

        Teacher teacher = new Teacher();
        //2、执行cas操作
        unsafe.compareAndSwapInt(teacher,idOffset,0,1);
        unsafe.compareAndSwapObject(teacher,nameOffset,null,"张三");

        System.out.println(teacher);

    }
}

@Data
class Teacher{
    volatile int id;
    volatile String name;
}