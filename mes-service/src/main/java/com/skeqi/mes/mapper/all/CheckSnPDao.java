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
import com.skeqi.mes.pojo.api.CheckSnPT;
import com.skeqi.mes.pojo.api.UpdateReworkSnPT;

@Component
@MapperScan
public interface CheckSnPDao {
	///****根据条码查询工单ID***///
	@Select("SELECT WORK_ORDER_ID as tempWorkOrderId,PRODUCTION_ID as tempTrackingProductionId FROM r_mes_plan_print_t where sn=#{sn}")
	CheckSnPT findWorkId(@Param("sn")String sn);

	//**根据sn查询工艺路线id和配方id**//

	@Select("SELECT plan.*  "
			+ "FROM r_mes_plan_print_t print,"
			+ "r_mes_workorder_detail_t plan"
			+ " WHERE print.WORK_ORDER_ID = plan.ID "
			+ "AND print.sn = #{sn}")
	RMesPlanT findAllPlan(@Param("sn")String sn);

	@Select("SELECT COUNT(CMS.ID) FROM C_MES_STATION_T CMS JOIN C_MES_LINE_T CML ON CMS.LINE_ID=CML.ID WHERE CMS.STATION_NAME=#{station} AND CML.NAME=#{line}")
	Integer queryTempStationCountByLineAndStation(@Param("line") String line,@Param("station")  String station);


	//判断是否有对应的产品配置
	@Select("SELECT CMS.ID as tempStationId,CMS.STATION_INDEX as tempStationIndex,"
			+ "CMS.STATION_NAME as tempStationName,CMS.STATION_TYPE as tempStationType,"
			+ "CMS.STATION_AUTOORNOT as tempStationAutoornot,CMS.LINE_ID as tempStationLineId,"
			+ "CMS.STATION_ENDORNOT as tempStationEndornot "
			+ "FROM C_MES_STATION_T CMS JOIN C_MES_LINE_T CML ON CMS.LINE_ID=CML.ID WHERE CMS.STATION_NAME = #{station} AND CML.NAME=#{line}")
//	@Results(id="checkSnPTMap1",value = {
//			@Result(column="ID",property="tempStationId",javaType = Integer.class),
//			@Result(column="STATION_INDEX",property="tempStationIndex",javaType = Integer.class),
//			@Result(column="STATION_NAME",property="tempStationName",javaType = String.class),
//			@Result(column="STATION_TYPE",property="tempStationType",javaType = String.class),
//			@Result(column="STATION_AUTOORNOT",property="tempStationAutoornot",javaType = String.class),
//			@Result(column="LINE_ID",property="tempStationLineId",javaType = Integer.class),
//			@Result(column="STATION_ENDORNOT",property="tempStationEndornot",javaType = String.class)
//	})
	CheckSnPT queryCheckSnPTByLineAndStation1(@Param("line") String line,@Param("station")  String station);


	@Select(" SELECT COUNT(ID) " +
			" FROM P_MES_TRACKING_T" +
			" WHERE SN=#{snBarcode} "+
			" AND ST=#{station}")
	Integer queryTempTrackingCount(String snBarcode, String station);


	//**根据工艺id和工位id查询此工位是否在工艺路线内**//
	@Select("SELECT count(id) FROM "
			+ "c_mes_production_way_t "
			+ " WHERE ROUTING_ID = #{routingId}"
			+ " AND ST_ID=#{tempStationId}")
	Integer queryTempPwayCount(@Param("routingId")Integer routingId,@Param("tempStationId")Integer tempStationId);

	//**根据工艺路线id查询工艺路线的首站和末站**//
	@Select(" SELECT MAX(SERIAL_NO) as tempProductionSerialMax,"
			+ "MIN(SERIAL_NO) as tempProductionSerialMin "
			+ " FROM C_MES_PRODUCTION_WAY_T "
			+ "WHERE ROUTING_ID = #{routingId}")
	CheckSnPT queryTempProduction(@Param("routingId")Integer routingId);


	//**查询此工位是第几站**//
	@Select(" SELECT SERIAL_NO FROM C_MES_PRODUCTION_WAY_T "
			+ "WHERE ROUTING_ID = #{routingId}"
			+ " AND ST_ID=#{tempStationId}")
	Integer queryTempPwayStationSerialNo(@Param("routingId")Integer routingId, @Param("tempStationId")Integer tempStationId);


	@Select(" SELECT COUNT(ID) " +
			" FROM R_MES_TRACKING_T" +
			" WHERE SN=#{snBarcode}")
	Integer queryTempTrackingCount1(@Param("snBarcode")String snBarcode);


	@Select(" SELECT ID as tempTrackingId,ST as tempTrackingStationName,"
			+ "ENGINESN as tempTrackingEnginesn,GEARBOXSN as tempTrackingGearbosxn,"
			+ "STATUS as tempTrackingStatus,PRODUCTION_ID as tempTrackingProductionId,"
			+ "LINE_ID as tempStationLineId,REWORK_FLAG as tempTrackingReworkFlag" +
			" FROM R_MES_TRACKING_T" +
			" WHERE SN=#{snBarcode}")
//	@Results(id="checkSnPTMap5",value = {
//			@Result(column="ID",property="tempTrackingId",javaType = Integer.class),
//			@Result(column="ST",property="tempTrackingStationName",javaType = String.class),
//			@Result(column="ENGINESN",property="tempTrackingEnginesn",javaType = String.class),
//			@Result(column="GEARBOXSN",property="tempTrackingGearbosxn",javaType = String.class),
//			@Result(column="STATUS",property="tempTrackingStatus",javaType = String.class),
//			@Result(column="PRODUCTION_ID",property="tempTrackingProductionId",javaType = Integer.class),
//			@Result(column="LINE_ID",property="tempStationLineId",javaType = Integer.class),
//			@Result(column="REWORK_FLAG",property="tempTrackingReworkFlag",javaType = String.class)
//	})
	CheckSnPT queryCheckSnPT(@Param("snBarcode")String snBarcode);


	//**根据工艺路线id和工位id查询此工位是第几站**//
	@Select(" SELECT ST_ID as tempPwayStationId,SERIAL_NO as tempPwayStationSerialNo"
			+ " from c_mes_production_way_t "
			+ " WHERE ROUTING_ID = #{routingId}"
			+ " AND ST_ID=#{tempStationId}")
//	@Results(id="checkSnPTMap6",value = {
//			@Result(column="ST_ID",property="tempPwayStationId",javaType = Integer.class),
//			@Result(column="SERIAL_NO",property="tempPwayStationSerialNo",javaType = Integer.class)
//	})
	CheckSnPT queryCheckSnPT6(@Param("routingId")Integer routingId, @Param("tempStationId")Integer tempStationId);


	@Select(" SELECT station.STATION_NAME as tempPwayBeforeStationName,way.SERIAL_NO as tempPwayBeforeSerialNo"
			+ " FROM C_MES_PRODUCTION_WAY_T way,"
			+ "C_MES_ROUTING_T sta,"
			+ "C_MES_STATION_T station"
			+ " WHERE way.ROUTING_ID = #{routingId} "
			+ " and station.ID=way.ST_ID "
			+ " and sta.ID=way.ROUTING_ID"
			+ " AND sta.LINE_ID=#{tempStationLineId}"
			+ " AND way.SERIAL_NO=#{tempPwayStationSerialNo}")
//	@Results(id="checkSnPTMap7",value = {
//			@Result(column="ST_NAME",property="tempPwayBeforeStationName",javaType = String.class),
//			@Result(column="SERIAL_NO",property="tempPwayBeforeSerialNo",javaType = Integer.class)
//	})
	CheckSnPT queryCheckSnPT7(@Param("routingId")Integer routingId, @Param("tempStationLineId")Integer tempStationLineId, @Param("tempPwayStationSerialNo")Integer tempPwayStationSerialNo);


	@Select("SELECT count(datil.id) "
			+ "FROM c_mes_recipe_t recipe,"
			+ "c_mes_recipe_datil_t datil "
			+ "WHERE datil.RECIPE_ID = recipe.ID"
			+ " AND recipe.STATION_ID=#{stationId} "
			+ " AND recipe.TOTAL_ID=#{routingId}")
	Integer queryTempRecipeCount(@Param("routingId")Integer routingId, @Param("stationId")Integer stationId);


	@Insert(" INSERT INTO P_MES_STATION_PASS_T(DT,SN,ST,STATUS,PASS_FLAG,LINE_ID)VALUES(now(),#{snBarcode},#{tempStationName},'OK',1,#{tempStationLineId})")
	void insertStationPass(@Param("snBarcode")String snBarcode, @Param("tempStationName")String tempStationName, @Param("tempStationLineId")Integer tempStationLineId);

	@Select(" SELECT COUNT(ID) FROM R_MES_REWORK_WAY_T WHERE SN=#{snBarcode} AND ST_ID=#{tempStationId}")
	Integer queryReworkWay(@Param("snBarcode")String snBarcode, @Param("tempStationId")Integer tempStationId);


	@Select(" SELECT ST_ID as tempReworkStationId,SERIAL_NO as tempReworkStationSerialNo"
			+ " FROM R_MES_REWORK_WAY_T WHERE SN=#{snBarcode} AND ST_ID=#{tempStationId}")
//	@Results(id="checkSnPTMap8",value = {
//			@Result(column="ST_ID",property="tempReworkStationId",javaType = Integer.class),
//			@Result(column="SERIAL_NO",property="tempReworkStationSerialNo",javaType = Integer.class)
//	})
	CheckSnPT queryReworkWay1(@Param("snBarcode")String snBarcode, @Param("tempStationId")Integer tempStationId);


	@Select(" SELECT ST_NAME as tempReworkBeforeName,SERIAL_NO as tempReworkBeforeSerialNo"
			+ " FROM R_MES_REWORK_WAY_T WHERE SN=#{snBarcode} AND SERIAL_NO=#{tempReworkStationSerialNo}")
//	@Results(id="checkSnPTMap9",value = {
//			@Result(column="ST_NAME",property="tempReworkBeforeName",javaType = String.class),
//			@Result(column="SERIAL_NO",property="tempReworkBeforeSerialNo",javaType = Integer.class)
//	})
	CheckSnPT queryReworkWay2(@Param("snBarcode")String snBarcode, @Param("tempReworkStationSerialNo")Integer tempReworkStationSerialNo);

	@Select("SELECT count(datil.id) "
			+ "FROM c_mes_recipe_t recipe,"
			+ "c_mes_recipe_datil_t datil "
			+ "WHERE datil.RECIPE_ID = recipe.ID "
			+ " AND recipe.STATION_ID=#{stationId} "
			+ " AND recipe.TOTAL_ID=#{routingId}")
	Integer queryTempRecipeCount2(@Param("routingId")Integer routingId, @Param("stationId")Integer stationId);


	@Select(" SELECT COUNT(ID)" +
			" FROM P_MES_TRACKING_T" +
			" WHERE SN=#{snBarcode}")
	Integer queryTempPTrackingCout(@Param("snBarcode")String snBarcode);


	@Select(" SELECT ID as tempTrackingId,ST as tempTrackingStationName,"
			+ "ENGINESN as tempTrackingEnginesn,GEARBOXSN as tempTrackingGearbosxn,"
			+ "STATUS as tempTrackingStatus,PRODUCTION_ID as tempTrackingProductionId,"
			+ "LINE_ID as tempStationLineId" +
			" FROM R_MES_TRACKING_T" +
			" WHERE SN=#{snBarcode}")
//	@Results(id="checkSnPTMap5",value = {
//			@Result(column="ID",property="tempTrackingId",javaType = Integer.class),
//			@Result(column="ST",property="tempTrackingStationName",javaType = String.class),
//			@Result(column="ENGINESN",property="tempTrackingEnginesn",javaType = String.class),
//			@Result(column="GEARBOXSN",property="tempTrackingGearbosxn",javaType = String.class),
//			@Result(column="STATUS",property="tempTrackingStatus",javaType = String.class),
//			@Result(column="PRODUCTION_ID",property="tempTrackingProductionId",javaType = Integer.class),
//			@Result(column="LINE_ID",property="tempStationLineId",javaType = Integer.class)
//	})
	CheckSnPT queryCheckSnPT4(@Param("snBarcode")String snBarcode);

	@Select(" SELECT ID as tempTrackingId,ST as tempTrackingStationName,"
			+ "ENGINESN as tempTrackingEnginesn,GEARBOXSN as tempTrackingGearbosxn,"
			+ "STATUS as tempTrackingStatus,PRODUCTION_ID as tempTrackingProductionId,"
			+ "LINE_ID as tempStationLineId" +
			" FROM P_MES_TRACKING_T" +
			" WHERE SN=#{snBarcode}")
//	@Results(id="checkSnPTMap5",value = {
//			@Result(column="ID",property="tempTrackingId",javaType = Integer.class),
//			@Result(column="ST",property="tempTrackingStationName",javaType = String.class),
//			@Result(column="ENGINESN",property="tempTrackingEnginesn",javaType = String.class),
//			@Result(column="GEARBOXSN",property="tempTrackingGearbosxn",javaType = String.class),
//			@Result(column="STATUS",property="tempTrackingStatus",javaType = String.class),
//			@Result(column="PRODUCTION_ID",property="tempTrackingProductionId",javaType = Integer.class),
//			@Result(column="LINE_ID",property="tempStationLineId",javaType = Integer.class)
//	})
	CheckSnPT queryCheckSnPT4P(@Param("snBarcode")String snBarcode);


//	@Options(useGeneratedKeys = true, keyProperty = "id",keyColumn="ID")
	@Insert(" INSERT INTO R_MES_TRACKING_T("
			+ "DT,ST,SN,ENGINESN,GEARBOXSN,STATUS,PLAN_ID,REWORK_FLAG,PRODUCTION_ID,LINE_ID,WORK_ORDER_ID) "
			+ "VALUES("
			+ "now(),#{station},#{snBarcode},'0',#{tempStationIndex},'OK',#{tempPlanId},"
			+ "'0',#{tempTrackingProductionId},#{tempStationLineId},#{tempWorkOrderId})")
	Integer insertTracking(@Param("station")String station,@Param("snBarcode")String snBarcode,
			@Param("tempStationIndex")Integer tempStationIndex,
			@Param("tempPlanId")Integer tempPlanId,
			@Param("tempTrackingProductionId")Integer tempTrackingProductionId,
			@Param("tempStationLineId")Integer tempStationLineId,
			@Param("tempWorkOrderId")Integer tempWorkOrderId);


	@Insert(" INSERT INTO R_MES_TRACKING_T "
			+ "(DT,ST,SN,ENGINESN,GEARBOXSN,STATUS,PLAN_ID,REWORK_FLAG,PRODUCTION_ID,LINE_ID,WORK_ORDER_ID)"
			+ "VALUES(now(),#{station},#{snBarcode},'0',#{tempStationIndex},'OK',"
			+ "#{tempPlanId},'0',#{tempTrackingProductionId},#{tempStationLineId},#{tempWorkOrderId})" )
	void insertTracking2(@Param("snBarcode")String snBarcode, @Param("tempStationIndex")Integer tempStationIndex, @Param("tempPlanId")Integer tempPlanId,
			@Param("tempTrackingProductionId")Integer tempTrackingProductionId, @Param("tempStationLineId")Integer tempStationLineId, @Param("tempWorkOrderId")Integer tempWorkOrderId);


	@Update("UPDATE r_mes_workorder_detail_t SET ONLINE_NUMBER=#{tempOnlineNumber} WHERE ID=#{tempPlanId}")
	void updatePlan(@Param("tempOnlineNumber")int tempOnlineNumber, @Param("tempPlanId")Integer tempPlanId);

	@Select("SELECT ONLINE_NUMBER FROM r_mes_workorder_detail_t WHERE ID=#{tempPlanId}")
	Integer queryTempOnlineNumber(@Param("tempPlanId")Integer tempPlanId);


	@Select(" SELECT COUNT(ID) FROM R_MES_REWORK_WAY_T WHERE SN=#{snBarcode} AND ST_ID=#{tempStationId}")
	Integer queryTempReworkWayCount(@Param("snBarcode")String snBarcode, @Param("tempStationId")Integer tempStationId);


	@Update("UPDATE R_MES_TRACKING_T SET ST='',REWORK_FLAG='1' WHERE ID=#{tempTrackingId}")
	void updateTracking(@Param("tempTrackingId")Integer tempTrackingId);


	@Select(" SELECT COUNT(ID) as TEMP_PWAY_COUNT FROM C_MES_PRODUCTION_WAY_T"
			+ " WHERE ROUTING_ID=#{routingId}"
			+ " AND ST_ID=#{tempStationId}")
	Integer queryPwayCount(@Param("routingId")Integer routingId, @Param("tempStationId")Integer tempStationId);


	@Select("SELECT ONLINE_NUMBER  FROM R_MES_WORKORDER_DETAIL_T WHERE ID=#{tempWorkOrderId}")
	Integer queryTempOrderOffline(@Param("tempWorkOrderId")Integer tempWorkOrderId);


	@Update("UPDATE R_MES_WORKORDER_DETAIL_T SET ONLINE_NUMBER=#{tempWorkOrderOffline} WHERE ID=#{tempWorkOrderId}")
	void updateWorkorderDetail(@Param("tempWorkOrderOffline")int tempWorkOrderOffline, @Param("tempWorkOrderId")Integer tempWorkOrderId);



	/**
	 * @param snBarconde
	 * @return
	 */
	@Select("select count(id) tempTrackingCount from r_mes_tracking_t where sn=#{snBarconde}")
	public UpdateReworkSnPT find1(String snBarconde);

	/**
	 * @param snBarconde
	 * @return
	 */
	@Select("select id as tempTrackingId,st as tempTrackingStationName,enginesn as tempTrackingEnginesn ,gearboxsn as tempTrackingGearbosxn ,"
			+ "status as tempTrackingStatus ,production_id as tempTrackingProductionId ,line_id as tempStationLineId,"
			+ "plan_id as tempPlanId,dt as tempDt,rework_flag as tempReworkFlag,work_order_id as tempWorkOrderId from r_mes_tracking_t "
			+ "where sn=#{snBarconde}")
	public UpdateReworkSnPT find2(String snBarconde);

	/**
	 * @param snBarconde
	 * @return
	 */
	@Select("select count(id) tempPTrackingCout from p_mes_tracking_t where sn=#{snBarconde}")
	public UpdateReworkSnPT find3(String snBarconde);

	@Select("select count(id) tempTrackingCount from r_mes_tracking_t where sn=#{snBarconde}")
	public UpdateReworkSnPT find4(String snBarconde);

	@Select("select count(id) tempPTrackingCout from p_mes_tracking_t where sn=#{snBarconde}")
	public UpdateReworkSnPT find5(String snBarconde);

	/**
	 * @param snBarconde
	 */
	@Update("update r_mes_tracking_t set enginesn='4',rework_flag='1' where sn=#{snBarconde}")
	public void update1(String snBarconde);

	/**
	 * @param snBarconde
	 */
	@Update("update p_mes_tracking_t set enginesn='4',rework_flag='1' where sn=#{snBarconde}")
	public void update2(String snBarconde);

	/**
	 * @param dx
	 */
	@Insert("insert into p_mes_tracking_t(dt,st,sn,enginesn,gearboxsn,"
			+ "status,plan_id,offline_dt,rework_flag,production_id," + "line_id,work_order_id)"
			+ "#{tempDt},#{stationname}," + "#{snBarconde},#{tempTrackingEnginesn},"
			+ "#{tempTrackingGearbosxn},#{tempTrackingStatus}," + "#{tempPlanId},NOW(),#{tempReworkFlag},"
			+ "#{tempTrackingProductionId},#{tempStationLineId}," + "#{tempWorkOrderId}")
	public void insert1(UpdateReworkSnPT dx);

	@Select("select * from p_mes_keypart_t where sn=#{snBarconde}")
	public List<UpdateReworkSnPT> insert2Find(@Param("snBarconde")String snBarconde);

	@Insert("insert into r_mes_keypart_t(dt,transfer,keypart_mode,st,sn,wid,tid,second_num,keypart_id,keypart_name,keypart_pn,keypart_num,keypart_module,keypart_rework_fg,keypart_rework_st,material_id,material_number)"
			+ "value(#{dt},#{transfer},#{keypartMode},#{st},#{sn},#{wid},#{tid},#{secondNum},#{keypartId},#{keypartName},#{keypartPn},#{keypartNum},#{keypartModule},#{keypartReworkFg},#{keypartReworkSt},#{materialId},#{materialNumber})")
	public void insert2(UpdateReworkSnPT dx);

	@Select("select * from p_mes_bolt_t where sn=#{snBarconde}")
	public List<UpdateReworkSnPT> insert3Find(@Param("snBarconde")String snBarconde);

	@Insert("insert into r_mes_bolt_t(DT,TRANSFER,BOLT_MODE,SN,ST,T,A,P,C,R,EQS,T_LIMIT,A_LIMIT,WID,TID,BOLT_NUM,BOLT_NAME,REWORK_FLAG,REWORK_ST,GUN_NO) value(#{dt},#{transfer},#{boltMode},#{sn},#{st},#{t},#{a},#{p},#{c},#{r},#{eqs},#{tLimit},#{aLimit},#{wid},#{tid},#{boltNum},#{boltName},#{reworkFlag},#{reworkSt},#{gunNo})")
	public void insert3(UpdateReworkSnPT dx);

	@Select("select * from p_mes_leakage_t where sn=#{snBarconde}")
	public List<UpdateReworkSnPT> insert4Find(@Param("snBarconde")String snBarconde);

	@Insert("insert into r_mes_leakage_t(DT,ST,SN,LEAKAGE_NAME,LEAKAGE_PV,LEAKAGE_LV,LEAKAGE_R,WID,TRANSFER,LEAKAGE_MODE,REWORK_FLAG,REASON)"
			+ "value(#{dt},#{st},#{sn},#{leakageName},#{leakagePv},#{leakageLv},#{leakageR},#{wId},#{transfer},#{leakageMode},#{reworkFlag},#{reason})")
	public void insert4(UpdateReworkSnPT dx);

	/**
	 * @param snBarconde
	 */
	@Delete("delete from r_mes_step_t where  sn=#{snBarconde}")
	public void delete1(String snBarconde);

	/**
	 * @param snBarconde
	 */
	@Delete("delete from r_mes_tracking_t where sn=#{snBarconde}")
	public void delete2(String snBarconde);

	/**
	 * @param snBarconde
	 */
	@Delete("delete from r_mes_keypart_t where sn=#{snBarconde}")
	public void delete3(String snBarconde);

	/**
	 * @param snBarconde
	 */
	@Delete("delete from r_mes_bolt_t where sn=#{snBarconde}")
	public void delete4(String snBarconde);

	/**
	 * @param snBarconde
	 */
	@Delete("delete from r_mes_leakage_t where sn=#{snBarconde}")
	public void delete5(String snBarconde);

	/**
	 * @param snBarconde
	 */
	@Delete("delete from r_mes_step_t where  sn=#{snBarconde}")
	public void delete6(String snBarconde);

	/**
	 * @param snBarconde
	 */
	@Delete("delete from r_mes_tracking_t where sn=#{snBarconde}")
	public void delete7(String snBarconde);

	/**
	 * @param snBarconde
	 */
	@Delete("delete from r_mes_keypart_t where sn=#{snBarconde}")
	public void delete8(String snBarconde);

	/**
	 * @param snBarconde
	 */
	@Delete("delete from r_mes_bolt_t where sn=#{snBarconde}")
	public void delete9(String snBarconde);

	/**
	 * @param snBarconde
	 */
	@Delete("delete from r_mes_leakage_t where sn=#{snBarconde}")
	public void delete10(String snBarconde);

	/**
	 * @param snBarconde
	 * @return
	 */
	@Select("select id as tempTrackingId,st as tempTrackingStationName,"
			+ "bst as tempTrackingBst,enginesn as tempTrackingEnginesn,"
			+ "gearboxsn as tempTrackingGearbosxn,status as tempTrackingStatus ,"
			+ "production_id as tempTrackingProductionId ,line_id as tempStationLineId,"
			+ "plan_id as tempPlanId,dt as tempDt,rework_flag as tempReworkFlag,"
			+ "work_order_id as tempWorkOrderId from p_mes_tracking_t" + " where sn=#{snBarconde}")
	public UpdateReworkSnPT find6(String snBarconde);

	/**
	 * @param dx
	 */
	@Insert("insert into r_mes_tracking_t (dt,st,bst,sn,enginesn,gearboxsn,status,plan_id,"
			+ "rework_flag,production_id,line_id,work_order_id)"
			+ "values(now(),#{stationname},#{tempTrackingBst},#{snBarconde},'5',#{tempTrackingGearbosxn},"
			+ "#{tempTrackingStatus},#{tempPlanId},'1',#{tempTrackingProductionId},#{tempStationLineId},"
			+ "#{tempWorkOrderId})")
	public void insert5(UpdateReworkSnPT dx);

	/**
	 * @param snBarconde
	 */
	@Delete("delete from p_mes_keypart_t where sn=#{snBarconde}")
	public void delete11(String snBarconde);

	/**
	 * @param snBarconde
	 */
	@Delete("delete from p_mes_bolt_t where sn=#{snBarconde}")
	public void delete12(String snBarconde);

	/**
	 * @param snBarconde
	 */
	@Delete("delete from p_mes_leakage_t where sn=#{snBarconde}")
	public void delete13(String snBarconde);

	@Update("update r_mes_tracking_t set STATUS='OK' where sn=#{sn}")
	public void updateRMesReworkSn(@Param("sn") String sn);

	@Select("select count(*) from r_mes_station_serial_flag_t where SN=#{snBarcode}")
	Integer queryStationDerialFlagCount(Map<String, Object> map);

	@Select("select *,#{snBarcode} as SN from c_mes_production_way_t where ROUTING_ID=#{routingId}")
	List<Map<String, Object>> queryStationWayList(Map<String, Object> map);

	@Insert("<script>" +
		"insert into r_mes_station_serial_flag_t (SN, DT, ST, SERIAL) " +
		"values " +
		"<foreach collection='list' item='item' index='index' separator=','>" +
		"(#{item.SN}, now(), #{item.ST_ID}, #{item.SERIAL_NO})" +
		"</foreach>" +
		"</script>")
	Integer insertStationDerialFlag(@Param("list") List<Map<String, Object>> list);

	@Select("select count(*) from r_mes_station_serial_flag_t where SN=#{SN} and SERIAL=#{SERIAL}")
	Integer queryFlagTotalCount(Map<String, Object> map);

	@Select("select count(*) from r_mes_station_serial_flag_t where SN=#{SN} and SERIAL=#{SERIAL} and FLAG=1")
	Integer queryFlagCompleteCount(Map<String, Object> map);

}
