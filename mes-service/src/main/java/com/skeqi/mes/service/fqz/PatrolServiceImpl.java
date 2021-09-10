package com.skeqi.mes.service.fqz;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.fqz.PatrolDAO;
import com.skeqi.mes.pojo.CMesPatrolT;
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.pojo.CMesStationT;

@Service
public class PatrolServiceImpl implements PatrolService {

	@Autowired
	private PatrolDAO dao;


	@Override
	public List<CMesPatrolT> findAll(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.findAll(map);
	}

	@Override
	public void insertPatrol(CMesPatrolT c) {
		// TODO Auto-generated method stub
		dao.insertPatrol(c);
	}

	@Override
	public void updatePatrol(CMesPatrolT c) {
		// TODO Auto-generated method stub
		dao.updatePatrol(c);
	}

	@Override
	public void delPatrol(String id) {
		// TODO Auto-generated method stub
		dao.delPatrol(id);
	}

	@Override
	public CMesPatrolT findByid(String id) {
		// TODO Auto-generated method stub
		return dao.findByid(id);
	}

	@Override
	public List<CMesStationT> findStation() {
		// TODO Auto-generated method stub
		return dao.findStation();
	}

	@Override
	public List<CMesProductionT> findPro() {
		// TODO Auto-generated method stub
		return dao.findPro();
	}

}
