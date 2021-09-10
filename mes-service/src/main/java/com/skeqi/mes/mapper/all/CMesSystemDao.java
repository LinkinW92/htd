package com.skeqi.mes.mapper.all;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.CMesSystem;


public interface CMesSystemDao {

	//查询系统数据
	List<CMesSystem> findByAll(@Param("name")String name);


	//修改系统数据
	Integer updateSystem(CMesSystem cMesSystem);



}
