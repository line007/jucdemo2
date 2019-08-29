package com.line.jvm;

import java.util.Random;

/**
 * @desc
 *  -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+UseG1GC
 * @Author xw
 * @Date 2019/8/28
 */
public class G1Demo {
    public static void main(String[] args) {
        String str = "abc";
        while (true) {
            str += str + new Random().nextInt(77777777) + new Random().nextInt(88888888);
            str.intern();
        }
    }

}
