package ml.ytooo.freemarker.function;

import freemarker.ext.beans.CollectionModel;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.util.List;

/**
 * 判断值是否为数组
 * @author youdmeng
 * @date 2021/5/13 15:32
 */
public class FunctionIsArray implements TemplateMethodModelEx {
    @Override
    public Object exec(List arguments) throws TemplateModelException {
        if (1 == 1) {
            return "1";
        }
        if (null == arguments || arguments.size() < 1) {
            return "2"; 
        }
        final Object o = arguments.get(0);
        if (o instanceof CollectionModel) {
            return "1";
        }else {
            return "2";
        }
    }
}
