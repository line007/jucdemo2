package com.line.datastructs.linkedlist;

import java.util.Stack;

/**
 * @desc 单链表demo
 *  1.链表添加
 *      1.1 普通添加
 *      1.2 顺序添加
 *  2.遍历
 *  3.删除
 *  4.修改
 *  5.查找倒数第k个元素
 *  6.链表反转
 *      6.1 数组方式反转
 *      6.2 栈的方式反转
 * @Author xw
 * @Date 2019/8/30
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) throws CloneNotSupportedException {
        HeroNode hero1 = new HeroNode(1,"宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2,"卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3,"吴用", "智多星");
        HeroNode hero4 = new HeroNode(4,"林冲", "豹子头");
        // 添加操作
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        /*// 1.1 正常添加
        singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);*/
        // 1.2 顺序添加
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero4);
        // 2.遍历
        singleLinkedList.list();
        System.out.println("------------------------------------------");
        // 3.修改
        HeroNode editHero = new HeroNode(1,"宋江", "及时雨2");
        singleLinkedList.update(editHero);
        System.out.println("修改后-----------------------");
        singleLinkedList.list();
        // 4.删除
        System.out.println("删除后-----------------------");
        int no = 3;
        singleLinkedList.del(no);
        singleLinkedList.list();
        // 5.查找倒数第k个元素
        HeroNode head = singleLinkedList.getHead();
        int index = 4; // 倒数第1个
        HeroNode lastIndexNode = singleLinkedList.findLastIndexNode(head, index);
        System.out.println("倒数第"+index+"个元素是：\n"+lastIndexNode);
        // 6.链表反转
        System.out.println("反转后--------------");
//        singleLinkedList.reverseByArray(head);
        singleLinkedList.reverseByStack(head);
        singleLinkedList.list();
    }
}
class SingleLinkedList {
    // 初始化一个头节点，不要动
    private HeroNode head = new HeroNode(0,"","");
    /**
     * 添加节点
     * 思路分析：
     * 1.找到最后一个节点，通过辅助变量temp
     * 2.temp.next = 新的节点
     * @param heroNode
     */
    public void add(HeroNode heroNode) {
       this.add(head, heroNode);
    }

    /**
     * 添加节点
     * 思路分析：
     * 1.找到最后一个节点，通过辅助变量temp
     * 2.temp.next = 新的节点
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
     * 删除节点
     * 1.找到要删除的节点前一个节点->temp
     * 2.temp.next = temp.next.next
     * @param no
     */
    public void del(int no) {
        // 1.找到需要删除的节点的前一个节点temp
        HeroNode temp = head; // 从头节点开始找
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == no) { // 当前节点.next = 新节点，即找到了
                break;
            }
            temp = temp.next;
        }
        temp.next = temp.next.next;
    }

    /**
     * 查找倒数第k个元素
     * 1.得到链表总长度 size
     * 2.for (i -> size-index)
     * @param head
     * @param index
     * @return
     */
    public HeroNode findLastIndexNode(HeroNode head, int index) {
        if (head.next == null) { // 空链表
            return null;
        }
        // 得到链表总长度 size
        int size = getLength(head);
        if (size <= 0 || index > size) { // 下标不存在
            return null;
        }
        HeroNode cur = head.next; // 3 // 3-1 = 2
        for (int i = 0; i < size - index; i++) { // for (i -> size-index)
            cur = cur.next;
        }
        return cur;
    }

    private int getLength(HeroNode head) {
        if (head.next == null) { // 空链表
            return 0;
        }
        int length = 0;
        HeroNode cur = head.next; // 从头节点开始找
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }

    public HeroNode getHead() {
        return head;
    }

    /**
     * 链表反转 H--A->B->C => R--C->B->A
     * 1.定义一个新链表reverseHead = new HeroNode()
     * 2.从头到尾遍历原来的链表，每遍历一个节点，将其取出，并放在新链表的最前端
     * 3.原节点.next = R.next，R.next = 原节点
     * @param head
     */
    public void reverseByArray(HeroNode head) throws CloneNotSupportedException {
        HeroNode reverseHead = new HeroNode(0,"","");
        HeroNode cur = head.next;
        HeroNode temp;
        while (cur != null) {
            temp = (HeroNode)cur.clone(); // 深copy
            if (reverseHead.next != null) {
                temp.next = reverseHead.next;
            }
            reverseHead.next = temp;
            // 后移
            cur = cur.next;
        }
        head.next = reverseHead.next;
    }

    /**
     * 栈方式反转
     * 1.定义一个栈 heroNodeStack = new Stack<>();
     * 2.从头到尾遍历原来的链表，每遍历一个节点，将其取出，并放入栈中
     * 3.遍历栈，定义一个新链表reverseHead = new HeroNode()，依次加入新链表
     * 4.原节点.next = reverseHead.next
     * @param head
     */
    public void reverseByStack(HeroNode head) throws CloneNotSupportedException {
        Stack<HeroNode> heroNodeStack = new Stack<>();
        HeroNode cur = head.next;
        while (cur != null) {

            heroNodeStack.push((HeroNode)cur.clone());
            // 后移
            cur = cur.next;
        }
        // 遍历栈，定义一个新链表reverseHead = new HeroNode()，依次加入新链表
        HeroNode reverseHead = new HeroNode(0,"","");
        while (!heroNodeStack.isEmpty()) {
            this.add(reverseHead, heroNodeStack.pop());
        }
        head.next = reverseHead.next;
    }
}
/*class HeroNode implements Cloneable {
    int no; // 编号
    private String name; // 姓名
    private String nickname;// 昵称
    HeroNode next;

    public HeroNode(int no, String name, String nickname) {
        this.name = name;
        this.no = no;
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public HeroNode getNext() {
        return next;
    }

    public void setNext(HeroNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        HeroNode heroNode = (HeroNode) super.clone();
        if (next != null) {
            heroNode.next = null;
        }
        return heroNode;
    }
}*/
