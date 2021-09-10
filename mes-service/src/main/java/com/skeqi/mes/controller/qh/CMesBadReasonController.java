package com.skeqi.mes.controller.qh;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.qh.CMesBadReasonT;
import com.skeqi.mes.service.qh.CMesBadReasonService;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.neo4j.cypher.internal.compiler.v2_2.functions.E;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * 不良原因
 * @ 创建人 FQZ
 * @ 创建时间   2021/2/19 14:02
 */
@Controller
@RequestMapping(value = "api", produces = MediaType.APPLICATION_JSON)
@Api(value = "不良原因管理", description = "不良原因管理", produces = MediaType.APPLICATION_JSON)
public class CMesBadReasonController {

    @Autowired
    CMesBadReasonService service;

    @RequestMapping(value = "/barReason/findAll",method = RequestMethod.POST)
    @ApiOperation(value = "查询所有不良原因",notes = "可根据名称查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "badReason", value = "不良原因", required = false, paramType = "query", dataType = "string")
    })
    @ResponseBody
    public JSONObject findAll(HttpServletRequest request, @RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "6") Integer pageSize,String badReason){
        JSONObject json = new JSONObject();
        try {
            CMesBadReasonT bad = new CMesBadReasonT(null,badReason,null,null);
            List<CMesBadReasonT> allBadReason = service.findAllBadReason(bad);
            PageInfo<CMesBadReasonT> pageinfo = new PageInfo<>(allBadReason,5);
            json.put("code",0);
            json.put("result",pageinfo);
            json.put("msg","返回成功");
            return json;
        }catch (Exception e){
        	e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
            json.put("code",1);
            json.put("msg","查询失败");
            return json;
        }
    }

    @RequestMapping(value = "/badReason/addBadReason",method = RequestMethod.POST)
    @ApiOperation(value = "添加不良原因",notes = "添加不良原因")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "badReason", value = "不良原因", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "badCode", value = "不良代码", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "badDesc", value = "不良描述", required = false, paramType = "query", dataType = "string")
    })
    @ResponseBody
	@OptionalLog(module="质量", module2="不良原因", method="新增不良原因")
    public JSONObject addBadReason(HttpServletRequest request, String badReason,String badCode,String badDesc){
        JSONObject json = new JSONObject();
        try {
            CMesBadReasonT bad = new CMesBadReasonT(null,badReason,badDesc,badCode);
            service.addBadReason(bad);
            json.put("code",0);
            json.put("msg","添加成功");
            return json;
        }catch (Exception e){
        	e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
            json.put("code",1);
            json.put("msg","添加失败");
            return json;
        }
    }

    @RequestMapping(value = "/badReason/updateBadReason",method = RequestMethod.POST)
    @ApiOperation(value = "修改不良原因",notes = "修改不良原因")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "badReason", value = "不良原因", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "badCode", value = "不良代码", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "badDesc", value = "不良描述", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query", dataType = "integer"),
    })
    @ResponseBody
	@OptionalLog(module="质量", module2="不良原因", method="编辑不良原因")
    public JSONObject updateBadReason(HttpServletRequest request, Integer id,String badReason,String badCode,String badDesc){
        JSONObject  json = new JSONObject();
        try {
            CMesBadReasonT bad = new CMesBadReasonT(id,badReason,badDesc,badCode);
            service.updateBadReason(bad);
            json.put("code",0);
            json.put("msg","修改成功");
            return json;
        }catch (Exception e){
        	e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
            json.put("code",1);
            json.put("msg","修改失败");
            return json;
        }
    }

    @RequestMapping(value = "/badReason/delBadReason",method = RequestMethod.DELETE)
    @ApiOperation(value = "删除不良原因",notes = "删除不良原因")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query", dataType = "integer"),
    })
    @ResponseBody
	@OptionalLog(module="质量", module2="不良原因", method="删除不良原因")
    public JSONObject delBadReason(HttpServletRequest request, Integer id){
        JSONObject  json = new JSONObject();
        try {
            service.delBadReason(id);
            json.put("code",0);
            json.put("msg","删除成功");
            return json;
        }catch (Exception e){
        	e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
            json.put("code",1);
            json.put("msg","删除失败");
            return json;
        }
    }
}
