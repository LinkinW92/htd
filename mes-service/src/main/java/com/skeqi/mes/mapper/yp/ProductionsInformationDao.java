package com.skeqi.mes.mapper.yp;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yinp
 * @explain 生产信息
 * @date 2021-2-20
 */
public interface ProductionsInformationDao {

	/**
	 * @explain 查询
	 * @return
	 */
	List<JSONObject> list();



}
