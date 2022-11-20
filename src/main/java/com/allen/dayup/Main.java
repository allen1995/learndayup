package com.allen.dayup;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException {
        HashMap<String,Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();

        String template = "{\"test\":\"test\",\"test1\":1,\"test2\":{}}";

        map.put("test", mapper.readTree(template).get("test"));
        map.put("test1", mapper.readTree(template).get("test1"));
        map.put("test2", mapper.readTree(template).get("test2"));

        System.out.println(mapper.writeValueAsString(map));

        System.out.println(JSONObject.toJSON(map));
    }
}
