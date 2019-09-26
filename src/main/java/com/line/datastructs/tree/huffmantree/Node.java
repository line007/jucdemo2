package com.line.datastructs.tree.huffmantree;

/**
 * @desc Node
 * @Author xw
 * @Date 2019/9/16
 */
public class Node implements Comparable<Node> {
    Byte data;
    Integer weight; // 结点的权值
    Node left;
    Node right;

    public Node(Byte data, Integer weight) {
        this.data = data;
        this.weight = weight;
    }

    public Node() {

    }

    public Node(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    // 前序遍历（父-左-右）
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

}
