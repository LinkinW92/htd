package com.skeqi.mes.mapper.fqz;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.CMesModuleTableT;

public interface CMesModuleTableDAO {

	//模组记录表查询
	public List<CMesModuleTableT> findAllModuleTable(CMesModuleTableT t);

	//查询数据
	public List<Map<String,Object>> findAll(@Param("id")Integer id,@Param("sn")String sn);
}
