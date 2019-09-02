package com.line.datastructs.stack;

/**
 * @desc TODO
 * @Author xw
 * @Date 2019/9/2
 */
public class ArrayStack {
    // 1,2,3 => 3,2,1
    private int maxSize;
    private int[] stack;
    private int top = -1; // 栈顶

    public ArrayStack(int size) {
        this.maxSize = size;
        stack = new int[maxSize];
    }

    public void push(int value) {
        if (isFull()) {
            System.out.println("栈已满");
            return;
        }
        stack[++top] = value;
    }

    private boolean isFull() {
        return top == (maxSize-1);
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int size() {
        return top;
    }

    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据");
            return;
        }
        for (int i = top; i >= 0 ; i--) {
            System.out.println(String.format("stack[%s]=%s", i, (char)stack[i]));
        }
    }
}
