package com.line.datastructs.tree;

/**
 * @desc 节点
 * @Author xw
 * @Date 2019/9/12
 */
public class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    // leftType：0-指向左子树，1-指向前驱结点
    private int leftType;
    // rightType：0-指向右子树，1-指向后继结点
    private int rightType;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    // 前序查找
    // 思路分析：
    // （1）先判断当前结点的no是否等于要查找的
    // （2）如果相等，返回当前结点
    // （3）如果不等，则判断当前结点的左子结点是否为空，如果非空，则递归前序查找
    // （4）如果左递归前序查找，找到结点，则返回，否则继续判断，当前的结点的右子结点是否为空，如果不空，则向右递归前序查找
    public HeroNode preOrderSearch(int no) {
        System.out.println("前序遍历");
        if (this.no == no) {
            return this;
        }
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) { // 找到就返回！！！
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    // 中序查找：左->父->右
    public HeroNode infixOrderSearch(int no) {
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) { // 找到就返回！！！
            return resNode;
        }
        System.out.println("中序遍历"); // 打印为什么放这里？-》打印跟着父结点走才是正确的次数
        if (this.no == no) {
            return this;
        }
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    // 后序查找：左->右->父
    public HeroNode postOrderSearch(int no) {
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null) { // 找到就返回！！！
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null) { // 找到就返回！！！
            return resNode;
        }
        System.out.println("后序遍历");
        if (this.no == no) {
            return this;
        }
        return null;
    }


    // 前序遍历：父节点->左子节点->右子节点
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
//            System.out.println(this.left);
            this.left.preOrder();
        }
        if (this.right != null) {
//            System.out.println(this.right);
            this.right.preOrder();
        }
    }

    // 中序遍历：左子节点->父节点->右子节点
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    // 后序遍历：左子节点->右子节点->父节点
    public void postOrder() {
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
        System.out.println(this);
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    // 思路分析：
    // （1）由于我们的二叉树是单向的，所以我们是判断当前结点的子节点是否需要删除结点，而不能去判断当前这个节点是否需要删除结点
    // （2）如果当前结点的左子结点不为空，并且左子结点就是要删除结点，就将this.left=null
    // （3）如果当前结点的右子结点不为空，并且右子结点就是要删除结点，就将this.right=null
    // （4）如果2、3两步没有删除结点，那么我们就需要向左子树进行递归删除
    // （5）如果第4步也没有删除，则应当向右子树进行递归删除
    public void delNode(int no) {
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        // 如果2、3两步没有删除结点，那么我们就需要向左子树进行递归删除
        if (this.left != null) {
            this.left.delNode(no);
//            return; // 不需要return
        }
        if (this.right != null) {
            this.right.delNode(no);
        }
    }

    public int getNo() {
        return no;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public HeroNode getLeft() {
        return left;
    }

    public HeroNode getRight() {
        return right;
    }
}
