package com.line.concurrent.locks;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @desc 读写锁
 *  花名册
 * @Author xw
 * @Date 2019/8/9
 */
public class ReentrantReadWriteLockDemo {
    public static void main(String[] args) {

        ReentrantReadData reentrantReadData = new ReentrantReadData();
        for (int i = 0; i < 5; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "写名字");
                reentrantReadData.add(Thread.currentThread().getName());
            }, "同学" + (i+1)).start();

            new Thread(() -> {
                System.out.println("============== 花名册 ===================");
                reentrantReadData.get().stream().forEach(System.out::println);
                System.out.println("=================================");
            }, "同学" + (i*10)).start();
        }

    }
}

class ReentrantReadData {
    volatile Set<String> students = new HashSet<>();
    ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    public void add(String name) {
        reentrantReadWriteLock.writeLock().lock();
        students.add(name);
        reentrantReadWriteLock.writeLock().unlock();
    }

    public Set<String> get() {
        reentrantReadWriteLock.readLock().lock();
        reentrantReadWriteLock.readLock().unlock();
        return students;
    }

}