package com.skeqi.mes.mapper.project;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

import com.skeqi.mes.pojo.project.CMesAndonFault;

@Component
@MapperScan
public interface CMesReportDAO {


	/**
	 * 产量统计
	* @author FQZ
	* @date 2020年4月27日下午1:50:40
	 */
	@Select("SELECT DATE_FORMAT(dt,'%d') as day,count(*) as num FROM "
			+ " c_mes_andon_info_t where month(dt)=#{id} and year(dt)=#{year} and LINE_NAME=#{lineName}"
			+ " GROUP BY DATE_FORMAT(dt,'%Y-%m-%d')")
	public List<Map<String,Object>> Reportyield(@Param("id")String id,@Param("lineName")String lineName,@Param("year")String year);


	@Select("SELECT DATE_FORMAT(dt,'%H')+1 as hour,count(*) as num FROM "
			+ " c_mes_andon_info_t where DATE_FORMAT(dt,'%Y-%m-%d')= #{id} and LINE_NAME=#{lineName}"
			+ " GROUP BY DATE_FORMAT(dt,'%H')")
	public List<Map<String,Object>> ReportyieldTwo(@Param("id")String id,@Param("lineName")String lineName);


	@Select("select max(YIELD_NUMBER) from c_mes_line_t where NAME=#{name}")
	public Integer findYield(@Param("name")String name);

	//获取指定日期的计划时间
	@Select("SELECT	sum(shift.PLAN_TIME) FROM	c_mes_scheduling_t sche,c_mes_shifts_team_t shift WHERE shift.ID = sche.SHIFT_ID  AND sche.DT = #{date} and sche.LINE_ID=#{lineid}")
	public Integer findSumTime(@Param("date")String date,@Param("lineid")Integer lineid);

	//获取指定日期的计划生产数量
	@Select("SELECT  PLAN_NUMBER FROM `c_mes_scheduling_t` sche  where  sche.DT = #{date} and sche.LINE_ID=#{lineid}")
	public Integer findNumber(@Param("date")String date,@Param("lineid")Integer lineid);

	//查询当天的所有创建故障时间
	@Select("SELECT ESTABLISH_DT FROM c_mes_andon_fault_t where LINE_NAME=#{lineName} and DATE_FORMAT(ESTABLISH_DT,'%Y-%m-%d')=#{date} and SOLVE_DT  is not null ")
	public List<String> findStartTime(@Param("lineName")String lineName,@Param("date")String date);

	//查询当天的所有创建故障时间
	@Select("SELECT SOLVE_DT FROM c_mes_andon_fault_t where LINE_NAME=#{lineName} and DATE_FORMAT(SOLVE_DT,'%Y-%m-%d')=#{date} and SOLVE_DT  is not null ")
	public List<String> findEndTime(@Param("lineName")String lineName,@Param("date")String date);


	//查询当天的所有故障id
	@Select("SELECT id FROM c_mes_andon_fault_t where LINE_NAME=#{lineName} and DATE_FORMAT(SOLVE_DT,'%Y-%m-%d')=#{date} and SOLVE_DT  is not null ")
	public List<Integer> findId(@Param("lineName")String lineName,@Param("date")String date);


	@Select("select ESTABLISH_DT,SOLVE_DT from  c_mes_andon_fault_t where ID=#{id}")
	public CMesAndonFault findFaultByid(Integer id);
	//根据id查询名称
	@Select("select NAME from c_mes_line_t where ID=#{id}")
	public String findLineName(Integer id);


	//查询产线的生产数量
/*	@Select("<script>"
			 + "SELECT   wo.COMPLETE_NUMBER number,line.`NAME` lineName,pro.PRODUCTION_NAME proName ,pro.PRODUCTION_SERIES  series,pro.PRODUCTION_TYPE proType  FROM c_mes_line_t  line  "
			+ " LEFT JOIN  r_mes_plan_t plan ON plan.LINE_ID = line.ID	LEFT JOIN c_mes_workorder_t wo on  wo.PLAN_ID =plan.id	    "
			+ " LEFT JOIN    c_mes_production_t  pro  ON  pro.ID = plan.PRODUCTION_ID  WHERE 	line.`PAIBAN_STATUS` = 0     and  pro.PRODUCTION_NAME  IS NOT NULL "
			 + "<if test=\"startTime!='' and startTime!=null and endTime!='' and endTime!=null \"> and dt BETWEEN #{startTime} and #{endTime}</if>"
			 + " GROUP BY  line.Name "
			  + "</script>")*/
	public List<Map<String,Object>> ProductionNUmberByLine(@Param("startTime")String startTime,@Param("endTime")String endTime);

   //根据产线名称查询产线上产品的生产数量
	@Select("SELECT   wo.COMPLETE_NUMBER number,line.`NAME` lineName,pro.PRODUCTION_TYPE proType  FROM c_mes_line_t  line  "
			+ " LEFT JOIN  r_mes_plan_t plan ON plan.LINE_ID = line.ID	LEFT JOIN c_mes_workorder_t wo on  wo.PLAN_ID =plan.id	    "
			+ " LEFT JOIN    c_mes_production_t  pro  ON  pro.ID = plan.PRODUCTION_ID  WHERE 	pro.`PRODUCTION_TYPE` = #{lineName}   	AND pro.PRODUCTION_NAME IS NOT NULL   "
			+ "  GROUP BY	pro.PRODUCTION_NAME")
	public List<Map<String,Object>> ProNumByLineName(@Param("lineName")String lineName);
}
