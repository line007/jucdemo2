package com.line.datastructs.queue;

/**
 * @desc 队列
 * 1.是什么：先进先出的一种数据结构
 * 2.思路分析：
 *      2.1 初始化数组 + 头、尾标识：
 *          定义一个数组 int[] arr = new int[]
 *          定义头front=-1、尾标识位rear=-1
 *      2.2 添加：rear++
 *      2.3 获取：fornt++
 *      2.4 遍历：for (front-rear)
 * @Author xw
 * @Date 2019/8/10
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(3);
        arrayQueue.addQueue(1);
        arrayQueue.addQueue(2);
        arrayQueue.addQueue(3);
        arrayQueue.getQueue();
        arrayQueue.getQueue();
        arrayQueue.getQueue();
        arrayQueue.showQueue();
        System.out.println("--------------");
        arrayQueue.addQueue(4); // 队列已满
        arrayQueue.getQueue(); // 队列已空
    }
}

// 使用数组模拟队列
class ArrayQueue {
    private int maxSize; //表示数组的最大容量
    private int front;//队列头
    private int rear;//队列尾
    private int[] arr; //该数组用于存放数据,模拟队列

    public ArrayQueue(int size) {
        this.maxSize = size;
        this.arr = new int[maxSize];
        this.front = -1;
        this.rear = -1;
    }

    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列已满");
            return;
        }
        arr[++rear] = n;
    }

    // 是否已满（尾+1 == 最大容量）
    private boolean isFull() {
        return (rear + 1) == maxSize;
    }

    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列已空");
        }
        return arr[++front];
    }

    // 是否已空（头==尾）
    private boolean isEmpty() {
        return front == rear;
    }

    public void showQueue() {
        for (int i = front+1; i <= rear; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }
}
