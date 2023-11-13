package com.allen.dayup.zk;


import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Test;

public class ClientFactory {

    /**
     * 简单模式，只需要提供链接地址和充实策略，其他参数用默认值
     */
    @Test
    public void test_createSimple() {
        String zkAddress = "192.168.56.121:2181";
        // 重试策略
        ExponentialBackoffRetry retry = new ExponentialBackoffRetry(1000, 3);

        CuratorFramework client = CuratorFrameworkFactory.
                newClient(zkAddress, retry);

        System.out.println("zk实例创建成功," + client );
    }
    
    @Test
    public void test_with_build() {
        String zkAddress = "192.168.56.121:2181";
        // 重试策略
        ExponentialBackoffRetry retry = 
                new ExponentialBackoffRetry(1000, 3);
        int connectTimeoutMs = 1000;
        int sessionTimeoutMs = 60000;

        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(zkAddress)
                .retryPolicy(retry)
                .connectionTimeoutMs(connectTimeoutMs)
                .sessionTimeoutMs(sessionTimeoutMs).build();

        System.out.println("zk client创建成功：" + client);
    }
}
