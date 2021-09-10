package com.skeqi.mes.controller.wf.jobManagement;

import com.skeqi.mes.pojo.wf.jobManagement.MesJobConfigurationRuleT;
import com.skeqi.mes.service.wf.jobManagement.MesJobConfigurationRuleTService;
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
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * 作业作业管理
 * @author Lenovo
 */
@RestController
@RequestMapping(value = "jobManagement/jobRule", produces = MediaType.APPLICATION_JSON)
@Api(value = "规则配置", description = "规则配置", produces = MediaType.APPLICATION_JSON)
public class JobRuleController {
    @Resource
    private MesJobConfigurationRuleTService jobConfigurationRuleTService;

    @ApiOperation(value = "查询配置", notes = "查询配置")
    @RequestMapping(value = "findList", method = RequestMethod.POST)
    @OptionalLog(module="作业管理", module2="规则配置", method="查询配置")
    public Rjson findList (HttpServletRequest request){
        try {
            List<MesJobConfigurationRuleT> requestTS = jobConfigurationRuleTService.selectAll();
            return Rjson.success(requestTS);
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "新增配置", notes = "新增配置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "名称", required = true, paramType = "query"),
            @ApiImplicitParam(name = "code", value = "编码", required = true, paramType = "query"),
            @ApiImplicitParam(name = "value", value = "值", required = true, paramType = "query"),
            @ApiImplicitParam(name = "jobCode", value = "作业配置编码", required = true, paramType = "query")
    })
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    @OptionalLog(module="作业管理", module2="规则配置", method="新增配置")
    public Rjson insert (HttpServletRequest request){
        try {
            String name = EqualsUtil.string(request,"name","名称",true);
            String code = EqualsUtil.string(request,"code","编码",true);
            String value = EqualsUtil.string(request,"value","值",true);
            String jobCode = EqualsUtil.string(request,"jobCode","作业配置编码",true);
            MesJobConfigurationRuleT jobConfigurationRuleT = new MesJobConfigurationRuleT();
            jobConfigurationRuleT.setName(name);
            jobConfigurationRuleT.setCode(code);
            jobConfigurationRuleT.setValue(value);
            jobConfigurationRuleT.setJobCode(jobCode);
            Integer i = jobConfigurationRuleTService.insertSelective(jobConfigurationRuleT);
            if (i<1){
                return Rjson.error("新增失败");
            }
            return Rjson.success("新增成功");
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "编辑配置", notes = "编辑配置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "名称", required = true, paramType = "query"),
            @ApiImplicitParam(name = "code", value = "编码", required = true, paramType = "query"),
            @ApiImplicitParam(name = "value", value = "值", required = true, paramType = "query"),
            @ApiImplicitParam(name = "jobCode", value = "作业配置编码", required = true, paramType = "query")
    })
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @OptionalLog(module="作业管理", module2="规则配置", method="编辑配置")
    public Rjson update (HttpServletRequest request){
        try {
            String name = EqualsUtil.string(request,"name","名称",true);
            String code = EqualsUtil.string(request,"code","编码",true);
            String value = EqualsUtil.string(request,"value","值",true);
            String jobCode = EqualsUtil.string(request,"jobCode","作业配置编码",true);
            MesJobConfigurationRuleT jobConfigurationRuleT = new MesJobConfigurationRuleT();
            jobConfigurationRuleT.setName(name);
            jobConfigurationRuleT.setCode(code);
            jobConfigurationRuleT.setValue(value);
            jobConfigurationRuleT.setJobCode(jobCode);
            Integer i = jobConfigurationRuleTService.updateByPrimaryKeySelective(jobConfigurationRuleT);
            if (i<1){
                return Rjson.error("编辑失败");
            }
            return Rjson.success("编辑成功");
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }

    @ApiOperation(value = "删除配置", notes = "删除配置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "编号", required = true, paramType = "query")
    })
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @OptionalLog(module="作业管理", module2="规则配置", method="删除配置")
    public Rjson delete (HttpServletRequest request){
        try {
            Integer id = EqualsUtil.integer(request,"id","编号",true);
            Integer i = jobConfigurationRuleTService.deleteByPrimaryKey(id);
            if (i<1){
                return Rjson.error("删除失败");
            }
            return Rjson.success("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            ToolUtils.errorLog(this, e, request);
            return Rjson.error(e.getMessage());
        }
    }
}
