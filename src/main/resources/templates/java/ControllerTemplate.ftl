package ${bussPackage}.${entityPackage}.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ${baseClassPackage}.web.BaseController;
import ${bussPackage}.${entityPackage}.entity.${className};
#if($pkColumnCount > 1)
import ${bussPackage}.${entityPackage}.entity.${className}Key;
#end
import ${bussPackage}.${entityPackage}.page.${className}Page;
import ${bussPackage}.${entityPackage}.service.${className}Service;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/${entityPackage}/${pathName}")
@Api(description = "|${className}|")
public class ${className}Controller extends BaseController<${className}>{

    private static final Logger logger = LoggerFactory.getLogger(${className}Controller.class);

    @Autowired
    private ${className}Service ${lowerName}Service;

	@ApiOperation(value = "|${className}|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("${entityPackage}:${pathName}:page")
    public ResponseMessage<PageInfo<${className}>> page(${className}Page page) throws Exception {
        List<${className}> rows = ${lowerName}Service.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|${className}|查询")
    @GetMapping("")
    @RequiresPermissions("${entityPackage}:${pathName}:list")
    public ResponseMessage<List<${className}>> list(${className}Page page) throws Exception {
        return Result.success(${lowerName}Service.queryByList(page));
	}

#if($pkColumnCount == 1)
    @ApiOperation(value = "|${className}|详情")
    @GetMapping("/{${pkColumn.dataName}}")
    @RequiresPermissions("${entityPackage}:${pathName}:get")
    public ResponseMessage<${className}> find(@PathVariable ${pkColumn.shortDataType} ${pkColumn.dataName}) throws Exception {
        return Result.success(${lowerName}Service.selectByPrimaryKey(${pkColumn.dataName}));
    }

#end
    @ApiOperation(value = "|${className}|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("${entityPackage}:${pathName}:save")
    public ResponseMessage<${className}> create(@RequestBody ${className} ${lowerName}) throws Exception {
        ${lowerName}Service.insertSelective(${lowerName});
        return Result.success(${lowerName});
    }

#if($pkColumnCount > 0)
    @ApiOperation(value = "|${className}|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("${entityPackage}:${pathName}:update")
    public ResponseMessage<${className}> update(@RequestBody ${className} ${lowerName}) throws Exception {
        ${lowerName}Service.updateByPrimaryKeySelective(${lowerName});
        return Result.success(${lowerName});
    }

#end
#if($pkColumnCount == 1)
    @ApiOperation(value = "|${className}|删除")
    @DeleteMapping("/{${pkColumn.dataName}}")
    @RequiresPermissions("${entityPackage}:${pathName}:delete")
    public ResponseMessage delete(@PathVariable ${pkColumn.shortDataType} ${pkColumn.dataName}) throws Exception {
        ${lowerName}Service.deleteByPrimaryKey(${pkColumn.dataName});
        logger.info("delete from ${tableName} where ${pkColumn.dataName} = {}", ${pkColumn.dataName});
        return Result.success();
    }

#elseif($pkColumnCount > 1)
    @ApiOperation(value = "|${className}|删除")
    @DeleteMapping("/${lowerNames}")
    @RequiresPermissions("${entityPackage}:${pathName}:delete")
    public ResponseMessage delete(${className}Key key) throws Exception {
        ${lowerName}Service.deleteByPrimaryKey(key);
        logger.info("delete from ${tableName} where key: {}", key);
        return Result.success();
    }

#end
}
