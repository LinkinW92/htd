package com.skeqi.mes.controller.all;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesLabelManagerT;
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.service.all.CMesProductionTService;
import com.skeqi.mes.service.yin.DeviceService;
import com.skeqi.mes.util.ToolUtils;

import sun.misc.BASE64Encoder;

@Controller
@RequestMapping("product")
/***
 *
 * @author ENS  产品管理 1
 *
 */
public class CMesProductionController {

	@Autowired
	DeviceService deviceService;
	@Autowired
	CMesProductionTService productionService;

	static BASE64Encoder encoder = new BASE64Encoder();


	@ResponseBody
	@RequestMapping(value="addAll",method = RequestMethod.POST, consumes = "multipart/form-data")
	public JSONObject addAll(@RequestParam(required=false)MultipartFile file,HttpServletRequest request) throws Exception{
		JSONObject json = new JSONObject();
		String path = null;
		if(file!=null){
			byte[] bytes = file.getBytes();
			path = encoder.encodeBuffer(bytes).trim();
		}
		String productionName =	request.getParameter("productionName");
		String productionType = request.getParameter("productionType");
		String productionSeries = request.getParameter("productionSeries");
		String productionVr = request.getParameter("productionVr");//验证
		String productionDiscription = request.getParameter("productionDiscription");
		String productionTrademark = request.getParameter("productionTrademark");
		String productionSte = request.getParameter("productionSte");
		Integer labelTypeId = Integer.parseInt(request.getParameter("labelTypeId"));
//	    Integer productionSystemId = Integer.parseInt(request.getParameter("productionSystemId"));
//	     Integer productionGroupId = Integer.parseInt(request.getParameter("productionGroupId"));
		CMesProductionT production = new CMesProductionT();
		production.setProductionName(productionName);
		production.setProductionType(productionType);
		production.setProductionSeries(productionSeries);
		production.setProductionVr(productionVr);
		production.setProductionDiscription(productionDiscription);
		production.setProductionTrademark(productionTrademark);
		production.setProductionSte(productionSte);
//		production.setProductionSystemId(productionSystemId);
//		production.setProductionGroupId(productionGroupId);
		production.setProductionPrintId(labelTypeId);
		production.setPath(path);
		try {
			productionService.addProduction(production);
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

	@ResponseBody
	@RequestMapping(value="updateProduct",method = RequestMethod.POST, consumes = "multipart/form-data")
	public JSONObject updateProduct(@RequestParam(required=false)MultipartFile files,HttpServletRequest request) throws Exception{
		JSONObject json = new JSONObject();
		String path = null;
		CMesProductionT production = new CMesProductionT();
		if(files!=null){
			byte[] bytes = files.getBytes();
			path = encoder.encodeBuffer(bytes).trim();
			production.setPath(path);
		}
		String productionName =	request.getParameter("productionName");
		String productionType = request.getParameter("productionType");
		String productionSeries = request.getParameter("productionSeries");
		String productionVr = request.getParameter("productionVr");//验证
		String productionDiscription = request.getParameter("productionDiscription");
		String productionSte = request.getParameter("productionSte5");
		String productionId = request.getParameter("id");
		Integer labelTypeId = Integer.parseInt(request.getParameter("labelTypeId"));
//		Integer productionGroupId = Integer.parseInt(request.getParameter("productionGroupId"));
//		Integer productionSystemId = Integer.parseInt(request.getParameter("productionSystemId"));


		production.setId(Integer.parseInt(productionId));
		production.setProductionName(productionName);
		production.setProductionType(productionType);
		production.setProductionSeries(productionSeries);
		production.setProductionVr(productionVr);
		production.setProductionDiscription(productionDiscription);
		production.setProductionSte(productionSte);
//		production.setProductionSystemId(productionSystemId);
//		production.setProductionGroupId(productionGroupId);
		production.setProductionPrintId(labelTypeId);
		production.setPath(path);
		try {
			productionService.updateProduction(production);
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



	// 产品删除
		@RequestMapping("delectById")
		@ResponseBody
		public JSONObject delectById(Model model, Integer id) {

			JSONObject json =new JSONObject();

			try {
				productionService.delProduction(id);
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


		@RequestMapping("findProductById")
		@ResponseBody
		public  JSONObject findProductById(HttpSession session,HttpServletRequest request,Model model) {
			String id = request.getParameter("id");
			JSONObject json = new JSONObject();
			CMesProductionT cMesProductionT;
			try {
				cMesProductionT = productionService.findProductionByid(Integer.parseInt(id));
				json.put("cMesProductionT", cMesProductionT);
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

		@RequestMapping("findAll")
		public String finAll(HttpServletRequest request, Model model,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page) {

			JSONObject json = new JSONObject();
			Map<String, Object> map = new HashMap<>();
			List<CMesLabelManagerT> labelManagerList = deviceService.listLabelManager(map);
			try {
				PageHelper.startPage(page,5);
				List<CMesProductionT> list = productionService.findAllProduction(null);
				PageInfo<CMesProductionT> pageInfo = new PageInfo<>(list,5);
				model.addAttribute("productList", list);
				model.addAttribute("pageInfo", pageInfo);
				json.put("code", 0);
			}catch (ServicesException e) {
				json.put("code", e.getCode());
				json.put("msg", e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				ToolUtils.errorLog(this, e, request);
				json.put("code", -1);
				json.put("msg", e.getMessage());
			}
			model.addAttribute("labelManagerList", labelManagerList);
			return "recipe_control/proManager";
		}




		@RequestMapping("productionList")
		public String planList(HttpServletRequest request,
				@RequestParam(required = false, defaultValue = "1", value = "page") Integer page, Model model) {
			JSONObject json = new JSONObject();
			try {
			PageHelper.startPage(page,5);
			List<CMesProductionT> rplanList = productionService.findAllProduction(null);
			PageInfo pageInfo = new PageInfo<>(rplanList, 5);
			request.setAttribute("pageInfo", pageInfo);

				List<CMesProductionT> list = productionService.findAllProduction(null);
				model.addAttribute("productList", list);
				json.put("code", 0);
			}catch (ServicesException e) {
				json.put("code", e.getCode());
				json.put("msg", e.getMessage());
			} catch (Exception e) {
				json.put("code", -1);
				json.put("msg", e.getMessage());
			}
			return "recipe_control/proManager";
		}


}
