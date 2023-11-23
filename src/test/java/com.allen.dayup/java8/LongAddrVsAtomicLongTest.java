package com.allen.dayup.java8;

import com.allen.dayup.util.Print;
import com.allen.dayup.util.ThreadUtil;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class LongAddrVsAtomicLongTest {

    private static final int TURN = 10000000;

    @Test
    public void testAtomicLong() {
        // 并发任务数
        final int TASK_AMOUNT = 10;

        // 线程池，获取CPU密集型
        ThreadPoolExecutor cpuIntenseTargetThreadPool = ThreadUtil.getCpuIntenseTargetThreadPool();

        // 定义一个原子对象
        AtomicLong value = new AtomicLong(0);

        CountDownLatch latch = new CountDownLatch(TASK_AMOUNT);

        long start = System.currentTimeMillis();
        // 提交累加任务
        for (int i = 0; i < TASK_AMOUNT; i++) {
            cpuIntenseTargetThreadPool.execute(() -> {
                for (int j = 0; j < TURN; j++) {
                    value.incrementAndGet();
                }
                latch.countDown();
                Print.tco("AtomicLong累加任务执行完成");
            });
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        Print.tco("AtomicLong任务执行耗时:" + (end - start) + "ms");
        Print.tco("AtomicLong累加后结果:" + value.get());
    }

    @Test
    public void testLongAddr() {
        // 并发任务数
        final int TASK_AMOUNT = 10;

        // 线程池，获取CPU密集型
        ThreadPoolExecutor cpuIntenseTargetThreadPool = ThreadUtil.getCpuIntenseTargetThreadPool();

        // 定义一个原子对象
        LongAdder longAdder = new LongAdder();

        CountDownLatch latch = new CountDownLatch(TASK_AMOUNT);

        long start = System.currentTimeMillis();
        // 提交累加任务
        for (int i = 0; i < TASK_AMOUNT; i++) {
            cpuIntenseTargetThreadPool.execute(() -> {
                for (int j = 0; j < TURN; j++) {
                    longAdder.add(1);
                }
                latch.countDown();
                Print.tco("LongAdder累加任务执行完成");
            });
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        Print.tco("LongAdder任务执行耗时:" + (end - start) + "ms");
        Print.tco("LongAdder累加后结果:" + longAdder.longValue());
    }
}
