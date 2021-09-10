package com.skeqi.mes.controller.all;

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
import com.skeqi.mes.pojo.CMesLabelManagerT;
import com.skeqi.mes.pojo.CMesLabelType;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.service.all.CMesLineTService;
import com.skeqi.mes.service.all.TechnologyService;

/**
 *
 * @author ENS 标签管理 1
 *
 */
@Controller
@RequestMapping("skq")
public class CMesLabelManagerController {


	@Autowired
	TechnologyService service;

	@Autowired
	CMesLineTService cMesLineTService;





	/**
	 * 标签管理
	 */
	@RequestMapping("labelManager")
	public String labelManager(HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page){
		CMesLineT line = new CMesLineT();
		JSONObject json = new JSONObject();
		try {
		List<CMesLineT> lineList = cMesLineTService.findAllLine(line);
		CMesLabelType labelType = new CMesLabelType();
		List<CMesLabelType> ruleTypeManagerList = service.findAllLabelType(labelType);
		CMesLabelManagerT labelManager = new CMesLabelManagerT();
		PageHelper.startPage(page,8);
		List<CMesLabelManagerT> labelManagerList = service.findAllLabelManager(labelManager);
		PageInfo<CMesLabelManagerT> pageInfo = new PageInfo<>(labelManagerList,5);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("lineList", lineList);
		request.setAttribute("ruleTypeManagerList", ruleTypeManagerList);
		json.put("code", 0);
		}catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}
		return "deviceManager_control/labelManager";
	}
	/**
	 * 通过ID查询标签信息
	 */
	@RequestMapping("findLabelManagerById")
	@ResponseBody
	public  JSONObject findLabelManagerById(HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page){
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");
		try {
			CMesLabelManagerT labelManager = service.findLabelManagerByid(Integer.parseInt(id));
			json.put("labelManager", labelManager);
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
	 * 添加标签
	 */
	@RequestMapping("addLabelManager")
	@ResponseBody
	public  JSONObject addLabelManager(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String labelNumber = request.getParameter("labelNumber").trim();
		String labelName = request.getParameter("labelName").trim();
		String labelRules = request.getParameter("labelRules").trim();
		String labelTypeId = request.getParameter("labelTypeId");
		String lineId = request.getParameter("lineId");
		String dis = request.getParameter("dis");
		CMesLabelManagerT labelManager = new CMesLabelManagerT();
		labelManager.setDis(dis);
		labelManager.setLabelName(labelName);
		labelManager.setLabelNumber(labelNumber);
		labelManager.setLabelRules(labelRules);
		labelManager.setLabelTypeId(Integer.parseInt(labelTypeId));
		labelManager.setLineId(Integer.parseInt(lineId));

		try {
			service.addLabelManager(labelManager);
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
	 * 修改标签信息
	 */
	@RequestMapping("editLabelManager")
	@ResponseBody
	public  JSONObject editLabelManager(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String labelNumber = request.getParameter("labelNumber").trim();
		String labelName = request.getParameter("labelName").trim();
		String labelRules = request.getParameter("labelRules").trim();
		String labelTypeId = request.getParameter("labelTypeId");
		String lineId = request.getParameter("lineId");
		String dis = request.getParameter("dis");
		String id = request.getParameter("id");
		CMesLabelManagerT labelManager = new CMesLabelManagerT();
		labelManager.setId(Integer.parseInt(id));
		labelManager.setDis(dis);
		labelManager.setLabelName(labelName);
		labelManager.setLabelNumber(labelNumber);
		labelManager.setLabelRules(labelRules);
		labelManager.setLabelTypeId(Integer.parseInt(labelTypeId));
		labelManager.setLineId(Integer.parseInt(lineId));

		try {
			service.updateLabelManager(labelManager);
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
	 * 删除标签信息
	 */
	@RequestMapping("removeLabelManager")
	@ResponseBody
	public  JSONObject removeLabelManager(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");

		try {
			service.delLabelManager(Integer.parseInt(id));
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
