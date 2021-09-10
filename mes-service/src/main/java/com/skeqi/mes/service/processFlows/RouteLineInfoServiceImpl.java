package com.skeqi.mes.service.processFlows;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.processFlows.RouteLineInfoDao;

@Service
public class RouteLineInfoServiceImpl implements RouteLineInfoService {
	@Autowired
	private RouteLineInfoDao dao;

	@Override
	public List<Map<String, Object>> showRouteLine(String projectNum,String specificationModel,String stationNum,String startTime,String endTime) {
		// TODO Auto-generated method stub
		return dao.showRouteLine(projectNum,specificationModel,stationNum,startTime,endTime);
	}

	@Override
	public String showMaxRouteLineId() {
		// TODO Auto-generated method stub
		return dao.showMaxRouteLineId();
	}

	@Override
	public Integer addRouteLine(String id, String projectNum, String stationNum, String specificationAndModel,
			String name) {
		// TODO Auto-generated method stub
		return dao.addRouteLine(id, projectNum, stationNum, specificationAndModel, name);
	}

	@Override
	public Map<String,Object> countRoutrLine(String projectNum, String stationNum, String specificationAndModel) {
		// TODO Auto-generated method stub
		return dao.countRoutrLine(projectNum, stationNum, specificationAndModel);
	}

	@Override
	public Integer showStationId(String stationName) {
		// TODO Auto-generated method stub
		return dao.showStationId(stationName);
	}

	@Override
	public Integer countProductWay( String routeId, String serialNo) {
		// TODO Auto-generated method stub
		return dao.countProductWay(routeId, serialNo);
	}

	@Override
	public Integer addProductWay(String stationId, String routId, String serialNo, String processRemarks,String runtimes) {
		// TODO Auto-generated method stub
		return dao.addProductWay(stationId, routId, serialNo, processRemarks,runtimes);
	}

	@Override
	public List<Map<String, Object>> showAllTaskByRouteId(String routeId) {
		// TODO Auto-generated method stub
		return dao.showAllTaskByRouteId(routeId);
	}

	@Override
	public Map<String, Object> showDetailsInfo(String taskId, String processOrder) {
		// TODO Auto-generated method stub
		return dao.showDetailsInfo(taskId, processOrder);
	}

	@Override
	public List<Map<String, Object>> showAllDetailsInfoByTaskId(String taskId, String serialNo) {
		// TODO Auto-generated method stub
		return dao.showAllDetailsInfoByTaskId(taskId, serialNo);
	}

	@Override
	public Integer updateDetailsOrderNum(String serialNo, String id) {
		// TODO Auto-generated method stub
		return dao.updateDetailsOrderNum(serialNo, id);
	}

	@Override
	public List<Map<String, Object>> showProductWayBySTAndRoute( String routLineId, String serialNo) {
		// TODO Auto-generated method stub
		return dao.showProductWayBySTAndRoute( routLineId, serialNo);
	}

	@Override
	public Integer updateWayDataInfos(String serialNo, String id) {
		// TODO Auto-generated method stub
		return dao.updateWayDataInfos(serialNo, id);
	}

	@Override
	public Integer addProcessDetails(Integer taskId, Integer planNum, String stationId, String serialNo) {
		// TODO Auto-generated method stub
		return dao.addProcessDetails(taskId, planNum, stationId, serialNo);
	}

	@Override
	public String showStationTypeInfos(String id) {
		// TODO Auto-generated method stub
		return dao.showStationTypeInfos(id);
	}

	@Override
	public Integer showMaxDetailsIdByTaskId(String taskId) {
		// TODO Auto-generated method stub
		return dao.showMaxDetailsIdByTaskId(taskId);
	}

	@Override
	public Map<String, Object> showDetailsById(String id) {
		// TODO Auto-generated method stub
		return dao.showDetailsById(id);
	}

	@Override
	public Integer showMaxId(String taskId, String processOrder) {
		// TODO Auto-generated method stub
		return dao.showMaxId(taskId, processOrder);
	}

	@Override
	public Integer countProductWayNum(String routeId) {
		// TODO Auto-generated method stub
		return dao.countProductWayNum(routeId);
	}

	@Override
	public Integer updateDetailsOrderNumx(String serialNo, String id) {
		// TODO Auto-generated method stub
		return dao.updateDetailsOrderNumx(serialNo, id);
	}

	@Override
	public Integer showProductWayId(String routeId) {
		// TODO Auto-generated method stub
		return dao.showProductWayId(routeId);
	}

	@Override
	public Integer showRouteId(String serialNo, String routeId) {
		// TODO Auto-generated method stub
		return dao.showRouteId(serialNo, routeId);
	}

	@Override
	public List<Integer> showAllStIdByWayId(String routeId) {
		// TODO Auto-generated method stub
		return dao.showAllStIdByWayId(routeId);
	}

	@Override
	public Integer delWayProductInfos(String stationId, String routeId, String serialNo) {
		// TODO Auto-generated method stub
		return dao.delWayProductInfos(stationId, routeId, serialNo);
	}

	@Override
	public List<Map<String, Object>> showProductWayBySTAndRoutes(String routLineId, String serialNo) {
		// TODO Auto-generated method stub
		return dao.showProductWayBySTAndRoutes(routLineId, serialNo);
	}

	@Override
	public Integer delRouteLine(String routeId) {
		// TODO Auto-generated method stub
		return dao.delRouteLine(routeId);
	}

	@Override
	public Integer stId(String routeId, String serialNo) {
		// TODO Auto-generated method stub
		return dao.stId(routeId, serialNo);
	}

	@Override
	public Integer delTaskInfos(String taskId) {
		// TODO Auto-generated method stub
		return dao.delTaskInfos(taskId);
	}

	@Override
	public Integer delOutsideListInfos(String taskId) {
		// TODO Auto-generated method stub
		return dao.delOutsideListInfos(taskId);
	}

	@Override
	public Integer delDetailsInfos(String taskId) {
		// TODO Auto-generated method stub
		return dao.delDetailsInfos(taskId);
	}

	@Override
	public Integer delTaskDetailsInfo(String taskId, String order) {
		// TODO Auto-generated method stub
		return dao.delTaskDetailsInfo(taskId, order);
	}

	@Override
	public Integer updateInitData(String taskId, String order) {
		// TODO Auto-generated method stub
		return dao.updateInitData(taskId, order);
	}

	@Override
	public List<Map<String, Object>> showAllDetailsInfoByTaskIdAndArea(String taskId, String minserialNo,
			String maxserialNo) {
		// TODO Auto-generated method stub
		return dao.showAllDetailsInfoByTaskIdAndArea(taskId, minserialNo, maxserialNo);
	}

	@Override
	public List<Map<String, Object>> showProductWayByRouteId(String routeId) {
		// TODO Auto-generated method stub
		return dao.showProductWayByRouteId(routeId);
	}

	@Override
	public String showProductWayIdxxs(String routeId, String serialNo) {
		// TODO Auto-generated method stub
		return dao.showProductWayIdxxs(routeId, serialNo);
	}

	@Override
	public List<Map<String, Object>> showAllWayInfoByTaskIdAndArea(String routeId, String minserialNo,
			String maxserialNo) {
		// TODO Auto-generated method stub
		return dao.showAllWayInfoByTaskIdAndArea(routeId, minserialNo, maxserialNo);
	}

	@Override
	public List<Map<String, Object>> showAllWayInfoByTaskIdAndAreaxx(String routeId, String minserialNo,
			String maxserialNo) {
		// TODO Auto-generated method stub
		return dao.showAllWayInfoByTaskIdAndAreaxx(routeId, minserialNo, maxserialNo);
	}

	@Override
	public List<Map<String, Object>> showAllDetailsInfoByTaskIdAndAreaxx(String taskId, String minserialNo,
			String maxserialNo) {
		// TODO Auto-generated method stub
		return dao.showAllDetailsInfoByTaskIdAndAreaxx(taskId, minserialNo, maxserialNo);
	}

	@Override
	public Integer updateWayDataInfosx(String runtimes, String id,String remarks) {
		// TODO Auto-generated method stub
		return dao.updateWayDataInfosx(runtimes, id,remarks);
	}





}
