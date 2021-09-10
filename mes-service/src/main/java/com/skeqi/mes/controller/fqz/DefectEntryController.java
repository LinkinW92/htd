package com.skeqi.mes.controller.fqz;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.CMesDefectEntryT;
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.service.fqz.DefectEntryService;
import com.skeqi.mes.util.ExcelUtil;

/**
 * 		缺陷录入
 * @author : FQZ
 * @Package: com.skeqi.commen.dgl
 * @date   : 2019年10月10日 下午3:05:37
 */

@Controller
@RequestMapping("defects")
public class DefectEntryController {


	@Autowired
	private DefectEntryService service;


//	@RequestMapping("/defectreason")
//	public String defectreason(HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page){
//		PageHelper.startPage(page,8);
//		Map<String,Object> map = new HashMap<>();
//		String sn = request.getParameter("sn");
//		String starttime = request.getParameter("act_start_time");
//		String endtime = request.getParameter("act_stop_time");
//		map.put("sn", sn);
//		map.put("starttime", starttime);
//		map.put("endtime", endtime);
//		request.setAttribute("starttime", starttime);
//		request.setAttribute("endtime", endtime);
//		request.setAttribute("sn",sn);
//		List<CMesDefectEntryT> findAll = service.findAll(map);
//		PageInfo<CMesDefectEntryT> pageInfo = new PageInfo<CMesDefectEntryT>(findAll,5);
//		List<CMesProductionT> findProduction = service.findProduction();
//		request.setAttribute("pageInfo",pageInfo);
//		request.setAttribute("findProduction",findProduction);
//		return "quality_control/defectreason";
//	}
//
//	@RequestMapping("/insertentry")
//	@ResponseBody
//	public Map<String,Object> insertentry(HttpServletRequest request){
//		Map<String,Object> map = new HashMap<>();
//		CMesDefectEntryT c = new CMesDefectEntryT();
//		String pro = request.getParameter("pro");
//		String sn = request.getParameter("sid");
//		String emp = request.getParameter("ename");
//		String reason = request.getParameter("note");
//		c.setEmp(emp);
//		c.setReason(reason);
//		c.setSn(sn);
//		c.setProductionId(Integer.parseInt(pro));
//		try {
//			service.insertentry(c);
//			map.put("msg",1);
//		} catch (Exception e) {
//			e.printStackTrace();
//			map.put("msg",2);
//		}
//		return map;
//	}
//
//	@RequestMapping("/editentry")
//	@ResponseBody
//	public CMesDefectEntryT editentry(HttpServletRequest request){
//		String id = request.getParameter("id");
//		CMesDefectEntryT findByid = service.findByid(id);
//		return findByid;
//	}
//
//	@RequestMapping("/updateentry")
//	@ResponseBody
//	public Map<String,Object> updateentry(HttpServletRequest request){
//		Map<String,Object> map = new HashMap<>();
//		CMesDefectEntryT c = new CMesDefectEntryT();
//		String id = request.getParameter("id");
//		String pro = request.getParameter("pros");
//		String sn = request.getParameter("sids");
//		String emp = request.getParameter("enames");
//		String reason = request.getParameter("notes");
//		c.setEmp(emp);
//		c.setReason(reason);
//		c.setSn(sn);
//		c.setId(Integer.parseInt(id));
//		c.setProductionId(Integer.parseInt(pro));
//		try {
//			service.updateentry(c);
//			map.put("msg",1);
//		} catch (Exception e) {
//			e.printStackTrace();
//			map.put("msg",2);
//		}
//		return map;
//	}
//
//	@RequestMapping("/deleteentry")
//	@ResponseBody
//	public Map<String,Object>  deleteentry(HttpServletRequest request){
//		Map<String,Object> map = new HashMap<>();
//		String id = request.getParameter("id");
//		try {
//			service.deleteentry(id);
//			map.put("msg",1);
//		} catch (Exception e) {
//			e.printStackTrace();
//			map.put("msg",2);
//		}
//		return map;
//	}
//
//	@RequestMapping("/exportentry")
//	@ResponseBody
//	public void exportentry(HttpServletRequest request,HttpServletResponse response){
//		Map<String,Object> map = new HashMap<>();
//		SimpleDateFormat s = new SimpleDateFormat("yyyyMMddHHmmss");
//		String sn = request.getParameter("sn");
//		String starttime = request.getParameter("act_start_time");
//		String endtime = request.getParameter("act_stop_time");
//		if(sn.equals("undefined")){
//			sn=null;
//		}
//		if(starttime.equals("undefined")){
//			starttime=null;
//		}
//		if(endtime.equals("undefined")){
//			endtime=null;
//		}
//		map.put("sn", sn);
//		map.put("starttime", starttime);
//		map.put("endtime", endtime);
//		try {
//			List<CMesDefectEntryT> findAll = service.findAll(map);
//			HSSFWorkbook  book = new HSSFWorkbook();
//			String headers[] = {"总成号","时间","产品名称","缺陷原因","录入员工"};
//			ExcelUtil.entryExcel(findAll,book,headers);
//			ExcelUtil.export(response, book, s.format(new Date()) + ".xls");
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}
}
