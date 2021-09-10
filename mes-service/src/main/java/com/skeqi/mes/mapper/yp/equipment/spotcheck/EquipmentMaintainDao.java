package com.skeqi.mes.mapper.yp.equipment.spotcheck;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * 设备保养
 *
 * @author yinp
 * @Date 2021年3月6日
 */
public interface EquipmentMaintainDao {

	/**
	 * 查询保养记录集合
	 * @param json
	 * @return
	 */
	public List<JSONObject> list(JSONObject json);

	/**
	 * 新增保养记录
	 * @param json
	 * @return
	 */
	public int add(JSONObject json);

	/**
	 * 更新保养记录
	 * @param json
	 * @return
	 */
	public int update(JSONObject json);

	/**
	 * 查询所有产线及设备
	 * @return
	 */
	public List<JSONObject> findLineAndEquipment();

}
