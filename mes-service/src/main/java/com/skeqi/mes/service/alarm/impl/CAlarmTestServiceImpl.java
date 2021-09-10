package com.skeqi.mes.service.alarm.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.mapper.alarm.CAlarmTestDao;
import com.skeqi.mes.pojo.CMesUserT;
import com.skeqi.mes.pojo.alarm.CAlarmEvent;
import com.skeqi.mes.pojo.alarm.CAlarmProblemLevel;
import com.skeqi.mes.service.alarm.CAlarmTestService;

@Service
public class CAlarmTestServiceImpl implements CAlarmTestService {

	@Autowired
	CAlarmTestDao dao;

	@Override
	public Integer addProblem(JSONObject json) throws Exception {

		CMesUserT user = dao.findUserByName(json.getString("userName"));

		if(user==null) {
			throw new Exception("用户不存在");
		}

		json.put("establishUserId", user.getId());

		return dao.addProblem(json);
	}

	@Override
	public List<CAlarmProblemLevel> findProblemLevelList() {
		// TODO Auto-generated method stub
		return dao.findProblemLevelList();
	}

	@Override
	public List<CAlarmEvent> findAlarmEventList() {
		// TODO Auto-generated method stub
		return dao.findAlarmEventList();
	}

}
