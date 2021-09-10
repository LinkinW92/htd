package com.skeqi.mes.service.wms;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.wms.StockInventoryDao;
import com.skeqi.mes.pojo.CMesUserT;
import com.skeqi.mes.pojo.wms.CWmsApprovalDetailsT;
import com.skeqi.mes.pojo.wms.CWmsApprovalT;
import com.skeqi.mes.pojo.wms.CWmsMaterialNumberT;
import com.skeqi.mes.pojo.wms.InventoryDetailT;
import com.skeqi.mes.pojo.wms.InventoryT;
import com.skeqi.mes.pojo.wms.ProcessApproval;
import com.skeqi.mes.pojo.wms.ProcessApprovalDetail;

@Service
public class StockInventoryServiceImpl implements StockInventoryService {

	@Autowired
	StockInventoryDao dao;

	@Override
	public Integer findMaterialNumber(Integer locationId, Integer materialId, Integer projectId) {
		// TODO Auto-generated method stub
		return dao.findMaterialNumber(locationId, materialId, projectId);
	}

	/**
	 * 新增盘点单
	 */
	@Override
	public boolean addInventory(JSONObject json) throws Exception {

		CMesUserT user = dao.findUserById(json.getInteger("userId"));

		Integer result = dao.findOutgoingQueue();
		if (result != 0) {
			throw new Exception("请先完成未完成的出库操作");
		}
		result = dao.findWarehousingQueue();
		if (result != 0) {
			throw new Exception("请先完成未完成的入库操作（包括加料）");
		}

		String listNo = json.getString("listNo");
		Integer materialId = json.getInteger("materialId");
		Integer number = json.getInteger("number");
		Integer stock = json.getInteger("stock");
		Integer differenceNumber = json.getInteger("differenceNumber");
		Integer locationId = json.getInteger("locationId");
		Integer projectId = json.getInteger("projectId");
		String trayCode = json.getString("trayCode");

		if (listNo == null || listNo.equals("")) {
			// 生成单号
			SimpleDateFormat s = new SimpleDateFormat("yyyyMMddHHmmss");
			// 单号
			listNo = "KCPD" + s.format(new Date());

			// 审批流程类型id
			Integer typeId = 9;

			ProcessApproval padx = new ProcessApproval();
			padx.setDeptId(Integer.parseInt(user.getDepartment()));
			padx.setRoleId(user.getRoleId());
			padx.setTypeId(typeId);

			// 查询审批流程
			List<ProcessApproval> paList = dao.findProcessApproval(padx);
			if (paList.size() == 0) {
				throw new Exception("您不能生成执行出库操作，可能是没有配置审批流程");
			}

			padx = paList.get(0);

			// 保存审批表
			CWmsApprovalT approval = new CWmsApprovalT();
			approval.setListNo(listNo);// 单号
			approval.setProcessId(padx.getId());// 流程主表id
			approval.setUserId(user.getId());// 申请用户id
			approval.setState(0);// 审批状态 0=未审批
			// 新增审批表记录
			int res = dao.addApproval(approval);
			if (res != 1) {
				throw new Exception("新增审批记录时出错了");
			}

			// 查询流程详情
			Map<String, Object> padMap = new HashMap<String, Object>();
			padMap.put("processId", padx.getId());
			List<ProcessApprovalDetail> padList = dao.findProcessApprovalDetailList(padMap);
			if (padList.size() == 0) {
				throw new Exception("查询审批流程详情出错了");
			}

			// 查询出刚刚新增的那一条审批记录数据
			CWmsApprovalT adx = dao.findApprovalByListNo(listNo);
			if (adx == null) {
				throw new Exception("没有查询到刚才新增的审批记录");
			}

			for (ProcessApprovalDetail processApprovalDetail : padList) {
				// 创建审批详情表对象
				CWmsApprovalDetailsT detail = new CWmsApprovalDetailsT();
				detail.setListNo(listNo);// 单号
				detail.setApprovalResult(0);// 0:未审批，1:通过，2:不通过
				detail.setUserId(user.getId());// 审批人id
				detail.setReason("");// 原因
				detail.setApprovalId(adx.getId());// 审批主表id
				detail.setPriorityLevel(processApprovalDetail.getPriorityLevel());// 优先级
				if (processApprovalDetail.getPriorityLevel() == 1) {
					detail.setYnApproved(1);
				}
				// 开始新增审批详情数据
				res = dao.addApprovalDetails(detail);
				if (res != 1) {
					throw new Exception("新增审批详情数据出错了");
				}

			}

			InventoryT dx = new InventoryT();
			dx.setListNo(listNo);
			dx.setUserId(user.getId());
			dx.setState(0);
			Integer res1 = dao.addInventory(dx);
			if (res1 != 1) {
				throw new Exception("新增盘点单据时出错了");
			}
		}

		Map<String, Object> dxMap = new HashMap<String, Object>();
		dxMap.put("listNo", listNo);
		List<InventoryT> dxList = dao.findInventoryList(dxMap);
		if (dxList == null) {
			throw new Exception("查询盘点单据时出错了");
		}
		InventoryT dx = dxList.get(0);

		InventoryDetailT detail = new InventoryDetailT();
		detail.setInventoryId(dx.getId());
		detail.setMaterialId(materialId);
		detail.setInventoryNo(number);
		detail.setStockNo(stock);
		detail.setDifferenceNo(differenceNumber);
		detail.setLocationId(locationId);
		detail.setProjectId(projectId);
		detail.setTrayCode(trayCode);
		Integer res = dao.addInventoryDetail(detail);
		if (res != 1) {
			throw new Exception("新增盘点单据详情时出错了");
		}

		return true;
	}

	@Override
	public List<InventoryT> findInventoryList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.findInventoryList(map);
	}

	@Override
	public List<InventoryDetailT> findInventoryDetailList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.findInventoryDetailList(map);
	}

	@Override
	public boolean implement(Integer id) throws Exception {
		Integer res = dao.updateInventoryState(id, 1);
		if (res != 1) {
			throw new Exception("更新盘点表状态失败了");
		}

		// 查询盘点详情
		List<JSONObject> inventoryDetailJsonList = dao.findInventoryDetail(id);

		// 保存需要修改的库位id跟托盘码
		List<JSONObject> locationJsonList = new ArrayList<JSONObject>();

		StringBuffer stoSqlBuffer = new StringBuffer();
		for (JSONObject jsonObject : inventoryDetailJsonList) {
			stoSqlBuffer.append("(");
			stoSqlBuffer.append("now(),");
			stoSqlBuffer.append(jsonObject.getInteger("materialId") + ",");

			if (jsonObject.getInteger("differenceNo") > 0) {
				stoSqlBuffer.append(jsonObject.getInteger("differenceNo") + ",");
			} else {
				stoSqlBuffer.append(jsonObject.getInteger("differenceNo") - jsonObject.getInteger("differenceNo")
						- jsonObject.getInteger("differenceNo") + ",");
			}

			stoSqlBuffer.append(jsonObject.getInteger("areaId") + ",");
			stoSqlBuffer.append(jsonObject.getInteger("raId") + ",");
			stoSqlBuffer.append(jsonObject.getInteger("locationId") + ",");
			stoSqlBuffer.append("'" + jsonObject.getString("listNo") + "',");
			stoSqlBuffer.append(jsonObject.getInteger("projectId") + ",");
			stoSqlBuffer.append("'" + jsonObject.getString("trayCode") + "',");
			stoSqlBuffer.append(jsonObject.getInteger("warehouseId") + ",");
			stoSqlBuffer.append("0,");
			if (jsonObject.getInteger("differenceNo") > 0) {
				stoSqlBuffer.append("0,");
			} else {
				stoSqlBuffer.append("1,");
			}
			stoSqlBuffer.append("'" + jsonObject.getString("materialName") + "',");
			stoSqlBuffer.append("'" + jsonObject.getString("warehouseName") + "',");
			stoSqlBuffer.append("'" + jsonObject.getString("areaName") + "',");
			stoSqlBuffer.append("'" + jsonObject.getString("raName") + "',");
			stoSqlBuffer.append("'" + jsonObject.getString("locationName") + "',");
			stoSqlBuffer.append("'" + jsonObject.getString("projectName") + "'),");

			if (jsonObject.getInteger("materialNumber") > 0) {
				// 库存变多了或者变少了
				// 更新物料库存表
				if (dao.updateStockMaterialNumber(jsonObject) != 1) {
					throw new Exception("更新物料库存表失败");
				}
			} else {
				// 库存没有了
				// 删除库存
				if (dao.deleteStockMaterialNumber(jsonObject) != 1) {
					throw new Exception("删除库存失败");
				}
			}

			// 第一次循环直接存如list
			if (locationJsonList.size() == 0) {
				JSONObject locationJson = new JSONObject();
				locationJson.put("locationId", jsonObject.getInteger("locationId"));
				locationJson.put("trayCode", jsonObject.getString("trayCode"));
				locationJsonList.add(locationJson);
			} else {
				// 除第一次外循环比较 然后list里没有就保存在list里
				for (int i = 0; i < locationJsonList.size(); i++) {
					if (locationJsonList.get(i).getInteger("locationId") == jsonObject.getInteger("locationId")) {
						continue;
					}

					if (i == locationJsonList.size() - 1) {
						JSONObject locationJson = new JSONObject();
						locationJson.put("locationId", jsonObject.getInteger("locationId"));
						locationJson.put("trayCode", jsonObject.getString("trayCode"));
						locationJsonList.add(locationJson);
					}

				}
			}
		}

		String stoSql = stoSqlBuffer.toString();
		stoSql = stoSql.substring(0, stoSql.length() - 1);

		if (dao.addRStorageDetail(stoSql) != 1) {
			throw new Exception("新增永久库存详情表记录失败");
		}

		// 更新盘点主表状态
		if (dao.newUpdateInventoryState(id) != 1) {
			throw new Exception("更新盘点主表状态失败");
		}

		// 更新库位托盘吗
		for (JSONObject jsonObject : locationJsonList) {
			// 通过库位查询是否还有物料库存
			int count = dao.findStockMaterialNumberByLocationId(jsonObject.getInteger("locationId"));
			if (count == 0) {
				jsonObject.put("state", 0);
				jsonObject.put("trayCode", "");
			} else {
				jsonObject.put("state", 5);
			}

			// 更新库位状态跟托盘码
			if (dao.updateLocationStateAndTrayCode(jsonObject) != 1) {
				throw new Exception("更新库位状态跟托盘码失败");
			}
		}

		return true;
	}

	@Override
	public boolean queryWhetherThereIsInventory() {
		Integer result = dao.queryWhetherThereIsInventory();
		if (result == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean findOutgoingQueue() {
		Integer result = dao.findOutgoingQueue();
		if (result == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean findWarehousingQueue() {
		Integer result = dao.findWarehousingQueue();
		if (result == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<JSONObject> findWarehouseAll() {
		// TODO Auto-generated method stub
		return dao.findWarehouseAll();
	}

	@Override
	public List<JSONObject> findAreaAllByWarehouseId(int warehouseId) {
		// TODO Auto-generated method stub
		return dao.findAreaAllByWarehouseId(warehouseId);
	}

	@Override
	public List<JSONObject> findReservoirAreaAllByAeraId(int areaId) {
		// TODO Auto-generated method stub
		return dao.findReservoirAreaAllByAeraId(areaId);
	}

	@Override
	public List<JSONObject> findLocationAllByReservoirAreaId(int reservoirAreaId) {
		// TODO Auto-generated method stub
		return dao.findLocationAllByReservoirAreaId(reservoirAreaId);
	}

	@Override
	public List<JSONObject> findMaterialAll(String materialName) {
		// TODO Auto-generated method stub
		return dao.findMaterialAll(materialName);
	}

	@Override
	public List<JSONObject> findProjectAll(String projectName) {
		// TODO Auto-generated method stub
		return dao.findProjectAll(projectName);
	}

	/**
	 * 通过单号查询盘点详情
	 *
	 * @param listNo
	 * @return
	 */
	@Override
	public List<JSONObject> findInventoryDetailByListNo(String listNo) {
		// TODO Auto-generated method stub
		return dao.findInventoryDetailByListNo(listNo);
	}

}
