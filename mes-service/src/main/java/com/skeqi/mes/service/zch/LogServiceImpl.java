package com.skeqi.mes.service.zch;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.zch.LogDao;
import com.skeqi.mes.pojo.zch.MesOperationLogT;

@Service
public class LogServiceImpl implements LogService {

	@Autowired
	LogDao logDao;

	@Override
	public Integer addOperationLog(MesOperationLogT logBo) {
		return logDao.addOperationLog(logBo);
	}

	@Override
	public List<Map<String, Object>> findOperationLogList(Map<String, Object> map) {
		return logDao.findOperationLogList(map);
	}

	@Override
	public void test() {
		System.out.println("-------------------test");
	}

	@Override
	public Integer addVisitLog(Map<String, Object> map) {
		return logDao.addVisitLog(map);
	}

	@Override
	public Integer addErrorLog(Map<String, Object> map) {
		return logDao.addErrorLog(map);
	}

	@Override
	public List<Map<String, Object>> findVisitLogList(Map<String, Object> map) {
		return logDao.findVisitLogList(map);
	}

}
