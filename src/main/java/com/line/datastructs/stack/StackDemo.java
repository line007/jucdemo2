package com.line.datastructs.stack;

/**
 * @desc 栈
 *  （1）定义：是一种"先进后出"的数据存储结构
 *  （2）有哪些操作：
 *      进栈
 *      出栈
 *      遍历
 * @Author xw
 * @Date 2019/8/12
 */
public class StackDemo {

    public static void main(String[] args) {
        /*ArrayStack arrayStack = new ArrayStack(3);
        arrayStack.push(1);
        arrayStack.push(2);
        arrayStack.push(3);
        arrayStack.push(1);
        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());*/

        // 计算 1+2+3+4
        String str = "1+2-3+4";
        int result = calculate(str);
        System.out.println(result);
    }

    private static int calculate(String str) {
        int size = str.length();
        ArrayStack numStack = new ArrayStack(size);
        ArrayStack operatorStack = new ArrayStack(size); // 操作符
        if (null == str || str.equals("")) {
            return 0;
        }
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            System.out.println(chars[i]);
            char c = chars[i];
            if (c == '+' || c == '-' || c == '*' || c == '/') {
                operatorStack.push(c);
            } else {
                numStack.push(c-'0'); // char转int
            }
        }

        // 遍历list
        operatorStack.list();
        numStack.list();
        // 1+2-3+4
        // [4,3,2,1] [+,-,+]
        while(!operatorStack.isEmpty()) {
            char c = (char)operatorStack.pop();
            int tmp = 0;
            if (c == '+' || c == '-' || c == '*' || c == '/') {
                if (c == '+') {
                    int num1 = numStack.pop();
                    int num2 = numStack.pop();
                    tmp += (num2 + num1);
                    numStack.push(tmp);
                } else if (c == '-') {
                    int num1 = numStack.pop();
                    int num2 = numStack.pop();
                    tmp += (num2 - num1);
                    numStack.push(tmp);
                } else if (c == '*') {

                }
            }
        }
        return numStack.pop();
    }

}

class ArrayStack {
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
