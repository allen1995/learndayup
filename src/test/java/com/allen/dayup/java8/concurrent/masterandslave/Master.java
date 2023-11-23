package com.allen.dayup.java8.concurrent.masterandslave;

import com.allen.dayup.util.Print;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

public class Master<T extends Task, R> {

    // 所有worker集合
    private HashMap<String, Worker<T, R>> workers = new HashMap<>();
    
    // 任务集合
    private LinkedBlockingQueue<T> taskQueue = new LinkedBlockingQueue<>();
    
    // 任务处理结果集合
    protected Map<String, R> resultMap = new ConcurrentHashMap<>();
    
    // Master任务调度线程
    private Thread thread = null;
    
    // 保存最终的和
    private AtomicLong sum = new AtomicLong(0);
    
    public Master(int workerCount) {
        for (int i = 0; i < workerCount; i++) {
            Worker<T, R> worker = new Worker<>();
            workers.put("子节点" + i, worker);
        }
        thread = new Thread(() -> this.execute());
        thread.start();
    }
    
    // 提交任务
    public void submit(T task) {
        taskQueue.add(task);
    }
    
    // 获取worker结果处理的回调函数
    private void resultCallback(Object o ) {
        Task<R> task = (Task<R>) o;
        
        String taskName = "Worker:" + task.getWorkerId() + "=" 
                + "Task:" + task.getId();
        
        R result = task.getResult();
        resultMap.put(taskName, result);
        
        sum.getAndAdd((Integer)result); // 累加结果
    }

    public void execute() {
        for ( ;; ) {
            // 获取工作节点，分配任务
            for( Map.Entry<String,Worker<T, R>> entry : workers.entrySet() ) {

                T task = null;

                try {
                    task = this.taskQueue.take();

                    Worker worker = entry.getValue();
                    worker.submit(task, this::resultCallback);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    // 获取最终结果
    public void printResult() {
        Print.tco("sum is: " + sum);
        for ( Map.Entry<String, R> entry : resultMap.entrySet() ) {
            String taskName = entry.getKey();
            Print.fo(taskName + ":" + entry.getValue());
        }
    }
}
