package com.skeqi.mes.controller.wf.productionQuality.ncCodeManager.config;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.pojo.wf.productionQuality.ncCodeManager.config.MesNcCodeConfigT;
import com.skeqi.mes.service.all.CMesStationTService;
import com.skeqi.mes.service.wf.productionQuality.ncCodeManager.config.MesNcCodeConfigTService;
import com.skeqi.mes.util.Constant;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;
import com.skeqi.mes.util.yp.EqualsUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;

/**
 * 不合格编码配置
 * @author Lenovo
 */
@RestController
@RequestMapping(value = "ncCode/config", produces = MediaType.APPLICATION_JSON)
@Api(value = "不合格编码配置", description = "不合格编码配置", produces = MediaType.APPLICATION_JSON)
public class NcCodeConfigController {
    @Resource
    private MesNcCodeConfigTService mesNcCodeConfigTService;

    @Resource
    private CMesStationTService stationTService;

    @RequestMapping(value = "findNcCodeConfigList", method = RequestMethod.POST)
    @ApiOperation(value = "查询不合格编码列表", notes = "查询不合格编码列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "code", value = "不合格编码", required = false, paramType = "query", dataType = "Integer")
    })
    @OptionalLog(module="质量", module2="不合格编码配置", method="查询不合格编码列表")
    public Rjson findNcCodeConfigList(HttpServletRequest request) throws ServicesException {
        List<MesNcCodeConfigT> list = null;
        try {
            Integer pageNum= EqualsUtil.pageNum(request);
            Integer pageSize= EqualsUtil.pageSize(request);
            String code = EqualsUtil.string(request, "code", "不合格编码", false);
            MesNcCodeConfigT mesNcCodeConfigT = new MesNcCodeConfigT();
            mesNcCodeConfigT.setCode(code);
            PageHelper.startPage(pageNum,pageSize);
            list = mesNcCodeConfigTService.selectAll(mesNcCodeConfigT);
            PageInfo<MesNcCodeConfigT> pageInfo = new PageInfo<>(list);
            return Rjson.success(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(Constant.INTERFACE_EXCEPTION);
        }
    }

    @RequestMapping(value = "findNcCodeConfigAll", method = RequestMethod.POST)
    @ApiOperation(value = "查询所有不合格编码配置", notes = "查询所有不合格编码配置")
    @OptionalLog(module="质量", module2="不合格编码配置", method="查询所有不合格编码配置")
    public Rjson findNcCodeConfigAll(HttpServletRequest request) throws ServicesException {
        List<MesNcCodeConfigT> list = null;
        try {
            list = mesNcCodeConfigTService.selectAll(new MesNcCodeConfigT());
            return Rjson.success(list);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(Constant.INTERFACE_EXCEPTION);
        }
    }

    @RequestMapping(value = "addNcCodeConfig", method = RequestMethod.POST)
    @ApiOperation(value = "新增不合格编码", notes = "新增不合格编码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "不合格编码", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "describe", value = "编码描述", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "category", value = "不合格编码类别", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "process", value = "适用工序", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "specialControl", value = "特殊管控", required = true, paramType = "query", dataType = "int")
    })
    @OptionalLog(module="质量", module2="不合格编码配置", method="新增不合格编码")
    public Rjson addNcCodeConfig(HttpServletRequest request) throws ServicesException {
        try {
            String code = EqualsUtil.string(request, "code", "不合格编码", true);
            String describe = EqualsUtil.string(request, "describe", "编码描述", true);
            String category = EqualsUtil.string(request, "category", "不合格编码类别", true);
            Integer process = EqualsUtil.integer(request, "process", "适用工序", false);
            Integer specialControl = EqualsUtil.integer(request, "specialControl", "特殊管控", true);
            MesNcCodeConfigT mesNcCodeConfigT = new MesNcCodeConfigT();
            mesNcCodeConfigT.setCode(code);
            mesNcCodeConfigT.setDescribe(describe);
            mesNcCodeConfigT.setCategory(category);
            mesNcCodeConfigT.setProcess(process);
            mesNcCodeConfigT.setSpecialControl(specialControl);
            mesNcCodeConfigT.setCdt(new Date());
            mesNcCodeConfigT.setUdt(new Date());
            return mesNcCodeConfigTService.insertSelective(mesNcCodeConfigT);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }


    @ApiOperation(value = "删除不合格编码", notes = "删除不合格编码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "delNcCodeConfigById", method = RequestMethod.POST)
    @OptionalLog(module="质量", module2="不合格编码配置", method="删除不合格编码")
    public Rjson delNcCodeConfigById(HttpServletRequest request) throws ServicesException {
        try {
            Integer id = EqualsUtil.integer(request, "id", "id", true);
            return mesNcCodeConfigTService.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }

    @RequestMapping(value = "editNcCodeConfig", method = RequestMethod.POST)
    @ApiOperation(value = "编辑不合格编码", notes = "编辑不合格编码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "code", value = "不合格编码", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "describe", value = "编码描述", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "category", value = "不合格编码类别", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "process", value = "适用工序", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "specialControl", value = "特殊管控", required = true, paramType = "query", dataType = "int")
    })
    @OptionalLog(module="质量", module2="不合格编码配置", method="编辑不合格编码")
    public Rjson editNcCodeConfig(HttpServletRequest request) throws ServicesException {
        try {
            Integer id = EqualsUtil.integer(request, "id", "id", true);
            String code = EqualsUtil.string(request, "code", "不合格编码", true);
            String describe = EqualsUtil.string(request, "describe", "编码描述", true);
            String category = EqualsUtil.string(request, "category", "不合格编码类别", true);
            Integer process = EqualsUtil.integer(request, "process", "适用工序", false);
            Integer specialControl = EqualsUtil.integer(request, "specialControl", "特殊管控", true);
            MesNcCodeConfigT mesNcCodeConfigT = new MesNcCodeConfigT();
            mesNcCodeConfigT.setId(id);
            mesNcCodeConfigT.setCode(code);
            mesNcCodeConfigT.setDescribe(describe);
            mesNcCodeConfigT.setCategory(category);
            mesNcCodeConfigT.setProcess(process);
            mesNcCodeConfigT.setSpecialControl(specialControl);
            mesNcCodeConfigT.setUdt(new Date());
            return mesNcCodeConfigTService.updateByPrimaryKeySelective(mesNcCodeConfigT);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }


    @ApiOperation(value = "查询所有工位", notes = "查询所有工位")
    @RequestMapping(value = "findAllStation", method = RequestMethod.POST)
    @OptionalLog(module="质量", module2="不合格编码配置", method="查询所有工位")
    public Rjson findAllStation(HttpServletRequest request, HttpServletResponse response){
        try {
            CMesStationT st = new CMesStationT();
            List<CMesStationT> stationList = stationTService.findAllStation(st);
            return Rjson.success(stationList);
        }catch(Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(Constant.INTERFACE_EXCEPTION);
        }
    }



    @ApiOperation(value = "验证总成号", notes = "验证总成号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sn", value = "总成号", required = true, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "verifySn", method = RequestMethod.POST)
    @OptionalLog(module="质量", module2="不合格记录", method="验证总成号")
    public Rjson verifySn(HttpServletRequest request) throws ServicesException {
        try {
            String sn = EqualsUtil.string(request, "sn", "总成号", true);
            return mesNcCodeConfigTService.verifySn(sn);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }
}
