package com.ys.example.designPattern.FactoryPattern;

/**
 * @Description 简单工厂
 * @Author 杨帅
 * @Date 2022/6/27 22:26
 * @Version 1.0
 **/
/*
        简单工厂模式的主要角色如下：
        简单工厂（SimpleFactory）：是简单工厂模式的核心，负责实现创建所有实例的内部逻辑。工厂类的创建产品类的方法可以被外界直接调用，创建所需的产品对象。
        抽象产品（Product）：是简单工厂创建的所有对象的父类，负责描述所有实例共有的公共接口。
        具体产品（ConcreteProduct）：是简单工厂模式的创建目标。
*/
public class Client {
    public static void main(String[] args) {
        Product productA = SimpleFactory.makeProduct(0);
        Product productB = SimpleFactory.makeProduct(1);
        Product productC = SimpleFactory.makeProduct(2);
        productA.show();
        productB.show();
        productC.show();

    }
    //抽象产品
    public interface Product {
        void show();
    }
    //具体产品：ProductA
    static class ConcreteProduct1 implements Product {
        @Override
        public void show() {
            System.out.println("具体产品1显示...");
        }
    }
    //具体产品：ProductB
    static class ConcreteProduct2 implements Product {
        @Override
        public void show() {
            System.out.println("具体产品2显示...");
        }
    }
    final class Const {
        static final int PRODUCT_A = 0;
        static final int PRODUCT_B = 1;
        static final int PRODUCT_C = 2;
    }
    static class SimpleFactory {
        public static Product makeProduct(int kind) {
            switch (kind) {
                case Const.PRODUCT_A:
                    return new ConcreteProduct1();
                case Const.PRODUCT_B:
                    return new ConcreteProduct2();
            }
            return null;
        }
    }
}