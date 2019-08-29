package com.line.concurrent.volatiledemo;

import java.util.concurrent.TimeUnit;

/**
 * @desc volatile可见性、非原子性验证
 * @Author xw
 * @Date 2019/8/19
 */
public class VolatileDemo {
    public static void main(String[] args) {
        // 验证可见性
        seeOkVisibility();
        // 验证非原子性
        seeNonAtomic();
    }

    private static void seeNonAtomic() {
        MyData myData = new MyData();
        for (int i = 1; i <= 100; i++) {
            new Thread(() -> {
                try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
                for (int j = 1; j <= 100 ; j++) {
                    myData.addPlusPlus();
                }
            }, "T" + i).start();
        }
        while (Thread.activeCount() > 2) {

        }
        System.out.println(Thread.currentThread().getName() + "\t mission is over value:" + myData.number);
    }

    // 设置可见性
    private static void seeOkVisibility() {
        MyData myData = new MyData();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");
            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
            myData.addTO60();
            System.out.println(Thread.currentThread().getName() + "\t updated number value: " + myData.number);
        }, "AAA").start();
        while (myData.number == 0) {

        }
        System.out.println(Thread.currentThread().getName() + "\t mission is over");
    }
}

class MyData {
    volatile int number;
    public void addTO60() {
        this.number = 60;
    }
    public void addPlusPlus() {
        this.number ++;
    }
}
