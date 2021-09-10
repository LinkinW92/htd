package com.skeqi.mes.service.yp.wms.other.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.wms.other.OtherPurchaseOrderDao;
import com.skeqi.mes.service.yp.wms.other.OtherPurchaseOrderService;

/**
 * @author yinp
 * @explain 采购单
 * @date 2021-07-15
 */
@Service
public class OtherPurchaseOrderServiceImpl implements OtherPurchaseOrderService {

	@Autowired
	OtherPurchaseOrderDao dao;

	/**
	 * 查询
	 * @param json
	 * @return
	 */
	@Override
	public List<JSONObject> list(JSONObject json) {
		return dao.list(json);
	}

	/**
	 * 查询行
	 * @param json
	 * @return
	 */
	@Override
	public List<JSONObject> listR(JSONObject json) {
		return dao.listR(json);
	}

}
