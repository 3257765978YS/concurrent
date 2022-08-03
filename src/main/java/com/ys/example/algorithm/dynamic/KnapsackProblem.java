package com.ys.example.algorithm.dynamic;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @Description 0 1 背包问题 （动态规划算法）
 * @Author 杨帅
 * @Date 2022/6/17 22:54
 * @Version 1.0
 **/
public class KnapsackProblem {
    public static void main(String[] args) {
        int[] w = {1, 4, 3};
        int[] val = {1500, 3000, 2000};

        int m = 4;//背包容量
        int n = val.length;//物品个数

        //为了记录放入商品的情况，我们定义一个二维数组
        int[][] path = new int[n + 1][m + 1];

        //创建二维数组
        int[][] v = new int[n + 1][m + 1];
        Arrays.fill(v[0], 0);
        IntStream.range(0, v.length).forEach(i -> v[i][0] = 0);

        //根据前面得到的公式来动态规划处理
        for (int i = 1; i < v.length; i++) { //不处理第一行，从第一行开始
            for (int j = 1; j < v[0].length; j++) {
                //公式
                if (w[i - 1] > j) { //因为实际情况是i,j是从1开始，故公式调整为w[i]改为w[i-1]
                    v[i][j] = v[i - 1][j];
                } else {
//                    v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]]);
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }


        for (int[] ints : v) {
            System.out.println(Arrays.toString(ints));
        }

        System.out.println("================================");
        //这样会得到所有的放入情况，其实我们只需要最后的放入
        /*for (int i = 0; i < path.length; i++) {
            for (int j = 0; j < path[0].length; j++) {
                if(path[i][j] == 1){
                    System.out.printf("第%d个商品放入背包\n",i);
                }
            }
        }*/
        int i = path.length - 1; //表示行的最大下标
        int j = path[0].length - 1; //表示列的最大下标
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                System.out.printf("第%d个商品放入背包\n", i);
                j -= w[i-1];
            }
            i--;
        }

    }
}
