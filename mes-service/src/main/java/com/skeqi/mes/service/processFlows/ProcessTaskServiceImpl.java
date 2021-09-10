package com.skeqi.mes.service.processFlows;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.processFlows.ProcessTaskDao;

@Service
public class ProcessTaskServiceImpl implements ProcessTaskService {

	@Autowired
	private ProcessTaskDao dao;
	@Override
	public List<Map<String, Object>> showProcessTask() {
		// TODO Auto-generated method stub
		return dao.showProcessTask();
	}

	@Override
	public Integer addProcessTask(String projectName, String projectNum, String specificationModel, String materialCode,
			String routeName, Integer planNum, Integer completeNum, Integer NGNum, Integer reworkNum,
			String operationDepartment,Integer status,String images,String materialName,String materialQuality,String numRemarks,String stationNum) {
		// TODO Auto-generated method stub
		return dao.addProcessTask(projectName, projectNum, specificationModel, materialCode, routeName, planNum, completeNum, NGNum, reworkNum, operationDepartment,status,images,materialName,materialQuality,numRemarks,stationNum);
	}

	@Override
	public Integer editProcessTask(String projectName, String projectNum, String specificationModel,
			String materialCode, String routeName, Integer planNum,
			String operationDepartment, Integer id,String images,String materialName,String materialQuality,String numRemarks,String stationNum) {
		// TODO Auto-generated method stub
		return dao.editProcessTask(projectName, projectNum, specificationModel, materialCode, routeName, planNum,  operationDepartment, id,images,materialName,materialQuality,numRemarks,stationNum);
	}

	@Override
	public Integer delProcessTask(Integer id) {
		// TODO Auto-generated method stub
		return dao.delProcessTask(id);
	}

	@Override
	public List<Map<String, Object>> showRouteLists() {
		// TODO Auto-generated method stub
		return dao.showRouteLists();
	}

	@Override
	public Integer countProcessTask(String specificationModel, String projectNum,Integer id,String meterialCode,String stationNum) {
		// TODO Auto-generated method stub
		return dao.countProcessTask(specificationModel, projectNum,id,meterialCode,stationNum);
	}

	@Override
	public Integer updateTaskStatus(Integer id, String flags) {
		// TODO Auto-generated method stub
		return dao.updateTaskStatus(id, flags);
	}

	@Override
	public List<Map<String, Object>> showProcessTaskByCondition(String projectName, String projectNum,
			String specificationModel, String status,String id,String materiCode) {
		// TODO Auto-generated method stub
		return dao.showProcessTaskByCondition(projectName, projectNum, specificationModel, status,id,materiCode);
	}

	@Override
	public Integer countEditRoute(String routeId) {
		// TODO Auto-generated method stub
		return dao.countEditRoute(routeId);
	}

	@Override
	public Integer countDelRoute(String routeId) {
		// TODO Auto-generated method stub
		return dao.countDelRoute(routeId);
	}

	@Override
	public List<Integer> showRouteIds(String routeId) {
		// TODO Auto-generated method stub
		return dao.showRouteIds(routeId);
	}

	@Override
	public List<Map<String, Object>> showAllProcessTaskPage(Integer processId) {
		// TODO Auto-generated method stub
		return dao.showAllProcessTaskPage(processId);
	}

	@Override
	public List<Map<String, Object>> showAllProcessTaskPageByCondition(Integer processId, String projectName,
			String projectNum, String specificationModel, String status, String id) {
		// TODO Auto-generated method stub
		return dao.showAllProcessTaskPageByCondition(processId, projectName, projectNum, specificationModel, status, id);
	}

	@Override
	public Map<String, Object> showTaskInfoById(Integer id) {
		// TODO Auto-generated method stub
		return dao.showTaskInfoById(id);
	}

	@Override
	public Integer addPDFFile(String pdf) {
		// TODO Auto-generated method stub
		return dao.addPDFFile(pdf);
	}

	@Override
	public Integer showAlreadyNum(String specification, String meterialCode, String stationNum) {
		// TODO Auto-generated method stub
		return dao.showAlreadyNum(specification, meterialCode, stationNum);
	}

	@Override
	public Integer updateSattusInfox() {
		// TODO Auto-generated method stub
		return dao.updateSattusInfox();
	}

	@Override
	public List<Map<String, Object>> showRouteList() {
		// TODO Auto-generated method stub
		return dao.showRouteList();
	}

	@Override
	public List<Map<String, Object>> showRouteListxs(String processNum, String processName) {
		// TODO Auto-generated method stub
		return dao.showRouteListxs(processNum, processName);
	}

	@Override
	public List<Map<String, Object>> showAllProcessInfo(String id) {
		// TODO Auto-generated method stub
		return dao.showAllProcessInfo(id);
	}

	@Override
	public  List<Map<String,Object>> showRouteLineInfo(String id) {
		// TODO Auto-generated method stub
		return dao.showRouteLineInfo(id);
	}

	@Override
	public Integer addRoutListInfo(String routeName, String processName, String remarksInfos) {
		// TODO Auto-generated method stub
		return dao.addRoutListInfo(routeName, processName, remarksInfos);
	}

	@Override
	public Integer countNum(String routeName, String processName) {
		// TODO Auto-generated method stub
		return dao.countNum(routeName, processName);
	}

	@Override
	public List<Map<String, Object>> showAllRouteLists(String id) {
		// TODO Auto-generated method stub
		return dao.showAllRouteLists(id);
	}

	@Override
	public Integer updateRoutListInfo(String remarksInfos, String routeName, String processName) {
		// TODO Auto-generated method stub
		return dao.updateRoutListInfo(remarksInfos, routeName, processName);
	}

	@Override
	public List<Map<String, Object>> showAllProcessLists(String id) {
		// TODO Auto-generated method stub
		return dao.showAllProcessLists(id);
	}

	@Override
	public String showRouteId(String projectNum, String stationNum, String specification) {
		// TODO Auto-generated method stub
		return dao.showRouteId(projectNum, stationNum, specification);
	}

}
