package com.skeqi.mes.service.yp.equipment.checkdata;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * 点检记录详情
 *
 * @date2021年3月11日
 * @author yinp
 */
public interface CheckDataDetailedService {

	/**
	 * 查询点检记录详情
	 * @param checkDataId
	 * @return
	 */
	public List<JSONObject> list(int checkDataId);

	/**
	 * 更新点检记录详情
	 * @param json
	 * @return
	 */
	public void update(JSONObject json) throws Exception;

}
