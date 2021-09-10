package com.skeqi.mes.service.yp.wms.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.wms.WmsProductionRequisitionDao;
import com.skeqi.mes.service.wf.baseMode.codeRule.CodeRuleService;
import com.skeqi.mes.service.yp.wms.WmsInventoryTransactionService;
import com.skeqi.mes.service.yp.wms.WmsProductionRequisitionService;
import com.skeqi.mes.service.yp.wms.WmsStockService;

/**
 * @author yinp
 * @explain 生产领用
 * @date 2021-08-9
 */
@Service
public class WmsProductionRequisitionServiceImpl implements WmsProductionRequisitionService {

	@Autowired
	WmsProductionRequisitionDao dao;

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
		// 工位ID
		String stationId = json.getString("stationId");
		// 工单编号
		String workOrderNo = json.getString("workOrderNo");
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

		// 查询BOM
		List<JSONObject> bomList = dao.findBom(workOrderNo, stationId);

		if (bomList == null || bomList.size() == 0) {
			throw new Exception("工单中的产品未配置可用的BOM");
		}

		for (JSONObject bom : bomList) {
			JSONObject r = new JSONObject();
			r.put("listNo", listNo);
			r.put("materialNo", bom.getString("materialNo"));
			r.put("materialName", bom.getString("materialName"));
			r.put("demandQuantity", bom.getString("quantity"));
			r.put("issuedQuantity", 0);
			r.put("unit", bom.getString("unit"));

			// 新增R表
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
		json.put("viewMode", 0);
		dao.update(json);
	}

	/**
	 * 查询R跟D表
	 *
	 * @param listNo
	 * @return
	 */
	@Override
	public List<JSONObject> findRAndD(String listNo) {
		List<JSONObject> list = dao.findRAndD(listNo);

		String workOrderNo = list.get(0).getString("workOrderNo");

		List<JSONObject> numberList = dao.queryTheIssuedMaterialQuantityByWorkOrderNo(workOrderNo, null);

		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < numberList.size(); j++) {
				if (list.get(i).getString("materialNo").equals(numberList.get(j).getString("materialNo"))) {
					list.get(i).put("alreadyNumber", numberList.get(j).getString("alreadyNumber"));
				}
			}
		}

		return list;
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
		// 行id
		Integer rId = json.getInteger("rId");
		// 包装id
		String packingId = json.getString("packingId");
		// 单件码
		String code = json.getString("code");

		// 查询条码是否存在重复录入
		JSONObject d = new JSONObject();
		d.put("rId", rId);
		d.put("packingId", packingId);
		d.put("code", code);
		List<JSONObject> dList = dao.listD(d);
		if (dList != null && dList.size() > 0) {
			throw new Exception("请勿重复录入条码");
		}

		JSONObject h = new JSONObject();
		h.put("listNo", listNo);
		// 查询头表
		List<JSONObject> hList = dao.list(h);
		if (hList == null || hList.size() == 0) {
			throw new Exception("单据不存在");
		}
		if (hList.size() != 1) {
			throw new Exception("单据号重复");
		}
		h = hList.get(0);
		// 工单编号
		String workOrderNo = h.getString("workOrderNo");

		// 通过单件码跟包装id查询库存
		// 盘点条码是否存在
		JSONObject stock = wmsStockService.findStockByUnitCodeAndPackingId(code, packingId);
		if (stock == null || !h.getString("factoryId").equals(stock.getString("factoryId"))) {
			throw new Exception("包装ID：" + packingId + " - 单件码：" + code + "库存不存在");
		}

		// 包装数量
		Integer packingNumber = stock.getInteger("numberOfPackages");
		// 单件数量
		Integer number = stock.getInteger("number");

		// 判断库存是否充足
		if (code != null && !code.equals("")) {
			if (stock.getInteger("number") <= 0) {
				throw new Exception("包装ID：" + packingId + " - 单件码：" + code + "库存不足");
			}
		} else {
			if (stock.getInteger("numberOfPackages") <= 0) {
				throw new Exception("包装ID：" + packingId + " - 单件码：" + code + "库存不足");
			}
		}

		// 通过id查询R表
		JSONObject r = dao.findRById(rId);

		if (!r.getString("materialNo").equals(stock.getString("materialNo"))) {
			throw new Exception("提交的物料不符");
		}

		// 需求数量
		Integer demandQuantity = r.getInteger("demandQuantity");

		// 通过工单编号查询已出的物料数量
		List<JSONObject> numberList = dao.queryTheIssuedMaterialQuantityByWorkOrderNo(workOrderNo,
				r.getString("materialNo"));
		if (numberList == null) {
			throw new Exception("未查到已出物料数量");
		}
		// 已出数量
		Integer alreadyNumber = numberList.get(0).getInteger("alreadyNumber");

		if (h.getString("type").equals("计划内领料")) {
			if (code != null && !code.equals("")) {
				if (demandQuantity < alreadyNumber + number) {
					throw new Exception("当前实际领用数超过待领数，请使用超领模式");
				}
			} else {
				if (demandQuantity < alreadyNumber + packingNumber) {
					throw new Exception("当前实际领用数超过待领数，请使用超领模式");
				}
			}
		}

		// 新增详情表
		d = new JSONObject();
		d.put("rId", rId);
		d.put("packingId", packingId);
		d.put("code", code);
		d.put("packingNumber", packingNumber);
		d.put("number", number);
		dao.addD(d);

		if (code != null && !code.equals("")) {
			r.put("issuedQuantity", r.getInteger("issuedQuantity") + number);
		} else {
			r.put("issuedQuantity", r.getInteger("issuedQuantity") + packingNumber);
		}
		// 更新头表
		dao.updateR(r);

	}

	/**
	 * 删除行表
	 *
	 * @param id
	 * @return
	 */
	@Override
	public void deleteR(Integer id) throws Exception {
		if (dao.deleteR(id) != 1) {
			throw new Exception("删除失败");
		}
		dao.deleteDByRId(id);
	}

	/**
	 * 删除详情表
	 *
	 * @param id
	 * @return
	 */

	@Override
	public void deleteD(Integer id) throws Exception {
		JSONObject d = new JSONObject();
		d.put("id", id);
		List<JSONObject> dList = dao.listD(d);
		if (dList == null || dList.size() != 1) {
			throw new Exception("详情不存在");
		}

		// 删除详情
		if (dao.deleteD(id) != 1) {
			throw new Exception("删除失败");
		}

		// 行ID
		Integer rId = dList.get(0).getInteger("rId");
		d = new JSONObject();
		d.put("rId", rId);
		dList = dao.listD(d);

		int num = 0;
		for (JSONObject json : dList) {
			if (json.getString("code") != null && !json.getString("code").equals("")) {
				num = num + json.getInteger("number");
			} else {
				num = num + json.getInteger("packingNumber");
			}
		}
		JSONObject r = new JSONObject();
		r.put("id", rId);
		r.put("issuedQuantity", num);
		if (dao.updateR(r) != 1) {
			throw new Exception("删除失败");
		}

	}

	/**
	 * 过账
	 *
	 * @param json
	 * @throws Exception
	 */
	@Override
	public void guozhang(JSONObject json) throws Exception {
		Integer userId = json.getInteger("userId");
		String listNo = json.getString("listNo");

		// 查询需过账数据
		List<JSONObject> data = dao.queryDataToBePosted(listNo);
		if (data == null || data.size() == 0) {
			throw new Exception("无任何需要过账的数据");
		}
		for (int i = 0; i < data.size(); i++) {
			// 物料id
			Integer materialId = data.get(i).getInteger("materialId");
			// 物料编号
			String materialNo = data.get(i).getString("materialNo");
			// 物料名称
			String materialName = data.get(i).getString("materialName");
			// 物料单位
			String unit = data.get(i).getString("unit");
			// 包装ID
			String packingId = data.get(i).getString("packingId");
			// 单件码
			String code = data.get(i).getString("code");
			// 包装数量
			Integer packingNumber = data.get(i).getInteger("packingNumber");
			// 单件数量
			Integer number = data.get(i).getInteger("number");
			// 工厂id
			String factoryId = data.get(i).getString("factoryId");
			// 工单编号
			String workOrderNo = data.get(i).getString("workOrderNo");
			// 产线id
			Integer lineId = data.get(i).getInteger("lineId");
			// 线边库料格
			String rockNo = data.get(i).getString("rockNo");
			// 工位id
			Integer stationId = data.get(i).getInteger("stationId");

			if (packingId == null || packingId.equals("")) {
				if (code == null || code.equals("")) {
					throw new Exception(materialName + "无出库物料");
				}
			}

			// 通过单件码跟包装id查询库存
			JSONObject stock = wmsStockService.findStockByUnitCodeAndPackingId(code, packingId);
			if (stock == null || stock.size() == 0) {
				throw new Exception("包装ID：" + packingId + " - 单件码：" + code + "无库存");
			}

			// 判断工厂
			if (!stock.getString("factoryId").equals(factoryId)) {
				throw new Exception("包装ID：" + packingId + " - 单件码：" + code + "无库存");
			}

			// 判断库存数量是否一致
			if (code != null && !code.equals("")) {
				if (stock.getInteger("number") != number) {
					throw new Exception("包装ID：" + packingId + " - 单件码：" + code + "库存不足");
				}
				stock.put("number", 0);
			} else {
				if (stock.getInteger("numberOfPackages") != packingNumber) {
					throw new Exception("包装ID：" + packingId + " - 单件码：" + code + "库存不足");
				}
				stock.put("numberOfPackages", 0);
			}

			// 更新库存表
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
			inventoryTransaction.put("transactionType", "020");
			// 操作员
			inventoryTransaction.put("userId", userId);
			// 交易代码
			inventoryTransaction.put("transactionCode", "生产领用");
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
		dao.update(h);
	}

}
