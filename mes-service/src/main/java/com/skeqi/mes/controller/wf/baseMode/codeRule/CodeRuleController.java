package com.skeqi.mes.controller.wf.baseMode.codeRule;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import com.skeqi.mes.pojo.wf.baseMode.coderRule.CMesCodeRuleT;
import com.skeqi.mes.util.yp.EqualsUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.service.wf.baseMode.codeRule.CodeRuleService;
import com.skeqi.mes.util.Constant;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RestController;

/**
 * 编码规则管理
 * @author SKQ
 *
 */
@RestController
@RequestMapping(value = "cod", produces = MediaType.APPLICATION_JSON)
@Api(value = "编码规则管理", description = "编码规则管理", produces = MediaType.APPLICATION_JSON)
public class CodeRuleController {

    @Resource
    CodeRuleService service;

    @RequestMapping(value = "/findCodeRuleList", method = RequestMethod.POST)
    @ApiOperation(value = "查询条码规则列表", notes = "查询条码规则列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "codeType", value = "类型编码", required = false, paramType = "query", dataType = "Integer")
    })
    public Rjson findCodeRuleList(HttpServletRequest request) throws ServicesException {
        List<CMesCodeRuleT> list = null;
        try {
            Integer pageNum= EqualsUtil.pageNum(request);
            Integer pageSize= EqualsUtil.pageSize(request);
            String codeType = EqualsUtil.string(request, "codeType", "类型编码", false);
            CMesCodeRuleT codeRuleT = new CMesCodeRuleT();
            codeRuleT.setCodeType(codeType);
            PageHelper.startPage(pageNum,pageSize);
            list = service.findCodeRuleList(codeRuleT);
            PageInfo<CMesCodeRuleT> pageInfo = new PageInfo<>(list);
            return Rjson.success(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }

    @RequestMapping(value = "/addCodeRule", method = RequestMethod.POST)
    @ApiOperation(value = "新增编号规则", notes = "新增编号规则")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "codeType", value = "类型编码", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "codeName", value = "编号名称", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "codeRulePrefix", value = "编号规则前缀", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "codeRule", value = "编号规则", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "codeRuleSuffix", value = "编号规则后缀", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "explain", value = "规则说明", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "resetCycle", value = "重置周期", required = true, paramType = "query", dataType = "int")
    })
    @OptionalLog(module="基础建模", module2="编号规则", method="新增编号规则")
    public Rjson addCodeRule(HttpServletRequest request) throws ServicesException {
        try {
            String codeType = EqualsUtil.string(request, "codeType", "类型编码", true);
            String codeName = EqualsUtil.string(request, "codeName", "编号名称", true);
            String codeRulePrefix = EqualsUtil.string(request, "codeRulePrefix", "编号规则前缀", true);
            String codeRule = EqualsUtil.string(request, "codeRule", "编号规则", true);
            Integer codeRuleSuffix = EqualsUtil.integer(request, "codeRuleSuffix", "编号规则后缀", true);
            String explain = EqualsUtil.string(request, "explain", "规则说明", true);
            Integer resetCycle = EqualsUtil.integer(request, "resetCycle", "重置周期", true);
            CMesCodeRuleT cMesCodeRuleT = new CMesCodeRuleT();
            cMesCodeRuleT.setCodeType(codeType);
            cMesCodeRuleT.setCodeName(codeName);
            cMesCodeRuleT.setCodeRulePrefix(codeRulePrefix);
            cMesCodeRuleT.setCodeRule(codeRule);
            cMesCodeRuleT.setCodeRuleSuffix(codeRuleSuffix);
            cMesCodeRuleT.setExplain(explain);
            cMesCodeRuleT.setResetCycle(resetCycle);
            service.addCodeRule(cMesCodeRuleT);

            return Rjson.success();
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }


    @ApiOperation(value = "删除编号规则", notes = "删除编号规则")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "/delCodeRuleListById", method = RequestMethod.POST)
    @OptionalLog(module="基础建模", module2="编号规则", method="删除编号规则")
    public Rjson delCodeRuleListById(HttpServletRequest request) throws ServicesException {
        try {
            Integer id = EqualsUtil.integer(request, "id", "id", true);
            return service.delCodeRuleListById(id);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }

    @RequestMapping(value = "/editCodeRule", method = RequestMethod.POST)
    @ApiOperation(value = "编辑编号规则", notes = "编辑编号规则")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "codeName", value = "编号名称", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "codeRulePrefix", value = "编号规则前缀", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "codeRule", value = "编号规则", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "codeRuleSuffix", value = "编号规则后缀", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "explain", value = "规则说明", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "resetCycle", value = "重置周期", required = true, paramType = "query", dataType = "int")
    })
    @OptionalLog(module="基础建模", module2="编号规则", method="编辑编号规则")
    public Rjson editCodeRule(HttpServletRequest request) throws ServicesException {
        try {
            Integer id = EqualsUtil.integer(request, "id", "id", true);
            String codeName = EqualsUtil.string(request, "codeName", "编号名称", true);
            String codeRulePrefix = EqualsUtil.string(request, "codeRulePrefix", "编号规则前缀", true);
            String codeRule = EqualsUtil.string(request, "codeRule", "编号规则", true);
            Integer codeRuleSuffix = EqualsUtil.integer(request, "codeRuleSuffix", "编号规则后缀", true);
            String explain = EqualsUtil.string(request, "explain", "规则说明", true);
            Integer resetCycle = EqualsUtil.integer(request, "resetCycle", "重置周期", true);
            CMesCodeRuleT codeRuleT = new CMesCodeRuleT();
            codeRuleT.setId(id);
            codeRuleT.setCodeName(codeName);
            codeRuleT.setCodeRulePrefix(codeRulePrefix);
            codeRuleT.setCodeRule(codeRule);
            codeRuleT.setCodeRuleSuffix(codeRuleSuffix);
            codeRuleT.setExplain(explain);
            codeRuleT.setResetCycle(resetCycle);
            return service.editCodeRule(codeRuleT);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "获取下一个编号", notes = "获取下一个编号")
    @RequestMapping(value = "getNextCode", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "类型编号", required = true, paramType = "query", dataType = "String"),
    })
    @OptionalLog(module="基础建模", module2="编号规则", method="获取下一个编号")
    public Rjson getNextCode(HttpServletRequest request) {
        try {
            String code = EqualsUtil.string(request, "code", "类型编号", true);
            //id 1.工单 2.物料 3. 物料批次映射 4.物料编码映射
            String latestCode = service.getLatestCode(code);
            return Rjson.success(latestCode);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }


    @ApiOperation(value = "更新最新编号", notes = "更新最新编号")
    @RequestMapping(value = "updateLatestCode", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "lastCode", value = "最新编号", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "createCodeTime", value = "编号周期开始时间", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "codeType", value = "类型编码", required = true, paramType = "query", dataType = "String")
    })
    @OptionalLog(module="基础建模", module2="编号规则", method="更新最新编号")
    public Rjson updateLatestCode(HttpServletRequest request) {
        try {
            String lastCode = EqualsUtil.string(request, "lastCode", "最新编号", true);
            String createCodeTime = EqualsUtil.string(request, "createCodeTime", "编号周期开始时间", false);
            String codeType = EqualsUtil.string(request, "codeType", "类型编码", true);
            CMesCodeRuleT cMesCodeRuleT = new CMesCodeRuleT();
            cMesCodeRuleT.setLastCode(lastCode);
            cMesCodeRuleT.setCreateCodeTime(new Date(Long.parseLong(createCodeTime)));
            cMesCodeRuleT.setCodeType(codeType);
            Integer integer = service.updateLatestCode(cMesCodeRuleT);
            if (integer<1){
                return Rjson.error("更新最新编号失败");
            }
            return Rjson.success("更新最新编号成功");
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }
}
