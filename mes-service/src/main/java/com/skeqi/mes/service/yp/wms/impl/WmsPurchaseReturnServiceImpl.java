package com.skeqi.mes.service.yp.wms.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.wms.WmsPurchaseReturnDao;
import com.skeqi.mes.service.wf.baseMode.codeRule.CodeRuleService;
import com.skeqi.mes.service.yp.wms.WmsInventoryTransactionService;
import com.skeqi.mes.service.yp.wms.WmsPurchaseReturnService;
import com.skeqi.mes.service.yp.wms.WmsStockService;

/**
 * @author yinp
 * @explain 采购退货
 * @date 2021-08-24
 */
@Service
public class WmsPurchaseReturnServiceImpl implements WmsPurchaseReturnService {
	
	@Autowired
	WmsPurchaseReturnDao dao;

	@Autowired
	CodeRuleService codeRuleService;

	@Autowired
	WmsStockService wmsStockService;

	@Autowired
	WmsInventoryTransactionService wmsInventoryTransactionService;

	/**
	 * 查询
	 * 
	 * @param json
	 * @return
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
	 */
	@Override
	public void add(JSONObject json) throws Exception {
		String orderNumber = json.getString("orderNumber");
		String listNo = "";
		try {
			listNo = codeRuleService.getLatestCode("expenseWarehousingListNo");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("生成单号时出错了");
		}

		if (listNo == null) {
			throw new Exception("生成单号返回空");
		}
		json.put("listNo", listNo);
		json.put("viewMode", 1);

		if (dao.add(json) != 1) {
			throw new Exception("新增失败");
		}

		// 查询销售订单R
		List<JSONObject> salesOrderRList = dao.findPurchaseOrderR(orderNumber);
		for (JSONObject salesOrderR : salesOrderRList) {
			JSONObject r = new JSONObject();
			r.put("listNo", listNo);
			r.put("materialNo", salesOrderR.getString("materialNo"));
			r.put("materialName", salesOrderR.getString("materialName"));
			r.put("demandQuantity", salesOrderR.getString("count"));
			r.put("issuedQuantity", 0);
			r.put("unit", salesOrderR.getString("unit"));
			dao.addR(r);
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
	 * @param json
	 * @return
	 */
	@Override
	public void delete(String listNo) throws Exception {
		JSONObject json = new JSONObject();
		json.put("listNo", listNo);
		json.put("viewMode", "0");
		if (dao.update(json) != 1) {
			throw new Exception("删除失败");
		}
	}

	/**
	 * 查询采购订单
	 * 
	 * @param listNo
	 * @return
	 */
	@Override
	public List<JSONObject> findPurchaseOrderH(String orderNumber) {
		return dao.findPurchaseOrderH(orderNumber);
	}

	/**
	 * 查询R跟D表
	 * 
	 * @param listNo
	 * @return
	 */
	@Override
	public List<JSONObject> findRAndD(String listNo) {
		String orderNumber = "";
		List<JSONObject> list = dao.findRAndD(listNo);
		if (list != null && list.size() > 0) {
			orderNumber = list.get(0).getString("orderNumber");
		}
		if (!orderNumber.equals("")) {
			List<JSONObject> a = dao.queryTheIssuedQuantityOfDocuments(orderNumber);
			for (int i = 0; i < list.size(); i++) {
				j: for (int j = 0; j < a.size(); j++) {
					if (list.get(i).getString("materialNo").equals(a.get(j).getString("materialNo"))) {
						list.get(i).put("alreadyNumber", a.get(j).getInteger("alreadyNumber"));
						break j;
					}
				}
			}

		}

		return list;
	}

	/**
	 * 新增D
	 * 
	 * @param json
	 * @throws Exception
	 */
	@Override
	public void addD(JSONObject json) throws Exception {
		String listNo = json.getString("listNo");
		String packingId = json.getString("packingId");
		String code = json.getString("code");
		Integer rId = json.getInteger("rId");

		JSONObject h = new JSONObject();
		h.put("listNo", listNo);
		List<JSONObject> hList = dao.list(h);
		if (hList == null || hList.size() == 0) {
			throw new Exception("单据不存在");
		}
		if (hList.size() > 1) {
			throw new Exception("单号存在重复");
		}
		h = hList.get(0);

		// 工厂
		String factoryId = h.getString("factoryId");
		// 库位
		String locationId = h.getString("locationId");

		// 查询库存
		JSONObject stock = wmsStockService.findStockByUnitCodeAndPackingId(code, packingId);
		if (stock == null || stock.size() == 0) {
			throw new Exception("包装ID：" + packingId + " - 单件码：" + code + " 库存不存在");
		}

		if (!stock.getString("factoryId").equals(factoryId) || !stock.getString("locationId").equals(locationId)) {
			throw new Exception("包装ID：" + packingId + " - 单件码：" + code + " 库存不存在");
		}

		if (code != null && !code.equals("")) {
			if (stock.getInteger("number") == 0) {
				throw new Exception("包装ID：" + packingId + " - 单件码：" + code + " 库存不足");
			}
		} else {
			if (stock.getInteger("numberOfPackages") == 0) {
				throw new Exception("包装ID：" + packingId + " - 单件码：" + code + " 库存不足");
			}
		}

		JSONObject r = new JSONObject();
		r.put("id", rId);
		List<JSONObject> rList = dao.listR(r);
		if (rList == null || rList.size() == 0) {
			throw new Exception("行ID不存在");
		}
		r = rList.get(0);

		if (!r.getString("materialNo").equals(stock.getString("materialNo"))) {
			throw new Exception("包装ID：" + packingId + " - 单件码：" + code + " 物料不一致");
		}

		// 判断条码是否已出库
		JSONObject d = new JSONObject();
		d.put("packingId", packingId);
		d.put("code", code);
		d.put("rId", rId);
		List<JSONObject> dList = dao.listD(d);
		if (dList != null && dList.size() != 0) {
			throw new Exception("包装ID：" + packingId + " - 单件码：" + code + " 已出库");
		}

		// 新增详情表记录
		d.put("packingNumber", stock.getInteger("numberOfPackages"));
		d.put("number", stock.getInteger("number"));
		dao.addD(d);

		d = new JSONObject();
		d.put("rId", rId);
		dList = dao.listD(d);
		Integer num = 0;
		for (int i = 0; i < dList.size(); i++) {
			if (dList.get(i).getString("code") != null && !dList.get(i).getString("code").equals("")) {
				num = num + dList.get(i).getInteger("number");
			} else {
				num = num + dList.get(i).getInteger("packingNumber");
			}
		}

		// 更新行表
		r = new JSONObject();
		r.put("id", rId);
		r.put("issuedQuantity", num);
		dao.updateR(r);

	}

	/**
	 * 删除D
	 * 
	 * @param id
	 * @throws Exception
	 */
	@Override
	public void deleteD(Integer id) throws Exception {
		JSONObject d = new JSONObject();
		d.put("id", id);
		List<JSONObject> dList = dao.listD(d);
		if (dList == null || dList.size() == 0) {
			throw new Exception("数据不存在");
		}
		d = dList.get(0);

		Integer rId = d.getInteger("rId");

		// 删除详情表
		dao.deleteD(id);

		d = new JSONObject();
		d.put("rId", rId);
		dList = dao.listD(d);
		Integer num = 0;
		for (int i = 0; i < dList.size(); i++) {
			if (dList.get(i).getString("code") != null && !dList.get(i).getString("code").equals("")) {
				num = num + dList.get(i).getInteger("number");
			} else {
				num = num + dList.get(i).getInteger("packingNumber");
			}
		}

		// 更新行表
		JSONObject r = new JSONObject();
		r.put("id", rId);
		r.put("issuedQuantity", num);
		dao.updateR(r);

	}

	/**
	 * 删除R
	 * 
	 * @param id
	 * @throws Exception
	 */
	@Override
	public void deleteR(Integer id) throws Exception {
		dao.deleteDByRId(id);
		dao.deleteR(id);
	}

	/**
	 * 过账
	 * 
	 * @param json
	 * @throws Exception
	 */
	@Override
	public void guoZhang(JSONObject json) throws Exception {
		String listNo = json.getString("listNo");
		Integer userId = json.getInteger("userId");

		// 查询过账数据
		List<JSONObject> postingDataList = dao.queryPostingData(listNo);
		for (JSONObject postingData : postingDataList) {
			// 工厂
			String factoryId = postingData.getString("factoryId");
			// 库位
			String locationId = postingData.getString("locationId");
			// 订单号
			String orderNumber = postingData.getString("orderNumber");
			// 物料id
			String materialId = postingData.getString("materialId");
			// 物料编号
			String materialNo = postingData.getString("materialNo");
			// 物料名称
			String materialName = postingData.getString("materialName");
			// 物料单位
			String unit = postingData.getString("unit");
			// 包装ID
			String packingId = postingData.getString("packingId");
			// 单件码
			String code = postingData.getString("code");
			// 包装数量
			Integer packingNumber = postingData.getInteger("packingNumber");
			// 单件数量
			Integer number = postingData.getInteger("number");

			if (code == null || code.equals("")) {
				if (packingId == null || packingId.equals("")) {
					throw new Exception(materialName + " 无出库物料，请核对");
				}
			}

			// 查询库存
			JSONObject stock = wmsStockService.findStockByUnitCodeAndPackingId(code, packingId);

			if (stock == null || stock.size() == 0) {
				throw new Exception("包装ID：" + packingId + " - 单件码：" + code + " 库存不存在");
			}

			// 判断工厂跟库位是否一致
			if (!stock.getString("factoryId").equals(factoryId) || !stock.getString("locationId").equals(locationId)) {
				throw new Exception("包装ID：" + packingId + " - 单件码：" + code + " 库存不存在");
			}

			// 判断库存是否充足
			if (code != null && !code.equals("")) {
				if (stock.getInteger("number") <= 0) {
					throw new Exception("包装ID：" + packingId + " - 单件码：" + code + " 库存不足");
				}
				if (!stock.getString("number").equals(number.toString())) {
					throw new Exception("包装ID：" + packingId + " - 单件码：" + code + " 出库的数量与库存数量不符");
				}
				stock.put("number", 0);
			} else {
				if (stock.getInteger("numberOfPackages") <= 0) {
					throw new Exception("包装ID：" + packingId + " - 单件码：" + code + " 库存不足");
				}
				if (!stock.getString("numberOfPackages").equals(packingNumber.toString())) {
					throw new Exception("包装ID：" + packingId + " - 单件码：" + code + " 出库的数量与库存数量不符");
				}
				stock.put("numberOfPackages", 0);
			}
			// 更新库存
			wmsStockService.update(stock);

			// 新增库存交易表
			JSONObject inventoryTransaction = new JSONObject();
			// 工厂ID
			inventoryTransaction.put("factoryId", factoryId);
			// 单据
			inventoryTransaction.put("listNo", listNo);
			// 项目ID
			inventoryTransaction.put("projectId", null);
			// 包装ID
			inventoryTransaction.put("packageId", packingId);
			// 单件码
			inventoryTransaction.put("unitCode", code);
			// 物料号
			inventoryTransaction.put("itemNo", materialNo);
			// 批次号
			inventoryTransaction.put("batchNumber", stock.getString("batchNumber"));// 自己生成
			// 数量
			inventoryTransaction.put("number", packingNumber);
			// 出入库标记
			inventoryTransaction.put("sign", "出库");
			// 交易类型
			inventoryTransaction.put("transactionType", "011");
			// 操作员
			inventoryTransaction.put("userId", userId);
			// 交易代码
			inventoryTransaction.put("transactionCode", "采购退货");
			// 发出库位
			inventoryTransaction.put("outLocationId", stock.getString("locationId"));
			// 接收库位
			inventoryTransaction.put("intLocationId", null);
			// 物料id
			inventoryTransaction.put("materialId", materialId);
			if (code != null && !code.equals("")) {
				// 数量
				inventoryTransaction.put("numberOfPackages", number);
			}
			wmsInventoryTransactionService.add(inventoryTransaction);
		}

		JSONObject h = new JSONObject();
		h.put("listNo", listNo);
		h.put("status", "过账");
		h.put("handleUserId", userId);
		h.put("handleDt", true);
		dao.update(h);
	}

}
