package com.ys.example.algorithm;

/**
 * @Description 二分算法
 * @Author 杨帅
 * @Date 2022/6/17 14:11
 * @Version 1.0
 **/
public class SearchTest {
    public static void main(String[] args) {
        int[] arr = {1,2,3,5,6,8,9,12,20,100};
        System.out.println(binarySearch(arr, 8));
        System.out.println(binarySearch_rec(arr, 0,arr.length,8));
    }

    /**
     * @Description: while循环实现 二分查找
     * @Author: 杨帅
     * @Date: 2022/6/17
     * @param arr
     * @param target
     * @return: int
     */
     private static int binarySearch(int[] arr,int target){
        int left = 0;
        int right = arr.length-1;
        int mid;

        while(left<=right){
            mid = (left+right)/2;
            if(target==arr[mid]){
                return mid;
            }else if(target > arr[mid]){
                left = mid+1;
            }else{
                right = mid-1;
            }
        }
        return -1;
    }

    /**
     * @Description:  递归实现 二分查找
     * @Author: 杨帅
     * @Date: 2022/6/17
     * @param arr
     * @param left
     * @param right
     * @param target
     * @return: int
     */
    private static int binarySearch_rec(int[] arr,int left,int right,int target){
        int mid;

        if(left <= right){
            mid = (left+right)/2;
            if(target == arr[mid]){
                return mid;
            }else if(target > arr[mid]){
                binarySearch_rec(arr,mid+1,left,target);
            }else{
                binarySearch_rec(arr,left,mid-1,target);
            }
        }

        return -1;
    }
}
