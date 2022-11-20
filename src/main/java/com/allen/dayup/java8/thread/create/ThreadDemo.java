package com.allen.dayup.java8.thread.create;

/**
 * @Auther: 20190598
 * @Date: 2022/3/23 14:26
 * @Description:
 */
public class ThreadDemo {

    private static final int MAX_CYCLE = 5;

    static class Task extends Thread {

        public Task(String name) {
            super("DemoThread-" + name);
        }

        @Override
        public void run() {

            for ( int i = 0; i < MAX_CYCLE; i++ ) {
                System.out.println(getName() + ",轮次：" + i );
            }

            System.out.println(getName() + "线程执行结束");
        }
    }

    public static void main(String[] args) {

        for (int i = 0; i < 2; i++) {
            Task task = new Task(String.valueOf(i));
            task.start();
        }

        System.out.println(Thread.currentThread().getName() + " 执行结束");
    }
}
