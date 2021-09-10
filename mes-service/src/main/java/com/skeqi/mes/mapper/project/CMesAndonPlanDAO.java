package com.skeqi.mes.mapper.project;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.skeqi.mes.pojo.CMesLineT;
import com.skeqi.mes.pojo.CMesProductionT;
import com.skeqi.mes.pojo.RMesPlanT;
import com.skeqi.mes.pojo.project.CMesScheduling;

@MapperScan
@Component
public interface CMesAndonPlanDAO {


	@Select("<script>" +
			"SELECT andon.*,line.NAME as lineName,pro.PRODUCTION_TYPE as productionName FROM "
			+ "r_mes_plan_t andon	LEFT JOIN c_mes_line_t line ON andon.LINE_ID = line.ID "
			+ " LEFT JOIN c_mes_production_t pro ON andon.PRODUCTION_ID = pro.ID  where  andon.COMPLETE_FLAG not in (3,4) "
//			+ "and andon.PLAN_NUMBER>andon.COMPLETE_NUMBER"
			+ "<if test=\"name!='' and name!=null \"> and andon.PLAN_NAME=#{name} </if>"
			+ "<if test=\"lineName!='' and lineName!=null \"> and line.NAME=#{lineName} </if>"
			+ "<if test=\"systemDate!='' and systemDate!=null \"> and andon.dt=#{systemDate} </if>"
			+ " 	ORDER BY  andon.dt DESC   </script>")
	public List<RMesPlanT> findAndonPlan(@Param("name")String name,@Param("lineName")String lineName,@Param("systemDate") String systemDate);

	@Select("<script>" +
			"SELECT andon.*,line.NAME as lineName,pro.PRODUCTION_TYPE as productionName  FROM "
			+ "r_mes_plan_t andon	LEFT JOIN c_mes_line_t line ON andon.LINE_ID = line.ID "
			+ " LEFT JOIN c_mes_production_t pro ON andon.PRODUCTION_ID = pro.ID  where (andon.COMPLETE_FLAG=4 or andon.COMPLETE_FLAG=3) "
			+ "<if test=\"name!='' and name!=null \"> and andon.PLAN_NAME=#{name} </if>"
			+ "<if test=\"lineName!='' and lineName!=null \"> and line.NAME=#{lineName} </if>"
			+ "<if test=\"systemDate!='' and systemDate!=null \"> and andon.dt=#{systemDate} </if>"
			+ " ORDER BY  andon.dt DESC  </script>")
	public List<RMesPlanT> findcompleteAndonPlan(@Param("name")String name,@Param("lineName")String lineName,@Param("systemDate") String systemDate);


	@Select("select * from r_mes_plan_t where ID=#{id}")
	public RMesPlanT findAndonPlanByid(Integer id);


	@Insert("insert into r_mes_plan_t(DT,PLAN_START_TIME,PLAN_END_TIME,"
			+ "PLAN_NAME,PLAN_NUMBER,COMPLETE_NUMBER,REMAIND_NUMBER,OK_NUMBER,NG_NUMBER,LINE_ID,COMPLETE_FLAG,PRODUCTION_ID,PRODUCT_MARK)"
			+ "values(now(),#{planStartTime},#{planEndTime},#{planName},#{planNumber},"
			+ "0,0,0,0,#{lineId},0,#{productionId},#{productMark})")
	public Integer addAndonPlan(RMesPlanT plan);


/*	@Insert("insert into r_mes_plan_t(DT,PLAN_NAME,PLAN_NUMBER,LINE_ID,PRODUCTION_ID,COMPLETE_NUMBER,COMPLETE_FLAG)"
			+ "values(now(),#{planName},#{planNumber},#{lineId},#{productionId},0,1)")*/
	public Integer addAndonImportPlan(RMesPlanT plan);




	@Update("update r_mes_plan_t set PLAN_START_TIME=#{planStartTime},PLAN_END_TIME=#{planEndTime},PLAN_NAME=#{planName},PLAN_NUMBER=#{planNumber},"
			+ "LINE_ID=#{lineId},PRODUCTION_ID=#{productionId},PRODUCT_MARK=#{productMark} where ID=#{id}")
	public Integer updateAndonPlan(RMesPlanT plan);


	@Delete("delete from r_mes_plan_t where ID=#{id}")
	public Integer delAndonPlan(Integer id);


	@Update("update r_mes_plan_t set COMPLETE_FLAG=#{status} where ID=#{id}")
	public Integer updateStatus(@Param("id")Integer id,@Param("status")Integer status);


	//更新实际开始时间
	@Update("update r_mes_plan_t set ACTUAL_START_TIME = now() where ID=#{id}")
	public Integer updateActualStartTime(Integer id);

	//更新实际完成时间
	@Update("update r_mes_plan_t set ACTUAL_END_TIME = now() where ID=#{id}")
	public Integer updateActualEndTime(Integer id);


	//查询是否存在此产线
	@Select("select max(id) from c_mes_line_t where NAME=#{name}")
	public Integer findLineByName(@Param("name")String name);


	//查询是否存在此产品
	@Select("select max(id) AS id,PRODUCTION_VR AS productionVr    from c_mes_production_t where PRODUCTION_TYPE=#{name}")
	public CMesProductionT findProByName(@Param("name")String name);

	//根据产线筛选产品标记
	@Select("select PRODUCT_MARK from c_mes_line_t where ID=#{id}")
	public String findProductmark(Integer id);

	//根据计划名查询是否有计划
	@Select("select count(*)  from  r_mes_plan_t   where  PLAN_NAME=#{name}")
	public Integer findByName(@Param("name")String name);


	@Update(" update r_mes_plan_t  set  ACTUAL_END_TIME=#{planDate} ,COMPLETE_FLAG=#{planStatus},OPREATION_USER=#{userName} where ID=#{id}   ")
	public Integer updateStatusl(RMesPlanT plan);


	//修改查询是否有计划
	@Select("	select count(*)  from  r_mes_plan_t   where  PLAN_NAME=#{planName}  and  id!=#{id}")
	public Integer findByNamel(@Param("planName")String planName, @Param("id")Integer id);

	//根据产线ID查询产线名称
	@Select("select name from c_mes_line_t    where id=#{lineId}")
	public String findByLineId(@Param("lineId")Integer lineId);

	//根据产线id查询产品标记
	@Select("select PRODUCT_MARK  AS productMark  from c_mes_line_t    where id=#{lineId}")
	public CMesLineT  findLineByProType(@Param("lineId")Integer lineId);

	/**
	 * 查询产线总计划
	 * @param lineName
	 * @return
	 */
	@Select("SELECT" +
			"		line.`NAME` AS lineName," +
			"		sum( plan.PLAN_NUMBER ) AS totalPlanNumber," +
			"		sum( plan.COMPLETE_NUMBER ) AS totalCompleteNumber " +
			"	FROM" +
			"		r_mes_plan_t plan" +
			"		JOIN c_mes_line_t line ON plan.LINE_ID = line.id " +
			"	WHERE" +
			"		line.id = #{lineId}" +
			"		AND DATE_FORMAT( plan.DT, '%Y-%m-%d' ) = #{dt}" +
			"	GROUP BY" +
			"	line.`NAME`")
	public JSONObject findLineTotalPlan(
			@Param("lineId")Integer integer,
			@Param("dt")String dt);

	/**
	 * 通过产线名称查询产线信息
	 * @param lineName
	 * @return
	 */
	@Select("select * from c_mes_line_t where `NAME` = #{lineName}")
	public CMesLineT findLine(@Param("lineName")String lineName);

	/**
	 * 通过排班id查询排版信息
	 * @param schedulingId
	 * @return
	 */
	@Select("select * from c_mes_scheduling_t where id = #{schedulingId}")
	public CMesScheduling findScheduling(@Param("schedulingId")Integer schedulingId);

	/**
	  * 查询最近一次下线产品的简称
	  * @param lineId
	  * @param asd
	  * @return
	  */
	 @Select("SELECT ifnull(production.PRODUCTION_TRADEMARK,production1.PRODUCTION_TRADEMARK) FROM c_mes_andon_info_t andonInfo left JOIN c_mes_production_t production ON SUBSTRING_INDEX( andonInfo.sn, '|', 1 ) = production.PRODUCTION_TYPE left JOIN c_mes_production_t production1 ON andonInfo.PRODUCT_MARK = production1.PRODUCTION_TYPE WHERE andonInfo.id = ( SELECT max( andonInfo.id ) FROM c_mes_andon_info_t andonInfo JOIN c_mes_line_t line ON andonInfo.LINE_NAME = line.`NAME` WHERE line.id = #{lineId} AND andonInfo.WORK_ID IN ( SELECT id FROM c_mes_workorder_t WHERE DATE_FORMAT( SUBSTRING_INDEX( WORK_NAME, ' - ', 1 ), '%Y-%m-%d' ) = #{dt}))")
	 public String findRecentlyOfflineProductAbbreviation(@Param("lineId")Integer lineId,@Param("dt")String dt);

	/**
	 * 查询每个产品型号的总产量
	 * @param lineId
	 * @param dt
	 * @return
	 */
	@Select("SELECT" +
			"	production.PRODUCTION_SERIES AS productionSeries," +
			"	sum( plan.PLAN_NUMBER ) AS totalPlanNumber," +
			"	sum( plan.COMPLETE_NUMBER ) AS totalCompleteNumber " +
			"FROM" +
			"	r_mes_plan_t plan" +
			"	JOIN c_mes_production_t production ON plan.PRODUCTION_ID = production.ID " +
			"WHERE" +
			"	plan.LINE_ID = #{lineId}" +
			"	AND DATE_FORMAT( plan.DT, '%Y-%m-%d' ) = #{dt}" +
			"GROUP BY" +
			"	production.PRODUCTION_SERIES")
	public List<JSONObject> findProductModelTotalPlan(@Param("lineId")Integer lineId,@Param("dt")String dt);

	//根据产线，时间，产品查询是否有这个计划
	@Select("SELECT count(*) from  r_mes_plan_t  where LINE_ID=#{lineId}  and  DT=#{dt}  and PRODUCTION_ID=#{pid}")
	public Integer findLineDtByPro(@Param("lineId")Integer lineId,@Param("dt") String dt,@Param("pid") Integer pid);

	@Update("update c_mes_workorder_t set NUMBER=#{planNumber}  where PLAN_ID=#{id} ")
	public Integer updateWorkNumber(@Param("id")Integer id,@Param("planNumber") Integer planNumber);

	//当前故障列表710重写版
	public List<JSONObject> findStationStatus710(@Param("lineName")String lineName);

	//查询所有产线
	@Select("select id,name from c_mes_line_t ORDER BY id")
	public List<JSONObject> findAllLine();

}
