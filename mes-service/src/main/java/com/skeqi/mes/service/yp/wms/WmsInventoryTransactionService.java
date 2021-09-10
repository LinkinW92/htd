package com.skeqi.mes.service.yp.wms;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yinp
 * @explain 库存交易记录
 * @date 2021-07-15
 */
public interface WmsInventoryTransactionService {

	/**
	 * 新增
	 * @param json
	 * @return
	 */
	public void add(JSONObject json) throws Exception;

}
