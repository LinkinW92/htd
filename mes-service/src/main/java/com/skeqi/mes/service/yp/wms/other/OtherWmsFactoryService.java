package com.skeqi.mes.service.yp.wms.other;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * 工厂
 * @author yinp
 * @date 2021年7月26日
 */
public interface OtherWmsFactoryService {

	/**
	 * 查询所有工厂
	 * @return
	 */
	public List<JSONObject> findAll();

}
