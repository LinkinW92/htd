package com.skeqi.mes.service.wll;

import java.util.List;
import java.util.Map;

import com.skeqi.mes.pojo.RegisterT;

public interface RegisterTService {

	List<RegisterT> registertList(Map<String, Object> map);
	void addRegisterT(Map<String, Object> map);
	void delRegisterT(Map<String, Object> map);
	void updateRegisterT(Map<String, Object> map);
	int queryRegisterT(String hostName);
	String queryHostName(int id);
}
