package com.allen.dayup.面经手册.chap2.circledepency;

/**
 * @Auther: 20190598
 * @Date: 2021/7/30 14:02
 * @Description:
 */
public class ABTest {

    public static void main(String[] args) {
        new ClassA();
    }
}


class ClassA {
    private ClassB b = new ClassB();
}

class ClassB {

    private ClassA a = new ClassA();
}
