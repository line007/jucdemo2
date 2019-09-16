package com.line.datastructs.tree;

/**
 * @desc 线索化二叉树
 *  案例：
 *      {1, 3, 6, 8, 10, 14}
 *  中序遍历方式：
 *      {8, 3, 10, 1, 14, 6}
 * @Author xw
 * @Date 2019/9/16
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {

        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(3, "吴用");
        HeroNode node3 = new HeroNode(6, "卢俊义");
        HeroNode node4 = new HeroNode(8, "林冲");
        HeroNode node5 = new HeroNode(10, "关胜");
        HeroNode node6 = new HeroNode(14, "张包");

        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();

        // 测试：以10号结点测试
        HeroNode leftNode = node5.getLeft();
        HeroNode rightNode = node5.getRight();
        System.out.println("10号结点的前驱节点：" + leftNode); // 3
        System.out.println("10号结点的后继节点：" + rightNode); // 1
    }
}
