package ${bussPackage}.${entityPackage}.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ${baseClassPackage}.service.BaseService;
import ${bussPackage}.${entityPackage}.dao.${className}Dao;
import ${bussPackage}.${entityPackage}.entity.${className};
#if($pkColumnCount > 1)
import ${bussPackage}.${entityPackage}.entity.${className}Key;
#end


/**
 *
 * <br>
 * <b>功能：</b>${codeName} ${className}Service<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> ${currentDate} <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("$!{lowerName}Service")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class ${className}Service extends BaseService<${className}, #if($pkColumnCount == 0)Void#elseif($pkColumnCount == 1)${pkColumn.shortDataType}#else${className}Key#end> {

    private static final Logger logger = LoggerFactory.getLogger(${className}Service.class);

    @Autowired
    private ${className}Dao dao;

    public ${className}Dao getDao() {
        return dao;
    }

}
