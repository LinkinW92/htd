package com.skeqi.mes.mapper.yp.wms.other;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * 工单
 * @author yinp
 * @date 2021年8月19日
 */
public interface OtherWorkOrderDao {

	/**
	 * 查询工单
	 * @param json
	 * @return
	 */
	public List<JSONObject> findWorkOrder(JSONObject json);

}
