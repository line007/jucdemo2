package com.line.algorithm;

import java.util.Arrays;

/**
 * @desc 普利姆算法
 * 应用案例：修路问题
 * <p>
 * 思路分析
 *  1.从<A>顶点开始处理=><A,G> 2
 *      A,C[7] A-G[2] A-B[5] =>
 *  2.<A,G>开始，将A和G顶点和他们相邻的还没有访问的顶面进行处理=> <A,G,B>
 *      A-C[7] A-B[5] G-B[3] G-F[6]
 *  3.<A,G,B>开始，将A,G,B顶点和他们相邻的还没有访问的顶面进行处理=> <A,G,B>
 *      A-C[7] G-E[4] G-F[6] B-D[9]
 *  ...
 *  4.{A,G,B,E,F,D} -> C // 第6次大循环，对应边<A,C>权值：7 => <A,G,B,E,F,D,C>
 * @Author xw
 * @Date 2019/10/4
 */
public class PrimAlgorithm {
    public static void main(String[] args) {
        char[] data = {'A','B','C','D','E','F','G'};
        int verxs = data.length;
        // 邻接矩阵
        int[][] weight = new int[][] {
                {10000,5,7,10000,10000,10000,2},
                {5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},
                {10000,10000,10000,4,5,10000,6},
                {2,3,10000,10000,4,6,10000}
        };
        // 创建MGraph对象
        MGraph graph = new MGraph(verxs);
        // 创建最小树
        MinTree minTree = new MinTree();
        minTree.createGraph(graph, verxs, data, weight);
        // 输出
        minTree.showGraph(graph);
        // 测试普利姆算法
        minTree.prim(graph, 0);
    }
}

// 最小生成树
class MinTree {

    public void prim(MGraph graph, int v) {
        int[] visited = new int[graph.verxs];
        visited[v] = 1; // 标记为已访问
        // h1,h2记录两个顶点的下标
        int h1 = -1;
        int h2 = -1;
        int minWeight = 10000; // 将minWeight初始成一个大数，后面遍历过程中，会被替换
        for (int k = 1; k < graph.verxs; k++) { // 因为有graph.verxs个顶点，普利姆算法结束后，有graph.verxs-1边

            // 这个是确定每一次生成的子圈，和哪个结点的距离最近
            for (int i = 0; i < graph.verxs; i++) { // i表示被访问过的结点
                for (int j = 0; j < graph.verxs; j++) { // j表示还没有被访问过的结点
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight) {
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            // 找到最小边
            System.out.println("边<"+ graph.data[h1] + "," + graph.data[h2] +"> 权值：" + minWeight);
            visited[h2] = 1; // 将这个点标记为已访问
            minWeight = 10000;
        }
    }

    /**
     * 创建邻接矩阵
     *
     * @param graph  图对象
     * @param verxs  图对应的顶点个数
     * @param data   图的各个顶点的值
     * @param weight 图的邻接矩阵
     */
    public void createGraph(MGraph graph, int verxs, char data[], int[][] weight) {
        int i, j;
        for (i = 0; i < verxs; i++) {
            graph.data[i] = data[i];
            for (j = 0; j < verxs; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    public void showGraph(MGraph graph) {
        for (int i = 0; i < graph.weight.length; i++) {
            System.out.println(Arrays.toString(graph.weight[i]));
        }
    }
}

class MGraph {
    int verxs; // 表示图的结点
    char[] data; // 存放结点个数
    int[][] weight; // 存放边，就是我们的邻接矩阵

    public MGraph(int verxs) {
        this.verxs = verxs;
        data = new char[verxs];
        weight = new int[verxs][verxs];
    }
}
