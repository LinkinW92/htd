package com.skeqi.mes.controller.all;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesDefectResionT;
import com.skeqi.mes.service.all.QualityService;
import com.skeqi.mes.util.ToolUtils;

/***
 *
 * @author ENS 原因管理
 *
 */
@Controller
@RequestMapping("Reason")
public class CMesReasonController {


	@Autowired
	QualityService qualityService;



	//原因列表
		@RequestMapping("findAll")
		public String findAll(HttpServletRequest request, Model model,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page){
			PageHelper.startPage(page,6);
			try {
				CMesDefectResionT dr = new CMesDefectResionT();
			List<CMesDefectResionT> list=qualityService.findAllReason(dr);
			PageInfo<CMesDefectResionT> pageInfo = new PageInfo<>(list,3);
			model.addAttribute("ReasonList", list);
			model.addAttribute("pageInfo", pageInfo);
			}catch (Exception e) {
				e.printStackTrace();
				ToolUtils.errorLog(this, e, request);
			}
			return "quality_control/reasonManager";
		}

		//原因新增
		@RequestMapping("addReason")
		@ResponseBody
		public  JSONObject addReason(Model model,HttpServletRequest request){
			JSONObject json = new JSONObject();
			String resionNumber=request.getParameter("resionNumber").trim();
			String dis=request.getParameter("dis").trim();
			CMesDefectResionT dr = new CMesDefectResionT();
			dr.setDis(dis);
			dr.setResionNumber(resionNumber);

			 	try {
			 		qualityService.addReason(dr);
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

		//原因删除
		@RequestMapping("removeReason")
		@ResponseBody
		public  JSONObject removeReason(Model model,HttpServletRequest request){
			JSONObject json = new JSONObject();
			int id=Integer.parseInt(request.getParameter("id"));
			try {
		 		qualityService.delReason(id);
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

		//根据id查询
		@RequestMapping("findById")
		@ResponseBody
		public  JSONObject findById(HttpServletRequest request){

			JSONObject json = new JSONObject();
			Integer id=Integer.parseInt(request.getParameter("id"));
			try {
				CMesDefectResionT resion = qualityService.findReasonByid(id);
		 		json.put("code", 0);
		 		json.put("resion", resion);
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

		//原因修改
		@RequestMapping("updateReason")
		@ResponseBody
		public  JSONObject updateReason(HttpServletRequest request){


			JSONObject json = new JSONObject();
			String dis=request.getParameter("dis").trim();
			Integer id=Integer.parseInt(request.getParameter("id"));
			String resionNumber=request.getParameter("resionNumber").trim();
			CMesDefectResionT dr = new CMesDefectResionT();
			dr.setId(id);
			dr.setDis(dis);
			dr.setResionNumber(resionNumber);
			try {
				qualityService.updateReason(dr);
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
