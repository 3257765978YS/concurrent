package com.ys.example.designPattern.LSP;

/**
 * @Description 里氏替换原则（Liskov Substitution Principle，LSP）
 * @Author 杨帅
 * @Date 2022/6/26 16:20
 * @Version 1.0
 **/
/*里氏替换原则的作用
        里氏替换原则的主要作用如下。
        里氏替换原则是实现开闭原则的重要方式之一。
        它克服了继承中重写父类造成的可复用性变差的缺点。
        它是动作正确性的保证。即类的扩展不会给已有的系统引入新的错误，降低了代码出错的可能性。
        加强程序的健壮性，同时变更时可以做到非常好的兼容性，提高程序的维护性、可扩展性，降低需求变更时引入的风险。
        里氏替换原则的实现方法
        里氏替换原则通俗来讲就是：子类可以扩展父类的功能，但不能改变父类原有的功能。也就是说：子类继承父类时，除添加新的方法完成新增功能外，尽量不要重写父类的方法。

        根据上述理解，对里氏替换原则的定义可以总结如下：
        子类可以实现父类的抽象方法，但不能覆盖父类的非抽象方法
        子类中可以增加自己特有的方法
        当子类的方法重载父类的方法时，方法的前置条件（即方法的输入参数）要比父类的方法更宽松
        当子类的方法实现父类的方法时（重写/重载或实现抽象方法），方法的后置条件（即方法的的输出/返回值）要比父类的方法更严格或相等*/
public class LSPtest {
    public static void main(String[] args) {
        Bird bird1 = new Swallow();
        Bird bird2 = new BrownKiwi();
        bird1.setSpeed(120);
        bird2.setSpeed(120);
        System.out.println("如果飞行300公里：");
        try {
            System.out.println("燕子将飞行" + bird1.getFlyTime(300) + "小时.");
            System.out.println("几维鸟将飞行" + bird2.getFlyTime(300) + "小时。");
        } catch (Exception err) {
            System.out.println("发生错误了!");
        }
    }
}
//鸟类
class Bird {
    double flySpeed;
    public void setSpeed(double speed) {
        flySpeed = speed;
    }
    public double getFlyTime(double distance) {
        return (distance / flySpeed);
    }
}
//燕子类
class Swallow extends Bird {
}
//几维鸟类
class BrownKiwi extends Bird {
    @Override
    public void setSpeed(double speed) {
        flySpeed = 0;
    }
}
