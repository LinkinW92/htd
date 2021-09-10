package com.skeqi.mes.service.processFlows;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ProcessLogInfoService {


	public List<Map<String,Object>> showAllProcessLogInfo(Integer id);

	public Integer addProcessLogInfo(String taskId,String processId,String logInfo,String logType);
}
