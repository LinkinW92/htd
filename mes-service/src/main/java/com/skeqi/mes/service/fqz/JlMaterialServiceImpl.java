package com.skeqi.mes.service.fqz;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.fqz.JlMaterialDAO;
import com.skeqi.mes.pojo.CMesJlMaterialT;

@Service
public class JlMaterialServiceImpl implements JlMaterialService{

	@Autowired
	private JlMaterialDAO dao;

	@Override
	public List<CMesJlMaterialT> findAll(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.findAll(map);
	}

	@Override
	public void insertJlMaterial(CMesJlMaterialT c) {
		// TODO Auto-generated method stub
		dao.insertJlMaterial(c);
	}

	@Override
	public void delJlMaterial(Integer id) {
		// TODO Auto-generated method stub
		dao.delJlMaterial(id);
	}

	@Override
	public CMesJlMaterialT findByid(Integer id) {
		// TODO Auto-generated method stub
		return dao.findByid(id);
	}

	@Override
	public void editJlMaterial(CMesJlMaterialT c) {
		// TODO Auto-generated method stub
		dao.editJlMaterial(c);
	}

	@Override
	public String findBomId(String name) {
		// TODO Auto-generated method stub
		return dao.findBomId(name);
	}



}
