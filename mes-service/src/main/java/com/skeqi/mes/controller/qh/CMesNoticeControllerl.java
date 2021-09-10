package com.skeqi.mes.controller.qh;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesEmpT;
import com.skeqi.mes.pojo.CMesEmpTeamT;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesNoticeT;
import com.skeqi.mes.service.all.CMesLineTService;
import com.skeqi.mes.service.all.ProduceService;
import com.skeqi.mes.util.ToolUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "api", produces = MediaType.APPLICATION_JSON)
@Api(value = "通知管理", description = "通知管理", produces = MediaType.APPLICATION_JSON)
public class CMesNoticeControllerl {


	@Autowired
	ProduceService produceService;
	@Autowired
	CMesLineTService lineService;

	/**
	 * 通知管理列表
	 */
	@RequestMapping(value = "/noticeList", method = RequestMethod.POST)
	@ApiOperation(value = "通知管理列表", notes = "通知管理列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageNum", value = "页码", required = false, paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "pageSize", value = "每页记录数", required = false, paramType = "query", dataType = "int") })
	public JSONObject noticeList(@RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "10") Integer pageSize){
		JSONObject json = new JSONObject();
		PageHelper.startPage(pageNum,pageSize);
		CMesNoticeT n = new CMesNoticeT();
		CMesEmpTeamT team = new CMesEmpTeamT();
		CMesLineT line = new CMesLineT();
		try {
		List<CMesNoticeT> noticeList = produceService.findAllNotice(n);//通知管理
   PageInfo<CMesNoticeT> pageInfo = new  PageInfo<>(noticeList);
   List<CMesEmpTeamT> shiftList = produceService.findAllTeam(team);
		List<CMesLineT> lineList = lineService.findAllLine(line);
	 	json.put("shiftList", shiftList);
		json.put("NoticeList", pageInfo);
	json.put("lineList", lineList);
		}catch (Exception e) {
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", "未知错误");
		}
		return json;
	}

	//初始化通知人姓名
	@RequestMapping(value = "/inItNoticeName", method = RequestMethod.POST)
	@ApiOperation(value = "初始化通知人姓名", notes = "初始化通知人姓名")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "name", value = "员工类型名称", required = false, paramType = "query", dataType = "int"),
		 })
	public JSONObject inItNoticeName(HttpServletRequest request, String name){
		JSONObject json = new JSONObject();

		CMesEmpT cMesEmpT2 = new CMesEmpT();
		cMesEmpT2.setEmpName(name);
		try {
			List<CMesEmpT> list =  produceService.findAllEmp(cMesEmpT2);
			List<String> noticeList = new ArrayList<>();
			for (CMesEmpT cMesEmpT : list) {
				noticeList.add(cMesEmpT.getEmpName());
			}
			json.put("noticeList",noticeList);
			json.put("code", 0);
		}catch (ServicesException e) {
			json.put("code",1);
			json.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		} catch (Exception e) {
			json.put("code", 1);
			json.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return json ;
	}
	//初始化通知人姓名
	@RequestMapping(value = "/getTeamName", method = RequestMethod.POST)
	@ApiOperation(value = "班组列表", notes = "班组列表")
//	@ApiImplicitParams({
//		@ApiImplicitParam(name = "name", value = "员工类型名称", required = false, paramType = "query", dataType = "int"),
//		 })
	public JSONObject getShiftsName(){
		JSONObject json = new JSONObject();
		CMesEmpTeamT emp = new CMesEmpTeamT();
		try {
		List<CMesEmpTeamT> getShiftsNameList = produceService.findAllTeam(emp);
		json.put("getShiftsNameList",getShiftsNameList);
		}catch (Exception e) {
			// TODO: handle exception
			json.put("code", 1);
			json.put("msg", e.getMessage());
		}
		return json ;
	}


	@RequestMapping(value = "/addNotice", method = RequestMethod.POST)
	@ApiOperation(value = "添加", notes = "添加")
	public JSONObject addNotice(HttpServletRequest request, @ModelAttribute CMesNoticeT cMesNoticeT){
		JSONObject json = new JSONObject();

		try {
			produceService.addNotice(cMesNoticeT);
			json.put("code", 0);
			json.put("msg", "添加成功");
		}catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		} catch (Exception e) {
			json.put("code", 1);
			json.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return json;
	}
}
