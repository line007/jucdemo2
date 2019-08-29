package com.line.concurrent.volatiledemo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @desc AtomicReference ABA问题
 * @Author xw
 * @Date 2019/8/8
 */
public class ABADemo {
    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    static AtomicStampedReference<Integer> atomicReference2 = new AtomicStampedReference<>(100, 1);

    public static void main(String[] args) {
        // 基础版
        atomicReferenceABA();
        // 改良版
        atomicStampedReferenceABA();
    }

    private static void atomicStampedReferenceABA() {
        new Thread(() -> {
            atomicReference2.compareAndSet(100, 101, 1, atomicReference2.getStamp()+1);
            atomicReference2.compareAndSet(101, 100, 2, atomicReference2.getStamp()+1);
        }, "t1").start();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicReference2.compareAndSet(100, 2019, 1, atomicReference2.getStamp()+1) + "\t" + atomicReference.get() + "\t" + atomicReference2.getStamp());
        }, "t2").start();
    }

    private static void atomicReferenceABA() {
        new Thread(() -> {
            atomicReference.compareAndSet(100, 101);
            atomicReference.compareAndSet(101, 100);
        }, "t1").start();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicReference.compareAndSet(100, 2019) + "\t" + atomicReference.get());
        }, "t2").start();
    }

}
