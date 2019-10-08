package com.line.algorithm;

import java.util.Arrays;

/**
 * @desc 弗洛伊德算法
 * @Author xw
 * @Date 2019/10/8
 */
public class FloydAlgorithm {

    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0] = new int[]{0, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, 0, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, 0, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, 0, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, 0, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, 0, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, 0};
        FloydGraph graph = new FloydGraph(vertex.length, matrix, vertex);
        graph.floyd();
        graph.show();

    }


}

class FloydGraph {
    private char[] vertex; // 存放顶点的数组
    private int[][] dis; // 保存，从各个顶点出发到其它顶点的距离，最后的结果也是保留在该数组
    private int[][] pre; // 保存到达目标顶点的前驱顶点

    public FloydGraph(int length, int[][] matrix, char[] vertex) {
        this.vertex = vertex;
        this.dis = matrix;
        this.pre = new int[length][length];
        // 对pre数组进行初始化，注意存放的是前驱顶点的下标
        for (int i = 0; i < length; i++) {
            Arrays.fill(pre[i], i);
        }
    }

    // 弗洛伊德算法
    public void floyd() {
        int len; // 变量保存距离
        // 从中间顶点遍历，就是中间顶点的下标[A,B,C,D,E,F,G]
        for (int k = 0; k < dis.length; k++) {
            // 从i顶点出发，[A,B,C,D,E,F,G]
            for (int i = 0; i < dis.length; i++) {
                // 到达j顶点，[A,B,C,D,E,F,G]
                for (int j = 0; j < dis.length; j++) {
                    len = dis[i][k] + dis[k][j]; // 求出从i顶点出发，经过k中间顶点，到达j顶点距离
                    if (len < dis[i][j]) { // 如果len小于dis[i][j]
                        dis[i][j] = len; // 更新距离
                        pre[i][j] = pre[k][j]; // 更新前驱顶点
                    }
                }
            }
        }
    }

    // show
    public void show() {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        for (int k = 0; k < dis.length; k++) {
            for (int i = 0; i < dis.length; i++) {
                System.out.print(vertex[pre[k][i]] + " ");
            }
            System.out.println();
            // 最短路径
            for (int i = 0; i < dis.length; i++) {
                System.out.print("<" + vertex[k] + "," + vertex[i] + ">=" + dis[k][i] + ",");
            }
            System.out.println();
        }
        System.out.println();
    }

}
