package com.line.jvm;

/**
 * @desc
 * 1.
 * 2
 *  -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParNewGC  (ParNew[par new generation] + Tenured[tenured generation])
 *  备注情况：Java HotSpot(TM) 64-Bit Server VM warning:
 *  Using the ParNew young collector with the Serial old collector is deprecated
 *  and will likely be removed in a future release
 *
 * 3
 *  -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelGC   (PSYoungGen + ParOldGen)
 *
 * 4
 * 4.1
 *  -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelOldGC  (PSYoungGen + ParOldGen)
 * 4.2 不加就默认UseParallelGC
 *  -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags
 *
 * 5
 *  -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseConcMarkSweepGC  （parNew + concurrent mark-sweep generation）
 *
 * 6
 *  -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseG1GC     （garbage-first heap）
 *
 * 7（理论知道即可，实际中已经被优化掉了，没有了。）
 *  -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialOldGC
 *
 * @Author xw
 * @Date 2019/8/28
 */
public class GCDemo {
    public static void main(String[] args) {
        String str = "aaa";
        System.out.println("GC test");
        str = null;
        System.gc();
    }
}
