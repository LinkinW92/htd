package com.skeqi.mes.controller.qh;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesShiftsTeamT;
import com.skeqi.mes.pojo.CMesUserT;
import com.skeqi.mes.pojo.PMesStationPassT;
import com.skeqi.mes.service.lcy.GetReportFormsService;
import com.skeqi.mes.util.ToolUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "api", produces = MediaType.APPLICATION_JSON)
@Api(value = "琦航报表", description = "琦航报表", produces = MediaType.APPLICATION_JSON)
public class GetReportFormsControllerl {

	@Autowired
	private GetReportFormsService getReportFormsService;





		@RequestMapping(value = "/ReportForms", method = RequestMethod.POST)
		@ApiOperation(value = "工位完成产量统计", notes = "工位完成产量统计")
		@ResponseBody
		@ApiImplicitParams({
				@ApiImplicitParam(name = "startTime", value = "开始时间", required = false, paramType = "query", dataType = "String"),
				@ApiImplicitParam(name = "endTime", value = "结束时间", required = false, paramType = "query", dataType = "String"),
				@ApiImplicitParam(name = "st", value = "工位名称", required = false, paramType = "query", dataType = "String"),
				@ApiImplicitParam(name = "lineId", value = "产线id", required = false, paramType = "query", dataType = "int")})
		public JSONObject userList(HttpServletRequest request, String startTime,String endTime,String st,Integer lineId) throws ServicesException {
		//	PageHelper.startPage(pageNum, pageSize);
         List<PMesStationPassT>productNumber=	getReportFormsService.getStationPassProductNumberL(startTime,endTime,st,lineId);
			JSONObject json = new JSONObject();

			try {
		//		PageInfo<CMesUserT> pageInfo = new PageInfo<>(lineList);
				json.put("code", 0);
				json.put("pageInfo", productNumber);
			} catch (Exception e) {
				e.printStackTrace();
				ToolUtils.errorLog(this, e, request);
				// TODO: handle exception
				json.put("code", 1);
				json.put("msg", "未知错误");
			}
			return json;
		}



		@RequestMapping(value = "/shiftNumberStatistics", method = RequestMethod.POST)
		@ApiOperation(value = "班次数量统计", notes = "班次数量统计")
		@ResponseBody
		@ApiImplicitParams({
				@ApiImplicitParam(name = "dt", value = "时间", required = false, paramType = "query", dataType = "String"),
				@ApiImplicitParam(name = "lineId", value = "产线id", required = false, paramType = "query", dataType = "int")})
		public JSONObject shiftNumberStatistics(HttpServletRequest request, String dt,Integer lineId) throws ServicesException {
		//	PageHelper.startPage(pageNum, pageSize);
             List<CMesShiftsTeamT>productNumber=	getReportFormsService.shiftsTeamList(lineId,dt);
			JSONObject json = new JSONObject();
			try {
		//		PageInfo<CMesUserT> pageInfo = new PageInfo<>(lineList);
				json.put("code", 0);
				json.put("pageInfo", productNumber);
			} catch (Exception e) {
				e.printStackTrace();
				ToolUtils.errorLog(this, e, request);
				// TODO: handle exception
				json.put("code", 1);
				json.put("msg", "未知错误");
			}
			return json;
		}
}
