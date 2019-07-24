define(['angularAMD','app/services/admin/DicService','app/services/${entityPackage}/${className}Service','app/filters/common/CommonFilter'], function (angularAMD) {
    'use strict';
    return ['$scope','DicService','${className}Service','$state','NgTableParams', '$location', function ($scope,DicService,${className}Service,$state,NgTableParams,$location) {
        $scope.init = function(){
            $scope.tableParams = new NgTableParams(angular.extend({
                page: 1,
                count: 10,
                sorting: {
                    name: 'asc'
                }
            },
            $location.search()), {
                counts: [10, 20, 50],
                paginationMaxBlocks: 9,
                total: 0,
                getData: function ($defer, params) {
                    ${dl}location.search(params.url());
                    ${className}Service.query(params).then(function(${lowerNames}){
                        params.total(${lowerNames}.total);
                        ${dl}defer.resolve(${lowerNames});
                    });
                }
            });
        };

        $scope.create${className} = function(${lowerName}){
            ${className}Service.create(${lowerName}).then(function(${lowerName}){
                $.layerLoading.hide();
                $state.reload();
            },function(response) {
                $.layerLoading.hide();
                if(response.data != null && !response.data.success){
                    $.Pop.alerts(response.data.msg);
                }
            });
        };

        $scope.edit${className} = function(${lowerName}){
            ${className}Service.update(${lowerName}).then(function(${lowerName}){
                $.layerLoading.hide();
                $state.reload();
            },function(response) {
                $.layerLoading.hide();
                if(response.data != null && !response.data.success){
                    $.Pop.alerts(response.data.msg);
                }
            });
        };

        $scope.save${className} = function(${lowerName},formHorizontal){
            ${lowerName} = ${lowerName} || {};
            $.layerLoading.show();
            ${lowerName}.id === undefined ? $scope.create${className} (${lowerName}) : $scope.edit${className} (${lowerName});
        };

        $scope.remove${className} = function(${lowerName}){
            $.Pop.confirms('确定要删除？',function(){
                ${className}Service.remove(${lowerName}).then(function(${lowerName}){
                    $scope.tableParams.reload();
                },function(response) {
                    if(response.data != null && !response.data.success){
                        $.Pop.alerts(response.data.msg);
                    }
                });
            });
        };

        $scope.search = function(){
            $scope.tableParams.filter({'q' : $scope.searchValue});
        };

        $scope.clickModal = function(){
            if(document.all){
                window.event.cancelBubble = true;
             }else{
                event.stopPropagation();
            }
        }

        $scope.init();
    }];
});
