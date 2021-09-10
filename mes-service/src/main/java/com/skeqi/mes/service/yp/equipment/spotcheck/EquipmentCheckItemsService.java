package com.skeqi.mes.service.yp.equipment.spotcheck;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * 设备点检项
 *
 * @Date 2021年3月4日
 * @author yinp
 */
public interface EquipmentCheckItemsService {

	/**
	 * 查询配置项集合
	 *
	 * @param editionId
	 * @return
	 */
	public List<JSONObject> list(int editionId);

	/**
	 * 新增点检项
	 *
	 * @param json
	 * @return
	 */
	public void add(JSONObject json) throws Exception;

	/**
	 * 更新点检项
	 *
	 * @param json
	 * @return
	 */
	public void update(JSONObject json) throws Exception;

	/**
	 * 删除点检项
	 *
	 * @param json
	 * @return
	 */
	public void delete(int id) throws Exception;

}
