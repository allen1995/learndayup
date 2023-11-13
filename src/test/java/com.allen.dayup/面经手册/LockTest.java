package com.allen.dayup.面经手册;

import com.allen.dayup.面经手册.chap2.lock.SyncLock;
import org.junit.Test;

/**
 * @Auther: allen
 * @Date: 2021-07-03 10:50
 * @Description:
 */
public class LockTest {

    @Test
    public void test_syncLock() throws InterruptedException {
        final SyncLock syncLock = new SyncLock();

        for (int i = 0; i < 10; i++) {
            Thread.sleep(200);
            new Thread(new TestLock(syncLock), String.valueOf(i)).start();
        }

        Thread.sleep(10000);
    }

    static class TestLock implements Runnable {

        private SyncLock lock;

        public TestLock(SyncLock syncLock) {
            this.lock = syncLock;
        }

        @Override
        public void run() {
            //try {
            //    Thread.sleep(1000);
            //} catch (InterruptedException e) {
            //    e.printStackTrace();
            //}

            try {
                lock.lock();
                System.out.printf("Thread[%s] exeute complete.\n", Thread.currentThread().getName());
            } finally {
                lock.unlock();
            }
        }
    }
}
