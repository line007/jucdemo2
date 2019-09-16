package com.line.datastructs.tree;

import java.time.LocalDateTime;

/**
 * @desc 堆排序
 * 排序思想：
 *  1.将待排序序列构造成一个大顶堆
 *  2.此时，整个队列最大的就是堆顶
 *  3.将其与末尾元素进行交换，此时末尾就为最大值
 *  4.然后将剩余n-1个元素重新构成一个堆，这样会得到n个元素的次小值，
 *      如此反复执行，便能得到一个有序序列
 *  可以看到在构建大顶堆的过程中，元素的个数逐渐减少，最后得到一个有序序列
 *
 *  案例：
 *    {4, 6, 8, 5, 9}
 * @Author xw
 * @Date 2019/9/16
 */
public class HeapSort {
    public static void main(String[] args) {

        int[] arr = {4, 6, 8, 5, 9};
        /*int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = new Random().nextInt(8000000);
        }*/
        System.out.println(LocalDateTime.now());
        heapSort(arr);
        System.out.println(LocalDateTime.now());
        //System.out.println("arr=" + Arrays.toString(arr));
    }

    private static void heapSort(int[] arr) {
        // 1.将待排序序列构造成一个大顶堆
        // （1）给定一个无序序列
        // （2）此时，我们从最后一个非叶子结点(arr.length/2-1)开始（即从5/2-1=1开始，arr[1] = 6），从左至右，从上至下进行调整
        // （3）找到第二个非叶子结点4，由于[4,9,8]中9元素最大，4和9交换
        // （4）这时，交换导致了子根[4,5,6]结构混乱，继续调整，[4,5,6]中6最大，交换4和6，此时就构造成一个大顶堆
        for (int i = arr.length/2-1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }

        // 2.将其与末尾元素进行交换，此时末尾就为最大值，
        // 然后继续调整，再将堆顶元素与末尾元素交换，得到第二大元素，如此反复进行交换，直到有序
        int temp;
        for (int j = arr.length-1; j > 0 ; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);
        }
    }

    /**
     * 功能：完成将以i对应的非叶子结点的树调整成大顶堆
     * 举例：int[] arr = {4, 6, 8, 5, 9} => i=1 => adjustHeap 得到 {4, 9, 8, 5, 6}
     * 如果我们再次调整，传入的是 i=0 得到 {9, 6, 8, 5, 4}
     * @param arr 待调整的数组
     * @param i 表示非叶子结点的下标
     * @param length 表示对多少个元素继续调整，length是在逐渐减少
     */
    public static void adjustHeap(int arr[], int i, int length) {
        int temp = arr[i];

        // k = 2*i + 1 k是i结点的左子结点
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) { // 步长为：k*2 + 1，即下一个左子节点
            // 左子节点的值小于右子结点的值
            if (k + 1 < length && arr[k] <arr[k+1]) {
                k++; // k指向右子结点
            }
            // 如果子结点大于父结点
            if (arr[k] > temp) {
                arr[i] = arr[k]; // 交换位置
                i = k; // i指向k，继续循环比较
            } else {
                break;
            }
        }
        // 当循环结束后，我们已经将以i为父结点的树的最大值，放在了最顶（局部）
        arr[i] = temp;
    }
}
