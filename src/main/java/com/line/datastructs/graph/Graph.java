package com.line.datastructs.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @desc 图
 * 基本概念：顶点、边、路径、无向图、有向图、带权图
 * 案例：(实现如下的效果)
 *      A   B   C   D   E
 *  A   0   1   1   0   0
 *  B   1   0   1   1   1
 *  C   1   1   0   0   0
 *  D   0   1   0   0   0
 *  E   0   1   0   0   0
 *
 *  （1）用图的方式实现上述效果图
 *      思路分析：
 *          1）5个顶点：A\B\C\D\E
 *          2）1-表示连通，0-表示不连通
 *  （2）用深度优先方式遍历
 *      思路分析：
 *          1）访问初始结点，并标记结点v为已访问
 *          2）查找结点v的第一个邻接结点
 *          3）若w存在，则继续执行步骤4，如果w不存在，则回到步骤1，将从v的下一个结点继续
 *          4）若w未被访问，对w进行深度优先遍历递归（即把w当作别一个v，然后进行步骤123）
 *          5）查找结点v的w邻接结点的下一个结点，转到步骤3
 *  （3）用广度优先方式遍历
 *      思路分析：
 *          1）访问初始结点v并标记结点v已访问
 *          2）结点v入队列
 *          3）当队列非空时，继续执行，否则算法结束
 *          4）出队列，取得队头结点u
 *          5）查找结点u的第一个邻接结点w
 *          6）若结点u的邻接结点w不存在，则转到步骤3；否则执行以下三个步骤：
 *              6.1 若结点w尚未被访问，则访问结点w并标记为已访问
 *              6.2 结点w入队列
 *              6.3 查找结点u的继w邻接结点后的下一个邻接结点w，转到步骤6
 *
 * @Author xw
 * @Date 2019/9/26
 */
public class Graph {

    private List<String> vertexList; // 顶点
    private int[][] edges; // 存储图对应的邻结矩阵
    private int numOfEdges; // 表示边的数目
    private boolean[] isVisited; // 边是否被访问过

    public static void main(String[] args) {

        // 创建图
        Graph graph = new Graph(5);
        // （1）添加顶点
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");
        graph.insertVertex("E");
        // （2）添加边
        // A-B,A-C,B-C,B-D,B-E
        graph.insertEdge(0,1, 1);
        graph.insertEdge(0,2, 1);
        graph.insertEdge(1,0, 1);
        graph.insertEdge(1,2, 1);
        graph.insertEdge(1,3, 1);
        graph.insertEdge(1,4, 1);

        // show一把
        graph.showGraph();

        // 深度优先思路分析：
        // 1）访问初始结点，并标记结点v为已访问
        // 2）查找结点v的第一个邻接结点
        // 3）若w存在，则继续执行步骤4，如果w不存在，则回到步骤1，将从v的下一个结点继续
        // 4）若w未被访问，对w进行深度优先遍历递归（即把w当作别一个v，然后进行步骤123）
        // 5）查找结点v的w邻接结点的下一个结点，转到步骤3
        System.out.println("=========== 深度优先  ==========");
        graph.dfs();
        System.out.println();

        // 广度优先思路分析：思路分析：
        // 1）访问初始结点v并标记结点v已访问
        // 2）结点v入队列
        // 3）当队列非空时，继续执行，否则算法结束
        // 4）出队列，取得队头结点u
        // 5）查找结点u的第一个邻接结点w
        // 6）若结点u的邻接结点w不存在，则转到步骤3；否则执行以下三个步骤：
        //  6.1 若结点w尚未被访问，则访问结点w并标记为已访问
        //  6.2 结点w入队列
        //  6.3 查找结点u的继w邻接结点后的下一个邻接结点w，转到步骤6
        System.out.println("=========== 广度优先  ==========");
        graph.bfs();
    }

    public void bfs() {
        isVisited = new boolean[numOfEdges];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }

    // 广度优先
    public void bfs(boolean[] isVisited, int i) {
        // 1）访问初始结点v并标记结点v已访问
        System.out.print(getValueByIndex(i) + "->");
        isVisited[i] = true;
        // 2）结点v入队列
        LinkedList queue = new LinkedList();
        queue.addLast(i);
        // 3）当队列非空时，继续执行，否则算法结束
        Integer u;
        Integer w;

        while (!queue.isEmpty()) {
            // 4）出队列，取得队头结点u
            u = (Integer) queue.removeFirst();
            // 5）查找结点u的第一个邻接结点w
            w = getFirstNeighbor(u);
            while (w != -1) {
                // 6.1 若结点w尚未被访问，则访问结点w并标记为已访问
                if (!isVisited[w]) {
                    System.out.print(getValueByIndex(w) + "->");
                    isVisited[w] = true;
                    // 6.2 结点w入队列
                    queue.addLast(w);
                }
                // 6.3 查找结点u的继w邻接结点后的下一个邻接结点w，转到步骤6
                w = getNextNeighbor(u, w);
            }
        }
    }

    // 深度优先
    public void dfs() {
        isVisited = new boolean[numOfEdges];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    /**
     *
     * @param isVisited 访问标识
     * @param i 从第0个元素开始
     */
    public void dfs(boolean[] isVisited, int i) {
        // 1）访问初始结点，并标记结点v为已访问
        System.out.print(getValueByIndex(i) + "->");
        isVisited[i] = true;
        // 2）查找结点v的第一个邻接结点
        int w = getFirstNeighbor(i);
        // 3）若w存在，则继续执行步骤4，如果w不存在，则回到步骤1，将从v的下一个结点继续
        while (w != -1) {
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            // 如果w已经被访问过
            w = getNextNeighbor(i, w);
        }
        // 4）若w未被访问，对w进行深度优先遍历递归（即把w当作别一个v，然后进行步骤123）
    }

    /**
     * 根据前一个邻接结点的下标来获取下一个邻接结点
     * @param v1
     * @param v2
     * @return
     */
    private int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) { // v2 + 1 => 从v2的下一个开始
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    /**
     * 查找结点v的第一个邻接结点
     * @param index 结点v的下标
     * @return
     */
    private int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    private int getNumOfVertex() {
        return vertexList.size();
    }

    private String getValueByIndex(int i) {
        return vertexList.get(i);
    }


    public Graph(int n) {
        vertexList = new ArrayList<>(n);
        edges = new int[n][n];
        numOfEdges = 0;
    }

    // 插入结点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }
    // 插入边
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges ++;
    }
    // 显示图
    public void showGraph() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }
}
