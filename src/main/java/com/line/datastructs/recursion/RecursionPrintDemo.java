package com.line.datastructs.recursion;

/**
 * @desc 打印问题
 * （1）打印
 * （2）阶乘
 * @Author xw
 * @Date 2019/9/3
 */
public class RecursionPrintDemo {
    public static void main(String[] args) {
        // 打印问题
        test1(4); // 2 3 4
        System.out.println("-----------");
        test2(4); // 2
        // 阶乘 4x3x2x1
        System.out.println(factorial(4));

    }

    // 阶乘
    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    // 输出什么？
    public static void test1(int n) {
        if (n > 2) {
            test1(n -1);
        }

        System.out.println("n=" + n);
    }
    // 输出什么？
    public static void test2(int n) {
        if (n > 2) {
            test2(n -1);
        } else {
            System.out.println("n=" + n);
        }
    }
}
