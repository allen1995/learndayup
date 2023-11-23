package com.allen.dayup.javahighcurrent2.waitnotify;

import com.allen.dayup.util.Print;

import static com.allen.dayup.util.ThreadUtil.sleepSeconds;

public class WaitNotifyDemo {
    
    static  Object locko = new Object();

    public static void main(String[] args) {
        Thread waitThread = new Thread(new WaitTarget(), "thread-wait");
        
        waitThread.start();
        sleepSeconds(1);
        
        Thread notifyThread = new Thread(new NotifyTarget(), "thread-notify");
        notifyThread.start();
    }
    
    static class WaitTarget implements  Runnable {

        @Override
        public void run() {
            // 加锁
            
            synchronized (locko) {

                try {
                    //启动等待
                    Print.tco("启动等待");
                    // 等待被通知，同时释放locko监视器的owner权限
                    locko.wait();
                    // 收到通知后，线程进入locko监视器的EntryList，等待被CPU调用
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                Print.tco("收到通知，线程继续执行业务代码");
            }
        }
    }
    
    
    static  class  NotifyTarget implements Runnable {

        @Override
        public void run() {
            // 加锁
            synchronized ( locko ) {
                 // 从屏幕读取输入，主要是为了阻塞线程，方便打印
                Print.consoleInput();
                
                // 获取locko锁，然后发送通知
                locko.notifyAll();
                Print.tco("发出通知了，但是线程没有立马被释放");
            }
        }
    }
}
