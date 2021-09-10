package com.skeqi.mes.service.processFlows;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface ProcessDetailsService {

//	展示工序详情

	public List<Map<String,Object>> showProcessDetails(Integer taskId);

//	新增工序详情

	public Integer addProcessDetails(Integer taskId,Integer planNum,List<Map<String,Object>>list);

//  删除工序详情（按照关联生产任务ID删除）

	public Integer delProcessDetails(Integer taskId);

//	编辑工序详情(适用于暂停状态下的编辑计划数量)

	public Integer editProcessDetails(Integer planNum,Integer taskId);

	public List<Map<String,Object>> showRouteList(Integer routeId,String taskId);

	public List<Map<String,Object>> showRouteLists(Integer routeId);

	public Integer showTaskId(String projectNum,String specificationModel,String stationName);

	public Integer updateFirstProcessDetails(Integer useable,Integer useablex,Integer taskId);

	public Map<String,Object> showTaskById(Integer id);

	public Integer updatePlanNum(Integer planNum,Integer taskId );

	public Map<String,Object> showFirstProcessInfo(Integer taskId);

	public List<Map<String,Object>> showAllProcessLogInfo(String taskId,String processId);

	public List<Map<String,Object>> showAllProcessLogByTypeIds(String typeId);

	public List<Map<String,Object>> showAllProcessLogByTypeIdsx(String typeId);

	public List<Map<String,Object>> showQualityLog(String logType,String user,String beginTime,String endTime,String specificationModel);

//	添加首站信息
	public Integer addFirstProcessInfos(String taskId,String processId,String serialNo,String planNum);

	public List<Map<String,Object>>  showRuteListxs(Integer routeId);

	public Map<String,Object> showAllDetailsInfoByProcessIdData(String taskId,String processId);

	public Integer updateDetailsAreadyData(String processOrder,String taskId,String processId);

	public Integer updateFirstDataByOrderByOne(String testNum,String taskId);

	public Integer showTaskStatusDataw(String taskId);

	public Map<String,Object> showProcessDetailsxx(Integer stakId,Integer order);

	public Integer updateFirstProcessDetailsx(Integer useable,Integer taskId);

	public Integer selectProcessIdById(String taskId);

	public Integer updateFirstProcessPushDownDetails(Integer useable,Integer taskId);

	public String showStationTypeInfo(String id);

	public Integer showProductWayId(String routeId);

	public Integer showRouteId(String id,String routeId);
}
