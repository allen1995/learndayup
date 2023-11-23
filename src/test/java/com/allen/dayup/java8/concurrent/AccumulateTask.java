package com.allen.dayup.java8.concurrent;

import com.allen.dayup.util.Print;

import java.util.concurrent.RecursiveTask;

public class AccumulateTask extends RecursiveTask<Integer> {
    
    private static final int THRESHOLD = 5;
    
    // 起始编号
    private int start; 
    
    // 结束编号
    private int end;

    public AccumulateTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        
        // 判断任务规模，规模小可以直接计算
        boolean canCompute = (start < end) && (end-start) <= THRESHOLD;
        if( canCompute ) {
            // 直接计算结果并返回
            for (int i = start; i <= end; i++) {
                sum += i;
            }

            Print.tcfo("执行任务,计算" + start + "到" + end 
                + "的和,结果是:" + sum);
            
        } else {
            // 任务过大，需要切分
            Print.tcfo("切分任务:将" + start + "到" + end + "的和一分为二");
            
            int middle = (end + start) / 2;
            
            // 切分成两个子任务
            AccumulateTask lTask = new AccumulateTask(start, middle);
            AccumulateTask rTask = new AccumulateTask(middle + 1, end);
            
            // 执行子任务
            lTask.fork();
            rTask.fork();
            
            // 等待子任务完成，获取子任务的计算结果
            Integer lResult = lTask.join();
            Integer rResult = rTask.join();
            
            // 合并结果
            sum = lResult + rResult;
        }
        
        return sum;
    }
}
