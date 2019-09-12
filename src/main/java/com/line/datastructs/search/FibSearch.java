package com.line.datastructs.search;

import java.util.Arrays;

/**
 * @desc 斐波那契查找算法
 * 思路分析：
 * 1.原理与二分查找、插值查找类似，也是通过改变mid值（该值在黄金分割点附近）位置，算法：mid = low + f[k-1] - 1
 *  f代码斐波那契数列
 *  （1）斐波那契数列特点：f[k] = f[k-1] + f[k-2] => (f[k] - 1) = (f[k-1]-1) + (f[k-2]-1) + 1
 *      假设顺序表长度为：length = (f[k] - 1) ，刚好可以分成 f[k-1]-1和f[k-2]-1两部分
 *      即中间位置 mid = low + (f[k-1] - 1)
 *
 * 案例：
 *  {1, 1, 2, 3, 5, 8, 13, 21, 34, 55}
 * @Author xw
 * @Date 2019/9/11
 */
public class FibSearch {
    static int maxSize = 12;
    public static void main(String[] args) {

        int[] arr = {1, 3, 5, 18, 56, 87, 100};
        int index = fibSearch(arr, 100);
        if (index == -1) {
            System.out.println("没有找到");
        } else {
            System.out.println("找到了，下标为：" + index);
        }
    }

    // mid = low + (f[k-1] - 1)
    private static int fibSearch(int[] a, int key) {
        // （1）创建斐波那契数列
        int high = a.length - 1; // 高位
        int low = 0; // 低位
        int k = 0; // 表示斐波那契的下标
        int f[] = fib();
        // （2）创建一个新数组，以f[k]为模板将原值copy得到新数组（因为f[k]的值可能大于a的长度，因此需要Arrays类，构造一个新的数组，并指向a[]，不足部分使用0补充）
        while (high > f[k] - 1) {
            // 1-1 k=1
            // 1-1 k=2
            // 2-1 k=3
            // 3-1 k=4
            // 5-1 k =5
            System.out.println(high + "--" + (f[k] - 1));
            k++;
        }
        System.out.println("k=" + k);
        int[] temp = Arrays.copyOf(a, f[k]); // 长度为8 f[k] => f[5] = 8，
        // （3）用高位替换0
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = temp[high];
        }
        System.out.println("temp=" + Arrays.toString(temp));
        // （4）使用while循环处理，得到我们的数key
        int mid;
        System.out.println("f=>" + Arrays.toString(f));
        while (low <= high) { // 只要这个条件满足，就可以找
            mid = low + (f[k-1] - 1);
            System.out.println("mid=" + mid);
            if (key < temp[mid]) { // 往左边查找
                high = mid - 1;
                // 1. 全部元素（13） = 前面的元素（8） + 后面的元素（5）
                // 2. f[k] = f[k-1] + f[k-2] => 13 = 8 + 5
                // [1, 1, 2, 3, 5, 8, 13, 21, 34, 55] // 5
                // [1, 1, 2, 3, 5, 8, 13, 18, 18, 18]
                // 因为前面有f[k-1]个元素，所以可以继续拆分 f[k-1] = f[k-2] + f[k-3] => 8 = 5 + 3
                // 即在 f[k-1] 的前面进行查找 k--
                // 即下次循环 mid = f[k-1-1] - 1
                k--; // 前移
            } else if (key > temp[mid]) { // 往右边查找
                low = mid + 1; // low 4 + 1
                // 为什么是 k -= 2
                // 1. 全部元素（13） = 前面元素（8） + 后面元素（5）
                // 2. f[k] = f[k-1] + f[k-2] => 13 = 8 + 5
                // 3. 因为后面有f[k-2] 所以可以继续拆分 f[k-1] = f[k-3] + f[k-4] => 5 = 3 + 2
                // 4. 即在f[k-2]的前面进行查找 k -= 2
                // 5. 即下次去循环mid = f[k-1-2] - 1
                k -= 2; // 5
            } else { // 找到了
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }

    private static int[] fib() {
        // f[k] = f[k-1] + f[k-2]
        int[] f = new int[maxSize];
        // 前2个为1，从第三个开始
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }
}
