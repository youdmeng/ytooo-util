package ml.ytooo.freemarker.function;


import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import org.apache.commons.lang3.StringUtils;

import java.text.NumberFormat;
import java.util.List;

/**
 * 数字去除 ，空格
 *
 * @author youdmeng
 * @date 2021/5/13 15:32
 */
public class FunctionNumberTrim implements TemplateMethodModelEx {
    @Override
    public Object exec(List arguments) throws TemplateModelException {
        if (null == arguments || arguments.size() < 1) {
            return "";
        }
        try {
            final Object obj = arguments.get(0);
            String numberStr = StringUtils.trim((String) obj);
            NumberFormat numberFormat = NumberFormat.getNumberInstance();
            return numberFormat.parse(numberStr).toString();
        } catch (Exception e) {
            return "";
        }
    }
}
