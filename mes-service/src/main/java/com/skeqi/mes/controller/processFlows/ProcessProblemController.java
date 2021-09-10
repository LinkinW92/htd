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

import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.service.processFlows.ProcessLogInfoService;
import com.skeqi.mes.service.processFlows.ProcessProblemService;
import com.skeqi.mes.util.Constant;
import com.skeqi.mes.util.Rjson;

import io.swagger.annotations.Api;

@Controller
@RequestMapping(value = "process", produces = MediaType.APPLICATION_JSON)
@Api(value = "问题提交", description = "问题提交", produces = MediaType.APPLICATION_JSON)
public class ProcessProblemController {

	@Autowired
	private ProcessProblemService service;
	@RequestMapping(value = "/showProblem", method = RequestMethod.POST)
	@ResponseBody
	public Rjson showProblemList(HttpServletRequest request) throws ServicesException {

		try {
//
			List<Map<String, Object>> list = null;
			Integer pageNum = null;
			Integer pageSize = null;
			if(request.getParameter("pageNum") != null){
				pageNum = Integer.parseInt(request.getParameter("pageNum"));
			}
			if(request.getParameter("pageSize") != null){
				pageSize = Integer.parseInt(request.getParameter("pageSize"));
			}
			list = service.showProblem();
			PageInfo<Map<String, Object>> pageInfo=Rjson.getPageInfoByFormatTime(list);
			return Rjson.success(pageInfo);

			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}


	@RequestMapping(value = "/addProblem", method = RequestMethod.POST)
	@ResponseBody
	public Rjson addProblemList(HttpServletRequest request) throws ServicesException {
		List<Map<String, Object>> list = null;
		try {
//
		String department  = request.getParameter("department");
		String describe = request.getParameter("problem");
		service.addProblem(department, describe);
		return Rjson.success();

			// }}
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

}
