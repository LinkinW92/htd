package com.skeqi.mes.service.yp.equipment.maintaindata;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * 保养记录详情
 *
 * @date2021年3月12日
 * @author yinp
 */
public interface MaintainDataDetailedService {

	/**
	 * 查询保养记录详情
	 * @param checkDataId
	 * @return
	 */
	public List<JSONObject> list(int checkDataId);

	/**
	 * 更新保养记录详情
	 * @param json
	 * @return
	 */
	public void update(JSONObject json) throws Exception;

}
