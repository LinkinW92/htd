package com.skeqi.mes.service.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.Exception.util.ParameterNullException;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.mapper.project.CMesFaultTypeDAO;
import com.skeqi.mes.pojo.project.CMesFaultTypeT;

@Service
public class CMesFaultTypeServiceImpl implements CMesFaultTypeService{

	@Autowired
	CMesFaultTypeDAO dao;

	@Override
	public List<CMesFaultTypeT> findAllFault(String name) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findAllFault(name);
	}

	@Override
	public Integer addFault(String name, String note) throws ServicesException {
		// TODO Auto-generated method stub
		if(name==null || name==""){
			throw new ParameterNullException("名称不能为空",200);
		}
		return dao.addFault(name, note);
	}

	@Override
	public Integer updateFault(String name, String note, Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(name==null || name==""){
			throw new ParameterNullException("名称不能为空",200);
		}else if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.updateFault(name, note, id);
	}

	@Override
	public Integer delFault(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.delFault(id);
	}



}
