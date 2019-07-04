package com.ytooo.security.bean;

/**
 * 响应数据结构
 * Created by Youdmeng on 2019/6/13 0013.
 */
public class SecurityBean {
    private String key;

    private String baseStr;

    /**
     * 属性get
     */
    public String getKey() {
        return key;
    }

    /**
     * 属性set
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * 属性get
     */
    public String getBaseStr() {
        return baseStr;
    }

    /**
     * 属性set
     */
    public void setBaseStr(String baseStr) {
        this.baseStr = baseStr;
    }

    public SecurityBean(String key, String baseStr) {
        this.key = key;
        this.baseStr = baseStr;
    }

    public SecurityBean() {
    }
}
