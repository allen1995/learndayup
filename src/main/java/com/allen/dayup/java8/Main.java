package com.allen.dayup.java8;


import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Auther: 20190598
 * @Date: 2021/9/24 10:07
 * @Description:
 */
public class Main {

    public static void main(String[] args) {
        String content = "50dbopbb42c0605c18e932d939f9dc8166e90785" + "ieb652b43nk7fcpyajj6i64fp4mdyz7m" + "8esp8jhp6tkss2adzk88p45hzay3nazr8yzdadh6cbf3fi3mewb7zryx8fwwjf4h83nfdpbayafw5mj7imbzbx83ndhcky2ss7mr3jkirej2h3fsc6rc2pxa87nnm2jzjc2zbj4w8rm7jixfbkkhayeff2fajmrmttzsjaah2zdwiad6xb4cnydtpmfnb24e7pm5az45wkfkbh2mzw3zsiabdie47hrn6fan3dp54z2y8md32e88ksfmbrhje4zm"
                + "1632820755";


        //String content = "12314214" + "ieb652b43nk7fcpyajj6i64fp4mdyz7m" + "8esp8jhp6tkss2adzk88p45hzay3nazr8yzdadh6cbf3fi3mewb7zryx8fwwjf4h83nfdpbayafw5mj7imbzbx83ndhcky2ss7mr3jkirej2h3fsc6rc2pxa87nnm2jzjc2zbj4w8rm7jixfbkkhayeff2fajmrmttzsjaah2zdwiad6xb4cnydtpmfnb24e7pm5az45wkfkbh2mzw3zsiabdie47hrn6fan3dp54z2y8md32e88ksfmbrhje4zm"
        //        + "1632449367284";

        String result = DigestUtils.sha1Hex(content.getBytes());
        System.out.println(result);

        //test_insert_head_LinkedList();

        //
        System.out.println("bb100000000017263b123456780105000001000101030103140f6464500001386cef20386cef2000000000000000f7".length());
    }

    public static void test_insert_ArrayList() {
        List<Integer> list = new ArrayList<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            list.add(i);
        }

        System.out.println("耗时:" + (System.currentTimeMillis() - start));
    }

    public static void test_insert_last_LinkedList() {
        LinkedList<Integer> list = new LinkedList<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            list.addLast(i);
        }

        System.out.println("耗时:" + (System.currentTimeMillis() - start));
    }

    public static void test_insert_head_LinkedList() {
        LinkedList<Integer> list = new LinkedList<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            list.addFirst(i);
        }

        System.out.println("耗时:" + (System.currentTimeMillis() - start));
    }
}
