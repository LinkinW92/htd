package com.skeqi.mes.service.processFlows;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.processFlows.ProcessProblemDao;
@Service
public class ProcessProblemServiceImpl implements ProcessProblemService {

	@Autowired
	private  ProcessProblemDao dao;
	@Override
	public List<Map<String, Object>> showProblem() {
		// TODO Auto-generated method stub
		return dao.showProblem();
	}

	@Override
	public Integer addProblem(String department, String describe) {
		// TODO Auto-generated method stub
		return dao.addProblem(department, describe);
	}

}
