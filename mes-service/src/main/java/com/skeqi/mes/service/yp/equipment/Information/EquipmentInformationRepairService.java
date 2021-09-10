package com.skeqi.mes.service.yp.equipment.Information;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yinp
 * @explain  设备维修
 * @date 2020-12-18
 */
public interface EquipmentInformationRepairService {

	/**
	 * @explain 查询集合
	 * @param parentId
	 * @return
	 */
	public List<JSONObject> list(int parentId);

	/**
	 * @explain 新增维修记录
	 * @param json
	 */
	public void add(JSONObject json) throws Exception;
}
