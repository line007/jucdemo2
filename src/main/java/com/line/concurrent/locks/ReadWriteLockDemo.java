package com.line.concurrent.locks;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @desc 读写锁
 *  多个线程操作一个资源类，所以为了满足并发量，读取共享资源应该可以同时进行。
 *  写操作：原子 + 独占，整个过程必须是一个整体，中间不能被分解、打断
 *  小总结：
 *      读读共存
 *      读写互斥
 *      写写互斥
 * @Author xw
 * @Date 2019/8/20
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 1; i <= 5; i++) {
            final int tempInt = i;
            new Thread(() -> {
                myCache.put(tempInt + "", tempInt + "");
            }, String.valueOf(i)).start();
        }

        for (int i = 1; i <= 5; i++) {
            final int tempInt = i;
            new Thread(() -> {
                myCache.get(tempInt + "");
            }, String.valueOf(i)).start();
        }

    }
}

class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    // 写
    public void put(String key, Object value) {
        lock.writeLock().lock();
        System.out.println(Thread.currentThread().getName() + "\t 正在写入：" + key);
        try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }
        map.put(key, value);
        System.out.println(Thread.currentThread().getName() + "\t 写入完成：" + key);
        lock.writeLock().unlock();
    }
    // 读
    public void get(String key) {
        lock.readLock().lock();
        System.out.println(Thread.currentThread().getName() + "\t 正在读取：" + key);
        try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }
        Object value = map.get(key);
        System.out.println(Thread.currentThread().getName() + "\t 读取完成：" + value);
        lock.readLock().unlock();
    }
}
