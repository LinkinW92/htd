package com.skeqi.mes.mapper.lyh;

import java.util.List;
import java.util.Map;

import com.skeqi.mes.pojo.RMesPlanT;

public interface PlanMDao {

	@SuppressWarnings("rawtypes")
	public List<RMesPlanT> rMesPlanList(Map map);
//	public void addUser(Map map);

}
