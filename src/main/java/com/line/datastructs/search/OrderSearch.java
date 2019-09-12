package com.line.datastructs.search;

/**
 * @desc 线性查找
 * 案例：
 *  {1, 8, 10, 89, 1000, 1234}
 * @Author xw
 * @Date 2019/9/11
 */
public class OrderSearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        int index = orderFind(arr, 89);
        if (index == -1) {
            System.out.println("没有找到");
        } else {
            System.out.println("找到了，下标为：" + index);
        }
    }

    private static int orderFind(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
