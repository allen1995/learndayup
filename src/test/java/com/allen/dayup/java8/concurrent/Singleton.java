package com.allen.dayup.java8.concurrent;

// 静态内部类懒加载模式
public class Singleton {
    
    private Singleton() {}
   
   // 使用静态内部类懒加载 
    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }
    
    public static final Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
