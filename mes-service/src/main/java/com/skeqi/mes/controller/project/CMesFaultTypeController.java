package com.skeqi.mes.controller.project;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.project.CMesFaultTypeT;
import com.skeqi.mes.service.project.CMesFaultTypeService;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="api",produces = MediaType.APPLICATION_JSON)
@Api(value = "故障类型管理", description = "故障类型管理", produces = MediaType.APPLICATION_JSON)
public class CMesFaultTypeController {

	@Autowired
	CMesFaultTypeService service;


	@RequestMapping(value="findFaultType",method=RequestMethod.POST)
	@ApiOperation(value = "故障类型列表", notes = "故障类型列表")
    @ApiImplicitParams({
      @ApiImplicitParam(name = "name",value = "故障名称", required = false, paramType = "query"),
      @ApiImplicitParam(name = "pages",value = "当前页", required = false, paramType = "query"),
    })
	public JSONObject findFaultType(HttpServletRequest request, String name,Integer pages){
		JSONObject json = new JSONObject();
		try {
			List<CMesFaultTypeT> findAllFault = null;
			if(pages==null || pages==0){
				findAllFault = service.findAllFault(name);
			}else{
				PageHelper.startPage(pages,10);
				 findAllFault = service.findAllFault(name);
				 PageInfo<CMesFaultTypeT> pageinfo = new PageInfo<CMesFaultTypeT>(findAllFault,5);
				 json.put("pageNum",pageinfo.getTotal());
				 findAllFault = pageinfo.getList();
			}
			json.put("code",0);
			json.put("info", findAllFault);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code", 1);
		}
		return json;
	}


	@RequestMapping(value="addFaultType",method=RequestMethod.POST)
	@ApiOperation(value = "添加故障类型", notes = "添加故障类型")
    @ApiImplicitParams({
      @ApiImplicitParam(name = "name",value = "故障名称", required = true, paramType = "query"),
      @ApiImplicitParam(name = "note",value = "备注", required = false, paramType = "query"),
    })
	@OptionalLog(module="生产", module2="故障类型", method="添加故障类型")
	public JSONObject addFaultType(HttpServletRequest request, String name,String note){
		JSONObject json = new JSONObject();
		try {
			service.addFault(name, note);
			json.put("code",0);
			json.put("msg", "添加成功");
		}catch (ServicesException e) {
			// TODO: handle exception
			json.put("code",1);
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code",1);
			json.put("msg", "未知错误");
		}
		return json;
	}


	@RequestMapping(value="updateFaultType",method=RequestMethod.POST)
	@ApiOperation(value = "修改故障类型", notes = "修改故障类型")
    @ApiImplicitParams({
      @ApiImplicitParam(name = "name",value = "故障名称", required = true, paramType = "query"),
      @ApiImplicitParam(name = "note",value = "备注", required = false, paramType = "query"),
      @ApiImplicitParam(name = "id",value = "id", required = true, paramType = "query"),
    })
	@OptionalLog(module="生产", module2="故障类型", method="修改故障类型")
	public JSONObject updateFaultType(HttpServletRequest request, String name,String note,Integer id){
		JSONObject json = new JSONObject();
		try {
			service.updateFault(name, note, id);
			json.put("code",0);
			json.put("msg", "修改成功");
		}catch (ServicesException e) {
			// TODO: handle exception
			json.put("code",1);
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code",1);
			json.put("msg", "未知错误");
		}
		return json;
	}

	@RequestMapping(value="deleteFaultType",method=RequestMethod.POST)
	@ApiOperation(value = "删除故障类型", notes = "删除故障类型")
    @ApiImplicitParams({
      @ApiImplicitParam(name = "id",value = "id", required = true, paramType = "query"),
    })
	@OptionalLog(module="生产", module2="故障类型", method="删除故障类型")
	public JSONObject deleteFaultType(HttpServletRequest request, Integer id){
		JSONObject json = new JSONObject();
		try {
			service.delFault(id);
			json.put("code",0);
			json.put("msg", "删除成功");
		}catch (ServicesException e) {
			// TODO: handle exception
			json.put("code",1);
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code",1);
			json.put("msg", "未知错误");
		}
		return json;
	}

}
