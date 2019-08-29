package com.line.concurrent.locks;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @desc 信号量
 *  停车场（5个车位，20人抢）
 * @Author xw
 * @Date 2019/8/9
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5); // 停车场只有5个车位
        for (int i = 1; i <= 20; i++) { // 20个人抢车位
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "》》》》抢到车位");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "离开了=======");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }, "同学" + i).start();
        }
    }
}
