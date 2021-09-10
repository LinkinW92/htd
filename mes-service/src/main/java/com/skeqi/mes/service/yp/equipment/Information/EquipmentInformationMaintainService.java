package com.skeqi.mes.service.yp.equipment.Information;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yinp
 * @explain 保养
 * @date 2020-12-18
 */
public interface EquipmentInformationMaintainService {

	/**
	 * 新增
	 *
	 * @param json
	 * @throws Exception
	 */
	public void add(JSONObject json) throws Exception;

}
