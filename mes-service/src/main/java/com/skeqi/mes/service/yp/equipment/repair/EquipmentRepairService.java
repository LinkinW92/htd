package com.skeqi.mes.service.yp.equipment.repair;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yinp
 * @explain 设备维修
 * @date 2021年3月11日
 */
public interface EquipmentRepairService {

	/**
	 * 查询
	 *
	 * @param json
	 * @return
	 */
	public List<JSONObject> list(JSONObject json);

	/**
	 * 新增
	 *
	 * @param json
	 * @return
	 */
	public void add(JSONObject json) throws Exception;

	/**
	 * 更新
	 *
	 * @param json
	 * @return
	 */
	public void update(JSONObject json) throws Exception;

	/**
	 * 删除
	 *
	 * @param id
	 * @return
	 */
	public void delete(int id) throws Exception;

	/**
	 * 查询所有产线跟设备
	 * @return
	 */
	public List<JSONObject> lineAndEquipment();

}
