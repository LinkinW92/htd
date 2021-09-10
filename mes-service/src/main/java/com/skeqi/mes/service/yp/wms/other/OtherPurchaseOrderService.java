package com.skeqi.mes.service.yp.wms.other;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yinp
 * @explain 采购单
 * @date 2021-07-15
 */
public interface OtherPurchaseOrderService {

	/**
	 * 查询
	 * @param json
	 * @return
	 */
	public List<JSONObject> list(JSONObject json);

	/**
	 * 查询行
	 * @param json
	 * @return
	 */
	public List<JSONObject> listR(JSONObject json);

}
