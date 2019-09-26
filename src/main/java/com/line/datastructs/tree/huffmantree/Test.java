package com.line.datastructs.tree.huffmantree;

/**
 * @desc Test
 * @Author xw
 * @Date 2019/9/16
 */
public class Test {
    public static void main(String[] args) {
        int temp = 49;
        System.out.println(Integer.toBinaryString(temp));
        // 不足8位，补高位，按位与
        // 1 0000 0000 | 0000 0001 => 1 0000 0001
        temp |= 256;
        String str = Integer.toBinaryString(temp);
        System.out.println(str.substring(str.length()-8));
    }
}
