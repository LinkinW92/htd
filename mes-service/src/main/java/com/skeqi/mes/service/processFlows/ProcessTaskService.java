package com.skeqi.mes.service.processFlows;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface ProcessTaskService {
//	测试pdf上传
	public Integer addPDFFile(String pdf);

//	展示生产任务

	public List<Map<String,Object>> showProcessTask();

//	新增生产任务

	public Integer addProcessTask(String projectName,String projectNum,String specificationModel,String materialCode,String routeName,Integer planNum,Integer completeNum,Integer NGNum,Integer reworkNum,String operationDepartment,Integer status,String images,String materialName,String materialQuality,String numRemarks,String stationNum);

//	编辑生产任务

	public Integer editProcessTask(String projectName,String projectNum,String specificationModel,String materialCode,String routeName,Integer planNum,String operationDepartment,Integer id,String images,String materialName,String materialQuality,String numRemarks,String stationNum);


//	删除生产任务

	public Integer delProcessTask(Integer id);

//	查询所有工艺路线
	public List<Map<String,Object>> showRouteLists();

	public Integer countProcessTask(String specificationModel,String projectNum,Integer id,String meterialCode,String stationNum);

	public Integer updateTaskStatus(Integer id,String flags);

	public List<Map<String,Object>> showProcessTaskByCondition(String stationNum,String projectNum,String specificationModel,String status,String id,String materiCode);

	public Integer countEditRoute(String routeId);

	public Integer countDelRoute(String routeId);

	public List<Integer> showRouteIds(String routeId);

	public List<Map<String,Object>> showAllProcessTaskPage(Integer processId);

	public List<Map<String,Object>> showAllProcessTaskPageByCondition(Integer processId,String projectName,String projectNum,String specificationModel,String status,String id);

	public Map<String,Object> showTaskInfoById(Integer id);

	public Integer showAlreadyNum(String specification,String meterialCode,String stationNum);

	public Integer updateSattusInfox();

	public List<Map<String,Object>> showRouteList();

	public List<Map<String,Object>> showRouteListxs(String processNum,String processName);

	public List<Map<String,Object>> showAllProcessInfo(String id);

	public List<Map<String,Object>> showRouteLineInfo(String id);

	public Integer addRoutListInfo(String routeName,String processName,String remarksInfos);

	public Integer countNum(String routeName,String processName);

	public List<Map<String,Object>> showAllRouteLists(String id);

	public Integer updateRoutListInfo(String remarksInfos,String routeName,String processName);

	public List<Map<String,Object>> showAllProcessLists(String id);

	public String showRouteId(String projectNum,String stationNum,String specification);
}
