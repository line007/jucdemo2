package com.line.datastructs.sort;

import java.util.Arrays;

/**
 * @desc 冒泡排序
 *  案例：arr[] = {3, 9, -1, 10, 20}
 * 思路分析：
 * 1.两层for循环遍历待排序的数组，i的index=[0,arr.length-1],j的index=[0,arr.length-1-i]
 *  （1）length-1：每一轮循环是两个数比较，所以-1
 *  （2）length-1-i：-1跟同上；每一轮结束会有一个最小值到数组最末端，所以每次-i
 * 2.如果 arr[j] > arr[j+1] 交换两个值的位置（arr[j] = arr[j+1]），需要用temp临时变量保存arr[j]的值
 * @Author xw
 * @Date 2019/9/4
 */
public class BubbleSort {
    public static void main(String[] args) {
        int arr[] = {3, 9, -1, 10, 20};
        int temp;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j+1]) {
                    // 直接交换是不行的，值会覆盖
                    /*arr[j] = arr[j+1];
                    arr[j+1] = arr[j];*/
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            System.out.println("第"+(i+1)+"轮过后，arr=" + Arrays.toString(arr));
        }

        // 推导过程
        // 第1轮
        /*int temp;
        for (int j = 0; j < arr.length - 1 - 0; j++) {
            if (arr[0] > arr[j+1]) {
                // 直接交换是不行的，值会覆盖
                *//*arr[j] = arr[j+1];
                arr[j+1] = arr[j];*//*
                temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
            }
        }
        System.out.println("第1轮过后，arr=" + Arrays.toString(arr));
        // 第2轮
        for (int j = 0; j < arr.length - 1 - 1; j++) {
            if (arr[1] > arr[j+1]) {
                // 直接交换是不行的，值会覆盖
                *//*arr[j] = arr[j+1];
                arr[j+1] = arr[j];*//*
                temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
            }
        }
        System.out.println("第2轮过后，arr=" + Arrays.toString(arr));
        // 第3轮
        for (int j = 0; j < arr.length - 1 - 2; j++) {
            if (arr[2] > arr[j+1]) {
                // 直接交换是不行的，值会覆盖
                *//*arr[j] = arr[j+1];
                arr[j+1] = arr[j];*//*
                temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
            }
        }
        System.out.println("第3轮过后，arr=" + Arrays.toString(arr));*/
    }
}
