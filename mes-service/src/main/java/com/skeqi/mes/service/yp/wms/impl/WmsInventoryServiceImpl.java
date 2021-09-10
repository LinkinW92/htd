package com.skeqi.mes.service.yp.wms.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.wms.WmsInventoryDao;
import com.skeqi.mes.service.wf.baseMode.codeRule.CodeRuleService;
import com.skeqi.mes.service.yp.wms.WmsInventoryService;
import com.skeqi.mes.service.yp.wms.WmsInventoryTransactionService;
import com.skeqi.mes.service.yp.wms.WmsStockService;
import com.skeqi.mes.util.yp.EqualsUtil;

/**
 * @author yinp
 * @explain 盘点
 * @date 2021-08-17
 */
@Service
public class WmsInventoryServiceImpl implements WmsInventoryService {

	@Autowired
	WmsInventoryDao dao;

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

		dao.add(json);
	}

	/**
	 * 更新
	 *
	 * @param json
	 * @return
	 */
	@Override
	public void update(JSONObject json) throws Exception {
		dao.update(json);
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
	 * 删除R
	 *
	 * @param id
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
	 * 新增D
	 *
	 * @param json
	 * @return
	 */
	@Override
	public void addD(JSONObject json) throws Exception {
		String listNo = json.getString("listNo");
		String packingId = json.getString("packingId");
		String code = json.getString("code");

		// 通过单件码跟包装id查询库存
		JSONObject stock = wmsStockService.findStockByUnitCodeAndPackingId(code, packingId);
		if (stock == null) {
			throw new Exception("未找到相关库存");
		}

		// 查询D
		List<JSONObject> dList = dao.listD(json);
		if (dList != null && dList.size() > 0) {
			throw new Exception("该条码已在此单据中提交");
		}

		// 通过单据号查询
		JSONObject h = dao.findByListNo(listNo);
		if (!stock.getString("factoryId").equals(h.getString("factoryId"))
				|| !stock.getString("locationId").equals(h.getString("locationId"))) {
			throw new Exception("未找到相关库存");
		}

		JSONObject r = new JSONObject();
		r.put("listNo", listNo);
		r.put("materialNo", stock.getString("materialNo"));
		List<JSONObject> rList = dao.listR(r);
		if (rList == null || rList.size() == 0) {
			// 库存数量
			r.put("stockNumber", stock.getString("numberOfPackages"));
			// 实数数量
			r.put("actualNumber", json.getString("packingActualNumber"));
			if (code != null && !code.equals("")) {
				r.put("actualNumber", json.getString("actualNumber"));
				r.put("stockNumber", stock.getString("number"));
			}
			// 新增R
			dao.addR(r);
		} else {
			r = rList.get(0);
			// 库存数量
			r.put("stockNumber", r.getInteger("stockNumber") + stock.getInteger("numberOfPackages"));
			// 实数数量
			r.put("actualNumber", r.getInteger("actualNumber") + json.getInteger("packingActualNumber"));
			if (code != null && !code.equals("")) {
				r.put("stockNumber", r.getInteger("stockNumber") + stock.getInteger("number"));
				r.put("actualNumber", r.getInteger("actualNumber") + json.getInteger("actualNumber"));
			}
			// 更新R
			dao.updateR(r);
		}

		JSONObject d = new JSONObject();
		// 行id
		d.put("rId", r.getString("id"));
		// 包装id
		d.put("packingId", packingId);
		// 单件码
		d.put("code", code);
		// 包装库存数量
		d.put("packingStockNumber", stock.getString("numberOfPackages"));
		// 包装实际数量
		d.put("packingActualNumber", json.getString("packingActualNumber"));
		// 单件库存数量
		d.put("stockNumber", stock.getString("number"));
		// 单件实际数量
		d.put("actualNumber", json.getString("actualNumber"));
		// 新增D
		dao.addD(d);

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
		d.put("rId", json.getString("rId"));
		List<JSONObject> dList = dao.listD(d);
		// 库存数量
		Integer stockNumber = 0;
		// 实际数量
		Integer actualNumber = 0;
		for (JSONObject dJson : dList) {
			if (dJson.getString("code") != null && !dJson.getString("code").equals("")) {
				stockNumber = stockNumber + dJson.getInteger("stockNumber");
				actualNumber = actualNumber + dJson.getInteger("actualNumber");
			} else {
				stockNumber = stockNumber + dJson.getInteger("packingStockNumber");
				actualNumber = actualNumber + dJson.getInteger("packingActualNumber");
			}
		}
		JSONObject r = new JSONObject();
		r.put("id", json.getString("rId"));
		r.put("actualNumber", actualNumber);
		r.put("stockNumber", stockNumber);
		// 更新R
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

		d = dList.get(0);
		String rId = d.getString("rId");
		// 库存数量
		Integer stockNumber = 0;
		// 实际数量
		Integer actualNumber = 0;

		d = new JSONObject();
		d.put("rId", rId);
		// 查询D
		dList = dao.listD(d);
		for (JSONObject dJson : dList) {
			if (dJson.getString("code") != null && !dJson.getString("code").equals("")) {
				stockNumber = stockNumber + dJson.getInteger("stockNumber");
				actualNumber = actualNumber + dJson.getInteger("actualNumber");
			} else {
				stockNumber = stockNumber + dJson.getInteger("packingStockNumber");
				actualNumber = actualNumber + dJson.getInteger("packingActualNumber");
			}
		}

		JSONObject r = new JSONObject();
		r.put("id", rId);
		r.put("actualNumber", actualNumber);
		r.put("stockNumber", stockNumber);
		// 更新R
		dao.updateR(r);
	}

	/**
	 * 查询R跟D
	 *
	 * @param json
	 * @return
	 */
	@Override
	public List<JSONObject> findRAndD(String listNo) {
		return dao.findRAndD(listNo);
	}

	/**
	 * 过账
	 *
	 * @param json
	 * @return
	 */
	@Override
	public void guozhang(JSONObject json) throws Exception {
		// 处理人
		Integer userId = json.getInteger("userId");
		// 单据号
		String listNo = json.getString("listNo");
		// 查询需要过账的数据
		List<JSONObject> list = dao.findInventory(listNo);

		for (JSONObject inventory : list) {
			// 盘点类型
			String type = inventory.getString("type");
			// 工厂ID
			String factoryId = inventory.getString("factoryId");
			// 库位ID
			String locationId = inventory.getString("locationId");
			// 包装id
			String packingId = inventory.getString("packingId");
			// 单件码
			String code = inventory.getString("code");
			// 包装库存数量
			Integer packingStockNumber = inventory.getInteger("packingStockNumber");
			// 包装实际数量
			Integer packingActualNumber = inventory.getInteger("packingActualNumber");
			// 单件库存数量
			Integer stockNumber = inventory.getInteger("stockNumber");
			// 单件实际数量
			Integer actualNumber = inventory.getInteger("actualNumber");
			// 物料id
			Integer materialId = inventory.getInteger("materialId");
			// 物料名称
			String materialName = inventory.getString("materialName");
			// 物料编号
			String materialNo = inventory.getString("materialNo");
			// 单位
			String unit = inventory.getString("unit");
			// 批次号
			String batchNumber = inventory.getString("batchNumber");

			// 过单件码跟包装id查询库存
			JSONObject stock = wmsStockService.findStockByUnitCodeAndPackingId(code, packingId);
			if (stock == null || !stock.getString("factoryId").equals(factoryId)
					|| !stock.getString("locationId").equals(locationId)) {
				throw new Exception("包装ID：" + packingId + "，单件码：" + code + "；无库存记录");
			}
			JSONObject updateStock = new JSONObject();
			// 库存id
			updateStock.put("id", stock.getString("id"));
			// 包装数量
			updateStock.put("numberOfPackages", packingActualNumber);
			if (code != null) {
				// 单件数量
				updateStock.put("number", actualNumber);
			}
			// 更新库存表
			wmsStockService.update(updateStock);

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
			inventoryTransaction.put("batchNumber", batchNumber);// 自己生成
			// 数量
			inventoryTransaction.put("number", packingActualNumber - packingStockNumber);
			// 出入库标记
			inventoryTransaction.put("sign", packingActualNumber - packingStockNumber > 0 ? "入库" : "出库");
			// 交易类型
			inventoryTransaction.put("transactionType", packingActualNumber - packingStockNumber > 0 ? "999" : "998");
			// 操作员
			inventoryTransaction.put("userId", userId);
			// 交易代码
			inventoryTransaction.put("transactionCode", "来料入库");
			// 发出库位
			inventoryTransaction.put("outLocationId", packingActualNumber - packingStockNumber < 0 ? locationId : null);
			// 接收库位
			inventoryTransaction.put("intLocationId", packingActualNumber - packingStockNumber > 0 ? locationId : null);
			// 物料id
			inventoryTransaction.put("materialId", materialId);

			if (code != null && !code.equals("")) {
				// 数量
				inventoryTransaction.put("number", actualNumber - stockNumber);
				// 出入库标记
				inventoryTransaction.put("sign", actualNumber - stockNumber > 0 ? "入库" : "出库");
				// 交易类型
				inventoryTransaction.put("transactionType", actualNumber - stockNumber > 0 ? "999" : "998");
				// 发出库位
				inventoryTransaction.put("outLocationId", actualNumber - stockNumber < 0 ? locationId : null);
				// 接收库位
				inventoryTransaction.put("intLocationId", actualNumber - stockNumber > 0 ? locationId : null);
			}
			wmsInventoryTransactionService.add(inventoryTransaction);
		}

		//更新单据状态
		JSONObject h = new JSONObject();
		h.put("listNo", listNo);
		h.put("handleUserId", userId);
		h.put("handleDt", true);
		h.put("status", "已完成");
		dao.update(h);

	}

}
