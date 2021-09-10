package com.skeqi.mes.service.processFlows;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.processFlows.ProcessDetailsDao;

@Service
public class ProcessDetailsServiceImpl implements ProcessDetailsService {

	@Autowired
	private ProcessDetailsDao dao;


	@Override
	public List<Map<String, Object>> showProcessDetails(Integer taskId) {
		// TODO Auto-generated method stub
		return dao.showProcessDetails(taskId);
	}

	@Override
	public Integer addProcessDetails(Integer taskId, Integer planNum,
			List<Map<String, Object>> list) {
		// TODO Auto-generated method stub
		return dao.addProcessDetails(taskId, planNum, list);
	}

	@Override
	public Integer delProcessDetails(Integer taskId) {
		// TODO Auto-generated method stub
		return dao.delProcessDetails(taskId);
	}

	@Override
	public Integer editProcessDetails(Integer planNum, Integer taskId) {
		// TODO Auto-generated method stub
		return dao.editProcessDetails(planNum, taskId);
	}

	@Override
	public List<Map<String, Object>> showRouteList(Integer routeId,String taskId) {
		// TODO Auto-generated method stub
		return dao.showRouteList(routeId,taskId);
	}

	@Override
	public Integer showTaskId(String projectNum, String specificationModel,String stationName) {
		// TODO Auto-generated method stub
		return dao.showTaskId(projectNum, specificationModel,stationName);
	}

	@Override
	public Integer updateFirstProcessDetails(Integer useable,Integer useablex, Integer taskId) {
		// TODO Auto-generated method stub
		return dao.updateFirstProcessDetails(useable,useablex, taskId);
	}

	@Override
	public Map<String, Object> showTaskById(Integer id) {
		// TODO Auto-generated method stub
		return dao.showTaskById(id);
	}

	@Override
	public Integer updatePlanNum(Integer planNum, Integer taskId) {
		// TODO Auto-generated method stub
		return dao.updatePlanNum(planNum, taskId);
	}

	@Override
	public Map<String, Object> showFirstProcessInfo(Integer taskId) {
		// TODO Auto-generated method stub
		return dao.showFirstProcessInfo(taskId);
	}

	@Override
	public List<Map<String, Object>> showAllProcessLogInfo(String taskId, String processId) {
		// TODO Auto-generated method stub
		return dao.showAllProcessLogInfo(taskId, processId);
	}

	@Override
	public List<Map<String, Object>> showAllProcessLogByTypeIds(String typeId) {
		// TODO Auto-generated method stub
		return dao.showAllProcessLogByTypeIds(typeId);
	}

	@Override
	public List<Map<String, Object>> showQualityLog(String logType,String user,String beginTime,String endTime,String specificationModel) {
		// TODO Auto-generated method stub
		return dao.showQualityLog(logType,user,beginTime,endTime,specificationModel);
	}

	@Override
	public List<Map<String, Object>> showAllProcessLogByTypeIdsx(String typeId) {
		// TODO Auto-generated method stub
		return dao.showAllProcessLogByTypeIdsx(typeId);
	}

	@Override
	public List<Map<String, Object>> showRouteLists(Integer routeId) {
		// TODO Auto-generated method stub
		return dao.showRouteLists(routeId);
	}

	@Override
	public Integer addFirstProcessInfos(String taskId, String processId, String serialNo, String planNum) {
		// TODO Auto-generated method stub
		return dao.addFirstProcessInfos(taskId, processId, serialNo, planNum);
	}

	@Override
	public List<Map<String, Object>> showRuteListxs(Integer routeId) {
		// TODO Auto-generated method stub
		return dao.showRuteListxs(routeId);
	}

	@Override
	public Map<String, Object> showAllDetailsInfoByProcessIdData(String taskId, String processId) {
		// TODO Auto-generated method stub
		return dao.showAllDetailsInfoByProcessIdData(taskId, processId);
	}

	@Override
	public Integer updateDetailsAreadyData(String processOrder, String taskId, String processId) {
		// TODO Auto-generated method stub
		return dao.updateDetailsAreadyData(processOrder, taskId, processId);
	}

	@Override
	public Integer updateFirstDataByOrderByOne(String testNum, String taskId) {
		// TODO Auto-generated method stub
		return dao.updateFirstDataByOrderByOne(testNum, taskId);
	}

	@Override
	public Integer showTaskStatusDataw(String taskId) {
		// TODO Auto-generated method stub
		return dao.showTaskStatusDataw(taskId);
	}

	@Override
	public Map<String, Object> showProcessDetailsxx(Integer stakId, Integer order) {
		// TODO Auto-generated method stub
		return dao.showProcessDetailsxx(stakId, order);
	}

	@Override
	public Integer updateFirstProcessDetailsx(Integer useable, Integer taskId) {
		// TODO Auto-generated method stub
		return dao.updateFirstProcessDetailsx(useable, taskId);
	}

	@Override
	public Integer selectProcessIdById(String taskId) {
		// TODO Auto-generated method stub
		return dao.selectProcessIdById(taskId);
	}

	@Override
	public Integer updateFirstProcessPushDownDetails(Integer useable, Integer taskId) {
		// TODO Auto-generated method stub
		return dao.updateFirstProcessPushDownDetails(useable, taskId);
	}

	@Override
	public String showStationTypeInfo(String id) {
		// TODO Auto-generated method stub
		return dao.showStationTypeInfo(id);
	}

	@Override
	public Integer showProductWayId(String routeId) {
		// TODO Auto-generated method stub
		return dao.showProductWayId(routeId);
	}

	@Override
	public Integer showRouteId(String id,String routeId) {
		// TODO Auto-generated method stub
		return dao.showRouteId(id, routeId);
	}
}
