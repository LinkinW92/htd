package com.skeqi.mes.service.wll;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skeqi.mes.mapper.wll.RegisterTDao;
import com.skeqi.mes.pojo.RegisterT;
@Service
@Transactional
public class RegisterTServiceImpl implements RegisterTService {

	@Autowired
	RegisterTDao registerTDao;

	@Override
	public List<RegisterT> registertList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return registerTDao.registerTList(map);
	}

	@Override
	public void delRegisterT(Map<String, Object> map) {
		// TODO Auto-generated method stub
		registerTDao.delregisterT(map);
	}

	@Override
	public void updateRegisterT(Map<String, Object> map) {
		// TODO Auto-generated method stub
		registerTDao.updateregisterT(map);
	}

	@Override
	public void addRegisterT(Map<String, Object> map) {
		// TODO Auto-generated method stub
		registerTDao.addRegisterT(map);
	}

	@Override
	public int queryRegisterT(String hostName) {
		// TODO Auto-generated method stub
		return registerTDao.queryRegisterT(hostName);
	}

	@Override
	public String queryHostName(int id) {
		// TODO Auto-generated method stub
		return registerTDao.queryHostName(id);
	}
}
