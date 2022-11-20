package com.allen.dayup.arithmetic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lisilong on 2019/2/22.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppRequestModel {
    // 当前app版本信息
    @JsonProperty(value = "appversion")
    private String appversion;
    // 当前移动设备的系统版本信息
    @JsonProperty(value = "sysversion")
    private String sysversion;
    // 设备唯一标识
    @JsonProperty(value = "imei")
    private String imei;
    // 用户唯一标识
    @JsonProperty(value = "userid")
    private String userid;
    // 跟踪id
    @JsonProperty(value = "traceid")
    private String traceid;
    // 具体业务接口请求参数，若无须传参，则param={}。
    @JsonProperty(value = "param")
    private Object param;
    private ObjectMapper objectMapper;

    public String getAppversion() { return appversion; }

    public void setAppversion(String appversion) {
        this.appversion = appversion;
    }

    public String getSysversion() {
        return sysversion;
    }

    public void setSysversion(String sysversion) {
        this.sysversion = sysversion;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getTraceid() { return traceid; }

    public void setTraceid(String traceid) {
        this.traceid = traceid;
    }

    public Object getParam() { return param; }

    public void setParam(String param) {
    	
        this.param = param;
    }
    
    public Map<String,Object> getMapParam() throws IOException { 
    	this.objectMapper = new ObjectMapper();
    	Map<String,Object> map = objectMapper.readValue(this.param.toString(), HashMap.class);
    	if(StringUtils.isEmpty((String)map.get("userid"))) {
    		map.put("userid", this.userid);
    	}
    	if(StringUtils.isEmpty((String)map.get("appversion"))) {
    		map.put("appversion", this.appversion);
    	}
    	if(StringUtils.isEmpty((String)map.get("sysversion"))) {
    		map.put("sysversion", this.sysversion);
    	}
    	if(StringUtils.isEmpty((String)map.get("imei"))) {
    		map.put("imei", this.imei);
    	}
    	if(StringUtils.isEmpty((String)map.get("traceid"))) {
    		map.put("traceid", this.traceid);
    	}
    	return map; 
    }
}