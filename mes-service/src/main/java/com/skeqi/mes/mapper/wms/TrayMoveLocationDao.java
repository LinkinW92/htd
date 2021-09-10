package com.skeqi.mes.mapper.wms;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.wms.CWmsAreaT;
import com.skeqi.mes.pojo.wms.CWmsLocationT;
import com.skeqi.mes.pojo.wms.CWmsReservoirAreaT;
import com.skeqi.mes.pojo.wms.WarehouseT;

/**
 * 托盘移库
 * @author yinp
 *
 */
public interface TrayMoveLocationDao {

	//仓库
	public List<JSONObject> findWarehouseList();
	//区域
	public List<JSONObject> findAreaList(@Param("warehouseId")int warehouseId);
	//库区
	public List<JSONObject> findreservoirAreaList(@Param("areaId")int areaId);
	//库位
	public List<JSONObject> findlocationList(JSONObject json);
	//更新库位
	public Integer updateMaterialNumberLocationId(
			@Param("outLocation")Integer outLocation,
			@Param("enterLocation")Integer enterLocation);

	//通过库位id查询库位
	public CWmsLocationT findLocation(@Param("locationId")Integer locationId);

	//修改库位状态跟托盘码
	public Integer updateLocationStateAndTray(
			@Param("locationId")Integer locationId,
			@Param("state")Integer state,
			@Param("tray")String tray);

}
