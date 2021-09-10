package com.skeqi.mes.mapper.processFlows;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

@MapperScan
@Component("ProcessClientDao")
public interface ProcessClientDao {

//	更新可用数量
	@Update("update c_process_details_t set USEABLE_NUM=#{useNum} where  TASK_ID=#{taskId} and PROCESS_ORDER=#{order}")
	Integer updateUseNumxx(@Param("useNum")String useNum,@Param("taskId")String taskId,@Param("order")String order);


//	更新成品入库数据
	@Update("update c_process_details_t set OK_FINISH_PRODUCTION_TEMS=#{finish},FINISH_PRODUCT_TEM =#{finish} where TASK_ID=#{taskId} and PROCESS_ORDER=#{order}")
	Integer updateFinishDataxs(@Param("finish")String finish,@Param("taskId")String taskId,@Param("order")String order);

//	查看生产任务对应的工序
	@Select("select ID,STATION_NAME from (select w.ST_ID,w.SERIAL_NO from c_process_production_task t left join c_mes_process_production_way_t w on t.ROUTE_NAME=w.ROUTING_ID where PROJECT_NUM=#{projectNum} and SPECIFICATION_AND_MODEL=#{specificationModels})as q left join c_mes_process_station_t s on ST_ID=s.ID order by q.SERIAL_NO asc")
	public List<Map<String,Object>> showAllClientRoute(@Param("projectNum")String projectNum,@Param("specificationModels")String specificationModels);

//	查询项目号与规格型号的唯一码
	@Select("select *,c.ID as DETAILS_ID,t.STATUS_FLAGS,c.PROCESS_ORDER as ORDER_NUM,c.USEABLE_NUM as USEABLE_NUMS,t.PROJECT_NAME from (select ID,STATUS_FLAGS,PROJECT_NAME from c_process_production_task where PROJECT_NUM=#{peojectNum} and SPECIFICATION_AND_MODEL=#{specificationModel} and MATERIAL_CODE=#{meterialCode} and STATION_NUM=#{stationNum}) t left join c_process_details_t c on t.ID=c.TASK_ID where PROCESS_ID=#{processName} ")
	public Map<String,Object> showTaskList(@Param("peojectNum")String peojectNum,@Param("specificationModel")String specificationModel,@Param("processName")String processName,@Param("meterialCode")String meterialCode,@Param("stationNum")String stationNum);

//	更新可用数量（品检后为可用数量）普通+委外
	@Update("<script>update c_process_details_t set USEABLE_NUM=#{useAble},TESTING_NUM=#{testingNum},NG_NUM=${ngNum}<if test=\"outNum!=null\">,OUTSOURCE_OUT_NUM=#{outNum}</if> where TASK_ID=#{taskId} and ID=#{id}</script>")
	public Integer updateDetailsUseable(@Param("useAble")Integer useAble,@Param("taskId")Integer taskId,@Param("id")Integer id,@Param("testingNum")Integer testingNum,@Param("ngNum")Integer ngNum,@Param("outNum")Integer outNum);

//查询工序类型
	@Select("select STATION_TYPES from c_mes_process_station_t where ID=#{id}")
	public Integer showprocessType(@Param("id")Integer id);

//	更新生产任务返修数量
	@Update("update c_process_details_t set USEABLE_NUM=#{useAble},REWORK_NUM=#{reworkNum},NG_NUM=${ngNum} where TASK_ID=#{taskId} and ID=#{id}")
	public Integer updateReworksDetailsUseable(@Param("useAble")Integer useAble,@Param("taskId")Integer taskId,@Param("id")Integer id,@Param("reworkNum")Integer reworkNum,@Param("ngNum")Integer ngNum);

//	更新生产任务信息
	@Update("<script>update c_process_production_task "
			+ "set"
			+ "<if test=\"ngNum!=null\">NG_NUM=#{ngNum}</if> "
			+ "<if test=\"ngNum!=null and reworkNum!=null\">,</if> "
			+ "<if test=\"reworkNum!=null\">REWORK_NUM=#{reworkNum}</if> "
			+ "where ID=#{id}</script>")
	public Integer updateProcessTaskInfo(@Param("ngNum")Integer ngNum,@Param("reworkNum")Integer reworkNum,@Param("id") Integer id);

//	查询指定生产任务的信息
	@Select("select * from c_process_production_task where ID=#{id}")
	public Map<String,Object> showProcessTaskInfos(@Param("id")Integer id);

//	下推完成数据更新
	@Update("update c_process_details_t set COMPLETE_NUM=#{completeNum} ,USEABLE_NUM=#{useAble} where PROCESS_ID=#{id} and TASK_ID=#{taskId}")
	public Integer updateDetailsTaskInfos(@Param("completeNum")Integer completeNum,@Param("useAble")Integer useAble,@Param("id")Integer id,@Param("taskId")Integer taskId);

//	查出当前工艺路线最大ID
	@Select("select max(PROCESS_ORDER) from c_process_details_t where TASK_ID=#{taskId}")
	public Integer maxOrder(@Param("taskId")Integer taskId);

//	查找序号的工序
	@Select("select * from c_process_details_t where TASK_ID=#{taskId} and PROCESS_ORDER=#{orderNum} and (SPLIT_FLAGS=1 or SPLIT_FLAGS=2)")
	public Map<String,Object> showNextProcessInfo(@Param("taskId")Integer taskId,@Param("orderNum")Integer orderNum);

//	更新下道工序待检测数量
	@Update("update c_process_details_t set OK_INSOURCE_TEM=#{nextOutNum},INSOURCE_IN_TEMS_APPROVAL='0',TESTING_NUM_TEM=#{nextOutNum},TESTING_NUM=#{testingNum} where  TASK_ID=#{taskId} and PROCESS_ORDER=#{orderNum} and (SPLIT_FLAGS=1 or SPLIT_FLAGS=2) ")//,FINISH_PRODUCT_TEM=#{testingNum}
	public Integer updateNextProcessInfo(@Param("testingNum")Integer testingNum,@Param("taskId")Integer taskId,@Param("orderNum")Integer orderNum,@Param("nextOutNum")Integer nextOutNum);

//	更新下道工序待检测数量(子分支)
	@Update("update c_process_details_t set OK_INSOURCE_TEM=0 where  TASK_ID=#{taskId} and PROCESS_ORDER=#{orderNum} and SPLIT_FLAGS=3")//,FINISH_PRODUCT_TEM=#{testingNum}
	public Integer updateNextChildProcessInfo(@Param("taskId")Integer taskId,@Param("orderNum")Integer orderNum);

//	更新下道工序待检测数量
	@Update("update c_process_details_t set PUSH_DOWN_NUM=#{pushDownNum},OK_OUTSOURCE_TEM=#{nextOutNum},INSOURCE_IN_TEMS_APPROVAL='0',TESTING_NUM_TEM=#{nextOutNum},TESTING_NUM=#{testingNum} where  TASK_ID=#{taskId} and PROCESS_ORDER=#{orderNum}")//,FINISH_PRODUCT_TEM=#{testingNum} OK_OUTSOURCE_TEM
	public Integer updateNextProcessInfox(@Param("testingNum")Integer testingNum,@Param("taskId")Integer taskId,@Param("orderNum")Integer orderNum,@Param("nextOutNum")Integer nextOutNum,@Param("pushDownNum")String pushDownNum);

//	@Update("update c_process_details_t set  where  TASK_ID=#{taskId} and PROCESS_ORDER=#{orderNum}")
//	public Integer updateFinishLastProcess();

//	根据工序类型查找任务单集合(下料下推只有有可用数量的才显示)
//	@Select("<script>select distinct k.ID,K.PROJECT_NUM,K.SPECIFICATION_AND_MODEL,K.MATERIAL_CODE,K.OPERATION_DEPARTMENT from (select * from c_process_production_task where ID in(select distinct  n.ID from (select w.ST_ID,t.ID from c_process_production_task t left join c_mes_process_production_way_t w on t.ROUTE_NAME=w.ROUTING_ID) n left join c_mes_process_station_t m on n.ST_ID=m.ID where m.STATION_TYPES=#{typeId})) k left join c_process_details_t p on k.ID=P.TASK_ID where PROCESS_ID in(select ID from c_mes_process_station_t where STATION_TYPES=#{typeId}) and USEABLE_NUM>0"
//			+ "<if test=\"projectNum!=null and projectNum!=''\"> and PROJECT_NUM like '%${projectNum}%' </if> <if test=\"specificationModel!=null and specificationModel!=''\"> and SPECIFICATION_AND_MODEL like '%${specificationModel}%' </if> <if test=\"statusFlags!=null and statusFlags!=''\"> and STATUS_FLAGS like '%${statusFlags}%' </if> </script>")
//	4.0
//	@Select("<script> select c.PROCESS_ORDER,d.ID as IDS,c.PUSH_DOWN_NUM,d.MATERIAL_CODE,d.STATION_NUM,d.MATERIAL_NAME,d.MATERIAL_QUALITY,d.NUM_REMARKS,d.IMAGES,c.OK_OUTSOURCE_TEM,c.INSOURCE_IN_TEMS,c.STATUS_FLAGS,d.STATUS_FLAGS as TASK_STATUS,c.IN_SUPPLIER,c.OK_INSOURCE_TEM,c.QUALITY_PERSON,d.ID,d.PROJECT_NUM,d.SPECIFICATION_AND_MODEL,c.STATION_NAME,c.PLAN_NUM,c.COMPLETE_NUM,c.USEABLE_NUM,c.OUTSOURCE_OUT_NUM,c.OUTSOURCE_IN_NUM,c.NG_NUM,c.REWORK_NUM,c.TESTING_NUM,c.PROCESS_ID,c.FINISH_PRODUCT_TEM from (select a.OK_OUTSOURCE_TEM,a.INSOURCE_IN_TEMS,a.TASK_ID,b.STATION_NAME,a.PLAN_NUM,a.COMPLETE_NUM,a.USEABLE_NUM,a.OUTSOURCE_OUT_NUM,a.OUTSOURCE_IN_NUM,a.NG_NUM,a.REWORK_NUM,a.TESTING_NUM,a.PROCESS_ID,a.FINISH_PRODUCT_TEM,a.QUALITY_PERSON,a.OK_INSOURCE_TEM,a.IN_SUPPLIER,a.STATUS_FLAGS,a.PUSH_DOWN_NUM,a.PROCESS_ORDER from c_process_details_t a left join c_mes_process_station_t b on a.PROCESS_ID = b.ID where b.STATION_TYPES=#{typeId}) c left join c_process_production_task d on c.TASK_ID=d.ID WHERE c.PUSH_DOWN_NUM>0  "//c.USEABLE_NUM>0
//			+ "<if test=\"stationNum!=''\"> and d.STATION_NUM like '%${stationNum}%'</if>"
//			+ "<if test=\"projectNum!=''\"> and d.PROJECT_NUM like '%${projectNum}%'</if>"
//			+ "<if test=\"specificationModel!=''\"> and d.SPECIFICATION_AND_MODEL like '%${specificationModel}%'</if>"
//			+ "<if test=\"materiCode!=''\"> and d.MATERIAL_CODE like '%${materiCode}%'</if> </script>")

	@Select("<script> select * from (select  c.ID as IDX,d.ROUTE_NAME,c.PROCESS_ORDER,d.ID as IDS,c.PUSH_DOWN_NUM,d.MATERIAL_CODE,d.STATION_NUM,d.MATERIAL_NAME,d.MATERIAL_QUALITY,d.NUM_REMARKS,d.IMAGES,c.OK_OUTSOURCE_TEM,c.INSOURCE_IN_TEMS,c.STATUS_FLAGS,d.STATUS_FLAGS as TASK_STATUS,c.IN_SUPPLIER,c.OK_INSOURCE_TEM,c.QUALITY_PERSON,d.ID,d.PROJECT_NUM,d.SPECIFICATION_AND_MODEL,c.STATION_NAME,c.PLAN_NUM,c.COMPLETE_NUM,c.USEABLE_NUM,c.OUTSOURCE_OUT_NUM,c.OUTSOURCE_IN_NUM,c.NG_NUM,c.REWORK_NUM,c.TESTING_NUM,c.PROCESS_ID,c.FINISH_PRODUCT_TEM from (select b.ID,a.OK_OUTSOURCE_TEM,a.INSOURCE_IN_TEMS,a.TASK_ID,b.STATION_NAME,a.PLAN_NUM,a.COMPLETE_NUM,a.USEABLE_NUM,a.OUTSOURCE_OUT_NUM,a.OUTSOURCE_IN_NUM,a.NG_NUM,a.REWORK_NUM,a.TESTING_NUM,a.PROCESS_ID,a.FINISH_PRODUCT_TEM,a.QUALITY_PERSON,a.OK_INSOURCE_TEM,a.IN_SUPPLIER,a.STATUS_FLAGS,a.PUSH_DOWN_NUM,a.PROCESS_ORDER from c_process_details_t a left join "
			+ "c_mes_process_station_t b on a.PROCESS_ID = b.ID where b.STATION_TYPES=#{typeId}) c left join c_process_production_task d on c.TASK_ID=d.ID WHERE c.PUSH_DOWN_NUM>0  )x left join c_mes_process_production_way_t y on x.IDX=y.ST_ID and x.ROUTE_NAME=y.ROUTING_ID where 1=1"
			+ "<if test=\"stationNum!=''\"> and x.STATION_NUM like '%${stationNum}%'</if>"
			+ "<if test=\"projectNum!=''\"> and x.PROJECT_NUM like '%${projectNum}%'</if>"
			+ "<if test=\"specificationModel!=''\"> and x.SPECIFICATION_AND_MODEL like '%${specificationModel}%'</if>"
			+ "<if test=\"materiCode!=''\"> and x.MATERIAL_CODE like '%${materiCode}%'</if> </script>")
	public List<Map<String,Object>> showAllProcessTypeTaskInfos(@Param("typeId")Integer typeId,@Param("projectNum")String projectNum,@Param("specificationModel")String specificationModel,@Param("statusFlags")String statusFlags,@Param("flagx")String flagx,@Param("stationNum")String stationNum,@Param("materiCode")String materiCode);
//	根据点击的生产任务形成类型下拉框（下料类型）
	@Select("select ID,STATION_NAME from c_mes_process_station_t where ID in(select w.ST_ID from c_process_production_task t left join c_mes_process_production_way_t w on t.ROUTE_NAME =w.ROUTING_ID where t.ID=#{taskId} ) and STATION_TYPES=#{typeId}")
	public List<Map<String,Object>> showAllRouteNameList(@Param("taskId")Integer taskId,@Param("typeId")Integer typeId);

//	最后一道工序完成数量与状态的改变
	@Update("<script>update c_process_production_task set COMPLETE_NUM=#{completeNum}<if test=\"statusFlags!=null\">,STATUS_FLAGS=#{statusFlags}</if> where ID=#{taskId}</script>")
	public Integer updateProcessTaskCompleteInfo(@Param("completeNum")Integer complete,@Param("statusFlags")Integer statusFlags,@Param("taskId")Integer taskId);

//	判断当前工序类型
	@Select("select STATION_TYPES from c_mes_process_station_t where ID in (select c.PROCESS_ID from (select ID,STATUS_FLAGS,ROUTE_NAME from c_process_production_task where PROJECT_NUM=#{projectNum} and SPECIFICATION_AND_MODEL=#{specificationModel}) t left join c_process_details_t c on t.ID=c.TASK_ID where PROCESS_ID=#{processId})")
	public Integer showNowProcesstypeInfo(@Param("projectNum")String projectNum,@Param("specificationModel")String specificationModel,@Param("processId")Integer processId);

//	更新委外工序下推信息
	@Update("update c_process_details_t set COMPLETE_NUM=#{completeNum} ,USEABLE_NUM=#{useAble},OUTSOURCE_IN_NUM=#{outsourceInNum} where PROCESS_ID=#{id} and TASK_ID=#{taskId}")
	public Integer updateDetailsTaskInInfos(@Param("completeNum")Integer completeNum,@Param("useAble")Integer useAble,@Param("id")Integer id,@Param("taskId")Integer taskId,@Param("outsourceInNum")Integer outsourceInNum);

//	展示委外发出工序
//	@Select("<script>select distinct k.ID,K.PROJECT_NUM,K.SPECIFICATION_AND_MODEL,K.MATERIAL_CODE,K.OPERATION_DEPARTMENT from (select * from c_process_production_task where ID in(select distinct  n.ID from (select w.ST_ID,t.ID from c_process_production_task t left join c_mes_process_production_way_t w on t.ROUTE_NAME=w.ROUTING_ID) n left join c_mes_process_station_t m on n.ST_ID=m.ID where m.STATION_TYPES=#{typeId})) k left join c_process_details_t p on k.ID=P.TASK_ID where PROCESS_ID in(select ID from c_mes_process_station_t where STATION_TYPES=#{typeId}) and (TESTING_NUM-OUTSOURCE_OUT_NUM)>0"
//			+ "<if test=\"projectNum!=null and projectNum!=''\"> and PROJECT_NUM like '%${projectNum}%' </if> <if test=\"specificationModel!=null and specificationModel!=''\"> and SPECIFICATION_AND_MODEL like '%${specificationModel}%' </if> <if test=\"statusFlags!=null and statusFlags!=''\"> and STATUS_FLAGS like '%${statusFlags}%' </if> </script>")
//	@Select("<script> select c.TASK_ID,c.SPLIT_FLAGS,c.ID as IDS,d.MATERIAL_NAME,d.MATERIAL_QUALITY,d.NUM_REMARKS,d.IMAGES,c.INSOURCE_IN_TEMS_APPROVAL,c.STATION_TYPES,c.ALL_OUT_SUPPLIER,c.OK_INSOURCE_TEM_APPROVLA,d.STATUS_FLAGS as TASK_STATUS,c.OK_INSOURCE_TEM,c.IN_PERSON,c.IN_SUPPLIER,c.OUT_PERSON,c.OUT_SUPPLIER,c.STATUS_FLAGS,c.TESTING_NUM_TEM,c.QUALITY_PERSON,d.ID,d.PROJECT_NUM,d.SPECIFICATION_AND_MODEL,c.STATION_NAME,c.PLAN_NUM,c.COMPLETE_NUM,c.USEABLE_NUM,c.OUTSOURCE_OUT_NUM,c.OUTSOURCE_IN_NUM,c.NG_NUM,c.REWORK_NUM,c.TESTING_NUM,c.PROCESS_ID,c.FINISH_PRODUCT_TEM from (select a.SPLIT_FLAGS,a.ID,a.INSOURCE_IN_TEMS_APPROVAL,a.OK_INSOURCE_TEM_APPROVLA,a.OK_INSOURCE_TEM,a.IN_PERSON,a.IN_SUPPLIER,a.OUT_PERSON,a.OUT_SUPPLIER,a.TASK_ID,b.STATION_NAME,a.PLAN_NUM,a.COMPLETE_NUM,a.USEABLE_NUM,a.OUTSOURCE_OUT_NUM,a.OUTSOURCE_IN_NUM,a.NG_NUM,a.REWORK_NUM,a.TESTING_NUM,a.PROCESS_ID,a.FINISH_PRODUCT_TEM,a.QUALITY_PERSON,a.TESTING_NUM_TEM,a.STATUS_FLAGS,a.ALL_OUT_SUPPLIER,b.STATION_TYPES from c_process_details_t a left join c_mes_process_station_t b on a.PROCESS_ID = b.ID where b.STATION_TYPES=#{typeId}) c left join c_process_production_task d on c.TASK_ID=d.ID WHERE  TESTING_NUM_TEM>0 and (c.SPLIT_FLAGS=1 or c.SPLIT_FLAGS=2 or c.SPLIT_FLAGS=3)  </script>")//c.TESTING_NUM>0 and(20210512)    OK_INSOURCE_TEM>0 （511） c.STATUS_FLAGS=#{flags}    <if test=\"flags==0\">and c.OK_INSOURCE_TEM>0</if> <if test=\"flags!=0\">and c.OK_INSOURCE_TEM_APPROVLA>0</if>
//	public List<Map<String,Object>> showAllProcessTypeTaskOutInfos(@Param("typeId")Integer typeId,@Param("projectNum")String projectNum,@Param("specificationModel")String specificationModel,@Param("statusFlags")String statusFlags);
//4.0
//	@Select("<script> (select c.PROCESS_ORDER,d.STATION_NUM,d.MATERIAL_CODE,c.TASK_ID,c.SPLIT_FLAGS,c.ID as IDS,d.MATERIAL_NAME,d.MATERIAL_QUALITY,d.NUM_REMARKS,d.IMAGES,c.INSOURCE_IN_TEMS_APPROVAL,c.STATION_TYPES,c.ALL_OUT_SUPPLIER,c.OK_INSOURCE_TEM_APPROVLA,d.STATUS_FLAGS as TASK_STATUS,c.OK_INSOURCE_TEM,c.IN_PERSON,c.IN_SUPPLIER,c.OUT_PERSON,c.OUT_SUPPLIER,c.STATUS_FLAGS,c.TESTING_NUM_TEM,c.QUALITY_PERSON,d.ID,d.PROJECT_NUM,d.SPECIFICATION_AND_MODEL,c.STATION_NAME,c.PLAN_NUM,c.COMPLETE_NUM,c.USEABLE_NUM,c.OUTSOURCE_OUT_NUM,c.OUTSOURCE_IN_NUM,c.NG_NUM,c.REWORK_NUM,c.TESTING_NUM,c.PROCESS_ID,c.FINISH_PRODUCT_TEM from (select a.SPLIT_FLAGS,a.ID,a.INSOURCE_IN_TEMS_APPROVAL,a.OK_INSOURCE_TEM_APPROVLA,a.OK_INSOURCE_TEM,a.IN_PERSON,a.IN_SUPPLIER,a.OUT_PERSON,a.OUT_SUPPLIER,a.TASK_ID,b.STATION_NAME,a.PLAN_NUM,a.COMPLETE_NUM,a.USEABLE_NUM,a.OUTSOURCE_OUT_NUM,a.OUTSOURCE_IN_NUM,a.NG_NUM,a.REWORK_NUM,a.TESTING_NUM,a.PROCESS_ID,a.FINISH_PRODUCT_TEM,a.QUALITY_PERSON,a.TESTING_NUM_TEM,a.STATUS_FLAGS,a.ALL_OUT_SUPPLIER,b.STATION_TYPES,a.PROCESS_ORDER from c_process_details_t a left join c_mes_process_station_t b on a.PROCESS_ID = b.ID where b.STATION_TYPES=#{typeId}) c left join c_process_production_task d on c.TASK_ID=d.ID WHERE  TESTING_NUM_TEM>0 and c.TESTING_NUM>0 and (c.SPLIT_FLAGS=1 or c.SPLIT_FLAGS=2) "
//			+ "<if test=\"stationNum!=''\"> and d.STATION_NUM like '%${stationNum}%'</if>"
//			+ "<if test=\"projectNum!=''\"> and d.PROJECT_NUM like '%${projectNum}%'</if>"
//			+ "<if test=\"specificationModel!=''\"> and d.SPECIFICATION_AND_MODEL like '%${specificationModel}%'</if>"
//			+ "<if test=\"materiCode!=''\"> and d.MATERIAL_CODE like '%${materiCode}%'</if>)"
//			+ "union all(select c.PROCESS_ORDER,d.STATION_NUM,d.MATERIAL_CODE,c.TASK_ID,c.SPLIT_FLAGS,c.ID as IDS,d.MATERIAL_NAME,d.MATERIAL_QUALITY,d.NUM_REMARKS,d.IMAGES,c.INSOURCE_IN_TEMS_APPROVAL,c.STATION_TYPES,c.ALL_OUT_SUPPLIER,c.OK_INSOURCE_TEM_APPROVLA,d.STATUS_FLAGS as TASK_STATUS,c.OK_INSOURCE_TEM,c.IN_PERSON,c.IN_SUPPLIER,c.OUT_PERSON,c.OUT_SUPPLIER,c.STATUS_FLAGS,c.TESTING_NUM_TEM,c.QUALITY_PERSON,d.ID,d.PROJECT_NUM,d.SPECIFICATION_AND_MODEL,c.STATION_NAME,c.PLAN_NUM,c.COMPLETE_NUM,c.USEABLE_NUM,c.OUTSOURCE_OUT_NUM,c.OUTSOURCE_IN_NUM,c.NG_NUM,c.REWORK_NUM,c.TESTING_NUM,c.PROCESS_ID,c.FINISH_PRODUCT_TEM from (select a.SPLIT_FLAGS,a.ID,a.INSOURCE_IN_TEMS_APPROVAL,a.OK_INSOURCE_TEM_APPROVLA,a.OK_INSOURCE_TEM,a.IN_PERSON,a.IN_SUPPLIER,a.OUT_PERSON,a.OUT_SUPPLIER,a.TASK_ID,b.STATION_NAME,a.PLAN_NUM,a.COMPLETE_NUM,a.USEABLE_NUM,a.OUTSOURCE_OUT_NUM,a.OUTSOURCE_IN_NUM,a.NG_NUM,a.REWORK_NUM,a.TESTING_NUM,a.PROCESS_ID,a.FINISH_PRODUCT_TEM,a.QUALITY_PERSON,a.TESTING_NUM_TEM,a.STATUS_FLAGS,a.ALL_OUT_SUPPLIER,b.STATION_TYPES,a.PROCESS_ORDER from c_process_details_t a left join c_mes_process_station_t b on a.PROCESS_ID = b.ID where b.STATION_TYPES=#{typeId}) c left join c_process_production_task d on c.TASK_ID=d.ID WHERE  TESTING_NUM_TEM>0  and c.SPLIT_FLAGS=3 "
//			+ "<if test=\"stationNum!=''\"> and d.STATION_NUM like '%${stationNum}%'</if>"
//			+ "<if test=\"projectNum!=''\"> and d.PROJECT_NUM like '%${projectNum}%'</if>"
//			+ "<if test=\"specificationModel!=''\"> and d.SPECIFICATION_AND_MODEL like '%${specificationModel}%'</if>"
//			+ "<if test=\"materiCode!=''\"> and d.MATERIAL_CODE like '%${materiCode}%'</if>)   order by STATION_NUM,MATERIAL_CODE,SPECIFICATION_AND_MODEL,PROCESS_ORDER,IDS </script>")
	@Select("<script> (  select y.DT,y.ST_ID,y.ROUTING_ID,y.SERIAL_NO,y.PROCESS_REMARKS,y.RUN_TIMES,x.ROUTE_NAME,x.IDX,x.PROCESS_ORDER,x.STATION_NUM,x.MATERIAL_CODE,x.TASK_ID,x.SPLIT_FLAGS,x.IDS,x.MATERIAL_NAME,x.MATERIAL_QUALITY,x.NUM_REMARKS,x.IMAGES,x.INSOURCE_IN_TEMS_APPROVAL,x.STATION_TYPES,x.ALL_OUT_SUPPLIER,x.OK_INSOURCE_TEM_APPROVLA,x.TASK_STATUS,x.OK_INSOURCE_TEM,x.IN_PERSON,x.IN_SUPPLIER,x.OUT_PERSON,x.OUT_SUPPLIER,x.STATUS_FLAGS,x.TESTING_NUM_TEM,x.QUALITY_PERSON,x.ID,x.PROJECT_NUM,x.SPECIFICATION_AND_MODEL,x.STATION_NAME,x.PLAN_NUM,x.COMPLETE_NUM,x.USEABLE_NUM,x.OUTSOURCE_OUT_NUM,x.OUTSOURCE_IN_NUM,x.NG_NUM,x.REWORK_NUM,x.TESTING_NUM,x.PROCESS_ID,x.FINISH_PRODUCT_TEM from (select d.ROUTE_NAME,c.IDX,c.PROCESS_ORDER,d.STATION_NUM,d.MATERIAL_CODE,c.TASK_ID,c.SPLIT_FLAGS,c.ID as IDS,d.MATERIAL_NAME,d.MATERIAL_QUALITY,d.NUM_REMARKS,d.IMAGES,c.INSOURCE_IN_TEMS_APPROVAL,c.STATION_TYPES,c.ALL_OUT_SUPPLIER,c.OK_INSOURCE_TEM_APPROVLA,d.STATUS_FLAGS as TASK_STATUS,c.OK_INSOURCE_TEM,c.IN_PERSON,c.IN_SUPPLIER,c.OUT_PERSON,c.OUT_SUPPLIER,c.STATUS_FLAGS,c.TESTING_NUM_TEM,c.QUALITY_PERSON,d.ID,d.PROJECT_NUM,d.SPECIFICATION_AND_MODEL,c.STATION_NAME,c.PLAN_NUM,c.COMPLETE_NUM,c.USEABLE_NUM,c.OUTSOURCE_OUT_NUM,c.OUTSOURCE_IN_NUM,c.NG_NUM,c.REWORK_NUM,c.TESTING_NUM,c.PROCESS_ID,c.FINISH_PRODUCT_TEM from (select b.ID as IDX,a.SPLIT_FLAGS,a.ID,a.INSOURCE_IN_TEMS_APPROVAL,a.OK_INSOURCE_TEM_APPROVLA,a.OK_INSOURCE_TEM,a.IN_PERSON,a.IN_SUPPLIER,a.OUT_PERSON,a.OUT_SUPPLIER,a.TASK_ID,b.STATION_NAME,a.PLAN_NUM,a.COMPLETE_NUM,a.USEABLE_NUM,a.OUTSOURCE_OUT_NUM,a.OUTSOURCE_IN_NUM,a.NG_NUM,a.REWORK_NUM,a.TESTING_NUM,a.PROCESS_ID,a.FINISH_PRODUCT_TEM,a.QUALITY_PERSON,a.TESTING_NUM_TEM,a.STATUS_FLAGS,a.ALL_OUT_SUPPLIER,b.STATION_TYPES,a.PROCESS_ORDER from c_process_details_t a left join c_mes_process_station_t b on a.PROCESS_ID = b.ID where b.STATION_TYPES=#{typeId}) c left join c_process_production_task d on c.TASK_ID=d.ID WHERE  TESTING_NUM_TEM>0 and c.TESTING_NUM>0 and (c.SPLIT_FLAGS=1 or c.SPLIT_FLAGS=2)  )x left join c_mes_process_production_way_t y on x.IDX=y.ST_ID and x.ROUTE_NAME=y.ROUTING_ID  where 1=1 "
			+ "<if test=\"stationNum!=''\"> and x.STATION_NUM like '%${stationNum}%'</if>"
			+ "<if test=\"projectNum!=''\"> and x.PROJECT_NUM like '%${projectNum}%'</if>"
			+ "<if test=\"specificationModel!=''\"> and x.SPECIFICATION_AND_MODEL like '%${specificationModel}%'</if>"
			+ "<if test=\"materiCode!=''\"> and x.MATERIAL_CODE like '%${materiCode}%'</if>)"
			+ "union all(select y.DT,y.ST_ID,y.ROUTING_ID,y.SERIAL_NO,y.PROCESS_REMARKS,y.RUN_TIMES,x.ROUTE_NAME,x.IDX,x.PROCESS_ORDER,x.STATION_NUM,x.MATERIAL_CODE,x.TASK_ID,x.SPLIT_FLAGS,x.IDS,x.MATERIAL_NAME,x.MATERIAL_QUALITY,x.NUM_REMARKS,x.IMAGES,x.INSOURCE_IN_TEMS_APPROVAL,x.STATION_TYPES,x.ALL_OUT_SUPPLIER,x.OK_INSOURCE_TEM_APPROVLA,x.TASK_STATUS,x.OK_INSOURCE_TEM,x.IN_PERSON,x.IN_SUPPLIER,x.OUT_PERSON,x.OUT_SUPPLIER,x.STATUS_FLAGS,x.TESTING_NUM_TEM,x.QUALITY_PERSON,x.ID,x.PROJECT_NUM,x.SPECIFICATION_AND_MODEL,x.STATION_NAME,x.PLAN_NUM,x.COMPLETE_NUM,x.USEABLE_NUM,x.OUTSOURCE_OUT_NUM,x.OUTSOURCE_IN_NUM,x.NG_NUM,x.REWORK_NUM,x.TESTING_NUM,x.PROCESS_ID,x.FINISH_PRODUCT_TEM from (select d.ROUTE_NAME,c.IDX,c.PROCESS_ORDER,d.STATION_NUM,d.MATERIAL_CODE,c.TASK_ID,c.SPLIT_FLAGS,c.ID as IDS,d.MATERIAL_NAME,d.MATERIAL_QUALITY,d.NUM_REMARKS,d.IMAGES,c.INSOURCE_IN_TEMS_APPROVAL,c.STATION_TYPES,c.ALL_OUT_SUPPLIER,c.OK_INSOURCE_TEM_APPROVLA,d.STATUS_FLAGS as TASK_STATUS,c.OK_INSOURCE_TEM,c.IN_PERSON,c.IN_SUPPLIER,c.OUT_PERSON,c.OUT_SUPPLIER,c.STATUS_FLAGS,c.TESTING_NUM_TEM,c.QUALITY_PERSON,d.ID,d.PROJECT_NUM,d.SPECIFICATION_AND_MODEL,c.STATION_NAME,c.PLAN_NUM,c.COMPLETE_NUM,c.USEABLE_NUM,c.OUTSOURCE_OUT_NUM,c.OUTSOURCE_IN_NUM,c.NG_NUM,c.REWORK_NUM,c.TESTING_NUM,c.PROCESS_ID,c.FINISH_PRODUCT_TEM from (select b.ID as IDX,a.SPLIT_FLAGS,a.ID,a.INSOURCE_IN_TEMS_APPROVAL,a.OK_INSOURCE_TEM_APPROVLA,a.OK_INSOURCE_TEM,a.IN_PERSON,a.IN_SUPPLIER,a.OUT_PERSON,a.OUT_SUPPLIER,a.TASK_ID,b.STATION_NAME,a.PLAN_NUM,a.COMPLETE_NUM,a.USEABLE_NUM,a.OUTSOURCE_OUT_NUM,a.OUTSOURCE_IN_NUM,a.NG_NUM,a.REWORK_NUM,a.TESTING_NUM,a.PROCESS_ID,a.FINISH_PRODUCT_TEM,a.QUALITY_PERSON,a.TESTING_NUM_TEM,a.STATUS_FLAGS,a.ALL_OUT_SUPPLIER,b.STATION_TYPES,a.PROCESS_ORDER from c_process_details_t a left join c_mes_process_station_t b on a.PROCESS_ID = b.ID where b.STATION_TYPES=#{typeId}) c left join c_process_production_task d on c.TASK_ID=d.ID WHERE  TESTING_NUM_TEM>0  and c.SPLIT_FLAGS=3 )x left join c_mes_process_production_way_t y on x.IDX=y.ST_ID and x.ROUTE_NAME=y.ROUTING_ID  where 1=1"
			+ "<if test=\"stationNum!=''\"> and x.STATION_NUM like '%${stationNum}%'</if>"
			+ "<if test=\"projectNum!=''\"> and x.PROJECT_NUM like '%${projectNum}%'</if>"
			+ "<if test=\"specificationModel!=''\"> and x.SPECIFICATION_AND_MODEL like '%${specificationModel}%'</if>"
			+ "<if test=\"materiCode!=''\"> and x.MATERIAL_CODE like '%${materiCode}%'</if>)   order by STATION_NUM,MATERIAL_CODE,SPECIFICATION_AND_MODEL,PROCESS_ORDER,IDS </script>")
	public List<Map<String,Object>> showAllProcessTypeTaskOutInfos(@Param("typeId")String typeId,@Param("flags")String flags,@Param("stationNum")String stationNum,@Param("projectNum")String projectNum,@Param("specificationModel")String specificationModel,@Param("materiCode")String materiCode );


//	展示委外发出工序
//	@Select("<script>select distinct k.ID,K.PROJECT_NUM,K.SPECIFICATION_AND_MODEL,K.MATERIAL_CODE,K.OPERATION_DEPARTMENT from (select * from c_process_production_task where ID in(select distinct  n.ID from (select w.ST_ID,t.ID from c_process_production_task t left join c_mes_process_production_way_t w on t.ROUTE_NAME=w.ROUTING_ID) n left join c_mes_process_station_t m on n.ST_ID=m.ID where m.STATION_TYPES=#{typeId})) k left join c_process_details_t p on k.ID=P.TASK_ID where PROCESS_ID in(select ID from c_mes_process_station_t where STATION_TYPES=#{typeId}) and (TESTING_NUM-OUTSOURCE_OUT_NUM)>0"
//			+ "<if test=\"projectNum!=null and projectNum!=''\"> and PROJECT_NUM like '%${projectNum}%' </if> <if test=\"specificationModel!=null and specificationModel!=''\"> and SPECIFICATION_AND_MODEL like '%${specificationModel}%' </if> <if test=\"statusFlags!=null and statusFlags!=''\"> and STATUS_FLAGS like '%${statusFlags}%' </if> </script>")
	@Select("<script> select d.STATION_NUM,d.MATERIAL_CODE,c.ID as IDS,d.MATERIAL_NAME,d.MATERIAL_QUALITY,d.NUM_REMARKS,d.IMAGES,c.ALL_IN_SUPPLIER,c.INSOURCE_IN_TEMS_APPROVAL,c.STATION_TYPES,c.ALL_OUT_SUPPLIER,c.OK_INSOURCE_TEM_APPROVLA,d.STATUS_FLAGS as TASK_STATUS,c.OK_INSOURCE_TEM,c.IN_PERSON,c.IN_SUPPLIER,c.OUT_PERSON,c.OUT_SUPPLIER,c.STATUS_FLAGS,c.TESTING_NUM_TEM,c.QUALITY_PERSON,d.ID,d.PROJECT_NUM,d.SPECIFICATION_AND_MODEL,c.STATION_NAME,c.PLAN_NUM,c.COMPLETE_NUM,c.USEABLE_NUM,c.OUTSOURCE_OUT_NUM,c.OUTSOURCE_IN_NUM,c.NG_NUM,c.REWORK_NUM,c.TESTING_NUM,c.PROCESS_ID,c.FINISH_PRODUCT_TEM from (select a.ID,a.ALL_IN_SUPPLIER,a.INSOURCE_IN_TEMS_APPROVAL,a.OK_INSOURCE_TEM_APPROVLA,a.OK_INSOURCE_TEM,a.IN_PERSON,a.IN_SUPPLIER,a.OUT_PERSON,a.OUT_SUPPLIER,a.TASK_ID,b.STATION_NAME,a.PLAN_NUM,a.COMPLETE_NUM,a.USEABLE_NUM,a.OUTSOURCE_OUT_NUM,a.OUTSOURCE_IN_NUM,a.NG_NUM,a.REWORK_NUM,a.TESTING_NUM,a.PROCESS_ID,a.FINISH_PRODUCT_TEM,a.QUALITY_PERSON,a.TESTING_NUM_TEM,a.STATUS_FLAGS,a.ALL_OUT_SUPPLIER,b.STATION_TYPES from c_process_details_t a left join c_mes_process_station_t b on a.PROCESS_ID = b.ID where b.STATION_TYPES=#{typeId}) c "
			+ "left join c_process_production_task d on c.TASK_ID=d.ID WHERE  INSOURCE_IN_TEMS_APPROVAL>0  "
			+ "<if test=\"stationNum!=''\"> and d.STATION_NUM like '%${stationNum}%'</if>"
			+ "<if test=\"projectNum!=''\"> and d.PROJECT_NUM like '%${projectNum}%'</if>"
			+ "<if test=\"specificationModel!=''\"> and d.SPECIFICATION_AND_MODEL like '%${specificationModel}%'</if>"
			+ "<if test=\"materiCode!=''\"> and d.MATERIAL_CODE like '%${materiCode}%'</if></script>")//c.STATUS_FLAGS=#{flags}    <if test=\"flags==0\">and c.OK_INSOURCE_TEM>0</if> <if test=\"flags!=0\">and c.OK_INSOURCE_TEM_APPROVLA>0</if>
//	public List<Map<String,Object>> showAllProcessTypeTaskOutInfos(@Param("typeId")Integer typeId,@Param("projectNum")String projectNum,@Param("specificationModel")String specificationModel,@Param("statusFlags")String statusFlags);
	public List<Map<String,Object>> showAllProcessTypeTaskOutInfosxs(@Param("typeId")String typeId,@Param("flags")String flags,@Param("stationNum")String stationNum,@Param("materiCode")String materiCode,@Param("projectNum")String projectNum,@Param("specificationModel")String specificationModel);




	@Select("(select c.OK_FINISH_PRODUCTION_TEMS_APPROVAL,c.OK_FINISH_PRODUCTION_TEMS,c.FINISH_PRODUCT_TEM,c.OK_INSOURCE_TEMS,c.OK_INSOURCE_TEM,c.ALL_QUALITY_PERSON,c.QUALITY_PERSON,c.OK_NUM_TEM_APPROVAL,c.NG_NUM_TEM_APPROVAL,c.TESTING_NUM_TEM,c.OK_OUTSOURCE_TEM,d.ID,d.PROJECT_NUM,d.SPECIFICATION_AND_MODEL,c.STATION_NAME,c.PLAN_NUM,c.COMPLETE_NUM,c.USEABLE_NUM,c.OUTSOURCE_OUT_NUM,c.OUTSOURCE_IN_NUM,c.NG_NUM,c.REWORK_NUM,c.TESTING_NUM,c.PROCESS_ID,c.STATUS_FLAGS,c.NG_NUM_TEM,d.STATUS_FLAGS as TASK_STATUS,c.ALL_REMARKS,c.REMARKS,c.PLAN_NUM,c.COMPLETE_NUM,c.STATION_TYPES from (select a.OK_FINISH_PRODUCTION_TEMS_APPROVAL,a.OK_FINISH_PRODUCTION_TEMS,a.OK_INSOURCE_TEMS,a.OK_INSOURCE_TEM,a.ALL_QUALITY_PERSON,a.QUALITY_PERSON,a.OK_NUM_TEM_APPROVAL,a.NG_NUM_TEM_APPROVAL,a.TESTING_NUM_TEM,a.PROCESS_ID,a.TASK_ID,b.STATION_NAME,a.PLAN_NUM,a.COMPLETE_NUM,a.USEABLE_NUM,a.OUTSOURCE_OUT_NUM,a.OUTSOURCE_IN_NUM,a.NG_NUM,a.REWORK_NUM,a.TESTING_NUM,a.STATUS_FLAGS,a.NG_NUM_TEM,a.ALL_REMARKS,a.REMARKS,b.STATION_TYPES,a.OK_OUTSOURCE_TEM,a.FINISH_PRODUCT_TEM from c_process_details_t a left join c_mes_process_station_t b on a.PROCESS_ID = b.ID ) c left join c_process_production_task d on c.TASK_ID=d.ID WHERE c.OK_FINISH_PRODUCTION_TEMS>0 and c.STATUS_FLAGS=#{statusFlags}  and d.ID=#{id} and c.PROCESS_ID=#{processId})"
			+ "union(select  c.OK_FINISH_PRODUCTION_TEMS_APPROVAL,c.OK_FINISH_PRODUCTION_TEMS,c.FINISH_PRODUCT_TEM,c.OK_INSOURCE_TEMS,c.OK_INSOURCE_TEM,c.ALL_QUALITY_PERSON,c.QUALITY_PERSON,c.OK_NUM_TEM_APPROVAL,c.NG_NUM_TEM_APPROVAL,c.TESTING_NUM_TEM,c.OK_OUTSOURCE_TEM,d.ID,d.PROJECT_NUM,d.SPECIFICATION_AND_MODEL,c.STATION_NAME,c.PLAN_NUM,c.COMPLETE_NUM,c.USEABLE_NUM,c.OUTSOURCE_OUT_NUM,c.OUTSOURCE_IN_NUM,c.NG_NUM,c.REWORK_NUM,c.TESTING_NUM,c.PROCESS_ID,c.STATUS_FLAGS,c.NG_NUM_TEM,d.STATUS_FLAGS as TASK_STATUS,c.ALL_REMARKS,c.REMARKS,c.PLAN_NUM,c.COMPLETE_NUM,c.STATION_TYPES from (select a.OK_FINISH_PRODUCTION_TEMS_APPROVAL,a.OK_FINISH_PRODUCTION_TEMS,a.OK_INSOURCE_TEMS,a.OK_INSOURCE_TEM,a.ALL_QUALITY_PERSON,a.QUALITY_PERSON,a.OK_NUM_TEM_APPROVAL,a.NG_NUM_TEM_APPROVAL,a.TESTING_NUM_TEM,a.PROCESS_ID,a.TASK_ID,b.STATION_NAME,a.PLAN_NUM,a.COMPLETE_NUM,a.USEABLE_NUM,a.OUTSOURCE_OUT_NUM,a.OUTSOURCE_IN_NUM,a.NG_NUM,a.REWORK_NUM,a.TESTING_NUM,a.STATUS_FLAGS,a.NG_NUM_TEM,a.ALL_REMARKS,a.REMARKS,b.STATION_TYPES,a.OK_OUTSOURCE_TEM,a.FINISH_PRODUCT_TEM from c_process_details_t a left join c_mes_process_station_t b on a.PROCESS_ID = b.ID ) "
			+ "c left join c_process_production_task d on c.TASK_ID=d.ID WHERE c.OUTSOURCE_OUT_NUM>0 and c.STATUS_FLAGS=#{statusFlags}  and d.ID=#{id} and c.PROCESS_ID=#{processId})")
	public Map<String,Object> showAllQualityApprovasListx(@Param("statusFlags")String statusFlags,@Param("id")String id,@Param("processId")String processId);



//	展示委外发出工序
//	@Select("<script>select distinct k.ID,K.PROJECT_NUM,K.SPECIFICATION_AND_MODEL,K.MATERIAL_CODE,K.OPERATION_DEPARTMENT from (select * from c_process_production_task where ID in(select distinct  n.ID from (select w.ST_ID,t.ID from c_process_production_task t left join c_mes_process_production_way_t w on t.ROUTE_NAME=w.ROUTING_ID) n left join c_mes_process_station_t m on n.ST_ID=m.ID where m.STATION_TYPES=#{typeId})) k left join c_process_details_t p on k.ID=P.TASK_ID where PROCESS_ID in(select ID from c_mes_process_station_t where STATION_TYPES=#{typeId}) and (TESTING_NUM-OUTSOURCE_OUT_NUM)>0"
//			+ "<if test=\"projectNum!=null and projectNum!=''\"> and PROJECT_NUM like '%${projectNum}%' </if> <if test=\"specificationModel!=null and specificationModel!=''\"> and SPECIFICATION_AND_MODEL like '%${specificationModel}%' </if> <if test=\"statusFlags!=null and statusFlags!=''\"> and STATUS_FLAGS like '%${statusFlags}%' </if> </script>")
//4.0
//	@Select("<script> select d.STATION_NUM,d.MATERIAL_CODE,c.SPLIT_FLAGS,c.ID as IDS,d.MATERIAL_NAME,d.MATERIAL_QUALITY,d.NUM_REMARKS,d.IMAGES,c.INSOURCE_IN_TEMS_APPROVAL,c.STATION_TYPES,c.ALL_OUT_SUPPLIER,c.OK_INSOURCE_TEM_APPROVLA,d.STATUS_FLAGS as TASK_STATUS,c.OK_INSOURCE_TEM,c.IN_PERSON,c.IN_SUPPLIER,c.OUT_PERSON,c.OUT_SUPPLIER,c.STATUS_FLAGS,c.TESTING_NUM_TEM,c.QUALITY_PERSON,d.ID,d.PROJECT_NUM,d.SPECIFICATION_AND_MODEL,c.STATION_NAME,c.PLAN_NUM,c.COMPLETE_NUM,c.USEABLE_NUM,c.OUTSOURCE_OUT_NUM,c.OUTSOURCE_IN_NUM,c.NG_NUM,c.REWORK_NUM,c.TESTING_NUM,c.PROCESS_ID,c.FINISH_PRODUCT_TEM from (select a.SPLIT_FLAGS,a.ID,a.INSOURCE_IN_TEMS_APPROVAL,a.OK_INSOURCE_TEM_APPROVLA,a.OK_INSOURCE_TEM,a.IN_PERSON,a.IN_SUPPLIER,a.OUT_PERSON,a.OUT_SUPPLIER,a.TASK_ID,b.STATION_NAME,a.PLAN_NUM,a.COMPLETE_NUM,a.USEABLE_NUM,a.OUTSOURCE_OUT_NUM,a.OUTSOURCE_IN_NUM,a.NG_NUM,a.REWORK_NUM,a.TESTING_NUM,a.PROCESS_ID,a.FINISH_PRODUCT_TEM,a.QUALITY_PERSON,a.TESTING_NUM_TEM,a.STATUS_FLAGS,a.ALL_OUT_SUPPLIER,b.STATION_TYPES from c_process_details_t a "
//			+ "left join c_mes_process_station_t b on a.PROCESS_ID = b.ID where b.STATION_TYPES=#{typeId}) c left join c_process_production_task d on c.TASK_ID=d.ID WHERE OK_INSOURCE_TEM_APPROVLA>0 "
//			+ "<if test=\"stationNum!=''\"> and d.STATION_NUM like '%${stationNum}%'</if>"
//			+ "<if test=\"projectNum!=''\"> and d.PROJECT_NUM like '%${projectNum}%'</if>"
//			+ "<if test=\"specificationModel!=''\"> and d.SPECIFICATION_AND_MODEL like '%${specificationModel}%'</if>"
//			+ "<if test=\"materiCode!=''\"> and d.MATERIAL_CODE like '%${materiCode}%'</if> </script>")
	// c.TESTING_NUM>0 and(20210512)c.STATUS_FLAGS=#{flags}    <if test=\"flags==0\">and c.OK_INSOURCE_TEM>0</if> <if test=\"flags!=0\">and c.OK_INSOURCE_TEM_APPROVLA>0</if>
//	public List<Map<String,Object>> showAllProcessTypeTaskOutInfos(@Param("typeId")Integer typeId,@Param("projectNum")String projectNum,@Param("specificationModel")String specificationModel,@Param("statusFlags")String statusFlags);
	@Select("<script> select * from (select d.ROUTE_NAME,c.IDX,d.STATION_NUM,d.MATERIAL_CODE,c.SPLIT_FLAGS,c.ID as IDS,d.MATERIAL_NAME,d.MATERIAL_QUALITY,d.NUM_REMARKS,d.IMAGES,c.INSOURCE_IN_TEMS_APPROVAL,c.STATION_TYPES,c.ALL_OUT_SUPPLIER,c.OK_INSOURCE_TEM_APPROVLA,d.STATUS_FLAGS as TASK_STATUS,c.OK_INSOURCE_TEM,c.IN_PERSON,c.IN_SUPPLIER,c.OUT_PERSON,c.OUT_SUPPLIER,c.STATUS_FLAGS,c.TESTING_NUM_TEM,c.QUALITY_PERSON,d.ID,d.PROJECT_NUM,d.SPECIFICATION_AND_MODEL,c.STATION_NAME,c.PLAN_NUM,c.COMPLETE_NUM,c.USEABLE_NUM,c.OUTSOURCE_OUT_NUM,c.OUTSOURCE_IN_NUM,c.NG_NUM,c.REWORK_NUM,c.TESTING_NUM,c.PROCESS_ID,c.FINISH_PRODUCT_TEM from (select b.ID as IDX,a.SPLIT_FLAGS,a.ID,a.INSOURCE_IN_TEMS_APPROVAL,a.OK_INSOURCE_TEM_APPROVLA,a.OK_INSOURCE_TEM,a.IN_PERSON,a.IN_SUPPLIER,a.OUT_PERSON,a.OUT_SUPPLIER,a.TASK_ID,b.STATION_NAME,a.PLAN_NUM,a.COMPLETE_NUM,a.USEABLE_NUM,a.OUTSOURCE_OUT_NUM,a.OUTSOURCE_IN_NUM,a.NG_NUM,a.REWORK_NUM,a.TESTING_NUM,a.PROCESS_ID,a.FINISH_PRODUCT_TEM,a.QUALITY_PERSON,a.TESTING_NUM_TEM,a.STATUS_FLAGS,a.ALL_OUT_SUPPLIER,b.STATION_TYPES from c_process_details_t a "
			+"left join c_mes_process_station_t b on a.PROCESS_ID = b.ID where b.STATION_TYPES=#{typeId}) c left join c_process_production_task d on c.TASK_ID=d.ID WHERE OK_INSOURCE_TEM_APPROVLA>0 )x left join c_mes_process_production_way_t y on x.IDX=y.ST_ID and x.ROUTE_NAME=y.ROUTING_ID where 1=1 "
			+ "<if test=\"stationNum!=''\"> and x.STATION_NUM like '%${stationNum}%'</if>"
			+ "<if test=\"projectNum!=''\"> and x.PROJECT_NUM like '%${projectNum}%'</if>"
			+ "<if test=\"specificationModel!=''\"> and x.SPECIFICATION_AND_MODEL like '%${specificationModel}%'</if>"
			+ "<if test=\"materiCode!=''\"> and x.MATERIAL_CODE like '%${materiCode}%'</if> </script>")
	public List<Map<String,Object>> showAllProcessTypeTaskOutInfosx(@Param("typeId")String typeId,@Param("flags")String flags,@Param("stationNum")String stationNum,@Param("materiCode")String materiCode,@Param("projectNum")String projectNum,@Param("specificationModel")String specificationModel);



//	更新委外发出数量
	@Update("update c_process_details_t set OUTSOURCE_OUT_NUM=#{useAble},OK_OUTSOURCE_TEM=#{useAble} where PROCESS_ID=#{id} and TASK_ID=#{taskId} ")
	public Integer updateOutNums(@Param("useAble")Integer useAble,@Param("id")Integer id,@Param("taskId")Integer taskId);

//	根据工序ID查询工序名称
	@Select("select STATION_NAME from c_mes_process_station_t where ID=#{id}")
	public String showProcessName(@Param("id")Integer id);

	@Select("select * from c_process_production_task where ID =#{id}")
	public Map<String,Object> showTaskStatusInfos(@Param("id")Integer id);

//  展示品检（新版）
//	@Select("select d.ID,d.PROJECT_NUM,d.SPECIFICATION_AND_MODEL,c.STATION_NAME,c.PLAN_NUM,c.COMPLETE_NUM,c.USEABLE_NUM,c.OUTSOURCE_OUT_NUM,c.OUTSOURCE_IN_NUM,c.NG_NUM,c.REWORK_NUM,c.TESTING_NUM,c.PROCESS_ID,c.STATUS_FLAGS,c.NG_NUM_TEM,d.STATUS_FLAGS as TASK_STATUS,c.REMARKS,c.QUALITY_PERSON,c.STATION_TYPES from (select a.PROCESS_ID,a.TASK_ID,b.STATION_NAME,a.PLAN_NUM,a.COMPLETE_NUM,a.USEABLE_NUM,a.OUTSOURCE_OUT_NUM,a.OUTSOURCE_IN_NUM,a.NG_NUM,a.REWORK_NUM,a.TESTING_NUM,a.STATUS_FLAGS,a.NG_NUM_TEM,a.REMARKS,a.QUALITY_PERSON,b.STATION_TYPES from c_process_details_t a left join c_mes_process_station_t b on a.PROCESS_ID = b.ID ) c left join c_process_production_task d on c.TASK_ID=d.ID WHERE c.TESTING_NUM>0 and c.STATUS_FLAGS=#{statusFlags}")and c.STATUS_FLAGS=#{statusFlags}
//	public List<Map<String,Object>> showQualityInfoList(@Param("statusFlags")String statusFlags);
//4.0
//	@Select("<script>(select c.PROCESS_ORDER,d.STATION_NUM,d.MATERIAL_CODE,c.SPLIT_FLAGS,c.ALL_OUT_SUPPLIER,c.ID as IDS,c.ALL_IN_SUPPLIER,d.NUM_REMARKS,d.MATERIAL_QUALITY,d.MATERIAL_NAME,d.IMAGES,c.OK_INSOURCE_TEMS,c.OK_OUTSOURCE_TEMS,c.OK_INSOURCE_TEM,c.ALL_QUALITY_PERSON,c.ALL_REMARKS,c.NG_NUM_TEM_APPROVAL,c.OK_NUM_TEM_APPROVAL,c.TESTING_NUM_TEM,c.OK_OUTSOURCE_TEM,d.ID,d.PROJECT_NUM,d.SPECIFICATION_AND_MODEL,c.STATION_NAME,c.PLAN_NUM,c.COMPLETE_NUM,c.USEABLE_NUM,c.OUTSOURCE_OUT_NUM,c.OUTSOURCE_IN_NUM,c.NG_NUM,c.REWORK_NUM,c.TESTING_NUM,c.PROCESS_ID,c.STATUS_FLAGS,c.NG_NUM_TEM,d.STATUS_FLAGS as TASK_STATUS,c.REMARKS,c.QUALITY_PERSON,c.STATION_TYPES from (select a.SPLIT_FLAGS,a.ALL_OUT_SUPPLIER,a.ID,a.ALL_IN_SUPPLIER,a.OK_INSOURCE_TEMS,a.OK_OUTSOURCE_TEMS,a.OK_INSOURCE_TEM,a.ALL_QUALITY_PERSON,a.ALL_REMARKS,a.NG_NUM_TEM_APPROVAL,a.OK_NUM_TEM_APPROVAL,a.PROCESS_ID,a.TASK_ID,b.STATION_NAME,a.PLAN_NUM,a.COMPLETE_NUM,a.USEABLE_NUM,a.OUTSOURCE_OUT_NUM,a.OUTSOURCE_IN_NUM,a.NG_NUM,a.REWORK_NUM,a.TESTING_NUM,a.STATUS_FLAGS,a.NG_NUM_TEM,a.REMARKS,a.QUALITY_PERSON,b.STATION_TYPES,a.OK_OUTSOURCE_TEM,a.TESTING_NUM_TEM,a.PROCESS_ORDER from c_process_details_t a left join c_mes_process_station_t b on a.PROCESS_ID = b.ID ) c left join c_process_production_task d on c.TASK_ID=d.ID WHERE c.TESTING_NUM>0  and c.STATION_TYPES!=3 "
//			+ "<if test=\"statusFlags!=1\">and TESTING_NUM_TEM>0</if>"
//			+ "<if test=\"statusFlags==1\">and (NG_NUM_TEM_APPROVAL>0 or OK_NUM_TEM_APPROVAL>0)</if>"
//			+ "<if test=\"stationNum!=''\"> and d.STATION_NUM like '%${stationNum}%'</if>"
//			+ "<if test=\"projectNum!=''\"> and d.PROJECT_NUM like '%${projectNum}%'</if>"
//			+ "<if test=\"specificationModel!=''\"> and d.SPECIFICATION_AND_MODEL like '%${specificationModel}%'</if>"
//			+ "<if test=\"materiCode!=''\"> and d.MATERIAL_CODE like '%${materiCode}%'</if>  order by PROCESS_ORDER desc)"
//			+ "UNION all(select c.PROCESS_ORDER,d.STATION_NUM,d.MATERIAL_CODE,c.SPLIT_FLAGS,c.ALL_OUT_SUPPLIER,c.ID as IDS,c.ALL_IN_SUPPLIER,d.NUM_REMARKS,d.MATERIAL_QUALITY,d.MATERIAL_NAME,d.IMAGES,c.OK_INSOURCE_TEMS,c.OK_OUTSOURCE_TEMS,c.OK_INSOURCE_TEM,c.ALL_QUALITY_PERSON,c.ALL_REMARKS,c.NG_NUM_TEM_APPROVAL,c.OK_NUM_TEM_APPROVAL,c.TESTING_NUM_TEM,c.OK_OUTSOURCE_TEM,d.ID,d.PROJECT_NUM,d.SPECIFICATION_AND_MODEL,c.STATION_NAME,c.PLAN_NUM,c.COMPLETE_NUM,c.USEABLE_NUM,c.OUTSOURCE_OUT_NUM,c.OUTSOURCE_IN_NUM,c.NG_NUM,c.REWORK_NUM,c.TESTING_NUM,c.PROCESS_ID,c.STATUS_FLAGS,c.NG_NUM_TEM,d.STATUS_FLAGS as TASK_STATUS,c.REMARKS,c.QUALITY_PERSON,c.STATION_TYPES from (select a.SPLIT_FLAGS,a.ALL_OUT_SUPPLIER,a.ID,a.ALL_IN_SUPPLIER,a.OK_INSOURCE_TEMS,a.OK_OUTSOURCE_TEMS,a.OK_INSOURCE_TEM,a.ALL_QUALITY_PERSON,a.ALL_REMARKS,a.NG_NUM_TEM_APPROVAL,a.OK_NUM_TEM_APPROVAL,a.PROCESS_ID,a.TASK_ID,b.STATION_NAME,a.PLAN_NUM,a.COMPLETE_NUM,a.USEABLE_NUM,a.OUTSOURCE_OUT_NUM,a.OUTSOURCE_IN_NUM,a.NG_NUM,a.REWORK_NUM,a.TESTING_NUM,a.STATUS_FLAGS,a.NG_NUM_TEM,a.REMARKS,a.QUALITY_PERSON,b.STATION_TYPES,a.OK_OUTSOURCE_TEM,a.TESTING_NUM_TEM,a.PROCESS_ORDER from c_process_details_t a left join c_mes_process_station_t b on a.PROCESS_ID = b.ID ) "
//			+ "c left join c_process_production_task d on c.TASK_ID=d.ID WHERE c.OUTSOURCE_OUT_NUM>0  and c.STATION_TYPES=3  <if test=\"statusFlags!=1\">and OK_INSOURCE_TEMS>0</if> <if test=\"statusFlags==1\">and (NG_NUM_TEM_APPROVAL>0 or OK_NUM_TEM_APPROVAL>0)</if>"
//			+ "<if test=\"stationNum!=''\"> and d.STATION_NUM like '%${stationNum}%'</if>"
//			+ "<if test=\"projectNum!=''\"> and d.PROJECT_NUM like '%${projectNum}%'</if>"
//			+ "<if test=\"specificationModel!=''\"> and d.SPECIFICATION_AND_MODEL like '%${specificationModel}%'</if>"
//			+ "<if test=\"materiCode!=''\"> and d.MATERIAL_CODE like '%${materiCode}%'</if> order by PROCESS_ORDER desc) order by  PROCESS_ORDER desc"
//			+ "</script>")
	//and c.STATUS_FLAGS=#{statusFlags}
//	@Select("<script>(select PROCESS_ORDER from (select c.IDX,d.ROUTE_NAME,c.PROCESS_ORDER,d.STATION_NUM,d.MATERIAL_CODE,c.SPLIT_FLAGS,c.ALL_OUT_SUPPLIER,c.ID as IDS,c.ALL_IN_SUPPLIER,d.NUM_REMARKS,d.MATERIAL_QUALITY,d.MATERIAL_NAME,d.IMAGES,c.OK_INSOURCE_TEMS,c.OK_OUTSOURCE_TEMS,c.OK_INSOURCE_TEM,c.ALL_QUALITY_PERSON,c.ALL_REMARKS,c.NG_NUM_TEM_APPROVAL,c.OK_NUM_TEM_APPROVAL,c.TESTING_NUM_TEM,c.OK_OUTSOURCE_TEM,d.ID,d.PROJECT_NUM,d.SPECIFICATION_AND_MODEL,c.STATION_NAME,c.PLAN_NUM,c.COMPLETE_NUM,c.USEABLE_NUM,c.OUTSOURCE_OUT_NUM,c.OUTSOURCE_IN_NUM,c.NG_NUM,c.REWORK_NUM,c.TESTING_NUM,c.PROCESS_ID,c.STATUS_FLAGS,c.NG_NUM_TEM,d.STATUS_FLAGS as TASK_STATUS,c.REMARKS,c.QUALITY_PERSON,c.STATION_TYPES from (select b.ID as IDX,a.SPLIT_FLAGS,a.ALL_OUT_SUPPLIER,a.ID,a.ALL_IN_SUPPLIER,a.OK_INSOURCE_TEMS,a.OK_OUTSOURCE_TEMS,a.OK_INSOURCE_TEM,a.ALL_QUALITY_PERSON,a.ALL_REMARKS,a.NG_NUM_TEM_APPROVAL,a.OK_NUM_TEM_APPROVAL,a.PROCESS_ID,a.TASK_ID,b.STATION_NAME,a.PLAN_NUM,a.COMPLETE_NUM,a.USEABLE_NUM,a.OUTSOURCE_OUT_NUM,a.OUTSOURCE_IN_NUM,a.NG_NUM,a.REWORK_NUM,a.TESTING_NUM,a.STATUS_FLAGS,a.NG_NUM_TEM,a.REMARKS,a.QUALITY_PERSON,b.STATION_TYPES,a.OK_OUTSOURCE_TEM,a.TESTING_NUM_TEM,a.PROCESS_ORDER from c_process_details_t a left join c_mes_process_station_t b on a.PROCESS_ID = b.ID ) c left join c_process_production_task d on c.TASK_ID=d.ID WHERE c.TESTING_NUM>0  and c.STATION_TYPES!=3 )x left join c_mes_process_production_way_t y on x.IDX=y.ST_ID and x.ROUTE_NAME=y.ROUTING_ID  where 1=1"
//			+ "<if test=\"statusFlags!=1\">and x.TESTING_NUM_TEM>0</if>"
//			+ "<if test=\"statusFlags==1\">and (x.NG_NUM_TEM_APPROVAL>0 or x.OK_NUM_TEM_APPROVAL>0)</if>"
//			+ "<if test=\"stationNum!=''\"> and x.STATION_NUM like '%${stationNum}%'</if>"
//			+ "<if test=\"projectNum!=''\"> and x.PROJECT_NUM like '%${projectNum}%'</if>"
//			+ "<if test=\"specificationModel!=''\"> and x.SPECIFICATION_AND_MODEL like '%${specificationModel}%'</if>"
//			+ "<if test=\"materiCode!=''\"> and x.MATERIAL_CODE like '%${materiCode}%'</if>  order by PROCESS_ORDER desc)"
//			+ "UNION all(select PROCESS_ORDER from (select d.ROUTE_NAME,c.IDX,c.PROCESS_ORDER,d.STATION_NUM,d.MATERIAL_CODE,c.SPLIT_FLAGS,c.ALL_OUT_SUPPLIER,c.ID as IDS,c.ALL_IN_SUPPLIER,d.NUM_REMARKS,d.MATERIAL_QUALITY,d.MATERIAL_NAME,d.IMAGES,c.OK_INSOURCE_TEMS,c.OK_OUTSOURCE_TEMS,c.OK_INSOURCE_TEM,c.ALL_QUALITY_PERSON,c.ALL_REMARKS,c.NG_NUM_TEM_APPROVAL,c.OK_NUM_TEM_APPROVAL,c.TESTING_NUM_TEM,c.OK_OUTSOURCE_TEM,d.ID,d.PROJECT_NUM,d.SPECIFICATION_AND_MODEL,c.STATION_NAME,c.PLAN_NUM,c.COMPLETE_NUM,c.USEABLE_NUM,c.OUTSOURCE_OUT_NUM,c.OUTSOURCE_IN_NUM,c.NG_NUM,c.REWORK_NUM,c.TESTING_NUM,c.PROCESS_ID,c.STATUS_FLAGS,c.NG_NUM_TEM,d.STATUS_FLAGS as TASK_STATUS,c.REMARKS,c.QUALITY_PERSON,c.STATION_TYPES from (select b.ID as IDX,a.SPLIT_FLAGS,a.ALL_OUT_SUPPLIER,a.ID,a.ALL_IN_SUPPLIER,a.OK_INSOURCE_TEMS,a.OK_OUTSOURCE_TEMS,a.OK_INSOURCE_TEM,a.ALL_QUALITY_PERSON,a.ALL_REMARKS,a.NG_NUM_TEM_APPROVAL,a.OK_NUM_TEM_APPROVAL,a.PROCESS_ID,a.TASK_ID,b.STATION_NAME,a.PLAN_NUM,a.COMPLETE_NUM,a.USEABLE_NUM,a.OUTSOURCE_OUT_NUM,a.OUTSOURCE_IN_NUM,a.NG_NUM,a.REWORK_NUM,a.TESTING_NUM,a.STATUS_FLAGS,a.NG_NUM_TEM,a.REMARKS,a.QUALITY_PERSON,b.STATION_TYPES,a.OK_OUTSOURCE_TEM,a.TESTING_NUM_TEM,a.PROCESS_ORDER from c_process_details_t a left join c_mes_process_station_t b on a.PROCESS_ID = b.ID ) c left join c_process_production_task d on c.TASK_ID=d.ID WHERE c.OUTSOURCE_OUT_NUM>0  and c.STATION_TYPES=3  )x left join c_mes_process_production_way_t y on x.IDX=y.ST_ID and x.ROUTE_NAME=y.ROUTING_ID where 1=1"
//			+ "<if test=\"statusFlags!=1\">and x.OK_INSOURCE_TEMS>0</if> <if test=\"statusFlags==1\">and (x.NG_NUM_TEM_APPROVAL>0 or x.OK_NUM_TEM_APPROVAL>0)</if>"
//			+ "<if test=\"stationNum!=''\"> and x.STATION_NUM like '%${stationNum}%'</if>"
//			+ "<if test=\"projectNum!=''\"> and x.PROJECT_NUM like '%${projectNum}%'</if>"
//			+ "<if test=\"specificationModel!=''\"> and x.SPECIFICATION_AND_MODEL like '%${specificationModel}%'</if>"
//			+ "<if test=\"materiCode!=''\"> and x.MATERIAL_CODE like '%${materiCode}%'</if> order by PROCESS_ORDER desc) order by  PROCESS_ORDER desc"
//			+ "</script>")
	@Select("<script>(select  y.DT,y.ST_ID, y.ROUTING_ID ,y.SERIAL_NO,y.PROCESS_REMARKS ,y.RUN_TIMES ,x.IDX, x.ROUTE_NAME, x.PROCESS_ORDER, x.STATION_NUM, x.MATERIAL_CODE, x.SPLIT_FLAGS, x.ALL_OUT_SUPPLIER, x.IDS, x.ALL_IN_SUPPLIER, x.NUM_REMARKS, x.MATERIAL_QUALITY, x.MATERIAL_NAME, x.IMAGES, x.OK_INSOURCE_TEMS, x.OK_OUTSOURCE_TEMS, x.OK_INSOURCE_TEM, x.ALL_QUALITY_PERSON, x.ALL_REMARKS, x.NG_NUM_TEM_APPROVAL, x.OK_NUM_TEM_APPROVAL, x.TESTING_NUM_TEM, x.OK_OUTSOURCE_TEM, x.ID, x.PROJECT_NUM, x.SPECIFICATION_AND_MODEL, x.STATION_NAME, x.PLAN_NUM, x.COMPLETE_NUM, x.USEABLE_NUM, x.OUTSOURCE_OUT_NUM, x.OUTSOURCE_IN_NUM, x.NG_NUM, x.REWORK_NUM, x.TESTING_NUM, x.PROCESS_ID, x.STATUS_FLAGS, x.NG_NUM_TEM, x.TASK_STATUS, x.REMARKS, x.QUALITY_PERSON, x.STATION_TYPES   from (select c.IDX,d.ROUTE_NAME,c.PROCESS_ORDER,d.STATION_NUM,d.MATERIAL_CODE,c.SPLIT_FLAGS,c.ALL_OUT_SUPPLIER,c.ID as IDS,c.ALL_IN_SUPPLIER,d.NUM_REMARKS,d.MATERIAL_QUALITY,d.MATERIAL_NAME,d.IMAGES,c.OK_INSOURCE_TEMS,c.OK_OUTSOURCE_TEMS,c.OK_INSOURCE_TEM,c.ALL_QUALITY_PERSON,c.ALL_REMARKS,c.NG_NUM_TEM_APPROVAL,c.OK_NUM_TEM_APPROVAL,c.TESTING_NUM_TEM,c.OK_OUTSOURCE_TEM,d.ID,d.PROJECT_NUM,d.SPECIFICATION_AND_MODEL,c.STATION_NAME,c.PLAN_NUM,c.COMPLETE_NUM,c.USEABLE_NUM,c.OUTSOURCE_OUT_NUM,c.OUTSOURCE_IN_NUM,c.NG_NUM,c.REWORK_NUM,c.TESTING_NUM,c.PROCESS_ID,c.STATUS_FLAGS,c.NG_NUM_TEM,d.STATUS_FLAGS as TASK_STATUS,c.REMARKS,c.QUALITY_PERSON,c.STATION_TYPES from (select b.ID as IDX,a.SPLIT_FLAGS,a.ALL_OUT_SUPPLIER,a.ID,a.ALL_IN_SUPPLIER,a.OK_INSOURCE_TEMS,a.OK_OUTSOURCE_TEMS,a.OK_INSOURCE_TEM,a.ALL_QUALITY_PERSON,a.ALL_REMARKS,a.NG_NUM_TEM_APPROVAL,a.OK_NUM_TEM_APPROVAL,a.PROCESS_ID,a.TASK_ID,b.STATION_NAME,a.PLAN_NUM,a.COMPLETE_NUM,a.USEABLE_NUM,a.OUTSOURCE_OUT_NUM,a.OUTSOURCE_IN_NUM,a.NG_NUM,a.REWORK_NUM,a.TESTING_NUM,a.STATUS_FLAGS,a.NG_NUM_TEM,a.REMARKS,a.QUALITY_PERSON,b.STATION_TYPES,a.OK_OUTSOURCE_TEM,a.TESTING_NUM_TEM,a.PROCESS_ORDER from c_process_details_t a left join c_mes_process_station_t b on a.PROCESS_ID = b.ID ) c left join c_process_production_task d on c.TASK_ID=d.ID WHERE c.TESTING_NUM>0  and c.STATION_TYPES!=3 )x left join c_mes_process_production_way_t y on x.IDX=y.ST_ID and x.ROUTE_NAME=y.ROUTING_ID  where 1=1"
		+ "<if test=\"statusFlags!=1\">and x.TESTING_NUM_TEM>0</if>"
		+ "<if test=\"statusFlags==1\">and (x.NG_NUM_TEM_APPROVAL>0 or x.OK_NUM_TEM_APPROVAL>0)</if>"
		+ "<if test=\"stationNum!=''\"> and x.STATION_NUM like '%${stationNum}%'</if>"
		+ "<if test=\"projectNum!=''\"> and x.PROJECT_NUM like '%${projectNum}%'</if>"
		+ "<if test=\"specificationModel!=''\"> and x.SPECIFICATION_AND_MODEL like '%${specificationModel}%'</if>"
		+ "<if test=\"processName!=''\"> and x.STATION_NAME like '%${processName}%'</if>"
		+ "<if test=\"materiCode!=''\"> and x.MATERIAL_CODE like '%${materiCode}%'</if>  order by PROCESS_ORDER desc)"
		+ "UNION all(select y.DT,y.ST_ID, y.ROUTING_ID ,y.SERIAL_NO,y.PROCESS_REMARKS ,y.RUN_TIMES ,x.IDX, x.ROUTE_NAME, x.PROCESS_ORDER, x.STATION_NUM, x.MATERIAL_CODE, x.SPLIT_FLAGS, x.ALL_OUT_SUPPLIER, x.IDS, x.ALL_IN_SUPPLIER, x.NUM_REMARKS, x.MATERIAL_QUALITY, x.MATERIAL_NAME, x.IMAGES, x.OK_INSOURCE_TEMS, x.OK_OUTSOURCE_TEMS, x.OK_INSOURCE_TEM, x.ALL_QUALITY_PERSON, x.ALL_REMARKS, x.NG_NUM_TEM_APPROVAL, x.OK_NUM_TEM_APPROVAL, x.TESTING_NUM_TEM, x.OK_OUTSOURCE_TEM, x.ID, x.PROJECT_NUM, x.SPECIFICATION_AND_MODEL, x.STATION_NAME, x.PLAN_NUM, x.COMPLETE_NUM, x.USEABLE_NUM, x.OUTSOURCE_OUT_NUM, x.OUTSOURCE_IN_NUM, x.NG_NUM, x.REWORK_NUM, x.TESTING_NUM, x.PROCESS_ID, x.STATUS_FLAGS, x.NG_NUM_TEM, x.TASK_STATUS, x.REMARKS, x.QUALITY_PERSON, x.STATION_TYPES from (select d.ROUTE_NAME,c.IDX,c.PROCESS_ORDER,d.STATION_NUM,d.MATERIAL_CODE,c.SPLIT_FLAGS,c.ALL_OUT_SUPPLIER,c.ID as IDS,c.ALL_IN_SUPPLIER,d.NUM_REMARKS,d.MATERIAL_QUALITY,d.MATERIAL_NAME,d.IMAGES,c.OK_INSOURCE_TEMS,c.OK_OUTSOURCE_TEMS,c.OK_INSOURCE_TEM,c.ALL_QUALITY_PERSON,c.ALL_REMARKS,c.NG_NUM_TEM_APPROVAL,c.OK_NUM_TEM_APPROVAL,c.TESTING_NUM_TEM,c.OK_OUTSOURCE_TEM,d.ID,d.PROJECT_NUM,d.SPECIFICATION_AND_MODEL,c.STATION_NAME,c.PLAN_NUM,c.COMPLETE_NUM,c.USEABLE_NUM,c.OUTSOURCE_OUT_NUM,c.OUTSOURCE_IN_NUM,c.NG_NUM,c.REWORK_NUM,c.TESTING_NUM,c.PROCESS_ID,c.STATUS_FLAGS,c.NG_NUM_TEM,d.STATUS_FLAGS as TASK_STATUS,c.REMARKS,c.QUALITY_PERSON,c.STATION_TYPES from (select b.ID as IDX,a.SPLIT_FLAGS,a.ALL_OUT_SUPPLIER,a.ID,a.ALL_IN_SUPPLIER,a.OK_INSOURCE_TEMS,a.OK_OUTSOURCE_TEMS,a.OK_INSOURCE_TEM,a.ALL_QUALITY_PERSON,a.ALL_REMARKS,a.NG_NUM_TEM_APPROVAL,a.OK_NUM_TEM_APPROVAL,a.PROCESS_ID,a.TASK_ID,b.STATION_NAME,a.PLAN_NUM,a.COMPLETE_NUM,a.USEABLE_NUM,a.OUTSOURCE_OUT_NUM,a.OUTSOURCE_IN_NUM,a.NG_NUM,a.REWORK_NUM,a.TESTING_NUM,a.STATUS_FLAGS,a.NG_NUM_TEM,a.REMARKS,a.QUALITY_PERSON,b.STATION_TYPES,a.OK_OUTSOURCE_TEM,a.TESTING_NUM_TEM,a.PROCESS_ORDER from c_process_details_t a left join c_mes_process_station_t b on a.PROCESS_ID = b.ID ) c left join c_process_production_task d on c.TASK_ID=d.ID WHERE c.OUTSOURCE_OUT_NUM>0  and c.STATION_TYPES=3  )x left join c_mes_process_production_way_t y on x.IDX=y.ST_ID and x.ROUTE_NAME=y.ROUTING_ID where 1=1"
		+ "<if test=\"statusFlags!=1\">and x.OK_INSOURCE_TEMS>0</if> <if test=\"statusFlags==1\">and (x.NG_NUM_TEM_APPROVAL>0 or x.OK_NUM_TEM_APPROVAL>0)</if>"
		+ "<if test=\"stationNum!=''\"> and x.STATION_NUM like '%${stationNum}%'</if>"
		+ "<if test=\"projectNum!=''\"> and x.PROJECT_NUM like '%${projectNum}%'</if>"
		+ "<if test=\"specificationModel!=''\"> and x.SPECIFICATION_AND_MODEL like '%${specificationModel}%'</if>"
		+ "<if test=\"processName!=''\"> and x.STATION_NAME like '%${processName}%'</if>"
		+ "<if test=\"materiCode!=''\"> and x.MATERIAL_CODE like '%${materiCode}%'</if> order by PROCESS_ORDER desc) order by  PROCESS_ORDER desc"
		+ "</script>")
	public List<Map<String,Object>> showQualityInfoList(@Param("statusFlags")String statusFlags,@Param("stationNum")String stationNum,@Param("projectNum")String projectNum,@Param("specificationModel")String specificationModel,@Param("materiCode")String materiCode,@Param("processName")String processName);

//	品检转审核(新版)
	@Update("<script>update c_process_details_t set EDL_FLAGS=1,USE_REWORK_NUM=#{reworkNums},USE_REWORK_NUM_TEM=#{reworkNums},<if test=\"testNum!='' and testNum!=null\">TESTING_NUM=#{testNum},</if> <if test=\"testNumTem!='' and testNumTem!=null\">TESTING_NUM_TEM=#{testNumTem},</if><if test=\"outsourceTem!='' and outsourceTem!=null and flagsx==1\">OK_OUTSOURCE_TEMS=#{outsourceTem},</if><if test=\"outsourceTem!='' and outsourceTem!=null and flagsx==0\">OK_OUTSOURCE_TEM=#{outsourceTem},</if><if test=\"qualityPerson!=null and qualityPerson!=''\">QUALITY_PERSON=#{qualityPerson},</if><if test=\"remarks!=null and remarks!=''\">REMARKS=#{remarks},</if>STATUS_FLAGS=#{flags}<if test=\"NGNum!=null and NGNum!=''\">,NG_NUM_TEM=#{NGNum}</if> where TASK_ID=#{taskId} and PROCESS_ID=#{peocessId} and ID=#{id}</script>")
	public Integer updateQualityApprovalList(@Param("taskId")String taskId,@Param("peocessId")String peocessId,@Param("NGNum")String NGNum,@Param("flags")String flags,@Param("remarks")String remarks,@Param("qualityPerson")String qualityPerson,@Param("outsourceTem")String outsourceTem,@Param("testNum")String testNum,@Param("testNumTem")String testNumTem,@Param("flagsx")String flagsx,@Param("reworkNums")String reworkNums,@Param("id")String id);

//	品检审核2.0(非委外)
	@Update("update c_process_details_t set STATUS_FLAGS=#{flags},TESTING_NUM_TEM=#{testNumTem},OK_OUTSOURCE_TEM=#{testNumTem},OK_NUM_TEM_APPROVAL=#{okNumTemApproval},NG_NUM_TEM_APPROVAL=#{ngNumTemApproval},ALL_REMARKS=#{remarks},REMARKS='',ALL_QUALITY_PERSON=#{person} where TASK_ID=#{taskId} and PROCESS_ID=#{processId} and ID=#{ids}")
	public Integer updateQualityApprovalLists(@Param("flags")String flags,@Param("testNumTem")String testNumTem,@Param("okNumTemApproval")String okNumTemApproval,@Param("ngNumTemApproval")String ngNumTemApproval,@Param("taskId")String taskId,@Param("processId")String processId,@Param("remarks")String remarks,@Param("person")String person,@Param("ids")String ids);

//拆分数据的更新(非委外)
	@Update("update c_process_details_t set TESTING_NUM_TEM=#{testNumTem},OK_OUTSOURCE_TEM=#{testNumTem} where  TASK_ID=#{taskId} and PROCESS_ID=#{processId}")
	public Integer updateSplitQualityData(@Param("taskId")String taskId,@Param("processId")String processId,@Param("testNumTem")String testNumTem);

//	品检审核2.0(委外)
	@Update("update c_process_details_t set STATUS_FLAGS=#{flags},OK_INSOURCE_TEMS=#{testNumTem},OK_OUTSOURCE_TEMS=#{testNumTem},OK_NUM_TEM_APPROVAL=#{okNumTemApproval},NG_NUM_TEM_APPROVAL=#{ngNumTemApproval},ALL_REMARKS=#{remarks},REMARKS='',ALL_QUALITY_PERSON=#{person} where TASK_ID=#{taskId} and PROCESS_ID=#{processId} and ID=#{ids}")
	public Integer updateQualityInSourceApprovalLists(@Param("flags")String flags,@Param("testNumTem")String testNumTem,@Param("okNumTemApproval")String okNumTemApproval,@Param("ngNumTemApproval")String ngNumTemApproval,@Param("taskId")String taskId,@Param("processId")String processId,@Param("remarks")String remarks,@Param("person")String person,@Param("ids")String ids);
//	更新任务单数据(新版)
	@Update("update c_process_details_t set OUTSOURCE_OUT_NUM=#{outsource},OK_OUTSOURCE_TEM=#{outsource},ALL_REMARKS=#{allRemarks},REMARKS=#{remarks},TESTING_NUM=#{testingNum},USEABLE_NUM=#{OKNum},OK_INSOURCE_TEM=#{OKNum},FINISH_PRODUCT_TEM=#{OKNum},NG_NUM=#{NGNum},PERSON=#{person}  where TASK_ID=#{taskId} and PROCESS_ID=#{peocessId} ")
	public Integer updateQualityApprovalData(@Param("testingNum")String testingNum,@Param("OKNum")String OKNum,@Param("NGNum")String NGNum,@Param("taskId")String taskId,@Param("peocessId")String peocessId,@Param("person")String person,@Param("remarks")String remarks,@Param("allRemarks")String allRemarks,@Param("outsource")String outsource);

//	获取指定的数据
//	@Select("select d.ID,d.PROJECT_NUM,d.SPECIFICATION_AND_MODEL,c.STATION_NAME,c.PLAN_NUM,c.COMPLETE_NUM,c.USEABLE_NUM,c.OUTSOURCE_OUT_NUM,c.OUTSOURCE_IN_NUM,c.NG_NUM,c.REWORK_NUM,c.TESTING_NUM,c.PROCESS_ID,c.STATUS_FLAGS,c.NG_NUM_TEM,d.STATUS_FLAGS as TASK_STATUS,c.ALL_REMARKS,c.REMARKS,c.PLAN_NUM,c.COMPLETE_NUM from (select a.PROCESS_ID,a.TASK_ID,b.STATION_NAME,a.PLAN_NUM,a.COMPLETE_NUM,a.USEABLE_NUM,a.OUTSOURCE_OUT_NUM,a.OUTSOURCE_IN_NUM,a.NG_NUM,a.REWORK_NUM,a.TESTING_NUM,a.STATUS_FLAGS,a.NG_NUM_TEM,a.ALL_REMARKS,a.REMARKS from c_process_details_t a left join c_mes_process_station_t b on a.PROCESS_ID = b.ID ) c left join c_process_production_task d on c.TASK_ID=d.ID WHERE c.TESTING_NUM>0 and c.STATUS_FLAGS=#{statusFlags}  and d.ID=#{id} and c.PROCESS_ID=#{processId}")
//	public Map<String,Object> showAllQualityApprovasList(@Param("statusFlags")String statusFlags,@Param("id")String id,@Param("processId")String processId);
	@Select("(select c.ID as IDS,c.INSOURCE_IN_TEMS_APPROVAL,c.OK_FINISH_PRODUCTION_TEMS_APPROVAL,c.OK_INSOURCE_TEMS,c.OK_INSOURCE_TEM,c.ALL_QUALITY_PERSON,c.QUALITY_PERSON,c.OK_NUM_TEM_APPROVAL,c.NG_NUM_TEM_APPROVAL,c.TESTING_NUM_TEM,c.OK_OUTSOURCE_TEM,d.ID,d.PROJECT_NUM,d.SPECIFICATION_AND_MODEL,c.STATION_NAME,c.PLAN_NUM,c.COMPLETE_NUM,c.USEABLE_NUM,c.OUTSOURCE_OUT_NUM,c.OUTSOURCE_IN_NUM,c.NG_NUM,c.REWORK_NUM,c.TESTING_NUM,c.PROCESS_ID,c.STATUS_FLAGS,c.NG_NUM_TEM,d.STATUS_FLAGS as TASK_STATUS,c.ALL_REMARKS,c.REMARKS,c.PLAN_NUM,c.COMPLETE_NUM,c.STATION_TYPES from (select a.ID,a.INSOURCE_IN_TEMS_APPROVAL,a.OK_FINISH_PRODUCTION_TEMS_APPROVAL,a.OK_INSOURCE_TEMS,a.OK_INSOURCE_TEM,a.ALL_QUALITY_PERSON,a.QUALITY_PERSON,a.OK_NUM_TEM_APPROVAL,a.NG_NUM_TEM_APPROVAL,a.TESTING_NUM_TEM,a.PROCESS_ID,a.TASK_ID,b.STATION_NAME,a.PLAN_NUM,a.COMPLETE_NUM,a.USEABLE_NUM,a.OUTSOURCE_OUT_NUM,a.OUTSOURCE_IN_NUM,a.NG_NUM,a.REWORK_NUM,a.TESTING_NUM,a.STATUS_FLAGS,a.NG_NUM_TEM,a.ALL_REMARKS,a.REMARKS,b.STATION_TYPES,a.OK_OUTSOURCE_TEM from c_process_details_t a left join c_mes_process_station_t b on a.PROCESS_ID = b.ID ) c left join c_process_production_task d on c.TASK_ID=d.ID WHERE c.TESTING_NUM>0 and c.STATUS_FLAGS=#{statusFlags}  and d.ID=#{id} and c.PROCESS_ID=#{processId} and c.ID=#{ids})"
			+ "union(select  c.ID as IDS,c.INSOURCE_IN_TEMS_APPROVAL,c.OK_FINISH_PRODUCTION_TEMS_APPROVAL,c.OK_INSOURCE_TEMS,c.OK_INSOURCE_TEM,c.ALL_QUALITY_PERSON,c.QUALITY_PERSON,c.OK_NUM_TEM_APPROVAL,c.NG_NUM_TEM_APPROVAL,c.TESTING_NUM_TEM,c.OK_OUTSOURCE_TEM,d.ID,d.PROJECT_NUM,d.SPECIFICATION_AND_MODEL,c.STATION_NAME,c.PLAN_NUM,c.COMPLETE_NUM,c.USEABLE_NUM,c.OUTSOURCE_OUT_NUM,c.OUTSOURCE_IN_NUM,c.NG_NUM,c.REWORK_NUM,c.TESTING_NUM,c.PROCESS_ID,c.STATUS_FLAGS,c.NG_NUM_TEM,d.STATUS_FLAGS as TASK_STATUS,c.ALL_REMARKS,c.REMARKS,c.PLAN_NUM,c.COMPLETE_NUM,c.STATION_TYPES from (select a.ID,a.INSOURCE_IN_TEMS_APPROVAL,a.OK_FINISH_PRODUCTION_TEMS_APPROVAL,a.OK_INSOURCE_TEMS,a.OK_INSOURCE_TEM,a.ALL_QUALITY_PERSON,a.QUALITY_PERSON,a.OK_NUM_TEM_APPROVAL,a.NG_NUM_TEM_APPROVAL,a.TESTING_NUM_TEM,a.PROCESS_ID,a.TASK_ID,b.STATION_NAME,a.PLAN_NUM,a.COMPLETE_NUM,a.USEABLE_NUM,a.OUTSOURCE_OUT_NUM,a.OUTSOURCE_IN_NUM,a.NG_NUM,a.REWORK_NUM,a.TESTING_NUM,a.STATUS_FLAGS,a.NG_NUM_TEM,a.ALL_REMARKS,a.REMARKS,b.STATION_TYPES,a.OK_OUTSOURCE_TEM from c_process_details_t a left join c_mes_process_station_t b on a.PROCESS_ID = b.ID ) "
			+ "c left join c_process_production_task d on c.TASK_ID=d.ID WHERE c.OUTSOURCE_OUT_NUM>0 and c.STATUS_FLAGS=#{statusFlags}  and d.ID=#{id} and c.PROCESS_ID=#{processId} and c.ID=#{ids})")
	public Map<String,Object> showAllQualityApprovasList(@Param("statusFlags")String statusFlags,@Param("id")String id,@Param("processId")String processId,@Param("ids")String ids);


//	获取拆分的所有数据（3.0）
	@Select("(select c.ID as IDS,c.INSOURCE_IN_TEMS_APPROVAL,c.OK_FINISH_PRODUCTION_TEMS_APPROVAL,c.OK_INSOURCE_TEMS,c.OK_INSOURCE_TEM,c.ALL_QUALITY_PERSON,c.QUALITY_PERSON,c.OK_NUM_TEM_APPROVAL,c.NG_NUM_TEM_APPROVAL,c.TESTING_NUM_TEM,c.OK_OUTSOURCE_TEM,d.ID,d.PROJECT_NUM,d.SPECIFICATION_AND_MODEL,c.STATION_NAME,c.PLAN_NUM,c.COMPLETE_NUM,c.USEABLE_NUM,c.OUTSOURCE_OUT_NUM,c.OUTSOURCE_IN_NUM,c.NG_NUM,c.REWORK_NUM,c.TESTING_NUM,c.PROCESS_ID,c.STATUS_FLAGS,c.NG_NUM_TEM,d.STATUS_FLAGS as TASK_STATUS,c.ALL_REMARKS,c.REMARKS,c.PLAN_NUM,c.COMPLETE_NUM,c.STATION_TYPES from (select a.ID,a.INSOURCE_IN_TEMS_APPROVAL,a.OK_FINISH_PRODUCTION_TEMS_APPROVAL,a.OK_INSOURCE_TEMS,a.OK_INSOURCE_TEM,a.ALL_QUALITY_PERSON,a.QUALITY_PERSON,a.OK_NUM_TEM_APPROVAL,a.NG_NUM_TEM_APPROVAL,a.TESTING_NUM_TEM,a.PROCESS_ID,a.TASK_ID,b.STATION_NAME,a.PLAN_NUM,a.COMPLETE_NUM,a.USEABLE_NUM,a.OUTSOURCE_OUT_NUM,a.OUTSOURCE_IN_NUM,a.NG_NUM,a.REWORK_NUM,a.TESTING_NUM,a.STATUS_FLAGS,a.NG_NUM_TEM,a.ALL_REMARKS,a.REMARKS,b.STATION_TYPES,a.OK_OUTSOURCE_TEM from c_process_details_t a left join c_mes_process_station_t b on a.PROCESS_ID = b.ID ) c left join c_process_production_task d on c.TASK_ID=d.ID WHERE c.TESTING_NUM>0  and d.ID=#{id} and c.PROCESS_ID=#{processId} )"
			+ "union(select  c.ID as IDS,c.INSOURCE_IN_TEMS_APPROVAL,c.OK_FINISH_PRODUCTION_TEMS_APPROVAL,c.OK_INSOURCE_TEMS,c.OK_INSOURCE_TEM,c.ALL_QUALITY_PERSON,c.QUALITY_PERSON,c.OK_NUM_TEM_APPROVAL,c.NG_NUM_TEM_APPROVAL,c.TESTING_NUM_TEM,c.OK_OUTSOURCE_TEM,d.ID,d.PROJECT_NUM,d.SPECIFICATION_AND_MODEL,c.STATION_NAME,c.PLAN_NUM,c.COMPLETE_NUM,c.USEABLE_NUM,c.OUTSOURCE_OUT_NUM,c.OUTSOURCE_IN_NUM,c.NG_NUM,c.REWORK_NUM,c.TESTING_NUM,c.PROCESS_ID,c.STATUS_FLAGS,c.NG_NUM_TEM,d.STATUS_FLAGS as TASK_STATUS,c.ALL_REMARKS,c.REMARKS,c.PLAN_NUM,c.COMPLETE_NUM,c.STATION_TYPES from (select a.ID,a.INSOURCE_IN_TEMS_APPROVAL,a.OK_FINISH_PRODUCTION_TEMS_APPROVAL,a.OK_INSOURCE_TEMS,a.OK_INSOURCE_TEM,a.ALL_QUALITY_PERSON,a.QUALITY_PERSON,a.OK_NUM_TEM_APPROVAL,a.NG_NUM_TEM_APPROVAL,a.TESTING_NUM_TEM,a.PROCESS_ID,a.TASK_ID,b.STATION_NAME,a.PLAN_NUM,a.COMPLETE_NUM,a.USEABLE_NUM,a.OUTSOURCE_OUT_NUM,a.OUTSOURCE_IN_NUM,a.NG_NUM,a.REWORK_NUM,a.TESTING_NUM,a.STATUS_FLAGS,a.NG_NUM_TEM,a.ALL_REMARKS,a.REMARKS,b.STATION_TYPES,a.OK_OUTSOURCE_TEM from c_process_details_t a left join c_mes_process_station_t b on a.PROCESS_ID = b.ID ) "
			+ "c left join c_process_production_task d on c.TASK_ID=d.ID WHERE c.OUTSOURCE_OUT_NUM>0   and d.ID=#{id} and c.PROCESS_ID=#{processId})")
	public List<Map<String,Object>> showAllSplitQualityApprovasList(@Param("id")String id,@Param("processId")String processId);

//	查找NG的数量
	@Select("select NG_NUM from c_process_production_task where ID=#{id}")
	public Integer showNGNum(@Param("id")String id);

//	更新TASK的NG数量
	@Update("update c_process_production_task set NG_NUM=#{NGNum}  where ID=#{id}")
	public Integer updateTaskNGNum(@Param("id")String id,@Param("NGNum")Integer NGNum);

//	更新临时NG数量
	@Update("update c_process_details_t set NG_NUM_TEM=0 where TASK_ID=#{taskId} and PROCESS_ID=#{peocessId}")
	public Integer updateNGNumTem(@Param("taskId")String taskId,@Param("peocessId") String peocessId);

//	查询是否是首站
	@Select("select PROCESS_ORDER from c_process_details_t where TASK_ID=#{taskId} and PROCESS_ID=#{processId}")
	public Integer showOrder(@Param("taskId")String taskId,@Param("processId")String processId);

//	撤销审批
	@Update("update c_process_details_t set TESTING_NUM=#{testNum},TESTING_NUM_TEM=#{testNum},OK_OUTSOURCE_TEM=#{outRouce},STATUS_FLAGS=0,NG_NUM_TEM=0,REMARKS='' where TASK_ID=#{taskId} and PROCESS_ID=#{processId}")
	public Integer revokeApproval(@Param("taskId")String taskId,@Param("processId")String processId,@Param("outRouce")Integer outRouce,@Param("testNum")Integer testNum);

//	更改入库品检批量数据
	@Update("update c_process_details_t set OK_FINISH_PRODUCTION_TEMS_APPROVAL=#{okFinishProductionTems},OK_FINISH_PRODUCTION_TEMS=#{okOutsourceTem},FINISH_PRODUCT_TEM=#{okOutsourceTem},STATUS_FLAGS=6,QUALITY_PERSON=#{person},REMARKS=#{remarks} where TASK_ID=#{taskId} and PROCESS_ID=#{processId}")//OK_OUTSOURCE_TEM=#{okOutsourceTem},
	public Integer finishProductApproval(@Param("finishProduct")String finishProduct,@Param("taskId")String taskId,@Param("processId")String processId,@Param("person")String person,@Param("remarks")String remarks,@Param("okOutsourceTem")Integer okOutsourceTem,@Param("okFinishProductionTems")Integer okFinishProductionTems);

//	展示入库详情
	@Select("<script>select d.STATION_NUM,d.MATERIAL_CODE,d.MATERIAL_NAME,d.MATERIAL_QUALITY,d.NUM_REMARKS,d.IMAGES,c.OK_FINISH_PRODUCTION_TEMS_APPROVAL,c.OK_FINISH_PRODUCTION_TEMS,c.OK_OUTSOURCE_TEM,c.REMARKS,c.STATUS_FLAGS,c.QUALITY_PERSON,d.ID,d.PROJECT_NUM,d.SPECIFICATION_AND_MODEL,c.STATION_NAME,c.PLAN_NUM,c.COMPLETE_NUM,c.USEABLE_NUM,c.OUTSOURCE_OUT_NUM,c.OUTSOURCE_IN_NUM,c.NG_NUM,c.REWORK_NUM,c.TESTING_NUM,c.PROCESS_ID,c.FINISH_PRODUCT_TEM from (select a.OK_FINISH_PRODUCTION_TEMS_APPROVAL,a.OK_FINISH_PRODUCTION_TEMS,a.OK_OUTSOURCE_TEM,a.TASK_ID,b.STATION_NAME,a.PLAN_NUM,a.COMPLETE_NUM,a.USEABLE_NUM,a.OUTSOURCE_OUT_NUM,a.OUTSOURCE_IN_NUM,a.NG_NUM,a.REWORK_NUM,a.TESTING_NUM,a.PROCESS_ID,a.FINISH_PRODUCT_TEM,a.QUALITY_PERSON,a.STATUS_FLAGS,a.REMARKS from c_process_details_t a left join c_mes_process_station_t b on a.PROCESS_ID = b.ID where b.STATION_TYPES=4) c left join c_process_production_task d on c.TASK_ID=d.ID WHERE c.OK_FINISH_PRODUCTION_TEMS>0 "
			+ "<if test=\"stationNum!=''\"> and d.STATION_NUM like '%${stationNum}%'</if>"
			+ "<if test=\"projectNum!=''\"> and d.PROJECT_NUM like '%${projectNum}%'</if>"
			+ "<if test=\"specificationModel!=''\"> and d.SPECIFICATION_AND_MODEL like '%${specificationModel}%'</if>"
			+ "<if test=\"materiCode!=''\"> and d.MATERIAL_CODE like '%${materiCode}%'</if></script>")//and c.STATUS_FLAGS=#{flags}
	public List<Map<String,Object>> showAllFinishProduct(@Param("flags")String flags,@Param("stationNum")String stationNum,@Param("materiCode")String materiCode,@Param("projectNum")String projectNum,@Param("specificationModel")String specificationModel);

	//展示入库详情
	@Select("<script>select d.STATION_NUM,d.MATERIAL_CODE,c.ID as IDS,d.MATERIAL_NAME,d.MATERIAL_QUALITY,d.NUM_REMARKS,d.IMAGES,c.OK_FINISH_PRODUCTION_TEMS_APPROVAL,c.OK_FINISH_PRODUCTION_TEMS,c.OK_OUTSOURCE_TEM,c.REMARKS,c.STATUS_FLAGS,c.QUALITY_PERSON,d.ID,d.PROJECT_NUM,d.SPECIFICATION_AND_MODEL,c.STATION_NAME,c.PLAN_NUM,c.COMPLETE_NUM,c.USEABLE_NUM,c.OUTSOURCE_OUT_NUM,c.OUTSOURCE_IN_NUM,c.NG_NUM,c.REWORK_NUM,c.TESTING_NUM,c.PROCESS_ID,c.FINISH_PRODUCT_TEM from (select a.ID,a.OK_FINISH_PRODUCTION_TEMS_APPROVAL,a.OK_FINISH_PRODUCTION_TEMS,a.OK_OUTSOURCE_TEM,a.TASK_ID,b.STATION_NAME,a.PLAN_NUM,a.COMPLETE_NUM,a.USEABLE_NUM,a.OUTSOURCE_OUT_NUM,a.OUTSOURCE_IN_NUM,a.NG_NUM,a.REWORK_NUM,a.TESTING_NUM,a.PROCESS_ID,a.FINISH_PRODUCT_TEM,a.QUALITY_PERSON,a.STATUS_FLAGS,a.REMARKS from c_process_details_t a "
			+ "left join c_mes_process_station_t b on a.PROCESS_ID = b.ID where b.STATION_TYPES=4) c left join c_process_production_task d on c.TASK_ID=d.ID WHERE c.OK_FINISH_PRODUCTION_TEMS_APPROVAL>0 "
			+ "<if test=\"stationNum!=''\"> and d.STATION_NUM like '%${stationNum}%'</if>"
			+ "<if test=\"projectNum!=''\"> and d.PROJECT_NUM like '%${projectNum}%'</if>"
			+ "<if test=\"specificationModel!=''\"> and d.SPECIFICATION_AND_MODEL like '%${specificationModel}%'</if>"
			+ "<if test=\"materiCode!=''\"> and d.MATERIAL_CODE like '%${materiCode}%'</if></script>")//and c.STATUS_FLAGS=#{flags}
	public List<Map<String,Object>> showAllFinishProductx(@Param("flags")String flags,@Param("stationNum")String stationNum,@Param("materiCode")String materiCode,@Param("projectNum")String projectNum,@Param("specificationModel")String specificationModel);

//	展示指定详情信息
	@Select("select * from c_process_details_t where TASK_ID=#{taskId} and PROCESS_ID=#{processId} and ID=#{id}")
	public Map<String,Object> showDetailsInfos(@Param("taskId")String taskId,@Param("processId")String processId,@Param("id")String id);

//	更新成品入库
	@Update("update c_process_details_t set OK_FINISH_PRODUCTION_TEMS_APPROVAL='0',COMPLETE_NUM=#{complete},USEABLE_NUM=#{useNum},FINISH_PRODUCT_TEM=#{useNum},STATUS_FLAGS=#{flags} where TASK_ID=#{taskId} and PROCESS_ID=#{processId}")
	public Integer updateDetailsInfo(@Param("complete")String complete,@Param("useNum")String useNum,@Param("taskId")String taskId,@Param("processId")String processId,@Param("flags")String flags);

//	更新任务单数据
	@Update("<script>update c_process_production_task set COMPLETE_NUM=#{complete}<if test=\"flags!='' and flags!=null\">,STATUS_FLAGS=#{flags}</if> where ID=#{id}</script>")
	public Integer updateNewTaskInfos(@Param("complete") String complete,@Param("flags") String flags,@Param("id") String id);

//	撤销成品入库
	@Update("update c_process_details_t set OK_FINISH_PRODUCTION_TEMS_APPROVAL='0',OK_FINISH_PRODUCTION_TEMS=#{useName},FINISH_PRODUCT_TEM=#{useName},STATUS_FLAGS=0,REMARKS='' where TASK_ID=#{taskId} and PROCESS_ID=#{processId}")
	public Integer revokeFinishProductNum(@Param("useName")String useName,@Param("taskId")String taskId,@Param("processId")String processId);

//	更新委外发出申请
	@Update("update c_process_details_t set OK_INSOURCE_TEM_APPROVLA=#{okNum},OUT_SUPPLIER=#{supplier},ALL_OUT_SUPPLIER=#{supplier},OUT_PERSON=#{person},OUT_SUPPLIER='',STATUS_FLAGS=#{flags},TESTING_NUM_TEM=#{testNum},OK_INSOURCE_TEM=#{testNum} where TASK_ID=#{taskId} and PROCESS_ID=#{processId} and ID=#{ids}")
	public Integer updateOutSourceInfo(@Param("okNum")String testNum,@Param("supplier")String supplier,@Param("taskId")String taskId,@Param("processId")String processId,@Param("person")String person,@Param("flags")String flags,@Param("testNum")Integer testNumx,@Param("ids")String ids);

//	更新委外发出审批
	@Update("update c_process_details_t set OK_OUTSOURCE_TEMS=#{testNum},OUTSOURCE_OUT_NUM=#{outNumx},OK_INSOURCE_TEMS=#{testNum},OK_INSOURCE_TEM_APPROVLA=0,OUT_SUPPLIER='',OUT_PERSON='',STATUS_FLAGS=#{flags},OUT_PERSON_APPROVAL=#{person} where TASK_ID=#{taskId} and PROCESS_ID=#{processId} and ID=#{id} ")//OK_OUTSOURCE_TEM=#{testNum},
	public Integer updateOutSourceInfox(@Param("testNum")String testNum,@Param("testingNum")String testingNum,@Param("taskId")String taskId,@Param("processId")String processId,@Param("flags")String flags,@Param("person")String person,@Param("outNumx")Integer outNumx,@Param("id")String id);

	@Update("update c_process_details_t set TESTING_NUM=#{testNum},OK_OUTSOURCE_TEMS=#{testNum},OUTSOURCE_OUT_NUM=#{outNumx},OK_INSOURCE_TEMS=#{testNum},OK_INSOURCE_TEM_APPROVLA=0,OUT_SUPPLIER='',OUT_PERSON='',STATUS_FLAGS=#{flags},OUT_PERSON_APPROVAL=#{person} where TASK_ID=#{taskId} and PROCESS_ID=#{processId} and ID=#{id} and SPLIT_FLAGS=4")//OK_OUTSOURCE_TEM=#{testNum},
	public Integer updateOutChildSourceInfox(@Param("testNum")String testNum,@Param("testingNum")String testingNum,@Param("taskId")String taskId,@Param("processId")String processId,@Param("flags")String flags,@Param("person")String person,@Param("outNumx")Integer outNumx,@Param("id")String id);

//  撤销委外审批
	@Update("update c_process_details_t set ALL_OUT_SUPPLIER='',OUT_SUPPLIER='',OUT_PERSON='',STATUS_FLAGS=#{flags},TESTING_NUM_TEM=#{testNum},OK_INSOURCE_TEM=#{testNum},OK_INSOURCE_TEM_APPROVLA=0 where TASK_ID=#{taskId} and PROCESS_ID=#{processId} and ID=#{id}")
	public Integer revokeOutSource(@Param("testNum")String testNum,@Param("taskId")String taskId,@Param("processId")String processId,@Param("flags")String flags,@Param("id")String id);

//  更新委外申请信息
	@Update("update c_process_details_t set IN_SUPPLIER='',INSOURCE_IN_TEMS_APPROVAL=#{newOutSourceInNum},INSOURCE_IN_TEMS=#{okOutsourceTems},OK_OUTSOURCE_TEM=#{okOutsourceTems},ALL_IN_SUPPLIER=#{inSupplier},STATUS_FLAGS=#{flags},IN_PERSON=#{person} where TASK_ID=#{taskId} and PROCESS_ID=#{processId} and ID=#{id}")
	public Integer updateInSourceInfo(@Param("inSupplier")String inSupplier,@Param("okInsourceTem")String okInsourceTem,@Param("flags")String flags,@Param("taskId")String taskId,@Param("processId")String processId,@Param("person")String person,@Param("okOutsourceTems")Integer okOutsourceTems,@Param("newOutSourceInNum")Integer newOutSourceInNum,@Param("id")String id);

//	撤销委外接收申请
	@Update("update c_process_details_t set INSOURCE_IN_TEMS_APPROVAL='0',OK_OUTSOURCE_TEM=#{useNum},INSOURCE_IN_TEMS=#{useNum},STATUS_FLAGS='0',IN_SUPPLIER='',IN_PERSON='' where TASK_ID=#{taskId} and PROCESS_ID=#{peocessId} and ID=#{id}")
	public Integer updateRevokeSourceInfo(@Param("useNum")String useNum,@Param("taskId")String taskId,@Param("peocessId")String peocessId,@Param("id")String id);

//	委外接收数据更新
	@Update("update c_process_details_t set INSOURCE_IN_TEMS_APPROVAL='0',STATUS_FLAGS=#{flags},IN_PERSON_APPROVAL=#{inPersonApproval},COMPLETE_NUM=#{OkNum},OUTSOURCE_IN_NUM=#{OkNum},USEABLE_NUM=#{useNum} where TASK_ID=#{taskId} and PROCESS_ID=#{processId} and ID=#{id}")
	public Integer updateInSourceprocessInfo(@Param("flags")String flags,@Param("inPersonApproval")String inPersonApproval,@Param("OkNum")String OkNum,@Param("useNum")String useNum,@Param("taskId")String taskId,@Param("processId")String processId,@Param("id")String id);

//	获取当前工序的序号
//	@Select("select PROCESS_ORDER from c_process_details_t where TASK_ID=#{taskId} and PROCESS_ID=#{peocessId}")

//	撤销品检记录2.0
	@Update("update c_process_details_t set TESTING_NUM_TEM=#{testNumTem} ,OK_OUTSOURCE_TEM=#{testNumTem},NG_NUM_TEM_APPROVAL=0,OK_NUM_TEM_APPROVAL=0,ALL_REMARKS='',ALL_QUALITY_PERSON='' where TASK_ID=#{taskId} and PROCESS_ID=#{processId} and ID=#{ids}")
	public Integer updateQualityByFlags(@Param("testNumTem")String testNumTem,@Param("taskId")String taskId,@Param("processId")String processId,@Param("ids")String ids);

	@Update("update c_process_details_t set OK_INSOURCE_TEMS=#{testNumTem} ,OK_OUTSOURCE_TEMS=#{testNumTem},NG_NUM_TEM_APPROVAL=0,OK_NUM_TEM_APPROVAL=0,ALL_REMARKS='',ALL_QUALITY_PERSON='' where TASK_ID=#{taskId} and PROCESS_ID=#{processId} and ID=#{ids}")
	public Integer updateQualityByFlagsx(@Param("testNumTem")String testNumTem,@Param("taskId")String taskId,@Param("processId")String processId,@Param("ids")String ids);

//	更新审批数据2.0
	@Update("update c_process_details_t set FINISH_PRODUCT_TEM=#{userNum},OK_OUTSOURCE_TEM=#{userNum},OK_FINISH_PRODUCTION_TEMS=#{userNum},OUTSOURCE_OUT_NUM=#{outsourceNum},INSOURCE_IN_TEMS=#{userNum},TESTING_NUM=#{testing},NG_NUM=#{ngNum},USEABLE_NUM=#{newUseNum},ALL_REMARKS='',ALL_QUALITY_PERSON='',NG_NUM_TEM_APPROVAL=0,OK_NUM_TEM_APPROVAL=0 where TASK_ID=#{taskId} and PROCESS_ID=#{processId} and ID=#{id}")//,OK_OUTSOURCE_TEM=#{userNum}
	public Integer updateQualityApprovalDatas(@Param("testing")String testing,@Param("ngNum")String ngNum,@Param("userNum")String userNum,@Param("taskId")String taskId,@Param("processId")String processId,@Param("outsourceNum")String outsourceNum,@Param("newUseNum")String newUseNum,@Param("id")String id);

//	更新审批数据2.0
	@Update("update c_process_details_t set FINISH_PRODUCT_TEM=#{userNum},OK_FINISH_PRODUCTION_TEMS=#{userNum},OUTSOURCE_OUT_NUM=#{outsourceNum},INSOURCE_IN_TEMS=#{userNum},TESTING_NUM=#{testing},NG_NUM=#{ngNum},USEABLE_NUM=#{newUseNum},ALL_REMARKS='',ALL_QUALITY_PERSON='',NG_NUM_TEM_APPROVAL=0,OK_NUM_TEM_APPROVAL=0 where TASK_ID=#{taskId} and PROCESS_ID=#{processId} and ID=#{id}")//,OK_OUTSOURCE_TEM=#{userNum}
	public Integer updateQualityApprovalDatasx(@Param("testing")String testing,@Param("ngNum")String ngNum,@Param("userNum")String userNum,@Param("taskId")String taskId,@Param("processId")String processId,@Param("outsourceNum")String outsourceNum,@Param("newUseNum")String newUseNum,@Param("id")String id);

	@Select("<script>select d.STATION_NUM,d.MATERIAL_CODE,c.ID as IDS,c.ALL_OUT_SUPPLIER,d.MATERIAL_NAME,d.MATERIAL_QUALITY,d.NUM_REMARKS,d.IMAGES,c.OK_OUTSOURCE_TEM,c.INSOURCE_IN_TEMS,c.STATUS_FLAGS,d.STATUS_FLAGS as TASK_STATUS,c.IN_SUPPLIER,c.OK_INSOURCE_TEM,c.QUALITY_PERSON,d.ID,d.PROJECT_NUM,d.SPECIFICATION_AND_MODEL,c.STATION_NAME,c.PLAN_NUM,c.COMPLETE_NUM,c.USEABLE_NUM,c.OUTSOURCE_OUT_NUM,c.OUTSOURCE_IN_NUM,c.NG_NUM,c.REWORK_NUM,c.TESTING_NUM,c.PROCESS_ID,c.FINISH_PRODUCT_TEM from (select a.ID,a.ALL_OUT_SUPPLIER,a.OK_OUTSOURCE_TEM,a.INSOURCE_IN_TEMS,a.TASK_ID,b.STATION_NAME,a.PLAN_NUM,a.COMPLETE_NUM,a.USEABLE_NUM,a.OUTSOURCE_OUT_NUM,a.OUTSOURCE_IN_NUM,a.NG_NUM,a.REWORK_NUM,a.TESTING_NUM,a.PROCESS_ID,a.FINISH_PRODUCT_TEM,a.QUALITY_PERSON,a.OK_INSOURCE_TEM,a.IN_SUPPLIER,a.STATUS_FLAGS from c_process_details_t a "
			+ "left join c_mes_process_station_t b on a.PROCESS_ID = b.ID where b.STATION_TYPES=#{typeId}) c left join c_process_production_task d on c.TASK_ID=d.ID WHERE c.INSOURCE_IN_TEMS>0  "
			+ "<if test=\"stationNum!=''\"> and d.STATION_NUM like '%${stationNum}%'</if>"
			+ "<if test=\"projectNum!=''\"> and d.PROJECT_NUM like '%${projectNum}%'</if>"
			+ "<if test=\"specificationModel!=''\"> and d.SPECIFICATION_AND_MODEL like '%${specificationModel}%'</if>"
			+ "<if test=\"materiCode!=''\"> and d.MATERIAL_CODE like '%${materiCode}%'</if> </script>")//and c.STATUS_FLAGS=#{flagx}
	public List<Map<String,Object>> showAllProcessTypeTaskInfosx(@Param("typeId")Integer typeId,@Param("projectNum")String projectNum,@Param("specificationModel")String specificationModel,@Param("statusFlags")String statusFlags,@Param("flagx")String flagx,@Param("stationNum")String stationNum,@Param("materiCode")String materiCode);

//	更新前数据
	@Select("select USE_REWORK_NUM from c_process_details_t where TASK_ID=#{taskId} and PROCESS_ID=#{processId} and ID=#{id}")//NG_NUM
	public Integer showProcessById(@Param("taskId")String taskId,@Param("processId")String processId,@Param("id")String id);

//	查看指定任务单信息
	@Select("select * from c_process_production_task where ID=#{taskId}")
	public Map<String,Object> showTaskById(@Param("taskId")String taskId);

//	查看工序名称
	@Select("select STATION_NAME from c_mes_process_station_t where  ID=#{processId}")
	public String showStationName(@Param("processId")String processId);

//	查找主分支记录
	@Select("select * from c_process_details_t where TASK_ID=#{taskId} and PROCESS_ID=#{processId} and (SPLIT_FLAGS=1 or SPLIT_FLAGS=2)")
	public Map<String,Object> showAllMainBranchInfo(@Param("taskId")String taskId,@Param("processId")String processId);

//	新增子分支记录
	@Insert("insert into c_process_details_t(TASK_ID,PROCESS_ID,PROCESS_ORDER,PLAN_NUM,COMPLETE_NUM,USEABLE_NUM,OUTSOURCE_OUT_NUM,OUTSOURCE_IN_NUM,NG_NUM,REWORK_NUM,TESTING_NUM,STATUS_FLAGS,NG_NUM_TEM,TESTING_NUM_TEM,USE_REWORK_NUM,USE_REWORK_NUM_TEM,OK_OUTSOURCE_TEM,OK_NUM_TEM_APPROVAL,NG_NUM_TEM_APPROVAL,TESTING_NUM_TEM_APPROVAL,OK_INSOURCE_TEM,OK_INSOURCE_TEM_APPROVLA,EDL_FLAGS,SPLIT_FLAGS) values("
			+ "#{taskId},#{processId},#{processOrder},#{planNum},0,0,0,0,0,0,0,0,0,#{testNum},0,0,#{planNum},0,0,0,0,0,1,3)")
	public Integer insertAllChildBranchInfo(@Param("taskId")String taskId,@Param("processId")String processId,@Param("processOrder")String processOrder,@Param("planNum")String planNum,@Param("testNum")String testNum,@Param("testNums")String testNums);

//	更新主分支状态
	@Update("update c_process_details_t set SPLIT_FLAGS=2 where ID=#{id}")
	public Integer updateSplitMainBrachInfo(@Param("id")String id);

//	更新委外发出计划数量(子分支)
	@Update("<script>update c_process_details_t set TESTING_NUM_TEM=#{testNum},OK_INSOURCE_TEM=#{testNum}<if test=\"splitFlags==3\">,SPLIT_FLAGS=4</if> where TASK_ID=#{taskId} and PROCESS_ID=#{processId} and ID=#{id}</script>")
	public Integer updateOutSidePlanNum(@Param("taskId")String taskId,@Param("processId")String processId,@Param("testNum")String testNum,@Param("splitFlags")String splitFlags,@Param("id")String id);

//	更新委外发出计划数量(主分支)
	@Update("<script> update c_process_details_t set TESTING_NUM_TEM=#{testNum},OK_INSOURCE_TEM=#{testNum} where TASK_ID=#{taskId} and PROCESS_ID=#{processId} </script>")
	public Integer updateOutSideMainPlanNum(@Param("taskId")String taskId,@Param("processId")String processId,@Param("testNum")String testNum);

//	更新委外发出计划数量(主分支)
	@Update("<script> update c_process_details_t set TESTING_NUM_TEM=#{testNum},OK_INSOURCE_TEM=#{testNum} where TASK_ID=#{taskId} and PROCESS_ID=#{processId} and (SPLIT_FLAGS=1 or SPLIT_FLAGS=2) </script>")
	public Integer updateOutSideMainPlanNumxx(@Param("taskId")String taskId,@Param("processId")String processId,@Param("testNum")String testNum);

//	更新委外发出计划数量(子分支)
	@Update("update c_process_details_t set OK_INSOURCE_TEM=0 where TASK_ID=#{taskId} and PROCESS_ID=#{processId} and SPLIT_FLAGS=3 ")
	public Integer updateOutSideChildPlanNum(@Param("taskId")String taskId,@Param("processId")String processId);

//	删除分支记录
	@Delete("delete from c_process_details_t where ID=#{id}")
    public Integer delOutSideChildBrach(@Param("id")String id);

//	撤销主分支
	@Update("update c_process_details_t set OK_INSOURCE_TEM_APPROVLA=0 where ID=#{id}")
	public Integer updateSplitOKApprovlaNum(@Param("id")String id);

	@Select("select count(*) from  c_process_details_t where TASK_ID=#{taskId} and PROCESS_ID=#{processId}")
	public Integer countSplitChildNums(@Param("taskId")String taskId,@Param("processId")String processId);

	@Update("update c_process_details_t set SPLIT_FLAGS=1 where TASK_ID=#{taskId} and PROCESS_ID=#{processId}")
	public Integer updateSplitStatusData(@Param("taskId")String taskId,@Param("processId")String processId);

	@Update("update c_process_details_t set TESTING_NUM=#{testNum},NG_NUM=#{ngNum},USEABLE_NUM=#{okNum} where TASK_ID=#{taskId} and PROCESS_ID=#{processId} and (SPLIT_FLAGS=1 or SPLIT_FLAGS=2)")
	public Integer updateTestNumData(@Param("taskId")String taskId,@Param("processId")String processId,@Param("testNum")String testNum,@Param("okNum")String okNum,@Param("ngNum")String ngNum);

//	获取子分支数据
	@Select("select * from c_process_details_t where ID=#{id}")
	public Map<String,Object> showTestNumData(@Param("id")String id);

//更新待品检数据
	@Update("update c_process_details_t set TESTING_NUM=#{testNum} where ID=#{id}")
	public Integer updateMainTestNumData(@Param("testNum")String testNum,@Param("id")String id);

//	查询工艺路线的任务单记录
	@Select("select * from c_process_production_task where ROUTE_NAME=#{routeId}")
	public List<Map<String,Object>> showAllTaskByRouteIdInfo(@Param("routeId")String routeId);

//	查询工序详情数据
	@Select("select * from c_process_details_t where TASK_ID=#{taskId} and PROCESS_ID=#{processId}")
	public List<Map<String,Object>> showAllDetailsByIdInfo(@Param("taskId")String taskId,@Param("processId")String processId);

//	新增委外清单列表
//	@Insert("insert c_process_outsource_list(TASK_ID,PROCESS_ID,OUT_SIDE_NUM,,IN_SIDE_NUM,OK_NUM,NG_NUM,SUPPLIER,STATUS,SPLIT_ID) values(#{taskId},#{processId},0,0,0,0,'',0,#{listId})")
	@Insert("INSERT INTO `processroute`.`c_process_outsource_list`( `TASK_ID`, `PROCESS_ID`, `SPLIT_ID`, `OUT_SIDE_NUM`, `OUT_SIDE_NUM_APPLY`, `OUT_SIDE_NUM_EXAMINE`, `IN_SIDE_NUM`, `IN_SIDE_NUM_APPLY`, `IN_SIDE_NUM_EXAMINE`, `OK_NUM`, `OK_NUM_APPLY`, `OK_NUM_EXAMINE`, `NG_NUM`, `NG_NUM_APPLY`, `NG_NUM_EXAMINE`, `SUPPLIER`, `STATUS`, `OUT_SIDE_PERSON_APPLY`, `OUT_SIDE_PERSON_EXAMINE`, `IN_SIDE_PERSON_APPLY`, `IN_SIDE_PERSON_EXAMINE`, `QUALITY_PERSON_APPLY`, `QUALITY_PERSON_EXAMINE`, `REWORK_NUM`, `REWORK_APPLY_NUM`, `REWORK_EXAMINE_NUM`) VALUES ( #{taskId}, #{processId}, #{listId}, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '', '1', 0, 0, 0, 0, 0, 0,0,0,0)")
	public Integer insertOutsourceListDatas(@Param("taskId")String taskId,@Param("processId")String processId,@Param("listId")String listId);

	@Insert("INSERT INTO `processroute`.`c_process_outsource_list`( `TASK_ID`, `PROCESS_ID`, `SPLIT_ID`, `OUT_SIDE_NUM`, `OUT_SIDE_NUM_APPLY`, `OUT_SIDE_NUM_EXAMINE`, `IN_SIDE_NUM`, `IN_SIDE_NUM_APPLY`, `IN_SIDE_NUM_EXAMINE`, `OK_NUM`, `OK_NUM_APPLY`, `OK_NUM_EXAMINE`, `NG_NUM`, `NG_NUM_APPLY`, `NG_NUM_EXAMINE`, `SUPPLIER`, `STATUS`, `OUT_SIDE_PERSON_APPLY`, `OUT_SIDE_PERSON_EXAMINE`, `IN_SIDE_PERSON_APPLY`, `IN_SIDE_PERSON_EXAMINE`, `QUALITY_PERSON_APPLY`, `QUALITY_PERSON_EXAMINE`, `REWORK_NUM`, `REWORK_APPLY_NUM`, `REWORK_EXAMINE_NUM`) VALUES ( #{taskId}, #{processId}, #{listId}, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '', '0', 0, 0, 0, 0, 0, 0,0,0,0)")
	public Integer insertOutsourceListDatasx(@Param("taskId")String taskId,@Param("processId")String processId,@Param("listId")String listId);

	@Select("select max(ID) from c_process_details_t where TASK_ID=#{taskId} and PROCESS_ID=#{processId} and SPLIT_FLAGS=3")
	public Integer showDetailsIdInfoById(@Param("taskId")String taskId,@Param("processId")String processId);

	@Delete("delete from c_process_outsource_list where TASK_ID=#{taskId} and PROCESS_ID=#{processId} and SPLIT_ID=#{id}")
	public Integer deloutSourceInfoById(@Param("taskId")String taskId,@Param("processId")String processId,@Param("id")String id);

	@Select("select * from c_process_outsource_list where TASK_ID=#{taskId} and PROCESS_ID=#{processId} order by ID")
	public List<Map<String,Object>> showAllOutsourceData(@Param("taskId")String taskId,@Param("processId")String processId);

	@Update("update c_process_outsource_list set PLAN_NUM=#{okNum},OUT_SIDE_NUM_APPLY=#{okNum} ,SUPPLIER=#{supplier},STATUS=1 where TASK_ID=#{taskId} and PROCESS_ID=#{processId} and SPLIT_ID=#{id}")
	public Integer updateOutsideNumTask(@Param("okNum")String okNum,@Param("supplier")String supplier,@Param("taskId")String taskId,@Param("processId")String processId,@Param("id")String id);

	@Update("update c_process_outsource_list set OUT_SIDE_NUM_EXAMINE=#{okNum},OUT_SIDE_NUM_APPLY=0,OUT_SIDE_NUM=#{okNum},STATUS=2 where TASK_ID=#{taskId} and PROCESS_ID=#{processId} and SPLIT_ID=#{id}")
	public Integer updateExamineOutsideNumTask(@Param("okNum")String okNum,@Param("taskId")String taskId,@Param("processId")String processId,@Param("id")String id);

	@Update("update c_process_details_t set OUTSOURCE_OUT_NUM=#{outsideNum} where TASK_ID=#{taskId} and PROCESS_ID=#{processId} and ID=#{id}")
	public Integer updateMainOutsideNumTask(@Param("outsideNum")String outsideNum,@Param("taskId")String taskId,@Param("processId")String processId,@Param("id")String id);

	@Update("update c_process_outsource_list set OK_NUM_APPLY=#{okNum},NG_NUM_APPLY=#{ngNum},STATUS=3 where TASK_ID=#{taskId} and PROCESS_ID=#{processId} and SPLIT_ID=#{id}")
	public Integer updateQualityAllNumTask(@Param("okNum")String okNum,@Param("ngNum")String ngNum,@Param("taskId")String taskId,@Param("processId")String processId,@Param("id")String id,@Param("testNum")String testNum);

	@Update("update c_process_outsource_list set OK_NUM_APPLY=#{okNum},NG_NUM_APPLY=#{ngNum},STATUS=20 where TASK_ID=#{taskId} and PROCESS_ID=#{processId} and SPLIT_ID=#{id}")
	public Integer updateQualityPartNumTask(@Param("okNum")String okNum,@Param("ngNum")String ngNum,@Param("taskId")String taskId,@Param("processId")String processId,@Param("id")String id,@Param("testNum")String testNum);

	@Select("select * from c_process_outsource_list  where TASK_ID=#{taskId} and PROCESS_ID=#{processId} and SPLIT_ID=#{id}")
	public Map<String,Object> showOutsideTaskById(@Param("taskId")String taskId,@Param("processId")String processId,@Param("id")String id);

	@Update("update c_process_outsource_list set OUT_SIDE_NUM=#{testNum},OK_NUM_APPLY=0,NG_NUM_APPLY=0  where TASK_ID=#{taskId} and PROCESS_ID=#{processId} and SPLIT_ID=#{id}")
	public Integer revockOutsideQuqalityNumTask(@Param("testNum")String testNum,@Param("taskId")String taskId,@Param("processId")String processId,@Param("id")String id);

	@Update("update c_process_outsource_list set STATUS=#{status} where TASK_ID=#{taskId} and PROCESS_ID=#{processId} and SPLIT_ID=#{id}")
	public Integer updateOutsideStatusNum(@Param("taskId")String taskId,@Param("processId")String processId,@Param("id")String id,@Param("status")String status);

	@Update("update c_process_outsource_list set OK_NUM=#{okNum},OK_NUM_EXAMINE=#{okNumExmine},OK_NUM_APPLY=0,NG_NUM=#{ngNum},NG_NUM_EXAMINE=#{ngNumExmine},NG_NUM_APPLY=0,OUT_SIDE_NUM=#{testNum} where TASK_ID=#{taskId} and PROCESS_ID=#{processId} and SPLIT_ID=#{id}")//OK_NUM_EXAMINE=#{okNum},
	public Integer updateOutsideExamineNum(@Param("okNum")String okNum,@Param("ngNum")String ngNum,@Param("testNum")String testNum,@Param("taskId")String taskId,@Param("processId")String processId,@Param("id")String id,@Param("okNumExmine")String okNumExmine,@Param("ngNumExmine")String ngNumExmine);

	@Update("update c_process_outsource_list set IN_SIDE_NUM_APPLY=#{insideApply}  where TASK_ID=#{taskId} and PROCESS_ID=#{processId} and SPLIT_ID=#{id} ")
	public Integer updateOutsideInsideApplyNum(@Param("insideApply")String insideApply,@Param("taskId")String taskId,@Param("processId")String processId,@Param("id")String id);

//	@Update("update c_process_outsource_list set IN_SIDE_NUM_APPLY=0 where TASK_ID=#{taskId} and PROCESS_ID=#{processId} and SPLIT_ID=#{id} ")
//	public Integer updateOutsideRevockApplyNum(@Param("taskId")String taskId,@Param("processId")String processId,@Param("id")String id);
	@Update("update c_process_outsource_list set IN_SIDE_NUM_APPLY=0 where TASK_ID=#{taskId} and PROCESS_ID=#{processId} and SPLIT_ID=#{id}")
	public  Integer updateOutsideRevockApplyNum(@Param("taskId")String taskId,@Param("processId")String processId,@Param("id")String id);

//	@Update("update c_process_outsource_list set IN_SIDE_NUM_APPLY=#{applyNum},IN_SIDE_NUM_EXAMINE=#{examineNum},IN_SIDE_NUM=#{examineNum} where TASK_ID=#{taskId} and PROCESS_ID=#{processId} and SPLIT_ID=#{id} ")
//	public Integer updateOutsideExamineNum(@Param("applyNum") String applyNum,@Param("examineNum") String examineNum,@Param("taskId")String taskId,@Param("processId")String processId,@Param("id")String id);
//
	@Update("update c_process_outsource_list set IN_SIDE_NUM=#{examineNum},IN_SIDE_NUM_EXAMINE=#{examineNum},IN_SIDE_NUM_APPLY=#{applyNum},OK_NUM=#{okNum} where TASK_ID=#{taskId} and PROCESS_ID=#{processId} and SPLIT_ID=#{id}")
	public  Integer updateOutsideExamineNums(@Param("okNum") String okNum,@Param("applyNum")String applyNum,@Param("examineNum")String examineNum,@Param("taskId")String taskId,@Param("processId")String processId,@Param("id")String id);

	@Update("update c_process_details_t set COMPLETE_NUM=#{complete},OUTSOURCE_IN_NUM=#{complete}  where TASK_ID=#{taskId} and PROCESS_ID=#{processId} and (SPLIT_FLAGS=1 or SPLIT_FLAGS=2)")
	public Integer updateMainComplete(@Param("complete")String complete,@Param("taskId")String taskId,@Param("processId")String processId);

	@Select("select sum(PLAN_NUM) from c_process_outsource_list where TASK_ID=#{taskId}  and PROCESS_ID=#{processId}")
	public Integer sumMainPlanNum(@Param("taskId")String taskId,@Param("processId")String processId);

	@Update("update c_process_details_t set PUSH_DOWN_NUM=#{pushDownNum} where TASK_ID=#{taskId}  and PROCESS_ORDER=#{orderNum}")
	public Integer updateDetailsPushDownNum(@Param("pushDownNum")String pushDownNum,@Param("taskId")String taskId,@Param("orderNum")String orderNum);






}
