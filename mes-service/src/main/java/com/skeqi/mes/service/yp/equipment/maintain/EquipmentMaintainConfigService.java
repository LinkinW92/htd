package com.skeqi.mes.service.yp.equipment.maintain;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * 设备保养配置
 *
 * @author yinp
 * @Date 2021年3月11日
 */
public interface EquipmentMaintainConfigService {

	/**
	 * 查询保养配置数据集合
	 *
	 * @param json
	 * @return
	 */
	public List<JSONObject> list(JSONObject json);

	/**
	 * 查询所有产线及设备
	 *
	 * @return
	 */
	public List<JSONObject> findLineAndEquipment();

	/**
	 * 新增保养配置
	 *
	 * @param json
	 * @return
	 */
	public void add(JSONObject json) throws Exception;

	/**
	 * 更新保养配置
	 *
	 * @param json
	 * @return
	 */
	public void update(JSONObject json) throws Exception;

	/**
	 * 删除保养配置
	 *
	 * @param json
	 * @return
	 */
	public void delete(int id, int equipmentId) throws Exception;

	/**
	 * 查询版本号
	 *
	 * @param equipmentId
	 * @return
	 */
	public List<JSONObject> findEdition(int equipmentId);

}
