package com.skeqi.mes.mapper.fqz;

import java.util.List;
import java.util.Map;

import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.pojo.CMesReturnRepairT;

public interface ReRepairDAO {

	public List<CMesReturnRepairT> findAll(Map<String,Object> map);

	public void insertReRepair(CMesReturnRepairT c);

	public void updateReRepair(CMesReturnRepairT c);

	public void delReRepair(String id);

	public void endRepair(String id);

	public CMesReturnRepairT findByid(String id);

	public List<CMesLineT> findLine();

	public List<CMesProductionT> findPro();

}
