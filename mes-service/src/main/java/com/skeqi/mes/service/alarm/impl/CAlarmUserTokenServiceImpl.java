package com.skeqi.mes.service.alarm.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.alarm.CAlarmUserTokenDao;
import com.skeqi.mes.pojo.CMesUserT;
import com.skeqi.mes.pojo.alarm.CAlarmIpWhiteList;
import com.skeqi.mes.service.alarm.CAlarmUserTokenService;
import com.skeqi.mes.util.yp.TokenUtil;

@Service
public class CAlarmUserTokenServiceImpl implements CAlarmUserTokenService {

	@Autowired
	CAlarmUserTokenDao dao;

	@Override
	public JSONObject findUserToken(String userName) {
		// TODO Auto-generated method stub
		return dao.findUserToken(userName);
	}

	@Override
	public CMesUserT findUserByToken(String token) {
		// TODO Auto-generated method stub
		return dao.findUserByToken(token);
	}

	@Override
	public Integer updateUserToken(String userName) {
		String token = TokenUtil.randomToken(36);
		return dao.updateUserToken(userName, token);
	}

	@Override
	public CAlarmIpWhiteList findIpWhiteList(String userName) {
		// TODO Auto-generated method stub
		return dao.findIpWhiteList(userName);
	}

	@Override
	public Integer addIpWhiteList(String userName, String ip) throws Exception {
		// TODO Auto-generated method stub
		CMesUserT user = dao.findUserByName(userName);
		if(user==null) {
			throw new Exception("用户不存在");
		}

		return dao.addIpWhiteList(user.getId(), ip);
	}

	@Override
	public Integer updateIpWhiteList(String userName, String ip) throws Exception {
		// TODO Auto-generated method stub

		CMesUserT user = dao.findUserByName(userName);
		if(user==null) {
			throw new Exception("用户不存在");
		}

		return dao.updateIpWhiteList(user.getId(), ip);
	}

}
