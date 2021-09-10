package com.skeqi.mes.service.yp.wms.other.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.wms.other.OtherWorkOrderDao;
import com.skeqi.mes.service.yp.wms.other.OtherWorkOrderService;

/**
 * 工单
 * @author yinp
 * @date 2021年8月19日
 */
@Service
public class OtherWorkOrderServiceImpl implements OtherWorkOrderService {

	@Autowired
	OtherWorkOrderDao dao;

	/**
	 * 查询工单
	 * @param json
	 * @return
	 */
	@Override
	public List<JSONObject> findWorkOrder(JSONObject json) {
		return dao.findWorkOrder(json);
	}

}
