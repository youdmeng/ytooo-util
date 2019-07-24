define(['angularAMD','app/services/admin/DicService', 'app/services/${entityPackage}/${className}Service','app/filters/common/CommonFilter'], function (angularAMD) {
    'use strict';

    return ['$scope', '$stateParams','DicService', '${className}Service', '$state', function ($scope, $stateParams,DicService, ${className}Service, $state) {
        ${dl}scope.viewEdit${className} = function(){
            ${dl}scope.${lowerName} = {};
            if($stateParams.${lowerName}Id){
                ${className}Service.getById($stateParams.${lowerName}Id).then(function(${lowerName}){
                    $scope.${lowerName} = ${lowerName};
                });
            }
        };

        ${dl}scope.create${className} = function(${lowerName}){
            ${className}Service.create(${lowerName}).then(function(${lowerName}){
                $.layerLoading.hide();
                ${dl}state.go('${lowerNames}');
            },function(response) {
                $.layerLoading.hide();
                if(response.data != null && !response.data.success){
                    $.Pop.alerts(response.data.msg);
                }
            });
        };

        ${dl}scope.edit${className} = function(${lowerName}){
            ${className}Service.update(${lowerName}).then(function(${lowerName}){
                $.layerLoading.hide();
                ${dl}state.go('${lowerNames}');
            },function(response) {
                $.layerLoading.hide();
                if(response.data != null && !response.data.success){
                    $.Pop.alerts(response.data.msg);
                }
            });
        };

        ${dl}scope.save${className} = function(${lowerName},formHorizontal){
            $.layerLoading.show();
            ${lowerName}.id === undefined ? ${dl}scope.create${className} (${lowerName}) : ${dl}scope.edit${className} (${lowerName});
        };

        ${dl}scope.init = function(){
            ${dl}scope.viewEdit${className}();
        }

        ${dl}scope.init();
    }];
});
