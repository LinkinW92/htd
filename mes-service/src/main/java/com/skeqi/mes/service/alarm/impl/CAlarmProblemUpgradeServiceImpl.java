package com.skeqi.mes.service.alarm.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.alarm.CAlarmProblemUpgradeDao;
import com.skeqi.mes.pojo.alarm.CAlarmProblemLevel;
import com.skeqi.mes.pojo.alarm.CAlarmProblemUpgrade;
import com.skeqi.mes.pojo.project.CMesLossTypeT;
import com.skeqi.mes.service.alarm.CAlarmProblemUpgradeService;

@Service
public class CAlarmProblemUpgradeServiceImpl implements CAlarmProblemUpgradeService {

	@Autowired
	CAlarmProblemUpgradeDao dao;

	@Override
	public List<CAlarmProblemUpgrade> findProblemUpgradeList() {
		// TODO Auto-generated method stub
		return dao.findProblemUpgradeList();
	}

	@Override
	public Integer addProblemUpgrade(CAlarmProblemUpgrade dx) throws Exception {

		// 判断是否有相同的升级策略
		Integer count = dao.findProblemUpgrade(dx);
		if (count > 0) {
			throw new Exception("同事件、同等级升级策略已存在");
		}

		return dao.addProblemUpgrade(dx);
	}

	@Override
	public Integer updateProblemUpgrade(CAlarmProblemUpgrade dx) throws Exception {
		// 判断是否有相同的升级策略
		Integer count = dao.findProblemUpgrade(dx);
		if (count > 0) {
			throw new Exception("同事件、同等级升级策略已存在");
		}
		return dao.updateProblemUpgrade(dx);
	}

	@Override
	public Integer deleteProblemUpgrade(Integer id) {
		// TODO Auto-generated method stub
		return dao.deleteProblemUpgrade(id);
	}

	@Override
	public List<CAlarmProblemLevel> findProblemLevelAll() {
		// TODO Auto-generated method stub
		return dao.findProblemLevelAll();
	}

	@Override
	public List<CMesLossTypeT> findLossTypeAll() {
		// TODO Auto-generated method stub
		return dao.findLossTypeAll();
	}

}
