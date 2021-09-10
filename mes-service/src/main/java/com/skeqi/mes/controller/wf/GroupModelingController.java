package com.skeqi.mes.controller.wf;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.qh.*;
import com.skeqi.mes.service.wf.CMesAreaTService;
import com.skeqi.mes.service.wf.CMesCompanyTService;
import com.skeqi.mes.service.wf.CMesFactoryTService;
import com.skeqi.mes.service.wf.CMesPlantTService;
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
import java.util.Comparator;
import java.util.List;

/**
 * 集团建模
 * @author Lenovo
 */
@RestController
@RequestMapping(value = "groupModeling", produces = MediaType.APPLICATION_JSON)
@Api(value = "集团建模", description = "集团建模", produces = MediaType.APPLICATION_JSON)
public class GroupModelingController {
    @Autowired
    private CMesCompanyTService companyTService;
    @Autowired
    private CMesFactoryTService factoryTService;

    @Autowired
    private CMesAreaTService areaTService;

    @Autowired
    private CMesPlantTService plantTService;

    @ApiOperation(value = "查询公司", notes = "查询公司")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "companyCode", value = "公司编号", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "companyName", value = "公司名称", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "Integer")
    })
    @RequestMapping(value = "findCompanyAll", method = RequestMethod.POST)
    public Rjson findCompanyAll(HttpServletRequest request){
        try {

            Integer pageNum= EqualsUtil.pageNum(request);
            Integer pageSize= EqualsUtil.pageSize(request);
            PageHelper.startPage(null == pageNum?1:pageNum,null == pageSize?10:pageSize);
            String companyCode = EqualsUtil.string(request, "companyCode", "公司编号", false);
            String companyName = EqualsUtil.string(request, "companyName", "公司名称", false);
            List<CMesCompanyT> list = companyTService.findCompanyAll(companyCode,companyName);
            PageInfo<CMesCompanyT> pageInfo = new PageInfo<CMesCompanyT>(list);
            return Rjson.success(pageInfo);
        } catch (Exception e) {
        	e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "查询工厂", notes = "查询工厂")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "companyName", value = "所属公司", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "factoryCode", value = "工厂编号", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "factoryName", value = "工厂名称", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "Integer")
    })
    @RequestMapping(value = "findFactoryAll", method = RequestMethod.POST)
    public Rjson findFactoryAll(HttpServletRequest request){
        try {
            Integer pageNum= EqualsUtil.pageNum(request);
            Integer pageSize= EqualsUtil.pageSize(request);
            PageHelper.startPage(null == pageNum?1:pageNum,null == pageSize?10:pageSize);
            String companyName = EqualsUtil.string(request, "companyName", "所属公司", false);
            String factoryCode = EqualsUtil.string(request, "factoryCode", "工厂编号", false);
            String factoryName = EqualsUtil.string(request, "factoryName", "工厂名称", false);
            List<CMesFactoryT> list = factoryTService.findFactoryAll(companyName,factoryCode,factoryName);
            PageInfo<CMesFactoryT> pageInfo = new PageInfo<>(list);
            return Rjson.success(pageInfo);
        } catch (Exception e) {
        	e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "查询区域", notes = "查询区域")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "companyName", value = "所属公司", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "factoryName", value = "所属工厂", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "areaCode", value = "所属区域", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "areaName", value = "区域名称", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "Integer")
    })

    @RequestMapping(value = "findAreaAll", method = RequestMethod.POST)
    public Rjson findAreaAll(HttpServletRequest request){
        try {
            Integer pageNum= EqualsUtil.pageNum(request);
            Integer pageSize= EqualsUtil.pageSize(request);
            PageHelper.startPage(null == pageNum?1:pageNum,null == pageSize?10:pageSize);
            String companyName=EqualsUtil.string(request,"companyName","所属公司",false);
            String factoryName=EqualsUtil.string(request,"factoryName","所属工厂",false);
            String areaCode=EqualsUtil.string(request,"areaCode","所属区域",false);
            String areaName=EqualsUtil.string(request,"areaName","区域名称",false);
            CMesAreaT cMesAreaT =new CMesAreaT();
            cMesAreaT.setCompanyName(companyName);
            cMesAreaT.setFactoryName(factoryName);
            cMesAreaT.setAreaCode(areaCode);
            cMesAreaT.setAreaName(areaName);
            List<CMesAreaT> list = areaTService.findAreaAll(cMesAreaT);
            PageInfo<CMesAreaT> pageInfo = new PageInfo<CMesAreaT>(list);
            return Rjson.success(pageInfo);
        } catch (Exception e) {
        	e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "查询车间", notes = "查询车间")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "companyName", value = "所属公司", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "factoryName", value = "所属工厂", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "areaName", value = "所属区域", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "plantCode", value = "车间编号", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "plantName", value = "车间名称", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "Integer")
    })
    @RequestMapping(value = "findPlantAll", method = RequestMethod.POST)
    public Rjson findPlantAll(HttpServletRequest request){
        try {
            Integer pageNum= EqualsUtil.pageNum(request);
            Integer pageSize= EqualsUtil.pageSize(request);
            PageHelper.startPage(null == pageNum?1:pageNum,null == pageSize?10:pageSize);
            String companyName=EqualsUtil.string(request,"companyName","所属公司",false);
            String factoryName=EqualsUtil.string(request,"factoryName","所属工厂",false);
            String areaName=EqualsUtil.string(request,"areaName","所属区域",false);
            String plantCode=EqualsUtil.string(request,"plantCode","车间编号",false);
            String plantName=EqualsUtil.string(request,"plantName","车间名称",false);
            CMesPlantT cMesPlantT =new CMesPlantT();
            cMesPlantT.setCompanyName(companyName);
            cMesPlantT.setAreaName(areaName);
            cMesPlantT.setFactoryName(factoryName);
            cMesPlantT.setPlantCode(plantCode);
            cMesPlantT.setPlantName(plantName);
            List<CMesPlantT> list = plantTService.findPlantAll(cMesPlantT);
            PageInfo<CMesPlantT> pageInfo = new PageInfo<CMesPlantT>(list);
            return Rjson.success(pageInfo);
        } catch (Exception e) {
        	e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }



    @ApiOperation(value = "新增公司", notes = "新增公司")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "companyCode", value = "公司编号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "companyName", value = "公司名称", required = true, paramType = "query"),
            @ApiImplicitParam(name = "companyAddress", value = "公司地址", required = true, paramType = "query"),
            @ApiImplicitParam(name = "describe", value = "公司描述", required = true, paramType = "query")
    })
    @RequestMapping(value = "addCompany", method = RequestMethod.POST)
	@OptionalLog(module="基础建模", module2="公司定义", method="新增公司")
    public Rjson addCompany(HttpServletRequest request, CMesCompanyT cMesCompanyT){
        try {
            if (StringUtils.isEmpty(cMesCompanyT)){
                return Rjson.error("表单数据不能为空");
            }
            Rjson rjson = companyTService.addCompany(cMesCompanyT);
            return rjson;
        } catch (Exception e) {
        	e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "编辑公司", notes = "编辑公司")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "公司id", required = true, paramType = "query"),
            @ApiImplicitParam(name = "companyCode", value = "公司编号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "companyName", value = "公司名称", required = true, paramType = "query"),
            @ApiImplicitParam(name = "companyAddress", value = "公司地址", required = true, paramType = "query"),
            @ApiImplicitParam(name = "describe", value = "公司描述", required = true, paramType = "query")
    })
    @RequestMapping(value = "editCompany", method = RequestMethod.POST)
	@OptionalLog(module="基础建模", module2="公司定义", method="编辑公司")
    public Rjson editCompany(HttpServletRequest request, CMesCompanyT cMesCompanyT){
        try {
            if (StringUtils.isEmpty(cMesCompanyT)){
                return Rjson.error("表单数据不能为空");
            }
            Rjson rjson = companyTService.editCompany(cMesCompanyT);
            return rjson;
        } catch (Exception e) {
        	e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "删除公司", notes = "删除公司")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "公司id", required = true, paramType = "query"),
            @ApiImplicitParam(name = "companyCode", value = "公司编号", required = true, paramType = "query")
    })
    @RequestMapping(value = "delCompanyByIdAndCode", method = RequestMethod.POST)
	@OptionalLog(module="基础建模", module2="公司定义", method="删除公司")
    public Rjson delCompanyByIdAndCode(HttpServletRequest request, CMesCompanyT companyT){
        try {
            if (StringUtils.isEmpty(companyT)){
                return Rjson.error("数据不能为空");
            }
            Rjson rjson = companyTService.delCompanyByIdAndCode(companyT);
            return rjson;
        } catch (Exception e) {
        	e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }




    @ApiOperation(value = "新增工厂", notes = "新增工厂")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "companyCode", value = "所属公司", required = true, paramType = "query"),
            @ApiImplicitParam(name = "factoryCode", value = "工厂编号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "factoryName", value = "工厂名称", required = true, paramType = "query"),
            @ApiImplicitParam(name = "describe", value = "工厂描述", required = true, paramType = "query")
    })
    @RequestMapping(value = "addFactory", method = RequestMethod.POST)
	@OptionalLog(module="基础建模", module2="工厂定义", method="新增工厂")
    public Rjson addCustomProperty(HttpServletRequest request, CMesFactoryT factoryT){
        try {
            if (StringUtils.isEmpty(factoryT)){
                return Rjson.error("表单数据不能为空");
            }
            //自定义属性所需参数
            JSONArray jsonArray = JSON.parseArray(request.getParameter("custom"));
            factoryT.setCustom(jsonArray);

            Rjson rjson = factoryTService.addFactory(factoryT);
            return rjson;
        } catch (Exception e) {
        	e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "编辑工厂", notes = "编辑工厂")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "工厂id", required = true, paramType = "query"),
            @ApiImplicitParam(name = "companyCode", value = "所属公司", required = true, paramType = "query"),
            @ApiImplicitParam(name = "factoryCode", value = "工厂编号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "factoryName", value = "工厂名称", required = true, paramType = "query"),
            @ApiImplicitParam(name = "describe", value = "工厂描述", required = true, paramType = "query")
    })
    @RequestMapping(value = "editFactory", method = RequestMethod.POST)
	@OptionalLog(module="基础建模", module2="工厂定义", method="编辑工厂")
    public Rjson editCustomProperty(HttpServletRequest request, CMesFactoryT factoryT){
        try {
            if (StringUtils.isEmpty(factoryT)){
                return Rjson.error("表单数据不能为空");
            }
            //修改自定义属性值（内容）
            JSONArray jsonArray = JSON.parseArray(request.getParameter("custom"));
            factoryT.setCustom(jsonArray);


            Rjson rjson = factoryTService.editFactory(factoryT);
            return rjson;
        } catch (Exception e) {
        	e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "删除工厂", notes = "删除工厂")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "工厂id", required = true, paramType = "query"),
            @ApiImplicitParam(name = "factoryCode", value = "工厂编号", required = true, paramType = "query")
    })
    @RequestMapping(value = "delFactoryByIdAndCode", method = RequestMethod.POST)
	@OptionalLog(module="基础建模", module2="工厂定义", method="删除工厂")
    public Rjson delFactoryByIdAndCode(HttpServletRequest request, CMesFactoryT factoryT){
        try {
            if (StringUtils.isEmpty(factoryT)){
                return Rjson.error("数据不能为空");
            }
            Rjson rjson = factoryTService.delFactoryByIdAndCode(factoryT);
            return rjson;
        } catch (Exception e) {
        	e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "新增区域", notes = "新增区域")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "companyCode", value = "所属公司", required = true, paramType = "query"),
            @ApiImplicitParam(name = "factoryCode", value = "所属工厂", required = true, paramType = "query"),
            @ApiImplicitParam(name = "areaCode", value = "区域编号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "areaName", value = "区域名称", required = true, paramType = "query"),
            @ApiImplicitParam(name = "describe", value = "区域描述", required = true, paramType = "query")
    })
    @RequestMapping(value = "addArea", method = RequestMethod.POST)
	@OptionalLog(module="基础建模", module2="区域定义", method="新增区域")
    public Rjson addArea(HttpServletRequest request, CMesAreaT areaT){
        try {
            if (StringUtils.isEmpty(areaT)){
                return Rjson.error("表单数据不能为空");
            }
            //自定义属性所需参数
            JSONArray jsonArray = JSON.parseArray(request.getParameter("custom"));
            areaT.setCustom(jsonArray);
            Rjson rjson = areaTService.addArea(areaT);
            return rjson;
        } catch (Exception e) {
        	e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "编辑区域", notes = "编辑区域")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "区域id", required = true, paramType = "query"),
            @ApiImplicitParam(name = "companyCode", value = "所属公司", required = true, paramType = "query"),
            @ApiImplicitParam(name = "factoryCode", value = "所属工厂", required = true, paramType = "query"),
            @ApiImplicitParam(name = "areaCode", value = "区域编号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "areaName", value = "区域名称", required = true, paramType = "query"),
            @ApiImplicitParam(name = "describe", value = "区域描述", required = true, paramType = "query"),

    })
    @RequestMapping(value = "editArea", method = RequestMethod.POST)
	@OptionalLog(module="基础建模", module2="区域定义", method="编辑区域")
    public Rjson editArea(HttpServletRequest request, CMesAreaT areaT){
        try {
            if (StringUtils.isEmpty(areaT)){
                return Rjson.error("表单数据不能为空");
            }
            //修改自定义属性值（内容）
            JSONArray jsonArray = JSON.parseArray(request.getParameter("custom"));
            areaT.setCustom(jsonArray);
            Rjson rjson = areaTService.editArea(areaT);
            return rjson;
        } catch (Exception e) {
        	e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "删除区域", notes = "删除区域")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "区域id", required = true, paramType = "query"),
            @ApiImplicitParam(name = "factoryCode", value = "区域编号", required = true, paramType = "query")
    })
    @RequestMapping(value = "delAreaByIdAndCode", method = RequestMethod.POST)
	@OptionalLog(module="基础建模", module2="区域定义", method="删除区域")
    public Rjson delAreaByIdAndCode(HttpServletRequest request, CMesAreaT areaT){
        try {
            if (StringUtils.isEmpty(areaT)){
                return Rjson.error("数据不能为空");
            }
            Rjson rjson = areaTService.delAreaByIdAndCode(areaT);
            return rjson;
        } catch (Exception e) {
        	e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }


    @ApiOperation(value = "新增车间", notes = "新增车间")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "companyCode", value = "所属公司", required = true, paramType = "query"),
            @ApiImplicitParam(name = "factoryCode", value = "所属工厂", required = true, paramType = "query"),
            @ApiImplicitParam(name = "areaCode", value = "所属区域", required = true, paramType = "query"),
            @ApiImplicitParam(name = "plantCode", value = "车间编号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "plantName", value = "车间名称", required = true, paramType = "query"),
            @ApiImplicitParam(name = "describe", value = "车间描述", required = true, paramType = "query"),
    })
    @RequestMapping(value = "addPlant", method = RequestMethod.POST)
	@OptionalLog(module="基础建模", module2="车间定义", method="新增车间")
    public Rjson addPlant(HttpServletRequest request, CMesPlantT plantT){
        try {
            if (StringUtils.isEmpty(plantT)){
                return Rjson.error("表单数据不能为空");
            }
            //自定义属性所需参数
            JSONArray jsonArray = JSON.parseArray(request.getParameter("custom"));
            plantT.setCustom(jsonArray);
            Rjson rjson = plantTService.addPlant(plantT);
            return rjson;
        } catch (Exception e) {
        	e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }


    @ApiOperation(value = "编辑车间", notes = "编辑车间")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "车间id", required = true, paramType = "query"),
            @ApiImplicitParam(name = "companyCode", value = "所属公司", required = true, paramType = "query"),
            @ApiImplicitParam(name = "factoryCode", value = "所属工厂", required = true, paramType = "query"),
            @ApiImplicitParam(name = "areaCode", value = "所属区域", required = true, paramType = "query"),
            @ApiImplicitParam(name = "plantCode", value = "车间编号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "plantName", value = "车间名称", required = true, paramType = "query"),
            @ApiImplicitParam(name = "describe", value = "车间描述", required = true, paramType = "query"),
    })
    @RequestMapping(value = "editPlant", method = RequestMethod.POST)
	@OptionalLog(module="基础建模", module2="车间定义", method="编辑车间")
    public Rjson editPlant(HttpServletRequest request, CMesPlantT plantT){
        try {
            if (StringUtils.isEmpty(plantT)){
                return Rjson.error("表单数据不能为空");
            }
            //修改自定义属性值（内容）
            JSONArray jsonArray = JSON.parseArray(request.getParameter("custom"));
            plantT.setCustom(jsonArray);
            Rjson rjson = plantTService.editPlant(plantT);
            return rjson;
        } catch (Exception e) {
        	e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "删除车间", notes = "删除车间")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "车间id", required = true, paramType = "query"),
            @ApiImplicitParam(name = "plantCode", value = "车间编号", required = true, paramType = "query")
    })
    @RequestMapping(value = "delPlantByIdAndCode", method = RequestMethod.POST)
	@OptionalLog(module="基础建模", module2="车间定义", method="删除车间")
    public Rjson delPlantByIdAndCode(HttpServletRequest request, CMesPlantT plantT){
        try {
            if (StringUtils.isEmpty(plantT)){
                return Rjson.error("数据不能为空");
            }
            Rjson rjson = plantTService.delPlantByIdAndCode(plantT);
            return rjson;
        } catch (Exception e) {
        	e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }

}
