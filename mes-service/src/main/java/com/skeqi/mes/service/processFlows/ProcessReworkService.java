package com.skeqi.mes.service.processFlows;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ProcessReworkService {

	public List<Map<String,Object>> showAllReworkInfo(String stationNum,String materiCode,String projectNum,String specificationModel);

	public Map<String,Object> showProcessDetailsInfo(String taskId,String processId,String id);

	public Integer updateReworkInfo(String reworkNum,String useReworkNums,String taskId,String processId,String reworkPerson,String id);

	public Integer revockReworkInfo(String useReworkNums,String taskId,String processId,String id);

	public List<Map<String,Object>> showAllReworkInfox(String stationNum,String materiCode,String projectNum,String specificationModel);

	public Map<String,Object> showTaskById(String taskId);

	public Integer updateReworkApprovalDatas(String OKNum,String NGNum,String reworkNum,String taskId,String processId,String person,String outOutsourceTem,String okFinishProductionTems,String id);
	public Integer updateReworkApprovalDatasx(String OKNum,String NGNum,String reworkNum,String taskId,String processId,String person,String outOutsourceTem,String okFinishProductionTems);

	public Integer updateTaskNGNum(String ngNum,String tasKId);

	public Integer updateApplyReworkInsideNum(String applyNum,String taskId,String processId,String id);

	public Integer updateRevockReworkInsideNum(String taskId,String processId,String id);

	public Integer updateReworkExamineInsideNum(String ngNum,String reworkNum,String applyNum,String taskId,String processId,String id,String okNum);

	public Integer updateReworkNumxx(String reworkNum,String taskId,String processId,String ngNum,String useNum);




}
