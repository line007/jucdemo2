package com.line.concurrent.locks;

import java.util.concurrent.TimeUnit;

/**
 * @desc 死锁
 * @Author xw
 * @Date 2019/8/23
 */
public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new HoldLockThread(lockA, lockB), "AAA").start();
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
        new Thread(new HoldLockThread(lockB, lockA), "BBB").start();
    }
}

class HoldLockThread implements Runnable {
    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) { // 锁A执有锁B
            System.out.println(Thread.currentThread().getName() + "\t 自己执有：" + lockA + "\t 尝试获取：" + lockB);
            try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "\t 自己执有：" + lockB + "\t 尝试获取：" + lockA);
            }
        }
    }
}
