package com.skeqi.mes.controller.qh;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesPlanTypeT;
import com.skeqi.mes.service.qh.CMesPlanTypeService;
import com.skeqi.mes.util.ToolUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/***
 *
 * 计划类型管理
 *
 */
@Controller
@RequestMapping(value = "api", produces = MediaType.APPLICATION_JSON)
@Api(value = "计划类型管理", description = "计划类型管理", produces = MediaType.APPLICATION_JSON)
public class CMesPlanTypeController {

	@Autowired
	CMesPlanTypeService planTypeService;

	/**
	 *
	 * 计划类型管理
	 */
	@RequestMapping(value = "/planType/delete", method = RequestMethod.POST)
	@ApiOperation(value = "根据id删除计划类型信息", notes = "根据id删除计划类型信息")
	@ApiImplicitParam(paramType = "query", name = "id", value = "责任id", required = true, dataType = "Integer")
	@ResponseBody
	public JSONObject delete(HttpServletRequest request, Integer id) throws ServicesException {
		JSONObject jo = new JSONObject();

		try {
			planTypeService.deletePlanType(id);
			jo.put("code", 0);
		} catch (Exception e) {
			jo.put("code", 1);
			jo.put("msg", "未知错误");
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}

		return jo;
	}

	@RequestMapping(value = "/planType/findById", method = RequestMethod.POST)
	@ApiOperation(value = "根据id查询计划类型信息", notes = "根据id查询计划类型信息")
	@ApiImplicitParam(paramType = "query", name = "id", value = "计划类型id", required = true, dataType = "Integer")
	@ResponseBody
	public JSONObject findById(HttpServletRequest request, Integer id) throws ServicesException {
		JSONObject jo = new JSONObject();
		try {
			CMesPlanTypeT planType = planTypeService.findPlanTypeById(id);
			jo.put("code", 0);
			jo.put("msg", planType);
		} catch (Exception e) {
			jo.put("code", 1);
			jo.put("msg","未知错误");
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return jo;
	}

	@ResponseBody
	@RequestMapping(value = "/planType/add", method = RequestMethod.POST)
	@ApiOperation("新增计划类型信息")
	public JSONObject add(HttpServletRequest request, @ModelAttribute CMesPlanTypeT planType) {
		JSONObject jo = new JSONObject();

		try {
			planTypeService.addPlanType(planType);
			jo.put("code", 0);
		} catch (Exception e) {
			jo.put("code", 1);
			jo.put("msg", "未知错误");
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}

		return jo;

	}

	@RequestMapping(value = "/planType/update", method = RequestMethod.POST)
	@ApiOperation(value = "修改计划类型信息", notes = "根据id修改计划类型信息")
	@ResponseBody
	public JSONObject update(HttpServletRequest request, @ModelAttribute CMesPlanTypeT planType) throws ServicesException {
		JSONObject jo = new JSONObject();
		try {
			planTypeService.updatePlanType(planType);
			jo.put("code", 0);
		} catch (Exception e) {
			jo.put("code", 1);
			jo.put("msg","未知错误");
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}

		return jo;
	}

	@RequestMapping(value = "/planType/findList", method = RequestMethod.POST)
	@ApiOperation(value = "查询计划类型信息列表", notes = "查询计划类型信息列表")
	@ResponseBody
	public JSONObject findList(HttpServletRequest request, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "6") Integer pageSize) throws ServicesException {
		PageHelper.startPage(pageNum, pageSize);

		JSONObject json = new JSONObject();
		try {
			CMesPlanTypeT planType = new CMesPlanTypeT();
			List<CMesPlanTypeT> planTypeList = planTypeService.findAllPlanType();
			PageInfo<CMesPlanTypeT> pageInfo = new PageInfo<>(planTypeList);

			json.put("code", 0);
			json.put("msg", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);

			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}

}
