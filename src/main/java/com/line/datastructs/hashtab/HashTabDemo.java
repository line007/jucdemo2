package com.line.datastructs.hashtab;

import java.util.Scanner;

/**
 * @desc 哈希表案例
 *  有一个公司，当有新员式来报道时，要求该员工的信息加入
 *  （id，性别，年龄，名字、住址），当输入该员工的id时，要求查询该员工的所有信息
 *  要求：
 *  不使用数据库，速度越快越好=> 哈希表（散列）
 *  添加时，保证按照id从低至高的顺序插入？
 *  （1）使用链表来实现哈希表，该链表不带表头，即：链表的第一个结点放存放雇员
 *  （2）思路分析并画出示意图
 *  （3）代码实现【增删改查（显示所有员工，按id查询）】
 * @Author xw
 * @Date 2019/9/12
 */
public class HashTabDemo {
    public static void main(String[] args) {

        HashTab hashTab = new HashTab();
        String key;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add: 添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找");
            System.out.println("exit: 退出系统");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("请输入id");
                    int id = scanner.nextInt();
                    System.out.println("请输入名字");
                    String name = scanner.next();
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入id");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }

    }
}
