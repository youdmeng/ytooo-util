package com.ytooo.string;

import org.springframework.util.StringUtils;

/**
 * Created by ebiz on 2017/8/13.
 */
public class StarStrUtil {
    /**
     * 证件号星号加密
     * @param str
     * @return
     */
    public static String asteriskByIdNo(String str) {
        if(StringUtils.hasText(str)) {
            str = str.trim();
            if(str.length() == 18){
                StringBuilder star = new StringBuilder();
                for(int i = 0 ;i < str.length() - 8;i++){
                    star.append("*");
                }
                str = str.substring(0, 7) + star + str.substring(str.length() - 1);
            }else if(str.length() == 15) {
                StringBuilder star2 = new StringBuilder();
                for(int i=0 ;i < str.length() - 4; i++){
                    star2.append("*");
                }
                str = str.substring(0, 3) + star2 + str.substring(str.length() - 1);
            }
        }
        return str;
    }

    /**
     * 手机号星号加密
     * @param str
     * @return
     */
    public static String asteriskByMobile(String str) {
        if(StringUtils.hasText(str)) {
            str = str.trim();
            if(str.length() >= 11) {
                str = str.substring(0, 3) + "****" + str.substring(str.length() - 4);
            } else {
                str = str.substring(0, 1) + "*****";
            }
        }
        return str;
    }

    /**
     * 账户号星号加密
     * @param str
     * @return
     */
    public static String asteriskByAccountNo(String str) {
        if(StringUtils.hasText(str)) {
            str = str.trim();
            if(str.indexOf("@") > 0) {
                String[] strs = str.split("@");
                if( strs[0].length() > 3 ) {
                    str = strs[0].substring(0, 3) + "***@" + strs[1];
                } else {
                    str = strs[0] + "***@" + strs[1];
                }
            } else {
                StringBuilder star = new StringBuilder();
                if(str.length() < 4) {
                    for(int i = 0 ;i < 11 - str.length(); i++){
                        star.append("*");
                    }
                    str = star + str;
                } else if(str.length() <= 11) {
                    str = "*******" + str.substring(str.length() - 4);
                }else{
                    StringBuilder star2 = new StringBuilder();
                    for(int i = 0 ;i < str.length() - 8;i++){
                        star2.append("*");
                    }
                    str = str.substring(0, 4) + star2 + str.substring(str.length() - 4);
                }
            }
        }
        return str;
    }

    /**
     * 姓名星号加密
     * @param str
     * @return
     */
    public static String asteriskByName(String str) {
        if(StringUtils.hasText(str)) {
            str = str.trim();
            if(str.length() >= 3) {
                str = str.substring(0, 1) + "**";
            } else {
                str = str.substring(0, 1) + "*";
            }
        }
        return str;
    }
}
