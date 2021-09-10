package com.skeqi.mes.service.fqz;

import java.util.List;
import java.util.Map;

import com.skeqi.mes.pojo.CMESMaterialRepairT;
import com.skeqi.mes.pojo.CMesMaterialT;
import com.skeqi.mes.pojo.CMesStationT;

public interface MaterialRepairService {
	public List<CMESMaterialRepairT> findAll(Map<String,Object> map);

	public List<CMesStationT> findStation();

	public List<CMesMaterialT> findMateial();

	public void insertM(CMESMaterialRepairT c);

	public void updatestatus(CMESMaterialRepairT c);

	public void delmaterial(String id);
}
