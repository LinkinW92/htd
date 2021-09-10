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
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.pojo.CMesReturnRepairT;
import com.skeqi.mes.service.all.CMesLineTService;
import com.skeqi.mes.service.all.CMesProductionTService;
import com.skeqi.mes.service.all.QualityService;
import com.skeqi.mes.util.ExcelUtil;
import com.skeqi.mes.util.ToolUtils;


/***
 *
 * @author ENS  返厂维修
 *
 */
@Controller
@RequestMapping("/repair")
public class CMesRepairController {

	@Autowired
	QualityService qualityService;
	@Autowired
	CMesLineTService lineService;
	@Autowired
	CMesProductionTService proService;



	@RequestMapping("/repairlist")
	public String repairlist(HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page){
		PageHelper.startPage(page,15);
		String sn = request.getParameter("sn");
		String lid = request.getParameter("lid");
		String pid = request.getParameter("pid");
		String status = request.getParameter("status");
		CMesReturnRepairT rr = new CMesReturnRepairT();
		if(sn!=null) {
			rr.setSn(sn);
		}
		if(pid!=null) {
			rr.setProductionId(Integer.parseInt(pid));
		}
		if(lid!=null) {
			rr.setLineId(Integer.parseInt(lid));
		}
		if(status!=null) {
			rr.setStatus(Integer.parseInt(status));
		}

			request.setAttribute("sn",sn);
			request.setAttribute("status",status);
			request.setAttribute("pid",pid);
			request.setAttribute("lid",lid);
		try {
			List<CMesReturnRepairT> findAll = qualityService.findAllReturnRepair(rr);
			PageInfo<CMesReturnRepairT> pageInfo = new PageInfo<CMesReturnRepairT>(findAll,5);
			CMesLineT line = new CMesLineT();
			CMesProductionT pro = new CMesProductionT();
			List<CMesLineT> findLine = lineService.findAllLine(line);
			List<CMesProductionT> findPro = proService.findAllProduction(pro);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("findLine", findLine);
			request.setAttribute("findPro", findPro);
		}catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return "quality_control/returnrepair";
	}

	@RequestMapping("/insertrerepair")
	@ResponseBody
	public JSONObject insertrerepair(HttpServletRequest request){
		CMesReturnRepairT c = new CMesReturnRepairT();
		JSONObject json = new JSONObject();
		String sns = request.getParameter("sns");
		String reason = request.getParameter("reason");
		String lines = request.getParameter("lines");
		String pro = request.getParameter("pro");
		c.setSn(sns);
		c.setReason(reason);
		c.setLineId(Integer.parseInt(lines));
		c.setProductionId(Integer.parseInt(pro));
		try {
			qualityService.addReturnRepair(c);
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

	@RequestMapping("/updatererepair")
	@ResponseBody
	public JSONObject updatererepair(HttpServletRequest request){
		String id = request.getParameter("id");
		JSONObject json = new JSONObject();
			try {
				qualityService.updateReturnRepair(Integer.parseInt(id));
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

	@RequestMapping("/exportreapair")
	@ResponseBody
	public void exportreapair(HttpServletRequest request,HttpServletResponse response){
		String sn = request.getParameter("sn");
		String lid = request.getParameter("lid");
		String pid = request.getParameter("pid");
		String status = request.getParameter("status");
		CMesReturnRepairT rr = new CMesReturnRepairT();
		if(sn!=null) {
			rr.setSn(sn);
		}
		if(pid!=null) {
			rr.setProductionId(Integer.parseInt(pid));
		}
		if(lid!=null) {
			rr.setLineId(Integer.parseInt(lid));
		}
		if(status!=null) {
			rr.setStatus(Integer.parseInt(status));
		}
		try {
			SimpleDateFormat s = new SimpleDateFormat("yyyyMMddHHmmss");
			List<CMesReturnRepairT> findAll = qualityService.findAllReturnRepair(rr);
			HSSFWorkbook  book = new HSSFWorkbook();
			String headers[] = {"总成号","返厂时间","产品名称","产线名称","维修完成时间","原因"};
			ExcelUtil.reapairExcel(findAll, book, headers);
			ExcelUtil.export(response, book, s.format(new Date()) + ".xls");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
	}

}
