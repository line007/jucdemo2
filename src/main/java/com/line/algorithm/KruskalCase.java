package com.line.algorithm;

import java.util.Arrays;

/**
 * @desc 克鲁斯卡尔算法
 * 案例：公交车问题
 * 1. 某城市新增7个站点，A,B,C,D,E,F,G,现在需要修路7个站点连通
 * 2. 各个站点距离用连线表示，比如A-B距离12公里
 * 3. 问：如何修路保证各个站点都能连通，并且总的修建公路总里程最短
 * @Author xw
 * @Date 2019/10/8
 */
public class KruskalCase {
    private static final int INF = Integer.MAX_VALUE;
    private char[] vertexs;
    private int[][] matrix;
    private int edgeNums; // 边的数量

    public KruskalCase(char[] vertexs,int[][] matrix ) {
        this.vertexs = vertexs;
        this.matrix = matrix;
        // 统计边
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i + 1; j < vertexs.length; j++) { // 每次少一条边，所以是i+1
                if (this.matrix[i][j] != INF) {
                    edgeNums++;
                }
            }
        }
    }

    public static void main(String[] args) {
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = {
                     /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/{ 0,   12, INF,  INF, INF, 16,  14 },
                /*B*/{ 12,  0,   10,  INF, INF, 7,   INF},
                /*C*/{ INF, 10,  0,   3,    5,  6,   INF },
                /*D*/{ INF, INF, 3,   0,    4,  INF, INF },
                /*E*/{ INF, INF, 5,   4,    0,  2,   8 },
                /*F*/{ 16,  7,   6,   INF,  2,  0,   9 },
                /*G*/{ 14,  INF, INF, INF,  8,  9,   0 }
        };
        // 创建KruskalCase对象实例
        KruskalCase kruskalCase = new KruskalCase(vertexs, matrix);
        //
        kruskalCase.print();
        kruskalCase.kruskal();
    }

    private void kruskal() {
        int index = 0;
        // 用于保存“已有最小生成树”中的每个顶点在最小生成树中的终点
        int[] ends = new int[edgeNums];
        // 创建结果数组，保存最后的最小生成树
        EData[] rets = new EData[edgeNums];
        //
        EData[] edges = getEdges();
        System.out.println("图的边的集合=" + Arrays.toString(edges) + " 共" + edges.length); // 12

        // 排序
        sortEdges(edges);
        System.out.println("图的边的集合=" + Arrays.toString(edges) + " 共" + edges.length); // 12

        // 遍历edges数组，将边添加到最小生成树中时，判断是准备加入的边是否形成了回路，如果没有，就加入rets，否则不能加入
        for (int i = 0; i < edgeNums; i++) {
            // 获取第i条边的起始位置
            int p1 = getPosition(edges[i].start);
            int p2 = getPosition(edges[i].end);
            // 获取p1、p2顶点的终点
            int m = getEnd(ends, p1);
            int n = getEnd(ends, p2);
            if (m != n) { // 没有形成回路
                ends[m] = n; // 设置m最小生成树中的终点，<E,F> [0,0,0,0,5,0,0,0,0,0,0]
                //ends[n] = n;
                rets[index++] = edges[i];
            }
        }

        // 统计并打印最小生成树，输出rets
        System.out.println("最小生成树");
        for (int i = 0; i < index; i++) {
            System.out.println(rets[i]);
        }
    }

    /**
     *
     * @param ch 顶点的值，比如'A','B'
     * @return 返回ch顶点对应的下标，如果找不到，返回-1
     */
    private int getPosition(char ch) {
        for (int i = 0; i < vertexs.length; i++) {
            if (vertexs[i] == ch) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 对边进行排序处理，冒泡排序
     * @param edges
     */
    private void sortEdges(EData[] edges) {
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                if (edges[j].weight > edges[j+1].weight) { // 交换
                    EData tmp = edges[j];
                    edges[j] = edges[j+1];
                    edges[j+1] = tmp;
                }
            }
        }
    }

    /**
     * 获取图中的边，放到Edata数组中，后面我们需要遍历该数组
     * 是通过matrix 邻接矩阵来获取
     * EData[] 形式[['A','B',12], ['B','F',7]...]
     * @return
     */
    private EData[] getEdges() {
        int index = 0;
        EData[] edges = new EData[edgeNums];
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i+1; j < vertexs.length; j++) { // 每次少一条边，所以是i+1
                if (matrix[i][j] != INF) {
                    edges[index++] = new EData(vertexs[i], vertexs[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }

    /**
     * 功能：获取下标为i的顶点的终点（），用于后面判断两个顶点的终点是否相同
     * @param ends 数组就是记录了各个顶点对应的终点是哪个，ends数组是在遍历过程中，逐步形成的
     * @param i 表示传入顶点对应的下标
     * @return 返回的就是这个下标为i的这个顶点对应的终点的下标，一会回头还有来理解
     */
    private int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }

    private void print() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == INF) {
                    System.out.print("INF\t\t\t\t");
                } else {
                    System.out.print(matrix[i][j] + "\t\t\t\t");
                }
            }
            System.out.println();
        }
    }
}
class EData {
    char start;
    char end;
    int weight;

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    public char getStart() {
        return start;
    }

    public void setStart(char start) {
        this.start = start;
    }

    public char getEnd() {
        return end;
    }

    public void setEnd(char end) {
        this.end = end;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "<" +
                start +
                ", " + end +
                "> = " + weight;
    }
}
