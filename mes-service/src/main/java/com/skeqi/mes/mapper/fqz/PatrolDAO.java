package com.skeqi.mes.mapper.fqz;

import java.util.List;
import java.util.Map;

import com.skeqi.mes.pojo.CMesPatrolT;
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.pojo.CMesStationT;

public interface PatrolDAO {

	public List<CMesPatrolT> findAll(Map<String,Object> map);

	public void insertPatrol(CMesPatrolT c);

	public void updatePatrol(CMesPatrolT c);

	public void delPatrol(String id);

	public CMesPatrolT findByid(String id);

	public List<CMesStationT> findStation();

	public List<CMesProductionT> findPro();
}
