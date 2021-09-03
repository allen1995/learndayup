package com.allen.dayup.java多线程模式设计.chap1;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Auther: allen
 * @Date: 2021-07-15 22:05
 * @Description:
 */
public class WorkerThread {

    public static void main(String[] args) {
        Helper helper = new Helper();
        helper.init();

        //提交任务，此时客户端线程为main线程
        for (int i = 0; i < 10; i++) {
            helper.submit("任务" + i +" | Something...");
        }

    }

    static class Helper {
        private BlockingQueue<String> queue = new ArrayBlockingQueue<>(50);

        private final Thread workerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    String task = null;

                    try {
                        task = queue.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(doProcess(task));
                }
            }
        });

        public void init(){
            workerThread.start();
        }

        protected String doProcess(String task) {
             return task + "->processed.";
        }

        public void submit(String task) {
            try {
                queue.put(task);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}



