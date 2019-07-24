<div class="clrfix operator-panel">
    <div class="clrfix">
        <a data-ui-sref="${lowerNames}_new" class="btn-add"><i class="icons icons-add"></i>添加</a>
    </div>
</div>
<div class="ng-table-container">
    <div class="ng-table-head clrfix">
        <div class="text">列表</div>
        <div class="search" data-search role="search"></div>
    </div>
    <table ng-table="tableParams" class="table ng-table-responsive table-hover">
        <tr ng-repeat="${lowerName} in $data">
           <td data-title="'序号'"><div>{{$index + 1}}</div></td>
#foreach($po in $!{columnDatas})
           <td data-title="'${po.viewData.title}'"><div>{{${lowerName}.${po.dataName}}}</div></td>
#end
           <td data-title="'操作'">
               <div class="ng-table-operator">
                   <a ui-sref="${lowerNames}_edit({${lowerName}Id:${lowerName}.id})" class="icons icon-edit" title="编辑" />
                   <a href="javascript:;" data-template="html/${entityPackage}/dialog/${className}Dialog.html" bs-modal="modal" class="icons icon-edit" title="编辑" />
                   <a href="javascript:;" ng-click="remove${className}(${lowerName});" class="icons icon-delete" title="删除" />
               </div>
           </td>
        </tr>
    </table>
</div>