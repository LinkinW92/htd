package com.skeqi.mes.service.yp.equipment.Information;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yinp
 * @explain 设备事件
 * @date 2020-12-18
 */
public interface EquipmentInformationEventService {

	/**
	 * @explain 查询事件集合
	 * @param parentId
	 * @return
	 */
	public List<JSONObject> list(int parentId);

	/**
	 * @explain 新增事件
	 * @param parentId
	 * @param event
	 */
	public void add(int parentId, int event) throws Exception;

}
