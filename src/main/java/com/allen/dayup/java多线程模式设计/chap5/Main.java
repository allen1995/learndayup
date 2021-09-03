package com.allen.dayup.java多线程模式设计.chap5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: allen
 * @Date: 2021-07-17 10:14
 * @Description:
 */
public class Main {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        //提交任务
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                    System.out.println("t1 execute complete.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        executorService.shutdown();

        //结束线程池后，再提交一个任务
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("t2 execute complete.");
            }
        });
    }
}
