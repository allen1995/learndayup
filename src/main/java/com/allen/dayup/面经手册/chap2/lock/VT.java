package com.allen.dayup.面经手册.chap2.lock;

/**
 * @Auther: allen
 * @Date: 2021-07-03 09:00
 * @Description:
 */
public class VT implements Runnable{

    public volatile boolean sign = false;


    @Override
    public void run() {
        while ( !sign ) {
        }
        System.out.println("你好！");
    }
}
