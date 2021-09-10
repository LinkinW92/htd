package com.skeqi.mes.service.crm;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.crm.CRMLogDao;

@Service
public class CRMLogServiceImpl implements CRMLogService {

	@Autowired
	private CRMLogDao dao;
	@Override
	public Integer addCRMLogInfo(String user, String menuName) {
		// TODO Auto-generated method stub
		return dao.addCRMLogInfo(user, menuName);
	}
	@Override
	public List<Map<String, Object>> showUser(String userName) {
		// TODO Auto-generated method stub
		return dao.showUser(userName);
	}

}
