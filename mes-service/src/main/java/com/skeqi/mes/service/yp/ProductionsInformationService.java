package com.skeqi.mes.service.yp;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yinp
 * @explain 生产信息
 * @date 2021-2-20
 */
public interface ProductionsInformationService {

	/**
	 * @explain 查询
	 * @return
	 */
	List<JSONObject> list();

	/**
	 * @explain 一键关机
	 * @param ip
	 */
	void shutDown(List<JSONObject> list) throws Exception;
}
