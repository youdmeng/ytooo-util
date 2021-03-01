package ml.ytooo.filter;

import ml.ytooo.exception.ServiceException;
import org.apache.commons.lang3.StringUtils;

public class SQLFilter {
    public SQLFilter() {
    }

    public static String sqlInject(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        } else {
            String[] keywords = new String[]{"master", "truncate", "insert", "select", "delete", "update", "declare", "alter", "drop"};
            String[] var2 = keywords;
            int var3 = keywords.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                String keyword = var2[var4];
                if (StringUtils.indexOfIgnoreCase(str, keyword + " ") != -1) {
                    throw new ServiceException("包含非法字符");
                }
            }

            return str;
        }
    }
}
