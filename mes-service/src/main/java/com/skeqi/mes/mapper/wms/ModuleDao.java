package com.skeqi.mes.mapper.wms;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

/**
 * 模组Controller
 *
 * @author 73414
 */
@Component
@MapperScan
public interface ModuleDao {

	// 查询计划打印表
	@Select("SELECT id,dt,sn,PLAN_ID as planId,LINE_ID as lineId,PRODUCTION_ID as productionId,SERIAL_NO as serialNo,PRINT_FLAG as printFlag,WORK_ORDER_ID as workOrderId  FROM r_mes_plan_print_t WHERE SN = #{sn}")
	public JSONObject findPlanPrint(@Param("sn") String sn);

	// 查询计划
	@Select("SELECT plan.ID as id,plan.DT as dt,plan.PLAN_NAME as planName,plan.PRODUCTION_ID as productionId,production.PRODUCTION_NAME as productionName,plan.PLAN_NUMBER as lanNumber,plan.COMPLETE_NUMBER as completeNumber,plan.REMAIND_NUMBER as remaindNumber,plan.OK_NUMBER as okNumber,plan.NG_NUMBER as ngNumber,plan.LINE_ID as lineId,line.`NAME` as lineName,plan.PLAN_LEVEL as planLevl,plan.COMPLETE_FLAG as completeFlag,plan.OPREATION_USER as opreationUser,plan.CREATE_BARCODE_FLAG as createBarcodeFlag,plan.PLAN_SERIALNO as planSerialno,plan.ONLINE_NUMBER as onlineNumber FROM r_mes_plan_t as plan join c_mes_production_t as production on plan.PRODUCTION_ID = production.id join c_mes_line_t as line on plan.LINE_ID = line.id where plan.id = #{id}")
	public JSONObject findPlan(@Param("id") Integer id);

	// 更新计划
	@Update("UPDATE r_mes_plan_t set COMPLETE_NUMBER = #{completeNumber},REMAIND_NUMBER = #{remaindNumber},OK_NUMBER = #{okNumber},NG_NUMBER = #{ngNumber},ONLINE_NUMBER = #{onlineNumber} where id = #{id}")
	public Integer updatePlan(JSONObject json);

	// 查询工单详情
	@Select("select id,DT,PRODUCTION_ID as productionId,ORDER_NUMBER as orderNumber,ONLINE_NUMBER as onlineNumber,OFFLINE_NUMBER as offlineNumber,DEFFECT_NUMBER  as deffectNumber,LINE_ID as lineId,TEAM_ID as teamId,LEVEL_NO as levelNo,`STATUS` as status,ALTER_DT as alterDt,PLAN_ID as planId,CREATE_BARCODE_FLAG as createBarcodeFlag,PRINTE_NUMBER as  printeNumber,SFC_CODE as sfcCode,LINE_CODE as lineCode,SFC_TYPE as sfcType,OLD_SFC_CODE as oldSfcCode,OLD_SN_CODE as oldSnCode from r_mes_workorder_detail_t where id = #{id}")
	public JSONObject findWorkorderDetail(@Param("id") Integer id);

	// 更新工单详情
	@Update("update r_mes_workorder_detail_t set ONLINE_NUMBER = #{onlineNumber},OFFLINE_NUMBER = #{offlineNumber},`status` = #{status} where id = #{id}")
	public Integer updateWorkorderDetail(JSONObject json);

	// 查询工单详情List
	@Select("select id,DT,PRODUCTION_ID as productionId,ORDER_NUMBER as orderNumber,ONLINE_NUMBER as onlineNumber,OFFLINE_NUMBER as offlineNumber,DEFFECT_NUMBER  as deffectNumber,LINE_ID as lineId,TEAM_ID as teamId,LEVEL_NO as levelNo,`STATUS` as status,ALTER_DT as alterDt,PLAN_ID as planId,CREATE_BARCODE_FLAG as createBarcodeFlag,PRINTE_NUMBER as  printeNumber,SFC_CODE as sfcCode,LINE_CODE as lineCode,SFC_TYPE as sfcType,OLD_SFC_CODE as oldSfcCode,OLD_SN_CODE as oldSnCode from r_mes_workorder_detail_t where PLAN_ID = #{planId}")
	public List<JSONObject> findWorkorderDetailList(@Param("planId") Integer planId);

	// 查询计划打印表List
	@Select("SELECT id,dt,sn,PLAN_ID as planId,LINE_ID as lineId,PRODUCTION_ID as productionId,SERIAL_NO as serialNo,PRINT_FLAG as printFlag,WORK_ORDER_ID as workOrderId  FROM r_mes_plan_print_t WHERE PLAN_ID = #{planId}")
	public List<JSONObject> findPlanPrintList(@Param("planId") Integer planId);

	// 新增计划表P
	@Insert("insert into p_mes_plan_t(DT,PLAN_NAME,PRODUCTION_ID,PLAN_NU MBER,COMPLETE_NUMBER,REMAIND_NUMBER,OK_NUMBER,NG_NUMBER,LINE_ID,PLAN_LEVEL,COMPLETE_FLAG,OPREATION_USER,CREATE_BARCODE_FLAG,OVER_DT,PLAN_SERIALNO,ONLINE_NUMBER,R_MES_PLAN_ID)value(#{dt},#{planName},#{productionId},#{planNumber},#{completeNumber},#{remaindNumber},#{okNumber},#{ngNumber},#{lineId},#{planLevele},#{completeFlag},#{opreationUser},#{createBarcodeFlag},now(),#{planSerialno},#{onlineNumber},#{id})")
	public Integer addPPlan(JSONObject json);

	// 新增工单详情表P
	@Insert("INSERT into p_mes_workorder_detail_t(DT,PRODUCTION_ID,ORDER_NUMBER,ONLINE_NUMBER,OFFLINE_NUMBER,DEFFECT_NUMBER,LINE_ID,TEAM_ID,LEVEL_NO,`STATUS`,ALTER_DT,PLAN_ID,CREATE_BARCODE_FLAG,PRINTE_NUMBER,SFC_CODE,LINE_CODE,SFC_TYPE,OLD_SFC_CODE,OLD_SN_CODE,R_MES_WORKORDER_DETAIL_ID)value(#{dt},#{productionId},#{orderNumber},#{onlineNumber},#{offlineNumber},#{deffectNumber},#{lineId},#{teamId},#{levelNo},#{status},#{alterDt},#{planId},#{createBarcodeFlag},#{printeNumber},#{sfcCode},#{lineCode},#{sfcType},#{oldSfcCode},#{oldSnCode},#{id})")
	public Integer addPWorkorderDetail(JSONObject json);

	// 新增计划打印表P
	@Insert("INSERT into p_mes_plan_print_t(DT,SN,PLAN_ID,LINE_ID,PRODUCTION_ID,SERIAL_NO,PRINT_FLAG,WORK_ORDER_ID,R_MES_PLAN_PRINT_ID)value(#{dt},#{sn},#{planId},#{lineId},#{productionId},#{serialNo},#{printFlag},#{workOrderId},#{id})")
	public Integer addPPlanPrint(JSONObject json);

	// 删除计划表R
	@Delete("delete from r_mes_plan_t where id = #{id}")
	public Integer deletePlan(@Param("id") Integer id);

	// 删除工单详情表R
	@Delete("delete from r_mes_workorder_detail_t where id = #{id}")
	public Integer deleteWorkorderDetail(@Param("id") Integer id);

	// 删除计划打印表R
	@Delete("delete from r_mes_plan_print_t where id = #{id}")
	public Integer deletePlanPrint(@Param("id") Integer id);

}
