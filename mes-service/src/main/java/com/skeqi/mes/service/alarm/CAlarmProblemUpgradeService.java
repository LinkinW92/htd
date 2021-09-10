package com.skeqi.mes.service.alarm;

import java.util.List;

import com.skeqi.mes.pojo.alarm.CAlarmProblemLevel;
import com.skeqi.mes.pojo.alarm.CAlarmProblemUpgrade;
import com.skeqi.mes.pojo.project.CMesLossTypeT;

/**
 * 问题升级
 *
 * @author yinp
 *
 */
public interface CAlarmProblemUpgradeService {
	// 查询问题升级集合
	public List<CAlarmProblemUpgrade> findProblemUpgradeList();

	// 新增问题升级集合
	public Integer addProblemUpgrade(CAlarmProblemUpgrade dx) throws Exception ;

	// 更新新增问题升级集合
	public Integer updateProblemUpgrade(CAlarmProblemUpgrade dx) throws Exception ;

	// 删除新增问题升级集合
	public Integer deleteProblemUpgrade(Integer id);

	// 查询所有 等级
	public List<CAlarmProblemLevel> findProblemLevelAll();

	// 查询所有损失类型
	public List<CMesLossTypeT> findLossTypeAll();


}
