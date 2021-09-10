package com.skeqi.mes.controller.all;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.skeqi.mes.pojo.CMesJlMaterialT;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesMaterialListDetailT;
import com.skeqi.mes.pojo.CMesMaterialListT;
import com.skeqi.mes.pojo.CMesMaterialT;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.service.all.CMesBomService;
import com.skeqi.mes.service.all.CMesLineTService;
import com.skeqi.mes.service.all.CMesMaterialService;
import com.skeqi.mes.service.all.CMesStationTService;
import com.skeqi.mes.util.ToolUtils;

/***
 *
 * @author ENS 料单管理  1
 *
 */
@Controller
@RequestMapping("skq")
public class CMesMaterialSheetManager {


	@Autowired
	CMesBomService bomService;

	@Autowired
	CMesMaterialService materialService;
	@Autowired
	CMesStationTService stationService;
	@Autowired
	CMesLineTService lineService;

	/**
	 * 料单管理
	 */
	@RequestMapping("materialSheetManager")
	public String materialSheetManager(HttpServletRequest request,
			@RequestParam(required = false, defaultValue = "1", value = "page") Integer page,
			@RequestParam(required = false, defaultValue = "1", value = "page1") Integer page1) {
		JSONObject json = new JSONObject();
		try {

		String lid = request.getParameter("lid");
		String lno = request.getParameter("lno");

		System.out.println("*************************************lid:"+lid);
		System.out.println("*************************************lno:"+lno);

		CMesStationT st = new CMesStationT();
		List<CMesStationT> stationList = stationService.findAllStation(st);

		CMesMaterialListT material = new CMesMaterialListT();
		if(lid!=null&&lid!="") {
			material.setLineId(Integer.parseInt(lid));
		}
		if(lno!=null&&lno!="") {
			material.setListNo(lno);
		}

		PageHelper.startPage(page, 5);
		List<CMesMaterialListT> materialLists = bomService.findAllMaterialList(material);
		PageInfo<CMesMaterialListT> pageInfo = new PageInfo<>(materialLists, 5);// 料单列表



		CMesMaterialListDetailT materialListDetail = new CMesMaterialListDetailT();
		String listNo = request.getParameter("listNo");
		String staname = request.getParameter("staname");

		if(listNo!=null&&listNo!="") {
			materialListDetail.setListNo(listNo);
		}
		if(staname!=null&&staname!="") {
			materialListDetail.setStationName(staname);
		}



		PageHelper.startPage(page1, 5);
		List<CMesMaterialListDetailT> materialListDetails =bomService.findMaterialListDetailByLike(materialListDetail);
		PageInfo<CMesMaterialListDetailT> pageInfo1 = new PageInfo<>(materialListDetails, 5);// 料单清单列表
		CMesJlMaterialT jl = new CMesJlMaterialT();

		List<CMesJlMaterialT> findJT = materialService.findAllMaterial(jl);


		CMesLineT line = new CMesLineT();
		List<CMesLineT> lineList = lineService.findAllLine(line);
		request.setAttribute("findJt", findJT);
		request.setAttribute("lid", lid);
		request.setAttribute("lno", lno);
		request.setAttribute("staname", staname);
		request.setAttribute("stationList", stationList);
		request.setAttribute("lineList", lineList);
		request.setAttribute("listNo", listNo);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("pageInfo1", pageInfo1);
		request.setAttribute("listNo", listNo);
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
		return "materiel_control/materialSheetManager";
	}

	@RequestMapping("/editstatus")
	@ResponseBody
	public JSONObject editstatus(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");
		try {
			bomService.findMaterialListByid(Integer.parseInt(id));
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
	 * 添加料单
	 *
	 * @throws Exception
	 */
	@RequestMapping("addMaterialList")
	@ResponseBody
	public  JSONObject addMaterialList(HttpServletRequest request) throws Exception {
		JSONObject json = new JSONObject();
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String listNo = request.getParameter("listNoS").trim();
		String listName = request.getParameter("listName").trim();
		String lineId = request.getParameter("lineId");
		String effectiveTime = request.getParameter("effectiveTime");
		System.out.println(effectiveTime);
		effectiveTime = effectiveTime + ":00";
		String invalidTime = request.getParameter("invalidTime");
		invalidTime = invalidTime + ":00";
		String listVersion = request.getParameter("listVersion");

		CMesMaterialListT material = new CMesMaterialListT();
		material.setListNo(listNo);
		material.setDt(new Date());
		material.setEffectiveTime(effectiveTime);
		material.setLineId(Integer.parseInt(lineId));
		material.setListName(listName);
		material.setListVersion(listVersion);
		material.setInvalidTime(invalidTime);
		try {
		bomService.addMaterialList(material);
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
	 * 通过ID 查询料单信息
	 */
	@RequestMapping("toEditMaterialList")
	@ResponseBody
	public  JSONObject toEditMaterialList(HttpServletRequest request) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");
		try {
		CMesMaterialListT materialLists = bomService.findMaterialListByid(Integer.parseInt(id));
		json.put("materialList", materialLists);
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
		return json;
	}

	/**
	 * 通过ID查询料单明细信息
	 */
	@RequestMapping("toEditMaterialDetail")
	@ResponseBody
	public  JSONObject toEditMaterialDetail(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");
		try {
			CMesMaterialListDetailT materialListDetails = bomService.findMaterialListDetailByid(Integer.parseInt(id));
			CMesJlMaterialT jl = new CMesJlMaterialT();
			List<CMesJlMaterialT> findJT = materialService.findAllMaterial(jl);
			for (CMesJlMaterialT cMesJlMaterialT : findJT) {
				if(materialListDetails.getMaterialName().equals(cMesJlMaterialT.getMaterialName())) {

					json.put("jlId", cMesJlMaterialT.getId());
				}
			}
			json.put("materialListDetail", materialListDetails);
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
	 * 修改料单信息
	 *
	 * @throws Exception
	 */
	@RequestMapping("editMaterialList")
	@ResponseBody
	public  JSONObject editMaterialList(HttpServletRequest request) throws Exception {
		JSONObject json = new JSONObject();
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String id = request.getParameter("id");
		String listNo = request.getParameter("listNo2").trim();
		String listNoS = request.getParameter("hilistno").trim();
		String listName = request.getParameter("listName").trim();
		String lineId = request.getParameter("lineId");
		String effectiveTime = request.getParameter("effectiveTime").trim();
//		effectiveTime = effectiveTime + ":00";
		String invalidTime = request.getParameter("invalidTime").trim();
//		invalidTime = invalidTime + ":00";
		String listVersion = request.getParameter("listVersion");

		CMesMaterialListT material = new CMesMaterialListT();
		material.setListNo(listNo);
		material.setDt(new Date());
		material.setEffectiveTime(effectiveTime);
		material.setLineId(Integer.parseInt(lineId));
		material.setListName(listName);
		material.setListVersion(listVersion);
		material.setInvalidTime(invalidTime);
		material.setId(Integer.parseInt(id));
		try {
			bomService.updateMaterialList(material);
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
		return json;
	}

	/**
	 * 删除料单信息
	 */
	@RequestMapping("delMaterialList")
	@ResponseBody
	public  JSONObject delMaterialList(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");
		try {
			bomService.delMaterialList(Integer.parseInt(id));
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

	@SuppressWarnings("rawtypes")
	@RequestMapping("findMaterialByName")
	@ResponseBody
	public  JSONObject findMaterialByName(HttpServletRequest request,
			@RequestParam(required = false, defaultValue = "1", value = "page") Integer page) {
		JSONObject json = new JSONObject();
		String materialName = request.getParameter("materialName");
		CMesJlMaterialT jl = new CMesJlMaterialT();
		jl.setMaterialName(materialName);
		try {
		List<CMesJlMaterialT> materialList = materialService.findAllMaterial(jl);
		json.put("materialList", materialList);
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
	 * 添加料单详情信息
	 */
	@RequestMapping("addMaterialDetail")
	@ResponseBody
	public  JSONObject addMaterialDetail(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String materialName = request.getParameter("mid-hidden");
		String graphNumber = request.getParameter("graphNumber").trim();
		/* String materialNo = request.getParameter("materialNo"); */

		String materialSheft = request.getParameter("materialSheft").trim();
		String materialReplace = request.getParameter("materialReplace");
		String materialTrace = request.getParameter("materialTrace");
		String materialNumber = request.getParameter("materialNumber");
		String materialImpFlag = request.getParameter("materialImpFlag");
		String materialCheck = request.getParameter("materialCheck");
		String materialGetNumber = request.getParameter("materialGetNumber");
		String materialStore = request.getParameter("materialStore");
		String materialGetCheckFlag = request.getParameter("materialGetCheckFlag");
		String stationId = request.getParameter("stationId");
		String materialPullFalg = request.getParameter("materialPullFalg");
		String materilaListId = request.getParameter("materilaListId");
		CMesMaterialListDetailT detail = new CMesMaterialListDetailT();
		detail.setMaterialName(materialName);
		detail.setFigureNo(graphNumber);
		detail.setMaterialSheft(materialSheft);
		detail.setMaterialReplace(materialReplace);
		detail.setMaterialTrace(Integer.parseInt(materialTrace));
		detail.setMaterialNumber(Integer.parseInt(materialNumber));
		detail.setMaterialImpFlag(Integer.parseInt(materialImpFlag));
		detail.setMaterialCheck(Integer.parseInt(materialCheck));
		detail.setMaterialGetNumber(Integer.parseInt(materialGetNumber));
		detail.setMaterialStore(materialStore);
		detail.setMaterialGetCheckFlag(Integer.parseInt(materialGetCheckFlag));
		detail.setStationId(Integer.decode(stationId));
		detail.setMaterialPullFalg(Integer.parseInt(materialPullFalg));
		detail.setMaterilaListId(materilaListId);

		try {
			bomService.addMaterialListDetail(detail);
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
	 * 修改料单明细信息
	 */
	@RequestMapping("editMaterialDetail")
	@ResponseBody
	public  JSONObject editMaterialDetail(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");
		String materialName = request.getParameter("mid");
		String graphNumber = request.getParameter("graphNumber");
		/* String materialNo = request.getParameter("materialNo"); */
		String materialSheft = request.getParameter("materialSheft");
		String materialReplace = request.getParameter("materialReplace");
		String materialTrace = request.getParameter("materialTrace");
		String materialNumber = request.getParameter("materialNumber");
		String materialImpFlag = request.getParameter("materialImpFlag");
		String materialCheck = request.getParameter("materialCheck");
		String materialGetNumber = request.getParameter("materialGetNumber");
		String materialStore = request.getParameter("materialStore");
		String materialGetCheckFlag = request.getParameter("materialGetCheckFlag");
		String stationId = request.getParameter("stationId");
		String materialPullFalg = request.getParameter("materialPullFalg");
		String materilaListId = request.getParameter("materilaListId");

		CMesMaterialListDetailT detail = new CMesMaterialListDetailT();
		detail.setMaterialName(materialName);
		detail.setFigureNo(graphNumber);
		detail.setMaterialSheft(materialSheft);
		detail.setMaterialReplace(materialReplace);
		detail.setMaterialTrace(Integer.parseInt(materialTrace));
		detail.setMaterialNumber(Integer.parseInt(materialNumber));
		detail.setMaterialImpFlag(Integer.parseInt(materialImpFlag));
		detail.setMaterialCheck(Integer.parseInt(materialCheck));
		detail.setMaterialGetNumber(Integer.parseInt(materialGetNumber));
		detail.setMaterialStore(materialStore);
		detail.setMaterialGetCheckFlag(Integer.parseInt(materialGetCheckFlag));
		detail.setStationId(Integer.decode(stationId));
		detail.setMaterialPullFalg(Integer.parseInt(materialPullFalg));
		detail.setMaterilaListId(materilaListId);
		detail.setId(Integer.parseInt(id));
		try {
			bomService.updateMaterialListDetail(detail);
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

	/**
	 * 删除料单明细信息
	 */
	@RequestMapping("delMaterialDetail")
	@ResponseBody
	public  JSONObject delMaterialDetail(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");
		try {
			bomService.delMaterialListDetail(Integer.parseInt(id));
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
