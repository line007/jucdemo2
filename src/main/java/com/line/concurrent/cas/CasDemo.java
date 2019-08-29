package com.line.concurrent.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @desc CAS保证原子性
 *  T1 100 -> 101
 *  T2 100 -> 2019
 * @Author xw
 * @Date 2019/8/20
 */
public class CasDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(100);
        // T1 100 -> 101
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t updated: " + atomicInteger.compareAndSet(100, 101) + ",value: " + atomicInteger.get());
        }, "T1").start();
        // T2 100 -> 2019
        new Thread(() -> {
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName() + "\t updated: " + atomicInteger.compareAndSet(100, 2019) + ",value: " + atomicInteger.get());
        }, "T2").start();
    }
}
