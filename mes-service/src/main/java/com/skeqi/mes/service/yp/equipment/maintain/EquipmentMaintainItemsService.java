package com.skeqi.mes.service.yp.equipment.maintain;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * 设备保养项
 *
 * @Date 2021年3月11日
 * @author yinp
 */
public interface EquipmentMaintainItemsService {

	/**
	 * 查询配置项集合
	 *
	 * @param editionId
	 * @return
	 */
	public List<JSONObject> list(int editionId);

	/**
	 * 新增保养项
	 *
	 * @param json
	 * @return
	 */
	public void add(JSONObject json) throws Exception;

	/**
	 * 更新保养项
	 *
	 * @param json
	 * @return
	 */
	public void update(JSONObject json) throws Exception;

	/**
	 * 删除保养项
	 *
	 * @param json
	 * @return
	 */
	public void delete(int id) throws Exception;

}
