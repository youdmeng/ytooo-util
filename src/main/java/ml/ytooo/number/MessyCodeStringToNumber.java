package ml.ytooo.number;

import org.apache.commons.lang3.StringUtils;

/**
 * 数字字符串格式化为数字
 * Created by Youdmeng on 2018/3/1 0001.
 */
public class MessyCodeStringToNumber {

    /**
     * 数字字符串格式化为数字
     *
     * @param messyCodeString 乱码数字字符串
     * @return 数字字符串
     */
    public static String getNumberFromMessyCodeString(String messyCodeString) {
        //空值
        if (StringUtils.isBlank(messyCodeString)) {
            return null;
        }
        String[] messyCodes = messyCodeString.split("");
        StringBuilder numberStr = new StringBuilder();
        for (String signNumber : messyCodes) {
            Integer falg = null;
            try {
                falg = Integer.parseInt(signNumber);
            } catch (NumberFormatException e) {
                falg = null;
            }

            if (StringUtils.equals(".", signNumber) || falg != null) {
                numberStr.append(signNumber);
            }
        }
        return numberStr.toString();
    }
}
