package com.skeqi.mes.mapper.all;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

import com.skeqi.mes.pojo.api.AssembleBoltPT;
import com.skeqi.mes.pojo.api.CheckAllRecipePT;
import com.skeqi.mes.pojo.api.GetcurrentBoltPT;


/**
 *
 * @name螺栓装配
 * @author Yinp
 * @date 2020年01月09日15:58:57
 *
 */
@Component
@MapperScan
public interface AssembleBoltPDao {


	@Select("SELECT plan.ID as id,plan.TOTAL_RECIPE_ID as totalRecipeId,plan.ROUTING_ID as routingId,pro.ST_ID as stId "
			+ " FROM r_mes_plan_print_t print,"
			+ " r_mes_workorder_detail_t plan, "
			+ " c_mes_routing_t routing,"
			+ "	c_mes_production_way_t pro "
			+ " WHERE print.WORK_ORDER_ID = plan.ID "
			+ "AND routing.ID = plan.ROUTING_ID "
			+ "AND routing.id = pro.ROUTING_ID "
			+ "AND print.sn = #{sn} ")
	List<CheckAllRecipePT> queryProductionWayList(@Param("sn")String sn);


	/**
	 *
	 * @param stationName
	 * @return stationType
	 */
	@Select("SELECT STATION_TYPE stationType FROM c_mes_station_t "
			+ "WHERE STATION_NAME= #{stationName}")
	public AssembleBoltPT find1(@Param("stationName")String stationName);

	/**
	 * 查询R表tracking数据
	 * @param lineName
	 * @param stationBoltName
	 * @param snBarcode
	 * @param stepNo
	 * @return materialName
	 * @return reworktimes
	 * @return aLimit
	 * @return tLimit
	 */
//	@Select("SELECT cmrd.material_name materialName,cmrd.reworktimes reworktimes,cmrd.a_limit aLimit,cmrd.t_limit tLimit "
//			+ "FROM c_mes_production_recipe_t cmpr" +
//			"	JOIN c_mes_recipe_datil_t cmrd ON cmrd.recipe_id = cmpr.recipe_id " +
//			"	JOIN r_mes_tracking_t rmt ON cmpr.production_id = rmt.production_id " +
//			"	JOIN c_mes_station_t cms ON cmpr.station_id = cms.id " +
//			"	JOIN c_mes_line_t cml ON cms.line_id = cml.id "
//			+ "where cml.name = #{lineName}"
//			+ "and cms.station_name = #{stationBoltName}"
//			+ "and rmt.sn = #{snBarcode}"
//			+ "and cmrd.stepno = #{stepNo}")
	@Select("SELECT datil.MATERIAL_NAME materialName, datil.REWORKTIMES reworktimes,datil.A_LIMIT aLimit,datil.T_LIMIT tLimit "
			+ "FROM c_mes_recipe_t recipe, "
			+ "c_mes_recipe_datil_t datil,"
			+ "c_mes_station_t sta "
			+ "WHERE recipe.ID = datil.RECIPE_ID  "
			+ "AND sta.ID = recipe.STATION_ID "
			+ "AND recipe.TOTAL_ID = #{totalRecipeId} "
			+ "AND sta.STATION_NAME = #{stationBoltName} "
			+ "AND datil.STEPNO=#{stepNo}")
	public AssembleBoltPT find2(@Param("totalRecipeId")Integer totalRecipeId,
			@Param("stationBoltName")String stationBoltName,
			@Param("snBarcode")String snBarcode,
			@Param("stepNo")String stepNo);

	/**
	 * 查询P表tracking数据
	 * @param lineName
	 * @param stationBoltName
	 * @param snBarcode
	 * @param stepNo
	 * @return materialName
	 * @return reworktimes
	 * @return aLimit
	 * @return tLimit
	 */
//	@Select("select distinct cmrd.material_name materialName,cmrd.reworktimes reworktimes,cmrd.a_limit a_Limit,cmrd.t_limit tLimit"
//			+ "from c_mes_production_recipe_t cmpr "
//			+ "JOIN c_mes_recipe_datil_t cmrd on cmrd.recipe_id=cmpr.recipe_id  "
//			+ "JOIN p_mes_tracking_t rmt on cmpr.production_id=rmt.production_id  "
//			+ "JOIN c_mes_station_t cms on cmpr.station_id=cms.id "
//			+ "JOIN c_mes_line_t cml on cms.line_id=cml.id "
//			+ "where cml.name = #{lineName}"
//			+ "and cms.station_name = #{stationBoltName}"
//			+ "and rmt.sn = #{snBarcode}"
//			+ "and cmrd.stepno = #{stepNo}")
	@Select("SELECT datil.MATERIAL_NAME materialName, datil.REWORKTIMES reworktimes,datil.A_LIMIT aLimit,datil.T_LIMIT tLimit "
			+ "FROM c_mes_recipe_t recipe, "
			+ "c_mes_recipe_datil_t datil,"
			+ "c_mes_station_t sta "
			+ "WHERE recipe.ID = datil.RECIPE_ID  "
			+ "AND sta.ID = recipe.STATION_ID "
			+ "AND recipe.TOTAL_ID = #{totalRecipeId} "
			+ "AND sta.STATION_NAME = #{stationBoltName} "
			+ "AND datil.STEPNO=#{stepNo}")
	public AssembleBoltPT find3(@Param("totalRecipeId")Integer totalRecipeId,
			@Param("stationBoltName")String stationBoltName,
			@Param("snBarcode")String snBarcode,
			@Param("stepNo")String stepNo);

	/**
	 * 查询R表tracking
	 * @param lineName
	 * @param stationBoltName
	 * @param snBarcode
	 * @param stepNo
	 * @return tempTLimitLower
	 * @return tempTLimitUpper
	 */
	@Select("select substr(cmrd.t_limit,1,instr(cmrd.t_limit,'-')-1) tempTLimitLower,"
			+ "substr(cmrd.t_limit,instr(cmrd.t_limit,'-')+1) tempTLimitUpper "
			+ "from c_mes_production_recipe_t cmpr "
			+ "join c_mes_recipe_datil_t cmrd on cmrd.recipe_id=cmpr.recipe_id "
			+ "join r_mes_tracking_t rmt on cmpr.production_id=rmt.production_id "
			+ "join c_mes_station_t cms on cmpr.station_id=cms.id "
			+ "join c_mes_line_t cml on cms.line_id=cml.id "
			+ "where cml.name=#{lineName} "
			+ "and cms.station_name=#{stationBoltName} "
			+ "and rmt.sn=#{snBarcode} "
			+ "and cmrd.stepno=#{stepNo}")
	public AssembleBoltPT find4(@Param("lineName")String lineName,
			@Param("stationBoltName")String stationBoltName,
			@Param("snBarcode")String snBarcode,
			@Param("stepNo")String stepNo);

	/**
	 * 查询P表tracking
	 * @param lineName
	 * @param stationBoltName
	 * @param snBarcode
	 * @param stepNo
	 * @return tempTLimitLower
	 * @return tempTLimitUpper
	 */
	@Select("select substr(cmrd.t_limit,1,instr(cmrd.t_limit,'-')-1) tempTLimitLower,"
			+ "substr(cmrd.t_limit,instr(cmrd.t_limit,'-')+1) tempTLimitUpper "
			+ "from c_mes_production_recipe_t cmpr "
			+ "join c_mes_recipe_datil_t cmrd on cmrd.recipe_id=cmpr.recipe_id "
			+ "join p_mes_tracking_t rmt on cmpr.production_id=rmt.production_id "
			+ "join c_mes_station_t cms on cmpr.station_id=cms.id "
			+ "join c_mes_line_t cml on cms.line_id=cml.id "
			+ "where cml.name=#{lineName} "
			+ "and cms.station_name=#{stationBoltName} "
			+ "and rmt.sn=#{snBarcode} "
			+ "and cmrd.stepno=#{stepNo}")
	public AssembleBoltPT find5(@Param("lineName")String lineName,
			@Param("stationBoltName")String stationBoltName,
			@Param("snBarcode")String snBarcode,
			@Param("stepNo")String stepNo);

	/**
	 * 查询R表tracking数据
	 * @param lineName
	 * @param stationBoltName
	 * @param snBarcode
	 * @param stepNo
	 * @return tempALimitLower
	 * @return tempALimitUpper
	 */
	@Select("select substr(cmrd.a_limit,1,instr(cmrd.a_limit,'-')-1) tempALimitLower,"
			+ "substr(cmrd.a_limit,instr(cmrd.a_limit,'-')+1) tempALimitUpper "
			+ "from c_mes_production_recipe_t cmpr "
			+ "join c_mes_recipe_datil_t cmrd on cmrd.recipe_id=cmpr.recipe_id "
			+ "join r_mes_tracking_t rmt on cmpr.production_id=rmt.production_id "
			+ "join c_mes_station_t cms on cmpr.station_id=cms.id "
			+ "join c_mes_line_t cml on cms.line_id=cml.id "
			+ "WHERE cml.name=#{lineName} "
			+ "and cms.station_name=#{stationBoltName} "
			+ "and rmt.sn=#{snBarcode} "
			+ "and cmrd.stepno=#{stepNo}")
	public AssembleBoltPT find6(@Param("lineName")String lineName,
			@Param("stationBoltName")String stationBoltName,
			@Param("snBarcode")String snBarcode,
			@Param("stepNo")String stepNo);

	/**
	 * 查询P表tracking数据
	 * @param lineName
	 * @param stationBoltName
	 * @param snBarcode
	 * @param stepNo
	 * @return tempALimitLower
	 * @return tempALimitUpper
	 */
	@Select("select substr(cmrd.a_limit,1,instr(cmrd.a_limit,'-')-1) tempALimitLower,"
			+ "substr(cmrd.a_limit,instr(cmrd.a_limit,'-')+1) tempALimitUpper "
			+ "from c_mes_production_recipe_t cmpr "
			+ "join c_mes_recipe_datil_t cmrd on cmrd.recipe_id=cmpr.recipe_id "
			+ "join p_mes_tracking_t rmt on cmpr.production_id=rmt.production_id "
			+ "join c_mes_station_t cms on cmpr.station_id=cms.id "
			+ "join c_mes_line_t cml on cms.line_id=cml.id "
			+ "WHERE cml.name=#{lineName} "
			+ "and cms.station_name=#{stationBoltName} "
			+ "and rmt.sn=#{snBarcode} "
			+ "and cmrd.stepno=#{stepNo}")
	public AssembleBoltPT find7(@Param("lineName")String lineName,
			@Param("stationBoltName")String stationBoltName,
			@Param("snBarcode")String snBarcode,
			@Param("stepNo")String stepNo);

	/**
	 *
	 * @param snBarcode
	 * @param stationBoltName
	 * @param tempMaterialName
	 * @return tempMinId
	 */
	@Select("select min(a.id) tempMinId from ("
				+ "select * from r_mes_bolt_t t "
				+ "where t.id in ("
					+ "select  tt.* from ("
						+ "select max(id) as id from r_mes_bolt_t "
						+ " where sn=#{snBarcode}  "
						+ "and st=#{stationBoltName}  "
						+ "and rework_flag='1' "
						+ "and bolt_name regexp #{tempMaterialName}||'_\\d{1,3}' group by bolt_name  "
					+ ") tt "
				+ ")  "
			+ ") a "
			+ "where a.r is null or a.r='ng'")
	public AssembleBoltPT find8(@Param("snBarcode")String snBarcode,
			@Param("stationBoltName")String stationBoltName,
			@Param("tempMaterialName")String tempMaterialName);

	/**
	 *
	 * @param snBarcode
	 * @param stationBoltName
	 * @param tempMaterialName
	 * @return tempMinId
	 */
	@Select("select min( a.id) tempMinId from (  select * from r_mes_bolt_t t  where t.id in ( select  tt.* from ( select max(id) as id from r_mes_bolt_t where sn=#{snBarcode}  and st=#{stationBoltName} and bolt_name regexp #{tempMaterialName}||'_\\d{1,3}' group by bolt_name ) tt) ) a where a.r is null or a.r='ng'")
	public AssembleBoltPT find9(@Param("snBarcode")String snBarcode,
			@Param("stationBoltName")String stationBoltName,
			@Param("tempMaterialName")String tempMaterialName);


	/**
	 *
	 * @param tempMinId
	 * @return tempT
	 * @return tempA
	 * @return tempR
	 * @return tempALimit
	 * @return tempTLimit
	 * @return tempBoltName
	 * @return tempBoltNum
	 * @return tempReworkFlag
	 * @return tempReworkSt
	 */
	@Select("select t tempT,a tempA,r tempR,a_limit tempALimit,t_limit tempTLimit,"
			+ "bolt_name tempBoltName,bolt_num tempBoltNum,rework_flag tempReworkFlag,"
			+ "rework_st tempReworkSt from r_mes_bolt_t "
			+ "where id=#{tempMinId}")
	public AssembleBoltPT find10(@Param("tempMinId")String tempMinId);

	/**
	 *
	 * @param aValues
	 * @param tValues
	 * @param tempAllResult
	 * @param emp
	 * @param tempMinId
	 */
	@Update("update r_mes_bolt_t set a=#{aValues},t=#{tValues},r=#{tempAllResult},wid=#{emp},dt=now() "
			+ "where id=#{tempMinId}")
	public void update1(@Param("aValues")String aValues,
			@Param("tValues")String tValues,
			@Param("tempAllResult")String tempAllResult,
			@Param("emp")String emp,
			@Param("tempMinId")String tempMinId);

	/**
	 *
	 * @param dx
	 */
	@Insert("insert into r_mes_bolt_t("
			+ "dt,transfer,bolt_mode,sn,st,t,a,r,"
			+ "t_limit,a_limit,wid,bolt_name,bolt_num,"
			+ "rework_flag,rework_st) "
			+ "values(now(),1,0,#{snBarcode},#{stationBoltName},"
			+ "#{tValues},#{aValues},#{tempAllResult},"
			+ "#{tempTLimit},#{tempALimit},#{emp},"
			+ "#{tempBoltName},#{tempBoltNum},#{tempReworkFlag},"
			+ "#{tempReworkSt})")
	public void insert1(AssembleBoltPT dx);

	/**
	 *
	 * @param snBarcode
	 * @param stationBoltName
	 * @param tempBoltName
	 * @return tempRemainBoltCount
	 */
	@Select("select count(id) tempRemainBoltCount from r_mes_bolt_t "
			+ "where sn=#{snBarcode} "
			+ "and st=#{stationBoltName} "
			+ "and bolt_name=#{tempBoltName} "
			+ "and r='ng' "
			+ "and rework_flag='1'")
	public AssembleBoltPT find11(@Param("snBarcode")String snBarcode,
			@Param("stationBoltName")String stationBoltName,
			@Param("tempBoltName")String tempBoltName);

	/**
	 *
	 * @param snBarcode
	 * @param stationBoltName
	 * @param tempMaterialName
	 * @return tempRemainBoltCount
	 */
	@Select("select count( a.id) tempRemainBoltCount from ( "
				+ "select * from r_mes_bolt_t t "
				+ "where t.id in ("
					+ "select  tt.* from ("
						+ "select max(id) as id from r_mes_bolt_t "
						+ "where sn=#{snBarcode} "
						+ "and rework_flag='1' "
						+ "and st=#{stationBoltName} "
						+ "and bolt_name regexp #{tempMaterialName}||'_\\d{1,3}' group by bolt_name"
					+ ")tt"
				+ " ) a "
			+ "where a.r is null or a.r='ng'")
	public AssembleBoltPT find12(@Param("snBarcode")String snBarcode,
			@Param("stationBoltName")String stationBoltName,
			@Param("tempMaterialName")String tempMaterialName);

	/**
	 *
	 * @param snBarcode
	 * @param stationBoltName
	 * @param tempMaterialName
	 * @return tempRemainBoltCount
	 */
	@Select("select count(a.id) tempRemainBoltCount from ("
				+ "select * from r_mes_bolt_t t  "
				+ "where t.id in ("
					+ "select  tt.* from ("
						+ "select max(id) as id from r_mes_bolt_t  "
						+ "where sn=#{snBarcode}  "
						+ "and reworkFlag='1' "
						+ "and st=#{stationBoltName}  "
						+ "and bolt_name regexp #{tempMaterialName}||'_\\d{1,3}' group by bolt_name  "
					+ ") tt "
				+ ")  "
			+ ") a "
			+ "where a.r is null or a.r='ng'")
	public AssembleBoltPT find13(@Param("snBarcode")String snBarcode,
			@Param("stationBoltName")String stationBoltName,
			@Param("tempMaterialName")String tempMaterialName);

	/**
	 *
	 * @param snBarcode
	 * @param stationBoltName
	 * @param tempBoltName
	 * @return tempRemainBoltCount
	 */
	@Select("select count(id) tempRemainBoltCount from r_mes_bolt_t "
			+ "where sn=#{snBarcode} "
			+ "and st=#{stationBoltName} "
			+ "and bolt_name=#{tempBoltName} "
			+ "and r='ng'")
	public AssembleBoltPT find14(@Param("snBarcode")String snBarcode,
			@Param("stationBoltName")String stationBoltName,
			@Param("tempBoltName")String tempBoltName);

	/**
	 *
	 * @param snBarcode
	 * @param stationBoltName
	 * @param tempMaterialName
	 * @return tempRemainBoltCount
	 */
	@Select("select count( a.id) tempRemainBoltCount from ("
				+ "select * from r_mes_bolt_t t "
				+ "where t.id in ("
					+ "select  tt.* from ("
						+ "select max(id) as id from r_mes_bolt_t "
						+ "where sn=#{snBarcode} "
						+ "and st=#{stationBoltName} "
						+ "and bolt_name regexp #{tempMaterialName}||'_\\d{1,3}' group by bolt_name "
					+ ") tt "
				+ ") "
			+ ") a where a.r is null or a.r='ng'")
	public AssembleBoltPT find15(@Param("snBarcode")String snBarcode,
			@Param("stationBoltName")String stationBoltName,
			@Param("tempMaterialName")String tempMaterialName);

	/**
	 *
	 * @param snBarcode
	 * @param stationBoltName
	 * @param tempMaterialName
	 * @return tempRemainBoltCount
	 */
	@Select("select count( a.id) tempRemainBoltCount from ( "
				+ "select * from r_mes_bolt_t t "
				+ "where t.id in ( "
					+ "select  tt.* from ( "
						+ "select max(id) as id from r_mes_bolt_t "
						+ "where sn=#{snBarcode} "
						+ "and st=#{stationBoltName} "
						+ "and bolt_name regexp #{tempMaterialName}||'_\\d{1,3}' group by bolt_name "
					+ ") tt "
				+ ") "
			+ ") a where a.r is null or a.r='ng'")
	public AssembleBoltPT find16(@Param("snBarcode")String snBarcode,
			@Param("stationBoltName")String stationBoltName,
			@Param("tempMaterialName")String tempMaterialName);

	/**
	 *
	 * @param snBarcode
	 * @param stationBoltName
	 * @param tempMaterialName
	 * @return tempRemainBoltCount
	 */
	@Select("select count( a.id) tempRemainBoltCount from ("
				+ "select *from r_mes_bolt_t t "
				+ "where t.id in ( "
					+ "select  tt.* from ( "
						+ "select max(id) as id from r_mes_bolt_t "
						+ "where sn=#{snBarcode} "
						+ "and rework_flag='1' "
						+ "and st=#{stationBoltName} "
						+ "and bolt_name regexp #{tempMaterialName}||'_\\d{1,3}' group by bolt_name "
					+ ") tt "
				+ ") "
			+ ") a where a.r is null or a.r='ng'")
	public AssembleBoltPT find17(@Param("snBarcode")String snBarcode,
			@Param("stationBoltName")String stationBoltName,
			@Param("tempMaterialName")String tempMaterialName);

	/**
	 *
	 * @param snBarcode
	 * @param stationBoltName
	 * @param tempMaterialName
	 * @return tempRemainBoltCount
	 */
	@Select("select count( a.id) tempRemainBoltCount from ( "
				+ "select * from r_mes_bolt_t t "
				+ "where t.id in ( "
					+ "select  tt.* from ( "
						+ "select max(id) as id from r_mes_bolt_t "
						+ "where sn=#{snBarcode} "
						+ "and st=#{stationBoltName} "
						+ "and bolt_name regexp #{tempMaterialName}||'_\\d{1,3}' group by bolt_name "
					+ ") tt"
				+ " ) "
			+ ") a where a.r is null or a.r='ng'")
	public AssembleBoltPT find18(@Param("snBarcode")String snBarcode,
			@Param("stationBoltName")String stationBoltName,
			@Param("tempMaterialName")String tempMaterialName);

	/**
	 *
	 * @param snBarcode
	 * @param stationBoltName
	 * @param tempMaterialName
	 * @return tempRemainBoltCount
	 */
	@Select("select count( a.id) tempRemainBoltCount from ( "
				+ "select * from r_mes_bolt_t t "
				+ "where t.id in ( "
					+ "select  tt.* from ( "
						+ "select max(id) as id from r_mes_bolt_t "
						+ "where sn=#{snBarcode} "
						+ "and rework_flag='1' "
						+ "and st=#{stationBoltName} "
						+ "and bolt_name regexp #{tempMaterialName}||'_\\d{1,3}'group by bolt_name "
					+ ") tt "
				+ ") "
			+ ") a where a.r is null or a.r='ng'")
	public AssembleBoltPT find19(@Param("snBarcode")String snBarcode,
			@Param("stationBoltName")String stationBoltName,
			@Param("tempMaterialName")String tempMaterialName);

	/**
	 *
	 * @param snBarcode
	 * @param stationBoltName
	 * @param tempMaterialName
	 * @return tempRemainBoltCount
	 */
	@Select("select count( a.id) tempRemainBoltCount from ( "
				+ "select * from r_mes_bolt_t t "
				+ "where t.id in ( "
					+ "select  tt.* from ( "
						+ "select max(id) as id from r_mes_bolt_t "
						+ "where sn=#{snBarcode} "
						+ "and st=#{stationBoltName} "
						+ "and bolt_name regexp #{tempMaterialName}||'_\\d{1,3}' group by bolt_name "
					+ ") tt "
				+ ") "
			+ ") a where a.r is null or a.r='ng'")
	public AssembleBoltPT find20(@Param("snBarcode")String snBarcode,
			@Param("stationBoltName")String stationBoltName,
			@Param("tempMaterialName")String tempMaterialName);


	//查询是否还有NG的螺栓没有OK
	@Select("select count(id) from r_mes_bolt_t where Y = 0 AND sn=#{snBarcode} AND st=#{stationName} AND bolt_name regexp #{tempMaterialName}||'_\\d{1,3}' ORDER BY ID")
	public Integer find1001(@Param("snBarcode")String snBarcode,
			@Param("stationName")String stationName,
			@Param("tempMaterialName")String tempMaterialName);

	//查询没有被OK的BOoltName
	@Select("select * from r_mes_bolt_t where id = ( select min(id) from r_mes_bolt_t where Y = 0  AND sn=#{snBarcode} AND st=#{stationName} AND bolt_name regexp #{tempMaterialName}||'_\\d{1,3}' )")
	public AssembleBoltPT find1002(@Param("snBarcode")String snBarcode,
			@Param("stationName")String stationName,
			@Param("tempMaterialName")String tempMaterialName);

	@Insert("insert into r_mes_bolt_t("
			+ "dt,transfer,bolt_mode,sn,st,t,a,r,t_limit,a_limit,wid,bolt_name,bolt_num,rework_flag,rework_st,Y) "
			+ "values(now(),1,0,#{sn},#{st},#{t},"
			+ "#{a},#{r},#{tLimit},"
			+ "#{aLimit},#{wId},#{boltName},"
			+ "#{boltNum},#{reworkFlag},#{reworkSt},"
			+ "#{Y})")
	public Integer insert1001(AssembleBoltPT dx);

	@Update("update r_mes_bolt_t set Y = #{Y} where SN = #{snBarcode} and ST = #{stationName} and BOLT_NAME = #{tempMaterialName}")
	public Integer update1001(@Param("snBarcode")String snBarcode,
			@Param("stationName")String stationName,
			@Param("tempMaterialName")String tempMaterialName,
			@Param("Y")Integer Y);

	//查询最大的已经ok最大的BOLT_NUM跟最大的BOLT_NUM
	@Select("SELECT (SELECT ifnull(max(BOLT_NUM),0) FROM R_MES_BOLT_T WHERE Y = 1 and SN = #{snBarcode} and ST = #{stationName} and bolt_name regexp #{tempMaterialName}||'_\\d{1,3}' ORDER BY ID) AS danQianBoltNum,(SELECT ifnull(max(BOLT_NUM),0) FROM R_MES_BOLT_T WHERE SN = #{snBarcode} and ST = #{stationName} and bolt_name regexp #{tempMaterialName}||'_\\d{1,3}' ORDER BY ID) AS MAXBoltNum")
	public AssembleBoltPT find1003(@Param("snBarcode")String snBarcode,
			@Param("stationName")String stationName,
			@Param("tempMaterialName")String tempMaterialName);

	@Select("select count(id) from r_mes_bolt_t  where Y = 0 AND sn=#{snBarcode} AND st=#{stationName} AND bolt_name regexp #{tempMaterialName}||'_\\d{1,3}'")
	public Integer find1004(@Param("snBarcode")String snBarcode,
			@Param("stationName")String stationName,
			@Param("tempMaterialName")String tempMaterialName);

	@Update("update r_mes_bolt_t set a=#{aValues},t=#{tValues},r=#{tempAllResult},wid=#{emp},dt=now() where SN = #{snBarcode} and ST = #{stationName} and BOLT_NAME = #{boltName}")
	public Integer update1002(@Param("aValues")String aValues,
			@Param("tValues")String tValues,
			@Param("tempAllResult")String tempAllResult,
			@Param("emp")String emp,
			@Param("snBarcode")String snBarcode,
			@Param("stationName")String stationName,
			@Param("boltName")String boltName);


	//校验螺栓

	//**根据sn查询总配方ID***//
	@Select("SELECT plan.TOTAL_RECIPE_ID "
			+ "FROM r_mes_plan_print_t print,"
			+ "r_mes_workorder_detail_t plan"
			+ " WHERE print.WORK_ORDER_ID = plan.ID "
			+ "AND print.sn = #{sn} limit 1")
	String  findAllPlan(@Param("sn")String sn);


	@Update("update r_mes_bolt_t set a=#{aValues},t=#{tValues},r=#{tempAllResult},wid=#{emp},dt=now() where SN = #{snBarcode} and ST = #{stationName} and BOLT_NAME = #{boltName} and R is null")
	public Integer update1002s(@Param("aValues")String aValues,
			@Param("tValues")String tValues,
			@Param("tempAllResult")String tempAllResult,
			@Param("emp")String emp,
			@Param("snBarcode")String snBarcode,
			@Param("stationName")String stationName,
			@Param("boltName")String boltName);

	@Select("SELECT COUNT(ID) FROM (SELECT * FROM r_mes_bolt_t WHERE sn=#{snBarcode} and st=#{stationBoltName} and  bolt_name regexp #{tempMaterialName}||'_\\d{1,3}' group by bolt_name ) TT WHERE TT.y=0")
	public Integer find15s(@Param("snBarcode")String snBarcode,
			@Param("stationBoltName")String stationBoltName,
			@Param("tempMaterialName")String tempMaterialName);

	/**
	 *
	 * @param stationBoltName
	 * @return tempStationTypetempStationType
	 */
	@Select("SELECT sta.STATION_TYPE as tempStationType FROM c_mes_station_t sta,c_mes_line_t line WHERE sta.LINE_ID=line.ID and sta.STATION_NAME=#{stationBoltName} and line.NAME=#{lineName}")
	public GetcurrentBoltPT finds1(@Param("stationBoltName")String stationBoltName,@Param("lineName")String lineName);

	/**
	 *
	 * @param lineName
	 * @param stationBoltName
	 * @param snBarcode
	 * @param stepNo
	 * @return tempMaterialName
	 * @return tempReworkTimes
	 */
	@Select("SELECT cmrd.material_name tempMaterialName,CASE cmrd.reworktimes WHEN '' THEN '0' ELSE cmrd.reworktimes end as tempReworkTimes "
			+ " FROM c_mes_recipe_t cmpr "
			+ "join c_mes_recipe_datil_t cmrd on cmrd.recipe_id = cmpr.id "
			+ "join c_mes_station_t cms on cmpr.station_id = cms.id "
			+ "join c_mes_line_t cml on cms.line_id = cml.id "
			+ "WHERE cml.NAME = #{lineName} "
			+ "AND cmpr.TOTAL_ID=#{totalId}"
			+ "AND cms.station_name = #{stationBoltName} "
			+ "AND cmrd.stepno = #{stepNo}")
	public GetcurrentBoltPT finds2(@Param("totalId")String totalId,
			@Param("lineName")String lineName,
			@Param("stationBoltName")String stationBoltName,
			@Param("snBarcode")String snBarcode,
			@Param("stepNo")String stepNo) ;

	/**
	 *
	 * @param lineName
	 * @param stationBoltName
	 * @param snBarcode
	 * @param stepNo
	 * @return tempMaterialName
	 * @return tempReworkTimes
	 */
	@Select("SELECT cmrd.material_name tempMaterialName,CASE cmrd.reworktimes WHEN '' THEN '0' ELSE cmrd.reworktimes end as tempReworkTimes "
			+ " FROM c_mes_production_recipe_t cmpr "
			+ "join c_mes_recipe_datil_t cmrd on cmrd.recipe_id = cmpr.recipe_id "
			+ "join c_mes_station_t cms on cmpr.station_id = cms.id "
			+ "join c_mes_line_t cml on cms.line_id = cml.id "
			+ "WHERE cml.NAME = lineName "
			+ "AND cms.station_name = #{stationBoltName} "
			+ "AND cmrd.stepno = #{stepNo}")
	public GetcurrentBoltPT finds3(@Param("totalId")String totalId,
			@Param("lineName")String lineName,
			@Param("stationBoltName")String stationBoltName,
			@Param("snBarcode")String snBarcode,
			@Param("stepNo")String stepNo) ;

	/**
	 *
	 * @param snBarcode
	 * @param stationBoltName
	 * @param tempMaterialName
	 * @return tempMinId
	 */
	@Select("select ifnull(min( a.id),0) tempMinId from ("
				+ "select * from r_mes_bolt_t t where t.id in ( "
					+ "select  tt.* from ( "
						+ "select max(id) as id from r_mes_bolt_t  "
						+ "where sn=#{snBarcode}  "
						+ "and st=#{stationBoltName}  "
						+ "and bolt_name regexp #{tempMaterialName}||'_\\d{1,3}'  group by bolt_name  "
					+ ") tt"
				+ ") "
			+ ") a where  a.r is null or a.r='ng';")
	public Integer finds4(@Param("snBarcode")String snBarcode,
			@Param("stationBoltName")String stationBoltName,
			@Param("tempMaterialName")String tempMaterialName);

	@Select("select ifnull(min( a.id),0) as tempMinId from ( "
				+ "select * from r_mes_bolt_t t where t.id in (  "
					+ "select  tt.* from ( "
						+ "select max(id) as id from r_mes_bolt_t  "
						+ "where sn=#{snBarcode}  "
						+ "and st=#{stationBoltName}  "
						+ "and bolt_name regexp #{tempMaterialName}||'_\\d{1,3}'  group by bolt_name  "
						+ ") tt "
					+ ") "
				+ ") a "
			+ "where  a.r is null or a.r='ng';")
	public Integer finds5(@Param("snBarcode")String snBarcode,
			@Param("stationBoltName")String stationBoltName,
			@Param("tempMaterialName")String tempMaterialName);

	/**
	 *
	 * @param tempMinId
	 * @return tempT
	 * @return tempA
	 * @return tempR
	 * @return tempALimit
	 * @return tempTLimit
	 * @return tempBoltName
	 * @return tempReworkFlag
	 * @return tempReworkSt
	 * @return tempBoltNum
	 */
	@Select("select t tempT,a tempA,r tempR,a_limit tempALimit,t_limit tempTLimit,"
			+ "bolt_name tempBoltName,rework_flag tempReworkFlag,rework_st tempReworkSt,bolt_num tempBoltNum "
			+ "from r_mes_bolt_t "
			+ "where id=#{tempMinId}")
	public GetcurrentBoltPT finds6(@Param("tempMinId")String tempMinId);

	/**
	 *
	 * @param snBarcode
	 * @param stationBoltName
	 * @param tempMaterialName
	 * @return tempRemainBoltCount
	 */
//	@Select("select ifnull(count( a.id),0) tempRemainBoltCount from ("
//				+ "select * from r_mes_bolt_t t where t.id in ( "
//					+ "select  tt.* from ("
//						+ "select max(id) as id from r_mes_bolt_t "
//						+ "where sn=#{snBarcode} "
//						+ "and rework_flag='1' "
//						+ "and st=#{stationBoltName} "
//						+ "and bolt_name regexp #{tempMaterialName}||'_\\d{1,3}' group by bolt_name "
//						+ ") tt"
//					+ ") "
//				+ ") a "
//			+ "where a.r is null or a.r='ng';")
	@Select("SELECT COUNT(ID) FROM (SELECT * FROM r_mes_bolt_t WHERE sn=#{snBarcode} and st=#{stationBoltName} and  bolt_name regexp #{tempMaterialName}||'_\\d{1,3}' group by bolt_name ) TT WHERE TT.y=0")
	public Integer finds7(@Param("snBarcode")String snBarcode,
			@Param("stationBoltName")String stationBoltName,
			@Param("tempMaterialName")String tempMaterialName);

	/**
	 *
	 * @param snBarcode
	 * @param stationBoltName
	 * @param tempMaterialName
	 * @return tempRemainBoltCount
	 */
//	@Select("select ifnull(count( a.id),0) tempRemainBoltCount from ("
//				+ "select * from r_mes_bolt_t t where t.id in ( "
//					+ "select  tt.* from ("
//						+ "select max(id) as id from r_mes_bolt_t "
//						+ "where sn=#{snBarcode} "
//						+ "and st=#{stationBoltName} "
//						+ "and bolt_name regexp #{tempMaterialName}||'_\\d{1,3}' group by bolt_name "
//						+ ") tt"
//					+ ") "
//				+ ") a "
//			+ "where a.r is null or a.r='ng';")
	@Select("SELECT COUNT(ID) FROM (SELECT * FROM r_mes_bolt_t WHERE sn=#{snBarcode} and st=#{stationBoltName} and  bolt_name regexp #{tempMaterialName}||'_\\d{1,3}' group by bolt_name ) TT WHERE TT.y=0")
	public Integer finds8(@Param("snBarcode")String snBarcode,
			@Param("stationBoltName")String stationBoltName,
			@Param("tempMaterialName")String tempMaterialName);

}
