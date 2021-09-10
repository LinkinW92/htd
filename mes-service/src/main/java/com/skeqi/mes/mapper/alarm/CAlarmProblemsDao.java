package com.skeqi.mes.mapper.alarm;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.CMesUserT;
import com.skeqi.mes.pojo.alarm.CAlarmEvent;
import com.skeqi.mes.pojo.alarm.CAlarmNoticeLogs;
import com.skeqi.mes.pojo.alarm.CAlarmProblemLevel;
import com.skeqi.mes.pojo.alarm.CAlarmProblems;
import com.skeqi.mes.pojo.project.CMesAndonFault;
import com.skeqi.mes.pojo.project.CMesLossTypeT;

public interface CAlarmProblemsDao {

	// 查询需要发送的邮件
	public List<JSONObject> findNnotificationMethod(@Param("lossTypeId") Integer lossTypeId,
			@Param("problemLevelId") Integer problemLevelId, @Param("problemState") Integer problemState);

	// 新增通知日志
	public Integer addNoticeLogs(CAlarmNoticeLogs dx);

	//通过id查询损失类型
	public CMesLossTypeT findLossTypeById(@Param("id")Integer id);

	//通过产线工位查询故障
	public CMesAndonFault findAndonFault(@Param("lineName")String lineName,@Param("stationName")String stationName);

}
