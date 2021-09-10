package com.skeqi.mes.service.all;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skeqi.mes.Exception.util.ParameterNullException;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.mapper.all.SMesRegisterTDAO;
import com.skeqi.mes.pojo.RegisterT;

@Service
@Transactional
public class SMesRegisterServiceImpl implements SMesRegisterTService {

	@Autowired
	private SMesRegisterTDAO dao;
	@Override
	public List<RegisterT> findAllRegisterT(RegisterT r) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findAllRegisterT(r);
	}

	@Override
	public Integer addRegisterT(RegisterT r) throws ServicesException {
		// TODO Auto-generated method stub
		if(r.getIp()==null || r.getIp()==""){
			throw new ParameterNullException("ip不能为空",200);
		}else if(r.getHostname()==null || r.getHostname()==""){
			throw new ParameterNullException("程序类型不能为空",200);
		}
		return dao.addRegisterT(r);
	}

	@Override
	public Integer updadteRegisterT(RegisterT r) throws ServicesException {
		// TODO Auto-generated method stub
		if(r.getIp()==null || r.getIp()==""){
			throw new ParameterNullException("ip不能为空",200);
		}else if(r.getHostname()==null || r.getHostname()==""){
			throw new ParameterNullException("程序类型不能为空",200);
		}
		return dao.updadteRegisterT(r);
	}

	@Override
	public Integer delRegisterT(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null||id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.delRegisterT(id);
	}

}
