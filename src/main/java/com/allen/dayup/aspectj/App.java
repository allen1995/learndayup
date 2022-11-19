package com.allen.dayup.aspectj;

/**
 * @Auther: 20190598
 * @Date: 2021/9/30 10:37
 * @Description:
 */
public class App {

    public static void main(String[] args) {
        App app = new App();
        app.say();
    }

    public void say() {
        System.out.println(System.getProperty("java.class.path"));
        System.out.println("App say.");
    }
}
