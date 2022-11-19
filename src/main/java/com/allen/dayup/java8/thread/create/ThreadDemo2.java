package com.allen.dayup.java8.thread.create;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Auther: 20190598
 * @Date: 2022/3/23 14:26
 * @Description:
 */
public class ThreadDemo2 {

    private static final int MAX_CYCLE = 5;

    static class Task implements Callable<String> {


        @Override
        public String call() {

            Long start = System.currentTimeMillis();

            for ( int i = 0; i < MAX_CYCLE; i++ ) {
                System.out.println(Thread.currentThread().getName() + ",轮次：" + i );
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "线程执行结束");
            return String.valueOf(System.currentTimeMillis() - start);
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        for (int i = 0; i < 2; i++) {
            FutureTask futureTask = new FutureTask<>(new Task());
            Thread task = new Thread(futureTask, "DemoThread2-" + i);

            task.start();
            System.out.println(Thread.currentThread().getName() + " 线程执行时间:" + futureTask.get());
        }

        System.out.println(Thread.currentThread().getName() + " 执行结束");
    }
}
