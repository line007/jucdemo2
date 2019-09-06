package com.line.datastructs.sort;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Random;

/**
 * @desc 快速排序
 * 思想：
 * （1）将一个数组分割成左右两部分
 * （2）分操作
 *      A.左边进行递归冒泡排序，直到有序
 *      B.右边进行递归冒泡排序，直到有序
 * （3）合操作（左边 + 右边）
 * （4）依次执行1-3步骤，直到有序
 *
 * 案例：
 *  {-9,78,0,23,-567,70}
 * @Author xw
 * @Date 2019/9/6
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {-9, 78, 0, 23, -567, 70};
        /*int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = new Random().nextInt(8000000);
        }*/
        System.out.println(LocalDateTime.now());
        quickSort(arr, 0, arr.length - 1);
        System.out.println(LocalDateTime.now());
        //System.out.println("arr=" + Arrays.toString(arr));
    }

    /**
     *
     * @param arr 待排数组
     * @param left 左边位置
     * @param right 右边位置
     */
    private static void quickSort(int[] arr, int left, int right) {
        // （1）将一个数组分割成左右两部分
        int l = left;
        int r = right;
        int pivot = arr[(left + right) / 2]; // 0 + 3 / 2
//        System.out.println(String.format("递归：%s,%s,%s,p=%s", Arrays.toString(arr), left, right,pivot));
        int temp;
        // 让小的值放左边、大的值放右边
        while (l < r) { // 下标比较
            while (arr[l] < pivot) { // 左边 < 中间值
                l += 1;
            }
            while (arr[r] > pivot) { // 右边 > 中间值
                r -= 1;
            }
            // l++ , r--，当 l > r，即左边下标>右边下标时结束
            if (l >= r) { // 左右位置重叠，继续各自前移一位，防止死归
                break;
            }
            // 交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            // 交换完成后，左边==中轴值，右边前移
            if (arr[l] == pivot) { // 左边==中轴值，左边前移
                r -= 1;
            }
            if (arr[r] == pivot) { // 右边==中轴值，左边前移
                l += 1;
            }
        }
        // 防止死归!!!
        if (l == r) { // 左右位置重叠，继续各自前移一位，防止死归
            l += 1;
            r -= 1;
        }
        if (left < r) { // 左递归：left~r [-9, 78, 0]
            quickSort(arr, left, r);
        }
        if (right > l) { // 右递归：l~right [0, 23, 78, 70]
            quickSort(arr, l, right);
        }
    }
}
