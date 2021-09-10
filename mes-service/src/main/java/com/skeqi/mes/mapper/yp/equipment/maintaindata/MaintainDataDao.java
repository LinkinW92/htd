package com.skeqi.mes.mapper.yp.equipment.maintaindata;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * 保养记录
 *
 * @date2021年3月12日
 * @author yinp
 */
public interface MaintainDataDao {

	/**
	 * 查询
	 *
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
	 * 通过设备id查询出需要保养的保养项
	 * @param equipmentId
	 * @return
	 */
	public List<JSONObject> itemList(@Param("equipmentId")int equipmentId);

	/**
	 * 新增保养记录详情
	 * @param sql
	 * @return
	 */
	public int addCheckDataDetailed(@Param("sql")String sql);

	/**
	 * 更新
	 * @param json
	 * @return
	 */
	public int update(JSONObject json);

	/**
	 * 查询所有产线跟设备
	 * @return
	 */
	public List<JSONObject> lineAndEquipment();

	/**
	 * 提交
	 * @param id
	 * @return
	 */
	public int sub(@Param("id")int id);

}
