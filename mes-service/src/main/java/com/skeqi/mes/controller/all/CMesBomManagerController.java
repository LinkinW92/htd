package com.skeqi.mes.controller.all;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.skeqi.mes.pojo.CMesBomT;
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.service.all.CMesBomService;
import com.skeqi.mes.service.all.CMesProductionTService;

/***
 *
 * @author ENS Bom清单  1
 *
 */
@Controller
@RequestMapping("skq")
public class CMesBomManagerController {

	@Autowired
	CMesBomService bomService;

	@Autowired
	CMesProductionTService productionService;



	@RequestMapping("bomManager")
	public String bomManager(HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page) {
		JSONObject json = new JSONObject();
		CMesProductionT production = new CMesProductionT();
		try {
			List<CMesProductionT> productionList = productionService.findAllProduction(production);
			PageHelper.startPage(page,8);
			CMesBomT bom = new CMesBomT();
			List<CMesBomT> bomList = bomService.findAllBom(bom);
			PageInfo<CMesBomT> pageInfo = new PageInfo<>(bomList,5);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("productionList", productionList);
			json.put("code", 0);
		}catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}
		return "bom_control/bomManager";
	}




	@RequestMapping("editBom")
	@ResponseBody
	public  JSONObject editBom(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");
		String bomName = request.getParameter("bomName").trim();
		String productionId = request.getParameter("productionId");
		String dis = request.getParameter("dis");
		CMesBomT bom = new CMesBomT();
		bom.setId(Integer.parseInt(id));
		bom.setDis(dis);
		bom.setBomName(bomName);
		bom.setProductionId(Integer.parseInt(productionId));
		try {
			bomService.updateBom(bom);
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


	@RequestMapping("addBom")
	@ResponseBody
	public  JSONObject addBom(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String bomName = request.getParameter("bomName").trim();
		String productionId = request.getParameter("productionId");
		String dis = request.getParameter("dis");
		CMesBomT bom = new CMesBomT();
		bom.setDis(dis);
		bom.setBomName(bomName);
		bom.setProductionId(Integer.parseInt(productionId));
		try {
			bomService.addBom(bom);
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
	/**
	 * 删除bom明细
	 */
	@RequestMapping("delBom")
	@ResponseBody
	public  JSONObject delBom(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");

			try {
				bomService.delBom(Integer.parseInt(id));
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

	@RequestMapping("toEditBom")
	@ResponseBody
	public  JSONObject toEditBom(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");

		CMesBomT bom;
		try {
			bom = bomService.findBomByid(Integer.parseInt(id));
			json.put("bomList", bom);
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


}
