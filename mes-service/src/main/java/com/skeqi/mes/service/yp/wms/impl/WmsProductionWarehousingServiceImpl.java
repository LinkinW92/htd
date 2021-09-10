package com.skeqi.mes.service.yp.wms.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.wms.WmsProductionWarehousingDao;
import com.skeqi.mes.service.wf.baseMode.codeRule.CodeRuleService;
import com.skeqi.mes.service.yp.wms.WmsInventoryTransactionService;
import com.skeqi.mes.service.yp.wms.WmsProductionWarehousingService;
import com.skeqi.mes.service.yp.wms.WmsStockService;

/**
 * @author yinp
 * @explain 生产入库
 * @date 2021-07-20
 */
@Service
public class WmsProductionWarehousingServiceImpl implements WmsProductionWarehousingService {

	@Autowired
	WmsProductionWarehousingDao dao;

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
	 * 更新
	 * @param json
	 * @throws Exception
	 */
	@Override
	public void update(JSONObject json) throws Exception {
		dao.update(json);
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
	 * 过账
	 *
	 * @param listNo
	 * @param id
	 * @throws Exception
	 */
	@Override
	public void guoZhang(String listNo, Integer id, Integer userId) throws Exception {
		// 通过单号查询线边库产品下线明细
		List<JSONObject> dList = dao.findListDByListNo(listNo);

		for (JSONObject dx : dList) {
			String packNo = dx.getString("packNo");
			String materialSn = dx.getString("materialSn");
			String packingId = dx.getString("packNo");
			// 查询线边库库存表
			JSONObject lineSideLibraryStock = dao.findLineSideLibraryStockByBatchCodeAndMaterialCode(packNo,
					materialSn);
			if(lineSideLibraryStock==null) {
				throw new Exception("线边库物料不存在");
			}
			if(!lineSideLibraryStock.getString("quantity").equals(dx.getString("quantity"))) {
				throw new Exception("包装id：" + packingId + ",单件码：" + materialSn +" 线边库数量不一致");
			}
			// 剩余数量
			Integer num = lineSideLibraryStock.getInteger("quantity") - dx.getInteger("quantity");
			lineSideLibraryStock.put("quantity", num);
			// 更新线边库库存表
			dao.updateLineSideLibraryStock(lineSideLibraryStock);

			// 查询库存表
			JSONObject stock = dao.findStockByCode(packNo, materialSn);
			if (stock == null) {

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

				JSONObject wmsStock = new JSONObject();
				// 工厂Id
				wmsStock.put("factoryId", dx.getString("factoryId"));
				// 包装ID
				wmsStock.put("packageId", packNo);
				// 单件码
				wmsStock.put("unitCode", materialSn);
				// 物料号
				wmsStock.put("ItemNo", dx.getString("materialNo"));
				// 批次号
				wmsStock.put("batchNumber", batchNumber);// 自己生成
				if (packNo == null) {
					// 数量
					wmsStock.put("number", dx.getString("quantity"));
				} else {
					// 包装数
					wmsStock.put("numberOfPackages", dx.getString("quantity"));
				}

				// 库位id
				wmsStock.put("locationId", dx.getString("locationId"));
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
				wmsStock.put("unit", dx.getString("unit"));
				// 供应商批次号
				wmsStock.put("supplierBatchNumber", null);
				// 生产日期
				wmsStock.put("dateOfManufacture", dx.getString("cdt"));
				// 有效截止日期
				wmsStock.put("expiryDate", null);
				// 物料id
				wmsStock.put("materialId", dx.getString("materialId"));

				// 新增库存
				wmsStockService.add(wmsStock);
			} else {
				// 更新数量
				JSONObject updateStock = new JSONObject();
				updateStock.put("id", stock.getString("id"));
				if (packNo == null) {
					// 数量
					updateStock.put("number", dx.getInteger("quantity") + stock.getInteger("number"));
				} else {
					// 包装数
					updateStock.put("numberOfPackages",
							dx.getInteger("quantity") + stock.getInteger("numberOfPackages"));
				}
				// 更新库存
				wmsStockService.update(updateStock);
			}

			String batchNumber = "";
			try {
				batchNumber = codeRuleService.getLatestCode("wmsProductionWarehousingBatchCode");
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("生成单号时出错了");
			}

			if (batchNumber == null) {
				throw new Exception("生成条码时返回空");
			}

			JSONObject wmsInventoryTransaction = new JSONObject();
			// 工厂ID
			wmsInventoryTransaction.put("factoryId", dx.getString("factoryId"));
			// 单据
			wmsInventoryTransaction.put("listNo", listNo);
			// 项目ID
			wmsInventoryTransaction.put("projectId", null);
			// 包装ID
			wmsInventoryTransaction.put("packageId", packNo);
			// 单件码
			wmsInventoryTransaction.put("unitCode", materialSn);
			// 物料号
			wmsInventoryTransaction.put("itemNo", dx.getString("materialNo"));
			// 批次号
			wmsInventoryTransaction.put("batchNumber", batchNumber);// 自己生成
			// 数量
			wmsInventoryTransaction.put("number", dx.getString("quantity"));
			// 出入库标记
			wmsInventoryTransaction.put("sign", "入库");
			// 交易类型
			wmsInventoryTransaction.put("transactionType", 020);
			// 操作员
			wmsInventoryTransaction.put("userId", userId);
			// 交易代码
			wmsInventoryTransaction.put("transactionCode", "来料入库");
			// 发出库位
			wmsInventoryTransaction.put("outLocationId", null);
			// 接收库位
			wmsInventoryTransaction.put("intLocationId", dx.getString("locationId"));

			// 新增新增库存交易表
			wmsInventoryTransactionService.add(wmsInventoryTransaction);
		}

		// 更新线边库产品下线单状态
		dao.updateStatus(id, "3");

	}

	/**
	 * 拒账
	 *
	 * @param listNo
	 * @param id
	 * @throws Exception
	 */
	@Override
	public void juZhang(String listNo, Integer id, Integer userId) throws Exception {
		// 更新线边库产品下线单状态
		dao.updateStatus(id, "4");
	}

	/**
	 * 查询R跟D表数据
	 * @param listNo
	 * @return
	 */
	@Override
	public List<JSONObject> findRAndD(String listNo) {
		return dao.findRAndD(listNo);
	}

	/**
	 * 查询所有产线
	 * @return
	 */
	@Override
	public List<JSONObject> findLineAll() {
		return dao.findLineAll();
	}

}
