package com.line.algorithm;

/**
 * @desc 动态规划算法案例：背包问题
 * 思路分析：
 * （1）假设：
 * 用w[i],v[i]来确定是否需要将该物品放入背包中；
 * 即对于给定的n个物品，设v[i],w[i]分别为第i个物品的价值和重量，C为背包的容量。
 * 再令v[i][j] 表示在前i个物品中能够装入容量j的背包的最大价值。则我们有下面的结果：
 * （2）结论：
 * 1）当v[i][0]=v[0][j]=0; // 表示填入表 第一行和第一列是0
 * 2）当w[i]>j时；v[i][j]=v[i-1][j] // 当准备加入新增的商品的容量大于当前背包的容量时，就直接使用上一个单元格的装入策略
 * 3）当j>=w[i]时；v[i][j]=max{v[i-1][j], v[i]+v[i-1][j-w[i]]}
 * // 当准入的新增的商品的容量小于等于当前背包的容量，装入方式：
 * v[i-1][j]：就是上一个单元格的装入的最大值
 * v[i]：表示当前商品的价值
 * v[i-1][j-w[i]]：装入i-1商品，到剩余空间j-w[i]的最大值
 * 当j>=w[i]时：v[i][j] = max{v[i-1][j], v[i-1][j-w[i]]}
 * <p>
 * 案例：
 * 物品      重量  价格
 * 吉他（G）   1   1500
 * 音响（S）   4   3000
 * 电脑（L）   3   2000
 * @Author xw
 * @Date 2019/9/27
 */
public class KnapsackProblem {
    public static void main(String[] args) {
        int[] w = {1, 4, 3}; // 物品重量
        int[] val = {1500, 3000, 2000}; // 物品价值
        int m = 4; // 背包的容量
        int n = val.length; // 物品个数
        // 创建二维数据
        int[][] v = new int[n + 1][m + 1];
        // 1）当v[i][0]=v[0][j]=0; // 表示填入表 第一行和第一列是0
        for (int i = 0; i < v.length; i++) {
            v[0][i] = 0; // 第一列为0
        }
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0; // 第一行为0
        }

        int[][] path = new int[n + 1][m + 1];
        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[0].length; j++) { // 不处理第1列
                // 当w[i]>j时；v[i][j]=v[i-1][j] // 当准备加入新增的商品的容量大于当前背包的容量时，就直接使用上一个单元格的装入策略
                if (w[i - 1] > j) {
                    v[i][j] = v[i - 1][j];
                } else {
                    // 当j>=w[i]时；v[i][j]=max{v[i-1][j], v[i]+v[i-1][j-w[i]]}
                    // v[i-1][j]：就是上一个单元格的装入的最大值
                    // v[i]：表示当前商品的价值
                    // v[i-1][j-w[i]]：装入i-1商品，到剩余空间j-w[i]的最大值
                    // 当准入的新增的商品的容量小于等于当前背包的容量，装入方式：
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) { // w[i]->w[i-1]替换?
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        // 把当前的情况记录到path
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }

        // 输出一把
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j] + "\t");
            }
            System.out.println();
        }

        System.out.println("========================");

        /*for (int i = 0; i < path.length; i++) {
            for (int j = 0; j < path[i].length; j++) {
                if (path[i][j] == 1) {
                    System.out.println(String.format("第%d个商品放入背包", i));
                }
            }
        }*/
        // 其实我们只需要最后的放入
        int i = path.length - 1;
        int j = path[0].length - 1;
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                System.out.println(String.format("第%d个商品放入到背包", i));
                j -= w[i - 1];
            }
            i--;
        }
    }
}
