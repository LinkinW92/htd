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

/**
 * 工序详情
 *
 * @author Huangzs
 */
@MapperScan
@Component("ProcessDetailsDao")
public interface ProcessDetailsDao {
	//品检日志
	@Select("<script> select * from c_process_operation_log o left join c_mes_process_station_t s on o.PROCESS_ID=s.ID where LOG_TYPE  like '%${logType}%'"
		+ "<if test=\"user!=null and user!='' and specificationModel!=null and specificationModel!=''\"> and o.LOG_INFO like '%${user}%' and o.LOG_INFO like '%${specificationModel}%' </if>"
		+ "<if test=\"user!=null and user!='' and (specificationModel==null or specificationModel=='')\"> and o.LOG_INFO like '%${user}%'  </if>"
		+ "<if test=\"(user==null or user=='' )and specificationModel!=null and specificationModel!=''\"> and o.LOG_INFO like '%${specificationModel}%' </if>"
		+ "<if test=\"beginTime!=null and beginTime!=''\"> and DATE_FORMAT(o.DT, '%Y-%m-%d') &gt;= DATE_FORMAT( #{beginTime}, '%Y-%m-%d')</if>"
		+ "<if test=\"endTime!=null and endTime!=''\"> and DATE_FORMAT(o.DT, '%Y-%m-%d') &lt;=DATE_FORMAT( #{endTime}, '%Y-%m-%d')</if>"
		+ " order by o.ID desc </script>")
//	o.DT,  specificationModel
	public List<Map<String, Object>> showQualityLog(@Param("logType") String logType, @Param("user") String user, @Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("specificationModel") String specificationModel);

	//	展示不同类型的工序
//	@Select("select * from c_process_operation_log o left join c_mes_process_station_t s on o.PROCESS_ID=s.ID where STATION_TYPES=#{typeId} order by o.DT desc")
	@Select("select * from c_process_operation_log o left join c_mes_process_station_t s on o.PROCESS_ID=s.ID where LOG_TYPE  like '%${logType}%' order by o.DT desc")
	public List<Map<String, Object>> showAllProcessLogByTypeIds(@Param("logType") String typeId);

	//	展示不同类型的工序
	@Select("select * from c_process_operation_log o left join c_mes_process_station_t s on o.PROCESS_ID=s.ID where STATION_TYPES=#{typeId} order by o.DT desc")
//	@Select("select * from c_process_operation_log o left join c_mes_process_station_t s on o.PROCESS_ID=s.ID where LOG_TYPE  like '%${logType}%' order by o.DT desc")
	public List<Map<String, Object>> showAllProcessLogByTypeIdsx(@Param("typeId") String typeId);

	//展示工序日志详情信息
	@Select("select * from c_process_operation_log where TASK_ID=#{taskId} and PROCESS_ID=#{processId} order by DT desc")
	public List<Map<String, Object>> showAllProcessLogInfo(@Param("taskId") String taskId, @Param("processId") String processId);

	@Select("select STATION_TYPES from c_mes_process_station_t where ID=#{id}")
	public String showStationTypeInfo(@Param("id") String id);


	//	展示工序详情
//	@Select("select * from c_process_details_t d left join c_mes_process_station_t s on d.PROCESS_ID=s.ID where TASK_ID=#{stakId} and (SPLIT_FLAGS=1 or SPLIT_FLAGS=2) order by PROCESS_ORDER asc ")//
//	4.0更新
	@Select("select * from (select * from (select c.PROCESS_ID,c.TASK_ID,c.ID,d.ROUTE_NAME,c.SPLIT_FLAGS,c.UNIT_PRICE,c.PROCESS_ORDER,c.STATION_NAME,c.PLAN_NUM,c.COMPLETE_NUM,c.NG_NUM,c.OUTSOURCE_OUT_NUM,c.PUSH_DOWN_NUM,c.TESTING_NUM,c.REWORK_NUM from (select a.PROCESS_ID,a.TASK_ID,b.ID,a.SPLIT_FLAGS,b.UNIT_PRICE,a.PROCESS_ORDER,b.STATION_NAME,a.PLAN_NUM,a.COMPLETE_NUM,a.NG_NUM,a.OUTSOURCE_OUT_NUM,a.PUSH_DOWN_NUM,a.TESTING_NUM,a.REWORK_NUM from c_process_details_t a left join c_mes_process_station_t b on a.PROCESS_ID=b.ID) c left join c_process_production_task d on c.TASK_ID = d.ID) e  where TASK_ID=#{stakId} and (e.SPLIT_FLAGS=1 or e.SPLIT_FLAGS=2)) f left join c_mes_process_production_way_t g on f.ROUTE_NAME=g.ROUTING_ID and f.ID=g.ST_ID ORDER BY g.SERIAL_NO asc;")
	public List<Map<String, Object>> showProcessDetails(@Param("stakId") Integer stakId);

	@Select("select * from c_process_details_t d left join c_mes_process_station_t s on d.PROCESS_ID=s.ID where TASK_ID=#{stakId} and (SPLIT_FLAGS=1 or SPLIT_FLAGS=2) and d.PROCESS_ID=#{order}  order by PROCESS_ORDER asc ")
//
	public Map<String, Object> showProcessDetailsxx(@Param("stakId") Integer stakId, @Param("order") Integer order);

	//	新增工序详情 14位   #{planNum} 变为0     4.0用于工艺路线删除限制条件
	@Insert("<script>insert into c_process_details_t(TASK_ID,PROCESS_ID,PROCESS_ORDER,PLAN_NUM,COMPLETE_NUM,USEABLE_NUM,OUTSOURCE_OUT_NUM,OUTSOURCE_IN_NUM,NG_NUM,REWORK_NUM,TESTING_NUM,STATUS_FLAGS,NG_NUM_TEM,TESTING_NUM_TEM,USE_REWORK_NUM,USE_REWORK_NUM_TEM,OK_OUTSOURCE_TEM,OK_NUM_TEM_APPROVAL,NG_NUM_TEM_APPROVAL,TESTING_NUM_TEM_APPROVAL,OK_INSOURCE_TEM,OK_INSOURCE_TEM_APPROVLA,EDL_FLAGS,SPLIT_FLAGS,PUSH_DOWN_NUM)"
		+ "values"
		+ "<foreach collection='list' item='item' index='index' separator=','>"
		+ "(#{taskId},#{item.ST_ID},#{item.SERIAL_NO},#{planNum},0,0,0,0,0,0,0,0,0,0,0,0,#{planNum},0,0,0,#{planNum},0,1,1,0)"
		+ "</foreach>"
		+ "</script>")
	public Integer addProcessDetails(@Param("taskId") Integer taskId, @Param("planNum") Integer planNum, @Param("list") List<Map<String, Object>> list);

	//	添加首站记录
	@Insert("insert into c_process_details_t(TASK_ID,PROCESS_ID,PROCESS_ORDER,PLAN_NUM,COMPLETE_NUM,USEABLE_NUM,OUTSOURCE_OUT_NUM,OUTSOURCE_IN_NUM,NG_NUM,REWORK_NUM,TESTING_NUM,STATUS_FLAGS,NG_NUM_TEM,TESTING_NUM_TEM,USE_REWORK_NUM,USE_REWORK_NUM_TEM,OK_OUTSOURCE_TEM,OK_NUM_TEM_APPROVAL,NG_NUM_TEM_APPROVAL,TESTING_NUM_TEM_APPROVAL,OK_INSOURCE_TEM,OK_INSOURCE_TEM_APPROVLA,SPLIT_FLAGS)"
		+ "values(#{taskId},#{processId},#{processOrder},#{planNum},0,0,0,0,0,0,0,0,0,#{planNum},0,0,#{planNum},0,0,0,#{planNum},0,1)")
	public Integer addFirstProcessInfos(@Param("taskId") String taskId, @Param("processId") String processId, @Param("processOrder") String serialNo, @Param("planNum") String planNum);


	//  删除工序详情（按照关联生产任务ID删除）
	@Delete("delete from c_process_details_t where TASK_ID=#{taskId} and ((USEABLE_NUM=0 and TESTING_NUM=0 and NG_NUM=0 and OUTSOURCE_OUT_NUM=0 and OUTSOURCE_IN_NUM=0) or (PROCESS_ORDER=1 and USEABLE_NUM=0 and NG_NUM=0 and OUTSOURCE_OUT_NUM=0 and OUTSOURCE_IN_NUM=0)) ")
	public Integer delProcessDetails(@Param("taskId") Integer taskId);

	//	编辑工序详情(适用于暂停状态下的编辑计划数量)
	@Update("update c_process_details_t set PLAN_NUM=#{planNum} where TASK_ID=#{taskId}")
	public Integer editProcessDetails(@Param("planNum") Integer planNum, @Param("taskId") Integer taskId);

	//	便利工艺路线工序详情
//	@Select("select * from c_mes_process_production_way_t where ROUTING_ID=#{routeId} order by SERIAL_NO asc")
//	@Select("(select * from c_mes_process_production_way_t a left join c_process_details_t b on a.ST_ID = b.PROCESS_ID where a.ROUTING_ID=#{routeId} and b.COMPLETE_NUM=0 and b.USEABLE_NUM=0 and b.TESTING_NUM=0 and b.NG_NUM=0 and b.OUTSOURCE_OUT_NUM=0 and b.OUTSOURCE_IN_NUM=0 and b.TASK_ID=#{taskId} order by SERIAL_NO)"
//			+ "union (select * from c_mes_process_production_way_t a left join c_process_details_t b on a.ST_ID = b.PROCESS_ID where  b.PLAN_NUM is null and a.ROUTING_ID=#{routeId})")
//	public List<Map<String,Object>> showRouteList(@Param("routeId")Integer routeId,@Param("taskId")String taskId);
	@Select("select * from c_mes_process_production_way_t a left join c_process_details_t b on a.ST_ID = b.PROCESS_ID where a.ROUTING_ID=#{routeId} and b.COMPLETE_NUM=0 and b.USEABLE_NUM=0 and b.TESTING_NUM=0 and b.NG_NUM=0 and b.OUTSOURCE_OUT_NUM=0 and b.OUTSOURCE_IN_NUM=0 and b.TASK_ID=#{taskId} and (b.SPLIT_FLAGS=1 or b.SPLIT_FLAGS=2) order by SERIAL_NO")
	public List<Map<String, Object>> showRouteList(@Param("routeId") Integer routeId, @Param("taskId") String taskId);

	@Select("select * from c_mes_process_production_way_t a left join c_process_details_t b on a.ST_ID = b.PROCESS_ID where  b.PLAN_NUM is null and a.ROUTING_ID=#{routeId}")
	public List<Map<String, Object>> showRuteListxs(@Param("routeId") Integer routeId);

	//	遍历工艺路线
	@Select("select * from c_mes_process_production_way_t  where ROUTING_ID=#{routeId}")
	public List<Map<String, Object>> showRouteLists(@Param("routeId") Integer routeId);

	//	展示当前新增的ID值
	@Select("select ID from c_process_production_task where PROJECT_NUM=#{projectNum} and SPECIFICATION_AND_MODEL=#{specificationModel} and STATION_NUM=#{stationName}")
	public Integer showTaskId(@Param("projectNum") String projectNum, @Param("specificationModel") String specificationModel, @Param("stationName") String stationName);

	//	设置首工序可用数量
	@Update("update  c_process_details_t set TESTING_NUM=#{useable},TESTING_NUM_TEM=#{useablex} where TASK_ID=#{taskId} and PROCESS_ORDER=1 and (SPLIT_FLAGS=1 or SPLIT_FLAGS=2)")
	public Integer updateFirstProcessDetails(@Param("useable") Integer useable, @Param("useablex") Integer useablex, @Param("taskId") Integer taskId);

	//	设置首工序的可下推数量
	@Update("update  c_process_details_t set PUSH_DOWN_NUM=#{useable} where TASK_ID=#{taskId} and PROCESS_ORDER=1 and (SPLIT_FLAGS=1 or SPLIT_FLAGS=2)")
	public Integer updateFirstProcessPushDownDetails(@Param("useable") Integer useable, @Param("taskId") Integer taskId);


	@Update("update  c_process_details_t set PUSH_DOWN_NUM=#{useable} where TASK_ID=#{taskId} and PROCESS_ORDER=1 and (SPLIT_FLAGS=1 or SPLIT_FLAGS=2)")
	public Integer updateFirstProcessDetailsx(@Param("useable") Integer useable, @Param("taskId") Integer taskId);

	//	查询生产任务的计划数量与工艺路线是否被修改进行不同操作
	@Select("select * from c_process_production_task where ID=#{id}")
	public Map<String, Object> showTaskById(@Param("id") Integer id);

	//	更新计划数量（用于针对只有计划数量改变的生产任务）
	@Update("update c_process_details_t set PLAN_NUM=#{planNum} where TASK_ID=#{taskId}")
	public Integer updatePlanNum(@Param("planNum") Integer planNum, @Param("taskId") Integer taskId);

	//	获取首工序数据
	@Select("select * from c_process_details_t where TASK_ID=#{taskId} and PROCESS_ORDER=1 and (SPLIT_FLAGS=1 or SPLIT_FLAGS=2) ")
	public Map<String, Object> showFirstProcessInfo(@Param("taskId") Integer taskId);

	@Select("select * from c_process_details_t where  TASK_ID=#{taskId} and (SPLIT_FLAGS=1 or SPLIT_FLAGS=2) and PROCESS_ID=#{processId} ")
	public Map<String, Object> showAllDetailsInfoByProcessIdData(@Param("taskId") String taskId, @Param("processId") String processId);

	@Update("update c_process_details_t set PROCESS_ORDER=#{processOrder},TESTING_NUM=0 where  TASK_ID=#{taskId} and (SPLIT_FLAGS=1 or SPLIT_FLAGS=2) and PROCESS_ID=#{processId}")
	public Integer updateDetailsAreadyData(@Param("processOrder") String processOrder, @Param("taskId") String taskId, @Param("processId") String processId);

	@Update("update c_process_details_t set TESTING_NUM=#{testNum} where TASK_ID=#{taskId} and PROCESS_ORDER=1 and (SPLIT_FLAGS=1 or SPLIT_FLAGS=2)")
	public Integer updateFirstDataByOrderByOne(@Param("testNum") String testNum, @Param("taskId") String taskId);

	@Select("select STATUS_FLAGS from c_process_production_task where ID=#{taskId}")
	public Integer showTaskStatusDataw(@Param("taskId") String taskId);

	@Select("select PROCESS_ID from c_process_details_t where TASK_ID=#{taskId} and PROCESS_ORDER=1 and (SPLIT_FLAGS=1 or SPLIT_FLAGS=0)")
	public Integer selectProcessIdById(@Param("taskId") String taskId);

	@Select("select max(SERIAL_NO) from c_mes_process_production_way_t where ROUTING_ID=#{routeId}")
	public Integer showProductWayId(@Param("routeId") String routeId);

	@Select("select ST_ID from c_mes_process_production_way_t where SERIAL_NO=#{serialNo} and ROUTING_ID=#{routeId}")
	public Integer showRouteId(@Param("serialNo") String serialNo, @Param("routeId") String routeId);


}
