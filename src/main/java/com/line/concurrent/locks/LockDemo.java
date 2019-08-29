package com.line.concurrent.locks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @desc 锁demo
 *  多线程异常：java.util.ConcurrentModificationException
 *  (1) Vector
 *  (2) synchronizedList
 *  (3) CopyOnWriteArrayList
 * @Author xw
 * @Date 2019/8/8
 */
public class LockDemo {
    public static void main(String[] args) throws InterruptedException {
//        List<Integer> list = new ArrayList<>();
//        Vector<Integer> list = new Vector<>();
//        List<Integer> list = Collections.synchronizedList(new ArrayList<>());
        List<Integer> list = new CopyOnWriteArrayList();
        for (int i = 1; i <= 10000; i++) {
            new Thread(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                    list.add(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"T" + i).start();
        }
        TimeUnit.SECONDS.sleep(2);
        System.out.println(list.size());
    }
}
