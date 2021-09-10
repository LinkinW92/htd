package com.skeqi.mes.service.fqz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.fqz.PMesDischargeDAO;
import com.skeqi.mes.pojo.PMesDischargeT;

@Service
public class PMesDischargeServiceImpl implements PMesDischargeService {

	@Autowired
	private PMesDischargeDAO dao;

	@Override
	public void insertDischarge(PMesDischargeT p) {
		// TODO Auto-generated method stub
		dao.insertDischarge(p);
	}

}
