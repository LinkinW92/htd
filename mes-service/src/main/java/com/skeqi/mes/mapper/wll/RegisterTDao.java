package com.skeqi.mes.mapper.wll;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.skeqi.mes.pojo.RegisterT;

public interface RegisterTDao {


	List<RegisterT> registerTList(Map<String, Object> map);

	void addRegisterT(Map<String, Object> map);

	void delregisterT(Map<String, Object> map);

	void updateregisterT(Map<String, Object> map);

	int queryRegisterT(@Param("hostName")String hostName);

	String queryHostName(@Param("id")int id);

}
