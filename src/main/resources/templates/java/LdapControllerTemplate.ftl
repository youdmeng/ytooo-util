package ${bussPackage}.controller.${entityPackage};

import java.util.List;
import java.util.Map;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pingan.jinke.dc.base.web.BaseController;
import ${bussPackage}.entity.${entityPackage}.${className};
import ${bussPackage}.page.${entityPackage}.${className}Page;
import ${bussPackage}.service.${entityPackage}.${className}Service;

/**
 *
 * <br>
 * <b>功能：</b>${className}Controller<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> Feb 2, 2013 <br>
 * <b>版权所有：<b>Copyright(C) 2015, Beijing  Science & Technology Co., Ltd.<br>
 */
@Controller
@RequestMapping("/${lowerName}")
public class ${className}Controller extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(${className}Controller.class);

    @Autowired
    private ${className}Service<${className}> ${lowerName}Service;

    @RequestMapping("/list.do")
    @ResponseBody
    public Map<String, Object> list(${className}Page page) throws Exception {
        List<${className}> rows = ${lowerName}Service.queryByList(page);
        return getGridData(page.getPager().getRowCount(), rows);
    }

    /**
     * 新建或修改
     *
     * @param entity
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/save.do", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(${className} entity) throws Exception {
        if (entity.getBaseName() == null || "".equals(entity.getBaseName().trim())) {
            ${lowerName}Service.insert(entity);
            return getSuccessMessage("${codeName}添加成功!");
        } else {
            ${lowerName}Service.updateByPrimaryKey(entity);
            return getSuccessMessage("${codeName}修改成功!");
        }
    }

    /**
     * 新建或修改，用于复杂表单
     *
     * @param formData
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/saveData.do", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public Map<String, Object> saveData(@RequestBody Map<String, Object> formData) throws Exception {
        JSONObject jsonForm = JSONObject.fromObject(formData);
        ${className} entity = (${className}) JSONObject.toBean(jsonForm.getJSONObject("entity"), ${className}.class);

        if (entity.getBaseName() == null || "".equals(entity.getBaseName().trim())) {
            ${lowerName}Service.insert(entity);
            return getSuccessMessage("合作伙伴添加成功!");
        } else {
            ${lowerName}Service.updateByPrimaryKey(entity);
            return getSuccessMessage("合作伙伴修改成功!");
        }
    }

    @RequestMapping("/findById.do")
    @ResponseBody
    public Map<String, Object> findById(String id) throws Exception {
        ${className} entity = ${lowerName}Service.selectByPrimaryKey(id);
        if (entity == null) {
            return getFailureMessage("没有找到对应的记录!");
        } else {
            return getSuccessData(entity);
        }
    }

    @RequestMapping("/deleteById.do")
    @ResponseBody
    public Map<String, Object> delete(${className} ${lowerName}) throws Exception {
        ${lowerName}Service.deleteByPrimaryKey(${lowerName});
        return getSuccessMessage("${codeName}删除成功!");
    }
}
