package com.line.datastructs.sort;

import java.util.Arrays;

/**
 * @desc 插入排序
 * 思路分析：
 *  （1）把n个待排序的元素看成为一个有序表和一个无序表，开始时有序表只包含一个元素，无序表中含有n-1个元素
 *  （2）排序过程中每次从无序表中取出第一个元素，把它的排序码依次与有序的排序码进行比较，将它插入到有序表中的适当位置，
 *      使之成为新的有序节点
 * 实例：
 *  {101, 34, 119, 1}
 * @Author xw
 * @Date 2019/9/4
 */
public class InsertSort {
    public static void main(String[] args) {
        int arr[] = {101, 34, 119, 1};
        for (int i = 1; i < arr.length; i++) { // 从第二个数开始找[101]<=>[34, 119, 1]
            int insertVal = arr[i]; // 待插入的数 34
            int insertIndex = i - 1; // 待插入的数的前一个下标 0 => 101

            // 给insertVal找到插入的位置
            // 1.insertIndex >= 0 保证插入不越界
            // 2.insertVal < arr[insertIndex] 待插入的数，还没有找到插入的位置，就需要将arr[insertIndex] 后移
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) { // 0 >= 0 && 34 < arr[0]=101
                arr[insertIndex + 1] = arr[insertIndex]; // arr[1] = arr[0] ==> arr[1] = 101 ==> {101,101,119,1}
                insertIndex--;
            }
            // 找到了
            arr[insertIndex + 1] = insertVal; // arr[-1+1] = 34
            System.out.println("第"+i+"轮过后，arr=" + Arrays.toString(arr));
        }
    }
}
