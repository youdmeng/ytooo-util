package com.ytooo.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ChengZhenxing on 2017/2/20.
 */
public class MobileCheckUtil {
    private static String MOBILE = "(^1[3|4|5|7|8]\\d{9}$)";
    private static String CHINA_MOBILE = "(^1(3[4-9]|4[7]|5[0-27-9]|7[8]|8[2-478])\\d{8}$)|(^1705\\d{7}$)";
    private static String CHINA_UNICOM = "(^1(3[0-2]|4[5]|5[56]|7[6]|8[56])\\d{8}$)|(^1709\\d{7}$)";
    private static String CHINA_TELECOM = "(^1(33|53|77|8[019])\\d{8})|(^1700\\d{7}$)";

    private static final int CM = 1;
    private static final int CN = 2;
    private static final int CT = 3;

    private static final Pattern pattern = Pattern.compile(MOBILE);
    private static final Pattern chinaMobile = Pattern.compile(CHINA_MOBILE);
    private static final Pattern chinaUnicom = Pattern.compile(CHINA_UNICOM);
    private static final Pattern chinaTelecom = Pattern.compile(CHINA_TELECOM);

    public static boolean isMobile(String mobileNumber) {
        Matcher matcher = pattern.matcher(mobileNumber);
        return matcher.matches();
    }

    public static int getMobileOperator(String mobileNumber) {

        if (isMobile(mobileNumber)) {
            Matcher matcher = chinaMobile.matcher(mobileNumber);
            if (matcher.matches()) {
                return CM;
            } else {
                matcher = chinaUnicom.matcher(mobileNumber);
                if (matcher.matches()) {
                    return CN;
                } else {
                    matcher = chinaTelecom.matcher(mobileNumber);
                    if (matcher.matches()) {
                        return CT;
                    } else {
                        return 0;
                    }
                }
            }
        } else {
            return 0;
        }
    }


}
