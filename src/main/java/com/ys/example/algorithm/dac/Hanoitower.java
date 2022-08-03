package com.ys.example.algorithm.dac;

/**
 * @Description 分治算法——实现汉诺塔
 * @Author 杨帅
 * @Date 2022/6/17 21:10
 * @Version 1.0
 **/
public class Hanoitower {
    public static void main(String[] args) {
        hanoiTower(2,'A','B','C');
    }

    public static void hanoiTower(int num,char a,char b,char c){
        //如果只有一个盘
        if(num == 1){
            System.out.println("第1个盘从"+a+"->"+c);
        }else{
            //如果我们有n >=2情况，我们总可以看作是两个盘1 ：最下面的盘  2：剩余的盘
            //1）先把剩下的盘从A->B，移动过程中会使用C
            hanoiTower(num-1,a,c,b);
            //2）接着将最下面的盘从A->C
            System.out.println("第"+num+"个盘从"+a+"->"+c);
            //3）最后我们将B塔中剩下的盘从B->C
            hanoiTower(num-1,b,a,c);
        }
    }
}
