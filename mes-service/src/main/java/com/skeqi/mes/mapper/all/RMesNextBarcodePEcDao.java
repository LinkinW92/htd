package com.skeqi.mes.mapper.all;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.skeqi.mes.pojo.api.RMesNextBarcodePEcT;

public interface RMesNextBarcodePEcDao {

	/**
	 * 按优先级获取进行中工单
	 * @return
	 */
	@Select("select * from r_mes_workorder_detail_t where STATUS = 1 and LINE_ID=#{lineId} order by LEVEL_NO asc")
	public List<RMesNextBarcodePEcT> find1(@Param("lineId")Integer lineId);

	/**
	 * 计划进行中数量
	 * @return
	 */
	@Select("select ifnull(count(id),0) from r_mes_workorder_detail_t where STATUS = 1 and LINE_ID=#{lineId}")
	public Integer find2(@Param("lineId")Integer lineId);

	/**
	 * 根据产线查询工单数量
	 * @param lineId
	 * @param id
	 * @return
	 */
	@Select("select ifnull(count(id),0) from r_mes_workorder_detail_t where `status` = '1'  and line_id = #{lineId}")
	public Integer find3(@Param("lineId")Integer lineId);

	@Select("select ifnull(max(level_no),0) from r_mes_workorder_detail_t where `status` = '1' and line_id = #{lineId}")
	public Integer find4(@Param("lineId")Integer lineId);

	@Select("select * from r_mes_workorder_detail_t where `status` = '1' and line_id = #{lineId} order by level_no asc")
	public List<RMesNextBarcodePEcT> find5(@Param("lineId")Integer lineId);

	@Select("select ifnull(count(id),0) from r_mes_workorder_detail_t where status = '1' and line_id = #{lineInId} and level_no = #{levelNo}")
	public Integer find6(@Param("lineInId")Integer lineInId,
			@Param("levelNo")Integer levelNo);

	@Select("select id AS tempCurrentPlanId,order_number AS tempPlanNumber, printe_number AS tempPlanOnlineNumber,product_id AS tempProductionId, plan_id AS tempPlanId from r_mes_workorder_detail_t where ID = #{id} and status = '1' and line_id = #{lineId} and level_no = #{levelNo}")
	public RMesNextBarcodePEcT find7(@Param("id")Integer id,
			@Param("lineId")Integer lineId,
			@Param("levelNo")Integer levelNo);

	@Select("select cmlt.`NAME` as tempLabelTypeName,cmlm.label_rules AS tempLabelRules,cmlm.label_name AS tempLabelName,"
			+ "cmlt.label_vr AS tempLabelVr,cmlt.id AS tempLabelTypeId,"
			+ "cmlm.label_rules AS tempLabelHead,"
			+ "cmlm.label_rules AS tempLabelEnd,"
			+ "cmlt.label_vr AS  tempLabelYmd,"
			+ "cmlt.label_vr AS tempLabelCode "
			+ "from r_mes_workorder_detail_t rmwd,"
			+ "c_mes_production_t cmp,"
			+ "c_mes_label_manager_t cmlm,"
			+ "c_mes_label_type cmlt "
			+ "where rmwd.product_id = cmp.id "
			+ "and rmwd.BARCODE_RULE_ID = cmlm.id "
			+ "and cmlm.label_type_id = cmlt.id "
			+ "and rmwd.id = #{temp_current_plan_id}")
	public RMesNextBarcodePEcT find8(@Param("temp_current_plan_id")Integer temp_current_plan_id);

	@Select("select count(t.id) from r_mes_plan_print_t t where dt >= DATE_FORMAT(now(),'%Y-%m-%d')")
	public Integer find9();

	@Select("select print_flag AS tempPrintFlag,sn AS tempOldSnCode from r_mes_plan_print_t t,(select max(id) maxid from r_mes_plan_print_t where DATE_FORMAT(dt,'%Y-%m-%d') = DATE_FORMAT(now(),'%Y-%m-%d') ) tt where t.id = tt.maxid")
	public RMesNextBarcodePEcT find10();

	//@Select("select ifnull(max(id),0) from r_mes_plan_print_t where to_days(dt) = to_days(now())")
	@Select("select if(to_days(dt) = to_days(now()), id,'0') as targetid from r_mes_plan_print_t where id = (select max(id) from r_mes_plan_print_t)")
	public Integer find11();

	@Select("select substr(sn,length(sn) -length(#{temp_label_code}) + 1,length(#{temp_label_code})) AS tempOldSnNum,serial_no AS tmepCodeSerialMax from r_mes_plan_print_t where id = #{temp_plan_print_count}")
	public RMesNextBarcodePEcT find12(@Param("temp_label_code")String temp_label_code,
			@Param("temp_plan_print_count")Integer temp_plan_print_count);

	@Insert("INSERT INTO r_mes_plan_print_t ("
			+ "dt, sn, plan_id, line_id, "
			+ "production_id, serial_no, print_flag, work_order_id ) "
			+ "VALUES ("
			+ "now(), #{nextBarcode}, #{temp_plan_id}, #{lineId}, "
			+ "#{temp_production_id}, #{tmep_code_serial_max}+1, '0', #{temp_current_plan_id} )")
	public Integer insert1(@Param("nextBarcode")String nextBarcode,
			@Param("temp_plan_id")Integer temp_plan_id,
			@Param("lineId")Integer lineId,
			@Param("temp_production_id")Integer temp_production_id,
			@Param("tmep_code_serial_max")Integer tmep_code_serial_max,
			@Param("temp_current_plan_id")Integer temp_current_plan_id);

	@Update("update r_mes_workorder_detail_t set printe_number = printe_number + 1 where id = #{temp_current_plan_id}")
	public Integer update1(@Param("temp_current_plan_id")Integer temp_current_plan_id);

	@Insert("insert into r_mes_plan_print_t(dt,sn,plan_id,line_id,production_id,serial_no,print_flag,work_order_id) values(now(),#{nextBarcode},#{temp_plan_id},#{lineId},#{temp_production_id},1,'0',#{temp_current_plan_id})")
	public Integer insert2(@Param("nextBarcode")String nextBarcode,
			@Param("temp_plan_id")Integer temp_plan_id,
			@Param("lineId")Integer lineId,
			@Param("temp_production_id")Integer temp_production_id,
			@Param("temp_current_plan_id")Integer temp_current_plan_id);

	/**
	 * 查询产线条码生成类型
	 * @param lineId
	 * @return
	 */
	@Select("select CODE_TYPE from c_mes_line_t where id = #{lineId}")
	public Integer findLineCodeType(@Param("lineId")Integer lineId);

	/**
	 * 查询同日期同产品
	 * @return
	 */
	@Select("select print_flag AS tempPrintFlag,sn AS tempOldSnCode,SERIAL_NO as tmepCodeSerialMax from r_mes_plan_print_t t,(	SELECT max( print.id ) maxid FROM r_mes_plan_print_t print,r_mes_workorder_detail_t plan  WHERE  print.dt >= DATE_FORMAT(now(),'%Y-%m-%d') and plan.id=print.WORK_ORDER_ID and plan.STATUS=1 AND print.PRODUCTION_ID =  #{productId}) tt where t.id = tt.maxid")
	public RMesNextBarcodePEcT findSNYrqYcp(@Param("productId")Integer productId);

	@Select("SELECT plan.STATUS FROM r_mes_plan_print_t print,r_mes_workorder_detail_t plan where plan.id=print.WORK_ORDER_ID and print.SN=#{sn} limit 1")
	public Integer findPlanstatus(@Param("sn")String sn);

	@Select("select sn from r_mes_plan_print_t where to_days(dt) = to_days(now()) order by id desc limit 1")
	public String findMaxSn();

	/**
	 * 查询同日期不同产品
	 * @return
	 */
	@Select("select print_flag AS tempPrintFlag,sn AS tempOldSnCode from r_mes_plan_print_t t,(select max(id) maxid from r_mes_plan_print_t where DATE_FORMAT(dt,'%Y-%m-%d') = DATE_FORMAT(now(),'%Y-%m-%d')) tt where t.id = tt.maxid")
	public RMesNextBarcodePEcT findSNYrqNcp();

	/**
	 * 查询同日期不同产品(P表)
	 * @return
	 */
	@Select("select print_flag AS tempPrintFlag,sn AS tempOldSnCode from p_mes_plan_print_t t,(select max(id) maxid from p_mes_plan_print_t where DATE_FORMAT(dt,'%Y-%m-%d') = DATE_FORMAT(now(),'%Y-%m-%d')) tt where t.id = tt.maxid")
	public RMesNextBarcodePEcT findSNYrqNcpP();

	/**
	 * 查询不同日期同产品
	 * @return
	 */
	@Select("select print_flag AS tempPrintFlag,sn AS tempOldSnCode from r_mes_plan_print_t t,(select max(id) maxid from r_mes_plan_print_t where PRODUCTION_ID = #{productId}) tt where t.id = tt.maxid")
	public RMesNextBarcodePEcT findSNNrqYcp(@Param("productId")Integer productId);

	/**
	 * 查询不同日期不同产品
	 * @return
	 */
	@Select("select print_flag AS tempPrintFlag,sn AS tempOldSnCode from r_mes_plan_print_t t,(select max(id) maxid from r_mes_plan_print_t) tt where t.id = tt.maxid")
	public RMesNextBarcodePEcT findSNNrqNcp();



	public static void main(String[] args) {

		String a1 = "03ZPE002A09";
		String a = "03ZPE002A09A430000001ASDF";
		String b = "####";
		String c = "#######";
		System.out.println(Integer.parseInt(a.substring(a.length()-(b.length()+c.length()),(a.length()-(b.length()+c.length()))+c.length())));
//		String s = a.substring(a.length()-b.length(), a.length()-(b.length()+7));
	}


	//同日期同产品（查询R表）
	@Select("SELECT SERIAL_NO as tmepCodeSerialMax,print_flag AS tempPrintFlag,sn AS tempOldSnCode FROM r_mes_plan_print_t WHERE ID = (SELECT max( print.id ) maxid FROM r_mes_plan_print_t print,r_mes_workorder_detail_t plan  WHERE DATE_FORMAT( print.dt, '%Y-%m-%d' ) = DATE_FORMAT( now(),'%Y-%m-%d' ) and plan.id=print.WORK_ORDER_ID and plan.STATUS=1 AND print.PRODUCTION_ID =  #{productionId})")
	public RMesNextBarcodePEcT trqtcpcxrb(@Param("productionId")Integer productionId);

	//同日期同产品（查询P表）
	@Select("SELECT SERIAL_NO as tmepCodeSerialMax,print_flag AS tempPrintFlag,sn AS tempOldSnCode FROM P_mes_plan_print_t WHERE ID = (SELECT max( print.id ) maxid FROM r_mes_plan_print_t print,r_mes_workorder_detail_t plan  WHERE DATE_FORMAT( print.dt, '%Y-%m-%d' ) = DATE_FORMAT( now(),'%Y-%m-%d' ) and plan.id=print.WORK_ORDER_ID and plan.STATUS=1 AND print.PRODUCTION_ID =  #{productionId})")
	public RMesNextBarcodePEcT trqtcpcxpb(@Param("productionId")Integer productionId);

}
