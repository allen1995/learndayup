package com.allen.dayup.java8.concurrent.masterandslave;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public class Worker<T extends Task, R> {
    
    //接受任务的阻塞队列
    private LinkedBlockingQueue<T> taskQueue = new LinkedBlockingQueue<>();
    
    // worker的编号
    static AtomicInteger index = new AtomicInteger();
    
    private int workerId;
    
    // 执行任务的线程
    private Thread thread = null;
    
    
    public Worker() {
        this.workerId = index.getAndIncrement();
        thread = new Thread(() -> this.run());
        thread.start();
        
    }
    
    // 轮询人物的执行方法
    public void run() {
    
        // 轮询启动所有子任务
        for ( ;; ) {
            try {
                T take = this.taskQueue.take();
                take.setWorkerId(workerId);
                take.execute();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    // 提交任务
    public void submit( T task, Consumer<R> action) {
        task.resultAction = action;

        try {
            this.taskQueue.put(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}
