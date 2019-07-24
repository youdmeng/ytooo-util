define(['app','ngload!restangular','app/services/common/CommonService'], function (app) {
  'use strict';
  app.factory('${className}Service', ['Restangular', 'CommonService', function (Restangular, CommonService) {
    var base = Restangular.all('/${entityPackage}/${lowerNames}');
    return {
        query : function(params) {
            return base.getList(CommonService.buildQueryParams(params));
        },
        create : function(${lowerName}) {
            return base.post(${lowerName}, {}, {'Content-Type' : 'application/json'});
        },
        getById : function(id) {
            return Restangular.one('/${entityPackage}/${lowerNames}', id).get();
        },
        update : function(${lowerName}) {
            return ${lowerName}.put();
        },
        remove : function(${lowerName}) {
            return ${lowerName}.remove();
        }
    };
  }]);
});
