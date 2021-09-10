package com.skeqi.mes.mapper.yp.equipment.Information;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yinp
 * @explain 保养
 * @date 2020-12-18
 */
public interface EquipmentInformationMaintainDao {

	/**
	 * 新增
	 *
	 * @param json
	 * @return
	 */
	public int add(JSONObject json);

	/**
	 * 通过设备id查询出需要保养的保养项
	 *
	 * @param equipmentId
	 * @return
	 */
	public List<JSONObject> itemList(@Param("equipmentId") int equipmentId);

	/**
	 * 新增保养记录详情
	 *
	 * @param sql
	 * @return
	 */
	public int addCheckDataDetailed(@Param("sql") String sql);

	/**
	 * 更新设备表保养时间
	 * @param json
	 * @return
	 */
	public int updateSpotCheckDate(JSONObject json);

}
