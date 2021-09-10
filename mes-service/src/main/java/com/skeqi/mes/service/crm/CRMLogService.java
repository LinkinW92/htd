package com.skeqi.mes.service.crm;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface CRMLogService {

	public Integer addCRMLogInfo(String user,String menuName);

	public List<Map<String,Object>> showUser(String userName);
}
