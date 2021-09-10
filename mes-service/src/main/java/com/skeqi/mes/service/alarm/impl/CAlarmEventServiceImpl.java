package com.skeqi.mes.service.alarm.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.mapper.alarm.CAlarmEventDao;
import com.skeqi.mes.pojo.CMesUserT;
import com.skeqi.mes.pojo.alarm.CAlarmEvent;
import com.skeqi.mes.pojo.alarm.CAlarmNotificationChannels;
import com.skeqi.mes.pojo.alarm.CAlarmNotificationChannelsType;
import com.skeqi.mes.pojo.alarm.CAlarmNotificationMethod;
import com.skeqi.mes.service.alarm.CAlarmEventService;
import com.skeqi.mes.util.yp.CheckUtil;

@Service
public class CAlarmEventServiceImpl implements CAlarmEventService {

	@Autowired
	CAlarmEventDao dao;

	@Override
	public PageInfo<CAlarmEvent> findAlarmEventList(String userName, Integer pageNumber, Integer pageSize)
			throws Exception {
		CMesUserT user = dao.findUserByName(userName);
		if (user == null) {
			throw new Exception("用户不存在");
		}

		PageHelper.startPage(pageNumber, pageSize);
		List<CAlarmEvent> list = dao.findAlarmEventList(user.getId());
		PageInfo<CAlarmEvent> pageInfo = new PageInfo<CAlarmEvent>(list);

		return pageInfo;
	}

	@Override
	public Integer addAlarmEvent(JSONObject json) throws Exception {
		CMesUserT user = dao.findUserByName(json.getString("userName"));
		if (user == null) {
			throw new Exception("用户不存在");
		}
		CAlarmEvent dx = new CAlarmEvent();
		dx.setUserId(user.getId());
		dx.setEvent(json.getString("event"));
		dx.setNote(json.getString("note"));

		Integer countNumber = dao.findAlarmEvent(dx);
		if (countNumber > 0) {
			throw new Exception("该事件已存在");
		}

		return dao.addAlarmEvent(dx);
	}

	@Override
	public Integer updateAlarmEvent(JSONObject json) throws Exception {
		CAlarmEvent dx = new CAlarmEvent();
		dx.setId(json.getInteger("id"));
		dx.setEvent(json.getString("event1"));
		dx.setNote(json.getString("note"));

		if (!json.getString("event").equals(json.getString("event1"))) {
			Integer countNumber = dao.findAlarmEvent(dx);
			if (countNumber > 0) {
				throw new Exception("该事件已存在");
			}
		}

		dx.setEvent(json.getString("event1"));

		return dao.updateAlarmEvent(dx);
	}

	@Override
	public Integer deleteAlarmEvent(Integer id) {
		// 删除通知方式
		dao.deleteNnotificationMethod(null, id);
		return dao.deleteAlarmEvent(id);
	}

}
