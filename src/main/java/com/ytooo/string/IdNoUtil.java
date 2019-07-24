package com.ytooo.string;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by ebiz on 2017/8/8.
 */
public class IdNoUtil {
    public static String getIdNoStar(String idNo){
        if (StringUtils.isBlank(idNo)||idNo.length()<14){
            return "";
        }
        return idNo.substring(0,6)+"*****"+idNo.substring(14);
    }
}
