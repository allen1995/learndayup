package com.allen.dayup.java8.thread.create;

/**
 * @Auther: 20190598
 * @Date: 2022/3/23 14:26
 * @Description:
 */
public class ThreadDemo1 {

    private static final int MAX_CYCLE = 5;

    static class Task implements Runnable {


        @Override
        public void run() {

            for ( int i = 0; i < MAX_CYCLE; i++ ) {
                System.out.println(Thread.currentThread().getName() + ",轮次：" + i );
            }

            System.out.println(Thread.currentThread().getName() + "线程执行结束");
        }
    }

    public static void main(String[] args) {

        for (int i = 0; i < 2; i++) {
            Thread task = new Thread(new Task(), "DemoThread1-" + i);

            task.start();
        }

        System.out.println(Thread.currentThread().getName() + " 执行结束");
    }
}
