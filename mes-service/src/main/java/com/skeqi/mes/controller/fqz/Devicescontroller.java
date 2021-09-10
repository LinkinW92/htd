package com.skeqi.mes.controller.fqz;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skeqi.mes.common.lcy.AllFronts;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.PMesStationPassEqStatusT;
import com.skeqi.mes.service.fqz.LineServiceImpl;
import com.skeqi.mes.util.ToolUtils;



@Controller
@RequestMapping("skq")
public class Devicescontroller {

	@Autowired
	private LineServiceImpl lineserviceimpl;

	@RequestMapping("/devicestate")
	public String devicestate(HttpServletRequest request){
		List<CMesLineT> listLine = lineserviceimpl.listLine();
		Integer id = listLine.get(0).getId();
		request.setAttribute("info",listLine);
		request.setAttribute("lineid",id);
		return "deviceManager_control/devicestate";
	}

	@RequestMapping("/getstation")
	public String getstation(HttpServletRequest request){
		String parameter = request.getParameter("deviceType");
		int lineid = Integer.parseInt(parameter);
		List<PMesStationPassEqStatusT> listStatus = lineserviceimpl.listStatus(lineid);
		List<CMesLineT> listLine = lineserviceimpl.listLine();
		Integer id = listLine.get(0).getId();
		request.setAttribute("listStatus",listStatus);
		request.setAttribute("info",listLine);
		request.setAttribute("lineid",parameter);
		return "deviceManager_control/devicestate";
	}

	@RequestMapping(value="/stopAll",produces="text/plain;charset=utf-8")
	@ResponseBody
	public String stopAll(String id, HttpServletRequest request){
		String flag = "停线成功";
		try {
			String findName = lineserviceimpl.findName(Integer.parseInt(id));
			AllFronts.stopAll(findName);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			flag = "停线失败";
		}
		return flag;
	}

	@RequestMapping(value="/startAll",produces="text/plain;charset=utf-8")
	@ResponseBody
	public String startAll(String id, HttpServletRequest request){
		String flag = "恢复成功";
		try {
			String findName = lineserviceimpl.findName(Integer.parseInt(id));
			AllFronts.startAll(findName);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			flag = "恢复失败";
		}
		return flag;
	}
}
