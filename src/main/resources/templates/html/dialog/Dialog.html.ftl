<div class="modal in" tabindex="-1" role="dialog" style="display: block;" ng-click="clickModal();">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close" ng-click="$hide()">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title">编辑${codeName}</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal clrfix" name="formHorizontal">
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
            <div class="modal-footer">
                <div class="button btn-primary" ng-click="save${className}(${lowerName},formHorizontal)">保存</div>
                <div class="button btn-default" ng-click="$hide()">取消</div>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
