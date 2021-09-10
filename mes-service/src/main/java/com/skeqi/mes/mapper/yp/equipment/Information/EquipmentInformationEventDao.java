package com.skeqi.mes.mapper.yp.equipment.Information;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yinp
 * @explain 设备事件
 * @date 2020-12-18
 */
public interface EquipmentInformationEventDao {

	/**
	 * @explain 查询事件集合
	 * @param parentId
	 * @return
	 */
	public List<JSONObject> list(@Param("parentId")int parentId);

	/**
	 * @explain 新增事件
	 * @param parentId
	 * @param event
	 */
	public int add(@Param("parentId")int parentId, @Param("event")int event);

}
