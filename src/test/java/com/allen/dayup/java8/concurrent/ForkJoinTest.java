package com.allen.dayup.java8.concurrent;

import com.allen.dayup.util.Print;
import org.junit.Test;

import java.util.concurrent.*;

public class ForkJoinTest {
    
    @Test
    public  void testAccumulate() throws ExecutionException, InterruptedException, TimeoutException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        
        // 创建一个累加任务
        AccumulateTask accumulateTask = new AccumulateTask(1,100);

        //region Description
        ForkJoinTask<Integer> future = forkJoinPool.submit(accumulateTask);
        //endregion

        Integer result = future.get(30, TimeUnit.SECONDS);

        Print.tcfo("最后计算结果:" + result);
    }
}
