package com.skeqi.mes.controller.all;

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

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesPatrolT;
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.service.all.CMesProductionTService;
import com.skeqi.mes.service.all.CMesStationTService;
import com.skeqi.mes.service.all.QualityService;
import com.skeqi.mes.util.ExcelUtil;
import com.skeqi.mes.util.ToolUtils;


@RequestMapping("patrol")
@Controller
public class CMesPatrolController {


	@Autowired
	QualityService qualityService;

	@Autowired
	CMesProductionTService productionService;

	@Autowired
	CMesStationTService stationService;


	@RequestMapping("/patrollist")
	public String patrollist(HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page){
		PageHelper.startPage(page, 8);
		Map<String,Object> map = new HashMap<String,Object>();
		String sn = request.getParameter("sn");
		String pid = request.getParameter("pid");
		String sid = request.getParameter("sid");
		request.setAttribute("sn",sn);
		request.setAttribute("sid",sid);
		request.setAttribute("pid",pid);
		CMesPatrolT patrol = new CMesPatrolT();
		CMesProductionT pro = new CMesProductionT();
		CMesStationT station = new CMesStationT();
		if(pid!=null && pid!="") {
			patrol.setProductionId(Integer.parseInt(pid));
		}
		if(sn!=null && sn!="") {
			patrol.setSn(sn);
		}
		if(sid!=null && sid!="") {
			patrol.setStationId(Integer.parseInt(sid));
		}
		try {
		List<CMesPatrolT> findAll = qualityService.findAllPatrol(patrol);

		List<CMesProductionT> findPro = productionService.findAllProduction(pro);

		List<CMesStationT> findStation = stationService.findAllStation(station);

		PageInfo<CMesPatrolT> pageInfo = new PageInfo<CMesPatrolT>(findAll,5);
		request.setAttribute("pageInfo",pageInfo);
		request.setAttribute("findPro", findPro);
		request.setAttribute("findStation", findStation);
		}catch (Exception e) {
			// TODO: handle exception
		}
		return "quality_control/patrolmanager";
	}

	@RequestMapping("/insertpatrol")
	@ResponseBody
	public JSONObject insertPatrol(HttpServletRequest request){
		JSONObject json = new JSONObject();
		CMesPatrolT c = new CMesPatrolT();
		String pro = request.getParameter("pro");
		String st = request.getParameter("st");
		String emps = request.getParameter("emps");
		String sn = request.getParameter("sns");
		String reason = request.getParameter("reason");
		c.setProductionId(Integer.parseInt(pro));
		c.setStationId(Integer.parseInt(st));
		c.setEmp(emps);
		c.setSn(sn);
		c.setReason(reason);
		try {
			 qualityService.addPatrol(c);
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

	@RequestMapping("/editpatrol")
	@ResponseBody
	public JSONObject editpatrol(HttpServletRequest request){
		String id = request.getParameter("id");
		JSONObject json = new JSONObject();
		try {
			CMesPatrolT findByid = qualityService.findPatrolByid(Integer.parseInt(id));
			json.put("findByid", findByid);
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

	@RequestMapping("/updatepatrol")
	@ResponseBody
	public JSONObject updatepatrol(HttpServletRequest request){
		JSONObject json = new JSONObject();
		String pro = request.getParameter("pros");
		String id = request.getParameter("id");
		String st = request.getParameter("sts");
		String emps = request.getParameter("empone");
		String sn = request.getParameter("snone");
		String reason = request.getParameter("reasons");
		CMesPatrolT c = new CMesPatrolT();
		c.setProductionId(Integer.parseInt(pro));
		c.setStationId(Integer.parseInt(st));
		c.setEmp(emps);
		c.setSn(sn);
		c.setReason(reason);
		c.setId(Integer.parseInt(id));
		try {
			qualityService.updatePatrol(c);
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

	@RequestMapping("/delpatrol")
	@ResponseBody
	public  JSONObject delpatrol(HttpServletRequest request){
		String id = request.getParameter("id");
		JSONObject json = new JSONObject();

		try {
			qualityService.delPatrol(Integer.parseInt(id));
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

	@RequestMapping("/exportpatrol")
	@ResponseBody
	public void exportpatrol(HttpServletRequest request,HttpServletResponse response){
		String sn = request.getParameter("sn");
		String pid = request.getParameter("pid");
		String sid = request.getParameter("sid");

		CMesPatrolT c = new CMesPatrolT();
		if(pid!=null) {
			c.setProductionId(Integer.parseInt(pid));
		}
		if(sid!=null) {
			c.setStationId(Integer.parseInt(sid));
		}
		if(sn!=null) {
			c.setSn(sn);
		}
		if(pid.equals("undefined")){
			pid=null;
		}
		if(sn.equals("undefined")){
			sn=null;
		}
		if(sid.equals("undefined")){
			sid=null;
		}
		try {
			SimpleDateFormat s = new SimpleDateFormat("yyyyMMddHHmmss");
			List<CMesPatrolT> findAll = qualityService.findAllPatrol(c);
			HSSFWorkbook  book = new HSSFWorkbook();
			String headers[] = {"总成号","时间","产品名称","工位名称","巡检员工","原因"};
			ExcelUtil.patrolExcel(findAll, book, headers);
			ExcelUtil.export(response, book, s.format(new Date()) + ".xls");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}

	}

}
