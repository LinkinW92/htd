package com.skeqi.mes.controller.processFlows;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.common.crm.ProcessLogModel;
import com.skeqi.mes.common.crm.ShowIPInfo;
import com.skeqi.mes.service.processFlows.ProcessLogInfoService;
import com.skeqi.mes.service.processFlows.ProcessManagerService;
import com.skeqi.mes.util.Constant;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "process", produces = MediaType.APPLICATION_JSON)
@Api(value = "工序管理", description = "工序管理", produces = MediaType.APPLICATION_JSON)
public class ProcessManagerController {
	@Autowired
	private ProcessManagerService service;
	@Autowired
	private ProcessLogInfoService logInfos;

	@RequestMapping(value = "/showProcess", method = RequestMethod.POST)
	@ApiOperation(value = "查询工序信息", notes = "查询工序信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int"), })
	@ResponseBody
	public Rjson showProcess(HttpServletRequest request) throws ServicesException {
		List<Map<String, Object>> list = null;
		Integer pageNum = null;
		Integer pageSize = null;
		if (request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		if (request.getParameter("pageSize") != null) {
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}
		try {
			// if(pageNum!=null&&pageSize!=null){
			// PageHelper.startPage(pageNum, pageSize);
			//// list = service.showDepartmentInfo(customerId);
			// list = service.showProcess();
			// PageInfo<Map<String, Object>>
			// pageInfo=Rjson.getPageInfoByFormatTime(list);
			// return Rjson.success(pageInfo);
			// }else if(pageNum==null&&pageSize==null){
			// list = service.showProcess();
			// return Rjson.success(list);
			// }else{
			PageHelper.startPage(pageNum, pageSize);
			// list = service.showDepartmentInfo(customerId);
			list = service.showProcess();
			PageInfo<Map<String, Object>> pageInfo = Rjson.getPageInfoByFormatTime(list);
			return Rjson.success(pageInfo);
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/addProcess", method = RequestMethod.POST)
	@ApiOperation(value = "新增工序", notes = "新增工序")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "processName", value = "工序名称", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "processType", value = "工序类型", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "unitPrice", value = "小时单价", required = false, paramType = "query", dataType = "string"),
		 })
	@ResponseBody
	public Rjson addProcess(HttpServletRequest request) throws ServicesException{

		String processName=request.getParameter("processName");
		String processType=request.getParameter("processType");
		String unitPrice=request.getParameter("unitPrice");
		if(service.countProcessByName(processName,"")>0){
			return Rjson.error(202, "类型名称已经存在");
		}

		try {
		  service.addProcess(processName,processType,unitPrice);
		  String stationId = service.showProcessById();
//			打印日志

			//获取客户端IP地址
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
//			获取用户名
			String userName = ToolUtils.getCookieUserName(request);
//			获取任务单信息


		  ProcessLogModel model = new ProcessLogModel();
//		  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请",String.valueOf(mapx.get("STATION_NUM")));
//		 model.addRouteInfo(userName, ipInfo , String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName ,String.valueOf(mapx.get("STATION_NUM")),"删除子分支");
		 String logInfo = model.addProcessManageLog(userName, ipInfo, "新增工序操作",processName);
		  System.out.println("log:"+logInfo);
		  logInfos.addProcessLogInfo("-2",stationId, logInfo, "新增工序操作");
		return Rjson.success();
		}
		catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}


	@RequestMapping(value = "/updateProcess", method = RequestMethod.POST)
	@ApiOperation(value = "编辑工序", notes = "编辑工序")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "工序ID", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "processName", value = "工序名称", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "processType", value = "工序类型", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "unitPrice", value = "小时单价", required = false, paramType = "query", dataType = "string"),
		 })
	@ResponseBody
	public Rjson updateProcess(HttpServletRequest request) throws ServicesException{
		String id =request.getParameter("id");
		String processName=request.getParameter("processName");
		String processType=request.getParameter("processType");
		String unitPrice=request.getParameter("unitPrice");
		if(service.countProcessByName(processName,id)>0){
			return Rjson.error(202, "工序名称已经存在，不能重复添加");
		}

		try {
//			工序4.0
//			if(service.showProductTaskByProcessId(Integer.parseInt(id))>0){
//				return Rjson.error(202, "存在绑定的生产任务已经生产，不能改变工序信息");
//			}
		 service.updateProcess(processName, id,processType,unitPrice);
//			打印日志

			//获取客户端IP地址
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
//			获取用户名
			String userName = ToolUtils.getCookieUserName(request);
//			获取任务单信息


		  ProcessLogModel model = new ProcessLogModel();
//		  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请",String.valueOf(mapx.get("STATION_NUM")));
//		 model.addRouteInfo(userName, ipInfo , String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName ,String.valueOf(mapx.get("STATION_NUM")),"删除子分支");
		 String logInfo = model.addProcessManageLog(userName, ipInfo, "编辑工序操作",processName);
		  System.out.println("log:"+logInfo);
		  logInfos.addProcessLogInfo("-2",id, logInfo, "编辑工序操作");
		return Rjson.success();}
		catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}


	@RequestMapping(value = "/delProcess", method = RequestMethod.POST)
	@ApiOperation(value = "删除工序", notes = "删除工序")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "工序ID", required = false, paramType = "query", dataType = "int"),
		 })
	@ResponseBody
	public Rjson delProcess(HttpServletRequest request) throws ServicesException{
		Integer id = Integer.parseInt(request.getParameter("id"));

		if(service.countRoutes(id)>0){
			return Rjson.error(202, "该工序存在工艺路线，请先删除关联工艺路线");
		}

		try {
			String stationName = service.showStationNameById(String.valueOf(id));
		  service.delProcess(id);
//			打印日志

			//获取客户端IP地址
			ShowIPInfo ip = new ShowIPInfo();
			String ipInfo = ip.getIpAddr(request);
//			获取用户名
			String userName = ToolUtils.getCookieUserName(request);
//			获取任务单信息


		  ProcessLogModel model = new ProcessLogModel();
//		  String logInfo = model.qualityLogInfo(userName, ipInfo, String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName, String.valueOf(okNum), String.valueOf(NGNum), allRemarks, allQualityPerson,"申请",String.valueOf(mapx.get("STATION_NUM")));
//		 model.addRouteInfo(userName, ipInfo , String.valueOf(mapx.get("PROJECT_NAME")), String.valueOf(mapx.get("PROJECT_NUM")),String.valueOf(mapx.get("SPECIFICATION_AND_MODEL")), stationName ,String.valueOf(mapx.get("STATION_NUM")),"删除子分支");
		 String logInfo = model.addProcessManageLog(userName, ipInfo, "删除工序操作",stationName);
		  System.out.println("log:"+logInfo);
		  logInfos.addProcessLogInfo("-2",String.valueOf(id), logInfo, "删除工序操作");
		return Rjson.success();}
		catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

}
