package com.skeqi.mes.service.wll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.wll.DefectSNManagerDAO;
import com.skeqi.mes.pojo.CMesDefectManager;
import com.skeqi.mes.pojo.CMesDefectResionT;
import com.skeqi.mes.pojo.CMesDutyManagerT;
import com.skeqi.mes.pojo.PMesDefectReasonT;

@Service
public class DefectSNManagerServiceImpl implements DefectSNManagerService {

	@Autowired
	private DefectSNManagerDAO dao;

	@Override
	public List<CMesDutyManagerT> findDuty() {
		// TODO Auto-generated method stub
		return dao.findDuty();
	}

	@Override
	public List<CMesDefectResionT> findDefect() {
		// TODO Auto-generated method stub
		return dao.findDefect();
	}

	@Override
	public List<CMesDefectManager> findDefactManager() {
		// TODO Auto-generated method stub
		return dao.findDefactManager();
	}

	@Override
	public void add(PMesDefectReasonT t) {
		// TODO Auto-generated method stub
		dao.add(t);
	}

	@Override
	public Integer findId(String SN) {
		// TODO Auto-generated method stub
		return dao.findId(SN);
	}

	@Override
	public void updates(String SN) {
		// TODO Auto-generated method stub
		dao.updates(SN);
	}

}
