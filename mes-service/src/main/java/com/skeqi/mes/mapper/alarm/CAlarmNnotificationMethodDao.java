package com.skeqi.mes.mapper.alarm;

import java.util.List;


import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.CMesUserT;
import com.skeqi.mes.pojo.alarm.CAlarmEmailConfig;
import com.skeqi.mes.pojo.alarm.CAlarmNotificationChannels;
import com.skeqi.mes.pojo.alarm.CAlarmNotificationChannelsType;
import com.skeqi.mes.pojo.alarm.CAlarmNotificationMethod;
import com.skeqi.mes.pojo.alarm.CAlarmProblemLevel;
import com.skeqi.mes.pojo.alarm.CAlarmShortmessageConfig;
import com.skeqi.mes.pojo.project.CMesLossTypeT;

/**
 * 通知方式
 *
 * @author yinp
 *
 */
public interface CAlarmNnotificationMethodDao {
	// 通过用户名查询用户
	public CMesUserT findUserByName(@Param("userName") String userName);

	// 查询通知渠道类型
	public List<CAlarmNotificationChannelsType> findNotificationChannelsTypeList();

	// 查询通知方式
	public List<CAlarmNotificationMethod> findNnotificationMethodList(@Param("userId") Integer userId);

	// 查询通知方式是否重名
	public Integer findNnotificationMethod(CAlarmNotificationMethod dx);

	// 新增通知方式
	public Integer addNnotificationMethod(CAlarmNotificationMethod dx);

	// 更新通知方式
	public Integer updateNnotificationMethod(CAlarmNotificationMethod dx);

	// 删除通知方式
	public Integer deleteNnotificationMethod(@Param("id") Integer id,
			@Param("notificationChannelsId") Integer notificationChannelsId);

	//查询所有问题等级
	public List<CAlarmProblemLevel> findProblemLevelAll();

	// 查询通知渠道
	public List<CAlarmNotificationChannels> findNotificationChannelsList(@Param("userId") Integer userId,
			@Param("typeId") Integer typeId);

	// 通过通知渠道查询通知渠道类型
	public CAlarmNotificationChannelsType findNotificationChannelsType(@Param("id") Integer id);

	// 查询邮箱服务是否存在
	public Integer findEamilCountById(@Param("id") Integer id, @Param("userId") Integer userId);

	// 查询短信服务是否存在
	public Integer findShortmessageCountById(@Param("id") Integer id, @Param("userId") Integer userId);

	// 查询通知渠道
	public CAlarmNotificationChannels findNotificationChannelsById(@Param("id") Integer id);

	// 查询邮箱服务
	public CAlarmEmailConfig findEmailConfigById(@Param("id") Integer id);

	// 查询短信服务
	public CAlarmShortmessageConfig findShortmessageConfigById(@Param("id") Integer id);

	//查询所有损失类型
	public List<CMesLossTypeT> findLossTypeAll();

}
