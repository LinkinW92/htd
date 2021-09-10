package com.skeqi.mes.controller.project;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

import com.skeqi.mes.service.lcy.GetReportFormsService;
import com.skeqi.mes.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesShiftsTeamT;
import com.skeqi.mes.service.project.CMesShiftTeamService;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.aop.OptionalLog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "api", produces = MediaType.APPLICATION_JSON)
@Api(value = "班次管理", description = "班次管理", produces = MediaType.APPLICATION_JSON)
public class CMesShiftTeamController {

	@Autowired
	CMesShiftTeamService service;

	@Autowired
	GetReportFormsService getReportFormsService;
	// 班次列表
	@RequestMapping(value = "findShiftTeam", method = RequestMethod.POST)
	@ApiOperation(value = "班次列表", notes = "班次列表")
	@ApiImplicitParams({ @ApiImplicitParam(name = "name", value = "名称", required = false, paramType = "query"),
			@ApiImplicitParam(name = "pages", value = "当前页", required = false, paramType = "query"),
			@ApiImplicitParam(name = "lineId", value = "产线id", required = false, paramType = "query")})
	public JSONObject findShiftTeam(HttpServletRequest request, String name, Integer pages, Integer lineId) {
		JSONObject json = new JSONObject();
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("name", name);
			map.put("lineId", lineId);
			List<CMesShiftsTeamT> findAllShift = service.findAllShift(map);
			if (pages == null || pages == 0) {
				findAllShift = service.findAllShift(map);
			} else {
				PageHelper.startPage(pages, 10);
				findAllShift = service.findAllShift(map);
				PageInfo<CMesShiftsTeamT> pageinfo = new PageInfo<CMesShiftsTeamT>(findAllShift, 5);
				json.put("pageNum", pageinfo.getTotal());
				findAllShift = pageinfo.getList();
			}
			json.put("code", 0);
			json.put("info", findAllShift);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code", 1);
		}
		return json;
	}

	// 添加班次列表
	@RequestMapping(value = "addShfitTeam", method = RequestMethod.POST)
	@ApiOperation(value = "添加班次", notes = "添加班次")
	@ApiImplicitParams({ @ApiImplicitParam(name = "name", value = "名称", required = true, paramType = "query"),
			@ApiImplicitParam(name = "startTime", value = "开始时间", required = true, paramType = "query"),
			@ApiImplicitParam(name = "endTime", value = "结束时间", required = true, paramType = "query"),
			@ApiImplicitParam(name = "lineName", value = "产线名称", required = true, paramType = "query"),
			@ApiImplicitParam(name = "dis", value = "备注", required = false, paramType = "query"),
			@ApiImplicitParam(name = "planTime", value = "计划时间", required = false, paramType = "query"),
			@ApiImplicitParam(name = "jumpTime", value = "是否跨时间", required = false, paramType = "query"), })
	@OptionalLog(module="生产", module2="班次管理", method="新增班次")
	public JSONObject addShfitTeam(HttpServletRequest request, CMesShiftsTeamT team) {
		JSONObject json = new JSONObject();
		Integer lineId = service.findByLineName(team.getLineName());
		Integer a = service.findByName(team.getName(), lineId);
		if (a != 0) {
			json.put("code", 1);
			json.put("msg", "当前班次已存在");
		} else {
			try {
				service.addShift(team);
				json.put("code", 0);
				json.put("msg", "添加成功");
			} catch (ServicesException e) {
				// TODO: handle exception
				json.put("code", 1);
				json.put("msg", e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				ToolUtils.errorLog(this, e, request);
				// TODO: handle exception
				json.put("code", 1);
				json.put("msg", "未知错误");
			}
		}

		return json;
	}

	// 添加班次列表
	@RequestMapping(value = "updateShiftTeam", method = RequestMethod.POST)
	@ApiOperation(value = "修改班次", notes = "修改班次")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query"),
			@ApiImplicitParam(name = "name", value = "名称", required = true, paramType = "query"),
			@ApiImplicitParam(name = "startTime", value = "开始时间", required = true, paramType = "query"),
			@ApiImplicitParam(name = "endTime", value = "结束时间", required = true, paramType = "query"),
			@ApiImplicitParam(name = "lineName", value = "产线名称", required = true, paramType = "query"),
			@ApiImplicitParam(name = "dis", value = "备注", required = true, paramType = "query"),
			@ApiImplicitParam(name = "planTime", value = "计划时间", required = false, paramType = "query"),
			@ApiImplicitParam(name = "jumpTime", value = "是否跨时间", required = false, paramType = "query"),})
	@OptionalLog(module="生产", module2="班次管理", method="编辑班次")
	public JSONObject updateShiftTeam(HttpServletRequest request, CMesShiftsTeamT team) {
		JSONObject json = new JSONObject();
		try {

			HttpSession session=request.getSession();
			// 班次班组Code查询
			String teamName = request.getParameter("teamName");
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			if (StringUtil.eqNu(teamName) && StringUtil.eqNu(startTime) && StringUtil.eqNu(endTime)) {
				CMesShiftsTeamT cMesShiftsTeamT = new CMesShiftsTeamT();
				cMesShiftsTeamT.setName(teamName);
				cMesShiftsTeamT.setStartTime(startTime);
				cMesShiftsTeamT.setEndTime(endTime);
				List<CMesShiftsTeamT> cMesShiftsTeamTS = getReportFormsService.shiftsTeamIdAndNameAndTime(cMesShiftsTeamT);
				if(cMesShiftsTeamTS.size()>0){
					team.setId(cMesShiftsTeamTS.get(0).getId());
					session.setAttribute("teamName", teamName);
					session.setAttribute("startTime", startTime);
					session.setAttribute("endTime", endTime);
				}else {
					json.put("code", 1);
					json.put("msg", "班次不存在");
					return json;
				}

			} else {
				CMesShiftsTeamT cMesShiftsTeamT = new CMesShiftsTeamT();
				cMesShiftsTeamT.setId(team.getId());
				List<CMesShiftsTeamT> cMesShiftsTeamTS = getReportFormsService.shiftsTeamIdAndNameAndTime(cMesShiftsTeamT);
				if(cMesShiftsTeamTS.size()>0){
					session.setAttribute("teamName", cMesShiftsTeamTS.get(0).getName());
					session.setAttribute("startTime", cMesShiftsTeamTS.get(0).getStartTime());
					session.setAttribute("endTime", cMesShiftsTeamTS.get(0).getEndTime());
				}else {
					json.put("code", 1);
					json.put("msg", "班次不存在");
					return json;
				}

			}
			service.updateShift(team);
			json.put("code", 0);
			json.put("msg", "修改成功");
		} catch (ServicesException e) {
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}

	// 修改班次烈性
	@RequestMapping(value = "deleteShiftTeam", method = RequestMethod.POST)
	@ApiOperation(value = "删除班次", notes = "删除班次")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query"), })
	@OptionalLog(module="生产", module2="班次管理", method="删除班次")
	public JSONObject deleteShiftTeam(HttpServletRequest request, Integer id) {
		JSONObject json = new JSONObject();
		try {
			HttpSession session=request.getSession();
			// 班次班组Code查询
			String teamName = request.getParameter("teamName");
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			if (StringUtil.eqNu(teamName) && StringUtil.eqNu(startTime) && StringUtil.eqNu(endTime)) {
				CMesShiftsTeamT cMesShiftsTeamT = new CMesShiftsTeamT();
				cMesShiftsTeamT.setName(teamName);
				cMesShiftsTeamT.setStartTime(startTime);
				cMesShiftsTeamT.setEndTime(endTime);
				List<CMesShiftsTeamT> cMesShiftsTeamTS = getReportFormsService.shiftsTeamIdAndNameAndTime(cMesShiftsTeamT);
				if(cMesShiftsTeamTS.size()>0){
					id=cMesShiftsTeamTS.get(0).getId();
					session.setAttribute("teamName", teamName);
					session.setAttribute("startTime", startTime);
					session.setAttribute("endTime", endTime);
				}else{
					json.put("code", 1);
					json.put("msg", "班次不存在");
					return json;
				}

			} else {
				CMesShiftsTeamT cMesShiftsTeamT = new CMesShiftsTeamT();
				cMesShiftsTeamT.setId(id);
				List<CMesShiftsTeamT> cMesShiftsTeamTS = getReportFormsService.shiftsTeamIdAndNameAndTime(cMesShiftsTeamT);
				if(cMesShiftsTeamTS.size()>0){
					session.setAttribute("teamName", cMesShiftsTeamTS.get(0).getName());
					session.setAttribute("startTime", cMesShiftsTeamTS.get(0).getStartTime());
					session.setAttribute("endTime", cMesShiftsTeamTS.get(0).getEndTime());
				}else {
					json.put("code", 1);
					json.put("msg", "班次不存在");
					return json;
				}

			}
			service.delShift(id);
			json.put("code", 0);
			json.put("msg", "删除成功");
		} catch (ServicesException e) {
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}

	// 产线列表
	@RequestMapping(value = "findAllLine", method = RequestMethod.POST)
	@ApiOperation(value = "产线列表", notes = "产线列表")
	public JSONObject findAllLine(HttpServletRequest request, Integer paibanStatus, Integer andengStatus) {
		JSONObject json = new JSONObject();
		try {
			List<CMesLineT> findAllLine = service.findAllLine();
			json.put("code", 0);
			json.put("info", findAllLine);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code", 1);
		}
		return json;
	}

	@RequestMapping(value = "findAllLinel", method = RequestMethod.POST)
	@ApiOperation(value = "产线列表(是否安灯，排班)", notes = "产线列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "paibanStatus", value = "是否排版", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "andengStatus", value = "是否安灯", required = false, paramType = "query", dataType = "int"), })
	public JSONObject findAllLinel(HttpServletRequest request, Integer paibanStatus, Integer andengStatus) {
		JSONObject json = new JSONObject();
		try {
			List<CMesLineT> findAllLine = service.findAllLineL(paibanStatus, andengStatus);
			json.put("code", 0);
			json.put("info", findAllLine);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code", 1);
		}
		return json;
	}


	@RequestMapping(value = "findShiftLineName", method = RequestMethod.POST)
	@ApiOperation(value = "查询当前班次存在的产线", notes = "查询当前班次存在的产线")
	public JSONObject findShiftLineName(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			List<CMesLineT> findAllLine = service.findShiftLineName();
			json.put("code", 0);
			json.put("info", findAllLine);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code", 1);
		}
		return json;
	}
}
