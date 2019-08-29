package com.line.concurrent.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @desc TODO
 * @Author xw
 * @Date 2019/8/9
 */
public class ReentrantLockDemo {

    // 1.使用ReentrantLock
    private final ReentrantLock lock = new ReentrantLock();
    volatile int num = 0;
    public void addLock() {
        lock.lock();
        try {
            num++;
        } finally {
            lock.unlock();
        }
    }

    // 2.使用AtomicInteger原子类
    AtomicInteger n = new AtomicInteger(0);
    public void addAtomic() {
        n.incrementAndGet();
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockDemo retrantLockDemo = new ReentrantLockDemo();
        for(int i = 1; i <= 1000; i++) {
            new Thread(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                    for (int j = 1; j <= 100; j++) {
                        //retrantLockDemo.add();
                        retrantLockDemo.addAtomic();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "T" + (i+1)).start();
        }
        while (Thread.activeCount() > 2) {

        }
        // System.out.println(retrantLockDemo.num);
        System.out.println(retrantLockDemo.n);
    }


}
