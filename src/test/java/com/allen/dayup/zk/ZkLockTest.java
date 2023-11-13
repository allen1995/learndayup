package com.allen.dayup.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMultiLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ZkLockTest {
    int count = 0;
    
    @Test
    public void test_ZkLock() {
        CuratorFramework client = ZkClient.instance();
        
        // 创建互斥锁
        InterProcessMutex zkMutex = new InterProcessMutex(client, "/mutex");

        ExecutorService pool = Executors.newFixedThreadPool(10);
        
        for (int i = 0; i < 10; i++) {
            
            pool.execute( () -> {
                try {
                    // 加锁
                    zkMutex.acquire();

                    for (int j = 0; j < 10; j++) {
                        count++;
                    }

                    System.out.println("count=" + count);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    
                    // 释放锁
                    try {
                        zkMutex.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
