package com.skeqi.mes.service.all;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.mapper.all.CMesSystemDao;
import com.skeqi.mes.pojo.CMesSystem;

@Service
@Transactional
public class CMesSystemServiceImpl implements CMesSystemService{

	@Autowired
	private CMesSystemDao dao;
	@Override
	public List<CMesSystem> findByAll(String name) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findByAll(name);
	}
	@Override
	public Integer updateSystem(CMesSystem cMesSystem) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.updateSystem(cMesSystem);
	}

}
