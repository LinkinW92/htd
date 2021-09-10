package com.skeqi.mes.mapper.alarm;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.alarm.CAlarmProblemLevel;

/**
 * 问题等级
 *
 * @author yinp
 *
 */
public interface CAlarmProblemLevelDao {

	// 查询问题等级
	public List<CAlarmProblemLevel> findProblemLevel();

	// 新增问题等级
	public Integer addProblemLevel(CAlarmProblemLevel dx);

	// 更新问题等级
	public Integer updateProblemLevel(CAlarmProblemLevel dx);

	// 删除新增问题等级
	public Integer deleteProblemLevel(@Param("id") Integer id);

}
