package com.skeqi.mes.controller.qh;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.apache.xmlbeans.impl.xb.xsdschema.All;
import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesEmpT;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.pojo.CMesRoutingT;
import com.skeqi.mes.pojo.CMesShiftsTeamT;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.pojo.CMesTotalRecipeT;
import com.skeqi.mes.pojo.RMesPlanT;
import com.skeqi.mes.pojo.RMesWorkorderDetailT;
import com.skeqi.mes.service.all.CMesLineTService;
import com.skeqi.mes.service.all.CMesProductionTService;
import com.skeqi.mes.service.all.CMesRecipeService;
import com.skeqi.mes.service.all.ProduceService;
import com.skeqi.mes.service.all.RMesPlanTService;
import com.skeqi.mes.util.ToolUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/***
 * 计划配置管理
 */
@Controller
@SuppressWarnings("all")
@RequestMapping(value = "api", produces = MediaType.APPLICATION_JSON)
@Api(value = "计划配置管理", description = "计划配置管理", produces = MediaType.APPLICATION_JSON)
public class CMesPlansController {

	@Autowired
	RMesPlanTService planService;
	@Autowired
	CMesLineTService lineTService;
	@Autowired
	CMesProductionTService productionService;
	@Autowired
	ProduceService produceService;
	@Autowired
	CMesRecipeService RecipeService;

	@RequestMapping(value = "/plan/findAllPage", method = RequestMethod.POST)
	@ApiOperation(value = "查询计划信息", notes = "查询计划信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int") })
	@ResponseBody
	public JSONObject findAllPage(HttpServletRequest request, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "10") Integer pageSize) throws ServicesException {

		JSONObject json = new JSONObject();

		CMesLineT line = new CMesLineT();
//		CMesProductionT pro = new CMesProductionT();
		CMesShiftsTeamT shiftTeam = new CMesShiftsTeamT();
		CMesEmpT emp = new CMesEmpT();
		try {
			List<CMesLineT> lineList = lineTService.findAllLine(line);
			List<CMesProductionT> productionList = productionService.findAllProductionL();
			List<CMesShiftsTeamT> shiftsTeamList = produceService.findAllShift(shiftTeam);
			List<CMesEmpT> empList = produceService.findAllEmp(emp);
			CMesRoutingT total = new CMesRoutingT();
			Integer recipeid = null;
			if (productionList.size() > 0) {
				total.setProductionId(productionList.get(0).getId());
				recipeid = productionList.get(0).getId();
			}



			json.put("lineList", lineList);
			json.put("productionList", productionList);
			json.put("shiftsTeamList", shiftsTeamList);
			json.put("empList", empList);

			PageHelper.startPage(pageNum, pageSize);
			List<RMesPlanT> rplanList = planService.findAllPlan(null);
			PageInfo<RMesPlanT> pageInfo = new PageInfo<RMesPlanT>(rplanList);
			json.put("pageInfo", pageInfo);
			json.put("code", 0);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);

			json.put("code", 1);
			json.put("msg", "未知错误");
		} finally {
			return json;
		}
	}
	@RequestMapping(value = "/plan/findAllTotal", method = RequestMethod.POST)
	@ApiOperation(value = "根据产品id查询配方", notes = "根据产品id查询配方")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "产品id", required = false, paramType = "query", dataType = "int"), })
	@ResponseBody
	public JSONObject findAllTotal(HttpServletRequest request, Integer id) throws ServicesException {
		JSONObject json = new JSONObject();
		try {

			List<CMesTotalRecipeT> findAllTotalRecipe = planService.findAllTotal(id);
			json.put("findAllRecipe", findAllTotalRecipe);
			json.put("code", 0);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			json.put("code", 1);
			json.put("msg", "未知错误");
		} finally {
			return json;
		}
	}

	@RequestMapping(value = "/plan/findAllRouting", method = RequestMethod.POST)
	@ApiOperation(value = "根据产品id查询工艺", notes = "根据产品id查询工艺")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pid", value = "产品id", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "lid", value = "产线id", required = false, paramType = "query", dataType = "int") })
	@ResponseBody
	public JSONObject findAllRouting(HttpServletRequest request, Integer pid, Integer lid) {
		JSONObject json = new JSONObject();
		try {
			// 判断pid和lid不能空
			if (pid == null || lid == null){
				json.put("findAllRouting", null);
				json.put("code", 0);
			}else {
				CMesRoutingT routingT = new CMesRoutingT();
				routingT.setProductionId(pid);
				routingT.setLineId(lid);
				List<CMesRoutingT> findAllRouting = planService.findAllRouting(routingT);
				json.put("findAllRouting", findAllRouting);
				json.put("code", 0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}

//	@RequestMapping(value = "/plan/findAllRouting", method = RequestMethod.POST)
//	@ApiOperation(value = "根据产品id查询工艺", notes = "根据产品id查询工艺")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "id", value = "产品id", required = false, paramType = "query", dataType = "int"), })
//	@ResponseBody
//	public JSONObject findAllRouting1( Integer id)  {
//		JSONObject json = new JSONObject();
//		try {
//              CMesRoutingT routingT = new CMesRoutingT();
//              routingT.setProductionId(id);
//			List<CMesRoutingT> findAllRouting = planService.findAllRouting(routingT);
//			json.put("findAllRouting", findAllRouting);
//			json.put("code", 0);
//		} catch (Exception e) {
//			e.printStackTrace();
//			json.put("code", 1);
//			json.put("msg", "未知错误");
//		}
//		return json;
//	}
//

	@RequestMapping(value = "/plan/findAllDetailPage", method = RequestMethod.POST)
	@ApiOperation(value = "查询计划明细信息(对应计划下所有工单信息)", notes = "查询对应计划下所有工单信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "planId", value = "计划id", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int") })
	@ResponseBody
	public JSONObject findAllDetailPage(HttpServletRequest request, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "6") Integer pageSize, Integer planId) throws ServicesException {

		JSONObject json = new JSONObject();
		try {

			PageHelper.startPage(pageNum, pageSize);
			RMesWorkorderDetailT work = new RMesWorkorderDetailT();
			work.setPlanId(planId);
			List<RMesWorkorderDetailT> workorderDetailList = planService.findAllWorkOrder(work);
			PageInfo<RMesWorkorderDetailT> pageInfo = new PageInfo<>(workorderDetailList, 5);
			json.put("pageInfo", pageInfo);
			json.put("code", 0);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);

			json.put("code", 1);
			json.put("msg", "未知错误");
		} finally {
			return json;
		}
	}

	@RequestMapping(value = "/plan/alterFlag", method = RequestMethod.POST)
	@ApiOperation(value = "修改计划状态", notes = "修改计划状态")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "planId", value = "计划id", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "flag", value = "计划状态", required = false, paramType = "query", dataType = "int") })
	@ResponseBody
	public JSONObject alterFlag(HttpServletRequest request, Integer flag, Integer planId) throws ServicesException {
		// updateFlagByPlanId
		JSONObject json = new JSONObject();
		// 完成标记 0:表示初始化 1：表示开始 2：表示暂停 3：表示强制关闭 4：表示完成
		try {
			switch (flag) {
			case 1:
				Integer num = planService.getBeginCountById(planId);
				if (num > 0) {
					json.put("msg", "该产线已有计划已开始，不能开始该计划");
					json.put("code", 1);
					return json;
				}
				break;
			case 3:
				Integer findPrintById = planService.findPrintById(planId);
				if (findPrintById > 0) {
					json.put("msg", "该计划下面还有条码未完成不能强制关闭");
					json.put("code", 1);
					return json;
				}
				break;
			case 4:
				Integer a = planService.findPlanNumByCountNum(planId);
				if (a != 0) {
					json.put("code", 1);
					json.put("msg", "计划未完成，若要移除请选择强制关闭");
					return json;
				}
				break;
			}

			planService.updateBarCodeFlagByPlanId(flag, planId);
			json.put("msg", "修改成功");
			json.put("code", 0);
		} catch (Exception e) {
			json.put("code", 1);
			json.put("msg", "未知错误");
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return json;
	}

	@ResponseBody
	@RequestMapping(value = "/plan/addPlanDetail", method = RequestMethod.POST)
	@ApiOperation("新增计划明细（工单）")
	public JSONObject addPlanDetail(HttpServletRequest request, @ModelAttribute RMesWorkorderDetailT workorderDetailT){
		JSONObject json = new JSONObject();
		Integer planId = workorderDetailT.getPlanId();

		workorderDetailT.setPlanId(planId);
		try {
		 planService.addWorkorder(workorderDetailT);
		 json.put("code", 0);
			json.put("msg","添加成功");

		} catch (ServicesException e) {
			json.put("code", 1);
			json.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		} catch (Exception e) {
			json.put("code", 1);
			json.put("msg","未知错误");
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return json;

	}

	@ResponseBody
	@RequestMapping(value = "/plan/addPlan", method = RequestMethod.POST)
	@ApiOperation("新增计划")
	public JSONObject addPlan(HttpServletRequest request, @ModelAttribute RMesPlanT plan)  {
		JSONObject json = new JSONObject();

		try {
			int level = 0;
			level = planService.getMaxLevel();
			plan.setPlanLevel((level + 1) + "");
			planService.addPlan(plan);
			json.put("code", 0);
			json.put("msg","添加成功");
		} catch (ServicesException e) {
			json.put("code", 1);
			json.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		} catch (Exception e) {
			json.put("code", 1);
			json.put("msg","未知错误");
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return json;

	}

	@RequestMapping(value = "/plan/alterDetail", method = RequestMethod.POST)
	@ApiOperation(value = "修改计划明细信息（工单）", notes = "修改计划明细信息（工单）")
	@ResponseBody
	public JSONObject alterDetail(HttpServletRequest request, @ModelAttribute RMesWorkorderDetailT workorderDetailT) throws ServicesException {
		JSONObject json = new JSONObject();

		try {
		 planService.updateWork(workorderDetailT);
		     json.put("code",0 );
			json.put("msg","修改成功");
		} catch (ServicesException e) {
			json.put("code", 1);
			json.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		} catch (Exception e) {
			json.put("code", 1);
			json.put("msg","未知错误");
		}
		return json;
	}

	@RequestMapping(value = "/plan/delPlanDetail", method = RequestMethod.POST)
	@ApiOperation(value = "根据id删除计划详情信息（工单）", notes = "根据id删除计划详情信息（工单）")
	@ApiImplicitParam(paramType = "query", name = "id", value = "工单id", required = true, dataType = "Integer")
	@ResponseBody
	public JSONObject delPlanDetail(HttpServletRequest request, Integer id) throws ServicesException {
		JSONObject json = new JSONObject();
		try {
			planService.delWorkorder(id);
			json.put("code", 0);
			json.put("msg","删除成功");
		} catch (ServicesException e) {
			json.put("code", 1);
			json.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		} catch (Exception e) {
			json.put("code", 1);
			json.put("msg","未知错误");
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return json;
	}
	@RequestMapping(value = "/plan/planDetail", method = RequestMethod.POST)
	@ApiOperation(value = "根据计划id查询工单详情", notes = "根据计划id查询工单详情")
	@ApiImplicitParam(paramType = "query", name = "planId", value = "计划id", required = true, dataType = "Integer")
	@ResponseBody
	public JSONObject planDetail(Integer planId){
		JSONObject json = new JSONObject();
			try {
				RMesWorkorderDetailT work = new RMesWorkorderDetailT();
				work.setPlanId(planId);
				List<RMesWorkorderDetailT> workorderDetailList = planService.findAllWorkOrder(work);
				json.put("pageInfo", workorderDetailList);
			} catch (Exception e) {
				json.put("code", 1);
				json.put("msg","未知错误");
			}

		return json;
	}
}
