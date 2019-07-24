define([], function () {
  'use strict';
  return [{
        state : '${lowerNames}',
        url : '/${entityPackage}/${lowerNames}',
        templateUrl : 'html/${entityPackage}/list/${className}List.html?v=' + appConfig.appVersion,
        controllerUrl : 'app/controllers/${entityPackage}/${className}ListController'
    }, {
        state : '${lowerNames}_new',
        url : '/${entityPackage}/${lowerNames}/new',
        templateUrl : 'html/${entityPackage}/form/${className}Form.html?v=' + appConfig.appVersion,
        controllerUrl : 'app/controllers/${entityPackage}/${className}FormController'
    }, {
        state : '${lowerNames}_edit',
        url : '/${entityPackage}/${lowerNames}/edit/:${lowerName}Id',
        templateUrl : 'html/${entityPackage}/form/${className}Form.html?v=' + appConfig.appVersion,
        controllerUrl : 'app/controllers/${entityPackage}/${className}FormController'
    }];
});
