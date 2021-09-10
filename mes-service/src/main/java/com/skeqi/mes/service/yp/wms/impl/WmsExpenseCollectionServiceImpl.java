package com.skeqi.mes.service.yp.wms.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.wms.WmsExpenseCollectionDao;
import com.skeqi.mes.service.wf.baseMode.codeRule.CodeRuleService;
import com.skeqi.mes.service.yp.wms.WmsExpenseCollectionService;
import com.skeqi.mes.service.yp.wms.WmsInventoryTransactionService;
import com.skeqi.mes.service.yp.wms.WmsStockService;

/**
 * @author yinp
 * @explain 费用化领用
 * @date 2021-08-20
 */
@Service
public class WmsExpenseCollectionServiceImpl implements WmsExpenseCollectionService {

	@Autowired
	WmsExpenseCollectionDao dao;

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
	 * 删除R
	 * 
	 * @param json
	 * @return
	 */
	@Override
	public void deleteR(Integer id) throws Exception {
		// 通过行id删除D
		dao.deleteDByRId(id);
		// 删除R
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
		// 单据号
		String listNo = json.getString("listNo");
		// 包装ID
		String packingId = json.getString("packingId");
		// 单件码
		String code = json.getString("code");

		JSONObject h = new JSONObject();
		h.put("listNo", listNo);
		List<JSONObject> hList = dao.list(h);
		if (hList == null || hList.size() == 0) {
			throw new Exception("单据不存在");
		}
		if (hList.size() != 1) {
			throw new Exception("单据存在重复");
		}
		h = hList.get(0);

		// 工厂
		String factoryId = h.getString("factoryId");
		// 库位
		String locationId = h.getString("locationId");

		// 查询库存是否存在
		JSONObject stock = wmsStockService.findStockByUnitCodeAndPackingId(code, packingId);
		if (stock == null) {
			throw new Exception("包装ID：" + packingId + " - 单件码：" + code + " 库存不存在");
		}

		// 判断工厂与库位
		if (!stock.getString("factoryId").equals(factoryId) || !stock.getString("locationId").equals(locationId)) {
			throw new Exception("包装ID：" + packingId + " - 单件码：" + code + " 库存不存在");
		}

		// 判断库存数量
		if (stock.getInteger("numberOfPackages") == 0) {
			if (stock.getInteger("number") == 0) {
				throw new Exception("包装ID：" + packingId + " - 单件码：" + code + " 库存不足");
			}
		}

		JSONObject d = new JSONObject();
		d.put("listNo", listNo);
		d.put("packingId", packingId);
		d.put("code", code);
		List<JSONObject> dList = dao.listD(d);
		if (dList != null && dList.size() > 0) {
			throw new Exception("包装ID：" + packingId + " - 单件码：" + code + " 已存在");
		}

		// 物料编号
		String materialNo = stock.getString("materialNo");
		// 物料名称
		String materialName = stock.getString("materialName");
		// 物料单位
		String unit = stock.getString("unit");
		// 库存包装数量
		Integer stockNumberOfPackages = stock.getInteger("numberOfPackages");
		// 库存单件数量
		Integer stockNumber = stock.getInteger("number");

		JSONObject r = new JSONObject();
		r.put("materialNo", materialNo);
		r.put("listNo", listNo);
		List<JSONObject> rList = dao.listR(r);
		if (rList != null && rList.size() == 1) {
			r = rList.get(0);

			if (code != null && !code.equals("")) {
				r.put("number", r.getInteger("number") + stockNumber);
			} else {
				r.put("number", r.getInteger("number") + stockNumberOfPackages);
			}
			if (dao.updateR(r) != 1) {
				throw new Exception("操作失败");
			}

		} else {
			r.put("listNo", listNo);
			r.put("materialNo", materialNo);
			r.put("materialName", materialName);
			r.put("unit", unit);
			if (code != null && !code.equals("")) {
				r.put("number", stockNumber);
			} else {
				r.put("number", stockNumberOfPackages);
			}
			if (dao.addR(r) != 1) {
				throw new Exception("操作失败");
			}
		}

		d = new JSONObject();
		d.put("rId", r.getInteger("id"));
		d.put("packingId", packingId);
		d.put("code", code);
		d.put("packingNumber", stockNumberOfPackages);
		d.put("number", stockNumber);
		if (dao.addD(d) != 1) {
			throw new Exception("操作失败");
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
		Integer rId = json.getInteger("rId");
		// 更新D表数量
		dao.updateD(json);

		// 查询D表
		JSONObject d = new JSONObject();
		d.put("rId", rId);
		List<JSONObject> dList = dao.listD(d);
		Integer num = 0;
		for (int i = 0; i < dList.size(); i++) {
			String code = dList.get(i).getString("code");
			if (code != null && !code.equals("")) {
				num = num + dList.get(i).getInteger("number");
			} else {
				num = num + dList.get(i).getInteger("packingNumber");
			}
		}

		// 更新R表数量
		JSONObject r = new JSONObject();
		r.put("id", rId);
		dao.updateR(r);

	}

	/**
	 * 删除D
	 * 
	 * @param json
	 * @return
	 */
	@Override
	public void deleteD(Integer id) throws Exception {
		JSONObject d = new JSONObject();
		d.put("id", id);
		List<JSONObject> dList = dao.listD(d);
		if (dList == null || dList.size() == 0) {
			throw new Exception("详情不存在");
		}
		d = dList.get(0);

		Integer rId = d.getInteger("rId");

		if (dao.deleteD(id) != 1) {
			throw new Exception("删除失败");
		}

		d = new JSONObject();
		d.put("rId", rId);
		dList = dao.listD(d);
		Integer num = 0;
		for (int i = 0; i < dList.size(); i++) {
			String packingId = dList.get(i).getString("packingId");
			String code = dList.get(i).getString("code");
			Integer packingNumber = dList.get(i).getInteger("packingNumber");
			Integer number = dList.get(i).getInteger("number");
			if (code != null && !code.equals("")) {
				num = num + number;
			} else {
				num = num + packingNumber;
			}
		}

		// 更新R表数量
		JSONObject r = new JSONObject();
		r.put("id", rId);
		r.put("number", num);
		dao.updateR(r);
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

	/**
	 * 过账
	 * 
	 * @param json
	 * @throws Exception
	 */
	@Override
	public void guoZhang(JSONObject json) throws Exception {
		Integer userId = json.getInteger("userId");
		String listNo = json.getString("listNo");
		// 查询需要过账的信息
		List<JSONObject> list = dao.queryTheInformationToBePosted(listNo);
		for (int i = 0; i < list.size(); i++) {
			// 工厂ID
			String factoryId = list.get(i).getString("factoryId");
			// 库位ID
			String locationId = list.get(i).getString("locationId");
			// 部门ID
			String departmentId = list.get(i).getString("departmentId");
			// 项目ID
			String projectId = list.get(i).getString("projectId");
			// 物料ID
			String materialId = list.get(i).getString("materialId");
			// 物料编号
			String materialNo = list.get(i).getString("materialNo");
			// 物料名称
			String materialName = list.get(i).getString("materialName");
			// 物料单位
			String unit = list.get(i).getString("unit");
			// 包装ID
			String packingId = list.get(i).getString("packingId");
			// 单件码
			String code = list.get(i).getString("code");
			// 包装数量
			Integer packingNumber = list.get(i).getInteger("packingNumber");
			// 单件数量
			Integer number = list.get(i).getInteger("number");

			// 通过单件码跟包装id查询库存
			JSONObject stock = wmsStockService.findStockByUnitCodeAndPackingId(code, packingId);
			if (stock == null || stock.size() == 0) {
				throw new Exception("包装ID：" + packingId + " - 单件码：" + code + " 库存不存在");
			}

			// 判断工厂跟库位是否一致
			if (!stock.getString("factoryId").equals(factoryId)
					|| !stock.getString("locationId").equals(locationId)) {
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
			inventoryTransaction.put("projectId", projectId);
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
			inventoryTransaction.put("transactionType", "041");
			// 操作员
			inventoryTransaction.put("userId", userId);
			// 交易代码
			inventoryTransaction.put("transactionCode", "费用化领用");
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
