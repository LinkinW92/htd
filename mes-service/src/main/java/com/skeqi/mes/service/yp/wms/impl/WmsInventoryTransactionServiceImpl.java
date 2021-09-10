package com.skeqi.mes.service.yp.wms.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.wms.WmsInventoryTransactionDao;
import com.skeqi.mes.service.yp.wms.WmsInventoryTransactionService;

/**
 * @author yinp
 * @explain 库存交易记录
 * @date 2021-07-15
 */
@Service
public class WmsInventoryTransactionServiceImpl implements WmsInventoryTransactionService {

	@Autowired
	WmsInventoryTransactionDao dao;

	/**
	 * 新增
	 *
	 * @param json
	 * @return
	 */
	@Override
	public void add(JSONObject json) throws Exception {
		if(dao.add(json)!=1) {
			throw new Exception("新增失败");
		}
	}

}
