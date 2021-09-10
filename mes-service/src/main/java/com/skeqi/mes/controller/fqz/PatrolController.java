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
import com.skeqi.mes.pojo.CMesPatrolT;
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.service.fqz.PatrolService;
import com.skeqi.mes.util.ExcelUtil;

@RequestMapping("patrol")
@Controller
public class PatrolController {

	@Autowired
	private PatrolService service;


//	@RequestMapping("/patrollist")
//	public String patrollist(HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page){
//		PageHelper.startPage(page, 8);
//		Map<String,Object> map = new HashMap<String,Object>();
//		String sn = request.getParameter("sn");
//		String pid = request.getParameter("pid");
//		String sid = request.getParameter("sid");
//		map.put("productionId",pid);
//		map.put("sn",sn);
//		map.put("stationId",sid);
//		request.setAttribute("sn",sn);
//		request.setAttribute("sid",sid);
//		request.setAttribute("pid",pid);
//		List<CMesPatrolT> findAll = service.findAll(map);
//		List<CMesProductionT> findPro = service.findPro();
//		List<CMesStationT> findStation = service.findStation();
//		PageInfo<CMesPatrolT> pageInfo = new PageInfo<CMesPatrolT>(findAll,5);
//		request.setAttribute("pageInfo",pageInfo);
//		request.setAttribute("findPro", findPro);
//		request.setAttribute("findStation", findStation);
//		return "quality_control/patrolmanager";
//	}
//
//	@RequestMapping("/insertpatrol")
//	@ResponseBody
//	public Map<String,Object> insertPatrol(HttpServletRequest request){
//		Map<String,Object> map = new HashMap<>();
//		CMesPatrolT c = new CMesPatrolT();
//		String pro = request.getParameter("pro");
//		String st = request.getParameter("st");
//		String emps = request.getParameter("emps");
//		String sn = request.getParameter("sns");
//		String reason = request.getParameter("reason");
//		c.setProductionId(Integer.parseInt(pro));
//		c.setStationId(Integer.parseInt(st));
//		c.setEmp(emps);
//		c.setSn(sn);
//		c.setReason(reason);
//		try {
//			service.insertPatrol(c);
//			map.put("msg", 1);
//		} catch (Exception e) {
//			e.printStackTrace();
//			map.put("msg",2);
//		}
//		return map;
//	}
//
//	@RequestMapping("/editpatrol")
//	@ResponseBody
//	public CMesPatrolT editpatrol(HttpServletRequest request){
//		String id = request.getParameter("id");
//		CMesPatrolT findByid = service.findByid(id);
//		return findByid;
//	}
//
//	@RequestMapping("/updatepatrol")
//	@ResponseBody
//	public Map<String,Object> updatepatrol(HttpServletRequest request){
//		Map<String,Object> map = new HashMap<>();
//		CMesPatrolT c = new CMesPatrolT();
//		String pro = request.getParameter("pros");
//		String id = request.getParameter("id");
//		String st = request.getParameter("sts");
//		String emps = request.getParameter("empone");
//		String sn = request.getParameter("snone");
//		String reason = request.getParameter("reasons");
//		c.setProductionId(Integer.parseInt(pro));
//		c.setStationId(Integer.parseInt(st));
//		c.setEmp(emps);
//		c.setSn(sn);
//		c.setReason(reason);
//		c.setId(Integer.parseInt(id));
//		try {
//			service.updatePatrol(c);
//			map.put("msg", 1);
//		} catch (Exception e) {
//			e.printStackTrace();
//			map.put("msg",2);
//		}
//		return map;
//	}
//
//	@RequestMapping("/delpatrol")
//	@ResponseBody
//	public  Map<String,Object> delpatrol(HttpServletRequest request){
//		String id = request.getParameter("id");
//		Map<String,Object> map = new HashMap<>();
//		try {
//			service.delPatrol(id);
//			map.put("msg", 1);
//		} catch (Exception e) {
//			e.printStackTrace();
//			map.put("msg",2);
//		}
//		return map;
//	}
//
//	@RequestMapping("/exportpatrol")
//	@ResponseBody
//	public void exportpatrol(HttpServletRequest request,HttpServletResponse response){
//		Map<String,Object> map = new HashMap<String,Object>();
//		Map<String,Object> maps = new HashMap<String,Object>();
//		String sn = request.getParameter("sn");
//		String pid = request.getParameter("pid");
//		String sid = request.getParameter("sid");
//		map.put("productionId",pid);
//		map.put("sn",sn);
//		map.put("stationId",sid);
//		if(pid.equals("undefined")){
//			pid=null;
//		}
//		if(sn.equals("undefined")){
//			sn=null;
//		}
//		if(sid.equals("undefined")){
//			sid=null;
//		}
//		try {
//			SimpleDateFormat s = new SimpleDateFormat("yyyyMMddHHmmss");
//			List<CMesPatrolT> findAll = service.findAll(map);
//			HSSFWorkbook  book = new HSSFWorkbook();
//			String headers[] = {"总成号","时间","产品名称","工位名称","巡检员工","原因"};
//			ExcelUtil.patrolExcel(findAll, book, headers);
//			ExcelUtil.export(response, book, s.format(new Date()) + ".xls");
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}
}
