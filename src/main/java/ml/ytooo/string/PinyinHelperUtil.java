package ml.ytooo.string;

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
        char[] cl_chars = chineselanguage.trim().toCharArray();
        StringBuilder hanyupinyin = new StringBuilder();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);// 输出拼音全部小写
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不带声调
        defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
        try {
            for (char cl_char : cl_chars) {
                if (String.valueOf(cl_char).matches("[\u4e00-\u9fa5]+")) {// 如果字符是中文,则将中文转为汉语拼音
                    hanyupinyin.append(PinyinHelper.toHanyuPinyinStringArray(cl_char, defaultFormat)[0]);
                } else {// 如果字符不是中文,则不转换
                    hanyupinyin.append(cl_char);
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            System.out.println("字符不能转成汉语拼音");
        }
        return hanyupinyin.toString();
    }

    public static String getFirstLettersUp(String ChineseLanguage) {
        return getFirstLetters(ChineseLanguage, HanyuPinyinCaseType.UPPERCASE);
    }

    public static String getFirstLettersLo(String ChineseLanguage) {
        return getFirstLetters(ChineseLanguage, HanyuPinyinCaseType.LOWERCASE);
    }

    public static String getFirstLetters(String ChineseLanguage, HanyuPinyinCaseType caseType) {
        char[] cl_chars = ChineseLanguage.trim().toCharArray();
        StringBuilder hanyupinyin = new StringBuilder();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(caseType);// 输出拼音全部大写
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不带声调
        try {
            for (char cl_char : cl_chars) {
                String str = String.valueOf(cl_char);
                if (str.matches("[\u4e00-\u9fa5]+")) {// 如果字符是中文,则将中文转为汉语拼音,并取第一个字母
                    hanyupinyin.append(PinyinHelper.toHanyuPinyinStringArray(cl_char, defaultFormat)[0].substring(0, 1));
                } else if (str.matches("[0-9]+")) {// 如果字符是数字,取数字
                    hanyupinyin.append(cl_char);
                } else if (str.matches("[a-zA-Z]+")) {// 如果字符是字母,取字母
                    hanyupinyin.append(cl_char);
                } else {// 否则不转换
                    hanyupinyin.append(cl_char);//如果是标点符号的话，带着
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            System.out.println("字符不能转成汉语拼音");
        }
        return hanyupinyin.toString();
    }

    public static String getPinyinString(String ChineseLanguage) {
        char[] cl_chars = ChineseLanguage.trim().toCharArray();
        StringBuilder hanyupinyin = new StringBuilder();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);// 输出拼音全部大写
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不带声调
        try {
            for (char cl_char : cl_chars) {
                String str = String.valueOf(cl_char);
                if (str.matches("[\u4e00-\u9fa5]+")) {// 如果字符是中文,则将中文转为汉语拼音,并取第一个字母
                    hanyupinyin.append(PinyinHelper.toHanyuPinyinStringArray(
                            cl_char, defaultFormat)[0]);
                } else if (str.matches("[0-9]+")) {// 如果字符是数字,取数字
                    hanyupinyin.append(cl_char);
                } else if (str.matches("[a-zA-Z]+")) {// 如果字符是字母,取字母

                    hanyupinyin.append(cl_char);
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
    public static String getFirstLetter(String ChineseLanguage) {
        char[] cl_chars = ChineseLanguage.trim().toCharArray();
        String hanyupinyin = "";
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);// 输出拼音全部大写
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不带声调
        try {
            String str = String.valueOf(cl_chars[0]);
            if (str.matches("[\u4e00-\u9fa5]+")) {// 如果字符是中文,则将中文转为汉语拼音,并取第一个字母
                hanyupinyin = PinyinHelper.toHanyuPinyinStringArray(
                        cl_chars[0], defaultFormat)[0].substring(0, 1);
                ;
            } else if (str.matches("[0-9]+")) {// 如果字符是数字,取数字
                hanyupinyin += cl_chars[0];
            } else if (str.matches("[a-zA-Z]+")) {// 如果字符是字母,取字母

                hanyupinyin += cl_chars[0];
            }  // 否则不转换

        } catch (BadHanyuPinyinOutputFormatCombination e) {
            System.out.println("字符不能转成汉语拼音");
        }
        return hanyupinyin;
    }

//    public static void main(String[] args) throws IllegalAccessException {
//        String name = " 引擎與安裝架及設備";
//        Cat cat = new Cat();
//        cat.setName(name);
//        cat.setAge(1);
//
//        System.out.println(cat);
//        System.out.println(obj2Simplified(cat));
//
//    }

    /**
     * 对象string转 简体
     *
     * @param o
     * @return
     * @author Youdmeng
     * Date 2020-05-08
     **/
    public static Object obj2Simplified(Object o) throws IllegalAccessException {

        if (null == o)
            return o;

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

//class Cat {
//    private String name;
//
//    private Integer age;
//
//    @Override
//    public String toString() {
//        return "Cat{" +
//                "name='" + name + '\'' +
//                ", age=" + age +
//                '}';
//    }
//
//    /**
//     * 属性get
//     */
//    public String getName() {
//        return name;
//    }
//
//    /**
//     * 属性set
//     */
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    /**
//     * 属性get
//     */
//    public Integer getAge() {
//        return age;
//    }
//
//    /**
//     * 属性set
//     */
//    public void setAge(Integer age) {
//        this.age = age;
//    }
//}
