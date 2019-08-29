package com.line.concurrent.arraylist;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @desc 非线程安全容器
 *  1.异常
 *      java.util.ConcurrentModificationException
 *  2.解决方案
 *      2.1 new Vector<>();
 *      2.2 Collections.synchronizedList(new ArrayList<>());
 *      2.3 new CopyOnWriteArrayList<>();
 * @Author xw
 * @Date 2019/8/20
 */
public class ContainerNotSafeDemo {
    public static void main(String[] args) {
        //listNotSafe();
        //setNotSafe();
        mapNotSafe();
    }

    private static void mapNotSafe() {
        // Map<String, String> map = new HashMap<>();
        // Map<String, String> map = Collections.synchronizedMap(new HashMap<>());
        Map<String, String> map = new ConcurrentHashMap<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), Thread.currentThread().getName());
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }

    private static void setNotSafe() {
        //Set<String> list = new HashSet<>();
        //Set<String> list = Collections.synchronizedSet(new HashSet<>());
        Set<String> list = new CopyOnWriteArraySet<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }

    private static void listNotSafe() {
        // List<String> list = new ArrayList<>();
        // List<String> list = new Vector<>();
        // List<String> list = Collections.synchronizedList(new ArrayList<>());
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
