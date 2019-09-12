package com.line.datastructs.hashtab;

/**
 * @desc 员工链表
 * @Author xw
 * @Date 2019/9/12
 */
public class EmpLinkedList {
    private Emp head;

    // 添加
    public void add(Emp emp) {
        if (head == null) {
            head = emp;
            return;
        }
        Emp curEmp = head;
        while (true) {
            if (curEmp.getNext() == null) {
                break;
            }
            curEmp = curEmp.getNext();
        }
        curEmp.setNext(emp);
    }

    // 遍历
    public void list(int no) {
        if (head == null) {
            System.out.println(String.format("第%d条链表为空", no));
            return;
        }
        Emp curEmp = head;
        System.out.print(String.format("第%d条链表信息为：", no));
        while (true) {
            System.out.print(String.format("id=%d,name=%s|\t", curEmp.getId(), curEmp.getName()));
            if (curEmp.getNext() == null) {
                break;
            }
            curEmp = curEmp.getNext();
        }
        System.out.println();
    }

    // 查询
    public Emp findEmpById(int id) {
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }
        Emp curEmp = head;
        while (true) {
            if (curEmp.getId() == id) {
                break;
            }
            if (curEmp.getNext() == null) {
                break;
            }
            curEmp = curEmp.getNext();
        }
        return curEmp;
    }
}
