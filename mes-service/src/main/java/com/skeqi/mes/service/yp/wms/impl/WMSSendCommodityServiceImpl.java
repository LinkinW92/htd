package com.skeqi.mes.service.yp.wms.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.wms.WMSSendCommodityDao;
import com.skeqi.mes.service.wf.baseMode.codeRule.CodeRuleService;
import com.skeqi.mes.service.yp.wms.WMSSendCommodityService;
import com.skeqi.mes.service.yp.wms.WmsInventoryTransactionService;
import com.skeqi.mes.service.yp.wms.WmsStockService;
import com.skeqi.mes.service.yp.wms.other.OtherPurchaseOrderService;
import com.skeqi.mes.service.yp.wms.other.OtherWmsMaterialService;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * @author yinp
 * @explain 来料入库
 * @date 2021-07-14
 */
@Service
public class WMSSendCommodityServiceImpl implements WMSSendCommodityService {

	@Autowired
	WMSSendCommodityDao dao;

	@Autowired
	CodeRuleService codeRuleService;

	@Autowired
	WmsStockService wmsStockService;

	@Autowired
	WmsInventoryTransactionService wmsInventoryTransactionService;

	@Autowired
	OtherWmsMaterialService otherWmsMaterialService;

	@Autowired
	OtherPurchaseOrderService otherPurchaseOrderService;

	/**
	 * 查询
	 *
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<JSONObject> list(JSONObject json) {
		return dao.list(json);
	}

	/**
	 * 新增
	 *
	 * @param json
	 * @return
	 * @throws Exception
	 */
	@Override
	public void add(JSONObject json) throws Exception {
		String orderNumber = json.getString("orderNumber");
		String listNo = null;
		try {
			listNo = codeRuleService.getLatestCode("wmsSalesReturn");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("生成单号时出错了");
		}

		if (listNo != null) {
			json.put("listNo", listNo);
		} else {
			throw new Exception("生成条码时返回空");
		}
		json.put("wQIS", "先入后检");

		if (dao.add(json) != 1) {
			throw new Exception("新增失败");
		}
	}

	/**
	 * 更新
	 *
	 * @param json
	 * @return
	 */
	@Override
	public void update(JSONObject json) throws Exception {
		if (dao.update(json) != 1) {
			throw new Exception("更新失败");
		}
	}

	/**
	 * 删除
	 *
	 * @param id
	 * @throws Exception
	 */
	@Override
	public void delete(Integer id) throws Exception {
		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("isDelete", 1);
		if (dao.update(json) != 1) {
			throw new Exception("删除失败");
		}
	}

	/**
	 * 新增行
	 * 
	 * @param json
	 * @return
	 */
	@Override
	public void addRow(JSONObject json) throws Exception {
		// 单号
		String listNo = json.getString("listNo");
		// 采购订单号
		String purchaseOrderNo = json.getString("purchaseOrderNo");
		// 送货单头表id
		Integer commodityId = json.getInteger("commodityId");
		// 物料集合
		String material = json.getString("materialList");

		List<JSONObject> list = JSONObject.parseArray(material, JSONObject.class);

		boolean al = false;
		for (int i = 0; i < list.size(); i++) {
			String materialNo = list.get(i).getString("materialNo");

			// 获取总实收数量
			Integer totalPaidInQuantity = dao.getTheTotalQuantityReceived(purchaseOrderNo, materialNo);
			// 获取订单应收数量
			Integer orderReceivableQuantity = dao.getOrderReceivableQuantity(purchaseOrderNo, materialNo);

			if (totalPaidInQuantity >= orderReceivableQuantity) {
				throw new Exception(list.get(i).getString("materialName") + " 的计划入库数量为0");
			}
			
			// 计划数量
			Integer num = orderReceivableQuantity - totalPaidInQuantity;
			list.get(i).put("count", num);
		}

		for (JSONObject jsonObject : list) {
			JSONObject r = new JSONObject();
			r.put("listNo", listNo);
			r.put("materialNo", jsonObject.getString("materialNo"));
			r.put("purchaseOrderNo", purchaseOrderNo);
			List<JSONObject> rList = dao.listRow(r);
			if (rList != null && rList.size() > 0) {
				throw new Exception(jsonObject.getString("materialName") + " 已存在");
			}
			
			//通过物料编号查询物料
			JSONObject materialJson = otherWmsMaterialService.findMaterialByNo(jsonObject.getString("materialNo"));
			if(materialJson.getString("wQIS").equals("先检后入")) {
				al = true;
			}else if(materialJson.getString("wQIS").equals("先入后检")) {
				
			}else {
				throw new Exception("物料未配置检验方式");
			}
			
			jsonObject.put("listNo", listNo);
			jsonObject.put("purchaseOrderNo", purchaseOrderNo);
			jsonObject.put("commodityId", commodityId);
			jsonObject.put("paidInNumber", 0);
			dao.addRow(jsonObject);
		}
		
		if(al) {
			JSONObject h = new JSONObject();
			h.put("listNo", listNo);
			h.put("wQIS", "先检后入");
			dao.updateTop(h);
		}
		
		

	}

	/**
	 * 删除行
	 *
	 * @param id
	 */
	@Override
	public void deleteRow(Integer id) throws Exception {
		dao.deleteDByRId(id);
		if (dao.deleteRow(id) != 1) {
			throw new Exception("删除失败");
		}

	}

	/**
	 * 新增详情
	 *
	 * @param json
	 * @return
	 */
	@Override
	public void addD(JSONObject json) throws Exception {
		String listNo = json.getString("listNo");
		String packingId = json.getString("packingId");
		String code = json.getString("code");
		Integer rowId = json.getInteger("rowId");

		// 通过单件码跟包装id查询库存
		JSONObject stock = wmsStockService.findStockByUnitCodeAndPackingId(code, packingId);
		if (stock != null) {
			throw new Exception("包装ID：" + packingId + " - 单件码：" + code + " 库存已存在");
		}

		JSONObject d = new JSONObject();
		d.put("packingId", packingId);
		d.put("code", code);
		d.put("listNo", listNo);
		List<JSONObject> dList = dao.listD(d);
		if (dList != null && dList.size() > 0) {
			throw new Exception("包装ID：" + packingId + " - 单件码：" + code + " 已经提交过了");
		}

		JSONObject r = new JSONObject();
		r.put("id", rowId);
		List<JSONObject> rList = dao.listRow(r);
		if (rList == null || rList.size() == 0) {
			throw new Exception("行数据不存在");
		}

		String materialNo = rList.get(0).getString("materialCode");

		// 通过物料编号查询物料
		JSONObject material = otherWmsMaterialService.findMaterialByNo(materialNo);
		// 检验方式
		String InspectionMethod = material.getString("InspectionMethod");
		// 物料名称
		String materialName = material.getString("MATERIAL_NAME");
		if (InspectionMethod == null || InspectionMethod.equals("")) {
			throw new Exception(materialName + " 未配置检验方式");
		}
		json.put("InspectionMethod", InspectionMethod);
		json.put("checkNumber", 0);
		json.put("qualifiedNumber", 0);

		if (dao.addD(json) != 1) {
			throw new Exception("新增失败");
		}

	}

	/**
	 * 更新详情
	 *
	 * @param json
	 * @return
	 */
	@Override
	public void updateD(JSONObject json) throws Exception {

		Integer rowId = json.getInteger("rowId");

		if (dao.updateD(json) != 1) {
			throw new Exception("更新失败");
		}

		JSONObject d = new JSONObject();
		d.put("rowId", rowId);
		List<JSONObject> dList = dao.listD(d);
		int num = 0;
		for (int i = 0; i < dList.size(); i++) {
			String code = dList.get(i).getString("code");
			Integer unitQuantity = dList.get(i).getInteger("unitQuantity");
			Integer numberOfPackages = dList.get(i).getInteger("numberOfPackages");
			if (code != null && !code.equals("")) {
				num = num + unitQuantity;
			} else {
				num = num + numberOfPackages;
			}
		}

		JSONObject r = new JSONObject();
		r.put("id", rowId);
		r.put("paidInNumber", num);
		// 更新行
		dao.updateRow(r);

	}

	/**
	 * 删除详情
	 *
	 * @param json
	 * @return
	 */
	@Override
	public void deleteD(Integer id, Integer rowId) throws Exception {
		// 删除详情
		dao.deleteD(id);

		JSONObject d = new JSONObject();
		d.put("rowId", rowId);
		List<JSONObject> dList = dao.listD(d);
		int num = 0;
		for (int i = 0; i < dList.size(); i++) {
			String code = dList.get(i).getString("code");
			Integer unitQuantity = dList.get(i).getInteger("unitQuantity");
			Integer numberOfPackages = dList.get(i).getInteger("numberOfPackages");
			if (code != null && !code.equals("")) {
				num = num + unitQuantity;
			} else {
				num = num + numberOfPackages;
			}
		}

		JSONObject r = new JSONObject();
		r.put("id", rowId);
		r.put("paidInNumber", num);
		// 更新行
		dao.updateRow(r);
	}

	/**
	 * 过账
	 *
	 * @param id
	 * @throws Exception
	 */
	@Override
	public void guoZhang(String listNo, Integer userId) throws Exception {
		JSONObject HJson = new JSONObject();
		HJson.put("listNo", listNo);
		// 查询单据
		List<JSONObject> HList = dao.list(HJson);
		if (HList == null || HList.size() != 1) {
			throw new Exception("单据不存在");
		}
		if (HList.get(0).getString("status").equals("5")) {
			throw new Exception("单据已过账");
		}
		HJson = HList.get(0);
		
		// 查询行
		HJson.put("commodityId", HJson.getString("id"));
		List<JSONObject> RList = dao.listRow(HJson);

		for (JSONObject jsonObject : RList) {
			// 采购订单号
			String purchaseOrderNo = jsonObject.getString("purchaseOrderNo");
			// 物料编号
			String materialNo = jsonObject.getString("materialCode");

			// 获取总实收数量
			Integer totalPaidInQuantity = dao.getTheTotalQuantityReceived(purchaseOrderNo, materialNo);
			// 获取订单应收数量
			Integer orderReceivableQuantity = dao.getOrderReceivableQuantity(purchaseOrderNo, materialNo);
			// 本次过账数量
			Integer postQuantity = jsonObject.getInteger("paidInNumber");

			if (orderReceivableQuantity - totalPaidInQuantity - postQuantity < 0) {
				throw new Exception("本次过账数量超出范围");
			}
		}

		// 查询需要过账的数据
		List<JSONObject> list = dao.queryTheDataToBePosted(listNo);
		if (list == null || list.size() == 0) {
			throw new Exception("单据内无数据");
		}
		for (JSONObject jsonObject : list) {
			// 包装ID
			String packageId = jsonObject.getString("packingId");
			// 单件码
			String code = jsonObject.getString("code");
			// 物料id
			Integer materialId = jsonObject.getInteger("materialId");
			// 工厂id
			Integer factoryId = jsonObject.getInteger("factoryId");
			// 物料名称
			String materialName = jsonObject.getString("materialName");
			// 单据数量
			Integer unitQuantity = jsonObject.getInteger("unitQuantity");
			// 包装数量
			Integer number = jsonObject.getInteger("numberOfPackages");

			if (packageId == null || packageId.equals("")) {
				if (code == null || code.equals("")) {
					throw new Exception(materialName + " 无入库条码");
				}
			}

			if (unitQuantity == null || unitQuantity == 0) {
				if (number == null || number == 0) {
					throw new Exception("包装ID：" + packageId + ",单件码：" + code + "  未录入数量");
				}
			}

			JSONObject stock = wmsStockService.findStockByUnitCodeAndPackingId(code, packageId);
			if (stock != null) {
				throw new Exception("包装ID：" + packageId + ",单件码：" + code + "  库存已不存在");
			}

			if (code != null && !code.equals("")) {
				number = unitQuantity;
			}
			String batchNumber = "";
			try {
				batchNumber = codeRuleService.getLatestCode("wMSSendCommodityBatchCode");
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("生成单号时出错了");
			}

			if (batchNumber == null) {
				throw new Exception("生成条码时返回空");
			}

			JSONObject wmsStock = new JSONObject();
			// 工厂Id
			wmsStock.put("factoryId", factoryId);
			// 包装ID
			wmsStock.put("packageId", packageId);
			// 单件码
			wmsStock.put("unitCode", code);
			// 物料号
			wmsStock.put("ItemNo", jsonObject.getString("materialNo"));
			// 批次号
			wmsStock.put("batchNumber", batchNumber);// 自己生成
			// 库位id
			wmsStock.put("locationId", jsonObject.getString("locationId"));
			// 货架号
			wmsStock.put("shelfNumber", null);
			// 待检数
			wmsStock.put("toBeInspectedNumber", 0);
			// 质量冻结数
			wmsStock.put("frozenNumber", 0);
			// 拣货锁定数
			wmsStock.put("pickingLockNumber", 0);
			// 出货锁定数
			wmsStock.put("shipmentLockingNumber", 0);
			// 单位
			wmsStock.put("unit", jsonObject.getString("unit"));
			// 供应商批次号
			wmsStock.put("supplierBatchNumber", jsonObject.getString("supplierBatchCode"));
			// 生产日期
			wmsStock.put("dateOfManufacture", jsonObject.getString("productionDt"));
			// 有效截止日期
			wmsStock.put("expiryDate", jsonObject.getString("termOfValidityDt"));
			// 物料id
			wmsStock.put("materialId", jsonObject.getString("materialId"));

			JSONObject wmsInventoryTransaction = new JSONObject();
			// 工厂ID
			wmsInventoryTransaction.put("factoryId", factoryId);
			// 单据
			wmsInventoryTransaction.put("listNo", listNo);
			// 项目ID
			wmsInventoryTransaction.put("projectId", null);
			// 包装ID
			wmsInventoryTransaction.put("packageId", packageId);
			// 单件码
			wmsInventoryTransaction.put("unitCode", code);
			// 物料号
			wmsInventoryTransaction.put("itemNo", jsonObject.getString("materialNo"));
			// 批次号
			wmsInventoryTransaction.put("batchNumber", batchNumber);// 自己生成
			// 数量
			wmsInventoryTransaction.put("number", jsonObject.getString("unitQuantity"));
			// 出入库标记
			wmsInventoryTransaction.put("sign", "入库");
			// 交易类型
			wmsInventoryTransaction.put("transactionType", 010);
			// 操作员
			wmsInventoryTransaction.put("userId", userId);
			// 交易代码
			wmsInventoryTransaction.put("transactionCode", "来料入库");
			// 发出库位
			wmsInventoryTransaction.put("outLocationId", null);
			// 接收库位
			wmsInventoryTransaction.put("intLocationId", jsonObject.getString("locationId"));

			// 通过物料id查询物料
			JSONObject material = otherWmsMaterialService.findMaterialById(materialId);
			if (material == null) {
				throw new Exception(materialName + " 物料不存在");
			}
			if (material.getString("wQIS") == null) {
				throw new Exception(materialName + " 未配置入库策略");
			}
			if (material.getString("wQIS").equals("先检后入")) {
				if (jsonObject.getString("InspectionMethod") == null) {

					throw new Exception(materialName + " 未配置检验方式");
				}

				if (jsonObject.getString("InspectionMethod").equals("全检")) {

					// 单件数量==校验数量
					if (!number.toString().equals(jsonObject.getString("checkNumber"))) {
						throw new Exception("请先联系检验人员完成检验才能入库");
					}
				} else {
					// 校验数量=0
					if (jsonObject.getInteger("checkNumber") == 0) {
						throw new Exception("请先联系检验人员完成检验才能入库");
					}
				}
				
				if(HJson.getString("checkUserId")==null || HJson.getString("checkUserId").equals("")) {
					throw new Exception("请在质检页面点击质检完成");
				}

				if(code!=null && !code.equals("")) {
					// 数量
					wmsStock.put("number", jsonObject.getString("qualifiedNumber"));
					// 待检数
					wmsStock.put("toBeInspectedNumber", jsonObject.getInteger("unitQuantity")-jsonObject.getInteger("checkNumber"));
				}else {
					// 包装数
					wmsStock.put("numberOfPackages", jsonObject.getString("qualifiedNumber"));
					// 待检数
					wmsStock.put("toBeInspectedNumber", jsonObject.getInteger("numberOfPackages")-jsonObject.getInteger("checkNumber"));
				}
			} else if (material.getString("wQIS").equals("先入后检")) {
				// 数量
				wmsStock.put("number", 0);
				// 包装数
				wmsStock.put("numberOfPackages", 0);
				// 待检数
				wmsStock.put("toBeInspectedNumber", number);

				// 数量
				wmsInventoryTransaction.put("number", number);
			} else {
				throw new Exception("入库策略有误");
			}

			// 新增库存表
			wmsStockService.add(wmsStock);

			// 新增库存交易表记录
			wmsInventoryTransactionService.add(wmsInventoryTransaction);
		}

		HJson.put("status", 5);
		JSONObject user = dao.findUserById(userId);
		String name = user.getString("userName");
		if (user.getString("fullName") != null && !user.getString("fullName").equals("")) {
			name = name + "（" + user.getString("fullName") + "）";
		}
		HJson.put("consigneeName", name);
		HJson.put("consignee", userId);
		// 更新
		dao.update(HJson);

	}

	/**
	 * 拒账
	 *
	 * @param listNo
	 * @param userId
	 */
	@Override
	public void juZhang(String listNo, Integer userId) throws Exception {
		JSONObject h = new JSONObject();
		h.put("listNo", listNo);
		h.put("status", 6);
		if (dao.updateStatus(h) != 1) {
			throw new Exception("操作失败");
		}

	}

	/**
	 * 查询R表跟D表
	 * 
	 * @param listNo
	 * @return
	 */
	@Override
	public List<JSONObject> findRAndD(String listNo) {
		return dao.findRAndD(listNo);
	}

}
