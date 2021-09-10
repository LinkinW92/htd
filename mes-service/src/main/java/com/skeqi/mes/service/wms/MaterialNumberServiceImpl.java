package com.skeqi.mes.service.wms;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.wms.MaterialNumberDao;
import com.skeqi.mes.pojo.CMesJlMaterialT;
import com.skeqi.mes.pojo.wms.CWmsLocationT;
import com.skeqi.mes.pojo.wms.CWmsMaterialNumberT;
import com.skeqi.mes.pojo.wms.CWmsProject;

/**
 * @package com.skeqi.mes.service.wms
 * @date 2020年2月17日
 * @author Yinp 物料库存
 */
@Service
public class MaterialNumberServiceImpl implements MaterialNumberService {

//	public MaterialNumberServiceImpl() {
//		ApplicationContext ap = new ClassPathXmlApplicationContext("spring-config.xml");
//		mapper = (MaterialNumberDao) ap.getBean("materialNumberDao");
//	}
	@Autowired
	MaterialNumberDao dao;

	/**
	 * 查询库位总库存
	 *
	 * @param locationId
	 * @return
	 */
	@Override
	public Integer findLocationCount(Integer locationId) {
		return dao.findLocationCount(locationId);
	}

	@Override
	public List<CWmsMaterialNumberT> findMaterialNumberList(Map<String, Object> map) {
		List<CWmsMaterialNumberT> list = dao.findMaterialNumberList(map);
		return list;
	}

	@Override
	public CWmsMaterialNumberT findMaterialNumberById(Integer materialNumberId) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("materialNumberId", materialNumberId);

		List<CWmsMaterialNumberT> materialNumberList = dao.findMaterialNumberList(map);
		if (materialNumberList.size() == 1) {
			CWmsMaterialNumberT materialNumber = materialNumberList.get(0);
			return materialNumber;
		} else {
			return null;
		}
	}

	@Override
	public boolean addMaterialNumber(CWmsMaterialNumberT materialNumber) {
		Integer result = dao.addMaterialNumber(materialNumber);
		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updateMaterialNumber(CWmsMaterialNumberT materialNumber) {
		Integer result = dao.updateMaterialNumber(materialNumber);
		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteMaterialNumber(Integer id, Integer locationId) throws Exception {

		Integer result = dao.deleteMaterialNumber(id);
		if (result <= 0) {
			throw new Exception("删除失败,删除的库存不存在");
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("locationId", locationId);
		List<CWmsMaterialNumberT> list = findMaterialNumberList(map);

		if (list == null || list.size() == 0) {
			// 表示该库位没有数据了 应该修改库位的状态
			Integer res = dao.updateLocationStateAndTray(locationId);
			if (res != 1) {
				throw new Exception("更新库位状态跟托盘码出错了");
			}
		}

		return true;
	}

	@Override
	public boolean updateLmminentRelease(CWmsMaterialNumberT dx, Integer str) {
		Integer result = dao.updateLmminentRelease(dx, str);
		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 查询总库存
	 */
	@Override
	public Integer findTotal(Integer projectId, Integer materialId, Integer warehouseId) {
		// TODO Auto-generated method stub
		return dao.findTotal(projectId, materialId, warehouseId);
	}

	/**
	 * 通过策列查询库存
	 *
	 * @param projectId
	 * @param materialId
	 * @param warehouseId
	 * @return
	 */
	@Override
	public List<CWmsMaterialNumberT> findMaterialNumber(Integer strategy, Integer projectId, Integer materialId,
			Integer warehouseId) {
		// TODO Auto-generated method stub
		return dao.findMaterialNumber(strategy, projectId, materialId, warehouseId);
	}

	@Override
	public List<CWmsLocationT> findLocationAll() {
		// TODO Auto-generated method stub
		return dao.findLocationAll();
	}

	@Override
	public List<CWmsProject> findProjectAll(String projectName) {
		// TODO Auto-generated method stub
		return dao.findProjectAll(projectName);
	}

	@Override
	public List<JSONObject> findMaterialAll(String materialName) {
		// TODO Auto-generated method stub
		return dao.findMaterialAll(materialName);
	}

	/**
	 * 查询条码
	 *
	 * @param materialId
	 * @param projectId
	 * @param locationId
	 * @return
	 */
	@Override
	public List<Map<String, Object>> findBarCode(Integer materialId, Integer projectId, Integer locationId) {
		return dao.findBarCode(materialId, projectId, locationId);
	}

	@Override
	public List<JSONObject> exportExcel(JSONObject json) {
		// TODO Auto-generated method stub
		return dao.exportExcel(json);
	}

	@Override
	public boolean updateBarCode(int id, String presentBarCode) throws Exception {
		Integer count, result;
//		Integer count = mapper.findBarCodeCount(sourceBarCode);
//		if(count==0) {
//			throw new Exception("原条码有误");
//		}
//		count = mapper.findBarCodeCount(presentBarCode);
//		if(count>0) {
//			throw new Exception("新条码已存在");
//		}
//		Integer result = mapper.updateAllMaterialBarCode(id, presentBarCode);
//		if(result!=1) {
//			throw new Exception("更新失败");
//		}
//		result = mapper.updateStorageDetailBarCode(sourceBarCode, presentBarCode);
//		if(result!=1) {
//			throw new Exception("更新失败");
//		}
		result = dao.updateMaterialNumberBarCode(id, presentBarCode);
		if (result != 1) {
			throw new Exception("更新失败");
		}

		return true;
	}

	/**
	 * 一键转移
	 *
	 * @param str
	 * @throws Exception
	 */
	@Override
	public void onekeyTransfer(String str, int locationId, int userId) throws Exception {
		List<JSONObject> listJson = null;
		try {
			listJson = JSONObject.parseArray(str, JSONObject.class);
			if (listJson == null) {
				throw new Exception();
			}
		} catch (Exception e) {
			throw new Exception("参数有误");
		}

		// 生成单号
		SimpleDateFormat s = new SimpleDateFormat("yyyyMMddHHmmss");
		String no = s.format(new Date());
		// 出库单号
		String listNo = "YK" + no;

		for (JSONObject json : listJson) {

			JSONObject stockJson = dao.findStockById(json.getInteger("id"));
			if (stockJson == null || stockJson.getInteger("id") == null) {
				throw new Exception("库存不存在");
			}
			if (stockJson.getInteger("materialNumber") - stockJson.getInteger("lmminentRelease") < json
					.getInteger("number")) {
				throw new Exception("库存不足");
			}
			if (stockJson.getInteger("locationId").equals(locationId)) {
				throw new Exception("库位相同");
			}


			JSONObject rStockJson = new JSONObject();
			rStockJson.put("materialId", stockJson.getInteger("materialId"));
			rStockJson.put("materialNumber", json.getInteger("number"));
			rStockJson.put("areaId", stockJson.getInteger("areaId"));
			rStockJson.put("rAreaId", stockJson.getInteger("rAreaId"));
			rStockJson.put("locationId", stockJson.getInteger("locationId"));
			rStockJson.put("listNo", listNo);
			rStockJson.put("projectId", stockJson.getInteger("projectId"));
			rStockJson.put("tray", stockJson.getString("tray"));
			rStockJson.put("warehouseId", stockJson.getInteger("warehouseId"));
			rStockJson.put("ynShift", 1);
			rStockJson.put("issueOrReceipt", 0);
			rStockJson.put("materialNumberId", stockJson.getInteger("id"));
			// 新增临时库存表记录
			if (dao.addRStock(rStockJson) != 1) {
				throw new Exception("新增临时库存表记录失败");
			}

			rStockJson.put("listNo", listNo);
			rStockJson.put("issueOrReceipt", 1);
			rStockJson.put("materialNumberId", 0);
			rStockJson.put("locationId", locationId);
			rStockJson.put("tray", "");
			// 新增临时库存表记录
			if (dao.addRStock(rStockJson) != 1) {
				throw new Exception("新增临时库存表记录失败");
			}

			// 更新库存数量
			if (dao.updateStockNumber(json.getInteger("id"), json.getInteger("number")) != 1) {
				throw new Exception("更新库存数量失败");
			}
		}

		List<JSONObject> OutTaskqueueList = new ArrayList<JSONObject>();
		OutTaskqueueList.add(listJson.get(0));
		for (int i = 0; i < listJson.size(); i++) {
			JSONObject json = listJson.get(i);

			j: for (int j = 0; j < OutTaskqueueList.size(); j++) {
				JSONObject outTaskqueueJson = OutTaskqueueList.get(j);
				if (outTaskqueueJson.getInteger("locationId").equals(json.getInteger("locationId"))) {
					break j;
				}
				if (j == OutTaskqueueList.size() - 1) {
					OutTaskqueueList.add(json);
				}

			}
		}

		// 保存库存入库详情sql
		StringBuffer OutTaskqueueBuffer = new StringBuffer();
		OutTaskqueueBuffer.append("value");
		for (JSONObject json : OutTaskqueueList) {
			OutTaskqueueBuffer.append("(");
			OutTaskqueueBuffer.append("now(),");
			OutTaskqueueBuffer.append("'" + listNo + "',");
			OutTaskqueueBuffer.append("'" + json.getString("tray") + "',");
			OutTaskqueueBuffer.append(0 + ",");
			OutTaskqueueBuffer.append(json.getInteger("locationId") + "),");
		}

		JSONObject locationJson = dao.findLocationById(locationId);
		JSONObject intaskqueueJson = new JSONObject();
		if (locationJson != null && locationJson.getInteger("locationStatus") != 0) {
			OutTaskqueueBuffer.append("(");
			OutTaskqueueBuffer.append("now(),");
			OutTaskqueueBuffer.append("'" + listNo + "',");
			OutTaskqueueBuffer.append("'" + locationJson.getString("tray") + "',");
			OutTaskqueueBuffer.append(0 + ",");
			OutTaskqueueBuffer.append(locationId + "),");


			intaskqueueJson.put("tray", locationJson.getString("tray"));

		}

		intaskqueueJson.put("listNo", listNo);
		intaskqueueJson.put("locationId", locationId);
		// 新增入库队列
		dao.addInTaskqueue(intaskqueueJson);

		String outTaskqueueSql = OutTaskqueueBuffer.toString();
		outTaskqueueSql = outTaskqueueSql.substring(0, outTaskqueueSql.length() - 1);
		// 新增出库队列
		dao.addOutTaskqueue(outTaskqueueSql);

		JSONObject stockTransferJson = new JSONObject();
		stockTransferJson.put("listNo", listNo);
		stockTransferJson.put("userId", userId);
		stockTransferJson.put("state", "未完成");
		dao.addStockTransfer(stockTransferJson);

	}

}
