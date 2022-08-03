package com.ys.example.algorithm.kmp;

/**
 * @Description
 * @Author 杨帅
 * @Date 2022/6/18 14:12
 * @Version 1.0
 **/
public class ViolenceMatch {
    public static void main(String[] args) {
        String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String str2 = "尚硅谷你尚硅你!";
        System.out.println(violenceMatch(str1, str2));
    }

    //暴力匹配方法
    public static int violenceMatch(String str1,String str2){
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int s1Len = s1.length;
        int s2Len = s2.length;

        int i = 0; //i指向s1
        int j = 0; //j指向s2

        while(i<s1Len && j<s2Len){
            if(s1[i] == s2[j]){
                i++;
                j++;
            }else{
                i = i-(j-1);
                j = 0;
            }
        }

        if(j == s2Len){
            return i-j;
        }else{
            return -1;
        }
    }
}
