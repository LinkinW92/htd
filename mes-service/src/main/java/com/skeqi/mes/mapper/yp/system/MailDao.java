package com.skeqi.mes.mapper.yp.system;

import com.alibaba.fastjson.JSONObject;

/**
 * 邮件服务
 * @author yinp
 * @date 2021年8月11日
 */
public interface MailDao {

	/**
	 * 查询邮箱配置
	 * @return
	 */
	public JSONObject findEmailConfig();

}
