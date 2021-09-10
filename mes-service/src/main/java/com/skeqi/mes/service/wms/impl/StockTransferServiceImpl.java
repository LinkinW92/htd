package com.skeqi.mes.service.wms.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.wms.StockTransferDao;
import com.skeqi.mes.service.wms.StockTransferService;
import com.skeqi.mes.util.ClientApiUseUtil;

/**
 * 库存转移
 *
 * @author yinp
 * @date 2021年4月28日
 *
 */
@Service
public class StockTransferServiceImpl implements StockTransferService {

	@Autowired
	StockTransferDao dao;

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
	 * 查询队列
	 *
	 * @param json
	 * @return
	 */
	@Override
	public List<JSONObject> findTaskqueue(JSONObject json) {
		String listNo = json.getString("listNo");
		String taskqueueType = json.getString("taskqueueType");
		if (taskqueueType.equals("in")) {
			return dao.findInTaskqueue(listNo);
		} else {
			return dao.findOutTaskqueue(listNo);
		}
	}

	/**
	 * 查询库存
	 *
	 * @param json
	 * @return
	 */
	@Override
	public List<JSONObject> findStock(JSONObject json) {
		String state = json.getString("state");
		String listNo = json.getString("listNo");
		Integer locationId = json.getInteger("locationId");

		if (state.equals("已完成")) {
			return dao.findPStock(listNo);
		} else {
			return dao.findRStock(listNo, locationId);
		}
	}

	/**
	 * 出库
	 *
	 * @param listNo
	 * @param locationId
	 * @throws Exception
	 */
	@Override
	public void chuku(String listNo, int locationId) throws Exception {
		JSONObject locationJson = dao.findLocationCoordinate(listNo, locationId);

		JSONObject json = new JSONObject();
		json.put("methodName", "MaterialOutbound");
		json.put("X", locationJson.getInteger("x"));
		json.put("Y", locationJson.getInteger("y"));
		json.put("Z", locationJson.getInteger("z"));

		// 拿出需要入库的托盘
		if (locationJson.getInteger("issueOrReceipt") == 1) {
			json.put("list", "1");
			json.put("trayCode", "1");
			// 删除出库队列
			dao.deleteOutTaskqueue(locationJson.getInteger("id"));
		} else {
			// 拿出需要出库的托盘
			json.put("trayCode", locationJson.getString("tray"));
			json.put("list", locationJson.getString("listNo"));

			// 修改出库队列动作标记
			dao.updateOutTaskqueueFlag(locationJson.getInteger("id"));
		}
		System.out.println(json);

		// 通过单号查询出库队列的数量
		int count = dao.findInTaskqueueByListNo(listNo);
		// 通过单号查询入库队列的数量
		count = count + dao.findOutTaskqueueByListNo(listNo);

		if (count == 0) {
			// 更新库存转移表状态
			dao.updateStockTransferState(listNo);
		}

		ClientApiUseUtil.UseApi(json);

	}

	/**
	 * 更新托盘码
	 *
	 * @param id
	 * @param tray
	 * @throws Exception
	 */
	@Override
	public void updateTray(int id, String tray) throws Exception {
		if (dao.findTrayCount(tray) > 0) {
			throw new Exception("托盘码已存在");
		}

		if (dao.updateTray(id, tray) != 1) {
			throw new Exception("更新托盘码失败");
		}
	}

	/**
	 * 改入库队列动作标记
	 *
	 * @param id
	 * @throws Exception
	 */
	@Override
	public void updateInTaskqueueFlag(int id) throws Exception {
		if (dao.updateInTaskqueueFlag(id) != 1) {
			throw new Exception("改入库队列动作标记失败");
		}

	}

	/**
	 * 改出库队列动作标记
	 *
	 * @param id
	 */
	@Override
	public void updateOutTaskqueueFlag(int id) throws Exception {
		if (dao.updateOutTaskqueueFlag(id) != 1) {
			throw new Exception("改出库队列动作标记失败");
		}
	}

	/**
	 * 直接入库
	 *
	 * @param json
	 * @throws Exception
	 */
	@Override
	public void directWarehousing(JSONObject json) throws Exception {
		json.put("issueOrReceipt", 1);
		// 查询临时库存详情
		List<JSONObject> temporaryStockDetailsJsonList = dao.queryTemporaryInventoryDetails(json);

		for (JSONObject jsonObject : temporaryStockDetailsJsonList) {
			// 查询物料库存
			JSONObject materialInventory = dao.queryMaterialInventory(jsonObject);
			if (materialInventory == null || materialInventory.getInteger("id") == null) {
				jsonObject.put("tray", json.getString("tray"));
				// 新增物料库存
				dao.newMaterialInventory(jsonObject);
			} else {
				jsonObject.put("materialNumberId", materialInventory.getInteger("id"));
				// 更新物料库存
				dao.updateMaterialInventory(jsonObject);
			}
			// 删除临时库存详情
			dao.deleteTemporaryMaterialInventory(jsonObject.getInteger("id"));
			// 新增永久库存详情
			dao.newPermanentInventoryDetails(jsonObject);

		}

		// 删除临时入库队列
		dao.deleteTemporaryStorageQueue(json.getInteger("id"));

		// 新增永久出库队列
		dao.addPermanentStorageQueue(json);

		// 修改库位状态以及托盘码
		dao.modifyTheStatusOfTheLocationAndTheTrayCode(json.getInteger("locationId"), 5, json.getString("tray"));
	}

	/**
	 * 直接出库
	 *
	 * @param json
	 * @throws Exception
	 */
	@Override
	public void directDelivery(JSONObject json) throws Exception {
		json.put("issueOrReceipt", 0);
		// 查询临时库存详情
		List<JSONObject> temporaryStockDetailsJsonList = dao.queryTemporaryInventoryDetails(json);

		for (JSONObject jsonObject : temporaryStockDetailsJsonList) {
			// 查询物料库存
			JSONObject materialInventory = dao.queryMaterialInventory(jsonObject);
			if (materialInventory != null && materialInventory.getInteger("id") != null) {
				jsonObject.put("tray", json.getString("tray"));

				if (materialInventory.getInteger("lmminentRelease") >= jsonObject.getInteger("materialNumber")) {
					// 出库更新库存
					dao.issueUpdateInventory(materialInventory.getInteger("id"),
							jsonObject.getInteger("materialNumber"));

					// 判断是否还有库存
					if (materialInventory.getInteger("materialNumber") - jsonObject.getInteger("materialNumber") == 0) {
						// 删除库存
						dao.deleteInventoryRecord(materialInventory.getInteger("id"));
					}
				}
			} else {
				throw new Exception("库存不存在");
			}

			// 删除临时库存详情
			dao.deleteTemporaryMaterialInventory(jsonObject.getInteger("id"));

			// 新增永久库存详情
			dao.newPermanentInventoryDetails(jsonObject);

			// 查询库位是否还有库存
			// 查询物料库存
			materialInventory = dao.queryMaterialInventory(jsonObject);
			if (materialInventory != null && materialInventory.getInteger("id") != null) {
				if (materialInventory.getInteger("materialNumber") == 0) {
					// 删除库存
					dao.deleteInventoryRecord(materialInventory.getInteger("id"));
				}

			}

		}

		// 删除临时出库队列
		dao.deleteTemporaryIssueQueue(json.getInteger("id"));

		// 新增永久出库队列
		dao.addPermanentIssueQueue(json);

		List<JSONObject> materialNumberList = dao.findMaterialNumberByLocationId(json.getInteger("locationId"));
		if (materialNumberList == null || materialNumberList.size() == 0) {
			// 修改库位状态以及托盘码
			dao.modifyTheStatusOfTheLocationAndTheTrayCode(json.getInteger("locationId"), 0, "");
		}

	}

}
