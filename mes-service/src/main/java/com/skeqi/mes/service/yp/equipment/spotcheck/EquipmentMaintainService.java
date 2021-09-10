package com.skeqi.mes.service.yp.equipment.spotcheck;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * 设备保养
 *
 * @author yinp
 * @Date 2021年3月6日
 */
public interface EquipmentMaintainService {

	/**
	 * 查询保养记录集合
	 *
	 * @param json
	 * @return
	 */
	public List<JSONObject> list(JSONObject json);

	/**
	 * 新增保养记录
	 *
	 * @param json
	 * @return
	 */
	public void add(JSONObject json) throws Exception;

	/**
	 * 更新保养记录
	 *
	 * @param json
	 * @return
	 */
	public void update(JSONObject json) throws Exception;

	/**
	 * 查询所有产线及设备
	 *
	 * @return
	 */
	public List<JSONObject> findLineAndEquipment();

}
