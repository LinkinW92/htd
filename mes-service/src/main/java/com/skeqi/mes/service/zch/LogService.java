package com.skeqi.mes.service.zch;

import java.util.List;
import java.util.Map;

import com.skeqi.mes.pojo.zch.MesOperationLogT;

public interface LogService {

	Integer addOperationLog(MesOperationLogT logBo);

	List<Map<String, Object>> findOperationLogList(Map<String, Object> map);

	void test();

	Integer addVisitLog(Map<String, Object> map);

	Integer addErrorLog(Map<String, Object> map);

	List<Map<String, Object>> findVisitLogList(Map<String, Object> map);

}
