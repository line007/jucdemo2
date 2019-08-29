package com.line.concurrent.volatiledemo;

import java.util.concurrent.TimeUnit;

/**
 * @desc DCL单例 + volatile（禁止指令重排）
 * @Author xw
 * @Date 2019/8/20
 */
public class SingletonDemo {
    private static volatile SingletonDemo instance = null;

    private SingletonDemo() {
        System.out.println(Thread.currentThread().getName() + "\t 我是构造方法");
    }

    public static SingletonDemo getInstance() {
        if (instance == null) {
            synchronized (SingletonDemo.class) {
                if (instance == null) {
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 1; i < 100; i++) {
            new Thread(() -> {
                try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
                SingletonDemo.getInstance();
            }, "T" + i).start();
        }
    }
}
