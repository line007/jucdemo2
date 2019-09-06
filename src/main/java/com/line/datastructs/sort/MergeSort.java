package com.line.datastructs.sort;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Random;

/**
 * @desc 归并排序
 *  利用分治策略，分而治之，即先分再合
 * （1）分
 *      A.找到中间下标mid
 *      B.向左递归
 *      C.向右递归
 * （2）治
 *      A.将左右两边（有序）的数据按照规则填充到temp数组，直到左右两边的有序序列有一边处理完毕为止
 *      B.把剩余数据的一边的数据依次全部填充到temp
 *      C.将temp数组的元素拷贝到arr（注意，并不是每次都拷贝所有！！！）
 *
 * 案例：
 *      {8, 4, 5, 7, 1, 3, 6, 2}
 * @Author xw
 * @Date 2019/9/6
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        /*int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = new Random().nextInt(8000000);
        }*/
        int[] temp = new int[arr.length]; // 归并排序需要额外的一个数组
        System.out.println(LocalDateTime.now());
        mergeSort(arr, 0, arr.length - 1, temp);
        System.out.println(LocalDateTime.now());
        System.out.println("arr=" + Arrays.toString(arr));

        /*int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = new Random().nextInt(8000000);
        }
        System.out.println(LocalDateTime.now());
        int[] temp = new int[arr.length]; // 归并排序需要额外的一个数组
        mergeSort(arr, 0, arr.length - 1, temp);
        System.out.println(LocalDateTime.now());*/
    }

    private static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            // 向左递归分解
            mergeSort(arr, left, mid, temp);
            // 向右递归分解
            mergeSort(arr, mid + 1, right, temp);
            // 合
            merge(arr, left, mid, right, temp);
        }
    }

    /**
     *
     * @param arr 排序原数组
     * @param left 左边有序序列的初始下标
     * @param mid 中间索引
     * @param right 右边索引
     * @param temp 做中转的数组
     */
    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        // （一）
        // 将左右两边（有序）的数据按照规则填充到temp数组
        // 直到左右两边的有序序列有一边处理完毕为止
        int i = left; // 左边有序序列的初始索引
        int j = mid + 1; // 右边有序序列的初始索引
        int t = 0;
        while(i <= mid && j <= right) {
            if (arr[i] <= arr[j]) { // 左边小，将左边的值放入temp
                temp[t] = arr[i];
                // 后移
                t += 1;
                i += 1;
            } else { // 反之，将右边的值放入temp
                temp[t] = arr[j];
                // 后移
                t += 1;
                j += 1;
            }
        }

        // （二）
        // 把剩余数据的一边的数据依次全部填充到temp
        while(i <= mid) { // 左边剩余
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }
        while (j <= right) { // 右边剩余
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }

        // （三）
        // 将temp数组的元素拷贝到arr
        // 注意，并不是每次都拷贝所有！！！
        t = 0;
        int tempLeft = left;
        // 第1次合并，left=0, right=1
        // 第2次合并，left=2, right=3
        // 第3次合并，left=0, right=3
        // ...
        while (tempLeft <= right) { // 左边 <= 右边
            arr[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }
    }
}
