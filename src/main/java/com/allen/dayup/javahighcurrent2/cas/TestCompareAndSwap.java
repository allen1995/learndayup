package com.allen.dayup.javahighcurrent2.cas;

import com.allen.dayup.util.Print;
import com.allen.dayup.util.ThreadUtil;
import sun.misc.Unsafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicLong;

import static com.allen.dayup.util.JvmUtil.getUnsafe;

public class TestCompareAndSwap {
    
    // 基于CAS实现无锁的安全自增
    static class OpitisticLockingPlus {
        
        // 并发数量
        private static final Integer THREAD_NUM = 10;
        
        // 内部值
        private volatile int value;
        
        // 不安全类
        private static final Unsafe unsafe = getUnsafe();
        
        // 获取value偏移量
        private static long valueOffset;
        
        // CAS设值失败统计类
        private final AtomicLong failure = new AtomicLong(0);
        
        static {
            try {
                valueOffset = unsafe.objectFieldOffset(
                        OpitisticLockingPlus.class.getDeclaredField("value"));

                Print.tco("valueOffset=" + valueOffset);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        
        // CAS修改
        public final boolean compareAndSet( int oldValue, int newValue) {
            
            return unsafe.compareAndSwapInt(this, valueOffset, oldValue, newValue);
        }
        
        // 使用无锁编程，实现安全自增方法
        public void selfPlus() {
            int oldValue = value;
            
            int i = 0;
            do {
                oldValue = value;
                
                if( i++ > 1 ) {
                    failure.incrementAndGet();
                }
            } while ( !compareAndSet(oldValue, oldValue + 1));
            
        }


        public static void main(String[] args) throws InterruptedException {
            // 1. 创建CAS测试类实例
            final  OpitisticLockingPlus cas = new OpitisticLockingPlus();
            // 2. 创建CountDownLatch锁，个数跟线程个数相等
            CountDownLatch latch = new CountDownLatch(THREAD_NUM);
            
            // 3. 创建混合任务线程，提交十个任务，任务完成后提交闭锁
            ThreadPoolExecutor mixedTargetThreadPool = ThreadUtil.getMixedTargetThreadPool();

            for (int i = 0; i < THREAD_NUM; i++) {
                mixedTargetThreadPool.submit( () -> {
                    for (int j = 0; j < 1000; j++) {
                        cas.selfPlus();
                    }
                    latch.countDown();
                    Print.tco("线程自增任务完成");
                });
            }
            
            // 4.主线程输出线程计算后结果
            latch.await();
            
            Print.tco("线程自增后结果:" + cas.value);
            Print.tco("cas失败次数:" + cas.failure.get());
            
            mixedTargetThreadPool.shutdownNow();
        }
        
    }
}
