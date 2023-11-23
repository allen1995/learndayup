package com.allen.dayup.java8.concurrent.masterandslave;

import com.allen.dayup.util.Print;
import com.allen.dayup.util.ThreadUtil;

import java.util.concurrent.TimeUnit;

public class MasterWorkTest {

    public static void main(String[] args) {
        // 创建Master, 初始化四个工作组
        Master<SimpleTask, Integer> master = new Master<>(4);
        
        // 定期向Master提交任务
        ThreadUtil.scheduleAtFixedRate(() -> master.submit(new SimpleTask()), 
                2, TimeUnit.SECONDS);
        
        // 定期从master提取结果
        ThreadUtil.scheduleAtFixedRate( () -> master.printResult(), 
                5, TimeUnit.SECONDS);
        
    }
    
    //简单任务
    static class SimpleTask extends Task<Integer> {

        @Override
        protected Integer doExecute() {

            Print.tco("task:" + getId() + " is done");
            return getId();
        }
    }
}
