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
import com.skeqi.mes.pojo.CMesMaterialMsgT;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.service.all.CMesBomService;
import com.skeqi.mes.service.all.CMesStationTService;

/***
 *
 * @author ENS 物料信息 1
 *
 */
@Controller
@RequestMapping("skq")
public class CMesMaterialMsgController {

	@Autowired
	CMesBomService bomService;
	@Autowired
	CMesStationTService stationService;


	/**
	 * 修改物料信息
	 */
	@RequestMapping("editMaterialMsg")
	@ResponseBody
	public  Object editMaterial(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String materialName = request.getParameter("materialName").trim();
		String st = request.getParameter("st");
		String materialType = request.getParameter("materialType");
		String materialVr = request.getParameter("materialVr");
		String materialComeType = request.getParameter("materialComeType");
		String materialSupplier = request.getParameter("materialSupplier");
		String uploadCode = request.getParameter("uploadCode");
		String dis = request.getParameter("dis");
		String id = request.getParameter("id");
		CMesMaterialMsgT msg = new CMesMaterialMsgT();
		msg.setId(Integer.parseInt(id));
		msg.setDis(dis);
		msg.setMaterialComeType(materialComeType);
		msg.setMaterialName(materialName);
		msg.setMaterialSupplier(materialSupplier);
		msg.setMaterialType(Integer.parseInt(materialType));
		msg.setMaterialVr(materialVr);
		msg.setSt(Integer.parseInt(st));
		msg.setUploadCode(uploadCode);

		try {
			bomService.updateMaterialMsg(msg);
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
	 * 物料信息
	 */
	@RequestMapping("materialMsgList")
	public String materialList(HttpServletRequest request,
			@RequestParam(required = false, defaultValue = "1", value = "page") Integer page) {
		JSONObject json = new JSONObject();
		String station = request.getParameter("station");
		CMesStationT st =new CMesStationT();
		try {
		List<CMesStationT> stationList = stationService.findAllStation(st);
		CMesMaterialMsgT msg = new CMesMaterialMsgT();
		if(station!=null&&station!="") {
			msg.setStationName(station);
		}
		PageHelper.startPage(page, 8);
		List<CMesMaterialMsgT> materialList = bomService.findAllMaterialMsg(msg);
		PageInfo<CMesMaterialMsgT> pageInfo = new PageInfo<>(materialList, 5);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("station", station);
		request.setAttribute("stationList", stationList);
		json.put("code", 0);
		}catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}
		return "bom_control/materialInformation";
	}

	/**
	 * 添加物料信息
	 */
	@RequestMapping("addMaterialMsg")
	@ResponseBody
	public  JSONObject addMaterial(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String materialName = request.getParameter("materialName").trim();
		String st = request.getParameter("st");
		String materialType = request.getParameter("materialType");
		String materialVr = request.getParameter("materialVr");
		String materialComeType = request.getParameter("materialComeType");
		String materialSupplier = request.getParameter("materialSupplier");
		String uploadCode = request.getParameter("uploadCode");
		String dis = request.getParameter("dis");
		CMesMaterialMsgT msg = new CMesMaterialMsgT();
		msg.setDis(dis);
		msg.setMaterialComeType(materialComeType);
		msg.setMaterialName(materialName);
		msg.setMaterialSupplier(materialSupplier);
		msg.setMaterialType(Integer.parseInt(materialType));
		msg.setMaterialVr(materialVr);
		msg.setSt(Integer.parseInt(st));
		msg.setUploadCode(uploadCode);
		try {
			bomService.addMaterialMsg(msg);
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
	 * 删除物料信息
	 */
	@RequestMapping("delMaterialMsg")
	@ResponseBody
	public JSONObject delMaterial(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");
			try {
				bomService.delMaterialMsg(Integer.parseInt(id));
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


	@RequestMapping("toMaterialMsg")
	@ResponseBody
	public  JSONObject toMaterial(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");


		CMesMaterialMsgT material;
		try {
			material = bomService.findMaterialMsgByid(Integer.parseInt(id));
		json.put("material", material);
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
