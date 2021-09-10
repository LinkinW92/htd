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
import com.skeqi.mes.pojo.CMesLeakageInfoT;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.service.all.CMesBomService;
import com.skeqi.mes.service.all.CMesStationTService;
import com.skeqi.mes.util.ToolUtils;

/***
 *
 * @author ENS 气密性信息 1
 *
 */
@Controller
@RequestMapping("skq")
public class CMesAirtightnessController {


	@Autowired
	CMesBomService bomService;
	@Autowired
	CMesStationTService stationService;

	/**
	 * 删除气密性信息
	 */
	@RequestMapping("delAirtightness")
	@ResponseBody
	public  JSONObject delAirtightness(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");
		try {
			bomService.delLeakage(Integer.parseInt(id));
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



	@RequestMapping("toEditAirtightness")
	@ResponseBody
	public  JSONObject toEditAirtightness(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");
		try {
				CMesLeakageInfoT leakage = bomService.findLeakageByid(Integer.parseInt(id));
				json.put("leakage", leakage);
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
	 * 修改气密性信息
	 */
	@RequestMapping("editAirtightness")
	@ResponseBody
	public  Object editAirtightness(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String leakageName = request.getParameter("leakageName").trim();
		String st = request.getParameter("st").trim();
		String pvLimit = request.getParameter("pvLimit").trim();
		String lvLimit = request.getParameter("lvLimit").trim();
		String uploadCode = request.getParameter("uploadCode").trim();
		String dis = request.getParameter("dis");
		String id = request.getParameter("id");
		CMesLeakageInfoT leakage = new CMesLeakageInfoT();
		leakage.setDis(dis);
		leakage.setId(Integer.parseInt(id));
		leakage.setLeakageName(leakageName);
		leakage.setLvLimit(lvLimit);
		leakage.setPvLimit(pvLimit);
		leakage.setSt(Integer.parseInt(st));
		leakage.setUploadCode(uploadCode);
		try {
			bomService.updateLeakage(leakage);
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
	 * 气密性信息
	 */
	@RequestMapping("airtightnessList")
	public String airtightnessList(HttpServletRequest request,
			@RequestParam(required = false, defaultValue = "1", value = "page") Integer page) {
		JSONObject json = new JSONObject();
		String station = request.getParameter("station");
		CMesStationT st = new CMesStationT();
		try {
			CMesLeakageInfoT leakageInfo = new CMesLeakageInfoT();
			if(station!=null&&station!="") {
				leakageInfo.setStationName(station);

			}
			List<CMesStationT> stationList = stationService.findAllStation(st);
			PageHelper.startPage(page, 8);
			List<CMesLeakageInfoT> leakageList = bomService.findAllLeakage(leakageInfo);
			PageInfo<CMesLeakageInfoT> pageInfo = new PageInfo<>(leakageList, 5);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("station", station);
			request.setAttribute("stationList", stationList);
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
		return "bom_control/airtightnessInformation";
	}

	/**
	 * 添加气密性信息
	 */
	@RequestMapping("addAirtightness")
	@ResponseBody
	public  JSONObject addAirtightness(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String leakageName = request.getParameter("leakageName").trim();
		String st = request.getParameter("st").trim();
		String pvLimit = request.getParameter("pvLimit").trim();
		String lvLimit = request.getParameter("lvLimit").trim();
		String uploadCode = request.getParameter("uploadCode").trim();
		String dis = request.getParameter("dis");
		CMesLeakageInfoT leakage = new CMesLeakageInfoT();
		leakage.setDis(dis);
		leakage.setLeakageName(leakageName);
		leakage.setLvLimit(lvLimit);
		leakage.setPvLimit(pvLimit);
		leakage.setSt(Integer.parseInt(st));
		leakage.setUploadCode(uploadCode);
		try {
			bomService.addLeakage(leakage);
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
