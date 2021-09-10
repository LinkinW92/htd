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

@Component("RouteLineInfoDao")
@MapperScan
public interface RouteLineInfoDao {

	@Select("<script>select  c.DT,c.RUN_TIMES,c.PROJECT_NUMX,c.STATION_NUMX,c.SPECIFICATION_AND_MODELX,c.`NAME`,d.STATION_NAME,c.SERIAL_NO,c.PROCESS_REMARKS from (select a.DT,b.PROJECT_NUMX,b.STATION_NUMX,b.SPECIFICATION_AND_MODELX,`NAME` ,ST_ID,SERIAL_NO,PROCESS_REMARKS,RUN_TIMES from c_mes_process_production_way_t a left join c_mes_process_routing_t b on a.ROUTING_ID=b.ID ) c left join c_mes_process_station_t d on c.ST_ID = d.ID "
			+ "where 1=1 <if test=\"projectNum!=null and projectNum!=''\">and c.PROJECT_NUMX  like '%${projectNum}%'</if>"
			+ "<if test=\"specificationModel!=null and specificationModel!=''\">and c.SPECIFICATION_AND_MODELX like '%${specificationModel}%'</if>"
			+ "<if test=\"stationNum!=null and stationNum!=''\">and c.STATION_NUMX like '%${stationNum}%'</if>"
			+ "<if test=\"startTime!=null and startTime!=''\">and c.DT &gt;= #{startTime}</if>"
			+ "<if test=\"endTime!=null and endTime!=''\">and c.DT &lt;= #{endTime}</if>"
//			+ "<if test=\"endTime!=null and endTime!=''\">and c.DT <= #{endTime}</if>"
			+ "order by c.PROJECT_NUMX,c.STATION_NUMX,c.SPECIFICATION_AND_MODELX,c.SERIAL_NO</script>")
	public List<Map<String,Object>> showRouteLine(@Param("projectNum")String projectNum,@Param("specificationModel")String specificationModel,@Param("stationNum")String stationNum,@Param("startTime")String startTime,@Param("endTime")String endTime);


	@Select("select max(ID) from  c_mes_process_routing_t")
	public String showMaxRouteLineId();

	@Insert("insert into c_mes_process_routing_t(ID,PROJECT_NUMX,STATION_NUMX,SPECIFICATION_AND_MODELX,`NAME`) values(#{id},#{projectNum},#{stationNum},#{specificationAndModel},#{name})")
	public Integer addRouteLine(@Param("id")String id,@Param("projectNum")String projectNum,@Param("stationNum")String stationNum,@Param("specificationAndModel")String specificationAndModel,@Param("name")String name);

	@Select("select count(*) as count,ID from c_mes_process_routing_t where PROJECT_NUMX=#{projectNum} and STATION_NUMX=#{stationNum} and SPECIFICATION_AND_MODELX=#{specificationAndModel}")
	public Map<String,Object> countRoutrLine(@Param("projectNum")String projectNum,@Param("stationNum")String stationNum,@Param("specificationAndModel")String specificationAndModel);

	@Select("select ID from c_mes_process_station_t where STATION_NAME=#{stationName}")
	public Integer showStationId(@Param("stationName") String stationName);

	@Select("select count(*) from c_mes_process_production_way_t where ROUTING_ID=#{routeId} and SERIAL_NO=#{serialNo}")
	public Integer countProductWay(@Param("routeId")String routeId,@Param("serialNo")String serialNo);

	@Insert("insert into c_mes_process_production_way_t(DT,ST_ID,ROUTING_ID,SERIAL_NO,PROCESS_REMARKS,RUN_TIMES) values(now(),#{stationId},#{routId},#{serialNo},#{processRemarks},#{runtimes})")
	public Integer addProductWay(@Param("stationId")String stationId,@Param("routId")String routId,@Param("serialNo")String serialNo,@Param("processRemarks")String processRemarks,@Param("runtimes")String runtimes);

	@Select("select * from c_process_production_task where ROUTE_NAME=#{routeId}")
	public List<Map<String,Object>> showAllTaskByRouteId(@Param("routeId")String routeId);

	@Select("select * from c_process_details_t where TASK_ID=#{taskId} and PROCESS_ORDER=#{processOrder}")
	public Map<String,Object> showDetailsInfo(@Param("taskId")String taskId,@Param("processOrder")String processOrder);

	@Select("select * from c_process_details_t where TASK_ID=#{taskId} and PROCESS_ORDER>=#{serialNo}")
	public List<Map<String,Object>> showAllDetailsInfoByTaskId(@Param("taskId")String taskId,@Param("serialNo")String serialNo);

	@Select("select * from c_process_details_t where TASK_ID=#{taskId} and PROCESS_ORDER>=#{minserialNo} and PROCESS_ORDER<#{maxserialNo}")
	public List<Map<String,Object>> showAllDetailsInfoByTaskIdAndArea(@Param("taskId")String taskId,@Param("minserialNo")String minserialNo,@Param("maxserialNo")String maxserialNo);

	@Select("select * from c_process_details_t where TASK_ID=#{taskId} and PROCESS_ORDER>#{minserialNo} and PROCESS_ORDER<=#{maxserialNo}")
	public List<Map<String,Object>> showAllDetailsInfoByTaskIdAndAreaxx(@Param("taskId")String taskId,@Param("minserialNo")String minserialNo,@Param("maxserialNo")String maxserialNo);

	@Select("select * from c_mes_process_production_way_t where ROUTING_ID=#{routeId} and SERIAL_NO>=#{minserialNo} and SERIAL_NO<#{maxserialNo}")
	public List<Map<String,Object>> showAllWayInfoByTaskIdAndArea(@Param("routeId")String routeId,@Param("minserialNo")String minserialNo,@Param("maxserialNo")String maxserialNo);

	@Select("select * from c_mes_process_production_way_t where ROUTING_ID=#{routeId} and SERIAL_NO>#{minserialNo} and SERIAL_NO<=#{maxserialNo}")
	public List<Map<String,Object>> showAllWayInfoByTaskIdAndAreaxx(@Param("routeId")String routeId,@Param("minserialNo")String minserialNo,@Param("maxserialNo")String maxserialNo);

	@Update("update c_process_details_t set PROCESS_ORDER=#{serialNo},TESTING_NUM_TEM=0 where ID=#{id}")
	public Integer updateDetailsOrderNumx(@Param("serialNo")String serialNo,@Param("id")String id);

	@Update("update c_process_details_t set PROCESS_ORDER=#{serialNo},PUSH_DOWN_NUM=0 where ID=#{id}")
	public Integer updateDetailsOrderNum(@Param("serialNo")String serialNo,@Param("id")String id);

	@Select("select * from  c_mes_process_production_way_t where  ROUTING_ID=#{routLineId} and SERIAL_NO>=#{serialNo}")
	public List<Map<String,Object>> showProductWayBySTAndRoute(@Param("routLineId")String routLineId,@Param("serialNo")String serialNo);

	@Update("update c_mes_process_production_way_t set SERIAL_NO=#{serialNo} where ID=#{id}")
	public Integer updateWayDataInfos(@Param("serialNo")String serialNo,@Param("id")String id);

	@Update("update c_mes_process_production_way_t set RUN_TIMES=#{runtimes},PROCESS_REMARKS=#{remarks} where ID=#{id}")
	public Integer updateWayDataInfosx(@Param("runtimes")String runtimes,@Param("id")String id,@Param("remarks")String remarks);

	@Insert("insert into c_process_details_t(TASK_ID,PROCESS_ID,PROCESS_ORDER,PLAN_NUM,COMPLETE_NUM,USEABLE_NUM,OUTSOURCE_OUT_NUM,OUTSOURCE_IN_NUM,NG_NUM,REWORK_NUM,TESTING_NUM,STATUS_FLAGS,NG_NUM_TEM,TESTING_NUM_TEM,USE_REWORK_NUM,USE_REWORK_NUM_TEM,OK_OUTSOURCE_TEM,OK_NUM_TEM_APPROVAL,NG_NUM_TEM_APPROVAL,TESTING_NUM_TEM_APPROVAL,OK_INSOURCE_TEM,OK_INSOURCE_TEM_APPROVLA,EDL_FLAGS,SPLIT_FLAGS,PUSH_DOWN_NUM) "
			+ "values(#{taskId},#{stationId},#{serialNo},#{planNum},0,0,0,0,0,0,0,0,0,#{planNum},0,0,#{planNum},0,0,0,#{planNum},0,1,1,0)")
	public Integer addProcessDetails(@Param("taskId")Integer taskId,@Param("planNum")Integer planNum,@Param("stationId")String stationId,@Param("serialNo")String serialNo);

	@Select("select STATION_TYPES from c_mes_process_station_t where ID=#{id}")
	public String showStationTypeInfos(@Param("id")String id);

	@Select("select max(PROCESS_ORDER) from c_process_details_t where TASK_ID=#{taskId}")
	public Integer showMaxDetailsIdByTaskId(@Param("taskId")String taskId);

	@Select("select * from c_process_details_t where ID=#{id}")
	public Map<String,Object> showDetailsById(@Param("id")String id);

	@Select("select ID from c_process_details_t where TASK_ID=#{taskId} and PROCESS_ORDER=#{processOrder} and (SPLIT_FLAGS=1 or SPLIT_FLAGS=2)")
	public Integer showMaxId(@Param("taskId")String taskId,@Param("processOrder")String processOrder);

	@Select("select count(ID) from c_mes_process_production_way_t where ROUTING_ID=#{routeId}")
	public Integer countProductWayNum(@Param("routeId")String routeId);

	@Select("select * from c_mes_process_production_way_t where ROUTING_ID=#{routeId}")
	public List<Map<String,Object>> showProductWayByRouteId(@Param("routeId")String routeId);

	@Select("select max(SERIAL_NO) from c_mes_process_production_way_t where ROUTING_ID=#{routeId}")
	public Integer showProductWayId(@Param("routeId")String routeId);

	@Select("select ST_ID from c_mes_process_production_way_t where SERIAL_NO=#{serialNo} and ROUTING_ID=#{routeId}")
	public Integer showRouteId(@Param("serialNo")String serialNo,@Param("routeId")String routeId);

	@Select("select ST_ID from c_mes_process_production_way_t where ROUTING_ID=#{routeId}")
	public List<Integer> showAllStIdByWayId(@Param("routeId")String routeId);

	@Delete("delete from c_mes_process_production_way_t  where ST_ID=#{stationId} and ROUTING_ID=#{routeId} and SERIAL_NO=#{serialNo}")
	public Integer delWayProductInfos(@Param("stationId")String stationId,@Param("routeId")String routeId,@Param("serialNo")String serialNo);

	@Select("select * from  c_mes_process_production_way_t where  ROUTING_ID=#{routLineId} and SERIAL_NO>#{serialNo}")
	public List<Map<String,Object>> showProductWayBySTAndRoutes(@Param("routLineId")String routLineId,@Param("serialNo")String serialNo);


	@Delete("delete from c_mes_process_routing_t where ID=#{routeId}")
	public Integer delRouteLine(@Param("routeId")String routeId);

	@Select("select ST_ID from c_mes_process_production_way_t where ROUTING_ID=#{routeId} and SERIAL_NO=#{serialNo}")
	public Integer stId(@Param("routeId")String routeId,@Param("serialNo")String serialNo);

	@Delete("delete from c_process_production_task where ID=#{taskId}")
	public Integer delTaskInfos(@Param("taskId")String taskId);

	@Delete("delete from c_process_outsource_list where TASK_ID=#{taskId}")
	public Integer delOutsideListInfos(@Param("taskId")String taskId);

	@Delete("delete from c_process_details_t where TASK_ID=#{taskId}")
	public Integer delDetailsInfos(@Param("taskId")String taskId);

	@Delete("delete from c_process_details_t where TASK_ID=#{taskId} and PROCESS_ORDER=#{order}")
	public Integer delTaskDetailsInfo(@Param("taskId")String taskId,@Param("order")String order);

	@Update("update c_process_details_t set PUSH_DOWN_NUM=0,TESTING_NUM_TEM=0,NG_NUM=0,USEABLE_NUM=0,COMPLETE_NUM=0,TESTING_NUM=0,OK_OUTSOURCE_TEMS=0,NG_OUTSOURCE_TEMS=0  where TASK_ID=#{taskId} and PROCESS_ORDER=#{order}")
	public Integer updateInitData(@Param("taskId")String taskId,@Param("order")String order);

	@Select("select ID from  c_mes_process_production_way_t where ROUTING_ID=#{routeId} and SERIAL_NO=#{serialNo}")
	public String showProductWayIdxxs(@Param("routeId")String routeId,@Param("serialNo")String serialNo);















}
