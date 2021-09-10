package com.skeqi.mes.service.yp.wms.other.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.yp.wms.other.OtherWmsCrmProjectDao;
import com.skeqi.mes.service.yp.wms.other.OtherWmsCrmProjectService;

/**
 * @author yinp
 * @explain Crm项目
 * @date 2021-07-29
 */
@Service
public class OtherWmsCrmProjectServiceImpl implements OtherWmsCrmProjectService {

	@Autowired
	OtherWmsCrmProjectDao dao;

	/**
	 * 查询所有项目
	 * @return
	 */
	@Override
	public List<JSONObject> findAll() {
		return dao.findAll();
	}

}
