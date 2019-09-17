package com.line.datastructs.tree.avltree;

import java.util.Arrays;

/**
 * @desc 平衡二叉树（AVL树）
 *  由于二叉排序树可能会出现问题，如{1,2,3,4,5,6}，就变成单链表了，引出解决方案：平衡二叉树
 * （1）左旋转
 *      条件：当右子树高度-左子树高度>1
 *      步骤：
 *          1）将A节点的右节点的左节点，指向A节点
 *          2）将A节点的右节点，指向A节点的右节点的左节点
 *      思路分析：
 *          1）创建一个新节点newNode，并设置值为当前节点的值：newNode.value = value
 *          2）把新节点的左子树设置为当前节点的左子树：newNode.left = left
 *          3）把当前节点的值替换为右子节点的值
 *          4）把当前节点的右子树设置成右子树的右子树
 *          5）把当前节点的左子树设置成新的节点
 *      案例：
 *          {4,3,6,5,7,8}
 * （2）右旋转（与左旋转原理一样）
 * （3）双旋转
 *      思路分析：
 *          1）当符合右旋转的条件时，
 *          1.1）如果它的左子树的右子树高度大于它的左子树的高度
 *          1.2）先对当前这个节点的左节点进行左旋转
 *          1.3）再对当前结点进行右旋转的操作即可
 *          2）当符合左旋转的条件时（与符合右旋转的条件同理）
 * @Author xw
 * @Date 2019/9/17
 */
public class AVLTreeDemo {
    public static void main(String[] args) {

        // 左旋转
//        int[] arr = {4, 3, 6, 5, 7, 8};
        // 右旋转
//        int[] arr = {10, 12, 8, 9, 7, 6};
        // 双旋转
        int[] arr = {10, 11, 7, 6, 8, 9};
//        int[] arr = {2, 1, 6, 5, 7, 3};
        AVLTree avlTree = new AVLTree();
        Arrays.stream(arr).forEach(m -> avlTree.add(new Node(m)));

        System.out.println("---中序遍历---");
        avlTree.infixOrder();

        System.out.println("---平衡处理---");
        System.out.println("树的高度：" + avlTree.getRoot().height());
        System.out.println("左子树的高度：" + avlTree.getRoot().leftHeight());
        System.out.println("右子树的高度：" + avlTree.getRoot().rightHeight());
    }
}
