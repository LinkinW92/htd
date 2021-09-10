package com.skeqi.mes.service.alarm;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.alarm.CAlarmNotificationChannels;
import com.skeqi.mes.pojo.alarm.CAlarmNotificationChannelsType;

/**
 * 通知渠道
 *
 * @author yinp
 *
 */
public interface CAlarmNotificationChannelsService {
	// 查询通知渠道类型
	public List<CAlarmNotificationChannelsType> findNotificationChannelsTypeList();

	// 查询通知渠道
	public PageInfo<CAlarmNotificationChannels> findNotificationChannelsList(String userName, Integer pageNumber,
			Integer pageSize) throws Exception;

	// 新增通知渠道
	public Integer addNotificationChannels(JSONObject json) throws Exception;

	// 更新通知渠道
	public Integer updateNotificationChannels(JSONObject json) throws Exception;

	// 删除通知渠道
	public Integer deleteNotificationChannels(Integer id);
}
