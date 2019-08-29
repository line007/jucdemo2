package com.line.concurrent.queue;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @desc
 * 题目：一个初始值为零的变量，两个线程对其交替操作，一个加1一个减1，来5轮
 *  线程      操作（方法）      资源类
 *  判断      干活              通知
 *  防止虚假唤醒：多线程判断一定要用while，防止虚假唤醒！！！
 *
 * @Author xw
 * @Date 2019/8/23
 */
public class ProConsumer_TaditionDemo {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
                shareData.increment();
            }
        }, "AA").start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                shareData.decrease();
            }
        }, "BB").start();
    }
}

class ShareData {
    volatile int number;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    // 加1
    public void increment() {
        lock.lock();
        try {
            // （1）判断：多线程判断一定要用while，防止虚假唤醒！！！
            while (number != 0) {
                // 等待，不能生产
                condition.await();
            }
            // （2）干活
            number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            // （3）通知
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    // 减少
    public void decrease() {
        lock.lock();
        try {
            // （1）判断：多线程判断一定要用while，防止虚假唤醒！！！
            while (number != 1) {
                // 等待，不能生产
                condition.await();
            }
            // （2）干活
            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            // （3）通知
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
