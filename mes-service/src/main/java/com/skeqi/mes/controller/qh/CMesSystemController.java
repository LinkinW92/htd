package com.skeqi.mes.controller.qh;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesSystem;
import com.skeqi.mes.pojo.CMesSystemT;
import com.skeqi.mes.service.all.CMesSystemService;
import com.skeqi.mes.util.ToolUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "api/system", produces = MediaType.APPLICATION_JSON)
@Api(value = "系统设置管理", description = "系统设置管理", produces = MediaType.APPLICATION_JSON)
public class CMesSystemController {

	@Autowired
	private CMesSystemService cSystemService;

	// 系统数据
	@RequestMapping(value = "/findByAll", method = RequestMethod.POST)
	@ApiOperation(value = "系统数据", notes = "系统数据")
	@ResponseBody
	public JSONObject findByAll(HttpServletRequest request) throws ServicesException {
		List<CMesSystem> cSystems = cSystemService.findByAll(null);

		JSONObject json = new JSONObject();
		try {

			json.put("code", 0);
			json.put("cSystems", cSystems);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}

	// 系统数据
	@RequestMapping(value = "updateSystem", method = RequestMethod.POST)
	@ApiOperation(value = "修改系统数据", notes = "修改系统数据")
	@ResponseBody
	public JSONObject updateSystem(@ModelAttribute CMesSystemT cMesSystemT) throws ServicesException {
		JSONObject json = new JSONObject();
		if (cMesSystemT != null) {
			if (cMesSystemT.getPasswrod() != null) {
				CMesSystem cMesSystem = new CMesSystem();
				cMesSystem.setId(1);
				cMesSystem.setParameter(cMesSystemT.getPasswrod());
				cSystemService.updateSystem(cMesSystem);
			}
			if (cMesSystemT.getVersion() != null) {
				CMesSystem cMesSystem = new CMesSystem();
				cMesSystem.setId(2);
				cMesSystem.setParameter(cMesSystemT.getVersion());
				cSystemService.updateSystem(cMesSystem);
			}
			if (cMesSystemT.getLibrary() != null) {
				CMesSystem cMesSystem = new CMesSystem();
				cMesSystem.setId(3);
				cMesSystem.setParameter(cMesSystemT.getLibrary());
				cSystemService.updateSystem(cMesSystem);
			}
			if (cMesSystemT.getApprove() != null) {
				CMesSystem cMesSystem = new CMesSystem();
				cMesSystem.setId(4);
				cMesSystem.setParameter(cMesSystemT.getApprove());
				cSystemService.updateSystem(cMesSystem);
			}

			if (cMesSystemT.getName() != null) {
				CMesSystem cMesSystem = new CMesSystem();
				cMesSystem.setId(6);
				System.err.println(cMesSystemT.getName());
				cMesSystem.setParameter(cMesSystemT.getName());
				cSystemService.updateSystem(cMesSystem);
			}
			if (cMesSystemT.getUrl() != null) {
				CMesSystem cMesSystem1 = new CMesSystem();
				CMesSystem cMesSystem = new CMesSystem();
				cMesSystem.setId(7);
				String url = cMesSystemT.getUrl();
				if (url=="1") {
					cMesSystem1.setId(5);
					cMesSystem1.setParameter("安灯系统");
					cSystemService.updateSystem(cMesSystem1);
				}else {
					cMesSystem1.setId(5);
					cMesSystem1.setParameter("琦航系统");
					cSystemService.updateSystem(cMesSystem1);
				}
				cMesSystem.setParameter(url);
				cSystemService.updateSystem(cMesSystem);
			}
			json.put("code", 0);
			json.put("value", "修改成功");
		} else {
			json.put("code", 1);
			json.put("value", "修改失败");
		}

		return json;
	}

	// 系统数据
	@RequestMapping(value = "updateUrl", method = RequestMethod.GET)
	@ApiOperation(value = "修改url", notes = "修改url")
	public ModelAndView updateUrl() throws ServicesException {
		List<CMesSystem> findByAll = cSystemService.findByAll("通用.URL");
		CMesSystem cMesSystem = findByAll.get(0);
		String url = null;
		if (cMesSystem.getParameter() =="1") {
			url = "redirect:http://192.168.7.17:8080/MES_System/view/html/index.html";
		}else if(cMesSystem.getParameter() =="2"){
			url = "redirect:http://192.168.7.17:8080/MES_System/view/skq/index.html";
		}
		return new ModelAndView(url);
	}

/*	    @RequestMapping(value = "openExe", method = RequestMethod.POST)
		@ApiOperation(value = "打开exe", notes = "打开exe")
		@ApiImplicitParam(paramType = "query", name = "fileLac", value = "地址", required = true, dataType = "String")
		public Process openExe(String fileLac) throws Exception {
			 Runtime rt = Runtime.getRuntime();
		       return  rt.exec(fileLac);
		}
	*/



}
