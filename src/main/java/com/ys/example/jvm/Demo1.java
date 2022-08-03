package com.ys.example.jvm;

import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * @Description 演示元空间内存溢出
 * -XX:MaxMetaspaceSize=8m
 * @Author 杨帅
 * @Date 2022/6/4 14:29
 * @Version 1.0
 **/
public class Demo1 extends ClassLoader{ //可以用来加载类的二进制字节码
    public static void main(String[] args) {
        int j = 0;
        try {
            Demo1 test = new Demo1();
            for (int i = 0; i < 10000; i++,j++) {
                //ClassWriter作用是生产类的二进制字节码
                ClassWriter cw = new ClassWriter(0);
                //版本号，public，类名包名，父类，接口
                cw.visit(Opcodes.V1_8,Opcodes.ACC_PUBLIC,"Class"+i,null,"java/lang/Object",null);
                //返回byte[]
                byte[] code = cw.toByteArray();
                //执行类的加载
                test.defineClass("Class"+i,code,0,code.length);
            }
        } finally {
            System.out.println(j);
        }
    }
}
