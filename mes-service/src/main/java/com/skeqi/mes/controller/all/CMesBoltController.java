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
import com.skeqi.mes.pojo.CMesBoltInfomationT;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.service.all.CMesBomService;
import com.skeqi.mes.service.all.CMesProductionTService;
import com.skeqi.mes.service.all.CMesStationTService;

/***
 *
 * @author ENS 螺栓信息 1
 *
 */
@Controller
@RequestMapping("skq")
public class CMesBoltController {

	@Autowired
	CMesBomService bomService;

	@Autowired
	CMesStationTService stationService;



	/**
	 * 螺栓信息
	 */
	@RequestMapping("boltList")
	public String boltList(HttpServletRequest request,
			@RequestParam(required = false, defaultValue = "1", value = "page") Integer page) {
		JSONObject json = new JSONObject();
		CMesStationT st = new CMesStationT();
		String station = request.getParameter("station");

		try {
		List<CMesStationT> stationList = stationService.findAllStation(st);
		CMesBoltInfomationT boltInfomation = new CMesBoltInfomationT();

		if(station!=null&&station!="") {
			boltInfomation.setStationName(station);

		}
		PageHelper.startPage(page, 8);
		List<CMesBoltInfomationT> boltList = bomService.findAllBolt(boltInfomation);
		PageInfo<CMesBoltInfomationT> pageInfo = new PageInfo<>(boltList, 5);
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
		return "bom_control/boltInformation";
	}

	/**
	 * 添加螺栓
	 */
	@RequestMapping("addBolt")
	@ResponseBody
	public JSONObject addBolt(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String boltName = request.getParameter("boltName").trim();
		String boltNo = request.getParameter("boltNo").trim();
		String st = request.getParameter("st").trim();
		String aLimit = request.getParameter("aLimit").trim();
		String tLimit = request.getParameter("tLimit").trim();
		String uploadCode = request.getParameter("uploadCode").trim();
		String programNo = request.getParameter("programNo").trim();
		String dis = request.getParameter("dis");
		CMesBoltInfomationT bolt = new CMesBoltInfomationT();
		bolt.setaLimit(aLimit);
		bolt.setBoltName(boltName);
		bolt.setBoltNo(Integer.parseInt(boltNo));
		bolt.setDis(dis);
		bolt.setProgramNo(Integer.parseInt(programNo));
		bolt.setSt(Integer.parseInt(st));
		bolt.settLimit(tLimit);
		bolt.setUploadCode(uploadCode);
		try {
			bomService.addBolt(bolt);
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
	 * 删除螺栓信息
	 */
	@RequestMapping("delBolt")
	@ResponseBody
	public  JSONObject delBolt(HttpServletRequest request) {
		String id = request.getParameter("id");
		JSONObject json = new JSONObject();
		try {
			bomService.delBolt(Integer.parseInt(id));
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



	@RequestMapping("toEditBolt")
	@ResponseBody
	public  JSONObject toEditBolt(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");
		try {
			CMesBoltInfomationT bolt = bomService.findBoltByid(Integer.parseInt(id));
			json.put("bolt", bolt);
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
	 * 修改螺栓信息
	 */
	@RequestMapping("editBolt")
	@ResponseBody
	public  JSONObject editBolt(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String boltName = request.getParameter("boltName").trim();
		String boltNo = request.getParameter("boltNo").trim();
		String st = request.getParameter("st").trim();
		String aLimit = request.getParameter("aLimit").trim();
		String tLimit = request.getParameter("tLimit").trim();
		String uploadCode = request.getParameter("uploadCode").trim();
		String programNo = request.getParameter("programNo").trim();
		String dis = request.getParameter("dis");
		String id = request.getParameter("id");

		CMesBoltInfomationT bolt = new CMesBoltInfomationT();
		bolt.setId(Integer.parseInt(id));
		bolt.setaLimit(aLimit);
		bolt.setBoltName(boltName);
		bolt.setBoltNo(Integer.parseInt(boltNo));
		bolt.setDis(dis);
		bolt.setProgramNo(Integer.parseInt(programNo));
		bolt.setSt(Integer.parseInt(st));
		bolt.settLimit(tLimit);
		bolt.setUploadCode(uploadCode);

		try {
			bomService.updateBolt(bolt);
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
