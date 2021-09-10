package com.skeqi.mes.service.wll;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skeqi.mes.mapper.wll.EmpTeamTDao;
import com.skeqi.mes.pojo.CMesEmpTeamT;

@Service
@Transactional
public class EmpTeamServiceImpl  implements EmpTeamService{

	@Autowired
	EmpTeamTDao EmpTeamDao;

	@Override
	public List<CMesEmpTeamT> empTeamList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return EmpTeamDao.empTeamList(map);
	}

}
