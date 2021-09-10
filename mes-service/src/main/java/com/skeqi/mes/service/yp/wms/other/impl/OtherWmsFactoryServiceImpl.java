package com.skeqi.mes.service.yp.wms.other.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.wms.other.OtherWmsFactoryDao;
import com.skeqi.mes.service.yp.wms.other.OtherWmsFactoryService;

/**
 * 工厂
 * @author yinp
 * @date 2021年7月26日
 */
@Service
public class OtherWmsFactoryServiceImpl implements OtherWmsFactoryService {

	@Autowired
	OtherWmsFactoryDao dao;

	/**
	 * 查询所有工厂
	 * @return
	 */
	@Override
	public List<JSONObject> findAll() {
		return dao.findAll();
	}

}
