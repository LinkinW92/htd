package com.skeqi.mes.controller.project;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.pojo.CMesShiftsTeamT;
import com.skeqi.mes.pojo.CMesTeamT;
import com.skeqi.mes.pojo.RMesPlanT;
import com.skeqi.mes.pojo.project.CMesScheduling;
import com.skeqi.mes.pojo.project.CMesWorkorderT;
import com.skeqi.mes.service.project.CMesAndonPlanService;
import com.skeqi.mes.service.project.CMesProductionService;
import com.skeqi.mes.service.project.CMesSchedulingService;
import com.skeqi.mes.util.ToolUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(value = "api", produces = MediaType.APPLICATION_JSON)
@Api(value = "计划管理", description = "计划管理", produces = MediaType.APPLICATION_JSON)
public class CMesAndonPlanController {

	@Autowired
	CMesAndonPlanService service;

	@Autowired
	CMesSchedulingService cMesSchedulingService;

	@Autowired
	CMesProductionService cMesProductionService;

	/**
	 * 20200630重写版 查询看板产量
	 *
	 * @param lineName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "findAndonPlan630", method = RequestMethod.POST)
	@ApiOperation(value = "查询看板产量", notes = "查询看板产量")
	public JSONObject findAndonPlan630(HttpServletRequest request, String lineName) throws Exception {
		JSONObject json = new JSONObject();
		try {
			JSONObject jsonObject = service.findKanBanYield(lineName);
			json.put("code", 0);
			json.put("info", jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			json.put("code", 1);
			json.put("msg", e.getMessage());
		} finally {
			return json;
		}

	}

//	public static void main(String[] args) {
//		Date date = new Date();
//		// Calendar calendar =new GregorianCalendar();
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(date);
//		calendar.add(calendar.DATE, -2);
//		date = calendar.getTime();
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//		String dateString = format.format(date);
//		long longDate = Long.valueOf(dateString.replaceAll("[-\\s:]", ""));
//		String dtString = "2020-07-22";
//		long dt = Long.valueOf(dtString.replaceAll("[-\\s:]", ""));
//		System.out.println(longDate);
//		System.out.println(dt);
//
//	}

	@RequestMapping(value = "findAndonPlan", method = RequestMethod.POST)
	@ApiOperation(value = "计划列表", notes = "计划列表")
	@ApiImplicitParams({ @ApiImplicitParam(name = "name", value = "计划名称", required = false, paramType = "query"),
			@ApiImplicitParam(name = "lineName", value = "产线名称", required = false, paramType = "query"),
			@ApiImplicitParam(name = "status", value = "未完成/完成(0/1)", required = false, paramType = "query"),
			@ApiImplicitParam(name = "pages", value = "当前页", required = false, paramType = "query"),
			@ApiImplicitParam(name = "systemDate", value = "计划日期", required = false, paramType = "query"), })
	public JSONObject findAndonPlan(HttpServletRequest request, String name, Integer status, String lineName, Integer pages, String systemDate)
			throws Exception {
		JSONObject json = new JSONObject();
		Date date = new Date();
		// Calendar calendar =new GregorianCalendar();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(calendar.DATE, -2);
		date = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = format.format(date);
		long time = Long.valueOf(dateString.replaceAll("[-\\s:]", ""));// 2天前时间
		List<RMesPlanT> findAndonPlan = null;
		if (pages == null || pages == 0) {
			if (status == 0) {
				findAndonPlan = service.findAndonPlan(name, lineName, systemDate);
			} else {
				findAndonPlan = service.findcompleteAndonPlan(name, lineName, systemDate);
			}
		} else {
			PageHelper.startPage(pages, 10);
			if (status == 0) {
				findAndonPlan = service.findAndonPlan(name, lineName, systemDate);
			} else {
				findAndonPlan = service.findcompleteAndonPlan(name, lineName, systemDate);
			}
			PageInfo<RMesPlanT> pageinfo = new PageInfo<RMesPlanT>(findAndonPlan, 5);
			json.put("pageNum", pageinfo.getTotal());
			findAndonPlan = pageinfo.getList();
		}
		try {
			for (RMesPlanT rMesPlanT : findAndonPlan) {
				if(StringUtils.isEmpty(rMesPlanT.getDt())) {
				}else {
					String dt = rMesPlanT.getDt();
			     	long planTime = Long.valueOf(dt.replaceAll("[-\\s:]", ""));// 计划时间
					Integer pid = rMesPlanT.getId();// 计划id
					if (planTime < time) {
						service.updateStatus(pid, 4);
					}
				}

			}
			json.put("code", 0);
			json.put("info", findAndonPlan);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code", 1);
		}
		return json;
	}

	@RequestMapping(value = "addPlan", method = RequestMethod.POST)
	@ApiOperation(value = "添加计划", notes = "添加计划")
	public JSONObject addPlan(HttpServletRequest request, @RequestBody RMesPlanT plan) {
		JSONObject json = new JSONObject();

		Integer a = service.findByName(plan.getPlanName());
		if (a != 0) {
			json.put("code", 1);
			json.put("msg", "当前计划已存在");
		} else {

			try {
				service.addAndonPlan(plan);
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

	@RequestMapping(value = "updatePlan", method = RequestMethod.POST)
	@ApiOperation(value = "修改计划", notes = "修改计划")
	public JSONObject updatePlan(HttpServletRequest request, @RequestBody RMesPlanT plan) {
		JSONObject json = new JSONObject();
		Integer lineId = plan.getLineId();// 产线
		String dt = plan.getDt();// 计划执行时间
		Integer pid = plan.getProductionId();// 产品id
		Integer a = service.findByNamel(plan.getPlanName(), plan.getId());
		if (a != 0) {
			json.put("code", 1);
			json.put("msg", "当前计划已存在");
		} else {
			Integer i = service.findLineDtByPro(lineId, dt, pid);
			if (i > 0) {
				json.put("code", 1);
				json.put("msg", "同一时间同一产线，不能有同样的产品");
			} else {
				try {
					service.updateAndonPlan(plan);
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
			}
		}
		return json;
	}

	@RequestMapping(value = "findPidByScheduling", method = RequestMethod.POST)
	@ApiOperation(value = "查询计划是否已排版", notes = "查询计划是否已排版")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query"), })
	public JSONObject findPidByScheduling(Integer id) {

		JSONObject json = new JSONObject();
		try {
			Set<Map<String, Object>> a = cMesSchedulingService.findPidByScheduling(id);
			if (a.size() > 0) {
				json.put("code", 1);
				json.put("msg", "当前计划已排产");
			} else {
				json.put("code", 0);
				json.put("msg", "成功");
			}
		} catch (Exception e) {
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}

	@RequestMapping(value = "deletePlan", method = RequestMethod.POST)
	@ApiOperation(value = "删除计划", notes = "删除计划")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query"), })
	public JSONObject deletePlan(HttpServletRequest request, Integer id) {
		JSONObject json = new JSONObject();
		try {
			service.delAndonPlan(id);
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

	@RequestMapping(value = "updataStatus", method = RequestMethod.POST)
	@ApiOperation(value = "更新计划状态", notes = "更新计划状态")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query"),
			@ApiImplicitParam(name = "planStatus", value = "计划状态", required = false, paramType = "query"),
			@ApiImplicitParam(name = "userName", value = "关闭人", required = false, paramType = "query") })
	public JSONObject updataStatus(HttpServletRequest request, Integer id, String planStatus, String userName) {
		JSONObject json = new JSONObject();
		RMesPlanT plan = new RMesPlanT();
		plan.setId(id);
		plan.setPlanStatus(planStatus);
		Date date = new Date();
		// 格式化
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sim.format(date);
		plan.setPlanDate(time);
		plan.setUserName(userName);
		try {
			service.updateStatusl(plan);
			json.put("code", 0);
			json.put("msg", "成功");
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}

	@RequestMapping(value = "findProductMark", method = RequestMethod.POST)
	@ApiOperation(value = "根据产线ID查询产品标记", notes = "根据产线ID查询产品标记")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "query"), })
	public JSONObject findProductMark(HttpServletRequest request, Integer id) {
		JSONObject json = new JSONObject();
		try {
			List<String> findProducMark = service.findProducMark(id);
			json.put("code", 0);
			json.put("info", findProducMark);
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

	@RequestMapping(value = "importAndonPlan", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "导入计划 ", notes = "导入计划 ")
	@Transactional(rollbackFor = { Exception.class })
	public JSONObject importAndonPlan(HttpServletRequest request, @RequestBody JSONArray info) throws Exception {
		JSONObject json = new JSONObject();
		if (info.size() < 0) {
			json.put("code", 1);
			json.put("msg", "导入数据为空");
			return json;
		}
		Integer findLineByName = 0;
		int sum = info.size();
		int error = 0;
		List<Object> list = new ArrayList<>();
		try {
			for (int i = 0; i < info.size(); i++) {
				JSONObject jsonObject = info.getJSONObject(i);
				Object planNumbers = jsonObject.get("planNumber");
				Object dt = jsonObject.get("dt");
				String date = null;
				if (!StringUtils.isEmpty(dt)) {
					date = dt.toString();
				}
				if (planNumbers == null || planNumbers == "") {
					json.put("code", 1);
					json.put("msg", "计划数量不能为空");
					return json;
				}
				String planNumber = planNumbers.toString();

				Object lineNames = jsonObject.get("lineName");
				if (lineNames == null || lineNames == "") {
					json.put("code", 1);
					json.put("msg", "产线名称不能为空");
					return json;
				} else {
					findLineByName = service.findLineByName(lineNames.toString());
					if (findLineByName == null) {
						json.put("code", 1);
						json.put("msg", "[" + lineNames + "]产线不存在");
						return json;
					}
				}
				Object w = jsonObject.get("productionType");
				if (w == null || w == "") {
					json.put("code", 1);
					json.put("msg", "产品编码不能为空");
					return json;
				}
				String productionType = w.toString();
				Integer b = cMesProductionService.findByProductionType(productionType);
				if (b.equals(null)) {
					json.put("code", 1);
					json.put("msg", "当前编码不存在");
					return json;
				}
				Integer max = 10000, min = 1;
				long randomNum = System.currentTimeMillis();
				int ran3 = (int) (randomNum % (max - min) + min);
				CMesProductionT pro = service.findProByName(productionType);// 查询此产品信息
				Integer pid = pro.getId();// 产品id
				String productMark = pro.getProductionVr();// 产品标记
				Integer lineId = service.findLineByName(lineNames.toString());
				RMesPlanT plan = new RMesPlanT();
				plan.setPlanNumber(Integer.parseInt(planNumber));
				plan.setProductionId(pid);
				plan.setPlanName("计划" + String.valueOf(ran3));
				plan.setLineId(lineId);
				plan.setProductMark(productMark);
				plan.setDt(date);
				if (StringUtils.isEmpty(plan.getDt())) {
					service.addAndonImportPlan(plan);// 添加计划
					error++;
				} else {
					CMesShiftsTeamT findByShiftId = cMesSchedulingService.findByShifAll(lineId);
					String lineName = service.findByLineId(lineId);
					CMesTeamT team = cMesSchedulingService.findByTeamAll();
					if (StringUtils.isEmpty(findByShiftId)) {
						error++;
						String aaa = lineName + "-" + date;
						list.add(aaa);
					} else {
						service.addAndonImportPlan(plan);// 添加计划
						String workName = date + " - " + lineName + " - " + findByShiftId.getName();
						Integer s = cMesSchedulingService.findByScheduling(lineId, findByShiftId.getId(), date);
						if (s > 0) {
							Integer scheId = cMesSchedulingService.findByScheduling2(lineId, findByShiftId.getId(),
									date);
							CMesWorkorderT work = new CMesWorkorderT();
							work.setPlanId(plan.getId());
							work.setWorkName(workName);
							work.setNumber(Integer.parseInt(planNumber));
							work.setProductMark(productMark);
							work.setCompleteNumber(0);
							work.setScheId(scheId);
							cMesSchedulingService.addWork3(work);
						} else {
							CMesScheduling du = new CMesScheduling();
							du.setShiftId(findByShiftId.getId());
							du.setDt(date);
							du.setTeamId(team.getId());
							du.setLineId(lineId);
							du.setPlanNumber(Integer.parseInt(planNumber));
							cMesSchedulingService.addScheduling(du);
							Integer scheId = du.getId();
							CMesWorkorderT work = new CMesWorkorderT();
							work.setPlanId(plan.getId());
							work.setWorkName(workName);
							work.setNumber(Integer.parseInt(planNumber));
							work.setProductMark(productMark);
							work.setCompleteNumber(0);
							work.setScheId(scheId);
							cMesSchedulingService.addWork3(work);
						}
					}
				}
			}
			json.put("code", 0);
			json.put("sum", sum);
			json.put("error", error);
			json.put("list", list);
			json.put("msg", "导入成功");
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}

}
