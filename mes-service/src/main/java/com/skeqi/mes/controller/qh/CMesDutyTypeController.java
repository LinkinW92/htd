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
import com.skeqi.mes.pojo.CMesDutyTypeManagerT;
import com.skeqi.mes.service.all.QualityService;
import com.skeqi.mes.util.ToolUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/***
 *
 * 责任类型管理
 *
 */
@Controller
@RequestMapping(value = "api", produces = MediaType.APPLICATION_JSON)
@Api(value = "责任类型管理", description = "责任类型管理", produces = MediaType.APPLICATION_JSON)
public class CMesDutyTypeController {

	@Autowired
	QualityService qualityService;

	/**
	 *
	 * 责任类型管理
	 */
	@RequestMapping(value = "/dutyType/delete", method = RequestMethod.POST)
	@ApiOperation(value = "根据id删除责任类型信息", notes = "根据id删除责任类型信息")
	@ApiImplicitParam(paramType = "query", name = "id", value = "责任id", required = true, dataType = "Integer")
	@ResponseBody
	public JSONObject delete(HttpServletRequest request, Integer id) throws ServicesException {
		JSONObject jo = new JSONObject();

		try {
			qualityService.delDutyType(id);
			jo.put("code", 0);
		} catch (ServicesException e) {
			jo.put("code", e.getCode());
			jo.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		} catch (Exception e) {
			jo.put("code", 1);
			jo.put("msg", "未知错误");
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}

		return jo;
	}

	@RequestMapping(value = "/dutyType/findById", method = RequestMethod.POST)
	@ApiOperation(value = "根据id查询责任类型信息", notes = "根据id查询责任类型信息")
	@ApiImplicitParam(paramType = "query", name = "id", value = "责任类型id", required = true, dataType = "Integer")
	@ResponseBody
	public JSONObject findById(HttpServletRequest request, Integer id) throws ServicesException {
		JSONObject jo = new JSONObject();
		try {
			CMesDutyTypeManagerT dutyTypeManager = qualityService.findDutyTypeByid(id);
			jo.put("code", 0);
			jo.put("msg", dutyTypeManager);
		} catch (ServicesException e) {
			jo.put("code", e.getCode());
			jo.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		} catch (Exception e) {
			jo.put("code", 1);
			jo.put("msg","未知错误");
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return jo;
	}

	@ResponseBody
	@RequestMapping(value = "/dutyType/add", method = RequestMethod.POST)
	@ApiOperation("新增责任信息")
	public JSONObject add(HttpServletRequest request, @ModelAttribute CMesDutyTypeManagerT dutyTypeManager) {
		JSONObject jo = new JSONObject();

		try {
			qualityService.addDutyType(dutyTypeManager);
			jo.put("code", 0);
		} catch (ServicesException e) {
			jo.put("code", e.getCode());
			jo.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		} catch (Exception e) {
			jo.put("code", 1);
			jo.put("msg", "未知错误");
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}

		return jo;

	}

	@RequestMapping(value = "/dutyType/update", method = RequestMethod.POST)
	@ApiOperation(value = "修改责任类型信息", notes = "根据id修改责任类型信息")
	@ResponseBody
	public JSONObject update(HttpServletRequest request, @ModelAttribute CMesDutyTypeManagerT dutyTypeManager) throws ServicesException {
		JSONObject jo = new JSONObject();
		try {
			qualityService.updateDutyType(dutyTypeManager);
			jo.put("code", 0);
		} catch (ServicesException e) {
			jo.put("code", e.getCode());
			jo.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		} catch (Exception e) {
			jo.put("code", 1);
			jo.put("msg","未知错误");
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}

		return jo;
	}

	@RequestMapping(value = "/dutyType/findList", method = RequestMethod.POST)
	@ApiOperation(value = "查询责任类型信息", notes = "查询责任类型信息")
	@ResponseBody
	public JSONObject findList(HttpServletRequest request, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "6") Integer pageSize) throws ServicesException {
		PageHelper.startPage(pageNum, pageSize);

		JSONObject json = new JSONObject();
		try {
			CMesDutyTypeManagerT dutyType = new CMesDutyTypeManagerT();
			List<CMesDutyTypeManagerT> dutyTypeList = qualityService.findAllDutyType(dutyType);
			PageInfo<CMesDutyTypeManagerT> pageInfo = new PageInfo<>(dutyTypeList);

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
