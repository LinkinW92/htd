package com.skeqi.mes.mapper.authorization;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yinp
 * @explain 申请授权
 * @date 2020-11-6
 */
public interface ApplicationForAuthorizationDao {

	/**
	 * @explain 保存校验记录
	 * @param json
	 * @return
	 */
	public int addCheck(JSONObject json);

	/**
	 * @explain 查询最后一次登录校验的数据
	 * @return
	 */
	public JSONObject findMaxId();

}
