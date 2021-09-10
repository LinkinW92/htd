package com.skeqi.mes.controller.dgl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.CMesDeviceSpotT;
import com.skeqi.mes.pojo.CMesDeviceT;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.service.dgl.CMesDeviceSpotService;

/**
 * 设备点检
 *
 * @author : FQZ
 * @Package: com.skeqi.commen.dgl
 * @date : 2019年10月10日 下午3:05:37
 */

@Controller
@RequestMapping("devices")
public class DeciveSpotController {
	@Autowired
	CMesDeviceSpotService spotService;

	@RequestMapping("/devicespotlist")
	public String devicelist(HttpServletRequest request,
			@RequestParam(required = false, defaultValue = "1", value = "page") Integer page) {
		Map<String, Object> map = new HashMap<String, Object>();
		String deviceName = request.getParameter("deviceName");
		String lineId = request.getParameter("lineId");
		request.setAttribute("deviceName", deviceName);
		request.setAttribute("lineId", lineId);
		map.put("deviceName", deviceName);
		map.put("lineId", lineId);
		PageHelper.startPage(page, 8);
		List<CMesDeviceSpotT> DeviceList = spotService.findDeviceSpotList(map);
		PageInfo<CMesDeviceSpotT> pageInfo = new PageInfo<>(DeviceList, 5);
		List<CMesLineT> Linelist = spotService.lineList();
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("Linelist", Linelist);
		return "deviceManager_control/devicespot";
	}


	@RequestMapping("findlineById")
	public @ResponseBody List<CMesDeviceT> findlineById(Integer lineId) {
		return spotService.findlineById(lineId);
	}

	@ResponseBody
	@RequestMapping("addDeviceSpot")
	public Integer addDeviceSpot(HttpServletRequest request) {

		Integer lineId = Integer.parseInt(request.getParameter("lineId"));
		String deviceId = request.getParameter("deviceId");
		Integer status = Integer.parseInt(request.getParameter("status"));
		String emp = request.getParameter("emp");
		CMesDeviceSpotT spot = new CMesDeviceSpotT();
		spot.setDeviceName(deviceId);
		spot.setLineId(lineId);
		spot.setStatus(status);
		spot.setEmp(emp);
		if (spotService.addDeviceSpot(spot) > 0) {
			return 1;
		}
		return 0;
	}

	@RequestMapping("delDeviceSpot")
	public @ResponseBody Integer delDeviceSpot(HttpServletRequest request) {
		Integer id = Integer.parseInt(request.getParameter("id"));
		return spotService.delDeviceSpot(id);
	}

	@RequestMapping("findByDeviceSpotId")
	public @ResponseBody CMesDeviceSpotT findByDeviceSpotId(HttpServletRequest request) {
		Integer id = Integer.parseInt(request.getParameter("id"));
		return spotService.findByDeviceSpotId(id);
	}

	@RequestMapping("updateDeviceSpot")
	public @ResponseBody Integer updateDeviceSpot(HttpServletRequest request) {
		Integer id = Integer.parseInt(request.getParameter("id"));
		String deviceId = request.getParameter("deviceId2");
		Integer lineId = Integer.parseInt(request.getParameter("lineId2"));
		Integer status = Integer.parseInt(request.getParameter("status2"));
		String emp = request.getParameter("emp2");
		CMesDeviceSpotT spotT = new CMesDeviceSpotT();
		spotT.setId(id);
		spotT.setDeviceName(deviceId);
		spotT.setLineId(lineId);
		spotT.setStatus(status);
		spotT.setEmp(emp);
		if (spotService.updateDeviceSpot(spotT) > 0) {
			return 1;
		}
		return 0;
	}

}
