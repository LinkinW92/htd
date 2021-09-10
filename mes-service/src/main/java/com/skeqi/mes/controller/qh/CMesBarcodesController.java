package com.skeqi.mes.controller.qh;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesMenuT;
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.pojo.CMesWorkBarcodeT;
import com.skeqi.mes.pojo.PMesTrackingT;
import com.skeqi.mes.pojo.RMesPlanT;
import com.skeqi.mes.pojo.RMesTrackingT;
import com.skeqi.mes.pojo.RMesWorkorderDetailT;
import com.skeqi.mes.service.all.CMesProductionTService;
import com.skeqi.mes.service.all.RMesPlanTService;
import com.skeqi.mes.service.zch.WorkorderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/***
 *
 * @author ENS  工单条码
 *
 */
@Controller
@RequestMapping(value = "api", produces = MediaType.APPLICATION_JSON)
@Api(value = "工单条码", description = "工单条码", produces = MediaType.APPLICATION_JSON)
public class CMesBarcodesController {

//	@Autowired
//	private WorkorderService service;
//
//	@RequestMapping(value = "/barcode/findList", method = RequestMethod.POST)
//	@ApiOperation(value = "查询工单条码", notes = "可根据sn，计划名称查询对应工单条码")
//	@ApiImplicitParams({
//		@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
//		@ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int"),
//		@ApiImplicitParam(name = "sn", value = "SN", required = false, paramType = "query", dataType = "String"),
//		@ApiImplicitParam(name = "workId", value = "计划名", required = false, paramType = "query", dataType = "String"),
//	})
//	@ResponseBody
//	public JSONObject findList(@RequestParam(defaultValue = "1") Integer pageNum,
//			@RequestParam(defaultValue = "10")Integer pageSize,String sn,String planName,String id)throws ServicesException {
//		PageHelper.startPage(pageNum, pageSize);
//		Map<String,Object> map = new HashMap<>();
//		map.put("planName",planName);
//		map.put("sn",sn);
//		map.put("workId", id);
//
////		List<CMesWorkBarcodeT> findAll = planService.findWorkBarcodeRP(map);
//		PageInfo<CMesWorkBarcodeT> pageInfo = new PageInfo<CMesWorkBarcodeT>(findAll);
//
//		JSONObject json = new JSONObject();
//		try {
//			json.put("code", 0);
//			json.put("msg", pageInfo);
//		} catch (Exception e) {
//			e.printStackTrace();
//
//			json.put("code", 1);
//			json.put("msg", "未知错误");
//		}
//		return json;
//	}
//
//
//	@RequestMapping(value = "/barcode/findPackWorkOrderState", method = RequestMethod.POST)
//	@ApiOperation(value = "查询pack的工单状态", notes = "根据sn，工单id查询对应pack工单状态")
//	@ApiImplicitParams({
//		@ApiImplicitParam(name = "workOrderId", value = "工单id", required = false, paramType = "query", dataType = "String"),
//		@ApiImplicitParam(name = "sn", value = "SN", required = false, paramType = "query", dataType = "String"),
//
//	})
//	@ResponseBody
//	public JSONObject findPackWorkOrderState(String workOrderId,String sn){
//		Map<String,Object> map = new HashMap<>();
//
//		JSONObject json = new JSONObject();
//		try {
//			if (workOrderId!=null&&!"".equals(workOrderId)) {
//				map = planService.findTrack(Integer.parseInt(workOrderId), sn);
//			}else{
//				map = planService.findTrack(null, sn);
//			}
//
//			json.put("code", 0);
//			json.put("msg", map);
//		} catch (Exception e) {
//
//			e.printStackTrace();
//
//			json.put("code", 1);
//			json.put("msg", "未知错误");
//		}
//
//		return json;
//	}
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
