package ml.ytooo.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringSelectUtil {

    public static String selectNumber(String target) {
        if (null == target || "".equals(target.trim())) {
            return null;
        }
        target = target.trim();
        StringBuilder desc = new StringBuilder();
        for (int i = 0; i < target.length(); i++) {
            if (target.charAt(i) >= 48 && target.charAt(i) <= 57) {
                desc.append(target.charAt(i));
            }
        }
        return desc.toString();
    }
    public static String selectChar (String target) {
        if (null == target || "".equals(target)) {
            return null;
        }
        target = target.trim();
        StringBuilder desc = new StringBuilder();
        for (int i = 0; i < target.length(); i++) {
            if ((target.charAt(i) >= 65  && target.charAt(i) <= 91)
                || (target.charAt(i) >= 97  && target.charAt(i) <= 122)) {
                desc.append(target.charAt(i));
            }
        }
        return desc.toString();
    }

    public static boolean HasDigit(String content) {
        boolean flag = false;
        Pattern p = Pattern.compile(".*\\d+.*");
        Matcher m = p.matcher(content);
        if (m.matches()) {
            flag = true;
        }
        return flag;
    }

    public static int ChineseNum(String value) {
        String regex = "[\u4e00-\u9fff]";
        return (" " + value + " ").split (regex).length - 1;
    }
}
