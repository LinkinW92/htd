package com.skeqi.mes.controller.wf.productionQuality.dataCollection.params;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.wf.productionQuality.dataCollection.params.MesDataCollectionParamsT;
import com.skeqi.mes.service.wf.productionQuality.dataCollection.params.MesDataCollectionParamsTService;
import com.skeqi.mes.util.Constant;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;
import com.skeqi.mes.util.wf.UniversalUtil;
import com.skeqi.mes.util.yp.EqualsUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;

/**
 * 数据收集组参数清单
 * @author Lenovo
 */
@RestController
@RequestMapping(value = "dataCollection/params", produces = MediaType.APPLICATION_JSON)
@Api(value = "数据收集组参数清单", description = "数据收集组参数清单", produces = MediaType.APPLICATION_JSON)
public class DataCollectionParamsController {
    @Resource
    private MesDataCollectionParamsTService paramsTService;

    @RequestMapping(value = "findParamsList", method = RequestMethod.POST)
    @ApiOperation(value = "查询数据收集组参数清单列表", notes = "查询数据收集组参数清单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "number", value = "编号", required = false, paramType = "query", dataType = "String")
    })
    @OptionalLog(module="质量", module2="数据收集组参数清单", method="查询数据收集组参数清单列表")
    public Rjson findParamsList(HttpServletRequest request) throws ServicesException {
        List<MesDataCollectionParamsT> list = null;
        try {
            Integer pageNum= EqualsUtil.pageNum(request);
            Integer pageSize= EqualsUtil.pageSize(request);
            String number = EqualsUtil.string(request, "number", "编号", false);
            MesDataCollectionParamsT paramsT = new MesDataCollectionParamsT();
            paramsT.setNumber(number);
            PageHelper.startPage(pageNum,pageSize);
            list = paramsTService.selectAll(paramsT);
            PageInfo<MesDataCollectionParamsT> pageInfo = new PageInfo<>(list);
            return Rjson.success(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(Constant.INTERFACE_EXCEPTION);
        }
    }

    @RequestMapping(value = "findParamsListAll", method = RequestMethod.POST)
    @ApiOperation(value = "查询所有数据收集组参数清单", notes = "查询所有数据收集组参数清单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "number", value = "编号", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "groupNumber", value = "所属组号", required = false, paramType = "query", dataType = "String")
    })
    @OptionalLog(module="质量", module2="数据收集组参数清单", method="查询所有数据收集组参数清单")
    public Rjson findParamsListAll(HttpServletRequest request) throws ServicesException {
        List<MesDataCollectionParamsT> list = null;
        try {
            String number = EqualsUtil.string(request, "number", "编号", false);
            String groupNumber = EqualsUtil.string(request, "groupNumber", "所属组号", false);
            MesDataCollectionParamsT mesDataCollectionParamsT = new MesDataCollectionParamsT();
            mesDataCollectionParamsT.setNumber(number);
            mesDataCollectionParamsT.setGroupNumber(groupNumber);
            list = paramsTService.selectAll(mesDataCollectionParamsT);
            return Rjson.success(list);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(Constant.INTERFACE_EXCEPTION);
        }
    }

    @RequestMapping(value = "addParams", method = RequestMethod.POST)
    @ApiOperation(value = "新增数据收集组参数清单", notes = "新增数据收集组参数清单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "参数名称", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "groupNumber", value = "所属组号", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "upperLimit", value = "参数上限", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "lowerLimit", value = "参数下限", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "necessary", value = "必收标记", required = true, paramType = "query", dataType = "int")
    })
    @OptionalLog(module="质量", module2="数据收集组参数清单", method="新增数据收集组参数清单")
    public Rjson addParams(HttpServletRequest request) throws ServicesException {
        try {
            String name = EqualsUtil.string(request, "name", "参数名称", true);
            String groupNumber = EqualsUtil.string(request, "groupNumber", "所属组号", true);
            String upperLimit = EqualsUtil.string(request, "upperLimit", "参数上限", true);
            String lowerLimit = EqualsUtil.string(request, "lowerLimit", "参数下限", true);
            Integer necessary = EqualsUtil.integer(request, "necessary", "必收标记", true);
            MesDataCollectionParamsT paramsT = new MesDataCollectionParamsT();
            //调用工具类生成编号
            String number = UniversalUtil.generateNumber();
            Boolean flag = true;
            while (flag){
                MesDataCollectionParamsT record = paramsTService.selectByPrimaryKey(number);
                if (!StringUtils.isEmpty(record)){
                    number = UniversalUtil.generateNumber();
                    continue;
                }
                flag=false;
            }
            paramsT.setNumber(number);
            paramsT.setName(name);
            paramsT.setGroupNumber(groupNumber);
            paramsT.setUpperLimit(upperLimit);
            paramsT.setLowerLimit(lowerLimit);
            paramsT.setNecessary(necessary);
            paramsT.setCdt(new Date());
            paramsT.setUdt(new Date());
            int i = paramsTService.insertSelective(paramsT);
            if (i<1){
                return Rjson.error("添加失败");
            }
            return Rjson.success("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }


    @ApiOperation(value = "删除数据收集组参数清单", notes = "删除数据收集组参数清单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "number", value = "编号", required = true, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "delParamsByNumber", method = RequestMethod.POST)
    @OptionalLog(module="质量", module2="数据收集组参数清单", method="删除数据收集组参数清单")
    public Rjson delParamsByNumber(HttpServletRequest request) throws ServicesException {
        try {
            String number = EqualsUtil.string(request, "number", "编号", true);
            int i = paramsTService.deleteByPrimaryKey(number);
            if (i>1){
                return Rjson.error("删除失败");
            }
            return Rjson.success("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }

    @RequestMapping(value = "editParams", method = RequestMethod.POST)
    @ApiOperation(value = "编辑数据收集组参数清单", notes = "编辑数据收集组参数清单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "number", value = "编号", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "name", value = "参数名称", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "groupNumber", value = "所属组号", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "upperLimit", value = "参数上限", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "lowerLimit", value = "参数下限", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "necessary", value = "必收标记", required = true, paramType = "query", dataType = "int")
    })
    @OptionalLog(module="质量", module2="数据收集组参数清单", method="编辑数据收集组参数清单")
    public Rjson editParams(HttpServletRequest request) throws ServicesException {
        try {
            String number = EqualsUtil.string(request, "number", "编号", true);
            String name = EqualsUtil.string(request, "name", "参数名称", true);
            String groupNumber = EqualsUtil.string(request, "groupNumber", "所属组号", true);
            String upperLimit = EqualsUtil.string(request, "upperLimit", "参数上限", true);
            String lowerLimit = EqualsUtil.string(request, "lowerLimit", "参数下限", true);
            Integer necessary = EqualsUtil.integer(request, "necessary", "必收标记", true);
            MesDataCollectionParamsT paramsT = new MesDataCollectionParamsT();
            paramsT.setNumber(number);
            paramsT.setName(name);
            paramsT.setGroupNumber(groupNumber);
            paramsT.setUpperLimit(upperLimit);
            paramsT.setLowerLimit(lowerLimit);
            paramsT.setNecessary(necessary);
            paramsT.setUdt(new Date());
            int i = paramsTService.updateByPrimaryKeySelective(paramsT);
            if (i>1){
                return Rjson.error("编辑失败");
            }
            return Rjson.success("编辑成功");
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }
}
