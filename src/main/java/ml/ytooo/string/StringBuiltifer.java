package ml.ytooo.string;

import java.util.ArrayList;
import java.util.List;

/**
 * 表名转 bean名 TA_ORG_INFO === >> orgInfo
 * Created by Youdmeng on 2019/7/8 0008.
 */
public class StringBuiltifer {


    public static String formateClassName(String name) {

        List<String> prefixs = new ArrayList<>();
        prefixs.add("TA");
        prefixs.add("T");

        String[] split = name.split("_");
        String nameStr = name;
        if (prefixs.contains(split[0].toUpperCase())) {
            StringBuilder nameSb = new StringBuilder(split[1]);
            for (int i = 2; i < split.length; i++) {
                nameSb.append('_').append(split[i]);
            }
            nameStr = nameSb.toString();
        }
        return formateName(nameStr.toLowerCase());
    }

    private static String formateName(String name) {
        if (name.contains("_")) {
            name = name.toLowerCase();
        }
        String[] split = name.split("_");
        if (split.length > 1) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < split.length; i++) {
                String tempTableName;
                if (i == 0) {
                    tempTableName = split[i].substring(0, 1).toLowerCase() +
                            split[i].substring(1);
                } else {
                    tempTableName = split[i].substring(0, 1).toUpperCase() +
                            split[i].substring(1);
                }
                sb.append(tempTableName);
            }

            return sb.toString();
        } else {
            return split[0].substring(0, 1).toUpperCase() + split[0].substring(1);
        }
    }
}
