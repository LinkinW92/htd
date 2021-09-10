package com.skeqi.mes.mapper.wms;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.wms.CWmsLocationT;
import com.skeqi.mes.pojo.wms.CWmsMaterialNumberT;

/**
 * 托盘管理
 * @author yinp
 *
 */
public interface TrayManagementDao {

	/**
	 * 查询
	 * @param map
	 * @return
	 */
	public List<JSONObject> findList(JSONObject json);

	/**
	 * 查询库位id+name集合
	 * @return
	 */
	public List<CWmsLocationT> findLocationListIdAndName();

	/**
	 * 通过库位id查询物料库存
	 * @param locationId
	 * @return
	 */
	public List<JSONObject> findMaterialNumberList(@Param("locationId")Integer locationId);

	/**
	 * 通过id查询库位
	 * @param locationId
	 * @return
	 */
	public CWmsLocationT findLocation(@Param("locationId")Integer locationId);

	/**
	 * 修改库位信息
	 * @param json
	 * @return
	 */
	public Integer updateLocation(
			@Param("locationId")Integer locationId,
			@Param("state")Integer state,
			@Param("tray")String tray);

}
