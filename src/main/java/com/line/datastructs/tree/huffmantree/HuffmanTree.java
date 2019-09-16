package com.line.datastructs.tree.huffmantree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @desc 赫夫曼树
 *  思路分析：
 *  （1）从小到大进行排序，将每一个数据（每个结点）可以看成是一颗最简单的二叉树
 *  （2）取出根节点权值最小的两颗二叉树
 *  （3）组成一颗新的二叉树，该新的二叉树的根节点的权值是两颗二叉树根节点权值的和
 *  （4）再将这颗新的二叉树，以根节点的权值大小再次排序，不断重复1-2-3-4步骤，
 *      直到数列中，所有的数据都被处理，就得到一颗赫夫曼树
 *  案例：
 *      {13, 7, 8, 3, 29, 6, 1}
 * @Author xw
 * @Date 2019/9/16
 */
public class HuffmanTree {
    public static void main(String[] args) {

        Integer[] arr = {13, 7, 8, 3, 29, 6, 1};
        createHuffmanTree(arr);
    }

    // 创建赫夫曼树的方法
    private static void createHuffmanTree(Integer[] arr) {
        List<Node> nodes = new ArrayList<>();
        Arrays.stream(arr).forEach(m -> nodes.add(new Node(m)));

        // 循环（最后只剩下一个结点）
        while (nodes.size() > 1) {
            // （1）从小到大进行排序，将每一个数据（每个结点）可以看成是一颗最简单的二叉树
            Collections.sort(nodes);
            //System.out.println(nodes);
            // （2）取出根节点权值最小的两颗二叉树
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parent = new Node(leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            // （3）从list中删除已处理过的结点
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            // （4）将parent加入到list
            nodes.add(parent);
        }
    }
}
