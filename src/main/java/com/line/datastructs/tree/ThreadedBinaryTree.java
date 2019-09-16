package com.line.datastructs.tree;

/**
 * @desc 线索化二叉树
 * @Author xw
 * @Date 2019/9/16
 */
public class ThreadedBinaryTree {
    private HeroNode root;
    // 前驱结点：在递归进行线索化时，pre问题要保留前一个结点
    private HeroNode pre = null;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    /**
     * 中序方式线索化
     * 原：{1, 3, 6, 8, 10. 14}
     * 中序遍历：{8, 3, 10, 1, 14, 6}
     */
    public void threadedNodes() {
        threadedNodes(root);
    }

    /**
     * 中序方式线索化（左、父、右）
     * 原：{1, 3, 6, 8, 10. 14}
     * 中序遍历：{8, 3, 10, 1, 14, 6}
     * @param node 当前需要线索化的结点
     */
    public void threadedNodes(HeroNode node) {

        if (node == null) {
            return;
        }

        // （一）线索化左子树
        threadedNodes(node.getLeft());

        // （二）处理当前结点的前驱结点
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }
        // 处理当前结点的后继结点（下一个结点的前驱节点处理!!!）
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            node.setRightType(1);
        }
        pre = node; // 处理完成后，让前驱结点后移!!!

        // （三）线索化右子树
        threadedNodes(node.getRight());
    }

}
