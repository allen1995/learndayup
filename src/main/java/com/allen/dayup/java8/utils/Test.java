package com.allen.dayup.java8.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: 20190598
 * @Date: 2020/6/2 13:38
 * @Description:
 */
public class Test {

    public static void main(String[] args) throws IOException {
        String myvalue = "[\n" +
                "\t{\n" +
                "\t\t\"userid\":\"50e8op178ef3fafb410c58dca84f1d179a464083\",\n" +
                "\t\t\"iotid\":\"Test\",\n" +
                "\t\t\"mainid\":8,\n" +
                "\t\t\"triggeriotid\":\"DhCMIlZ8vVy6lSfwbRtO001006d400\",\n" +
                "\t\t\"templateid\":2\n" +
                "\t},\n" +
                "\t\n" +
                "\t{\n" +
                "\t\t\"userid\":\"50e8op178ef3fafb410c58dca84f1d179a464083\",\n" +
                "\t\t\"iotid\":\"Test\",\n" +
                "\t\t\"mainid\":8,\n" +
                "\t\t\"triggeriotid\":\"tLyIqq3I30wwCtVKccvc000100\",\n" +
                "\t\t\"templateid\":2\n" +
                "\t}\n" +
                "]";

        Map<String, AppSimplegatewayTriggerdeviceVo> map = new HashMap<>();
        map.put("1",new AppSimplegatewayTriggerdeviceVo());
        map.put("2", new AppSimplegatewayTriggerdeviceVo());
        String json = Jackson2Utils.converObjectToJson(map);
        System.out.println(json);
        System.out.println();

        Map<String,AppSimplegatewayTriggerdeviceVo> test = Jackson2Utils.converJsonToMap(json, AppSimplegatewayTriggerdeviceVo.class);
        System.out.println(test);

        System.out.println();
        List<AppSimplegatewayTriggerdeviceVo> triggerdeviceVos = Jackson2Utils.converJsonToList(myvalue, AppSimplegatewayTriggerdeviceVo.class);
        System.out.println(triggerdeviceVos);

        System.out.println();
        List<Object> triggerdeviceVos1 = Jackson2Utils.coverJsonToList(myvalue);
        System.out.println(triggerdeviceVos1);

    }
}
