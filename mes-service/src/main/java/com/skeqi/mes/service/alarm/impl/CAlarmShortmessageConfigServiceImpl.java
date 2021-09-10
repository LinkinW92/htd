package com.skeqi.mes.service.alarm.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.alarm.CAlarmShortmessageConfigDao;
import com.skeqi.mes.pojo.CMesUserT;
import com.skeqi.mes.pojo.alarm.CAlarmShortmessageConfig;
import com.skeqi.mes.service.alarm.CAlarmShortmessageConfigService;

@Service
public class CAlarmShortmessageConfigServiceImpl implements CAlarmShortmessageConfigService {

	@Autowired
	CAlarmShortmessageConfigDao dao;

	@Override
	public List<CAlarmShortmessageConfig> findShortmessageConfigList(String userName) {
		// TODO Auto-generated method stub
		return dao.findShortmessageConfigList(userName);
	}

	@Override
	public Integer addShortmessageConfig(CAlarmShortmessageConfig dx,String userName) throws Exception {
		CMesUserT user = dao.findUserByName(userName);
		if(user==null) {
			throw new Exception("用户不存在");
		}
		dx.setUserId(user.getId());
		return dao.addShortmessageConfig(dx);
	}

	@Override
	public Integer updateShortmessageConfig(CAlarmShortmessageConfig dx,String userName)throws Exception {
		CMesUserT user = dao.findUserByName(userName);
		if(user==null) {
			throw new Exception("用户不存在");
		}
		dx.setUserId(user.getId());
		return dao.updateShortmessageConfig(dx);
	}

	@Override
	public Integer deleteShortmessageConfig(Integer id) {
		// TODO Auto-generated method stub
		return dao.deleteShortmessageConfig(id);
	}

}
