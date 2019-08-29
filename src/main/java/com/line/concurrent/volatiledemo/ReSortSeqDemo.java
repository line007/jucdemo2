package com.line.concurrent.volatiledemo;

/**
 * @desc 指令重排
 * @Author xw
 * @Date 2019/8/8
 */
public class ReSortSeqDemo {
    int a = 0;
    boolean flag = false;
    public void method01() { // a与flag没有依赖关系，存在指令重排的可能性
        a = 1;
        flag = true;
    }

    // 多线程环境中线程交替执行，由于编译器优化重排的存在，两个线程中使用的变量能否保证一致性无法确定
    // JDK7以后做了优化
    public void method02() {
        if (flag){
            a = a + 5;
        }
    }

    public static void main(String[] args) {
        ReSortSeqDemo reSortSeqDemo = new ReSortSeqDemo();
        for(int i = 1; i <= 100; i++) {
            new Thread(() -> {
                reSortSeqDemo.method01();
                reSortSeqDemo.method02();
                System.out.println(Thread.currentThread().getName() + " change number to " + reSortSeqDemo.a);
            },"T" + i).start();
        }
    }
}
