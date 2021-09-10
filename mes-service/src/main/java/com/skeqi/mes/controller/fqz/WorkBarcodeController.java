package com.skeqi.mes.controller.fqz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.pojo.CMesWorkBarcodeT;
import com.skeqi.mes.pojo.PMesTrackingT;
import com.skeqi.mes.pojo.RMesPlanT;
import com.skeqi.mes.pojo.RMesTrackingT;
import com.skeqi.mes.pojo.RMesWorkorderDetailT;
import com.skeqi.mes.service.fqz.WorkBarcodeService;

/**
 * 工单条码
 *
 * @author : FQZ
 * @Package: com.skeqi.mes.controller.fqz
 * @date : 2019年10月9日 上午11:53:16
 */
@Controller
@RequestMapping("barcode")
public class WorkBarcodeController {

	@Autowired
	private WorkBarcodeService service;

	/*
	 * @RequestMapping("/barcodelist") public String barcodelist(HttpServletRequest
	 * request,@RequestParam(required = false,defaultValue = "1",value =
	 * "page")Integer page){ PageHelper.startPage(page,8); Map<String,Object> map =
	 * new HashMap<>(); String sn = request.getParameter("sn"); //条码 String planName
	 * = request.getParameter("planName"); //计划名 String id =
	 * request.getParameter("id"); //工单id if(planName!=null &&
	 * !planName.equals("")){ map.put("planName",planName);
	 * request.setAttribute("planName",planName); } if(sn!=null && !sn.equals("")){
	 * map.put("sn",sn); request.setAttribute("sn",sn); }
	 *
	 * if(id!=null && !id.equals("")){ map.put("id", id);
	 * request.setAttribute("id",id); } List<CMesWorkBarcodeT> findAll
	 * =service.findAll(map); if(findAll.size()>0){ PageInfo<CMesWorkBarcodeT>
	 * pageinfo = new PageInfo<CMesWorkBarcodeT>(findAll,5);
	 * request.setAttribute("pageInfo", pageinfo); }else{
	 * PageHelper.startPage(page,8); List<CMesWorkBarcodeT> list = findAll =
	 * service.findAllP(map); PageInfo<CMesWorkBarcodeT> pageinfos = new
	 * PageInfo<CMesWorkBarcodeT>(list,5); request.setAttribute("pageInfo",
	 * pageinfos); }
	 *
	 * return "plan_control/work_barcode"; }
	 *
	 *//**
		 * 查询pack的工位状态
		 *
		 * @author FQZ
		 * @date 2019年10月10日上午9:59:57
		 *//*
			 * @RequestMapping("/sndail")
			 *
			 * @ResponseBody public Map<String,Object> sndail(HttpServletRequest request){
			 * Map<String,Object> map = new HashMap<>(); String workId =
			 * request.getParameter("workId"); String sn = request.getParameter("sn");
			 * RMesTrackingT findR = service.findR(workId, sn); if(findR!=null){
			 * map.put("sn", findR.getSn()); //sn map.put("dt",findR.getDt()); //开始时间
			 * map.put("OFFLINE_DT",null); //下线时间 map.put("status", 0); //完成状态
			 * map.put("st",findR.getSt()) ;;//当前工位 }else{ PMesTrackingT findP =
			 * service.findP(workId, sn); if(findP!=null){ map.put("sn", findP.getSn());
			 * //sn map.put("dt",findP.getDt()); //开始时间
			 * map.put("offlinedt",findP.getOffLineDt()); //下线时间 map.put("status", 1);
			 * //完成状态 map.put("st",null) ;;//当前工位 }else{ map.put("msg",0); return map; } }
			 * return map; }
			 *
			 *
			 * */
		 @RequestMapping("/getinfo")
		 @ResponseBody
		 public Map<String,Object> getinfo(HttpServletRequest request){
			 Map<String,Object> map = new HashMap<String,Object>();
			 String id = request.getParameter("id"); //产线id
			 List<CMesProductionT> findPro = service.findPro(Integer.parseInt(id)); //查询产品name和图片路径
			 List<RMesPlanT>findPlan = service.findPlan(id);
			 String planName=null;
			 if(findPlan.size()>1){
			  //如果有多个计划
				  for (int i = 0; i < findPlan.size(); i++) {
					    planName= planName+","+findPlan.get(i).getPlanName();
					  }
			 }else if(findPlan.size()==1){
				planName = findPlan.get(0).getPlanName();
			 }
			  List<RMesWorkorderDetailT> findWorkor = service.findWorkor(id); //查询工单
			  String name = null;
			  String path = null;
			  if(findPro.size()>0){
				   name = findPro.get(0).getProductionName();
				   path = findPro.get(0).getPath();
			  }
			  map.put("name",name);
			  map.put("path",path);
			  map.put("planName",planName);
			  map.put("findWorkor",findWorkor);
			  return map;
		 }

}
