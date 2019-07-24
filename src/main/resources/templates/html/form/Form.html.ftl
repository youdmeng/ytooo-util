<div class="go-back-list">
    <a data-ui-sref="${lowerNames}"><i class="icons icons-arrow-left"></i>返回列表</a>
</div>
<div class="form-horizontal-panel">
    <div class="form-horizontal-title">创建</div>
    <form class="form-horizontal clrfix p-rel" name="formHorizontal">
#foreach($po in $!{columnDatas})
#if ($po.dataName != $primaryKey)
        <div class="control-group">
            <label class="control-label"><span class="required">*</span>${po.viewData.title}：</label>
            <div class="controls">
                <input autocomplete="off" ng-class="{'input-error':!formHorizontal.${po.dataName}.$valid && formHorizontal.${po.dataName}.$dirty}" class="form-control" type="text" ng-model="${lowerName}.${po.dataName}" name="${po.dataName}" required>
                <div class="form-error-msg" ng-show="!formHorizontal.${po.dataName}.$valid && formHorizontal.${po.dataName}.$dirty">请输入${po.viewData.title}</div>
            </div>
        </div>
#end
#end
    </form>
</div>
<div class="form-horizontal-btns clrfix">
    <div class="form-btn btn-blue-hg35" ng-click="save${className}(${lowerName},formHorizontal)">保存</div>
    <a data-ui-sref="${lowerNames}" class="bform-btn btn-white-hg35">取消</a>
</div>
