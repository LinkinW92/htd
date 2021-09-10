package com.skeqi.mes.service.qh.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.qh.CQhAuthorityInterfaceDao;
import com.skeqi.mes.pojo.qh.CQhAuthorityInterfaceT;
import com.skeqi.mes.pojo.qh.CQhMenuT;
import com.skeqi.mes.service.qh.CQhAuthorityInterfaceService;

/**
 * @author yinp
 * @explain 模块接口
 * @date 2020-9-9
 */
@Service
public class CQhAuthorityInterfaceServiceImpl implements CQhAuthorityInterfaceService {

	@Autowired
	CQhAuthorityInterfaceDao dao;

	@Override
	public List<CQhAuthorityInterfaceT> queryAuthorityInterfaceList(JSONObject json) {
		// TODO Auto-generated method stub
		return dao.queryAuthorityInterfaceList(json);
	}

	@Override
	public Integer addAuthorityInterface(JSONObject json) {
		// TODO Auto-generated method stub
		return dao.addAuthorityInterface(json);
	}

	@Override
	public Integer updateAuthorityInterface(JSONObject json) {
		// TODO Auto-generated method stub
		return dao.updateAuthorityInterface(json);
	}

	@Override
	public Integer deleteAuthorityInterface(Integer id) {
		// TODO Auto-generated method stub
		return dao.deleteAuthorityInterface(id);
	}

	@Override
	public List<CQhMenuT> findMenuAll() {
		// TODO Auto-generated method stub
		return dao.findMenuAll();
	}

	@Override
	public List<CQhMenuT> findMenuBySuperiorMenuIdList(Integer superiorMenuId) {
		// TODO Auto-generated method stub
		return dao.findMenuBySuperiorMenuIdList(superiorMenuId);
	}

}
