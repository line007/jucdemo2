package com.line.concurrent.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @desc OddEventDemo
 * @Author xw
 * @Date 2019/8/9
 */
public class OddEventDemo {

    public static void main(String[] args) {
        
        //重入锁,ReentrantLock
        Lock lock = new ReentrantLock();
        OddEventNum oddEventNum = new OddEventNum();
        new Thread(() -> {
            while (oddEventNum.num < 100) {
                if (oddEventNum.flag) {
                    lock.lock();
                    System.out.println(" 奇数：" + oddEventNum.num);
                    oddEventNum.num++;
                    oddEventNum.flag = false; // 改变标识位
                    lock.unlock();
                }
                /*synchronized (oddEventNum) {
                    if (!oddEventNum.flag) { // 偶数
                        try {
                            oddEventNum.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println(" 奇数：" + oddEventNum.num);
                        oddEventNum.num++;
                        oddEventNum.flag = false; // 改变标识位
                        oddEventNum.notify();
                    }
                }*/
            }
        }, "Odd").start();
        new Thread(() -> {
            while (oddEventNum.num <= 100) {
                if (!oddEventNum.flag) {
                    lock.lock();
                    System.out.println(" 偶数：" + oddEventNum.num);
                    oddEventNum.num++;
                    oddEventNum.flag = true; // 改变标识位
                    lock.unlock();
                }
                /*synchronized (oddEventNum) {
                    if (oddEventNum.flag) { // 奇数
                        try {
                            oddEventNum.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println(" 偶数：" + oddEventNum.num);
                        oddEventNum.num++;
                        oddEventNum.flag = true; // 改变标识位
                        oddEventNum.notify();
                    }
                }*/
            }
        }, "Event").start();
    }
}

class OddEventNum {
    volatile int num = 1; // 从1开始
    boolean flag = true; // 奇数
}
