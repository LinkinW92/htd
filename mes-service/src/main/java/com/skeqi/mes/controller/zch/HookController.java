package com.skeqi.mes.controller.zch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.service.zch.HookService;
import com.skeqi.mes.util.Constant;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * Hook管理
 * @author SKQ
 *
 */
@Controller
@RequestMapping(value = "job", produces = MediaType.APPLICATION_JSON)
@Api(value = "Hook管理", description = "Hook管理", produces = MediaType.APPLICATION_JSON)
public class HookController {
	@Autowired
	HookService service;

	@RequestMapping(value = "/findJobList", method = RequestMethod.POST)
	@ApiOperation(value = "查询计划管理列表", notes = "查询计划管理列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int"),
	})
	@ResponseBody
	public Rjson findJobList(HttpServletRequest request) throws ServicesException {
		List<Map<String, Object>> list = null;
		Map<String, Object> map = new HashMap<>();

		try {
			map = ToolUtils.getParameterMap(request);

			Integer pageNum = 1;
            Integer pageSize = 10;
            if (map.get("pageNum") != null) {
                pageNum = Integer.parseInt(map.get("pageNum").toString());
            }
            if (map.get("pageSize") != null) {
                pageSize = Integer.parseInt(map.get("pageSize").toString());
            }

            PageHelper.startPage(pageNum, pageSize);
			list = service.findJobList(map);
			PageInfo<Map<String, Object>> pageInfo = Rjson.getPageInfoByFormatTime(list);

			return Rjson.success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/findJobBindingList", method = RequestMethod.POST)
	@ApiOperation(value = "查询计划管理列表", notes = "查询计划管理列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = "stationId", value = "工位id", required = true, paramType = "query", dataType = "int"),
	})
	@ResponseBody
	public Rjson findJobBindingList(HttpServletRequest request) throws ServicesException {
		List<Map<String, Object>> list = null;
		Map<String, Object> map = new HashMap<>();

		try {
			map = ToolUtils.getParameterMap(request);

			if(StringUtils.isEmpty(map.get("stationId"))) {
				return Rjson.error(Constant.MISSING_PARAMETER_EXCEPTION);
			}

			Integer pageNum = 1;
            Integer pageSize = 10;
            if (map.get("pageNum") != null) {
                pageNum = Integer.parseInt(map.get("pageNum").toString());
            }
            if (map.get("pageSize") != null) {
                pageSize = Integer.parseInt(map.get("pageSize").toString());
            }

            PageHelper.startPage(pageNum, pageSize);
			list = service.findJobBindingList(map);
			PageInfo<Map<String, Object>> pageInfo = Rjson.getPageInfoByFormatTime(list);

			return Rjson.success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/addJobBinding", method = RequestMethod.POST)
	@ApiOperation(value = "绑定Job", notes = "绑定Job")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "stationId", value = "工位id", required = true, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "jobId", value = "作业Id", required = true, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "inOut", value = "进出站 1 进站 2 出站", required = true, paramType = "query", dataType = "int"),
	})
	@ResponseBody
	@OptionalLog(module = "基础模块", module2 = "工位管理", method= "绑定Job")
	public Rjson addJobBinding(HttpServletRequest request) throws ServicesException {
		Map<String, Object> map = new HashMap<>();

		try {
			map = ToolUtils.getParameterMap(request);

			if(StringUtils.isEmpty(map.get("stationId")) || StringUtils.isEmpty(map.get("jobId"))
					|| StringUtils.isEmpty(map.get("inOut"))) {
				return Rjson.error(Constant.MISSING_PARAMETER_EXCEPTION);
			}

			Integer result = service.addJobBinding(map);
			if(result == -1) {
				return Rjson.error("已添加该作业");
			}

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/editJobBindingStatus", method = RequestMethod.POST)
	@ApiOperation(value = "修改绑定状态", notes = "修改绑定状态")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "ID", value = "绑定id", required = true, paramType = "query", dataType = "int"),
		@ApiImplicitParam(name = "status", value = "状态", required = true, paramType = "query", dataType = "int"),
	})
	@ResponseBody
	@OptionalLog(module = "基础模块", module2 = "工位管理", method= "修改绑定状态")
	public Rjson editJobBindingStatus(HttpServletRequest request) throws ServicesException {
		Map<String, Object> map = new HashMap<>();

		try {
			map = ToolUtils.getParameterMap(request);

			if(StringUtils.isEmpty(map.get("ID")) || StringUtils.isEmpty(map.get("status"))) {
				return Rjson.error(Constant.MISSING_PARAMETER_EXCEPTION);
			}

			service.editJobBindingStatus(map);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}

	@RequestMapping(value = "/deleteJobBinding", method = RequestMethod.POST)
	@ApiOperation(value = "解绑Job", notes = "解绑Job")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "ID", value = "绑定id", required = true, paramType = "query", dataType = "int"),
	})
	@ResponseBody
	@OptionalLog(module = "基础模块", module2 = "工位管理", method= "解绑Job")
	public Rjson deleteJobBinding(HttpServletRequest request) throws ServicesException {
		Map<String, Object> map = new HashMap<>();

		try {
			map = ToolUtils.getParameterMap(request);

			if(StringUtils.isEmpty(map.get("ID"))) {
				return Rjson.error(Constant.MISSING_PARAMETER_EXCEPTION);
			}

			service.deleteJobBinding(map);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			return Rjson.error(Constant.INTERFACE_EXCEPTION);
		}
	}
}
