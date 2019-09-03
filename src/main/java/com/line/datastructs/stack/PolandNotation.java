package com.line.datastructs.stack;

import java.math.BigDecimal;
import java.util.*;

/**
 * @desc 波兰计算器
 *  suffixExpression=(30+4)*5-6 => 30 4 + 5 * 6 - => 164
 * 1.将suffixExpression放入ArrayList中 [30, 4, +, 5, *, 6, -]
 * 2.将ArrayList传递给一个方法，遍历ArrayList配合栈完成计算
 *
 * 中缀转后缀表达式
 * 1.初始化两个栈：运算符栈s1和存储结果栈s2
 * 2.从左至右扫描中缀表达式
 * 3.遇到数字时，将其压入s2
 * 4.遇到运算符时，比较其与s1栈顶运算符的优先级；
 * （1）如果s1为空，或栈顶运算符为左括号“（”，则直接将些运算符入栈s1；
 * （2）否则，若优先级比栈顶优先级高，也将运算符压入s1
 * （3）否则，将s1栈顶的运算符弹出并压入s2中，再次转到（4.1）与s1中新的栈顶运算符相比较
 * 5.遇到括号时：
 * （1）如果是“（”，则直接压入s1
 * （2）如果是“）”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到“（”为止，此时将这一对括号丢弃
 * 6.重复步骤2-5，一直到表达式的最右边
 * 7.将s1中剩余的运算符依次弹出并压入s2
 * 8.依次弹出s2中的元素并输出，结果的逆序即为表达式对应的后缀表达式
 *
 * @Author xw
 * @Date 2019/9/3
 */
public class PolandNotation {
    public static void main(String[] args) {
        // 中缀转后缀表达式
        /*String expression = "(30+4)*5-6"; // 30 4 + 5 * 6 - => 164
        expression = "4*5-8+60+8/2"; // 4 5 * 8 - 60 + 8 2 / + => 76
        List<String> list = infixToSuffixExpression(expression);
        System.out.println("后缀表达式：" + list);
        int res = calculate(list);
        System.out.println("res=" + res);*/

        // 长+2x（宽+高）>100cm => (%s+%s*(%s+%s))>%s
        int length = 100;
        int width = 10;
        int height = 10;
        String expression = String.format("(%s+2*(%s+%s))>%s", length, width, height, 100);
        System.out.println(String.format("%s=>%s", expression, calculate2(infixToSuffixExpression(expression)).intValue()));
        // 长>100CM 或者 宽>100cm
        length = 120;
        width = 77;
        expression = String.format("%s>%s|%s>%s", length, 100, width, 100);
        System.out.println(String.format("%s=>%s",expression, calculate2(infixToSuffixExpression(expression)).intValue()));
        // 重量>100kg
        double weight = 100.75;
        expression = String.format("%s>%s", weight, 100.75);
        System.out.println(String.format("%s=>%s", expression, calculate2(infixToSuffixExpression(expression)).intValue()));
        /*System.out.println(1|0|0|0); // 1
        System.out.println(1|0); // 1
        System.out.println(0|0); // 0
        System.out.println(1&1&0); // 1
        System.out.println(1&0); // 0
        System.out.println(0&0); // 0*/
    }

    /**
     * 中缀转后缀表达式
     * 1.初始化两个栈：运算符栈s1和存储结果栈s2
     * 2.从左至右扫描中缀表达式
     * 3.遇到数字时，将其压入s2
     * 4.遇到运算符时，比较其与s1栈顶运算符的优先级；
     * （1）如果s1为空，或栈顶运算符为左括号“（”，则直接将些运算符入栈s1；
     * （2）否则，若优先级比栈顶优先级高，也将运算符压入s1
     * （3）否则，将s1栈顶的运算符弹出并压入s2中，再次转到（4.1）与s1中新的栈顶运算符相比较
     * 5.遇到括号时：
     * （1）如果是“（”，则直接压入s1
     * （2）如果是“）”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到“（”为止，此时将这一对括号丢弃
     * 6.重复步骤2-5，一直到表达式的最右边
     * 7.将s1中剩余的运算符依次弹出并压入s2
     * 8.依次弹出s2中的元素并输出，结果的逆序即为表达式对应的后缀表达式
     */
    private static List<String> infixToSuffixExpression(String expression) {
        // 1.初始化两个栈：运算符栈s1和存储结果栈s2
        Stack<String> s1 = new Stack<>();
        List<String> s2 = new ArrayList<>();
        int index = 0;
        String numStr = ""; // 数字拼接串
        String item;
        // 2.从左至右扫描中缀表达式
        while (true) {
            if (index == expression.length()) { // 跳出循环
                break;
            }
            if (index == expression.length() - 1) { // 最后一个
                item = expression.substring(expression.length() - 1);
                if (isNum(item)) {
                    s2.add(item);
                } else {
                    addOper(s1, s2, item);
                }
                break;
            }

            // 3.遇到数字时，将其压入s2
            item = expression.substring(index, index + 1);
            // System.out.println("item="+item);
            if (isNum(item)) {
                while (index != expression.length() && isNum(expression.substring(index, index + 1))) {
                    numStr += expression.substring(index, index + 1); // 拼接
                    index++;
                }
                s2.add(numStr);
                //System.out.println("numStr=" + numStr);
                numStr = "";
            } else {
                // 4.遇到运算符时，比较其与s1栈顶运算符的优先级；
                addOper(s1, s2, item);
                index++;
            }
        }
        // 6.重复步骤2-5，一直到表达式的最右边
        // 7.将s1中剩余的运算符依次弹出并压入s2
        while (!s1.isEmpty()) {
            s2.add(s1.pop());
        }
        // System.out.println("s1=>" + s1);
        // System.out.println("s2=>" + s2);
        return s2;
    }

    private static boolean isNum(String item) {
        return item.matches("\\d+") || item.equals(".");
    }
    private static boolean isStrNum(String item) {
        return item.matches("\\d+(\\.\\d+)?$");
    }

    private static void addOper(Stack<String> s1, List<String> s2, String item) {
        // 4.遇到运算符时，比较其与s1栈顶运算符的优先级；
        if (s1.isEmpty() || s1.peek().equals("(")) {
            // 4.1 如果s1为空，或栈顶运算符为左括号“（”，则直接将些运算符入栈s1；
            s1.push(item);
        } else if (item.equals(")") || item.equals("(")) { // 5.遇到括号时：
            if (item.equals("(")) {
                // 5.1 如果是“（”，则直接压入s1
                s1.push(item);
            } else {
                // 5.2 如果是“）”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到“（”为止，此时将这一对括号丢弃
                String oper = s1.pop();
                while (!"(".equals(oper)) {
                    s2.add(oper);
                    oper = s1.pop();
                }
            }
        } else if (priority(item) < priority(s1.peek())) {
            //4.2 否则，若优先级比栈顶优先级高，也将运算符压入s1
            s1.push(item);
        } else {
            //（3）否则，将s1栈顶的运算符弹出并压入s2中，再次转到（4.1）与s1中新的栈顶运算符相比较
            s2.add(s1.pop());
            s1.push(item); // s1压入新运算符
        }
    }

    private static int priority(String item) {
        if (item.equals("|")) {
            return 3;
        } else if (item.equals("&")) {
            return 3;
        } else if (item.equals(">")) {
            return 2;
        } else if (item.equals("<")) {
            return 2;
        } else if (item.equals("+")) {
            return 1;
        } else if (item.equals("-")) {
            return 1;
        } else if (item.equals("*")) {
            return -1;
        } else if (item.equals("/")) {
            return -1;
        } else if (item.equals("(")) {
            return -2;
        } else if (item.equals(")")) {
            return -2;
        } else {
            throw new RuntimeException("运算符有误");
        }
    }

    /**
     * 整数计算
     * @param list
     * @return
     */
    private static int calculate(List<String> list) {
        Stack<String> stack = new Stack<>();
        for (String item : list) {
            if (isStrNum(item)) { // 数字
                stack.push(item);
            } else {
                int num2 = Integer.valueOf(stack.pop());
                int num1 = Integer.valueOf(stack.pop());
                int res;
                if (item.equals("|")) {
                    res = num1 | num2;
                    return res;
                } else if (item.equals("&")) {
                    res = num1 & num2;
                    return res;
                } else if (item.equals(">")) {
                    res = num1 > num2 ? 1 : 0;
                } else if (item.equals("<")) {
                    res = num1 < num2 ? 1 : 0;
                } else if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                // 把结果重新压入栈
                stack.push(res + "");
            }
        }
        return Integer.valueOf(stack.pop());
    }

    /**
     * 浮点计算
     * @param list
     * @return
     */
    private static BigDecimal calculate2(List<String> list) {
        Stack<String> stack = new Stack<>();
        for (String item : list) {
            if (isStrNum(item)) { // 数字
                stack.push(item);
            } else {
                BigDecimal num2 = new BigDecimal(stack.pop());
                BigDecimal num1 = new BigDecimal(stack.pop());
                BigDecimal res;
                if (item.equals("|")) {
                    res = new BigDecimal(num1.intValue() | num2.intValue());
                    return res;
                } else if (item.equals("&")) {
                    res = new BigDecimal(num1.intValue() & num2.intValue());
                    return res;
                } else if (item.equals(">")) {
                    res = new BigDecimal(num1.doubleValue() > num2.doubleValue() ? 1 : 0);
                } else if (item.equals("<")) {
                    res = new BigDecimal(num1.doubleValue() < num2.doubleValue() ? 1 : 0);
                } else if (item.equals("+")) {
                    res = new BigDecimal(num1.doubleValue() + num2.doubleValue());
                } else if (item.equals("-")) {
                    res = new BigDecimal(num1.doubleValue() - num2.doubleValue());
                } else if (item.equals("*")) {
                    res = new BigDecimal(num1.doubleValue() * num2.doubleValue());
                } else if (item.equals("/")) {
                    res = new BigDecimal(num1.doubleValue() / num2.doubleValue());
                } else {
                    throw new RuntimeException("运算符有误");
                }
                // 把结果重新压入栈
                stack.push(res.doubleValue() + "");
            }
        }
        return new BigDecimal(stack.pop());
    }

    private static List<String> getListString(String suffixExpression) {
        return Arrays.asList(suffixExpression.split(" "));
    }
}
