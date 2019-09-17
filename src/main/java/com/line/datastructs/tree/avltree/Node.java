package com.line.datastructs.tree.avltree;

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

        // 左旋转：当右子树高度-左子树高度>1
        if (rightHeight() - leftHeight() > 1) {
            if (this.right.rightHeight() > this.right.leftHeight()) {
                this.right.rightRotate();
                leftRotate();
            } else {
                leftRotate();
            }
            return;
        }
        // 右旋转：当左子树高度-右子树的高度>1
        if (leftHeight() - rightHeight() > 1) {
            // 1）当符合右旋转的条件时，
            // 2）如果它的左子树的右子树高度大于它的左子树的高度
            // 3）先对当前这个节点的左节点进行左旋转
            // 4）再对当前结点进行右旋转的操作即可
            if (this.left.rightHeight() > this.left.leftHeight()) {
                this.left.leftRotate();
                rightRotate();
            } else {
                rightRotate();
            }
        }
    }

    // 右旋转
    private void rightRotate() {
        // 1）创建一个新节点newNode，并设置值为当前节点的值：newNode.value = value
        Node newNode = new Node(this.value);
        newNode.right = this.right;
        newNode.left = this.left.right;
        this.value = this.left.value;
        this.left = this.left.left;
        this.right = newNode;
    }

    // 左旋转
    // 1）创建一个新节点newNode，并设置值为当前节点的值：newNode.value = value
    // 2）把新节点的左子树设置为当前节点的左子树：newNode.left = left
    // 3）把当前节点的值替换为右子节点的值
    // 4）把当前节点的右子树设置成右子树的右子树
    // 5）把当前节点的左子树设置成新的节点
    private void leftRotate() {
        // 1）创建一个新节点newNode，并设置值为当前节点的值：newNode.value = value
        Node newNode = new Node(this.value);
        // 2）把新节点的左子树设置为当前节点的左子树：newNode.left = left
        newNode.left = this.left;
        // 3）把当前节点的值替换为右子节点的值
        this.value = this.right.value;
        // 4）把当前节点的右子树设置成右子树的右子树
        this.right = this.right.right;
        // 5）把当前节点的左子树设置成新的节点
        this.left = newNode;
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

    // 左子树的高度
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    // 右子树的高度
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    /**
     * @return 返回以该节点为根结点的树的高度
     */
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }
}
