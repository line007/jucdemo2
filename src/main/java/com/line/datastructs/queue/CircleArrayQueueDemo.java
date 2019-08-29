package com.line.datastructs.queue;

/**
 * @desc 模拟环形队列
 *  1.是什么：先进先出并且能重复利用的一种数据结构，
 *  2.思路分析：
 *      2.1 初始化数组 + 第一个元素、最后一个元素标识：
 *          定义一个数组 int[] arr = new int[maxSize]
 *          第一个元素: front = 0
 *          最后一个元素：rear = 0
 *      2.2 添加：(rear++ % maxSize)
 *      2.3 获取：(front++ % maxSize)
 *      2.4 遍历：for (font%maxSize -> rear*maxSize)
 *
 * @Author xw
 * @Date 2019/8/24
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        CircleArrayQueue circleArrayQueue = new CircleArrayQueue(4); // 只能放3个元素，第一个位置空出来
        circleArrayQueue.addQueue(1);
        circleArrayQueue.addQueue(2);
        circleArrayQueue.addQueue(3);
        circleArrayQueue.showQueue();
        System.out.println("--------------------");
        circleArrayQueue.getQueue();
        circleArrayQueue.addQueue(4);
        circleArrayQueue.showQueue();
        System.out.println("--------------------");
        circleArrayQueue.addQueue(5); // 队列已满
        circleArrayQueue.getQueue();
        circleArrayQueue.getQueue();
        circleArrayQueue.getQueue();
        circleArrayQueue.getQueue(); // 队列已空
    }
}

class CircleArrayQueue {
    private int maxSize; //表示数组的最大容量
    private int front;// 第一个元素
    private int rear;// 最后一个元素
    private int[] arr; //该数组用于存放数据,模拟队列

    public CircleArrayQueue(int size) {
        this.maxSize = size;
        this.arr = new int[maxSize];
        this.front = 0;
        this.rear = 0;
    }

    public void addQueue(int n) {
        //判断队列是否已满
        if(isFull()){
            System.out.println("队列满,不能加入数据");
            return;
        }
        arr[(rear++ % maxSize)] = n;
    }

    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列已空");
        }
        return arr[front++ % maxSize];
    }

    // 是否已空（第一个元素 == 最后一个元素）
    private boolean isEmpty() {
        return front == rear;
    }
    //判断队列是否已满
    public boolean isFull(){
        return (rear+1)%maxSize == front;
    }

    public void showQueue() {
        for (int i = front; i <= rear; i++) {
            System.out.printf("arr[%d]=%d\n", i%maxSize, arr[i%maxSize]);
        }
        /*for (int i = front; i <= front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i%maxSize, arr[i%maxSize]);
        }*/
    }
    /*//求出当前队列有效数据的个数
    public int size(){
        //加上maxSize 防止模出负数 因为这是一个环形队列
        return (rear + maxSize - front)%maxSize;
    }*/
}
