package com.line.datastructs.linkedlist;

/**
 * @desc 单向环形列表
 * 1.约瑟夫问题：
 *  设编号为1，2，...n的n个人围坐一圈，约定编号为k(1<=k<=n)的人
 *  从1开始报数，数到m的那个人出列，它的下一位又从1开始报数，数到m的那个人又出列，
 *  依次类推，直到所有人出列为止，由此产生一个环形队列
 * 2.假设问题：
 *  n = 5 有5个人
 *  k = 1 从第一个人开始
 *  m = 2 每次数2下
 * 3.思路分析：
 * （1）定义一个辅助变量helper，指向最后一个节点
 * （2）当小孩报数时，first、helper同时移动 m-1 次（每次是从自己开始数）
 * （3）这时，可以将first指向的小孩节点出圈
 *      first = first.next
 *      helper.next = first
 *      原来的first节点没有任何引用，就会被回收
 * @Author xw
 * @Date 2019/9/2
 */
public class CircleSingleLinkedListDemo {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();
        circleSingleLinkedList.countBoy(1, 2, 5);
    }
}
class CircleSingleLinkedList {
    Boy first;

    /**
     * 添加
     * 1.第一个节点，next=自己
     * 2.定义一个辅助curBoy，当前节点
     * 3.curBoy后移
     * @param nums
     */
    public void addBoy(int nums) {
        Boy curBoy = null;
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {
                // 第一个小孩（next=自己）
                first = boy;
                first.setNext(first);
                curBoy = first;
            } else {
                curBoy.setNext(boy);
                boy.setNext(first); // 指向第一个
                curBoy = boy; // 移动指针
            }
        }
    }

    /**
     * 遍历环形列表
     * 1.跳出循环条件：curBoy.next = first
     */
    public void showBoy() {
        if (first == null) {
            System.out.println("没有任何小孩");
            return;
        }
        Boy curBoy = first;
        while (true) {
            System.out.println(String.format("小孩的编号 %s", curBoy.getNo()));
            if (curBoy.getNext() == first) {
                break;
            }
            curBoy = curBoy.next; // 后移
        }
    }

    /**
     * 出圈顺序
     * 3.思路分析：
     * （1）定义一个辅助变量helper，指向最后一个节点
     * （2）当小孩报数时，first、helper同时移动 m-1 次（每次是从自己开始数）
     * （3）这时，可以将first指向的小孩节点出圈
     *      first = first.next
     *      helper.next = first
     *      原来的first节点没有任何引用，就会被回收
     * @param startNo 从第几个小孩开始
     * @param countNum 表示数几下
     * @param nums 最初有多少个小孩
     */
    public void countBoy(int startNo, int countNum, int nums) {
        // 验证
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误，请重新输入");
            return;
        }
        Boy helper = first; // 让helper指向最后一个节点
        while (true) {
            if (helper.next == first) {
                break;
            }
            helper = helper.next;
        }

        while (true) {
            if (helper == first) { // 圈中只有一个人
                System.out.println(String.format("最后一个小孩%s出圈", first.no));
                break;
            }
            // 当小孩报数时，first、helper同时移动 m-1 次（每次是从自己开始数）
            for (int i = 0; i < countNum - 1; i++) {
                first = first.next;
                helper = helper.next;
            }
            System.out.println(String.format("小孩%s出圈", first.no));
            // 这时，可以将first指向的小孩节点出圈
            first = first.next;
            helper.next = first;
        }
    }
}

class Boy {
    int no;
    Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
