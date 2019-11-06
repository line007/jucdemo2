package com.line.util;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @desc 正则表达式学习
 * @Author xw
 * @Date 2019/11/6
 */
public class RegUtils {
    public static void main(String[] args) {
        // 1.邮政编码
        System.out.println(checkPostcode("426100"));
        // 2.EMAIL
        System.out.println(checkEmail("abc1@qq.com"));
        // 3.IP地址
        System.out.println(ipValid("192.168.1.111"));
        // 4.正则表达式几种常用功能——查询，提取，替换，分割
        // 查询
        System.out.println(findStr("a|f", "abcd"));
        // 提取
        extractStr();
        // 分割
        splitStr();
        // 替换（删除）
        String regEx = "a+"; //表示一个或多个a
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher("aaabbced a ccdeaa");
        System.out.println("替换：" + m.replaceAll("A"));
        System.out.println("删除：" + m.replaceAll(""));
    }

    private static void splitStr() {
        String regEx = "::";
        Pattern p = Pattern.compile(regEx);
        String[] r = p.split("xd::abc::cde");
        System.out.println(Arrays.toString(r));
    }

    private static void extractStr() {
        String regEx = ".+\\\\(.+)$";
        String str = "c:\\dir1\\dir2\\name.txt";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        boolean rs = m.find();
        if (rs) {
            for (int i = 1; i <= m.groupCount(); i++) {
                System.out.println(m.group(i));
            }
        }
    }

    public static boolean findStr(String regEx, String str) {
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }

    // 3.IP地址
    public static boolean ipValid(String s) {
        String regex0 = "(2[0-4]\\d)" + "|(25[0-5])";
        String regex1 = "1\\d{2}";
        String regex2 = "[1-9]\\d";
        String regex3 = "\\d";
        String regex = "(" + regex0 + ")|(" + regex1 + ")|(" + regex2 + ")|(" + regex3 + ")";
        regex = "(" + regex + ").(" + regex + ").(" + regex + ").(" + regex + ")";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(s);
        return m.matches();
    }

    // 2.EMAIL
    public static boolean checkEmail(String inputStr) {
        Pattern p = Pattern.compile("[0-9A-Za-z]+@([0-9a-zA-Z]+.){1,2}(com|net|cn|com.cn)");
        Matcher m = p.matcher(inputStr);
        if (!m.matches()) {
            System.out.println("****电子邮件格式不符！*****");
            return false;
        }
        return true;
    }

    // 1.邮政编码
    public static boolean checkPostcode(String inputStr) {
        Pattern p = Pattern.compile("[0-9]{6}");
        Matcher m = p.matcher(inputStr);
        if (!m.matches()) {
            System.out.println("****邮政编码格式不符！*****");
            return false;
        }
        return true;
    }

}
