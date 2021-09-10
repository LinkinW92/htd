package com.skeqi.mes.service.processFlows;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.processFlows.ProcessLogInfoDao;

@Service
public class ProcessLogInfoServiceImpl implements ProcessLogInfoService {

	@Autowired
	private ProcessLogInfoDao dao;
	@Override
	public List<Map<String, Object>> showAllProcessLogInfo(Integer id) {
		// TODO Auto-generated method stub
		return dao.showAllProcessLogInfo(id);
	}

	@Override
	public Integer addProcessLogInfo(String taskId, String processId, String logInfo, String logType) {
		// TODO Auto-generated method stub
		return dao.addProcessLogInfo(taskId, processId, logInfo, logType);
	}

}
