package ml.ytooo.string;

import org.springframework.util.StringUtils;

public class StarTransFormUtil {
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

    /**
     * 字符串换行添加</br>
     * @param str 需要格式化的字符串
     * @param len 字符串分割长度
     * @param headLen  属性名长度
     * @return    格式化后的字符串
     * @author Youdmeng
     * Date 2020-10-15 
     **/
    public static String strAddBr(String str, int len,int headLen) {
        if (0 == len || null == str) { // 数据过滤
            return str;
        }
        int length = str.length(); // 初始字符长度
        int times = length / len;
        for (int i = 0; i <= times; i++) {
            length = str.length(); // 每次添加<br/>字符扩张
            int end = (i + 1) * len + (i * 5) - (0 == i ? 0 : headLen); //配置 添加头数字后,截断长度,并减去多赋值的头
            if (end < length) {
                str = str.substring(0, end) + "<br/>" + str.substring(end, length);
            }
            if (i == 0) { //第二行后添加头长度
                len += headLen;
            }
        }
        return str;
    }
}
