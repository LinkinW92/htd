package com.skeqi.mes.mapper.all;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

import com.skeqi.mes.pojo.api.CheckAllRecipePT;
import com.skeqi.mes.pojo.api.CheckClientLoginPT;
import com.skeqi.mes.pojo.api.CheckPlanSnPT;
import com.skeqi.mes.pojo.api.CheckProductionPT;

@Component
@MapperScan
public interface CheckAllRecipePDao {

	//*****//查询此sn的默认工艺路线和总配方
	@Select("select id from c_mes_total_recipe_t where PRODUCTION_ID=#{pid} and LINE_ID=#{lineId} and STATUS=1")
	public Integer findDefaultTotal(@Param("lineId")Integer lineId,@Param("pid")Integer pid);
	@Select("select id from c_mes_routing_t where PRODUCTION_ID=#{pid} and LINE_ID=#{lineId} and default_route=1")
	public Integer findDefaultRouting(@Param("lineId")Integer lineId,@Param("pid")Integer pid);
	@Select("select ST_ID as stId  from c_mes_production_way_t where ROUTING_ID=#{tid}")
	public List<CheckAllRecipePT> findStId(@Param("tid")Integer id);
	@Select("select id from c_mes_line_t where name=#{name}")
	public Integer findLineId(@Param("name")String lineName);

	@Select("SELECT plan.ID as id,plan.TOTAL_RECIPE_ID as totalRecipeId,plan.ROUTING_ID as routingId,pro.ST_ID as stId "
			+ " FROM r_mes_plan_print_t print,"
			+ "r_mes_workorder_detail_t plan,"
			+ "c_mes_routing_t routing,"
			+ "c_mes_production_way_t pro "
			+ " WHERE print.WORK_ORDER_ID = plan.ID "
			+ "AND routing.ID = plan.ROUTING_ID "
			+ "AND routing.id = pro.ROUTING_ID "
			+ "AND print.sn = #{sn} ")

	List<CheckAllRecipePT> queryProductionWayList(@Param("sn")String sn,@Param("productionLine")Integer productionLine, @Param("productionId")Integer productionId);

	@Select("SELECT count(datil.id) "
			+ "FROM c_mes_recipe_t recipe,"
			+ "c_mes_recipe_datil_t datil "
			+ " WHERE datil.RECIPE_ID = recipe.ID"
			+ " AND recipe.STATION_ID=#{stationId} "
			+ " AND recipe.TOTAL_ID=#{routingId}")
	Integer queryCountId(@Param("routingId")Integer routingId,@Param("productionId")Integer productionId, @Param("stationId")Integer stationId);

	//客户端登录校验
	@Select("SELECT COUNT(*)" +
			" INTO TEMP_EMP_COUNT" +
			" FROM C_MES_EMP_T E" +
			" WHERE E.EMP_NAME=#{username} AND E.EMP_VR=#{password}")
	Integer queryEmpByNameAndVr(@Param("username") String username,@Param("password") String password);


	@Select(" SELECT ID as tempEmpId,STATION_ID as tempEmpStationId,"
			+ "LINE_ID as tempEmpLineId,EMP_TYPE as tempEmpType" +
			" FROM C_MES_EMP_T E" +
			" WHERE E.EMP_NAME=#{username} AND E.EMP_VR=#{password}")
//	@Results(id="checkClientLoginMap1",value = {
//			@Result(column="ID",property="tempEmpId",javaType = Integer.class),
//			@Result(column="STATION_ID",property="tempEmpStationId",javaType = Integer.class),
//			@Result(column="LINE_ID",property="tempEmpLineId",javaType = String.class),
//			@Result(column="EMP_TYPE",property="tempEmpType",javaType = String.class)
//	})
	CheckClientLoginPT queryCheckClientLogin1(@Param("username") String username,@Param("password") String password);



	@Select(" SELECT S.ID as tempStId,L.ID as tempLineId" +
			" FROM C_MES_LINE_T L,C_MES_STATION_T S" +
			" WHERE L.NAME=#{lineEmpName} "
			+ "AND S.STATION_NAME=#{stationEmpName} "
			+ "AND S.LINE_ID=L.ID")
//	@Results(id="checkClientLoginMap2",value = {
//			@Result(column="SID",property="tempStId",javaType = Integer.class),
//			@Result(column="LID",property="tempLineId",javaType = Integer.class)
//	})
	CheckClientLoginPT queryCheckClientLogin2(@Param("lineEmpName")String lineEmpName,@Param("stationEmpName") String stationEmpName);

	@Select("SELECT COUNT(*)" +
			" FROM P_MES_EMP_WORKTIME_T" +
			" WHERE ST=#{stationEmp}")
	Integer queryTempWorkingCountByName(@Param("stationEmp")String stationEmp);


	@Insert("INSERT INTO P_MES_EMP_WORKTIME_T(DT,EMPID,ST) VALUES (NOW(),#{tempEmpId},#{stationEmp})")
	void insertEmpWorktime(Integer tempEmpId, @Param("stationEmp")String stationEmp);


	@Select("SELECT EMPID as tempWorktimeEmpid,DT_OFF as tempWorktimeDtoff" +
			" FROM P_MES_EMP_WORKTIME_T" +
			" WHERE ID=(SELECT MAX(ID) FROM P_MES_EMP_WORKTIME_T WHERE ST=#{stationEmp});")
//	@Results(id="checkClientLoginMap3",value = {
//			@Result(column="EMPID",property="tempWorktimeEmpid",javaType = Integer.class),
//			@Result(column="DT_OFF",property="tempWorktimeDtoff",javaType = Integer.class)
//	})
	CheckClientLoginPT queryCheckClientLogin3(@Param("stationEmp")String stationEmp);

	//计划校验

	@Select("SELECT COUNT(ID) FROM R_MES_PLAN_PRINT_T WHERE SN=#{snPlan}")
	Integer queryTempPlanPrintSnCount(@Param("snPlan")String snPlan);


	@Select(" SELECT PLAN_ID as tempPlanId,LINE_ID as tempLineId,"
			+ "PRODUCTION_ID as tempProdutionId,WORK_ORDER_ID as tempWorkOrderId"+
			" FROM R_MES_PLAN_PRINT_T "+
			" WHERE ID=( SELECT MAX(ID) FROM R_MES_PLAN_PRINT_T WHERE SN=#{snPlan})")
//	@Results(id="checkPlanSnPTMap1",value = {
//			@Result(column="PLAN_ID",property="tempPlanId",javaType = Integer.class),
//			@Result(column="LINE_ID",property="tempLineId",javaType = Integer.class),
//			@Result(column="PRODUCTION_ID",property="tempProdutionId",javaType = Integer.class),
//			@Result(column="WORK_ORDER_ID",property="tempWorkOrderId",javaType = Integer.class)
//	})
	CheckPlanSnPT queryCheckPlanSnPT1(@Param("snPlan")String snPlan);

	@Select(" SELECT LEVEL_NO as tempPlanLevel,STATUS as tempWorkerOrderStatus,PLAN_ID as tempPlanId"+
			" FROM R_MES_WORKORDER_DETAIL_T WHERE ID=#{tempWorkOrderId}")
//	@Results(id="checkPlanSnPTMap2",value = {
//			@Result(column="LEVEL_NO",property="tempPlanLevel",javaType = Integer.class),
//			@Result(column="STATUS",property="tempWorkerOrderStatus",javaType = String.class)
//	})
	CheckPlanSnPT queryCheckPlanSnPT2(@Param("tempWorkOrderId")Integer tempWorkOrderId);

	@Select(" SELECT LEVEL_NO as tempPlanLevel,STATUS as tempWorkerOrderStatus,ID as tempPlanId"+
			" FROM R_MES_WORKORDER_DETAIL_T WHERE ID=#{tempWorkOrderId}")
//	@Results(id="checkPlanSnPTMap2",value = {
//			@Result(column="LEVEL_NO",property="tempPlanLevel",javaType = Integer.class),
//			@Result(column="STATUS",property="tempWorkerOrderStatus",javaType = String.class)
//	})
	CheckPlanSnPT queryCheckWorkorderSnPT2(@Param("tempWorkOrderId")Integer tempWorkOrderId);


	@Select("SELECT COMPLETE_FLAG FROM R_MES_PLAN_T WHERE ID=#{tempPlanId}")
	String queryTempPlanStatus(@Param("tempPlanId")Integer tempPlanId);

	@Select("SELECT STATUS FROM R_MES_WORKORDER_DETAIL_T WHERE ID=#{tempPlanId}")
	String queryTempWorkorderStatus(@Param("tempPlanId")Integer tempPlanId);

	@Select(" SELECT COUNT(ID) FROM R_MES_WORKORDER_DETAIL_T "+
			" WHERE LEVEL_NO < #{tempPlanLevel} AND LINE_ID=#{tempLineId} and status=1")
	Integer queryTempPlanLevelBefore(@Param("tempPlanLevel") Integer tempPlanLevel,@Param("tempLineId") Integer tempLineId );


	@Select("SELECT W.ORDER_NUMBER as tempPlanNumber,W.ONLINE_NUMBER as tempPlanOnlineNumber,W.STATUS as tempWorkerOrderStatus"
			+ " FROM R_MES_WORKORDER_DETAIL_T W,R_MES_PLAN_T T "
			+ " WHERE W.LEVEL_NO=#{tempPlanLeve}-1 "
			+ " AND W.LINE_ID=#{tempLineId} "
			+ " AND W.PLAN_ID=T.ID "
			+ " AND T.COMPLETE_FLAG=1")
//	@Results(id="checkPlanSnPTMap3",value = {
//			@Result(column="ORDER_NUMBER",property="tempPlanNumber",javaType = Integer.class),
//			@Result(column="ONLINE_NUMBER",property="tempPlanOnlineNumber",javaType = Integer.class),
//			@Result(column="STATUS",property="tempWorkerOrderStatus",javaType = String.class)
//	})
	CheckPlanSnPT queryCheckPlanSnPT3(@Param("tempWorkOrderId")Integer tempWorkOrderId,@Param("tempPlanLeve")Integer tempPlanLeve,@Param("tempLineId")Integer tempLineId);

	@Select("SELECT W.ORDER_NUMBER as tempPlanNumber,W.ONLINE_NUMBER as tempPlanOnlineNumber,W.STATUS as tempWorkerOrderStatus"
			+ " FROM R_MES_WORKORDER_DETAIL_T W"
			+ " WHERE W.LEVEL_NO=#{tempPlanLeve}-1 "
			+ " AND W.LINE_ID=#{tempLineId} "
			+ " AND W.STATUS=1")
//	@Results(id="checkPlanSnPTMap3",value = {
//			@Result(column="ORDER_NUMBER",property="tempPlanNumber",javaType = Integer.class),
//			@Result(column="ONLINE_NUMBER",property="tempPlanOnlineNumber",javaType = Integer.class),
//			@Result(column="STATUS",property="tempWorkerOrderStatus",javaType = String.class)
//	})
	CheckPlanSnPT queryCheckWorkorderSnPT3(@Param("tempWorkOrderId")Integer tempWorkOrderId,@Param("tempPlanLeve")Integer tempPlanLeve,@Param("tempLineId")Integer tempLineId);

	//校验产品

	@Select("SELECT p.ID id,P.PRODUCTION_VR productionVr,P.PRODUCTION_STE productionSte from  C_MES_PRODUCTION_T p")
	List<CheckProductionPT> queryCheckProductionList(@Param("sId")String sId);

	@Select("SELECT p.ID id,P.PRODUCTION_VR productionVr,P.PRODUCTION_STE productionSte from  C_MES_PRODUCTION_T p where ID=#{pId}")
	List<CheckProductionPT> queryCheckProduction(@Param("pId")String pId);
}
