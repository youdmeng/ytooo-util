package ml.ytooo.freemarker.function;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import ml.ytooo.time.DateUtil;

import java.util.List;

/**
 * 字符串转时间戳
 * 1993-05-26 24:12:10 728064730000
 * ${dateStrToTimestamp("yyyy-mm-dd HH:mm:ss","1993-05-26 24:12:10")}
 * 两个参数： 原格式 时间字符串
 *
 * @author youdmeng
 * @date 2021/5/13 15:32
 */
public class FunctionDateStrToTimestamp implements TemplateMethodModelEx {
    @Override
    public Object exec(List arguments) throws TemplateModelException {
        if (null == arguments || arguments.size() < 2) {
            return "";
        }
        try {
            Object param = arguments.get(1);
            Object from = arguments.get(0);
            return DateUtil.parseDate(param.toString(), from.toString()).getTime() + "";
        } catch (Exception e) {
            return "";
        }
    }
}
