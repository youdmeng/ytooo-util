package ml.ytooo.freemarker.function;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import ml.ytooo.time.DateUtil;

import java.util.List;

/**
 * 日期字符串转日期字符串
 * 1993-05-26 24:12:10(yyyy-mm-dd HH:mm:ss) ==> 993-12-27 12(yyyy-mm-dd mm)
 * ${dateStrToDateStr("yyyy-mm-dd HH:mm:ss","1993-05-26 24:12:10","yyyy-mm-dd mm")}
 * 两个参数： 原格式 时间字符串 目标格式
 *
 * @author youdmeng
 * @date 2021/5/13 15:32
 */
public class FunctionDateStrToDateStr implements TemplateMethodModelEx {
    @Override
    public Object exec(List arguments) throws TemplateModelException {
        if (null == arguments || arguments.size() < 3) {
            return "";
        }
        try {
            Object param = arguments.get(1);
            Object from = arguments.get(0);
            Object to = arguments.get(2);
            return DateUtil.getDateStr(DateUtil.parseDate(param.toString(), from.toString()), to.toString());
        } catch (Exception e) {
            return "";
        }
    }
}
