package com.skeqi.mes.mapper.yp.wms.other;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yinp
 * @explain Crm项目
 * @date 2021-07-29
 */
public interface OtherWmsCrmProjectDao {

	/**
	 * 查询所有Crm项目
	 * @return
	 */
	public List<JSONObject> findAll();

}
