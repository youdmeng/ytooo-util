package ${bussPackage}.entity.${entityPackage};

import java.util.Set;

import javax.naming.Name;

import org.springframework.data.annotation.Transient;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.DnAttribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import com.pingan.jinke.common.ldap.LdapBaseEntity;

#foreach($importClasses in $!{entityImportClasses})
import ${importClasses};
#end

/**
 *
 * <br>
 * <b>功能：</b>${codeName} ${className}Entity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> ${currentDate} <br>
 * <b>版权所有：<b>Copyright(C) 2015, Beijing  Science & Technology Co., Ltd.<br>
 */
@Entry(objectClasses = { "${className}", "top" }, base = "ou=${objectEntry}")
public class ${className} extends LdapBaseEntity {

    @Id
    public Name dn;

#foreach($po in $!{columnDatas})
    @Attribute(name = "${po.dataName}")
#if (${po.ldapData.dnAttribute} == true)
    @DnAttribute(value = "${po.ldapData.value}", index = ${po.ldapData.index})
#end
#if (${po.ldapData.transient} == true)
    @Transient
#end
#if(${po.ldapData.memberOf} == true)
    private Set<Name> ${po.dataName};
#else
    private ${po.shortDataType} ${po.dataName};
#end

#end
#foreach($po in $!{columnDatas})

#if(${po.ldapData.memberOf} == true)
    public Set<Name> get${po.upperDataName}() {
        return this.${po.dataName};
    }

    public void set${po.upperDataName}(Set<Name> ${po.dataName}) {
        this.${po.dataName} = ${po.dataName};
    }
#else
    public ${po.shortDataType} get${po.upperDataName}() {
        return this.${po.dataName};
    }

    public void set${po.upperDataName}(${po.shortDataType} ${po.dataName}) {
        this.${po.dataName} = ${po.dataName};
    }
#end
#end

    public Name getDn() {
        return dn;
    }

    public void setDn(Name dn) {
        this.dn = dn;
    }

}

