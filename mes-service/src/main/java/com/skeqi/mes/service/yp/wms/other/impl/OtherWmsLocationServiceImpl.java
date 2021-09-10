package com.skeqi.mes.service.yp.wms.other.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.wms.other.OtherWmsLocationDao;
import com.skeqi.mes.service.yp.wms.other.OtherWmsLocationService;

/**
 * 库位
 * @author yinp
 * @date 2021年7月26日
 */
@Service
public class OtherWmsLocationServiceImpl implements OtherWmsLocationService {

	@Autowired
	OtherWmsLocationDao dao;

	/**
	 * 查询所有库位
	 * @return
	 */
	@Override
	public List<JSONObject> findAll() {
		return dao.findAll();
	}

}
