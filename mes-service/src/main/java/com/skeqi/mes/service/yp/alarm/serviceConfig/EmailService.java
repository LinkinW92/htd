package com.skeqi.mes.service.yp.alarm.serviceConfig;

import com.alibaba.fastjson.JSONObject;

/**
 * 邮箱配置
 *
 * @author yinp
 * @Data 2021年3月22日
 */
public interface EmailService {

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

	/**
	 * 测试邮箱配置
	 * @param json
	 */
	public void test(JSONObject json) throws Exception;

}
