package com.line.datastructs.tree.binarysorttree;

/**
 * @desc 二叉排序树
 * 思路分析：
 *  1.排序树添加：
 *      （1）if（待插入节点值 > 当前节点值） -> 往右子树递归找（当右子树为空，找到了）
 *      （2）if（待插入节点值 < 当前节点值） -> 往左子树递归找（当左子树为空，找到了）
 *  2.排序树删除：（三种情况）
 *  情况一：删除叶子节点（比如：2,5,9,12）
 *      （1）先找到要删除的节点targetNode
 *      （2）找到targetNode的父节点
 *      （3）确定targetNode是parent的左子节点还是右子节点
 *      （4）根据前面的情况来删除：
 *              左子结点 -> parent.left = null
 *              右子结点 -> parent.right = null
 *  情况二：删除只有一颗子树的节点（比如：1）
 *      （1）先找到要删除的节点targetNode
 *      （2）找到targetNode的父节点
 *      （3）确定targetNode的子节点是左节点还右节点
 *      （4）确定targetNode是parent的左子节点还是右子节点
 *      （5）如果targetNode有左子节点
 *          5.1 如果targetNode是parent的左子节点 -> parent.left = targetNode.left
 *          5.2 如果targetNode是parent的右子节点 -> parent.right = targetNode.left
 *      （6）如果targetNode有右子节点
 *          6.1 如果targetNode是parent的左子节点 -> parent.left = targetNode.right
 *          6.2 如果targetNode是parent的右子节点 -> parent.right = targetNode.right
 *  情况三：删除有两颗子树的节点（比如：7, 3, 10）
 *      （1）先找到要删除的节点targetNode
 *      （2）找到targetNode的父节点
 *      （3）从targetNode的右子树找到最小的结点
 *      （4）用一个临时变量，将最小结点值保存temp=12
 *      （5）删除该最小节点
 *      （6）targetNode=temp
 *
 * 案例：
 * {7,3,10,12,5,1,9}
 * @Author xw
 * @Date 2019/9/16
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        Integer[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }
        System.out.println("----中序遍历----");
        binarySortTree.infixOrder();

        // 节点删除
        int value = 1; // 2,5,9,12 | 7, 3, 10
        binarySortTree.delNode(value);
        System.out.println("----中序遍历----");
        binarySortTree.infixOrder();
    }
}
