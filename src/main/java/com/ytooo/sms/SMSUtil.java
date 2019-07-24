package com.ytooo.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by handong on 2017/5/8.
 */
@Component
public class SMSUtil {
    private final static Logger logger = LoggerFactory.getLogger(SMSUtil.class);

    static String corpID="TJYW03461";//账户名
    static String pd="123456";//密码
    static String sign="【糖尿病服务推广组】";

    @Deprecated
    public static long sendSMS(String Mobile,String Content,String send_time) throws MalformedURLException, UnsupportedEncodingException {
        String send_content= URLEncoder.encode(StringUtils.trimAllWhitespace(Content+sign), "utf-8");//发送内容
        System.out.print(send_content);
        URL url = new URL("http://api.bjszrk.com/sdk/BatchSend.aspx?CorpID="+corpID+"&Pwd="+pd+"&Mobile="+Mobile+"&Content="+send_content+"&Cell=&SendTime="+send_time+"&encode=utf-8");
        BufferedReader in = null;
        long inputLine = 0;
        try {
            in = new BufferedReader(new InputStreamReader(url.openStream()));
            inputLine =  Long.parseLong(in.readLine());
            in.close();
        } catch (Exception e) {
            logger.error("error sendSMS "+Mobile+" "+Content+"  "+send_time);
            inputLine=-2;
        }

        return inputLine;
    }

}
