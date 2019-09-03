package com.line.datastructs.stack;

/**
 * @desc 综合计数器
 * 1.通过一个index索引，来遍历我们的表达式
 * 2.如果是数字，就直接入数栈
 * 3.如果是符号，分以下2种情况：
 * 3.1 如果发现符号栈为空，直接入栈
 * 3.2 如果符号栈有操作符，就进行比较，
 *  如果当前的操作符的优先级小于或者等于栈中的操作符，就需要从数栈中弹出两个数，进行比较，在从符号栈中pop出一个符号，进行运算，
 *  将得到的结果，入数栈，然后将当前的操作符入符号栈；
 *  如果当前的操作符的优先级大于栈中的操作符，就直接入符号栈
 * 4.当表达式扫描完毕，就顺序的从数栈和符号栈中pop出相应的数和符号，并运行，
 * 5.最后在数栈中只有一个数字，就是表达式的结果
 *
 * 验证：
 *  3+2*6-2=13
 * @Author xw
 * @Date 2019/9/2
 */
public class Calculator {
    public static void main(String[] args) {
        // "3+20*6-20*6"
        //cal("3+20*6-20*6");
        // 3+2>3 => 1
        // 3 2 + 1 >
        // + >
        //cal("(3+2)>1");
        System.out.println("abc".substring(2));
        String item = ")";
        System.out.println(item.equals(")"));
        System.out.println(".".equals("."));
    }

    private static void cal(String expression) {
        int index = 0;
        char ch;
        ArrayStack numStack = new ArrayStack(10);
        ArrayStack operStack = new ArrayStack(10);
        int oper;
        int num2;
        int num1;
        int res;
        while (true) {
            ch = expression.substring(index, index + 1).charAt(0);
            if (operStack.isOper(ch)) { // 是操作符
                System.out.println("操作符号：" + ch);
                /* 如果当前的操作符的优先级小于或者等于栈中的操作符，就需要从数栈中弹出两个数，进行比较，在从符号栈中pop出一个符号，进行运算，
                   将得到的结果，入数栈，然后将当前的操作符入符号栈；*/
                if (!operStack.isEmpty()) {
                    // 判断优先级
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num2 = numStack.pop();
                        num1 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        numStack.push(res);
                        operStack.push(ch);
                    } else {
                        // 如果当前的操作符的优先级大于栈中的操作符，就直接入符号栈
                        operStack.push(ch);
                    }
                } else { // 3.1 如果发现符号栈为空，直接入操作符栈
                    operStack.push(ch);
                }
            } else { // 数字，直接入数栈 ch - 48
                if (index == expression.length()-1) {
                    numStack.push(ch - 48);
                } else {
                    // 再往下取
                    String numStr = (ch - 48) + ""; // ch-48
                    char nextChar = expression.substring(index + 1, index + 2).charAt(0); // 往后取一位
                    while (!operStack.isOper(nextChar)) {
                        numStr += (nextChar-48);
                        index ++;
                        nextChar = expression.substring(index + 1, index + 2).charAt(0); // 往后取一位
                    }
                    if (!numStr.equals("")) {
                        numStack.push(Integer.valueOf(numStr));
                    } else {
                        numStack.push(ch - 48);
                    }
                }
            }
            index ++;
            if (index >= expression.length()) {
                break;
            }
        }
        // 当表达式扫描完毕，就顺序的从数栈和符号栈中pop出相应的数和符号，并运行，
        while (true) {
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res); // 将运算结果重新压入栈
        }
        System.out.println(String.format("表达式%s结果为：%s", expression, numStack.pop()));
    }

}
