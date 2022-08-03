package com.ys.example.algorithm.kmp;

import java.util.Arrays;

/**
 * @Description
 * @Author 杨帅
 * @Date 2022/6/18 21:52
 * @Version 1.0
 **/
public class KMPAlgorithm {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";

//        String str2 = "BBC";

        int[] next = kmpNext(str2); //[0,1,2,0]
        System.out.println(Arrays.toString(next));

        System.out.println(kmpSearch(str1, str2, next));
    }


    /**
     * @Description: kmp搜索算法
     * @Author: 杨帅
     * @Date: 2022/6/18
     * @param str1 源字符串
     * @param str2 子串
     * @param next  部分匹配表，是子串对应的部分匹配表
     * @return: int -1表示不匹配，否则返回第一个匹配的位置
     */
    public static int kmpSearch(String str1,String str2,int[] next){
        //遍历
        for (int i = 0,j = 0; i < str1.length(); i++) {
            //考量str1.charAt(i) != str2.charAt(j)的情况
            //是kmp算法的另一核心
            while(j>0 && str1.charAt(i) != str2.charAt(j)){
                j = next[j-1];
            }
            if(str1.charAt(i) == str2.charAt(j)){
                j++;
            }
            if(j == str2.length()){//找到了
                return i-j+1;
            }
        }
        return -1;
    }

    //获取到一个字符串（字串）的部分匹配值
    public static int[] kmpNext(String dest){
        //创建一个next数组保存部分匹配值
        int[] next = new int[dest.length()];
        next[0] = 0;//如果字符串长度为1 部分匹配值就是0
        for (int i = 1,j = 0; i < dest.length(); i++) {
            //当dest.charAt(i) != dest.charAt(j)，我们需要从next[j-1]获取新的j
            //直到我们发现有dest.charAt(i) == dest.charAt(j)成立 才退出
            //kmp算法的核心
            while (j>0 && dest.charAt(i) != dest.charAt(j)){
                j = next[j-1];
            }

            //当dest.charAt(i) == dest.charAt(j)满足时，部分匹配值就是+1
            if(dest.charAt(i) == dest.charAt(j)){
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
