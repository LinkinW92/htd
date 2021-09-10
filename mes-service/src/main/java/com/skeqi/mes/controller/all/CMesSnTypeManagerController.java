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
import com.skeqi.mes.pojo.CMesAssemblyType;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.service.all.CMesLineTService;
import com.skeqi.mes.service.all.CMesMaterialService;

/***
 *
 * @author ENS 总成类型管理
 *
 */
@Controller
@RequestMapping("skq")
public class CMesSnTypeManagerController {

	@Autowired
	CMesLineTService cMesLineTService;
	@Autowired
	CMesMaterialService materialService;

	/**
	 * 总成类型列表
	 */
	@RequestMapping("snTypeManager")
	public String snTypeManager(HttpServletRequest request,
			@RequestParam(required = false, defaultValue = "1", value = "page") Integer page) {
		JSONObject json = new JSONObject();

		CMesAssemblyType assemblyType = new CMesAssemblyType();
		PageHelper.startPage(page, 5);
		try {
		List<CMesAssemblyType> assemblyTypeList = materialService.findAllAssembly(assemblyType);
		PageInfo<CMesAssemblyType> pageInfo = new PageInfo<>(assemblyTypeList, 5);
		CMesLineT line  = new CMesLineT();
		List<CMesLineT> lineList = cMesLineTService.findAllLine(line);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("lineList", lineList);
		json.put("code", 0);
		}catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}

		return "materiel_control/snTypeManager";
	}

	/**
	 * 添加总成类型
	 */
	@RequestMapping("addAssemblyType")
	@ResponseBody
	public  JSONObject addAssemblyType(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String assemblyTypeNo = request.getParameter("assemblyTypeNo").trim();
		String assemblyTypeName = request.getParameter("assemblyTypeName").trim();
		String lineId = request.getParameter("lineId");
		String assemblyTypeDis = request.getParameter("assemblyTypeDis");

		CMesAssemblyType type = new CMesAssemblyType();
		type.setAssemblyTypeDis(assemblyTypeDis);
		type.setAssemblyTypeName(assemblyTypeName);
		type.setLineId(Integer.parseInt(lineId));
		type.setAssemblyTypeNo(assemblyTypeNo);

		try {
			materialService.addAssmbly(type);
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
	 * 删除总成类型
	 */
	@RequestMapping("delAssemblyType")
	@ResponseBody
	public  JSONObject delShift(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");

			try {
				materialService.delAssmebly(Integer.parseInt(id));
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
	 * 通过ID查询总成类型
	 */
	@RequestMapping("toEditAssemblyType")
	@ResponseBody
	public  JSONObject toEditAssemblyType(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");

		try {
			CMesAssemblyType assemblyType = materialService.findAssemblyByid(Integer.parseInt(id));
			json.put("code", 0);
			json.put("assemblyType", assemblyType);
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
	 * 修改总成类型
	 */
	@RequestMapping("editAssemblyType")
	@ResponseBody
	public  JSONObject editAssemblyType(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");
		String assemblyTypeNo = request.getParameter("assemblyTypeNo").trim();
		String assemblyTypeName = request.getParameter("assemblyTypeName").trim();
		String lineId = request.getParameter("lineId");
		String assemblyTypeDis = request.getParameter("assemblyTypeDis");
		CMesAssemblyType type = new CMesAssemblyType();
		type.setAssemblyTypeDis(assemblyTypeDis);
		type.setAssemblyTypeName(assemblyTypeName);
		type.setLineId(Integer.parseInt(lineId));
		type.setAssemblyTypeNo(assemblyTypeNo);
		type.setId(Integer.parseInt(id));
		try {
			materialService.updateAssmebly(type);
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
