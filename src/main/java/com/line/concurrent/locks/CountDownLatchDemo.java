package com.line.concurrent.locks;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @desc 倒计时
 *  5个人都走了，才关门?
 * @Author xw
 * @Date 2019/8/9
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for(int i = 1; i <= 5; i++) { // 5个同学
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "离开");
                    TimeUnit.SECONDS.sleep(1);
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "同学[" + i + "]").start();
        }
        countDownLatch.await();
        System.out.println("班长关门");
    }
}
