package com.skeqi.mes.controller.fqz;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesJlMaterialT;
import com.skeqi.mes.pojo.CMesMaterialTypeT;
import com.skeqi.mes.service.all.CMesMaterialService;
import com.skeqi.mes.service.fqz.JlMaterialService;
import com.skeqi.mes.util.ImportExcel;
import com.skeqi.mes.util.ToolUtils;

@Controller
@RequestMapping("/skq")
public class JlMaterialController {

	@Autowired
	private JlMaterialService service;

	@Autowired
	private CMesMaterialService mservice;

	/*
	 * @RequestMapping("/materielManager") public String
	 * materielManager(HttpServletRequest request,@RequestParam(required =
	 * false,defaultValue = "1",value = "page")Integer page) throws
	 * ServicesException{ PageHelper.startPage(page,15); Map<String,Object> map =
	 * new HashMap<>(); String materialName = request.getParameter("materialName");
	 * //名称 String bomId = request.getParameter("bomId"); //物料编码 String materialType
	 * = request.getParameter("materialType"); //物料类型 map.put("materialName",
	 * materialName); map.put("bomId", bomId); map.put("materialType",
	 * materialType); List<CMesJlMaterialT> findAll =new
	 * ArrayList<CMesJlMaterialT>(); try { findAll = service.findAll(map); } catch
	 * (Exception e) { // TODO: handle exception e.printStackTrace(); }
	 * List<CMesJlMaterialT> findAll = service.findAll(map);
	 * PageInfo<CMesJlMaterialT> pageinfo = new
	 * PageInfo<CMesJlMaterialT>(findAll,5); List<CMesMaterialTypeT>
	 * findAllMaterialType = mservice.findAllMaterialType(null);
	 * request.setAttribute("pageInfo", pageinfo);
	 * request.setAttribute("materialName",materialName);
	 * request.setAttribute("bomId",bomId ); request.setAttribute("materialType",
	 * materialType); request.setAttribute("mtype", findAllMaterialType);; return
	 * "materiel_control/materielManager"; }
	 *
	 * @RequestMapping("/addJlmaterial")
	 *
	 * @ResponseBody public Map<String,Object> addJlmaterial(HttpServletRequest
	 * request) throws Exception{ Map<String,Object> map = new
	 * HashMap<String,Object>(); SimpleDateFormat formatter = new
	 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); CMesJlMaterialT c = new
	 * CMesJlMaterialT(); String bomId = request.getParameter("bomId");
	 * c.setBomId(bomId); String materialName =
	 * request.getParameter("materialName"); c.setMaterialName(materialName); String
	 * description = request.getParameter("description");
	 * c.setDescription(description); String specifications =
	 * request.getParameter("specifications"); c.setSpecifications(specifications);
	 * String materialGroup = request.getParameter("materialGroup");
	 * c.setMaterialGroup(materialGroup); String materialType =
	 * request.getParameter("materialType"); c.setMaterialType(materialType); String
	 * stockUnit = request.getParameter("stockUnit"); c.setStockUnit(stockUnit);
	 * String inventoryModelGroup = request.getParameter("inventoryModelGroup");
	 * c.setInventoryModelGroup(inventoryModelGroup); String inventoryDimensionGroup
	 * = request.getParameter("inventoryDimensionGroup");
	 * c.setInventoryDimensionGroup(inventoryDimensionGroup); String release =
	 * request.getParameter("release"); c.setRelease(release); String inspection =
	 * request.getParameter("inspection"); c.setInspection(inspection); String
	 * fictitious = request.getParameter("fictitious"); c.setFictitious(fictitious);
	 * String salesUnit = request.getParameter("salesUnit");
	 * c.setSalesUnit(salesUnit); String secrecy = request.getParameter("secrecy");
	 * c.setSecrecy(secrecy); String purchasingUnit =
	 * request.getParameter("purchasingUnit"); c.setPurchasingUnit(purchasingUnit);
	 * String productionTeam = request.getParameter("productionTeam");
	 * c.setProductionTeam(productionTeam); String mininumberOfPackages =
	 * request.getParameter("mininumberOfPackages");
	 * c.setMininumberOfPackages(mininumberOfPackages); String termOfValidity =
	 * request.getParameter("termOfValidity");
	 * c.setTermOfValidity(formatter.parse(termOfValidity+":00")); String typenum =
	 * request.getParameter("typenum"); c.setTypenum(typenum); String voltage =
	 * request.getParameter("voltage"); c.setVoltage(voltage); String partCounts =
	 * request.getParameter("partCounts"); c.setPartCounts(partCounts); String
	 * cellCapacity = request.getParameter("cellCapacity");
	 * c.setCellCapacity(cellCapacity); String scan = request.getParameter("scan");
	 * c.setScan(scan); String cellSpecification =
	 * request.getParameter("cellSpecification");
	 * c.setSpecifications(cellSpecification); try { service.insertJlMaterial(c);
	 * map.put("msg",1); } catch (Exception e) { e.printStackTrace();
	 * map.put("msg",2); } return map; }
	 *
	 * @RequestMapping("/delJlmaterial")
	 *
	 * @ResponseBody public Map<String,Object> delJlmaterial(HttpServletRequest
	 * request){ Map<String,Object> map = new HashMap<String,Object>(); String id =
	 * request.getParameter("id"); try {
	 * service.delJlMaterial(Integer.parseInt(id)); map.put("msg",1); } catch
	 * (Exception e) { map.put("msg",2); } return map; }
	 *
	 * @RequestMapping("/findJlMaterial")
	 *
	 * @ResponseBody public CMesJlMaterialT findJlMaterial(HttpServletRequest
	 * request){ Map<String,Object> map = new HashMap<String,Object>(); String id =
	 * request.getParameter("id"); CMesJlMaterialT findByid =
	 * service.findByid(Integer.parseInt(id)); return findByid; }
	 *
	 * @RequestMapping("/editJlmaterial")
	 *
	 * @ResponseBody public Map<String,Object> editJlmaterial(HttpServletRequest
	 * request) throws ParseException{ Map<String,Object> map = new
	 * HashMap<String,Object>(); SimpleDateFormat formatter = new
	 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); CMesJlMaterialT c = new
	 * CMesJlMaterialT(); String id = request.getParameter("id");
	 * c.setId(Integer.parseInt(id)); String bomId = request.getParameter("bomId1");
	 * c.setBomId(bomId); String materialName =
	 * request.getParameter("materialName1"); c.setMaterialName(materialName);
	 * String description = request.getParameter("description1");
	 * c.setDescription(description); String specifications =
	 * request.getParameter("specifications1"); c.setSpecifications(specifications);
	 * String materialGroup = request.getParameter("materialGroup1");
	 * c.setMaterialGroup(materialGroup); String materialType =
	 * request.getParameter("materialType1"); c.setMaterialType(materialType);
	 * String stockUnit = request.getParameter("stockUnit1");
	 * c.setStockUnit(stockUnit); String inventoryModelGroup =
	 * request.getParameter("inventoryModelGroup1");
	 * c.setInventoryModelGroup(inventoryModelGroup); String inventoryDimensionGroup
	 * = request.getParameter("inventoryDimensionGroup1");
	 * c.setInventoryDimensionGroup(inventoryDimensionGroup); String release =
	 * request.getParameter("release1"); c.setRelease(release); String inspection =
	 * request.getParameter("inspection1"); c.setInspection(inspection); String
	 * fictitious = request.getParameter("fictitious1");
	 * c.setFictitious(fictitious); String salesUnit =
	 * request.getParameter("salesUnit1"); c.setSalesUnit(salesUnit); String secrecy
	 * = request.getParameter("secrecy1"); c.setSecrecy(secrecy); String
	 * purchasingUnit = request.getParameter("purchasingUnit1");
	 * c.setPurchasingUnit(purchasingUnit); String productionTeam =
	 * request.getParameter("productionTeam1"); c.setProductionTeam(productionTeam);
	 * String mininumberOfPackages = request.getParameter("mininumberOfPackages1");
	 * c.setMininumberOfPackages(mininumberOfPackages); String termOfValidity =
	 * request.getParameter("termOfValidity1");
	 * c.setTermOfValidity(formatter.parse(termOfValidity+":00")); String typenum =
	 * request.getParameter("typenum1"); c.setTypenum(typenum); String voltage =
	 * request.getParameter("voltage1"); c.setVoltage(voltage); String partCounts =
	 * request.getParameter("partCounts1"); c.setPartCounts(partCounts); String
	 * cellCapacity = request.getParameter("cellCapacity1");
	 * c.setCellCapacity(cellCapacity); String scan = request.getParameter("scan1");
	 * c.setScan(scan); String cellSpecification =
	 * request.getParameter("cellSpecification1");
	 * c.setCellSpecification(cellSpecification); try { service.editJlMaterial(c);
	 * map.put("msg",1); } catch (Exception e) { e.printStackTrace();
	 * map.put("msg",2); } return map; }
	 */

	/**
	 * @author FQZ
	 * @date 2019年11月25日下午2:35:57
	 */
	@RequestMapping("/importMaterial")
	@ResponseBody
	@Transactional(rollbackFor = { Exception.class })
	public Map<String, Object> importMaterial(HttpServletRequest request, @RequestParam(value = "file", required = false) MultipartFile excelFile) {
		Map<String, Object> map2 = new HashMap<String, Object>();
		String fileName = excelFile.getOriginalFilename(); // 获取文件名
		InputStream in = null;
		try {
			in = excelFile.getInputStream();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
		}
		Map<Integer, List<Object>> map = new HashMap<Integer, List<Object>>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CMesJlMaterialT c = new CMesJlMaterialT();
		try {
			map = ImportExcel.getIoValue(in, fileName); // 调用封装的方法获取数据
			List<Object> list2 = map.get(1);
			if (!list2.get(0).toString().trim().equals("物料编码")) {
				map2.put("msg", "第一列名称错误");
				return map2;
			}
			if (!list2.get(1).toString().trim().equals("物料名称")) {
				map2.put("msg", "第二列名称错误");
				return map2;
			} else if (!list2.get(2).toString().trim().equals("描述")) {
				map2.put("msg", "第三列名称错误");
				return map2;
			} else if (!list2.get(3).toString().trim().equals("规格")) {
				map2.put("msg", "第四列名称错误");
				return map2;
			} else if (!list2.get(4).toString().trim().equals("物料组")) {
				map2.put("msg", "第五列名称错误");
				return map2;
			} else if (!list2.get(5).toString().trim().equals("物料类型")) {
				map2.put("msg", "第六列名称错误");
				return map2;
			} else if (!list2.get(6).toString().trim().equals("库存单位")) {
				map2.put("msg", "第七列名称错误");
				return map2;
			} else if (!list2.get(7).toString().trim().equals("库存模型组")) {
				map2.put("msg", "第八列名称错误");
				return map2;
			} else if (!list2.get(8).toString().trim().equals("库存维组")) {
				map2.put("msg", "第九列名称错误");
				return map2;
			} else if (!list2.get(9).toString().trim().equals("发放")) {
				map2.put("msg", "第十列名称错误");
				return map2;
			} else if (!list2.get(10).toString().trim().equals("检验等级")) {
				map2.put("msg", "第十一列名称错误");
				return map2;
			} else if (!list2.get(11).toString().trim().equals("虚拟")) {
				map2.put("msg", "第十二列名称错误");
				return map2;
			} else if (!list2.get(12).toString().trim().equals("销售单位")) {
				map2.put("msg", "第十三列名称错误");
				return map2;
			} else if (!list2.get(13).toString().trim().equals("保密否")) {
				map2.put("msg", "第十四列名称错误");
				return map2;
			} else if (!list2.get(14).toString().trim().equals("采购单位")) {
				map2.put("msg", "第十五列名称错误");
				return map2;
			} else if (!list2.get(15).toString().trim().equals("生产组")) {
				map2.put("msg", "第十六名称错误");
				return map2;
			} else if (!list2.get(16).toString().trim().equals("最小包装数量")) {
				map2.put("msg", "第十七列名称错误");
				return map2;
			} else if (!list2.get(17).toString().trim().equals("有效期")) {
				map2.put("msg", "第十八列名称错误");
				return map2;
			} else if (!list2.get(18).toString().trim().equals("型号")) {
				map2.put("msg", "第十九列名称错误");
				return map2;
			} else if (!list2.get(19).toString().trim().equals("电压容量")) {
				map2.put("msg", "第二十列名称错误");
				return map2;
			} else if (!list2.get(20).toString().trim().equals("子件数")) {
				map2.put("msg", "第二十一列名称错误");
				return map2;
			} else if (!list2.get(21).toString().trim().equals("电芯容量")) {
				map2.put("msg", "第二十二列名称错误");
				return map2;
			} else if (!list2.get(22).toString().trim().equals("是否扫描")) {
				map2.put("msg", "第二十三列名称错误");
				return map2;
			} else if (!list2.get(23).toString().trim().equals("电芯规格")) {
				map2.put("msg", "第二十四列名称错误");
				return map2;
			}
			for (int i = 2; i < map.size(); i++) {
				List<Object> list = map.get(i);
				for (int j = 0; j < list.size(); j++) {
					if (j == 0) {
						if (list.get(j) != null && list.get(j) != "") { // 物料编码
							c.setBomId(list.get(j).toString());
						} else {
							map2.put("msg", "'物料编码'下不允许有空值");
							return map2;
						}
					} else if (j == 1) {
						if (list.get(j) != null && list.get(j) != "") { // 物料名称
							c.setMaterialName(list.get(j).toString());
						} else {
							map2.put("msg", "'物料名称'下不允许有空值");
							return map2;
						}
					} else if (j == 2) {
						if (list.get(j) != null && list.get(j) != "") { // 描述
							c.setDescription(list.get(j).toString());
						} else {
							map2.put("msg", "'描述'下不允许有空值");
							return map2;
						}
					} else if (j == 3) {
						if (list.get(j) != null && list.get(j) != "") { // 规格
							c.setSpecifications(list.get(j).toString());
						} else {
							map2.put("msg", "'规格'下不允许有空值");
							return map2;
						}
					} else if (j == 4) {
						if (list.get(j) != null && list.get(j) != "") { // 物料组
							c.setMaterialGroup(list.get(j).toString());
						} else {
							map2.put("msg", "'物料组'下不允许有空值");
							return map2;
						}
					} else if (j == 5) {
						if (list.get(j).equals("BOM") || list.get(j).equals("物料")) { // 物料类型
							c.setMaterialType(list.get(j).toString());
						} else {
							map2.put("msg", "'物料类型'下有错误");
							return map2;
						}
					} else if (j == 6) {
						if (list.get(j) != null && list.get(j) != "") { // 库存单位
							c.setStockUnit(list.get(j).toString());
						} else {
							map2.put("msg", "'库存单位'下不允许有空值");
							return map2;
						}
					} else if (j == 7) {
						if (list.get(j) != null && list.get(j) != "") { // 库存模型组
							c.setInventoryModelGroup(list.get(j).toString());
						} else {
							map2.put("msg", "'库存模型组'下不允许有空值");
							return map2;
						}
					} else if (j == 8) {
						if (list.get(j) != null && list.get(j) != "") { // 库存维组
							c.setInventoryDimensionGroup(list.get(j).toString());
						} else {
							map2.put("msg", "'库存维组'下不允许有空值");
							return map2;
						}
					} else if (j == 9) {
						if (list.get(j).equals("Y") || list.get(j).equals("N")) { // 发放
							c.setRelease(list.get(j).toString());
						} else {
							map2.put("msg", "'发放方式'下不允许有空值");
							return map2;
						}
					} else if (j == 10) {
						if (list.get(j) != null && list.get(j) != "") { // 检验等级
							c.setInspection(list.get(j).toString());
						} else {
							c.setInspection(null);
						}
					} else if (j == 11) {
						if (list.get(j).equals("Y") || list.get(j).equals("N")) { // 虚拟
							c.setFictitious(list.get(j).toString());
						} else {
							map2.put("msg", "'虚拟下'不允许有空值");
							return map2;
						}
					} else if (j == 12) {
						if (list.get(j) != null && list.get(j) != "") { // 销售单位
							c.setSalesUnit(list.get(j).toString());
						} else {
							map2.put("msg", "'销售单位'下不允许有空值");
							return map2;
						}
					} else if (j == 13) {
						if (list.get(j).equals("Y") || list.get(j).equals("N")) { // 保密否
							c.setSecrecy(list.get(j).toString());
						} else {
							map2.put("msg", "'保密否'下不允许有空值");
							return map2;
						}
					} else if (j == 14) {
						if (list.get(j) != null && list.get(j) != "") { // 采购单位
							c.setPurchasingUnit(list.get(j).toString());
						} else {
							map2.put("msg", "'采购单位'下不允许有空值");
							return map2;
						}
					} else if (j == 15) {
						if (list.get(j) != null && list.get(j) != "") { // 生产组
							c.setProductionTeam(list.get(j).toString());
						} else {
							map2.put("msg", "'生产组'下不允许有空值");
							return map2;
						}
					} else if (j == 16) {
						if (list.get(j) != null && list.get(j) != "") { // 最小包装数量
							c.setMininumberOfPackages(list.get(j).toString());
						} else {
							c.setMininumberOfPackages(null);
						}
					} else if (j == 17) {
						if (list.get(j) != null && list.get(j) != "") { // 有效期
							c.setTermOfValidity(list.get(j).toString());
						} else {
							c.setTermOfValidity(null);
						}
					} else if (j == 18) {
						if (list.get(j) != null && list.get(j) != "") { // 型号
							c.setTypenum(list.get(j).toString());
						} else {
							map2.put("msg", "'型号'下不允许有空值");
							return map2;
						}
					} else if (j == 19) {
						if (list.get(j) != null && list.get(j) != "") { // 电压容量
							c.setVoltage(list.get(j).toString());
						} else {
							map2.put("msg", "'电压容量'下不允许有空值");
							return map2;
						}
					} else if (j == 20) {
						if (list.get(j) != null && list.get(j) != "") { // 子件数
							c.setPartCounts(list.get(j).toString());
						} else {
							map2.put("msg", "'子件数'下不允许有空值");
							return map2;
						}
					} else if (j == 21) {
						if (list.get(j) != null && list.get(j) != "") { // 电芯容量
							c.setCellCapacity(list.get(j).toString());
						} else {
							map2.put("msg", "'电芯容量'下不允许有空值");
							return map2;
						}
					} else if (j == 22) {
						if (list.get(j).equals("N") || list.get(j).equals("Y")) { // 是否扫描
							c.setScan(list.get(j).toString());
						} else {
							map2.put("msg", "'是否扫描'下不允许有空值");
							return map2;
						}
					} else if (j == 23) {
						if (list.get(j) != null && list.get(j) != "") { // 电芯规格
							c.setCellSpecification(list.get(j).toString());
						} else {
							map2.put("msg", "'电芯规格'下不允许有空值");
							return map2;
						}
					}
				}
				service.insertJlMaterial(c);
				map2.put("msg", "true");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ToolUtils.errorLog(this, e, request);
			throw new RuntimeException();
		}
		return map2;
	}
}
