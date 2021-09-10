package com.skeqi.mes.service.yp.oa;

import com.alibaba.fastjson.JSONObject;

/**
 * 钉钉
 *
 * @author yinp
 *
 */
public interface DingtalkService {

	/**
	 * 绑定用户
	 *
	 * @param json
	 */
	public JSONObject bindingUsers(JSONObject json) throws Exception;

	/**
	 * 更新用户信息
	 *
	 * @param json
	 */
	public void updateUser(JSONObject json) throws Exception;

}
