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
import com.skeqi.mes.pojo.CMesEmpTeamT;
import com.skeqi.mes.pojo.CMesShiftsTeamT;
import com.skeqi.mes.service.all.ProduceService;
import com.skeqi.mes.util.ToolUtils;

/***
 *
 * @author ENS 班组管理
 *
 */

@Controller
@RequestMapping("skq")
public class CMesTeamManagerController {

	@Autowired
	ProduceService produceService;


	/**
	 * 班组列表
	 */
	@RequestMapping("teamManager")
	public String teamManager(HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page) {
		HashMap<String, Object> map = new HashMap<>();
		PageHelper.startPage(page,8);
		CMesEmpTeamT emp = new CMesEmpTeamT();
		CMesShiftsTeamT shift = new CMesShiftsTeamT();
		try {
		List<CMesEmpTeamT> empTeamList = produceService.findAllTeam(emp);
		PageInfo<CMesEmpTeamT> pageInfo = new PageInfo<>(empTeamList,5);
		List<CMesShiftsTeamT> shiftsTeamList = produceService.findAllShift(shift);
		request.setAttribute("shiftsTeamList", shiftsTeamList);
		request.setAttribute("pageInfo", pageInfo);
		}catch (Exception e) {
			// TODO: handle exception
		}
		return "production_control/teamManager";
	}




	/**
	 * 添加班组
	 */
	@RequestMapping("addEmpTeam")
	@ResponseBody
	public  JSONObject addEmpTeam(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String name = request.getParameter("name").trim();
		String shiftsTeamId = request.getParameter("shiftsTeamId");
		String dis = request.getParameter("dis");
		String emps = request.getParameter("emps");
		CMesEmpTeamT team = new CMesEmpTeamT();
		team.setName(name);
		team.setDis(dis);
		team.setShiftsTeamId(Integer.parseInt(shiftsTeamId));
		team.setEmps(emps);

			try {
				produceService.addTeam(team);
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


	/**
	 * 通过ID查询班组信息
	 */
	@RequestMapping("toEditEmpTeam")
	@ResponseBody
	public  JSONObject toEditEmpTeam(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");
		try {
			CMesEmpTeamT team = produceService.findTeamByid(Integer.parseInt(id));
			json.put("empTeam", team);
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
	 * 修改班组
	 */
	@RequestMapping("editEmpTeam")
	public @ResponseBody Map<String, Object> editEmpTeam(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");
		String name = request.getParameter("name").trim();
		String shiftsTeamId = request.getParameter("shiftsTeamId");
		String dis = request.getParameter("dis");
		String emps = request.getParameter("emps");
		CMesEmpTeamT team = new CMesEmpTeamT();
		team.setName(name);
		team.setDis(dis);
		team.setShiftsTeamId(Integer.parseInt(shiftsTeamId));
		team.setEmps(emps);
		team.setShiftsTeamId(Integer.parseInt(shiftsTeamId));
		team.setId(Integer.parseInt(id));
		try {
			produceService.updateTeam(team);
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
	 * 删除班组
	 */

	@RequestMapping("delEmpTeam")
	@ResponseBody
	public  JSONObject delEmpTeam(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");
		try {
			produceService.delTeam(Integer.parseInt(id));
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
