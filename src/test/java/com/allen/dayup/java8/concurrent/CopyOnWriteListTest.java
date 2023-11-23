package com.allen.dayup.java8.concurrent;

import com.allen.dayup.util.Print;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.allen.dayup.util.ThreadUtil.sleepSeconds;

public class CopyOnWriteListTest {

    @Test
    public void testSynchronizedList() {
        List<String> notSafeList = Arrays.asList("a", "b", "c");
        List<String> synList = Collections.synchronizedList(notSafeList);
        
        // 创建一个执行目标
        ConcurrentTarget concurrentTarget = new ConcurrentTarget(synList);

        for (int i = 0; i < 10; i++) {
            new Thread(concurrentTarget, "thread" + i).start();
        }
        
        sleepSeconds(1000);
    }

    @Test
    public void testCopyOnWriteList() {
        List<String> notSafeList = Arrays.asList("a", "b", "c");
        // 并发安全的CopyOnWriteList
        List<String> synList = new CopyOnWriteArrayList<>(notSafeList);

        // 创建一个执行目标
        ConcurrentTarget copyOnWriteListDemo = new ConcurrentTarget(synList);

        for (int i = 0; i < 10; i++) {
            new Thread(copyOnWriteListDemo, "thread" + i).start();
        }

        sleepSeconds(1000);
    }
    
    static class ConcurrentTarget implements Runnable {

        // 并发同步集合
        List<String> targetList = null;

        public ConcurrentTarget(List<String> targetList) {
            this.targetList = targetList;
        }

        @Override
        public void run() {
            Iterator<String> it = targetList.iterator();
            
            while ( it.hasNext() ) {
                // 在迭代操作时，对列表进行修改
                String threadName = Thread.currentThread().getName();

                Print.tco("开始往集合放入元素:" + threadName);
                targetList.add(threadName);
            }
            
        }
    }
    
    
}
