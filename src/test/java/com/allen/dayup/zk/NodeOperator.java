package com.allen.dayup.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.xml.stream.events.Characters;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class NodeOperator {
    CuratorFramework client;
    
    @Before
    public  void init() {
        String zkAddress = "192.168.56.121:2181";
        // 重试策略
        ExponentialBackoffRetry retry = new ExponentialBackoffRetry(1000, 3);

        client = CuratorFrameworkFactory.
                newClient(zkAddress, retry);
        
        System.out.println("zk实例创建成功," + client );
        
    }
    
    
    @Test
    public void test_create_persitent_node() throws Exception {
        client.start();
        
        String data = "hello";
        byte[] payload = data.getBytes(StandardCharsets.UTF_8);
        String path = "/test/CURD/node-1";

        String s = client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                .forPath(path, payload);

        System.out.println("节点创建成功：" + s);
    }
    
    @Test
    public void test_readNode() throws Exception {
        client.start();
        
        String path = "/test/CURD/node-1";
        Stat stat = client.checkExists().forPath(path);
        
        if( stat != null ) {
            byte[] bytes = client.getData().forPath(path);
            String data = new String(bytes, "UTF-8");
            System.out.println("read data:" + data);
            
            String parentPath = "/test";
            List<String> children = client.getChildren().forPath(parentPath);
            
            for ( String child : children ) {
                System.out.println("child: " + child);
            }
        }
    }
    
    @Test
    public void test_updateNode() throws Exception {
        client.start();
        
        String path = "/test/CURD/node-1";
        
        String data = "hello world";
        byte[] payload = data.getBytes(StandardCharsets.UTF_8);


        Stat stat = client.setData().forPath(path, payload);
        if( stat != null ) {
            System.out.println("写入结果：" + stat);
        }
    }

    @Test
    public void test_updateNode_async() throws Exception {
        AsyncCallback.StringCallback callback = new AsyncCallback.StringCallback() {
            @Override
            public void processResult(int i, String s, Object o, String s1) {
                System.out.println("i = " + i + " | " + "s = " + s 
                        + " | " + " o = " + o + " | " + "s1 = " + s1 );
            }
        };
        client.start();
        
        String path = "/test/CURD/node-1";
        
        String data = "hello world async";
        byte[] payload = data.getBytes(StandardCharsets.UTF_8);


        client.setData()
                .inBackground(callback)
                .forPath(path, payload);

        Thread.sleep(10000);
    }
    
    @Test
    public void test_deleteNode () throws Exception {
        client.start();
        String path = "/test/CURD/node-1";

        client.delete().forPath(path);

        // 删除后查看结果
        String parentPath = "/test";
        List<String> children = client.getChildren().forPath(parentPath);

        for ( String child : children ) {
            System.out.println("child: " + child);
        }
    } 
    
}
