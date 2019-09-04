package com.line.datastructs.sort;

import java.util.Arrays;

/**
 * @desc 选择排序
 * arr[] = {101, 34, 119, 1}
 * （1）第一次从arr[0]~arr[n]中选取最小值，与arr[0]交换
 * （2）第二次从arr[1]~arr[n]中选取最小值，与arr[1]交换
 *  ...
 * （3）第i次从arr[i-1]~arr[n-1]中选取最小值，与arr[i-1]交换
 * （4）第n-1次从arr[n-2]~arr[n-1]中选取最小值，与arr[n-2]交换，总共通过n-1次，得到一个按排序码从小到大排列的有序序列
 *
 * @Author xw
 * @Date 2019/9/4
 */
public class SelectSort {
    public static void main(String[] args) {
        int arr[] = {101, 34, 119, 1};
        for (int i = 0; i < arr.length - 1; i++) { // arr.length - 1 只要n-1次就能排好顺序
            int minIndex = i;
            int min = arr[i];
            for (int j = i+1; j < arr.length; j++) { // i+1，每结束一轮，最小下标往后移
                if (min > arr[j]) { // 不是最小值
                    min = arr[j];
                    minIndex = j;
                }
            }
            // 找到最小值，与arr[0]交换，并放在第一个位置，即arr[0]
            if (minIndex != i) { // 最小下标发生改变，才调整位置
                arr[minIndex] = arr[i]; // [101, 34, 119, 1] => [34, 101, 119, 1]
                arr[i] = min;
            }
            System.out.println("第"+(i+1)+"轮过后，arr=" + Arrays.toString(arr));
        }

        /*// 以下是推导过程
        // 第1轮
        int minIndex = 0;
        int min = arr[0];
        for (int j = 0 + 0; j < arr.length; j++) {
            if (min > arr[j]) { // 不是最小值
                min = arr[j];
                minIndex = j;
            }
        }
        // 找到最小值，与arr[0]交换，并放在第一个位置，即arr[0]
        arr[minIndex] = arr[0]; // [101, 34, 119, 1] => [34, 101, 119, 1]
        arr[0] = min;
        System.out.println("第1轮过后，arr=" + Arrays.toString(arr));

        // 第2轮
        minIndex = 1;
        min = arr[1];
        for (int j = 0 + 1; j < arr.length; j++) {
            if (min > arr[j]) { // 不是最小值
                min = arr[j];
                minIndex = j;
            }
        }
        // 找到最小值，与arr[0]交换，并放在第一个位置，即arr[0]
        arr[minIndex] = arr[1]; // [34, 101, 119, 1] => [34, 101, 119, 1]
        arr[1] = min;
        System.out.println("第2轮过后，arr=" + Arrays.toString(arr));

        // 第3轮
        minIndex = 2;
        min = arr[2];
        for (int j = 0 + 2; j < arr.length; j++) {
            if (min > arr[j]) { // 不是最小值
                min = arr[j];
                minIndex = j;
            }
        }
        // 找到最小值，与arr[0]交换，并放在第一个位置，即arr[0]
        arr[minIndex] = arr[2]; // [34, 101, 119, 1] => [34, 101, 119, 1]
        arr[2] = min;
        System.out.println("第3轮过后，arr=" + Arrays.toString(arr));*/
    }
}
