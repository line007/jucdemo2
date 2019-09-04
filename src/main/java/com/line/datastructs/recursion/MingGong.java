package com.line.datastructs.recursion;

/**
 * @desc 迷宫问题
 * 1.初始化一个8行7列的矩阵 map[8][7]
 * 2.假设：
 * （1）三种类型：0-还没有走,1-档板,2-可以走（已走）,3-走不通
 * （2）策略：下-》右-》下-》左
 * （3）初始值：周围都是1
 * 3.过程分析：
 * （1）已经找到（确定找到的条件：第7行6列即为找到 =》 [6,5]=2）
 * （2）还没有找到按策略去找
 *
 * @Author xw
 * @Date 2019/9/3
 */
public class MingGong {
    public static void main(String[] args) {
        // 1.初始化一个8行7列的矩阵arr[8][7]
        int[][] map = new int[8][7];
        // 2.初始值：周围都是1
        // 第1行和第8行设置档板
        for (int j = 0; j < 7; j++) {
            map[0][j] = 1;
            map[7][j] = 1;
        }
        // 第1列和第7列设置档板
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        // 输出矩阵
        print(map);
        System.out.println("-------------------------");
        // 设置档板 [3,1]=1,[3,2]=1
        map[3][1] = 1;
        map[3][2] = 1;
        print(map);

        // 策略：下-》右-》上-》左
        setWay(map, 1, 1); // 从[1,1]这个位置开始
        System.out.println("策略:下-》右-》上-》左 =>");
        print(map);
    }

    private static boolean setWay(int[][] map, int i, int j) {
        // （1）已经找到（确定找到的条件：第7行6列即为找到 =》 [6,5]=2）
        if (map[6][5] == 2) {
            return true;
        }
        // （2）还没有找到，按策略下-》右-》上-》左找
        if (map[i][j] == 0) { // 1) 未走
            map[i][j] = 2; // 改标识位
            if (setWay(map, i + 1, j)) {
                return true;
            } else if (setWay(map, i, j + 1)) {
                return true;
            } else if (setWay(map, i - 1, j)) {
                return true;
            } else if (setWay(map, i, j - 1)) {
                return true;
            } else {
                // 如果还是没有找到，说明是死路
                map[i][j] = 3; // 重置标识位
                return false;
            }
        } else { // 2) 已走，可能是1，2，3
            return false;
        }
    }

    private static void print(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
