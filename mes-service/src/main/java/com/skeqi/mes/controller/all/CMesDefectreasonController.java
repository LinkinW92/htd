package com.skeqi.mes.controller.all;

import java.text.ParseException;
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
import com.skeqi.mes.pojo.CMesDefectEntryT;
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.service.all.CMesProductionTService;
import com.skeqi.mes.service.all.QualityService;
import com.skeqi.mes.util.ExcelUtil;
import com.skeqi.mes.util.ToolUtils;

/***
 *
 * @author ENS 缺陷录入
 *
 */
@Controller
@RequestMapping("defects")
public class CMesDefectreasonController {

	@Autowired
	QualityService qualityService;
	@Autowired
	CMesProductionTService productionService;



	@RequestMapping("/defectreason")
	public String defectreason(HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page){
		PageHelper.startPage(page,8);
		String sn = request.getParameter("sn");
		String starttime = request.getParameter("act_start_time");
		String endtime = request.getParameter("act_stop_time");

		request.setAttribute("starttime", starttime);
		request.setAttribute("endtime", endtime);
		request.setAttribute("sn",sn);
		CMesDefectEntryT entry = new CMesDefectEntryT();
		if(sn!=null) {
			entry.setSn(sn);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		if(starttime!=null&&endtime!=null&&!"".equals(starttime)&&!"".equals(endtime)) {

			entry.setStarttime(starttime);
			entry.setEndtime(endtime);

		}
		try {
		List<CMesDefectEntryT> findAll = qualityService.findAllDefectEntry(entry);

		PageInfo<CMesDefectEntryT> pageInfo = new PageInfo<CMesDefectEntryT>(findAll,5);
		CMesProductionT pro = new CMesProductionT();
		List<CMesProductionT> findProduction = productionService.findAllProduction(pro);
		request.setAttribute("pageInfo",pageInfo);
		request.setAttribute("findProduction",findProduction);
		System.out.println("pageInfo================"+pageInfo);
		}catch (Exception e) {
			// TODO: handle exception
		}
		return "quality_control/defectreason";
	}

	@RequestMapping("/insertentry")
	@ResponseBody
	public JSONObject insertentry(HttpServletRequest request){
		JSONObject json = new JSONObject();
		CMesDefectEntryT c = new CMesDefectEntryT();
		String pro = request.getParameter("pro");
		String sn = request.getParameter("sid");
		String emp = request.getParameter("ename");
		String reason = request.getParameter("note");
		c.setEmp(emp);
		c.setReason(reason);
		c.setSn(sn);
		c.setProductionId(Integer.parseInt(pro));


		try {
			qualityService.addDefectEntry(c);
			json.put("code", 0);
		}catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}
		return json;
	}

	@RequestMapping("/editentry")
	@ResponseBody
	public JSONObject editentry(HttpServletRequest request){
		String id = request.getParameter("id");
		JSONObject json = new JSONObject();
		try {
			CMesDefectEntryT findByid  = qualityService.findDefectEntryByid(Integer.parseInt(id));
			json.put("findByid", findByid);
			json.put("code", 0);
		}catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}
		return json;
	}

	@RequestMapping("/updateentry")
	@ResponseBody
	public JSONObject updateentry(HttpServletRequest request){
		JSONObject json = new JSONObject();
		CMesDefectEntryT c = new CMesDefectEntryT();
		String id = request.getParameter("id");
		String pro = request.getParameter("pros");
		String sn = request.getParameter("sids");
		String emp = request.getParameter("enames");
		String reason = request.getParameter("notes");
		c.setEmp(emp);
		c.setReason(reason);
		c.setSn(sn);
		c.setId(Integer.parseInt(id));
		c.setProductionId(Integer.parseInt(pro));
		try {
			qualityService.updateDefectEntry(c);
			json.put("code", 0);
		}catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}
		return json;
	}

	@RequestMapping("/deleteentry")
	@ResponseBody
	public JSONObject  deleteentry(HttpServletRequest request){
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");
		try {
			qualityService.delDefectEntry(Integer.parseInt(id));
			json.put("code", 0);
		}catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}
		return json;
	}

	@RequestMapping("/exportentry")
	@ResponseBody
	public void exportentry(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> map = new HashMap<>();
		SimpleDateFormat s = new SimpleDateFormat("yyyyMMddHHmmss");
		String sn = request.getParameter("sn");
		String starttime = request.getParameter("act_start_time");
		String endtime = request.getParameter("act_stop_time");
		if(sn.equals("undefined")){
			sn=null;
		}
		if(starttime.equals("undefined")){
			starttime=null;
		}
		if(endtime.equals("undefined")){
			endtime=null;
		}
		CMesDefectEntryT entry = new CMesDefectEntryT();
		if(sn!=null) {
			entry.setSn(sn);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		if(starttime!=null&&endtime!=null) {

			entry.setStarttime(starttime);
			entry.setEndtime(endtime);

		}
		try {
			List<CMesDefectEntryT> findAll = qualityService.findAllDefectEntry(entry);
			HSSFWorkbook  book = new HSSFWorkbook();
			String headers[] = {"总成号","时间","产品名称","缺陷原因","录入员工"};
			ExcelUtil.entryExcel(findAll,book,headers);
			ExcelUtil.export(response, book, s.format(new Date()) + ".xls");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
	}

}
