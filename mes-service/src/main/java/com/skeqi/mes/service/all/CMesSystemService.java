package com.skeqi.mes.service.all;

import java.util.List;

import com.skeqi.mes.Exception.util.ServicesException;
import com.skeqi.mes.pojo.CMesSystem;

public interface CMesSystemService {
	   //查询系统数据
		List<CMesSystem> findByAll(String name)throws ServicesException;

		//修改系统数据
		Integer updateSystem(CMesSystem cMesSystem)throws ServicesException;
}
