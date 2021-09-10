package com.skeqi.mes.service.processFlows;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skeqi.mes.mapper.processFlows.ProcessClientDao;

@Service
public class ProcessClientServiceImpl implements ProcessClientService {

	@Autowired
	private ProcessClientDao dao;
	@Override
	public List<Map<String, Object>> showAllClientRoute(String projectNum, String specificationModels) {
		// TODO Auto-generated method stub
		return dao.showAllClientRoute(projectNum, specificationModels);
	}
	@Override
	public Map<String, Object> showTaskList(String peojectNum, String specificationModel,String processName,String meterialCode,String stationNum) {
		// TODO Auto-generated method stub
		return dao.showTaskList(peojectNum, specificationModel,processName,meterialCode,stationNum);
	}
	@Override
	public Integer updateDetailsUseable(Integer useAble, Integer taskId, Integer id,Integer testingNum,Integer ngNum,Integer outNum) {
		// TODO Auto-generated method stub
		return dao.updateDetailsUseable(useAble, taskId, id, testingNum,ngNum,outNum);
	}
	@Override
	public Integer showprocessType(Integer id) {
		// TODO Auto-generated method stub
		return dao.showprocessType(id);
	}
	@Override
	public Integer updateReworksDetailsUseable(Integer useAble, Integer taskId, Integer id, Integer reworkNum,
			Integer ngNum) {
		// TODO Auto-generated method stub
		return dao.updateReworksDetailsUseable(useAble, taskId, id, reworkNum, ngNum);
	}
	@Override
	public Integer updateProcessTaskInfo(Integer ngNum, Integer reworkNum, Integer id) {
		// TODO Auto-generated method stub
		return dao.updateProcessTaskInfo(ngNum, reworkNum, id);
	}
	@Override
	public Map<String, Object> showProcessTaskInfos(Integer id) {
		// TODO Auto-generated method stub
		return dao.showProcessTaskInfos(id);
	}
	@Override
	public Integer updateDetailsTaskInfos(Integer completeNum, Integer useAble, Integer id,Integer taskId) {
		// TODO Auto-generated method stub
		return dao.updateDetailsTaskInfos(completeNum, useAble, id,taskId);
	}
	@Override
	public Integer maxOrder(Integer taskId) {
		// TODO Auto-generated method stub
		return dao.maxOrder(taskId);
	}
	@Override
	public Map<String, Object> showNextProcessInfo(Integer taskId, Integer orderNum) {
		// TODO Auto-generated method stub
		return dao.showNextProcessInfo(taskId, orderNum);
	}
	@Override
	public Integer updateNextProcessInfo(Integer testingNum, Integer taskId, Integer orderNum,Integer nextOutNum) {
		// TODO Auto-generated method stub
		return dao.updateNextProcessInfo(testingNum, taskId, orderNum,nextOutNum);
	}
	@Override
	public List<Map<String, Object>> showAllProcessTypeTaskInfos(Integer typeId,String projectNum,String specificationModel,String statusFlags,String flagx,String stationNum,String materiCode) {
		// TODO Auto-generated method stub
		return dao.showAllProcessTypeTaskInfos(typeId,projectNum,specificationModel,statusFlags,flagx,stationNum,materiCode);
	}
	@Override
	public List<Map<String, Object>> showAllRouteNameList(Integer taskId, Integer typeId) {
		// TODO Auto-generated method stub
		return dao.showAllRouteNameList(taskId, typeId);
	}
	@Override
	public Integer updateProcessTaskCompleteInfo(Integer complete, Integer statusFlags, Integer taskId) {
		// TODO Auto-generated method stub
		return dao.updateProcessTaskCompleteInfo(complete, statusFlags, taskId);
	}
	@Override
	public Integer showNowProcesstypeInfo(String projectNum, String specificationModel, Integer processId) {
		// TODO Auto-generated method stub
		return dao.showNowProcesstypeInfo(projectNum, specificationModel, processId);
	}
	@Override
	public Integer updateDetailsTaskInInfos(Integer completeNum, Integer useAble, Integer id, Integer taskId,
			Integer outsourceInNum) {
		// TODO Auto-generated method stub
		return dao.updateDetailsTaskInInfos(completeNum, useAble, id, taskId, outsourceInNum);
	}
	@Override
	public List<Map<String, Object>> showAllProcessTypeTaskOutInfos(String typeId,String flags,String stationNum,String projectNum,String specificationModel,String materiCode) {
		// TODO Auto-generated method stub
		return dao.showAllProcessTypeTaskOutInfos(typeId,flags,stationNum,projectNum,specificationModel,materiCode);
	}
	@Override
	public Integer updateOutNums( Integer useAble, Integer id, Integer taskId) {
		// TODO Auto-generated method stub
		return dao.updateOutNums( useAble, id, taskId);
	}
	@Override
	public String showProcessName(Integer id) {
		// TODO Auto-generated method stub
		return dao.showProcessName(id);
	}
	@Override
	public Map<String,Object> showTaskStatusInfos(Integer id) {
		// TODO Auto-generated method stub
		return dao.showTaskStatusInfos(id);
	}
	@Override
	public List<Map<String, Object>> showQualityInfoList(String statusFlags,String stationNum,String projectNum,String specificationModel,String materiCode,String processName) {
		// TODO Auto-generated method stub
		return dao.showQualityInfoList(statusFlags,stationNum,projectNum,specificationModel,materiCode,processName);
	}

	@Override
	public Integer updateQualityApprovalData(String testingNum, String OKNum, String NGNum, String taskId,
			String peocessId,String person,String remarks,String allRemarks,String outsource) {
		// TODO Auto-generated method stub
		return dao.updateQualityApprovalData(testingNum, OKNum, NGNum, taskId, peocessId,person,remarks,allRemarks,outsource);
	}
	@Override
	public Integer updateQualityApprovalList(String taskId, String peocessId, String NGNum, String flags,
			String remarks, String qualityPerson,String outsourceTem,String testNum,String testNumTem,String flagsx,String reworksNum,String id) {
		// TODO Auto-generated method stub
		return dao.updateQualityApprovalList(taskId, peocessId, NGNum, flags, remarks, qualityPerson,outsourceTem,testNum,testNumTem,flagsx,reworksNum,id);
	}
	@Override
	public Map<String, Object> showAllQualityApprovasList(String statusFlags, String id, String processId,String ids) {
		// TODO Auto-generated method stub
		return dao.showAllQualityApprovasList(statusFlags, id, processId,ids);
	}
	@Override
	public Integer showNGNum(String id) {
		// TODO Auto-generated method stub
		return dao.showNGNum(id);
	}
	@Override
	public Integer updateTaskNGNum(String id, Integer NGNum) {
		// TODO Auto-generated method stub
		return dao.updateTaskNGNum(id, NGNum);
	}
	@Override
	public Integer updateNGNumTem(String taskId, String peocessId) {
		// TODO Auto-generated method stub
		return dao.updateNGNumTem(taskId, peocessId);
	}
	@Override
	public Integer showOrder(String taskId, String processId) {
		// TODO Auto-generated method stub
		return dao.showOrder(taskId, processId);
	}
	@Override
	public Integer revokeApproval(String taskId, String processId,Integer outRouce,Integer testNum) {
		// TODO Auto-generated method stub
		return dao.revokeApproval(taskId, processId,outRouce,testNum);
	}
	@Override
	public Integer finishProductApproval(String finishProduct,String taskId,String processId,String person,String remarks,Integer okOutsourceTem,Integer okFinishProductionTems) {
		// TODO Auto-generated method stub
		return dao.finishProductApproval(finishProduct,taskId,processId,person,remarks,okOutsourceTem,okFinishProductionTems);
	}
	@Override
	public List<Map<String, Object>> showAllFinishProduct(String flags,String stationNum,String materiCode,String projectNum,String specificationModel) {
		// TODO Auto-generated method stub
		return dao.showAllFinishProduct(flags,stationNum,materiCode,projectNum,specificationModel);
	}
	@Override
	public Map<String, Object> showDetailsInfos(String taskId, String processId,String id) {
		// TODO Auto-generated method stub
		return dao.showDetailsInfos(taskId, processId,id);
	}
	@Override
	public Integer updateDetailsInfo(String complete, String useNum, String taskId, String processId,String flags) {
		// TODO Auto-generated method stub
		return dao.updateDetailsInfo(complete, useNum, taskId, processId,flags);
	}
	@Override
	public Integer updateNewTaskInfos(String complete, String flags, String id) {
		// TODO Auto-generated method stub
		return dao.updateNewTaskInfos(complete, flags, id);
	}
	@Override
	public Integer revokeFinishProductNum(String useName, String taskId, String processId) {
		// TODO Auto-generated method stub
		return dao.revokeFinishProductNum(useName, taskId, processId);
	}
	@Override
	public Integer updateOutSourceInfo(String testNum, String supplier, String taskId, String processId,String person,String flags,Integer testNums,String ids) {
		// TODO Auto-generated method stub
		return dao.updateOutSourceInfo(testNum, supplier, taskId, processId,person,flags,testNums,ids);
	}
	@Override
	public Integer updateOutSourceInfox(String testNum, String testingNum, String taskId, String processId,
			String flags,String person,Integer outNumx,String id) {
		// TODO Auto-generated method stub
		return dao.updateOutSourceInfox(testNum, testingNum, taskId, processId, flags,person,outNumx,id);
	}
	@Override
	public Integer revokeOutSource(String testNum, String taskId, String processId, String flags,String id) {
		// TODO Auto-generated method stub
		return dao.revokeOutSource(testNum, taskId, processId, flags,id);
	}
	@Override
	public Integer updateInSourceInfo(String inSupplier, String okInsourceTem, String flags, String taskId,
			String processId,String person,Integer okOutsourceTems,Integer newOutSourceInNum,String id) {
		// TODO Auto-generated method stub
		return dao.updateInSourceInfo(inSupplier, okInsourceTem, flags, taskId, processId,person,okOutsourceTems,newOutSourceInNum,id);
	}
	@Override
	public Integer updateRevokeSourceInfo(String useNum, String taskId, String peocessId,String id) {
		// TODO Auto-generated method stub
		return dao.updateRevokeSourceInfo(useNum, taskId, peocessId,id);
	}
	@Override
	public Integer updateInSourceprocessInfo(String flags, String inPersonApproval, String OkNum, String useNum,String taskId,String processId,String id) {
		// TODO Auto-generated method stub
		return dao.updateInSourceprocessInfo(flags, inPersonApproval, OkNum, useNum,taskId,processId,id);
	}
	@Override
	public Integer updateQualityApprovalLists(String flags, String testNumTem, String okNumTemApproval,
			String ngNumTemApproval, String taskId, String processId,String remarks,String person,String ids) {
		// TODO Auto-generated method stub
		return dao.updateQualityApprovalLists(flags, testNumTem, okNumTemApproval, ngNumTemApproval, taskId, processId,remarks,person,ids);
	}
	@Override
	public Integer updateQualityByFlags(String testNumTem, String taskId, String processId,String ids) {
		// TODO Auto-generated method stub
		return dao.updateQualityByFlags(testNumTem, taskId, processId,ids);
	}
	@Override
	public Integer updateQualityApprovalDatas(String testing, String ngNum, String userNum, String taskId,
			String processId,String outsourceNum,String newUseNum,String id) {
		// TODO Auto-generated method stub
		return dao.updateQualityApprovalDatas(testing, ngNum, userNum, taskId, processId,outsourceNum,newUseNum,id);
	}
	@Override
	public Integer updateQualityInSourceApprovalLists(String flags, String testNumTem, String okNumTemApproval,
			String ngNumTemApproval, String taskId, String processId, String remarks, String person,String ids) {
		// TODO Auto-generated method stub
		return dao.updateQualityInSourceApprovalLists(flags, testNumTem, okNumTemApproval, ngNumTemApproval, taskId, processId, remarks, person,ids);
	}
	@Override
	public Integer updateQualityByFlagsx(String testNumTem, String taskId, String processId,String ids) {
		// TODO Auto-generated method stub
		return dao.updateQualityByFlagsx(testNumTem, taskId, processId,ids);
	}
	@Override
	public List<Map<String, Object>> showAllProcessTypeTaskInfosx(Integer typeId, String projectNum,
			String specificationModel, String statusFlags, String flagx,String stationNum,String materiCode) {
		// TODO Auto-generated method stub
		return dao.showAllProcessTypeTaskInfosx(typeId, projectNum, specificationModel, statusFlags, flagx,stationNum,materiCode);
	}
	@Override
	public List<Map<String, Object>> showAllProcessTypeTaskOutInfosx(String typeId, String flags,String stationNum,String materiCode,String projectNum,String specificationModel) {
		// TODO Auto-generated method stub
		return dao.showAllProcessTypeTaskOutInfosx(typeId, flags,stationNum,materiCode,projectNum,specificationModel);
	}
	@Override
	public List<Map<String, Object>> showAllProcessTypeTaskOutInfosxs(String typeId, String flags,String stationNum,String materiCode,String projectNum,String specificationModel) {
		// TODO Auto-generated method stub
		return dao.showAllProcessTypeTaskOutInfosxs(typeId, flags,stationNum,materiCode,projectNum,specificationModel);
	}
	@Override
	public Map<String, Object> showAllQualityApprovasListx(String statusFlags, String id, String processId) {
		// TODO Auto-generated method stub
		return dao.showAllQualityApprovasListx(statusFlags, id, processId);
	}
	@Override
	public List<Map<String, Object>> showAllFinishProductx(String flags,String stationNum,String materiCode,String projectNum,String specificationModel) {
		// TODO Auto-generated method stub
		return dao.showAllFinishProductx(flags,stationNum,materiCode,projectNum,specificationModel);
	}
	@Override
	public Integer updateQualityApprovalDatasx(String testing, String ngNum, String userNum, String taskId,
			String processId, String outsourceNum,String newUseName,String id) {
		// TODO Auto-generated method stub
		return dao.updateQualityApprovalDatasx(testing, ngNum, userNum, taskId, processId, outsourceNum,newUseName,id);
	}
	@Override
	public Integer showProcessById(String taskId, String processId,String id) {
		// TODO Auto-generated method stub
		return dao.showProcessById(taskId, processId,id);
	}
	@Override
	public Map<String, Object> showTaskById(String taskId) {
		// TODO Auto-generated method stub
		return dao.showTaskById(taskId);
	}
	@Override
	public String showStationName(String processId) {
		// TODO Auto-generated method stub
		return dao.showStationName(processId);
	}
	@Override
	public Integer updateNextProcessInfox(Integer testingNum, Integer taskId, Integer orderNum, Integer nextOutNum,String pushDownNum) {
		// TODO Auto-generated method stub
		return dao.updateNextProcessInfox(testingNum, taskId, orderNum, nextOutNum,pushDownNum);
	}
	@Override
	public  List<Map<String,Object>> showAllSplitQualityApprovasList(String id, String processId) {
		// TODO Auto-generated method stub
		return dao.showAllSplitQualityApprovasList(id, processId);
	}
	@Override
	public Integer updateSplitQualityData(String taskId, String processId, String testNumTem) {
		// TODO Auto-generated method stub
		return dao.updateSplitQualityData(taskId, processId, testNumTem);
	}
	@Override
	public Map<String, Object> showAllMainBranchInfo(String taskId, String processId) {
		// TODO Auto-generated method stub
		return dao.showAllMainBranchInfo(taskId, processId);
	}
	@Override
	public Integer insertAllChildBranchInfo(String taskId, String processId, String processOrder, String planNum,String testNum,String testNums) {
		// TODO Auto-generated method stub
		return dao.insertAllChildBranchInfo(taskId, processId, processOrder, planNum,testNum,testNums);
	}
	@Override
	public Integer updateSplitMainBrachInfo(String id) {
		// TODO Auto-generated method stub
		return dao.updateSplitMainBrachInfo(id);
	}
	@Override
	public Integer updateOutSidePlanNum(String taskId, String processId, String testNum,String splitFlags,String id) {
		// TODO Auto-generated method stub
		return dao.updateOutSidePlanNum(taskId, processId, testNum,splitFlags,id);
	}
	@Override
	public Integer updateOutSideChildPlanNum(String taskId, String processId) {
		// TODO Auto-generated method stub
		return dao.updateOutSideChildPlanNum(taskId, processId);
	}
	@Override
	public Integer updateOutSideMainPlanNum(String taskId, String processId, String testNum) {
		// TODO Auto-generated method stub
		return dao.updateOutSideMainPlanNum(taskId, processId, testNum);
	}
	@Override
	public Integer delOutSideChildBrach(String id) {
		// TODO Auto-generated method stub
		return dao.delOutSideChildBrach(id);
	}
	@Override
	public Integer updateSplitOKApprovlaNum(String id) {
		// TODO Auto-generated method stub
		return dao.updateSplitOKApprovlaNum(id);
	}
	@Override
	public Integer countSplitChildNums(String taskId, String processId) {
		// TODO Auto-generated method stub
		return dao.countSplitChildNums(taskId, processId);
	}
	@Override
	public Integer updateSplitStatusData(String taskId, String processId) {
		// TODO Auto-generated method stub
		return dao.updateSplitStatusData(taskId, processId);
	}
	@Override
	public Integer updateTestNumData(String taskId, String processId, String testNum,String okNum,String ngNum) {
		// TODO Auto-generated method stub
		return dao.updateTestNumData(taskId, processId, testNum,okNum,ngNum);
	}
	@Override
	public Integer updateNextChildProcessInfo(Integer taskId, Integer orderNum) {
		// TODO Auto-generated method stub
		return dao.updateNextChildProcessInfo(taskId, orderNum);
	}
	@Override
	public Integer updateOutChildSourceInfox(String testNum, String testingNum, String taskId, String processId,
			String flags, String person, Integer outNumx, String id) {
		// TODO Auto-generated method stub
		return dao.updateOutChildSourceInfox(testNum, testingNum, taskId, processId, flags, person, outNumx, id);
	}
	@Override
	public Map<String,Object> showTestNumData(String id) {
		// TODO Auto-generated method stub
		return dao.showTestNumData(id);
	}
	@Override
	public Integer updateMainTestNumData(String testNum, String id) {
		// TODO Auto-generated method stub
		return dao.updateMainTestNumData(testNum, id);
	}
	@Override
	public List<Map<String, Object>> showAllTaskByRouteIdInfo(String routeId) {
		// TODO Auto-generated method stub
		return dao.showAllTaskByRouteIdInfo(routeId);
	}
	@Override
	public List<Map<String, Object>> showAllDetailsByIdInfo(String taskId, String processId) {
		// TODO Auto-generated method stub
		return dao.showAllDetailsByIdInfo(taskId, processId);
	}
	@Override
	public Integer insertOutsourceListDatas(String taskId, String processId,String listId) {
		// TODO Auto-generated method stub
		return dao.insertOutsourceListDatas(taskId, processId,listId);
	}
	@Override
	public Integer showDetailsIdInfoById(String taskId, String processId) {
		// TODO Auto-generated method stub
		return dao.showDetailsIdInfoById(taskId, processId);
	}
	@Override
	public Integer deloutSourceInfoById(String taskId, String processId, String id) {
		// TODO Auto-generated method stub
		return dao.deloutSourceInfoById(taskId, processId, id);
	}
	@Override
	public List<Map<String, Object>> showAllOutsourceData(String taskId, String processId) {
		// TODO Auto-generated method stub
		return dao.showAllOutsourceData(taskId, processId);
	}
	@Override
	public Integer updateOutsideNumTask(String okNum, String supplier, String taskId, String processId, String id) {
		// TODO Auto-generated method stub
		return dao.updateOutsideNumTask(okNum, supplier, taskId, processId, id);
	}
	@Override
	public Integer insertOutsourceListDatasx(String taskId, String processId, String listId) {
		// TODO Auto-generated method stub
		return dao.insertOutsourceListDatasx(taskId, processId, listId);
	}
	@Override
	public Integer updateExamineOutsideNumTask(String okNum, String taskId, String processId, String id) {
		// TODO Auto-generated method stub
		return dao.updateExamineOutsideNumTask(okNum, taskId, processId, id);
	}
	@Override
	public Integer updateMainOutsideNumTask(String outsideNum, String taskId, String processId, String id) {
		// TODO Auto-generated method stub
		return dao.updateMainOutsideNumTask(outsideNum, taskId, processId, id);
	}
	@Override
	public Integer updateQualityAllNumTask(String okNum, String ngNum, String taskId, String processId, String id,String testNum) {
		// TODO Auto-generated method stub
		return dao.updateQualityAllNumTask(okNum, ngNum, taskId, processId, id,testNum);
	}
	@Override
	public Integer updateQualityPartNumTask(String okNum, String ngNum, String taskId, String processId, String id,String testNum) {
		// TODO Auto-generated method stub
		return dao.updateQualityPartNumTask(okNum, ngNum, taskId, processId, id,testNum);
	}
	@Override
	public Map<String, Object> showOutsideTaskById(String taskId, String processId, String id) {
		// TODO Auto-generated method stub
		return dao.showOutsideTaskById(taskId, processId, id);
	}
	@Override
	public Integer revockOutsideQuqalityNumTask(String testNum, String taskId, String processId, String id) {
		// TODO Auto-generated method stub
		return dao.revockOutsideQuqalityNumTask(testNum, taskId, processId, id);
	}
	@Override
	public Integer updateOutsideStatusNum(String taskId, String processId, String id, String status) {
		// TODO Auto-generated method stub
		return dao.updateOutsideStatusNum(taskId, processId, id, status);
	}
	@Override
	public Integer updateOutsideExamineNum(String okNum, String ngNum, String testNum, String taskId, String processId,
			String id,String okNumExmine,String ngNumExmine) {
		// TODO Auto-generated method stub
		return dao.updateOutsideExamineNum(okNum, ngNum, testNum, taskId, processId, id,okNumExmine,ngNumExmine);
	}
	@Override
	public Integer updateOutsideInsideApplyNum(String insideApply, String taskId, String processId, String id) {
		// TODO Auto-generated method stub
		return dao.updateOutsideInsideApplyNum(insideApply, taskId, processId, id);
	}
	@Override
	public Integer updateOutsideRevockApplyNum(String taskId, String processId, String id) {
		// TODO Auto-generated method stub
		return dao.updateOutsideRevockApplyNum(taskId, processId, id);
	}
//	@Override
//	public Integer updateOutsideExamineNum(String applyNum, String examineNum, String taskId, String processId,
//			String id) {
//		// TODO Auto-generated method stub
//		return mapper.updateOutsideExamineNum(applyNum, examineNum, taskId, processId, id);
//	}
//	@Override
//	public Integer updateOutsideExamineNum(String applyNum, String examineNum, String taskId, String processId,
//			String id) {
//		// TODO Auto-generated method stub
//		return mapper.updateOutsideExamineNum(applyNum, examineNum, taskId, processId, id);
//	}
	@Override
	public Integer updateOutsideExamineNums(String okNum,String applyNum, String examineNum, String taskId, String processId,
			String id) {
		// TODO Auto-generated method stub
		return dao.updateOutsideExamineNums(okNum,applyNum, examineNum, taskId, processId, id);
	}
	@Override
	public Integer updateMainComplete(String complete, String taskId, String processId) {
		// TODO Auto-generated method stub
		return dao.updateMainComplete(complete, taskId, processId);
	}
	@Override
	public Integer sumMainPlanNum(String taskId, String processId) {
		// TODO Auto-generated method stub
		return dao.sumMainPlanNum(taskId, processId);
	}
	@Override
	public Integer updateOutSideMainPlanNumxx(String taskId, String processId, String testNum) {
		// TODO Auto-generated method stub
		return dao.updateOutSideMainPlanNumxx(taskId, processId, testNum);
	}
	@Override
	public Integer updateDetailsPushDownNum(String pushDownNum, String taskId, String orderNum) {
		// TODO Auto-generated method stub
		return dao.updateDetailsPushDownNum(pushDownNum, taskId, orderNum);
	}
	@Override
	public Integer updateFinishDataxs(String finish, String taskId, String order) {
		// TODO Auto-generated method stub
		return dao.updateFinishDataxs(finish, taskId, order);
	}
	@Override
	public Integer updateUseNumxx(String useNum, String taskId, String order) {
		// TODO Auto-generated method stub
		return dao.updateUseNumxx(useNum, taskId, order);
	}



}
