package com.skeqi.mes.service.yp.wms.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.wms.WmsExpenseWarehousingDao;
import com.skeqi.mes.service.wf.baseMode.codeRule.CodeRuleService;
import com.skeqi.mes.service.yp.wms.WmsExpenseWarehousingService;
import com.skeqi.mes.service.yp.wms.WmsInventoryTransactionService;
import com.skeqi.mes.service.yp.wms.WmsStockService;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * @author yinp
 * @explain 费用化入库
 * @date 2021-07-28
 */
@Service
public class WmsExpenseWarehousingServiceImpl implements WmsExpenseWarehousingService {

	@Autowired
	WmsExpenseWarehousingDao dao;

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
		if (dao.update(json) != 1) {
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
		String materialNo = json.getString("materialNo");
		// 查询详情
		List<JSONObject> list = dao.listR(json);

		for (JSONObject dx : list) {
			if (dx.getString("materialNo").equals(materialNo)) {
				throw new Exception("该物料已经录入");
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
		Integer rId = json.getInteger("rId");
		String packingId = json.getString("packingId");
		String code = json.getString("code");

		JSONObject stock = wmsStockService.findStockByUnitCodeAndPackingId(code, packingId);
		if (stock != null) {
			throw new Exception("条码库存已存在");
		}

		JSONObject r = new JSONObject();
		r.put("id", rId);
		List<JSONObject> rList = dao.listR(r);
		if (rList == null || rList.size() == 0) {
			throw new Exception("数据不存在");
		}
		String listNo = rList.get(0).getString("listNo");

		// 查询详情表
		JSONObject dDx = new JSONObject();
		dDx.put("listNo", listNo);
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

		if (dao.addD(json) != 1) {
			throw new Exception("新增失败");
		}

		JSONObject d = new JSONObject();
		d.put("rId", rId);
		dList = dao.listD(d);
		Integer num = 0;
		for (JSONObject dJson : dList) {
			String code1 = dJson.getString("code");
			Integer packingNumber = dJson.getInteger("packingNumber");
			Integer number = dJson.getInteger("number");
			if (code1 != null && !code1.equals("")) {
				num = num + number;
			} else {
				num = num + packingNumber;
			}
		}

		r = new JSONObject();
		r.put("id", rId);
		r.put("number", num);
		dao.updateR(r);
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
			throw new Exception("数据不存在");
		}
		Integer rId = dList.get(0).getInteger("rId");
		d = new JSONObject();
		d.put("rId", rId);
		dList = dao.listD(d);
		Integer num = 0;
		for (JSONObject dJson : dList) {
			String code = dJson.getString("code");
			Integer packingNumber = dJson.getInteger("packingNumber");
			Integer number = dJson.getInteger("number");
			if (code != null && !code.equals("")) {
				num = num + number;
			} else {
				num = num + packingNumber;
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
	public void deleteD(Integer id) throws Exception {

		JSONObject d = new JSONObject();
		d.put("id", id);
		List<JSONObject> dList = dao.listD(d);
		if (dList == null || dList.size() == 0) {
			throw new Exception("数据不存在");
		}

		if (dao.deleteD(id) != 1) {
			throw new Exception("删除失败");
		}

		Integer rId = dList.get(0).getInteger("rId");
		d = new JSONObject();
		d.put("rId", rId);
		dList = dao.listD(d);
		Integer num = 0;
		for (JSONObject dJson : dList) {
			String code = dJson.getString("code");
			Integer packingNumber = dJson.getInteger("packingNumber");
			Integer number = dJson.getInteger("number");
			if (code != null && !code.equals("")) {
				num = num + number;
			} else {
				num = num + packingNumber;
			}
		}

		JSONObject r = new JSONObject();
		r.put("id", rId);
		r.put("number", num);
		dao.updateR(r);

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

		// 收货工厂id
		Integer receivingFactoryId = hDx.getInteger("receivingFactoryId");
		// 收货库位Id
		Integer receivingLocationId = hDx.getInteger("receivingLocationId");

		// 查询需要过账的信息
		List<JSONObject> theInformationToBePostedList = dao.queryTheInformationToBePosted(listNo);

		if (theInformationToBePostedList == null || theInformationToBePostedList.size() == 0) {
			throw new Exception("无过账数据");
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
			// 物料名称
			String materialName = the.getString("materialName");
			// 单位
			String unit = the.getString("unit");

			if (packingId == null || packingId.equals("")) {
				if (code == null || code.equals("")) {
					throw new Exception(materialName + " 无物料数据");
				}
			}

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

				if (code == null || code.equals("")) {
					// 数量
					addStockDx.put("number", 0);
					// 包装数
					addStockDx.put("numberOfPackages", inNumber);
				}

				wmsStockService.add(addStockDx);
			} else {
				// 更新
				// 更新库存
				JSONObject updateStockDx = new JSONObject();
				// id
				updateStockDx.put("id", stock.getString("id"));
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
			inventoryTransaction.put("transactionType", "040");
			// 操作员
			inventoryTransaction.put("userId", userId);
			// 交易代码
			inventoryTransaction.put("transactionCode", "费用化入库");
			// 发出库位
			inventoryTransaction.put("outLocationId", null);
			// 接收库位
			inventoryTransaction.put("intLocationId", receivingLocationId);
			// 物料id
			wmsInventoryTransactionService.add(inventoryTransaction);
		}

		// 更新状态
		hDx.put("status", "过账");
		hDx.put("receivingUserId", userId);
		hDx.put("receivingDt", true);
		if (dao.update(hDx) != 1) {
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
