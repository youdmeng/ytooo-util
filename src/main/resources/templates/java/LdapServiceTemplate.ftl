package ${bussPackage}.service.${entityPackage};

import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pingan.jinke.common.ldap.LdapBaseService;
import com.pingan.jinke.common.util.CommonUtil;
import ${bussPackage}.dao.${entityPackage}.${className}Dao;
import ${bussPackage}.entity.${entityPackage}.${className};
import ${bussPackage}.page.${entityPackage}.${className}Page;

/**
 *
 * <br>
 * <b>功能：</b>${codeName} ${className}Service<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> ${currentDate} <br>
 * <b>版权所有：<b>Copyright(C) 2015, Beijing  Science & Technology Co., Ltd.<br>
 */
@Service("$!{lowerName}Service") @Transactional
public class ${className}Service<T> extends LdapBaseService<T> {
    private static final Logger logger = LoggerFactory.getLogger(${className}Service.class);

    @Autowired
    private ${className}Dao<T> dao;

    public ${className}Dao<T> getDao() {
        return dao;
    }

    public List<T> queryByList(${className}Page page) throws Exception {
        String baseName = "ou=${objectEntry}";
        String filter = "(&(&(objectclass=${className})(objectclass=top))(&(cn=*)"  +
#foreach($po in $!{columnDatas})
    #if (${po.ldapData.memberOf} == false)
        "(${po.dataName}=" + CommonUtil.ldapFilterTransfer(page.get${po.upperDataName}()) + ")" +
    #end
#end
    "))";
        return super.queryByList(page, baseName, filter, new ${className}AttributesMapper(baseName));
    }

    static class ${className}AttributesMapper<T> implements AttributesMapper{

        private String baseName;

        public ${className}AttributesMapper(String baseName) {
            this.baseName = baseName;
        }

        public T mapFromAttributes(Attributes attributes) throws NamingException {
            ${className} ${lowerName} = new ${className}();
            ${lowerName}.setBaseName(baseName);
#foreach($po in $!{columnDatas})
#if(${po.ldapData.memberOf} == false)
            ${lowerName}.set${po.upperDataName}((${po.shortDataType}) (attributes.get("${po.dataName}") == null ? null : attributes.get("${po.dataName}").get()));
#end
#end
            return (T) ${lowerName};
        }
    }

}

