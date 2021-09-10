package com.skeqi.mes.service.fqz;

import java.util.List;
import java.util.Map;

import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.pojo.CMesReturnRepairT;

public interface ReturnRepairService {

public List<CMesReturnRepairT> findAll(Map<String,Object> map);

	public void insertReRepair(CMesReturnRepairT c);

	public void updateReRepair(CMesReturnRepairT c);

	public void delReRepair(String id);

	public CMesReturnRepairT findByid(String id);

	public List<CMesLineT> findLine();

	public List<CMesProductionT> findPro();
	public void endRepair(String id);
}
