package com.allen.dayup.zk;


import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Test;

public class ZkClient {
    
    public static CuratorFramework client;

    public static final String ZK_ADDRESS = "192.168.56.121:2181";
    
    
    public static CuratorFramework instance() {
        if( client == null ) {
            client = createSimple(ZK_ADDRESS);
        }
        return client;
    }

    /**
     * 简单模式，只需要提供链接地址和充实策略，其他参数用默认值
     */
    public static CuratorFramework createSimple(String zkAddress) {
//        String zkAddress = "192.168.56.121:2181";
        // 重试策略
        ExponentialBackoffRetry retry = new ExponentialBackoffRetry(1000, 3);

        CuratorFramework client = CuratorFrameworkFactory.
                newClient(zkAddress, retry);

        client.start();
        System.out.println("zk实例创建成功," + client );
        
        return client;
    }
    
    public static CuratorFramework createWithBuilder(String zkAddress) {
//        String zkAddress = "192.168.56.121:2181";
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
        
        return client;
    }
}
