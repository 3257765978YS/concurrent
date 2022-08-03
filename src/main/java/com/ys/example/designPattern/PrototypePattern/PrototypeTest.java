package com.ys.example.designPattern.PrototypePattern;

/**
 * @Description  java 中的clone()本质是浅克隆
 * @Author 杨帅
 * @Date 2022/6/27 12:33
 * @Version 1.0
 **/
class Realizetype implements Cloneable{
    Realizetype(){
        System.out.println("具体原型创建成功！");
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        System.out.println("具体原型复制成功！");
        return (Realizetype) super.clone();
    }
}

//原型模式的测试类
public class PrototypeTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        Realizetype obj1 = new Realizetype();
        Realizetype obj2 = (Realizetype) obj1.clone();
        System.out.println(obj1);
        System.out.println(obj2);
        System.out.println("obj1==obj2?" + (obj1 == obj2));
    }
}
