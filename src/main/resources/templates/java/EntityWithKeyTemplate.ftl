package ${bussPackage}.entity.${entityPackage};

#foreach($importClasses in $!{entityImportClasses})
import ${importClasses};
#end

/**
 * <b>功能：</b>${codeName} ${className}Entity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> ${currentDate} <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class ${className} extends ${className}Key {

#foreach($po in $!{notPkColumnDatas})
#if(${po.shortDataType} == 'Date')
    @org.springframework.format.annotation.DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
#end
    private ${po.shortDataType} ${po.dataName};
#end

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
#foreach($po in $!{columnDatas})
     * <li>${po.dataName} -> ${po.columnName}</li>
#end
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
#foreach($po in $!{notPkColumnDatas})
            case "${po.dataName}": return "${po.columnName}";
#end
            default: return ${className}Key.fieldToColumn(fieldName);
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
#foreach($po in $!{columnDatas})
     * <li>${po.columnName} -> ${po.dataName}</li>
#end
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
#foreach($po in $!{notPkColumnDatas})
            case "${po.columnName}": return "${po.dataName}";
#end
            default: return ${className}Key.columnToField(columnName);
        }
    }
    
#foreach($po in $!{notPkColumnDatas})
    /** ${po.columnComment} **/
    public ${po.shortDataType} get${po.upperDataName}() {
        return this.${po.dataName};
    }

    /** ${po.columnComment} **/
    public void set${po.upperDataName}(${po.shortDataType} ${po.dataName}) {
        this.${po.dataName} = ${po.dataName};
    }

#end
}
