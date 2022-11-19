package com.allen.dayup.java8.thread.lock;

import org.apache.commons.lang3.RandomUtils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.locks.StampedLock;

/**
 * @Auther: 20190598
 * @Date: 2022/3/25 17:17
 * @Description:
 */
public class StamedLockDemo {

    public static void main(String[] args) throws InterruptedException {
        Point point = new Point();

        CompletableFuture.runAsync( () -> {
            for (int i = 0; i < 100; i++) {
                CompletableFuture.runAsync( () -> {

                    try {
                        //读写锁可重入

                        double area = point.distanceFromOrigin();
                        System.out.println(Thread.currentThread().getName() + " 计算面积为:" + area);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                });
            }
        });

        CompletableFuture.runAsync( () -> {
            for (int i = 0; i < 10; i++) {
                CompletableFuture.runAsync( () -> {

                    try {
                        double x = RandomUtils.nextDouble(10,100);
                        double y = RandomUtils.nextDouble(10,100);
                        point.move(x, y);

                        System.out.println(Thread.currentThread().getName() + " update x=" + x + ", y=" + y);
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                });
            }
        });

        Thread.currentThread().join();
    }
}


class Point {
    private StampedLock stampedLock = new StampedLock();
    private double x;
    private double y;

    public void move(double x, double y) {
        long stamp = stampedLock.writeLock();

        try {
            this.x = x;
            this.y = y;
        } finally {
            stampedLock.unlock(stamp);
        }
    }

    public double distanceFromOrigin() {
        long stamp = stampedLock.tryOptimisticRead();

        double currentX = this.x;
        double currentY = this.y;

        if( !stampedLock.validate(stamp)) {
            stamp = stampedLock.readLock();

            try {
                currentX = this.x;
                currentY = this.y;
            } finally {
                stampedLock.unlockRead(stamp);
            }
        }

        return Math.sqrt(currentX*currentX + currentY*currentX);
    }

}
