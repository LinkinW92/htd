package com.skeqi.mes.service.yp.equipment.spotcheck;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * 设备点检版本
 *
 * @author yinp Date 2021年3月6日
 */
public interface EquipmentCheckEditionService {

	/**
	 * 查询版本信息集合
	 *
	 * @param equipmentId
	 * @return
	 */
	public List<JSONObject> list(int equipmentId);

	/**
	 * 新增版本信息
	 *
	 * @param json
	 * @return
	 */
	public void add(JSONObject json) throws Exception;

	/**
	 * 更新版本信息
	 *
	 * @param json
	 * @return
	 */
	public void update(JSONObject json) throws Exception;

	/**
	 * 删除版本
	 *
	 * @param id
	 * @return
	 */
	public void delete(int id) throws Exception;

	/**
	 * 启用某个版本
	 *
	 * @param id
	 */
	public void qiYong(int id, int equipmentId) throws Exception;

	/**
	 * 查询闲置版本号
	 * @return
	 */
	public List<JSONObject> nullEditionList();


}
