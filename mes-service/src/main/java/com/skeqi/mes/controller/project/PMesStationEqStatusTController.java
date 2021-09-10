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
import com.skeqi.mes.pojo.project.PMesStationEqStatusT;
import com.skeqi.mes.service.project.PMesStationEqStatusService;
import com.skeqi.mes.util.ToolUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="api",produces = MediaType.APPLICATION_JSON)
@Api(value = "设备状态统计", description = "设备状态统计", produces = MediaType.APPLICATION_JSON)
public class PMesStationEqStatusTController {

	@Autowired
	PMesStationEqStatusService service;

	@RequestMapping(value="findEqName",method=RequestMethod.POST)
	@ApiOperation(value = "设备状态列表", notes = "设备状态列表")
    @ApiImplicitParams({
      @ApiImplicitParam(name = "name",value = "名称", required = false, paramType = "query"),
      @ApiImplicitParam(name = "pages",value = "当前页", required = false, paramType = "query"),
    })
	public JSONObject findEqName(HttpServletRequest request, String name,Integer pages){
		JSONObject json = new JSONObject();
		try {
			 List<PMesStationEqStatusT> findAllEq = service.findAllEq(name);
			 if(pages==null || pages==0){
				 findAllEq = service.findAllEq(name);
				}else{
					 PageHelper.startPage(pages,10);
					 findAllEq = service.findAllEq(name);
					 PageInfo<PMesStationEqStatusT> pageinfo = new PageInfo<PMesStationEqStatusT>(findAllEq,5);
					 json.put("pageNum", pageinfo.getTotal());
					 findAllEq = pageinfo.getList();
				}
			json.put("code",0);
			json.put("info", findAllEq);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code", 1);
		}
		return json;
	}


	@RequestMapping(value="addEqName",method=RequestMethod.POST)
	@ApiOperation(value = "添加设备状态", notes = "添加设备状态")
    @ApiImplicitParams({
      @ApiImplicitParam(name = "st",value = "工位", required = true, paramType = "query"),
      @ApiImplicitParam(name = "eqName",value = "设备名称", required = true, paramType = "query"),
      @ApiImplicitParam(name = "eqStatusType",value = "设备状态类型", required = true, paramType = "query"),
      @ApiImplicitParam(name = "resion",value = "原因", required = false, paramType = "query"),
      @ApiImplicitParam(name = "lineId",value = "产线id", required = true, paramType = "query"),
    })
	public JSONObject addEqName(HttpServletRequest request, PMesStationEqStatusT eq){
		JSONObject json = new JSONObject();
		try {
			service.addEq(eq);
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


	@RequestMapping(value="updateEqName",method=RequestMethod.POST)
	@ApiOperation(value = "修改设备状态", notes = "修改设备状态")
    @ApiImplicitParams({
	    @ApiImplicitParam(name = "id",value = "id", required = true, paramType = "query"),
	    @ApiImplicitParam(name = "st",value = "工位", required = true, paramType = "query"),
	      @ApiImplicitParam(name = "eqName",value = "设备名称", required = true, paramType = "query"),
	      @ApiImplicitParam(name = "eqStatusType",value = "设备状态类型", required = true, paramType = "query"),
	      @ApiImplicitParam(name = "resion",value = "原因", required = false, paramType = "query"),
	      @ApiImplicitParam(name = "lineId",value = "产线id", required = true, paramType = "query"),
    })
	public JSONObject updateEqName(HttpServletRequest request, PMesStationEqStatusT eq){
		JSONObject json = new JSONObject();
		try {
			service.udpateEq(eq);
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

	@RequestMapping(value="deleteEqName",method=RequestMethod.POST)
	@ApiOperation(value = "删除设备状态", notes = "删除设备状态")
    @ApiImplicitParams({
      @ApiImplicitParam(name = "id",value = "id", required = true, paramType = "query"),
    })
	public JSONObject deleteEqName(HttpServletRequest request, Integer id){
		JSONObject json = new JSONObject();
		try {
			service.delEq(id);
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
