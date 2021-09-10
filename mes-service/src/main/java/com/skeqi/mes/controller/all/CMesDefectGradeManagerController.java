package com.skeqi.mes.controller.all;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesDefectGradeManagerT;
import com.skeqi.mes.service.all.QualityService;

/***
 *
 * @author ENS 缺陷等级管理
 *
 */

@Controller
@RequestMapping("skq")
public class CMesDefectGradeManagerController {

	@Autowired
	QualityService qualityService;


	//缺陷等级管理的删除方法
		@RequestMapping("removeDefectGradeManager")
		@ResponseBody
		public JSONObject removeDefectGradeManager(HttpServletRequest request){
			JSONObject json = new JSONObject();
			Integer id=Integer.parseInt(request.getParameter("id"));//获取id
			//判断等级管理中是否有这个缺陷管理的使用，若有客户不能删除，若没有，则删除
			 try {
				qualityService.delDefectGrade(id);
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

		//缺陷等级管理的查找
		@RequestMapping("findDefectGradeManagerById")
		@ResponseBody
		public JSONObject findDefectGradeManagerById(HttpServletRequest request){
			Integer id=Integer.parseInt(request.getParameter("id"));//获取id
			JSONObject json = new JSONObject();
			try {
				CMesDefectGradeManagerT	defectGradeManage = qualityService.findDefectGradeByid(id);
				json.put("code", 0);
				json.put("defectGradeManage", defectGradeManage);
			}catch (ServicesException e) {
				json.put("code", e.getCode());
				json.put("msg", e.getMessage());
			} catch (Exception e) {
				json.put("code", -1);
				json.put("msg", e.getMessage());
			}
			return json;
		}
		//缺陷等级管理的添加
				@RequestMapping("addDefectGradeManager")
				@ResponseBody
				public JSONObject addDefectGradeManager(HttpServletRequest request){
					JSONObject json = new JSONObject();
				String defectGradeId=request.getParameter("defectGradeId");
				String defectGradeName =request.getParameter("defectGradeName");
				String defectGradeDis = request.getParameter("defectGradeDis");
				CMesDefectGradeManagerT	defectGradeManage  = new CMesDefectGradeManagerT();
				defectGradeManage.setDefectGradeDis(defectGradeDis);
				defectGradeManage.setDefectGradeId(defectGradeId);
				defectGradeManage.setDefectGradeName(defectGradeName);
				try {
					qualityService.addDefectGrade(defectGradeManage);
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
				//缺陷等级管理的修改
				@RequestMapping("updataDefectGradeManager")
				@ResponseBody
				public JSONObject updataDefectGradeManager(HttpServletRequest request){
					Integer id=Integer.parseInt(request.getParameter("id"));//获取id
					String defectGradeId=request.getParameter("defectGradeId");
					String defectGradeName =request.getParameter("defectGradeName");
					CMesDefectGradeManagerT	defectGradeManage  = new CMesDefectGradeManagerT();
					defectGradeManage.setDefectGradeId(defectGradeId);
					defectGradeManage.setDefectGradeName(defectGradeName);
					defectGradeManage.setId(id);
					JSONObject json = new JSONObject();
					try {
						qualityService.updateDefectGrade(defectGradeManage);
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



				//缺陷等级管理的列表
					@RequestMapping("queryDefectGradeManagerList")
					public String queryDefectGradeManagerList(HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page){
							PageHelper.startPage(page,13);
							JSONObject json = new JSONObject();
							CMesDefectGradeManagerT	defectGradeManage  = new CMesDefectGradeManagerT();
							try {
							List<CMesDefectGradeManagerT> defectGradeList = qualityService.findAllDefectGrade(defectGradeManage);
							PageInfo<CMesDefectGradeManagerT> pageInfo = new PageInfo<>(defectGradeList,8);
							request.setAttribute("pageInfo", pageInfo);
							json.put("code", 0);
							}catch (ServicesException e) {
								json.put("code", e.getCode());
								json.put("msg", e.getMessage());
							} catch (Exception e) {
								json.put("code", -1);
								json.put("msg", e.getMessage());
							}
							return "quality_control/defectGradeManager";
					}




}
