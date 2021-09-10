package com.skeqi.mes.controller.project;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ParameterNullException;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.mapper.qh.CmesScheduling;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.RMesPlanT;
import com.skeqi.mes.pojo.project.CMesAndonPlanT;
import com.skeqi.mes.pojo.project.CMesPlan;
import com.skeqi.mes.pojo.project.CMesScheduling;
import com.skeqi.mes.pojo.project.CMesSchedulingL;
import com.skeqi.mes.pojo.project.CMesWorkorderT;
import com.skeqi.mes.pojo.project.Scheduling;
import com.skeqi.mes.service.all.CMesLineTService;
import com.skeqi.mes.service.project.CMesAndonPlanService;
import com.skeqi.mes.service.project.CMesSchedulingService;
import com.skeqi.mes.util.ToolUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller
@RestController
@RequestMapping(value = "api", produces = MediaType.APPLICATION_JSON)
@Api(value = "排班", description = "排班", produces = MediaType.APPLICATION_JSON)
public class CMesSchedulingController {

	@Autowired
	CMesSchedulingService service;

	@Autowired
	CMesAndonPlanService  cPlanService;

	@Autowired
	CMesLineTService cMesLineTService;

	@RequestMapping(value = "addScheduling", method = RequestMethod.POST)
	@ApiOperation(value = "添加排班表", notes = "添加排班表")
	@ApiImplicitParams({ @ApiImplicitParam(name = "shiftId", value = "班次id", required = false, paramType = "query"),
			@ApiImplicitParam(name = "teamId", value = "班组id", required = false, paramType = "query"),
			@ApiImplicitParam(name = "lineId", value = "产线id", required = false, paramType = "query"),
			@ApiImplicitParam(name = "number", value = "数量", required = false, paramType = "query"),
			@ApiImplicitParam(name = "dt", value = "时间", required = false, paramType = "query"),

	})
	public JSONObject addScheduling(HttpServletRequest request, Integer shiftId, Integer teamId, Integer number, String dt, Integer lineId) {
		JSONObject json = new JSONObject();
		try {
			service.addSchebuling(shiftId, teamId, number, dt, lineId);
			json.put("code", 0);
			json.put("msg", "添加成功");
		} catch (ServicesException e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
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

	@RequestMapping(value = "delScheduling", method = RequestMethod.POST)
	@ApiOperation(value = "删除排班表", notes = "删除排班表")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "排班id", required = false, paramType = "query"),

	})
	public JSONObject delScheduling(HttpServletRequest request, Integer id) {
		JSONObject json = new JSONObject();
		try {
			service.delSche(id);
			json.put("code", 0);
			json.put("msg", "删除成功");
		} catch (ServicesException e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
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

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "findAllScheduling", method = RequestMethod.POST)
	@ApiOperation(value = "排班列表", notes = "排班列表")
	@ApiImplicitParams({ @ApiImplicitParam(name = "pages", value = "当前页", required = false, paramType = "query"),
			@ApiImplicitParam(name = "lineId", value = "产线名称", required = false, paramType = "query"), })
	public JSONObject findAllScheduling(HttpServletRequest request, Integer pages, Integer lineId) {
		JSONObject json = new JSONObject();
		// 查询产线ID
		// Integer lineId=service.findByLineId(lineName);
		try {
			Set<Map<String, Object>> findAllSchebuling = null;
			if (pages == null || pages == 0) {
				findAllSchebuling = service.findAllSchebuling(lineId);
			} else {
				PageHelper.startPage(pages, 10);
				findAllSchebuling = service.findAllSchebuling(lineId);
				PageInfo<Map<String, Object>> pageinfo = new PageInfo<Map<String, Object>>(
						(List<Map<String, Object>>) findAllSchebuling, 5);
				json.put("pageNum", pageinfo.getTotal());
				findAllSchebuling = (Set<Map<String, Object>>) pageinfo.getList();
			}
			json.put("code", 0);
			json.put("info", findAllSchebuling);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code", 1);
		}
		return json;
	}

	@RequestMapping(value = "updateSchedulingL", method = RequestMethod.POST)
	@ApiOperation(value = "批量修改班次L", notes = "批量修改班次")
	public JSONObject updateScheduling(HttpServletRequest request, @RequestBody List<Scheduling> slist) throws Exception {
		JSONObject json = new JSONObject();
		// System.err.println("slist.size()===========" + slist.size());
		try {
			for (Scheduling scheduling : slist) {
				String workName = scheduling.getDt() + " - " + scheduling.getLine() + " - " + scheduling.getShift();
				// 查询产线ID
				Integer lineId = service.findByLineId(scheduling.getLine());
			//	System.err.println("lineId===="+lineId);
				// 查询班次ID
				Integer shiftId = service.findByShiftId(scheduling.getShift(), lineId);
			//	System.err.println("shiftId===="+shiftId);
				// 查询班组ID
				String team = scheduling.getTeam();
				Integer teamId = service.findByTeamId(team);
		//		System.err.println("teamId===="+teamId);
				Integer a = service.findByScheduling(lineId, shiftId, scheduling.getDt()); // 查询有没有这个数据
				List<CmesScheduling> plans = scheduling.getPlans();// 计划数据
				if (a != 0) {
					// 查询排班表ID
					int id = service.findByScheduling2(lineId, shiftId, scheduling.getDt());
					if (plans == null || plans.size() == 0) {
						// 删除工单表
						service.delSche(id);
						// 班组为空
						if (team == null || team.equals("")) {
							service.delScheById(id);// 删除排版表
						}
						json.put("code", 0);
						json.put("msg", "成功");
					} else {
						for (CmesScheduling plan : plans) {
							String pname = plan.getPlanName();// 计划名称
							Integer pid = service.findByPlanId(pname);// 计划id
							Integer planNumber = plan.getPlanNumber();
							Integer planrealNumber = plan.getPlanrealNumber();

					    	CMesAndonPlanT cMesPlan=	service.findByPlan(pid);
					    	String productionMark = cMesPlan.getProductionMark();//产品标记
					 //   	System.err.println("aaaaaapname="+pid);
					    //	System.err.println("aaaaaapname="+productionMark);

							Integer ww = service.findwork(id, pid);
							if (ww != null) {
								List<Integer> workId = service.findByWorkId(id);
								for (Integer wid : workId) {
									if (!ww.equals(wid)) {
										service.delWorkByPlanId(wid);// 删除
									}
								}
							} else {
								service.addWork2(pid, planNumber, id, workName, planrealNumber,productionMark);
							}
							if (planrealNumber == null) {
								service.update(lineId, shiftId, scheduling.getDt(), teamId, planNumber, planrealNumber);// 修改排版表
							} else {
								if (planrealNumber != 0) {
									service.update(lineId, shiftId, scheduling.getDt(), teamId, planNumber,
											planrealNumber);// 修改排版表
								} else {
									planrealNumber = null;
									service.update(lineId, shiftId, scheduling.getDt(), teamId, planNumber,
											planrealNumber);// 修改排版表
								}
							}
						}
					}
				} else {
					// 排版表不存在此数据数据
					CMesScheduling du = new CMesScheduling();
					du.setShiftId(shiftId);// 班次
					du.setDt(scheduling.getDt());// 日期
					du.setTeamId(teamId);// 班组
					du.setLineId(lineId); // 产线
					// System.err.println("plans.size()=" + plans.size());
					if (plans.size() == 0) {
						// 添加排版表
						cMesLineTService.addSchebuling(du);
					} else {
						if (team == null || team.equals("")) {
							// service.delScheById(id);// 删除排版表
						} else {
							for (CmesScheduling plan : plans) {
								String pname = plan.getPlanName();// 计划名称
								Integer planNumber = plan.getPlanNumber();// 计划数量
								Integer planrealNumber = plan.getPlanrealNumber();// 实际生成数量
								Integer pid = service.findByPlanId(pname);// 计划id
						    	CMesAndonPlanT cMesPlan=	service.findByPlan(pid);
						    	String productionMark = cMesPlan.getProductionMark();//产品标记
					//	    	System.err.println("pname="+pname);
								du.setPlanNumber(planNumber);// 向排班表添加计划数量
								if (planrealNumber == null) {
									du.setActualNumber(null);
								} else {
									if (planrealNumber != 0) {
										du.setActualNumber(planrealNumber);
									} else {
										planrealNumber = null;
										du.setActualNumber(null);// 向排版表添加实际生成数量
									}
								}
								Integer b = service.findByScheduling(lineId, shiftId, scheduling.getDt()); // 查询有没有这个数据
								if (b == 0) {
									// 添加排版表
									cMesLineTService.addSchebuling(du);
								}
								// 计划id不为null
								if (pid != null) {
									// 查询排班表ID
									int id = service.findByScheduling2(lineId, shiftId, scheduling.getDt());
									service.addWork2(pid, planNumber, id, workName, planrealNumber,productionMark);
								}
							}
						}
					}
				}
				json.put("code", 0);
				json.put("msg", "成功");
			}
			// }

		} catch (ServicesException e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			json.put("code", 1);
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}

	@RequestMapping(value = "addWork", method = RequestMethod.POST)
	@ApiOperation(value = "添加工单", notes = "添加工单")
	@ApiImplicitParams({ @ApiImplicitParam(name = "planId", value = "计划id", required = false, paramType = "query"),
			@ApiImplicitParam(name = "number", value = "工单数量", required = false, paramType = "query"),
			@ApiImplicitParam(name = "scheId", value = "排班ID", required = false, paramType = "query"), })
	public JSONObject addWork(HttpServletRequest request, Integer planId, Integer number, Integer scheId) {
		JSONObject json = new JSONObject();
		try {
			service.addWork(planId, number, scheId);
			json.put("code", 0);
			json.put("msg", "添加成功");
		} catch (ServicesException e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			json.put("code", 1);
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}

	@RequestMapping(value = "delWork", method = RequestMethod.POST)
	@ApiOperation(value = "删除工单", notes = "删除工单")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "工单id", required = false, paramType = "query"), })
	public JSONObject delWork(HttpServletRequest request, Integer id) {
		JSONObject json = new JSONObject();
		try {
			service.delWork(id);
			json.put("code", 0);
			json.put("msg", "删除成功");
		} catch (ServicesException e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			json.put("code", 1);
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}

	@RequestMapping(value = "updateWork", method = RequestMethod.POST)
	@ApiOperation(value = "修改工单", notes = "修改工单")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "工单id", required = false, paramType = "query"),
			@ApiImplicitParam(name = "planNumber", value = "计划数量", required = false, paramType = "query"),
			// @ApiImplicitParam(name = "completeId",value = "完成数量", required =
			// false, paramType = "query"),
	})
	public JSONObject updateWork(HttpServletRequest request, Integer id, Integer planNumber) {
		JSONObject json = new JSONObject();
		try {
			service.updateWork(id, planNumber);
			json.put("code", 0);
			json.put("msg", "修改成功");
		} catch (ServicesException e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			json.put("code", 1);
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}

	@RequestMapping(value = "findAllWork", method = RequestMethod.POST)
	@ApiOperation(value = "工单列表", notes = "工单列表")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "工单id", required = false, paramType = "query"),
			@ApiImplicitParam(name = "planId", value = "计划id", required = false, paramType = "query") })
	public JSONObject findAllWork(HttpServletRequest request, Integer id, Integer planId) {
		JSONObject json = new JSONObject();
		try {
			List<CMesWorkorderT> findAllWork = service.findAllWork(id, planId);
			json.put("code", 0);
			json.put("info", findAllWork);
		} catch (ServicesException e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			json.put("code", 1);
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}

	@RequestMapping(value = "findAllSchedulingL", method = RequestMethod.POST)
	@ApiOperation(value = "排班列表", notes = "排班列表")
	@ApiImplicitParams({ @ApiImplicitParam(name = "pages", value = "当前页", required = false, paramType = "query"),
			@ApiImplicitParam(name = "lineId", value = "产线名称", required = false, paramType = "query"),
			@ApiImplicitParam(name = "startTime", value = "开始时间", required = false, paramType = "query"),
			@ApiImplicitParam(name = "endTime", value = "结束时间", required = false, paramType = "query"), })
	public JSONObject findAllSchedulingL(HttpServletRequest request, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "10") Integer pageSize, String startTime, String endTime) {
		PageHelper.startPage(pageNum, pageSize);
		JSONObject json = new JSONObject();
		List<CMesSchedulingL> findAllSchebuling = service.findAllSchebulingl(startTime, endTime);
		PageInfo<CMesSchedulingL> pageInfo = new PageInfo<>(findAllSchebuling);
		try {
			json.put("code", 0);
			json.put("info", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			json.put("code", 1);
		}
		return json;
	}
	@RequestMapping(value = "findSchePlan", method = RequestMethod.POST)
	@ApiOperation(value = "计划列表", notes = "计划列表")
@ApiImplicitParams({ @ApiImplicitParam(name = "lineName", value = "产线名称", required = false, paramType = "query"),
		 })
	public JSONObject findSchePlan(HttpServletRequest request, String lineName) {
		JSONObject json = new JSONObject();
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String time = format.format(date);
		try {
			List<RMesPlanT>  findAllWork = service.findSchePlan(time,lineName);
			json.put("code", 0);
			json.put("info", findAllWork);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}
}
