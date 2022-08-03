package com.ys.example.designPattern.PrototypePattern;

/**
 * @Description 用原型模式除了可以生成相同的对象，还可以生成相似的对象
 * @Author 杨帅
 * @Date 2022/6/27 18:05
 * @Version 1.0
 **/
public class ProtoTypeCitation {
    public static void main(String[] args) throws CloneNotSupportedException {
        citation obj1 = new citation("张三", 12,"同学：在2016学年第一学期中表现优秀，被评为三好学生。", "韶关学院");
        obj1.display();
        citation obj2 = (citation) obj1.clone();
        obj2.setName("李四");
        obj2.setAge(20);
        obj2.display();
        obj1.display();
        System.out.println("head == head1 : " + (obj1.head == obj2.head) );
        System.out.println("obj1 == obj2 : " + (obj1== obj2 ));
        System.out.println("info1 == info2 : " + (obj1.info== obj2.info ));
        System.out.println("age1 == age2 : " + (obj1.age== obj2.age ));
    }
}
//奖状类
class citation implements Cloneable {
    public Head head;
    String name;
    int age;
    String info;
    String college;
    static class Head {
    }

    citation(String name, int age,String info, String college) {
        this.name = name;
        this.age = age;
        this.info = info;
        this.college = college;
        System.out.println("奖状创建成功！");
    }

    public int getAge() {
        return age;
    }

    void setAge(int age){
        this.age = age;
    }

    void setName(String name) {
        this.name = name;
    }
    String getName() {
        return (this.name);
    }
    void display() {
        System.out.println(name + age + info + college);
    }
    @Override
    public Object clone() throws CloneNotSupportedException {
        System.out.println("奖状拷贝成功！");
        return (citation) super.clone();
    }
}
