package com.skeqi.mes.service.processFlows;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.processFlows.ProcessManagerDao;

@Service
public class ProcessManagerServiceImpl implements ProcessManagerService {

	@Autowired
	private ProcessManagerDao dao;
	@Override
	public List<Map<String, Object>> showProcess() {
		// TODO Auto-generated method stub
		return dao.showProcess();
	}

	@Override
	public Integer addProcess(String processName,String processType,String unitPrice) {
		// TODO Auto-generated method stub
		return dao.addProcess(processName,processType,unitPrice);
	}

	@Override
	public Integer updateProcess(String processName, String id,String processType,String unitPrice) {
		// TODO Auto-generated method stub
		return dao.updateProcess(processName, id,processType,unitPrice);
	}

	@Override
	public Integer delProcess(Integer id) {
		// TODO Auto-generated method stub
		return dao.delProcess(id);
	}

	@Override
	public Integer countProcessByName(String processName,String id) {
		// TODO Auto-generated method stub
		return dao.countProcessByName(processName, id);
	}

	@Override
	public Integer countRoutes(Integer id) {
		// TODO Auto-generated method stub
		return dao.countRoutes(id);
	}

	@Override
	public Integer showProductTaskByProcessId(Integer processId) {
		// TODO Auto-generated method stub
		return dao.showProductTaskByProcessId(processId);
	}

	@Override
	public String showProcessById() {
		// TODO Auto-generated method stub
		return dao.showProcessById();
	}

	@Override
	public String showStationNameById(String id) {
		// TODO Auto-generated method stub
		return dao.showStationNameById(id);
	}

}
