package com.ys.example.leetCode;

/**
 * @Description
 * @Author 杨帅
 * @Date 2022/6/23 16:14
 * @Version 1.0
 **/
public class p4 {

    public static int mod = 1000000007;
    public static void main(String[] args) {
        System.out.println(InversePairs(new int[]{1, 2, 3, 4, 5, 6, 7, 0}));
//        System.out.println(InversePairs(new int[]{1, 2, 3}));
    }

    public static int mergeSort(int left,int right,int[] data,int[] temp){
        if(left>=right) return 0;
        int mid = (left + right) / 2;
        int res = mergeSort(left,mid,data,temp) + mergeSort(mid+1,right,data,temp);
        res %= mod;

        int i = left,j = mid +1;
        for (int k = left; k <= right ; k++) {
            temp[k] = data[k];
        }
        for(int k = left; k <= right; k++){
            if(i == mid + 1)
                data[k] = temp[j++];
            else if(j == right + 1 || temp[i] <= temp[j])
                data[k] = temp[i++];
                //左边比右边大，答案增加
            else{
                data[k] = temp[j++];
                // 统计逆序对
                res += mid - i + 1;
            }
        }
        return res % mod;
    }

    public static int InversePairs(int [] array) {
        int n = array.length;
        int[] res = new int[n];
        return mergeSort(0, n - 1, array, res);

        //以下代码会报代码过长的错误
        /*int count = 0;
        int n = array.length-1;
        for(int i = n;i>0;i--){
            for(int j = i-1;j>=0;j--){
                if(array[j]>array[i]){
                    count++;
                }
            }
        }
        return count % 1000000007;*/
    }
}
