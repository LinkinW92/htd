package com.skeqi.mes.service.yp.wms.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.wms.WmsSalesReturnReceiptDao;
import com.skeqi.mes.service.wf.baseMode.codeRule.CodeRuleService;
import com.skeqi.mes.service.yp.wms.WmsInventoryTransactionService;
import com.skeqi.mes.service.yp.wms.WmsSalesReturnReceiptService;
import com.skeqi.mes.service.yp.wms.WmsStockService;

/**
 * 销售退货入库单
 *
 * @author yinp
 * @date 2021年7月27日
 */
@Service
public class WmsSalesReturnReceiptServiceImpl implements WmsSalesReturnReceiptService {

	@Autowired
	WmsSalesReturnReceiptDao dao;

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
	 * @throws Exception
	 */
	@Override
	public void add(JSONObject json) throws Exception {
		String listNo = "";
		try {
			listNo = codeRuleService.getLatestCode("salesReturnReceiptKListNo");
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
		json.put("viewMode", 0);

		if (dao.update(json) != 1) {
			throw new Exception("删除失败");
		}

	}

	/**
	 * 查询R
	 *
	 * @param json
	 * @return
	 */
	@Override
	public List<JSONObject> listR(JSONObject json) {
		return dao.listR(json);
	}

	/**
	 * 新增R
	 *
	 * @param json
	 * @return
	 */
	@Override
	public void addR(JSONObject json) throws Exception {
		dao.addR(json);
	}

	/**
	 * 更新R
	 *
	 * @param json
	 * @return
	 */
	@Override
	public void updateR(JSONObject json) throws Exception {
		dao.updateR(json);
	}

	/**
	 * 删除R
	 *
	 * @param id
	 * @return
	 */
	@Override
	public void deleteR(Integer id) throws Exception {
		if (dao.deleteR(id) != 1) {
			throw new Exception("删除失败");
		}
		// 通过行id删除D
		dao.deleteDByRId(id);
	}

	/**
	 * 查询D
	 *
	 * @param json
	 * @return
	 */
	@Override
	public List<JSONObject> listD(JSONObject json) {
		return dao.listD(json);
	}

	/**
	 * 新增D
	 *
	 * @param json
	 * @return
	 */
	@Override
	public void addD(JSONObject json) throws Exception {
		// 单据号
		String listNo = json.getString("listNo");
		// 包装id
		String packingId = json.getString("packingId");
		// 单件码
		String code = json.getString("code");

		// 查询单据
		JSONObject hDx = new JSONObject();
		hDx.put("listNo", listNo);
		List<JSONObject> hList = dao.list(hDx);
		if (hList == null || hList.size() == 0) {
			throw new Exception("单据不存在");
		} else if (hList.size() > 1) {
			throw new Exception("单据号存在重复");
		}
		// 找到单据信息
		hDx = hList.get(0);
		// 销售退货单号
		String salesReturnListNo = hDx.getString("salesReturnListNo");

		// 查询本次扫描的条码是否已经扫描过了
		JSONObject dDx = new JSONObject();
		dDx.put("listNo", listNo);
		dDx.put("packingId", packingId);
		dDx.put("code", code);
		List<JSONObject> dDxList = dao.listD(dDx);
		if (dDxList != null && dDxList.size() > 0) {
			throw new Exception("条码已录入");
		}

		// 通过订单号查询销售订单
		List<JSONObject> salesOrderList = dao.findSalesOrderByOrderNumber(salesReturnListNo);

		// 通过单件码或者包装id查询最进一次库存交易记录
		JSONObject inventory = dao.queryInventoryTransactions(packingId, code);
		if (inventory == null) {
			throw new Exception("条码不存在");
		}
		// 物料编号
		String materialNo = inventory.getString("materialNo");

		// 判断扫描到的物料是否存在本次退货的退货单里
		for (int i = 0; i < salesOrderList.size(); i++) {
			if (salesOrderList.get(i).getString("materialNo").equals(materialNo)) {
				// 存在
				// 查询过账数量
				Integer number = dao.queryPostingQuantity(salesReturnListNo, materialNo);
				if (number >= salesOrderList.get(i).getInteger("quantity")) {
					throw new Exception("过账物料数量超出");
				}
				break;
			}
			if (i == salesOrderList.size() - 1) {
				// 不存在
				throw new Exception("扫描到的物料不存在退货单里");
			}
		}

		// 查询r表
		JSONObject rDx = new JSONObject();
		rDx.put("materialNo", materialNo);
		rDx.put("listNo", listNo);
		List<JSONObject> rDxList = dao.listR(rDx);
		if (rDxList == null || rDxList.size() == 0) {
			// 新增
			rDx.put("number", 0);
			rDx.put("unit", inventory.getString("unit"));
			dao.addR(rDx);
		} else {
			rDx = rDxList.get(0);
		}
		// 行id
		Integer rId = rDx.getInteger("id");

		// 新增明细
		json.put("rId", rId);
		json.put("packingNumber", 0);
		json.put("number", 0);
		if (dao.addD(json) != 1) {
			throw new Exception("新增失败");
		}

		JSONObject d = new JSONObject();
		d.put("rId", rId);
		List<JSONObject> dList = dao.listD(d);
		int num = 0;
		for (int i = 0; i < dList.size(); i++) {
			String code1 = dList.get(i).getString("code");
			if (code1 != null && !code1.equals("")) {
				num = num + dList.get(i).getInteger("number");
			} else {
				num = num + dList.get(i).getInteger("packingNumber");
			}
		}
		rDx = new JSONObject();
		rDx.put("id", rId);
		rDx.put("number", num);
		dao.updateR(rDx);

	}

	/**
	 * 更新D
	 *
	 * @param json
	 * @return
	 */
	@Override
	public void updateD(JSONObject json) throws Exception {
		if (dao.updateD(json) != 1) {
			throw new Exception("更新失败");
		}

		Integer id = json.getInteger("id");
		JSONObject d = new JSONObject();
		d.put("id", id);
		List<JSONObject> dList = dao.listD(d);
		if (dList == null || dList.size() == 0) {
			throw new Exception("操作的数据不存在");
		}
		d = dList.get(0);
		Integer rId = d.getInteger("rId");

		d = new JSONObject();
		d.put("rId", rId);
		dList = dao.listD(d);
		int num = 0;
		for (int i = 0; i < dList.size(); i++) {
			String code1 = dList.get(i).getString("code");
			if (code1 != null && !code1.equals("")) {
				num = num + dList.get(i).getInteger("number");
			} else {
				num = num + dList.get(i).getInteger("packingNumber");
			}
		}
		JSONObject r = new JSONObject();
		r.put("id", rId);
		r.put("number", num);
		dao.updateR(r);

	}

	/**
	 * 删除D
	 *
	 * @param id
	 * @return
	 */
	@Override
	public void deleteD(JSONObject json) throws Exception {
		// 详情表id
		Integer id = json.getInteger("id");

		JSONObject d = new JSONObject();
		d.put("id", id);
		List<JSONObject> dList = dao.listD(d);
		if (dList == null || dList.size() == 0) {
			throw new Exception("操作的数据不存在");
		}
		d = dList.get(0);
		Integer rId = d.getInteger("rId");

		// 删除D表
		if (dao.deleteD(id) != 1) {
			throw new Exception("删除失败");
		}

		d = new JSONObject();
		d.put("rId", rId);
		dList = dao.listD(d);
		int num = 0;
		for (int i = 0; i < dList.size(); i++) {
			String code1 = dList.get(i).getString("code");
			if (code1 != null && !code1.equals("")) {
				num = num + dList.get(i).getInteger("number");
			} else {
				num = num + dList.get(i).getInteger("packingNumber");
			}
		}
		JSONObject r = new JSONObject();
		r.put("id", rId);
		r.put("number", num);
		dao.updateR(r);

	}

	/**
	 * 查询销售退货单
	 *
	 * @param json
	 * @return
	 */
	@Override
	public List<JSONObject> findSalesReturn(JSONObject json) {
		return dao.findSalesReturn(json);
	}

	/**
	 * 过账
	 *
	 * @param json
	 */
	@Override
	public void guoZhang(JSONObject json) throws Exception {
		String listNo = json.getString("listNo");
		Integer userId = json.getInteger("userId");

		// 查询需要过账的详细信息
		List<JSONObject> theDetailsToBePostedList = dao.queryTheDetailsToBePosted(listNo);
		if (theDetailsToBePostedList == null || theDetailsToBePostedList.size() == 0) {
			throw new Exception("未查询到需要过账的信息");
		}

		// 销售退货单号
		String salesReturnListNo = theDetailsToBePostedList.get(0).getString("salesReturnListNo");

		// 通过订单号查询销售订单
		List<JSONObject> salesOrderList = dao.findSalesOrderByOrderNumber(salesReturnListNo);

		for (JSONObject theDetailsToBePosted : theDetailsToBePostedList) {
			// 收货工厂id
			Integer receivingFactoryId = theDetailsToBePosted.getInteger("receivingFactoryId");
			// 收货库位id
			Integer receivingLocationId = theDetailsToBePosted.getInteger("receivingLocationId");
			// 物料id
			Integer materialId = theDetailsToBePosted.getInteger("materialId");
			// 物料编号
			String materialNo = theDetailsToBePosted.getString("materialNo");
			// 单位
			String unit = theDetailsToBePosted.getString("unit");
			// 包装Id
			String packingId = theDetailsToBePosted.getString("packingId");
			// 单件码
			String code = theDetailsToBePosted.getString("code");

			// 本次过账数量
			Integer packingNumber = theDetailsToBePosted.getInteger("packingNumber");
			Integer number = theDetailsToBePosted.getInteger("number");
			
			if(packingNumber==0) {
				if(number==0) {
					throw new Exception("包装ID：" + packingId + " - 单件码：" + code + " 请先编辑数量");
				}
			}

			// 查询过账数量
			Integer num = dao.queryPostingQuantity(listNo, materialNo);
			// 订单数量
			Integer orderNum = 0;
			// 找到对应物料
			for (int i = 0; i < salesOrderList.size(); i++) {
				if (salesOrderList.get(i).getString("materialNo").equals(materialNo)) {
					orderNum = salesOrderList.get(i).getInteger("quantity");
					break;
				}
				if (i == salesOrderList.size() - 1) {
					throw new Exception("包装ID：" + packingId + " - 单件码：" + code + "销售退货单内未找到  " + theDetailsToBePosted.getString("materialName"));
				}
			}

			// 判断数量是否够
//			if (code != null && !code.equals("")) {
//				if (number + num > orderNum) {
//					throw new Exception("包装ID：" + packingId + " - 单件码：" + code + "过账数量超出退货数量  " + theDetailsToBePosted.getString("materialName"));
//				}
//			} else {
//				if (packingNumber + num > orderNum) {
//					throw new Exception("包装ID：" + packingId + " - 单件码：" + code + "过账数量超出退货数量  " + theDetailsToBePosted.getString("materialName"));
//				}
//			}

			// 新增库存
			String batchNumber = "";
			try {
				batchNumber = codeRuleService.getLatestCode("wmsProductionWarehousingBatchCode");
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("生成批次号时出错了");
			}

			if (batchNumber == null) {
				throw new Exception("生成批次号返回空");
			}

			// 通过单件码跟包装id查询库存
			JSONObject stock = wmsStockService.findStockByUnitCodeAndPackingId(code, packingId);
			if (stock == null) {

				JSONObject addStockDx = new JSONObject();
				// 工厂Id
				addStockDx.put("factoryId", receivingFactoryId);
				// 包装ID
				addStockDx.put("packageId", packingId);
				// 单件码
				addStockDx.put("unitCode", code);
				// 物料号
				addStockDx.put("ItemNo", materialNo);
				// 批次号
				addStockDx.put("batchNumber", batchNumber);// 自己生成
				if (code != null && !code.equals("")) {
					// 数量
					addStockDx.put("number", number);
					addStockDx.put("numberOfPackages", 0);
				} else {
					// 数量
					addStockDx.put("number", 0);
					addStockDx.put("numberOfPackages", packingNumber);
				}
				// 库位id
				addStockDx.put("locationId", receivingLocationId);
				// 货架号
				addStockDx.put("shelfNumber", null);
				// 待检数
				addStockDx.put("toBeInspectedNumber", 0);
				// 质量冻结数
				addStockDx.put("frozenNumber", 0);
				// 拣货锁定数
				addStockDx.put("pickingLockNumber", 0);
				// 出货锁定数
				addStockDx.put("shipmentLockingNumber", 0);
				// 单位
				addStockDx.put("unit", unit);
				// 供应商批次号
				addStockDx.put("supplierBatchNumber", null);
				// 生产日期
				addStockDx.put("dateOfManufacture", null);
				// 有效截止日期
				addStockDx.put("expiryDate", null);

				wmsStockService.add(addStockDx);

			} else {
				// 更新库存
				JSONObject updateStockDx = new JSONObject();
				// 工厂Id
				updateStockDx.put("factoryId", receivingFactoryId);
				// 库位id
				updateStockDx.put("locationId", receivingLocationId);
				// id
				updateStockDx.put("id", stock.getString("id"));
				if (code != null && !code.equals("")) {
					// 数量
					updateStockDx.put("number", number + stock.getInteger("number"));
				} else {
					// 数量
					updateStockDx.put("numberOfPackages", packingNumber + stock.getInteger("numberOfPackages"));
				}
				wmsStockService.update(updateStockDx);
			}

			// 新增库存交易表
			JSONObject inventoryTransaction = new JSONObject();
			// 工厂ID
			inventoryTransaction.put("factoryId", receivingFactoryId);
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
			inventoryTransaction.put("batchNumber", batchNumber);// 自己生成
			if (code != null && !code.equals("")) {
				// 数量
				inventoryTransaction.put("number", number);
			} else {
				// 数量
				inventoryTransaction.put("number", packingNumber);
			}
			// 出入库标记
			inventoryTransaction.put("sign", "入库");
			// 交易类型
			inventoryTransaction.put("transactionType", "030");
			// 操作员
			inventoryTransaction.put("userId", userId);
			// 交易代码
			inventoryTransaction.put("transactionCode", "销售退货入库");
			// 发出库位
			inventoryTransaction.put("outLocationId", null);
			// 接收库位
			inventoryTransaction.put("intLocationId", receivingLocationId);
			wmsInventoryTransactionService.add(inventoryTransaction);

		}

		// 更新状态
		JSONObject hDx = new JSONObject();
		hDx.put("listNo", listNo);
		hDx.put("status", "过账");
		hDx.put("receivingUserId", userId);
		hDx.put("receivingDt", true);
		if (dao.updateStatus(hDx) != 1) {
			throw new Exception("过账失败");
		}

	}

	/**
	 * 查询R跟D表数据
	 * 
	 * @param listNo
	 * @return
	 */
	@Override
	public List<JSONObject> findRAndD(String listNo) {
		return dao.findRAndD(listNo);
	}

}
