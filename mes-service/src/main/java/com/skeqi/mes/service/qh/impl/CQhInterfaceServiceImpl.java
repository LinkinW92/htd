package com.skeqi.mes.service.qh.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.qh.CQhInterfaceDao;
import com.skeqi.mes.service.qh.CQhInterfaceService;

/**
 * @author yinp
 * @explain 菜单配置--接口操作
 * @date 2020-10-15
 */
@Service
public class CQhInterfaceServiceImpl implements CQhInterfaceService {

	@Autowired
	CQhInterfaceDao dao;

	@Override
	public int addInterface(JSONObject json) {
		// TODO Auto-generated method stub
		return dao.addInterface(json);
	}

	@Override
	public int deleteInterface(int id) {
		// TODO Auto-generated method stub
		return dao.deleteInterface(id);
	}

	@Override
	public int updateInterface(JSONObject json) {
		// TODO Auto-generated method stub
		return dao.updateInterface(json);
	}

}
