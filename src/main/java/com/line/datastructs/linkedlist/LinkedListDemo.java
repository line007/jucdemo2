package com.line.datastructs.linkedlist;

import java.util.*;

/**
 * @desc 链表
 *  （1）单链表中节点的数量
 *  （2）查找单链表中的倒数第K个结点
 *  （3）单链表的反转
 *      A.数组
 *      B.栈
 *  （4）从头到尾打印单链表（要求：反向遍历，Stack栈）
 *  （5）合并两个有序的单链表，合并之后链表依然有序（??? => 暂无法理解）
 * @Author xw
 * @Date 2019/8/10
 */
public class LinkedListDemo {

    public static void main(String[] args) {

        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add(new HeroNode("周瑜", 1));
        singleLinkedList.add(new HeroNode("黄盖", 3));
        singleLinkedList.add(new HeroNode("刘备", 5));
        System.out.println("============================");
        printHeroNode(singleLinkedList.getRoot());

        System.out.println("============================");
        // （1）单链表中节点的数量
        System.out.println(String.format("链表的数量[%s]", singleLinkedList.getSize()));
        // （2）查找单链表中的倒数第K个结点
        int k = 3;
        HeroNode heroNode = singleLinkedList.getLastKNode(k);
        System.out.println(String.format("倒数第k=%s个节点值为%s",k, heroNode.getName()));
        //（3）单链表的反转
        System.out.println("反转前：=================");
        printHeroNode(singleLinkedList.getRoot());
//        singleLinkedList.reverse_array();
        singleLinkedList.reverse_stack();
        //（4）从头到尾打印单链表（要求：反向遍历，Stack栈）
        System.out.println("反转后：=================");
        printHeroNode(singleLinkedList.getRoot());
        //（5）合并两个有序的单链表，合并之后链表依然有序
        // singleLinkedList.merge();
        SingleLinkedList singleLinkedList2 = new SingleLinkedList();
        singleLinkedList2.add(new HeroNode("诸葛亮", 2));
        singleLinkedList2.add(new HeroNode("曹操", 4));
        singleLinkedList2.add(new HeroNode("郭嘉", 6));
        //singleLinkedList.mergeSort(singleLinkedList2);
        SingleLinkedList.mergeTwoLists(singleLinkedList.getRoot(), singleLinkedList2.getRoot());
        System.out.println("合并后：=================");
        printHeroNode(singleLinkedList.getRoot());
    }

    private static void printHeroNode(HeroNode rootNode) {
        HeroNode next = rootNode.getNext();
        while (next != null) {
            System.out.println(next.getName() + "--" + next.getSort());
            next = next.getNext();
        }
    }
}

/**
 * 双链表
 */
class DoubleLinkedList {

    private HeroNode root;
    private HeroNode current;

    public DoubleLinkedList() {
        root = new HeroNode("",0);
    }

    public int getSize() {
        int i = 0;
        HeroNode next = root.getNext();
        while (next != null) {
            next = next.getNext();
            i++;
        }
        return i;
    }

    public void add(HeroNode heroNode) {
        System.out.println(String.format("添加[%s]", heroNode.getName()));
        if (root.getNext() == null) { // 只有一个
            root.setNext(heroNode);
            heroNode.setPre(root); // 设置前一个节点
            return;
        }
        current = getCurrentNode(root);
        current.setNext(heroNode);
        heroNode.setPre(current);
    }

    public HeroNode getCurrentNode(HeroNode heroNode) {
        // 是否有子节点
        if (heroNode.getNext() == null) { // 没有子节点，即为最后一个节点
            return heroNode;
        }
        return getCurrentNode(heroNode.getNext());
    }

    public HeroNode getRoot() {
        return root;
    }

    public void mergeSort(DoubleLinkedList targetLinkedList) {
        // this[2,4,6] -> target[1,3,5] = [1,2,3,4,5,6]
        // （1）循环target，遍历每一个节点
        // （2）与当前所有节点比较，if (target.sort <= this.sort) -> this.pre.next=target,target.next=this [1,2,4,6]
        // target.pre=target.next [0,3,5]
        // 循环target，遍历每一个节点
        HeroNode targetRoot = targetLinkedList.getRoot();
        HeroNode targetNext = targetRoot.getNext();
        HeroNode thisRoot = this.root;
        HeroNode thisNext = thisRoot.getNext();
        while (targetNext != null) {
            while (thisNext != null) {
                if (targetNext.getSort() <= thisNext.getSort()) {
                    // 解除target节点关系
                    targetNext.getPre().setNext(targetNext.getNext()); // [0,1,3,5] => [0,3,5]
                    // 重新构架this节点关系
                    thisNext.getPre().setNext(targetNext); // [0,2,4,6] => [0,1]-[2,4,6]
                    targetNext.setNext(thisNext); // [0,1]+[2,4,6]
                }
                thisNext = thisNext.getNext();
            }
            thisNext = thisRoot.getNext(); // 再从新来
            targetNext = targetNext.getNext();
        }
    }
}

/**
 * 单链表
 */
class SingleLinkedList {
    private HeroNode root;
    private HeroNode current;

    public int getSize() {
        int i = 0;
        HeroNode next = root.getNext();
        while (next != null) {
            next = next.getNext();
            i++;
        }
        return i;
    }

    public SingleLinkedList() {
        root = new HeroNode("", 0);
    }

    public void add(HeroNode heroNode) {
        System.out.println(String.format("添加[%s]", heroNode.getName()));
        if (root.getNext() == null) { // 只有一个
            root.setNext(heroNode);
            return;
        }
        current = getCurrentNode(root);
        current.setNext(heroNode);
    }

    public HeroNode getCurrentNode(HeroNode heroNode) {
        // 是否有子节点
        if (heroNode.getNext() == null) { // 没有子节点，即为最后一个节点
            return heroNode;
        }
        return getCurrentNode(heroNode.getNext());
    }

    public HeroNode getRoot() {
        return root;
    }

    public HeroNode getLastKNode(int k) {
        //
        if (k == 0) { // 第一个
            return root.getNext();
        }
        int i = 0;
        HeroNode next = root.getNext();
        while (next != null) {
            i++;
            if (i == k) { // 找到了
                return next;
            }
            next = next.getNext();
        }
        return null;
    }

    /**
     * 数组方式反转
     */
    public void reverse_array() {
        // （1）用数组的方式实现
        HeroNode[] heroNodes = new HeroNode[getSize()];
        HeroNode next = root.getNext();
        int i = 0;
        while (next != null) {
            heroNodes[i++] = next;
            next = next.getNext();
        }
        // 清空所有节点关系
        Set<HeroNode> set = new HashSet<>();
        for (int j = 0; j < heroNodes.length; j++) {
            set.add(heroNodes[j]);
        }
        this.clear(set.iterator());
        // 重新添加
        for (int j = heroNodes.length-1; j >= 0 ; j--) {
            this.add(heroNodes[j]);
        }
    }

    private void clear(Iterator<HeroNode> itr) {
        this.root.setNext(null);
        while (itr.hasNext()) {
            HeroNode node = itr.next();
            node.setNext(null);
        }
    }

    public void reverse_stack() {
        // （2）用栈的方式实现
        Stack<HeroNode> stack = new Stack<>();
        HeroNode next = root.getNext();
        while (next != null) {
            stack.add(next);
            next = next.getNext();
        }
        // 清空所有节点关系
        this.clear(stack.iterator());

        // 重新添加
        Iterator<HeroNode> itr2 = stack.iterator();
        while (itr2.hasNext()) {
            HeroNode node = itr2.next();
            this.add(node);
        }
    }

    public static HeroNode mergeTwoLists(HeroNode l1, HeroNode l2) {
        HeroNode head;
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.getSort() <= l2.getSort()) {
            head = l1;
            l1 = l1.getNext();
        } else {
            head = l2;
            l2 = l2.getNext();
        }
        HeroNode temp = head;
        while (l1 != null && l2 != null) {
            if (l1.getSort() <= l2.getSort()) {
                temp.setNext(l1);
                l1 = l1.getNext();
            } else {
                temp.setNext(l2);
                l2 = l2.getNext();
            }
            temp = temp.getNext();
        }
        if (l1 == null) {
            temp.setNext(l2);
        }
        if (l2 == null) {
            temp.setNext(l1);
        }
        return temp;
    }
}

class HeroNode {
    private String name;
    private int sort;
    private HeroNode next;
    private HeroNode pre;

    public HeroNode(String name, int sort) {
        this.name = name;
        this.sort = sort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getNext() {
        return next;
    }

    public void setNext(HeroNode next) {
        this.next = next;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public HeroNode getPre() {
        return pre;
    }

    public void setPre(HeroNode pre) {
        this.pre = pre;
    }
}
