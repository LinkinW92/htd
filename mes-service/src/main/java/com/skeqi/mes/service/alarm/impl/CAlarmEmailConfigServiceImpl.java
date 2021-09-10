package com.skeqi.mes.service.alarm.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.alarm.CAlarmEmailConfigDao;
import com.skeqi.mes.pojo.CMesUserT;
import com.skeqi.mes.pojo.alarm.CAlarmEmailConfig;
import com.skeqi.mes.service.alarm.CAlarmEmailConfigService;

@Service
public class CAlarmEmailConfigServiceImpl implements CAlarmEmailConfigService {

	@Autowired
	CAlarmEmailConfigDao dao;

	@Override
	public List<CAlarmEmailConfig> findEmailConfigList(String userName) {
		// TODO Auto-generated method stub
		return dao.findEmailConfigList(userName);
	}

	@Override
	public Integer addEmailConfig(CAlarmEmailConfig dx, String userName) throws Exception {
		CMesUserT user = dao.findUserByName(userName);
		if(user==null) {
			throw new Exception("用户不存在");
		}
		dx.setUserId(user.getId());
		return dao.addEmailConfig(dx);
	}

	@Override
	public Integer updateEmailConfig(CAlarmEmailConfig dx, String userName) throws Exception {
		CMesUserT user = dao.findUserByName(userName);
		if(user==null) {
			throw new Exception("用户不存在");
		}
		dx.setUserId(user.getId());
		return dao.updateEmailConfig(dx);
	}

	@Override
	public Integer deleteEmailConfig(Integer id) {
		// TODO Auto-generated method stub
		return dao.deleteEmailConfig(id);
	}

}
