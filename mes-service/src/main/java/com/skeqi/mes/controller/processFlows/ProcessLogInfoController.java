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

import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.service.processFlows.ProcessLogInfoService;
import com.skeqi.mes.util.Constant;
import com.skeqi.mes.util.Rjson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "process", produces = MediaType.APPLICATION_JSON)
@Api(value = "打印日志", description = "打印日志", produces = MediaType.APPLICATION_JSON)
public class ProcessLogInfoController {

	@Autowired
	private ProcessLogInfoService service;

	@RequestMapping(value = "/showAllProcessLogInfo", method = RequestMethod.POST)
	@ApiOperation(value = "根据指定生产任务查询工序", notes = "根据指定生产任务查询工序")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "生产任务ID", required = false, paramType = "query", dataType = "string"),

	})
	@ResponseBody
	public Rjson showAllProcessLogInfo(HttpServletRequest request) throws ServicesException {
		List<Map<String, Object>> list = null;
		try {
//			Integer taskId = Integer.parseInt(request.getParameter("taskId"));
			Integer id = Integer.parseInt(request.getParameter("id"));
			String user = request.getParameter("user");
			String beginTime = request.getParameter("beginTime");
			String endTime = request.getParameter("endTime");

			list = service.showAllProcessLogInfo(id);
			return Rjson.success(list);
			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}
}
