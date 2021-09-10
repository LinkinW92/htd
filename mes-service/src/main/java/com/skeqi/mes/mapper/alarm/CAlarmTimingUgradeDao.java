package com.skeqi.mes.mapper.alarm;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.alarm.CAlarmNoticeLogs;
import com.skeqi.mes.pojo.alarm.CAlarmProblemUpgradeLogs;
import com.skeqi.mes.pojo.alarm.CAlarmProblems;
import com.skeqi.mes.pojo.project.CMesAndonFault;

/**
 * 定时升级
 *
 * @author yinp
 *
 */
public interface CAlarmTimingUgradeDao {

	// 查询升级记录
	public CAlarmProblemUpgradeLogs findProblemUpgradeLogs(@Param("faultId") Integer faultId);

	// 新增升级记录
	public Integer addProblemUpgradeLogs(CAlarmProblemUpgradeLogs dx);

	// 查询升级
	public List<JSONObject> findProblemUpgrade();

	// 查询当前故障
	public List<CMesAndonFault> findAndonFault();

	// 升级
	public Integer upgrade(@Param("id") Integer id, @Param("levelId") Integer levelId);

	// 查询需要发送的邮件
	public List<JSONObject> findNnotificationMethod(@Param("lossTypeId") Integer lossTypeId,
			@Param("problemLevelId") Integer problemLevelId,
			@Param("problemState")Integer problemState);

	// 新增通知日志
	public Integer addNoticeLogs(CAlarmNoticeLogs dx);

}
