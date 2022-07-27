package ml.ytooo.freemarker;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import ml.ytooo.freemarker.function.*;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

/**
 * 报文转换平台Freemarker转换工具
 *
 * @author youdmeng
 * @date 2021/4/17 14:06
 */
@Slf4j
@Component
public class FreemarkerTemplateUtil implements ApplicationContextAware {
    private static ApplicationContext context;

    private static Configuration configuration;

    private static StringTemplateLoader stringTemplateLoader;

    static {
        try {
            configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
            configuration.setSetting("number_format", "0");
            configuration.setSetting("boolean_format", "trueee@@@you,falseee@@@qiu");
            stringTemplateLoader = new StringTemplateLoader();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
    /**
     * @param templateJson 源数据
     * @param templateName
     * @return freemarker.template.Template
     * @author youdmeng
     * @date 2021/4/14 18:24
     **/
    private static Template initTemplate(String templateJson, String templateName) throws IOException, TemplateException {
//        configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
//        configuration.setSetting("number_format", "0");
//        configuration.setSetting("boolean_format", "trueee@@@you,falseee@@@qiu");
//        stringTemplateLoader = new StringTemplateLoader();


        stringTemplateLoader.putTemplate(templateName, templateJson);
        //设置模版加载器
        configuration.setTemplateLoader(stringTemplateLoader);
        addFunction(configuration);
        return configuration.getTemplate(templateName, StandardCharsets.UTF_8.name());
    }

    /**
     *  freeMarker引擎执行入口，根据传入对象与模板解析出转换后报文
     * @author you.minda
     * @date 2022/2/24 18:00
     * @param
     * @param dataMap 数据
     * @param templateJson 模板
     * @param templateName 模板名称
     * @return java.lang.String
     **/
    public static String genjsonWithTemplate(Object dataMap, String templateJson, String templateName) throws TemplateException {
//        configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);


        StringWriter writer = new StringWriter();
        Template template = null;
        synchronized (FreemarkerTemplateUtil.class) {
            try {
                template = configuration.getTemplate(templateName, StandardCharsets.UTF_8.name());
            } catch (Exception e) {
                try {
                    template = initTemplate(templateJson, templateName);
                } catch (Exception exception) {
                    log.error("freeMarker转换异常", e);
                    throw new RuntimeException("freeMarker转换异常");
                }
            }
        }
        try {
            template.process(dataMap, writer);
        } catch (Exception e) {
            log.error("freeMarker转换异常", e);
        }
        return writer.toString();
    }

    /**
     * 添加内置函数
     *
     * @param configuration
     * @return void
     * @author youdmeng
     * @date 2021/5/13 16:12
     **/
    public static void addFunction(Configuration configuration) {
        // 时间戳转日期字符串
        configuration.setSharedVariable("timestampToDateStr", new FunctionTimestampToDateStr());
        // 日期字符串转日期字符串
        configuration.setSharedVariable("dateStrToDateStr", new FunctionDateStrToDateStr());
        // 字符串转时间戳
        configuration.setSharedVariable("dateStrToTimestamp", new FunctionDateStrToTimestamp());
        // 判断值是否为数组
        configuration.setSharedVariable("isArray", new FunctionIsArray());
        // 数字去除 ，空格
        configuration.setSharedVariable("numberTrim", new FunctionNumberTrim());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
        configuration = (Configuration) applicationContext.getBean("FreemarkerConfigConfiguration");
        stringTemplateLoader = (StringTemplateLoader) applicationContext.getBean("FreemarkerConfigStringTemplateLoader");
    }
}
