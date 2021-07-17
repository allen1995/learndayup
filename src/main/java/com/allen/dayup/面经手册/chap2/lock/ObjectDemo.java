package com.allen.dayup.面经手册.chap2.lock;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

/**
 * @Auther: allen
 * @Date: 2021-07-03 09:44
 * @Description:
 */
public class ObjectDemo {

    public static void main(String[] args) {
        System.out.println(VM.current().details());
        Object obj = new Object();
        System.out.println(obj + " 十六进制哈希：" + Integer.toHexString(obj.hashCode()));

        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
    }
}
