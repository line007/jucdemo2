package com.line.datastructs.search;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @desc 二分查找
 * 案例：
 *  {1, 8, 10, 89, 1000, 1234}
 * 一、思路分析（返回第一个）：
 *  1.首先确定该数组的中间下标
 *      mid = (left + right) / 2
 *  2.然后让需要查找的数findVal与arr[mid]比较
 *  2.1 findVal > arr[mid]，说明要查找的数在右边，需要向右递归
 *  2.2 findVal < arr[mid]，说明要查找的数在左边，需要向左递归
 *  2.3 findVal == arr[mid]，说明已经找到了，返回
 *
 *  问题：结束递归的条件是什么?
 *  1) 找到就结束递归
 *  2) 递归完整个数组，仍然没找到findVal，也需要结束递归，当left>right就需要退出
 *
 * 二、思路分析（返回所有符合条件的下标）：
 *  1.在找到mid索引值时，不要立马返回
 *  2.向mid索引值的左边扫描，将所有满足条件元素的下标，加入到集合
 *  3.向mid索引值的右边扫描，将所有满足条件元素的下标，加入到集合
 * @Author xw
 * @Date 2019/9/11
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        // （1）使用二分查找，返回第一个符合条件的下标
        int findVal = 89;
        int index = binarySearch(arr, 0, arr.length, findVal);
        if (index == -1) {
            System.out.println("没有找到");
        } else {
            System.out.println("找到了，下标为：" + index);
        }

        // （2）使用二分查找，返回所有符合条件的下标数组
        int[] arr2 = {1000, 8, 1000, 89, 1000, 1000, 1000, 1234};
        findVal = 1000;
        List<Integer> resIndexList = binarySearch2(arr2, 0, arr2.length, findVal);
        System.out.println("arr2=" + resIndexList);
    }

    /**
     * 二分查找算法
     * @param arr 数组
     * @param left 左边索引
     * @param right 右边索引
     * @param findVal 要查找的值
     * @return 如果找到返回下标，否则返回-1
     */
    private static List<Integer> binarySearch2(int[] arr, int left, int right, int findVal) {
        // 1.首先确定该数组的中间下标
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        // 2.然后让需要查找的数findVal与arr[mid]比较
        // *  2.1 findVal > arr[mid]，说明要查找的数在右边，需要向右递归
        // *  2.2 findVal < arr[mid]，说明要查找的数在左边，需要向左递归
        // *  2.3 findVal == arr[mid]，说明已经找到了，返回
        if (findVal > midVal) {
            binarySearch2(arr, mid, right, findVal);
        } else if (findVal < midVal) {
            binarySearch2(arr, 0, mid + 1, findVal);
        } else {
            List<Integer> resIndexList = new ArrayList<>();
            // 思路分析（返回所有符合条件的下标）：
            // *  1.在找到mid索引值时，不要立马返回
            // *  2.向mid索引值的左边扫描，将所有满足条件元素的下标，加入到集合
            // *  3.向mid索引值的右边扫描，将所有满足条件元素的下标，加入到集合
            int temp = mid - 1;
            while (true) {
                if (temp < 0) {
                    break;
                }
                if (arr[temp] != findVal) {
                    temp -= 1;
                    continue;
                }
                resIndexList.add(temp);
                // 否则
                temp -= 1;
            }
            resIndexList.add(mid);

            temp = mid + 1;
            while (true) {
                if (temp > arr.length - 1) {
                    break;
                }
                if (arr[temp] != findVal) {
                    temp += 1;
                    continue;
                }
                resIndexList.add(temp);
                // 否则
                temp += 1;
            }
            return resIndexList.stream().sorted().collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    /**
     * 二分查找算法
     * @param arr 数组
     * @param left 左边索引
     * @param right 右边索引
     * @param findVal 要查找的值
     * @return 如果找到返回下标，否则返回-1
     */
    private static int binarySearch(int[] arr, int left, int right, int findVal) {
        // 1.首先确定该数组的中间下标
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        // 2.然后让需要查找的数findVal与arr[mid]比较
        // *  2.1 findVal > arr[mid]，说明要查找的数在右边，需要向右递归
        // *  2.2 findVal < arr[mid]，说明要查找的数在左边，需要向左递归
        // *  2.3 findVal == arr[mid]，说明已经找到了，返回
        if (findVal > midVal) {
            binarySearch(arr, mid, right, findVal);
        } else if (findVal < midVal) {
            binarySearch(arr, 0, mid + 1, findVal);
        } else {
            return mid;
        }
        return -1;
    }
}
