package ml.ytooo.freemarker.function;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import ml.ytooo.time.DateUtil;

import java.util.Date;
import java.util.List;

/**
 * 时间戳转日期字符串
 * 1620981286534 ==> 2021-34-14 34
 * ${timestampToDateStr(" + now + ",\"yyyy-mm-dd mm\")}
 * 三个参数： 时间戳 目标格式
 *
 * @author youdmeng
 * @date 2021/5/13 15:32
 */
public class FunctionTimestampToDateStr implements TemplateMethodModelEx {
    @Override
    public Object exec(List arguments) throws TemplateModelException {
        if (null == arguments || arguments.size() < 2) {
            return "";
        }
        try {
            Object param = arguments.get(0);
            Object format = arguments.get(1);
            final String dateStr;
            dateStr = DateUtil.getDateStr(new Date((Long.parseLong(param.toString()))), format.toString());
            return dateStr;
        } catch (Exception e) {
            return "";
        }
    }
}
