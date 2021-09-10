package com.skeqi.mes.service.alarm.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.mapper.alarm.CAlarmNotificationChannelsDao;
import com.skeqi.mes.pojo.CMesUserT;
import com.skeqi.mes.pojo.alarm.CAlarmNotificationChannels;
import com.skeqi.mes.pojo.alarm.CAlarmNotificationChannelsType;
import com.skeqi.mes.service.alarm.CAlarmNotificationChannelsService;
import com.skeqi.mes.util.yp.CheckUtil;

/**
 * 通知渠道
 *
 * @author yinp
 *
 */
@Service
public class CAlarmNotificationChannelsServiceImpl implements CAlarmNotificationChannelsService {

	@Autowired
	CAlarmNotificationChannelsDao dao;

	@Override
	public List<CAlarmNotificationChannelsType> findNotificationChannelsTypeList() {
		// TODO Auto-generated method stub
		return dao.findNotificationChannelsTypeList();
	}

	@Override
	public PageInfo<CAlarmNotificationChannels> findNotificationChannelsList(String userName, Integer pageNumber,
			Integer pageSize) throws Exception {
		CMesUserT user = dao.findUserByName(userName);
		if (user == null) {
			throw new Exception("用户不存在");
		}

		PageHelper.startPage(pageNumber, pageSize);
		List<CAlarmNotificationChannels> list = dao.findNotificationChannelsList(user.getId());
		PageInfo<CAlarmNotificationChannels> pageInfo = new PageInfo<CAlarmNotificationChannels>(list);

		return pageInfo;
	}

	@Override
	public Integer addNotificationChannels(JSONObject json) throws Exception {
		CMesUserT user = dao.findUserByName(json.getString("userName"));
		if (user == null) {
			throw new Exception("用户不存在");
		}

		switch (json.getInteger("notificationChannelsTypeId")) {
		case 1:
			CheckUtil.email(json.getString("notificationChannelsContent"));
			break;
		case 2:
			CheckUtil.phone(json.getString("notificationChannelsContent"));
			break;
		case 3:
			CheckUtil.phone(json.getString("notificationChannelsContent"));
			break;
		}

		CAlarmNotificationChannels dx = new CAlarmNotificationChannels();
		dx.setUserId(user.getId());
		dx.setNotificationChannels(json.getString("notificationChannels"));
		dx.setNotificationChannelsTypeId(json.getInteger("notificationChannelsTypeId"));
		dx.setNotificationChannelsContent(json.getString("notificationChannelsContent"));

		Integer countNumber = dao.findNotificationChannels(dx);
		if (countNumber > 0) {
			throw new Exception("通知渠道已存在");
		}

		return dao.addNotificationChannels(dx);
	}

	@Override
	public Integer updateNotificationChannels(JSONObject json) throws Exception {

		switch (json.getInteger("notificationChannelsTypeId")) {
		case 1:
			CheckUtil.email(json.getString("notificationChannelsContent"));
			break;
		case 2:
			CheckUtil.phone(json.getString("notificationChannelsContent"));
			break;
		case 3:
			CheckUtil.phone(json.getString("notificationChannelsContent"));
			break;
		}

		CAlarmNotificationChannels dx = new CAlarmNotificationChannels();
		dx.setId(json.getInteger("id"));
		dx.setNotificationChannelsTypeId(json.getInteger("notificationChannelsTypeId"));
		dx.setNotificationChannelsContent(json.getString("notificationChannelsContent"));

		Integer countNumber = dao.findNotificationChannels(dx);
		if (countNumber > 0) {
			throw new Exception("通知渠道已存在");
		}

		dx.setNotificationChannels(json.getString("notificationChannels"));

		return dao.updateNotificationChannels(dx);
	}

	@Override
	public Integer deleteNotificationChannels(Integer id) {
		// TODO Auto-generated method stub
		return dao.deleteNotificationChannels(id);
	}

}
