package com.skeqi.mes.mapper.processFlows;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

@MapperScan
@Component("ProcessReworkDao")
public interface ProcessReworkDao {
//查询需要返修的数据
//	4.0
//	@Select("<script>select d.MATERIAL_CODE,d.STATION_NUM,c.ID as IDS,d.MATERIAL_NAME,d.MATERIAL_QUALITY,d.NUM_REMARKS,d.IMAGES,c.USE_REWORK_NUM_PERSON,c.USE_REWORK_NUM,c.USE_REWORK_NUM_TEM,c.USE_REWORK_NUM_TEM_APPROVAL,c.OK_INSOURCE_TEMS,c.OK_OUTSOURCE_TEMS,c.OK_INSOURCE_TEM,c.ALL_QUALITY_PERSON,c.ALL_REMARKS,c.NG_NUM_TEM_APPROVAL,c.OK_NUM_TEM_APPROVAL,c.TESTING_NUM_TEM,c.OK_OUTSOURCE_TEM,d.ID,d.PROJECT_NUM,d.SPECIFICATION_AND_MODEL,c.STATION_NAME,c.PLAN_NUM,c.COMPLETE_NUM,c.USEABLE_NUM,c.OUTSOURCE_OUT_NUM,c.OUTSOURCE_IN_NUM,c.NG_NUM,c.REWORK_NUM,c.TESTING_NUM,c.PROCESS_ID,c.STATUS_FLAGS,c.NG_NUM_TEM,d.STATUS_FLAGS as TASK_STATUS,c.REMARKS,c.QUALITY_PERSON,c.STATION_TYPES from (select a.ID,a.USE_REWORK_NUM_PERSON,a.USE_REWORK_NUM_TEM,a.USE_REWORK_NUM_TEM_APPROVAL,a.USE_REWORK_NUM,a.OK_INSOURCE_TEMS,a.OK_OUTSOURCE_TEMS,a.OK_INSOURCE_TEM,a.ALL_QUALITY_PERSON,a.ALL_REMARKS,a.NG_NUM_TEM_APPROVAL,a.OK_NUM_TEM_APPROVAL,a.PROCESS_ID,a.TASK_ID,b.STATION_NAME,a.PLAN_NUM,a.COMPLETE_NUM,a.USEABLE_NUM,a.OUTSOURCE_OUT_NUM,a.OUTSOURCE_IN_NUM,a.NG_NUM,a.REWORK_NUM,a.TESTING_NUM,a.STATUS_FLAGS,a.NG_NUM_TEM,a.REMARKS,a.QUALITY_PERSON,b.STATION_TYPES,a.OK_OUTSOURCE_TEM,a.TESTING_NUM_TEM from c_process_details_t a "
//			+ "left join c_mes_process_station_t b on a.PROCESS_ID = b.ID ) c left join c_process_production_task d on c.TASK_ID=d.ID WHERE c.USE_REWORK_NUM>0"
//			+ "<if test=\"stationNum!=''\"> and d.STATION_NUM like '%${stationNum}%'</if>"
//			+ "<if test=\"projectNum!=''\"> and d.PROJECT_NUM like '%${projectNum}%'</if>"
//			+ "<if test=\"specificationModel!=''\"> and d.SPECIFICATION_AND_MODEL like '%${specificationModel}%'</if>"
//			+ "<if test=\"materiCode!=''\"> and d.MATERIAL_CODE like '%${materiCode}%'</if></script>")
	@Select("<script>select * from (select c.IDX,d.ROUTE_NAME,d.MATERIAL_CODE,d.STATION_NUM,c.ID as IDS,d.MATERIAL_NAME,d.MATERIAL_QUALITY,d.NUM_REMARKS,d.IMAGES,c.USE_REWORK_NUM_PERSON,c.USE_REWORK_NUM,c.USE_REWORK_NUM_TEM,c.USE_REWORK_NUM_TEM_APPROVAL,c.OK_INSOURCE_TEMS,c.OK_OUTSOURCE_TEMS,c.OK_INSOURCE_TEM,c.ALL_QUALITY_PERSON,c.ALL_REMARKS,c.NG_NUM_TEM_APPROVAL,c.OK_NUM_TEM_APPROVAL,c.TESTING_NUM_TEM,c.OK_OUTSOURCE_TEM,d.ID,d.PROJECT_NUM,d.SPECIFICATION_AND_MODEL,c.STATION_NAME,c.PLAN_NUM,c.COMPLETE_NUM,c.USEABLE_NUM,c.OUTSOURCE_OUT_NUM,c.OUTSOURCE_IN_NUM,c.NG_NUM,c.REWORK_NUM,c.TESTING_NUM,c.PROCESS_ID,c.STATUS_FLAGS,c.NG_NUM_TEM,d.STATUS_FLAGS as TASK_STATUS,c.REMARKS,c.QUALITY_PERSON,c.STATION_TYPES from (select b.ID as IDX,a.ID,a.USE_REWORK_NUM_PERSON,a.USE_REWORK_NUM_TEM,a.USE_REWORK_NUM_TEM_APPROVAL,a.USE_REWORK_NUM,a.OK_INSOURCE_TEMS,a.OK_OUTSOURCE_TEMS,a.OK_INSOURCE_TEM,a.ALL_QUALITY_PERSON,a.ALL_REMARKS,a.NG_NUM_TEM_APPROVAL,a.OK_NUM_TEM_APPROVAL,a.PROCESS_ID,a.TASK_ID,b.STATION_NAME,a.PLAN_NUM,a.COMPLETE_NUM,a.USEABLE_NUM,a.OUTSOURCE_OUT_NUM,a.OUTSOURCE_IN_NUM,a.NG_NUM,a.REWORK_NUM,a.TESTING_NUM,a.STATUS_FLAGS,a.NG_NUM_TEM,a.REMARKS,a.QUALITY_PERSON,b.STATION_TYPES,a.OK_OUTSOURCE_TEM,a.TESTING_NUM_TEM from c_process_details_t a left join c_mes_process_station_t b on a.PROCESS_ID = b.ID ) c left join c_process_production_task d on c.TASK_ID=d.ID WHERE c.USE_REWORK_NUM>0)x left join c_mes_process_production_way_t y on x.IDX=y.ST_ID and x.ROUTE_NAME=y.ROUTING_ID where 1=1"
	+ "<if test=\"stationNum!=''\"> and x.STATION_NUM like '%${stationNum}%'</if>"
	+ "<if test=\"projectNum!=''\"> and x.PROJECT_NUM like '%${projectNum}%'</if>"
	+ "<if test=\"specificationModel!=''\"> and x.SPECIFICATION_AND_MODEL like '%${specificationModel}%'</if>"
	+ "<if test=\"materiCode!=''\"> and x.MATERIAL_CODE like '%${materiCode}%'</if></script>")
	public List<Map<String,Object>> showAllReworkInfo(@Param("stationNum")String stationNum,@Param("materiCode")String materiCode,@Param("projectNum")String projectNum,@Param("specificationModel")String specificationModel);

	//查询需要返修的数据
//	4.0
//		@Select("<script>select d.STATION_NUM,d.MATERIAL_CODE,c.ID as IDS,d.MATERIAL_NAME,d.MATERIAL_QUALITY,d.NUM_REMARKS,d.IMAGES,c.USE_REWORK_NUM_PERSON,c.USE_REWORK_NUM,c.USE_REWORK_NUM_TEM,c.USE_REWORK_NUM_TEM_APPROVAL,c.OK_INSOURCE_TEMS,c.OK_OUTSOURCE_TEMS,c.OK_INSOURCE_TEM,c.ALL_QUALITY_PERSON,c.ALL_REMARKS,c.NG_NUM_TEM_APPROVAL,c.OK_NUM_TEM_APPROVAL,c.TESTING_NUM_TEM,c.OK_OUTSOURCE_TEM,d.ID,d.PROJECT_NUM,d.SPECIFICATION_AND_MODEL,c.STATION_NAME,c.PLAN_NUM,c.COMPLETE_NUM,c.USEABLE_NUM,c.OUTSOURCE_OUT_NUM,c.OUTSOURCE_IN_NUM,c.NG_NUM,c.REWORK_NUM,c.TESTING_NUM,c.PROCESS_ID,c.STATUS_FLAGS,c.NG_NUM_TEM,d.STATUS_FLAGS as TASK_STATUS,c.REMARKS,c.QUALITY_PERSON,c.STATION_TYPES from (select a.ID,a.USE_REWORK_NUM_PERSON,a.USE_REWORK_NUM_TEM,a.USE_REWORK_NUM_TEM_APPROVAL,a.USE_REWORK_NUM,a.OK_INSOURCE_TEMS,a.OK_OUTSOURCE_TEMS,a.OK_INSOURCE_TEM,a.ALL_QUALITY_PERSON,a.ALL_REMARKS,a.NG_NUM_TEM_APPROVAL,a.OK_NUM_TEM_APPROVAL,a.PROCESS_ID,a.TASK_ID,b.STATION_NAME,a.PLAN_NUM,a.COMPLETE_NUM,a.USEABLE_NUM,a.OUTSOURCE_OUT_NUM,a.OUTSOURCE_IN_NUM,a.NG_NUM,a.REWORK_NUM,a.TESTING_NUM,a.STATUS_FLAGS,a.NG_NUM_TEM,a.REMARKS,a.QUALITY_PERSON,b.STATION_TYPES,a.OK_OUTSOURCE_TEM,a.TESTING_NUM_TEM from c_process_details_t a "
//				+ "left join c_mes_process_station_t b on a.PROCESS_ID = b.ID ) c left join c_process_production_task d on c.TASK_ID=d.ID WHERE c.USE_REWORK_NUM_TEM_APPROVAL>0"
//				+ "<if test=\"stationNum!=''\"> and d.STATION_NUM like '%${stationNum}%'</if>"
//				+ "<if test=\"projectNum!=''\"> and d.PROJECT_NUM like '%${projectNum}%'</if>"
//				+ "<if test=\"specificationModel!=''\"> and d.SPECIFICATION_AND_MODEL like '%${specificationModel}%'</if>"
//				+ "<if test=\"materiCode!=''\"> and d.MATERIAL_CODE like '%${materiCode}%'</if></script>")
	@Select("<script>select * from (select d.ROUTE_NAME,c.IDX,d.STATION_NUM,d.MATERIAL_CODE,c.ID as IDS,d.MATERIAL_NAME,d.MATERIAL_QUALITY,d.NUM_REMARKS,d.IMAGES,c.USE_REWORK_NUM_PERSON,c.USE_REWORK_NUM,c.USE_REWORK_NUM_TEM,c.USE_REWORK_NUM_TEM_APPROVAL,c.OK_INSOURCE_TEMS,c.OK_OUTSOURCE_TEMS,c.OK_INSOURCE_TEM,c.ALL_QUALITY_PERSON,c.ALL_REMARKS,c.NG_NUM_TEM_APPROVAL,c.OK_NUM_TEM_APPROVAL,c.TESTING_NUM_TEM,c.OK_OUTSOURCE_TEM,d.ID,d.PROJECT_NUM,d.SPECIFICATION_AND_MODEL,c.STATION_NAME,c.PLAN_NUM,c.COMPLETE_NUM,c.USEABLE_NUM,c.OUTSOURCE_OUT_NUM,c.OUTSOURCE_IN_NUM,c.NG_NUM,c.REWORK_NUM,c.TESTING_NUM,c.PROCESS_ID,c.STATUS_FLAGS,c.NG_NUM_TEM,d.STATUS_FLAGS as TASK_STATUS,c.REMARKS,c.QUALITY_PERSON,c.STATION_TYPES from (select b.ID as IDX,a.ID,a.USE_REWORK_NUM_PERSON,a.USE_REWORK_NUM_TEM,a.USE_REWORK_NUM_TEM_APPROVAL,a.USE_REWORK_NUM,a.OK_INSOURCE_TEMS,a.OK_OUTSOURCE_TEMS,a.OK_INSOURCE_TEM,a.ALL_QUALITY_PERSON,a.ALL_REMARKS,a.NG_NUM_TEM_APPROVAL,a.OK_NUM_TEM_APPROVAL,a.PROCESS_ID,a.TASK_ID,b.STATION_NAME,a.PLAN_NUM,a.COMPLETE_NUM,a.USEABLE_NUM,a.OUTSOURCE_OUT_NUM,a.OUTSOURCE_IN_NUM,a.NG_NUM,a.REWORK_NUM,a.TESTING_NUM,a.STATUS_FLAGS,a.NG_NUM_TEM,a.REMARKS,a.QUALITY_PERSON,b.STATION_TYPES,a.OK_OUTSOURCE_TEM,a.TESTING_NUM_TEM from c_process_details_t a left join c_mes_process_station_t b on a.PROCESS_ID = b.ID ) c left join c_process_production_task d on c.TASK_ID=d.ID WHERE c.USE_REWORK_NUM_TEM_APPROVAL>0)x left join c_mes_process_production_way_t y on x.IDX=y.ST_ID and x.ROUTE_NAME=y.ROUTING_ID where 1=1"
			+ "<if test=\"stationNum!=''\"> and d.STATION_NUM like '%${stationNum}%'</if>"
			+ "<if test=\"projectNum!=''\"> and d.PROJECT_NUM like '%${projectNum}%'</if>"
			+ "<if test=\"specificationModel!=''\"> and d.SPECIFICATION_AND_MODEL like '%${specificationModel}%'</if>"
			+ "<if test=\"materiCode!=''\"> and d.MATERIAL_CODE like '%${materiCode}%'</if></script>")
		public List<Map<String,Object>> showAllReworkInfox(@Param("stationNum")String stationNum,@Param("materiCode")String materiCode,@Param("projectNum")String projectNum,@Param("specificationModel")String specificationModel);
//获取当前返修类型信息
	@Select("select * from c_process_details_t where TASK_ID=#{taskId} and PROCESS_ID=#{processId} and ID=#{id}")
	public Map<String,Object> showProcessDetailsInfo(@Param("taskId")String taskId,@Param("processId")String processId,@Param("id")String id);

//更新返修审批数据
	@Update("update c_process_details_t set USE_REWORK_NUM_PERSON=#{reworkPerson},USE_REWORK_NUM_TEM_APPROVAL=#{reworkNum},USE_REWORK_NUM=#{useReworkNums},USE_REWORK_NUM_TEM=#{useReworkNums} where TASK_ID=#{taskId} and PROCESS_ID=#{processId} and ID=#{id}")
	public Integer updateReworkInfo(@Param("reworkNum")String reworkNum,@Param("useReworkNums")String useReworkNums,@Param("taskId")String taskId,@Param("processId")String processId,@Param("reworkPerson")String reworkPerson,@Param("id")String id);

//	撤销返修申请数据
	@Update("update c_process_details_t set USE_REWORK_NUM_PERSON='',USE_REWORK_NUM_TEM_APPROVAL='0',USE_REWORK_NUM=#{useReworkNums},USE_REWORK_NUM_TEM=#{useReworkNums} where TASK_ID=#{taskId} and PROCESS_ID=#{processId} and ID=#{id}")
	public Integer revockReworkInfo(@Param("useReworkNums")String useReworkNums,@Param("taskId")String taskId,@Param("processId")String processId,@Param("id")String id);

//	查询任务单
	@Select("select * from c_process_production_task where ID=#{taskId}")
	public Map<String,Object> showTaskById(@Param("taskId")String taskId);

//	更新返修数据
	@Update("update c_process_details_t set OK_OUTSOURCE_TEM=#{outOutsourceTem},FINISH_PRODUCT_TEM=#{okFinishProductionTems},OK_FINISH_PRODUCTION_TEMS =#{okFinishProductionTems},INSOURCE_IN_TEMS=#{outOutsourceTem},USE_REWORK_NUM_PERSON='',USE_REWORK_NUM_PERSON_APPROVAL=#{person},USE_REWORK_NUM_TEM_APPROVAL='0',USEABLE_NUM=#{OKNum},NG_NUM=#{NGNum},REWORK_NUM=#{reworkNum} where TASK_ID=#{taskId} and PROCESS_ID=#{processId} and ID=#{id}")//OK_OUTSOURCE_TEM=#{outOutsourceTem},
	public Integer updateReworkApprovalDatas(@Param("OKNum")String OKNum,@Param("NGNum")String NGNum,@Param("reworkNum")String reworkNum,@Param("taskId")String taskId,@Param("processId")String processId,@Param("person")String person,@Param("outOutsourceTem")String outOutsourceTem,@Param("okFinishProductionTems")String okFinishProductionTems,@Param("id")String id);

	@Update("update c_process_details_t set FINISH_PRODUCT_TEM=#{okFinishProductionTems},OK_FINISH_PRODUCTION_TEMS =#{okFinishProductionTems},INSOURCE_IN_TEMS=#{outOutsourceTem},USE_REWORK_NUM_PERSON='',USE_REWORK_NUM_PERSON_APPROVAL=#{person},USE_REWORK_NUM_TEM_APPROVAL='0',USEABLE_NUM=#{OKNum},NG_NUM=#{NGNum},REWORK_NUM=#{reworkNum} where TASK_ID=#{taskId} and PROCESS_ID=#{processId}")//OK_OUTSOURCE_TEM=#{outOutsourceTem},
	public Integer updateReworkApprovalDatasx(@Param("OKNum")String OKNum,@Param("NGNum")String NGNum,@Param("reworkNum")String reworkNum,@Param("taskId")String taskId,@Param("processId")String processId,@Param("person")String person,@Param("outOutsourceTem")String outOutsourceTem,@Param("okFinishProductionTems")String okFinishProductionTems);


//	更新NG数量
	@Update("update c_process_production_task set NG_NUM=#{ngNum} where ID=#{taskId}")
	public Integer updateTaskNGNum(@Param("ngNum")String ngNum,@Param("taskId")String taskId);

//	委外数据更新
	@Update("update c_process_outsource_list set REWORK_APPLY_NUM=#{applyNum} where TASK_ID=#{taskId} and PROCESS_ID=#{processId} and SPLIT_ID=#{id}")
	public Integer updateApplyReworkInsideNum(@Param("applyNum")String applyNum,@Param("taskId")String taskId,@Param("processId")String processId,@Param("id")String id);

//	@Update("update c_process_outsource_list set IN_SIDE_NUM_APPLY=0 where TASK_ID=#{taskId} and PROCESS_ID=#{processId} and SPLIT_ID=#{id}")
//	public  Integer updateOutsideRevockApplyNum(@Param("taskId")String taskId,@Param("processId")String processId,@Param("id")String id);

	@Update("update c_process_outsource_list set REWORK_APPLY_NUM=0 where TASK_ID=#{taskId} and PROCESS_ID=#{processId} and SPLIT_ID=#{id}")
	public Integer updateRevockReworkInsideNum(@Param("taskId")String taskId,@Param("processId")String processId,@Param("id")String id);

	@Update("update c_process_outsource_list set NG_NUM=#{ngNum},OK_NUM=#{okNum},REWORK_NUM=#{reworkNum},REWORK_EXAMINE_NUM=#{reworkNum},REWORK_APPLY_NUM=#{applyNum} where TASK_ID=#{taskId} and PROCESS_ID=#{processId} and SPLIT_ID=#{id}")
	public Integer updateReworkExamineInsideNum(@Param("ngNum")String ngNum,@Param("reworkNum")String reworkNum,@Param("applyNum")String applyNum,@Param("taskId")String taskId,@Param("processId")String processId,@Param("id")String id,@Param("okNum")String okNum);


	@Update("update c_process_details_t set REWORK_NUM=#{reworkNum},NG_NUM=#{ngNum},USEABLE_NUM=#{useNum} where TASK_ID=#{taskId} and PROCESS_ID=#{processId} and (SPLIT_FLAGS=1 or SPLIT_FLAGS=2)")
	public Integer updateReworkNumxx(@Param("reworkNum")String reworkNum,@Param("taskId")String taskId,@Param("processId")String processId,@Param("ngNum")String ngNum,@Param("useNum")String useNum);

}
