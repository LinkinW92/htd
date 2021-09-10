package com.skeqi.mes.service.yp.wms.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.wms.WmsIntraLibraryTransferDao;
import com.skeqi.mes.service.wf.baseMode.codeRule.CodeRuleService;
import com.skeqi.mes.service.yp.wms.WmsIntraLibraryTransferService;
import com.skeqi.mes.service.yp.wms.WmsInventoryTransactionService;
import com.skeqi.mes.service.yp.wms.WmsStockService;

/**
 * @author yinp
 * @explain 库内转移
 * @date 2021-08-02
 */
@Service
public class WmsIntraLibraryTransferServiceImpl implements WmsIntraLibraryTransferService {

	@Autowired
	WmsIntraLibraryTransferDao dao;

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
		json.put("viewMode", "0");
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
		// 单据号
		String listNo = json.getString("listNo");
		// 包装id
		String packingId = json.getString("packingId");
		// 单件码
		String code = json.getString("code");

		// 查询头表
		JSONObject h = new JSONObject();
		h.put("listNo", listNo);
		List<JSONObject> hList = dao.list(h);
		if (hList == null || hList.size() != 1) {
			throw new Exception("单号不存在");
		}
		h = hList.get(0);

		// 来源工厂ID
		Integer sourceFactoryId = h.getInteger("sourceFactoryId");
		// 来源库位ID
		Integer sourceLocationId = h.getInteger("sourceLocationId");

		// 通过单件码跟包装id查询库存
		JSONObject stock = wmsStockService.findStockByUnitCodeAndPackingId(code, packingId);
		if (stock == null) {
			throw new Exception("无此库存");
		}

		if (!stock.getString("factoryId").equals(sourceFactoryId.toString())) {
			throw new Exception("扫描的条码与选择的工厂不匹配");
		}
		if (!stock.getString("locationId").equals(sourceLocationId.toString())) {
			throw new Exception("扫描的条码与选择的库位不匹配");
		}

		// 物料编号
		String materialNo = stock.getString("materialNo");
		// 库存数量
		Integer number = stock.getInteger("number");
		// 库存数量
		Integer numberOfPackages = stock.getInteger("numberOfPackages");
		// 物料单位
		String unit = stock.getString("unit");

		// 查询行表
		JSONObject rDx = new JSONObject();
		rDx.put("listNo", json.getString("listNo"));
		rDx.put("materialNo", materialNo);
		List<JSONObject> rList = dao.listR(rDx);

		if (rList == null || rList.size() == 0) {
			// 新增行表
			rDx = new JSONObject();
			rDx.put("listNo", listNo);
			rDx.put("materialNo", materialNo);
			if (stock.getString("code") != null && !stock.getString("code").equals("")) {
				rDx.put("number", number);
			} else {
				rDx.put("number", numberOfPackages);
			}
			rDx.put("unit", unit);
			if (dao.addR(rDx) != 1) {
				throw new Exception("新增行表记录时出错了");
			}
		} else {

			rDx = rList.get(0);

			// 查询详情表
			// 判断条码是否被扫描过
			JSONObject dDx = new JSONObject();
			dDx.put("rId", rDx.getInteger("id"));
			List<JSONObject> dList = dao.listD(dDx);
			if (dList != null && dList.size() > 0) {
				for (JSONObject d : dList) {
					if (packingId != null && !packingId.equals("")) {
						if (d.getString("packingId").equals(packingId)) {
							throw new Exception("条码已存在");
						}
					}
					if (code != null && !code.equals("")) {
						if (d.getString("code").equals(code)) {
							throw new Exception("条码已存在");
						}
					}

				}

			}

		}

		json.put("number", number);
		json.put("packingNumber", numberOfPackages);
		json.put("rId", rDx.getString("id"));
		if (dao.addD(json) != 1) {
			throw new Exception("新增失败");
		}
		
		JSONObject d = new JSONObject();
		d.put("rId", rDx.getString("id"));
		List<JSONObject> dList = dao.listD(d);
		int num = 0;
		for (int i = 0; i < dList.size(); i++) {
			if (dList.get(i).getString("code") != null && !dList.get(i).getString("code").equals("")) {
				num = num + dList.get(i).getInteger("number");
			}else {
				num = num + dList.get(i).getInteger("packingNumber");	
			}
		}
		JSONObject r = new JSONObject();
		r.put("id", rDx.getString("id"));
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

		// 包装id
		String packingId = dDx.getString("packingId");
		// 单件码
		String code = dDx.getString("code");
		Integer number = dDx.getInteger("packingNumber");
		if (code != null && !code.equals("")) {
			number = dDx.getInteger("number");
		}

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
			}else {
				num = num + dList.get(i).getInteger("packingNumber");	
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

			if (stock == null) {
				throw new Exception("包装ID：" + packingId + ",单件码：" + code + "  库存不存在");
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
			inventoryTransaction.put("transactionType", "000");
			// 操作员
			inventoryTransaction.put("userId", userId);
			// 交易代码
			inventoryTransaction.put("transactionCode", "");
			// 发出库位
			inventoryTransaction.put("outLocationId", null);
			// 接收库位
			inventoryTransaction.put("intLocationId", receivingLocationId);
			// 物料id
			inventoryTransaction.put("materialId", materialId);
			wmsInventoryTransactionService.add(inventoryTransaction);

			// 出入库标记
			inventoryTransaction.put("sign", "出库");
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

}
