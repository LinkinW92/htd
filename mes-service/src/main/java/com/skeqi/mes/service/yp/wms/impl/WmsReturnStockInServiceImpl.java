package com.skeqi.mes.service.yp.wms.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.wms.WmsReturnStockInDao;
import com.skeqi.mes.service.wf.baseMode.codeRule.CodeRuleService;
import com.skeqi.mes.service.yp.wms.WmsInventoryTransactionService;
import com.skeqi.mes.service.yp.wms.WmsReturnStockInService;
import com.skeqi.mes.service.yp.wms.WmsStockService;

/**
 * @author yinp
 * @explain 退料入库
 * @date 2021-07-26
 */
@Service
public class WmsReturnStockInServiceImpl implements WmsReturnStockInService {

	@Autowired
	WmsReturnStockInDao dao;

	@Autowired
	WmsStockService wmsStockService;

	@Autowired
	CodeRuleService codeRuleService;

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
	 * 更新
	 * 
	 * @param json
	 * @return
	 */
	@Override
	public void update(JSONObject json) {
		dao.update(json);
	}

	/**
	 * 查询行
	 *
	 * @param json
	 * @return
	 */
	@Override
	public List<JSONObject> listR(JSONObject json) {
		return dao.listR(json);
	}

	/**
	 * 查询详情
	 *
	 * @param json
	 * @return
	 */
	@Override
	public List<JSONObject> listD(JSONObject json) {
		return dao.listD(json);
	}

	/**
	 * 过账
	 *
	 * @param json
	 * @throws Exception
	 */
	@Override
	public void guoZhang(JSONObject json) throws Exception {
		// 单号
		String listNo = json.getString("listNo");
		// 操作用户
		Integer userId = json.getInteger("userId");

		// 查询需要过账的信息
		List<JSONObject> postingInformationList = dao.queryPostingInformation(listNo);
		if (postingInformationList == null || postingInformationList.size() == 0) {
			throw new Exception("无过账数据");
		}
		for (JSONObject postingInformation : postingInformationList) {
			// 收货工厂
			Integer receivingGoodsFactoryId = postingInformation.getInteger("receivingGoodsFactoryId");
			// 收货库位
			Integer receivingGoodsLocationId = postingInformation.getInteger("receivingGoodsLocationId");
			// 物料条码
			String materialSn = postingInformation.getString("materialSn");
			// 物料id
			Integer materialId = postingInformation.getInteger("materialId");
			// 物料编号
			String materialNo = postingInformation.getString("materialNo");
			// 单位
			String unit = postingInformation.getString("unit");
			// 数量
			Integer quantity = postingInformation.getInteger("quantity");

			// 通过条码查询线边库库存
			JSONObject lslStock = dao.findLslStockByMaterialCode(materialSn);
			if (lslStock == null) {
				throw new Exception(materialSn + "条码不存在");
			}
			
			//批次码
			String batchCode = lslStock.getString("batchCode");
			//单件码
			String materialCode = lslStock.getString("materialCode");
			
			if (quantity > lslStock.getInteger("quantity")) {
				throw new Exception("包装ID：" + batchCode + " - 单件码：" + materialCode + " 线边库的库存数量低于本次需求的数量");
			}
			
			// 更新线边库库存
			Integer updateNumber = lslStock.getInteger("quantity") - quantity;
			lslStock.put("quantity", updateNumber);
			if (dao.updateLslStock(lslStock) != 1) {
				throw new Exception("更新线边库库存是出错了");
			}

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
			JSONObject stock = wmsStockService.findStockByUnitCodeAndPackingId(materialCode, batchCode);
			if (stock == null) {

				JSONObject addStockDx = new JSONObject();
				// 工厂Id
				addStockDx.put("factoryId", receivingGoodsFactoryId);
				// 包装ID
				addStockDx.put("packageId", batchCode);
				// 单件码
				addStockDx.put("unitCode", materialCode);
				// 物料号
				addStockDx.put("ItemNo", materialNo);
				// 批次号
				addStockDx.put("batchNumber", batchNumber);// 自己生成
				if(materialCode!=null && !materialCode.equals("")) {
					// 数量
					addStockDx.put("number", quantity);
					// 包装数
					addStockDx.put("numberOfPackages", 0);
				}else {
					addStockDx.put("number", 0);
					// 包装数
					addStockDx.put("numberOfPackages", quantity);
				}
				// 库位id
				addStockDx.put("locationId", receivingGoodsLocationId);
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
				// 物料id
				addStockDx.put("materialId", materialId);

				wmsStockService.add(addStockDx);

			} else {
				// 更新库存
				JSONObject updateStockDx = new JSONObject();
				// id
				updateStockDx.put("id", stock.getString("id"));
				// 数量
				updateStockDx.put("number", quantity + stock.getInteger("number"));
				wmsStockService.update(updateStockDx);
			}

			// 新增库存交易表
			JSONObject inventoryTransaction = new JSONObject();
			// 工厂ID
			inventoryTransaction.put("factoryId", receivingGoodsFactoryId);
			// 单据
			inventoryTransaction.put("listNo", listNo);
			// 项目ID
			inventoryTransaction.put("projectId", null);
			// 包装ID
			inventoryTransaction.put("packageId", null);
			// 单件码
			inventoryTransaction.put("unitCode", materialSn);
			// 物料号
			inventoryTransaction.put("itemNo", materialNo);
			// 批次号
			inventoryTransaction.put("batchNumber", batchNumber);// 自己生成
			// 数量
			inventoryTransaction.put("number", quantity);
			// 出入库标记
			inventoryTransaction.put("sign", "入库");
			// 交易类型
			inventoryTransaction.put("transactionType", 020);
			// 操作员
			inventoryTransaction.put("userId", userId);
			// 交易代码
			inventoryTransaction.put("transactionCode", "来料入库");
			// 发出库位
			inventoryTransaction.put("outLocationId", null);
			// 接收库位
			inventoryTransaction.put("intLocationId", receivingGoodsLocationId);
			wmsInventoryTransactionService.add(inventoryTransaction);

		}

		// 更新状态
		dao.updateState(listNo, 1);

		JSONObject h = new JSONObject();
		h.put("listNo", listNo);
		List<JSONObject> hList = dao.list(h);
		h = hList.get(0);
		h.put("receivingGoodsUserId", userId);
		h.put("receivingGoodsDt", true);
		dao.update(h);

	}

	/**
	 * 拒帐
	 *
	 * @param json
	 * @throws Exception
	 */
	@Override
	public void juZhang(JSONObject json) throws Exception {
		// 更新状态
		dao.updateState(json.getString("listNo"), 2);
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
