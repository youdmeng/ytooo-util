import {BaseModel} from "../base.model";

export class ${className}Model extends BaseModel{

#foreach($po in $!{columnDatas})
    /** ${po.columnComment} */
    ${po.dataName}: ${po.jsDataType};
#end

}
