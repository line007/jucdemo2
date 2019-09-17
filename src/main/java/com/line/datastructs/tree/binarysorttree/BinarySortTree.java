package com.line.datastructs.tree.binarysorttree;

/**
 * @desc 二叉排序树
 * @Author xw
 * @Date 2019/9/16
 */
public class BinarySortTree {

    private Node root;

    public BinarySortTree() {

    }

    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    // 中序遍历
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉排序树为空，不能遍历！");
        }
    }

    /**
     * 排序树删除：（三种情况）
     * 情况一：删除叶子节点（比如：2,5,9,12）
     *     （1）先找到要删除的节点targetNode
     *     （2）找到targetNode的父节点
     *     （3）确定targetNode是parent的左子节点还是右子节点
     *     （4）根据前面的情况来删除：
     *             左子结点 -> parent.left = null
     *             右子结点 -> parent.right = null
     * 情况二：删除只有一颗子树的节点（比如：1）
     *     （1）先找到要删除的节点targetNode
     *     （2）找到targetNode的父节点
     *     （3）确定targetNode的子节点有左节点还右节点
     *     （4）确定targetNode是parent的左子节点还是右子节点
     *     （5）如果targetNode有左子节点
     *         5.1 如果targetNode是parent的左子节点 -> parent.left = targetNode.left
     *         5.2 如果targetNode是parent的右子节点 -> parent.right = targetNode.left
     *     （6）如果targetNode有右子节点
     *         6.1 如果targetNode是parent的左子节点 -> parent.left = targetNode.right
     *         6.2 如果targetNode是parent的右子节点 -> parent.right = targetNode.right
     * 情况三：删除有两颗子树的节点（比如：7, 3, 10）
     *     （1）先找到要删除的节点targetNode
     *     （2）找到targetNode的父节点
     *     （3）从targetNode的右子树找到最小的结点
     *     （4）用一个临时变量，将最小结点值保存temp=12
     *     （5）删除该最小节点
     *     （6）targetNode=temp
     * @param value
     */
    public void delNode(int value) {
        if (root == null) {
            return;
        }

        Node targetNode = search(value);
        if (targetNode == null) {
            return;
        }
        if (root.left == null && root.right == null) { // 删除根节点
            root = null;
            return;
        }

        Node parent = searchParent(value);
        if (targetNode.left == null && targetNode.right == null) {
            // 情况一：删除叶子节点（比如：2,5,9,12）
            //     *     （1）先找到要删除的节点targetNode
            //     *     （2）找到targetNode的父节点
            //     *     （3）确定targetNode是parent的左子节点还是右子节点
            //     *     （4）根据前面的情况来删除：
            //     *             左子结点 -> parent.left = null
            //     *             右子结点 -> parent.right = null
            if (parent.left != null && parent.left.value == value) {
                parent.left = null;
            } else if (parent.right != null && parent.right.value == value) {
                parent.right = null;
            }
        } else if (targetNode.left != null && targetNode.right != null) {
            // * 情况三：删除有两颗子树的节点（比如：7, 3, 10）
            //     *     （3）从targetNode的右子树找到最小的结点
            //     *     （4）用一个临时变量，将最小结点值保存temp=12
            //     *     （5）删除该最小节点
            //     *     （6）targetNode=temp
            int minVal = delRightTreeMin(targetNode.right);
            targetNode.value = minVal;
        } else {
            // * 情况二：删除只有一颗子树的节点（比如：1）
            if (targetNode.left != null) { // （5）如果targetNode有左子节点
                if (parent.left != null && parent.left.value == value) {
                    parent.left = targetNode.left;
                } else if (parent.right != null && parent.right.value == value) {
                    parent.right = targetNode.left;
                }
            } else { // （6）如果targetNode有右子节点
                if (parent.left != null && parent.left.value == value) {
                    parent.left = targetNode.right;
                } else if (parent.right != null && parent.right.value == value) {
                    parent.right = targetNode.right;
                }
            }
        }
    }

    /**
     *
     * @param node 传入的结点（当前二叉排序树的根节点）
     * @return 返回以node为根节点的二叉排序树的最小结点的值
     */
    private int delRightTreeMin(Node node) {
        Node target = node;
        // 循环左子树就能得到最小节点
        while (target.left != null) {
            target = target.left;
        }
        delNode(target.value);
        return target.value;
    }

    private Node searchParent(int value) {
        if (root != null) {
            return root.searchParent(value);
        } else {
            return null;
        }
    }

    public Node search(int value) {
        if (root != null) {
            return root.infixOrderSearch(value);
        } else {
            return null;
        }
    }
}
