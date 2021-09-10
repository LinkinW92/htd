package com.skeqi.mes.service.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.Exception.util.ParameterNullException;
import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.mapper.project.CMesShiftTeamDAO;
import com.skeqi.mes.mapper.project.CMesTeamDAO;
import com.skeqi.mes.pojo.CMesShiftsTeamT;
import com.skeqi.mes.pojo.CMesTeamT;

@Service
public class CMesTeamServiceImpl implements CMesTeamService{

	@Autowired
	CMesShiftTeamDAO shiftDao;

	@Autowired
	CMesTeamDAO  dao;

	@Override
	public List<CMesTeamT> findAllTeam(String name) throws ServicesException {
		// TODO Auto-generated method stub
		return dao.findAllTeam(name);
	}

	@Override
	public Integer addTeam(CMesTeamT team) throws ServicesException {
		// TODO Auto-generated method stub
		if(team.getName()==null || team.getName()==""){
			throw new ParameterNullException("名称不能为空",200);
		}
		return dao.addTeam(team);
	}

	@Override
	public Integer updateTeam(CMesTeamT team) throws ServicesException {
		// TODO Auto-generated method stub
		if(team.getName()==null || team.getName()==""){
			throw new ParameterNullException("名称不能为空",200);
		}
		return dao.updateTeam(team);
	}

	@Override
	public Integer delTeam(Integer id) throws ServicesException {
		// TODO Auto-generated method stub
		if(id==null ){
			throw new ParameterNullException("id不能为空",200);
		}
		return dao.delTeam(id);
	}

	@Override
	public Integer findByName(String name) {
		// TODO Auto-generated method stub
		return dao.findByName( name);
	}

}
