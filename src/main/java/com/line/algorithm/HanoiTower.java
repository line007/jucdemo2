package com.line.algorithm;

/**
 * @desc 分治算法案例：汉诺塔
 * （1）基本概念
 * 分治算法是一种很重要的算法，字面上的解释是“分而治之”，就是把一个复杂的问题
 * 分解成两个或更多的相同或相似的子问题...直到最后子问题可以简单的直接求解，原
 * 问题的解即子问题的解的合并，这个技巧就是很多高效算法的基础，如排序算法（快速排序，归并排序），傅里叶变换（快速傅里叶变换）...
 * （2）基本步骤
 * 1）分解：将原问题分解为若干个规模较小的问题，相互独立，与原问题形式相同的子问题
 * 2）解决：若子问题规模较小则直接解决，否则递归地解各个子问题
 * 3）合并：将各个子问题的解合并为原问题的解
 * （3）分治算法设计模式
 * if |P|<=n0
 * then return (ADHOC(P))
 * // 将P分解为较小的问题P1,P2...PK
 * for i <- 1 to k
 * do yi <- Divide-and-Conquer(Pi) 递归解决Pi
 * T <- MERGE(y1,y2...yk) 合并子问题
 * return (T)
 * <p>
 * |P|：表示问题P的规模
 * n0：表示阈值，表示当问题P的规模不超过n0时，问题已容易直接解出，不必再继续分解。
 * ADHOC(P)：是该分治法中的基本子算法，用于直接解小规模的问题P。因此，当P的规模不超过n0时直接用算法ADHOC(P)求解
 * 算法MERGE(y1,y2...yk)：是该分治算法中的合并子算法，用于将P的子问题P1,P2...PK的相应的解y1,y2,..yk合并为P的解。
 * <p>
 * 经典案例：汉诺塔
 * 思路分析：
 * （1）如果有一个盘，A->C
 * n0=2
 * if (n<=n0) {
 * // 直接解出来
 * }
 * // 将P分解为较小的问题P1,P2...PK
 * while(n>n0) {
 * 分(n);
 * n--;
 * }
 * // T <- MERGE(y1,y2...yk) 合并子问题
 * @Author xw
 * @Date 2019/9/27
 */
public class HanoiTower {
    public static void main(String[] args) {
        hanoiTower(3, 'A', 'B', 'C');
    }

    private static void hanoiTower(int num, char a, char b, char c) {
        if (num == 1) { // 只有一个盘，直接解出
            System.out.println("第1个盘从" + a + "->" + c);
        } else {
            // 如果n>=2的情况
            // 1.先把最上面的所有盘A->B，移动过程会使用C
            hanoiTower(num - 1, a, c, b);
            // 2.把最下边的盘A->C
            System.out.println("第" + num + "个盘从" + a + "->" + c);
            // 3.把B塔所有盘从B->C，移动过程使用到A
            hanoiTower(num - 1, b, a, c);
        }
    }
}
