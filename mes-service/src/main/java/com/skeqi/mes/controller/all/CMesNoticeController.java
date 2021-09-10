package com.skeqi.mes.controller.all;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.common.lcy.GetDate;
import com.skeqi.mes.pojo.CMesEmpT;
import com.skeqi.mes.pojo.CMesEmpTeamT;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesNoticeT;
import com.skeqi.mes.service.all.CMesLineTService;
import com.skeqi.mes.service.all.ProduceService;
import com.skeqi.mes.util.ToolUtils;

/***
 *
 * @author ENS 通知管理
 *
 */
@Controller
@RequestMapping("skq")
public class CMesNoticeController {

	@Autowired
	ProduceService produceService;
	@Autowired
	CMesLineTService lineService;

	/**
	 * 通知管理列表
	 */
	@RequestMapping("noticeList")
	public String noticeList(HttpSession session,HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page){

		PageHelper.startPage(page,8);
		CMesNoticeT n = new CMesNoticeT();
		CMesEmpTeamT team = new CMesEmpTeamT();
		CMesLineT line = new CMesLineT();
		try {
		List<CMesNoticeT> noticeList = produceService.findAllNotice(n);//通知管理
		PageInfo<CMesNoticeT> pageInfo = new PageInfo<>(noticeList,5);
		List<CMesEmpTeamT> shiftList = produceService.findAllTeam(team);
		List<CMesLineT> lineList = lineService.findAllLine(line);
		request.setAttribute("shiftList", shiftList);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("lineList", lineList);
		}catch (Exception e) {
			// TODO: handle exception
		}
		return "production_control/noticeManager";
	}


	//初始化通知人姓名
	@RequestMapping("inItNoticeName")
	@ResponseBody
	public JSONObject inItNoticeName(HttpServletRequest request){
		JSONObject json = new JSONObject();
		String  str= request.getParameter("getShiftTeam");
		System.err.println("str===="+str);
		if(str==null||str==""){
			return json;
		}

		Integer getShiftTeam = Integer.parseInt(str);
		CMesEmpT emp = new CMesEmpT();
		emp.setEmpTeamId(getShiftTeam);

		try {
			System.err.println("emo===="+emp);
			List<CMesEmpT> list =  produceService.findAllEmp(emp);
			List<String> noticeList = new ArrayList<>();
			for (CMesEmpT cMesEmpT : list) {
				noticeList.add(cMesEmpT.getEmpName());
			}
			json.put("noticeList",noticeList);
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
		return json ;
	}

	//初始化通知人姓名
	@RequestMapping("getShiftsName")
	@ResponseBody
	public JSONObject getShiftsName(){
		JSONObject jo = new JSONObject();
		CMesEmpTeamT emp = new CMesEmpTeamT();
		try {
		List<CMesEmpTeamT> getShiftsNameList = produceService.findAllTeam(emp);
		jo.put("getShiftsNameList",getShiftsNameList);
		}catch (Exception e) {
			// TODO: handle exception
		}
		return jo ;
	}


	@RequestMapping("addNotice")
	@ResponseBody
	public JSONObject addNotice(HttpServletRequest request){
		JSONObject json = new JSONObject();
		String head=request.getParameter("head");
		String noticeContent = request.getParameter("noticeContent");
		String  s=request.getParameter("s");
		CMesNoticeT t = new CMesNoticeT();
		t.setHead(head);
		t.setNoticeContent(noticeContent);
		t.setContacts(s);
		t.setDt(new Date());
		try {
			produceService.addNotice(t);
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


}
