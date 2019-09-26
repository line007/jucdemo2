package com.line.datastructs.stack;

/**
 * @desc ArrayStack
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

    public boolean isOper(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '>' || ch == '<';
    }

    // (a+b)>2
    public int priority(int ch) {
        int priority;
        switch (ch) {
            case '+':
            case '-':
                priority = -1;
                break;
            case '*':
            case '/':
                priority = 1;
                break;
            case '>':
            case '<':
                priority = 2;
                break;
            default:
                priority = 0;
                break;
        }
        return priority;
    }

    public int peek() {
        return stack[top];
    }

    public int cal(int num1, int num2, int oper) {
        int res = 0;
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '>':
                res = num1 > num2 ? 1 : 0;
                break;
            case '<':
                res = num1 < num2 ? 1 : 0;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }
}
