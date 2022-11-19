package com.allen.dayup.pattern;

import org.junit.Test;

/**
 * @Auther: 20190598
 * @Date: 2022/3/25 15:41
 * @Description:
 */
public class DemoTest {

    @Test
    public void test_pattern() {
        String str = "abc123def";
        String patt1 = "/[0-9]+/";

        System.out.println(str.matches(patt1));
    }
}
