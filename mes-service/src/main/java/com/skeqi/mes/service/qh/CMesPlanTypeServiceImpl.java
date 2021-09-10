package com.skeqi.mes.service.qh;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.qh.CMesPlanTypeDao;
import com.skeqi.mes.pojo.CMesPlanTypeT;

@Service
public class CMesPlanTypeServiceImpl implements CMesPlanTypeService {
	@Autowired
	CMesPlanTypeDao dao;

	@Override
	public List<CMesPlanTypeT> findAllPlanType() {
		// TODO Auto-generated method stub
		return dao.findAllPlanType();
	}

	@Override
	public void addPlanType(CMesPlanTypeT planType) {
		// TODO Auto-generated method stub
		dao.addPlanType(planType);
	}

	@Override
	public void updatePlanType(CMesPlanTypeT planType) {
		// TODO Auto-generated method stub
		dao.updatePlanType(planType);
	}

	@Override
	public void deletePlanType(Integer id) {
		// TODO Auto-generated method stub
		dao.deletePlanType(id);
	}

	@Override
	public CMesPlanTypeT findPlanTypeById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
