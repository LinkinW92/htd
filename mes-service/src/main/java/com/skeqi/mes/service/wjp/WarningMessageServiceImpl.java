package com.skeqi.mes.service.wjp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skeqi.mes.mapper.wjp.WarningMessageDao;
import com.skeqi.mes.pojo.CMesAlarmCodeT;
@Service

public class WarningMessageServiceImpl implements WarningMessageService{

	@Autowired
	private WarningMessageDao warningMessageDao;

	//报警列表
	@Transactional
	@Override
	public List<CMesAlarmCodeT> findAll(Map map) {
		return warningMessageDao.findAll(map);
	}

	//删除报警信息
	@Transactional
	@Override
	public void delAlarm(int id) {
		warningMessageDao.delAlarm(id);
	}

	//条件查询报警信息
	@Transactional
	@Override
	public List<CMesAlarmCodeT> selAlarm(Map map) {
		return warningMessageDao.selAlarm(map);
	}

	//报警新增
	@Transactional
	@Override
	public void addAlarm(@SuppressWarnings("rawtypes") Map map) {
		warningMessageDao.addAlarm(map);
	}

	//根据id查询
	@Transactional
	@Override
	public CMesAlarmCodeT findById(@SuppressWarnings("rawtypes") Map map) {
		return warningMessageDao.findById(map);
	}

	//报警修改
	@Transactional
	@Override
	public void updateAlarm(Map map) {
		warningMessageDao.updateAlarm(map);
	}

}
