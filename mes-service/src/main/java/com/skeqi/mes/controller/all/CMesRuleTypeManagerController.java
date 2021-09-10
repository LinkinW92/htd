package com.skeqi.mes.controller.all;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.skeqi.mes.pojo.CMesLabelType;
import com.skeqi.mes.service.all.CMesLineTService;
import com.skeqi.mes.service.all.TechnologyService;

/***
 *
 * @author ENS 规则类型管理 1
 *
 */
@Controller
@RequestMapping("skq")
public class CMesRuleTypeManagerController {


	@Autowired
	TechnologyService service;

	@Autowired
	CMesLineTService cMesLineTService;


	/**
	 * 规则类型列表
	 */
	@RequestMapping("ruleTypeManager")
	public Object ruleTypeManager(HttpServletRequest request,
			@RequestParam(required = false, defaultValue = "1", value = "page") Integer page) {
		CMesLabelType labelType = new CMesLabelType();
		JSONObject json = new JSONObject();
		try {
			PageHelper.startPage(page, 8);
			List<CMesLabelType> ruleTypeManagerList = service.findAllLabelType(labelType);
			PageInfo<CMesLabelType> pageInfo = new PageInfo<>(ruleTypeManagerList, 5);
			request.setAttribute("pageInfo", pageInfo);
			json.put("code", 0);
		}catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}
		return "deviceManager_control/ruleTypeManager";
	}

	/**
	 * 添加规则类型
	 */
	@RequestMapping("addRuleTypeManager")
	@ResponseBody
	public  JSONObject addRuleTypeManager(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String name = request.getParameter("name").trim();
		String labelVr = request.getParameter("labelVr").trim();
		String labelDis = request.getParameter("labelDis");
		CMesLabelType labelType = new CMesLabelType();
		labelType.setName(name);
		labelType.setLabelVr(labelVr);
		labelType.setLabelDis(labelDis);

		try {
			service.addLabelType(labelType);
			json.put("code", 0);
		}catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}
		return json;
	}

	/**
	 * 通过ID查询规则类型信息
	 */
	@RequestMapping("findRuleTypeManagerById")
	@ResponseBody
	public  JSONObject findRuleTypeManagerById(HttpServletRequest request,
			@RequestParam(required = false, defaultValue = "1", value = "page") Integer page) {
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");
		try {
			CMesLabelType ruleTypeManager = service.findLabelTypeByid(Integer.parseInt(id));
			json.put("ruleTypeManager", ruleTypeManager);
			json.put("code", 0);
		}catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}
		return json;
	}

	/**
	 * 修改规则类型
	 */
	@RequestMapping("editRuleTypeManager")
	@ResponseBody
	public  JSONObject editRuleTypeManager(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");
		String name = request.getParameter("name").trim();
		String labelVr = request.getParameter("labelVr").trim();
		String labelDis = request.getParameter("labelDis");
		CMesLabelType labelType = new CMesLabelType();
		labelType.setName(name);
		labelType.setLabelVr(labelVr);
		labelType.setLabelDis(labelDis);
		labelType.setId(Integer.parseInt(id));

		 try {
			 	service.updateLabelType(labelType);
			 	json.put("code", 0);
			}catch (ServicesException e) {
				json.put("code", e.getCode());
				json.put("msg", e.getMessage());
			} catch (Exception e) {
				json.put("code", -1);
				json.put("msg", e.getMessage());
			}
		return json;
	}

	/**
	 * 删除规则类型
	 */
	@RequestMapping("removeRuleTypeManager")
	@ResponseBody
	public  JSONObject removeRuleTypeManager(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");

		try {
			service.delLabelType(Integer.parseInt(id));
			json.put("code", 0);
		}catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}
		return json;
	}

}
