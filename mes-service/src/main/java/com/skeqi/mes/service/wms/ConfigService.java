package com.skeqi.mes.service.wms;

import com.alibaba.fastjson.JSONObject;

/**
 *
 * @author yinp
 * @date 2021年4月25日
 *
 */
public interface ConfigService {

	/**
	 * 查询
	 *
	 * @return
	 */
	public JSONObject select();

	/**
	 * 更新
	 *
	 * @return
	 */
	public void update(JSONObject json) throws Exception;

}
