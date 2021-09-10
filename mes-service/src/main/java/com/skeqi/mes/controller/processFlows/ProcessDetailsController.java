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
import com.skeqi.mes.service.processFlows.ProcessClientService;
import com.skeqi.mes.service.processFlows.ProcessDetailsService;
import com.skeqi.mes.util.Constant;
import com.skeqi.mes.util.Rjson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "process", produces = MediaType.APPLICATION_JSON)
@Api(value = "工序详情", description = "工序详情", produces = MediaType.APPLICATION_JSON)
public class ProcessDetailsController {

	@Autowired
	private ProcessDetailsService service;
	@Autowired
	private ProcessClientService service2;

	@RequestMapping(value = "/showProcessDetails", method = RequestMethod.POST)
	@ApiOperation(value = "查询工序详情", notes = "查询工序详情")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "taskId", value = "生产任务ID", required = false, paramType = "query", dataType = "int"),
	})
	@ResponseBody
	public Rjson showProcessDetails(HttpServletRequest request) throws ServicesException {
		List<Map<String, Object>> list = null;
		try {
			Integer taskId = Integer.parseInt(request.getParameter("taskId"));
			list = service.showProcessDetails(taskId);
			for(Map map : list){
				String processId = map.get("PROCESS_ID").toString();
				String TASK_ID = map.get("TASK_ID").toString();
				List<Map<String,Object>> list2 =service2.showAllOutsourceData(TASK_ID, processId);
				map.put("outSourceTable", list2);
				if(list2.size()==0){
				map.put("showFlags", 0);
				}else{
				map.put("showFlags", 1);
				}
			}
			return Rjson.success(list);
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/showAllProcessLogInfoxs", method = RequestMethod.POST)
	@ApiOperation(value = "查询工序日志详情", notes = "查询工序日志详情")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "taskId", value = "生产任务ID", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "processId", value = "工序ID", required = false, paramType = "query", dataType = "string"),
	})
	@ResponseBody
	public Rjson showAllProcessLogInfo(HttpServletRequest request) throws ServicesException {
		List<Map<String, Object>> list = null;
		try {
			String taskId =request.getParameter("taskId");
			String processId =request.getParameter("processId");

			list = service.showAllProcessLogInfo(taskId, processId);
			return Rjson.success(list);
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}


	@RequestMapping(value = "/showQualityLog", method = RequestMethod.POST)
	@ApiOperation(value = "查询品检日志详情", notes = "查询品检日志详情")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "logType", value = "生产任务ID", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int"),
	})
	@ResponseBody
	public Rjson showQualityLog(HttpServletRequest request) throws ServicesException {
		List<Map<String, Object>> list = null;
		try {
			String logType =request.getParameter("logType");
			String user =request.getParameter("user");
			String beginTime =request.getParameter("beginTime");
			String endTime =request.getParameter("endTime");
			String specificationModel =request.getParameter("specificationModel");
			Integer pageNum = null;
			Integer pageSize = null;
			if (request.getParameter("pageNum") != null) {
				pageNum = Integer.parseInt(request.getParameter("pageNum"));
			}
			if (request.getParameter("pageSize") != null) {
				pageSize = Integer.parseInt(request.getParameter("pageSize"));
			}
			PageHelper.startPage(pageNum, pageSize);
			// list = service.showDepartmentInfo(customerId);
			list = service.showQualityLog(logType, user, beginTime, endTime,specificationModel);
			PageInfo<Map<String, Object>> pageInfo = Rjson.getPageInfoByFormatTime(list);
			return Rjson.success(pageInfo);
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/showAllProcessLogByTypeIds", method = RequestMethod.POST)
	@ApiOperation(value = "查询特定工序日志详情", notes = "查询特定工序日志详情")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "typeId", value = "生产任务ID", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int"),

	})
	@ResponseBody
	public Rjson showAllProcessLogByTypeIds(HttpServletRequest request) throws ServicesException {
		List<Map<String, Object>> list = null;
		try {
			String typeId =request.getParameter("typeId");

			Integer pageNum = null;
			Integer pageSize = null;
			if (request.getParameter("pageNum") != null) {
				pageNum = Integer.parseInt(request.getParameter("pageNum"));
			}
			if (request.getParameter("pageSize") != null) {
				pageSize = Integer.parseInt(request.getParameter("pageSize"));
			}
			PageHelper.startPage(pageNum, pageSize);
			// list = service.showDepartmentInfo(customerId);
			list = service.showAllProcessLogByTypeIds(typeId);
			PageInfo<Map<String, Object>> pageInfo = Rjson.getPageInfoByFormatTime(list);
			return Rjson.success(pageInfo);


			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}


	@RequestMapping(value = "/showAllProcessLogByTypeIdsx", method = RequestMethod.POST)
	@ApiOperation(value = "查询特定工序日志详情", notes = "查询特定工序日志详情")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "typeId", value = "生产任务ID", required = false, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int"),

	})
	@ResponseBody
	public Rjson showAllProcessLogByTypeIdsx(HttpServletRequest request) throws ServicesException {
		List<Map<String, Object>> list = null;
		try {
			String typeId =request.getParameter("typeId");

			Integer pageNum = null;
			Integer pageSize = null;
			if (request.getParameter("pageNum") != null) {
				pageNum = Integer.parseInt(request.getParameter("pageNum"));
			}
			if (request.getParameter("pageSize") != null) {
				pageSize = Integer.parseInt(request.getParameter("pageSize"));
			}
			PageHelper.startPage(pageNum, pageSize);
			// list = service.showDepartmentInfo(customerId);
			list = service.showAllProcessLogByTypeIdsx(typeId);
			PageInfo<Map<String, Object>> pageInfo = Rjson.getPageInfoByFormatTime(list);
			return Rjson.success(pageInfo);


			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/showTaskStatus", method = RequestMethod.POST)
	@ApiOperation(value = "查询委外任务状态", notes = "查询委外任务状态")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "taskId", value = "生产任务ID", required = false, paramType = "query", dataType = "string"),


	})
	@ResponseBody
	public Rjson showTaskStatus(HttpServletRequest request) throws ServicesException {
		List<Map<String, Object>> list = null;
		try {
			String taskId =request.getParameter("taskId");



			// list = service.showDepartmentInfo(customerId);
			Integer tatus = service.showTaskStatusDataw(taskId);

			return Rjson.success(tatus);


			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

}
