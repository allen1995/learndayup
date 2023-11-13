package com.allen.dayup.zk;

import org.apache.zookeeper.data.Id;
import org.junit.Test;

public class IdMakerTest {
    
    @Test
    public void test_idMaker() {

        IDMaker idMaker = new IDMaker();
        try {
            idMaker.init();

            String nodeName = "/test/IDMaker/Id-";

            for (int i = 0; i < 10; i++) {
                String s = idMaker.makeId(nodeName);
                System.out.println("第" + i + "个ID:" + s);
            }
        } finally {
            idMaker.destory();
        }


    }
}
