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
import com.skeqi.mes.pojo.CMesDefectGradeManagerT;
import com.skeqi.mes.pojo.CMesDefectManager;
import com.skeqi.mes.service.all.QualityService;
import com.skeqi.mes.util.ToolUtils;

/***
 *
 * @author ENS 缺陷管理
 *
 */
@Controller
@RequestMapping("skq")
public class CMesDefectManagerController {

	@Autowired
	QualityService qualityService;



	/**
	 *
	 * 缺陷管理
	 */
	//初始化下拉选
	@RequestMapping("getInitDefectGradeManagerList")
	@ResponseBody
	public JSONObject getInitDefectGradeManagerList(HttpServletRequest request){
		JSONObject jo = new JSONObject();
		CMesDefectGradeManagerT d = new CMesDefectGradeManagerT();
		try {
			List<CMesDefectGradeManagerT> list = qualityService.findAllDefectGrade(d);
			jo.put("getInit",list);
		} catch (ServicesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return jo;
	}


	@RequestMapping("removeAllDefectManager")
	@ResponseBody
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false, rollbackFor = Exception.class)
	public JSONObject  removeAllDefectManager(HttpServletRequest request){
		JSONObject json = new JSONObject();
		Integer id=Integer.parseInt(request.getParameter("id"));
		try {
			qualityService.delDefectManager(id);
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
		json.put("flag",true);
		return json;
	}



	//缺陷管理的删除方法
	@RequestMapping("removeDefectManager")
	@ResponseBody
	public JSONObject removeDefectManager(HttpServletRequest request){
		JSONObject json = new JSONObject();
		String id=request.getParameter("id");//获取id

			try {
				qualityService.delDefectManager(Integer.parseInt(id));
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
	//缺陷管理的查找
		@RequestMapping("findDefectManagerById")
		@ResponseBody
		public JSONObject findDefectManagerById(HttpServletRequest request){
			String id=request.getParameter("id");//获取id
			JSONObject json = new JSONObject();
			try {
			CMesDefectManager defectManager=qualityService.findDefectManagerByid(Integer.parseInt(id));
				json.put("defectManager", defectManager);
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

	//缺陷管理的添加
	@RequestMapping("addDefectManager")
	@ResponseBody
	public JSONObject addDefectManager(HttpServletRequest request){
		String defectId=request.getParameter("defectId");
		String defectName =request.getParameter("defectName");
		String defectDis = request.getParameter("defectDis");
		Integer defectGrade=Integer.parseInt(request.getParameter("defectGrade"));

		CMesDefectManager defectManager = new CMesDefectManager();
		defectManager.setDefectId(defectId);
		defectManager.setDefectName(defectName);
		defectManager.setDefectDis(defectDis);
		CMesDefectGradeManagerT g = new CMesDefectGradeManagerT();
		g.setId(defectGrade);
		defectManager.setDefectGrade(g);
		JSONObject json = new JSONObject();
		try {
			qualityService.addDefectManager(defectManager);
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

	//缺陷管理的修改
	@RequestMapping("updateDefectManager")
	@ResponseBody
	public JSONObject updataDefectManager(HttpServletRequest request){
		JSONObject json = new JSONObject();
		int id=Integer.parseInt(request.getParameter("id"));
		String defectId=request.getParameter("defectId");
		String defectName=request.getParameter("defectName");
		Integer defectGrade=Integer.parseInt(request.getParameter("defectGrade"));
		CMesDefectGradeManagerT g = new CMesDefectGradeManagerT();
		g.setId(defectGrade);
		CMesDefectManager defectManager = new CMesDefectManager();
		defectManager.setDefectGrade(g);
		defectManager.setDefectId(defectId);
		defectManager.setDefectName(defectName);
		defectManager.setId(id);
		try {
			qualityService.updateDefectManager(defectManager);
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

	//缺陷管理的列表
	@RequestMapping("queryDefectManagerList")
	public String queryDefectManagerList(HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page){
			PageHelper.startPage(page,13);

			try {
				CMesDefectManager t = new CMesDefectManager();
			List<CMesDefectManager> defectList = qualityService.findAllDefectManager(t);
			PageInfo<CMesDefectManager> pageInfo = new PageInfo<>(defectList,8);
			request.setAttribute("pageInfo", pageInfo);
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				ToolUtils.errorLog(this, e, request);
			}
			return "quality_control/defectManager";
	}


}
