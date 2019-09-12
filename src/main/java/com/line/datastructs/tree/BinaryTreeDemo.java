package com.line.datastructs.tree;

/**
 * @desc 二叉树遍历
 *  案例：1-宋江、2-吴用、3-卢俊义、4-林冲
 * @Author xw
 * @Date 2019/9/12
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        // 1.前、中、后序遍历
        test_simple_order();

        // 2.查找指定节点（前、中、后序查找）
        //test_sample_order_search();

        // 3.删除节点（后序遍历为例）
        //test_del_node();
    }

    private static void test_del_node() {
        // 要求：
        // （1）如果删除的节点是叶子节点，则删除该节点
        // （2）如果删除的是非叶子节点，则删除该子树
        // 思路分析：
        // （1）由于我们的二叉树是单向的，所以我们是判断当前结点的子节点是否需要删除结点，而不能去判断当前这个节点是否需要删除结点
        // （2）如果当前结点的左子结点不为空，并且左子结点就是要删除结点，就将this.left=null
        // （3）如果当前结点的右子结点不为空，并且右子结点就是要删除结点，就将this.right=null
        // （4）如果2、3两步没有删除结点，那么我们就需要向左子树进行递归删除
        // （5）如果第4步也没有删除，则应当向右子树进行递归删除
        BinaryTree binaryTree = new BinaryTree();
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");
        root.setLeft(node2);
        root.setRight(node3);
        node3.setLeft(node5);
        node3.setRight(node4);
        binaryTree.setRoot(root);
        System.out.println("删除前--------------------");
        binaryTree.preOrder();
        //        binaryTree.delNode(5);
        binaryTree.delNode(3);
        System.out.println("删除后 --------------------");
        binaryTree.preOrder();
    }

    // 2.1 前序查找
    // 思路分析：
    // （1）先判断当前结点的no是否等于要查找的
    // （2）如果相等，返回当前结点
    // （3）如果不等，则判断当前结点的左子结点是否为空，如果非空，则递归前序查找
    // （4）如果左递归前序查找，找到结点，则返回，否则继续判断，当前的结点的右子结点是否为空，如果不空，则向右递归前序查找
    // 2.2 中序、后序同理
    private static void test_sample_order_search() {
        BinaryTree binaryTree = new BinaryTree();
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");
        root.setLeft(node2);
        root.setRight(node3);
        node3.setLeft(node5);
        node3.setRight(node4);
        binaryTree.setRoot(root);

        HeroNode heroNode = binaryTree.preOrderSearch(5); // 1->2->3->4->5，4次找到5
        System.out.println("结果：heroNode=" + heroNode);
        System.out.println("-----------------------------------------");
        heroNode = binaryTree.infixOrderSearch(5); // 2->1->3->5->4，3次找到5
        System.out.println("结果：heroNode=" + heroNode);
        heroNode = binaryTree.postOrderSearch(5); // 2->5->4->3->1，2次找到5
        System.out.println("结果：heroNode=" + heroNode);
    }

    private static void test_simple_order() {
        BinaryTree binaryTree = new BinaryTree();
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        binaryTree.setRoot(root);

        System.out.println("前序遍历------------->");
        binaryTree.preOrder(); // 1,2,3,4
        System.out.println("中序遍历------------->");
        binaryTree.infixOrder(); // 2,1,3,4
        System.out.println("后序遍历------------->");
        binaryTree.postOrder(); // 2,3,4,1
    }
}
