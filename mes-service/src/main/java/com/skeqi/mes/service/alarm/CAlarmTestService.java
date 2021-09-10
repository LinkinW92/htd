package com.skeqi.mes.service.alarm;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.alarm.CAlarmEvent;
import com.skeqi.mes.pojo.alarm.CAlarmProblemLevel;

public interface CAlarmTestService {
	public Integer addProblem(JSONObject json) throws Exception;
	public List<CAlarmProblemLevel> findProblemLevelList();
	public List<CAlarmEvent> findAlarmEventList();
}
