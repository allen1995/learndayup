package com.allen.dayup.java8.concurrent.completionfuture;

import com.allen.dayup.util.Print;
import com.allen.dayup.util.ThreadUtil;
import org.junit.Test;

import java.util.concurrent.*;

import static com.allen.dayup.util.ThreadUtil.sleepSeconds;

public class CompletionFutureDemo {

    @Test
    public void runAsyncTest() throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            sleepSeconds(2);// 模拟执行一秒

            Print.tco("run end.");
        });

        // 等待异步任务执行完成
        future.get(3, TimeUnit.SECONDS);
    }

    @Test
    public void test_supplyAsync() throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> {
            long start = System.currentTimeMillis();
            sleepSeconds(1);
            long end = System.currentTimeMillis();

            return end - start;
        });

        // 等待异步任务执行完成
        Long time = future.get(2, TimeUnit.SECONDS);

        Print.tco("异步任务执行耗时:" + time + "ms");
    }

    @Test
    public void test_whenCompletion() throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            sleepSeconds(1);// 模拟执行一秒

            throw new RuntimeException("发生异常");
        });

        // 设置完成任务后的钩子函数
        future.whenComplete((t, action) -> {
            Print.tco("执行完成");
        });

        // 设置异常后的钩子函数
        future.exceptionally(t -> {
            Print.tcfo("执行失败：" + t.getMessage());
            return null;
        });


        // 等待异步任务执行完成
        future.get();

    }

    @Test
    public void test_hadnle() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            sleepSeconds(1);// 模拟执行一秒

            throw new RuntimeException("发生异常");
        });

        // 统一处理异常
        future.handle((input, t) -> {
            if (t == null) {
                Print.tcfo("没有发生异常！");
            } else {
                Print.tcfo("sorry,发生了异常!");
            }
            return null;

        });

        future.get();
    }

    @Test
    public void test_mythreadpool() throws ExecutionException, InterruptedException, TimeoutException {
        ThreadPoolExecutor threadPool = ThreadUtil.getMixedTargetThreadPool();

        CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> {
            Print.tcfo("run begin");
            long start = System.currentTimeMillis();
            sleepSeconds(1);
            long end = System.currentTimeMillis();

            Print.tcfo("run end");
            return end - start;
        }, threadPool);

        // 等待异步任务执行完成
        Long time = future.get(2, TimeUnit.SECONDS);

        Print.tco("异步任务执行耗时:" + time + "ms");

    }

    // 串行化执行任务
    @Test
    public void test_thenApply() throws ExecutionException, InterruptedException {

        CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> {
            long firstStep = 10L + 10L;
            Print.tco("第一步输出:" + firstStep);
            return firstStep;
        }).thenApply(firstStepResult -> {
            long secondStep = firstStepResult * 2;
            Print.cfo("第二步输出:" + secondStep);
            return secondStep;
        });

        long result = future.get();
        Print.tco("最后计算结果:" + result);

    }

    @Test
    public void test_thenCompose() throws ExecutionException, InterruptedException {
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> {
            long firstStep = 10L + 10L;
            Print.tco("第一步输出:" + firstStep);
            return firstStep;
        }).thenCompose(firstStepResult -> {

            return CompletableFuture.supplyAsync(() -> {
                long secondStep = firstStepResult * 2;
                Print.cfo("第二步输出:" + secondStep);
                return secondStep;
            });
        });

        long result = future.get();
        Print.tco("最后计算结果:" + result);
    }

    // 并行执行任务
    @Test
    public void test_thenCombine() throws ExecutionException, InterruptedException {
        CompletableFuture<Long> future1 = CompletableFuture.supplyAsync(() -> {
            long firstStep = 10L + 10L;
            Print.tcfo("并行执行A输出:" + firstStep);
            return firstStep;
        });

        CompletableFuture<Long> future2 = CompletableFuture.supplyAsync(() -> {
            long firstStep = 10L;
            Print.tcfo("并行执行B输出:" + firstStep);
            return firstStep;
        });

        CompletableFuture<Long> future3 = future1.thenCombine(future2, (result1, result2) -> {
            return result1 * result2;
        });

        long result = future3.get();
        Print.tco("最终结果:" + result);
    }

    @Test
    public void test_allof() throws ExecutionException, InterruptedException {

        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            sleepSeconds(1);
            Print.tco("run end");
        });

        CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> {
            sleepSeconds(2);
            Print.tco("run end");

        });

        CompletableFuture<Void> future3 = CompletableFuture.runAsync(() -> {
            sleepSeconds(2);
            Print.tco("run end");

        });

        CompletableFuture<Void> future = CompletableFuture.allOf(future1, future2, future3);

        // 等待所有子任务执行完成后才能返回，取决于最慢的任务
        future.get();
    }

    // 子任务选择执行
    @Test
    public void test_applytoEither() throws ExecutionException, InterruptedException {
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> {
            long firstStep = 10L + 10L;
            Print.tco("first step result:" + firstStep);
            return firstStep;
        });

        CompletableFuture<Long> future1 = CompletableFuture.supplyAsync(() -> {
            long secondStep = 100L + 100L;
            Print.tco("first step result:" + secondStep);
            ThreadUtil.sleepSeconds(1);
            return secondStep;
        });

        // 最后输出的结果取决于future和future1哪个执行的更快
        CompletableFuture<Long> future2 = future.applyToEither(future1, result -> {
            Print.tco("最终结果:" + result);
            return result;
        });

        future2.get();

    }
}
