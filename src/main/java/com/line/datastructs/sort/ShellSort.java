package com.line.datastructs.sort;

import java.util.Arrays;

/**
 * @desc 希尔排序
 *  它是一种更高效的插入排序，也称为缩小增量排序。
 *  产生原因：
 *      由于插入排序存在问题，当需要插入数最小时，后移的次数明显增多，对效率有影响
 *  基本思想：
 *      把记录按下标的一定增量分组，对每组使用直接插入排序算法排序；
 *      随着增量减少，每组包含的关键词越来越多，当增量减至1时，整个文件恰被分成一组，算法便终止
 *
 *  案例：
 *      {8, 9, 1, 7, 2, 3, 5, 4, 6, 0}
 * @Author xw
 * @Date 2019/9/5
 */
public class ShellSort {
    public static void main(String[] args) {

        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        shellSort(arr);
    }

    private static void shellSort(int[] arr) {
        int temp;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            // 第1轮 [8,3] [9,5] [1,4] [7,6][2,0]
            for (int i = gap; i < arr.length; i++) { // 循环5轮，从5开始
                for (int j = i - gap; j >= 0; j -= gap) { // 2个数比较
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            System.out.println("分" + gap + "组过后，arr=" + Arrays.toString(arr));
        }
        /*// 第1轮 [8,3] [9,5] [1,4] [7,6][2,0]
        for (int i = 5; i < arr.length; i++) { // 循环5轮，从5开始
            for (int j = i - 5; j >= 0; j -= 5) { // 2个数比较
                if (arr[j] > arr[j + 5]) {
                    temp = arr[j];
                    arr[j] = arr[j + 5];
                    arr[j + 5] = temp;
                }
                System.out.println(Arrays.toString(arr));
            }
        }
        System.out.println("第1轮过后，arr=" + Arrays.toString(arr));
        // 第2轮 [3, 5, 1, 6, 0, 8, 9, 4, 7, 2]
        //       [3, 5, 1, 6, 0, 8, 9, 4, 7, 2] i=2 3v1
        //       [3, 5, 1, 6, 0, 8, 9, 4, 7, 2] i=3 5v6
        //       [3, 5, 1, 6, 0, 8, 9, 4, 7, 2] i=4 1v0
        //       [3, 5, 1, 6, 0, 8, 9, 4, 7, 2] i=5 6v8 5v6
        int count = 0;
        for (int i = 2; i < arr.length; i++) { // 循环5轮，从5开始
            for (int j = i - 2; j >= 0; j -= 2) { // 2个数比较
                if (arr[j] > arr[j + 2]) {
                    temp = arr[j];
                    arr[j] = arr[j + 2];
                    arr[j + 2] = temp;
                }
                System.out.println(Arrays.toString(arr));
                count ++;
            }
        }
        System.out.println("第2轮过后count="+count+"，arr=" + Arrays.toString(arr));

        // 第3轮
        count = 0;
        for (int i = 1; i < arr.length; i++) { //
            for (int j = i - 1; j >= 0; j -= 1) { // 5个数比较
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
                System.out.println(Arrays.toString(arr));
                count ++;
            }
        }
        System.out.println("第2轮过后count="+count+"，arr=" + Arrays.toString(arr));*/
    }
}
