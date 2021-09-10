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
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.pojo.CMesReturnRepairT;
import com.skeqi.mes.service.fqz.ReturnRepairService;
import com.skeqi.mes.util.ExcelUtil;

@Controller
@RequestMapping("/repair")
public class ReturnRepairController {

	@Autowired
	private ReturnRepairService service;

//
//	@RequestMapping("/repairlist")
//	public String repairlist(HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page){
//		PageHelper.startPage(page,15);
//		Map<String,Object> map = new HashMap<>();
//		String sn = request.getParameter("sn");
//		String lid = request.getParameter("lid");
//		String pid = request.getParameter("pid");
//		String status = request.getParameter("status");
//		map.put("sn", sn);
//		map.put("productionId", pid);
//		map.put("lineId", lid);
//		map.put("status", status);
//		request.setAttribute("sn",sn);
//		request.setAttribute("status",status);
//		request.setAttribute("pid",pid);
//		request.setAttribute("lid",lid);
//		List<CMesReturnRepairT> findAll = service.findAll(map);
//		PageInfo<CMesReturnRepairT> pageInfo = new PageInfo<CMesReturnRepairT>(findAll,5);
//		List<CMesLineT> findLine = service.findLine();
//		List<CMesProductionT> findPro = service.findPro();
//		request.setAttribute("pageInfo", pageInfo);
//		request.setAttribute("findLine", findLine);
//		request.setAttribute("findPro", findPro);
//		return "quality_control/returnrepair";
//	}
//
//	@RequestMapping("/insertrerepair")
//	@ResponseBody
//	public Map<String,Object> insertrerepair(HttpServletRequest request){
//		CMesReturnRepairT c = new CMesReturnRepairT();
//		Map<String,Object> map = new HashMap<String,Object>();
//		String sns = request.getParameter("sns");
//		String reason = request.getParameter("reason");
//		String lines = request.getParameter("lines");
//		String pro = request.getParameter("pro");
//		c.setSn(sns);
//		c.setReason(reason);
//		c.setLineId(Integer.parseInt(lines));
//		c.setProductionId(Integer.parseInt(pro));
//		try {
//			service.insertReRepair(c);
//			map.put("msg",1);
//		} catch (Exception e) {
//			e.printStackTrace();
//			map.put("msg",2);
//			// TODO: handle exception
//		}
//		return map;
//	}
//
//	@RequestMapping("/updatererepair")
//	@ResponseBody
//	public Map<String,Object> updatererepair(HttpServletRequest request){
//		String id = request.getParameter("id");
//		Map<String,Object> map = new HashMap<String,Object>();
//		try {
//			service.endRepair(id);
//			map.put("msg",1);
//		} catch (Exception e) {
//			e.printStackTrace();
//			map.put("msg",2);
//			// TODO: handle exception
//		}
//		return map;
//	}
//
//	@RequestMapping("/exportreapair")
//	@ResponseBody
//	public void exportreapair(HttpServletRequest request,HttpServletResponse response){
//		Map<String,Object> map = new HashMap<String,Object>();
//		String sn = request.getParameter("sn");
//		String lid = request.getParameter("lid");
//		String pid = request.getParameter("pid");
//		String status = request.getParameter("status");
//		map.put("sn", sn);
//		map.put("productionId", pid);
//		map.put("lineId", lid);
//		map.put("status", status);
//		try {
//			SimpleDateFormat s = new SimpleDateFormat("yyyyMMddHHmmss");
//			List<CMesReturnRepairT> findAll = service.findAll(map);
//			HSSFWorkbook  book = new HSSFWorkbook();
//			String headers[] = {"总成号","返厂时间","产品名称","产线名称","维修完成时间","原因"};
//			ExcelUtil.reapairExcel(findAll, book, headers);
//			ExcelUtil.export(response, book, s.format(new Date()) + ".xls");
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}
}
