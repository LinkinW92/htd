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
import com.skeqi.mes.pojo.CMesEmpT;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.pojo.CMesRecipe;
import com.skeqi.mes.pojo.CMesRoutingT;
import com.skeqi.mes.pojo.CMesShiftsTeamT;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.pojo.CMesTotalRecipeT;
import com.skeqi.mes.pojo.RMesPlanT;
import com.skeqi.mes.pojo.RMesWorkorderDetailT;
import com.skeqi.mes.pojo.Routing;
import com.skeqi.mes.service.all.CMesLineTService;
import com.skeqi.mes.service.all.CMesProductionTService;
import com.skeqi.mes.service.all.CMesRecipeService;
import com.skeqi.mes.service.all.ProduceService;
import com.skeqi.mes.service.all.RMesPlanTService;
import com.skeqi.mes.util.ToolUtils;

/***
 *
 * @author ENS 计划配置管理
 *
 */
@Controller
@RequestMapping("/skq")
public class CMesPlanController {

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

	@RequestMapping("planList")
	public Object planList(HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page){
		CMesLineT line = new CMesLineT();
		CMesProductionT pro = new CMesProductionT();
		CMesShiftsTeamT shiftTeam = new CMesShiftsTeamT();
		CMesEmpT emp = new CMesEmpT();
		try {
			List<CMesLineT> lineList = lineTService.findAllLine(line);
			List<CMesProductionT> productionList = productionService.findAllProduction(pro);
			List<CMesShiftsTeamT> shiftsTeamList = produceService.findAllShift(shiftTeam);
			List<CMesEmpT> empList = produceService.findAllEmp(emp);
			CMesRoutingT total = new CMesRoutingT();
			Integer recipeid = null;
			if(productionList.size()>0){
				total.setProductionId(productionList.get(0).getId());
				recipeid = productionList.get(0).getId();
			}
			List<CMesTotalRecipeT> findAllTotalRecipe =planService.findAllTotal(recipeid);
			List<CMesRoutingT> findAllRouting = planService.findAllRouting(total);

			request.setAttribute("findAllRouting",findAllRouting);
			request.setAttribute("findAllRecipe", findAllTotalRecipe);
			request.setAttribute("lineList", lineList);
			request.setAttribute("productionList", productionList);
			request.setAttribute("shiftsTeamList", shiftsTeamList);
			request.setAttribute("empList", empList);

			PageHelper.startPage(page,5);
			List<RMesPlanT> rplanList = planService.findAllPlan(null);
			PageInfo<RMesPlanT> pageInfo = new PageInfo<RMesPlanT>(rplanList,5);
			request.setAttribute("pageInfo", pageInfo);


		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return "plan_control/planManager";
	}
	@RequestMapping("planDetail")
	@ResponseBody
	public JSONObject planDetail(HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page){
		JSONObject json = new JSONObject();
		String id = request.getParameter("planId");
		RMesPlanT plan = new RMesPlanT();
		plan.setPlanSerialno(id);

		try {
		List<RMesPlanT> planList = planService.findAllPlan(plan);
		Integer planId = null;
		try {
			planId = planList.get(0).getId();
			} catch (Exception e) {
		}
			PageHelper.startPage(page,5);
			RMesWorkorderDetailT work = new RMesWorkorderDetailT();
			work.setPlanId(planId);
			List<RMesWorkorderDetailT> workorderDetailList = planService.findAllWorkOrder(work);
			PageInfo<RMesWorkorderDetailT> pageInfo = new PageInfo<>(workorderDetailList,5);
			json.put("pageInfo", pageInfo);
			json.put("workorderDetailList", workorderDetailList);
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
	@RequestMapping("toUpdatePlanDetail")
	@ResponseBody
	public JSONObject toUpdatePlanDetail(HttpServletRequest request){
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");
		try {
			RMesWorkorderDetailT workorderDetail = planService.findWorkOrderByid(Integer.parseInt(id));
			json.put("workorderDetail", workorderDetail);
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


	@RequestMapping("addPlanDetail")
	@ResponseBody
	public  JSONObject addPlanDetail(HttpServletRequest request) throws Exception, ServicesException{
		JSONObject json = new JSONObject();
		String productionId = request.getParameter("productionId");
		String orderNumber = request.getParameter("orderNumber").trim();
		String teamId = request.getParameter("teamId").trim();
		String status = request.getParameter("status").trim();
//		String lineId = request.getParameter("lineId").trim();
		String planId = request.getParameter("planId").trim();
		RMesPlanT findPlanByid = planService.findPlanByid(Integer.parseInt(planId));
		RMesPlanT plans = new RMesPlanT();
		plans.setPlanSerialno(planId);
		Integer lineId = planService.findAllPlan(plans).get(0).getLineId();
		if (planId==""||planId==null) {
			json.put("code", -2);
			json.put("msg", "计划ID为空");
			return json;
		}

		try {
		RMesPlanT plan = new RMesPlanT();
		RMesWorkorderDetailT workorder = new RMesWorkorderDetailT();
		workorder.setProductionId(productionId);
		workorder.setOrderNumber(Integer.parseInt(orderNumber));
		workorder.setTeamId(Integer.parseInt(teamId));
		workorder.setStatus(Integer.parseInt(status));
		workorder.setLineId(lineId);
		workorder.setPlanId(Integer.parseInt(planId));
		plan.setPlanSerialno(planId);
		List<RMesPlanT> planList = planService.findAllPlan(plan);
		workorder.setPlanId(planList.get(0).getId());
			planService.addWorkorder(workorder);
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
	@RequestMapping("editPlanDetail")
	@ResponseBody
	public  JSONObject editPlanDetail(HttpServletRequest request){
		JSONObject json = new JSONObject();
		String productionId = request.getParameter("productionId");
		String orderNumber = request.getParameter("orderNumber");
		String teamId = request.getParameter("teamId").trim();
		String status = request.getParameter("status").trim();
//		String lineId = request.getParameter("lineId").trim();
		String levelNo = request.getParameter("levelNo").trim();//淇敼鍚庣殑浼樺厛绾�
		String id = request.getParameter("id").trim();
		String planId = request.getParameter("planId").trim();

		RMesWorkorderDetailT workorder = new RMesWorkorderDetailT();
		workorder.setProductionId(productionId);
		workorder.setId(Integer.parseInt(id));
		workorder.setTeamId(Integer.parseInt(teamId));
		workorder.setOrderNumber(Integer.parseInt(orderNumber));
		workorder.setLevelNo(Integer.parseInt(levelNo));
//		workorder.setLineId(Integer.parseInt(lineId));
		workorder.setStatus(Integer.parseInt(status));
		workorder.setPlanId(Integer.parseInt(planId));
		try {
			planService.updateWork(workorder);
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
	@RequestMapping("delPlanDetail")
	@ResponseBody
	public JSONObject delPlanDetail(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");
		try {
			planService.delWorkorder(Integer.parseInt(id));
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





//	@RequestMapping("updateFlag")
//	public @ResponseBody Map<String, Object> updateFlag(HttpServletRequest request){
//		Map<String, Object> map = new HashMap<>();
//		String flag = request.getParameter("flag").trim();
//		String planSerialno = request.getParameter("planId").trim();
//		String levels = request.getParameter("levels").trim();
//
//		planService.up
//		try {
//
//			if (flag.equals("3")||flag.equals("4")) {
//				RMesPlanT plan = planService.getPlanById(planSerialno);
//				String pId = planService.findPlanByPlanSerialno(planSerialno).get(0).getId().toString();
//				List<RMesPlanPrintT> rPrintList = planService.getPlanPrintById(pId);
//				for (RMesPlanPrintT rMesPlanPrintT : rPrintList) {
//					map.put("dt", rMesPlanPrintT.getDt());
//					map.put("sn", rMesPlanPrintT.getSn());
//					map.put("planId", rMesPlanPrintT.getPlanId());
//					map.put("lineId", rMesPlanPrintT.getLineId());
//					map.put("productionId", rMesPlanPrintT.getProductionId());
//					map.put("serialNo", rMesPlanPrintT.getSerialNO());
//					map.put("printFlag", rMesPlanPrintT.getPrintFlag());
//					planService.addBarCodeToPPlanPrint(map);
//				}
//				map.put("planId", pId);
//				planService.delPlanDetail(map);
//				planService.delBarCode(map);
//				planService.updateAllLevelByPlanId(Integer.parseInt(levels));
//				planService.deletePlanByPlanId(planSerialno);
//				plan.setCompleteFlag(flag);
//				planService.addPlanForEver(plan);
//			}else {
//				/*if (flag.equals("1")) {
//					if (!levels.equals("1")) {
//						List<RMesPlanT> plans = planService.getPlanByLevel(Integer.parseInt(levels)-1);
//						if (plans.get(0).getCompleteFlag().equals("0")) {
//							map.put("msg", -1);
//							return map;
//						}
//					}
//				}*/
//				//鎵ц鍒濆鍖栥�佸紑濮嬨�佹殏鍋滄搷浣� 淇敼鐘舵��
//				planService.updateFlagByPlanId(planSerialno, Integer.parseInt(flag));//淇敼鐘舵��
//			}
//			map.put("msg", "ok");
//			map.put("flag", flag);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return map;
//	}
	/**
	 * 娣诲姞璁″垝
	 * @throws ServicesException
	 */
	@RequestMapping("addPlan")
	@ResponseBody
	public  JSONObject addPlan(HttpServletRequest request) throws ServicesException{
		JSONObject json = new JSONObject();
		String planSerialno = request.getParameter("planSerialno").trim();
		String productionType = request.getParameter("productionType").trim();
		String planNumber = request.getParameter("planNumber").trim();
		String operationUser = request.getParameter("operationUser").trim();
		String planName = request.getParameter("planName").trim();
		String rouid = request.getParameter("rouid");
		String rid = request.getParameter("rid");
		int level = 0;
		RMesPlanT plan = new RMesPlanT();
		try {
			level = planService.getMaxLevel();
		}catch (Exception e) {

		}
		try {

			CMesRoutingT rou = new CMesRoutingT();
			rou.setId(Integer.parseInt(rouid));
			List<CMesRoutingT> findAllRouting = planService.findAllRouting(rou);    //根据工艺路线查询产线id
			Integer id = findAllRouting.get(0).getLineId();  //产线id
			List<CMesStationT> findAllStation = planService.findAllStation(Integer.parseInt(rid));   //此配方下所有的工位以及产线
			for (CMesStationT cMesStationT : findAllStation) {
				if(cMesStationT.getLineId()!=id){
					json.put("code",-1);
					json.put("msg","["+cMesStationT.getStationName()+"}不在此工艺路线涉及的产线下");
					return json;
				}
			}
			plan.setRoutingId(Integer.parseInt(rouid));  //工艺id
			plan.setTotalRecipeId(Integer.parseInt(rid));   ///总配方id
			plan.setLineId(id);
		} catch (Exception e) {
			// TODO: handle exception
		}

		plan.setPlanLevel((level+1)+"");
		plan.setPlanSerialno(planSerialno);
		plan.setProductionId(Integer.parseInt(productionType));
		plan.setPlanNumber(Integer.parseInt(planNumber));
		plan.setOpreationUser(operationUser);
		plan.setPlanName(planName);
		try {
			planService.addPlan(plan);
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
	 * 根据产品获取工艺路线和总配方
	* @author FQZ
	 * @throws Exception
	* @date 2020年4月11日上午8:52:33
	 */
	@RequestMapping("/findRecipeRouting")
	@ResponseBody
	public JSONObject getRecipe(HttpServletRequest request) throws Exception{
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");
		CMesRoutingT routing = new CMesRoutingT();
		routing.setProductionId(Integer.parseInt(id));
		List<CMesRoutingT> findAllRouting = planService.findAllRouting(routing);
		List<CMesTotalRecipeT> findAllTotal = planService.findAllTotal(Integer.parseInt(id));
		json.put("findAllRouting", findAllRouting);
		json.put("findAllTotal", findAllTotal);
		return json;
	}


//
//	@RequestMapping("movePlan")
//	@ResponseBody
//	public  JSONObject movePlan(HttpServletRequest request){
//		JSONObject json = new JSONObject();
//		String planSerialno = request.getParameter("planId").trim();
//		String levels = request.getParameter("levels").trim();
//		String valu = request.getParameter("valu").trim();
//		RMesPlanT plan = new RMesPlanT();
//		plan.setPlanLevel((Integer.parseInt(levels)-1)+"");
//		RMesPlanT plan2 = null;
//		if (valu.equals("0")) {
//			List<RMesPlanT> plans = planService.findAllPlan(plan);
//			if (plans.size()>0) {
//				try {
//
////
////					<update id="updateWork">
////					update R_MES_WORKORDER_DETAIL_T set
////					production_id = #{productionId},order_number=#{orderNumber},
////					line_id=#{lineId},team_id=#{teamId},
////					level_no=#{levelNo},status=#{status},alter_dt=NOW() where id = #{id}
////				</update>
//
//					RMesWorkorderDetailT work = new RMesWorkorderDetailT();
//
//					plan2 = plans.get(0);
//
//					planService.updateWork(work);
//					planService.updateLevelByPlanId(plan.getPlanSerialno(), Integer.parseInt(levels));//灏嗕笂涓�鏉＄殑浼樺厛绾т慨鏀逛负鏈潯鐨勪紭鍏堢骇锛堜篃灏辨槸浜掓崲浣嶇疆锛�
//					planService.updateLevelByPlanId(planSerialno, Integer.parseInt(levels)-1);//灏嗘湰鏉＄殑浼樺厛绾т慨鏀逛负涓婃潯鐨勪紭鍏堢骇
//					map.put("msg", "ok");//濡傛灉娌″紓甯� 杩斿洖 ok
//					return map;
//				} catch (Exception e) {
//					e.printStackTrace();
//					map.put("msg", 2);//寮傚父
//					return map;
//				}
//			}else {
//				map.put("msg", 0);//濡傛灉涓虹┖璇存槑鏈潯宸茬粡鏄涓�鏉★紝杩斿洖 0銆�
//				return map;
//			}
//		}
//		if (valu.equals("1")) {
//			plans = planService.getPlanByLevel(Integer.parseInt(levels)+1);
//			if (plans.size()>0) {
//				try {
//					plan = plans.get(0);
//					planService.updateLevelByPlanId(plan.getPlanSerialno(), Integer.parseInt(levels));//灏嗕笅涓�鏉＄殑浼樺厛绾т慨鏀逛负鏈潯鐨勪紭鍏堢骇锛堜篃灏辨槸浜掓崲浣嶇疆锛�
//					planService.updateLevelByPlanId(planSerialno, Integer.parseInt(levels)+1);//灏嗘湰鏉＄殑浼樺厛绾т慨鏀逛负涓嬫潯鐨勪紭鍏堢骇
//					map.put("msg", "ok");//濡傛灉娌″紓甯� 杩斿洖 ok
//				} catch (Exception e) {
//				}
//			}else {
//				map.put("msg", 1);//濡傛灉鏈変负绌鸿鏄庢湰鏉″凡缁忔槸鏈�鍚庝竴鏉★紝杩斿洖 1銆�
//				return map;
//			}
//		}
//		return map;
//	}
//
}
