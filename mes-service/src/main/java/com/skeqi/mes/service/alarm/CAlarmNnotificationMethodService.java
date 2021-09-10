package com.skeqi.mes.service.alarm;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.alarm.CAlarmEvent;
import com.skeqi.mes.pojo.alarm.CAlarmNotificationChannels;
import com.skeqi.mes.pojo.alarm.CAlarmNotificationChannelsType;
import com.skeqi.mes.pojo.alarm.CAlarmNotificationMethod;
import com.skeqi.mes.pojo.alarm.CAlarmProblemLevel;
import com.skeqi.mes.pojo.project.CMesLossTypeT;

/**
 * 通知方式
 *
 * @author yinp
 *
 */
public interface CAlarmNnotificationMethodService {
	// 查询通知渠道类型
	public List<CAlarmNotificationChannelsType> findNotificationChannelsTypeList();

	// 查询通知方式
	public PageInfo<CAlarmNotificationMethod> findNnotificationMethodList(String userName, Integer pageNumber,
			Integer pageSize) throws Exception;

	// 新增通知方式
	public Integer addNnotificationMethod(JSONObject json) throws Exception;

	// 更新通知方式
	public Integer updateNnotificationMethod(JSONObject json) throws Exception;

	// 删除通知方式
	public Integer deleteNnotificationMethod(Integer id);

	//查询所有问题等级
	public List<CAlarmProblemLevel> findProblemLevelAll();

	// 查询通知渠道
	public List<CAlarmNotificationChannels> findNotificationChannelsList(String userName, Integer typeId)
			throws Exception;

	//测试
	public void test(Integer notificationChannelsTypeId, Integer notificationChannelsId,Integer serviceId) throws Exception;

	//查询所有损失类型
	public List<CMesLossTypeT> findLossTypeAll();

}
