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

import com.skeqi.mes.pojo.api.MoveDataPT;
import com.skeqi.mes.pojo.api.MoveOrderDataPT;
import com.skeqi.mes.pojo.api.StationpassPT;
import com.skeqi.mes.pojo.api.UpdatePlanPT;

/**
 * @date 2020年1月13日
 * @author yinp
 *
 */
@Component
@MapperScan
public interface UpdatePlanPDao {

	/**
	 *
	 * @param runPlanId
	 * @param runLineId
	 * @return cPlanPrintId
	 * @return cPlanPrintDt
	 * @return cPlanPrintSn
	 * @return cPlanPrintPlanId
	 * @return cPlanPrintLineId
	 * @return cPlanPrintProductionId
	 * @return cPlanPrintSerialNo
	 * @return cPlanPrintPrintFlag
	 * @return cPlanPrintWorkOrderId
	 */
	@Select("select ID cPlanPrintId,DT cPlanPrintDt,"
			+ "SN cPlanPrintSn,PLAN_ID cPlanPrintPlanId,"
			+ "LINE_ID cPlanPrintLineId,PRODUCTION_ID cPlanPrintProductionId,"
			+ "SERIAL_NO cPlanPrintSerialNo,PRINT_FLAG cPlanPrintPrintFlag,"
			+ "WORK_ORDER_ID cPlanPrintWorkOrderId from r_mes_plan_print_t "
			+ " where WORK_ORDER_ID=#{runPlanId} "
			+ "and line_id=#{runLineId} ")
	public List<UpdatePlanPT> findList1(@Param("runPlanId")Integer runPlanId,@Param("runLineId")Integer runLineId);

	@Select("select ID cPlanPrintId,DT cPlanPrintDt,"
			+ "SN cPlanPrintSn,PLAN_ID cPlanPrintPlanId,"
			+ "LINE_ID cPlanPrintLineId,PRODUCTION_ID cPlanPrintProductionId,"
			+ "SERIAL_NO cPlanPrintSerialNo,PRINT_FLAG cPlanPrintPrintFlag,"
			+ "WORK_ORDER_ID cPlanPrintWorkOrderId from r_mes_plan_print_t "
			+ "where SN=#{sn} ")
	public UpdatePlanPT findListBySN(@Param("sn")String sn);


	/**
	 *
	 * @param runLineId
	 * @return cPlanId;
	 * @return cPlanCPlanDt;
	 * @return cPlanPlanName;
	 * @return cPlanProductionId;
	 * @return cPlanPlanNumber;
	 * @return cPlanCompleteNumber;
	 * @return cPlanRemaindNumber;
	 * @return cPlanOkNumber;
	 * @return cPlanNgNumber;
	 * @return cPlanLineId;
	 * @return cPlanPlanLevel;
	 * @return cPlanCompleteFlag;
	 * @return cPlanOpreationUser;
	 * @return cPlanCreateBarcodeFlag;
	 * @return cPlanPlanSerialno;
	 * @return cPlanOnlineNumber;
	 */
	@Select("select ID as cPlanId,DT as cPlanCPlanDt,PLAN_NAME as cPlanPlanName,"
			+ "PRODUCTION_ID as cPlanProductionId,PLAN_NUMBER as cPlanPlanNumber,"
			+ "COMPLETE_NUMBER as cPlanCompleteNumber,REMAIND_NUMBER as cPlanRemaindNumber,"
			+ "OK_NUMBER as cPlanOkNumber,NG_NUMBER" +
			" as cPlanNgNumber,LINE_ID as cPlanLineId,PLAN_LEVEL as cPlanPlanLevel,"
			+ "COMPLETE_FLAG as cPlanCompleteFlag,OPREATION_USER as cPlanOpreationUser,"
			+ "CREATE_BARCODE_FLAG as cPlanCreateBarcodeFlag,PLAN_SERIALNO as cPlanPlanSerialno,"
			+ "ONLINE_NUMBER as cPlanOnlineNumber,ROUTING_ID as routingId,TOTAL_RECIPE_ID as  totalRecipeId from r_mes_plan_t"
			+ " where id=#{runPlanId}")
	public List<UpdatePlanPT> findList2(@Param("runPlanId")String runPlanId);

	/**
	 *
	 * @param tempPlanLevel
	 * @return cPlanId;
	 * @return cPlanCPlanDt;
	 * @return cPlanPlanName;
	 * @return cPlanProductionId;
	 * @return cPlanPlanNumber;
	 * @return cPlanCompleteNumber;
	 * @return cPlanRemaindNumber;
	 * @return cPlanOkNumber;
	 * @return cPlanNgNumber;
	 * @return cPlanLineId;
	 * @return cPlanPlanLevel;
	 * @return cPlanCompleteFlag;
	 * @return cPlanOpreationUser;
	 * @return cPlanCreateBarcodeFlag;
	 * @return cPlanPlanSerialno;
	 * @return cPlanOnlineNumber;
	 */
	@Select("select ID as cPlanId,DT as cPlanCPlanDt,PLAN_NAME as cPlanPlanName,"
			+ "PRODUCTION_ID as cPlanProductionId,PLAN_NUMBER as cPlanPlanNumber,"
			+ "COMPLETE_NUMBER as cPlanCompleteNumber,REMAIND_NUMBER as cPlanRemaindNumber,"
			+ "OK_NUMBER as cPlanOkNumber,NG_NUMBER" +
			" as cPlanNgNumber,LINE_ID as cPlanLineId,PLAN_LEVEL as cPlanPlanLevel,"
			+ "COMPLETE_FLAG as cPlanCompleteFlag,OPREATION_USER as cPlanOpreationUser,"
			+ "CREATE_BARCODE_FLAG as cPlanCreateBarcodeFlag,PLAN_SERIALNO as cPlanPlanSerialno,"
			+ "ONLINE_NUMBER as cPlanOnlineNumber,ROUTING_ID as routingId,TOTAL_RECIPE_ID as  totalRecipeId  from r_mes_plan_t"
			+ " where id=#{tempPlanLevel}")
	public List<UpdatePlanPT> findList3(@Param("tempPlanLevel")String tempPlanLevel);

	/**
	 *
	 * @param runPlanId
	 * @return cWorkOrderId;
	 * @return cWorkOrderDt;
	 * @return cWorkOrderProductionId;
	 * @return cWorkOrderOrderNumber;
	 * @return cWorkOrderOnlineNumber;
	 * @return cWorkOrderOfflineNumber;
	 * @return cWorkOrderDeffectNumber;
	 * @return cWorkOrderLineId;
	 * @return cWorkOrderTeamId;
	 * @return cWorkOrderLevelNo;
	 * @return cWorkOrderStatus;
	 * @return cWorkOrderAlterDt;
	 * @return cWorkOrderPlanId;
	 * @return cWorkOrderCreateBarcodeFlag;
	 * @return cWorkOrderPrinteNumber;
	 * @return cWorkOrderSfcCode;
	 * @return cWorkOrderLineCode;
	 * @return cWorkOrderSfcType;
	 * @return cWorkOrderOldSfcCode;
	 * @return cWorkOrderOldSnCode;
	 */
	@Select("select ID as cWorkOrderId,DT as cWorkOrderDt,"
			+ "PRODUCT_ID as cWorkOrderProductionId,"
			+ "ORDER_NUMBER as cWorkOrderOrderNumber,"
			+ "ONLINE_NUMBER as cWorkOrderOnlineNumber,"
			+ "OFFLINE_NUMBER as cWorkOrderOfflineNumber,"
			+ "DEFFECT_NUMBER as cWorkOrderDeffectNumber,"
			+ "LINE_ID as cWorkOrderLineId,TEAM_ID as cWorkOrderTeamId,"
			+ "LEVEL_NO as cWorkOrderLevelNo,`STATUS` as cWorkOrderStatus,"
			+ "ALTER_DT as cWorkOrderAlterDt,PLAN_ID as cWorkOrderPlanId,"
			+ "CREATE_BARCODE_FLAG as cWorkOrderCreateBarcodeFlag,"
			+ "PRINTE_NUMBER as cWorkOrderPrinteNumber,SFC_CODE as cWorkOrderSfcCode,"
			+ "LINE_CODE as cWorkOrderLineCode,SFC_TYPE as cWorkOrderSfcType,"
			+ "OLD_SFC_CODE as cWorkOrderOldSfcCode,OLD_SN_CODE as cWorkOrderOldSnCode "
			+ "from r_mes_workorder_detail_t"
			+ " where ID=#{runPlanId}")
	public List<UpdatePlanPT> findList4(@Param("runPlanId")String runPlanId);

	/**
	 *
	 * @param dx
	 */
	@Insert("insert into p_mes_plan_t("
			+ "id,dt,plan_name,production_id,plan_number,complete_number,"
			+ "remaind_number,ok_number,ng_number,line_id,plan_level,"
			+ "complete_flag,opreation_user,create_barcode_flag,over_dt,"
			+ "plan_serialno,online_number,r_mes_plan_id,TOUTING_ID,TOTAL_RECIPE_ID)"
			+ "VALUES(#{cPlanId},#{cPlanCPlanDt},#{cPlanPlanName},"
			+ "#{cPlanProductionId},#{cPlanPlanNumber},"
			+ "#{cPlanCompleteNumber},#{cPlanRemaindNumber},"
			+ "#{cPlanOkNumber},#{cPlanNgNumber},"
			+ "#{cPlanLineId},#{cPlanPlanLevel},"
			+ "#{cPlanCompleteFlag},#{cPlanOpreationUser},"
			+ "#{cPlanCreateBarcodeFlag},now(),"
			+ "#{cPlanPlanSerialno},#{cPlanOnlineNumber},"
			+ "#{cPlanId},#{routingId},#{totalRecipeId} )")
	public void insert1(UpdatePlanPT dx);

	/**
	 *
	 * @param dx
	 */
	@Insert("insert into p_mes_plan_print_t("
			+ "id,dt,sn,line_id,production_id,serial_no,print_flag,"
			+ "work_order_id,r_mes_plan_print_id)"
			+ "VALUES (#{cPlanPrintId},#{cPlanPrintDt}, #{cPlanPrintSn}, "
			+ "#{cPlanPrintLineId},#{cPlanPrintProductionId}, "
			+ "#{cPlanPrintSerialNo},#{cPlanPrintPrintFlag}, "
			+ "#{cPlanPrintWorkOrderId},#{cPlanPrintId})")
	public void insert2(UpdatePlanPT dx);

	@Insert("insert into p_mes_workorder_detail_t("
			+ "id,dt,product_id,order_number,online_number,offline_number,"
			+ "deffect_number,line_id,team_id,level_no,status,alter_dt,plan_id,"
			+ "r_mes_workorder_detail_id)"
			+ "values(#{cWorkOrderId},#{cWorkOrderDt},#{cWorkOrderProductionId},"
			+ "#{cWorkOrderOrderNumber},#{cWorkOrderOnlineNumber},"
			+ "#{cWorkOrderOfflineNumber},#{cWorkOrderDeffectNumber},"
			+ "#{cWorkOrderLineId},#{cWorkOrderTeamId},"
			+ "#{cWorkOrderLevelNo},#{cWorkOrderStatus},"
			+ "#{cWorkOrderAlterDt},#{cWorkOrderPlanId},"
			+ "#{cWorkOrderId})")
	public void insert3(UpdatePlanPT dx);

	/**
	 *
	 * @param cPlanPlanLevel
	 * @param cPlanId
	 */
	@Update("update r_mes_plan_t set plan_level=#{cPlanPlanLevel}-1 "
			+ "where id=#{cPlanId}")
	public void update1(@Param("cPlanPlanLevel")String cPlanPlanLevel,@Param("cPlanId")String cPlanId);

	@Update("update r_mes_workorder_detail_t set LEVEL_NO=LEVEL_NO-1 where LEVEL_NO>#{cPlanPlanLevel} and LINE_ID=#{cWorkOrderLineId}")
	public void update2(UpdatePlanPT dx);

	//
	//
	//
	//
	//
	//
	//
	/**
	 *
	 * @param snMove
	 * @return
	 */
	@Select("select ID as rKeypartsId,DT as rKeypartsDt,TRANSFER as rKeypartsTransfer,"
			+ "KEYPART_MODE as rKeypartsKeypartMode,ST as rKeypartsSt,SN as rKeypartsSn,"
			+ "WID as rKeypartsWid,TID as rKeypartsTid,SECOND_NUM as rKeypartsSecondNum,"
			+ "KEYPART_ID as rKeypartsKeypartId,KEYPART_NAME as rKeypartsKeypartName,"
			+ "KEYPART_PN as rKeypartsKeypartPn,KEYPART_NUM as rKeypartsKeypartNum,"
			+ "KEYPART_MODULE as rKeypartsKeypartModule,KEYPART_REWORK_FG as rKeypartsKeypartReworkFg,"
			+ "KEYPART_REWORK_ST as rKeypartsKeypartReworkSt,MATERIAL_ID as rKeypartsMaterialId,"
			+ "MATERIAL_NUMBER as rKeypartsMaterialNumber from r_mes_keypart_t "
			+ "where sn=#{snMove}")
	public List<MoveDataPT> findListData1(@Param("snMove")String snMove);

	/**
	 *
	 * @param snMove
	 * @return
	 */
	@Select("select ID as rBoltsId,DT as rBoltsDt,TRANSFER as rBoltsTransfer,"
			+ "BOLT_MODE as rBoltsBoltMode,SN as rBoltsSn,ST as rBoltsSt,T as rBoltsT,"
			+ "A as rBoltsA,P as rBoltsP,C as rBoltsC,R as rBoltsR,EQS as rBoltsEqs,"
			+ "T_LIMIT as rBoltsTLimit,A_LIMIT as rBoltsALimit,WID as rBoltsWid,"
			+ "TID as rBoltsTid,BOLT_NUM as rBoltsBoltNum,BOLT_NAME as rBoltsBoltName,"
			+ "REWORK_FLAG as rBoltsReworkFlag,REWORK_ST as rBoltsReworkSt,"
			+ "GUN_NO as rBoltsGunNo from r_mes_bolt_t "
			+ "where sn=#{snMove}")
	public List<MoveDataPT> findListData2(@Param("snMove")String snMove);

	/**
	 *
	 * @param snMove
	 * @return
	 */
	@Select("select ID as rLeakagesId,DT as rLeakagesDt,ST as rLeakagesSt,"
			+ "SN as rLeakagesSn,LEAKAGE_NAME as rLeakagesLeakageName,"
			+ "LEAKAGE_PV as rLeakagesLeakagePv,LEAKAGE_LV as rLeakagesLeakageLv,"
			+ "LEAKAGE_R as rLeakagesLeakageR,WID as rLeakagesWid,TRANSFER as rLeakagesTransfer,"
			+ "LEAKAGE_MODE as rLeakagesLeakageMode,REWORK_FLAG as rLeakagesReworkFlag "
			+ "from r_mes_leakage_t "
			+ "where sn=#{snMove}")
	public List<MoveDataPT> findListData3(@Param("snMove")String snMove);

	/**
	 *
	 * @param dx
	 */
	@Insert("insert into p_mes_keypart_t("
			+ "id,dt,transfer,keypart_mode,st,sn,wid,tid,second_num,keypart_id,"
			+ "keypart_name,keypart_pn,keypart_num,keypart_module,keypart_rework_fg,"
			+ "keypart_rework_st,material_id,material_number)"
			+ "values(#{rKeypartsId},#{rKeypartsDt},"
			+ "#{rKeypartsTransfer},#{rKeypartsKeypartMode},"
			+ "#{rKeypartsSt},#{rKeypartsSn},"
			+ "#{rKeypartsWid},#{rKeypartsTid},"
			+ "#{rKeypartsSecondNum},#{rKeypartsKeypartId},"
			+ "#{rKeypartsKeypartName},#{rKeypartsKeypartPn},"
			+ "#{rKeypartsKeypartNum},#{rKeypartsKeypartModule},"
			+ "#{rKeypartsKeypartReworkFg},#{rKeypartsKeypartReworkSt},"
			+ "#{rKeypartsMaterialId},#{rKeypartsMaterialNumber})")
	public Integer insertData1(MoveDataPT dx);

	/**
	 *
	 * @param dx
	 */
	@Insert("insert into p_mes_bolt_t(id,dt,transfer,bolt_mode,sn,st,t,a,"
			+ "p,c,r,eqs,t_limit,a_limit,wid,tid,bolt_num,bolt_name,"
			+ "rework_flag,rework_st,gun_no)"
			+ "values(#{rBoltsId},#{rBoltsDt},#{rBoltsTransfer},"
			+ "#{rBoltsBoltMode},#{rBoltsSn},#{rBoltsSt},#{rBoltsT},"
			+ "#{rBoltsA},#{rBoltsP},#{rBoltsC},#{rBoltsR},#{rBoltsEqs},"
			+ "#{rBoltsTLimit},#{rBoltsALimit},#{rBoltsWid},#{rBoltsTid},"
			+ "#{rBoltsBoltNum},#{rBoltsBoltName},#{rBoltsReworkFlag},"
			+ "#{rBoltsReworkSt},#{rBoltsGunNo})")
	public Integer insertData2(MoveDataPT dx);

	/**
	 *
	 * @param dx
	 */
	@Insert("insert into p_mes_leakage_t("
			+ "id,dt,st,sn,leakage_name,"
			+ "leakage_pv,leakage_lv,"
			+ "leakage_r,wid,transfer,"
			+ "leakage_mode,rework_flag)"
			+ "values(#{rLeakagesId},#{rLeakagesDt},"
			+ "#{rLeakagesSt},#{rLeakagesSn},"
			+ "#{rLeakagesLeakageName},#{rLeakagesLeakagePv},"
			+ "#{rLeakagesLeakageLv},#{rLeakagesLeakageR},"
			+ "#{rLeakagesWid},#{rLeakagesTransfer},"
			+ "#{rLeakagesLeakageMode},#{rLeakagesReworkFlag})")
	public Integer insertData3(MoveDataPT dx);

	//
	//
	//
	//
	//
	//

	/**
	 *
	 * @param runPrintCode
	 * @return cPlanPrintSn
	 * @return cPlanPrintPlanId
	 * @return cPlnPrintLineId
	 * @return cPlanPrintProductionId
	 * @return cPlanPrintSerialNo
	 * @return cPlanPrintPrintFlag
	 * @return cPlanPrintWorkOrderId
	 * @return cPlanPrintId
	 */
	@Select("select sn cPlanPrintSn,plan_id cPlanPrintPlanId,line_id cPlnPrintLineId,"
			+ "production_id cPlanPrintProductionId,serial_no cPlanPrintSerialNo,print_flag cPlanPrintPrintFlag,work_order_id cPlanPrintWorkOrderId,id cPlanPrintId "
			+ "from r_mes_plan_print_t "
			+ "where sn=#{runPrintCode}")
	public List<MoveOrderDataPT> findOrder1(String runPrintCode);

	/**
	 *
	 * @param runOrderId
	 * @return cWorkOrderId
	 * @return cWorkOrderDt
	 * @return cWorkOrderProductionId
	 * @return cWorkOrderOrderNumber
	 * @return cWorkOrderOnlineNumber
	 * @return cWorkOrderOfflineNumber
	 * @return cWorkOrderDeffectNumber
	 * @return cWorkOrderLineId
	 * @return cWorkOrderTeamId
	 * @return cWorkOrderLevelNo
	 * @return cWorkOrderStatus
	 * @return cWorkOrderAlterDt
	 * @return cWorkOrderPlanId
	 * @return cWorkOrderCreateBarcodeFlag
	 * @return cWorkOrderPrinteNumber
	 * @return cWorkOrderSfcCode
	 * @return cWorkOrderLineCode
	 * @return cWorkOrderSfcType
	 * @return cWorkOrderOldSfcCode
	 * @return cWorkOrderOldSnCode
	 */
	@Select("select ID cWorkOrderId,DT cWorkOrderDt,PRODUCT_ID cWorkOrderProductionId,"
			+ "ORDER_NUMBER cWorkOrderOrderNumber,ONLINE_NUMBER cWorkOrderOnlineNumber,OFFLINE_NUMBER cWorkOrderOfflineNumber,"
			+ "DEFFECT_NUMBER cWorkOrderDeffectNumber,LINE_ID cWorkOrderLineId,TEAM_ID cWorkOrderTeamId,"
			+ "LEVEL_NO cWorkOrderLevelNo,STATUS cWorkOrderStatus,ALTER_DT cWorkOrderAlterDt,"
			+ "PLAN_ID cWorkOrderPlanId,CREATE_BARCODE_FLAG cWorkOrderCreateBarcodeFlag,PRINTE_NUMBER cWorkOrderPrinteNumber,"
			+ "SFC_CODE cWorkOrderSfcCode,LINE_CODE cWorkOrderLineCode,SFC_TYPE cWorkOrderSfcType,"
			+ "OLD_SFC_CODE cWorkOrderOldSfcCode,OLD_SN_CODE cWorkOrderOldSnCode "
			+ "from r_mes_workorder_detail_t "
			+ "where id=#{runOrderId}")
	public List<MoveOrderDataPT> findOrder2(String runOrderId);

	/**
	 *
	 * @param dx
	 */
	@Insert("INSERT INTO p_mes_plan_print_t("
			+ "dt,sn,plan_id,line_id,"
			+ "production_id,serial_no,"
			+ "print_flag,work_order_id) "
			+ "VALUES(#{cPlanPrintSn},#{cPlanPrintPlanId},"
			+ "#{cPlanPrintLineId},#{cPlanPrintProductionId},"
			+ "#{cPlanPrintSerialNo},#{cPlanPrintPrintFlag},"
			+ "#{cPlanPrintWorkOrderId})")
	public void insertOrder1(MoveOrderDataPT dx);

	/**
	 *
	 * @param dx
	 */
	@Insert("INSERT INTO p_mes_workorder_detail_t("
			+ "dt,product_id,order_number,"
			+ "online_number,offline_number,"
			+ "deffect_number,line_id,team_id,"
			+ "level_no,status,alter_dt,plan_id,"
			+ "printe_number,old_sn_code,"
			+ "create_barcode_flag,sfc_code,"
			+ "line_code,sfc_type,old_sfc_code "
			+ "VALUES(#{cWorkOrderDt},#{cWorkOrderProductionId},"
			+ "#{cWorkOrderOrderNumber},#{cWorkOrderOnlineNumber},"
			+ "#{cWorkOrderOfflineNumber},#{cWorkOrderDeffectNumber},"
			+ "#{cWorkOrderLineId},#{cWorkOrderTeamId},"
			+ "#{cWorkOrderLevelNo},#{cWorkOrderStatus},"
			+ "#{cWorkOrderAlterDt},#{cWorkOrderPlanId},"
			+ "#{cWorkOrderPrinteNumber},#{cWorkOrderOldSnCode},"
			+ "#{cWorkOrderCreateBarcodeFlag},#{cWorkOrderOldSfcCode},"
			+ "#{cWorkOrderLineCode},#{cWorkOrderSfcType},#{cWorkOrderSfcCode})")
	public void insertOrder2(MoveOrderDataPT dx);

	/**
	 *
	 * @param cPlanPrintId
	 */
	@Delete("delete from r_mes_plan_print_t where id=#{cPlanPrintId};")
	public void deleteOrder1(String cPlanPrintId);

	/**
	 *
	 * @param cWorkOrderId
	 */
	@Delete("delete from r_mes_workorder_detail_t where id=#{cWorkOrderId}")
	public void deleteOrder2(String cWorkOrderId);


	//
	//
	//
	//
	//
	//
	//
	@Select("select distinct st as locationStation from p_mes_station_pass_t "
			+ "where line_id=#{strLine}")
	public List<StationpassPT> findStation1(String strLine);

	@Select("select distinct sn as locationDataSn from p_mes_station_pass_t "
			+ "where line_id=#{strLine} "
			+ "and st=#{locationStationSt} "
			+ "and dt between #{beginTime}"
			+ "and #{endTime}")
	public List<StationpassPT> findStation2(@Param("strLine")String strLine,@Param("locationStationSt")String locationStationSt,@Param("beginTime")String beginTime,@Param("endTime")String endTime);

	@Select("select count(*) as num1  from  p_mes_station_pass_t "
			+ "where sn=#{locationDataSn} "
			+ "and line_id=#{strLine}  "
			+ "and st=#{locationStationSt}  "
			+ "and pass_flag=1")
	public StationpassPT findStation3(@Param("locationDataSn")String locationDataSn,@Param("strLine")String strLine,@Param("locationStationSt")String locationStationSt);

	@Select("select count(*) as num2  from  p_mes_station_pass_t "
			+ "where sn=#{locationDataSn} "
			+ "and line_id=#{strLine}  "
			+ "and st=#{locationStationSt}  "
			+ "and pass_flag=2")
	public StationpassPT findStation4(@Param("locationDataSn")String locationDataSn,@Param("strLine")String strLine,@Param("locationStationSt")String locationStationSt);

	@Select("select  (((b.dt- a.dt)*24)*60) temp3 from ("
				+ "select * from p_mes_station_pass_t where id=("
					+ "select  max(id) from  p_mes_station_pass_t "
					+ "where line_id=#{strLine} "
					+ "and st=#{locationStationSt} "
					+ "and pass_flag=1 and sn=#{locationDataSn}"
				+ ")"
			+ ")a "
			+ "left join ("
				+ "select * from p_mes_station_pass_t "
				+ "where id=("
					+ "select  max(id) from p_mes_station_pass_t "
					+ "where  line_id=#{strLine} "
					+ "and st=#{locationStationSt} "
					+ "and  pass_flag=2"
				+ ")"
			+ ")b on a.sn = b.sn;")
	public StationpassPT findStation5(String locationDataSn,String strLine,String locationStationSt);

	@Select("select count(*) as testNg from p_mes_station_pass_t "
			+ "where line_id=#{strLine} "
			+ "and st=#{locationStationSt} "
			+ "and status='ok' "
			+ "and pass_flag=2 "
			+ "and dt between #{beginTime} "
			+ "and #{endTime}")
	public StationpassPT findStation6(String strLine,String locationStationSt,String beginTime,String endTime);

	@Select("select count(*) testAll from p_mes_station_pass_t "
			+ "where line_id=#{strLine} "
			+ "and st=#{locationStationSt} "
			+ "and pass_flag=2")
	public StationpassPT findStation7(String strLine,String locationStationSt);

	@Select("select  count(emp) testEmpnum from p_mes_station_pass_t "
			+ "where line_id=#{strLine} "
			+ "and st=#{locationStationSt} "
			+ "and dt between #{beginTime}"
			+ "and #{endTime}")
	public StationpassPT findStation8(String strLine,String locationStationSt,String beginTime,String endTime);

	@Select("select emp_department as testEmpnum from c_mes_emp_t "
			+ "where emp_no in ("
				+ "select emp from p_mes_station_pass_t "
				+ "where line_id=#{strLine} "
				+ "and st=#{locationStationSt} "
				+ "and dt between #{beginTime} "
				+ "and #{endTime} "
			+ ")")
	public StationpassPT findStation9(String strLine,String locationStationSt,String beginTime,String endTime);

	@Select("select * from r_mes_workorder_detail_t where ID=#{id}")
	public Map<String, Object> getWorkorderById(@Param("id")Integer id);

	@Insert("insert into p_mes_workorder_detail_t "
			+ "(`DT`, `WORKORDER_ID`, `ORDER_NUMBER`, `ONLINE_NUMBER`, `OFFLINE_NUMBER`"
			+ ", `DEFFECT_NUMBER`, `LINE_ID`, `TEAM_ID`, `LEVEL_NO`, `STATUS`"
			+ ", `ALTER_DT`, `PLAN_ID`, `CREATE_BARCODE_FLAG`, `PRINTE_NUMBER`, `SFC_CODE`"
			+ ", `LINE_CODE`, `SFC_TYPE`, `OLD_SFC_CODE`, `OLD_SN_CODE`, `COMPLETE_NUMBER`"
			+ ", `R_MES_WORKORDER_DETAIL_ID`, `PLAN_START_TIME`, `PLAN_END_TIME`, `ACTUAL_START_TIME`, `ACTUAL_END_TIME`"
			+ ", `ROUTING_ID`, `BARCODE_RULE_ID`, `BOM_ID`, `PRODUCT_ID`, `TOTAL_RECIPE_ID`"
			+ ", `OK_NUMBER`, `ORDER_ID`, `ORDERRECORD_ID`)"
			+ "values"
			+ "(#{DT}, #{WORKORDER_ID}, #{ORDER_NUMBER}, #{ONLINE_NUMBER}, #{OFFLINE_NUMBER}"
			+ ",#{DEFFECT_NUMBER}, #{LINE_ID}, #{TEAM_ID}, #{LEVEL_NO}, #{STATUS}"
			+ ",#{ALTER_DT}, #{PLAN_ID}, #{CREATE_BARCODE_FLAG}, #{PRINTE_NUMBER}, #{SFC_CODE}"
			+ ",#{LINE_CODE}, #{SFC_TYPE}, #{OLD_SFC_CODE}, #{OLD_SN_CODE}, #{COMPLETE_NUMBER}"
			+ ",#{ID}, #{PLAN_START_TIME}, #{PLAN_END_TIME}, #{ACTUAL_START_TIME}, #{ACTUAL_END_TIME}"
			+ ",#{ROUTING_ID}, #{BARCODE_RULE_ID}, #{BOM_ID}, #{PRODUCT_ID}, #{TOTAL_RECIPE_ID}"
			+ ",#{OK_NUMBER}, #{ORDER_ID}, #{ORDERRECORD_ID})")
	public Integer InsertWorkorderP(Map<String, Object> wo);
}
