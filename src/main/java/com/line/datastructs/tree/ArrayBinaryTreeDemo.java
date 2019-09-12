package com.line.datastructs.tree;

/**
 * @desc 顺序二叉树（只考虑完全二叉树）
 * 思路分析：
 *  n: 表示二叉树中的第几个元素（按0开始编号）
 *  （1）第n个元素的左子节点为2*n + 1
 *  （2）第n个元素的右子节点为2*n + 2、
 *  （3）第n个元素的父节点为arr[(n-1)/2]
 * 案例：
 *  给个数组：{1, 2, 3, 4, 5, 6, 7}，要求以前序遍历的方式遍历，结果为：1,2,4,5,3,6,7
 * @Author xw
 * @Date 2019/9/12
 */
public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
        arrayBinaryTree.preOrder(); // 前序：1,2,4,5,3,6,7
        System.out.println();
        arrayBinaryTree.infixOrder(); // 中序：4,2,5,1,6,3,7
        System.out.println();
        arrayBinaryTree.postOrder(); // 后序：4,5,2,6,7,3,1
    }
}
