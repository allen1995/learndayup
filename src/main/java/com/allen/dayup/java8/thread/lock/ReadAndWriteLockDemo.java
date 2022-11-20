package com.allen.dayup.java8.thread.lock;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Auther: 20190598
 * @Date: 2022/3/25 16:57
 * @Description:
 */
public class ReadAndWriteLockDemo {

    public static void main(String[] args) throws InterruptedException {
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock =  readWriteLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

        CompletableFuture.runAsync( () -> {
            for (int i = 0; i < 100; i++) {
                CompletableFuture.runAsync( () -> {

                    try {
                        //读写锁可重入
                        readLock.lock();
                        readLock.lock();

                        System.out.println(Thread.currentThread().getName() + " execute read business logic.");
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {

                        readLock.unlock();
                        readLock.unlock();
                    }

                });
            }
        });

        CompletableFuture.runAsync( () -> {
            for (int i = 0; i < 10; i++) {
                CompletableFuture.runAsync( () -> {

                    try {
                        writeLock.lock();

                        System.out.println(Thread.currentThread().getName() + " execute write business logic.");
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {

                        writeLock.unlock();
                    }

                });
            }
        });

        Thread.currentThread().join();
    }
}
