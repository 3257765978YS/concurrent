package com.ys.example.leetCode;

/**
 * @Description 寻找峰值
 * @Author 杨帅
 * @Date 2022/6/23 15:16
 * @Version 1.0
 **/
public class p3 {
    /*  给定一个长度为n的数组nums，请你找到峰值并返回其索引。数组可能包含多个峰值，
        在这种情况下，返回任何一个所在位置即可。
            1.峰值元素是指其值严格大于左右相邻值的元素。严格大于即不能有等于
            2.假设 nums[-1] = nums[n] = -\infty−∞
            3.对于所有有效的 i 都有 nums[i] != nums[i + 1]
            4.你可以使用O(logN)的时间复杂度实现此问题吗？
     */
    public static void main(String[] args) {
        System.out.println(findPeakElement(new int[]{1,2}));

    }

    public static int findPeakElement(int[] nums) {
        /*int n = nums.length;
        if(n==0)return 0;
        if(n==2) return nums[1]>nums[0]?1:0;
        for(int i = 1;i<n-1;i++){
            if(nums[i-1]<nums[i] && nums[i+1]<nums[i]){
                return i;
            }
        }
        return nums[0]>nums[n-1]?0:n-1;*/

        //只要往高处走，就一定有波峰，采用二分法进行区间的压缩
        int left = 0;
        int right = nums.length - 1;
        //二分法
        while (left < right) {
            int mid = (left + right) / 2;
            //右边是往下，不一定有坡峰
            if (nums[mid] > nums[mid + 1])
                right = mid;
                //右边是往上，一定能找到波峰
            else
                left = mid + 1;
        }
        //其中一个波峰
        return right;
    }


}
