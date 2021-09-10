package com.skeqi.mes.mapper.yp.equipment.Information;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yinp
 * @explain  设备维修
 * @date 2020-12-18
 */
public interface EquipmentInformationRepairDao {

	/**
	 * @explain 查询集合
	 * @param parentId
	 * @return
	 */
	public List<JSONObject> list(@Param("parentId")int parentId);

	/**
	 * @explain 新增维修记录
	 * @param json
	 */
	public int add(JSONObject json);

	/*==============以下是事件=============*/
	/**
	 * @explain 新增事件
	 * @param parentId
	 * @param event
	 */
	public int addEvent(@Param("parentId")int parentId, @Param("event")int event);

	/*==================以下是设备===============*/
	/**
	 * @explain 更新设备的上次维修时间
	 * @param parentId
	 * @return
	 */
	public int updateEquipmentLastRepairDate(@Param("parentId")int parentId);

}
