package com.skeqi.mes.service.alarm.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.alarm.CAlarmProblemLevelDao;
import com.skeqi.mes.pojo.alarm.CAlarmProblemLevel;
import com.skeqi.mes.service.alarm.CAlarmProblemLevelService;

/**
 * 问题等级
 *
 * @author yinp
 *
 */
@Service
public class CAlarmProblemLevelServiceImpl implements CAlarmProblemLevelService {

	@Autowired
	CAlarmProblemLevelDao dao;

	@Override
	public List<CAlarmProblemLevel> findProblemLevel() {
		// TODO Auto-generated method stub
		return dao.findProblemLevel();
	}

	@Override
	public Integer addProblemLevel(CAlarmProblemLevel dx) {
		// TODO Auto-generated method stub
		return dao.addProblemLevel(dx);
	}

	@Override
	public Integer updateProblemLevel(CAlarmProblemLevel dx) {
		// TODO Auto-generated method stub
		return dao.updateProblemLevel(dx);
	}

	@Override
	public Integer deleteProblemLevel(Integer id) {
		// TODO Auto-generated method stub
		return dao.deleteProblemLevel(id);
	}

}
