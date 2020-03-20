package com.ytooo.config;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

/**
 * 各个模块的dozer配置文件
 */
@Configuration
public class DozerConfig {

    /**
     * dozer
     * @return
     */
    @Bean(name = "org.dozer.Mapper")
    public DozerBeanMapper dozer() {
        List<String> mappingFiles = Arrays.asList("dozer/dozer-mapping.xml");
        DozerBeanMapper dozerBean = new DozerBeanMapper();
        dozerBean.setMappingFiles(mappingFiles);
        return dozerBean;
    }

}
