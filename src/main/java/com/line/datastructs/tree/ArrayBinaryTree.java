package com.line.datastructs.tree;

/**
 * @desc 顺序二叉树
 * @Author xw
 * @Date 2019/9/12
 */
public class ArrayBinaryTree {

    private int[] arr;

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public void preOrder() {
        preOrder(0);
    }

    public void infixOrder() {
        infixOrder(0);
    }

    // 前序遍历：父、左、右
    // 思路分析：
    // *  n: 表示二叉树中的第几个元素（按0开始编号）
    // *  （1）第n个元素的左子节点为2*n + 1
    // *  （2）第n个元素的右子节点为2*n + 2、
    // *  （3）第n个元素的父节点为arr[(n-1)/2]
    public void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能遍历！");
            return;
        }
        System.out.print(arr[index] + "\t");
        if ((2 * index + 1) < arr.length) {
            preOrder(2 * index + 1);
        }
        if ((2 * index + 2) < arr.length) {
            preOrder(2 * index + 2);
        }
    }
    // 中序
    public void infixOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能遍历！");
            return;
        }
        if ((2 * index + 1) < arr.length) {
            infixOrder(2 * index + 1);
        }
        System.out.print(arr[index] + "\t");
        if ((2 * index + 2) < arr.length) {
            infixOrder(2 * index + 2);
        }
    }

    public void postOrder() {
        postOrder(0);
    }
    public void postOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能遍历！");
            return;
        }
        if ((2 * index + 1) < arr.length) {
            postOrder(2 * index + 1);
        }
        if ((2 * index + 2) < arr.length) {
            postOrder(2 * index + 2);
        }
        System.out.print(arr[index] + "\t");
    }
}
