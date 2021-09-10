package com.skeqi.mes.service.fqz;

import java.util.List;
import java.util.Map;

import com.skeqi.mes.pojo.CMesModuleTableT;

public interface CMesModuleTableService {

	public List<CMesModuleTableT> findAllModuleTable(CMesModuleTableT t);

	public List<Map<String,Object>> findAll(Integer id,String sn);
}
