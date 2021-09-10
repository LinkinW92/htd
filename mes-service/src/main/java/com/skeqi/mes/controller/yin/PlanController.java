package com.skeqi.mes.controller.yin;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.CMesEmpT;
import com.skeqi.mes.pojo.CMesEmpTypeT;
import com.skeqi.mes.pojo.CMesLabelManagerT;
import com.skeqi.mes.pojo.CMesLabelType;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesMaterialT;
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.pojo.CMesRecipeDatilT;
import com.skeqi.mes.pojo.CMesRecipeT;
import com.skeqi.mes.pojo.CMesRecipeTypeT;
import com.skeqi.mes.pojo.CMesShiftsTeamT;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.pojo.PMesPlanPrintT;
import com.skeqi.mes.pojo.PMesPlanT;
import com.skeqi.mes.pojo.RMesPlanPrintT;
import com.skeqi.mes.pojo.RMesPlanT;
import com.skeqi.mes.pojo.RMesWorkorderDetailT;
import com.skeqi.mes.service.yin.DeviceService;
import com.skeqi.mes.service.yin.MaterialService;
import com.skeqi.mes.service.yin.PlanService;
import com.skeqi.mes.service.yin.ProductionService;
import com.skeqi.mes.service.yin.UsersService;
import com.skeqi.mes.util.ToolUtils;
import com.skeqi.mes.util.YMDUtil;

@Controller
@RequestMapping("skq")
public class PlanController {
	@Autowired
	PlanService planService;
	@Autowired
	DeviceService deviceService;
	@Autowired
	UsersService usersService;
	@Autowired
	ProductionService productionService;
	@Autowired
	MaterialService materialService;

//	@RequestMapping("planList")
//	public Object planList(HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page){
//		Map<String, Object> map = new HashMap<>();
//		List<CMesLineT> lineList = planService.lineList();
//		List<CMesProductionT> productionList = planService.productionList();
//		List<CMesShiftsTeamT> shiftsTeamList = productionService.shiftsTeamList(map);
//		List<CMesEmpT> empList = productionService.empList(map);
//		//寮曞叆鍒嗛〉鏌ヨ锛屼娇鐢≒ageHelper鍒嗛〉鍔熻兘
//		//鍦ㄦ煡璇箣鍓嶄紶鍏ュ綋鍓嶉〉锛岀劧鍚庡灏戣褰�
//		PageHelper.startPage(page,5);
//		//startPage鍚庣揣璺熺殑杩欎釜鏌ヨ灏辨槸鍒嗛〉鏌ヨ
//		List<RMesPlanT> rplanList = planService.rplanList(map);
//		//浣跨敤PageInfo鍖呰鏌ヨ缁撴灉锛屽彧闇�瑕佸皢pageInfo浜ょ粰椤甸潰灏卞彲浠�
//		PageInfo<RMesPlanT> pageInfo = new PageInfo<>(rplanList,5);
//		request.setAttribute("pageInfo", pageInfo);
//		request.setAttribute("lineList", lineList);
//		request.setAttribute("productionList", productionList);
//		request.setAttribute("shiftsTeamList", shiftsTeamList);
//		request.setAttribute("empList", empList);
//		return "plan_control/planManager";
//	}
//	@RequestMapping("planDetail")
//	@ResponseBody
//	public Map<String, Object> planDetail(HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page){
//		Map<String, Object> map = new HashMap<>();
//		String id = request.getParameter("planId");
//		List<RMesPlanT> planList = planService.findPlanByPlanSerialno(id);
//		Integer planId = null;
//		try {
//			planId = planList.get(0).getId();
//		} catch (Exception e) {
//		}
//		map.put("planId", planId);
//		PageHelper.startPage(page,5);
//		List<RMesWorkorderDetailT> workorderDetailList = planService.workorderDetailList(map);
//		PageInfo<RMesWorkorderDetailT> pageInfo = new PageInfo<>(workorderDetailList,5);
//		map.put("pageInfo", pageInfo);
//		map.put("workorderDetailList", workorderDetailList);
//		return map;
//	}
//	@RequestMapping("toUpdatePlanDetail")
//	@ResponseBody
//	public Map<String, Object> toUpdatePlanDetail(HttpServletRequest request){
//		Map<String, Object> map = new HashMap<>();
//		String id = request.getParameter("id");
//		map.put("id", id);
//		List<RMesWorkorderDetailT> workorderDetailList = planService.workorderDetailList(map);
//		map.put("workorderDetail", workorderDetailList.get(0));
//		return map;
//	}

	@RequestMapping("index")
	public String main(){
		return "index";
	}


	@RequestMapping("movePlan")
	public @ResponseBody Map<String, Object> movePlan(HttpServletRequest request){
		Map<String, Object> map = new HashMap<>();
		String planSerialno = request.getParameter("planId").trim();//瑕佺Щ鍔ㄧ殑浜х嚎ID
		String levels = request.getParameter("levels").trim();//瑕佺Щ鍔ㄧ殑浜х嚎浼樺厛绾�
		String valu = request.getParameter("valu").trim();//涓婄Щ锛�0锛夎繕鏄笅绉伙紙1锛�
		List<RMesPlanT> plans = null;
		RMesPlanT plan = null;
		if (valu.equals("0")) {
			plans = planService.getPlanByLevel(Integer.parseInt(levels)-1);
			if (plans.size()>0) {
				try {
					plan = plans.get(0);
					planService.updateLevelByPlanId(plan.getPlanSerialno(), Integer.parseInt(levels));//灏嗕笂涓�鏉＄殑浼樺厛绾т慨鏀逛负鏈潯鐨勪紭鍏堢骇锛堜篃灏辨槸浜掓崲浣嶇疆锛�
					planService.updateLevelByPlanId(planSerialno, Integer.parseInt(levels)-1);//灏嗘湰鏉＄殑浼樺厛绾т慨鏀逛负涓婃潯鐨勪紭鍏堢骇
					map.put("msg", "ok");//濡傛灉娌″紓甯� 杩斿洖 ok
					return map;
				} catch (Exception e) {
					e.printStackTrace();
					ToolUtils.errorLog(this, e, request);
					map.put("msg", 2);//寮傚父
					return map;
				}
			}else {
				map.put("msg", 0);//濡傛灉涓虹┖璇存槑鏈潯宸茬粡鏄涓�鏉★紝杩斿洖 0銆�
				return map;
			}
		}
		if (valu.equals("1")) {
			plans = planService.getPlanByLevel(Integer.parseInt(levels)+1);
			if (plans.size()>0) {
				try {
					plan = plans.get(0);
					planService.updateLevelByPlanId(plan.getPlanSerialno(), Integer.parseInt(levels));//灏嗕笅涓�鏉＄殑浼樺厛绾т慨鏀逛负鏈潯鐨勪紭鍏堢骇锛堜篃灏辨槸浜掓崲浣嶇疆锛�
					planService.updateLevelByPlanId(planSerialno, Integer.parseInt(levels)+1);//灏嗘湰鏉＄殑浼樺厛绾т慨鏀逛负涓嬫潯鐨勪紭鍏堢骇
					map.put("msg", "ok");//濡傛灉娌″紓甯� 杩斿洖 ok
				} catch (Exception e) {
				}
			}else {
				map.put("msg", 1);//濡傛灉鏈変负绌鸿鏄庢湰鏉″凡缁忔槸鏈�鍚庝竴鏉★紝杩斿洖 1銆�
				return map;
			}
		}
		return map;
	}
	/**
	 * 鏀瑰彉璁″垝鐘舵��
	 */
	@RequestMapping("updateFlag")
	public @ResponseBody Map<String, Object> updateFlag(HttpServletRequest request){
		Map<String, Object> map = new HashMap<>();
		String flag = request.getParameter("flag").trim();//flag褰撳墠閫夋嫨鐨勬搷浣滐紝completeFlag鐩墠姝ｅ湪鎵ц鐨勬搷浣�
		//		String completeFlag = request.getParameter("completeFlag").trim();
		String planSerialno = request.getParameter("planId").trim();
		String levels = request.getParameter("levels").trim();
		try {
			//濡傛灉鎵ц寮哄埗鍏抽棴鎴栧畬鎴� 鎿嶄綔 鍦≧_MES_PLAN_T琛ㄧЩ闄ゆ湰鏉¤鍒� 骞跺皢鍚庨潰鐨勮鍒掍紭鍏堢骇-1
			//骞跺湪姘镐箙璁″垝琛ㄦ坊鍔犺褰�
			if (flag.equals("3")||flag.equals("4")) {
				RMesPlanT plan = planService.getPlanById(planSerialno);
				String pId = planService.findPlanByPlanSerialno(planSerialno).get(0).getId().toString();
				List<RMesPlanPrintT> rPrintList = planService.getPlanPrintById(pId);
				for (RMesPlanPrintT rMesPlanPrintT : rPrintList) {
					map.put("dt", rMesPlanPrintT.getDt());
					map.put("sn", rMesPlanPrintT.getSn());
					map.put("planId", rMesPlanPrintT.getPlanId());
					map.put("lineId", rMesPlanPrintT.getLineId());
					map.put("productionId", rMesPlanPrintT.getProductionId());
					map.put("serialNo", rMesPlanPrintT.getSerialNO());
					map.put("printFlag", rMesPlanPrintT.getPrintFlag());
					planService.addBarCodeToPPlanPrint(map);
				}
				map.put("planId", pId);
				planService.delPlanDetail(map);
				planService.delBarCode(map);
				planService.updateAllLevelByPlanId(Integer.parseInt(levels));
				planService.deletePlanByPlanId(planSerialno);
				plan.setCompleteFlag(flag);
				planService.addPlanForEver(plan);
			}else {
				planService.updateFlagByPlanId(planSerialno, Integer.parseInt(flag));//淇敼鐘舵��
			}
			map.put("msg", "ok");
			map.put("flag", flag);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return map;
	}
//	/**
//	 * 娣诲姞璁″垝
//	 */
//	@RequestMapping("addPlan")
//	public @ResponseBody Map<String, Object> addPlan(HttpServletRequest request){
//		Map<String, Object> map = new HashMap<>();
//		String planSerialno = request.getParameter("planSerialno").trim();
//		String lineId = request.getParameter("line");
//		String productionType = request.getParameter("productionType").trim();
//		String planNumber = request.getParameter("planNumber").trim();
//		String operationUser = request.getParameter("operationUser").trim();
//		String planName = request.getParameter("planName").trim();
//		int level = 0;
//		List<RMesPlanT> planList = planService.findPlanByPlanSerialno(planSerialno);
//		List<RMesPlanT> planList1 = planService.findPlanByPlanName(planName);
//		if (planList1.size()>0||planList.size()>0) {
//			map.put("msg", 2);
//			return map;
//		}
//		try {
//			level = planService.getMaxLevel();
//		} catch (Exception e) {
//		}
//		if (planSerialno==null||productionType==null||planNumber==null||operationUser==null||planName==null||
//				planSerialno==""||productionType==""||planNumber==""||operationUser==""||planName=="") {
//			map.put("msg", 0);
//			return map;
//		}
//		try {
//			map.put("level", level+1);
//			map.put("planSerialno", planSerialno);
//			map.put("productionType", productionType);
//			map.put("planNumber", planNumber);
//			map.put("operationUser", operationUser);
//			map.put("planName", planName);
//			map.put("lineId",lineId);
//			planService.addPlan(map);
//			map.put("msg", 1);
//		} catch (Exception e) {
//			e.printStackTrace();
//			map.put("msg", 2);
//		}
//		return map;
//	}
	/**
	 * 娣诲姞璁″垝鏄庣粏
	 */
//	@RequestMapping("addPlanDetail")
//	public @ResponseBody Map<String, Object> addPlanDetail(HttpServletRequest request){
//		Map<String, Object> map = new HashMap<>();
//		String productionId = request.getParameter("productionId");
//		String orderNumber = request.getParameter("orderNumber").trim();
//		String teamId = request.getParameter("teamId").trim();
//		String status = request.getParameter("status").trim();
//		String lineId = request.getParameter("lineId").trim();
//		String planId = request.getParameter("planId").trim();
//		map.put("productionId", productionId);
//		map.put("orderNumber", orderNumber);
//		map.put("teamId", teamId);
//		map.put("status", status);
//		map.put("lineId", lineId);
//
//		if (planId==""||planId==null) {
//			map.put("msg", -1);
//			return map;
//		}
//		List<RMesPlanT> planList = planService.findPlanByPlanSerialno(planId);
//		map.put("planId", planList.get(0).getId());
//		List<RMesWorkorderDetailT> workorderDetailList = planService.workorderDetailList(map);
//		int count = 0;
//		for (int i = 0; i < workorderDetailList.size(); i++) {
//			count+=workorderDetailList.get(i).getOrderNumber();
//		}
//		map.put("id", planList.get(0).getId());
//		List<RMesPlanT> rplanList = planService.rplanList(map);
//		if (count+Integer.parseInt(orderNumber)>rplanList.get(0).getPlanNumber()) {
//			map.put("msg", 2);
//			return map;
//		}
//		//閫氳繃璁″垝id鏌ヨ鏈�澶т紭鍏堢骇
//		Integer maxLeveNo = null;
//		try {
//			Map<String, Object> map2 = new HashMap<>();
//			map2.put("lineId", lineId);
//			maxLeveNo = planService.getMaxLevelNoByPlanId(map2);
//		} catch (Exception e) {
//		}
//		if (maxLeveNo==null) {
//			maxLeveNo=0;
//		}
//
//		map.put("levelNo", maxLeveNo+1);
//		List<RMesWorkorderDetailT> workorderDetailList1 = planService.findWorkOrderByProductionId(map);
//		if (workorderDetailList1.size()>0) {
//			map.put("msg", 3);
//			return map;
//		}
//		try {
//			planService.addPlanDetail(map);
//			map.put("msg", 1);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return map;
//	}
//	@RequestMapping("editPlanDetail")
//	public @ResponseBody Map<String, Object> editPlanDetail(HttpServletRequest request){
//		Map<String, Object> map = new HashMap<>();
//		Map<String, Object> map1 = new HashMap<>();
//		Map<String, Object> map2 = new HashMap<>();
//		String productionId = request.getParameter("productionId");
//		String orderNumber = request.getParameter("orderNumber").trim();
//		String teamId = request.getParameter("teamId").trim();
//		String status = request.getParameter("status").trim();
//		String lineId = request.getParameter("lineId").trim();
//		String levelNo = request.getParameter("levelNo").trim();//淇敼鍚庣殑浼樺厛绾�
//		String id = request.getParameter("id").trim();
//		map.put("productionId", productionId);
//		map.put("orderNumber", orderNumber);
//		map.put("teamId", teamId);
//		map.put("status", status);
//		map.put("lineId", lineId);
//		map.put("levelNo", levelNo);
//		map.put("id", id);
//		List<RMesWorkorderDetailT> workorderDetailList = planService.workorderDetailList(map);
//		int count = 0;
//		for (int i = 0; i < workorderDetailList.size(); i++) {
//			count+=workorderDetailList.get(i).getOrderNumber();
//		}
//		map1.put("id", workorderDetailList.get(0).getPlanId());
//		List<RMesPlanT> rplanList = planService.rplanList(map1);
//		//		if (count+Integer.parseInt(orderNumber)>rplanList.get(0).getPlanNumber()) {
//		//			map.put("msg", 2);
//		//			return map;
//		//		}
//		Integer nLevelNo = Integer.parseInt(levelNo);//淇敼鍚庣殑浼樺厛绾�
//		Integer oLevelNo = workorderDetailList.get(0).getLevelNo();//淇敼鍓嶇殑浼樺厛绾�
//		if (nLevelNo<oLevelNo) {
//			map.put("flag", 0);
//		}else if (nLevelNo>oLevelNo) {
//			map.put("flag", 1);
//		}else {
//			map.put("flag", 2);
//		}
//		//閫氳繃璁″垝id鏌ヨ鏈�澶т紭鍏堢骇
//		map2.put("lineId", lineId);
//		Integer maxLeveNo = planService.getMaxLevelNoByPlanId(map2);
//		if (Integer.parseInt(levelNo)>maxLeveNo) {
//			map.put("flag", 3);
//			map.put("levelNo", maxLeveNo);
//		}
//		map.put("nLevelNo", nLevelNo);
//		map.put("oLevelNo", oLevelNo);
//
//		try {
//			planService.editLevelNo(map);
//			planService.editPlanDetail(map);
//			map.put("msg", 1);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return map;
//	}
//	@RequestMapping("delPlanDetail")
//	@ResponseBody
//	public Map<String, Object> delPlanDetail(HttpServletRequest request) {
//		Map<String, Object> map = new HashMap<>();
//		String id = request.getParameter("id");
//		map.put("planDetailId", id);
//		List<RMesWorkorderDetailT> workorderDetailList = planService.workorderDetailList(map);
//		try {
//			planService.delPlanDetail(map);
//			planService.delBarCode(map);
//			map.put("levelNo", workorderDetailList.get(0).getLevelNo());
//			map.put("lineId", workorderDetailList.get(0).getLineId());
//			planService.editOtherPlanDetailLevelNo(map);
//			map.put("msg", 0);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return map;
//	}
	/**
	 * 寮哄埗鍏抽棴璁㈠崟鍒楄〃
	 */
//	@RequestMapping("closeList")
//	public Object closeList(HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page)throws Exception{
//		String planSerialno = request.getParameter("planSerialno");
//		String act_start_time = request.getParameter("act_start_time");
//		String act_stop_time = request.getParameter("act_stop_time");
//		SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd ");
//		Map<String, Object> map = new HashMap<>();
//		Date date1=null;
//		Date date2=null;
//		if (act_start_time!=null&&act_start_time!="") {
//			date1   =   formatter.parse(act_start_time);
//		}
//		if (act_stop_time!=null&&act_stop_time!="") {
//			date2   =   formatter.parse(act_stop_time);
//		}
//		map.put("planSerialno", planSerialno);
//		map.put("date1", date1);
//		map.put("date2", date2);
//		map.put("flag", 3);
//
//		//寮曞叆鍒嗛〉鏌ヨ锛屼娇鐢≒ageHelper鍒嗛〉鍔熻兘
//		//鍦ㄦ煡璇箣鍓嶄紶鍏ュ綋鍓嶉〉锛岀劧鍚庡灏戣褰�
//		PageHelper.startPage(page,8);
//		//startPage鍚庣揣璺熺殑杩欎釜鏌ヨ灏辨槸鍒嗛〉鏌ヨ
//		List<PMesPlanT> planList = planService.planList(map);
//		//浣跨敤PageInfo鍖呰鏌ヨ缁撴灉锛屽彧闇�瑕佸皢pageInfo浜ょ粰椤甸潰灏卞彲浠�
//		PageInfo<PMesPlanT> pageInfo = new PageInfo<>(planList,5);
//		//pageINfo灏佽浜嗗垎椤电殑璇︾粏淇℃伅锛屼篃鍙互鎸囧畾杩炵画鏄剧ず鐨勯〉鏁�
//		request.setAttribute("planSerialno", planSerialno);
//		request.setAttribute("act_start_time", act_start_time);
//		request.setAttribute("act_stop_time", act_stop_time);
//		request.setAttribute("pageInfo", pageInfo);
//		return "plan_control/closeList";
//	}
	/**
	 * 宸插畬鎴愬伐鍗曞垪琛�
	 */
//	@RequestMapping("okList")
//	public Object okList(HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page)throws Exception{
//		String planSerialno = request.getParameter("planSerialno");
//		String act_start_time = request.getParameter("act_start_time");
//		String act_stop_time = request.getParameter("act_stop_time");
//		SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd ");
//		Map<String, Object> map = new HashMap<>();
//		Date date1=null;
//		Date date2=null;
//
//		if (act_start_time!=null&&act_start_time!="") {
//			date1   =   formatter.parse(act_start_time);
//		}
//		if (act_stop_time!=null&&act_stop_time!="") {
//			date2   =   formatter.parse(act_stop_time);
//		}
//		map.put("planSerialno", planSerialno);
//		map.put("date1", date1);
//		map.put("date2", date2);
//		map.put("flag", 4);
//
//		//寮曞叆鍒嗛〉鏌ヨ锛屼娇鐢≒ageHelper鍒嗛〉鍔熻兘
//		//鍦ㄦ煡璇箣鍓嶄紶鍏ュ綋鍓嶉〉锛岀劧鍚庡灏戣褰�
//		PageHelper.startPage(page,8);
//		//startPage鍚庣揣璺熺殑杩欎釜鏌ヨ灏辨槸鍒嗛〉鏌ヨ
//		List<PMesPlanT> planList = planService.planList(map);
//		//浣跨敤PageInfo鍖呰鏌ヨ缁撴灉锛屽彧闇�瑕佸皢pageInfo浜ょ粰椤甸潰灏卞彲浠�
//		PageInfo<PMesPlanT> pageInfo = new PageInfo<>(planList,5);
//		//pageINfo灏佽浜嗗垎椤电殑璇︾粏淇℃伅锛屼篃鍙互鎸囧畾杩炵画鏄剧ず鐨勯〉鏁�
//		request.setAttribute("planSerialno", planSerialno);
//		request.setAttribute("act_start_time", act_start_time);
//		request.setAttribute("act_stop_time", act_stop_time);
//		request.setAttribute("pageInfo", pageInfo);
//		return "plan_control/okList";
//	}
	/**
	 * 鏉＄爜鍒楄〃
	 * @return
	 */
	@RequestMapping("doBarCode")
	public Object doBarCode(HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page) throws Exception{
		List<CMesLineT> lineList = planService.lineList();
		List<CMesProductionT> productionList = planService.productionList();
		String line = request.getParameter("line");
		String productionType = request.getParameter("productionType");
		String opreationUser = request.getParameter("opreationUser");
		Map<String, Object> map = new HashMap<>();
		map.put("flag", 1);
		map.put("line", line);
		map.put("productionType", productionType);
		map.put("opreationUser", opreationUser);
		map.put("status", 1);

		List<RMesPlanT> rplanList = planService.rplanList(map);

		PageHelper.startPage(page,9);
		List<RMesWorkorderDetailT> workorderDetailList = planService.workorderDetailList(map);
		PageInfo<RMesWorkorderDetailT> pageInfo = new PageInfo<>(workorderDetailList,5);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("lineList", lineList);
		request.setAttribute("productionList", productionList);
		request.setAttribute("lines", line);
		request.setAttribute("productionTypes", productionType);
		request.setAttribute("opreationUsers", opreationUser);
		return "plan_control/bar_code";
	}

	/**
	 * 閫氳繃缂栧彿銆佹暟閲� 鐢熸垚鏉＄爜
	 * @return
	 */
	@RequestMapping("generationBarCode")
	public @ResponseBody Map<String, Object> generationBarCode(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> map1 = new HashMap<>();
		Map<String, Object> map2 = new HashMap<>();
		Map<String, Object> map3 = new HashMap<>();
		Map<String, Object> map5 = new HashMap<>();
		Map<String, Object> map6 = new HashMap<>();
		Map<String, Object> map7 = new HashMap<>();
		Map<String, Object> map8 = new HashMap<>();
		//获取工单名称
		String workorderId = request.getParameter("workorderId");
		/*
		 * 		获取工单信息
		 * 		planId		lineId		productionId		serialNo		printFlag		workOrderId
		 */
		map.put("id", workorderId);
		List<RMesWorkorderDetailT> workorderDetailList = planService.workorderDetailList(map);

		//获取工单所属计划
		map2.put("id", workorderDetailList.get(0).getPlanId());
		List<RMesPlanT> rplanList = planService.rplanList(map2);

		//获取产品信息
		map1.put("id", rplanList.get(0).getProductionId());
		List<CMesProductionT> productionList = planService.productionLists(map1);
		Integer productionId = productionList.get(0).getId();

		//标签规则
		Integer labelId = productionList.get(0).getProductionPrintId();
		map5.put("id", labelId);
		List<CMesLabelManagerT> listLabelManager = deviceService.listLabelManager(map5);
		//标签类型ID
		Integer labelTypeId = listLabelManager.get(0).getLabelTypeId();

		map6.put("id", labelTypeId);
		List<CMesLabelType> ruleTypeManagerList = deviceService.ruleTypeManagerList(map6);
		String ruleTypeVr = ruleTypeManagerList.get(0).getLabelVr();

		//标签规则
		String labelRules = listLabelManager.get(0).getLabelRules();

		//类型规则
		String ruleTypeVr1 = ruleTypeVr.substring(0, ruleTypeVr.indexOf("#"));


		String snStart = labelRules.substring(0,labelRules.indexOf("#"));
		//工单所属产线
		map3.put("id", workorderDetailList.get(0).getLineId());
		List<CMesLineT> lineList = usersService.lineList(map3);
		//工单产线的条码生成方式
		Integer codeType = lineList.get(0).getCodeType();
		//工单生成时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(workorderDetailList.get(0).getDt());

		Calendar cal = Calendar.getInstance();
		int year1 = cal.get(Calendar.YEAR);
		int month1 = cal.get(Calendar.MONTH) + 1;
		int day1 = cal.get(Calendar.DAY_OF_MONTH);
		//
		Map<String, Character> map4 = YMDUtil.getCharForDate();
		Character year = map4.get("y"+year1);
		Character month = map4.get("m"+month1);
		Character day = map4.get("d"+day1);
		List<RMesPlanPrintT> getMaxPlanPrintById = null;
		int snNumber = 0;
		int c = counter(labelRules, '#');
		int planNumber = workorderDetailList.get(0).getOrderNumber();
		String echelon = labelRules.substring(labelRules.lastIndexOf("#")+1, labelRules.length());
		String yearLast = new SimpleDateFormat("yy",Locale.CHINESE).format(new Date());
		//同日期同产品条码连续
		map2.put("lineId", workorderDetailList.get(0).getLineId());
		if (codeType==0) {
			map2.put("productionId", productionId);
			map2.put("dt", date);
			getMaxPlanPrintById = planService.getMaxPlanPrintById(map2);
		}
		//同日期不同产品条码连续
		else if (codeType==1) {
			map2.put("productionId", null);
			map2.put("dt", date);
			getMaxPlanPrintById = planService.getMaxPlanPrintById(map2);
		}
		//不同日期同产品条码连续
		else if (codeType==2) {
			map2.put("productionId", productionId);
			map2.put("dt", null);
			getMaxPlanPrintById = planService.getMaxPlanPrintById(map2);
		}
		//不同日期不同产品条码连续
		else if (codeType==3) {
			map2.put("productionId", null);
			map2.put("dt", null);
			getMaxPlanPrintById = planService.getMaxPlanPrintById(map2);
		}
		else{

		}
		if (getMaxPlanPrintById.size()>0) {
			String sn = getMaxPlanPrintById.get(0).getSn();

			//查询此条码的信息（产品ID，用于查询标签规则）
			map6.put("sn", sn);
			List<RMesPlanPrintT> getPlanPrintBySn = planService.getPlanPrintBySn(map6);

			//查询产品信息
			map7.put("id", getPlanPrintBySn.get(0).getProductionId());
			List<CMesProductionT> productionList1 = planService.productionLists(map7);

			//通过产品ID 查询标签规则信息
			map8.put("id", productionList1.get(0).getProductionPrintId());
			List<CMesLabelManagerT> listLabelManager1 = deviceService.listLabelManager(map5);

			String labelRules1 = listLabelManager1.get(0).getLabelRules();
			int d = counter(labelRules1, '#');
			//获取最大条码的数量
			snNumber = Integer.parseInt(sn.substring(sn.length()-d+1, sn.length()));
		}
		//工单所属计划ID
		map2.put("planId", workorderDetailList.get(0).getPlanId());
		//工单所属产线ID
		map2.put("lineId", workorderDetailList.get(0).getLineId());
		//工单所属产品ID
		map2.put("productionId",productionId);
		//工单ID
		map2.put("workOrderId", workorderDetailList.get(0).getId());
		//工单生成时间
		map2.put("dt", sdf.parse(workorderDetailList.get(0).getDt()));

		try {
			String a = "";
			for (int i = 1; i < planNumber+1; i++) {
				if (c ==1) {
					a=""+(snNumber+i);
				}
				if (c==2) {
					if ((snNumber+i)<10) {
						a="0"+(snNumber+i);
					}else if ((snNumber+i)<100) {
						a=""+(snNumber+i);
					}
				}
				if (c==3) {
					if ((snNumber+i)<10) {
						a="00"+(snNumber+i);
					}else if ((snNumber+i)<100) {
						a="0"+(snNumber+i);
					}else if ((snNumber+i)<1000) {
						a=""+(snNumber+i);
					}
				}
				if (c==4) {
					if ((snNumber+i)<10) {
						a="000"+(snNumber+i);
					}else if ((snNumber+i)<100) {
						a="00"+(snNumber+i);
					}else if ((snNumber+i)<1000) {
						a="0"+(snNumber+i);
					}else if ((snNumber+i)<10000) {
						a=""+(snNumber+i);
					}
				}
				if (c==5) {
					if ((snNumber+i)<10) {
						a="0000"+(snNumber+i);
					}else if ((snNumber+i)<100) {
						a="000"+(snNumber+i);
					}else if ((snNumber+i)<1000) {
						a="00"+(snNumber+i);
					}else if ((snNumber+i)<10000) {
						a="0"+(snNumber+i);
					}else if ((snNumber+i)<100000) {
						a=""+(snNumber+i);
					}
				}
				if (c==6) {
					if ((snNumber+i)<10) {
						a="00000"+(snNumber+i);
					}else if ((snNumber+i)<100) {
						a="0000"+(snNumber+i);
					}else if ((snNumber+i)<1000) {
						a="000"+(snNumber+i);
					}else if ((snNumber+i)<10000) {
						a="00"+(snNumber+i);
					}else if ((snNumber+i)<100000) {
						a="0"+(snNumber+i);
					}else if ((snNumber+i)<1000000) {
						a=""+(snNumber+i);
					}
				}
				if (c==7) {
					if ((snNumber+i)<10) {
						a="000000"+(snNumber+i);
					}else if ((snNumber+i)<100) {
						a="00000"+(snNumber+i);
					}else if ((snNumber+i)<1000) {
						a="0000"+(snNumber+i);
					}else if ((snNumber+i)<10000) {
						a="000"+(snNumber+i);
					}else if ((snNumber+i)<100000) {
						a="00"+(snNumber+i);
					}else if ((snNumber+i)<1000000) {
						a="0"+(snNumber+i);
					}else if ((snNumber+i)<10000000) {
						a=""+(snNumber+i);
					}
				}
				//使用 GB/T 34014规则
				if (labelTypeId==1) {
					map2.put("barCode", snStart+year+month+day+echelon+a);
				}/*else if (labelTypeId==2) {
					Calendar calendar = Calendar.getInstance();
					calendar.setFirstDayOfWeek(Calendar.MONDAY);
					calendar.setTime(new Date());
					SimpleDateFormat sdf1 = new SimpleDateFormat("yy");
					String year2 = sdf1.format(new Date());
					int noWeek = calendar.get(Calendar.WEEK_OF_YEAR);
					String week = YMDUtil.getWeekOfDate(new Date());
					map2.put("barCode", snStart+year2+noWeek+week+a+echelon);
				}*/else {
					SimpleDateFormat formatter = new SimpleDateFormat(ruleTypeVr1);
					String date1 = formatter.format(new Date());
					map2.put("barCode", snStart+yearLast+date1+a+echelon);
				}
				//获取最大序列
				int getMaxSerialNo = 0;
				try {
					getMaxSerialNo = planService.getMaxSerialNo(map2);
				} catch (Exception e) {
				}
				map2.put("serialNo", getMaxSerialNo+1);
				planService.addbarCode(map2);
			}
			planService.updateBarCodeFlagByWorkOrderId(workorderDetailList.get(0).getId(), "1");
			map.put("msg", 0);
		} catch (Exception e) {
			map.put("msg", 1);
		}
		return map ;
	}


	@RequestMapping("showBarCode")
	public @ResponseBody Object showBarCode(HttpServletRequest request,HttpServletResponse response)throws Exception{
		String wordOrderId = request.getParameter("wordOrderId");
		List<RMesPlanPrintT> planPrintList = planService.getPlanPrintById(wordOrderId);
		StringBuilder str = new StringBuilder();
		str.append("<ul class='list-group'>");
		String path = request.getContextPath();
		String basePath ="/MES_System/";

		for (int i = 0; i < planPrintList.size(); i++) {
			str.append("<li class='list-group-item'>"+"<a href='"+basePath+"skq/electronicReport?sn="+planPrintList.get(i).getSn()+"'>"+planPrintList.get(i).getSn()+"</a>"+"</li>");
		}
		str.append("</ul>");
		return str;
	}


	//===================================================閰嶆柟閰嶇疆=======================================================


	  @RequestMapping("/getrecipe")

	 @ResponseBody
	 public List<CMesRecipeT> getrecipe(HttpServletRequest request){
	 String id = request.getParameter("id");
	 List<CMesRecipeT> findRecipe =planService.findRecipe(id);
	 return findRecipe;
	 }



	@RequestMapping("recipeDetail")
	public String recipeDetail(HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page){
		Map<String, Object> map = new HashMap<>();
		String productionId = request.getParameter("productionId");
		List<CMesRecipeT> recipeList = planService.findRecipe(productionId);
		List<CMesRecipeTypeT> recipeType = planService.recipeTypeList(map);
		List<CMesMaterialT> materialList = materialService.materialList(map);
		String recipeId = request.getParameter("recipeId");
		if (recipeList.size()>0) {
			if (recipeId==null||recipeId=="") {recipeId = recipeList.get(0).getId().toString();}
		}
		map.put("recipeId", recipeId);

		String stationId = request.getParameter("stationId");
		if (productionId!=null&&!productionId.equals("0")) {map.put("productionId", productionId);}
		if (stationId!=null&&!stationId.equals("0")) {map.put("stationId", stationId);}
		PageHelper.startPage(page,8);
		List<CMesRecipeDatilT> recipeDatilList = planService.getRecipeDatil(map);
		//		for (CMesRecipeDatilT cMesRecipeDatilT : recipeDatilList) {
		//			System.err.println(cMesRecipeDatilT);
		//		}
		PageInfo<CMesRecipeDatilT> pageInfo = new PageInfo<>(recipeDatilList,5);
		List<CMesProductionT> productionList = planService.productionList();
		List<CMesStationT> stationList = planService.stationList();
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("recipeDatilList", recipeDatilList);
		request.setAttribute("stationList", stationList);
		request.setAttribute("productionList", productionList);
		request.setAttribute("recipeList", recipeList);
		request.setAttribute("recipeId", recipeId);
		request.setAttribute("productionId", productionId);
		request.setAttribute("stationId", stationId);
		request.setAttribute("recipeType", recipeType);
		request.setAttribute("materialList", materialList);
		return "recipe_control/recipeDetail";
	}
	/*
	 * <select class="form-control" id="" name="">
						<c:forEach items="${materialList }" var="material">
							<option value="${material.id }">${material.materialName }</option>
						</c:forEach>
						</select>
	 */

	@RequestMapping("toEditBomDetail")
	public @ResponseBody Map<String, Object> toEditBomDetail(HttpServletRequest request){
		String id = request.getParameter("id");
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		@SuppressWarnings("unused")
		List<CMesRecipeDatilT> bomDetailList = null;
		try {
			CMesRecipeDatilT recipeDatil = planService.getRecipeDatil(map).get(0);
			map.put("recipeDatil", recipeDatil);
		} catch (Exception e) {
		}
		return map;
	}
	/**
	 * 鍒犻櫎BOM鏄庣粏
	 * @param request
	 * @return
	 */
	@RequestMapping("deleteBomDetail")
	public @ResponseBody Map<String, Object> deleteBomDetail(HttpServletRequest request){
		String id = request.getParameter("id");
		String stepNo = request.getParameter("stepNo");
		String recipeId = request.getParameter("recipeId");
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("setpNo", stepNo);
		map.put("recipeId", recipeId);
		try {
			planService.deleteBomDetail(map);
			planService.updateStepNoOrderBy(map);
			map.put("msg", 1);
		} catch (Exception e) {
		}
		return map;
	}
	/**
	 * 娣诲姞BOM鏄庣粏
	 * @throws Exception
	 */
	@RequestMapping(value="addBomDetail",method = RequestMethod.POST, consumes = "multipart/form-data")
	public @ResponseBody Map<String, Object> addBomDetails(HttpServletRequest request,
			MultipartFile file1,MultipartFile file2,MultipartFile file3,
			MultipartFile file4,MultipartFile file5,MultipartFile file6,MultipartFile file7) throws Exception{
		Map<String, Object> map = new HashMap<>();
		MultipartFile file = null;
		String pictures1 = file1.getOriginalFilename();
		String pictures2 = file2.getOriginalFilename();
		String pictures3 = file3.getOriginalFilename();
		String pictures4 = file4.getOriginalFilename();
		String pictures5 = file5.getOriginalFilename();
		String pictures6 = file6.getOriginalFilename();
/*		String pictures7 = file7.getOriginalFilename();*/
		// 鏂囦欢鍚嶄娇鐢ㄥ綋鍓嶆椂闂�
		String name = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		// 鑾峰彇涓婁紶鍥剧墖鐨勬墿灞曞悕(jpg/png/...)
		String extension1 = FilenameUtils.getExtension(pictures1);
		String extension2 = FilenameUtils.getExtension(pictures2);
		String extension3 = FilenameUtils.getExtension(pictures3);
		String extension4 = FilenameUtils.getExtension(pictures4);
		String extension5 = FilenameUtils.getExtension(pictures5);
		String extension6 = FilenameUtils.getExtension(pictures6);
/*		String extension7 = FilenameUtils.getExtension(pictures7);  */
		// 鍥剧墖涓婁紶鐨勭浉瀵硅矾寰勶紙鍥犱负鐩稿璺緞鏀惧埌椤甸潰涓婂氨鍙互鏄剧ず鍥剧墖锛�
		String path = null;
		String path1 = name + "." + extension1;
		String path2 = name + "." + extension2;
		String path3 = name + "." + extension3;
		String path4 = name + "." + extension4;
		String path5 = name + "." + extension5;
		String path6 = name + "." + extension6;
		String revieworNo = request.getParameter("revieworNo");
		String exactorNo = request.getParameter("exactorNo");
		String stepCategory = request.getParameter("stepCategory");
		String reworktimes = request.getParameter("reworktimes");
		String chekorno = request.getParameter("chekorno");
		String recipeId = request.getParameter("recipeId");
		String materialName = request.getParameter("materialName");
		String number = request.getParameter("number");
		String feaCode = request.getParameter("feaCode");
		String boltEQS = request.getParameter("boltEQS");
		String uploadCode = request.getParameter("uploadCode");
		String materialPN = request.getParameter("materialPN");
		String materialName2 = request.getParameter("materialId2");
		String number2 = request.getParameter("number2");
		String boltEQS2 = request.getParameter("boltEQS2");
		String uploadCode2 = request.getParameter("uploadCode2");
		String gunNo2 = request.getParameter("gunNo2");
		String programNo2 = request.getParameter("programNo2");
		String sleeveNo2 = request.getParameter("sleeveNo2");
		String materialName3 = request.getParameter("materialName3");
		String boltEQS3 = request.getParameter("boltEQS3");
		String number3 = request.getParameter("number3");
		String uploadCode3 = request.getParameter("uploadCode3");
		String programNo3 = request.getParameter("programNo3");
		String materialName4 = request.getParameter("materialName4");
		String boltEQS4 = request.getParameter("boltEQS4");
		String materialName5 = request.getParameter("materialName5");
		String aLimit = request.getParameter("aLimit");
		String tLimit = request.getParameter("tLimit");

		String chekorno6 = request.getParameter("chekorno6");
		String materialId6 = request.getParameter("materialId6");
		String number6 = request.getParameter("number6");
		String feaCode6 = request.getParameter("feaCode6");
		String boltEQS6 = request.getParameter("boltEQS6");
		String uploadCode6 = request.getParameter("uploadCode6");
		String materialPN6 = request.getParameter("materialPN6");
		String revieworNo6 = request.getParameter("revieworNo6");
		String exactorNo6 = request.getParameter("exactorNo6");

		String chekorno7 = request.getParameter("chekorno7");
		String materialName7 = request.getParameter("materialName7");
		String number7 = request.getParameter("number7");
		String feaCode7 = request.getParameter("feaCode7");


		map.put("aLimit", aLimit);
		map.put("tLimit", tLimit);
		map.put("chekorno", chekorno);
		String stepCategoryName = null;
		if (stepCategory.equals("1") && pictures1!="") {
			stepCategoryName = "1";
			path=path1;
			if (!extension1.equals("jpg")&&!extension1.equals("png")&&!extension1.equals("jpeg")) {
				map.put("msg", 1);
				return map;
			}
			file=file1;
		}else if (stepCategory.equals("2") && pictures1!="") {
			stepCategoryName = "2";
			path=path1;
			if (!extension1.equals("jpg")&&!extension1.equals("png")&&!extension1.equals("jpeg")) {
				map.put("msg", 1);
				return map;
			}
			file=file1;
		}else if (stepCategory.equals("3") && pictures6!="") {
			stepCategoryName = "3";
			path=path6;
			if (!extension6.equals("jpg")&&!extension6.equals("png")&&!extension6.equals("jpeg")) {
				map.put("msg", 1);
				return map;
			}
			file=file6;
		}else if (stepCategory.equals("4") && pictures1!="") {
			stepCategoryName = "4";
			path=path1;
			if (!extension1.equals("jpg")&&!extension1.equals("png")&&!extension1.equals("jpeg")) {
				map.put("msg", 1);
				return map;
			}
			file=file1;
		}else if (stepCategory.equals("5") && pictures1!="") {
			stepCategoryName = "5";
			path=path1;
			if (!extension1.equals("jpg")&&!extension1.equals("png")&&!extension1.equals("jpeg")) {
				map.put("msg", 1);
				return map;
			}
			file=file1;
		}else if (stepCategory.equals("6") && pictures1!="") {
			stepCategoryName = "6";
			path=path1;
			if (!extension1.equals("jpg")&&!extension1.equals("png")&&!extension1.equals("jpeg")) {
				map.put("msg", 1);
				return map;
			}
			file=file1;
		}else if (stepCategory.equals("7") && pictures2!="") {
			stepCategoryName = "7";
			path=path2;
			if (!extension2.equals("jpg")&&!extension2.equals("png")&&!extension2.equals("jpeg")) {
				map.put("msg", 1);
				return map;
			}
			file=file2;
		}else if (stepCategory.equals("8") && pictures3!="") {
			stepCategoryName = "8";
			path=path3;
			if (!extension3.equals("jpg")&&!extension3.equals("png")&&!extension3.equals("jpeg")) {
				map.put("msg", 1);
				return map;
			}
			file=file3;
		}else if (stepCategory.equals("9") && pictures3!="") {
			stepCategoryName = "9";
			path=path3;
			if (!extension3.equals("jpg")&&!extension3.equals("png")&&!extension3.equals("jpeg")) {
				map.put("msg", 1);
				return map;
			}
			file=file3;
		}else if (stepCategory.equals("10") && pictures4!="") {
			stepCategoryName = "10";
			path=path4;
			if (!extension4.equals("jpg")&&!extension4.equals("png")&&!extension4.equals("jpeg")) {
				map.put("msg", 1);
				return map;
			}
			file=file4;
		}else if (stepCategory.equals("11")  && pictures4!="") {
			stepCategoryName = "11";
			path=path4;
			if (!extension4.equals("jpg")&&!extension4.equals("png")&&!extension4.equals("jpeg")) {
				map.put("msg", 1);
				return map;
			}
			file=file4;
		}else if (stepCategory.equals("12")  && pictures4!="") {
			stepCategoryName = "12";
			path=path4;
			if (!extension4.equals("jpg")&&!extension4.equals("png")&&!extension4.equals("jpeg")) {
				map.put("msg", 1);
				return map;
			}
			file=file4;
		}else if (stepCategory.equals("13")  && pictures3!="") {
			stepCategoryName = "13";
			path=path3;
			if (!extension3.equals("jpg")&&!extension3.equals("png")&&!extension3.equals("jpeg")) {
				map.put("msg", 1);
				return map;
			}
			file=file3;
		}else if (stepCategory.equals("14")  && pictures5!="") {
			stepCategoryName = "14";
			path=path5;
			if (!extension5.equals("jpg")&&!extension5.equals("png")&&!extension5.equals("jpeg")) {
				map.put("msg", 1);
				return map;
			}
			file=file5;
		}else if (stepCategory.equals("15")  && pictures3!="") {
			stepCategoryName = "15";
			path=path3;
			if (!extension3.equals("jpg")&&!extension3.equals("png")&&!extension3.equals("jpeg")) {
				map.put("msg", 1);
				return map;
			}
			file=file3;
		}
		if(path!="" && path!=null){
			String url = "C:\\upload";
			File dir = new File(url);
			if(!dir.exists()) {
				dir.mkdirs();
			}
			// 涓婁紶鍥剧墖  '
			map.put("pictures", path);
			file.transferTo(new File(url+"/"+path));
		}
		if (extension1.equals("")&&extension2.equals("")&&extension3.equals("")&&extension4.equals("")&&
				extension5.equals("")&&extension6.equals("")) {
			map.put("pictures", "");
		}
		int maxStepNo = 0;
		try {
			maxStepNo = planService.findMaxStepNoByRecipeId(Integer.parseInt(recipeId));
		} catch (Exception e) {
		}
		map.put("maxStepNo", maxStepNo+1);
		map.put("recipeId", recipeId);
		map.put("reworktimes", reworktimes);
		map.put("stepCategoryName", stepCategoryName);
		map.put("stepCategory", stepCategory);
		map.put("materialName", materialName);
		map.put("feaCode", feaCode);
		map.put("materialPN", materialPN);
		map.put("number", number);
		map.put("boltEQS", boltEQS);
		map.put("uploadCode", uploadCode);
		map.put("materialName2", materialName2);
		map.put("number2", number2);
		map.put("boltEQS2", boltEQS2);
		map.put("uploadCode2", uploadCode2);
		map.put("gunNo2", gunNo2);
		map.put("programNo2", programNo2);
		map.put("sleeveNo2", sleeveNo2);
		map.put("materialName3", materialName3);
		map.put("boltEQS3", boltEQS3);
		map.put("programNo3", programNo3);
		map.put("number3", number3);
		map.put("uploadCode3", uploadCode3);
		map.put("materialName4", materialName4);
		map.put("boltEQS4", boltEQS4);
		map.put("materialName5", materialName5);
		map.put("revieworNo", revieworNo);
		map.put("exactorNo", exactorNo);

		map.put("chekorno6", chekorno6);
		map.put("materialId6", materialId6);
		map.put("number6", number6);
		map.put("feaCode6", feaCode6);
		map.put("boltEQS6", boltEQS6);
		map.put("uploadCode6", uploadCode6);
		map.put("materialPN6", materialPN6);
		map.put("revieworNo6", revieworNo6);
		map.put("exactorNo6", exactorNo6);

		map.put("chekorno7", chekorno7);
		map.put("materialName7", materialName7);
		map.put("number7", number7);
		map.put("feaCode7", feaCode7);
		planService.addRecipeDetail(map);
		map.put("msg", 0);
		return map;
	}
	/**
	 * 淇敼BOM鏄庣粏
	 * @param request
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("editBomDetail")
	public @ResponseBody Map<String, Object> editBomDetail(HttpServletRequest request,MultipartFile file)throws Exception{

		Map<String, Object> map = new HashMap<>();
		String setpNos2 = request.getParameter("setpNos2");
		String revieworNo = request.getParameter("revieworNo");
		String exactorNo = request.getParameter("exactorNo");
		String reworktimes = request.getParameter("reworktimes");
		String id2 = request.getParameter("id2");
		String chekorno = request.getParameter("chekorno");
		String materialNames2 = request.getParameter("materialNames2");
		String numbers2 = request.getParameter("numbers2");
		String materialPNs2 = request.getParameter("materialPNs2");
		String boltEQSs2 = request.getParameter("boltEQSs2");
		String uploadCodes2 = request.getParameter("uploadCodes2");
		String feaCodes2 = request.getParameter("feaCodes2");
		String flag = request.getParameter("flag");

		String id3 = request.getParameter("id3");
		String setpNos3 = request.getParameter("setpNos3");
		String materialNames3 = request.getParameter("materialNames3");
		String numbers3 = request.getParameter("numbers3");
		String gunNos3 = request.getParameter("gunNos3");
		String programNos3 = request.getParameter("programNos3");
		String sleeveNos3 = request.getParameter("sleeveNos3");
		String uploadCodes3 = request.getParameter("uploadCodes3");
		String boltEQSs3 = request.getParameter("boltEQSs3");
		String aLimit = request.getParameter("aLimit");
		String tLimit = request.getParameter("tLimit");

		String id4 = request.getParameter("id4");
		String setpNos4 = request.getParameter("setpNos4");
		String materialNames4 = request.getParameter("materialNames4");
		String numbers4 = request.getParameter("numbers4");
		String uploadCodes4 = request.getParameter("uploadCodes4");
		String boltEQSs4 = request.getParameter("boltEQSs4");
		String programNo4 = request.getParameter("programNo4");

		String id5 = request.getParameter("id5");
		String setpNos5 = request.getParameter("setpNos5");
		String materialNames5 = request.getParameter("materialNames5");
		String boltEQSs5 = request.getParameter("boltEQSs5");

		String id6 = request.getParameter("id6");
		String setpNos6 = request.getParameter("setpNos6");
		String materialNames6 = request.getParameter("materialNames6");


		String setpNos7 = request.getParameter("setpNos7");
		String materialId7 = request.getParameter("materialId7");
		String numbers7 = request.getParameter("numbers7");
		String materialPNs7 = request.getParameter("materialPNs7");
		String boltEQSs7 = request.getParameter("boltEQSs7");
		String uploadCodes7 = request.getParameter("uploadCodes7");
		String feaCodes7 = request.getParameter("feaCodes7");
		String chekorno7 = request.getParameter("chekorno7");
		String revieworNo7 = request.getParameter("revieworNo7");
		String exactorNo7 = request.getParameter("exactorNo7");
		String id7 = request.getParameter("id7");
		//鑾峰彇閰嶆柟ID
		String recipeId = request.getParameter("recipeId");
		//1.寰楀埌褰撳墠宸ュ簭缂栧彿
		String stepNo = request.getParameter("stepNo");
		//2.寰楀埌灏嗚淇敼鐨勫伐搴忕紪鍙�
		//setpNos2
		map.put("revieworNo", revieworNo);
		map.put("exactorNo", exactorNo);
		map.put("revieworNo7", revieworNo7);
		map.put("exactorNo7", exactorNo7);
		if (!file.isEmpty()) {
			String pictures = file.getOriginalFilename();
			// 鏂囦欢鍚嶄娇鐢ㄥ綋鍓嶆椂闂�
			String name = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
			// 鑾峰彇涓婁紶鍥剧墖鐨勬墿灞曞悕(jpg/png/...)
			String extension = FilenameUtils.getExtension(pictures);
			// 鍥剧墖涓婁紶鐨勭浉瀵硅矾寰勶紙鍥犱负鐩稿璺緞鏀惧埌椤甸潰涓婂氨鍙互鏄剧ず鍥剧墖锛�
			String path =  name + "." + extension;
			if (!extension.equals("jpg")&&!extension.equals("png")&&!extension.equals("jpeg")) {
				map.put("msg", 1);
				return map;
			}
			// 鍥剧墖涓婁紶鐨勭粷瀵硅矾寰�
			String url = "C:\\upload";
			File dir = new File(url);
			if(!dir.exists()) {
				dir.mkdirs();
			}
			map.put("pictures", path);
			// 涓婁紶鍥剧墖
			file.transferTo(new File(url+"/"+path));
			//      String picturesToByte = UploadImages.getImageBinary(url);
		}else{
			Map<String, Object> maps = new HashMap<>();
			if(id2!=null){
				map.put("id", id2);
			}else if(id3!=null){
				map.put("id", id3);
			}else if(id4!=null){
				map.put("id", id4);
			}else if(id5!=null){
				map.put("id", id5);
			}else if(id6!=null){
				map.put("id", id6);
			}else if(id7!=null){
				map.put("id", id7);
			}
			CMesRecipeDatilT recipeDatil = planService.getRecipeDatil(maps).get(0);
			map.put("pictures", recipeDatil.getPicturnPath());
		}
		map.put("chekorno", chekorno);
		if (flag.equals("2")) {
			if (Integer.parseInt(stepNo)<Integer.parseInt(setpNos2)) {
				map.put("setpNo", setpNos2);
				map.put("recipeId", recipeId);
				List<CMesRecipeDatilT> recipeDatils = planService.getRecipeDatilByStepNo(map);
				if (recipeDatils.size()>0) {
					map.put("setpNo2", setpNos2);
					map.put("setpNo", stepNo);
					map.put("recipeId", recipeId);
					planService.updateStepNoOrderBy(map);
				}else {
					Integer maxStepNo = planService.findMaxStepNoByRecipeId(Integer.parseInt(recipeId));
					map.put("setpNos2", maxStepNo);
					map.put("setpNo", stepNo);
					planService.updateStepNoOrderBy(map);
				}
			}else if (Integer.parseInt(stepNo)>Integer.parseInt(setpNos2)){
				map.put("setpNo", setpNos2);
				map.put("setpNo2", stepNo);
				map.put("recipeId", recipeId);
				planService.updateStepNoOrderBy2(map);
			}
			map.put("flag", 2);
			map.put("id2", id2);
			map.put("setpNos2", setpNos2);
			map.put("materialNames2", materialNames2);
			map.put("numbers2", numbers2);
			map.put("materialPNs2", materialPNs2);
			map.put("boltEQSs2", boltEQSs2);
			map.put("uploadCodes2", uploadCodes2);
			map.put("feaCodes2", feaCodes2);
			planService.editRecipeDetail(map);
		}
		if (flag.equals("3")) {
			if (Integer.parseInt(stepNo)<Integer.parseInt(setpNos3)) {
				map.put("setpNo", setpNos3);
				map.put("recipeId", recipeId);
				List<CMesRecipeDatilT> RecipeDatils = planService.getRecipeDatilByStepNo(map);
				if (RecipeDatils.size()>0) {
					map.put("setpNo2", setpNos3);
					map.put("setpNo", stepNo);
					map.put("recipeId", recipeId);
					planService.updateStepNoOrderBy(map);
				}else {
					Integer maxStepNo = planService.findMaxStepNoByRecipeId(Integer.parseInt(recipeId));
					map.put("setpNos3", maxStepNo);
					map.put("setpNo", stepNo);
					planService.updateStepNoOrderBy(map);
				}
			}else if (Integer.parseInt(stepNo)>Integer.parseInt(setpNos3)){
				map.put("setpNo", setpNos3);
				map.put("setpNo2", stepNo);
				map.put("recipeId", recipeId);
				planService.updateStepNoOrderBy2(map);
			}
			map.put("reworktimes", reworktimes);
			map.put("flag", 3);
			map.put("id3", id3);
			map.put("setpNos3", setpNos3);
			map.put("materialNames3", materialNames3);
			map.put("numbers3", numbers3);
			map.put("gunNos3", gunNos3);
			map.put("programNos3", programNos3);
			map.put("sleeveNos3", sleeveNos3);
			map.put("boltEQSs3", boltEQSs3);
			map.put("uploadCodes3", uploadCodes3);
			map.put("aLimit", aLimit);
			map.put("tLimit", tLimit);
			planService.editRecipeDetail(map);
		}
		if (flag.equals("4")) {
			if (Integer.parseInt(stepNo)<Integer.parseInt(setpNos4)) {
				map.put("setpNo", setpNos4);
				map.put("recipeId", recipeId);
				List<CMesRecipeDatilT> RecipeDatils = planService.getRecipeDatilByStepNo(map);
				if (RecipeDatils.size()>0) {
					map.put("setpNo2", setpNos4);
					map.put("setpNo", stepNo);
					map.put("recipeId", recipeId);
					planService.updateStepNoOrderBy(map);
				}else {
					Integer maxStepNo = planService.findMaxStepNoByRecipeId(Integer.parseInt(recipeId));
					map.put("setpNos4", maxStepNo);
					map.put("setpNo", stepNo);
					planService.updateStepNoOrderBy(map);
				}
			}else if (Integer.parseInt(stepNo)>Integer.parseInt(setpNos4)){
				map.put("setpNo", setpNos4);
				map.put("setpNo2", stepNo);
				map.put("recipeId", recipeId);
				planService.updateStepNoOrderBy2(map);
			}
			map.put("flag", 4);
			map.put("id4", id4);
			map.put("setpNos4", setpNos4);
			map.put("materialNames4", materialNames4);
			map.put("numbers4", numbers4);
			map.put("boltEQSs4", boltEQSs4);
			map.put("programNo4", programNo4);
			map.put("uploadCodes4", uploadCodes4);
			planService.editRecipeDetail(map);
		}
		if (flag.equals("5")) {
			if (Integer.parseInt(stepNo)<Integer.parseInt(setpNos5)) {
				map.put("setpNo", setpNos5);
				map.put("recipeId", recipeId);
				List<CMesRecipeDatilT> RecipeDatils = planService.getRecipeDatilByStepNo(map);
				if (RecipeDatils.size()>0) {
					map.put("setpNo2", setpNos5);
					map.put("setpNo", stepNo);
					map.put("recipeId", recipeId);
					planService.updateStepNoOrderBy(map);
				}else {
					Integer maxStepNo = planService.findMaxStepNoByRecipeId(Integer.parseInt(recipeId));
					map.put("setpNos5", maxStepNo);
					map.put("setpNo", stepNo);
					planService.updateStepNoOrderBy(map);
				}
			}else if (Integer.parseInt(stepNo)>Integer.parseInt(setpNos5)){
				map.put("setpNo", setpNos5);
				map.put("setpNo2", stepNo);
				map.put("recipeId", recipeId);
				planService.updateStepNoOrderBy2(map);
			}
			map.put("flag", 5);
			map.put("id5", id5);
			map.put("setpNos5", setpNos5);
			map.put("materialNames5", materialNames5);
			map.put("boltEQSs5", boltEQSs5);
			planService.editRecipeDetail(map);
		}
		if (flag.equals("6")) {
			if (Integer.parseInt(stepNo)<Integer.parseInt(setpNos6)) {
				map.put("setpNo", setpNos6);
				map.put("recipeId", recipeId);
				List<CMesRecipeDatilT> RecipeDatils = planService.getRecipeDatilByStepNo(map);
				if (RecipeDatils.size()>0) {
					map.put("setpNo2", setpNos6);
					map.put("setpNo", stepNo);
					map.put("recipeId", recipeId);
					planService.updateStepNoOrderBy(map);
				}else {
					Integer maxStepNo = planService.findMaxStepNoByRecipeId(Integer.parseInt(recipeId));
					map.put("setpNos6", maxStepNo);
					map.put("setpNo", stepNo);
					planService.updateStepNoOrderBy(map);
				}
			}else if (Integer.parseInt(stepNo)>Integer.parseInt(setpNos6)){
				map.put("setpNo", setpNos6);
				map.put("setpNo2", stepNo);
				map.put("recipeId", recipeId);
				planService.updateStepNoOrderBy2(map);
			}
			map.put("flag", 6);
			map.put("id6", id6);
			map.put("setpNos6", setpNos6);
			map.put("materialNames6", materialNames6);
			planService.editRecipeDetail(map);
		}
		map.put("chekorno7", chekorno7);
		if (flag.equals("7")) {
			if (Integer.parseInt(stepNo)<Integer.parseInt(setpNos7)) {
				map.put("setpNo", setpNos7);
				map.put("recipeId", recipeId);
				List<CMesRecipeDatilT> recipeDatils = planService.getRecipeDatilByStepNo(map);
				if (recipeDatils.size()>0) {
					map.put("setpNo2", setpNos7);
					map.put("setpNo", stepNo);
					map.put("recipeId", recipeId);
					planService.updateStepNoOrderBy(map);
				}else {
					Integer maxStepNo = planService.findMaxStepNoByRecipeId(Integer.parseInt(recipeId));
					map.put("setpNos2", maxStepNo);
					map.put("setpNo", stepNo);
					planService.updateStepNoOrderBy(map);
				}
			}else if (Integer.parseInt(stepNo)>Integer.parseInt(setpNos7)){
				map.put("setpNo", setpNos7);
				map.put("setpNo2", stepNo);
				map.put("recipeId", recipeId);
				planService.updateStepNoOrderBy2(map);
			}
			map.put("flag", 7);
			map.put("id7", id7);
			map.put("setpNos7", setpNos7);
			map.put("materialId7", materialId7);
			map.put("numbers7", numbers7);
			map.put("materialPNs7", materialPNs7);
			map.put("boltEQSs7", boltEQSs7);
			map.put("uploadCodes7", uploadCodes7);
			map.put("feaCodes7", feaCodes7);
			planService.editRecipeDetail(map);
		}
		map.put("msg", 0);
		return map;
	}
	public static int counter(String s,char c){
		int count=0;
		for(int i=0;i<s.length();i++){
			if(s.charAt(i)==c){
				count++;
			}
		}
		return count;
	}
}
