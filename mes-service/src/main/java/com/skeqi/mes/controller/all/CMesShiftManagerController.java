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
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesShiftsTeamT;
import com.skeqi.mes.service.all.CMesLineTService;
import com.skeqi.mes.service.all.ProduceService;
import com.skeqi.mes.util.ToolUtils;

/***
 *
 * @author ENS 班次管理
 *
 */
@Controller
@RequestMapping("skq")
public class CMesShiftManagerController {


	@Autowired
	ProduceService produceService;
	@Autowired
	CMesLineTService lineService;


	/**
	 * 班次列表
	 */
	@RequestMapping("shiftManager")
	public String shiftManager(HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page) {

		PageHelper.startPage(page,8);
		CMesShiftsTeamT t = new CMesShiftsTeamT();
		CMesLineT line = new CMesLineT();
		try {
		List<CMesShiftsTeamT> shiftsTeamList = produceService.findAllShift(t);
		PageInfo<CMesShiftsTeamT> pageInfo = new PageInfo<>(shiftsTeamList,5);
		List<CMesLineT> lineList = lineService.findAllLine(line);
		request.setAttribute("lineList", lineList);
		request.setAttribute("pageInfo", pageInfo);
		}catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return "production_control/shiftManager";
	}



	/**
	 * 添加班次
	 */
	@RequestMapping("addShift")
	@ResponseBody
	public  JSONObject addShiftsTeam(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String name = request.getParameter("name").trim();
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String dis = request.getParameter("dis");
		String lineId = request.getParameter("lineId");
		CMesShiftsTeamT t = new CMesShiftsTeamT();
		t.setLineId(Integer.parseInt(lineId));
		t.setName(name);
		t.setStartTime(startTime);
		t.setEndTime(endTime);
		t.setDis(dis);
		try {
			produceService.addShift(t);
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
	 * 通过ID查询班次信息
	 */
	@RequestMapping("toEditShift")
	@ResponseBody
	public  JSONObject toEditShift(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");
		try {
			CMesShiftsTeamT shiftsTeam =produceService.findShiftByid(Integer.parseInt(id));
			String startTime=shiftsTeam.getStartTime();
			String endTime=shiftsTeam.getEndTime();
			json.put("shiftsTeam", shiftsTeam);
			json.put("startTime", startTime);
			json.put("endTime", endTime);
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
	 * 修改班次
	 */
	@RequestMapping("editShift")
	@ResponseBody
	public  JSONObject editShift(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");
		String name = request.getParameter("name").trim();
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String dis = request.getParameter("dis");
		String lineId = request.getParameter("lineId");
		CMesShiftsTeamT t = new CMesShiftsTeamT();
		t.setLineId(Integer.parseInt(lineId));
		t.setName(name);
		t.setStartTime(startTime);
		t.setEndTime(endTime);
		t.setDis(dis);
		t.setId(Integer.parseInt(id));
		try {
			produceService.updateShift(t);
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
	 * 删除班次
	 */
	@RequestMapping("delShift")
	@ResponseBody
	public  JSONObject delShift(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");
		try {
			produceService.delShift(Integer.parseInt(id));
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
