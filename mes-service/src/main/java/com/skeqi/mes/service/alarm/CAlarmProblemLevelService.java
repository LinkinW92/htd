package com.skeqi.mes.service.alarm;

import java.util.List;

import com.skeqi.mes.pojo.alarm.CAlarmProblemLevel;

/**
 * 问题等级
 *
 * @author yinp
 *
 */
public interface CAlarmProblemLevelService {

	// 查询问题等级
	public List<CAlarmProblemLevel> findProblemLevel();

	// 新增问题等级
	public Integer addProblemLevel(CAlarmProblemLevel dx);

	// 更新问题等级
	public Integer updateProblemLevel(CAlarmProblemLevel dx);

	// 删除新增问题等级
	public Integer deleteProblemLevel(Integer id);

}
