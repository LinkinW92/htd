package com.skeqi.mes.controller.yin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.CMesBoltInfomationT;
import com.skeqi.mes.pojo.CMesBomDetailT;
import com.skeqi.mes.pojo.CMesBomT;
import com.skeqi.mes.pojo.CMesLeakageInfoT;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesMaterialMsgT;
import com.skeqi.mes.pojo.CMesOtherDataT;
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.service.yin.BomService;
import com.skeqi.mes.service.yin.PlanService;

@Controller
@RequestMapping("skq")
public class BomController {
	@Autowired
	BomService bomService;
	@Autowired
	PlanService planService;

	/*
	 * @RequestMapping("bomManager") public String bomManager(HttpServletRequest
	 * request,@RequestParam(required = false,defaultValue = "1",value =
	 * "page")Integer page) { Map<String, Object> map = new HashMap<>();
	 * List<CMesProductionT> productionList = planService.productionList();
	 * PageHelper.startPage(page,8); List<CMesBomT> bomList =
	 * bomService.bomList(map); PageInfo<CMesBomT> pageInfo = new
	 * PageInfo<>(bomList,5); request.setAttribute("pageInfo", pageInfo);
	 * request.setAttribute("productionList", productionList); return
	 * "bom_control/bomManager"; }
	 */

	/*
	 * @RequestMapping("addBom") public @ResponseBody Object
	 * addBom(HttpServletRequest request) { Map<String, Object> map = new
	 * HashMap<>(); String bomName = request.getParameter("bomName").trim(); String
	 * productionId = request.getParameter("productionId"); String dis =
	 * request.getParameter("dis"); map.put("bomName", bomName);
	 * map.put("productionId", Integer.parseInt(productionId)); map.put("dis", dis);
	 * List<CMesBomT> bomList = bomService.bomList(map); if (bomList.size()>0) {
	 * map.put("msg", 1); return map; } try { bomService.addBom(map); map.put("msg",
	 * 0); } catch (Exception e) { } return map; }
	 *//**
		 * 删除bom明细
		 *//*
			 * @RequestMapping("delBom") public @ResponseBody Object
			 * delBom(HttpServletRequest request) { Map<String, Object> map = new
			 * HashMap<>(); String id = request.getParameter("id"); map.put("id", id); //
			 * //验证标签管理是否正关联此规则类型 // int countBomDetailsByBomId =
			 * bomService.countBomDetailsByBomId(map); try { bomService.delBom(map);
			 * map.put("msg", 0); } catch (Exception e) { } return map; }
			 *
			 * @RequestMapping("toEditBom") public @ResponseBody Object
			 * toEditBom(HttpServletRequest request) { Map<String, Object> map = new
			 * HashMap<>(); String id = request.getParameter("id"); map.put("id", id);
			 * List<CMesBomT> bomList = bomService.bomList(map); map.put("bomList",
			 * bomList.get(0)); return map; }
			 */
	/*
	 * @RequestMapping("toMaterialMsg") public @ResponseBody Object
	 * toMaterial(HttpServletRequest request) { Map<String, Object> map = new
	 * HashMap<>(); String id = request.getParameter("id"); map.put("id", id);
	 * List<CMesMaterialMsgT> materialList = bomService.materialMsgList(map);
	 * map.put("material", materialList.get(0)); return map; }
	 */
	/**
	 * 修改BOM
	 */
	/*
	 * @RequestMapping("editBom") public @ResponseBody Object
	 * editBom(HttpServletRequest request) { Map<String, Object> map = new
	 * HashMap<>(); String id = request.getParameter("id"); String bomName =
	 * request.getParameter("bomName").trim(); String productionId =
	 * request.getParameter("productionId"); String dis =
	 * request.getParameter("dis"); map.put("id", id); List<CMesBomT> bomList =
	 * bomService.bomList(map); map.put("bomName", bomName); map.put("productionId",
	 * productionId); map.put("dis", dis); if
	 * (!bomList.get(0).getBomName().equals(bomName)) { int countBomByName =
	 * bomService.countBomByName(map); if (countBomByName>0) { map.put("msg", 1);
	 * return map; } } try { bomService.editBom(map); map.put("msg", 0); } catch
	 * (Exception e) { } return map; }
	 */
	/**
	 * 修改物料信息
	 */
	/*
	 * @RequestMapping("editMaterialMsg") public @ResponseBody Object
	 * editMaterial(HttpServletRequest request) { Map<String, Object> map = new
	 * HashMap<>(); String materialName =
	 * request.getParameter("materialName").trim(); String st =
	 * request.getParameter("st"); String materialType =
	 * request.getParameter("materialType"); String materialVr =
	 * request.getParameter("materialVr"); String materialComeType =
	 * request.getParameter("materialComeType"); String materialSupplier =
	 * request.getParameter("materialSupplier"); String uploadCode =
	 * request.getParameter("uploadCode"); String dis = request.getParameter("dis");
	 * String id = request.getParameter("id"); map.put("id", id);
	 * List<CMesMaterialMsgT> materialList = bomService.materialMsgList(map);
	 * map.put("materialName", materialName); map.put("st", st);
	 * map.put("materialType", materialType); map.put("materialVr", materialVr);
	 * map.put("materialComeType", materialComeType); map.put("materialSupplier",
	 * materialSupplier); map.put("uploadCode", uploadCode); map.put("dis", dis); if
	 * (!materialList.get(0).getMaterialName().equals(materialName)) { map.put("id",
	 * null); List<CMesMaterialMsgT> materialList1 =
	 * bomService.materialMsgList(map); if (materialList1.size() > 0) {
	 * map.put("msg", 1); return map; } } try { bomService.editMaterialMsg(map);
	 * map.put("msg", 0); } catch (Exception e) { e.printStackTrace(); } return map;
	 * }
	 *
	 *//**
		 * 物料信息
		 */
	/*
	 * @RequestMapping("materialMsgList") public String
	 * materialList(HttpServletRequest request,
	 *
	 * @RequestParam(required = false, defaultValue = "1", value = "page") Integer
	 * page) { Map<String, Object> map = new HashMap<>(); String station =
	 * request.getParameter("station"); List<CMesStationT> stationList =
	 * planService.stationList(); map.put("station", station);
	 * PageHelper.startPage(page, 8); List<CMesMaterialMsgT> materialList =
	 * bomService.materialMsgList(map); PageInfo<CMesMaterialMsgT> pageInfo = new
	 * PageInfo<>(materialList, 5); request.setAttribute("pageInfo", pageInfo);
	 * request.setAttribute("station", station); request.setAttribute("stationList",
	 * stationList); return "bom_control/materialInformation"; }
	 *
	 *//**
		 * 添加物料信息
		 */
	/*
	 * @RequestMapping("addMaterialMsg") public @ResponseBody Object
	 * addMaterial(HttpServletRequest request) { Map<String, Object> map = new
	 * HashMap<>(); String materialName =
	 * request.getParameter("materialName").trim(); String st =
	 * request.getParameter("st"); String materialType =
	 * request.getParameter("materialType"); String materialVr =
	 * request.getParameter("materialVr"); String materialComeType =
	 * request.getParameter("materialComeType"); String materialSupplier =
	 * request.getParameter("materialSupplier"); String uploadCode =
	 * request.getParameter("uploadCode"); String dis = request.getParameter("dis");
	 * map.put("materialName", materialName); map.put("st", st);
	 * map.put("materialType", materialType); map.put("materialVr", materialVr);
	 * map.put("materialComeType", materialComeType); map.put("materialSupplier",
	 * materialSupplier); map.put("uploadCode", uploadCode); map.put("dis", dis);
	 * List<CMesMaterialMsgT> materialList = bomService.materialMsgList(map); if
	 * (materialList.size() > 0) { map.put("msg", 1); return map; } try {
	 * bomService.addMaterialMsg(map); map.put("msg", 0); } catch (Exception e) {
	 * e.printStackTrace(); } return map; }
	 *
	 *//**
		 * 删除物料信息
		 *//*
			 * @RequestMapping("delMaterialMsg") public @ResponseBody Object
			 * delMaterial(HttpServletRequest request) { Map<String, Object> map = new
			 * HashMap<>(); String id = request.getParameter("id"); map.put("id", id); try {
			 * bomService.delMaterialMsg(map); map.put("msg", 0); } catch (Exception e) { }
			 * return map; }
			 */

	/**
	 * 螺栓信息
	 */
	/*
	 * @RequestMapping("boltList") public String boltList(HttpServletRequest
	 * request,
	 *
	 * @RequestParam(required = false, defaultValue = "1", value = "page") Integer
	 * page) { Map<String, Object> map = new HashMap<>(); String station =
	 * request.getParameter("station"); map.put("station", station); map.put("st",
	 * station); List<CMesStationT> stationList = planService.stationList();
	 * PageHelper.startPage(page, 8); List<CMesBoltInfomationT> boltList =
	 * bomService.boltList(map); PageInfo<CMesBoltInfomationT> pageInfo = new
	 * PageInfo<>(boltList, 5); request.setAttribute("pageInfo", pageInfo);
	 * request.setAttribute("station", station); request.setAttribute("stationList",
	 * stationList); return "bom_control/boltInformation"; }
	 *
	 *//**
		 * 添加螺栓
		 */
	/*
	 * @RequestMapping("addBolt") public @ResponseBody Object
	 * addBolt(HttpServletRequest request) { Map<String, Object> map = new
	 * HashMap<>(); String boltName = request.getParameter("boltName").trim();
	 * String boltNo = request.getParameter("boltNo").trim(); String st =
	 * request.getParameter("st").trim(); String aLimit =
	 * request.getParameter("aLimit").trim(); String tLimit =
	 * request.getParameter("tLimit").trim(); String uploadCode =
	 * request.getParameter("uploadCode").trim(); String programNo =
	 * request.getParameter("programNo").trim(); String dis =
	 * request.getParameter("dis"); map.put("boltName", boltName); map.put("st",
	 * st); map.put("boltNo", boltNo); map.put("aLimit", aLimit); map.put("tLimit",
	 * tLimit); map.put("programNo", programNo); map.put("uploadCode", uploadCode);
	 * map.put("dis", dis); int count = bomService.countBoltByNo(map); int count1 =
	 * bomService.countBoltByName(map); if (count > 0) { map.put("msg", 1); return
	 * map; } if (count1 > 0) { map.put("msg", 2); return map; } try {
	 * bomService.addBolt(map); map.put("msg", 0); } catch (Exception e) {
	 * e.printStackTrace(); } return map; }
	 *
	 *//**
		 * 删除螺栓信息
		 */

	/*
	 * @RequestMapping("delBolt") public @ResponseBody Object
	 * delBolt(HttpServletRequest request) { Map<String, Object> map = new
	 * HashMap<>(); String id = request.getParameter("id"); map.put("id", id); try {
	 * bomService.delBolt(map); map.put("msg", 0); } catch (Exception e) { } return
	 * map; }
	 *
	 *//**
		 * 删除气密性信息
		 *//*
			 * @RequestMapping("delAirtightness") public @ResponseBody Object
			 * delAirtightness(HttpServletRequest request) { Map<String, Object> map = new
			 * HashMap<>(); String id = request.getParameter("id"); map.put("id", id); try {
			 * bomService.delAirtightness(map); map.put("msg", 0); } catch (Exception e) { }
			 * return map; }
			 *
			 * @RequestMapping("toEditBolt") public @ResponseBody Object
			 * toEditBolt(HttpServletRequest request) { Map<String, Object> map = new
			 * HashMap<>(); String id = request.getParameter("id"); map.put("id", id);
			 * List<CMesBoltInfomationT> boltList = bomService.boltList(map);
			 * map.put("bolt", boltList.get(0)); return map; }
			 */

	/*
	 * @RequestMapping("toEditAirtightness") public @ResponseBody Object
	 * toEditAirtightness(HttpServletRequest request) { Map<String, Object> map =
	 * new HashMap<>(); String id = request.getParameter("id"); map.put("id", id);
	 * List<CMesLeakageInfoT> leakageList = bomService.leakageList(map);
	 * map.put("leakage", leakageList.get(0)); return map; }
	 */

	/**
	 * 修改螺栓信息
	 *//*
		 * @RequestMapping("editBolt") public @ResponseBody Object
		 * editBolt(HttpServletRequest request) { Map<String, Object> map = new
		 * HashMap<>(); String boltName = request.getParameter("boltName").trim();
		 * String boltNo = request.getParameter("boltNo").trim(); String st =
		 * request.getParameter("st").trim(); String aLimit =
		 * request.getParameter("aLimit").trim(); String tLimit =
		 * request.getParameter("tLimit").trim(); String uploadCode =
		 * request.getParameter("uploadCode").trim(); String programNo =
		 * request.getParameter("programNo").trim(); String dis =
		 * request.getParameter("dis"); String id = request.getParameter("id");
		 * map.put("id", id); List<CMesBoltInfomationT> boltList =
		 * bomService.boltList(map); map.put("boltName", boltName); map.put("st", st);
		 * map.put("boltNo", boltNo); map.put("aLimit", aLimit); map.put("tLimit",
		 * tLimit); map.put("programNo", programNo); map.put("uploadCode", uploadCode);
		 * map.put("dis", dis); if (!boltList.get(0).getBoltName().equals(boltName) ||
		 * !boltList.get(0).getBoltNo().equals(boltNo)) { int count =
		 * bomService.countBoltByNo(map); int count1 = bomService.countBoltByName(map);
		 * if (count > 0) { map.put("msg", 1); return map; } if (count1 > 0) {
		 * map.put("msg", 2); return map; } } try { bomService.editBolt(map);
		 * map.put("msg", 0); } catch (Exception e) { e.printStackTrace(); } return map;
		 * }
		 */
	/**
	 * 修改气密性信息
	 */
	/*
	 * @RequestMapping("editAirtightness") public @ResponseBody Object
	 * editAirtightness(HttpServletRequest request) { Map<String, Object> map = new
	 * HashMap<>(); String leakageName = request.getParameter("leakageName").trim();
	 * String st = request.getParameter("st").trim(); String pvLimit =
	 * request.getParameter("pvLimit").trim(); String lvLimit =
	 * request.getParameter("lvLimit").trim(); String uploadCode =
	 * request.getParameter("uploadCode").trim(); String dis =
	 * request.getParameter("dis"); String id = request.getParameter("id");
	 * map.put("id", id); List<CMesLeakageInfoT> leakageList =
	 * bomService.leakageList(map); map.put("leakageName", leakageName);
	 * map.put("st", st); map.put("pvLimit", pvLimit); map.put("lvLimit", lvLimit);
	 * map.put("uploadCode", uploadCode); map.put("dis", dis); if
	 * (!leakageList.get(0).getLeakageName().equals(leakageName)) { map.put("id",
	 * null); List<CMesLeakageInfoT> leakageList1 = bomService.leakageList(map); if
	 * (leakageList1.size() > 0) { map.put("msg", 1); return map; } } try {
	 * bomService.editAirtightness(map); map.put("msg", 0); } catch (Exception e) {
	 * e.printStackTrace(); } return map; }
	 *
	 *//**
		 * 气密性信息
		 */
	/*
	 * @RequestMapping("airtightnessList") public String
	 * airtightnessList(HttpServletRequest request,
	 *
	 * @RequestParam(required = false, defaultValue = "1", value = "page") Integer
	 * page) { Map<String, Object> map = new HashMap<>(); String station =
	 * request.getParameter("station"); map.put("station", station);
	 * List<CMesStationT> stationList = planService.stationList();
	 * PageHelper.startPage(page, 8); List<CMesLeakageInfoT> leakageList =
	 * bomService.leakageList(map); PageInfo<CMesLeakageInfoT> pageInfo = new
	 * PageInfo<>(leakageList, 5); request.setAttribute("pageInfo", pageInfo);
	 * request.setAttribute("station", station); request.setAttribute("stationList",
	 * stationList); return "bom_control/airtightnessInformation"; }
	 *
	 *//**
		 * 添加气密性信息
		 */
	/*
	 * @RequestMapping("addAirtightness") public @ResponseBody Object
	 * addAirtightness(HttpServletRequest request) { Map<String, Object> map = new
	 * HashMap<>(); String leakageName = request.getParameter("leakageName").trim();
	 * String st = request.getParameter("st").trim(); String pvLimit =
	 * request.getParameter("pvLimit").trim(); String lvLimit =
	 * request.getParameter("lvLimit").trim(); String uploadCode =
	 * request.getParameter("uploadCode").trim(); String dis =
	 * request.getParameter("dis"); map.put("leakageName", leakageName);
	 * map.put("st", st); map.put("pvLimit", pvLimit); map.put("lvLimit", lvLimit);
	 * map.put("uploadCode", uploadCode); map.put("dis", dis);
	 * List<CMesLeakageInfoT> leakageList = bomService.leakageList(map); if
	 * (leakageList.size() > 0) { map.put("msg", 1); return map; } try {
	 * bomService.addAirtightness(map); map.put("msg", 0); } catch (Exception e) {
	 * e.printStackTrace(); } return map; }
	 */
	/*
		*//**
			 * 其他信息
			 */
	/*
	 * @RequestMapping("otherDataList") public String
	 * otherDataList(HttpServletRequest request,
	 *
	 * @RequestParam(required = false, defaultValue = "1", value = "page") Integer
	 * page) { Map<String, Object> map = new HashMap<>(); String station =
	 * request.getParameter("station"); map.put("station", station);
	 * List<CMesStationT> stationList = planService.stationList();
	 * PageHelper.startPage(page, 8); List<CMesOtherDataT> otherDataList =
	 * bomService.otherDataList(map); PageInfo<CMesOtherDataT> pageInfo = new
	 * PageInfo<>(otherDataList, 5); request.setAttribute("pageInfo", pageInfo);
	 * request.setAttribute("station", station); request.setAttribute("stationList",
	 * stationList); return "bom_control/otherInformation"; }
	 *
	 *//**
		 * 添加其他信息
		 */
	/*
	 * @RequestMapping("addOtherData") public @ResponseBody Object
	 * addOtherData(HttpServletRequest request) { Map<String, Object> map = new
	 * HashMap<>(); String otherName = request.getParameter("otherName").trim();
	 * String st = request.getParameter("st"); String uploadCode =
	 * request.getParameter("uploadCode").trim(); String dis =
	 * request.getParameter("dis"); map.put("otherName", otherName);
	 * List<CMesOtherDataT> otherDataList = bomService.otherDataList(map);
	 * map.put("st", st); map.put("uploadCode", uploadCode); map.put("dis", dis); if
	 * (otherDataList.size() > 0) { map.put("msg", 1); return map; } try {
	 * bomService.addOtherData(map); map.put("msg", 0); } catch (Exception e) {
	 * e.printStackTrace(); } return map; }
	 *
	 *//**
		 * 删除其他信息
		 *//*
			 * @RequestMapping("delOtherData") public @ResponseBody Object
			 * delOtherData(HttpServletRequest request) { Map<String, Object> map = new
			 * HashMap<>(); String id = request.getParameter("id"); map.put("id", id); try {
			 * bomService.delOtherData(map); map.put("msg", 0); } catch (Exception e) { }
			 * return map; }
			 */

	/**
	 * 删除BOM明细
	 */
	/*
	 * @RequestMapping("delBomDetails") public @ResponseBody Object
	 * delBomDetails(HttpServletRequest request) { Map<String, Object> map = new
	 * HashMap<>(); String id = request.getParameter("id"); map.put("id", id); try {
	 * bomService.delBomDetails(map); map.put("msg", 0); } catch (Exception e) { }
	 * return map; }
	 */
	/*
	 * @RequestMapping("toEditOtherData") public @ResponseBody Object
	 * toEditOtherData(HttpServletRequest request) { Map<String, Object> map = new
	 * HashMap<>(); String id = request.getParameter("id"); map.put("id", id);
	 * List<CMesOtherDataT> otherDataList = bomService.otherDataList(map);
	 * map.put("otherData", otherDataList.get(0)); return map; }
	 *
	 *//**
		 * 修改其他信息
		 */
	/*
	 * @RequestMapping("editOtherData") public @ResponseBody Object
	 * editOtherData(HttpServletRequest request) { Map<String, Object> map = new
	 * HashMap<>(); String otherName = request.getParameter("otherName").trim();
	 * String st = request.getParameter("st"); String uploadCode =
	 * request.getParameter("uploadCode").trim(); String dis =
	 * request.getParameter("dis"); String id = request.getParameter("id");
	 * map.put("id", id); List<CMesOtherDataT> otherDataList =
	 * bomService.otherDataList(map); map.put("st", st); map.put("otherName",
	 * otherName); map.put("uploadCode", uploadCode); map.put("dis", dis); if
	 * (!otherDataList.get(0).getOtherName().equals(otherName)) { int
	 * countOtherByName = bomService.countOtherByName(map); if (countOtherByName >
	 * 0) { map.put("msg", 1); return map; } } try { bomService.editOtherData(map);
	 * map.put("msg", 0); } catch (Exception e) { e.printStackTrace(); } return map;
	 * }
	 */
	/*
		*//**
			 * BOM明细
			 */
	/*
	 * @RequestMapping("bomDetailsList") public String
	 * bomDetailsList(HttpServletRequest request,
	 *
	 * @RequestParam(required = false, defaultValue = "1", value = "page") Integer
	 * page) { Map<String, Object> map = new HashMap<>(); List<CMesMaterialMsgT>
	 * materialList = bomService.materialMsgList(map);// 物料信息 String bomName =
	 * request.getParameter("bomName"); String bomId =
	 * request.getParameter("bomId"); map.put("bomId", bomId); map.put("bomName",
	 * bomName); PageHelper.startPage(page, 8); List<CMesBomDetailT> bomDetailList =
	 * bomService.bomDetailList(map); PageInfo<CMesBomDetailT> pageInfo = new
	 * PageInfo<>(bomDetailList, 5); List<CMesBomT> bomList =
	 * bomService.bomList(map); List<CMesLineT> findLine = bomService.findLine();
	 * List<CMesStationT> findStation = bomService.findStation();
	 * request.setAttribute("lineList", findLine);
	 * request.setAttribute("stationList", findStation);
	 * request.setAttribute("bomId", bomId); request.setAttribute("bomName",
	 * bomName); request.setAttribute("pageInfo", pageInfo);
	 * request.setAttribute("bomList", bomList);
	 * request.setAttribute("materialList", materialList); return
	 * "bom_control/bomDetails"; }
	 *
	 * @RequestMapping("getBomDetail") public @ResponseBody Object
	 * getBomDetail(HttpServletRequest request) { Map<String, Object> map = new
	 * HashMap<>(); List<CMesMaterialMsgT> materialList =
	 * bomService.materialMsgList(map);// 物料信息 List<CMesBoltInfomationT> boltList =
	 * bomService.boltList(map);// 螺栓信息 List<CMesLeakageInfoT> leakageList =
	 * bomService.leakageList(map);// 气密性信息 List<CMesOtherDataT> otherDataList =
	 * bomService.otherDataList(map); map.put("otherDataList", otherDataList);
	 * map.put("materialList", materialList); map.put("boltList", boltList);
	 * map.put("leakageList", leakageList); return map; }
	 *
	 *//**
		 * 添加BOM明细
		 */
	/*
	 * @RequestMapping("addBomDetails") public @ResponseBody Object
	 * addBomDetail(HttpServletRequest request) { Map<String, Object> map = new
	 * HashMap<>(); String dataType = request.getParameter("dataType").trim(); //
	 * bom类型 String dataId = request.getParameter("dataId"); // 物料。螺栓。气密id String
	 * reciveNumber = request.getParameter("reciveNumber").trim(); // 数量 String
	 * traceFlag = request.getParameter("traceFlag"); // 是否追溯 String bomId =
	 * request.getParameter("bomId"); // bom id String lineId =
	 * request.getParameter("lineId"); String stationId =
	 * request.getParameter("stationId"); map.put("bomId", bomId);
	 * map.put("dataType", dataType); map.put("dataId", dataId);
	 * map.put("reciveNumber", reciveNumber); map.put("traceFlag", traceFlag);
	 * map.put("lineId", lineId); map.put("stationId", stationId);
	 *
	 * List<CMesBomDetailT> bomDetailList =bomService.bomDetailList(map); if
	 * (bomDetailList.size()>0) { map.put("msg", 1); return map; }
	 *
	 * try { bomService.addBomDetails(map); map.put("msg", 0); } catch (Exception e)
	 * { e.printStackTrace(); } return map; }
	 *
	 * @RequestMapping("toEditBomDetails") public @ResponseBody Object
	 * toEditBomDetail(HttpServletRequest request) { Map<String, Object> map = new
	 * HashMap<>(); List<CMesBoltInfomationT> boltList = bomService.boltList(map);//
	 * 螺栓信息 List<CMesLeakageInfoT> leakageList = bomService.leakageList(map);//
	 * 气密性信息 List<CMesOtherDataT> otherDataList = bomService.otherDataList(map);
	 * List<CMesMaterialMsgT> materialList = bomService.materialMsgList(map);// 物料信息
	 * map.put("otherDataList", otherDataList); map.put("materialList",
	 * materialList); map.put("boltList", boltList); map.put("leakageList",
	 * leakageList); String id = request.getParameter("id"); map.put("id", id);
	 * List<CMesBomDetailT> bomDetailList = bomService.bomDetailList(map);
	 * map.put("bomDetail", bomDetailList.get(0)); return map; }
	 *
	 *//**
		 * 修改BOM明细
		 *//*
			 * @RequestMapping("editBomDetails") public @ResponseBody Object
			 * editBomDetails(HttpServletRequest request) { Map<String, Object> map = new
			 * HashMap<>(); String dataType = request.getParameter("dataType").trim();
			 * String dataId = request.getParameter("dataId"); String reciveNumber =
			 * request.getParameter("reciveNumber").trim(); String traceFlag =
			 * request.getParameter("traceFlag"); String bomId =
			 * request.getParameter("bomId"); String id = request.getParameter("id");
			 * map.put("id", id); map.put("bomId", bomId); map.put("dataType", dataType);
			 * map.put("dataId", dataId); map.put("reciveNumber", reciveNumber);
			 * map.put("traceFlag", traceFlag); try { bomService.editBomDetails(map);
			 * map.put("msg", 0); } catch (Exception e) { e.printStackTrace(); } return map;
			 * }
			 */
}
