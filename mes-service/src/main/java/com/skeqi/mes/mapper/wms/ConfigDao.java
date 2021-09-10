package com.skeqi.mes.mapper.wms;

import com.alibaba.fastjson.JSONObject;

/**
 *
 * @author yinp
 * @date 2021年4月25日
 *
 */
public interface ConfigDao {

	/**
	 * 查询
	 * @return
	 */
	public JSONObject select();

	/**
	 * 更新
	 * @return
	 */
	public int update(JSONObject json);

}
