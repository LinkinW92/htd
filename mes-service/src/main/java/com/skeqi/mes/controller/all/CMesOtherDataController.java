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
import com.skeqi.mes.pojo.CMesOtherDataT;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.service.all.CMesBomService;
import com.skeqi.mes.service.all.CMesStationTService;
import com.skeqi.mes.util.ToolUtils;

/***
 *
 * @author ENS 其他信息管理 1
 *
 */
@Controller
@RequestMapping("skq")
public class CMesOtherDataController {


	@Autowired
	CMesBomService bomService;

	@Autowired
	CMesStationTService stationService;


	/**
	 * 其他信息
	 */
	@RequestMapping("otherDataList")
	public String otherDataList(HttpServletRequest request,
			@RequestParam(required = false, defaultValue = "1", value = "page") Integer page) {
		JSONObject json = new JSONObject();
		String station = request.getParameter("station");
		try {
		CMesStationT st = new CMesStationT();
		List<CMesStationT> stationList = stationService.findAllStation(st);
		CMesOtherDataT other = new CMesOtherDataT();
		if(station!=null&&station!="") {

			other.setOtherName(station);

		}
		PageHelper.startPage(page, 8);
		List<CMesOtherDataT> otherDataList = bomService.findAllOther(other);
		PageInfo<CMesOtherDataT> pageInfo = new PageInfo<>(otherDataList, 5);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("station", station);
		request.setAttribute("stationList", stationList);
		json.put("code", 0);
		}catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		} catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return "bom_control/otherInformation";
	}

	/**
	 * 添加其他信息
	 */
	@RequestMapping("addOtherData")
	@ResponseBody
	public  JSONObject addOtherData(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String otherName = request.getParameter("otherName").trim();
		String st = request.getParameter("st");
		String uploadCode = request.getParameter("uploadCode").trim();
		String dis = request.getParameter("dis");
		CMesOtherDataT other = new CMesOtherDataT();
		other.setDis(dis);
		other.setOtherName(otherName);
		other.setSt(Integer.parseInt(st));
		other.setUploadCode(uploadCode);

		try {
				bomService.addOther(other);
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
	 * 删除其他信息
	 */
	@RequestMapping("delOtherData")
	@ResponseBody
	public  JSONObject delOtherData(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");
		try {
			bomService.delOther(Integer.parseInt(id));
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


	@RequestMapping("toEditOtherData")
	@ResponseBody
	public  JSONObject toEditOtherData(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");

		try {
			CMesOtherDataT	otherData = bomService.findOtherByid(Integer.parseInt(id));
			json.put("otherData", otherData);
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
	 * 修改其他信息
	 */
	@RequestMapping("editOtherData")
	@ResponseBody
	public  JSONObject editOtherData(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String otherName = request.getParameter("otherName").trim();
		String st = request.getParameter("st");
		String uploadCode = request.getParameter("uploadCode").trim();
		String dis = request.getParameter("dis");
		String id = request.getParameter("id");

		CMesOtherDataT other = new CMesOtherDataT();
		other.setDis(dis);
		other.setOtherName(otherName);
		other.setSt(Integer.parseInt(st));
		other.setUploadCode(uploadCode);
		other.setId(Integer.parseInt(id));

		try {
			bomService.updateOther(other);
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
