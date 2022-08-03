package com.ys.example.algorithm;

import java.util.Arrays;

/**
 * @Description 排序算法
 * @Author 杨帅
 * @Date 2022/5/2 22:12
 * @Version 1.0
 **/
public class SortTest {
    public static void main(String[] args) {
        int[] a = {2, 7, 5, 4, 16, 18};
        int[] arr = {9, 8, 7, 10, 123, 6, 789, 654, 88, 658};
        System.out.println("=============排序前============");
        System.out.println(Arrays.toString(arr));
//        straightSort(a);
//        bubbleSort(a);
//        selectSort(a);
//        shellSort(a);
//        quickSort(a,0,5);
//        heapSort(a);
        process(arr, 0, 9);
//        radixSort(arr);
        System.out.println("=============排序后============");
        System.out.println(Arrays.toString(arr));

    }

    /**
     * @param a
     * @Description: 直接插入排序
     * @Author: 杨帅
     * @Date: 2022/5/3
     */
    private static void straightSort(int[] a) {
        int i, j;
        int tmp = 0;
        int len = a.length;
        for (i = 1; i < len; i++) {
            if (a[i] < a[i - 1]) {
                tmp = a[i];
                a[i] = a[i - 1];
                for (j = i - 1; j >= 0 && a[j] > tmp; j--) {
                    a[j + 1] = a[j];
                }
                a[j + 1] = tmp;
            }
        }
    }

    /**
     * @param a
     * @Description: 冒泡排序
     * @Author: 杨帅
     * @Date: 2022/5/3
     */
    private static void bubbleSort(int[] a) {
        int len = a.length;
        for (int i = 0; i < len - 1; i++) {
            int count = 0;
            for (int j = 0; j < len - 1 - i; j++) {
                if (a[j] > a[j + 1]) {
                    swap(a, j, j + 1);
                    count++;
                }
            }
            if (count == 0) {
                break;
            }
        }
    }

    /**
     * @param a
     * @Description: 简单选择排序
     * @Author: 杨帅
     * @Date: 2022/5/3
     */
    private static void selectSort(int[] a) {
        int i, j, k;
        int len = a.length;

        for (i = 0; i < len - 1; i++) {
            k = i;
            for (j = i + 1; j < len; j++) {
                if (a[j] < a[k]) {
                    k = j;
                }
            }
            if (k != i) {
                swap(a, i, k);
            }
        }
    }

    /**
     * @param a
     * @Description: 希尔排序
     * @Author: 杨帅
     * @Date: 2022/5/3
     */
    private static void shellSort(int[] a) {
        /**
         * 虽然嵌套了三个循环，但是数据的交换次数并不多
         */
        int second = 0;
        for (int gap = a.length / 2; gap >= 1; gap /= 2) {
            for (int i = gap; i < a.length; i++) {
                int j = i;
                //分组后，每一组先排序前面的，再排序后面的
                while (j - gap >= 0 && a[j] < a[j - gap]) {
                    second++;
                    swap(a, j, j - gap);
                    j -= gap;
                }
            }
        }
        System.out.println("希尔排序交换次数：" + second);
    }

    /**
     * @param arr
     * @param low
     * @param high
     * @Description: 快速排序
     * @Author: 杨帅
     * @Date: 2022/5/4
     */
    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int loc = partition(arr, low, high);
            quickSort(arr, low, loc - 1);
            quickSort(arr, loc + 1, high);
        }
    }

    /**
     * @param arr
     * @Description: 堆排序
     * @Author: 杨帅
     * @Date: 2022/5/4
     */
    private static void heapSort(int[] arr) {
        int temp = 0;
        //堆排序
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }

        for (int j = arr.length - 1; j > 0; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);
        }
    }

    /**
     * @param a
     * @param L
     * @param R
     * @Description: 归并排序
     * @Author: 杨帅
     * @Date: 2022/5/4
     */
    private static void process(int[] a, int L, int R) {
        if (L == R) {
            return;
        }
        int mid = L + (R - L) / 2;//计算中点
        process(a, L, mid);
        process(a, mid + 1, R);
        mergeSort(a, L, mid, R);
    }

    private static void radixSort(int[] arr) {
        //定义一个二维数组表示桶 总共 0～9 10个桶
        //基数排序属于空间换时间的经典算法
        int[][] bucket = new int[10][arr.length];
        //用一个一维数组来表示每个桶存入的个数情况
        int[] bucketElementsCounts = new int[10];
        //找出传入数组的最大值
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        int maxLength = (max + "").length();
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            //获取元素下标
            for (int j = 0; j < arr.length; j++) {
                //获取下标
                int digitOfElement = arr[j] / n % 10;
                //放桶
                bucket[digitOfElement][bucketElementsCounts[digitOfElement]++] = arr[j];
            }
            int index = 0;
            for (int k = 0; k < bucketElementsCounts.length; k++) {
                if (bucketElementsCounts[k] != 0) {
                    for (int l = 0; l < bucketElementsCounts[k]; l++) {
                        arr[index++] = bucket[k][l];
                    }
                }
                //把桶下表清零
                bucketElementsCounts[k] = 0;
            }

        }
    }


    /*********************************************************************************/
    /***********************               辅助方法                *********************/
    /*********************************************************************************/

    /**
     * @param a
     * @param L
     * @param mid
     * @param R
     * @Description: 两数组归并操作
     * @Author: 杨帅
     * @Date: 2022/5/4
     */
    private static void mergeSort(int[] a, int L, int mid, int R) {
        int[] arr = new int[R - L + 1];
        int i = L;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= R) {
            arr[k++] = a[i] < a[j] ? a[i++] : a[j++];
        }
        //左边这个数组的指针超出序列尾
        while (i <= mid) {
            arr[k++] = a[i++];
        }
        //右边边这个数组的指针超出序列尾
        while (j <= R) {
            arr[k++] = a[j++];
        }

        for (k = L; k <= R; k++) {
            a[k] = arr[k - L];
        }
    }


    /**
     * @param arr
     * @param i
     * @param length
     * @Description: 堆排序——调整为大顶堆
     * @Author: 杨帅
     * @Date: 2022/5/4
     */
    private static void adjustHeap(int[] arr, int i, int length) {
        //a[i]>a[2i+1]&&a[i]>a[2i+2]
        int temp = arr[i];
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            //先比较左子节点和右子节点的大小，最大的那个和temp进行交换
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;//k指向右子节点
            }
            //如果非子节点的值小于左子节点和右子节点的值
            if (arr[k] > temp) {
                //temp和arr[k]进行交换
                arr[i] = arr[k];
                i = k;//继续循环比较，假设k是左子节点，k+1是右子节点，然后引出公式
            } else {
                break;
            }
        }
        //当for循环结束后，我们已经将以i为父节点的树的最大值，放在了最顶上（局部）
        arr[i] = temp;
    }


    /**
     * @param arr
     * @param low
     * @param high
     * @Description: 快速排序——第一趟，分成前后两部分，前一部分的元素均小于后一部分的元素
     * @Author: 杨帅
     * @Date: 2022/5/4
     * @return: int
     */
    private static int partition(int[] arr, int low, int high) {
        int i, j;
        int pivot;
        pivot = arr[low];
        i = low;
        j = high;
        while (i < j) {
            while (i < j && arr[j] >= pivot) {
                j--;
            }
            arr[i] = arr[j];
            while (i < j && arr[i] <= pivot) {
                i++;
            }
            arr[j] = arr[i];
        }
        //i==j时
        arr[i] = pivot;
        return i;
    }

    /**
     * @param arr
     * @param a
     * @param b
     * @Description: 通用 交换数组内俩元素 方法
     * @Author: 杨帅
     * @Date: 2022/5/4
     */
    public static void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }


}
