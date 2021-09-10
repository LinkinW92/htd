package com.skeqi.mes.controller.yp.wms;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.service.yp.wms.FactoryMaterialService;
import com.skeqi.mes.util.Rjson;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * @author yinp
 * @explain 工厂物料
 * @date 2021-07-06
 */
@RestController
@RequestMapping("/api/wms/factoryMaterial")
public class FactoryMaterialController {

	@Autowired
	FactoryMaterialService service;

	/**
	 * 查询集合
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	public Rjson list(HttpServletRequest request) {
		try {
			Integer pageNum = EqualsUtil.pageNum(request);
			Integer pageSize = EqualsUtil.pageSize(request);

			String materialName = EqualsUtil.string(request, "materialName", "物料名称", false);
			Integer factoryId = EqualsUtil.integer(request, "factoryId", "工厂Id", false);

			JSONObject json = new JSONObject();
			json.put("materialName", materialName);
			json.put("factoryId", factoryId);

			PageHelper.startPage(pageNum, pageSize);
			List<JSONObject> list = service.list(json);
			PageInfo<JSONObject> pageInfo = new PageInfo<JSONObject>(list, 5);

			return Rjson.success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 新增
	 *
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/add")
	public Rjson add(HttpServletRequest request) {
		try {
			String materialId = EqualsUtil.string(request, "materialId", "物料ID", true);
			String factoryId = EqualsUtil.string(request, "factoryId", "工厂ID", true);
			String stockUnit = EqualsUtil.string(request, "stockUnit", "库存单位", false);
			String inventoryModelGroup = EqualsUtil.string(request, "inventoryModelGroup", "库存模型组", false);
			String inventoryDimensionGroup = EqualsUtil.string(request, "inventoryDimensionGroup", "库存维组", false);
			String release = EqualsUtil.string(request, "release", "发放", false);
			String inspection = EqualsUtil.string(request, "inspection", "检验等级", false);
			String fictitious = EqualsUtil.string(request, "fictitious", "虚拟", false);
			String salesUnit = EqualsUtil.string(request, "salesUnit", "销售单位", false);
			String secrecy = EqualsUtil.string(request, "secrecy", "保密否", false);
			String purchasingUnit = EqualsUtil.string(request, "purchasingUnit", "采购单位", false);
			String productionTeam = EqualsUtil.string(request, "productionTeam", "生产组", false);
			String mininumberOfPackages = EqualsUtil.string(request, "mininumberOfPackages", "最小包装数量", false);
			String termOfValidity = EqualsUtil.string(request, "termOfValidity", "有效期", false);
			String typenum = EqualsUtil.string(request, "typenum", "型号", false);
			String voltage = EqualsUtil.string(request, "voltage", "电压容量", false);
			String partCounts = EqualsUtil.string(request, "partCounts", "子件数", false);
			String cellCapacity = EqualsUtil.string(request, "cellCapacity", "电芯容量", false);
			String scan = EqualsUtil.string(request, "scan", "是否扫描", false);
			String cellSpecification = EqualsUtil.string(request, "cellSpecification", "电芯规格", false);
			String tracesType = EqualsUtil.string(request, "tracesType", "追溯方式", false);
			String deliveryStrategy = EqualsUtil.string(request, "deliveryStrategy", "出库策略", false);
			String purchasingStrategy = EqualsUtil.string(request, "purchasingStrategy", "采购策略", false);
			String procurementCycle = EqualsUtil.string(request, "procurementCycle", "采购周期", false);
			String wQIS = EqualsUtil.string(request, "wQIS", "入库质检策略", false);
			String InspectionMethod = EqualsUtil.string(request, "InspectionMethod", "检验方式", false);

			JSONObject json = new JSONObject();
			json.put("materialId", materialId);
			json.put("factoryId", factoryId);
			json.put("stockUnit", stockUnit);
			json.put("inventoryModelGroup", inventoryModelGroup);
			json.put("inventoryDimensionGroup", inventoryDimensionGroup);
			json.put("release", release);
			json.put("inspection", inspection);
			json.put("fictitious", fictitious);
			json.put("salesUnit", salesUnit);
			json.put("secrecy", secrecy);
			json.put("purchasingUnit", purchasingUnit);
			json.put("productionTeam", productionTeam);
			json.put("mininumberOfPackages", mininumberOfPackages);
			json.put("termOfValidity", termOfValidity);
			json.put("typenum", typenum);
			json.put("voltage", voltage);
			json.put("partCounts", partCounts);
			json.put("cellCapacity", cellCapacity);
			json.put("scan", scan);
			json.put("cellSpecification", cellSpecification);
			json.put("tracesType", tracesType);
			json.put("deliveryStrategy", deliveryStrategy);
			json.put("purchasingStrategy", purchasingStrategy);
			json.put("procurementCycle", procurementCycle);
			json.put("wQIS", wQIS);
			json.put("InspectionMethod", InspectionMethod);

			service.add(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 更新
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/update")
	public Rjson update(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "ID", true);
			String materialId = EqualsUtil.string(request, "materialId", "物料ID", true);
			String factoryId = EqualsUtil.string(request, "factoryId", "工厂ID", true);
			String stockUnit = EqualsUtil.string(request, "stockUnit", "库存单位", false);
			String inventoryModelGroup = EqualsUtil.string(request, "inventoryModelGroup", "库存模型组", false);
			String inventoryDimensionGroup = EqualsUtil.string(request, "inventoryDimensionGroup", "库存维组", false);
			String release = EqualsUtil.string(request, "release", "发放", false);
			String inspection = EqualsUtil.string(request, "inspection", "检验等级", false);
			String fictitious = EqualsUtil.string(request, "fictitious", "虚拟", false);
			String salesUnit = EqualsUtil.string(request, "salesUnit", "销售单位", false);
			String secrecy = EqualsUtil.string(request, "secrecy", "保密否", false);
			String purchasingUnit = EqualsUtil.string(request, "purchasingUnit", "采购单位", false);
			String productionTeam = EqualsUtil.string(request, "productionTeam", "生产组", false);
			String mininumberOfPackages = EqualsUtil.string(request, "mininumberOfPackages", "最小包装数量", false);
			String termOfValidity = EqualsUtil.string(request, "termOfValidity", "有效期", false);
			String typenum = EqualsUtil.string(request, "typenum", "型号", false);
			String voltage = EqualsUtil.string(request, "voltage", "电压容量", false);
			String partCounts = EqualsUtil.string(request, "partCounts", "子件数", false);
			String cellCapacity = EqualsUtil.string(request, "cellCapacity", "电芯容量", false);
			String scan = EqualsUtil.string(request, "scan", "是否扫描", false);
			String cellSpecification = EqualsUtil.string(request, "cellSpecification", "电芯规格", false);
			String tracesType = EqualsUtil.string(request, "tracesType", "追溯方式", false);
			String deliveryStrategy = EqualsUtil.string(request, "deliveryStrategy", "出库策略", false);
			String purchasingStrategy = EqualsUtil.string(request, "purchasingStrategy", "采购策略", false);
			String procurementCycle = EqualsUtil.string(request, "procurementCycle", "采购周期", false);
			String wQIS = EqualsUtil.string(request, "wQIS", "入库质检策略", false);
			String InspectionMethod = EqualsUtil.string(request, "InspectionMethod", "检验方式", false);

			JSONObject json = new JSONObject();
			json.put("id", id);
			json.put("materialId", materialId);
			json.put("factoryId", factoryId);
			json.put("stockUnit", stockUnit);
			json.put("inventoryModelGroup", inventoryModelGroup);
			json.put("inventoryDimensionGroup", inventoryDimensionGroup);
			json.put("release", release);
			json.put("inspection", inspection);
			json.put("fictitious", fictitious);
			json.put("salesUnit", salesUnit);
			json.put("secrecy", secrecy);
			json.put("purchasingUnit", purchasingUnit);
			json.put("productionTeam", productionTeam);
			json.put("mininumberOfPackages", mininumberOfPackages);
			json.put("termOfValidity", termOfValidity);
			json.put("typenum", typenum);
			json.put("voltage", voltage);
			json.put("partCounts", partCounts);
			json.put("cellCapacity", cellCapacity);
			json.put("scan", scan);
			json.put("cellSpecification", cellSpecification);
			json.put("tracesType", tracesType);
			json.put("deliveryStrategy", deliveryStrategy);
			json.put("purchasingStrategy", purchasingStrategy);
			json.put("procurementCycle", procurementCycle);
			json.put("wQIS", wQIS);
			json.put("InspectionMethod", InspectionMethod);

			service.update(json);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 删除
	 *
	 * @param request
	 * @return
	 */
	@Transactional
	@RequestMapping("/delete")
	public Rjson delete(HttpServletRequest request) {
		try {
			Integer id = EqualsUtil.integer(request, "id", "ID", true);

			service.delete(id);

			return Rjson.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询所有物料
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/findMaterialAll")
	public Rjson findMaterialAll(HttpServletRequest request) {
		try {
			List<JSONObject> list = service.findMaterialAll();

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

	/**
	 * 查询所有工厂
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/findFactoryAll")
	public Rjson findFactoryAll(HttpServletRequest request) {
		try {
			List<JSONObject> list = service.findFactoryAll();

			return Rjson.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Rjson.error(e.getMessage());
		}
	}

}
