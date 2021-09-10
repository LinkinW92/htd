package com.skeqi.mes.controller.wms.homepage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.neo4j.cypher.internal.compiler.v2_2.planner.logical.greedy.expand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skeqi.mes.pojo.wms.CWmsLocationT;
import com.skeqi.mes.pojo.wms.CWmsStorageListDetailT;
import com.skeqi.mes.service.wms.homepage.HomepageService;
import com.skeqi.mes.util.ToolUtils;


@Controller
@RequestMapping("/home")
public class HomepageController {

	@Autowired
	private HomepageService homepageService;

	// 首页
	@RequestMapping("/Homepage")
	public String Homepage(HttpServletRequest request) {
		Map<String, String> dateMap = date();
		request.setAttribute("dateMap", dateMap);
		String ks = dateMap.get("ks");
		String js = dateMap.get("js");
		// 出库量查询
		Integer ck = outboundQuantityQuery(ks, js);
		request.setAttribute("ck", ck);
		// 入库量查询
		Integer rk = inputVolumeQuery(dateMap.get("ks"), dateMap.get("js"));
		request.setAttribute("rk", rk);
		// 库存余量查询
		Integer yl = inventoryMarginQuery();
		request.setAttribute("yl", yl);

		try {
			Map<String,String> qxdateMap = qxtdate();
			// 曲线图入库查询
			request.setAttribute("rkfind", rkfind(qxdateMap.get("ks"),qxdateMap.get("js")));
			// 曲线图出库查询
			request.setAttribute("ckfind", ckfind(qxdateMap.get("ks"),qxdateMap.get("js")));

		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}

		//库位查询
		List<CWmsLocationT> kwfind = cWmsLocationTQuery();
		request.setAttribute("kwfid", kwfind);

		if(request.getParameter("page")!=null&&request.getParameter("page").equals("1")){
			return "stock_control/inventoryBalance";
		}
		return "/wms/view/index";
	}

	// 出库量查询
	public Integer outboundQuantityQuery(String ks, String js) {
		return homepageService.outboundQuantityQuery(ks, js);
	};

	// 入库量查询
	public Integer inputVolumeQuery(String ks, String js) {
		return homepageService.inputVolumeQuery(ks, js);
	};

	// 库存余量查询
	public Integer inventoryMarginQuery() {
		return homepageService.inventoryMarginQuery();
	};

	// 通知消息查询
	public Integer messageNumberQuery() {
		return homepageService.messageNumberQuery();
	};

	// 获取时间
	public Map<String, String> date() {
		SimpleDateFormat dfjs = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		SimpleDateFormat dfks = new SimpleDateFormat("yyyy-MM-");// 设置日期格式
		String js = dfjs.format(new Date());// js结束时间
		String ks = dfks.format(new Date()) + 01;// ks时间
		Map<String, String> dateMap = new HashMap<String, String>();
		dateMap.put("ks", ks);
		dateMap.put("js", js);
		return dateMap;
	}

	// 曲线图入库查询
	public List<CWmsStorageListDetailT> rkfind(String ks,String js) {
		List<CWmsStorageListDetailT> cWmsStorageListDetailTs = homepageService.rkfind(ks,js);
//		List<CWmsStorageListDetailT> list = new ArrayList<CWmsStorageListDetailT>();
//		list.add(cWmsStorageListDetailTs.get(0));
//		cWmsStorageListDetailTs.remove(0);
		//去重复
//		for (CWmsStorageListDetailT c1 : cWmsStorageListDetailTs) {
//			for (int i = 0; i < list.size(); i++) {
//				if(c1.getQxtrkdate().equals(list.get(i).getQxtrkdate())){
//					list.get(i).setMaterialNumber(list.get(i).getMaterialNumber()+c1.getMaterialNumber());
//					break;
//				}
//				if(i+1==list.size()){
//					CWmsStorageListDetailT cw = new CWmsStorageListDetailT();
//					cw = c1;
//					list.add(cw);
//				}
//			}
//		}
		return cWmsStorageListDetailTs;
	};

	// 曲线图出库查询
	public List<CWmsStorageListDetailT> ckfind(String ks,String js) {
		List<CWmsStorageListDetailT> cWmsStorageListDetailTs = homepageService.ckfind(ks,js);
//		List<CWmsStorageListDetailT> list = new ArrayList<CWmsStorageListDetailT>();
//		list.add(cWmsStorageListDetailTs.get(0));
//		cWmsStorageListDetailTs.remove(0);
		//去重复
//		for (CWmsStorageListDetailT c1 : cWmsStorageListDetailTs) {
//			for (int i = 0; i < list.size(); i++) {
//				if(c1.getQxtrkdate().equals(list.get(i).getQxtrkdate())){
//					list.get(i).setMaterialNumber(list.get(i).getMaterialNumber()+c1.getMaterialNumber());
//					break;
//				}
//				if(i+1==list.size()){
//					CWmsStorageListDetailT cw = new CWmsStorageListDetailT();
//					cw = c1;
//					list.add(cw);
//				}
//			}
//		}
		return cWmsStorageListDetailTs;
	};

	// 曲线图获取时间
	public Map<String, String> qxtdate() throws Exception{
		Map<String, String> dateMap = new HashMap<String, String>();
		SimpleDateFormat sj = new SimpleDateFormat("yyyy-MM-dd");
        String today = sj.format(new Date());
        Date d = sj.parse(today);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.add(Calendar.DATE, 1);
        dateMap.put("js", sj.format(calendar.getTime()));
        calendar.add(calendar.DATE, -11);
		dateMap.put("ks", sj.format(calendar.getTime()));
		return dateMap;
	}

	//库位查询
	@RequestMapping("cha")
	@ResponseBody
	public List<CWmsLocationT> cWmsLocationTQuery(){
		return homepageService.cWmsLocationTQuery();
	};

}
