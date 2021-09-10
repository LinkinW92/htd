package com.skeqi.mes.controller.yin;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.CMesAssemblyType;
import com.skeqi.mes.pojo.CMesJlMaterialT;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesManufactureParametersT;
import com.skeqi.mes.pojo.CMesMaterialListDetailT;
import com.skeqi.mes.pojo.CMesMaterialListT;
import com.skeqi.mes.pojo.CMesMaterialT;
import com.skeqi.mes.pojo.CMesMaterialTypeT;
import com.skeqi.mes.pojo.CMesMfParametersDetailT;
import com.skeqi.mes.pojo.CMesProductionProcessT;
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.pojo.CMesStationT;
import com.skeqi.mes.service.fqz.JlMaterialService;
import com.skeqi.mes.service.yin.DeviceService;
import com.skeqi.mes.service.yin.MaterialService;
import com.skeqi.mes.service.yin.PlanService;
import com.skeqi.mes.service.yin.ProductionService;
import com.skeqi.mes.util.ToolUtils;

@Controller
@RequestMapping("skq")
public class MaterielController {
	@Autowired
	MaterialService materialService;
	@Autowired
	ProductionService productionService;
	@Autowired
	PlanService planService;
	@Autowired
	DeviceService deviceService;
	@Autowired
	JlMaterialService jtservice;

	/**
	 * 总成类型列表
	 */
	/*
	 * @RequestMapping("snTypeManager") public String
	 * snTypeManager(HttpServletRequest request,
	 *
	 * @RequestParam(required = false, defaultValue = "1", value = "page") Integer
	 * page) { Map<String, Object> map = new HashMap<>(); PageHelper.startPage(page,
	 * 5); List<CMesAssemblyType> assemblyTypeList =
	 * materialService.assemblyTypeList(map); PageInfo<CMesAssemblyType> pageInfo =
	 * new PageInfo<>(assemblyTypeList, 5); List<CMesLineT> lineList =
	 * productionService.lineList(); request.setAttribute("pageInfo", pageInfo);
	 * request.setAttribute("lineList", lineList); return
	 * "materiel_control/snTypeManager"; }
	 *
	 *//**
		 * 添加总成类型
		 */
	/*
	 * @RequestMapping("addAssemblyType") public @ResponseBody Map<String, Object>
	 * addAssemblyType(HttpServletRequest request) { HashMap<String, Object> map =
	 * new HashMap<>(); String assemblyTypeNo =
	 * request.getParameter("assemblyTypeNo").trim(); String assemblyTypeName =
	 * request.getParameter("assemblyTypeName").trim(); String lineId =
	 * request.getParameter("lineId"); String assemblyTypeDis =
	 * request.getParameter("assemblyTypeDis"); map.put("assemblyTypeNo",
	 * assemblyTypeNo); map.put("assemblyTypeName", assemblyTypeName);
	 * map.put("assemblyTypeDis", assemblyTypeDis); map.put("lineId", lineId);
	 * Integer count = materialService.countAssemblyTypeByAddAssemblyTypeNo(map); if
	 * (count > 0) { map.put("msg", 1); return map; } Integer count1 =
	 * materialService.countAssemblyTypeByAddAssemblyTypeName(map); if (count1 > 0)
	 * { map.put("msg", 2); return map; } try {
	 * materialService.addAssemblyType(map); map.put("msg", 0); } catch (Exception
	 * e) { e.printStackTrace(); } return map; }
	 *
	 *//**
		 * 删除总成类型
		 */
	/*
	 * @RequestMapping("delAssemblyType") public @ResponseBody Map<String, Object>
	 * delShift(HttpServletRequest request) { HashMap<String, Object> map = new
	 * HashMap<>(); String id = request.getParameter("id"); map.put("id", id); //
	 * 验证物料信息是否正关联此总成类型 int countMaterialByAssemblyTypeId =
	 * materialService.countMaterialByAssemblyTypeId(map); if
	 * (countMaterialByAssemblyTypeId > 0) { map.put("msg", -1); return map; } try {
	 * materialService.delAssemblyType(map); map.put("msg", 0); } catch (Exception
	 * e) { } return map; }
	 *
	 *//**
		 * 通过ID查询总成类型
		 */
	/*
	 * @RequestMapping("toEditAssemblyType") public @ResponseBody Map<String,
	 * Object> toEditAssemblyType(HttpServletRequest request) { HashMap<String,
	 * Object> map = new HashMap<>(); String id = request.getParameter("id");
	 * map.put("id", id); List<CMesAssemblyType> assemblyTypeList =
	 * materialService.assemblyTypeList(map); map.put("assemblyType",
	 * assemblyTypeList.get(0)); return map; }
	 *
	 *//**
		 * 修改总成类型
		 *//*
			 * @RequestMapping("editAssemblyType") public @ResponseBody Map<String, Object>
			 * editAssemblyType(HttpServletRequest request) { HashMap<String, Object> map =
			 * new HashMap<>(); String id = request.getParameter("id"); String
			 * assemblyTypeNo = request.getParameter("assemblyTypeNo").trim(); String
			 * assemblyTypeName = request.getParameter("assemblyTypeName").trim(); String
			 * lineId = request.getParameter("lineId"); String assemblyTypeDis =
			 * request.getParameter("assemblyTypeDis"); map.put("assemblyTypeNo",
			 * assemblyTypeNo); map.put("assemblyTypeName", assemblyTypeName);
			 * map.put("assemblyTypeDis", assemblyTypeDis); map.put("lineId", lineId);
			 * map.put("id", id); List<CMesAssemblyType> assemblyTypeList =
			 * materialService.assemblyTypeList(map); if
			 * (!assemblyTypeList.get(0).getAssemblyTypeName().equals(assemblyTypeName) ||
			 * !assemblyTypeList.get(0).getAssemblyTypeNo().equals(assemblyTypeNo)) {
			 * Integer count = materialService.countAssemblyTypeByAddAssemblyTypeNo(map); if
			 * (count > 0) { map.put("msg", 1); return map; } Integer count1 =
			 * materialService.countAssemblyTypeByAddAssemblyTypeName(map); if (count1 > 0)
			 * { map.put("msg", 2); return map; } } try {
			 * materialService.editAssemblyType(map); map.put("msg", 0); } catch (Exception
			 * e) { } return map; }
			 */

	/**
	 * 物料管理列表
	 */
//	@RequestMapping("materielManager")
//	public String materielManager(HttpServletRequest request,@RequestParam(required = false,defaultValue = "1",value = "page")Integer page) {
//		Map<String, Object> map = new HashMap<>();
//		PageHelper.startPage(page,8);
//		String materialName = request.getParameter("materialName");
//		map.put("materialName", materialName);
//		List<CMesMaterialT> materialList = materialService.materialList(map);
//		PageInfo<CMesMaterialT> pageInfo = new PageInfo<>(materialList,5);
//		List<CMesAssemblyType> assemblyTypeList = materialService.assemblyTypeList(map);
//		List<CMesMaterialTypeT> materialTypeList = materialService.materialTypeList(map);
//		List<CMesLabelManagerT> labelManagerList = deviceService.listLabelManager(map);
//		request.setAttribute("pageInfo", pageInfo);
//		request.setAttribute("assemblyTypeList", assemblyTypeList);
//		request.setAttribute("materialTypeList", materialTypeList);
//		request.setAttribute("labelManagerList", labelManagerList);
//		request.setAttribute("materialName", materialName);
//		return "materiel_control/materielManager";
//	}
	/**
	 * 添加物料
	 *
	 * @throws Exception
	 * @throws IllegalStateException
	 */
	@RequestMapping(value = "addMaterial", method = RequestMethod.POST, consumes = "multipart/form-data")
	@ResponseBody
	public Map<String, Object> addMaterial(@RequestParam(required = false) MultipartFile file,
			HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		String path = null;
		String url = null;
		if (!file.isEmpty()) {
			String pictures = file.getOriginalFilename(); // 得到上传时的文件名
			String name = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()); // 获取当前时间并转换为string类型
			String extension = FilenameUtils.getExtension(pictures); // 获取文件后缀
			path = name + "." + extension;
			if (!extension.equals("jpg") && !extension.equals("png") && !extension.equals("jpeg")) {
				map.put("msg", 1);
				return map;
			}
			url = "E:\\upload"; // 要保存的路径
			File dir = new File(url);
			if (!dir.exists()) { // 判断这个路径不存在
				dir.mkdirs(); // 如果不存在就创建
			}
			map.put("path", path);
		}
		String graphNumber = request.getParameter("graphNumber").trim();
		String materialNo = request.getParameter("materialNo").trim();
		String materialName = request.getParameter("materialName").trim();
		String materialTypeId = request.getParameter("materialTypeId");
		String materialEdition = request.getParameter("materialEdition");
		String assemblyTypeId = request.getParameter("assemblyTypeId");
		String materialSeriesName = request.getParameter("materialSeriesName").trim();
		String printLable = request.getParameter("printLable").trim();
		String productionCommodityNumber = request.getParameter("productionCommodityNumber").trim();
		String productionLicence = request.getParameter("productionLicence");
		String proctionSecurityCode = request.getParameter("proctionSecurityCode");
		String samplingType = request.getParameter("samplingType");
		String transferMaterial = request.getParameter("transferMaterial").trim();
		String boxedNumber = request.getParameter("boxedNumber").trim();
		String batchNumber = request.getParameter("batchNumber").trim();
		String materialVr = request.getParameter("materialVr").trim();
		String standardNumber = request.getParameter("standardNumber");
		map.put("graphNumber", graphNumber);
		map.put("materialNo", materialNo);
		map.put("materialName", materialName);
		map.put("materialTypeId", materialTypeId);
		map.put("materialEdition", materialEdition);
		map.put("assemblyTypeId", assemblyTypeId);
		map.put("materialSeriesName", materialSeriesName);
		map.put("printLable", printLable);
		map.put("productionCommodityNumber", productionCommodityNumber);
		map.put("productionLicence", productionLicence);
		map.put("proctionSecurityCode", proctionSecurityCode);
		map.put("samplingType", samplingType);
		map.put("transferMaterial", transferMaterial);
		map.put("boxedNumber", boxedNumber);
		map.put("batchNumber", batchNumber);
		map.put("materialVr", materialVr);
		map.put("standardNumber", standardNumber);
		// Integer count = materialService.countMaterialByName(map);
		Integer count1 = materialService.countMaterialByNo(map);
		// if (count>0) {//判断物料名称是否存在
		// map.put("msg", 1);
		// return map;
		// }
		if (count1 > 0) {// 判断物料编号是否存在
			map.put("msg", 2);
			return map;
		}
		try {
			materialService.addMaterial(map);
			if (!file.isEmpty()) {
				file.transferTo(new File(url + "/" + path));
			}
			map.put("msg", 0);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return map;
	}

	/**
	 * 删除物料信息
	 */
	@RequestMapping("delMaterial")
	public @ResponseBody Map<String, Object> delMaterial(HttpServletRequest request) {
		HashMap<String, Object> map = new HashMap<>();
		String id = request.getParameter("id");
		map.put("id", id);
		List<CMesMaterialT> materialList = materialService.materialList(map);
		map.put("materialName", materialList.get(0).getMaterialName());
		// 验证料单部件明细是否正关联此物料信息
		int countMaterialListDetailByMaterilaListId = materialService.countMaterialListDetailByMaterilaListId(map);
		if (countMaterialListDetailByMaterilaListId > 0) {
			map.put("msg", -1);
			// map.put("str", "");
			return map;
		}
		try {
			materialService.delMaterial(map);
			map.put("msg", 0);
		} catch (Exception e) {
		}
		return map;
	}

	/**
	 * 通过ID查询物料信息
	 */
	@RequestMapping("toEditMaterial")
	public @ResponseBody Map<String, Object> toEditMaterial(HttpServletRequest request) {
		HashMap<String, Object> map = new HashMap<>();
		String id = request.getParameter("id");
		map.put("id", id);
		List<CMesMaterialT> materialList = materialService.materialList(map);
		map.put("material", materialList.get(0));
		return map;
	}

	/**
	 * 修改物料信息
	 */
	@RequestMapping(value = "editMaterial", method = RequestMethod.POST, consumes = "multipart/form-data")
	public @ResponseBody Map<String, Object> editMaterial(@RequestParam(required = false) MultipartFile files,
			HttpServletRequest request) {
		HashMap<String, Object> map = new HashMap<>();
		String path = null;
		String url = null;
		if (!files.isEmpty()) {
			String pictures = files.getOriginalFilename(); // 得到上传时的文件名
			String name = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()); // 获取当前时间并转换为string类型
			String extension = FilenameUtils.getExtension(pictures); // 获取文件后缀
			path = name + "." + extension;
			if (!extension.equals("jpg") && !extension.equals("png") && !extension.equals("jpeg")) {
				map.put("msg", 3);
				return map;
			}
			url = "E:\\upload"; // 要保存的路径
			File dir = new File(url);
			if (!dir.exists()) { // 判断这个路径不存在
				dir.mkdirs(); // 如果不存在就创建
			}
			map.put("path", path);
		} else {
			HashMap<String, Object> maps = new HashMap<>();
			String id = request.getParameter("id");
			maps.put("id", id);
			List<CMesMaterialT> materialList = materialService.materialList(maps);
			map.put("path", materialList.get(0).getPath());
		}
		String id = request.getParameter("id");
		String graphNumber = request.getParameter("graphNumber").trim();
		String materialNo = request.getParameter("materialNo").trim();
		String materialName = request.getParameter("materialName").trim();
		String materialTypeId = request.getParameter("materialTypeId");
		String materialEdition = request.getParameter("materialEdition");
		String assemblyTypeId = request.getParameter("assemblyTypeId");
		String materialSeriesName = request.getParameter("materialSeriesName").trim();
		String printLable = request.getParameter("printLable");
		String productionCommodityNumber = request.getParameter("productionCommodityNumber");
		String productionLicence = request.getParameter("productionLicence");
		String proctionSecurityCode = request.getParameter("proctionSecurityCode").trim();
		String samplingType = request.getParameter("samplingType");
		String transferMaterial = request.getParameter("transferMaterial").trim();
		String boxedNumber = request.getParameter("boxedNumber").trim();
		String batchNumber = request.getParameter("batchNumber").trim();
		String materialVr = request.getParameter("materialVr").trim();
		String standardNumber = request.getParameter("standardNumber").trim();
		map.put("graphNumber", graphNumber);
		map.put("materialNo", materialNo);
		map.put("materialName", materialName);
		map.put("materialTypeId", materialTypeId);
		map.put("materialEdition", materialEdition);
		map.put("assemblyTypeId", assemblyTypeId);
		map.put("materialSeriesName", materialSeriesName);
		map.put("printLable", printLable);
		map.put("productionCommodityNumber", productionCommodityNumber);
		map.put("productionLicence", productionLicence);
		map.put("proctionSecurityCode", proctionSecurityCode);
		map.put("samplingType", samplingType);
		map.put("transferMaterial", transferMaterial);
		map.put("boxedNumber", boxedNumber);
		map.put("batchNumber", batchNumber);
		map.put("materialVr", materialVr);
		map.put("standardNumber", standardNumber);
		map.put("id", id);
		List<CMesMaterialT> materialList = materialService.materialList(map);
		if (!materialList.get(0).getMaterialNo().equals(materialNo)) {
			Integer count = materialService.countMaterialByNo(map);
			// 判断物料编号是否存在
			if (count > 0) {
				map.put("msg", 1);
				return map;
			}
		}
		try {
			materialService.editMaterial(map);
			if (!files.isEmpty()) {
				files.transferTo(new File(url + "/" + path));
			}
			map.put("msg", 0);
		} catch (Exception e) {
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		return map;
	}
	/*
		*//**
			 * 物料类型管理
			 */
	/*
	 * @RequestMapping("materielTypeManager") public String
	 * materielTypeManager(HttpServletRequest request,@RequestParam(required =
	 * false,defaultValue = "1",value = "page")Integer page) { Map<String, Object>
	 * map = new HashMap<>(); PageHelper.startPage(page,5); List<CMesMaterialTypeT>
	 * materialTypeList = materialService.materialTypeList(map);
	 * PageInfo<CMesMaterialTypeT> pageInfo = new PageInfo<>(materialTypeList,5);
	 * request.setAttribute("pageInfo", pageInfo); return
	 * "materiel_control/materialTypeManager"; }
	 *
	 *//**
		 * 添加物料类型
		 */
	/*
	 * @RequestMapping("addMaterialType") public @ResponseBody Map<String, Object>
	 * addMaterialType(HttpServletRequest request) { HashMap<String, Object> map =
	 * new HashMap<>(); String materialType = request.getParameter("materialType");
	 * String dis = request.getParameter("dis"); map.put("materialType",
	 * materialType); map.put("dis", dis); Integer count =
	 * materialService.countMaterialTypeByName(map); if (count>0) { map.put("msg",
	 * 1); return map; } try { materialService.addMaterialType(map); map.put("msg",
	 * 0); } catch (Exception e) { e.printStackTrace(); } return map; }
	 *
	 *//**
		 * 通过ID查询物料类型信息
		 */
	/*
	 * @RequestMapping("toEditMaterialType") public @ResponseBody Map<String,
	 * Object> toEditMaterialType(HttpServletRequest request) { HashMap<String,
	 * Object> map = new HashMap<>(); String id = request.getParameter("id");
	 * map.put("id", id); List<CMesMaterialTypeT> materialTypeList =
	 * materialService.materialTypeList(map); map.put("materialType",
	 * materialTypeList.get(0)); return map; }
	 *//**
		 * 删除物料类型
		 */
	/*
	 * @RequestMapping("delMaterialType") public @ResponseBody Map<String, Object>
	 * delMaterialType(HttpServletRequest request) { HashMap<String, Object> map =
	 * new HashMap<>(); String id = request.getParameter("id"); map.put("id", id);
	 * //验证物料是否正关联此物料类型 int countMaterialMsgByMaterialType =
	 * materialService.countMaterialMsgByMaterialType(map); if
	 * (countMaterialMsgByMaterialType>0) { map.put("msg", -1); return map; } try {
	 * materialService.delMaterialType(map); map.put("msg", 0); } catch (Exception
	 * e) { } return map; }
	 *
	 *
	 *//**
		 * 修改物料类型
		 *//*
			 * @RequestMapping("editMaterialType") public @ResponseBody Map<String, Object>
			 * editMaterialType(HttpServletRequest request) { HashMap<String, Object> map =
			 * new HashMap<>(); String id = request.getParameter("id"); String materialType
			 * = request.getParameter("materialType"); String dis =
			 * request.getParameter("dis"); map.put("materialType", materialType);
			 * map.put("dis", dis); map.put("id", id); List<CMesMaterialTypeT>
			 * materialTypeList = materialService.materialTypeList(map); if
			 * (!materialTypeList.get(0).getMaterialType().equals(materialType)) { Integer
			 * count = materialService.countMaterialTypeByName(map); if (count>0) {
			 * map.put("msg", 1); return map; } } try {
			 * materialService.editMaterialType(map); map.put("msg", 0); } catch (Exception
			 * e) { } return map; }
			 */

	/**
	 * 料单管理
	 */
	/*
	 * @RequestMapping("materialSheetManager") public String
	 * materialSheetManager(HttpServletRequest request,
	 *
	 * @RequestParam(required = false, defaultValue = "1", value = "page") Integer
	 * page,
	 *
	 * @RequestParam(required = false, defaultValue = "1", value = "page1") Integer
	 * page1) { Map<String, Object> map = new HashMap<>();
	 * PageHelper.startPage(page, 5); String lid = request.getParameter("lid");
	 * String lno = request.getParameter("lno"); map.put("lid", lid); map.put("lno",
	 * lno); List<CMesMaterialListT> materialLists =
	 * materialService.materialLists(map); PageInfo<CMesMaterialListT> pageInfo =
	 * new PageInfo<>(materialLists, 5);// 料单列表
	 *
	 * String listNo = request.getParameter("listNo"); map.put("listNo", listNo);
	 * String staname = request.getParameter("staname"); map.put("staname",
	 * staname); PageHelper.startPage(page1, 5); List<CMesMaterialListDetailT>
	 * materialListDetails = materialService.materialListDetails(map);
	 * PageInfo<CMesMaterialListDetailT> pageInfo1 = new
	 * PageInfo<>(materialListDetails, 5);// 料单清单列表
	 *
	 * List<CMesMaterialT> materialList = materialService.materialList(map);
	 *
	 * List<CMesStationT> stationList = planService.stationList();
	 *
	 * List<CMesLineT> lineList = productionService.lineList(); Map<String, Object>
	 * mapss = new HashMap<String, Object>(); List<CMesJlMaterialT> findJT =
	 * jtservice.findAll(mapss); request.setAttribute("findJt", findJT);
	 * request.setAttribute("lid", lid); request.setAttribute("lno", lno);
	 * request.setAttribute("staname", staname); request.setAttribute("stationList",
	 * stationList); request.setAttribute("materialList", materialList);
	 * request.setAttribute("lineList", lineList); request.setAttribute("listNo",
	 * listNo); request.setAttribute("pageInfo", pageInfo);
	 * request.setAttribute("pageInfo1", pageInfo1); request.setAttribute("listNo",
	 * listNo); return "materiel_control/materialSheetManager"; }
	 *
	 * @RequestMapping("/editstatus")
	 *
	 * @ResponseBody public HashMap<String, Object> editstatus(HttpServletRequest
	 * request) { HashMap<String, Object> map = new HashMap<>(); String id =
	 * request.getParameter("id"); try { materialService.editstatus(id);
	 * map.put("msg", 1); } catch (Exception e) { // TODO: handle exception
	 * e.printStackTrace(); map.put("msg", 2); } return map; }
	 *
	 *//**
		 * 添加料单
		 *
		 * @throws Exception
		 */
	/*
	 * @RequestMapping("addMaterialList") public @ResponseBody Map<String, Object>
	 * addMaterialList(HttpServletRequest request) throws Exception {
	 * HashMap<String, Object> map = new HashMap<>(); SimpleDateFormat sim = new
	 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); String listNo =
	 * request.getParameter("listNoS").trim(); String listName =
	 * request.getParameter("listName").trim(); String lineId =
	 * request.getParameter("lineId"); String effectiveTime =
	 * request.getParameter("effectiveTime"); effectiveTime = effectiveTime + ":00";
	 * String invalidTime = request.getParameter("invalidTime"); invalidTime =
	 * invalidTime + ":00"; String listVersion =
	 * request.getParameter("listVersion"); map.put("listNo", listNo);
	 * map.put("listName", listName); map.put("lineId", lineId);
	 * map.put("effectiveTime", effectiveTime); map.put("invalidTime", invalidTime);
	 * map.put("listVersion", listVersion); Date starttime =
	 * sim.parse(effectiveTime); // 开始时间 Date endtime = sim.parse(invalidTime); //
	 * 开始时间 boolean before = starttime.before(endtime); if (before == false) {
	 * map.put("msg", 6); return map; } double findMaxVersion =
	 * materialService.findMaxVersion(); if (findMaxVersion >=
	 * Double.parseDouble(listVersion)) { map.put("msg", 5); return map; } Integer
	 * count = materialService.countMaterialListByNo(map); if (count > 0) {
	 * map.put("msg", 1); return map; } Integer count1 =
	 * materialService.countMaterialListByName(map); if (count1 > 0) {
	 * map.put("msg", 2); return map; } try { materialService.addMaterialList(map);
	 * map.put("msg", 0); } catch (Exception e) { e.printStackTrace(); } return map;
	 * }
	 *
	 *//**
		 * 通过ID 查询料单信息
		 */
	/*
	 * @RequestMapping("toEditMaterialList") public @ResponseBody Map<String,
	 * Object> toEditMaterialList(HttpServletRequest request) { SimpleDateFormat sdf
	 * = new SimpleDateFormat("yyyy-MM-dd HH:mm"); HashMap<String, Object> map = new
	 * HashMap<>(); String id = request.getParameter("id"); map.put("id", id);
	 * List<CMesMaterialListT> materialLists = materialService.materialLists(map);
	 * String effectiveTime = sdf.format(materialLists.get(0).getEffectiveTime());
	 * String invalidTime = sdf.format(materialLists.get(0).getInvalidTime());
	 * map.put("materialList", materialLists.get(0)); map.put("effectiveTime",
	 * effectiveTime); map.put("invalidTime", invalidTime); return map; }
	 *
	 *//**
		 * 通过ID查询料单明细信息
		 */
	/*
	 * @RequestMapping("toEditMaterialDetail") public @ResponseBody Map<String,
	 * Object> toEditMaterialDetail(HttpServletRequest request) { HashMap<String,
	 * Object> map = new HashMap<>(); String id = request.getParameter("id");
	 * map.put("id", id); List<CMesMaterialListDetailT> materialListDetails =
	 * materialService.materialListDetails(map); map.put("materialListDetail",
	 * materialListDetails.get(0)); return map; }
	 *
	 *//**
		 * 修改料单信息
		 *
		 * @throws Exception
		 */
	/*
	 * @RequestMapping("editMaterialList") public @ResponseBody Map<String, Object>
	 * editMaterialList(HttpServletRequest request) throws Exception {
	 * HashMap<String, Object> map = new HashMap<>(); SimpleDateFormat sim = new
	 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); String id =
	 * request.getParameter("id"); String listNo =
	 * request.getParameter("listNo2").trim(); String listNoS =
	 * request.getParameter("hilistno").trim(); String listName =
	 * request.getParameter("listName").trim(); String lineId =
	 * request.getParameter("lineId"); String effectiveTime =
	 * request.getParameter("effectiveTime").trim(); effectiveTime = effectiveTime +
	 * ":00"; String invalidTime = request.getParameter("invalidTime").trim();
	 * invalidTime = invalidTime + ":00"; String listVersion =
	 * request.getParameter("listVersion"); Date starttime =
	 * sim.parse(effectiveTime); // 开始时间 Date endtime = sim.parse(invalidTime); //
	 * 开始时间 boolean before = starttime.before(endtime); if (before == false) {
	 * map.put("msg", 6); return map; } map.put("listNo", listNo);
	 * map.put("listName", listName); map.put("lineId", lineId);
	 * map.put("effectiveTime", effectiveTime); map.put("invalidTime", invalidTime);
	 * map.put("listVersion", listVersion); map.put("id", id); double findMaxVersion
	 * = materialService.findMaxVersion(); if (findMaxVersion >=
	 * Double.parseDouble(listVersion)) { if (Double.parseDouble(listNoS) !=
	 * Double.parseDouble(listVersion)) { map.put("msg", 5); return map; } }
	 * List<CMesMaterialListT> materialLists = materialService.materialLists(map);
	 * if (!materialLists.get(0).getListNo().equals(listNo)) { Integer count =
	 * materialService.countMaterialListByNo(map); if (count > 0) { map.put("msg",
	 * 1); return map; } } if (!materialLists.get(0).getListName().equals(listName))
	 * { Integer count1 = materialService.countMaterialListByName(map); if (count1 >
	 * 0) { map.put("msg", 2); return map; } } try {
	 * materialService.editMaterialList(map); map.put("msg", 0); } catch (Exception
	 * e) { e.printStackTrace(); } return map; }
	 *
	 *//**
		 * 删除料单信息
		 */
	/*
	 * @RequestMapping("delMaterialList") public @ResponseBody Map<String, Object>
	 * delMaterialList(HttpServletRequest request) { HashMap<String, Object> map =
	 * new HashMap<>(); String id = request.getParameter("id"); map.put("id", id);
	 * // 验证料单部件明细是否正关联此料单 int countMaterialListByMaterialListId =
	 * materialService.countMaterialListByMaterialListId(map); if
	 * (countMaterialListByMaterialListId > 0) { map.put("msg", -1); return map; }
	 * try { materialService.delMaterialList(map); map.put("msg", 0); } catch
	 * (Exception e) { } return map; }
	 *
	 * @SuppressWarnings("rawtypes")
	 *
	 * @RequestMapping("findMaterialByName") public @ResponseBody Map
	 * findMaterialByName(HttpServletRequest request,
	 *
	 * @RequestParam(required = false, defaultValue = "1", value = "page") Integer
	 * page) { Map<String, Object> map = new HashMap<>(); String materialName =
	 * request.getParameter("materialName"); map.put("materialName", materialName);
	 * List<CMesMaterialT> materialList = materialService.materialList(map);
	 * map.put("materialList", materialList); return map; }
	 *
	 *//**
		 * 添加料单详情信息
		 */
	/*
	 * @RequestMapping("addMaterialDetail") public @ResponseBody Map<String, Object>
	 * addMaterialDetail(HttpServletRequest request) { HashMap<String, Object> map =
	 * new HashMap<>(); String materialName = request.getParameter("mid-hidden");
	 * String materialNo = jtservice.findBomId(materialName); String graphNumber =
	 * request.getParameter("graphNumber").trim(); String materialNo =
	 * request.getParameter("materialNo"); String materialSheft =
	 * request.getParameter("materialSheft").trim(); String materialReplace =
	 * request.getParameter("materialReplace"); String materialTrace =
	 * request.getParameter("materialTrace"); String materialNumber =
	 * request.getParameter("materialNumber"); String materialImpFlag =
	 * request.getParameter("materialImpFlag"); String materialCheck =
	 * request.getParameter("materialCheck"); String materialGetNumber =
	 * request.getParameter("materialGetNumber"); String materialStore =
	 * request.getParameter("materialStore"); String materialGetCheckFlag =
	 * request.getParameter("materialGetCheckFlag"); String stationId =
	 * request.getParameter("stationId"); String materialPullFalg =
	 * request.getParameter("materialPullFalg"); String materilaListId =
	 * request.getParameter("materilaListId"); map.put("materialName",
	 * materialName); map.put("figureNo", graphNumber); map.put("materialNo",
	 * materialNo); map.put("materialSheft", materialSheft);
	 * map.put("materialReplace", materialReplace); map.put("materialTrace",
	 * materialTrace); map.put("materialNumber", materialNumber);
	 * map.put("materialImpFlag", materialImpFlag); map.put("materialCheck",
	 * materialCheck); map.put("materialGetNumber", materialGetNumber);
	 * map.put("materialStore", materialStore); map.put("materialGetCheckFlag",
	 * materialGetCheckFlag); map.put("stationId", stationId);
	 * map.put("materialPullFalg", materialPullFalg); map.put("materilaListId",
	 * materilaListId);
	 *
	 * try { materialService.addMaterialDetail(map); map.put("msg", 0); } catch
	 * (Exception e) { e.printStackTrace(); } return map; }
	 *
	 *//**
		 * 修改料单明细信息
		 */
	/*
	 * @RequestMapping("editMaterialDetail") public @ResponseBody Map<String,
	 * Object> editMaterialDetail(HttpServletRequest request) { HashMap<String,
	 * Object> map = new HashMap<>(); String id = request.getParameter("id"); String
	 * materialName = request.getParameter("mids-hidden"); String materialNo =
	 * materialService.findNo(materialName); String graphNumber =
	 * request.getParameter("graphNumber"); String materialNo =
	 * request.getParameter("materialNo"); String materialSheft =
	 * request.getParameter("materialSheft"); String materialReplace =
	 * request.getParameter("materialReplace"); String materialTrace =
	 * request.getParameter("materialTrace"); String materialNumber =
	 * request.getParameter("materialNumber"); String materialImpFlag =
	 * request.getParameter("materialImpFlag"); String materialCheck =
	 * request.getParameter("materialCheck"); String materialGetNumber =
	 * request.getParameter("materialGetNumber"); String materialStore =
	 * request.getParameter("materialStore"); String materialGetCheckFlag =
	 * request.getParameter("materialGetCheckFlag"); String stationId =
	 * request.getParameter("stationId"); String materialPullFalg =
	 * request.getParameter("materialPullFalg"); String materilaListId =
	 * request.getParameter("materilaListId"); map.put("materialName",
	 * materialName); map.put("figureNo", graphNumber); map.put("materialNo",
	 * materialNo); map.put("materialSheft", materialSheft);
	 * map.put("materialReplace", materialReplace); map.put("materialTrace",
	 * materialTrace); map.put("materialNumber", materialNumber);
	 * map.put("materialImpFlag", materialImpFlag); map.put("materialCheck",
	 * materialCheck); map.put("materialGetNumber", materialGetNumber);
	 * map.put("materialStore", materialStore); map.put("materialGetCheckFlag",
	 * materialGetCheckFlag); map.put("stationId", stationId);
	 * map.put("materialPullFalg", materialPullFalg); map.put("materilaListId",
	 * materilaListId); map.put("id", id); try {
	 * materialService.editMaterialDetail(map); map.put("msg", 0); } catch
	 * (Exception e) { e.printStackTrace(); } return map; }
	 *
	 *//**
		 * 删除料单明细信息
		 *//*
			 * @RequestMapping("delMaterialDetail") public @ResponseBody Map<String, Object>
			 * delMaterialDetail(HttpServletRequest request) { HashMap<String, Object> map =
			 * new HashMap<>(); String id = request.getParameter("id"); map.put("id", id);
			 * try { materialService.delMaterialDetail(map); map.put("msg", 0); } catch
			 * (Exception e) { } return map; }
			 */

	/**
	 * 制造参数清单列表
	 */
	/*
	 * @RequestMapping("manuParameterListManager") public String
	 * manuParameterListManager(HttpServletRequest request,
	 *
	 * @RequestParam(required = false, defaultValue = "1", value = "page") Integer
	 * page,
	 *
	 * @RequestParam(required = false, defaultValue = "1", value = "page1") Integer
	 * page1) { Map<String, Object> map = new HashMap<>();
	 * PageHelper.startPage(page, 8); String lid = request.getParameter("lid");
	 * String lno = request.getParameter("lno"); map.put("lid", lid); map.put("lno",
	 * lno); List<CMesManufactureParametersT> manuParameterLists =
	 * materialService.manuParameterLists(map); PageInfo<CMesManufactureParametersT>
	 * pageInfo = new PageInfo<>(manuParameterLists, 5);// 制造参数清单列表
	 *
	 * String listNo = request.getParameter("listNo"); map.put("listNo", listNo);
	 * String staName = request.getParameter("staName"); map.put("staName",
	 * staName); PageHelper.startPage(page1, 5); List<CMesMfParametersDetailT>
	 * mfParametersDetailList = materialService.mfParametersDetailList(map);
	 * PageInfo<CMesMfParametersDetailT> pageInfo1 = new
	 * PageInfo<>(mfParametersDetailList, 5);// 制造参数清单明细列表
	 *
	 * List<CMesLineT> lineList = productionService.lineList(); List<CMesStationT>
	 * findStation = materialService.findStation(); request.setAttribute("lid",
	 * lid); request.setAttribute("lno", lno); request.setAttribute("findStation",
	 * findStation); request.setAttribute("pageInfo", pageInfo);
	 * request.setAttribute("pageInfo1", pageInfo1);
	 * request.setAttribute("lineList", lineList); request.setAttribute("listNo",
	 * listNo); request.setAttribute("staName", staName); return
	 * "materiel_control/manuParameterListManager"; }
	 *
	 *//**
		 * 添加制造参数清单
		 *
		 * @throws Exception
		 */
	/*
	 * @RequestMapping("addManuParameterList") public @ResponseBody Map<String,
	 * Object> addManuParameterList(HttpServletRequest request) throws Exception {
	 * HashMap<String, Object> map = new HashMap<>(); SimpleDateFormat sim = new
	 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); String listNo =
	 * request.getParameter("listNo"); String listName =
	 * request.getParameter("listName").trim(); String lineId =
	 * request.getParameter("lineId"); String effectiveTime =
	 * request.getParameter("effectiveTime"); effectiveTime = effectiveTime + ":00";
	 * String invalidTime = request.getParameter("invalidTime"); invalidTime =
	 * invalidTime + ":00"; String listVersion =
	 * request.getParameter("listVersion"); Date starttime =
	 * sim.parse(effectiveTime); // 开始时间 Date endtime = sim.parse(invalidTime); //
	 * 开始时间 boolean before = starttime.before(endtime); if (before == false) {
	 * map.put("msg", 6); return map; } map.put("listNo", listNo);
	 * map.put("listName", listName); map.put("lineId", lineId);
	 * map.put("effectiveTime", effectiveTime); map.put("invalidTime", invalidTime);
	 * map.put("listVersion", listVersion); Integer count =
	 * materialService.countaddManuParameterListByNo(map); Integer count1 =
	 * materialService.countaddManuParameterListByName(map); Double findMaxmanu =
	 * materialService.findMaxmanu(); if (findMaxmanu >=
	 * Double.parseDouble(listVersion)) { map.put("msg", 5); return map; } if (count
	 * > 0) { map.put("msg", 2); return map; } if (count1 > 0) { map.put("msg", 1);
	 * return map; } try { materialService.addManuParameterList(map); map.put("msg",
	 * 0); } catch (Exception e) { e.printStackTrace(); } return map; }
	 *
	 *//**
		 * 通过ID 查询制造参数清单
		 */
	/*
	 * @RequestMapping("toEditManuParameterList") public @ResponseBody Map<String,
	 * Object> toEditManuParameterList(HttpServletRequest request) {
	 * SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	 * HashMap<String, Object> map = new HashMap<>(); String id =
	 * request.getParameter("id"); map.put("id", id);
	 * List<CMesManufactureParametersT> manuParameterLists =
	 * materialService.manuParameterLists(map); String effectiveTime =
	 * sdf.format(manuParameterLists.get(0).getEffectiveTime()); String invalidTime
	 * = sdf.format(manuParameterLists.get(0).getInvalidTime());
	 * map.put("manuParameterList", manuParameterLists.get(0));
	 * map.put("effectiveTime", effectiveTime); map.put("invalidTime", invalidTime);
	 * return map; }
	 *
	 *//**
		 * 修改制造清单
		 *
		 * @throws Exception
		 */
	/*
	 * @RequestMapping("editManuParameterList") public @ResponseBody Map<String,
	 * Object> editManuParameterList(HttpServletRequest request) throws Exception {
	 * HashMap<String, Object> map = new HashMap<>(); SimpleDateFormat sim = new
	 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); String id =
	 * request.getParameter("id"); String listNo = request.getParameter("listNo");
	 * String listName = request.getParameter("listName").trim(); String lineId =
	 * request.getParameter("lineId"); String effectiveTime =
	 * request.getParameter("effectiveTime"); effectiveTime = effectiveTime + ":00";
	 * String invalidTime = request.getParameter("invalidTime"); invalidTime =
	 * invalidTime + ":00"; String listVersion =
	 * request.getParameter("listVersion"); String hiverson =
	 * request.getParameter("hiverson"); Date starttime = sim.parse(effectiveTime);
	 * // 开始时间 Date endtime = sim.parse(invalidTime); // 开始时间 boolean before =
	 * starttime.before(endtime); if (before == false) { map.put("msg", 6); return
	 * map; } map.put("listNo", listNo); map.put("listName", listName);
	 * map.put("lineId", lineId); map.put("effectiveTime", effectiveTime);
	 * map.put("invalidTime", invalidTime); map.put("listVersion", listVersion);
	 * map.put("id", id); Double findMaxmanu = materialService.findMaxmanu();
	 * List<CMesManufactureParametersT> manuParameterLists =
	 * materialService.manuParameterLists(map); if (findMaxmanu >=
	 * Double.parseDouble(listVersion)) { if (Double.parseDouble(hiverson) !=
	 * Double.parseDouble(listVersion)) { map.put("msg", 5); return map; } } if
	 * (!manuParameterLists.get(0).getListNo().equals(listNo) ||
	 * !manuParameterLists.get(0).getListName().equals(listName)) { Integer count =
	 * materialService.countaddManuParameterListByNo(map); Integer count1 =
	 * materialService.countaddManuParameterListByName(map); if (count > 0) {
	 * map.put("msg", 2); return map; } if (count1 > 0) { map.put("msg", 1); return
	 * map; } } try { materialService.editManuParameterList(map); map.put("msg", 0);
	 * } catch (Exception e) { e.printStackTrace(); } return map; }
	 *
	 *//**
		 * 删除制造清单信息
		 */
	/*
	 * @RequestMapping("delManuParameterList") public @ResponseBody Map<String,
	 * Object> delManuParameterList(HttpServletRequest request) { HashMap<String,
	 * Object> map = new HashMap<>(); String id = request.getParameter("id");
	 * map.put("id", id); // 验证工艺配置是否关联制造清单信息 int
	 * countProductionProcessByParameterListId =
	 * materialService.countProductionProcessByParameterListId(map); if
	 * (countProductionProcessByParameterListId > 0) { map.put("msg", -1); return
	 * map; } try { materialService.delManuParameterList(map); map.put("msg", 0); }
	 * catch (Exception e) { } return map; }
	 *
	 *//**
		 * 添加制造参数清单明细
		 */
	/*
	 * @RequestMapping("addManuParameterListDetail") public @ResponseBody
	 * Map<String, Object> addManuParameterListDetail(HttpServletRequest request) {
	 * HashMap<String, Object> map = new HashMap<>(); String mfParametersId =
	 * request.getParameter("mfParametersId"); String parameterNo =
	 * request.getParameter("parameterNo"); String parameterName =
	 * request.getParameter("parameterName"); String parameterMainFlag =
	 * request.getParameter("parameterMainFlag"); String parameterCheck =
	 * request.getParameter("parameterCheck"); String screwNumber =
	 * request.getParameter("screwNumber"); String normalT =
	 * request.getParameter("normalT"); String tUpperLimit =
	 * request.getParameter("tUpperLimit"); String tLowerLimit =
	 * request.getParameter("tLowerLimit"); String normalA =
	 * request.getParameter("normalA"); String aUpperLimit =
	 * request.getParameter("aUpperLimit"); String aLowerLimit =
	 * request.getParameter("aLowerLimit"); String otherMormalT =
	 * request.getParameter("otherMormalT"); String otherUpperLimit =
	 * request.getParameter("otherUpperLimit"); String otherLowerLimit =
	 * request.getParameter("otherLowerLimit"); String parameterReplace =
	 * request.getParameter("parameterReplace"); String staid =
	 * request.getParameter("staName"); map.put("staid", staid);
	 * map.put("mfParametersId", mfParametersId); map.put("parameterNo",
	 * parameterNo); map.put("parameterName", parameterName);
	 * map.put("parameterMainFlag", parameterMainFlag); map.put("parameterCheck",
	 * parameterCheck); map.put("screwNumber", screwNumber); map.put("normalT",
	 * normalT); map.put("tUpperLimit", tUpperLimit); map.put("tLowerLimit",
	 * tLowerLimit); map.put("normalA", normalA); map.put("aUpperLimit",
	 * aUpperLimit); map.put("aLowerLimit", aLowerLimit); map.put("otherMormalT",
	 * otherMormalT); map.put("otherUpperLimit", otherUpperLimit);
	 * map.put("otherLowerLimit", otherLowerLimit); map.put("parameterReplace",
	 * parameterReplace);
	 *
	 * try { materialService.addManuParameterListDetail(map); map.put("msg", 0); }
	 * catch (Exception e) { e.printStackTrace(); } return map; }
	 *
	 *//**
		 * 通过ID 查询制造参数清单
		 */
	/*
	 * @RequestMapping("toEditManuParameterListDetail") public @ResponseBody
	 * Map<String, Object> toEditManuParameterListDetail(HttpServletRequest request)
	 * { HashMap<String, Object> map = new HashMap<>(); String id =
	 * request.getParameter("id"); map.put("id", id); List<CMesMfParametersDetailT>
	 * mfParametersDetailList = materialService.mfParametersDetailList(map);
	 * map.put("mfParametersDetail", mfParametersDetailList.get(0)); return map; }
	 *
	 *//**
		 * 修改制造清单明细
		 */
	/*
	 * @RequestMapping("editManuParameterListDetail") public @ResponseBody
	 * Map<String, Object> editManuParameterListDetail(HttpServletRequest request) {
	 * HashMap<String, Object> map = new HashMap<>(); String id =
	 * request.getParameter("id"); String mfParametersId =
	 * request.getParameter("mfParametersId"); String parameterNo =
	 * request.getParameter("parameterNo"); String parameterName =
	 * request.getParameter("parameterName"); String parameterMainFlag =
	 * request.getParameter("parameterMainFlag"); String parameterCheck =
	 * request.getParameter("parameterCheck"); String screwNumber =
	 * request.getParameter("screwNumber"); String normalT =
	 * request.getParameter("normalT"); String tUpperLimit =
	 * request.getParameter("tUpperLimit"); String tLowerLimit =
	 * request.getParameter("tLowerLimit"); String normalA =
	 * request.getParameter("normalA"); String aUpperLimit =
	 * request.getParameter("aUpperLimit"); String aLowerLimit =
	 * request.getParameter("aLowerLimit"); String otherMormalT =
	 * request.getParameter("otherMormalT"); String otherUpperLimit =
	 * request.getParameter("otherUpperLimit"); String otherLowerLimit =
	 * request.getParameter("otherLowerLimit"); String parameterReplace =
	 * request.getParameter("parameterReplace"); String staid =
	 * request.getParameter("staName"); map.put("staid", staid);
	 * map.put("mfParametersId", mfParametersId); map.put("parameterNo",
	 * parameterNo); map.put("parameterName", parameterName);
	 * map.put("parameterMainFlag", parameterMainFlag); map.put("parameterCheck",
	 * parameterCheck); map.put("screwNumber", screwNumber); map.put("normalT",
	 * normalT); map.put("tUpperLimit", tUpperLimit); map.put("tLowerLimit",
	 * tLowerLimit); map.put("normalA", normalA); map.put("aUpperLimit",
	 * aUpperLimit); map.put("aLowerLimit", aLowerLimit); map.put("otherMormalT",
	 * otherMormalT); map.put("otherUpperLimit", otherUpperLimit);
	 * map.put("otherLowerLimit", otherLowerLimit); map.put("parameterReplace",
	 * parameterReplace); map.put("id", id); try {
	 * materialService.editManuParameterListDetail(map); map.put("msg", 0); } catch
	 * (Exception e) { e.printStackTrace(); } return map; }
	 *
	 *//**
		 * 删除制造清单明细
		 *//*
			 * @RequestMapping("delManuParameterListDetail") public @ResponseBody
			 * Map<String, Object> delManuParameterListDetail(HttpServletRequest request) {
			 * HashMap<String, Object> map = new HashMap<>(); String id =
			 * request.getParameter("id"); map.put("id", id); try {
			 * materialService.delManuParameterListDetail(map); map.put("msg", 0); } catch
			 * (Exception e) { } return map; }
			 */

	/*	*//**
			 * 工艺配置信息
			 */
	/*
	 * @RequestMapping("processConfiguration") public String
	 * processConfiguration(HttpServletRequest request,
	 *
	 * @RequestParam(required = false, defaultValue = "1", value = "page") Integer
	 * page) { Map<String, Object> map = new HashMap<>(); PageHelper.startPage(page,
	 * 5); List<CMesProductionProcessT> productionProcess =
	 * materialService.productionProcess(map); PageInfo<CMesProductionProcessT>
	 * pageInfo = new PageInfo<>(productionProcess, 5); List<CMesMaterialListT>
	 * materialLists = materialService.materialLists(map);// 料单
	 * List<CMesProductionT> productionList = planService.productionList();// 产品
	 * List<CMesManufactureParametersT> manuParameterLists =
	 * materialService.manuParameterLists(map);// 制造清单 List<CMesLineT> lineList =
	 * productionService.lineList(); request.setAttribute("pageInfo", pageInfo);
	 * request.setAttribute("materialLists", materialLists);
	 * request.setAttribute("productionList", productionList);
	 * request.setAttribute("lineList", lineList);
	 * request.setAttribute("manuParameterLists", manuParameterLists); return
	 * "materiel_control/processConfiguration"; }
	 *
	 *//**
		 * 添加工艺配置
		 */
	/*
	 * @RequestMapping("addProcessConfig") public @ResponseBody Map<String, Object>
	 * addProcessConfig(HttpServletRequest request) { HashMap<String, Object> map =
	 * new HashMap<>(); String lineId = request.getParameter("lineId"); String
	 * materialListId = request.getParameter("materialListId"); String mfParameterId
	 * = request.getParameter("mfParameterId"); String productonId =
	 * request.getParameter("productonId"); map.put("lineId", lineId);
	 * map.put("materialListId", materialListId); map.put("mfParameterId",
	 * mfParameterId); map.put("productonId", productonId); int count =
	 * materialService.countProcessConfigByAll(map); if (count > 0) { map.put("msg",
	 * 1); return map; } try { materialService.addProcessConfig(map); map.put("msg",
	 * 0); } catch (Exception e) { e.printStackTrace(); } return map; }
	 *
	 *//**
		 * 删除工艺配置
		 */
	/*
	 * @RequestMapping("delProcessConfig") public @ResponseBody Map<String, Object>
	 * delProcessConfig(HttpServletRequest request) { HashMap<String, Object> map =
	 * new HashMap<>(); String id = request.getParameter("id"); map.put("id", id);
	 * try { materialService.delProcessConfig(map); map.put("msg", 0); } catch
	 * (Exception e) { } return map; }
	 *
	 *//**
		 * 通过ID查询工艺配置信息
		 */
	/*
	 * @RequestMapping("toEditProcessConfig") public @ResponseBody Map<String,
	 * Object> toEditProcessConfig(HttpServletRequest request) { HashMap<String,
	 * Object> map = new HashMap<>(); String id = request.getParameter("id");
	 * map.put("id", id); List<CMesProductionProcessT> productionProcess =
	 * materialService.productionProcess(map); map.put("productionProcess",
	 * productionProcess.get(0)); return map; }
	 *
	 *//**
		 * 修改工艺配置
		 *//*
			 * @RequestMapping("editProcessConfig") public @ResponseBody Map<String, Object>
			 * editProcessConfig(HttpServletRequest request) { HashMap<String, Object> map =
			 * new HashMap<>(); String id = request.getParameter("id"); String lineId =
			 * request.getParameter("lineId"); String materialListId =
			 * request.getParameter("materialListId"); String mfParameterId =
			 * request.getParameter("mfParameterId"); String productonId =
			 * request.getParameter("productonId"); map.put("lineId", lineId);
			 * map.put("materialListId", materialListId); map.put("mfParameterId",
			 * mfParameterId); map.put("productonId", productonId); map.put("id", id);
			 * List<CMesProductionProcessT> productionProcess =
			 * materialService.productionProcess(map); if
			 * (!productionProcess.get(0).getLineId().equals(lineId) &&
			 * !productionProcess.get(0).getMaterialListId().equals(materialListId) &&
			 * !productionProcess.get(0).getMfParameterId().equals(mfParameterId) &&
			 * !productionProcess.get(0).getProductonId().equals(productonId)) { int count =
			 * materialService.countProcessConfigByAll(map); if (count > 0) { map.put("msg",
			 * 1); return map; } } try { materialService.editProcessConfig(map);
			 * map.put("msg", 0); } catch (Exception e) { e.printStackTrace(); } return map;
			 * }
			 */

}
