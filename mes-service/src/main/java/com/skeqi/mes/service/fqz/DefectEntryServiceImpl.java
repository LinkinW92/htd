package com.skeqi.mes.service.fqz;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.fqz.DefectEntryDAO;
import com.skeqi.mes.pojo.CMesDefectEntryT;
import com.skeqi.mes.pojo.CMesProductionT;

@Service
public class DefectEntryServiceImpl implements DefectEntryService{

	@Autowired
	private DefectEntryDAO dao;


	@Override
	public List<CMesDefectEntryT> findAll(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.findAll(map);
	}

	@Override
	public void insertentry(CMesDefectEntryT c) {
		// TODO Auto-generated method stub
		dao.insertentry(c);
	}

	@Override
	public void updateentry(CMesDefectEntryT c) {
		// TODO Auto-generated method stub
		dao.updateentry(c);
	}

	@Override
	public void deleteentry(String id) {
		// TODO Auto-generated method stub
		dao.deleteentry(id);
	}



	@Override
	public List<CMesProductionT> findProduction() {
		// TODO Auto-generated method stub
		return dao.findProduction();
	}

	@Override
	public CMesDefectEntryT findByid(String id) {
		// TODO Auto-generated method stub
		return dao.findByid(id);
	}

}
