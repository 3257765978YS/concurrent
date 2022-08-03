package com.ys.example.leetCode;

/**
 * @Description 二维数组中的查找
 * @Author 杨帅
 * @Date 2022/6/23 14:46
 * @Version 1.0
 **/
public class p2 {
    public static void main(String[] args) {
/*
        描述
        在一个二维数组array中（每个一维数组的长度相同），
        每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
        请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
        [
        [1,2,8,9],
        [2,4,9,12],
        [4,7,10,13],
        [6,8,11,15]
        ]
        给定 target = 7，返回 true。
        给定 target = 3，返回 false。
        数据范围：矩阵的长宽满足 0 \le n,m \le 5000≤n,m≤500 ， 矩阵中的值满足 0 \le val \le 10^90≤val≤10
        9
        进阶：空间复杂度 O(1)O(1) ，时间复杂度 O(n+m)O(n+m)
        */

        System.out.println(Find(1, new int[][]{{2}}));
    }

    public static boolean Find(int target, int[][] array) {

        if (array.length == 0) return false;
        int n = array.length - 1;

        if (array[0].length == 0) return false;
        int m = array[0].length - 1;
        int i = m;
        while (n >= 0 && i >= 0) {
            if (target == array[n][m - i]) {
                return true;
            } else if (target < array[n][m - i]) {
                n -= 1;
            } else {
                i -= 1;
            }
        }
        return false;
    }
}
