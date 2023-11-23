package com.allen.dayup.javahighcurrent2.waitnotify;

import com.allen.dayup.javahighcurrent2.waitnotify.actor.Consumer;
import com.allen.dayup.javahighcurrent2.waitnotify.actor.Producer;
import com.allen.dayup.javahighcurrent2.waitnotify.good.Goods;
import com.allen.dayup.javahighcurrent2.waitnotify.good.IGoods;
import com.allen.dayup.util.JvmUtil;
import com.allen.dayup.util.Print;
import com.google.common.base.Supplier;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.allen.dayup.util.ThreadUtil.sleepSeconds;

public class CommunicatePetStore {

    public static void main(String[] args) {
        Print.cfo("当前进程ID:" + JvmUtil.getProcessID());
        System.setErr(System.out);
        
        // 创建享数据区
        DataBuffer<IGoods> buffer = new DataBuffer<>();
        
        // 生产者执行的动作
        Callable<IGoods> produceAction = () -> {
            // 生成一个随机商品
            IGoods iGoods = Goods.produceOne();
            // 将商品加入共享区
            buffer.add(iGoods);

            return iGoods;
        };
        
        // 消费者执行的动作
        Callable<IGoods> consumerAction = () -> {
            IGoods goods = null;
            goods = buffer.fetch();
            return goods;
        };
        
        // 同时并发执行的线程数
        final int nThreads = 20;
        ExecutorService threadPool = Executors.newFixedThreadPool(nThreads);

        // 一个生产者
        int PRODUCER_NUM = 1;
        for (int i = 0; i < PRODUCER_NUM; i++) {
            // 生产者每生产一个商品，间隔50s
            threadPool.submit(new Producer(produceAction, 50));
        }
        
        sleepSeconds(2);
        
        // 消费者
        int CONSUMER_NUM = 11;
        for (int i = 0; i < CONSUMER_NUM; i++) {
            // 消费商品
            threadPool.submit(new Consumer(consumerAction,100));
        }
    }
    
    private static final int MAX_AMOUNT = 1000;
    
    static class DataBuffer<T> {

        // 保存数据
        private List<T> dataList = new LinkedList<>();

        // 数据缓存区厂区
        private Integer amount = 0;

        private final Object LOCK_OBJECT = new Object();
        private final Object NOT_FULL = new Object();
        private final Object NOT_EMPTY = new Object();
        
        // 向数据区添加元素
        public void add(T element) throws Exception {
            
            
            while ( amount > MAX_AMOUNT ) {
                synchronized (NOT_FULL) {
                    Print.tco("队列已经满了");
                    // 等待未满通知
                    NOT_FULL.wait();
                }
            }
            
            synchronized ( LOCK_OBJECT ) {
                dataList.add(element);
                amount++;
            }
            
            synchronized ( NOT_EMPTY ) {
                // 发送未空通知
                NOT_EMPTY.notify();
            }
        }
        
        public T fetch() throws Exception{
            T element = null;
            
            while ( amount <= 0 ) {
                Print.tco("队列已经空了");
                // 等待未空通知
                NOT_EMPTY.wait();
            }
            
            synchronized ( LOCK_OBJECT ) {
                element = dataList.remove(0);
                amount--;
            }
            
            synchronized ( NOT_FULL ) {
                //发送未满通知
                NOT_FULL.notify();
            }
            
            return element;
        }
        
    }
}
