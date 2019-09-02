package com.line.datastructs.stack;

/**
 * @desc 数据模拟栈
 * 1.使用数组模拟栈 arr = new Array[maxSize]
 * 2.定义一个top表示顶
 * 3.入栈 arr[++top] = data
 * 4.出栈 return arr[--top]
 * @Author xw
 * @Date 2019/9/2
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(3);
        arrayStack.push(1);
        arrayStack.push(2);
        arrayStack.push(3);
        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());
    }
}
