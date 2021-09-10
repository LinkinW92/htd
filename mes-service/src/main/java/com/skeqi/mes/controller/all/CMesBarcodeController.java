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
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.pojo.CMesWorkBarcodeT;
import com.skeqi.mes.pojo.PMesTrackingT;
import com.skeqi.mes.pojo.RMesPlanT;
import com.skeqi.mes.pojo.RMesTrackingT;
import com.skeqi.mes.pojo.RMesWorkorderDetailT;
import com.skeqi.mes.service.all.CMesProductionTService;
import com.skeqi.mes.service.all.RMesPlanTService;

/***
 *
 * @author ENS  工单条码
 *
 */
@Controller
@RequestMapping("barcode")
public class CMesBarcodeController {

	@Autowired
	RMesPlanTService planService;
	@Autowired
	CMesProductionTService productionService;



	@RequestMapping("/barcodelist")
	public String barcodelist(HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page){
		JSONObject json = new JSONObject();
		Map<String,Object> map = new HashMap<>();
		String sn = request.getParameter("sn");  //条码
		String planName = request.getParameter("planName");  //计划名
		String id = request.getParameter("id");  //工单id
		if(planName!=null && !planName.equals("")){
			map.put("planName",planName);
			request.setAttribute("planName",planName);
		}
		if(sn!=null && !sn.equals("")){
			map.put("sn",sn);
			request.setAttribute("sn",sn);
		}

		if(id!=null && !id.equals("")){
			map.put("id", id);
			request.setAttribute("id",id);
		}
		try {
			PageHelper.startPage(page,8);
			List<CMesWorkBarcodeT> findAll =planService.findWorkBarcode(map);
			if(findAll.size()>0) {
				PageInfo<CMesWorkBarcodeT> pageinfo = new PageInfo<CMesWorkBarcodeT>(findAll,5);
				request.setAttribute("pageInfo", pageinfo);
			}else {
				PageHelper.startPage(page,8);
				List<CMesWorkBarcodeT> findWorkBarcodeP = planService.findWorkBarcodeP(map);
				PageInfo<CMesWorkBarcodeT> pageinfo = new PageInfo<CMesWorkBarcodeT>(findWorkBarcodeP,5);
				request.setAttribute("pageInfo", pageinfo);
			}
			json.put("code", 0);
		}catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}
		return "plan_control/work_barcode";
	}

	/**
	 * 查询pack的工位状态
	* @author FQZ
	* @date 2019年10月10日上午9:59:57
	 */

	@RequestMapping("/sndail")
	@ResponseBody
	public Map<String,Object> sndail(HttpServletRequest request){
		Map<String,Object> map = new HashMap<>();
		String workId = request.getParameter("workId");
		String sn = request.getParameter("sn");

		 try {
			 map = planService.findTrack(Integer.parseInt(workId), sn);
			 map.put("code", 0);
			}catch (ServicesException e) {
				map.put("code", e.getCode());
				map.put("msg", e.getMessage());
			} catch (Exception e) {
				map.put("code", -1);
				map.put("msg", e.getMessage());
			}


		return map;
	}
	/*
	@RequestMapping("/getinfo")
	@ResponseBody
	public Map<String,Object> getinfo(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		String id = request.getParameter("id");  //产线id
		CMesProductionT findPro =planService.

		RMesPlanT findPlan = planService.findPlanByid(Integer.parseInt(id));
		String planName=null;
		if(findPlan.size()>1){  //如果有多个计划
			for (int i = 0; i < findPlan.size(); i++) {
				planName= planName+","+findPlan.get(i).getPlanName();
			}
		}else{
			planName = findPlan.get(0).getPlanName();
		}
		List<RMesWorkorderDetailT> findWorkor = service.findWorkor(id);  //查询工单
		String name = findPro.getProductionName();
		String path = findPro.getPath();
		map.put("name",name);
		map.put("path",path);
		map.put("planName",planName);
		map.put("findWorkor",findWorkor);
		return map;
	}


	 @RequestMapping("/getinfo")
	 @ResponseBody
	 public Map<String,Object> getinfo(HttpServletRequest request){
	  Map<String,Object> map = new HashMap<String,Object>();
	  String id = request.getParameter("id"); //产线id
	  CMesProductionT production = new CMesProductionT();
	  List<CMesProductionT> findPro = productionService.findAllProduction(pro);
	  RMesPlanT rPlan = new RMesPlanT();
	  rPlan.setLineId(Integer.parseInt(id));
	  List<RMesPlanT> findPlan = planService.findAllPlan(rPlan);

	  findAllPlan
	  String planName=null;
	  if(findPlan.size()>1){
	 //如果有多个计划
	 for (int i = 0; i < findPlan.size(); i++) {
		 planName= planName+","+findPlan.get(i).getPlanName();
		 }
	 }else{
		 planName =findPlan.get(0).getPlanName();
		 }
	  List<RMesWorkorderDetailT> findWorkor = service.findWorkor(id);
	  //查询工单
	  String name = findPro.get(0).getProductionName();
	  String path = findPro.get(0).getPath();
	  map.put("name",name); map.put("path",path);
	  map.put("planName",planName);
	  map.put("findWorkor",findWorkor);
	  return map; }

	  */

}
