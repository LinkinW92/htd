package com.skeqi.mes.controller.all;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.common.lcy.GetDate;
import com.skeqi.mes.pojo.CMesDutyManagerT;
import com.skeqi.mes.pojo.CMesDutyTypeManagerT;
import com.skeqi.mes.service.all.QualityService;
import com.skeqi.mes.util.ToolUtils;

/***
 *
 * @author ENS 责任管理
 *
 */
@Controller
@RequestMapping("skq")
public class CMesDutyManagerController {

	@Autowired
	QualityService qualityService;


	/**
	 *
	 * 责任管理
	 */
	//初始化下拉选
		@RequestMapping("getInitDutyTypeManagerList")
		@ResponseBody
		public JSONObject getInitDutyTypeManagerList(HttpServletRequest request){
			JSONObject jo = new JSONObject();
			CMesDutyTypeManagerT dtm = new CMesDutyTypeManagerT();
			List<CMesDutyTypeManagerT> list;
			try {
				list = qualityService.findAllDutyType(dtm);
				jo.put("getInit",list);
			} catch (ServicesException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ToolUtils.errorLog(this, e, request);
			}
			return jo;

		}

	//缺陷管理的列表
		@RequestMapping("queryDutyManagerList")
		public String queryDutyManagerList(HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page){
				PageHelper.startPage(page,13);
				try {
					CMesDutyManagerT dm = new CMesDutyManagerT();
					List<CMesDutyManagerT> defectList = qualityService.findAllDutyManager(dm);
					PageInfo<CMesDutyManagerT> pageInfo = new PageInfo<>(defectList,8);
					request.setAttribute("pageInfo", pageInfo);
				} catch (ServicesException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					ToolUtils.errorLog(this, e, request);
				}

				return "quality_control/dutyManager";
		}


	//责任管理的删除方法
	@RequestMapping("removeDutyManager")
	@ResponseBody
	public JSONObject removeDutyManager(HttpServletRequest request){
		JSONObject jo = new JSONObject();
		Integer id=Integer.parseInt(request.getParameter("id"));//获取id
		try {
			qualityService.delDutyManager(id);
			jo.put("code", 0);
		}catch (ServicesException e) {
			jo.put("code", e.getCode());
			jo.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		} catch (Exception e) {
			jo.put("code", -1);
			jo.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return jo;
	}

	@RequestMapping("removeAllDutyManager")
	@ResponseBody
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false, rollbackFor = Exception.class)
	public JSONObject  removeAllDutyManager(HttpServletRequest request){
		JSONObject jo = new JSONObject();
		Integer id=Integer.parseInt(request.getParameter("id"));
		try {
			qualityService.delDutyManager(id);
			jo.put("code", 0);
		}catch (ServicesException e) {
			jo.put("code", e.getCode());
			jo.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		} catch (Exception e) {
			jo.put("code", -1);
			jo.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return jo;
	}

	//责任管理的查找
	@RequestMapping("findDutyManagerById")
	@ResponseBody
	public JSONObject findDutyManagerById(HttpServletRequest request){
		Integer id=Integer.parseInt(request.getParameter("id"));//获取id
		JSONObject jo = new JSONObject();
		try {
			CMesDutyManagerT dutyManager=qualityService.findDutyManagerByid(id);
			jo.put("dutyManager", dutyManager);
			jo.put("code", 0);
		}catch (ServicesException e) {
			jo.put("code", e.getCode());
			jo.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		} catch (Exception e) {
			jo.put("code", -1);
			jo.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return jo;
	}
	//责任管理的添加
	@RequestMapping("addDutyManager")
	@ResponseBody
	public JSONObject addDutyManager(HttpServletRequest request){

		String dutyId=request.getParameter("dutyId");
		String dutyName =request.getParameter("dutyName");
		String dutyDis = request.getParameter("dutyDis");
		Integer dutyType=Integer.parseInt(request.getParameter("dutyType"));
		JSONObject jo = new JSONObject();
		CMesDutyManagerT dm = new CMesDutyManagerT();
		dm.setDutyId(dutyId);
		dm.setDutyName(dutyName);
		CMesDutyTypeManagerT dtm = new CMesDutyTypeManagerT();
		dtm.setId(dutyType);
		dm.setDutyType(dtm);
		dm.setDutyDis(dutyDis);
		try {
			qualityService.addDutyManager(dm);
			jo.put("code", 0);
		}catch (ServicesException e) {
			jo.put("code", e.getCode());
			jo.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		} catch (Exception e) {
			jo.put("code", -1);
			jo.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return jo;


	}
	//责任管理的修改
	@RequestMapping("updateDutyManager")
	@ResponseBody
	public JSONObject updataDutyManager(HttpServletRequest request){
		Integer id = Integer.parseInt(request.getParameter("id"));
		String dutyId=request.getParameter("dutyId");
		String dutyName =request.getParameter("dutyName");
		Integer dutyType=Integer.parseInt(request.getParameter("dutyType"));
		JSONObject jo = new JSONObject();
		CMesDutyManagerT dm = new CMesDutyManagerT();
		dm.setDutyId(dutyId);
		dm.setDutyName(dutyName);
		CMesDutyTypeManagerT dtm = new CMesDutyTypeManagerT();
		dtm.setId(dutyType);
		dm.setDutyType(dtm);
		dm.setId(id);

		try {
			qualityService.updateDutyManager(dm);
			jo.put("code", 0);
		}catch (ServicesException e) {
			jo.put("code", e.getCode());
			jo.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		} catch (Exception e) {
			jo.put("code", -1);
			jo.put("msg", e.getMessage());
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}

		return jo;
	}

}
