package com.line.datastructs.recursion;

/**
 * @desc 8皇后问题
 * 1.是什么？
 *  8x8的矩阵，任意两个位置不能处于同一行、同一列或同一斜线，能找出多少种解法，这就是8皇后问题
 * 2.思路分析
 * （1）第一个皇后放第一行第一列[0,0]
 * （2）第二个皇后放第二行第一列[1,0]...直到判断ok，
 *      如果不ok，继续放在第二列、第三列、依次把所有列放完，找到一个合适位置
 * （3）继续第三个皇后，还是第一列、第二列...直到第8个皇后也放在一个不冲突的位置，
 *      算是找到一个正确解
 * （4）当得到一个正确解时，在栈回退到上一个栈时，就会开始回溯，即将第一个皇后，放在第一列的所有正确解，全部得到
 * （5）然后回头继续第一个皇后放第二列，后面继续循环执行1-4步骤
 * 3.说明
 *  理论上创建一个二维数组来表示一个棋盘，但实际上可以通过算法，
 *  用一个一维数组即可解决 arr[8] = {0, 4, 7, 5, 2, 6, 1, 3}
 *  // arr[0] = 0 表示第1个皇后（第一行）放在第1列，即[0,0]
 *  // arr[1] = 4 表示第2个皇后（第二行）放在第5列，即[1,4]
  * 下标表示第几行 即第几个皇后
 *  arr[i] = val，val表示第i+1个皇后放在第i+1行的第val+1列
 * @Author xw
 * @Date 2019/9/4
 */
public class Queen8 {

    private int max;
    private int[] arr;
    private int count;

    public Queen8(int max) {
        this.max = max;
        this.arr = new int[max];
    }

    public static void main(String[] args) {
        for (int i = 0; i < 8; i++) {
            System.out.print(String.format("R%s\t", i+1));
        }
        System.out.println();
        Queen8 queen8 = new Queen8(8);
        queen8.check(0);
        System.out.println(String.format("共有%s种解法", queen8.count));
    }

    /**
     * 表示第n个皇后
     * @param n
     */
    private void check(int n) {
        if (n == max) { // 全部放置完毕
            count++;
            print();
            return;
        }
        for (int i = 0; i < max; i++) {
            // 先把当前的皇后放该行第一列[i,0]
            arr[n] = i;
            if (jundge(n)) { // 不冲突
                // 接着放
                check(n + 1);
            }
            // 如果冲突，就继续执行arr[n] = i；即将第n个皇后，放置在本行的后移的一个位置
        }
    }

    /**
     * 表示第n个皇后
     * 用一个一维数组即可解决 arr[8] = {0, 4, 7, 5, 2, 6, 1, 3}
     * // arr[0] = 0 表示第1个皇后（第一行）放在第1列，即[0,0]
     * // arr[1] = 4 表示第2个皇后（第二行）放在第5列，即[1,4]
     * @param n
     * @return
     */
    private boolean jundge(int n) {
        // 任意两个位置不能处于同一行、同一列或同一斜线
        for (int i = 0; i < n; i++) { // max -> n
            if (arr[i] == arr[n] || (Math.abs(n-i) == Math.abs(arr[n]-arr[i]))) {
                // 同一行（i == n不需要判断，i不会重复）
                // 同一列（arr[i] == arr[n]）
                // 同一斜线（n=行+1、arr[n]=列+1 =》 n=i+1、arr[n]=arry[i]+1 =》 n-i = arr[n]-arr[i]），有可能为负数，所以要取绝对值
                return false;
            }
        }
        return true;
    }

    private void print() {
        for (int i = 0; i < max; i++) {
            System.out.print(String.format("%s\t", arr[i]));
        }
        System.out.println();
    }
}
