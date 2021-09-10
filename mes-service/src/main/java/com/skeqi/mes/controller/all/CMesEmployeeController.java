package com.skeqi.mes.controller.all;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.skeqi.mes.pojo.CMesEmpT;
import com.skeqi.mes.pojo.CMesEmpTeamT;
import com.skeqi.mes.pojo.CMesEmpTypeT;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.pojo.RoleT;
import com.skeqi.mes.service.all.CMesLineTService;
import com.skeqi.mes.service.all.CMesRoleTService;
import com.skeqi.mes.service.all.CMesStationTService;
import com.skeqi.mes.service.all.ProduceService;
import com.skeqi.mes.util.ToolUtils;

/***
 *
 * @author ENS 员工管理
 *
 */

@Controller
@RequestMapping("skq")
public class CMesEmployeeController {

	@Autowired
	ProduceService produceService;
	@Autowired
	CMesLineTService lineService;
	@Autowired
	CMesStationTService stationService;
	@Autowired
	CMesRoleTService roleService;


	/**
	 * 员工列表
	 */
	@RequestMapping("employeeManager")
	public String employeeManager(HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page) {
		String ename = request.getParameter("ename");
		PageHelper.startPage(page,10);
		CMesEmpT emp = new CMesEmpT();
		emp.setEmpName(ename);
		CMesEmpTeamT empTeam = new CMesEmpTeamT();
		CMesLineT line = new CMesLineT();
		CMesEmpTypeT empType = new CMesEmpTypeT();
		CMesStationT station = new CMesStationT();
		RoleT role = new RoleT();

		try {
		List<CMesEmpT> empList = produceService.findAllEmp(emp);
		PageInfo<CMesEmpT> pageInfo = new PageInfo<>(empList,5);
		List<CMesEmpTeamT> empTeamList = produceService.findAllTeam(empTeam);
		List<CMesLineT> lineList = lineService.findAllLine(line);
		List<CMesEmpTypeT> empTypeList = produceService.findAllEmpType(empType);

		if(lineList.size()>0){
			station.setLineId(lineList.get(0).getId());
		}
		List<CMesStationT> stationList = stationService.findAllStation(station);

		List<RoleT> roleList = roleService.findAllRole(role);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("stationList", stationList);
		request.setAttribute("empTeamList", empTeamList);
		request.setAttribute("lineList", lineList);
		request.setAttribute("roleList", roleList);
		request.setAttribute("empTypeList", empTypeList);
		request.setAttribute("ename",ename);
		}catch (Exception e) {
			// TODO: handle exception
		}
		return "production_control/employeeManager";

	}

	@RequestMapping("stationListsss")
	@ResponseBody
	public  List<CMesStationT> findlineById(Integer lineId) {
		CMesStationT station = new CMesStationT();
		station.setLineId(lineId);

		try {
			return stationService.findAllStation(station);
		} catch (ServicesException e) {
			// TODO Auto-generated catch block
			return null;
		}

	}

	/**
	 * 添加员工
	 * @return
	 */
	@RequestMapping("addEmp")
	@ResponseBody
	public JSONObject addEmp(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String empNo = request.getParameter("empNo").trim();
		String empName = request.getParameter("empName").trim();
		String empSex = request.getParameter("empSex");
		String empType = request.getParameter("empType");
		String empTp = request.getParameter("empTp");
		String dt = request.getParameter("dt");
		String empDepartment = request.getParameter("empDepartment");
		String empTeamId = request.getParameter("empTeamId");
		String lineId = request.getParameter("lineId");
		String empVr = request.getParameter("empVr");
		String empMail = request.getParameter("empMail");
		String stationId = request.getParameter("stationId");
		String isWhole = request.getParameter("isWhole");
		String passWord = request.getParameter("passWord");

		CMesEmpT emp = new CMesEmpT();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		//emp.setRoleId(Integer.parseInt(roleId));
		emp.setEmpMail(empMail);
		emp.setStationId(stationId);
		emp.setpassword(passWord);
		emp.setEmpNo(empNo);
		emp.setEmpName(empName);
		emp.setEmpSex( Integer.parseInt(empSex));
		emp.setEmpType(Integer.parseInt(empType));
		emp.setEmpTp(empTp);
		emp.setEmpDepartment(empDepartment);
		emp.setEmpTeamId(Integer.parseInt(empTeamId));
		emp.setLineId(Integer.parseInt(lineId));
		emp.setEmpVr(empVr);
		emp.setIsWhole(isWhole);
		try {
			emp.setDt(sdf.parse(dt));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}

		try {
			produceService.addEmp(emp);
			json.put("code", 0);
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
		return json;
	}
	//回显
	@RequestMapping("toUpdateEmp")
	@ResponseBody
	public  JSONObject toUpdateEmp(HttpServletRequest request){
		String id = request.getParameter("id");
		JSONObject json = new JSONObject();
		try {
			CMesEmpT c = produceService.findEmpByid(Integer.parseInt(id));
			json.put("code", 0);
			json.put("emp", c);
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
		return json;
	}

	/**
	 * 修改员工信息
	 */
	@RequestMapping("editEmp")
	@ResponseBody
	public  JSONObject editEmp(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String empNo = request.getParameter("empNo1").trim();
		String empName = request.getParameter("empName1").trim();
		String empSex = request.getParameter("empSex1");
		String empType = request.getParameter("empType1");
		String empTp = request.getParameter("empTp1");
		String dt = request.getParameter("dt1");
		String empDepartment = request.getParameter("empDepartment1");
		String empTeamId = request.getParameter("empTeamId1");
		String lineId = request.getParameter("lineId1");
		String empVr = request.getParameter("empVr1");
		String id = request.getParameter("id");
		String empMail = request.getParameter("empMail1");
		String stationId = request.getParameter("stationId1");
		String isWhole = request.getParameter("isWhole");
		String passWord = request.getParameter("passWord");

		CMesEmpT emp = new CMesEmpT();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		emp.setIsWhole(isWhole);
		emp.setEmpMail(empMail);
		emp.setStationId(stationId);
		emp.setEmpNo(empNo);
		emp.setEmpName(empName);
		emp.setEmpSex( Integer.parseInt(empSex));
		emp.setEmpType(Integer.parseInt(empType));
		emp.setEmpTp(empTp);
		emp.setEmpDepartment(empDepartment);
		emp.setEmpTeamId(Integer.parseInt(empTeamId));
		emp.setLineId(Integer.parseInt(lineId));
		emp.setEmpVr(empVr);
		emp.setId(Integer.parseInt(id));
		emp.setpassword(passWord);

		try {
			emp.setDt(sdf.parse(dt));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		try {
			produceService.updateEmp(emp);
			json.put("code", 0);
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
		return json;
	}

	@RequestMapping("delEmp")
	@ResponseBody
	public  JSONObject delEmp(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");
		try {
			produceService.delEmp(Integer.parseInt(id));
			json.put("code", 0);
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
		return json;
	}

}
