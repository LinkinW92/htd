package com.skeqi.mes.service.all;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skeqi.mes.Exception.util.NameRepeatException;
import com.skeqi.mes.Exception.util.ParameterNullException;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.mapper.all.CMesAlarmCodeTDAO;
import com.skeqi.mes.pojo.CMesAlarmCodeT;

@Service
@Transactional
public class CMesAlarmCodeServiceImpl implements CMesAlarmCodeService {

	@Autowired
	private CMesAlarmCodeTDAO dao;

	@Override
	public List<CMesAlarmCodeT> findAllAlarm(CMesAlarmCodeT t) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findAllAlarm(t);
	}

	@Override
	public CMesAlarmCodeT findAlarmByid(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.findAlarmByid(id);
	}

	@Override
	public Integer addAlarm(CMesAlarmCodeT c) throws ServicesException {
		// TODO Auto-generated method stub
		if(c.getAlarmCode()==null || c.getAlarmCode()==0){
			throw new ParameterNullException("报警编号不能为空",200);
		}else if(c.getAlarmEnglish()==null || c.getAlarmEnglish()==""){
			throw new ParameterNullException("报警英文信息不能为空",200);
		}else if(c.getAlarmText()==null || c.getAlarmText()==""){
			throw new ParameterNullException("报警中文信息不能为空",200);
		}

		List<CMesAlarmCodeT> findAllAlarm = dao.findAllAlarm(c);   //查询报警编号是否重复
		if(findAllAlarm.size()>0){
			throw new NameRepeatException("报警编号重复",100);
		}
		return dao.addAlarm(c);
	}

	@Override
	public Integer updateAlarm(CMesAlarmCodeT c) throws ServicesException {
		// TODO Auto-generated method stub
		 if(c.getAlarmEnglish()==null || c.getAlarmEnglish()==""){
			throw new ParameterNullException("报警英文信息不能为空",200);
		}else if(c.getAlarmText()==null || c.getAlarmText()==""){
			throw new ParameterNullException("报警中文信息不能为空",200);
		}

		return dao.updateAlarm(c);
	}

	@Override
	public Integer delAlarm(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null || id==0){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.delAlarm(id);
	}

}
