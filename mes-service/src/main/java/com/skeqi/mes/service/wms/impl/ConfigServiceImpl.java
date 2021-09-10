package com.skeqi.mes.service.wms.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.wms.ConfigDao;
import com.skeqi.mes.service.wms.ConfigService;

/**
 *
 * @author yinp
 * @date 2021年4月25日
 *
 */
@Service
public class ConfigServiceImpl implements ConfigService {

	@Autowired
	ConfigDao dao;

	/**
	 * 查询
	 *
	 * @return
	 */
	@Override
	public JSONObject select() {
//		return mapper.select();
		return null;
	}

	/**
	 * 更新
	 *
	 * @return
	 */
	@Override
	public void update(JSONObject json) throws Exception {
		if (dao.update(json) != 1) {
			throw new Exception("更新失败");
		}
	}

}
