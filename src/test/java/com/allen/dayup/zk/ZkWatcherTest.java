package com.allen.dayup.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

public class ZkWatcherTest {
    
    Logger log = LoggerFactory.getLogger(ZkWatcherTest.class); 

    public static final String ZK_ADDRESS = "192.168.56.121:2181";
    private String workerPath = "/test/listener/remoteNode";
    private String subWorkerPath = "/test/listener/remoteNode/id-";

    /**
     * zk默认的watcher是一次性的，注册完成后只能监听到一次，需要每次都重新注册
     */
    @Test
    public void testWatcher() {

        CuratorFramework client = ZkClient.instance();
        
        try {
            //创建worker节点
            client.create().creatingParentsIfNeeded()
                    .withMode(CreateMode.PERSISTENT)
                    .forPath(workerPath);

            Watcher watcher = new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    System.out.println("监听到的变化 WatcherEvent = " + watchedEvent);
                }
            };
            
            byte[] content = client.getData()
                    .usingWatcher(watcher)
                    .forPath(workerPath);
            log.info("监听到节点的内容:{}", new String(content, StandardCharsets.UTF_8));
            
            // 第一次变更节点内容
            client.setData().forPath(workerPath, "第一次修改节点".getBytes(StandardCharsets.UTF_8));
            
            // 第二次变更节点内容
            client.setData().forPath(workerPath, 
                    "第二次修改节点".getBytes(StandardCharsets.UTF_8));
            
            Thread.sleep(Integer.MAX_VALUE);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过节点缓存监听：Curator实现，可以反复监听一个节点的变动
     */
    @Test
    public void testWatcherNodeCache() {

        CuratorFramework client = ZkClient.instance();

        try {
            //创建worker节点
            Stat stat = client.checkExists().forPath(workerPath);
            if( stat == null ) {
                client.create().creatingParentsIfNeeded()
                        .withMode(CreateMode.PERSISTENT)
                        .forPath(workerPath);
            }
            
            // 创建节点缓存实例
            NodeCache nodeCache = new NodeCache(client, workerPath, false);

            NodeCacheListener listener = new NodeCacheListener() {

                @Override
                public void nodeChanged() throws Exception {
                    ChildData currentData = nodeCache.getCurrentData();
                    log.info("ZNode节点状态变化, path={}", currentData.getPath());
                    log.info("ZNode节点状态变化, data={}", new String(currentData.getData(),
                            StandardCharsets.UTF_8));
                    log.info("ZNode节点状态变化, stat={}", currentData.getStat());

                    System.out.println("ZNode节点状态变化, path=" + currentData.getPath());
                    System.out.println("ZNode节点状态变化, data=" + new String(currentData.getData(),
                            StandardCharsets.UTF_8));
                    System.out.println("ZNode节点状态变化, stat=" + currentData.getStat());
                }
            };

            // 启动节点的事件监听
            nodeCache.getListenable().addListener(listener);
            nodeCache.start();
            
            // 第一次变更节点内容
            client.setData().forPath(workerPath, "第一次修改节点".getBytes(StandardCharsets.UTF_8));
            
            Thread.sleep(2000);
            // 第二次变更节点内容
            client.setData().forPath(workerPath,
                    "第二次修改节点".getBytes(StandardCharsets.UTF_8));
            
            Thread.sleep(2000);
            
            // 第三次变更节点内容
            client.setData().forPath(workerPath,
                    "第三次修改节点".getBytes(StandardCharsets.UTF_8));

            Thread.sleep(2000);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void test_pathCache() {
        CuratorFramework client = ZkClient.instance();


        try {
            //创建worker节点
            Stat stat = client.checkExists().forPath(workerPath);
            if( stat == null ) {
                client.create().creatingParentsIfNeeded()
                        .withMode(CreateMode.PERSISTENT)
                        .forPath(workerPath);
            }

            PathChildrenCache cache = new PathChildrenCache(client, workerPath, true);

            PathChildrenCacheListener listener = new PathChildrenCacheListener() {
                @Override
                public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                    PathChildrenCacheEvent.Type type = pathChildrenCacheEvent.getType();
                    ChildData data = pathChildrenCacheEvent.getData();
                    
                    switch ( type ) {
                        case CHILD_ADDED:
                            System.out.println("子节点新增,path:" + data.getPath() + " | data=" + data.getData());
                            break;
                        case CHILD_UPDATED:
                            System.out.println("子节点修改,path:" + data.getPath() + " | data=" + data.getData());
                            break;
                        case CHILD_REMOVED:
                            System.out.println("子节点删除,path:" + data.getPath() + " | data=" + data.getData());
                            break;
                    }
                }
            };
            
            cache.getListenable().addListener(listener);
            cache.start();

            for (int i = 0; i < 3; i++) {
                ZkClient.instance().create().creatingParentsIfNeeded()
                        .withMode(CreateMode.PERSISTENT)
                        .forPath(subWorkerPath + i);
            }
            
            Thread.sleep(5000);

            for (int i = 0; i < 3; i++) {
                ZkClient.instance().delete()
                        .forPath(subWorkerPath+i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    
    @Test
    public void test_treeCache() {
        CuratorFramework client = ZkClient.instance();


        try {
            //创建worker节点
            Stat stat = client.checkExists().forPath(workerPath);
            if( stat == null ) {
                client.create().creatingParentsIfNeeded()
                        .withMode(CreateMode.PERSISTENT)
                        .forPath(workerPath);
            }

            TreeCache cache = new TreeCache(client, workerPath);

            TreeCacheListener listener = new TreeCacheListener() {
                @Override
                public void childEvent(CuratorFramework curatorFramework, TreeCacheEvent treeCacheEvent) throws Exception {
                    TreeCacheEvent.Type type = treeCacheEvent.getType();
                    ChildData data = treeCacheEvent.getData();

                    switch (type) {
                        case NODE_ADDED:
                            System.out.println("节点新增,path:" + data.getPath() + " | data=" + data.getData());
                            break;
                        case NODE_UPDATED:
                            System.out.println("节点修改,path:" + data.getPath() + " | data=" + data.getData());
                            break;
                        case NODE_REMOVED:
                            System.out.println("节点删除,path:" + data.getPath() + " | data=" + data.getData());
                            break;
                    }
                }
            };

            cache.getListenable().addListener(listener);
            cache.start();

            for (int i = 0; i < 3; i++) {
                ZkClient.instance().create().creatingParentsIfNeeded()
                        .withMode(CreateMode.PERSISTENT)
                        .forPath(subWorkerPath + i);
            }

            Thread.sleep(1000);

            for (int i = 0; i < 3; i++) {
                ZkClient.instance().delete()
                        .forPath(subWorkerPath+i);
            }
            
            Thread.sleep(1000);
            ZkClient.instance().delete().forPath(workerPath);
            
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
