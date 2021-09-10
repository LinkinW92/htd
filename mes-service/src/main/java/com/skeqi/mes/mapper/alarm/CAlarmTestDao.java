package com.skeqi.mes.mapper.alarm;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.CMesUserT;
import com.skeqi.mes.pojo.alarm.CAlarmEvent;
import com.skeqi.mes.pojo.alarm.CAlarmProblemLevel;

public interface CAlarmTestDao {

	//新增问题
	public Integer addProblem(JSONObject json);

	//查询用户
	public CMesUserT findUserByName(@Param("userName")String userName);

	//查询问题级别
	public List<CAlarmProblemLevel> findProblemLevelList();

	//查询事件
	public List<CAlarmEvent> findAlarmEventList();

}
