package com.allen.dayup.java8.thread.lock;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.StampedLock;

/**
 * @Auther: 20190598
 * @Date: 2022/3/25 16:39
 * @Description:
 */
public class ReentrantLockDemo {

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();

        CompletableFuture.runAsync( () -> {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " get lock success.");

                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + " back lock success");
            }
        });

        CompletableFuture.runAsync( () -> {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " get lock success.");

            }  finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + " back lock success");
            }
        });


        Thread.sleep(5000L);
    }
}

