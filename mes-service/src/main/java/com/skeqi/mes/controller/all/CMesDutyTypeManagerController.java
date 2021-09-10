package com.skeqi.mes.controller.all;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesDutyTypeManagerT;
import com.skeqi.mes.service.all.QualityService;
import com.skeqi.mes.util.ToolUtils;

/***
 *
 * @author ENS 责任类型管理
 *
 */
@Controller
@RequestMapping("skq")
public class CMesDutyTypeManagerController {

	@Autowired
	QualityService qualityService;

	/**
	 *
	 * 责任类型管理
	 */
	// 责任类型管理的删除方法
	@RequestMapping("removeDutyTypeManager")
	@ResponseBody
	public JSONObject removeDutyTypeManager(HttpServletRequest request) {
		JSONObject jo = new JSONObject();
		Integer id = Integer.parseInt(request.getParameter("id"));// 获取id
		try {
			qualityService.delDutyType(id);
			jo.put("code", 0);
		} catch (ServicesException e) {
			jo.put("code", e.getCode());
			jo.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		} catch (Exception e) {
			jo.put("code", -1);
			jo.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}

		return jo;
	}

	// 责任类型管理的查找
	@RequestMapping("findDutyTypeManagerById")
	@ResponseBody
	public JSONObject findDutyTypeManagerById(HttpServletRequest request) {
		Integer id = Integer.parseInt(request.getParameter("id"));// 获取id
		JSONObject jo = new JSONObject();
		try {
			CMesDutyTypeManagerT dutyTypeManager = qualityService.findDutyTypeByid(id);
			jo.put("code", 0);
			jo.put("dutyTypeManager", dutyTypeManager);
		} catch (ServicesException e) {
			jo.put("code", e.getCode());
			jo.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		} catch (Exception e) {
			jo.put("code", -1);
			jo.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return jo;
	}

	// 责任类型管理的添加
	@RequestMapping("addDutyTypeManager")
	@ResponseBody
	public JSONObject addDutyTypeManager(HttpServletRequest request) {
		JSONObject jo = new JSONObject();
		String dutyTypeId = request.getParameter("dutyTypeId");
		String dutyTypeName = request.getParameter("dutyTypeName");
		String dutyTypeDis = request.getParameter("dutyTypeDis");
		CMesDutyTypeManagerT dutyTypeManager = new CMesDutyTypeManagerT();
		dutyTypeManager.setDutyTypeDis(dutyTypeDis);
		dutyTypeManager.setDutyTypeId(dutyTypeId);
		dutyTypeManager.setDutyTypeName(dutyTypeName);
		try {
			qualityService.addDutyType(dutyTypeManager);
			jo.put("code", 0);
		} catch (ServicesException e) {
			jo.put("code", e.getCode());
			jo.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		} catch (Exception e) {
			jo.put("code", -1);
			jo.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}

		return jo;

	}

	// 责任类型管理的修改
	@RequestMapping("updateDutyTypeManager")
	@ResponseBody
	public JSONObject updataDutyTypeManager(HttpServletRequest request) {
		JSONObject jo = new JSONObject();
		Integer id = Integer.parseInt(request.getParameter("id"));// 获取id
		String dutyTypeId = request.getParameter("dutyTypeId");
		String dutyTypeName = request.getParameter("dutyTypeName");
		CMesDutyTypeManagerT dutyTypeManager = new CMesDutyTypeManagerT();
		dutyTypeManager.setId(id);
		dutyTypeManager.setDutyTypeId(dutyTypeId);
		dutyTypeManager.setDutyTypeName(dutyTypeName);

		try {
			qualityService.updateDutyType(dutyTypeManager);
			jo.put("code", 0);
		} catch (ServicesException e) {
			jo.put("code", e.getCode());
			jo.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		} catch (Exception e) {
			jo.put("code", -1);
			jo.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}

		return jo;
	}

	// 责任类型管理的列表
	@RequestMapping("queryDutyTypeManagerList")
	public String queryDutyTypeManagerList(HttpServletRequest request,
			@RequestParam(required = false, defaultValue = "1", value = "page") Integer page) {
		PageHelper.startPage(page, 13);
		CMesDutyTypeManagerT dutyType = new CMesDutyTypeManagerT();
		try {
			List<CMesDutyTypeManagerT> dutyTypeList = qualityService.findAllDutyType(dutyType);
			PageInfo<CMesDutyTypeManagerT> pageInfo = new PageInfo<>(dutyTypeList, 8);
			request.setAttribute("pageInfo", pageInfo);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "quality_control/dutyTypeManager";
	}

}
