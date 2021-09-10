package com.skeqi.mes.controller.wf.productionQuality.dataCollection.range;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.wf.productionQuality.dataCollection.range.MesDataCollectionRangeT;
import com.skeqi.mes.service.wf.productionQuality.dataCollection.range.MesDataCollectionRangeTService;
import com.skeqi.mes.service.yp.equipment.Information.EquipmentInformationService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 数据收集组适用范围
 * @author Lenovo
 */
@RestController
@RequestMapping(value = "dataCollection/range", produces = MediaType.APPLICATION_JSON)
@Api(value = "数据收集组适用范围", description = "数据收集组适用范围", produces = MediaType.APPLICATION_JSON)
public class DataCollectionRangeController {
    @Resource
    private MesDataCollectionRangeTService rangeTService;

    @Autowired
    EquipmentInformationService service;

    @RequestMapping(value = "findRangeList", method = RequestMethod.POST)
    @ApiOperation(value = "查询数据收集组适用范围列表", notes = "查询数据收集组适用范围列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "number", value = "编号", required = false, paramType = "query", dataType = "Integer")
    })
    @OptionalLog(module="质量", module2="数据收集组适用范围", method="查询数据收集组适用范围列表")
    public Rjson findRangeList(HttpServletRequest request) throws ServicesException {
        List<MesDataCollectionRangeT> list = null;
        try {
            Integer pageNum= EqualsUtil.pageNum(request);
            Integer pageSize= EqualsUtil.pageSize(request);
            String number = EqualsUtil.string(request, "number", "编号", false);
            MesDataCollectionRangeT rangeT = new MesDataCollectionRangeT();
            rangeT.setNumber(number);
            PageHelper.startPage(pageNum,pageSize);
            list = rangeTService.selectAll(rangeT);
            PageInfo<MesDataCollectionRangeT> pageInfo = new PageInfo<>(list);
            return Rjson.success(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(Constant.INTERFACE_EXCEPTION);
        }
    }

    @RequestMapping(value = "findRangeListAll", method = RequestMethod.POST)
    @ApiOperation(value = "查询所有数据收集组适用范围", notes = "查询所有数据收集组适用范围")
    @OptionalLog(module="质量", module2="数据收集组适用范围", method="查询所有数据收集组适用范围")
    public Rjson findRangeListAll(HttpServletRequest request) throws ServicesException {
        List<MesDataCollectionRangeT> list = null;
        try {
            list = rangeTService.selectAll(new MesDataCollectionRangeT());
            return Rjson.success(list);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(Constant.INTERFACE_EXCEPTION);
        }
    }

    @RequestMapping(value = "addRange", method = RequestMethod.POST)
    @ApiOperation(value = "新增数据收集组适用范围", notes = "新增数据收集组适用范围")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "范围名称", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "equipment", value = "设备", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "material", value = "物料", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "process", value = "工序", required = true, paramType = "query", dataType = "String")
    })
    @OptionalLog(module="质量", module2="数据收集组适用范围", method="新增数据收集组适用范围")
    public Rjson addRange(HttpServletRequest request) throws ServicesException {
        try {
            String name = EqualsUtil.string(request, "name", "范围名称", true);
            String equipment = EqualsUtil.string(request, "equipment", "设备", true);
            String material = EqualsUtil.string(request, "material", "物料", true);
            String process = EqualsUtil.string(request, "process", "工序", true);

            MesDataCollectionRangeT rangeT = new MesDataCollectionRangeT();
            //调用工具类生成编号
            String number = UniversalUtil.generateNumber();
            Boolean flag = true;
            while (flag){
                MesDataCollectionRangeT record = rangeTService.selectByPrimaryKey(number);
                if (!StringUtils.isEmpty(record)){
                    number = UniversalUtil.generateNumber();
                    continue;
                }
                flag=false;
            }
            rangeT.setNumber(number);
            rangeT.setName(name);
            rangeT.setEquipment(equipment);
            rangeT.setMaterial(material);
            rangeT.setProcess(process);
            rangeT.setCdt(new Date());
            rangeT.setUdt(new Date());
            int i = rangeTService.insertSelective(rangeT);
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


    @ApiOperation(value = "删除数据收集组适用范围", notes = "删除数据收集组适用范围")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "number", value = "编号", required = true, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "delRangeByNumber", method = RequestMethod.POST)
    @OptionalLog(module="质量", module2="数据收集组适用范围", method="删除数据收集组适用范围")
    public Rjson delRangeByNumber(HttpServletRequest request) throws ServicesException {
        try {
            String number = EqualsUtil.string(request, "number", "编号", true);
            int i = rangeTService.deleteByPrimaryKey(number);
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

    @RequestMapping(value = "editRange", method = RequestMethod.POST)
    @ApiOperation(value = "编辑数据收集组适用范围", notes = "编辑数据收集组适用范围")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "范围名称", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "number", value = "编号", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "equipment", value = "设备", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "material", value = "物料", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "process", value = "工序", required = true, paramType = "query", dataType = "String")
    })
    @OptionalLog(module="质量", module2="数据收集组适用范围", method="编辑数据收集组适用范围")
    public Rjson editRange(HttpServletRequest request) throws ServicesException {
        try {
            String name = EqualsUtil.string(request, "name", "范围名称", true);
            String number = EqualsUtil.string(request, "number", "编号", true);
            String equipment = EqualsUtil.string(request, "equipment", "设备", true);
            String material = EqualsUtil.string(request, "material", "物料", true);
            String process = EqualsUtil.string(request, "process", "工序", true);
            MesDataCollectionRangeT rangeT = new MesDataCollectionRangeT();
            rangeT.setNumber(number);
            rangeT.setEquipment(equipment);
            rangeT.setMaterial(material);
            rangeT.setProcess(process);
            rangeT.setUdt(new Date());
            int i = rangeTService.updateByPrimaryKeySelective(rangeT);
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
