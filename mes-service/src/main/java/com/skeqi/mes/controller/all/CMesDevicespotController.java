package com.skeqi.mes.controller.all;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesDeviceSpotT;
import com.skeqi.mes.pojo.CMesDeviceT;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.service.all.CMesDeviceAllService;
import com.skeqi.mes.service.all.CMesLineTService;

/***
 *
 * @author ENS 设备点检
 *
 */
public class CMesDevicespotController {

//	@Autowired
//	private CMesDeviceAllService deviceService;
//
//	@Autowired
//	private CMesLineTService lineService;
//
//
//	@RequestMapping("/devicespotlist")
//	public String devicelist(HttpServletRequest request,
//			@RequestParam(required = false, defaultValue = "1", value = "page") Integer page) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		String deviceName = request.getParameter("deviceName");
//		String lineId = request.getParameter("lineId");
//		request.setAttribute("deviceName", deviceName);
//		request.setAttribute("lineId", lineId);
//		PageHelper.startPage(page, 8);
//		CMesDeviceSpotT device = new CMesDeviceSpotT();
//		CMesLineT line = new CMesLineT();
//		device.setLineId(Integer.parseInt(lineId));
//		device.setDeviceName(deviceName);
//		try {
//			List<CMesDeviceSpotT> DeviceList = deviceService.findAllSpot(device);
//			PageInfo<CMesDeviceSpotT> pageInfo = new PageInfo<>(DeviceList, 5);
//			List<CMesLineT> Linelist = lineService.findAllLine(line);
//			request.setAttribute("pageInfo", pageInfo);
//			request.setAttribute("Linelist", Linelist);
//		}catch (Exception e) {
//			// TODO: handle exception
//		}
//		return "deviceManager_control/devicespot";
//	}
//
//
//	@RequestMapping("findlineById")
//	@ResponseBody
//	public  List<CMesDeviceT> findlineById(Integer lineId) {
//		return spotService.findlineById(lineId);
//	}
//
//	@ResponseBody
//	@RequestMapping("addDeviceSpot")
//	public JSONObject addDeviceSpot(HttpServletRequest request) {
//		JSONObject json = new JSONObject();
//		Integer lineId = Integer.parseInt(request.getParameter("lineId"));
//		String deviceId = request.getParameter("deviceId");
//		Integer status = Integer.parseInt(request.getParameter("status"));
//		String emp = request.getParameter("emp");
//		CMesDeviceSpotT spot = new CMesDeviceSpotT();
//		spot.setDeviceName(deviceId);
//		spot.setLineId(lineId);
//		spot.setStatus(status);
//		spot.setEmp(emp);
//		try {
//			deviceService.addSpot(spot);
//			json.put("code", 0);
//		}catch (ServicesException e) {
//			json.put("code", e.getCode());
//			json.put("msg", e.getMessage());
//		} catch (Exception e) {
//			json.put("code", -1);
//			json.put("msg", e.getMessage());
//		}
//		return json;
//	}
//
//	@RequestMapping("delDeviceSpot")
//	@ResponseBody
//	public  JSONObject delDeviceSpot(HttpServletRequest request) {
//		Integer id = Integer.parseInt(request.getParameter("id"));
//		JSONObject json = new JSONObject();
//		try {
//			deviceService.delSppot(id);
//			json.put("code", 0);
//		}catch (ServicesException e) {
//			json.put("code", e.getCode());
//			json.put("msg", e.getMessage());
//		} catch (Exception e) {
//			json.put("code", -1);
//			json.put("msg", e.getMessage());
//		}
//		return json;
//	}
//
//	@RequestMapping("findByDeviceSpotId")
//	@ResponseBody
//	public  JSONObject findByDeviceSpotId(HttpServletRequest request) {
//		Integer id = Integer.parseInt(request.getParameter("id"));
//		JSONObject json = new JSONObject();
//		try {
//			CMesDeviceSpotT device = deviceService.findSpotByid(id);
//			json.put("code", 0);
//			json.put("device", device);
//		}catch (ServicesException e) {
//			json.put("code", e.getCode());
//			json.put("msg", e.getMessage());
//		} catch (Exception e) {
//			json.put("code", -1);
//			json.put("msg", e.getMessage());
//		}
//		return json;
//	}
//
//	@RequestMapping("updateDeviceSpot")
//	@ResponseBody
//	public  JSONObject updateDeviceSpot(HttpServletRequest request) {
//		JSONObject json = new JSONObject();
//		Integer id = Integer.parseInt(request.getParameter("id"));
//		String deviceId = request.getParameter("deviceId2");
//		Integer lineId = Integer.parseInt(request.getParameter("lineId2"));
//		Integer status = Integer.parseInt(request.getParameter("status2"));
//		String emp = request.getParameter("emp2");
//		CMesDeviceSpotT spotT = new CMesDeviceSpotT();
//		spotT.setId(id);
//		spotT.setDeviceName(deviceId);
//		spotT.setLineId(lineId);
//		spotT.setStatus(status);
//		spotT.setEmp(emp);
//		try {
//			deviceService.updateSpot(spotT);
//			json.put("code", 0);
//		}catch (ServicesException e) {
//			json.put("code", e.getCode());
//			json.put("msg", e.getMessage());
//		} catch (Exception e) {
//			json.put("code", -1);
//			json.put("msg", e.getMessage());
//		}
//		return json;
//	}

}
