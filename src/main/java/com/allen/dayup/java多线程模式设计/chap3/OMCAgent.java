package com.allen.dayup.java多线程模式设计.chap3;

/**
 * @Auther: allen
 * @Date: 2021-07-17 08:22
 * @Description: 处理彩信中心路由表的变更
 */
public class OMCAgent extends Thread{
    private volatile boolean isTableModificationMsg = false;

    @Override
    public void run() {
        while (true) {
            if( isTableModificationMsg ) {
                //当收到消息后，重新从内存中加载配置
                MMSCRouter.setInstancce(new MMSCRouter());
            }
        }
    }

    public void tableModificationMsg() {
        this.isTableModificationMsg = true;
    }
}
