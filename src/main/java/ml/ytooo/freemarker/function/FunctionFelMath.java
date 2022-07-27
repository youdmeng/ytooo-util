package ml.ytooo.freemarker.function;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import ml.ytooo.time.DateUtil;

import java.util.List;

/**
 * fel计算引擎
 * 两个参数： 目标格式
 *
 * @author youdmeng
 * @date 2021/5/13 15:32
 */
public class FunctionFelMath implements TemplateMethodModelEx {
    @Override
    public Object exec(List arguments) throws TemplateModelException {
        if (null == arguments || arguments.size() < 2) {
            return "";
        }
        Object param = arguments.get(1);
        Object from = arguments.get(0);
        Object to = arguments.get(2);
        try {
            return DateUtil.getDateStr(DateUtil.parseDate(param.toString(), from.toString()), to.toString());
        } catch (Exception e) {
            return "";
        }
    }
}
