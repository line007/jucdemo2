package com.line.datastructs.tree.huffmantree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @desc 赫夫曼编码
 * @Author xw
 * @Date 2019/9/16
 */
public class HuffmanCode {
    public static void main(String[] args) {
        // 13, 7, 8, 3, 29, 6, 1
        Integer[] arr = {13, 7, 8, 3, 29, 6, 1};
        List<Node> nodes = new ArrayList<>();
        Arrays.stream(arr).forEach(m -> nodes.add(new Node(m)));
        Node root = createHuffmanTree(nodes);
        root.preOrder();
    }

    // 创建赫夫曼树的方法
    private static Node createHuffmanTree(List<Node> nodes) {
        // 循环（最后只剩下一个结点）
        while (nodes.size() > 1) {
            // （1）从小到大进行排序，将每一个数据（每个结点）可以看成是一颗最简单的二叉树
            Collections.sort(nodes);
            // （2）取出根节点权值最小的两颗二叉树
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            // 创建一个新的二叉树，没有data，只有权值
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            // （3）从list中删除已处理过的结点
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            // （4）将parent加入到list
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    private static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("赫夫曼树为空！");
        }
    }
}
