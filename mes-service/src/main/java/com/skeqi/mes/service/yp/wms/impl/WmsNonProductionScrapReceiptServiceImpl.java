package com.skeqi.mes.service.yp.wms.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.wms.WmsNonProductionScrapReceiptDao;
import com.skeqi.mes.service.wf.baseMode.codeRule.CodeRuleService;
import com.skeqi.mes.service.yp.wms.WmsInventoryTransactionService;
import com.skeqi.mes.service.yp.wms.WmsNonProductionScrapReceiptService;
import com.skeqi.mes.service.yp.wms.WmsStockService;

/**
 * @author yinp
 * @explain 非生产报废入库
 * @date 2021-07-29
 */
@Service
public class WmsNonProductionScrapReceiptServiceImpl implements WmsNonProductionScrapReceiptService {

	@Autowired
	WmsNonProductionScrapReceiptDao dao;

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

	}

	/**
	 * 更新
	 *
	 * @param json
	 * @return
	 */
	@Override
	public void update(JSONObject json) throws Exception {
		if (dao.updateAll(json) != 1) {
			throw new Exception("更新失败");
		}
	}

	/**
	 * 删除
	 *
	 * @param listNo
	 */
	@Override
	public void delete(String listNo) throws Exception {
		JSONObject json = new JSONObject();
		json.put("listNo", listNo);
		json.put("viewMode", "0");
		if (dao.updateAll(json) != 1) {
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
		String materialId = json.getString("materialId");
		// 查询详情
		List<JSONObject> list = dao.listR(json);

		for (JSONObject dx : list) {
			if (dx.getString("materialId").equals(materialId)) {
				throw new Exception("改物料已经录入");
			}
		}

		if (dao.addR(json) != 1) {
			throw new Exception("新增失败");
		}
	}

	/**
	 * 更新R
	 *
	 * @param json
	 * @return
	 */
	@Override
	public void updateR(JSONObject json) throws Exception {
		if (dao.updateR(json) != 1) {
			throw new Exception("更新失败");
		}
	}

	/**
	 * 删除R
	 *
	 * @param json
	 * @return
	 */
	@Override
	public void deleteR(Integer id) throws Exception {
		dao.deleteDByRId(id);
		if (dao.deleteR(id) != 1) {
			throw new Exception("删除失败");
		}
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
		// 查询详情表
		JSONObject dDx = new JSONObject();
		dDx.put("rId", json.getString("rId"));
		List<JSONObject> dList = dao.listD(dDx);
		if (dList != null && dList.size() > 0) {
			for (JSONObject jsonObject : dList) {
				if (json.getString("packingId") != null && !json.getString("packingId").equals("")) {
					if (jsonObject.getString("packingId").equals(json.getString("packingId"))) {
						throw new Exception("条码已存在");
					}
				}

				if (json.getString("code") != null && !json.getString("code").equals("")) {
					if (jsonObject.getString("code").equals(json.getString("code"))) {
						throw new Exception("条码已存在");
					}
				}
			}
		}

		// 查询行表
		JSONObject rDx = new JSONObject();
		rDx.put("id", json.getString("rId"));
		List<JSONObject> rList = dao.listR(rDx);
		if (rList == null || rList.size() == 0) {
			throw new Exception("行不存在");
		}
		rDx = rList.get(0);

		// 查询头表
		JSONObject h = new JSONObject();
		h.put("listNo", rDx.getString("listNo"));
		List<JSONObject> hList = dao.list(h);
		if (hList == null || hList.size() != 1) {
			throw new Exception("单号不存在");
		}
		h = hList.get(0);
		// 包装id
		String packingId = json.getString("packingId");
		// 单件码
		String code = json.getString("code");
		// 报废类型：费用化报废、库存报废
		String scrapType = h.getString("scrapType");
		if (scrapType.equals("费用化报废")) {

		} else if (scrapType.equals("库存报废")) {
			// 通过单件码跟包装id查询库存
			JSONObject stock = wmsStockService.findStockByUnitCodeAndPackingId(code, packingId);
			if (stock == null) {
				throw new Exception("库存报废：条码不存在");
			}
		} else {
			throw new Exception("报废类型有误");
		}

		if (dao.addD(json) != 1) {
			throw new Exception("新增失败");
		}

		dDx = new JSONObject();
		dDx.put("rId", json.getString("rId"));
		dList = dao.listD(dDx);
		if (dList != null || dList.size() != 0) {
			int num = 0;
			for (int i = 0; i < dList.size(); i++) {
				if (dList.get(i).getString("code") != null && !dList.get(i).getString("code").equals("")) {
					num = num + dList.get(i).getInteger("number");
				} else {
					num = num + dList.get(i).getInteger("packingNumber");
				}
			}

			// 更新行表数量
			rDx.put("number", num);
			if (dao.updateR(rDx) != 1) {
				throw new Exception("更新行表数量时出错了");
			}
		}

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

		JSONObject d = new JSONObject();
		d.put("id", json.getString("id"));
		List<JSONObject> dList = dao.listD(d);
		if (dList == null || dList.size() == 0) {
			throw new Exception("数据不存在");
		}
		String rId = dList.get(0).getString("rId");
		d = new JSONObject();
		d.put("rId", rId);
		dList = dao.listD(d);

		int num = 0;
		for (int i = 0; i < dList.size(); i++) {
			if (dList.get(i).getString("code") != null && !dList.get(i).getString("code").equals("")) {
				num = num + dList.get(i).getInteger("number");
			} else {
				num = num + dList.get(i).getInteger("packingNumber");
			}
		}

		// 更新行表数量
		JSONObject r = new JSONObject();
		r.put("id", rId);
		r.put("number", num);
		if (dao.updateR(r) != 1) {
			throw new Exception("更新行表数量时出错了");
		}
	}

	/**
	 * 删除D
	 *
	 * @param id
	 * @return
	 */
	@Override
	public void deleteD(Integer id) throws Exception {
		// 查询详情表
		JSONObject dDx = new JSONObject();
		dDx.put("id", id);
		List<JSONObject> dList = dao.listD(dDx);
		if (dList == null || dList.size() == 0) {
			throw new Exception("删除的数据不存在");
		}
		dDx = dList.get(0);
		// 行id
		Integer rId = dDx.getInteger("rId");

		// 查询行表
		JSONObject rDx = new JSONObject();
		rDx.put("id", rId);
		List<JSONObject> rList = dao.listR(rDx);
		if (rList == null || rList.size() == 0) {
			throw new Exception("行不存在");
		}
		rDx = rList.get(0);

		if (dao.deleteD(id) != 1) {
			throw new Exception("删除失败");
		}
		
		JSONObject d = new JSONObject();
		d.put("rId", rId);
		dList = dao.listD(d);
		
		int num = 0;
		for (int i = 0; i < dList.size(); i++) {
			if (dList.get(i).getString("code") != null && !dList.get(i).getString("code").equals("")) {
				num = num + dList.get(i).getInteger("number");
			} else {
				num = num + dList.get(i).getInteger("packingNumber");
			}
		}

		// 更新行表数量
		JSONObject r = new JSONObject();
		r.put("id", rId);
		r.put("number", num);
		if (dao.updateR(r) != 1) {
			throw new Exception("更新行表数量时出错了");
		}

	}

	/**
	 * 过账
	 *
	 * @param json
	 * @throws Exception
	 */
	@Override
	public void guoZhang(JSONObject json) throws Exception {
		// 单据号
		String listNo = json.getString("listNo");
		// 用户id
		Integer userId = json.getInteger("userId");

		// 查询单据
		JSONObject hDx = new JSONObject();
		hDx.put("listNo", listNo);
		List<JSONObject> hList = dao.list(hDx);
		if (hList == null || hList.size() == 0) {
			throw new Exception("单据不存在");
		} else if (hList.size() != 1) {
			throw new Exception("通过单据查询出多条记录");
		}
		hDx = hList.get(0);
		// 报废类型：费用化报废、库存报废
		String scrapType = hDx.getString("scrapType");

		// 收货工厂id
		Integer receivingFactoryId = hDx.getInteger("receivingFactoryId");
		// 收货库位Id
		Integer receivingLocationId = hDx.getInteger("receivingLocationId");

		if (receivingFactoryId == null || receivingFactoryId.equals("")) {
			throw new Exception("请先设置收货工厂");
		}
		if (receivingLocationId == null || receivingLocationId.equals("")) {
			throw new Exception("请先设置收货库位");
		}

		// 查询需要过账的信息
		List<JSONObject> theInformationToBePostedList = dao.queryTheInformationToBePosted(listNo);

		if (theInformationToBePostedList == null || theInformationToBePostedList.size() == 0) {
			throw new Exception("数据不完整");
		}

		for (JSONObject the : theInformationToBePostedList) {
			// 包装id
			String packingId = the.getString("packingId");
			// 单件码
			String code = the.getString("code");
			// 物料id
			Integer materialId = the.getInteger("materialId");
			// 物料号
			String materialNo = the.getString("materialNo");
			// 单位
			String unit = the.getString("unit");

			// 入库数量
			Integer inNumber = the.getInteger("packingNumber");
			if (code != null && !code.equals("")) {
				inNumber = the.getInteger("number");
			}

			if (inNumber == 0) {
				throw new Exception("请编辑数量   包装id：" + packingId + ",单件码：" + code);
			}

			String batchNumber = "";
			try {
				batchNumber = codeRuleService.getLatestCode("all");
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("生成批次号时出错了    " + e.getMessage());
			}

			if (batchNumber == null) {
				throw new Exception("生成批次号返回空");
			}

			// 查询库存
			JSONObject stock = wmsStockService.findStockByUnitCodeAndPackingId(code, packingId);

			if (scrapType.equals("库存报废")) {
				if (stock == null) {
					throw new Exception(code + "-" + packingId + "-无库存");
				} else {
					if (inNumber > stock.getInteger("number")) {
						throw new Exception(code + "-" + packingId + "-库存不足");
					}
				}
			}

			if (stock == null) {
				// 新增
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
				// 数量
				addStockDx.put("number", inNumber);
				// 包装数
				addStockDx.put("numberOfPackages", 0);
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
				// 物料id
				addStockDx.put("materialId", materialId);

				wmsStockService.add(addStockDx);
			} else {
				// 更新
				// 更新库存
				JSONObject updateStockDx = new JSONObject();
				// id
				updateStockDx.put("id", stock.getString("id"));
				// 工厂Id
				updateStockDx.put("factoryId", receivingFactoryId);
				// 库位id
				updateStockDx.put("locationId", receivingLocationId);
				// 数量
				updateStockDx.put("number", inNumber + stock.getInteger("number"));
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
			// 数量
			inventoryTransaction.put("number", inNumber);
			// 出入库标记
			inventoryTransaction.put("sign", "入库");
			// 交易类型
			inventoryTransaction.put("transactionType", "050");
			// 操作员
			inventoryTransaction.put("userId", userId);
			// 交易代码
			inventoryTransaction.put("transactionCode", "来料入库");
			// 发出库位
			inventoryTransaction.put("outLocationId", null);
			// 接收库位
			inventoryTransaction.put("intLocationId", receivingLocationId);
			// 物料id
			inventoryTransaction.put("materialId", materialId);
			wmsInventoryTransactionService.add(inventoryTransaction);
		}

		// 更新状态
		hDx.put("status", "过账");
		hDx.put("receivingUserId", userId);
		hDx.put("receivingDt", true);
		if (dao.updateAll(hDx) != 1) {
			throw new Exception("过账失败");
		}

	}

	/**
	 * 分组查询库存
	 *
	 * @param json
	 * @return
	 */
	@Override
	public List<JSONObject> findStockGroupByMaterial(JSONObject json) {
		return dao.findStockGroupByMaterial(json);
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
