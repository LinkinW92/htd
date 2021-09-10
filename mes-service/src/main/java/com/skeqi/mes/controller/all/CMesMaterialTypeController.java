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
import com.skeqi.mes.pojo.CMesMaterialTypeT;
import com.skeqi.mes.service.all.CMesMaterialService;
import com.skeqi.mes.util.ToolUtils;

/***
 *
 * @author ENS  物料类型管理 1
 *
 */
@Controller
@RequestMapping("skq")
public class CMesMaterialTypeController {

	@Autowired
	CMesMaterialService materialService;



	/**
	 * 物料类型管理
	 */
	@RequestMapping("materielTypeManager")
	public String materielTypeManager(HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page) {
		CMesMaterialTypeT material = new CMesMaterialTypeT();
		PageHelper.startPage(page,5);
		try {
			List<CMesMaterialTypeT> materialTypeList = materialService.findAllMaterialType(material);
			PageInfo<CMesMaterialTypeT> pageInfo = new PageInfo<>(materialTypeList,5);
			request.setAttribute("pageInfo", pageInfo);
		} catch (ServicesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return "materiel_control/materialTypeManager";
	}

	/**
	 * 添加物料类型
	 */
	@RequestMapping("addMaterialType")
	@ResponseBody
	public  JSONObject addMaterialType(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String materialType = request.getParameter("materialType");
		String dis = request.getParameter("dis");
		CMesMaterialTypeT material = new CMesMaterialTypeT();
		material.setMaterialType(materialType);
		material.setDis(dis);
		material.setDt(new Date());
		try {
			materialService.addMaterialType(material);
			json.put("code", 0);
		}  catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		}catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}

		return json;
	}

	/**
	 * 通过ID查询物料类型信息
	 */
	@RequestMapping("toEditMaterialType")
	@ResponseBody
	public JSONObject toEditMaterialType(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");

		CMesMaterialTypeT material;
		try {
			material = materialService.findMaterialTypeByid(Integer.parseInt(id));
			json.put("code", 0);
			json.put("materialType", material);
		}  catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		}catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}

		return json;
	}
	/**
	 * 删除物料类型
	 */
	@RequestMapping("delMaterialType")
	@ResponseBody
	public JSONObject delMaterialType(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");

		try {
			materialService.delMaterialType(Integer.parseInt(id));
			json.put("code", 0);
		}  catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		}catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}

		return json;
	}


	/**
	 * 修改物料类型
	 */
	@RequestMapping("editMaterialType")
	@ResponseBody
	public JSONObject editMaterialType(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");
		String materialType = request.getParameter("materialType");
		String dis = request.getParameter("dis");

		CMesMaterialTypeT material = new CMesMaterialTypeT();
		material.setId(Integer.parseInt(id));
		material.setMaterialType(materialType);
		material.setDis(dis);

		try {
			materialService.updateMaterialType(material);
			json.put("code", 0);
		}  catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		}catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}

		return json;
	}



}
