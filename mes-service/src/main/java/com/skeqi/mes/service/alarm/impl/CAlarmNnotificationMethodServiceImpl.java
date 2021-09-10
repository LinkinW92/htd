package com.skeqi.mes.service.alarm.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.mapper.alarm.CAlarmNnotificationMethodDao;
import com.skeqi.mes.pojo.CMesUserT;
import com.skeqi.mes.pojo.alarm.CAlarmEmailConfig;
import com.skeqi.mes.pojo.alarm.CAlarmNotificationChannels;
import com.skeqi.mes.pojo.alarm.CAlarmNotificationChannelsType;
import com.skeqi.mes.pojo.alarm.CAlarmNotificationMethod;
import com.skeqi.mes.pojo.alarm.CAlarmProblemLevel;
import com.skeqi.mes.pojo.project.CMesLossTypeT;
import com.skeqi.mes.service.alarm.CAlarmNnotificationMethodService;
import com.skeqi.mes.util.yp.EamilUtil;

/**
 * 通知方式
 *
 * @author yinp
 *
 */
@Service
public class CAlarmNnotificationMethodServiceImpl implements CAlarmNnotificationMethodService {

	@Autowired
	CAlarmNnotificationMethodDao dao;

	@Override
	public List<CAlarmNotificationChannelsType> findNotificationChannelsTypeList() {
		// TODO Auto-generated method stub
		return dao.findNotificationChannelsTypeList();
	}

	@Override
	public PageInfo<CAlarmNotificationMethod> findNnotificationMethodList(String userName, Integer pageNumber,
			Integer pageSize) throws Exception {
		CMesUserT user = dao.findUserByName(userName);
		if (user == null) {
			throw new Exception("用户不存在");
		}

		PageHelper.startPage(pageNumber, pageSize);
		List<CAlarmNotificationMethod> list = dao.findNnotificationMethodList(user.getId());
		PageInfo<CAlarmNotificationMethod> pageInfo = new PageInfo<CAlarmNotificationMethod>(list);
		return pageInfo;
	}

	@Override
	public Integer addNnotificationMethod(JSONObject json) throws Exception {
		CMesUserT user = dao.findUserByName(json.getString("userName"));
		if (user == null) {
			throw new Exception("用户不存在");
		}

		CAlarmNotificationMethod dx = new CAlarmNotificationMethod();
		dx.setLossTypeId(json.getInteger("lossTypeId"));
		dx.setNotificationChannelsId(json.getInteger("notificationChannelsId"));
		dx.setUserId(user.getId());
		dx.setServiceId(json.getInteger("serviceId"));
		dx.setProblemLevelId(json.getInteger("problemLevelId"));
		dx.setProblemState(json.getInteger("problemState"));

		CAlarmNotificationChannelsType type = dao.findNotificationChannelsType(dx.getNotificationChannelsId());

		switch (type.getNotificationChannelsTypeName()) {
		case "短信":
			Integer count = dao.findShortmessageCountById(dx.getServiceId(), user.getId());
			if (count <= 0) {
				throw new Exception("没找到对应的服务编号");
			}
			break;
		case "邮件":
			count = dao.findEamilCountById(dx.getServiceId(), user.getId());
			if (count <= 0) {
				throw new Exception("没找到对应的服务编号");
			}
			break;
		}

//		Integer count = mapper.findEamilCountById(id, userId)

//		Integer count = mapper.findNnotificationMethod(dx);
//		if(count>0) {
//			throw new Exception("该事件已经有对应的通知方式了");
//		}

		return dao.addNnotificationMethod(dx);
	}

	@Override
	public Integer updateNnotificationMethod(JSONObject json) throws Exception {
		CMesUserT user = dao.findUserByName(json.getString("userName"));
		if (user == null) {
			throw new Exception("用户不存在");
		}
		CAlarmNotificationMethod dx = new CAlarmNotificationMethod();
		dx.setId(json.getInteger("id"));
		dx.setLossTypeId(json.getInteger("lossTypeId"));
		dx.setNotificationChannelsId(json.getInteger("notificationChannelsId"));
		dx.setServiceId(json.getInteger("serviceId"));
		dx.setProblemLevelId(json.getInteger("problemLevelId"));
		dx.setProblemState(json.getInteger("problemState"));

		CAlarmNotificationChannelsType type = dao.findNotificationChannelsType(dx.getNotificationChannelsId());

		switch (type.getNotificationChannelsTypeName()) {
		case "短信":
			Integer count = dao.findShortmessageCountById(dx.getServiceId(), user.getId());
			if (count <= 0) {
				throw new Exception("没找到对应的服务编号");
			}
			break;
		case "邮件":
			count = dao.findEamilCountById(dx.getServiceId(), user.getId());
			if (count <= 0) {
				throw new Exception("没找到对应的服务编号");
			}
			break;
		}

		return dao.updateNnotificationMethod(dx);
	}

	@Override
	public Integer deleteNnotificationMethod(Integer id) {
		// TODO Auto-generated method stub
		return dao.deleteNnotificationMethod(id, null);
	}

	@Override
	public List<CAlarmProblemLevel> findProblemLevelAll() {
		// TODO Auto-generated method stub
		return dao.findProblemLevelAll();
	}

	@Override
	public List<CAlarmNotificationChannels> findNotificationChannelsList(String userName, Integer typeId)
			throws Exception {
		CMesUserT user = dao.findUserByName(userName);
		if (user == null) {
			throw new Exception("用户不存在");
		}
		return dao.findNotificationChannelsList(user.getId(), typeId);
	}

	@Override
	public void test(Integer notificationChannelsTypeId, Integer notificationChannelsId,Integer serviceId) throws Exception{

		if (notificationChannelsTypeId == 1) {// 邮件
			//获取要发送的用户
			CAlarmNotificationChannels notificationChannels = dao.findNotificationChannelsById(notificationChannelsId);
			String [] notificationChannelsContents = notificationChannels.getNotificationChannelsContent().split(",");

			//获取邮箱配置
			CAlarmEmailConfig emailConfig = dao.findEmailConfigById(serviceId);

			//设置邮件参数
			EamilUtil.SENDER_EAMIL = emailConfig.getSenderEmail();
			EamilUtil.THE_SERVER = emailConfig.getTheServer();
			EamilUtil.AUTHORIZATION_CODE = emailConfig.getAuthorizationCode();

			for (String string : notificationChannelsContents) {
				EamilUtil.sendEamil(string, "测试邮件", "您好,这是一封测试邮件。");
			}

		}

	}

	@Override
	public List<CMesLossTypeT> findLossTypeAll() {
		// TODO Auto-generated method stub
		return dao.findLossTypeAll();
	}

}
