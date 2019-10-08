package com.line.algorithm;

import java.util.Arrays;

/**
 * @desc 迪杰斯特拉算法
 * 案例：最短路径问题
 * 1. 战争时期，胜利乡有7个村庄(A,B,C,D,E,F,G)，现在有6个邮差，从G点出发，需要分别把邮件分别送到A,B,C,D,E,F 六个村庄
 * 2. 各个村庄的距离用边线表示（权），比如A-B距离5公里
 * 3. 问：如何计算最短距离
 *
 * @Author xw
 * @Date 2019/10/8
 */
public class DijkstraAlgorithm {
    public static void main(String[] args) {

        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0] = new int[]{N,5,7,N,N,N,2};
        matrix[1] = new int[]{5,N,N,9,N,N,3};
        matrix[2] = new int[]{7,N,N,N,8,N,N};
        matrix[3] = new int[]{N,9,N,N,N,4,N};
        matrix[4] = new int[]{N,N,8,N,N,5,4};
        matrix[5] = new int[]{N,N,N,4,5,N,6};
        matrix[6] = new int[]{2,3,N,N,4,6,N};
        // 创建Graph对象
        Graph graph = new Graph(vertex, matrix);
        graph.showGraph();
        // 测试迪杰斯特拉算法
        graph.dsj(6); // G
        graph.showDijkstra();
    }
}

class Graph {
    private char[] vertex;
    private int[][] matrix;
    private VisitedVertex vv; // 已经访问过的顶点的集合

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    // 显示结果
    public void showDijkstra() {
        vv.show();
    }

    // 显示图
    public void showGraph() {
        for (int[] link : matrix) {
            System.out.println(Arrays.toString(link));
        }
    }

    public void dsj(int index) {
        vv = new VisitedVertex(vertex.length, index);
        update(index); // 更新index顶点到周围顶点的距离和前驱顶点
        for (int j = 0; j < vertex.length; j++) {
            index = vv.updateArr(); // 选择并返回新的访问顶点
            update(index); // 更新index顶点到周围顶点的距离和前驱顶点
        }
    }

    private void update(int index) {
        int len;
        // 遍历邻接矩阵
        for (int j = 0; j < matrix[index].length; j++) {
            // len = 出发顶点到index顶点的距离 + 从index顶点到j顶点的距离
            len = vv.getDis(index) + matrix[index][j];
            // 如果j顶点没有被访问过，并且len小于出发顶点到j顶点的距离，就需要更新
            if (!vv.in(j) && len < vv.getDis(j)) {
                vv.updatePre(j, index); // 更新j顶点的前驱为index顶点
                vv.updateDis(j, len); // 更新出发顶点到j顶点的距离
            }
        }
    }
}
class VisitedVertex {
    int[] alread_arr;
    int[] pre_visited;
    int[] dis;

    public VisitedVertex(int length, int index) {
        this.alread_arr = new int[length];
        this.pre_visited = new int[length];
        this.dis = new int[length];
        // 初始化，dis数组
        Arrays.fill(dis, 65535);
        this.dis[index] = 0;
    }

    /**
     * 判断index顶点是否被访问过
     * @param index
     * @return 如果访问过，返回true，否则false
     */
    public boolean in(int index) {
        return alread_arr[index] == 1;
    }

    /**
     * 更新出发顶点到index顶点的距离
     * @param index
     * @param len
     */
    public void updateDis(int index, int len) {
        dis[index] = len;
    }

    /**
     * 更新pre这个顶点的前驱顶点为index顶点
     * @param pre
     * @param index
     */
    public void updatePre(int pre, int index) {
        pre_visited[pre] = index;
    }

    /**
     * 返回出发顶点到index顶点的距离
     * @param index
     * @return
     */
    public int getDis(int index) {
        return dis[index];
    }

    /**
     * 继续选择并返回新的访问顶点，比如这时的G完后，就是A点作为新的访问顶点（注意不是出发顶点）
     * @return
     */
    public int updateArr() {
        int min = 65535, index = 0;
        for (int i = 0; i < alread_arr.length; i++) {
            if (alread_arr[i] == 0 && dis[i] < min) {
                min = dis[i];
                index = i;
            }
        }
        alread_arr[index] = 1;
        return index;
    }

    // 显示最后的结果
    public void show() {
        System.out.println("========================");
        for (int i : alread_arr) {
            System.out.print(i + " ");
        }
        System.out.println();
        // 输出pre_visited
        for (int i : pre_visited) {
            System.out.print(i + " ");
        }
        System.out.println();
        // 输出dis
        for (int i : dis) {
            System.out.print(i + " ");
        }
        System.out.println();
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int count = 0;
        for (int i : dis) {
            if (i != 65535) {
                System.out.print(vertexs[count] + "(" + i + ")");
            } else {
                System.out.print("N ");
            }
            count++;
        }
    }
}
