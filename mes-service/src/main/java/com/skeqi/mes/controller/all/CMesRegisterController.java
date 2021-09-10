package com.skeqi.mes.controller.all;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.pojo.RegisterT;
import com.skeqi.mes.service.all.CMesLineTService;
import com.skeqi.mes.service.all.CMesStationTService;
import com.skeqi.mes.service.all.SMesRegisterTService;
import com.skeqi.mes.service.wll.RegisterTService;
import com.skeqi.mes.util.ToolUtils;


/***
 *
 * @author 程序注册 1
 *
 */

@Controller
@RequestMapping("skq")
public class CMesRegisterController {

	@Autowired
	SMesRegisterTService registerService;
	@Autowired
	CMesLineTService lineService;
	@Autowired
	CMesStationTService stationService;


	@RequestMapping("addRegisterT")
	@ResponseBody
	public  JSONObject addRegisterT(HttpServletRequest request) throws Exception{
		JSONObject json = new JSONObject();
		InetAddress ia = InetAddress.getLocalHost();
		System.err.println("id====="+ia);
		byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
		System.err.println("mac====="+mac);
		StringBuffer sb = new StringBuffer("");
		for(int i=0; i<mac.length; i++) {
			if(i!=0) {
				sb.append("-");
			}
			//字节转换为整�?
			int temp = mac[i]&0xff;
			String str = Integer.toHexString(temp);
			System.err.println("str==="+str);
			if(str.length()==1) {
				sb.append("0"+str);
			}else {
				sb.append(str);
			}
		}
		String ip = request.getParameter("ip");
		String lineId = request.getParameter("lineId");
		String stationId = request.getParameter("stationId");
		String hostName = request.getParameter("hostName");

		RegisterT register = new RegisterT();
		register.setHostname(hostName);
		register.setIp(ip);
		register.setLineId(lineId);
		register.setStationId(Integer.parseInt(stationId));
		register.setMac(sb.toString());
		System.err.println(register);
		try {
			registerService.addRegisterT(register);
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


	@RequestMapping("delRegisterT")
	@ResponseBody
	public  JSONObject delRegisterT(HttpServletRequest request){
		String id = request.getParameter("id");
		JSONObject json = new JSONObject();
		try {
			registerService.delRegisterT(Integer.parseInt(id));
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



	@RequestMapping("registerList")
	public String registerList(HttpSession session,HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page){
		JSONObject json = new JSONObject();
		RegisterT register = new RegisterT();
		CMesLineT line = new CMesLineT();
		CMesStationT station = new CMesStationT();
		try {
		PageHelper.startPage(page,8);
		List<RegisterT> registerList;
		registerList = registerService.findAllRegisterT(null);
		for (RegisterT registerT : registerList) {
			System.err.println(registerT.getDt());
		}
		PageInfo<RegisterT> pageInfo = new PageInfo<>(registerList,5);
		List<CMesLineT> lineList = lineService.findAllLine(null);
		List<CMesStationT> stationList = stationService.findAllStation(null);
		json.put("code", 0);
		request.setAttribute("lineList", lineList);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("stationList", stationList);
		}catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}
		return "base_control/programRegister";
	}

	@RequestMapping("updateRegisterT")
	@ResponseBody
	public  JSONObject editStation(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String ip = request.getParameter("ip");
		String lineId = request.getParameter("lineId");
		String stationId = request.getParameter("stationId");
		String hostName = request.getParameter("hostName");
		String id = request.getParameter("id");

		RegisterT register = new RegisterT();
		register.setId(Integer.parseInt(id));
		register.setIp(ip);
		register.setLineId(lineId);
		register.setStationId(Integer.parseInt(stationId));
		register.setHostname(hostName);
		try {
			registerService.updadteRegisterT(register);
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



	 @RequestMapping("findStationByLineId")
	 @ResponseBody
	public JSONObject findStationByLineId(HttpServletRequest request) {
		 Integer id = Integer.parseInt(request.getParameter("id"));
		 JSONObject json = new JSONObject();
		 CMesStationT st = new CMesStationT();
		 List<CMesStationT> stationList = new ArrayList<CMesStationT>();
		 try {
			List<CMesStationT> list = stationService.findAllStation(st);
			for (CMesStationT cMesStationT : list) {
				if(id ==cMesStationT.getLineId()) {

					stationList.add(cMesStationT);

				}
			}
		} catch (ServicesException e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}

		 json.put("stationList", stationList);
		return json;
	}


	 @RequestMapping("toUpdateRegister")
	 @ResponseBody
	 public  JSONObject toUpdateRegister(HttpServletRequest request){

		 String id = request.getParameter("id");

		 JSONObject json = new JSONObject();

		try {
			List<RegisterT>	list = registerService.findAllRegisterT(null);
			for (RegisterT registerT : list) {

				if(id.equals(registerT.getId()+"")) {

					json.put("register", registerT);

					Integer lineId =Integer.parseInt(registerT.getLineId());
					 CMesStationT st = new CMesStationT();
					 List<CMesStationT> stationList = new ArrayList<CMesStationT>();
						List<CMesStationT> sList = stationService.findAllStation(st);
						for (CMesStationT cMesStationT : sList) {
							if(lineId ==cMesStationT.getLineId()) {

								stationList.add(cMesStationT);
							}
						}
					 json.put("stationList", stationList);
				}

			}
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
