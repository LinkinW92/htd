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
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesManufactureParametersT;
import com.skeqi.mes.pojo.CMesMaterialListT;
import com.skeqi.mes.pojo.CMesProductionProcessT;
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.service.all.CMesBomService;
import com.skeqi.mes.service.all.CMesLineTService;
import com.skeqi.mes.service.all.CMesMaterialService;
import com.skeqi.mes.service.all.CMesProductionTService;

/***
 *
 * @author ENS 工艺配置
 *
 */
@Controller
@RequestMapping("skq")
public class CMesProcessConfigurationController {


	@Autowired
	CMesMaterialService materialService;
	@Autowired
	CMesBomService bomService;
	@Autowired
	CMesProductionTService ps;
	@Autowired
	CMesLineTService lineService;
	/**
	 * 工艺配置信息
	 */
	@RequestMapping("processConfiguration")
	public String processConfiguration(HttpServletRequest request,
			@RequestParam(required = false, defaultValue = "1", value = "page") Integer page) {
		JSONObject json = new JSONObject();
		PageHelper.startPage(page, 5);
		CMesProductionProcessT pp = new CMesProductionProcessT();
		try {
		List<CMesProductionProcessT> productionProcess = materialService.findAllProcess(pp);

		PageInfo<CMesProductionProcessT> pageInfo = new PageInfo<>(productionProcess, 5);
		CMesMaterialListT ml = new CMesMaterialListT();
		List<CMesMaterialListT> materialLists =bomService.findAllMaterialList(ml);
		CMesProductionT pro = new CMesProductionT();
		List<CMesProductionT> productionList = ps.findAllProduction(pro);
		CMesManufactureParametersT mfg = new CMesManufactureParametersT();
		List<CMesManufactureParametersT> manuParameterLists =bomService.findAllMFG(mfg);

			//	materialService.manuParameterLists(map);// 制造清单
		CMesLineT line = new CMesLineT();
		List<CMesLineT> lineList =lineService.findAllLine(line);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("materialLists", materialLists);
		request.setAttribute("productionList", productionList);
		request.setAttribute("lineList", lineList);
		request.setAttribute("manuParameterLists", manuParameterLists);
		json.put("code", 0);
		}catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}
		return "materiel_control/processConfiguration";
	}

	/**
	 * 添加工艺配置
	 */
	@RequestMapping("addProcessConfig")
	@ResponseBody
	public  JSONObject addProcessConfig(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String lineId = request.getParameter("lineId");
		String materialListId = request.getParameter("materialListId");
		String mfParameterId = request.getParameter("mfParameterId");
		String productonId = request.getParameter("productonId");
		CMesProductionProcessT pp = new CMesProductionProcessT();
		pp.setLineId(Integer.parseInt(lineId));
		pp.setMaterialListId(Integer.parseInt(materialListId));
		pp.setMfParameterId(Integer.parseInt(mfParameterId));
		pp.setProductonId(Integer.parseInt(productonId));
		try {
			materialService.addProcess(pp);
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
	 * 删除工艺配置
	 */
	@RequestMapping("delProcessConfig")
	@ResponseBody
	public  JSONObject delProcessConfig(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");
		try {
			materialService.delProcess(Integer.parseInt(id));
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
	 * 通过ID查询工艺配置信息
	 */
	@RequestMapping("toEditProcessConfig")
	@ResponseBody
	public JSONObject toEditProcessConfig(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");
		try {
			CMesProductionProcessT	productionProcess = materialService.findProcessByid(Integer.parseInt(id));
			json.put("code", 0);
			json.put("productionProcess", productionProcess);
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
	 * 修改工艺配置
	 */
	@RequestMapping("editProcessConfig")
	@ResponseBody
	public  JSONObject editProcessConfig(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");
		String lineId = request.getParameter("lineId");
		String materialListId = request.getParameter("materialListId");
		String mfParameterId = request.getParameter("mfParameterId");
		String productonId = request.getParameter("productonId");
		CMesProductionProcessT pp = new CMesProductionProcessT();
		pp.setLineId(Integer.parseInt(lineId));
		pp.setMaterialListId(Integer.parseInt(materialListId));
		pp.setMfParameterId(Integer.parseInt(mfParameterId));
		pp.setProductonId(Integer.parseInt(productonId));
		pp.setId(Integer.parseInt(id));
		try {
			materialService.updateProcess(pp);
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
