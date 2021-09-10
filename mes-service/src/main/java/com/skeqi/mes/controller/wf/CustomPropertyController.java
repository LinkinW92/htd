package com.skeqi.mes.controller.wf;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.CMesJlMaterialT;
import com.skeqi.mes.pojo.qh.CMesCustomProperty;
import com.skeqi.mes.service.qh.CQhMenuService;
import com.skeqi.mes.service.wf.CMesCustomPropertyService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;
import com.skeqi.mes.util.yp.EqualsUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 自定义属性管理
 * @author Lenovo
 */
@RestController
@RequestMapping(value = "custom", produces = MediaType.APPLICATION_JSON)
@Api(value = "自定义属性管理", description = "自定义属性管理", produces = MediaType.APPLICATION_JSON)
public class CustomPropertyController {

    @Autowired
    private CMesCustomPropertyService customPropertyService;

    @Autowired
    CQhMenuService cQhMenuService;


    @ApiOperation(value = "查询自定义属性", notes = "查询自定义属性")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "objectType", value = "对象类型", required = false, paramType = "query"),
            @ApiImplicitParam(name = "bindScopeKey", value = "属性范围", required = false, paramType = "query"),
            @ApiImplicitParam(name = "bindCondition", value = "范围条件", required = false, paramType = "query"),
            @ApiImplicitParam(name = "bindScopeValue", value = "属性范围值", required = false, paramType = "query")
    })
    @RequestMapping(value = "findCustomProperty", method = RequestMethod.POST)
    public Rjson findCustomProperty(HttpServletRequest request){
        try {
            String objectType = EqualsUtil.string(request, "objectType", "对象类型", false);
            String bindScopeKey = EqualsUtil.string(request, "bindScopeKey", "属性范围", false);
            String bindCondition = EqualsUtil.string(request, "bindCondition", "范围条件", false);
            String bindScopeValue = EqualsUtil.string(request, "bindScopeValue", "属性范围值", false);
            CMesCustomProperty cMesCustomProperty = new CMesCustomProperty();
            cMesCustomProperty.setObjectType(StringUtils.isEmpty(objectType)?null:objectType);
            cMesCustomProperty.setBindScopeKey(StringUtils.isEmpty(bindScopeKey)?null:bindScopeKey);
            cMesCustomProperty.setBindCondition(StringUtils.isEmpty(bindCondition)?null:bindCondition);
            cMesCustomProperty.setBindScopeValue(StringUtils.isEmpty(bindScopeValue)?null:bindScopeValue);
            List<CMesCustomProperty> list = customPropertyService.findCustomProperty(cMesCustomProperty);
            //根据"属性范围"排序
            list.sort(Comparator.comparing(CMesCustomProperty::getBindScopeKey));
            return Rjson.success(list);
        } catch (Exception e) {
        	e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "查询自定义属性", notes = "查询自定义属性")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码", required = true, paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "页码大小", required = true, paramType = "query"),
            @ApiImplicitParam(name = "objectType", value = "对象类型", required = false, paramType = "query"),
            @ApiImplicitParam(name = "propertyName", value = "属性名称", required = false, paramType = "query")
    })
    @RequestMapping(value = "findCustomPropertyAll", method = RequestMethod.POST)
    public Rjson findCustomPropertyAll(HttpServletRequest request){
        try {
            Integer pageNum = EqualsUtil.pageNum(request);
            Integer pageSize = EqualsUtil.pageSize(request);
            String objectType = EqualsUtil.string(request, "objectType", "对象类型", false);
            String propertyName = EqualsUtil.string(request, "propertyName", "属性名称", false);
            CMesCustomProperty cMesCustomProperty = new CMesCustomProperty();
            cMesCustomProperty.setObjectType(StringUtils.isEmpty(objectType)?null:objectType);
            cMesCustomProperty.setPropertyName(StringUtils.isEmpty(propertyName)?null:propertyName);

            PageHelper.startPage(pageNum, pageSize);

            List<CMesCustomProperty> list = customPropertyService.findCustomPropertyAll(cMesCustomProperty);

            PageInfo<CMesCustomProperty> pageInfo= new PageInfo<CMesCustomProperty>(list, 5);
            return Rjson.success(pageInfo);
        } catch (Exception e) {
        	e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "新增自定义属性", notes = "新增自定义属性")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "propertyName", value = "属性名称", required = true, paramType = "query"),
            @ApiImplicitParam(name = "propertyEnglishName", value = "属性英文名称", required = true, paramType = "query"),
            @ApiImplicitParam(name = "value", value = "属性值", required = true, paramType = "query"),
            @ApiImplicitParam(name = "propertyType", value = "属性类型", required = true, paramType = "query"),
            @ApiImplicitParam(name = "propertyExplain", value = "属性说明", required = true, paramType = "query"),
            @ApiImplicitParam(name = "objectType", value = "对象类型", required = true, paramType = "query"),
            @ApiImplicitParam(name = "bindScopeKey", value = "属性范围", required = true, paramType = "query"),
            @ApiImplicitParam(name = "bindCondition", value = "范围条件", required = true, paramType = "query"),
            @ApiImplicitParam(name = "bindScopeValue", value = "属性范围值", required = true, paramType = "query")
    })
    @RequestMapping(value = "addCustomProperty", method = RequestMethod.POST)
	@OptionalLog(module="基础建模", module2="自定义属性", method="新增自定义属性")
    public Rjson addCustomProperty(HttpServletRequest request, CMesCustomProperty customProperty){
        try {
            if (StringUtils.isEmpty(customProperty)){
                return Rjson.error("表单数据不能为空");
            }
            Rjson rjson = customPropertyService.addCustomProperty(customProperty);
            return rjson;
        } catch (Exception e) {
        	e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "编辑自定义属性", notes = "编辑自定义属性")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "属性id", required = true, paramType = "query"),
            @ApiImplicitParam(name = "propertyName", value = "属性名称", required = true, paramType = "query"),
            @ApiImplicitParam(name = "propertyEnglishName", value = "属性英文名称", required = true, paramType = "query"),
            @ApiImplicitParam(name = "value", value = "属性值", required = true, paramType = "query"),
            @ApiImplicitParam(name = "propertyType", value = "属性类型", required = true, paramType = "query"),
            @ApiImplicitParam(name = "propertyExplain", value = "属性说明", required = true, paramType = "query"),
            @ApiImplicitParam(name = "objectType", value = "对象类型", required = true, paramType = "query"),
            @ApiImplicitParam(name = "bindScopeKey", value = "属性范围", required = true, paramType = "query"),
            @ApiImplicitParam(name = "bindCondition", value = "范围条件", required = true, paramType = "query"),
            @ApiImplicitParam(name = "bindScopeValue", value = "属性范围值", required = true, paramType = "query")
    })
    @RequestMapping(value = "editCustomProperty", method = RequestMethod.POST)
	@OptionalLog(module="基础建模", module2="自定义属性", method="编辑自定义属性")
    public Rjson editCustomProperty(HttpServletRequest request, CMesCustomProperty customProperty){
        try {
            if (StringUtils.isEmpty(customProperty)){
                return Rjson.error("表单数据不能为空");
            }
            Rjson rjson = customPropertyService.editCustomProperty(customProperty);
            return rjson;
        } catch (Exception e) {
        	e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "删除自定义属性", notes = "删除自定义属性")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "属性id", required = true, paramType = "query")
    })
    @RequestMapping(value = "delCustomPropertyById", method = RequestMethod.POST)
	@OptionalLog(module="基础建模", module2="自定义属性", method="编辑自定义属性")
    public Rjson delCustomPropertyById(HttpServletRequest request, Integer id){
        try {
            if (StringUtils.isEmpty(id)){
                return Rjson.error("数据不能为空");
            }
            Rjson rjson = customPropertyService.delCustomProperty(id);
            return rjson;
        } catch (Exception e) {
        	e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "查询属性分组", notes = "查询属性分组")
    @RequestMapping(value = "findPropertyGroupList", method = RequestMethod.POST)
    public Rjson findPropertyGroupList(HttpServletRequest request){
        try {
            List<JSONObject> menuList = cQhMenuService.findMenuList(new JSONObject());
            return Rjson.success(menuList);
        } catch (Exception e) {
        	e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }

}
