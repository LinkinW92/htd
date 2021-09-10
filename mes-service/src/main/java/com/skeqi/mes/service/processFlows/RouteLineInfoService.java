package com.skeqi.mes.service.processFlows;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface RouteLineInfoService {

//	获取工艺路线详情
	public List<Map<String,Object>> showRouteLine(String projectNum,String specificationModel,String stationNum,String startTime,String endTime);

//	获取routing的最大ID
	public String showMaxRouteLineId();

//  新增routing数据
	public Integer addRouteLine(String id,String projectNum,String stationNum,String specificationAndModel,String name);

//	查询是否存在routing的ID
	public Map<String,Object> countRoutrLine(String projectNum,String stationNum,String specificationAndModel);

//	展示工序名称的工序ID
	public Integer showStationId(String stationName);

//	展示是否存在重复的工艺路线信息
	public Integer countProductWay(String routeId,String serialNo);

//	新增productWay的数据
	public Integer addProductWay(String stationId,String routId,String serialNo,String processRemarks,String runtimes);

//	展示存在工艺路线的TASK
	public List<Map<String,Object>> showAllTaskByRouteId(String routeId);

//	查询工序详情
	public Map<String,Object> showDetailsInfo(String taskId,String processOrder);

//	遍历插入后的所有工序
	public List<Map<String,Object>> showAllDetailsInfoByTaskId(String taskId,String serialNo);

//	更新序列号
	public Integer updateDetailsOrderNum(String serialNo,String id);

//  展示way数据
	public List<Map<String,Object>> showProductWayBySTAndRoute(String routLineId,String serialNo);

// 	更新WAY数据
	public Integer updateWayDataInfos(String serialNo,String id);

//	跟新首工序数据
	public Integer addProcessDetails(Integer taskId,Integer planNum,String stationId,String serialNo);

//	查找工序类型（委外  非委外）
	public String showStationTypeInfos(String id);

//	获取最大的指定的details的工序顺序号
	public Integer showMaxDetailsIdByTaskId(String taskId);

//	获取指定ID的details
	public Map<String,Object> showDetailsById(String id);

//	获取最大的工序顺序ID
	public Integer showMaxId(String taskId,String processOrder);

//	获取最大ID
	public Integer countProductWayNum(String routeId);

//	更新委外数据
	public Integer updateDetailsOrderNumx(String serialNo,String id);

//	或许工艺路线最大序号
	public Integer showProductWayId(String routeId);

//	获取工艺路线最大的工序的序号ID
	public Integer showRouteId(String serialNo,String routeId);

//	检索工艺路线的所有工序ID
	public List<Integer> showAllStIdByWayId(String routeId);

//	删除way的信息
	public Integer delWayProductInfos(String stationId,String routeId,String serialNo);

//	删除后排序工序
	public List<Map<String,Object>> showProductWayBySTAndRoutes(String routLineId,String serialNo);

//	删除工艺路线
	public Integer delRouteLine(String routeId);

//	展示新的工序ID
	public Integer stId(String routeId,String serialNo);

//	删除task数据
	public Integer delTaskInfos(String taskId);

//	删除委外任务单数据
	public Integer delOutsideListInfos(String taskId);

//	删除工序详情数据
	public Integer delDetailsInfos(String taskId);

//  删除指任务单的指定工序
	public Integer delTaskDetailsInfo(String taskId,String order);

//	初始化新增数据details
	public Integer updateInitData(String taskId,String order);

//	获取区间details
	public List<Map<String,Object>> showAllDetailsInfoByTaskIdAndArea(String taskId,String minserialNo,String maxserialNo);

//	获取所有的way
	public List<Map<String,Object>> showProductWayByRouteId(String routeId);

//	获取当前way的ID
	public String showProductWayIdxxs(String routeId,String serialNo);

//	查找way的区域
	public List<Map<String,Object>> showAllWayInfoByTaskIdAndArea(String routeId,String minserialNo,String maxserialNo);

	public List<Map<String,Object>> showAllWayInfoByTaskIdAndAreaxx(String routeId,String minserialNo,String maxserialNo);

	public List<Map<String,Object>> showAllDetailsInfoByTaskIdAndAreaxx(String taskId,String minserialNo,String maxserialNo);

//	工艺路线更新运行时间
	public Integer updateWayDataInfosx(String runtimes,String id,String remarks);


}
