package com.skeqi.mes.service.processFlows;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.processFlows.ProcessReworkDao;

@Service
public class ProcessReworkServiceImpl implements ProcessReworkService {

	@Autowired
	private ProcessReworkDao dao;
	@Override
	public List<Map<String, Object>> showAllReworkInfo(String stationNum,String materiCode,String projectNum,String specificationModel) {
		// TODO Auto-generated method stub
		return dao.showAllReworkInfo(stationNum,materiCode,projectNum,specificationModel);
	}
	@Override
	public Map<String, Object> showProcessDetailsInfo(String taskId, String processId,String id) {
		// TODO Auto-generated method stub
		return dao.showProcessDetailsInfo(taskId, processId,id);
	}
	@Override
	public Integer updateReworkInfo(String reworkNum, String useReworkNums, String taskId, String processId,String reworkPerson,String id) {
		// TODO Auto-generated method stub
		return dao.updateReworkInfo(reworkNum, useReworkNums, taskId, processId,reworkPerson,id);
	}
	@Override
	public Integer revockReworkInfo(String useReworkNums, String taskId, String processId,String id) {
		// TODO Auto-generated method stub
		return dao.revockReworkInfo(useReworkNums, taskId, processId,id);
	}
	@Override
	public List<Map<String, Object>> showAllReworkInfox(String stationNum,String materiCode,String projectNum,String specificationModel) {
		// TODO Auto-generated method stub
		return dao.showAllReworkInfox(stationNum,materiCode,projectNum,specificationModel);
	}
	@Override
	public Map<String, Object> showTaskById(String taskId) {
		// TODO Auto-generated method stub
		return dao.showTaskById(taskId);
	}
	@Override
	public Integer updateReworkApprovalDatas(String OKNum, String NGNum, String reworkNum, String taskId,
			String processId,String person,String outOutsourceTem,String okFinishProductionTems,String id) {
		// TODO Auto-generated method stub
		return dao.updateReworkApprovalDatas(OKNum, NGNum, reworkNum, taskId, processId,person,outOutsourceTem,okFinishProductionTems,id);
	}
	@Override
	public Integer updateTaskNGNum(String ngNum,String tasKId) {
		// TODO Auto-generated method stub
		return dao.updateTaskNGNum(ngNum,tasKId);
	}
	@Override
	public Integer updateReworkApprovalDatasx(String OKNum, String NGNum, String reworkNum, String taskId,
			String processId, String person, String outOutsourceTem, String okFinishProductionTems) {
		// TODO Auto-generated method stub
		return dao.updateReworkApprovalDatasx(OKNum, NGNum, reworkNum, taskId, processId, person, outOutsourceTem, okFinishProductionTems);
	}
	@Override
	public Integer updateApplyReworkInsideNum(String applyNum, String taskId, String processId, String id) {
		// TODO Auto-generated method stub
		return dao.updateApplyReworkInsideNum(applyNum, taskId, processId, id);
	}
	@Override
	public Integer updateRevockReworkInsideNum(String taskId, String processId, String id) {
		// TODO Auto-generated method stub
		return dao.updateRevockReworkInsideNum(taskId, processId, id);
	}
	@Override
	public Integer updateReworkExamineInsideNum(String ngNum, String reworkNum, String applyNum, String taskId,
			String processId, String id,String okNum) {
		// TODO Auto-generated method stub
		return dao.updateReworkExamineInsideNum(ngNum, reworkNum, applyNum, taskId, processId, id,okNum);
	}
	@Override
	public Integer updateReworkNumxx(String reworkNum, String taskId, String processId,String ngNum,String useNum) {
		// TODO Auto-generated method stub
		return dao.updateReworkNumxx(reworkNum, taskId, processId,ngNum,useNum);
	}

}
