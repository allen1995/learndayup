package com.allen.dayup.rxjava;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther: 20190598
 * @Date: 2022/11/4 14:24
 * @Description:
 */

public class Main {

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger(1);

        for ( int i = 0; i < 5; i++ ) {
            CompletableFuture.supplyAsync(() -> {
               while (true) {
                   System.out.println("Thread[" + Thread.currentThread().getName()+"],atomicInteger:" + atomicInteger.getAndIncrement());
                   if( atomicInteger.get() > 30 ) {
                       return "";
                   }
               }
            });
        }

        Thread.sleep(10000);
    }
}
