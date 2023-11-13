package com.allen.dayup.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class IDMaker {

    private CuratorFramework client;

    @PostConstruct
    public void init() {
        String zkAddress = "192.168.56.121:2181";
        // 重试策略
        ExponentialBackoffRetry retry = new ExponentialBackoffRetry(1000, 3);

        client = CuratorFrameworkFactory.
                newClient(zkAddress, retry);

        System.out.println("zk实例创建成功," + client);
        client.start();
        
    }

    @PreDestroy
    public void destory() {
        client.close();
    }

    public String makeId(String nodeName) {
        String str = createSeqNode(nodeName);
        
        if( str != null ) {
            int index = str.lastIndexOf(nodeName);
            
            if( index >= 0 ) {
                index += nodeName.length();
                return index < str.length() ? str.substring(index) : "";
            }
        }
        
        return str;
    }

    private String createSeqNode(String pathPefix) {
        try {
            String destPath = client.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
                    .forPath(pathPefix);
            
            return destPath;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
