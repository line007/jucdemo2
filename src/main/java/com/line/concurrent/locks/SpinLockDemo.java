package com.line.concurrent.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @desc TODO
 * @Author xw
 * @Date 2019/8/29
 */
public class SpinLockDemo {
    AtomicReference<Thread> atomicReference = new AtomicReference<>();
    public void myLock() {
        Thread thread = Thread.currentThread();
//        System.out.println(thread.getName() + "\t come in (...)");
        while (!atomicReference.compareAndSet(null, thread)) {

        }
    }

    public void myUnlock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
//        System.out.println(Thread.currentThread().getName() + "\t invoked myUnlock()");
    }

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        MyData myData = new MyData();
        for (int i = 1; i <= 10000; i++) {
            new Thread(() -> {
                try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
                spinLockDemo.myLock();
                for (int j = 1; j <= 100 ; j++) {
                    myData.addPlusPlus();
                }
                spinLockDemo.myUnlock();
            }, String.valueOf(i)).start();
        }
        while (Thread.activeCount() > 2) {

        }
        System.out.println(Thread.currentThread().getName() + "\t mission is over value:" + myData.number);
    }
}
class MyData {
    int number;
    public void addPlusPlus() {
        this.number ++;
    }
}
