package com.skeqi.mes.mapper.all;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

import com.skeqi.mes.pojo.RMesPlanT;
import com.skeqi.mes.pojo.api.UpdateSnPT;

/**
 * @author Yinp
 * @date 2020年1月13日
 */
@Component
@MapperScan
public interface UpdateSnPDao {
	//**根据工单ID查询下线数量***///
	@Select("SELECT OFFLINE_NUMBER FROM r_mes_workorder_detail_t where id=#{Id}")
	Integer findOfflineNumber(@Param("Id")Integer id);


	//**根据sn查询工艺路线id和配方id**//
	/**
	 *
	* @author FQZ
	* @date 2020年4月2日上午9:01:32
	 */
	@Select("SELECT plan.*  "
			+ "FROM r_mes_plan_print_t print,"
			+ "r_mes_workorder_detail_t plan"
			+ " WHERE print.WORK_ORDER_ID = plan.ID "
			+ "AND print.sn = #{sn}")
	RMesPlanT findAllPlan(@Param("sn")String sn);

	/**
	 * @param stationName
	 * @param lineId
	 * @return tempStationCount
	 */
	@Select("select count( cms.id) tempStationCount from c_mes_station_t cms,c_mes_line_t cml "
			+ "where cms.station_name=#{stationName} "
			+ "and cml.name=#{lineName} "
			+ "and cms.line_id=cml.id")
	public UpdateSnPT find1(@Param("stationName")String stationName,
			@Param("lineName")String lineName);

	@Select("select cms.id as tempStationId,"
			+ "cms.station_index as tempStationIndex,"
			+ "cms.station_name as tempStationName,"
			+ "cms.station_type as tempStationType,"
			+ "cms.station_autoornot as tempStationAutoornot,"
			+ "cms.line_id as tempStationLineId,"
			+ "cms.station_endornot as tempStationEndornot "
			+ "from c_mes_station_t cms,c_mes_line_t cml  "
			+ "where cms.line_id=cml.id "
			+ "and cms.station_name=#{stationname} "
			+ "and cml.name=#{lineName}")
	public UpdateSnPT find2(@Param("stationname")String stationname,
			@Param("lineName")String lineName);

	@Insert("insert into p_mes_station_pass_t("
			+ "dt,sn,st,status,pass_flag,line_id)"
			+ "values(now(),#{snBarconde},#{tempStationName},'ok',2,#{tempStationLineId});")
	public Integer insert1(UpdateSnPT dx);

	@Delete("delete from r_mes_step_t "
			+ "where st=#{tempStationName} "
			+ "and sn=#{snBarconde} "
			+ "and line_name=#{lineName}")
	public Integer delete1(@Param("tempStationName")String tempStationName,
			@Param("snBarconde")String snBarconde,
			@Param("lineName")String lineName);

	@Delete("delete from r_mes_keypart_t "
			+ "where sn=#{snBarconde}")
	public Integer delete2(@Param("snBarconde")String snBarconde);

	@Delete("delete from r_mes_bolt_t "
			+ "where sn=#{snBarconde}")
	public Integer delete3(@Param("snBarconde")String snBarconde);

	@Delete("delete from r_mes_leakage_t "
			+ "where sn=#{snBarconde}")
	public Integer delete4(@Param("snBarconde")String snBarconde);

	@Select("select count(id) tempTrackingCount from r_mes_tracking_t where sn=#{snBarconde}")
	public UpdateSnPT find3(@Param("snBarconde")String snBarconde);

	@Select("select id as tempTrackingId,st as tempTrackingStationName,"
			+ "enginesn as tempTrackingEnginesn,gearboxsn as tempTrackingGearbosxn,"
			+ "status as tempTrackingStatus,production_id as tempTrackingProductionId,"
			+ "line_id as tempStationLineId,work_order_id as tempPlanId,dt as tempDt,"
			+ "rework_flag as tempReworkFlag,work_order_id as tempWorkOrderId "
			+ "from r_mes_tracking_t "
			+ "where sn=#{snBarconde} limit 1")
	public UpdateSnPT find4(@Param("snBarconde")String snBarconde);

	@Select(" SELECT MAX(SERIAL_NO) as tempProductionSerialMax,"
			+ "MIN(SERIAL_NO) as tempProductionSerialMin "
			+ " FROM C_MES_PRODUCTION_WAY_T "
			+ "WHERE ROUTING_ID = #{routingId}")
	public UpdateSnPT find5(@Param("routingId")Integer routingId);

	@Select(" SELECT SERIAL_NO as tempPwayStationSerial_no FROM C_MES_PRODUCTION_WAY_T "
			+ "WHERE ROUTING_ID = #{routingId}"
			+ " AND ST_ID=#{tempStationId}")
	public UpdateSnPT find6(@Param("routingId")Integer routingId,
			@Param("tempStationId")String tempStationId);

	@Update("update r_mes_tracking_t set "
			+ "st=#{tempStationName},status=#{tempTrackingStatus},"
			+ "gearboxsn=#{tempStationIndex} "
			+ "where sn=#{snBarconde}")
	public Integer update1(@Param("tempStationName")String tempStationName,
			@Param("tempTrackingStatus")String tempTrackingStatus,
			@Param("tempStationIndex")String tempStationIndex,
			@Param("snBarconde")String snBarconde);

	@Insert("insert into p_mes_station_pass_t("
			+ "dt,sn,st,status,pass_flag,line_id)"
			+ "values(now(),#{snBarconde},#{tempStationName},'ok',2,#{tempStationLineId});")
	public Integer insert2(@Param("snBarconde")String snBarconde,
			@Param("tempStationName")String tempStationName,
			@Param("tempStationLineId")String tempStationLineId);

	@Delete("delete from r_mes_step_t "
			+ "where st=#{tempStationName} "
			+ "and sn=#{snBarconde} "
			+ "and line_name=#{lineName}")
	public Integer delete5(@Param("tempStationName")String tempStationName,
			@Param("snBarconde")String snBarconde,
			@Param("lineName")String lineName);

	@Insert("insert into p_mes_station_pass_t(dt,sn,st,status,pass_flag,line_id)"
			+ "values(NOW(),#{snBarconde},#{tempStationName},'ok',2,#{tempStationLineId})")
	public Integer insert3(@Param("snBarconde")String snBarconde,
			@Param("tempStationName")String tempStationName,
			@Param("tempStationLineId")String tempStationLineId);

	@Insert("insert into p_mes_tracking_t("
			+ "dt,st,sn,enginesn,gearboxsn,status,plan_id,offline_dt,"
			+ "rework_flag,production_id,line_id,work_order_id)"
			+ "values(#{tempDt},#{stationname},#{snBarconde},"
			+ "#{tempTrackingEnginesn},#{tempTrackingGearbosxn},"
			+ "#{tempTrackingStatus},#{tempPlanId},NOW(),#{tempReworkFlag},"
			+ "#{tempTrackingProductionId},#{tempStationLineId},#{tempWorkOrderId})")
	public Integer insert4(UpdateSnPT dx);

	@Select("select ORDER_NUMBER as tempPlanNumber,complete_number as tempCompletePlanCout,"
			+ "ok_number as tempCompleteOkPlanCount from r_mes_workorder_detail_t "
			+ "where id=#{tempPlanId}")
	public UpdateSnPT find7(@Param("tempPlanId")String tempPlanId);

	@Update("update r_mes_workorder_detail_t "
			+ "set offline_number=(#{tempWorkOrderOffline}+1),status='2',"
			+ "complete_number=(#{tempCompletePlanCout}+1),"
			+ "ok_number=(#{tempCompleteOkPlanCount}+1) "
			+ "where id=#{tempWorkOrderId}")
	public Integer update2(@Param("tempWorkOrderOffline")String tempWorkOrderOffline,
			@Param("tempCompletePlanCout")String tempCompletePlanCout,
	@Param("tempCompleteOkPlanCount")String tempCompleteOkPlanCount,
			@Param("tempWorkOrderId")String tempWorkOrderId);

	@Update("update r_mes_plan_t "
			+ "set complete_number=(#{tempCompletePlanCout}+1),"
			+ "ok_number=(#{tempCompleteOkPlanCount}+1),"
			+ "complete_flag='4' where id=#{tempPlanId}")
	public Integer update3(@Param("tempCompletePlanCout")String tempCompletePlanCout,
			@Param("tempCompleteOkPlanCount")String tempCompleteOkPlanCount,
			@Param("tempPlanId")String tempPlanId);

	@Insert("insert into p_mes_tracking_t("
			+ "dt,st,sn,enginesn,gearboxsn,status,"
			+ "plan_id,offline_dt,rework_flag,production_id,"
			+ "line_id,work_order_id)"
			+ "values(#{tempDt},#{stationname},#{snBarconde},"
			+ "#{tempTrackingEnginesn},#{tempTrackingGearbosxn},"
			+ "#{tempTrackingStatus},#{tempPlanId},NOW(),#{tempReworkFlag},"
			+ "#{tempTrackingProductionId},#{tempStationLineId},#{tempWorkOrderId})")
	public Integer insert5(UpdateSnPT dx);

	@Select("select work_order_id as workId from r_mes_plan_print_t "
			+ "where sn=#{snBarconde}")
	public UpdateSnPT find8(@Param("snBarconde")String snBarconde);

	@Delete("delete from r_mes_plan_t where id=#{tempPlanId}")
	public Integer delete6(@Param("tempPlanId")String tempPlanId);

	@Delete("delete from r_mes_plan_print_t where WORK_ORDER_ID=#{snBarconde}")
	public Integer delete7(@Param("snBarconde")String snBarconde);

	@Delete("delete from r_mes_workorder_detail_t where ID=#{workId}")
	public Integer delete8(@Param("workId")Integer workId);

	@Update("update r_mes_workorder_detail_t "
			+ "set complete_number=(#{tempCompletePlanCout}+1),"
			+ "ok_number=(#{tempCompleteOkPlanCount}+1) "
			+ "where id=#{tempPlanId}")
	public Integer update4(@Param("tempCompletePlanCout")String tempCompletePlanCout,
			@Param("tempCompleteOkPlanCount")String tempCompleteOkPlanCount,
			@Param("tempPlanId")String tempPlanId);

	@Select("select order_number as tempWorkOrderNumber, offline_number as tempWorkOrderOffline from r_mes_workorder_detail_t "
			+ "where id=#{tempWorkOrderId}")
	public UpdateSnPT find9(@Param("tempWorkOrderId")String tempWorkOrderId);

	@Update("update r_mes_workorder_detail_t "
			+ "set offline_number=(#{tempWorkOrderOffline}+1),"
			+ "status='2' where id=#{tempWorkOrderId}")
	public Integer update5(@Param("tempWorkOrderOffline")String tempWorkOrderOffline,
			@Param("tempWorkOrderId")String tempWorkOrderId);

	@Update("update r_mes_workorder_detail_t "
			+ "set offline_number=(#{tempWorkOrderOffline}+1) "
			+ "where id=#{tempWorkOrderId}")
	public Integer update6(@Param("tempWorkOrderOffline")String tempWorkOrderOffline,
			@Param("tempWorkOrderId")String tempWorkOrderId);

	@Insert("insert into p_mes_tracking_t("
			+ "dt,st,sn,enginesn,gearboxsn,"
			+ "status,plan_id,offline_dt,"
			+ "rework_flag,production_id,"
			+ "line_id,work_order_id)"
			+ "values(#{tempDt},#{stationname},"
			+ "#{snBarconde},#{tempTrackingEnginesn},"
			+ "#{tempTrackingGearbosxn},#{tempTrackingStatus},"
			+ "#{tempPlanId},NOW(),#{tempReworkFlag},"
			+ "#{tempTrackingProductionId},"
			+ "#{tempStationLineId},#{tempWorkOrderId})")
	public Integer insert6(UpdateSnPT dx);

	@Insert("insert into p_mes_tracking_t("
			+ "dt,st,sn,enginesn,gearboxsn,status,"
			+ "plan_id,offline_dt,rework_flag,production_id,"
			+ "line_id,work_order_id)"
			+ "VALUES(#{tempDt},#{stationname},"
			+ "#{snBarconde},#{tempTrackingEnginesn},"
			+ "#{tempTrackingGearbosxn},#{tempTrackingStatus},"
			+ "#{tempPlanId},NOW(),#{tempReworkFlag},"
			+ "#{tempTrackingProductionId},"
			+ "#{tempStationLineId},#{tempWorkOrderId})")
	public Integer insert7(UpdateSnPT dx);

	@Delete("delete from r_mes_step_t "
			+ "where st=#{tempStationName} "
			+ "and sn=#{snBarconde} "
			+ "and line_name=#{lineName}")
	public Integer delete9(@Param("tempStationName")String tempStationName,
			@Param("snBarconde")String snBarconde,
			@Param("lineName")String lineName);

	@Delete("delete from r_mes_tracking_t "
			+ "where sn=#{snBarconde}")
	public Integer delete10(@Param("snBarconde")String snBarconde);

	@Delete("delete from r_mes_keypart_t "
			+ "where sn=#{snBarconde}")
	public Integer delete11(@Param("snBarconde")String snBarconde);

	@Delete("delete from r_mes_bolt_t "
			+ "where sn=#{snBarconde}")
	public Integer delete12(@Param("snBarconde")String snBarconde);

	@Delete("delete from r_mes_leakage_t "
			+ "where sn=#{snBarconde}")
	public Integer delete13(@Param("snBarconde")String snBarconde);

	@Insert("insert into p_mes_station_pass_t("
			+ "dt,sn,st,status,pass_flag,line_id)"
			+ "values(now(),#{snBarconde},#{tempStationName},'ok',2,#{tempStationLineId})")
	public Integer insert8(@Param("snBarconde")String snBarconde,
			@Param("tempStationName")String tempStationName,
			@Param("tempStationLineId")String tempStationLineId);

	@Update("update r_mes_tracking_t set "
			+ "st=#{tempStationName},"
			+ "status=#{tempTrackingStatus},"
			+ "gearboxsn=#{tempStationIndex} "
			+ "where sn=#{snBarconde}")
	public Integer update7(@Param("tempStationName")String tempStationName,
			@Param("tempTrackingStatus")String tempTrackingStatus,
			@Param("tempStationIndex")String tempStationIndex,
			@Param("snBarconde")String snBarconde);

	@Delete("delete from r_mes_step_t "
			+ "where st=#{tempStationName} "
			+ "and sn=#{snBarconde} "
			+ "and line_name=#{lineName}")
	public Integer delete14(@Param("tempStationName")String tempStationName,
			@Param("snBarconde")String snBarconde,
			@Param("lineName")String lineName);

	@Select(" SELECT MAX(SERIAL_NO) as tempProductionSerialMax,"
			+ "MIN(SERIAL_NO) as tempProductionSerialMin "
			+ " FROM C_MES_PRODUCTION_WAY_T "
			+ "WHERE ROUTING_ID = #{routingId}")
	public UpdateSnPT find10(@Param("routingId")Integer routingId);

	@Select(" SELECT SERIAL_NO as tempPwayStationSerial_no FROM C_MES_PRODUCTION_WAY_T "
			+ "WHERE ROUTING_ID = #{routingId}"
			+ " AND ST_ID=#{tempStationId}")
	public UpdateSnPT find11(@Param("routingId")Integer routingId,
			@Param("tempStationId")String tempStationId);

	@Update("update r_mes_tracking_t set "
			+ "st=#{tempStationName},"
			+ "status='ok',"
			+ "gearboxsn=#{tempStationIndex} "
			+ "where sn=#{snBarconde}")
	public Integer update8(@Param("tempStationName")String tempStationName,
			@Param("tempStationIndex")String tempStationIndex,
			@Param("snBarconde")String snBarconde);

	@Insert("insert into p_mes_station_pass_t("
			+ "dt,sn,st,status,pass_flag,line_id)"
			+ "values(now(),#{snBarconde},#{tempStationName},'ok',2,#{tempStationLineId})")
	public Integer insert9(@Param("snBarconde")String snBarconde,
			@Param("tempStationName")String tempStationName,
			@Param("tempStationLineId")String tempStationLineId);

	@Delete("delete from r_mes_step_t "
			+ "where st=#{tempStationName} "
			+ "and sn=#{snBarconde} "
			+ "and line_name=#{lineName}")
	public Integer delete15(@Param("tempStationName")String tempStationName,
			@Param("snBarconde")String snBarconde,
			@Param("lineName")String lineName);

	@Insert("insert into p_mes_station_pass_t("
			+ "dt,sn,st,status,pass_flag,line_id)"
			+ "values(NOW(),#{snBarconde},#{tempStationName},#{tempTrackingStatus},2,#{tempStationLineId})")
	public Integer insert10(@Param("snBarconde")String snBarconde,
			@Param("tempStationName")String tempStationName,
			@Param("tempTrackingStatus")String tempTrackingStatus,
			@Param("tempStationLineId")String tempStationLineId);

	@Update("update r_mes_tracking_t set "
			+ "st=#{tempStationName},"
			+ "gearboxsn=#{tempStationIndex} "
			+ "where sn=#{snBarconde}")
	public Integer update9(@Param("tempStationName")String tempStationName,
			@Param("tempStationIndex")String tempStationIndex,
			@Param("snBarconde")String snBarconde);

	@Delete("delete from r_mes_step_t "
			+ "where st=#{tempStationName} "
			+ "and sn=#{snBarconde} "
			+ "and line_name=#{lineName}")
	public Integer delete16(@Param("tempStationName")String tempStationName,
			@Param("snBarconde")String snBarconde,
			@Param("lineName")String lineName);

	@Select(" SELECT * FROM c_mes_production_t where ID=#{productionId} ")
	List<Map<String, Object>> getProduction(Map<String, Object> map);

	@Select("select * from r_mes_workorder_detail_t where ID=#{workorderId}")
	List<Map<String, Object>> getWorkorder(Map<String, Object> map);

	@Insert("insert into c_mes_material_instance_t (MATERIAL_CODE, MATERIAL_SN, MATERIAL_NAME, MATERIAL_TYPE, MATERIAL_NUMBER, DT, ALTER_DT, WORKORDER_ID, NUMBER_REMAINING)"
			+ "values(#{MATERIAL_CODE}, #{SN}, #{MATERIAL_NAME}, 6, 1, NOW(), NOW(), #{WORKORDER_ID}, 0)")
	Integer insertMaterialInstance(Map<String, Object> map);

	@Update("update r_mes_station_serial_flag_t set FLAG=1 where SN=#{SN} and ST=#{ST}")
	Integer updateStationSerialFlag(Map<String, Object> map);


}
