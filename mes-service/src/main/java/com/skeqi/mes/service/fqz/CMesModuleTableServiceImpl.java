package com.skeqi.mes.service.fqz;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.fqz.CMesModuleTableDAO;
import com.skeqi.mes.pojo.CMesModuleTableT;

@Service
public class CMesModuleTableServiceImpl implements CMesModuleTableService{

	@Autowired
	private CMesModuleTableDAO dao;

	@Override
	public List<CMesModuleTableT> findAllModuleTable(CMesModuleTableT t) {
		// TODO Auto-generated method stub
		return dao.findAllModuleTable(t);
	}

	@Override
	public List<Map<String, Object>> findAll(Integer id, String sn) {
		// TODO Auto-generated method stub
		return dao.findAll(id, sn);
	}


}
