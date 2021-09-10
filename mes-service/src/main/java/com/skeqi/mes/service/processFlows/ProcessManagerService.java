package com.skeqi.mes.service.processFlows;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface ProcessManagerService {

//	展示工序列表

	public List<Map<String,Object>> showProcess();

//	新增工序

	public Integer addProcess(String processName,String processType,String unitPrice);

//	编辑工序

	public Integer updateProcess(String processName,String id,String processType,String unitPrice);

//	删除工序

	public Integer delProcess(Integer id);

	public Integer countProcessByName(String processName,String id);

	public Integer countRoutes(Integer id);

	public Integer showProductTaskByProcessId(Integer processId);

	public String showProcessById();

	public String showStationNameById(String id);

}
