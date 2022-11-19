package com.allen.dayup.java8;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Auther: 20190598
 * @Date: 2022/4/6 13:19
 * @Description:
 */
public class ObjectMapperTest {

    @Test
    public void test_readTree() throws IOException {
        //String s = new String("eyJjb2RlIjoyODUzMiwiaWQiOiIyNmE1Mjg0Ni0wNTFjLTRhYjMtODkxZS1kODQzYzUxYWJkMjYiLCJsb2NhbGl6ZWRNc2ciOiLml6DmlYjnmoTnlKjmiLfouqvku73kv6Hmga8iLCJtZXNzYWdlIjoiaW52YWxpZCB1c2VyIGluZm8ifQo".getBytes(), "UTF-8");
        //System.out.println(s);
        String template = "{\n" +
                "\t\"a\":true,\n" +
                "\t\"b\":1\n" +
                "}";

        Person person = JSONObject.parseObject(template, Person.class);


        System.out.println(person);

        System.out.println(StringUtils.isNotEmpty(null));

        int opentype = 5;
        System.out.println((opentype >= 1 && opentype <= 3) || (opentype == 8)
                || (opentype == 9));

        List<Map<String,String>> lists = new ArrayList<>();
        List<String> strs = lists.stream().map( device -> {
            return device.get("test");
        }).collect(Collectors.toList());

        System.out.println(strs);
    }
}

class Person {
    private boolean a;

    private boolean b;

    public Person() {
    }

    public Person(boolean a, boolean b) {
        this.a = a;
        this.b = b;
    }

    public boolean isA() {
        return a;
    }

    public void setA(boolean a) {
        this.a = a;
    }

    public boolean isB() {
        return b;
    }

    public void setB(boolean b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "Person{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }
}
