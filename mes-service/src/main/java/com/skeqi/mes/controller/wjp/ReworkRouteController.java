package com.skeqi.mes.controller.wjp;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.common.lcy.GetDate;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.pojo.PMesBoltT;
import com.skeqi.mes.pojo.PMesDefectReasonT;
import com.skeqi.mes.pojo.PMesKeypartT;
import com.skeqi.mes.pojo.PMesLeakageT;
import com.skeqi.mes.pojo.PMesStation;
import com.skeqi.mes.pojo.RTrackingT;
import com.skeqi.mes.pojo.ReworkWayT;
import com.skeqi.mes.service.wjp.ReworkRouteService;
import com.skeqi.mes.service.yin.ReportService;


@Controller
@RequestMapping("ReworkRoute")
public class ReworkRouteController {

	@Autowired
	private ReworkRouteService reworkRouteService;

	@Autowired
	ReportService reportService;

	//查询原因列表
	@RequestMapping("reason")
	public String findReason(Model model,HttpServletRequest request){
		List<RTrackingT> rTracking=reworkRouteService.findReason();
		List<CMesStationT> list=reworkRouteService.findStationName();
		model.addAttribute("reworkRoute", rTracking);
		model.addAttribute("findstationName", list);
		request.setAttribute("station", list);
		return "processPlan_control/reworkRoute";
	}

	//根据总成号查询
	@RequestMapping("findReworkRoute")
	public @ResponseBody Object findReworkRoute(Model model,
			HttpServletRequest request,
			@RequestParam(required = false,defaultValue = "1",value = "page")Integer page,
			@RequestParam(required = false,defaultValue = "1",value = "page2")Integer page2,
			@RequestParam(required = false,defaultValue = "1",value = "page3")Integer page3,
			HttpServletResponse response,HttpSession session){
		String sn=request.getParameter("sn").trim();
		Map<String,Object> map=new HashMap<>();
		map.put("SN", sn);
		map.put("sn", sn);
		Integer queryPage2=Integer.parseInt(request.getParameter("queryPage2"));
		Integer queryPage1=Integer.parseInt(request.getParameter("queryPage1"));
		Integer queryPage3=Integer.parseInt(request.getParameter("queryPage3"));
		RTrackingT rTrackingT=reworkRouteService.findReworkRoute(map);

		if(rTrackingT!=null){
			List<RTrackingT> rTracking=reworkRouteService.findReason();
			List<ReworkWayT> findReworkBySn = reworkRouteService.findReworkBySn(map);
			String lineId = rTrackingT.getLineId();
			map.put("lineId", lineId);
			List<CMesStationT> listStationByLineId = reworkRouteService.listStationByLineId(map);
			List<PMesDefectReasonT> defectReason = reworkRouteService.defectReason(map);
			if (defectReason.size()>0) {
				map.put("defectReason", defectReason.get(0));
			}

			map.put("status", 0);
			PageHelper.startPage(queryPage1,8);
			List<PMesBoltT> boltList = reportService.boltList(map);
			PageInfo<PMesBoltT> pageInfo1 = new PageInfo<>(boltList,5);

			StringBuffer sb1 = new StringBuffer();
			for(int i=0;i<pageInfo1.getSize();i++){
				sb1.append("<tr>");
				if(GetDate.getDateforSimple(pageInfo1.getList().get(i).getDt())==null
						||"null".equals(GetDate.getDateforSimple(pageInfo1.getList().get(i).getDt()))){

					sb1.append("<td scope=\"row\"></td>");
				}else{
					sb1.append("<td scope=\"row\">"+GetDate.getDateforSimple(pageInfo1.getList().get(i).getDt())+"</td>");

				}

				if(pageInfo1.getList().get(i).getBoltName()==null
						||"null".equals(pageInfo1.getList().get(i).getBoltName())){

					sb1.append("<td scope=\"row\"></td>");
				}else{

					sb1.append("<td scope=\"row\">"+pageInfo1.getList().get(i).getBoltName()+"</td>");
				}
				if(pageInfo1.getList().get(i).getA()==null
						||"null".equals(pageInfo1.getList().get(i).getA())){
					sb1.append("<td scope=\"row\"></td>");
				}else{
					sb1.append("<td scope=\"row\">"+pageInfo1.getList().get(i).getA()+"</td>");
				}
				if(pageInfo1.getList().get(i).getT()==null
						||"null".equals(pageInfo1.getList().get(i).getT())){
					sb1.append("<td scope=\"row\"></td>");
				}else{
					sb1.append("<td scope=\"row\">"+pageInfo1.getList().get(i).getT()+"</td>");
				}
				if(pageInfo1.getList().get(i).getR()==null
						||"null".equals(pageInfo1.getList().get(i).getR())){
					sb1.append("<td scope=\"row\"></td>");
				}else{
					sb1.append("<td scope=\"row\">"+pageInfo1.getList().get(i).getR()+"</td>");
				}
				if(pageInfo1.getList().get(i).getWid()==null
						||"null".equals(pageInfo1.getList().get(i).getWid())){
					sb1.append("<td scope=\"row\"></td>");
				}else{
					sb1.append("<td scope=\"row\">"+pageInfo1.getList().get(i).getWid()+"</td>");
				}
				if(pageInfo1.getList().get(i).getSt()==null
						||"null".equals(pageInfo1.getList().get(i).getSt())){
					sb1.append("<td scope=\"row\"></td>");
				}else{
					sb1.append("<td scope=\"row\">"+pageInfo1.getList().get(i).getSt()+"</td>");
				}
				sb1.append("<td><a onclick=\"disassembly("+pageInfo1.getList().get(i).getId()+",1); \" href=\"javascript:;\" class=\"btn btn-default\" style=\"padding-top:2px;background-color: #d9534f;color: white;\">拆解</a></td>");
				sb1.append("</tr>");
			}


			PageHelper.startPage(queryPage2,8);
			List<PMesKeypartT> keypartList = reportService.keypartList(map);
			PageInfo<PMesKeypartT> pageInfo2 = new PageInfo<>(keypartList,5);
			StringBuffer sb2 = new StringBuffer();

			for(int i=0;i<pageInfo2.getSize();i++){
				sb2.append("<tr>");
				if(GetDate.getDateforSimple(pageInfo2.getList().get(i).getDt())==null
						||"null".equals(GetDate.getDateforSimple(pageInfo2.getList().get(i).getDt()))){

					sb2.append("<td scope=\"row\"></td>");
				}else{
					sb2.append("<td scope=\"row\">"+GetDate.getDateforSimple(pageInfo2.getList().get(i).getDt())+"</td>");

				}
				if(pageInfo2.getList().get(i).getKeypartName()==null
						||"null".equals(pageInfo2.getList().get(i).getKeypartName())){

					sb2.append("<td scope=\"row\"></td>");
				}else{

					sb2.append("<td scope=\"row\">"+pageInfo2.getList().get(i).getKeypartName()+"</td>");
				}
				if(pageInfo2.getList().get(i).getKeypartNum()==null
						||"null".equals(pageInfo2.getList().get(i).getKeypartNum())){
					sb2.append("<td scope=\"row\"></td>");
				}else{
					sb2.append("<td scope=\"row\">"+pageInfo2.getList().get(i).getKeypartNum()+"</td>");
				}
				if(pageInfo2.getList().get(i).getWid()==null
						||"null".equals(pageInfo2.getList().get(i).getWid())){
					sb2.append("<td scope=\"row\"></td>");
				}else{
					sb2.append("<td scope=\"row\">"+pageInfo2.getList().get(i).getWid()+"</td>");
				}
				if(pageInfo2.getList().get(i).getSt()==null
						||"null".equals(pageInfo2.getList().get(i).getSt())){
					sb2.append("<td scope=\"row\"></td>");
				}else{
					sb2.append("<td scope=\"row\">"+pageInfo2.getList().get(i).getSt()+"</td>");
				}


				sb2.append("<td><a onclick=\"disassembly("+pageInfo2.getList().get(i).getId()+",2); \" href=\"javascript:;\" class=\"btn btn-default\" style=\"padding-top:2px;background-color: #d9534f;color: white;\">拆解</a></td>");
				sb2.append("</tr>");
			}




			PageHelper.startPage(queryPage3,8);
			List<PMesLeakageT> leakageList = reportService.leakageList(map);
			PageInfo<PMesLeakageT> pageInfo3 = new PageInfo<>(leakageList,5);
			StringBuffer sb3 = new StringBuffer();
			for(int i=0;i<pageInfo3.getSize();i++){
				sb3.append("<tr>");

				if(GetDate.getDateforSimple(pageInfo3.getList().get(i).getDt())==null
						||"null".equals(GetDate.getDateforSimple(pageInfo3.getList().get(i).getDt()))){

					sb3.append("<td scope=\"row\"></td>");
				}else{
					sb3.append("<td scope=\"row\">"+GetDate.getDateforSimple(pageInfo3.getList().get(i).getDt())+"</td>");

				}

				if(pageInfo3.getList().get(i).getLeakageName()==null
						||"null".equals(pageInfo3.getList().get(i).getLeakageName())){

					sb3.append("<td scope=\"row\"></td>");
				}else{
					sb3.append("<td scope=\"row\">"+pageInfo3.getList().get(i).getLeakageName()+"</td>");

				}



				if(pageInfo3.getList().get(i).getLeakagePv()==null
						||"null".equals(pageInfo3.getList().get(i).getLeakagePv())){
					sb3.append("<td scope=\"row\"></td>");
				}else{

					sb3.append("<td scope=\"row\">"+pageInfo3.getList().get(i).getLeakagePv()+"</td>");
				}
				if(pageInfo3.getList().get(i).getLeakageLv()==null
						||"null".equals(pageInfo3.getList().get(i).getLeakageLv())){
					sb3.append("<td scope=\"row\"></td>");
				}else{

					sb3.append("<td scope=\"row\">"+pageInfo3.getList().get(i).getLeakageLv()+"</td>");
				}
				if(pageInfo3.getList().get(i).getLeakageR()==null
						||"null".equals(pageInfo3.getList().get(i).getLeakageR())){
					sb3.append("<td scope=\"row\"></td>");
				}else{

					sb3.append("<td scope=\"row\">"+pageInfo3.getList().get(i).getLeakageR()+"</td>");
				}
				if(pageInfo3.getList().get(i).getWid()==null
						||"null".equals(pageInfo3.getList().get(i).getWid())){
					sb3.append("<td scope=\"row\"></td>");
				}else{
					sb3.append("<td scope=\"row\">"+pageInfo3.getList().get(i).getWid()+"</td>");
				}
				if(pageInfo3.getList().get(i).getSt()==null
						||"null".equals(pageInfo3.getList().get(i).getSt())){
					sb3.append("<td scope=\"row\"></td>");
				}else{
					sb3.append("<td scope=\"row\">"+pageInfo3.getList().get(i).getSt()+"</td>");
				}
				sb3.append("<td><a onclick=\"disassembly("+pageInfo3.getList().get(i).getId()+",3); \" href=\"javascript:;\" class=\"btn btn-default\" style=\"padding-top:2px;background-color: #d9534f;color: white;\">拆解</a></td>");
				sb3.append("</tr>");
			}

			map.put("sn", sn);
			map.put("sb1", sb1);
			map.put("sb2", sb2);
			map.put("sb3", sb3);
			map.put("pageInfo1", pageInfo1);
			map.put("pageInfo2", pageInfo2);
			map.put("pageInfo3", pageInfo3);
			model.addAttribute("reworkRoute", rTracking);
			map.put("rework", rTrackingT);
			map.put("reworks", findReworkBySn);
			map.put("listStationByLineId", listStationByLineId);
			map.put("msg", 0);
		}else{
			map.put("msg", 1);
		}
		return map;
	}

	//信息移动
	@RequestMapping("addReworkRoute")
	public String insPtrakingT(Model model,HttpServletRequest request){
		Map<String, Object> map=new HashMap<>();
		String id = request.getParameter("ids");
		System.err.println(id);
		String dis=request.getParameter("dis");
		String reasonDetail=request.getParameter("reasonDetail");
		String engineSN=request.getParameter("engineSN");
		String sn=request.getParameter("sn");
		map.put("id", id);
		map.put("dis", dis);
		map.put("SN", sn);
		map.put("reasonDetail", reasonDetail);
		map.put("defectType", dis);
		RTrackingT findReworkRoute = reworkRouteService.findReworkRoute(map);
		map.put("dt",findReworkRoute.getDT() );
		map.put("st", findReworkRoute.getST());
		map.put("bst", findReworkRoute.getBST());
		System.err.println(findReworkRoute.getBST());
		map.put("engineSn", engineSN);
		map.put("dis", findReworkRoute.getDis());
		map.put("planId", findReworkRoute.getPlan_id());
		map.put("reworkFlag", findReworkRoute.getRework_flag());
		map.put("typeName", findReworkRoute.getTypeName());
		map.put("trayNum", findReworkRoute.getTrayNum());
		map.put("productNum", findReworkRoute.getProductNum());
		map.put("status", findReworkRoute.getStatus());
		map.put("gearboxSn", findReworkRoute.getGearboxSN());
		map.put("sysDate", new Date());
		reworkRouteService.addPTracking(map);
		reworkRouteService.adddefectReason(map);
		reworkRouteService.delRTracking(map);
		return "redirect:reason";
	}

	//返工
	@RequestMapping("update")
	public String update(Model model,HttpServletRequest request){
		Map<String, Object> map=new HashMap<>();
		int id=Integer.parseInt(request.getParameter("ida"));
		String engineSN=request.getParameter("engineSN");
		String dis=request.getParameter("dis");
		map.put("id", id);
		map.put("dis", dis);
		map.put("engineSN", engineSN);
		reworkRouteService.updRework(map);
		return "redirect:/ReworkRoute/reason";
	}

	//产生序列号
	@SuppressWarnings("rawtypes")
	@RequestMapping("updateReworkWayT")
	public @ResponseBody Map updateReworkWayT(HttpServletRequest request,Model model){
		Map<String, Object> map = new HashMap<>();
		String menuIds = request.getParameter("menuIds");	//序列号
		String sn=request.getParameter("sn").trim();		//总成
		String dis=request.getParameter("dis");				//原因
		String engineSN=request.getParameter("engineSN");	//下线类型
		//String st=request.getParameter("stationId");		//工位ID
		String id=request.getParameter("idW");
		map.put("dis", dis);
		map.put("engineSN", engineSN);
		map.put("id", id);
		reworkRouteService.updReworkRoute(map);
		map.put("sn", sn);
		reworkRouteService.delReworkRoute(map);
		String str[] = menuIds.split(",");
		int count = 1;
		for (int i = 0; i < str.length; i++) {
			map.put("sertalNo", count);
			map.put("stName", str[i]);
			map.put("stationName",str[i]);
			ReworkWayT reworkWayT=reworkRouteService.selectNo(map);
			if(reworkWayT!=null){
				reworkRouteService.deleteNo(map);
			}
			int stationId=reworkRouteService.findStationId(map);
			if(i==0){
				CMesStationT cMesStationT=reworkRouteService.stationName(map);
				if(cMesStationT!=null){
					map.put("msg", "ok");
				}else{
					map.put("msg", "null");
					return map;
				}
			}
			map.put("dt", new Date());
			map.put("stId", stationId);
			count++;
			reworkRouteService.insert(map);
		}
		return map;
	}

}
