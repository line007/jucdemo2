package com.line.datastructs.hashtab;

/**
 * @desc 员工哈希表
 * @Author xw
 * @Date 2019/9/12
 */
public class HashTab {

    private EmpLinkedList[] empLinkedListArray;
    private int size = 8;

    public HashTab() {
        empLinkedListArray = new EmpLinkedList[size];
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    // 添加
    public void add(Emp emp) {
        int empLinkedListNo = hashFun(emp.getId());
        empLinkedListArray[empLinkedListNo].add(emp);
    }

    // 遍历
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i+1);
        }
    }

    // 查找
    public void findEmpById(int id) {
        int empLinkedListNo = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNo].findEmpById(id);
        if (emp != null) {
            System.out.println(String.format("在第%d条链表中找到雇员 id=%s \n", (empLinkedListNo + 1), id));
        } else {
            System.out.println("在哈希表中没找到该雇员。");
        }
    }

    private int hashFun(int id) {
        return id % size;
    }
}
