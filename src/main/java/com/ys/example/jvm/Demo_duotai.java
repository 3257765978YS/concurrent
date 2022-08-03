package com.ys.example.jvm;

import java.io.IOException;

/**
 * @Description
 * @Author 杨帅
 * @Date 2022/6/8 22:07
 * @Version 1.0
 **/
public class Demo_duotai {
    public static void test(Animal animal){
        animal.eat();
        System.out.println(animal.toString());
    }

    public static void main(String[] args) throws IOException {
        test(new Cat());
        test(new Dog());
        System.in.read();
    }

}
abstract class Animal{

    public abstract void eat();

    @Override
    public String toString() {
        return "我是"+this.getClass().getSimpleName();
    }
}
class Cat extends Animal{
    @Override
    public void eat() {
        System.out.println("吃鱼");
    }
}

class Dog extends Animal{
    @Override
    public void eat() {
        System.out.println("啃骨头");
    }
}

