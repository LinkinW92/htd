package com.skeqi.mes.service.wms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.mapper.wms.ChargingTaskqueueDao;
import com.skeqi.mes.pojo.CMesUserT;
import com.skeqi.mes.pojo.wms.CWmsApprovalT;
import com.skeqi.mes.pojo.wms.CWmsInTaskqueueT;
import com.skeqi.mes.pojo.wms.CWmsLocationT;
import com.skeqi.mes.pojo.wms.CWmsMaterialNumberT;
import com.skeqi.mes.pojo.wms.CWmsStorageDetailT;
import com.skeqi.mes.util.ZplPrintingUtil;

@Service
public class ChargingTaskqueueImpl implements ChargingTaskqueueService {

	@Autowired
	ChargingTaskqueueDao dao;

	@Override
	public PageInfo<CWmsApprovalT> findList(JSONObject json) throws Exception {

		PageHelper.startPage(json.getInteger("pageNumber"), 8);
		List<CWmsApprovalT> list = dao.findApproval(json);
		PageInfo<CWmsApprovalT> pageInfo = new PageInfo<CWmsApprovalT>(list, 5);

		return pageInfo;
	}

	@Override
	public List<CWmsInTaskqueueT> findInTaskqueueTList(Map<String, Object> map) throws Exception {
		if (map == null) {
			map = new HashMap<String, Object>();
		}
		List<CWmsInTaskqueueT> list = dao.findInTaskqueue(map);

		return list;
	}

	@Override
	public PageInfo<JSONObject> findStorageDetail(Map<String, Object> map) throws Exception {

		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("id", map.get("inTaskqueueId"));
		List<CWmsInTaskqueueT> itList = dao.findInTaskqueue(map1);

		if (itList.size() <= 0) {
			throw new Exception("查询入库队列出错了");
		}

		CWmsInTaskqueueT itDx = itList.get(0);

		map.put("locationId", itDx.getLocationId());
		map.put("listNo", itDx.getListNo());

		PageHelper.startPage(Integer.parseInt(map.get("pageNumber").toString()), 8);
		List<JSONObject> list = dao.findStorageDetail(map);
		PageInfo<JSONObject> pageInfo = new PageInfo<JSONObject>(list, 5);

		return pageInfo;
	}

	/**
	 * 加料不出库
	 *
	 * @throws Exception
	 */
	@Override
	public boolean buchuku(Integer inTaskqueueId) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", inTaskqueueId);
		List<CWmsInTaskqueueT> inTaskqueueList = dao.findInTaskqueue(map);
		if (inTaskqueueList.size() != 1) {
			throw new Exception("查询加料队列出错了");
		}

		CWmsInTaskqueueT inTaskqueue = inTaskqueueList.get(0);
		// 删除临时表
		Integer res = dao.deleteInTaskqueue(inTaskqueueId);
		if (res != 1) {
			throw new Exception("删除临时加料队列数据失败了");
		}
		// 新增永久表数据
		res = dao.addPInTaskqueue(inTaskqueue);
		if (res != 1) {
			throw new Exception("新增永久加料队列数据失败了");
		}

		String listNo = inTaskqueue.getListNo();
		int locationId = inTaskqueue.getLocationId();

		// 查询
		List<JSONObject> sdList = dao.findRStorageDetail(listNo, locationId);
		if (sdList.size() == 0) {
			throw new Exception("没有查询到库存详情");
		}

		StringBuffer materialNumber = new StringBuffer();
		materialNumber.append("value");
		StringBuffer storage = new StringBuffer();
		storage.append("value");

		for (int i = 0; i < sdList.size(); i++) {

			// 查询是否存在库存
			JSONObject materialNumberJson = new JSONObject();
			materialNumberJson.put("materialId", sdList.get(i).getInteger("materialId"));
			materialNumberJson.put("projectId", sdList.get(i).getInteger("projectId"));
			materialNumberJson.put("locationId", sdList.get(i).getInteger("locationId"));
			materialNumberJson = dao.findMaterialNumberJsonObject(materialNumberJson);

			if (materialNumberJson != null && materialNumberJson.getInteger("ID") != null) {
				// 存在
				materialNumberJson.put("materialNumber", sdList.get(i).getInteger("materialNumber"));
				// 更新库存
				dao.updateStockMaterialNumber(materialNumberJson);
			} else {
				// 不存在
				materialNumberJson = new JSONObject();
				materialNumberJson.put("MATERIAL_NO", sdList.get(i).getString("materialNo"));
				materialNumberJson.put("MATERIAL_NAME", sdList.get(i).getString("materialName"));
				materialNumberJson.put("MATERIAL_NUMBER", sdList.get(i).getInteger("materialNumber"));
				materialNumberJson.put("PROJECT_ID", sdList.get(i).getInteger("projectId"));
				materialNumberJson.put("MATERIAL_ID", sdList.get(i).getInteger("materialId"));
				materialNumberJson.put("WAREHOUSE_ID", sdList.get(i).getInteger("warehouseId"));
				materialNumberJson.put("AREA_ID", sdList.get(i).getInteger("areaId"));
				materialNumberJson.put("LOCATION_ID", sdList.get(i).getInteger("locationId"));
				materialNumberJson.put("RESERVOIR_AREA_ID", sdList.get(i).getInteger("reservoirAreaId"));
				materialNumberJson.put("LMMINENT_RELEASE", 0);
				materialNumberJson.put("FREEZING_NUMBER", 0);
				materialNumberJson.put("RESERVED_NUMBER", 0);
				materialNumberJson.put("TRAY", sdList.get(i).getString("tray"));

				// 新增物料库存
				if (dao.addMaterialNumber(materialNumberJson) != 1) {
					throw new Exception("新增物料库存失败了");
				}

			}

			String sql2 = "(now()," + sdList.get(i).getInteger("materialId") + ","
					+ sdList.get(i).getInteger("materialNumber") + "," + sdList.get(i).getInteger("areaId") + ","
					+ sdList.get(i).getInteger("reservoirAreaId") + "," + sdList.get(i).getInteger("locationId") + ",'"
					+ sdList.get(i).getString("listNo") + "'," + sdList.get(i).getInteger("projectId") + ",'"
					+ sdList.get(i).getString("tray") + "'," + sdList.get(i).getInteger("warehouseId") + ",0,1," + "'"
					+ sdList.get(i).getString("barCode") + "'," + "'" + sdList.get(i).getString("materialName") + "',"
					+ "'" + sdList.get(i).getString("warehouseName") + "'," + "'" + sdList.get(i).getString("areaName")
					+ "'," + "'" + sdList.get(i).getString("reName") + "'," + "'"
					+ sdList.get(i).getString("locationName") + "','" + sdList.get(i).getString("projectName") + "',"
					+ sdList.get(i).getInteger("materialNumberId") + ")";
			storage.append(sql2);

			if (i != (sdList.size() - 1)) {
				materialNumber.append(",");
				storage.append(",");
			}

			// 更新库存时间
			dao.updateNumberDt(sdList.get(i).getInteger("projectId"), sdList.get(i).getInteger("materialId"),
					sdList.get(i).getInteger("locationId"));
		}

		// 删除临时库存详情表数据
		int result = dao.deleteRStorageDetail(listNo);
		if (result <= 0) {
			throw new Exception("删除临时库存详情表数据失败了");
		}

		// 新增永久库存详情表数据
		result = dao.addPStorageDetail(storage.toString());
		if (result <= 0) {
			throw new Exception("新增永久库存详情表数据失败了");
		}

		return true;
	}

	@Override
	public boolean printing(String listNo) throws Exception {

		Map<String, Object> map = dao.findAllMaterialBarcodeIdAndBarCode(listNo);
		if (map == null) {
			throw new Exception("单据号未查询出需要打印的条码");
		}
		boolean res = ZplPrintingUtil.printing(map.get("barCode").toString());
		if (!res) {
			throw new Exception(map.get("barCode") + "打印失败！");
		}

		Integer resNum = dao.updataAllMaterialBarcode(Integer.parseInt(map.get("id").toString()));
		return true;
	}

	/**
	 * 查询条码
	 *
	 * @param listNo
	 * @param materialId
	 * @return
	 */
	@Override
	public List<JSONObject> findBarCode(String listNo, int materialId) {
		return dao.findBarCode(listNo, materialId);
	}

	/**
	 * 出库
	 *
	 * @throws Exception
	 */
	@Override
	public void MaterialOutbound(int id, int locationId) throws Exception {
		// 通过库位id查询动作标记count
		int result = dao.findRInTaskqueueFlagCountByLocationId(locationId);
		if (result == 0) {
			// 修改临时队列表动作标记
			result = dao.updateRInTaskqueueFlag(id);
			if (result != 1) {
				throw new Exception("修改临时队列表动作标记失败了");
			}
		} else {
			throw new Exception("该库位当前正在操作中，请稍后再试。");
		}

	}

	/**
	 * 更新库位状态
	 *
	 * @param locationId
	 * @param locationStatus
	 */
	@Override
	public void updateLocationStatus(int locationId, int locationStatus) throws Exception {
		if (dao.updateLocationStatus(locationId, locationStatus) != 1) {
			throw new Exception("更新失败");
		}
	}

}
