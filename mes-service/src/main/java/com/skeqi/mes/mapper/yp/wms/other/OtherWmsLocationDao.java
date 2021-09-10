package com.skeqi.mes.mapper.yp.wms.other;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * 库位
 * @author yinp
 * @date 2021年7月26日
 */
public interface OtherWmsLocationDao {

	/**
	 * 查询所有库位
	 * @return
	 */
	public List<JSONObject> findAll();

}
