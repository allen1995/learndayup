package com.allen.dayup.java多线程模式设计.chap3;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: allen
 * @Date: 2021-07-17 08:00
 * @Description: 彩信中心路由规则管理器
 */
public final class MMSCRouter {

    //用volatile修饰，保证多线程下的可见性
    private static volatile MMSCRouter instance = new MMSCRouter();

    //维护手机号码前缀到彩信中心的映射关系
    private final Map<String,MMCInfo> routeMap;


    public MMSCRouter() {
        //将数据库中的配置加载到内存中
        this.routeMap = retrieveRoutemapFromDb();
    }

    private Map<String, MMCInfo> retrieveRoutemapFromDb() {
        Map<String,MMCInfo> data = new HashMap<>();
        //省略加载代码

        return data;
    }

    /**
     * 根据手机号码前缀获取对应彩信中心信息
     * @param msisdnPrefix
     * @return
     */
    public MMCInfo getMMSC(String msisdnPrefix) {
        return  routeMap.get(msisdnPrefix);
    }

    /**
     * 将当前的实例更新为指定的新实例
     * @param newInstance
     */
    public static void setInstancce(MMSCRouter newInstance) {
        instance = newInstance;
    }

    private static Map<String,MMCInfo> deepCopy(Map<String,MMCInfo> mmcInfoMap) {
        Map<String,MMCInfo> result = new HashMap<>();

        for( Map.Entry<String,MMCInfo> entry : mmcInfoMap.entrySet() ) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }

    /**
     * 获取路由集合
     * @return
     */
    public  Map<String,MMCInfo> getRoutemap() {
        return Collections.unmodifiableMap(deepCopy(routeMap));
    }
}
