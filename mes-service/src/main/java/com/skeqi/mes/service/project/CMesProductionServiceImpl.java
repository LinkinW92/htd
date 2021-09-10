package com.skeqi.mes.service.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.Exception.util.ParameterNullException;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.mapper.project.CMesProductionDAO;
import com.skeqi.mes.pojo.CMesProductionT;

@Service
public class CMesProductionServiceImpl implements CMesProductionService{

	@Autowired
	CMesProductionDAO dao;

	@Override
	public List<CMesProductionT> findAllPro(String name) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findAllPro(name);
	}

	@Override
	public Integer addPro(CMesProductionT pro) throws ServicesException {
		return dao.addPro(pro);
	}

	@Override
	public Integer updatePro(CMesProductionT pro) throws ServicesException {
		// TODO Auto-generated method stub
		if(pro.getProductionName()==null || pro.getProductionName()==""){
			throw new ParameterNullException("产品名称不能为空",200);
		}
		return dao.updatePro(pro);
	}

	@Override
	public Integer delPro(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.delPro(id);
	}

	@Override
	public Integer findByProductionType(String name) {
		// TODO Auto-generated method stub
		return dao.findByProductionType(name);
	}

	@Override
	public Integer findByProductionTypeL(String productionType, Integer id) {
		// TODO Auto-generated method stub
		return dao.findByProductionTypeL(productionType,id);
	}

	@Override
	public Integer addProL(CMesProductionT t) {
		// TODO Auto-generated method stub
		return dao.addProL(t);
	}

	@Override
	public CMesProductionT findByProTypeL(String productionType) {
		// TODO Auto-generated method stub
		return dao.findByProTypeL( productionType);
	}

	@Override
	public CMesProductionT findProIdAndName(Integer id, String proName) {
		return dao.findProIdAndName(id,proName);
	}
}
