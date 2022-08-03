package com.ys.example.designPattern.ISP;

/**
 * @Description 接口隔离原则（Interface Segregation Principle，ISP）
 * @Author 杨帅
 * @Date 2022/6/26 15:59
 * @Version 1.0
 **/
public class ISPtest {
    public static void main(String[] args) {
        /*InputModule input = StuScoreList.getInputModule();
        CountModule count = StuScoreList.getCountModule();
        PrintModule print = StuScoreList.getPrintModule();
        input.insert();
        count.countAverage();
        print.printStuInfo();*/

        //以下代码只有在默认构造器为public时才编译通过
        /*StuScoreList stuScoreList = new StuScoreList();
        stuScoreList.insert();
        stuScoreList.countAverage();
        stuScoreList.printStuInfo();*/

    }
}

interface InputModule {
    void insert();
    void delete();
    void modify();
}
//统计模块接口
interface CountModule {
    void countTotalScore();
    void countAverage();
}
//打印模块接口
interface PrintModule {
    void printStuInfo();
    void queryStuInfo();
}
//实现类
class StuScoreList implements InputModule, CountModule, PrintModule {
    private StuScoreList() {
    }
    //类似 List list = new ArrayList<>; 返回的List list为接口类型，ArrayList实现了List接口
    public static InputModule getInputModule() {
        return (InputModule) new StuScoreList();
    }
    public static CountModule getCountModule() {
        return (CountModule) new StuScoreList();
    }
    public static PrintModule getPrintModule() {
        return (PrintModule) new StuScoreList();
    }
    @Override
    public void insert() {
        System.out.println("输入模块的insert()方法被调用！");
    }
    @Override
    public void delete() {
        System.out.println("输入模块的delete()方法被调用！");
    }
    @Override
    public void modify() {
        System.out.println("输入模块的modify()方法被调用！");
    }
    @Override
    public void countTotalScore() {
        System.out.println("统计模块的countTotalScore()方法被调用！");
    }
    @Override
    public void countAverage() {
        System.out.println("统计模块的countAverage()方法被调用！");
    }
    @Override
    public void printStuInfo() {
        System.out.println("打印模块的printStuInfo()方法被调用！");
    }
    @Override
    public void queryStuInfo() {
        System.out.println("打印模块的queryStuInfo()方法被调用！");
    }
}
