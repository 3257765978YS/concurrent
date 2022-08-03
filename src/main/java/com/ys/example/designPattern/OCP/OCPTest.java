package com.ys.example.designPattern.OCP;

/**
 * @Description 开闭原则的含义是：
 * 当应用的需求改变时，在不修改软件实体的源代码或者二进制代码的前提下，
 * 可以扩展模块的功能，使其满足新的需求。
 * 开闭原则的作用
 * @Author 杨帅
 * @Date 2022/6/26 16:23
 * @Version 1.0
 **/

/*分析：Windows 的主题是桌面背景图片、窗口颜色和声音等元素的组合。
        用户可以根据自己的喜爱更换自己的桌面主题，也可以从网上下载新的主题。
        这些主题有共同的特点，可以为其定义一个抽象类（Abstract Subject），
        而每个具体的主题（Specific Subject）是其子类。
        用户窗体可以根据需要选择或者增加新的主题，而不需要修改原代码，
        所以它是满足开闭原则的*/
public class OCPTest {
}
