package com.allen.dayup.面经手册.chap2.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: allen
 * @Date: 2021-07-04 10:02
 * @Description:
 */
public class ReentrantLockDemo {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();

        Thread thread1 =  new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    lock.lock();
                    System.out.printf("[%s]获取锁！",Thread.currentThread().getName());
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } finally {
                    lock.unlock();
                    System.out.printf("[%s]释放锁！",Thread.currentThread().getName());
                }
            }
        },"t1");
        thread1.start();

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();

                    System.out.printf("[%s]获取锁！",Thread.currentThread().getName());
                } finally {
                    lock.unlock();
                    System.out.printf("[%s]释放锁！", Thread.currentThread().getName());
                }
            }
        });

        thread2.start();


        try {
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
