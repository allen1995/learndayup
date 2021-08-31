package com.allen.dayup.java8;



/**
 * @Auther: 20190598
 * @Date: 2021/8/11 19:30
 * @Description:
 */
public class AssertsDemo {

    public static void main(String[] args) {
        testAsserts(-1);
    }

    public static void testAsserts(int a ) {
        //当a>0,打印a的值
        assert a > 0 : "a必须大于0";

        System.out.println(a);
    }
}
