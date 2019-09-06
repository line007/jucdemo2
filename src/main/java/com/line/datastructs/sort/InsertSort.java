package com.line.datastructs.sort;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Random;

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
        int arr[] = {34, 101, 119, 1};
        /*int[] arr = new int[160000];
        for (int i = 0; i < 160000; i++) {
            arr[i] = new Random().nextInt(8000000);
        }*/
        System.out.println(LocalDateTime.now());
        insert_sort2(arr);
        System.out.println(LocalDateTime.now());
    }

    /**
     * 升级版
     * @param arr
     */
    private static void insert_sort2(int[] arr) {
        // 第i轮
        // （1）找到待插入的位置
        // （2）后移（从插入位置向数组末端后移，直到条件不成立）
        // （3）替换（将新值插入找到的位置）
        for (int i = 1; i < arr.length; i++) {
            int insertIndex = i - 1; // 该位置前一个位置
            int insertVal = arr[i]; // 待插入的值

            // （2）后移（从插入位置依次位移，条件：插入值<当前值（初始为插入值的前一个位置!!!）&& 插入位置 >= 0）
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) { // arr[insertIndex]表示插入位置的前一个值
                // 以2为例，[3,10][2,1] , insertIndex = 2 -1
                // A.(2 < 10) => 后移 [3,10][10,1] insertIndex=0
                // B.(2 < 3) => 后移 [3,3][10,1] insertIndex=-1
                // C.(insertIndex < 0) => 跳出循环
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            // （3）替换（将新值插入找到的位置）
            arr[insertIndex + 1] = insertVal; // insertIndex + 1 ，因为跳转循环多减了1
            //System.out.println("第" + i + "轮过后，arr=" + Arrays.toString(arr));
        }
    }

    /**
     * 普通版
     * @param arr
     */
    private static void insert_sort(int[] arr) {
        // 第i轮
        // （1）找到待插入的位置
        // （2）后移（从插入位置向数组末端后移，直到条件不成立）
        // （3）替换（将新值插入找到的位置）
        for (int i = 1; i < arr.length; i++) {
            int insertIndex = i - 1; // 该位置前一个位置
            int insertVal = arr[i]; // 待插入的值
            for (int j = 0; j < arr.length; j++) {
                if (insertVal < arr[j]) { // （1）找到待插入的位置 j = i - 1，可以用insertIndex替换，优化见insert_sort2
                    // （2）后移（从插入位置依次位移，条件：插入值<当前值（初始为插入值的前一个位置!!!）&& 插入位置 >= 0）
                    while (insertIndex >= 0 && insertVal < arr[insertIndex]) { // arr[insertIndex]表示插入位置的前一个值
                        // 以2为例，[3,10][2,1] , insertIndex = 2 -1
                        // A.(2 < 10) => 后移 [3,10][10,1] insertIndex=0
                        // B.(2 < 3) => 后移 [3,3][10,1] insertIndex=-1
                        // C.(insertIndex < 0) => 跳出循环
                        arr[insertIndex + 1] = arr[insertIndex];
                        insertIndex--;
                    }
                    // （3）替换（将新值插入找到的位置）
                    arr[insertIndex + 1] = insertVal; // insertIndex + 1 ，因为跳转循环多减了1
                    break;
                }
            }
            System.out.println("第"+i+"轮过后，arr=" + Arrays.toString(arr));
        }

        /*// 第1轮
        // （1）找到待插入的位置
        // （2）后移（从插入位置向数组末端后移，直到条件不成立）
        // （3）替换（将新值插入找到的位置）
        int insertIndex = 1 - 1; // 该位置前一个位置
        int insertVal = arr[1]; // 待插入的值
        for (int j = 0; j < arr.length; j++) {
            if (insertVal < arr[j]) { // （1）找到待插入的位置
                // （2）后移（从插入位置依次位移，条件：插入值<当前值（初始为插入值的前一个位置!!!）&& 插入位置 >= 0）
                while (insertIndex >= 0 && insertVal < arr[insertIndex]) { // arr[insertIndex]表示插入位置的前一个值
                    // 以2为例，[3,10][2,1] , insertIndex = 2 -1
                    // A.(2 < 10) => 后移 [3,10][10,1] insertIndex=0
                    // B.(2 < 3) => 后移 [3,3][10,1] insertIndex=-1
                    // C.(insertIndex < 0) => 跳出循环
                    arr[insertIndex + 1] = arr[insertIndex];
                    insertIndex--;
                }
                // （3）替换（将新值插入找到的位置）
                arr[insertIndex + 1] = insertVal; // insertIndex + 1 ，因为跳转循环多减了1
                break;
            }
        }
        System.out.println("第"+1+"轮过后，arr=" + Arrays.toString(arr));
        // 第2轮
        insertIndex = 2 - 1; // 该位置前一个位置
        insertVal = arr[2]; // 待插入的值
        for (int j = 0; j < arr.length; j++) {
            if (insertVal < arr[j]) { // （1）找到待插入的位置
                // （2）后移（从插入位置依次位移，条件：插入值<当前值（初始为插入值的前一个位置!!!）&& 插入位置 >= 0）
                while (insertIndex >= 0 && insertVal < arr[insertIndex]) { // arr[insertIndex]表示插入位置的前一个值
                    // 以2为例，[3,10][2,1] , insertIndex = 2 -1
                    // A.(2 < 10) => 后移 [3,10][10,1] insertIndex=0
                    // B.(2 < 3) => 后移 [3,3][10,1] insertIndex=-1
                    // C.(insertIndex < 0) => 跳出循环
                    arr[insertIndex + 1] = arr[insertIndex];
                    insertIndex--;
                }
                // （3）替换（将新值插入找到的位置）
                arr[insertIndex + 1] = insertVal; // insertIndex + 1 ，因为跳转循环多减了1
                break;
            }
        }
        System.out.println("第"+2+"轮过后，arr=" + Arrays.toString(arr));
        // 第3轮
        insertIndex = 3 - 1; // 该位置前一个位置
        insertVal = arr[3]; // 待插入的值
        for (int j = 0; j < arr.length; j++) {
            if (insertVal < arr[j]) { // （1）找到待插入的位置
                // （2）后移（从插入位置依次位移，条件：插入值<当前值（初始为插入值的前一个位置!!!）&& 插入位置 >= 0）
                while (insertIndex >= 0 && insertVal < arr[insertIndex]) { // arr[insertIndex]表示插入位置的前一个值
                    // 以2为例，[3,10][2,1] , insertIndex = 2 -1
                    // A.(2 < 10) => 后移 [3,10][10,1] insertIndex=0
                    // B.(2 < 3) => 后移 [3,3][10,1] insertIndex=-1
                    // C.(insertIndex < 0) => 跳出循环
                    arr[insertIndex + 1] = arr[insertIndex];
                    insertIndex--;
                }
                // （3）替换（将新值插入找到的位置）
                arr[insertIndex + 1] = insertVal; // insertIndex + 1 ，因为跳转循环多减了1
                break;
            }
        }
        System.out.println("第"+3+"轮过后，arr=" + Arrays.toString(arr));*/
    }

}
