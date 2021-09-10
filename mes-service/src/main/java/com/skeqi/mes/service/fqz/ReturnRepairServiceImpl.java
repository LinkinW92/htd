package com.skeqi.mes.service.fqz;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.fqz.ReRepairDAO;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.pojo.CMesReturnRepairT;

@Service
public class ReturnRepairServiceImpl implements ReturnRepairService {

	@Autowired
	private ReRepairDAO dao;
	@Override
	public List<CMesReturnRepairT> findAll(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.findAll(map);
	}

	@Override
	public void insertReRepair(CMesReturnRepairT c) {
		// TODO Auto-generated method stub
			dao.insertReRepair(c);
	}

	@Override
	public void updateReRepair(CMesReturnRepairT c) {
		// TODO Auto-generated method stub
			dao.updateReRepair(c);
	}

	@Override
	public void delReRepair(String id) {
		// TODO Auto-generated method stub
			dao.delReRepair(id);
	}

	@Override
	public CMesReturnRepairT findByid(String id) {
		// TODO Auto-generated method stub
		return dao.findByid(id);
	}

	@Override
	public List<CMesLineT> findLine() {
		// TODO Auto-generated method stub
		return dao.findLine();
	}

	@Override
	public List<CMesProductionT> findPro() {
		// TODO Auto-generated method stub
		return dao.findPro();
	}

	@Override
	public void endRepair(String id) {
		// TODO Auto-generated method stub
		dao.endRepair(id);
	}

}
