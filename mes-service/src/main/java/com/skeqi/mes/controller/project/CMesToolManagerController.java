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
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.pojo.project.CMesToolManager;
import com.skeqi.mes.service.project.CMesToolManagerService;
import com.skeqi.mes.util.ToolUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="api",produces = MediaType.APPLICATION_JSON)
@Api(value = "设备管理", description = "设备管理", produces = MediaType.APPLICATION_JSON)
public class CMesToolManagerController {

	@Autowired
	CMesToolManagerService service;

	@RequestMapping(value="findTool",method=RequestMethod.POST)
	@ApiOperation(value = "设备列表", notes = "设备列表")
    @ApiImplicitParams({
      @ApiImplicitParam(name = "name",value = "设备名称", required = false, paramType = "query"),
      @ApiImplicitParam(name = "pages",value = "当前页", required = false, paramType = "query"),
    })
	public JSONObject findTool(HttpServletRequest request, String name,Integer pages){
		JSONObject json = new JSONObject();
		try {
			List<CMesToolManager> findAllTool = service.findAllTool(name);
			if(pages==null || pages==0){
				findAllTool = service.findAllTool(name);
			}else{
				 PageHelper.startPage(pages,10);
				 findAllTool = service.findAllTool(name);
				 PageInfo<CMesToolManager> pageinfo = new PageInfo<CMesToolManager>(findAllTool,5);
				 json.put("pageNum", pageinfo.getTotal());
				 findAllTool = pageinfo.getList();
			}
			json.put("code",0);
			json.put("info", findAllTool);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code", 1);
		}
		return json;
	}


	@RequestMapping(value="addTool",method=RequestMethod.POST)
	@ApiOperation(value = "添加设备", notes = "添加设备")
    @ApiImplicitParams({
	      @ApiImplicitParam(name = "toolNo",value = "设备编号", required = true, paramType = "query"),
	      @ApiImplicitParam(name = "lineId",value = "产线id", required = true, paramType = "query"),
	      @ApiImplicitParam(name = "stationId",value = "工位Id", required = true, paramType = "query"),
	      @ApiImplicitParam(name = "toolName",value = "设备名称", required = true, paramType = "query"),
	      @ApiImplicitParam(name = "toolDis",value = "备注", required = false, paramType = "query"),
//	      @ApiImplicitParam(name = "estimateLife",value = "预计寿命", required = true, paramType = "query"),
//	      @ApiImplicitParam(name = "maintainCycle",value = "维护周期(天)", required = true, paramType = "query"),
//	      @ApiImplicitParam(name = "firstUse",value = "初次使用时间", required = true, paramType = "query"),
    })
	public JSONObject addTool(HttpServletRequest request, CMesToolManager tool){
		JSONObject json = new JSONObject();
		try {
			service.addTool(tool);
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


	@RequestMapping(value="updateTool",method=RequestMethod.POST)
	@ApiOperation(value = "修改设备", notes = "修改设备")
    @ApiImplicitParams({
    	 @ApiImplicitParam(name = "toolNo",value = "设备编号", required = true, paramType = "query"),
	      @ApiImplicitParam(name = "toolName",value = "设备名称", required = true, paramType = "query"),
	      @ApiImplicitParam(name = "toolDis",value = "备注", required = false, paramType = "query"),
//	      @ApiImplicitParam(name = "estimateLife",value = "预计寿命", required = true, paramType = "query"),
//	      @ApiImplicitParam(name = "maintainCycle",value = "维护周期(天)", required = true, paramType = "query"),
	      @ApiImplicitParam(name = "id",value = "id", required = true, paramType = "query"),
	      @ApiImplicitParam(name = "lineId",value = "产线id", required = true, paramType = "query"),
	      @ApiImplicitParam(name = "stationId",value = "工位Id", required = true, paramType = "query"),

    })
	public JSONObject updateTool(HttpServletRequest request, CMesToolManager tool){
		JSONObject json = new JSONObject();
		try {
			service.updateTool(tool);
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

	@RequestMapping(value="deleteTool",method=RequestMethod.POST)
	@ApiOperation(value = "删除设备", notes = "删除设备")
    @ApiImplicitParams({
      @ApiImplicitParam(name = "id",value = "id", required = true, paramType = "query"),
    })
	public JSONObject deleteTool(HttpServletRequest request, Integer id){
		JSONObject json = new JSONObject();
		try {
			service.delTool(id);
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

	@RequestMapping(value="maintainTool",method=RequestMethod.POST)
	@ApiOperation(value = "保养设备", notes = "保养设备")
    @ApiImplicitParams({
      @ApiImplicitParam(name = "id",value = "id", required = true, paramType = "query"),
    })
	public JSONObject maintainTool(HttpServletRequest request, Integer id){
		JSONObject json = new JSONObject();
		try {
			service.maintain(id);
			json.put("code",0);
			json.put("msg", "保养成功");
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

	@RequestMapping(value="UseTool",method=RequestMethod.POST)
	@ApiOperation(value = "设备使用", notes = "设备使用")
    @ApiImplicitParams({
      @ApiImplicitParam(name = "id",value = "ID", required = true, paramType = "query"),
      @ApiImplicitParam(name = "num",value = "使用次数", required = true, paramType = "query"),
    })
	public JSONObject UseTool(HttpServletRequest request, Integer id,Integer num){
		JSONObject json = new JSONObject();
		try {
			service.UseTool(id, num);
			json.put("code",0);
			json.put("msg", "更新成功");
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


	@RequestMapping(value="findStationByLine",method=RequestMethod.POST)
	@ApiOperation(value = "工位列表", notes = "工位列表")
    @ApiImplicitParams({
      @ApiImplicitParam(name = "lineid",value = "产线id", required = true, paramType = "query"),
    })
	public JSONObject findStationByLine(HttpServletRequest request, Integer lineid){
		JSONObject json = new JSONObject();
		try {
			List<CMesStationT> findStation = service.findStation(lineid);
			json.put("code",0);
			json.put("info", findStation);
		}catch (ServicesException e) {
			// TODO: handle exception
			json.put("code",1);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code",1);
		}
		return json;
	}
}
