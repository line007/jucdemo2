package com.line.datastructs.tree.binarysorttree;

/**
 * @desc 节点
 * @Author xw
 * @Date 2019/9/16
 */
public class Node {

    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    public void add(Node node) {
        if (node == null) {
            return;
        }

        if (node.value < this.value) { // < 当前树的值
            if (this.left == null) { // 左边为空
                this.left = node;
            } else { // 非空，向左递归
                this.left.add(node);
            }
        } else {
            if (this.right == null) { // 右边为空
                this.right = node;
            } else { // 非空，向右递归
                this.right.add(node);
            }
        }
    }

    // 左、父、左
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    // 中序查找：左->父->右
    public Node infixOrderSearch(int value) {
        Node resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(value);
        }
        if (resNode != null) { // 找到就返回！！！
            return resNode;
        }
        //System.out.println("中序遍历"); // 打印为什么放这里？-》打印跟着父结点走才是正确的次数
        if (this.value == value) {
            return this;
        }
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(value);
        }
        return resNode;
    }

    public Node searchParent(int value) {
        if (this.left != null && this.left.value == value || this.right != null && this.right.value == value) {
            return this;
        } else {
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value);
            } else {
                return null;
            }
        }
    }
}
