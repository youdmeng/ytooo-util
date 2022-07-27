package ml.ytooo.freemarker.config;

import freemarker.cache.StringTemplateLoader;
import freemarker.ext.beans.BeansWrapperBuilder;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import ml.ytooo.freemarker.FreemarkerTemplateUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author youdmeng
 * @date 2021/4/29 16:44
 */
@Configuration
@Slf4j
public class FreemarkerConfig {

    @Bean(value = "FreemarkerConfigConfiguration")
    public freemarker.template.Configuration getTemplateConfiguration() {
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.getVersion());
        try {
            configuration.setSetting("number_format", "0");
            configuration.setSetting("boolean_format", "trueee@@@you,falseee@@@qiu");
        } catch (TemplateException e) {
            log.error("初始化FreemarkerConfigConfiguration失败", e);
          //..
        }
        FreemarkerTemplateUtil.addFunction(configuration);
        configuration.unsetObjectWrapper();
        configuration.setObjectWrapper(new BeansWrapperBuilder(freemarker.template.Configuration.getVersion()).build());
        return configuration;
    }

    @Bean(value = "FreemarkerConfigStringTemplateLoader")
    public StringTemplateLoader getStringTemplateLoader() {
        return new StringTemplateLoader();
    }

}
