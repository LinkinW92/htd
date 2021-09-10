package com.skeqi.mes.mapper.yp.wms;

import com.alibaba.fastjson.JSONObject;

/**
 * @author yinp
 * @explain 库存交易记录
 * @date 2021-07-15
 */
public interface WmsInventoryTransactionDao {

	/**
	 * 新增
	 * @param json
	 * @return
	 */
	public int add(JSONObject json);

}
