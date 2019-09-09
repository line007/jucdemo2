package com.line.util;

/**
 * @desc 运算符运算
 * @Author xw
 * @Date 2019/9/9
 */
public class OperatorCalc {
    public static void main(String[] args) {
        System.out.println(~12); // 1100 ~ => 1100 + 1 => 1101 = 13
        System.out.println(2 << 3); // 0010 << 0001 0000 => 2*2^3 = 16
        System.out.println(8 >> 2); // 1000 >> 2 => 0010
        System.out.println(12 & 5); // 1100 & 0101 => 0100 = 4
        System.out.println(12 | 5); // 1100 & 0101 => 1101 = 13
    }
}
