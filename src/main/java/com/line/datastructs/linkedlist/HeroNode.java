package com.line.datastructs.linkedlist;

/**
 * @desc HeroNode
 * @Author xw
 * @Date 2019/9/2
 */
public class HeroNode implements Cloneable {
    int no; // 编号
    private String name; // 姓名
    private String nickname;// 昵称
    HeroNode next; // 下一个节点
    HeroNode pre; // 前一个节点

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
}
