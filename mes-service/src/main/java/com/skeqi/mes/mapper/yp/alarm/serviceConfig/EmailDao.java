package com.skeqi.mes.mapper.yp.alarm.serviceConfig;

import com.alibaba.fastjson.JSONObject;

/**
 * 邮箱配置
 *
 * @author yinp
 * @Data 2021年3月22日
 */
public interface EmailDao {

	/**
	 * 查询邮箱配置
	 * @return
	 */
	public JSONObject find();

	/**
	 * 更新邮箱配置
	 * @return
	 */
	public void update(JSONObject json);

}
