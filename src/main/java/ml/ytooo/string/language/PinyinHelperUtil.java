package ml.ytooo.string.language;

import com.github.houbb.opencc4j.util.ZhConverterUtil;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;

public class PinyinHelperUtil {

    /**
     * 将文字转为汉语拼音
     *
     * @param chineselanguage 要转成拼音的中文
     */
    public String toHanyuPinyin(String chineselanguage) {
        char[] clChars = chineselanguage.trim().toCharArray();
        StringBuilder hanyupinyin = new StringBuilder();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);// 输出拼音全部小写
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不带声调
        defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
        try {
            for (char clChar : clChars) {
                if (String.valueOf(clChar).matches("[\u4e00-\u9fa5]+")) {// 如果字符是中文,则将中文转为汉语拼音
                    hanyupinyin.append(PinyinHelper.toHanyuPinyinStringArray(clChar, defaultFormat)[0]);
                } else {// 如果字符不是中文,则不转换
                    hanyupinyin.append(clChar);
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            System.out.println("字符不能转成汉语拼音");
        }
        return hanyupinyin.toString();
    }

    public static String getFirstLettersUp(String chineseLanguage) {
        return getFirstLetters(chineseLanguage, HanyuPinyinCaseType.UPPERCASE);
    }

    public static String getFirstLettersLo(String chineseLanguage) {
        return getFirstLetters(chineseLanguage, HanyuPinyinCaseType.LOWERCASE);
    }

    public static String getFirstLetters(String chineseLanguage, HanyuPinyinCaseType caseType) {
        char[] clChars = chineseLanguage.trim().toCharArray();
        StringBuilder hanyupinyin = new StringBuilder();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(caseType);// 输出拼音全部大写
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不带声调
        try {
            for (char clChar : clChars) {
                String str = String.valueOf(clChar);
                if (str.matches("[\u4e00-\u9fa5]+")) {// 如果字符是中文,则将中文转为汉语拼音,并取第一个字母
                    hanyupinyin.append(PinyinHelper.toHanyuPinyinStringArray(clChar, defaultFormat)[0].substring(0, 1));
                } else if (str.matches("[0-9]+")) {// 如果字符是数字,取数字
                    hanyupinyin.append(clChar);
                } else if (str.matches("[a-zA-Z]+")) {// 如果字符是字母,取字母
                    hanyupinyin.append(clChar);
                } else {// 否则不转换
                    hanyupinyin.append(clChar);//如果是标点符号的话，带着
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            System.out.println("字符不能转成汉语拼音");
        }
        return hanyupinyin.toString();
    }

    public static String getPinyinString(String chineseLanguage) {
        char[] clChars = chineseLanguage.trim().toCharArray();
        StringBuilder hanyupinyin = new StringBuilder();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);// 输出拼音全部大写
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不带声调
        try {
            for (char clChar : clChars) {
                String str = String.valueOf(clChar);
                if (str.matches("[\u4e00-\u9fa5]+")) {// 如果字符是中文,则将中文转为汉语拼音,并取第一个字母
                    hanyupinyin.append(PinyinHelper.toHanyuPinyinStringArray(
                            clChar, defaultFormat)[0]);
                } else if (str.matches("[0-9]+")) {// 如果字符是数字,取数字
                    hanyupinyin.append(clChar);
                } else if (str.matches("[a-zA-Z]+")) {// 如果字符是字母,取字母

                    hanyupinyin.append(clChar);
                }  // 否则不转换

            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            System.out.println("字符不能转成汉语拼音");
        }
        return hanyupinyin.toString();
    }

    /**
     * 取第一个汉字的第一个字符
     *
     * @return String
     * @throws
     * @Title: getFirstLetter
     * @Description:
     */
    public static String getFirstLetter(String chineseLanguage) {
        char[] chars = chineseLanguage.trim().toCharArray();
        String hanyupinyin = "";
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);// 输出拼音全部大写
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不带声调
        try {
            String str = String.valueOf(chars[0]);
            if (str.matches("[\u4e00-\u9fa5]+")) {// 如果字符是中文,则将中文转为汉语拼音,并取第一个字母
                hanyupinyin = PinyinHelper.toHanyuPinyinStringArray(
                        chars[0], defaultFormat)[0].substring(0, 1);
                ;
            } else if (str.matches("[0-9]+")) {// 如果字符是数字,取数字
                hanyupinyin += chars[0];
            } else if (str.matches("[a-zA-Z]+")) {// 如果字符是字母,取字母

                hanyupinyin += chars[0];
            }  // 否则不转换

        } catch (BadHanyuPinyinOutputFormatCombination e) {
            System.out.println("字符不能转成汉语拼音");
        }
        return hanyupinyin;
    }

    /**
     * 对象string转 简体
     *
     * @param o
     * @return
     * @author Youdmeng
     * Date 2020-05-08
     **/
    public static Object obj2Simplified(Object o) throws IllegalAccessException {

        if (null == o) return null;
        Field[] fields = o.getClass().getDeclaredFields();
        for (Field f : fields) {
            if (StringUtils.equals("class java.lang.String", f.getType().toString())) {
                f.setAccessible(true);
                f.set(o, change2Simplified((String) f.get(o)));
            }
        }
        return o;
    }

    /**
     * 中文转简体
     *
     * @param o
     * @return
     * @author Youdmeng
     * Date 2020-05-08
     **/
    public static String change2Simplified(String o) {
        return ZhConverterUtil.toSimple(o);
    }

    /**
     * 中文转繁体
     *
     * @param o
     * @return
     * @author Youdmeng
     * Date 2020-05-08
     **/
    public static String change2Traditional(String o) {
        return ZhConverterUtil.toTraditional(o);
    }

}