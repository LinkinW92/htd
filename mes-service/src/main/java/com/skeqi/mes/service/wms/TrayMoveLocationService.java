package com.skeqi.mes.service.wms;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.wms.CWmsAreaT;
import com.skeqi.mes.pojo.wms.CWmsLocationT;
import com.skeqi.mes.pojo.wms.CWmsReservoirAreaT;
import com.skeqi.mes.pojo.wms.WarehouseT;

/**
 * 托盘移库
 *
 * @author yinp
 *
 */
public interface TrayMoveLocationService {

	// 仓库
	public List<JSONObject> findWarehouseList();

	// 区域
	public List<JSONObject> findAreaList(int warehouseId);

	// 库区
	public List<JSONObject> findreservoirAreaList(int areaId);

	// 库位
	public List<JSONObject> findlocationList(JSONObject json);

	//提交
	public boolean submit(JSONObject json)throws Exception ;

}
