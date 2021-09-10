package com.skeqi.mes.mapper.alarm;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.CMesUserT;
import com.skeqi.mes.pojo.alarm.CAlarmNotificationChannels;
import com.skeqi.mes.pojo.alarm.CAlarmNotificationChannelsType;

/**
 * 通知渠道
 *
 * @author yinp
 *
 */
public interface CAlarmNotificationChannelsDao {
	// 通过用户名查询用户
	public CMesUserT findUserByName(@Param("userName") String userName);

	// 查询通知渠道类型
	public List<CAlarmNotificationChannelsType> findNotificationChannelsTypeList();

	// 查询通知渠道
	public List<CAlarmNotificationChannels> findNotificationChannelsList(@Param("userId") Integer userId);

	// 查询通知渠道是否重名
	public Integer findNotificationChannels(CAlarmNotificationChannels dx);

	// 新增通知渠道
	public Integer addNotificationChannels(CAlarmNotificationChannels dx);

	// 更新通知渠道
	public Integer updateNotificationChannels(CAlarmNotificationChannels dx);

	// 删除通知渠道
	public Integer deleteNotificationChannels(@Param("id") Integer id);
}
