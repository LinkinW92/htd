package com.skeqi.mes.mapper.wll;

import java.util.List;
import java.util.Map;

import com.skeqi.mes.pojo.CMesEmpTeamT;


public interface EmpTeamTDao {
	List<CMesEmpTeamT> empTeamList(Map<String, Object> map);
}
