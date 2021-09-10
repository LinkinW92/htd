package com.skeqi.mes.service.processFlows;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ProcessClientService {

	public List<Map<String,Object>> showAllTaskByRouteIdInfo(String routeId);

	public List<Map<String,Object>> showAllDetailsByIdInfo(String taskId,String processId);
//	查看生产任务对应的工序
	public List<Map<String,Object>> showAllClientRoute(String projectNum,String specificationModels);

	public Map<String,Object> showTaskList(String peojectNum,String specificationModel,String processName,String meterialCode,String stationNum);

	public Integer updateDetailsUseable(Integer useAble,Integer taskId,Integer id,Integer testingNum,Integer ngNum,Integer outNum);

	public Integer showprocessType(Integer id);

	public Integer updateReworksDetailsUseable(Integer useAble,Integer taskId,Integer id,Integer reworkNum,Integer ngNum);

	public Integer updateProcessTaskInfo(Integer ngNum,Integer reworkNum,Integer id);

	public Map<String,Object> showProcessTaskInfos(Integer id);

	public Integer updateDetailsTaskInfos(Integer completeNum,Integer useAble,Integer id,Integer taskId);

	public Integer maxOrder(Integer taskId);

	public Map<String,Object> showNextProcessInfo(Integer taskId,Integer orderNum);

	public Integer updateNextProcessInfo(Integer testingNum,Integer taskId,Integer orderNum,Integer nextOutNum);

	public Integer updateNextProcessInfox(Integer testingNum,Integer taskId,Integer orderNum,Integer nextOutNum,String pushDownNum);

	public List<Map<String,Object>> showAllProcessTypeTaskInfos(Integer typeId,String projectNum,String specificationModel,String statusFlags,String flagx,String stationNum,String materiCode);

	public List<Map<String,Object>> showAllProcessTypeTaskInfosx(Integer typeId,String projectNum,String specificationModel,String statusFlags,String flagx,String stationNum,String materiCode);

	public List<Map<String,Object>> showAllRouteNameList(Integer taskId,Integer typeId);

	public Integer updateProcessTaskCompleteInfo(Integer complete,Integer statusFlags,Integer taskId);

	public Integer showNowProcesstypeInfo(String projectNum,String specificationModel,Integer processId);

	public Integer updateDetailsTaskInInfos(Integer completeNum,Integer useAble,Integer id,Integer taskId,Integer outsourceInNum);

//	public List<Map<String,Object>> showAllProcessTypeTaskOutInfos(Integer typeId,String projectNum,String specificationModel,String statusFlags);
	public List<Map<String,Object>> showAllProcessTypeTaskOutInfos(String typeId,String flags,String stationNum,String projectNum,String specificationModel,String materiCode);

	public List<Map<String,Object>> showAllProcessTypeTaskOutInfosxs(String typeId,String flags,String stationNum,String materiCode,String projectNum,String specificationModel);

//	public List<Map<String,Object>> showAllProcessTypeTaskOutInfos(Integer typeId,String projectNum,String specificationModel,String statusFlags);
	public List<Map<String,Object>> showAllProcessTypeTaskOutInfosx(String typeId,String flags,String stationNum,String materiCode,String projectNum,String specificationModel);


	public Integer updateOutNums(Integer useAble,Integer id,Integer taskId);

	public String showProcessName(Integer id);

	public Map<String,Object> showTaskStatusInfos(Integer id);

	public List<Map<String,Object>> showQualityInfoList(String statusFlags,String stationNum,String projectNum,String specificationModel,String materiCode,String processName);


	public Integer updateQualityApprovalList(String taskId,String peocessId,String NGNum,String flags,String remarks,String qualityPerson,String outsourceTem,String testNum,String testNumTem,String flagsx,String reworksNum,String id);

	public Integer updateQualityApprovalData(String testingNum,String OKNum,String NGNum,String taskId,String peocessId,String person,String remarks,String allRemarks,String outsource);

	public Map<String,Object> showAllQualityApprovasList(String statusFlags,String id,String processId,String ids);

	public Map<String,Object> showAllQualityApprovasListx(String statusFlags,String id,String processId);

	public Integer showNGNum(String id);

	public Integer updateTaskNGNum(String id,Integer NGNum);

	public Integer updateNGNumTem(String taskId, String peocessId);

	public Integer showOrder(String taskId,String processId);

	public Integer revokeApproval(String taskId,String processId,Integer outRouce,Integer testNum);

	public Integer finishProductApproval(String finishProduct,String taskId,String processId,String person,String remarks,Integer okOutsourceTem,Integer okFinishProductionTems);

	public List<Map<String,Object>> showAllFinishProduct(String flags,String stationNum,String materiCode,String projectNum,String specificationModel);

	public List<Map<String,Object>> showAllFinishProductx(String flags,String stationNum,String materiCode,String projectNum,String specificationModel);

	public Map<String,Object> showDetailsInfos(String taskId,String processId,String id);

	public Integer updateDetailsInfo(String complete,String useNum,String taskId,String processId,String flags);

	public Integer updateNewTaskInfos(String complete,String flags,String id);

	public Integer revokeFinishProductNum(String useName,String taskId,String processId);

	public Integer updateOutSourceInfo(String testNum,String supplier,String taskId,String processId,String person,String flags,Integer testNums,String ids);

	public Integer updateOutSourceInfox(String testNum,String testingNum,String taskId,String processId,String flags,String person,Integer outNumx,String id);

	public Integer revokeOutSource(String testNum,String taskId,String processId,String flags,String id);

	public Integer updateInSourceInfo(String inSupplier,String okInsourceTem,String flags,String taskId,String processId,String person,Integer okOutsourceTems,Integer newOutSourceInNum,String id);

	public Integer updateRevokeSourceInfo(String useNum,String taskId,String peocessId,String id);

	public Integer updateInSourceprocessInfo(String flags,String inPersonApproval,String OkNum,String useNum,String taskId,String processId,String id);

	public Integer updateQualityApprovalLists(String flags,String testNumTem,String okNumTemApproval,String ngNumTemApproval,String taskId,String processId,String remarks,String person,String ids);

	public Integer updateQualityByFlags(String testNumTem,String taskId,String processId,String ids);

	public Integer updateQualityByFlagsx(String testNumTem,String taskId,String processId,String ids);

	public Integer updateQualityApprovalDatas(String testing,String ngNum,String userNum,String taskId,String processId,String outsourceNum,String newUseNum,String id);

	public Integer updateQualityApprovalDatasx(String testing,String ngNum,String userNum,String taskId,String processId,String outsourceNum,String newUsweNum,String id);

	public Integer updateQualityInSourceApprovalLists(String flags,String testNumTem,String okNumTemApproval,String ngNumTemApproval,String taskId,String processId,String remarks,String person,String ids);


//	更新前数据
	public Integer showProcessById(String taskId,String processId,String id);

	public Map<String,Object> showTaskById(String taskId);

	public String showStationName(String processId);

//	展示拆分的所有数据
	public  List<Map<String,Object>> showAllSplitQualityApprovasList(String id,String processId);

//	根性拆分数据
	public Integer updateSplitQualityData(String taskId,String processId,String testNumTem);

//	主分支信息
	public Map<String,Object> showAllMainBranchInfo(String taskId,String processId);
//	新增子分支
	public Integer insertAllChildBranchInfo(String taskId,String processId,String processOrder,String planNum,String testNum,String testNums);
//	更新主分支状态
	public Integer updateSplitMainBrachInfo(String id);

//	更新委外发出数据(主分支)
	public Integer updateOutSidePlanNum(String taskId,String processId,String testNum,String splitFlags,String id);

//	更新委外发出数据(子分支)
	public Integer updateOutSideChildPlanNum(String taskId,String processId);

	public Integer updateOutSideMainPlanNum(String taskId,String processId,String testNum);

    public Integer delOutSideChildBrach(String id);

    public Integer updateSplitOKApprovlaNum(String id);

    public Integer countSplitChildNums(String taskId,String processId);

    public Integer updateSplitStatusData(String taskId,String processId);

    public Integer updateTestNumData(String taskId,String processId,String testNum,String okNum,String ngNum);

    public Integer updateNextChildProcessInfo(Integer taskId,Integer orderNum);

    public Integer updateOutChildSourceInfox(String testNum,String testingNum,String taskId,String processId,String flags,String person,Integer outNumx,String id);

    public Map<String,Object> showTestNumData(String id);

    public Integer updateMainTestNumData(String testNum,String id);

    public Integer insertOutsourceListDatas(String taskId,String processId,String listId);

    public Integer insertOutsourceListDatasx(String taskId,String processId,String listId);

    public Integer showDetailsIdInfoById(String taskId,String processId);

    public Integer deloutSourceInfoById(String taskId,String processId,String id);

    public List<Map<String,Object>> showAllOutsourceData(String taskId,String processId);

	public Integer updateOutsideNumTask(String okNum,String supplier,String taskId,String processId,String id);

	public Integer updateExamineOutsideNumTask(String okNum,String taskId,String processId,String id);

	public Integer updateMainOutsideNumTask(String outsideNum,String taskId,String processId,String id);

	public Integer updateQualityAllNumTask(String okNum,String ngNum,String taskId,String processId,String id,String testNum);

	public Integer updateQualityPartNumTask(String okNum,String ngNum,String taskId,String processId,String id,String testNum);

	public Map<String,Object> showOutsideTaskById(String taskId,String processId,String id);

	public Integer revockOutsideQuqalityNumTask(String testNum,String taskId,String processId,String id);

	public Integer updateOutsideStatusNum(String taskId,String processId,String id,String status);

	public Integer updateOutsideExamineNum(String okNum,String ngNum,String testNum,String taskId,String processId,String id,String okNumExmine,String ngNumExmine);

	public Integer updateOutsideInsideApplyNum(String insideApply,String taskId,String processId,String id);

	public Integer updateOutsideRevockApplyNum(String taskId,String processId,String id);

//	public Integer updateOutsideExamineNum( String applyNum, String examineNum,String taskId,String processId,String id);
	public  Integer updateOutsideExamineNums(String okNum,String applyNum,String examineNum,String taskId,String processId,String id);

	public Integer updateMainComplete(String complete,String taskId,String processId);

	public Integer sumMainPlanNum(String taskId,String processId);

	public Integer updateOutSideMainPlanNumxx(String taskId,String processId,String testNum);

	public Integer updateDetailsPushDownNum(String pushDownNum,String taskId,String orderNum);

	Integer updateFinishDataxs(String finish,String taskId,String order);

	Integer updateUseNumxx(String useNum,String taskId,String order);


}
