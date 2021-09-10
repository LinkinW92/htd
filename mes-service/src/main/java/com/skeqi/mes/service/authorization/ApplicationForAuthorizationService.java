package com.skeqi.mes.service.authorization;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yinp
 * @explain 申请授权
 * @date 2020-11-6
 */
public interface ApplicationForAuthorizationService {

	/**
	 * @explain 申请授权
	 * @param json
	 * @return
	 */
	public String apply(JSONObject json) throws Exception;

	/**
	 * @explain 校验
	 * @param code
	 * @return
	 */
	public String autograph(JSONObject json) throws Exception;

}
