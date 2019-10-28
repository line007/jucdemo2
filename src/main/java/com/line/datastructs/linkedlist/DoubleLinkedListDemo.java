package com.line.datastructs.linkedlist;

/**
 * @desc 双向链表
 * @Author xw
 * @Date 2019/9/2
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {

        HeroNode hero1 = new HeroNode(1,"宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2,"卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3,"吴用", "智多星");
        HeroNode hero4 = new HeroNode(4,"林冲", "豹子头");
        // 添加操作
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        // 1.1 正常添加
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);
        doubleLinkedList.list();
        // 3.修改
        HeroNode editHero = new HeroNode(1,"宋江", "及时雨2");
        doubleLinkedList.update(editHero);
        System.out.println("修改后-----------------------");
        doubleLinkedList.list();
        // 4.删除
        System.out.println("删除后-----------------------");
        int no = 3;
        doubleLinkedList.del(no);
        doubleLinkedList.list();
    }
}
class DoubleLinkedList {
    // 初始化一个头节点，不要动
    private HeroNode head = new HeroNode(0,"","");

    public void add(HeroNode heroNode) {
        this.add(this.head, heroNode);
    }
    /**
     * 添加节点
     * 思路分析： A=B=C => A=B=C =D
     * 1.找到最后一个节点，通过辅助变量temp
     * 2.temp.next = 新的节点
     * 3.temp.pre = temp
     * @param heroNode
     */
    public void add(HeroNode head, HeroNode heroNode) {
        // （1）找到添加节点的位置，通过辅助变量temp
        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        // （2）temp.next = 新的节点
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    /**
     * 顺序添加：
     * 思路分析：
     * 1.首先找到新添加的节点的位置（当前节点.next>新增节点），是通过辅助变量（指针temp）
     * 2.如果当前节点temp存在next节点，新的节点.next = temp.next
     * 3.将temp.next = 新的节点
     * @param heroNode 新节点
     */
    public void addByOrder(HeroNode heroNode) {
        // 找到要添加的节点前一个位置，当前节点cur，上一个节点temp=cur.pre
        HeroNode temp = head;
        boolean flag = false; // 是否存在相同编号的
        while (true) {
            if (temp.next == null) { // 已经在链表最后
                break;
            }
            if (temp.next.no > heroNode.no) { // 位置找到了，就在temp的后面插入
                break;
            } else if (temp.next.no == heroNode.no) {
                flag = true; // 编号一样
                break;
            }
            temp = temp.next; // 后移
        }
        if (flag) {
            System.out.println(String.format("已存在编号[%s]，不能重复添加", temp.next.no));
        } else {
            if (temp.next != null) { // 如果存在next节点，则用当前节点.next = temp.next!!!
                heroNode.next = temp.next;
            }
            temp.next = heroNode;
        }
    }

    public void list() {
        // 链表为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next; // 头节点不能动
        while (true) {
            if (temp == null) { // 判断是否到链表最后
                break;
            }
            System.out.println(temp);
            temp = temp.next; // 后移
        }
    }

    /**
     * 更新节点：
     * 1.找到需要修改的节点的前一个节点temp（当前节点.next = 新节点）
     * 2.新节点.next = temp.next.next，temp.next = 新节点
     * @param heroNode
     */
    public void update(HeroNode heroNode) {
        // 1.找到需要修改的节点的前一个节点temp
        HeroNode temp = head; // 从头节点开始找
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == heroNode.no) { // 当前节点.next = 新节点，即找到了
                break;
            }
            temp = temp.next;
        }
        // 新节点.next = temp.next.next，temp.next = 新节点
        if (temp.next != null) {
            heroNode.next = temp.next.next;
        }
        temp.next = heroNode;
    }

    /**
     * 删除节点（能实现自我删除）
     * 1.找到要删除的节点->temp
     * 2.temp.pre.next = temp.next
     * @param no
     */
    public void del(int no) {
        HeroNode temp = head; // 从头节点开始找
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.no == no) { // 当前节点.next = 新节点，即找到了
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.pre.next = temp.next;
            /*if (temp.next != null) {
                temp.next.pre = temp.pre;
            }*/
        } else {
            System.out.println(String.format("要删除的%s节点不存在", no));
        }
    }
}

