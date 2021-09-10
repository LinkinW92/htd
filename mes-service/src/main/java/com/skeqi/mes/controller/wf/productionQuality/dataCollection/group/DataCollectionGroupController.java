package com.skeqi.mes.controller.wf.productionQuality.dataCollection.group;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.wf.productionQuality.dataCollection.group.MesDataCollectionGroupT;
import com.skeqi.mes.service.wf.productionQuality.dataCollection.group.MesDataCollectionGroupTService;
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
 * 数据收集组定义
 * @author Lenovo
 */
@RestController
@RequestMapping(value = "dataCollection/group", produces = MediaType.APPLICATION_JSON)
@Api(value = "数据收集组定义", description = "数据收集组定义", produces = MediaType.APPLICATION_JSON)
public class DataCollectionGroupController {

    @Resource
    private MesDataCollectionGroupTService groupTService;

    @RequestMapping(value = "findGroupList", method = RequestMethod.POST)
    @ApiOperation(value = "查询数据收集组列表", notes = "查询数据收集组列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "number", value = "组号", required = false, paramType = "query", dataType = "Integer")
    })
    @OptionalLog(module="质量", module2="数据收集组定义", method="查询不合格编码列表")
    public Rjson findGroupList(HttpServletRequest request) throws ServicesException {
        List<MesDataCollectionGroupT> list = null;
        try {
            Integer pageNum= EqualsUtil.pageNum(request);
            Integer pageSize= EqualsUtil.pageSize(request);
            String number = EqualsUtil.string(request, "number", "组号", false);
            MesDataCollectionGroupT groupT = new MesDataCollectionGroupT();
            groupT.setNumber(number);
            PageHelper.startPage(pageNum,pageSize);
            list = groupTService.selectAll(groupT);
            PageInfo<MesDataCollectionGroupT> pageInfo = new PageInfo<>(list);
            return Rjson.success(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(Constant.INTERFACE_EXCEPTION);
        }
    }

    @RequestMapping(value = "findGroupListAll", method = RequestMethod.POST)
    @ApiOperation(value = "查询所有数据收集组", notes = "查询所有数据收集组")
    @OptionalLog(module="质量", module2="数据收集组定义", method="查询所有数据收集组")
    public Rjson findGroupListAll(HttpServletRequest request) throws ServicesException {
        List<MesDataCollectionGroupT> list = null;
        try {
            list = groupTService.selectAll(new MesDataCollectionGroupT());
            return Rjson.success(list);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(Constant.INTERFACE_EXCEPTION);
        }
    }

    @RequestMapping(value = "addGroup", method = RequestMethod.POST)
    @ApiOperation(value = "新增数据收集组", notes = "新增数据收集组")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "组名称", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "range", value = "适用范围", required = true, paramType = "query", dataType = "String")
    })
    @OptionalLog(module="质量", module2="数据收集组定义", method="新增数据收集组")
    public Rjson addGroup(HttpServletRequest request) throws ServicesException {
        try {
            String name = EqualsUtil.string(request, "name", "组名称", true);
            String range = EqualsUtil.string(request, "range", "适用范围", true);
            MesDataCollectionGroupT groupT = new MesDataCollectionGroupT();
            //调用工具类生成编号
            String number = UniversalUtil.generateNumber();
            Boolean flag = true;
            while (flag){
                MesDataCollectionGroupT record = groupTService.selectByPrimaryKey(number);
                if (!StringUtils.isEmpty(record)){
                    number = UniversalUtil.generateNumber();
                    continue;
                }
                flag=false;
            }
            groupT.setNumber(number);
            groupT.setName(name);
            groupT.setRange(range);
            groupT.setCdt(new Date());
            groupT.setUdt(new Date());
            int i = groupTService.insertSelective(groupT);
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


    @ApiOperation(value = "删除数据收集组", notes = "删除数据收集组")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "number", value = "编号", required = true, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "delGroupByNumber", method = RequestMethod.POST)
    @OptionalLog(module="质量", module2="数据收集组定义", method="删除数据收集组")
    public Rjson delGroupByNumber(HttpServletRequest request) throws ServicesException {
        try {
            String number = EqualsUtil.string(request, "number", "编号", true);
            int i = groupTService.deleteByPrimaryKey(number);
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

    @RequestMapping(value = "editGroup", method = RequestMethod.POST)
    @ApiOperation(value = "编辑数据收集组", notes = "编辑数据收集组")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "number", value = "组编号", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "name", value = "组名称", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "range", value = "适用范围", required = true, paramType = "query", dataType = "String")
    })
    @OptionalLog(module="质量", module2="数据收集组定义", method="编辑数据收集组")
    public Rjson editGroup(HttpServletRequest request) throws ServicesException {
        try {
            String number = EqualsUtil.string(request, "number", "组编号", true);
            String name = EqualsUtil.string(request, "name", "组名称", true);
            String range = EqualsUtil.string(request, "range", "适用范围", true);
            MesDataCollectionGroupT groupT = new MesDataCollectionGroupT();
            groupT.setNumber(number);
            groupT.setName(name);
            groupT.setRange(range);
            groupT.setUdt(new Date());
            int i = groupTService.updateByPrimaryKeySelective(groupT);
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
