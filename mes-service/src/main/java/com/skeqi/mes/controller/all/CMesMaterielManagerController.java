package com.skeqi.mes.controller.all;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesJlMaterialT;
import com.skeqi.mes.pojo.CMesMaterialTypeT;
import com.skeqi.mes.pojo.CMesSystem;
import com.skeqi.mes.pojo.wms.CWmsApprovalT;
import com.skeqi.mes.service.all.CMesMaterialService;
import com.skeqi.mes.service.all.CMesSystemService;
import com.skeqi.mes.util.ToolUtils;

/***
 *
 * @author ENS 物料管理 1
 *
 */
@Controller
@RequestMapping("/skq")
public class CMesMaterielManagerController {

	@Autowired
	CMesMaterialService materialService;

	@Autowired
	CMesSystemService sService;

	/**
	 * 查询所有物料id+name
	 *
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "material/findIdNameAll", method = RequestMethod.POST)
	public void findIdNameAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
//
//		// 接收参数
//		String str = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
//		response.setContentType("application/json");
//		// 将接受到的字符串转换为json数组
//		JSONObject json = JSONObject.parseObject(str);



		try {
			List<CMesJlMaterialT> list = materialService.findIdNameAll();

			data.put("msg", "查询成功！");
			data.put("code", true);
			result.put("result", data);
			result.put("data", list);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			data.put("msg", e.getMessage());
			data.put("code", false);
			result.put("result", data);
		} finally {
			response.getWriter().append(result.toJSONString());
		}
	}

	@RequestMapping("/materielManager")
	public String materielManager(HttpServletRequest request,
			@RequestParam(required = false, defaultValue = "1", value = "page") Integer page) throws Exception {

		boolean XT355_356_357 = false;
		/**
		 * 查询系统配置
		 */
		List<CMesSystem> systemList = sService.findByAll(null);
		if (systemList == null || systemList.size() == 0) {
			throw new Exception("查询系统配置出错了");
		}

		for (CMesSystem cMesSystem : systemList) {
			// 比对项目号
			if (cMesSystem.getName().equals("通用.项目")) {
				if (cMesSystem.getParameter().equals("XT357")) {
					XT355_356_357 = true;
				}
			}
		}
		// 结束

		if(XT355_356_357){
			request = materielManagerXT355_356_357(request, page);
		}else{
			request = materielManager1(request, page);
		}

		return "materiel_control/materielManager";
	}

	public HttpServletRequest materielManager1(HttpServletRequest request, Integer page) throws ServicesException {
		PageHelper.startPage(page, 15);
		Map<String, Object> map = new HashMap<>();
		String materialName = request.getParameter("materialName"); // 名称
		String bomId = request.getParameter("bomId"); // 物料编码
		String materialType = request.getParameter("materialType"); // 物料类型
		List<CMesJlMaterialT> findAll = new ArrayList<CMesJlMaterialT>();
		CMesJlMaterialT jlMaterial = new CMesJlMaterialT();
		jlMaterial.setBomId(bomId);
		jlMaterial.setMaterialName(materialName);
		jlMaterial.setMaterialType(materialType);
		try {
			findAll = materialService.findAllMaterial(jlMaterial);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		/* List<CMesJlMaterialT> findAll = service.findAll(map); */
		PageInfo<CMesJlMaterialT> pageinfo = new PageInfo<CMesJlMaterialT>(findAll, 5);
		CMesMaterialTypeT material = new CMesMaterialTypeT();
		List<CMesMaterialTypeT> findAllMaterialType = materialService.findAllMaterialType(material);
		request.setAttribute("pageInfo", pageinfo);
		request.setAttribute("materialName", materialName);
		request.setAttribute("bomId", bomId);
		request.setAttribute("materialType", materialType);
		request.setAttribute("mtype", findAllMaterialType);
		;
		return request;
	}

	public HttpServletRequest materielManagerXT355_356_357(HttpServletRequest request, Integer page)
			throws ServicesException {
		PageHelper.startPage(page, 15);
		Map<String, Object> map = new HashMap<>();
		String materialName = request.getParameter("materialName"); // 名称
		String bomId = request.getParameter("bomId"); // 物料编码
		String materialType = request.getParameter("materialType"); // 物料类型
		List<CMesJlMaterialT> findAll = new ArrayList<CMesJlMaterialT>();
		CMesJlMaterialT jlMaterial = new CMesJlMaterialT();
		jlMaterial.setBomId(bomId);
		jlMaterial.setMaterialName(materialName);
		jlMaterial.setMaterialType(materialType);
		try {
			findAll = materialService.findAllMaterialXT355_356_357(jlMaterial);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		/* List<CMesJlMaterialT> findAll = service.findAll(map); */
		PageInfo<CMesJlMaterialT> pageinfo = new PageInfo<CMesJlMaterialT>(findAll, 5);
		CMesMaterialTypeT material = new CMesMaterialTypeT();
		List<CMesMaterialTypeT> findAllMaterialType = materialService.findAllMaterialType(material);
		request.setAttribute("pageInfo", pageinfo);
		request.setAttribute("materialName", materialName);
		request.setAttribute("bomId", bomId);
		request.setAttribute("materialType", materialType);
		request.setAttribute("mtype", findAllMaterialType);
		;
		return request;
	}

	@RequestMapping("/addJlmaterial")
	@ResponseBody
	public JSONObject addJlmaterial(HttpServletRequest request) throws Exception {

		boolean XT355_356_357 = false;

		/**
		 * 查询系统配置
		 */
		List<CMesSystem> systemList = sService.findByAll(null);
		if (systemList == null || systemList.size() == 0) {
			throw new Exception("查询系统配置出错了");
		}

		for (CMesSystem cMesSystem : systemList) {
			// 比对项目号
			if (cMesSystem.getName().equals("通用.项目")) {
				if (cMesSystem.getParameter().equals("XT357")) {
					XT355_356_357 = true;
				}
			}
		}
		// 结束

		JSONObject json = new JSONObject();
		CMesJlMaterialT c = new CMesJlMaterialT();

		if (XT355_356_357) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String bomId = request.getParameter("bomId");
			c.setBomId(bomId);
			String materialName = request.getParameter("materialName");
			c.setMaterialName(materialName);
			String description = request.getParameter("description");
			c.setDescription(description);
			String specifications = request.getParameter("specifications");

			String materialLength = request.getParameter("materialLength");
			c.setMaterialLength(materialLength);
			String materialWidth = request.getParameter("materialWidth");
			c.setMaterialWidth(materialWidth);
			String materialHight = request.getParameter("materialHight");
			c.setMaterialHight(materialHight);
			String materialVolume = request.getParameter("materialVolume");
			c.setMaterialVolume(materialVolume);
			String materialWeight = request.getParameter("materialWeight");
			c.setMaterialWeight(materialWeight);
			String materialLt = request.getParameter("materialLt");
			c.setMaterialLt(materialLt);
			String materialIncomType = request.getParameter("materialIncomType");
			c.setMaterialIncomType(materialIncomType);
			String materialLowLimitmaterial = request.getParameter("materialLowLimitmaterial");
			c.setMaterialLowLimitmaterial(materialLowLimitmaterial);
			String materialBatch = request.getParameter("materialBatch");
			c.setMaterialBatch(materialBatch);
			String daysOfFailure = request.getParameter("daysOfFailure");
			c.setDaysOfFailure(daysOfFailure);
		} else {

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String bomId = request.getParameter("bomId");
			c.setBomId(bomId);
			String materialName = request.getParameter("materialName");
			c.setMaterialName(materialName);
			String description = request.getParameter("description");
			c.setDescription(description);
			String specifications = request.getParameter("specifications");
			c.setSpecifications(specifications);
			String materialGroup = request.getParameter("materialGroup");
			c.setMaterialGroup(materialGroup);
			String materialType = request.getParameter("materialType");
			c.setMaterialType(materialType);
			String stockUnit = request.getParameter("stockUnit");
			c.setStockUnit(stockUnit);
			String inventoryModelGroup = request.getParameter("inventoryModelGroup");
			c.setInventoryModelGroup(inventoryModelGroup);
			String inventoryDimensionGroup = request.getParameter("inventoryDimensionGroup");
			c.setInventoryDimensionGroup(inventoryDimensionGroup);
			String release = request.getParameter("release");
			c.setRelease(release);
			String inspection = request.getParameter("inspection");
			c.setInspection(inspection);
			String fictitious = request.getParameter("fictitious");
			c.setFictitious(fictitious);
			String salesUnit = request.getParameter("salesUnit");
			c.setSalesUnit(salesUnit);
			String secrecy = request.getParameter("secrecy");
			c.setSecrecy(secrecy);
			String purchasingUnit = request.getParameter("purchasingUnit");
			c.setPurchasingUnit(purchasingUnit);
			String productionTeam = request.getParameter("productionTeam");
			c.setProductionTeam(productionTeam);
			String mininumberOfPackages = request.getParameter("mininumberOfPackages");
			c.setMininumberOfPackages(mininumberOfPackages);
			String termOfValidity = request.getParameter("termOfValidity");
			c.setTermOfValidity(termOfValidity + ":00");
			String typenum = request.getParameter("typenum");
			c.setTypenum(typenum);
			String voltage = request.getParameter("voltage");
			c.setVoltage(voltage);
			String partCounts = request.getParameter("partCounts");
			c.setPartCounts(partCounts);
			String cellCapacity = request.getParameter("cellCapacity");
			c.setCellCapacity(cellCapacity);
			String scan = request.getParameter("scan");
			c.setScan(scan);
			String cellSpecification = request.getParameter("cellSpecification");
			c.setCellSpecification(cellSpecification);

			String materialLength = request.getParameter("materialLength");
			c.setMaterialLength(materialLength);
			String materialWidth = request.getParameter("materialWidth");
			c.setMaterialWidth(materialWidth);
			String materialHight = request.getParameter("materialHight");
			c.setMaterialHight(materialHight);
			String materialVolume = request.getParameter("materialVolume");
			c.setMaterialVolume(materialVolume);
			String materialWeight = request.getParameter("materialWeight");
			c.setMaterialWeight(materialWeight);
			String materialLt = request.getParameter("materialLt");
			c.setMaterialLt(materialLt);
			String materialIncomType = request.getParameter("materialIncomType");
			c.setMaterialIncomType(materialIncomType);
			String materialLowLimitmaterial = request.getParameter("materialLowLimitmaterial");
			c.setMaterialLowLimitmaterial(materialLowLimitmaterial);
			String materialBatch = request.getParameter("materialBatch");
			c.setMaterialBatch(materialBatch);
			String daysOfFailure = request.getParameter("daysOfFailure");
			c.setDaysOfFailure(daysOfFailure);
		}

		try {
			materialService.addMaterial(c);
			json.put("code", 0);
		} catch (ServicesException e) {
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

	@RequestMapping("/delJlmaterial")
	@ResponseBody
	public JSONObject delJlmaterial(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");
		try {
			materialService.delMaterial(Integer.parseInt(id));
			json.put("code", 0);
		} catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}
		return json;
	}

	@RequestMapping("/findJlMaterial")
	@ResponseBody
	public JSONObject findJlMaterial(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");
		try {
			CMesJlMaterialT findByid = materialService.findMaterialByid(Integer.parseInt(id));
			json.put("jlMaterial", findByid);
			json.put("code", 0);
		} catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}
		return json;
	}

	@RequestMapping("/editJlmaterial")
	@ResponseBody
	public JSONObject editJlmaterial(HttpServletRequest request) throws ParseException {
		JSONObject json = new JSONObject();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		CMesJlMaterialT c = new CMesJlMaterialT();
		String id = request.getParameter("id");
		c.setId(Integer.parseInt(id));
		String bomId = request.getParameter("bomId1");
		c.setBomId(bomId);
		String materialName = request.getParameter("materialName1");
		c.setMaterialName(materialName);
		String description = request.getParameter("description1");
		c.setDescription(description);
		String specifications = request.getParameter("specifications1");
		c.setSpecifications(specifications);
		String materialGroup = request.getParameter("materialGroup1");
		c.setMaterialGroup(materialGroup);
		String materialType = request.getParameter("materialType1");
		c.setMaterialType(materialType);
		String stockUnit = request.getParameter("stockUnit1");
		c.setStockUnit(stockUnit);
		String inventoryModelGroup = request.getParameter("inventoryModelGroup1");
		c.setInventoryModelGroup(inventoryModelGroup);
		String inventoryDimensionGroup = request.getParameter("inventoryDimensionGroup1");
		c.setInventoryDimensionGroup(inventoryDimensionGroup);
		String release = request.getParameter("release1");
		c.setRelease(release);
		String inspection = request.getParameter("inspection1");
		c.setInspection(inspection);
		String fictitious = request.getParameter("fictitious1");
		c.setFictitious(fictitious);
		String salesUnit = request.getParameter("salesUnit1");
		c.setSalesUnit(salesUnit);
		String secrecy = request.getParameter("secrecy1");
		c.setSecrecy(secrecy);
		String purchasingUnit = request.getParameter("purchasingUnit1");
		c.setPurchasingUnit(purchasingUnit);
		String productionTeam = request.getParameter("productionTeam1");
		c.setProductionTeam(productionTeam);
		String mininumberOfPackages = request.getParameter("mininumberOfPackages1");
		c.setMininumberOfPackages(mininumberOfPackages);
		String termOfValidity = request.getParameter("termOfValidity1");
		c.setTermOfValidity(termOfValidity + ":00");
		String typenum = request.getParameter("typenum1");
		c.setTypenum(typenum);
		String voltage = request.getParameter("voltage1");
		c.setVoltage(voltage);
		String partCounts = request.getParameter("partCounts1");
		c.setPartCounts(partCounts);
		String cellCapacity = request.getParameter("cellCapacity1");
		c.setCellCapacity(cellCapacity);
		String scan = request.getParameter("scan1");
		c.setScan(scan);
		String cellSpecification = request.getParameter("cellSpecification1");
		c.setCellSpecification(cellSpecification);
		String materialLength = request.getParameter("materialLength1");
		c.setMaterialLength(materialLength);
		String materialWidth = request.getParameter("materialWidth1");
		c.setMaterialWidth(materialWidth);
		String materialHight = request.getParameter("materialHight1");
		c.setMaterialHight(materialHight);
		String materialVolume = request.getParameter("materialVolume1");
		c.setMaterialVolume(materialVolume);
		String materialWeight = request.getParameter("materialWeight1");
		c.setMaterialWeight(materialWeight);
		String materialLt = request.getParameter("materialLt1");
		c.setMaterialLt(materialLt);
		String materialIncomType = request.getParameter("materialIncomType1");
		c.setMaterialIncomType(materialIncomType);
		String materialLowLimitmaterial = request.getParameter("materialLowLimitmaterial1");
		c.setMaterialLowLimitmaterial(materialLowLimitmaterial);
		String materialBatch = request.getParameter("materialBatch1");
		c.setMaterialBatch(materialBatch);
		String daysOfFailure = request.getParameter("daysOfFailure1");
		c.setDaysOfFailure(daysOfFailure);
		try {
			materialService.updateMaterial(request);
			json.put("code", 0);
		} catch (ServicesException e) {
			json.put("code", e.getCode());
			json.put("msg", e.getMessage());
		} catch (Exception e) {
			json.put("code", -1);
			json.put("msg", e.getMessage());
		}
		return json;
	}

}
