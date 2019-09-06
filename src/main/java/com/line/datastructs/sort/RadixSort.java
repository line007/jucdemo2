package com.line.datastructs.sort;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Random;

/**
 * @desc 基数排序
 * 特别说明：
 *      该demo不支持负数排序，如想实现负数排序，请参考https://code.i-harness.com/zh-CN/q/e98fa9
 * 排序思想：
 * （1）将所有比较数值统一为同样的数位长度，数位较短的数前面补0。
 * （2）然后，从最低位开始，依次进行一次排序。这样从最低位排序一直到最高位排序
 *      完成后，数列就变成一个有序序列了。
 *
 * 案例：
 *  {53, 3, 542, 748, 14, 214}
 * @Author xw
 * @Date 2019/9/6
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {53, 3, 542, 748, 14, 214};
        /*int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = new Random().nextInt(8000000);
        }*/
        System.out.println(LocalDateTime.now());
        radixSort(arr);
        System.out.println(LocalDateTime.now());
        System.out.println("arr=" + Arrays.toString(arr));
    }

    private static void radixSort(int[] arr) {
        // 初始化10个桶，每个桶的长度为数组长度
        int[][] bucket = new int[10][arr.length];
        // 记录每个桶实际数量
        int[] bucketElementCounts = new int[10];
        int maxLength;
        int maxVal = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[i-1]) {
                maxVal = arr[i];
            }
        }
        maxLength = (maxVal + "").length();

        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            // 第1轮
            for (int j = 0; j < arr.length; j++) {
                int digitOfElement = arr[j] / n % 10; // 个位数字
                // 放到对应的桶里
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }
            // 按照这个桶的顺序将数据重新放入到原数组
            int index = 0;
            for (int k = 0; k < bucketElementCounts.length; k++) {
                if (bucketElementCounts[k] != 0) { // 桶里有数据
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        arr[index++] = bucket[k][l];
                    }
                }
                bucketElementCounts[k] = 0; // 清空桶数量
            }
            // System.out.println("第"+(i+1)+"轮，arr=" + Arrays.toString(arr));
        }
        /*// 第1轮
        for (int j = 0; j < arr.length; j++) {
            int digitOfElement = arr[j] % 10; // 个位数字
            // 放到对应的桶里
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;
        }
        // 按照这个桶的顺序将数据重新放入到原数组
        int index = 0;
        for (int k = 0; k < bucketElementCounts.length; k++) {
            if (bucketElementCounts[k] != 0) { // 桶里有数据
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    arr[index++] = bucket[k][l];
                }
            }
        }
        System.out.println("第1轮，arr=" + Arrays.toString(arr));*/
    }
}
