package com.skeqi.mes.service.wms;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.wms.TrayMoveLocationDao;
import com.skeqi.mes.pojo.wms.CWmsAreaT;
import com.skeqi.mes.pojo.wms.CWmsLocationT;
import com.skeqi.mes.pojo.wms.CWmsReservoirAreaT;
import com.skeqi.mes.pojo.wms.WarehouseT;
import com.skeqi.mes.util.ClientApiUseUtil;

/**
 * 托盘移库
 *
 * @author yinp
 *
 */
@Service
public class TrayMoveLocationServiceImpl implements TrayMoveLocationService {

	@Autowired
	TrayMoveLocationDao dao;

	/**
	 * 查询仓库
	 */
	@Override
	public List<JSONObject> findWarehouseList() {
		// TODO Auto-generated method stub
		return dao.findWarehouseList();
	}

	/**
	 * 查询区域
	 */
	@Override
	public List<JSONObject> findAreaList(int warehouseId) {
		// TODO Auto-generated method stub
		return dao.findAreaList(warehouseId);
	}

	/**
	 * 查询库区
	 */
	@Override
	public List<JSONObject> findreservoirAreaList(int areaId) {
		// TODO Auto-generated method stub
		return dao.findreservoirAreaList(areaId);
	}

	/**
	 * 查询库位
	 */
	@Override
	public List<JSONObject> findlocationList(JSONObject json) {
		// 入库还是出库，1移入 0移出
		Integer moveIn = json.getInteger("moveIn");
		Integer locationStatus = 5;

		if (moveIn == 1) {
			locationStatus = 0;
		}

		json.put("locationStatus", locationStatus);

		return dao.findlocationList(json);
	}

	@Override
	public boolean submit(JSONObject json) throws Exception {

		Integer outLocationId = json.getInteger("outLocation");
		Integer enterLocationId = json.getInteger("enterLocation");

		Integer result = dao.updateMaterialNumberLocationId(outLocationId, enterLocationId);
		if (result <= 0) {
			throw new Exception("更新库位数据出错了");
		}

		CWmsLocationT outLocation = dao.findLocation(outLocationId);
		dao.updateLocationStateAndTray(outLocation.getId(), 0, "");

		CWmsLocationT enterLocation = dao.findLocation(enterLocationId);
		dao.updateLocationStateAndTray(enterLocation.getId(), 5, outLocation.getTray());

		// 需要调用的接口名称
		json.put("methodName", "ControlStock");
		// 任务类型
		json.put("Mission_Type", 6);
		// 任务号
		json.put("Mission_ID", 1);
		// 货物尺寸
		json.put("Goods_Size", outLocation.getTrayType());
		// 条码值
		json.put("Pallet_Num", 1);
		// 取料X坐标
		json.put("From_Row", outLocation.getLocationX());
		// 取料Y坐标
		json.put("From_List", outLocation.getLocationY());
		// 取料Z坐标
		json.put("From_Layer", outLocation.getLocationZ());
		// 放料X坐标
		json.put("To_Row", enterLocation.getLocationX());
		// 放料y坐标
		json.put("To_List", enterLocation.getLocationY());
		// 放料z坐标
		json.put("To_Layer", enterLocation.getLocationZ());

		// 调用客户端接口
		json = ClientApiUseUtil.UseApi(json);

		if (json.getBoolean("remark")) {
			return true;
		} else {
			throw new Exception("调用客户端接口出现错误");
		}

	}

}
