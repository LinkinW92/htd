package com.skeqi.mes.service.fqz;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.fqz.IQCCheckDAO;
import com.skeqi.mes.pojo.CMesIqcCheckT;
import com.skeqi.mes.pojo.CMesMaterialT;

@Service
public class IQCCheckServiceImpl implements IQCCheckService {

	@Autowired
	private IQCCheckDAO dao;

	@Override
	public List<CMesIqcCheckT> findAll(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.findAll(map);
	}

	@Override
	public void insertIQC(CMesIqcCheckT c) {
		// TODO Auto-generated method stub
		dao.insertIQC(c);
	}

	@Override
	public void deleteIQC(String id) {
		// TODO Auto-generated method stub
		dao.deleteIQC(id);
	}

	@Override
	public CMesIqcCheckT findByid(String id) {
		// TODO Auto-generated method stub
		return dao.findByid(id);
	}

	@Override
	public void updateIQC(CMesIqcCheckT c) {
		// TODO Auto-generated method stub
		dao.updateIQC(c);
	}

	@Override
	public void fuhe(String id, String name) {
		// TODO Auto-generated method stub
		dao.fuhe(id, name);
	}

	@Override
	public List<CMesMaterialT> findMaterial() {
		// TODO Auto-generated method stub
		return dao.findMaterial();
	}

	@Override
	public void freezes(String id, String status) {
		// TODO Auto-generated method stub
		dao.freezes(id, status);
	}

}
