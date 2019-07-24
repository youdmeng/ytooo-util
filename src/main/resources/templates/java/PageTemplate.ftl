package ${bussPackage}.${entityPackage}.page;

import ${baseClassPackage}.page.BasePage;

#foreach($importClasses in $!{entityImportClasses})
import ${importClasses};
#end

/**
 * <b>功能：</b>${codeName} ${className}Page<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> ${currentDate} <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class ${className}Page extends BasePage {

#foreach($po in $!{columnDatas})
    private String ${po.dataName};
#if(${po.shortDataType} == 'Date')
    private String ${po.dataName}1;
    private String ${po.dataName}2;
#end
    private String ${po.dataName}Operator = "=";
#end

#foreach($po in $!{columnDatas})
    public String get${po.upperDataName}() {
        return this.${po.dataName};
    }

    public void set${po.upperDataName}(String ${po.dataName}) {
        this.${po.dataName} = ${po.dataName};
    }

#if(${po.shortDataType} == 'Date')
    public String get${po.upperDataName}1() {
        return this.${po.dataName}1;
    }

    public void set${po.upperDataName}1(String ${po.dataName}1) {
        this.${po.dataName}1 = ${po.dataName}1;
    }

    public String get${po.upperDataName}2() {
        return this.${po.dataName}2;
    }

    public void set${po.upperDataName}2(String ${po.dataName}2) {
        this.${po.dataName}2 = ${po.dataName}2;
    }

#end
    public String get${po.upperDataName}Operator() {
        return this.${po.dataName}Operator;
    }

    public void set${po.upperDataName}Operator(String ${po.dataName}Operator) {
        this.${po.dataName}Operator = ${po.dataName}Operator;
    }

#end
}
