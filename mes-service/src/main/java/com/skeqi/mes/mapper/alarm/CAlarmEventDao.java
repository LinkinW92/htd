package com.skeqi.mes.mapper.alarm;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.CMesUserT;
import com.skeqi.mes.pojo.alarm.CAlarmEvent;

/**
 * 事件
 *
 * @author yinp
 *
 */
public interface CAlarmEventDao {

	// 通过用户名查询用户
	public CMesUserT findUserByName(@Param("userName") String userName);

	// 查询事件
	public List<CAlarmEvent> findAlarmEventList(@Param("userId") Integer userId);

	// 查询事件是否重名
	public Integer findAlarmEvent(CAlarmEvent dx);

	// 新增事件
	public Integer addAlarmEvent(CAlarmEvent dx);

	// 更新事件
	public Integer updateAlarmEvent(CAlarmEvent dx);

	// 删除事件
	public Integer deleteAlarmEvent(@Param("id") Integer id);

	// 删除通知方式
	public Integer deleteNnotificationMethod(@Param("id") Integer id,
			@Param("notificationChannelsId") Integer notificationChannelsId);

}
