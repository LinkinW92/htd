package com.skeqi.mes.controller.all;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skeqi.mes.common.lcy.AllFronts;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.PMesStationPassEqStatusT;
import com.skeqi.mes.service.all.CMesDeviceAllService;
import com.skeqi.mes.service.all.CMesLineTService;
import com.skeqi.mes.service.fqz.LineServiceImpl;

//设备状态监控
public class CMesdevicestateController {



	@Autowired
	private CMesLineTService lineService;

	@Autowired
	private CMesDeviceAllService deviceService;


//	@RequestMapping("/devicestate")
//	public String devicestate(HttpServletRequest request){
//		CMesLineT line = new CMesLineT();
//		try {
//		List<CMesLineT> listLine = lineService.findAllLine(line);
//		Integer id = listLine.get(0).getId();
//		request.setAttribute("info",listLine);
//		request.setAttribute("lineid",id);
//		}catch (Exception e) {
//			// TODO: handle exception
//		}
//		return "deviceManager_control/devicestate";
//	}
//
//	@RequestMapping("/getstation")
//	public String getstation(HttpServletRequest request){
//		String parameter = request.getParameter("deviceType");
//		int lineid = Integer.parseInt(parameter);
//		CMesLineT line = new CMesLineT();
//		List<PMesStationPassEqStatusT> listStatus = deviceService.
//				lineserviceimpl.listStatus(lineid);
//		List<CMesLineT> listLine =  lineService.findAllLine(line);
//		Integer id = listLine.get(0).getId();
//		request.setAttribute("listStatus",listStatus);
//		request.setAttribute("info",listLine);
//		request.setAttribute("lineid",parameter);
//		return "deviceManager_control/devicestate";
//	}
//
//	@RequestMapping(value="/stopAll",produces="text/plain;charset=utf-8")
//	@ResponseBody
//	public String stopAll(String id){
//		String flag = "停线成功";
//		try {
//			String findName = lineserviceimpl.findName(Integer.parseInt(id));
//			AllFronts.stopAll(findName);
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			flag = "停线失败";
//		}
//		return flag;
//	}
//
//	@RequestMapping(value="/startAll",produces="text/plain;charset=utf-8")
//	@ResponseBody
//	public String startAll(String id){
//		String flag = "恢复成功";
//		try {
//			String findName = lineserviceimpl.findName(Integer.parseInt(id));
//			AllFronts.startAll(findName);
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			flag = "恢复失败";
//		}
//		return flag;
//	}

}
