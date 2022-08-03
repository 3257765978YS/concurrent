package com.ys.example;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @Description
 * @Author 杨帅
 * @Date 2022/2/21 21:19
 * @Version 1.0
 **/
public class UnsafeAccessor {
    private static final Unsafe unsafe;

    static{
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            //设置允许访问私有成员变量
            theUnsafe.setAccessible(true);
            unsafe = (Unsafe) theUnsafe.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw  new Error(e);
        }
    }

    public static Unsafe getUnsafe(){
        return unsafe;
    }
}
