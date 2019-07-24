package com.ytooo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 配置文件
 * Created by Youdmeng on 2019/7/10 0010.
 */

@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtCconfig {

    private String password;

    /**
     * 属性get
     */
    public String getPassword() {
        return password;
    }

    /**
     * 属性set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
