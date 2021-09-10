package com.skeqi.mes.controller.all;


import java.util.List;
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
import com.skeqi.mes.pojo.CMesEmpTypeT;
import com.skeqi.mes.service.all.CMesLineTService;
import com.skeqi.mes.service.all.CMesRoleTService;
import com.skeqi.mes.service.all.CMesStationTService;
import com.skeqi.mes.service.all.ProduceService;

/***
 *
 * @author ENS 员工类型管理
 *
 */

@Controller
@RequestMapping("skq")
public class CMesEmpTypeController {

	@Autowired
	ProduceService produceService;
	@Autowired
	CMesLineTService lineService;
	@Autowired
	CMesStationTService stationService;
	@Autowired
	CMesRoleTService roleService;


	/**
	 * 员工类型管理
	 * @param request
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("empTypeList")
	public String empTypeList(HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page) throws Exception {

		PageHelper.startPage(page,8);
		CMesEmpTypeT emp = new CMesEmpTypeT();
		List<CMesEmpTypeT> empTypeList = null;
		try {
			 empTypeList = produceService.findAllEmpType(emp);
			PageInfo<CMesEmpTypeT> pageInfo = new PageInfo<>(empTypeList,5);
			request.setAttribute("pageInfo", pageInfo);
		}catch (Exception e) {
			// TODO: handle exception
		}
		return "production_control/employeeTypeManager";
	}
	/**
	 * 添加员工类型
	 * @param request
	 * @return
	 */
	@RequestMapping("addEmpType")
	@ResponseBody
	public  JSONObject addEmpType(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String empType = request.getParameter("empType").trim();
		String dis = request.getParameter("dis").trim();

		CMesEmpTypeT emp = new CMesEmpTypeT();
		emp.setEmpType(empType);
		emp.setDis(dis);
		try {
			produceService.addEmpType(emp);
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

	@RequestMapping("toUpdateEmpType")
	@ResponseBody
	public  JSONObject toUpdateEmpType(HttpServletRequest request){
		String id = request.getParameter("id");
		JSONObject json = new JSONObject();
		try {
			CMesEmpTypeT empType = produceService.findEmpTypeByid(Integer.parseInt(id));
			json.put("code", 0);
			json.put("empTypes", empType);
		}catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}
		return json;
	}

	@RequestMapping("editEmpType")
	@ResponseBody
	public  JSONObject editEmpType(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String empType = request.getParameter("empType").trim();
		String dis = request.getParameter("dis").trim();
		String id = request.getParameter("id").trim();
		CMesEmpTypeT emp = new CMesEmpTypeT();
		emp.setEmpType(empType);
		emp.setDis(dis);
		emp.setId(Integer.parseInt(id));
		try {
			produceService.updateEmpType(emp);
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

	@RequestMapping("delEmpType")
	@ResponseBody
	public JSONObject delEmpType(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");

		try {
			produceService.delEmpType(Integer.parseInt(id));
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
