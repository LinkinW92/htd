package com.skeqi.mes.service.yp.equipment.Information;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yinp
 * @explain 点检
 * @date 2020-12-18
 */
public interface EquipmentInformationSpotCheckService {

	/**
	 * 新增
	 *
	 * @param json
	 * @throws Exception
	 */
	public void add(JSONObject json) throws Exception;

}
