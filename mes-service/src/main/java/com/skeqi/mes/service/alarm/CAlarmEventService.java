package com.skeqi.mes.service.alarm;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.skeqi.mes.pojo.alarm.CAlarmEvent;
import com.skeqi.mes.pojo.alarm.CAlarmNotificationChannels;
import com.skeqi.mes.pojo.alarm.CAlarmNotificationChannelsType;
import com.skeqi.mes.pojo.alarm.CAlarmNotificationMethod;

public interface CAlarmEventService {

	// 查询事件
	public PageInfo<CAlarmEvent> findAlarmEventList(String userName, Integer pageNumber, Integer pageSize) throws Exception;

	// 新增事件
	public Integer addAlarmEvent(JSONObject json) throws Exception;
	// 更新事件
	public Integer updateAlarmEvent(JSONObject json) throws Exception ;
	// 删除事件
	public Integer deleteAlarmEvent(Integer id);

}
