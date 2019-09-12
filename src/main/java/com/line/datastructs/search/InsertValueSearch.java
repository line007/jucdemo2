package com.line.datastructs.search;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @desc 插值查找算法
 *  由于二分查找针对大数据量首、末两端的查找位移次数太多，影响性能，所以出现了动态中间下标，即插值查找算法。
 * 思路分析：
 *  int mid = left + (right - left)*(findVal - arr[left])/(arr[right] - arr[left])
 *  如：arr = {1,2,3...100}
 *  （1）查找1
 *      mid = 0 + (99 - 0)*(1 - 1)/(100 - 1) => 0 + 99 * 0/99 = 0
 *  （2）查找100
 *      mid = 0 + (99 - 0)*(100 - 1)/(100 - 1) => 0 + 99 * 99/99 = 99
 *
 *  案例：
 *      {1,2,3...100}
 * @Author xw
 * @Date 2019/9/11
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        Integer[] arr = Stream.iterate(1, x -> x+1).limit(100).collect(Collectors.toList()).toArray(new Integer[100]);
        int index = insertValueSearch(arr, 0, arr.length - 1, 100); // arr.length - 1
        System.out.println(index);
    }

    /**
     * 插值查找算法
     * @param arr 数组
     * @param left 左边索引
     * @param right 右边索引
     * @param findVal 要查找的值
     * @return 如果找到返回下标，否则返回-1
     */
    private static int insertValueSearch(Integer[] arr, int left, int right, int findVal) {

        // 防止下标越界
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return  -1;
        }

        // 1.首先确定该数组的中间下标
//        int mid = (left + right) / 2;
        int mid = left + (right - left)*(findVal - arr[left])/(arr[right] - arr[left]);
        int midVal = arr[mid];
        // 2.然后让需要查找的数findVal与arr[mid]比较
        // *  2.1 findVal > arr[mid]，说明要查找的数在右边，需要向右递归
        // *  2.2 findVal < arr[mid]，说明要查找的数在左边，需要向左递归
        // *  2.3 findVal == arr[mid]，说明已经找到了，返回
        if (findVal > midVal) {
            insertValueSearch(arr, mid, right, findVal);
        } else if (findVal < midVal) {
            insertValueSearch(arr, 0, mid + 1, findVal);
        } else {
            return mid;
        }
        return -1;
    }
}
