/**
 * Copyright ®2010 ebiz Co. Ltd.
 * All rights reserved.
 * Package:  com.ebiz.platform.common.api.util
 * FileName: MathUtil.java
 */
package ml.ytooo.number;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * 数学计算工具类
 *
 * @author dingkui
 * @version 1.0
 * @date 2010-11-23
 */
public class MathUtil {

    /**
     * 默认除法精度
     */
    private static final int DEF_DIV_SCALE = 2;

    /**
     * 默认数字，一亿
     */
    private static final BigDecimal HUNDRED_MILLION = new BigDecimal("100000000");

    /**
     * 默认数字，一万
     */
    private static final BigDecimal TEN_THOUSAND = new BigDecimal("10000");

    /**
     * 默认数字，一千
     */
    private static final BigDecimal THOUSAND = new BigDecimal("1000");

    /**
     * 默认数字，一百
     */
    private static final BigDecimal HUNDRED = new BigDecimal("100");

    /**
     * 数组，对应下标（阿拉伯数字）的大写汉字
     */
    private static final StringBuffer CHINESE_NUMBER = new StringBuffer("零壹贰叁肆伍陆柒捌玖");

    /**
     * 做加法
     *
     * @param a 加数
     * @param b 加数
     * @return 和
     */
    public static BigDecimal add(BigDecimal a, BigDecimal b) {
        return a.add(b);
    }

    /**
     * 做减法
     *
     * @param a 被减数
     * @param b 减数
     * @return 差
     */
    public static BigDecimal sub(BigDecimal a, BigDecimal b) {
        return a.subtract(b);
    }

    /**
     * 做乘法
     *
     * @param a 乘数
     * @param b 乘数
     * @return 积
     */
    public static BigDecimal mul(BigDecimal a, BigDecimal b) {
        return a.multiply(b);
    }

    /**
     * 做除法（有精度，结果四舍五入）
     *
     * @param a     被除数
     * @param b     除数
     * @param scale 精度
     * @return 商
     */
    public static BigDecimal divRound(BigDecimal a, BigDecimal b, int scale) {
        if (scale < 0) {
            scale = DEF_DIV_SCALE;
        }
        return a.divide(b, scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 做除法（完整精度）
     *
     * @param a 被除数
     * @param b 除数
     * @return 商
     */
    public static BigDecimal div(BigDecimal a, BigDecimal b) {
        return a.divide(b);
    }

    /**
     * 做除法（有精度，指定小数位之后，全部舍去）
     *
     * @param a     被除数
     * @param b     除数
     * @param scale 精度
     * @return 商
     */
    public static BigDecimal divFloor(BigDecimal a, BigDecimal b, int scale) {
        if (scale < 0) {
            scale = DEF_DIV_SCALE;
        }
       return a.divide(b, DEF_DIV_SCALE, RoundingMode.FLOOR);
    }

    /**
     * 乘方运算
     *
     * @param a 底数
     * @param b 指数
     * @return 计算结果
     */
    public static BigDecimal pow(BigDecimal a, BigDecimal b) {
        double aa = a.doubleValue();
        double bb = b.doubleValue();
        return new BigDecimal(String.valueOf(Math.pow(aa, bb)));
    }

    /**
     * 根据指定的小数位数，四舍五入
     *
     * @param a     原数字
     * @param scale 小数位数
     * @return 处理后的数字
     */
    public static BigDecimal round(BigDecimal a, int scale) {
        if (scale < 0) {
            scale = DEF_DIV_SCALE;
        }
        BigDecimal one = new BigDecimal("1");
        return a.divide(one, scale, RoundingMode.FLOOR);
    }

    /**
     * 根据指定的小数位数，舍去尾数
     *
     * @param a     原数字
     * @param scale 小数位数
     * @return 处理后的数字
     */
    public static BigDecimal floor(BigDecimal a, int scale) {
        if (scale < 0) {
            scale = DEF_DIV_SCALE;
        }
        BigDecimal one = new BigDecimal("1");
        return a.divide(one, scale, RoundingMode.FLOOR);
    }

    /**
     * 比较大小，a>b
     *
     * @param a 参数a
     * @param b 参数b
     * @return boolean型结果，a>b则为true，反之为false
     */
    public static boolean larger(BigDecimal a, BigDecimal b) {
        boolean result = false;
        if (0 < a.compareTo(b)) {
            result = true;
        }
        return result;
    }

    /**
     * 比较大小，a<b
     *
     * @param a 参数a
     * @param b 参数b
     * @return boolean型结果，a<b则为true，反之为false
     */
    public static boolean smaller(BigDecimal a, BigDecimal b) {
        boolean result = false;
        if (0 > a.compareTo(b)) {
            result = true;
        }
        return result;
    }

    /**
     * 比较大小，a=b
     *
     * @param a 参数a
     * @param b 参数b
     * @return boolean型结果，a=b则为true，反之为false
     */
    public static boolean equal(BigDecimal a, BigDecimal b) {
        boolean result = false;
        if (0 == a.compareTo(b)) {
            result = true;
        }
        return result;
    }

    /**
     * 比较大小，a>=b
     *
     * @param a 参数a
     * @param b 参数b
     * @return boolean型结果，a>=b则为true，反之为false
     */
    public static boolean largerOrEqual(BigDecimal a, BigDecimal b) {
        boolean result = false;
        if (0 <= a.compareTo(b)) {
            result = true;
        }
        return result;
    }

    /**
     * 比较大小，a<=b
     *
     * @param a 参数a
     * @param b 参数b
     * @return boolean型结果，a<=b则为true，反之为false
     */
    public static boolean smallerOrEqual(BigDecimal a, BigDecimal b) {
        boolean result = false;
        if (0 >= a.compareTo(b)) {
            result = true;
        }
        return result;
    }

    /**
     * 根据阿拉伯数字，取得中文大写汉字
     *
     * @param value 一位的阿拉伯数字
     * @return 大写汉字
     */
    private static char getChineseNum(int value) {
        return CHINESE_NUMBER.charAt(value);
    }

    /**
     * 将小数部分，转化成大写汉字
     *
     * @param originValue 阿拉伯数字金额的小数部分
     * @return 大写汉字表示的金额
     */
    private static StringBuffer formtDecimalToChinese(BigDecimal originValue) {
        // 返回结果，小数部分
        StringBuffer result = new StringBuffer();
        int value = round(mul(originValue, HUNDRED), 0).intValue();
        if (10 <= value) {
            int tenValue = value / 10;
            value = value % 10;
            result.append(getChineseNum(tenValue));
            result.append("角");
        } else {
            result.append("零");
        }
        if (0 < value) {
            result.append(getChineseNum(value));
            result.append("分");
        }
        return result;
    }

    /**
     * 将整数部分表示成大写汉字
     *
     * @param originValue 金额的整数部分
     * @return 大写汉字表示的金额
     */
    private static StringBuffer formtIntegerToChinese(BigDecimal originValue) {
        // 返回结果，整数部分
        StringBuffer result = new StringBuffer();
        // 当前数
        BigDecimal curValue = new BigDecimal(originValue.toString());
        // 判断金额大于一亿
        if (0 <= curValue.compareTo(HUNDRED_MILLION)) {
            BigDecimal hundredMillionValue = divFloor(curValue, HUNDRED_MILLION, 0);
            curValue = sub(curValue, mul(hundredMillionValue, HUNDRED_MILLION));
            result.append(formatThousandsValueToChinese(hundredMillionValue));
            result.append("亿");
        }
        // 金额大于一万
        if (0 <= curValue.compareTo(TEN_THOUSAND)) {
            // 
            BigDecimal tenThousandValue = divFloor(curValue, TEN_THOUSAND, 0);
            curValue = sub(curValue, mul(tenThousandValue, TEN_THOUSAND));
            if (0 > tenThousandValue.compareTo(THOUSAND)) {
                result.append("零");
            }
            result.append(formatThousandsValueToChinese(tenThousandValue));
            result.append("万");
        }
        // 金额的万以下部分
        if (0 <= curValue.compareTo(BigDecimal.ZERO)) {
            if (0 > curValue.compareTo(THOUSAND)) {
                result.append("零");
            }
            result.append(formatThousandsValueToChinese(curValue));
        }
        if (0 != result.length()) {
            result.append("元");
        }
        return result;
    }

    /**
     * 将千位以内的数字，转化成大写汉字
     *
     * @param originValue 阿拉伯数字
     * @return 大写汉字
     */
    private static StringBuffer formatThousandsValueToChinese(BigDecimal originValue) {
        // 返回结果千位级别汉字表示数字
        StringBuffer result = new StringBuffer();
        // “零”状态位
        boolean zeroFlag = false;
        BigDecimal curValue = new BigDecimal(originValue.toString());
        // 金额大于一千
        if (0 <= curValue.compareTo(THOUSAND)) {
            BigDecimal thousandValue = divFloor(curValue, THOUSAND, 0);
            curValue = sub(curValue, mul(thousandValue, THOUSAND));
            result.append(getChineseNum(thousandValue.intValue()));
            result.append("仟");
        } else if (0 != result.length()) {
            zeroFlag = true;
            result.append("零");
        }
        // 金额大于一百
        if (0 <= curValue.compareTo(HUNDRED)) {
            BigDecimal hundredValue = divFloor(curValue, HUNDRED, 0);
            curValue = sub(curValue, mul(hundredValue, HUNDRED));
            result.append(getChineseNum(hundredValue.intValue()));
            result.append("百");
        } else if (!zeroFlag && 0 != result.length()) {
            zeroFlag = true;
            result.append("零");
        }
        // 金额大于十
        if (0 <= curValue.compareTo(BigDecimal.TEN)) {
            BigDecimal tenValue = divFloor(curValue, BigDecimal.TEN, 0);
            curValue = sub(curValue, mul(tenValue, BigDecimal.TEN));
            result.append(getChineseNum(tenValue.intValue()));
            result.append("拾");
        } else if (!zeroFlag && 0 != result.length()) {
            zeroFlag = true;
            result.append("零");
        }
        // 金额的个位部分
        if (0 < curValue.compareTo(BigDecimal.ZERO)) {
            result.append(getChineseNum(curValue.intValue()));
        }
        return result;
    }

    /**
     * 将阿拉伯数字的金额，转换成大写汉字（分单位以下，四舍五入）
     *
     * @param amount 阿拉伯数字表示的金额
     * @return 大写汉字表示的金额
     */
    public static StringBuffer transAmountToChinese(BigDecimal amount) {
        StringBuffer chineseAmount = new StringBuffer();
        int level = amount.compareTo(BigDecimal.ZERO);
        switch (level) {
            case -1:
                chineseAmount.append("负");
                break;
            case 0:
                chineseAmount.append("零元整");
                break;
            case 1:
                BigDecimal integerPart = round(amount, 0);
                BigDecimal decimalPart = sub(amount, integerPart);
                if (integerPart.compareTo(BigDecimal.ZERO) >= 0) {
                    StringBuffer integerString = formtIntegerToChinese(integerPart);
                    chineseAmount.append(integerString);
                }
                StringBuffer decimalString = formtDecimalToChinese(decimalPart);
                chineseAmount.append(decimalString);
                int decimalLength = decimalString.length();
                switch (decimalLength) {
                    case 0:
                        chineseAmount.append("整");
                        break;
                    case 2:
                        if ('角' == decimalString.charAt(1)) {
                            chineseAmount.append("整");
                        }
                        break;
                }
                break;
        }
        return chineseAmount;
    }

    /**
     * 保留两位小数位数格式化显示金额
     *
     * @param amount 金额
     * @return 格式化后的金额
     */
    public static String formatAmount(BigDecimal amount) {
        if (amount == null || amount.doubleValue() == 0) {
            return "0.00";
        }
        DecimalFormat sdf = new DecimalFormat("###,##0.00");
        return sdf.format(amount);
    }

    /**
     * 保留两位小数位数格式化显示金额
     *
     * @param amount 金额
     * @return 格式化后的金额
     */
    public static String formatAmount2(BigDecimal amount) {
        if (amount == null || amount.doubleValue() == 0) {
            return "0.00";
        }
        DecimalFormat sdf = new DecimalFormat("##0.00");
        return sdf.format(amount);
    }

    /**
     * 根据需要保留的小数位数格式化显示金额
     *
     * @param amount 金额
     * @param count  小数位数
     * @return 格式化后的金额
     */
    public static String formatAmountByRound(BigDecimal amount, int count) {
        if (amount == null || amount.doubleValue() == 0) {
            amount = new BigDecimal(0);
        }
        StringBuilder formatStr = new StringBuilder("##0");

        if (count > 0) {
            formatStr = new StringBuilder("##0.");
            for (int i = 0; i < count; i++) {
                formatStr.append("0");
            }
        }
        DecimalFormat sdf = new DecimalFormat(formatStr.toString());
        return sdf.format(amount);
    }

    /**
     * 根据需要保留的小数位数格式化显示金额
     *
     * @param amount 金额
     * @param count  小数位数
     * @return 格式化后的金额
     */
    public static String formatAmountByRound2(BigDecimal amount, int count) {
        if (amount == null || amount.doubleValue() == 0) {
            amount = new BigDecimal(0);
        }
        StringBuilder formatStr = new StringBuilder("###,##0");

        if (count > 0) {
            formatStr = new StringBuilder("###,##0.");
            for (int i = 0; i < count; i++) {
                formatStr.append("0");
            }
        }
        DecimalFormat sdf = new DecimalFormat(formatStr.toString());
        return sdf.format(amount);
    }

    /**
     * 格式化显示百分比
     *
     * @param amount 百分比
     * @return 格式化后的百分比
     */
    public static String formatPercent(BigDecimal amount) {
        if (amount == null || amount.doubleValue() == 0) {
            return "0.00%";
        }
        DecimalFormat format = new DecimalFormat("#0.00%");
        return format.format(amount);
    }

    /**
     * 格式化显示汇率
     *
     * @param rate 汇率值
     * @return 格式化后的汇率值
     */
    public static String formatRate(Float rate) {
        if (rate == null || rate == 0) {
            return "0.00";
        }
        DecimalFormat sdf = new DecimalFormat("####.00");
        return sdf.format(rate);
    }

    /**
     * 将百分数转换成小数
     *
     * @param value 要操作的字符串
     * @return String 处理后的小数值
     */
    public static double changePercentToDecimal(String value) {
        String percent = value.substring(0, value.lastIndexOf("%"));
        double p = Double.parseDouble(percent);
        return p / 100;
    }

}
