package com.skeqi.mes.mapper.yp.equipment.repair;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yinp
 * @explain 设备维修
 * @date 2021年3月11日
 */
public interface EquipmentRepairDao {

	/**
	 * 查询
	 * @param json
	 * @return
	 */
	public List<JSONObject> list(JSONObject json);

	/**
	 * 新增
	 * @param json
	 * @return
	 */
	public int add(JSONObject json);

	/**
	 * 更新
	 * @param json
	 * @return
	 */
	public int update(JSONObject json);

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public int delete(@Param("id") int id);

	/**
	 * 查询所有产线跟设备
	 * @return
	 */
	public List<JSONObject> lineAndEquipment();

}
