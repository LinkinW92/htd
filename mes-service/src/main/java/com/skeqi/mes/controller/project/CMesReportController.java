package com.skeqi.mes.controller.project;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.service.project.CMesReportService;
import com.skeqi.mes.util.ToolUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="api",produces = MediaType.APPLICATION_JSON)
@Api(value = "报表", description = "报表", produces = MediaType.APPLICATION_JSON)
public class CMesReportController {


	@Autowired
	CMesReportService service;


	@RequestMapping(value="Reportyield",method=RequestMethod.POST)
	@ApiOperation(value = "产量报表", notes = "产量报表")
    @ApiImplicitParams({
      @ApiImplicitParam(name = "lineName",value = "产线Name", required = false, paramType = "query"),
      @ApiImplicitParam(name = "date",value = "日期", required = false, paramType = "query"),
    })
	public JSONObject Reportyield(HttpServletRequest request, String lineName,String date){
		JSONObject json = new JSONObject();
		try {
			Map<Integer, Integer> reportyield = service.Reportyield(date, lineName);
			Map<Integer, Integer>  reportyieldTwo = service.ReportyieldTwo(date, lineName);
			json.put("code",0);
			json.put("day", reportyield);
			json.put("hour", reportyieldTwo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			json.put("code", 1);
		}
		return json;
	}


	@RequestMapping(value="ReporUse",method=RequestMethod.POST)
	@ApiOperation(value = "设备使用率", notes = "设备使用率")
    @ApiImplicitParams({
      @ApiImplicitParam(name = "lineName",value = "产线Name", required = false, paramType = "query"),
      @ApiImplicitParam(name = "date",value = "日期", required = false, paramType = "query"),
    })
	public JSONObject ReporUse(HttpServletRequest request, String lineName,String date){
		JSONObject json = new JSONObject();
		try {
			Integer findYield = service.findYield(lineName);
			Map<Integer, Integer> reportyieldTwo = service.ReportyieldTwo(date, lineName);
			json.put("code",0);
			json.put("hour", reportyieldTwo);
			json.put("num", findYield);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			json.put("code", 1);
		}
		return json;
	}

	@RequestMapping(value="DeviceOEE",method=RequestMethod.POST)
	@ApiOperation(value = "设备OEE", notes = "设备OEE")
    @ApiImplicitParams({
      @ApiImplicitParam(name = "lineId",value = "产线Id", required = true, paramType = "query"),
      @ApiImplicitParam(name = "startTime",value = "起始时间", required = true, paramType = "query"),
      @ApiImplicitParam(name = "endTime",value = "结束时间", required = true, paramType = "query"),
    })
	public JSONObject DeviceOEE(HttpServletRequest request, Integer lineId,String startTime,String endTime){
		JSONObject json = new JSONObject();
		try {
			JSONArray findOEE = service.findOEE(lineId, startTime, endTime);
			json.put("code",0);
			json.put("info", findOEE);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			json.put("code", 1);
		}
		return json;
	}


	@RequestMapping(value="ProductionNumberByLine",method=RequestMethod.POST)
	@ApiOperation(value = "产量统计", notes = "产线生产数量")
    @ApiImplicitParams({
      @ApiImplicitParam(name = "startTime",value = "起始时间", required = false, paramType = "query"),
      @ApiImplicitParam(name = "endTime",value = "结束时间", required = false, paramType = "query"),
    })
	public JSONObject ProductionNumberByLine(HttpServletRequest request, String startTime,String endTime){
		JSONObject json = new JSONObject();
	//	System.err.println("==="+startTime+"==="+endTime);
		try {
			List<Map<String, Object>> pro = service.ProductionNumberByLine(startTime, endTime);
			json.put("code",0);
			json.put("info", pro);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			json.put("code", 1);
			json.put("code", "未知错误");
		}
		return json;
	}
	@RequestMapping(value="/ProNumByLineName",method=RequestMethod.POST)
	@ApiOperation(value = "产线上产品生产数量", notes = "产线上产品生产数量")
    @ApiImplicitParams({
      @ApiImplicitParam(name = "lineName",value = "产线名称", required = false, paramType = "query"),
    })
	public JSONObject ProNumByLineName(HttpServletRequest request, String lineName){
		JSONObject json = new JSONObject();
	List<Map<String,Object>>	 ProNumByLineName=service.ProNumByLineName(lineName);
		try {
			json.put("code",0);
			json.put("info", ProNumByLineName);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			json.put("code", 1);
		}
		return json;
	}
}
